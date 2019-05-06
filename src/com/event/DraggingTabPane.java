package com.event;

/**
 * @author tony
 * @date 2019/5/7 0:43
 */
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * f your drag object isn't serializable, save it in a global variable during the drag.
 */
public class DraggingTabPane extends Application {

    private static final DataFormat TAB_TYPE = new DataFormat("nonserializableObject/tab");
    private static Tab dndTab;// global for drag-n-drop of non-serializable type

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane1 = createDndTabPane();
        TabPane tabPane2 = createDndTabPane();
        VBox root = new VBox(10);
        root.getChildren().addAll(tabPane1, tabPane2);

        final Random rng = new Random();
        for (int i=1; i<=8; i++) {
            final Tab tab = createDraggableTab("Tab "+i);
            final StackPane pane = new StackPane();
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            String style = String.format("-fx-background-color: rgb(%d, %d, %d);", red, green, blue);
            pane.setStyle(style);
            final Label label = new Label("This is tab "+i);
            label.setStyle(String.format("-fx-text-fill: rgb(%d, %d, %d);", 256-red, 256-green, 256-blue));
            pane.getChildren().add(label);
            pane.setMinWidth(600);
            pane.setMinHeight(250);
            tab.setContent(pane);
            if (i<=4) {
                tabPane1.getTabs().add(tab);
            } else {
                tabPane2.getTabs().add(tab);
            }
        }

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public TabPane createDndTabPane() {
        final TabPane tabPane = new TabPane();
        tabPane.setOnDragOver(event -> {
            if (event.getDragboard().hasContent(TAB_TYPE)
                    && dndTab.getTabPane() != tabPane) {// && different from source location
                event.acceptTransferModes(TransferMode.MOVE);
                event.consume();
            }
        });
        tabPane.setOnDragDropped(event -> {
            if (event.getDragboard().hasContent(TAB_TYPE)
                    && dndTab.getTabPane() != tabPane) {// && different from source location
                dndTab.getTabPane().getTabs().remove(dndTab);
                tabPane.getTabs().add(dndTab);
                event.setDropCompleted(true);
                event.consume();
            }
        });
        return tabPane;
    }

    private Tab createDraggableTab(String text) {
        final Tab tab = new Tab();
        final Label label = new Label(text);
        tab.setGraphic(label);
        label.setOnDragDetected(event -> {
            Dragboard dragboard = label.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.put(TAB_TYPE, 1);
            dndTab = tab;
            dragboard.setContent(clipboardContent);
            event.consume();
        });
        return tab ;
    }
}
