package de.volkswagen.f73.model.buttons;

import de.volkswagen.f73.view.EmployeeView;
import de.volkswagen.f73.view.EnclosureView;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmployeeControllerApi;
import io.swagger.client.api.EnclosureControllerApi;
import io.swagger.client.model.Employee;
import io.swagger.client.model.Enclosure;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The class "RemoveEmployeeButton"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class RemoveEmployeeButton extends Button {

    private final long id;
    private final EmployeeControllerApi api = new EmployeeControllerApi();

    public RemoveEmployeeButton(long id) {
        this.id = id;
        this.setText("entfernen");
        setAction();
    }

    private void setAction() {
        this.setOnAction((e)->{
            try {
                EmployeeView view = (EmployeeView) this.getParent().getParent().getScene().getRoot();
                final Employee[] deleteEmployee = new Employee[1];
                view.getEmployeeList().forEach((employee) -> {
                    if(employee.getId() == this.id){
                        deleteEmployee[0] = employee;
                    }
                });
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mitarbeiter löschen");
                alert.setHeaderText("Mitarbeiter aus Datenenbank löschen");
                alert.setContentText("Sind Sie sich sicher " + deleteEmployee[0].getJob().getValue() + " " + deleteEmployee[0].getName() + " zu löschen?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    api.deleteEmployee(this.id);
                    view.getEmployeeList().remove(deleteEmployee[0]);
                }
            } catch (ApiException ex) {
                ex.printStackTrace();
            }
        });
    }

}
