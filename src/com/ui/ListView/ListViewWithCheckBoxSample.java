package com.ui.ListView;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * 自定义List View的内容
 * checkBox
 *
 */
public class ListViewWithCheckBoxSample extends Application {

    ListView<String> listView = new ListView<>();
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
        box.getChildren().addAll(listView);
        VBox.setVgrow(listView, Priority.ALWAYS);

        listView.setItems(data);

//        listView.setCellFactory((ListView<String> l) -> new ColorRectCell());

        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String param) {
                System.out.println(param);

                return null;
            }
        }));

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println("old_val:" + old_val);
                    System.out.println("new_val:" + new_val);
                    System.out.println(":" + ov.getValue());
//                    label.setText(new_val);
//                    label.setTextFill(Color.web(new_val));
                });




        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("点击了");
            }
        });

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


