package com.event.fileDrag;

/**
 * @author tony
 * @date 2019/5/6 23:06
 */

import javafx.scene.control.TextField;

/**
 * Created by loongshawn on 2016/11/3.
 *
 * NOTE 路径输入框拖入事件
 */
public class MyStyleTextField {

    private static MyStyleTextField uniqueInstance = null;

    private MyStyleTextField(){

    }

    public static MyStyleTextField getInstance(){

        if (uniqueInstance == null){
            uniqueInstance = new MyStyleTextField();
        }
        return uniqueInstance;
    }

    public TextField getTextFiled(){

        TextField textField = new TextField();

        // 1、文件拖到控件上方
        textField.setOnDragOver(new DragOverEvent(textField));
        // 鼠标按钮松开
        textField.setOnDragDropped(new DragDroppedEvent(textField));

        return textField;
    }

}

