package com.css.boostrap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * @Author : Val
 * @Created : 2016-01-12
 * @E-mail : valer@126.com
 * @Version : 1.0
 */
public class JbootxDemo extends Application {

    private String cssPath ="styles/bootstrap3.css";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/styles/bootstrap3overview.fxml"));
        loader.load();

        primaryStage.setScene(new Scene(loader.<ScrollPane>getRoot(), 800, 600));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
