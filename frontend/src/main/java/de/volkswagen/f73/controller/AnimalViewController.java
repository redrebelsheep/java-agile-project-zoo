package de.volkswagen.f73.controller;

import de.volkswagen.f73.view.AnimalView;
import io.swagger.client.ApiException;
import io.swagger.client.api.AnimalControllerApi;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.api.EnclosureControllerApi;
import io.swagger.client.model.Animal;
import io.swagger.client.model.AnimalDTO;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Enclosure;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class "AnimalViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class AnimalViewController {

    private final AnimalView animalView;
    private final AnimalControllerApi animalControllerApi = new AnimalControllerApi();
    private final EnclosureControllerApi enclosureControllerApi = new EnclosureControllerApi();
    private final EmployeeControllerApi employeeControllerApi = new EmployeeControllerApi();
    private final String addButtonAddText = "Tier anlegen";
    private final String addButtonUpdateText = "Tier bearbeiten";

    public AnimalViewController(AnimalView animalView) {
        this.animalView = animalView;
        configureContent();
    }

    private void configureContent() {
        getEnclosureAndEmployeeData();
        getActualData();
        setButtonsAction();
        setListener();
    }

    private void getEnclosureAndEmployeeData() {
        try {
            List<Employee> employeeList = this.employeeControllerApi.getAllWithJobEmployee("Arzt");
            List<Enclosure> enclosureList = this.enclosureControllerApi.getAllEnclosures();
            if(employeeList != null){
                this.animalView.getEmployeeList().addAll(employeeList);
            }
            if(enclosureList != null){
                this.animalView.getEnclosureList().addAll(enclosureList);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setButtonsAction() {
        setActionOnAddButton();
        setActionOnClearButton();
    }

    private void setActionOnAddButton() {
        this.animalView.getAddButton().setOnAction((e)->{
            if(this.animalView.getAddButton().getText().equals(this.addButtonAddText)){
                addButtonAddFunction();
            }else if(this.animalView.getAddButton().getText().equals(this.addButtonUpdateText)){
                addButtonUpdateFunction();
            }
        });
    }

    private void addButtonAddFunction() {
        if (checkNeededFieldsAreFilled()) {
            String name = this.animalView.getNameLabeldTextField().getLabeledTextField().getText();
            String species = this.animalView.getSpeciesLabeldTextField().getLabeledTextField().getText();
            BigDecimal subsistenceCosts = new BigDecimal(this.animalView.getSubsistenceCostsLabelLabel().getLabeledTextField().getText());
            Employee vet = this.animalView.getEmployeeComboBox().getSelectionModel().getSelectedItem();
            Enclosure enclosure = this.animalView.getEnclosureComboBox().getSelectionModel().getSelectedItem();
            AnimalDTO animalToAdd = new AnimalDTO().name(name).species(species).subsistenceCosts(subsistenceCosts);
            if (vet != null && enclosure != null) {
                animalToAdd.setVet(vet.getId());
                animalToAdd.setEnclosure(enclosure.getId());
            } else if (vet != null) {
                animalToAdd.setVet(vet.getId());
            } else if (enclosure != null) {
                animalToAdd.setEnclosure(enclosure.getId());
            }
            try {
                System.out.println(animalToAdd);
                this.animalControllerApi.addAnimal(animalToAdd);
                formClearAll();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            getActualData();
        }else{
            alertNeededFieldsAreInvalid();
        }
    }

    private void addButtonUpdateFunction() {
        if(checkNeededFieldsAreFilled()){
            String name = this.animalView.getNameLabeldTextField().getLabeledTextField().getText();
            String species = this.animalView.getSpeciesLabeldTextField().getLabeledTextField().getText();
            BigDecimal subsistenceCosts = new BigDecimal(this.animalView.getSubsistenceCostsLabelLabel().getLabeledTextField().getText());
            Employee vet = this.animalView.getEmployeeComboBox().getSelectionModel().getSelectedItem();
            Enclosure enclosure = this.animalView.getEnclosureComboBox().getSelectionModel().getSelectedItem();
            Animal animal= this.animalView.getAnimalTableView().getSelectionModel().getSelectedItem();
            AnimalDTO animalToUpdate = new AnimalDTO()
                    .id(animal.getId())
                    .name(name)
                    .species(species)
                    .subsistenceCosts(subsistenceCosts)
                    .vet(vet.getId())
                    .enclosure(enclosure.getId());
            try {
                System.out.println(animalToUpdate);
                this.animalControllerApi.putAnimal(animalToUpdate);
                formClearAll();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            getActualData();
        }else{
            alertNeededFieldsAreInvalid();
        }
    }

    private void alertNeededFieldsAreInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fehlende Daten");
        alert.setHeaderText(null);
        String neededFields = "Name, Tierart und Verpflegungskosten";
        alert.setContentText("Bitte füllen Sie die benötigten Felder aus:\n" + neededFields);

        alert.showAndWait();
    }

    /**
        return true if the needed fields are filled
     */
    private boolean checkNeededFieldsAreFilled() {
        return !this.animalView.getNameLabeldTextField().getLabeledTextField().getText().isEmpty() &&
                !this.animalView.getSpeciesLabeldTextField().getLabeledTextField().getText().isEmpty() &&
                !this.animalView.getSubsistenceCostsLabelLabel().getLabeledTextField().getText().isEmpty();
    }

    private void setActionOnClearButton() {
        this.animalView.getClearButton().setOnAction((e)->{
            formClearAll();
        });
    }

    private void formClearAll() {
            this.animalView.getNameLabeldTextField().getLabeledTextField().clear();
            this.animalView.getSpeciesLabeldTextField().getLabeledTextField().clear();
            this.animalView.getSubsistenceCostsLabelLabel().getLabeledTextField().clear();
            this.animalView.getEmployeeComboBox().getSelectionModel().clearSelection();
            this.animalView.getEnclosureComboBox().getSelectionModel().clearSelection();
            this.animalView.getAnimalTableView().getSelectionModel().clearSelection();
    }

    private void getActualData() {
        try {
            List<Animal> list = this.animalControllerApi.getAllAnimals();
            if(list != null){
                this.animalView.getAnimalList().clear();
                this.animalView.getAnimalList().addAll(list);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        this.animalView.getAnimalTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if(this.animalView.getAnimalTableView().getSelectionModel().isEmpty()){
                    this.animalView.getAddButton().setText(this.addButtonAddText);
                }else {
                    this.animalView.getAddButton().setText(this.addButtonUpdateText);
                    Animal selectedAnimal = this.animalView.getAnimalTableView().getSelectionModel().getSelectedItem();
                    this.animalView.getNameLabeldTextField().getLabeledTextField().setText(selectedAnimal.getName());
                    this.animalView.getSpeciesLabeldTextField().getLabeledTextField().setText(selectedAnimal.getSpecies());
                    this.animalView.getSubsistenceCostsLabelLabel().getLabeledTextField().setText(selectedAnimal.getSubsistenceCosts().toString());
                    if(selectedAnimal.getVet() == null){
                        this.animalView.getEmployeeComboBox().getSelectionModel().clearSelection();
                    }else {
                        this.animalView.getEmployeeComboBox().getSelectionModel().select(this.employeeControllerApi.getEmployeeById(selectedAnimal.getVet()));
                    }
                    if(selectedAnimal.getEnclosure() == null){
                        this.animalView.getEnclosureComboBox().getSelectionModel().clearSelection();
                    }else {
                        this.animalView.getEnclosureComboBox().getSelectionModel().select(this.enclosureControllerApi.getEnclosureById(selectedAnimal.getEnclosure()));
                    }
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        });
    }

}
