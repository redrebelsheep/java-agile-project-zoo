package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.LabeledTextField;
import de.volkswagen.f73.model.TopBar;
import de.volkswagen.f73.model.listcells.StallListCell;
import io.swagger.client.model.Stall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class "ReportView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class ReportView extends BorderPane {

    private final  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final TopBar topBar;
    private final Label selectedReportDateLabel = new Label("Report für den " + LocalDate.now().format(this.dateFormatter));
    private final DatePicker reportDatePicker = new DatePicker();
    private final Button selectDateButton = new Button("auswählen");
    private final LabeledTextField visitorsLabeledTextField = new LabeledTextField("Besucher");
    private final Button confirmVisitorInputButton = new Button("bestätigen");
    private final HBox inputFormHBox = new HBox();
    private final ListView<Stall> stallsToReportListView = new ListView<>();
    private final ObservableList<Stall> stallsToReportList = FXCollections.observableArrayList();
    private final VBox reportStallsFormVBox = new VBox();
    private final LabeledTextField stallIncomeLabeledTextField = new LabeledTextField("Verkaufsstandeinnahmen", true);
    private final Button saveStallReportButton = new Button("übernehmen");
    private final ListView<Stall> stallsReportedListView = new ListView<>();
    private final ObservableList<Stall> stallsReportedList = FXCollections.observableArrayList();
    private final Button saveReportButton = new Button("Report speichern");

    public ReportView() {
        this.topBar = new TopBar("Report - Übersicht");
        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.selectedReportDateLabel, labeledReportDatePickerVBBox(), visitorsHBox(), this.inputFormHBox, this.saveReportButton);
        centerVBox.setMinWidth(1700 - 150.0);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(1700, 800);
        this.setTop(this.topBar);
        SideBarView sideBarView = new SideBarView();
        SideBarViewController sideBarController = new SideBarViewController(sideBarView);
        this.setLeft(sideBarView);
        this.setCenter(centerVBox);
    }

    private VBox labeledReportDatePickerVBBox(){
        Label reportDatePickerLabel = new Label("Wählen Sie ein Datum aus:");
        reportDatePickerLabel.setMinSize(400,60);
        this.reportDatePicker.setMinSize(200,60);
        this.reportDatePicker.setValue(LocalDate.now());
        this.reportDatePicker.setShowWeekNumbers(true);
        this.reportDatePicker.getEditor().setDisable(true);
        this.selectDateButton.setMinSize(200, 60);
        this.selectDateButton.setAlignment(Pos.CENTER);
        HBox datePickerButtonHBox = new HBox(this.reportDatePicker, this.selectDateButton);
        datePickerButtonHBox.setSpacing(20);
        datePickerButtonHBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(reportDatePickerLabel, datePickerButtonHBox);
        vBox.setPadding(new Insets(2, 5, 0, 5));
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private HBox visitorsHBox() {
        HBox hBox = new HBox(this.visitorsLabeledTextField, this.confirmVisitorInputButton);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(20);
        this.confirmVisitorInputButton.setMinSize(200,60);
        this.confirmVisitorInputButton.setAlignment(Pos.CENTER);
        return hBox;
    }

    private void configureContent() {
        configureInputFormHBox();
        configureStallsToReportListView();
        configureStallsReportVBox();
        configureStallsReportedListView();
        configureSaveReportButton();
    }

    private void configureInputFormHBox() {
        this.inputFormHBox.getChildren().addAll(listViewWithLabel("Brauchen Eintrag", this.stallsToReportListView),
                this.reportStallsFormVBox, listViewWithLabel("Erledigt", this.stallsReportedListView));
        this.inputFormHBox.setSpacing(20);
        this.inputFormHBox.setAlignment(Pos.CENTER);
        this.inputFormHBox.setDisable(true);
        this.inputFormHBox.setPadding(new Insets(20, 5, 20, 5));
    }

    private VBox listViewWithLabel(String labelText, ListView<Stall> listView){
        Label label = new Label(labelText);
        label.setMinSize(200, 60);
        VBox vBox = new VBox(label, listView);
        vBox.setSpacing(2);
        return vBox;
    }

    private void configureStallsToReportListView() {
        this.stallsToReportListView.setItems(this.stallsToReportList);
        this.stallsToReportListView.setMinSize(200,300);
        this.stallsToReportListView.setCellFactory(param -> new StallListCell());
    }

    private void configureStallsReportVBox() {
        this.reportStallsFormVBox.getChildren().addAll(this.stallIncomeLabeledTextField, this.saveStallReportButton);
        this.reportStallsFormVBox.setAlignment(Pos.CENTER);
        this.reportStallsFormVBox.setSpacing(20);
        this.saveStallReportButton.setMinSize(200, 60);
        this.saveStallReportButton.setAlignment(Pos.CENTER);
    }

    private void configureStallsReportedListView() {
        this.stallsReportedListView.setItems(this.stallsReportedList);
        this.stallsReportedListView.setMinSize(200,300);
        this.stallsReportedListView.setCellFactory(param -> new StallListCell());
    }


    private void configureSaveReportButton() {
        this.saveReportButton.setAlignment(Pos.CENTER);
        this.saveReportButton.setMinSize(200,60);
        this.saveReportButton.setDisable(true);
    }

    public Label getSelectedReportDateLabel() {
        return selectedReportDateLabel;
    }

    public DatePicker getReportDatePicker() {
        return reportDatePicker;
    }

    public Button getSelectDateButton() {
        return selectDateButton;
    }

    public LabeledTextField getVisitorsLabeledTextField() {
        return visitorsLabeledTextField;
    }

    public Button getConfirmVisitorInputButton() {
        return confirmVisitorInputButton;
    }

    public HBox getInputFormHBox() {
        return inputFormHBox;
    }

    public ListView<Stall> getStallsToReportListView() {
        return stallsToReportListView;
    }

    public ObservableList<Stall> getStallsToReportList() {
        return stallsToReportList;
    }

    public LabeledTextField getStallIncomeLabeledTextField() {
        return stallIncomeLabeledTextField;
    }

    public Button getSaveStallReportButton() {
        return saveStallReportButton;
    }

    public ListView<Stall> getStallsReportedListView() {
        return stallsReportedListView;
    }

    public ObservableList<Stall> getStallsReportedList() {
        return stallsReportedList;
    }

    public Button getSaveReportButton() {
        return saveReportButton;
    }
}
