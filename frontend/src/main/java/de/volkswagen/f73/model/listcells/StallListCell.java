package de.volkswagen.f73.model.listcells;

import io.swagger.client.model.Stall;
import javafx.scene.control.ListCell;

/**
 * The class "StallListCell"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class StallListCell extends ListCell<Stall> {
    @Override
    protected void updateItem(Stall item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty && item != null){
            setText("item.getName() " + item.getType().getValue());
        }else {
            setText(null);
        }
    }
}
