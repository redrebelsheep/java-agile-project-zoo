package de.volkswagen.f73.model.buttons;

import de.volkswagen.f73.view.AnimalView;
import io.swagger.client.ApiException;
import io.swagger.client.api.AnimalControllerApi;
import io.swagger.client.model.Animal;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The class "RemoveAnimalButton"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class RemoveAnimalButton extends Button {

    private final long id;
    private final AnimalControllerApi api = new AnimalControllerApi();

    public RemoveAnimalButton(long id) {
        this.id = id;
        this.setText("entfernen");
        setAction();
    }

    private void setAction() {
        this.setOnAction((e)->{
            try {
                AnimalView view = (AnimalView) this.getParent().getParent().getScene().getRoot();
                final Animal[] deleteAnimal = new Animal[1];
                view.getAnimalList().forEach((animal) -> {
                    if(animal.getId() == this.id){
                        deleteAnimal[0] = animal;
                    }
                });
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Tier löschen");
                alert.setHeaderText("Tier aus Datenenbank löschen");
                alert.setContentText("Sind Sie sich sicher " + deleteAnimal[0].getSpecies() + " " + deleteAnimal[0].getName() + " zu löschen?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    this.api.deleteAnimal(id);
                    view.getAnimalList().remove(deleteAnimal[0]);
                }
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        });
    }

}
