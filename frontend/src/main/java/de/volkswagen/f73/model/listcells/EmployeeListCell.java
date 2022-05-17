package de.volkswagen.f73.model.listcells;

import io.swagger.client.model.Employee;
import javafx.scene.control.ListCell;

/**
 * The class "EmployeeListCell"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EmployeeListCell extends ListCell<Employee> {
    @Override
    protected void updateItem(Employee item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty && item != null){
            setText(item.getName());
        }else {
            setText(null);
        }
    }
}
