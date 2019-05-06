package com.event.dragDemo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CustomListCell extends ListCell<String>
{
    private JavaFxTestMain controller;
    
    public CustomListCell(JavaFxTestMain controller)
    {
        this.controller = controller;
    }
    
    @Override
    public void updateItem(final String item, boolean empty)
    {
        super.updateItem(item, empty);
        if (item != null)
        {
            HBox hbox = new HBox(10);
            hbox.setAlignment(Pos.CENTER);
            Label label = new Label(item);
            Button button = new Button("删除" + item);
            button.setOnAction(new EventHandler<ActionEvent>()
            {
                
                @Override
                public void handle(ActionEvent event)
                {
                    controller.deleteListItem(item);
                }
            });
            hbox.setOnDragDetected(new DragStartHandle(hbox, item));
            hbox.getChildren().addAll(label, button);
            setGraphic(hbox);
        }
    }
}
