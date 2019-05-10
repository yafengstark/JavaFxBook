package com.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author tony
 * @date 2019/5/10 18:36
 */
public class UpdateUiByPlatformRunlater extends Application {


    @Override
    public void start(Stage primaryStage) {


        VBox box = new VBox();
        // 这种方式也不好
        Button button = new Button("更新");
        Label label = new Label("待更新");
        box.getChildren().addAll(
                button, label
        );

        button.setOnAction((event) -> {

            Platform.runLater(new Runnable() {
                public void run() {
                    label.textProperty().set("更新借宿");
                }
            });

        });


        primaryStage.setScene(new Scene(new StackPane(box), 120, 75));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
