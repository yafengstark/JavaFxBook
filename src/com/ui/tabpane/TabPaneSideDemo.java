package com.ui.tabpane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author tony
 * @date 2019/5/6 12:39
 */
public class TabPaneSideDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabs");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);



        BorderPane borderPane = new BorderPane();



        TabPane vTabPane = new TabPane();
        vTabPane.setSide(Side.LEFT);
        vTabPane.setTabMinWidth(30);
        vTabPane.setTabMaxWidth(30);
        vTabPane.setTabMinHeight(100);
        vTabPane.setTabMaxHeight(100);

        Tab cpuTab = new Tab();
        cpuTab.setGraphic(createTabHeader("CPU", null));
        cpuTab.setClosable(false);

        Tab memoryTab = new Tab("内存");

        vTabPane.getTabs().addAll(
                cpuTab,
                memoryTab
        );


        //tabPane.setSide(Side.TOP);
//        tabPane.setSide(Side.RIGHT);
        //tabPane.setSide(Side.BOTTOM);

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(vTabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private StackPane createTabHeader(String text, Node graphics){
        return new StackPane(new Group(new Label(text, graphics)));
    }

}
//
