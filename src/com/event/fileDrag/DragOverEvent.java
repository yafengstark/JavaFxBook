package com.event.fileDrag;

/**
 * @author tony
 * @date 2019/5/6 23:06
 */

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by loongshawn 2016/11/3.
 *
 * NOTE 文件拖到控件上方事件
 */
public class DragOverEvent implements EventHandler<DragEvent> {

    private Logger logger = Logger.getLogger(DragOverEvent.class.getName());
    private TextField textField;

    public DragOverEvent(TextField textField){
        this.textField = textField;
    }

    @Override
    public void handle(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
//        if (event.getGestureSource() != textField){
//            logger.info("run here");
//
//        }
    }
}

