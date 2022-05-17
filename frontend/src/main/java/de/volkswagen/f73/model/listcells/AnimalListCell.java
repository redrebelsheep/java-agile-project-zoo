package de.volkswagen.f73.model.listcells;

import io.swagger.client.model.Animal;
import io.swagger.client.model.Enclosure;
import javafx.scene.control.ListCell;

import java.util.List;

/**
 * The class "AnimalListCell"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class AnimalListCell extends ListCell<List<Animal>> {
    @Override
    protected void updateItem(List<Animal> item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty && item != null){
            setText(item.size() + "");
        }else {
            setText(null);
        }
    }
}
