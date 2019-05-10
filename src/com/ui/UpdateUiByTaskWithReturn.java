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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 在 JavaFx 中，如果在非Fx线程要执行Fx线程相关的任务，必须在 Platform.runlater 中执行，
 而 runlater 中代码将不会阻塞当前线程，所以当需要 runlater 中代码执行返回值，再顺序执行后续代码时，需要采用以下方法：
 *
 *
 * 好像有点问题，很奇怪
 * @author tony
 * @date 2019/5/10 18:36
 */
public class UpdateUiByTaskWithReturn extends Application {


    @Override
    public void start(Stage primaryStage) {


        VBox box = new VBox();
        // 这种方式也不好
        Button button = new Button("更新");
        Label label = new Label("待更新");

        ProgressBar bar = new ProgressBar();
        box.getChildren().addAll(
                button
        );
        button.setOnAction((event) -> {

            System.out.println("触发事件");
             FutureTask<String> query = new FutureTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    // 新建一个窗口(方法中将创建stage)
                    System.out.println("why");
                    return "更新UI成功";
                }
            });

            Platform.runLater(query);       // 重点。不会阻塞

            System.out.println("执行到这");
            String vcode = null;     // 这样就能获取返回值
            try {
                vcode = query.get(); // 会阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println(vcode);


        });


        primaryStage.setScene(new Scene(new StackPane(box), 120, 75));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
