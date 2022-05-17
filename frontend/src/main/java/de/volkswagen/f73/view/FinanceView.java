package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.TopBar;
import de.volkswagen.f73.model.enums.IncomeType;
import de.volkswagen.f73.model.enums.ExpanseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class "FinanceView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class FinanceView extends BorderPane {

    private final TopBar topBar;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DatePicker fromDatePicker = new DatePicker(LocalDate.now().minusDays(1));
    private final DatePicker untilDatePicker = new DatePicker(LocalDate.now().minusDays(1));
    private final Button selectSettingsButton = new Button("auswählen");
    private final Button incomeButton = new Button("Einnahmen");
    private final Button expanseButton = new Button("Ausgaben");
    private final HBox navigationHBox = new HBox(this.incomeButton, this.expanseButton);
    private final ComboBox<IncomeType> incomeTypeComboBox = new ComboBox<>();
    private final ComboBox<ExpanseType> expanseTypeComboBox = new ComboBox<>();
    private final HBox selectSettingsHBox = new HBox(this.fromDatePicker, this.untilDatePicker, this.incomeTypeComboBox,
            this.selectSettingsButton);
    private final Label shownDateRangesLabel = new Label();
    private final Pane centerContentPane = new Pane();
    private PieChart financePieChart;
    private StackedBarChart<String, Number> financeStackedBarChart;

    /**
     * Instantiates a new Finance view.
     */
    public FinanceView() {
        this.topBar = new TopBar("Finanzen - Übersicht");
        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.navigationHBox, this.selectSettingsHBox,
                this.shownDateRangesLabel, this.centerContentPane);
        centerVBox.setMinWidth(1500);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(1700, 800);
        this.setTop(this.topBar);
        this.shownDateRangesLabel.setText("Gesammt - Einnahmen vom "
                + this.fromDatePicker.getValue().format(this.dateFormatter) + " bis zum "
                + this.untilDatePicker.getValue().format(this.dateFormatter));
        SideBarView sideBarView = new SideBarView();
        SideBarViewController sideBarController = new SideBarViewController(sideBarView);
        this.setLeft(sideBarView);
        this.setCenter(centerVBox);
    }

    private void configureContent() {
        configureNavigationHBox();
        configureSettingsHBox();
    }

    private void configureNavigationHBox() {
        this.navigationHBox.setAlignment(Pos.CENTER);
        this.navigationHBox.setSpacing(30);
        this.expanseButton.setAlignment(Pos.CENTER);
        this.expanseButton.setMinSize(100,30);
        this.incomeButton.setAlignment(Pos.CENTER);
        this.incomeButton.setMinSize(100,30);
        this.incomeButton.setDisable(true);
    }

    private void configureSettingsHBox() {
        this.selectSettingsHBox.setSpacing(10);
        this.selectSettingsHBox.setPadding(new Insets(20,5,20,5));
        this.selectSettingsHBox.setAlignment(Pos.CENTER);
        this.fromDatePicker.setMinSize(200,30);
        this.untilDatePicker.setMinSize(200,30);
        setIncomeComboBoxSettings();
        setExpanseComboBoxSettings();
        this.selectSettingsButton.setMinSize(100, 30);
        this.selectSettingsButton.setAlignment(Pos.CENTER);
    }

    private void setIncomeComboBoxSettings() {
        ObservableList<IncomeType> items = FXCollections.observableArrayList();
        items.addAll(IncomeType.values());
        this.incomeTypeComboBox.setItems(items);
        this.incomeTypeComboBox.getSelectionModel().selectFirst();
        this.incomeTypeComboBox.setMinSize(150, 30);
    }

    private void setExpanseComboBoxSettings() {
        ObservableList<ExpanseType> items = FXCollections.observableArrayList();
        items.addAll(ExpanseType.values());
        this.expanseTypeComboBox.setItems(items);
        this.expanseTypeComboBox.getSelectionModel().selectFirst();
        this.expanseTypeComboBox.setMinSize(150, 30);
    }

    /**
     * Sets data in center content pane.
     *
     * @param isContentAvailable the is content available
     */
    public void setDataInCenterContentPane(boolean isContentAvailable) {
        if(isContentAvailable){
            HBox diagramBox = new HBox(this.financeStackedBarChart, this.financePieChart);
            this.financePieChart.setMinSize(700, 400);
            this.financeStackedBarChart.setMinSize(700, 400);
            this.centerContentPane.getChildren().clear();
            this.centerContentPane.getChildren().add(diagramBox);
        }else {
            Label noContentLabel = new Label("Keine Daten für den ausgewählten Zeitraum vorhanden");
            noContentLabel.setMinSize(1400, 800);
            noContentLabel.setAlignment(Pos.CENTER);
            this.centerContentPane.getChildren().clear();
            this.centerContentPane.getChildren().add(noContentLabel);
        }
    }

    /**
     * Gets from date picker.
     *
     * @return the from date picker
     */
    public DatePicker getFromDatePicker() {
        return fromDatePicker;
    }

    /**
     * Gets until date picker.
     *
     * @return the until date picker
     */
    public DatePicker getUntilDatePicker() {
        return untilDatePicker;
    }

    /**
     * Gets select settings button.
     *
     * @return the select settings button
     */
    public Button getSelectSettingsButton() {
        return selectSettingsButton;
    }

    /**
     * Gets income button.
     *
     * @return the income button
     */
    public Button getIncomeButton() {
        return incomeButton;
    }

    /**
     * Gets expanse button.
     *
     * @return the expanse button
     */
    public Button getExpanseButton() {
        return expanseButton;
    }

    /**
     * Gets income type combo box.
     *
     * @return the income type combo box
     */
    public ComboBox<IncomeType> getIncomeTypeComboBox() {
        return incomeTypeComboBox;
    }

    /**
     * Gets expanse type combo box.
     *
     * @return the expanse type combo box
     */
    public ComboBox<ExpanseType> getExpanseTypeComboBox() {
        return expanseTypeComboBox;
    }

    /**
     * Gets select settings h box.
     *
     * @return the select settings h box
     */
    public HBox getSelectSettingsHBox() {
        return selectSettingsHBox;
    }

    /**
     * Gets shown date ranges label.
     *
     * @return the shown date ranges label
     */
    public Label getShownDateRangesLabel() {
        return shownDateRangesLabel;
    }

    /**
     * Sets finance pie chart.
     *
     * @param financePieChart the finance pie chart
     */
    public void setFinancePieChart(PieChart financePieChart) {
        this.financePieChart = financePieChart;
    }

    /**
     * Sets finance stacked bar chart.
     *
     * @param financeStackedBarChart the finance stacked bar chart
     */
    public void setFinanceStackedBarChart(StackedBarChart<String, Number> financeStackedBarChart) {
        this.financeStackedBarChart = financeStackedBarChart;
    }
}
