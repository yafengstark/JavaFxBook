package com.event.dragDemo;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

/**
 * 拖拽结束监听器
 * 
 * @author
 * @version  [2013-8-2]
 */
public class DragOverHandler implements EventHandler<DragEvent>
{
    
    private JavaFxTestMain contrller;
    
    public DragOverHandler(JavaFxTestMain contrller)
    {
        this.contrller = contrller;
    }
    
    public void handle(DragEvent event)
    {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString())
        {
            contrller.addTextToPane(event.getX(), event.getY(), db.getString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
