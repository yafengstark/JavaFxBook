package com.event.treeviewDrag;


import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * 叶节点拉出
 *
 * @author tony
 * @date 2019/5/6 23:30
 */
public class TreeViewNoSelection extends Application {


    private static int cellCount =  0 ;
    private final DataFormat objectDataFormat = new DataFormat("application/x-java-serialized-object");

    @Override
    public void start(Stage primaryStage) {
        TreeView<Integer> tree = new TreeView<>();
        tree.setShowRoot(false);

        Task<TreeItem<Integer>> buildTreeTask = new Task<TreeItem<Integer>>() {

            @Override
            protected TreeItem<Integer> call() throws Exception {
                TreeItem<Integer> treeRoot = new TreeItem<>(0);


                IntStream.range(1, 10).mapToObj(this::createItem)
                        .forEach(treeRoot.getChildren()::add);
                return treeRoot ;
            }
            private TreeItem<Integer> createItem(int value) {
                TreeItem<Integer> item = new TreeItem<>(value);
                if (value < 100_000) {
                    for (int i = 0; i < 10; i++) {
                        item.getChildren().add(createItem(value * 10 + i));
                    }
                }
                return item ;
            }

        };



        tree.setCellFactory(tv -> new TreeCell<Integer>() {

            {
                System.out.println("Cells created: "+(++cellCount));

                setOnDragDetected(e -> {
                    if (! isEmpty()) {
                        Dragboard db = startDragAndDrop(TransferMode.COPY);
                        ClipboardContent cc = new ClipboardContent();
                        cc.put(objectDataFormat, getItem());
                        db.setContent(cc);
                        Label label = new Label(String.format("Add %,d", getItem()));
                        new Scene(label);
                        db.setDragView(label.snapshot(null, null));
                    }
                });
            }

            @Override
            public void updateItem(Integer value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%,d", value));
                }
            }
        });

        IntegerProperty total = new SimpleIntegerProperty();
        Label label = new Label();
        label.textProperty().bind(total.asString("Total: %,d"));

        label.setOnDragOver(e ->
                e.acceptTransferModes(TransferMode.COPY));

        // in real life use a CSS pseudoclass and external CSS file for the background:
        label.setOnDragEntered(e -> label.setStyle("-fx-background-color: yellow;"));
        label.setOnDragExited(e -> label.setStyle(""));

        label.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(objectDataFormat)) {
                Integer value = (Integer) db.getContent(objectDataFormat);
                total.set(total.get() + value);
                e.setDropCompleted(true);
            }
        });

        BorderPane.setMargin(label, new Insets(10));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(new Label("Loading..."));

        buildTreeTask.setOnSucceeded(e -> {
            tree.setRoot(buildTreeTask.getValue());
            root.setCenter(tree);
            root.setBottom(label);
        });

        primaryStage.setScene(new Scene(root, 250, 400));
        primaryStage.show();

        Thread t = new Thread(buildTreeTask);
        t.setDaemon(true);
        t.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
