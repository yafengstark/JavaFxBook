package com.css.boostrap;

/**
 * @author tony
 * @date 2019/4/26 20:48
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author com *
 */
public class JavaFxFirst extends Application {
    String cssPath ="styles/bootstrap3.css";
    public static void main(String[] args) {

        System.out.println("Launching JavaFX application.");

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the init() method.
    @Override
    public void init() {
        System.out.println("Inside the init() method.");
    }

    // Override the start() method.
    @Override
    public void start(Stage myStage) {

        System.out.println("Inside the start() method.");

        // Give the stage a title.
        myStage.setTitle("JavaFX Skeleton.");

        // Create a root node. In this case, a flow layout
        // is used, but several alternatives exist.
        BorderPane rootNode = new BorderPane();

        Button button = new Button("test");
        button.getStyleClass().add("primary");

        rootNode.setTop(button);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 300, 200);
        myScene.getStylesheets().add(cssPath);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Show the stage and its scene.
        myStage.show();
    }

    // Override the stop() method.
    @Override
    public void stop() {
        System.out.println("Inside the stop() method.");
    }
}