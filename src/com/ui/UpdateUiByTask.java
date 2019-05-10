package com.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 *
 *
 * @author tony
 * @date 2019/5/10 18:36
 */
public class UpdateUiByTask extends Application {


    @Override
    public void start(Stage primaryStage) {


        VBox box = new VBox();
        // 这种方式也不好
        Button button = new Button("更新");
        Label label = new Label("待更新");

        ProgressBar bar = new ProgressBar();
        box.getChildren().addAll(
                button, label, bar
        );
        button.setOnAction((event) -> {

            Task task = new Task<Void>() {
                @Override public Void call() {
                    final int max = 1000000;
                    for (int i = 1; i <= max; i++) {
                        updateProgress(i, max);
                    }
                    return null;
                }
            };


            bar.progressProperty().bind(task.progressProperty());
            new Thread(task).start();

        });


        primaryStage.setScene(new Scene(new StackPane(box), 120, 75));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
