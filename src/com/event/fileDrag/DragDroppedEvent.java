package com.event.fileDrag;

/**
 * @author tony
 * @date 2019/5/6 23:07
 */

import com.sun.javafx.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.io.File;


/**
 * Created by loongshawn 2016/11/3.
 *
 * NOTE 文件拖到控件上方，鼠标松开事件
 */
public class DragDroppedEvent implements EventHandler<DragEvent> {

//    private static final Logger logger = Logger.getLogger(DragDroppedEvent.class);

    private TextField textField;

    public DragDroppedEvent(TextField textField){
        this.textField = textField;
    }

    @Override
    public void handle(DragEvent event) {

        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()){
            try {
                File file = dragboard.getFiles().get(0);
                if (file != null) {
                    textField.setText(file.getAbsolutePath());
                }
            }catch (Exception e){
                e.printStackTrace();
//                logger.debug(e.toString());
            }
        }
    }
}

