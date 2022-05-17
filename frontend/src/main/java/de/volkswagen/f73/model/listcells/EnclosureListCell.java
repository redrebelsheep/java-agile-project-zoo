package de.volkswagen.f73.model.listcells;

import io.swagger.client.model.Enclosure;
import javafx.scene.control.ListCell;

/**
 * The class "EnclosureListCell"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class EnclosureListCell extends ListCell<Enclosure> {
    @Override
    protected void updateItem(Enclosure item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty && item != null){
            setText(item.getName());
        }else {
            setText(null);
        }
    }
}
