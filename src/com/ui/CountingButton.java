package com.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 在JavaFX中执行所需操作的最简单方法是使用Timeline来处理暂停.
 * 时间轴适当地为您管理线程：
 *
 *
 * @author tony
 * @date 2019/5/9 21:52
 */
public class CountingButton extends Application {


    @Override
    public void start(Stage primaryStage) {

        Button button = new Button("Count");

        Timeline timeline = new Timeline();
        for (int count = 0; count <= 5 ; count++) {
            final String text = Integer.toString(count);
            KeyFrame frame = new KeyFrame(Duration.seconds(count), event ->
                    button.setText(text));
            timeline.getKeyFrames().add(frame);
        }

        button.setOnAction(e -> timeline.play());

        primaryStage.setScene(new Scene(new StackPane(button), 120, 75));
        primaryStage.show();
    }

//    Button button = new Button("Start");
//        button.setOnAction(e -> {
//        Thread thread = new Thread(() -> {
//            for (int i = 0; i <= 5 ; i++) {
//                final String text = "Count: "+i ;
//                Platform.runLater(() -> button.setText(text));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException exc) {
//                    exc.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//    });


    public static void main(String[] args) {
        launch(args);
    }
}
