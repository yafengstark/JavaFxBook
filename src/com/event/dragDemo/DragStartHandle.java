package com.event.dragDemo;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * 拖拽起始监听器
 * 
 * @author
 * @version  [2013-8-2]
 */
public class DragStartHandle implements EventHandler<MouseEvent>
{
    private Node source;
    
    private String str;
    
    public DragStartHandle(Node source, String str)
    {
        this.source = source;
        this.str = str;
    }
    
    public void handle(MouseEvent event)
    {
        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(str);
        db.setContent(content);
        
        event.consume();
    }
}
