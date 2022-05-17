package de.volkswagen.f73.controller;

import de.volkswagen.f73.model.enums.ExpanseType;
import de.volkswagen.f73.model.enums.IncomeType;
import de.volkswagen.f73.view.FinanceView;
import io.swagger.client.ApiException;
import io.swagger.client.api.ZooAccountControllerApi;
import io.swagger.client.api.ZooHistoryControllerApi;
import io.swagger.client.model.ZooAccount;
import io.swagger.client.model.ZooHistory;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The class "FinanceViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class FinanceViewController {

    private final FinanceView financeView;
    private final ZooAccountControllerApi zooAccountControllerApi = new ZooAccountControllerApi();
    private final List<ZooAccount> zooAccountList = new ArrayList<>();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate fromDate = LocalDate.now().minusDays(1);
    private LocalDate untilDate = LocalDate.now().minusDays(1);
    private ExpanseType selectedExpanseType = ExpanseType.ALL;
    private IncomeType selectedIncomeType = IncomeType.ALL;
    private boolean isIncomeShown = true;

    public FinanceViewController(FinanceView financeView){
        this.financeView = financeView;
        configureContent();
    }

    private void configureContent() {
        placeContentInCenter();
        setActionsOnButtons();
    }

    private void placeContentInCenter() {
        if(checkIfContentIsAvailable()){
            createCharts();
            this.financeView.setDataInCenterContentPane(true);
        }else {
            this.financeView.setDataInCenterContentPane(false);
        }
    }

    private boolean checkIfContentIsAvailable() {
        this.zooAccountList.clear();
        try {
            List<ZooAccount> tmpZooAccountList = this.zooAccountControllerApi.getAllEntries();
            if(tmpZooAccountList != null){
                tmpZooAccountList.forEach(account -> {
                    if(checkDateIsBetween(account.getDate().toString())){
                        this.zooAccountList.add(account);
                    }
                });
            }else {
                return false;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void fillAccountList(List<ZooAccount> accounts){
        accounts.add(new ZooAccount().id(1L).date(org.threeten.bp.LocalDate.now().minusDays(1))
                .bankBalance(new BigDecimal("17.73")).bookingType(ZooAccount.BookingTypeEnum.EINKOMMEN)
                .usageOfBooking("Eintrittsgelder").valueOfBooking(new BigDecimal("1500")));
    }

    private boolean checkDateIsBetween(String dateToCheck) {
        return LocalDate.parse(dateToCheck).isAfter(this.fromDate) && LocalDate.parse(dateToCheck).isBefore(this.untilDate);
    }

    private void createCharts() {
        createPieChart();
        createStackedBarChart();
    }

    private void createPieChart() {

    }

    private void createStackedBarChart() {
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Datum");

        NumberAxis yAxis = new NumberAxis();
        if(this.isIncomeShown){
            yAxis.setLabel("Einnahmen");
            setXAxisLabelWithIncomeType(xAxis);
        }else {
            yAxis.setLabel("Ausgaben");
            setXAxisLabelWithExpanseType(xAxis);
        }
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        fillBarChartWithData(barChart);

        this.financeView.setFinanceStackedBarChart(barChart);
    }

    private void setXAxisLabelWithIncomeType(CategoryAxis xAxis) {
        switch (this.selectedIncomeType){
            case ALL:
                xAxis.getCategories().addAll("Gesammt", "Eintrittsgeld", "Spenden", "Verkäufe");
                break;
            case ENTRANCE_FEE:
                xAxis.getCategories().add("Eintrittsgeld");
                break;
            case DONATES:
                xAxis.getCategories().add("Spenden");
                break;
            case SALES:
                xAxis.getCategories().add("Verkäufe");
                break;
        }
    }

    private void setXAxisLabelWithExpanseType(CategoryAxis xAxis) {
        switch (this.selectedExpanseType){
            case ALL:
                xAxis.getCategories().addAll("Gesammt", "Gehege", "Tiere", "Mitarbeiter");
                break;
            case ENCLOSURES:
                xAxis.getCategories().add("Gehege");
                break;
            case ANIMALS:
                xAxis.getCategories().add("Tiere");
                break;
            case EMPLOYEES:
                xAxis.getCategories().add("Mitarbeiter");
                break;
        }
    }

    private void fillBarChartWithData(StackedBarChart<String, Number> barChart) {

    }

    private void setActionsOnButtons() {
        setActionOnSelectSettingsButton();
        setActionOnIncomeButton();
        setActionOnExpanseButton();
    }

    private void setActionOnSelectSettingsButton() {
        this.financeView.getSelectSettingsButton().setOnAction((e) -> {
           this.fromDate = this.financeView.getFromDatePicker().getValue();
           this.untilDate = this.financeView.getUntilDatePicker().getValue();
           String shownText;
           if(this.isIncomeShown){
               this.selectedIncomeType = this.financeView.getIncomeTypeComboBox().getValue();
               shownText = this.selectedIncomeType.toString() + " - Einnahmen vom "
                       + this.fromDate.format(this.dateFormatter) + " bis zum " +
                       this.untilDate.format(this.dateFormatter);
           }else {
               this.selectedExpanseType = this.financeView.getExpanseTypeComboBox().getValue();
               shownText = this.selectedExpanseType.toString() + " - Ausgaben vom "
                       + this.fromDate.format(this.dateFormatter) + " bis zum " +
                       this.untilDate.format(this.dateFormatter);
           }
            this.financeView.getShownDateRangesLabel().setText(shownText);
            placeContentInCenter();
        });
    }

    private void setActionOnIncomeButton() {
        this.financeView.getIncomeButton().setOnAction((e) -> {
           this.financeView.getExpanseButton().setDisable(false);
           this.financeView.getIncomeButton().setDisable(true);
            this.financeView.getSelectSettingsHBox().getChildren().remove(2);
            this.financeView.getSelectSettingsHBox().getChildren().add(2, this.financeView.getIncomeTypeComboBox());
            this.financeView.getIncomeTypeComboBox().getSelectionModel().selectFirst();
            this.financeView.setDataInCenterContentPane(false);
            this.selectedIncomeType = IncomeType.ALL;
            this.isIncomeShown = true;
            this.financeView.getShownDateRangesLabel().setText(this.selectedIncomeType.toString() + " - Einnahmen vom "
                    + this.fromDate.format(this.dateFormatter) + " bis zum " +
                    this.untilDate.format(this.dateFormatter));
        });
    }

    private void setActionOnExpanseButton() {
        this.financeView.getExpanseButton().setOnAction((e) -> {
            this.financeView.getExpanseButton().setDisable(true);
            this.financeView.getIncomeButton().setDisable(false);
            this.financeView.getSelectSettingsHBox().getChildren().remove(2);
            this.financeView.getSelectSettingsHBox().getChildren().add(2, this.financeView.getExpanseTypeComboBox());
            this.financeView.getExpanseTypeComboBox().getSelectionModel().selectFirst();
            this.financeView.setDataInCenterContentPane(false);
            this.selectedExpanseType = ExpanseType.ALL;
            this.isIncomeShown = false;
            this.financeView.getShownDateRangesLabel().setText(this.selectedExpanseType.toString() + " - Ausgaben vom "
                    + this.fromDate.format(this.dateFormatter) + " bis zum " +
                    this.untilDate.format(this.dateFormatter));
        });
    }

}
