package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.LabeledTextField;
import de.volkswagen.f73.model.buttons.RemoveEmployeeButton;
import de.volkswagen.f73.model.TopBar;
import io.swagger.client.model.Employee;
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

/**
 * The class "EmployeeView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EmployeeView extends BorderPane {

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private final TopBar topBar;
    private final TableView<Employee> employeeTableView;
    private final HBox formHBox = new HBox();
    private final LabeledTextField nameLabeldTextField = new LabeledTextField("Name");
    private final LabeledTextField salaryLabeldTextField = new LabeledTextField("Gehalt", true);
    private final ComboBox<Employee.JobEnum> jobComboBox = new ComboBox<>();
    private final Button addButton = new Button("Mitarbeiter anlegen");
    private final Button clearButton = new Button("Clear");

    /**
     * Instantiates a new Employee view.
     */
    public EmployeeView() {
        this.topBar = new TopBar("Mitarbeiter - Übersicht");
        this.employeeTableView = new TableView();
        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.employeeTableView, this.formHBox);
        centerVBox.setMinWidth(1700 - 150.0);
        centerVBox.setMargin(this.employeeTableView, new Insets(15));
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
        this.employeeTableView.getColumns().addAll(
                tableColumnWithCellValue("Name", "name"),
                tableColumnWithCellValue("Job", "job"),
                tableColumnWithCellValue("Gehalt", "salary"),
                tableColumnWithCellValue("Entfernen","delete")
        );
        this.employeeTableView.setItems(this.employeeList);
    }

    private <T> TableColumn<Employee, T> tableColumnWithCellValue(String columnName, String valueName) {
        TableColumn<Employee, T> tableColumn = new TableColumn<>(columnName);
        tableColumn.setMinWidth(150);
        if(!valueName.equals("delete")){
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(valueName));
        }
        if (valueName.equals("salary")) {
            salaryCellFactory(tableColumn);
        }else if (valueName.equals("delete")){
            deleteButtonCellFactory(tableColumn);
        }
        return tableColumn;
    }

    private <T> void salaryCellFactory(TableColumn<Employee, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T number, boolean empty) {
                super.updateItem(number, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", number));
                }
            }
        });
    }

    private <T> void deleteButtonCellFactory(TableColumn<Employee, T> tableColumn) {
        Callback<TableColumn<Employee, T>, TableCell<Employee, T>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Employee, T> call(final TableColumn<Employee, T> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            TableRow<Employee> row = getTableRow();
                            Employee employee = row.getItem();
                            if(employee != null){
                                long id = employee.getId();
                                RemoveEmployeeButton btn = new RemoveEmployeeButton(id);
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
                this.salaryLabeldTextField,
                setJobComboBoxSettings(),
                this.addButton,
                this.clearButton);
        this.formHBox.setSpacing(5);
        this.formHBox.setAlignment(Pos.BOTTOM_CENTER);
        this.addButton.setMinSize(200, 60);
        this.addButton.setAlignment(Pos.CENTER);
        this.clearButton.setMinSize(200,60);
        this.clearButton.setAlignment(Pos.CENTER);
    }

    private VBox setJobComboBoxSettings() {
        Label label = new Label( "Job:");
        label.setMinSize(200, 60);
        ObservableList<Employee.JobEnum> items = FXCollections.observableArrayList();
        items.addAll(Employee.JobEnum.values());
        this.jobComboBox.setItems(items);
        this.jobComboBox.setMinSize(200, 60);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(2, 5, 0, 5));
        vBox.getChildren().addAll(label, this.jobComboBox);
        return vBox;
    }

    /**
     * Gets employee table view.
     *
     * @return the employee table view
     */
    public TableView<Employee> getEmployeeTableView() {
        return employeeTableView;
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
     * Gets salary labeld text field.
     *
     * @return the salary labeld text field
     */
    public LabeledTextField getSalaryLabeldTextField() {
        return salaryLabeldTextField;
    }

    /**
     * Gets job combo box.
     *
     * @return the job combo box
     */
    public ComboBox<Employee.JobEnum> getJobComboBox() {
        return jobComboBox;
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
     * Gets employee list.
     *
     * @return the employee list
     */
    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }
}
