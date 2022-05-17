package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.LabeledTextField;
import de.volkswagen.f73.model.TopBar;
import de.volkswagen.f73.model.buttons.RemoveStallButton;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Stall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The class "StallView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class StallView extends BorderPane {

    private final ObservableList<Stall> stallList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private final TopBar topBar;
    private final TableView<io.swagger.client.model.Stall> stallTableView;
    private final HBox formHBox = new HBox();
    private final LabeledTextField nameLabeldTextField = new LabeledTextField("Name");
    private final ComboBox<Stall.TypeEnum> stallTypeComboBox = new ComboBox<>();
    private final LabeledTextField operatingCostsLabelLabel = new LabeledTextField("Unterhaltskosten", true);
    private final ComboBox<Employee> employeeComboBox = new ComboBox<>();
    private final Button addButton = new Button("Verkaufsstand\nanlegen");
    private final Button clearButton = new Button("Clear");

    public StallView() {
        this.topBar = new TopBar("Verkaufsstände - Übersicht");
        this.stallTableView = new TableView();
        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.stallTableView, this.formHBox);
        centerVBox.setMinWidth(1700 - 150.0);
        centerVBox.setMargin(this.stallTableView, new Insets(15));
        centerVBox.setMargin(this.formHBox, new Insets(15));
        this.setMinSize(1700, 800);
        this.setTop(this.topBar);
        SideBarView sideBarView = new SideBarView();
        SideBarViewController sideBarController = new SideBarViewController(sideBarView);
        this.setLeft(sideBarView);
        this.setCenter(centerVBox);
    }

    private void configureContent() {
        configureTableView();
        configureForm();
    }

    @SuppressWarnings("unchecked")
    private void configureTableView() {
        this.stallTableView.getColumns().addAll(
                tableColumnWithCellValue("Verkaufskategorie", "type"),
                tableColumnWithCellValue("Unterhaltskosten", "operatingCost"),
                tableColumnWithCellValue("Verkäufer", "seller"),
                tableColumnWithCellValue("Entfernen", "delete")
        );
        this.stallTableView.setItems(this.stallList);
    }

    private <T> TableColumn<Stall, T> tableColumnWithCellValue(String columnName, String valueName) {
        TableColumn<Stall, T> tableColumn = new TableColumn<>(columnName);
        tableColumn.setMinWidth(150);
        if(valueName != "delete"){
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(valueName));
        }
        switch (valueName) {
            case "operatingCost":
                operatingCostCellFactory(tableColumn);
                break;
            case "seller":
                sellerCellFactory(tableColumn);
                break;
            case "delete":
                deleteButtonCellFactory(tableColumn);
                break;
        }
        return tableColumn;
    }

    private <T> void operatingCostCellFactory(TableColumn<Stall, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });
    }

    private <T> void sellerCellFactory(TableColumn<Stall, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Employee seller = null;
                    if (item != null) {
                        EmployeeControllerApi api = new EmployeeControllerApi();
                        Long id = (Long) item;
                        try {
                            seller = api.getEmployeeById(id);
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                    AtomicReference<String> text = new AtomicReference<>("");
                    if(seller != null){
                        text.set(seller.getName());
                    }
                    setText(text.get());
                }
            }
        });
    }

    private <T> void deleteButtonCellFactory(TableColumn<Stall, T> tableColumn) {
        Callback<TableColumn<Stall, T>, TableCell<Stall, T>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Stall, T> call(final TableColumn<Stall, T> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            TableRow<Stall> row = getTableRow();
                            Stall stall = row.getItem();
                            if(stall != null){
                                long id = stall.getId();
                                RemoveStallButton btn = new RemoveStallButton(id);
                                setGraphic(btn);
                            }
                        }
                    }
                };
            }
        };
        tableColumn.setCellFactory(cellFactory);
    }

    private void configureForm() {
        this.formHBox.getChildren().addAll(
                this.operatingCostsLabelLabel,
                setComboBoxSettings("Verkaufskategorie", this.stallTypeComboBox, FXCollections.observableArrayList(Stall.TypeEnum.values())),
                setComboBoxSettings("Verkäufer", this.employeeComboBox, this.employeeList),
                this.addButton,
                this.clearButton);
        this.formHBox.setSpacing(5);
        this.formHBox.setAlignment(Pos.BOTTOM_CENTER);
        this.addButton.setMinSize(200, 60);
        this.addButton.setAlignment(Pos.CENTER);
        this.clearButton.setMinSize(200, 60);
        this.clearButton.setAlignment(Pos.CENTER);
    }

    private <T> VBox setComboBoxSettings(String promptText, ComboBox<T> comboBox, ObservableList<T> items) {
        Label label = new Label(promptText + ":");
        label.setMinSize(200, 60);
        comboBox.setItems(items);
        comboBox.setMinSize(200, 60);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(2, 5, 0, 5));
        vBox.getChildren().addAll(label, comboBox);
        return vBox;
    }

    public TableView<Stall> getStallTableView() {
        return stallTableView;
    }

    public LabeledTextField getNameLabeledTextField() {
        return nameLabeldTextField;
    }

    public ComboBox<Stall.TypeEnum> getStallTypeComboBox() {
        return stallTypeComboBox;
    }

    public LabeledTextField getOperatingCostsLabelLabel() {
        return operatingCostsLabelLabel;
    }

    public ComboBox<Employee> getEmployeeComboBox() {
        return employeeComboBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public ObservableList<Stall> getStallList() {
        return stallList;
    }

    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }
}
