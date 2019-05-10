package com.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * 解决 ：java.lang.IllegalStateException:
 * Not on FX application thread; currentThread =
 *
 * 发现Task类里面的call方法还是使用传统子线程做处理
 只有successed, running, scheduled, cancelled,
 failed等方法才是使用JavaFX线程工作的
 *
 * @author tony
 * @date 2019/5/9 21:46
 */
public class UpdateUiByThread extends Application {


    @Override
    public void start(Stage primaryStage) {


        // 这种方式也不好
        Button button = new Button("Count");

        button.setOnAction((event) -> {

            new Thread(new Task<Void>() {
                // call方法里面的线程非JavaFX线程
                @Override
                protected Void call() throws Exception {
//                    button.setText("1xxx");  // 这里会报错
                    return null;
                }
                @Override
                protected void succeeded() {
                    button.setText("xxx");
                    System.out.println(Thread.currentThread());

                    super.succeeded();
                }

            }).start();

        });


        primaryStage.setScene(new Scene(new StackPane(button), 120, 75));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}

