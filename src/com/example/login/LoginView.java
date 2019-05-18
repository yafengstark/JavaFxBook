package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author tony
 * @date 2019/5/18 12:17
 */
public class LoginView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("某地理信息系统");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
