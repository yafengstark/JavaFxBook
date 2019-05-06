package com.ui.titledpanesample;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author tony
 * @date 2019/5/5 23:17
 */
public class AccordionDemo extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage stage) {
        stage.setTitle("TitledPane");
        Scene scene = new Scene(new Group(), 450, 250);

        TitledPane gridTitlePane = new TitledPane();


        final Accordion accordion = new Accordion ();


        TitledPane placeTitledPane = new TitledPane("地点", null);

        TitledPane layerTitledPane = new TitledPane("图层", null);
        CheckBoxTreeItem<String> rootItem =
                new CheckBoxTreeItem<>("底图");
        rootItem.setExpanded(true);
        final TreeView treeView = new TreeView(rootItem);
        treeView.setEditable(true);

        treeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView(new Callback<TreeItem<String>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TreeItem<String> param) {
                System.out.println("name:"+ param.getValue() );
//                return new SimpleBooleanProperty(Integer.parseInt(param.getValue()) %2 == 0);
                return new SimpleBooleanProperty(true);
            }
        }));

        for (int i = 0; i < 8; i++) {
            final CheckBoxTreeItem<String> checkBoxTreeItem =
                    new CheckBoxTreeItem<>( "" + (i+1));

            // 勾选事件
            checkBoxTreeItem.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {


                System.out.println(checkBoxTreeItem.getValue());
//                System.out.println(wasChecked);
//                System.out.println(isNowChecked);
                if (isNowChecked) {

                } else {

                }
            });

            rootItem.getChildren().add(checkBoxTreeItem);
        }




        // 鼠标点击节点事件
        treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();

                if (node instanceof Text
                        || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
                    System.out.println("Node click: " + name);
                }
            }
        });


        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(treeView);
        layerTitledPane.setContent(vBox);




        accordion.getPanes().addAll(placeTitledPane, layerTitledPane);




        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        hbox.getChildren().setAll( accordion);

        Group root = (Group)scene.getRoot();
        root.getChildren().add(hbox);
        stage.setScene(scene);
        stage.show();
    }
}
