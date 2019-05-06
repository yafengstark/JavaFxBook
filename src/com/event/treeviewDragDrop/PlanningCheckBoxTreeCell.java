package com.event.treeviewDragDrop;

import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 * @author tony
 * @date 2019/5/6 23:37
 */
public class PlanningCheckBoxTreeCell extends CheckBoxTreeCell<PlanningItem> {

    public PlanningCheckBoxTreeCell() {
    }

    @Override
    public void updateItem(PlanningItem item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setText(null);
        }

    }
}
