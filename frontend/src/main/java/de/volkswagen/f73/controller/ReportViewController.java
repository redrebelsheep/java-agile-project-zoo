package de.volkswagen.f73.controller;

import de.volkswagen.f73.model.StallReport;
import de.volkswagen.f73.view.ReportView;
import io.swagger.client.ApiException;
import io.swagger.client.api.StallControllerApi;
import io.swagger.client.model.Stall;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The class "ReportViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class ReportViewController {

    private final ReportView reportView;
    private final StallControllerApi stallControllerApi = new StallControllerApi();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DecimalFormat decimalFormat = new DecimalFormat();
    private LocalDate dateToReport = LocalDate.now();
    private int visitorsAtDay = 0;
    private final List<StallReport> reportedStalls = new ArrayList<>();

    public ReportViewController(ReportView reportView) {
        this.reportView = reportView;
        this.decimalFormat.setMaximumFractionDigits(2);
        this.decimalFormat.setMinimumFractionDigits(2);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        this.decimalFormat.setDecimalFormatSymbols(dfs);
        configureContent();
    }

    private void configureContent() {
        setButtonsActions();
        setListener();
    }

    private void setButtonsActions() {
        setActionOnSelectDateButton();
        setActionOnConfirmVisitorInputButton();
        setActionOnSaveStallReportButton();
        setActionOnSaveReportButton();
    }

    private void setActionOnSelectDateButton() {
        this.reportView.getSelectDateButton().setOnAction((e) -> {
            if(this.reportView.getReportDatePicker().getValue().isBefore(LocalDate.now().plusDays(1))) {
                this.dateToReport = this.reportView.getReportDatePicker().getValue();
                this.visitorsAtDay = 0;
                this.reportView.getSelectedReportDateLabel().setText("Report für den " + this.dateToReport.format(this.dateFormatter));
                this.reportView.getInputFormHBox().setDisable(true);
                this.reportView.getSaveReportButton().setDisable(true);
                formClearAll();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fehlerhafter Tag");
                alert.setHeaderText(null);
                alert.setContentText("Der ausgewählte Tag kann nicht in der Zukunft liegen!");
                alert.showAndWait();
            }
        });
    }

    private void setActionOnConfirmVisitorInputButton() {
        this.reportView.getConfirmVisitorInputButton().setOnAction((e)->{
            this.visitorsAtDay = Integer.parseInt(this.reportView.getVisitorsLabeledTextField().getLabeledTextField().getText());
            if(this.reportView.getInputFormHBox().isDisabled()){
                getData();
                this.reportView.getInputFormHBox().setDisable(false);
                this.reportView.getSaveReportButton().setDisable(false);
            }
        });
    }

    private void setActionOnSaveStallReportButton() {
        this.reportView.getSaveStallReportButton().setOnAction((e)->{
            if(this.reportView.getStallsToReportListView().getSelectionModel().getSelectedItem() != null){
                saveNewStallReport();
            }else if(this.reportView.getStallsReportedListView().getSelectionModel().getSelectedItem() != null){
                updateReportedStall();
            }
        });
    }

    private void saveNewStallReport() {
        Stall stall = this.reportView.getStallsToReportListView().getSelectionModel().getSelectedItem();
        BigDecimal income = new BigDecimal(this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().getText());
        StallReport report = new StallReport(stall, income);
        this.reportedStalls.add(report);
        this.reportView.getStallsReportedList().add(stall);
        this.reportView.getStallsToReportList().remove(stall);
        this.reportView.getStallsToReportListView().getSelectionModel().clearSelection();
        this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().clear();
    }

    private void updateReportedStall() {
        Stall stall = this.reportView.getStallsReportedListView().getSelectionModel().getSelectedItem();
        this.reportedStalls.forEach((reportedStall)->{
            if(reportedStall.getStall().getId().equals(stall.getId())){
                BigDecimal income = new BigDecimal(this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().getText());
                reportedStall.setIncome(income);
            }
        });
    }

    private void setActionOnSaveReportButton() {
        this.reportView.getSaveReportButton().setOnAction((e)->{
            if(this.reportView.getStallsToReportList().size() == 0){

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nicht vollständig");
                alert.setHeaderText(null);
                alert.setContentText("Es haben nicht alle Verkaufsstände ihre Einnahmen gespeichert!");
                alert.showAndWait();
            }
        });
    }

    private void setListener() {
        setListenerOnToReportListView();
        setListenerOnReportedListView();
    }

    private void setListenerOnToReportListView() {
        this.reportView.getStallsToReportListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null){
                        if(this.reportedStalls.size() > 0) {
                            this.reportView.getStallsReportedListView().getSelectionModel().clearSelection();
                        }
                        this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().clear();
                    }
                });
    }

    private void setListenerOnReportedListView() {
        this.reportView.getStallsReportedListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    if(newValue != null){
                        if(this.reportView.getStallsToReportList().size() > 0) {
                            this.reportView.getStallsToReportListView().getSelectionModel().clearSelection();
                        }
                        AtomicReference<String> incomeString = new AtomicReference<>("");
                        Stall stall = this.reportView.getStallsReportedListView().getSelectionModel().getSelectedItem();
                        this.reportedStalls.forEach((reportedStall)->{
                            if(reportedStall.getStall().getId().equals(stall.getId())){
                                String reportedIncome = this.decimalFormat.format(reportedStall.getIncome());
                                incomeString.set(reportedIncome);
                            }
                        });
                        this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().setText(incomeString.get());
                    }
                });
    }

    private void getData() {
        try {
            List<Stall> stalls = this.stallControllerApi.getAllStalls();
            if(stalls != null){
                this.reportView.getStallsToReportList().addAll(stalls);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void formClearAll() {
        this.reportView.getVisitorsLabeledTextField().getLabeledTextField().clear();
        this.reportView.getStallsReportedList().clear();
        this.reportView.getStallsToReportList().clear();
        this.reportView.getStallIncomeLabeledTextField().getLabeledTextField().clear();
        this.reportedStalls.clear();
    }
}
