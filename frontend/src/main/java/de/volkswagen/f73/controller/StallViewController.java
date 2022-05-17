package de.volkswagen.f73.controller;

import de.volkswagen.f73.view.StallView;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.api.StallControllerApi;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Stall;
import io.swagger.client.model.StallDTO;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class "StallViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class StallViewController {

    private final StallControllerApi stallControllerApi = new StallControllerApi();
    private final EmployeeControllerApi employeeControllerApi = new EmployeeControllerApi();
    private final StallView stallView;
    private final String addButtonAddText = "Verkaufsstand\nanlegen";
    private final String addButtonUpdateText = "Verkaufsstand\nbearbeiten";

    public StallViewController(StallView stallView) {
        this.stallView = stallView;
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
        this.stallView.getAddButton().setOnAction((e)->{
            if(this.stallView.getAddButton().getText().equals(this.addButtonAddText)){
                addButtonAddFunction();
            }else if(this.stallView.getAddButton().getText().equals(this.addButtonUpdateText)){
                addButtonUpdateFunction();
            }
        });
    }

    private void addButtonAddFunction() {
        if (checkNeededFieldsAreFilled()) {
            BigDecimal operatingCost = new BigDecimal(this.stallView.getOperatingCostsLabelLabel().getLabeledTextField().getText());
            Stall.TypeEnum type = this.stallView.getStallTypeComboBox().getSelectionModel().getSelectedItem();
            StallDTO.TypeEnum typeDTO = StallDTO.TypeEnum.fromValue(type.getValue());
            Employee seller = this.stallView.getEmployeeComboBox().getSelectionModel().getSelectedItem();
            StallDTO stallToAdd = new StallDTO().operatingCost(operatingCost).type(typeDTO);
            if(seller != null){
                stallToAdd.setSeller(seller.getId());
            }
            try {
                this.stallControllerApi.addStall(stallToAdd);
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
            BigDecimal operatingCost = new BigDecimal(this.stallView.getOperatingCostsLabelLabel().getLabeledTextField().getText());
            Stall.TypeEnum type = this.stallView.getStallTypeComboBox().getSelectionModel().getSelectedItem();
            StallDTO.TypeEnum typeDTO = StallDTO.TypeEnum.fromValue(type.getValue());
            Employee seller = this.stallView.getEmployeeComboBox().getSelectionModel().getSelectedItem();
            Long id = this.stallView.getStallTableView().getSelectionModel().getSelectedItem().getId();
            StallDTO stallToUpdate = new StallDTO().id(id).operatingCost(operatingCost).type(typeDTO);
            if(seller != null){
                stallToUpdate.setSeller(seller.getId());
            }
            try {
                this.stallControllerApi.putStall(stallToUpdate);
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
        String neededFields = "Verkaufskategorie und Unterhaltskosten";
        alert.setContentText("Bitte füllen Sie die benötigten Felder aus:\n" + neededFields);

        alert.showAndWait();
    }

    /**
     return true if the needed fields are filled
     */
    private boolean checkNeededFieldsAreFilled() {
        return !this.stallView.getOperatingCostsLabelLabel().getLabeledTextField().getText().isEmpty() &&
                !this.stallView.getStallTypeComboBox().getSelectionModel().isEmpty();
    }

    private void setActionOnClearButton() {
        this.stallView.getClearButton().setOnAction((e)->{
            formClearAll();
        });
    }

    private void formClearAll() {
        this.stallView.getNameLabeledTextField().getLabeledTextField().clear();
        this.stallView.getOperatingCostsLabelLabel().getLabeledTextField().clear();
        this.stallView.getStallTypeComboBox().getSelectionModel().clearSelection();
        this.stallView.getEmployeeComboBox().getSelectionModel().clearSelection();
        this.stallView.getStallTableView().getSelectionModel().clearSelection();
    }

    private void getActualData() {
        try {
            List<Stall> stalls = this.stallControllerApi.getAllStalls();

            if(stalls != null){
                this.stallView.getStallList().clear();
                this.stallView.getStallList().addAll(stalls);
            }
            getEmployeeData();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void getEmployeeData() {
        try {
            List<Employee> employees = this.employeeControllerApi.getAllWithJobEmployee("Verkaeufer");
            if(employees != null){
                employees = employees.stream().filter(seller -> seller.getStall() == null).collect(Collectors.toList());
                this.stallView.getEmployeeList().clear();
                this.stallView.getEmployeeList().addAll(employees);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        this.stallView.getStallTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(this.stallView.getStallTableView().getSelectionModel().getSelectedItems().isEmpty()){
                this.stallView.getAddButton().setText(this.addButtonAddText);
            }else{
                this.stallView.getAddButton().setText(this.addButtonUpdateText);
                this.stallView.getEmployeeComboBox().getSelectionModel().clearSelection();
                Stall selectedStall = this.stallView.getStallTableView().getSelectionModel().getSelectedItem();
                this.stallView.getOperatingCostsLabelLabel().getLabeledTextField().setText(selectedStall.getOperatingCost().toString());
                this.stallView.getStallTypeComboBox().getSelectionModel().select(selectedStall.getType());
                if(selectedStall.getSeller() != null){
                    try {
                        this.stallView.getEmployeeComboBox().getSelectionModel().select(this.employeeControllerApi.getEmployeeById(selectedStall.getSeller()));
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}