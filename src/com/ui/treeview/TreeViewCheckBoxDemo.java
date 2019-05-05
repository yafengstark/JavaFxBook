package com.ui.treeview;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author tony
 * @date 2019/5/5 22:38
 */

public class TreeViewCheckBoxDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");

        CheckBoxTreeItem<String> rootItem =
                new CheckBoxTreeItem<>("View Source Files");
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

        StackPane root = new StackPane();
        root.getChildren().add(treeView);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
