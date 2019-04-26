package com.ui.ListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * 自定义List View的内容
 * 彩色列表
 *
 */
public class ListViewSetCellFactorySample extends Application {

    ListView<String> list = new ListView<>();
    ObservableList<String> data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");

    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box, 200, 200);
        stage.setScene(scene);
        stage.setTitle("ListViewChooseEventSample");
        box.getChildren().addAll(list);
        VBox.setVgrow(list, Priority.ALWAYS);

        list.setItems(data);

        list.setCellFactory((ListView<String> l) -> new ColorRectCell());

        stage.show();
    }

    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {

                rect.setFill(Color.web(item));
                setGraphic(rect);// 关键
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
