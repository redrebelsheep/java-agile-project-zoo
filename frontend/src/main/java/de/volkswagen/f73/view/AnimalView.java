package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.LabeledTextField;
import de.volkswagen.f73.model.TopBar;
import de.volkswagen.f73.model.buttons.RemoveAnimalButton;
import de.volkswagen.f73.model.listcells.EmployeeListCell;
import de.volkswagen.f73.model.listcells.EnclosureListCell;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.api.EnclosureControllerApi;
import io.swagger.client.model.Animal;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Enclosure;
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
 * The class "AnimalView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class AnimalView extends BorderPane {

    private final ObservableList<Animal> animalList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private final ObservableList<Enclosure> enclosureList = FXCollections.observableArrayList();
    private final TopBar topBar;
    private final TableView<Animal> animalTableView;
    private final HBox formHBox = new HBox();
    private final LabeledTextField nameLabeldTextField = new LabeledTextField("Name");
    private final LabeledTextField speciesLabeldTextField = new LabeledTextField("Tierart");
    private final LabeledTextField subsistenceCostsLabelLabel = new LabeledTextField("Verpflegungskosten", true);
    private final ComboBox<Enclosure> enclosureComboBox = new ComboBox<>();
    private final ComboBox<Employee> employeeComboBox = new ComboBox<>();
    private final Button addButton = new Button("Tier anlegen");
    private final Button clearButton = new Button("Clear");

    /**
     * Instantiates a new Animal view.
     */
    public AnimalView() {
        this.topBar = new TopBar("Tiere - Übersicht");
        this.animalTableView = new TableView();
        configureView();
        configureContent();
    }

    /**
     *  Configure the view
     */
    private void configureView() {
        VBox centerVBox = new VBox(this.animalTableView, this.formHBox);
        centerVBox.setMinWidth(1700 - 150.0);
        centerVBox.setMargin(this.animalTableView, new Insets(15));
        centerVBox.setMargin(this.formHBox, new Insets(15));
        this.setMinSize(1700, 800);
        this.setTop(this.topBar);
        SideBarView sideBarView = new SideBarView();
        SideBarViewController sideBarController = new SideBarViewController(sideBarView);
        this.setLeft(sideBarView);
        this.setCenter(centerVBox);
    }

    /**
     *  Configure the content
     */
    private void configureContent() {
        configureTableView();
        configureForm();
    }

    /**
     *  Configure the tableview
     */
    @SuppressWarnings("unchecked")
    private void configureTableView() {
        this.animalTableView.getColumns().addAll(
                tableColumnWithCellValue("Name", "name"),
                tableColumnWithCellValue("Tierart", "species"),
                tableColumnWithCellValue("Verpflegungskosten", "subsistenceCosts"),
                tableColumnWithCellValue("Tierarzt", "vet"),
                tableColumnWithCellValue("Gehege", "enclosure"),
                tableColumnWithCellValue("Entfernen", "delete")
        );
        this.animalTableView.setItems(this.animalList);
    }

    /**
     *
     * @param columnName the columnname
     * @param valueName the cellvaluename
     * @param <T>
     * @return tablecolumn with cellfactory
     */
    private <T> TableColumn<Animal, T> tableColumnWithCellValue(String columnName, String valueName) {
        TableColumn<Animal, T> tableColumn = new TableColumn<>(columnName);
        tableColumn.setMinWidth(150);
        if(!valueName.equals("delete")){
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(valueName));
        }
        switch (valueName) {
            case "subsistenceCosts":
                subsistenceCostsCellFactory(tableColumn);
                break;
            case "vet":
                staffCellFactory(tableColumn);
                break;
            case "enclosure":
                enclosureCellFactory(tableColumn);
                break;
            case "delete":
                deleteButtonCellFactory(tableColumn);
                break;
        }
        return tableColumn;
    }

    private <T> void subsistenceCostsCellFactory(TableColumn<Animal, T> tableColumn) {
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

    private <T> void staffCellFactory(TableColumn<Animal, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Employee vet = null;
                    if (item != null) {
                        EmployeeControllerApi api = new EmployeeControllerApi();
                        vet = null;
                        try {
                            Long id = (Long) item;
                            vet = api.getEmployeeById(id);
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                    AtomicReference<String> text = new AtomicReference<>("Kein Tierarzt");
                    if(vet != null){
                        text.set(vet.getName());
                    }
                    setText(text.get());
                }
            }
        });
    }

    private <T> void enclosureCellFactory(TableColumn<Animal, T> tableColumn) {
        tableColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    EnclosureControllerApi api = new EnclosureControllerApi();
                    Enclosure enclosure = null;
                    if (item != null) {
                        try {
                            enclosure = api.getEnclosureById((Long)item);
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                    AtomicReference<String> text = new AtomicReference<>("Kein Gehege");
                    if(enclosure != null){
                        text.set(enclosure.getName());
                    }
                    setText(text.get());
                }
            }
        });
    }

    private <T> void deleteButtonCellFactory(TableColumn<Animal, T> tableColumn) {
        Callback<TableColumn<Animal, T>, TableCell<Animal, T>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Animal, T> call(final TableColumn<Animal, T> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            TableRow<Animal> row = getTableRow();
                            Animal animal = row.getItem();
                            if(animal != null){
                                long id = animal.getId();
                                RemoveAnimalButton btn = new RemoveAnimalButton(id);
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
                this.speciesLabeldTextField,
                this.subsistenceCostsLabelLabel,
                setComboBoxSettings("Tierarzt", this.employeeComboBox, this.employeeList),
                setComboBoxSettings("Gehege", this.enclosureComboBox, this.enclosureList),
                this.addButton,
                this.clearButton);
        this.formHBox.setSpacing(5);
        this.formHBox.setAlignment(Pos.BOTTOM_CENTER);
        this.addButton.setMinSize(200, 60);
        this.addButton.setAlignment(Pos.CENTER);
        this.clearButton.setMinSize(200,60);
        this.clearButton.setAlignment(Pos.CENTER);
        this.employeeComboBox.setButtonCell(new EmployeeListCell());
        this.employeeComboBox.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>(){
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new EmployeeListCell();
            }
        });
        this.enclosureComboBox.setButtonCell(new EnclosureListCell());
        this.enclosureComboBox.setCellFactory(new Callback<ListView<Enclosure>, ListCell<Enclosure>>(){
            @Override
            public ListCell<Enclosure> call(ListView<Enclosure> param) {
                return new EnclosureListCell();
            }
        });
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

    /**
     * Gets clear button.
     *
     * @return the clear button
     */
    public Button getClearButton() {
        return clearButton;
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
     * Gets animal table view.
     *
     * @return the animal table view
     */
    public TableView<Animal> getAnimalTableView() {
        return animalTableView;
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
     * Gets species labeld text field.
     *
     * @return the species labeld text field
     */
    public LabeledTextField getSpeciesLabeldTextField() {
        return speciesLabeldTextField;
    }

    /**
     * Gets subsistence costs label label.
     *
     * @return the subsistence costs label label
     */
    public LabeledTextField getSubsistenceCostsLabelLabel() {
        return subsistenceCostsLabelLabel;
    }

    /**
     * Gets enclosure combo box.
     *
     * @return the enclosure combo box
     */
    public ComboBox<Enclosure> getEnclosureComboBox() {
        return enclosureComboBox;
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
     * Gets animal list.
     *
     * @return the animal list
     */
    public ObservableList<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * Gets employee list.
     *
     * @return the employee list
     */
    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * Gets enclosure list.
     *
     * @return the enclosure list
     */
    public ObservableList<Enclosure> getEnclosureList() {
        return enclosureList;
    }
}
