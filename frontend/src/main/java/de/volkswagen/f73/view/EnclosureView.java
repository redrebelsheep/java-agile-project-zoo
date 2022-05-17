package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.listcells.EmployeeListCell;
import de.volkswagen.f73.model.buttons.RemoveEnclosureButton;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.model.Animal;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Enclosure;
import de.volkswagen.f73.model.LabeledTextField;
import de.volkswagen.f73.model.TopBar;
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

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The class "EnclosureView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EnclosureView extends BorderPane {

    private final ObservableList<Enclosure> enclosureList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeesInEnclosureList = FXCollections.observableArrayList();
    private final TopBar topBar;
    private final TableView<Enclosure> enclosureTableView = new TableView<>();
    private final HBox formHBox = new HBox();
    private final LabeledTextField nameLabeldTextField = new LabeledTextField("Name");
    private final LabeledTextField maintenanceCostsLabeldTextField = new LabeledTextField("Unterhaltskosten", true);
    private final ComboBox<Employee> employeeComboBox = new ComboBox<>();
    private final Button addEmployeeButton = new Button("Tierpfleger\nhinzufügen");
    private final ListView<Employee> employeeListView = new ListView<>();
    private final Button removeEmployeeButton = new Button("Tierpfleger\nentfernen");
    private final Button addButton = new Button("Gehege\nanlegen");
    private final Button clearButton = new Button("Clear");

    /**
     * Instantiates a new Enclosure view.
     */
    public EnclosureView() {
        this.topBar = new TopBar("Gehege - Übersicht");

        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.enclosureTableView, this.formHBox);
        centerVBox.setMinWidth(1700 - 150.0);
        centerVBox.setMargin(this.enclosureTableView, new Insets(15));
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
        this.enclosureTableView.getColumns().addAll(
                tableColumnWithCellValue("Name", "name"),
                tableColumnWithCellValue("Anzahl Tiere", "animals"),
                tableColumnWithCellValue("Unterhaltskosten", "maintenanceCosts"),
                tableColumnWithCellValue("Tierpfleger", "staff"),
                tableColumnWithCellValue("Entfernen", "delete")
        );
        this.enclosureTableView.setItems(this.enclosureList);
    }

    private <T> TableColumn<Enclosure, T> tableColumnWithCellValue(String columnName, String valueName) {
        TableColumn<Enclosure, T> tableColumn = new TableColumn<>(columnName);
        tableColumn.setMinWidth(150);
        if(!valueName.equals("delete")){
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(valueName));
        }
        switch (valueName) {
            case "maintenanceCosts":
                maintenanceCostsCellFactory(tableColumn);
                break;
            case "staff":
                staffCellFactory(tableColumn);
                break;
            case "animals":
                animalsCellFactory(tableColumn);
                break;
            case "delete":
                deleteButtonCellFactory(tableColumn);
                break;
        }
        return tableColumn;
    }

    private <T> void maintenanceCostsCellFactory(TableColumn<Enclosure, T> tableColumn) {
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

    private <T> void staffCellFactory(TableColumn<Enclosure, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    EmployeeControllerApi api = new EmployeeControllerApi();
                    List<Long> list = (List<Long>) item;
                    AtomicReference<String> text = new AtomicReference<>("");
                    if(item != null && list.size() > 0 ) {
                        list.forEach((employeeId)->{
                            try {
                                Employee employee = api.getEmployeeById(employeeId);
                                if(text.get().equals("")){
                                    text.set(employee.getName());
                                }else{
                                    String tmp = text.get();
                                    text.set(tmp + ", " + employee.getName());
                                }
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    setText(text.get());
                }
            }
        });
    }

    private <T> void animalsCellFactory(TableColumn<Enclosure, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    List<Animal> list = (List<Animal>) item;
                    AtomicReference<String> text = new AtomicReference<>("0");
                    if(list.size() > 0) {
                        text.set(list.size() + "");
                    }
                    setText(text.get());
                }
            }
        });
    }

    private <T> void deleteButtonCellFactory(TableColumn<Enclosure, T> tableColumn) {
        Callback<TableColumn<Enclosure, T>, TableCell<Enclosure, T>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Enclosure, T> call(final TableColumn<Enclosure, T> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            TableRow<Enclosure> row = getTableRow();
                            Enclosure enclosure = row.getItem();
                            if(enclosure != null){
                                long id = enclosure.getId();
                                RemoveEnclosureButton btn = new RemoveEnclosureButton(id);
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
                this.nameLabeldTextField,
                this.maintenanceCostsLabeldTextField,
                setEmployeeComboBoxSettings(),
                this.addEmployeeButton,
                this.employeeListView,
                this.removeEmployeeButton,
                this.addButton, this.clearButton);
        this.formHBox.setSpacing(15);
        this.formHBox.setAlignment(Pos.BOTTOM_CENTER);
        setButtonSettings(this.addButton);
        setButtonSettings(this.addEmployeeButton);
        setButtonSettings(this.removeEmployeeButton);
        setButtonSettings(this.clearButton);
        this.employeeListView.setItems(this.employeesInEnclosureList);
        this.employeeListView.setMaxSize(200, 100);
        this.employeeListView.setMinSize(200, 100);
        this.employeeListView.setCellFactory(param -> new EmployeeListCell());
    }

    private VBox setEmployeeComboBoxSettings() {
        Label label = new Label("Tierpfleger:");
        label.setMinSize(200, 60);
        this.employeeComboBox.setItems(this.employeeList);
        this.employeeComboBox.setMinSize(200, 60);
        this.employeeComboBox.setButtonCell(new EmployeeListCell());
        this.employeeComboBox.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>(){
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new EmployeeListCell();
            }
        });
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(2, 5, 0, 5));
        vBox.getChildren().addAll(label, this.employeeComboBox);
        return vBox;
    }

    private void setButtonSettings(Button button) {
        button.setMinSize(200, 60);
        button.setAlignment(Pos.CENTER);
    }

    /**
     * Gets employees in enclosure list.
     *
     * @return the employees in enclosure list
     */
    public ObservableList<Employee> getEmployeesInEnclosureList() {
        return employeesInEnclosureList;
    }

    /**
     * Gets enclosure table view.
     *
     * @return the enclosure table view
     */
    public TableView<Enclosure> getEnclosureTableView() {
        return enclosureTableView;
    }

    /**
     * Gets name labeld text field.
     *
     * @return the name labeld text field
     */
    public LabeledTextField getNameLabeldTextField() {
        return nameLabeldTextField;
    }

    /**
     * Gets maintenance costs labeld text field.
     *
     * @return the maintenance costs labeld text field
     */
    public LabeledTextField getMaintenanceCostsLabeldTextField() {
        return maintenanceCostsLabeldTextField;
    }

    /**
     * Gets employee combo box.
     *
     * @return the employee combo box
     */
    public ComboBox<Employee> getEmployeeComboBox() {
        return employeeComboBox;
    }

    /**
     * Gets add employee button.
     *
     * @return the add employee button
     */
    public Button getAddEmployeeButton() {
        return addEmployeeButton;
    }

    /**
     * Gets remove employee button.
     *
     * @return the remove employee button
     */
    public Button getRemoveEmployeeButton() {
        return removeEmployeeButton;
    }

    /**
     * Gets add button.
     *
     * @return the add button
     */
    public Button getAddButton() {
        return addButton;
    }

    /**
     * Gets clear button.
     *
     * @return the clear button
     */
    public Button getClearButton() {
        return clearButton;
    }

    /**
     * Gets employee list view.
     *
     * @return the employee list view
     */
    public ListView<Employee> getEmployeeListView() {
        return employeeListView;
    }

    /**
     * Gets enclosure list.
     *
     * @return the enclosure list
     */
    public ObservableList<Enclosure> getEnclosureList() {
        return enclosureList;
    }

    /**
     * Gets employee list.
     *
     * @return the employee list
     */
    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }
}
