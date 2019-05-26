package com.ui.alert;

import javafx.scene.control.Alert;

public class AlertSample {


    public  static void alertSuccess(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();

    }

    public static void alertFail(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("失败");
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();

    }

}
