package de.volkswagen.f73.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.volkswagen.f73.view.EnclosureView;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.api.EnclosureControllerApi;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Enclosure;
import io.swagger.client.model.EnclosureDTO;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The class "EnclosureViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EnclosureViewController {

    private final EnclosureControllerApi enclosureControllerApi = new EnclosureControllerApi();
    private final EmployeeControllerApi employeeControllerApi = new EmployeeControllerApi();
    private final EnclosureView enclosureView;
    private final String addButtonAddText = "Gehege\nanlegen";
    private final String addButtonUpdateText = "Gehege\nbearbeiten";

    public EnclosureViewController(EnclosureView enclosureView) {
        this.enclosureView = enclosureView;
        configureContent();
    }

    private void configureContent() {
        getEmployeeData();
        getActualData();
        setButtonsActions();
        setListener();
    }

    private void getEmployeeData() {
        try {
            List<Employee> list = this.employeeControllerApi.getAllWithJobEmployee("Tierpfleger");
            if(list != null){
                this.enclosureView.getEmployeeList().addAll(list);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setButtonsActions() {
        setActionOnAddButton();
        setActionOnRemoveEmployeeButton();
        setActionOnAddEmployeeButton();
        setActionOnClearButton();
    }

    private void setActionOnAddButton() {
        this.enclosureView.getAddButton().setOnAction((e) -> {
            if(this.enclosureView.getAddButton().getText().equals(this.addButtonAddText)){
                    addButtonAddFunction();
            }else if (this.enclosureView.getAddButton().getText().equals(this.addButtonUpdateText)){
                    addButtonUpdateFunction();
            }
        });
    }

    private void addButtonAddFunction() {
        if (checkNeededFieldsAreFilled()) {
            String name = this.enclosureView.getNameLabeldTextField().getLabeledTextField().getText();
            BigDecimal maintenanceCosts = new BigDecimal(this.enclosureView.getMaintenanceCostsLabeldTextField().getLabeledTextField().getText());
            EnclosureDTO enclosureToAdd = new EnclosureDTO().name(name).maintenanceCosts(maintenanceCosts);
            if (this.enclosureView.getEmployeesInEnclosureList().size() > 0) {
                List<Long> staffToEnclosureSet = new ArrayList();
                this.enclosureView.getEmployeesInEnclosureList().forEach(e -> staffToEnclosureSet.add(e.getId()));
                enclosureToAdd.setStaff(staffToEnclosureSet);
            }
            System.out.println(enclosureToAdd);
            try {
                this.enclosureControllerApi.addEnclosure(enclosureToAdd);
                formClearAll();
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
            getActualData();
        }else {
            alertNeededFieldsAreInvalid();
        }
    }

    private void addButtonUpdateFunction() {
        if (checkNeededFieldsAreFilled()) {
            String name = this.enclosureView.getNameLabeldTextField().getLabeledTextField().getText();
            BigDecimal maintenanceCosts = new BigDecimal(this.enclosureView.getMaintenanceCostsLabeldTextField().getLabeledTextField().getText());
            Long id = this.enclosureView.getEnclosureTableView().getSelectionModel().getSelectedItem().getId();
            List<Long> animals = this.enclosureView.getEnclosureTableView().getSelectionModel().getSelectedItem().getAnimals();
            EnclosureDTO enclosureToUpdate = new EnclosureDTO().id(id).name(name).maintenanceCosts(maintenanceCosts).animals(animals);
            if (this.enclosureView.getEmployeesInEnclosureList().size() > 0) {
                List<Long> staffToEnclosureSet = new ArrayList();
                this.enclosureView.getEmployeesInEnclosureList().forEach(e -> staffToEnclosureSet.add(e.getId()));
                enclosureToUpdate.setStaff(staffToEnclosureSet);
            }else {
                List<Long> staffs = new ArrayList<>();
                enclosureToUpdate.setStaff(staffs);
            }
            System.out.println(enclosureToUpdate);
            ObjectMapper mapper = new ObjectMapper();
            try {
                System.out.println(mapper.writeValueAsString(enclosureToUpdate));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                this.enclosureControllerApi.putEnclosure(enclosureToUpdate);
                formClearAll();
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
            getActualData();
        }else {
            alertNeededFieldsAreInvalid();
        }
    }

    private void alertNeededFieldsAreInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fehlende Daten");
        alert.setHeaderText(null);
        String neededFields = "Name und Unterhaltskosten";
        alert.setContentText("Bitte füllen Sie die benötigten Felder aus:\n" + neededFields);

        alert.showAndWait();
    }

    /**
     return true if the needed fields are filled
     */
    private boolean checkNeededFieldsAreFilled() {
        return !this.enclosureView.getNameLabeldTextField().getLabeledTextField().getText().isEmpty() &&
                !this.enclosureView.getMaintenanceCostsLabeldTextField().getLabeledTextField().getText().isEmpty();
    }

    private void setActionOnRemoveEmployeeButton() {
        this.enclosureView.getRemoveEmployeeButton().setOnAction((e) -> {
            Employee employeeToRemove = this.enclosureView.getEmployeeListView().getSelectionModel().getSelectedItem();
            this.enclosureView.getEmployeesInEnclosureList().remove(employeeToRemove);
        });
    }

    private void setActionOnAddEmployeeButton() {
        this.enclosureView.getAddEmployeeButton().setOnAction((e) -> {
            Employee employeeToAdd = this.enclosureView.getEmployeeComboBox().getSelectionModel().getSelectedItem();
            if (!this.enclosureView.getEmployeesInEnclosureList().contains(employeeToAdd) && employeeToAdd != null) {
                this.enclosureView.getEmployeesInEnclosureList().add(employeeToAdd);
            }
        });
    }

    private void setActionOnClearButton() {
        this.enclosureView.getClearButton().setOnAction((e) -> {
            formClearAll();
        });
    }

    private void formClearAll() {
        this.enclosureView.getNameLabeldTextField().getLabeledTextField().clear();
        this.enclosureView.getMaintenanceCostsLabeldTextField().getLabeledTextField().clear();
        this.enclosureView.getEmployeeComboBox().getSelectionModel().clearSelection();
        this.enclosureView.getEnclosureTableView().getSelectionModel().clearSelection();
        this.enclosureView.getEmployeesInEnclosureList().clear();
    }

    private void getActualData() {
        try {
            List<Enclosure> enclosures = this.enclosureControllerApi.getAllEnclosures();
            if(enclosures != null){
                this.enclosureView.getEnclosureList().clear();
                this.enclosureView.getEnclosureList().addAll(enclosures);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        this.enclosureView.getEnclosureTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(this.enclosureView.getEnclosureTableView().getSelectionModel().isEmpty()){
                this.enclosureView.getAddButton().setText(this.addButtonAddText);
            }else{
                this.enclosureView.getEmployeesInEnclosureList().clear();
                this.enclosureView.getEmployeeComboBox().getSelectionModel().clearSelection();
                Enclosure selectedEnclosure = this.enclosureView.getEnclosureTableView().getSelectionModel().getSelectedItem();
                this.enclosureView.getNameLabeldTextField().getLabeledTextField().setText(selectedEnclosure.getName());
                this.enclosureView.getMaintenanceCostsLabeldTextField().getLabeledTextField().setText(selectedEnclosure.getMaintenanceCosts().toString());
                List<Employee> employees = new ArrayList<>();
                selectedEnclosure.getStaff().forEach(e->{
                    try {
                        employees.add(this.employeeControllerApi.getEmployeeById(e));
                    } catch (ApiException ex) {
                        ex.printStackTrace();
                    }
                });
                this.enclosureView.getEmployeesInEnclosureList().addAll(employees);
                this.enclosureView.getAddButton().setText(this.addButtonUpdateText);
            }
        });
    }

}
