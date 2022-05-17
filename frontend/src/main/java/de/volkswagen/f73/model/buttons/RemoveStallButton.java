package de.volkswagen.f73.model.buttons;

import de.volkswagen.f73.view.StallView;
import io.swagger.client.ApiException;
import io.swagger.client.api.StallControllerApi;
import io.swagger.client.model.Stall;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The class "RemoveStallButton"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class RemoveStallButton extends Button {

    private final long id;
    private final StallControllerApi api = new StallControllerApi();

    public RemoveStallButton(long id) {
        this.id = id;
        this.setText("entfernen");
        setAction();
    }

    private void setAction() {
        this.setOnAction((e)->{
            try {
                StallView view = (StallView) this.getParent().getParent().getScene().getRoot();
                final Stall[] deleteStalls = new Stall[1];
                view.getStallList().forEach((stall) -> {
                    if(stall.getId() == this.id){
                        deleteStalls[0] = stall;
                    }
                });
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Verkaufsstand löschen");
                alert.setHeaderText("Verkaufsstand aus Datenenbank löschen");
                alert.setContentText("Sind Sie sich sicher den Verkaufsstand zu löschen?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    api.deleteStall(this.id);
                    view.getStallList().remove(deleteStalls[0]);
                }
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        });
    }

}
