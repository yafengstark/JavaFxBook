package com.event.treedrag;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeDragDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree Drag Example");

        TreeItem<TaskNode> rootItem = new TreeItem<TaskNode>(new TaskNode("Tasks"));
        rootItem.setExpanded(true);
        
        ObservableList<TreeItem<TaskNode>> children = rootItem.getChildren();
        children.add(new TreeItem<TaskNode>(new TaskNode("do laundry")));
        children.add(new TreeItem<TaskNode>(new TaskNode("get groceries")));
        children.add(new TreeItem<TaskNode>(new TaskNode("drink beer")));
        children.add(new TreeItem<TaskNode>(new TaskNode("defrag hard drive")));
        children.add(new TreeItem<TaskNode>(new TaskNode("walk dog")));
        children.add(new TreeItem<TaskNode>(new TaskNode("buy beer")));

        TreeItem<TaskNode> second =  new TreeItem<TaskNode>(new TaskNode("second"));
        second.getChildren().addAll(
                new TreeItem<TaskNode>(new TaskNode("second"))
        );
        children.add(second);

        TreeView<TaskNode> tree = new TreeView<TaskNode>(rootItem);
        tree.setCellFactory(new TaskCellFactory());
        
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
