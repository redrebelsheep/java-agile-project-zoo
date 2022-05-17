package de.volkswagen.f73.model.buttons;

import de.volkswagen.f73.view.EnclosureView;
import io.swagger.client.ApiException;
import io.swagger.client.api.EnclosureControllerApi;
import io.swagger.client.model.Enclosure;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The class "RemoveEnclosureButton"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class RemoveEnclosureButton extends Button {

    private final long id;
    private final EnclosureControllerApi api = new EnclosureControllerApi();

    public RemoveEnclosureButton(long id) {
        this.id = id;
        this.setText("entfernen");
        setAction();
    }

    private void setAction() {
        this.setOnAction((e)->{
            try {
                EnclosureView view = (EnclosureView) this.getParent().getParent().getScene().getRoot();
                final Enclosure[] deleteEnclosure = new Enclosure[1];
                view.getEnclosureList().forEach((enclosure) -> {
                    if(enclosure.getId() == this.id){
                        deleteEnclosure[0] = enclosure;
                    }
                });
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Gehege löschen");
                alert.setHeaderText("Gehege aus Datenenbank löschen");
                alert.setContentText("Sind Sie sich sicher das Gehege " + deleteEnclosure[0].getName() + " zu löschen?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    api.deleteEnclosure(this.id);
                    view.getEnclosureList().remove(deleteEnclosure[0]);
                }
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        });
    }

}
