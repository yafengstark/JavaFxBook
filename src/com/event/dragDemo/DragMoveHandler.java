package com.event.dragDemo;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *拖拽中监听器
 * 
 * @author
 * @version  [2013-8-2]
 */
public class DragMoveHandler implements EventHandler<DragEvent>
{
    public void handle(DragEvent event)
    {
        Dragboard db = event.getDragboard();
        if (db.hasString())
        {
            event.acceptTransferModes(TransferMode.COPY);
        }
        
        event.consume();
    }
}
