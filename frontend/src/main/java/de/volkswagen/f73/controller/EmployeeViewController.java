package de.volkswagen.f73.controller;

import de.volkswagen.f73.view.EmployeeView;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.model.Employee;
import io.swagger.client.model.EmployeeDTO;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class "EmployeeViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EmployeeViewController {

    private final EmployeeControllerApi employeeControllerApi = new EmployeeControllerApi();
    private final EmployeeView employeeView;
    private final String addButtonAddText = "Mitarbeiter anlegen";
    private final String addButtonUpdateText = "Mitarbeiter bearbeiten";

    public EmployeeViewController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        configureContent();
    }

    private void configureContent() {
        getActualData();
        setButtonsActions();
        setListener();
    }

    private void setButtonsActions() {
        setActionOnAddButton();
        setActionOnClearButton();
    }

    private void setActionOnAddButton() {
        this.employeeView.getAddButton().setOnAction((e) -> {
           if(this.employeeView.getAddButton().getText().equals(this.addButtonAddText)){
                addButtonAddFunction();
           }else if(this.employeeView.getAddButton().getText().equals(this.addButtonUpdateText)){
               addButtonUpdateFunction();
           }
        });
    }

    private void addButtonAddFunction() {
        if (checkNeededFieldsAreFilled()) {
            String name = this.employeeView.getNameLabeldTextField().getLabeledTextField().getText();
            BigDecimal salary = new BigDecimal(this.employeeView.getSalaryLabeldTextField().getLabeledTextField().getText());
            Employee.JobEnum job = this.employeeView.getJobComboBox().getSelectionModel().getSelectedItem();
            EmployeeDTO employeeToAdd = new EmployeeDTO().name(name).salary(salary);
            if(job != null){
                EmployeeDTO.JobEnum jobDTO = EmployeeDTO.JobEnum.fromValue(job.getValue());
                employeeToAdd.setJob(jobDTO);
            }
            System.out.println(employeeToAdd.toString());
            try {
                this.employeeControllerApi.addEmployee(employeeToAdd);
                formClearAll();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            getActualData();
        }else {
            alertNeededFieldsAreInvalid();
        }
    }

    private void addButtonUpdateFunction() {
        if (checkNeededFieldsAreFilled()) {
            String name = this.employeeView.getNameLabeldTextField().getLabeledTextField().getText();
            BigDecimal salary = new BigDecimal(this.employeeView.getSalaryLabeldTextField().getLabeledTextField().getText());
            Employee.JobEnum job = this.employeeView.getJobComboBox().getSelectionModel().getSelectedItem();
            Long id = this.employeeView.getEmployeeTableView().getSelectionModel().getSelectedItem().getId();
            EmployeeDTO employeeToUpdate = new EmployeeDTO().id(id).name(name).salary(salary);
            if(job != null){
                EmployeeDTO.JobEnum jobDTO = EmployeeDTO.JobEnum.fromValue(job.getValue());
                employeeToUpdate.setJob(jobDTO);
            }else {
                employeeToUpdate.setJob(null);
            }
            try {
                this.employeeControllerApi.putEmployee(employeeToUpdate);
                formClearAll();
            } catch (ApiException e) {
                e.printStackTrace();
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
        String neededFields = "Name, Gehalt und Job";
        alert.setContentText("Bitte füllen Sie die benötigten Felder aus:\n" + neededFields);

        alert.showAndWait();
    }

    /**
     return true if the needed fields are filled
     */
    private boolean checkNeededFieldsAreFilled() {
        return !this.employeeView.getNameLabeldTextField().getLabeledTextField().getText().isEmpty() &&
                !this.employeeView.getSalaryLabeldTextField().getLabeledTextField().getText().isEmpty() &&
                !this.employeeView.getJobComboBox().getSelectionModel().isEmpty();
    }

    private void setActionOnClearButton() {
        this.employeeView.getClearButton().setOnAction((e)->{
            formClearAll();
        });
    }

    private void formClearAll() {
        this.employeeView.getNameLabeldTextField().getLabeledTextField().clear();
        this.employeeView.getSalaryLabeldTextField().getLabeledTextField().clear();
        this.employeeView.getJobComboBox().getSelectionModel().clearSelection();
        this.employeeView.getEmployeeTableView().getSelectionModel().clearSelection();
    }

    private void getActualData() {
        try {
            List<Employee> employees = this.employeeControllerApi.getAllEmployee();
            if(employees != null){
                this.employeeView.getEmployeeList().clear();
                this.employeeView.getEmployeeList().addAll(employees);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        this.employeeView.getEmployeeTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(this.employeeView.getEmployeeTableView().getSelectionModel().isEmpty()){
               this.employeeView.getAddButton().setText(this.addButtonAddText);
            }else {
                this.employeeView.getAddButton().setText(this.addButtonUpdateText);
                Employee selectedEmployee = this.employeeView.getEmployeeTableView().getSelectionModel().getSelectedItem();
                this.employeeView.getNameLabeldTextField().getLabeledTextField().setText(selectedEmployee.getName());
                this.employeeView.getSalaryLabeldTextField().getLabeledTextField().setText(selectedEmployee.getSalary().toString());
                if(selectedEmployee.getJob() == null){
                    this.employeeView.getJobComboBox().getSelectionModel().clearSelection();
                }else{
                    this.employeeView.getJobComboBox().getSelectionModel().select(selectedEmployee.getJob());
                }
            }
        });
    }
}
