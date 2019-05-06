package com.event.treeviewDragDrop;


import com.ui.treeview.TreeViewSample;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * https://stackoverflow.com/questions/51558538/drag-and-drop-with-javafx-treeview
 *
 * @author tony
 * @date 2019/5/6 23:38
 */
public final class TestplaygroundUiUpdate extends Application {

    private final DataFormat objectDataFormat = new DataFormat("application/x-java-serialized-object");

    TreeItem<PlanningItem> itemTemp;

    private final String key = "1fafajfa";

    /**
     * Constructor.
     */
    public TestplaygroundUiUpdate() {
        // empty.
    }

    /**
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        TreeItem<PlanningItem> treeItemRoot = new TreeItem<>(new PlanningItem(1.0));


        TreeItem<PlanningItem> nodeItemA = new TreeItem<>(new PlanningItem(2));
        TreeItem<PlanningItem> nodeItemB = new TreeItem<>(new PlanningItem(2));
        treeItemRoot.getChildren().addAll(nodeItemA, nodeItemB);

        TreeItem<PlanningItem> nodeItemA1 = new TreeItem<>(new PlanningItem("A1"));
        TreeItem<PlanningItem> nodeItemB1 = new TreeItem<>(new PlanningItem("B1"));
        nodeItemA.getChildren().addAll(nodeItemA1);
        nodeItemB.getChildren().addAll(nodeItemB1);

        TreeView<PlanningItem> treeView = new TreeView<>(treeItemRoot);


        treeView.setCellFactory(new Callback<TreeView<PlanningItem>, TreeCell<PlanningItem>>() {
            @Override
            public TreeCell<PlanningItem> call(TreeView<PlanningItem> siTreeView) {
                final TreeCell<PlanningItem> cell = new TreeCell<>();

//                cell.setText(siTreeView.getSelectionModel().getSelectedItem().getValue().toString());

                cell.setText("hello");

                cell.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start drag-and-drop gesture */
                        System.out.println("onDragDetected");

                        /* allow any transfer mode */
                        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

                        /* put a string on dragboard */
                        ClipboardContent content = new ClipboardContent();

//                        content.put(objectDataFormat, cell);
                        content.put(objectDataFormat, 1);
                        itemTemp = cell.getTreeItem();
                        // content.putString("Hello");// cell.getText());
                        db.setContent(content);

                        event.consume();
                    }
                });

//                cell.setOnDragOver(new EventHandler<DragEvent>() {
//                    public void handle(DragEvent event) {
//                        /* data is dragged over the target */
//                        System.out.println("onDragOver");
//
//                        /*
//                         * accept it only if it is not dragged from the same node and if it has a string data
//                         */
//                        if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
//                            /* allow for both copying and moving, whatever user chooses */
//                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                        }
//
//                        event.consume();
//                    }
//                });

//                cell.setOnDragEntered(new EventHandler<DragEvent>() {
//                    public void handle(DragEvent event) {
//                        /* the drag-and-drop gesture entered the target */
//                        System.out.println("onDragEntered");
//                        /* show to the user that it is an actual gesture target */
//                        if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
//                            // target.setFill(Color.GREEN);
//                        }
//
//                        event.consume();
//                    }
//                });

//                cell.setOnDragExited(new EventHandler<DragEvent>() {
//                    public void handle(DragEvent event) {
//                        System.out.println("onDragExited");
//
//                        /* mouse moved away, remove the graphical cues */
////                         target.setFill(Color.BLACK);
//
//                        event.consume();
//                    }
//                });

//                cell.setOnDragDropped(new EventHandler<DragEvent>() {
//                    public void handle(DragEvent event) {
//                        /* data dropped */
//                        System.out.println("onDragDropped");
//                        /* if there is a string data on dragboard, read it and use it */
//                        Dragboard db = event.getDragboard();
//                        boolean success = false;
//                        if (db.hasString()) {
//                            cell.setText(db.getString());
//                            success = true;
//                        }
//
//
//
//                        itemTemp.getChildren().add(cell.getTreeItem());
//                        System.out.println("添加结束");
//
//                        /*
//                         * let the source know whether the string was successfully transferred and used
//                         */
//                        event.setDropCompleted(success);
//
//                        event.consume();
//                    }
//                });

                cell.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture ended */
                        System.out.println("onDragDone");
                        /* if the data was successfully moved, clear it */
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            cell.setText("");
                        }
//                        cell.setText("放置完");
                        TreeItem source = cell.getTreeItem();
//                        boolean remove = source.getParent().getChildren().remove(source);
//                        System.out.println("Remove");

                        // --------------------
                        Dragboard db = event.getDragboard();
                        if (db.hasContent(objectDataFormat)) {
//                            CellSerializable target = (CellSerializable)db.getContent(objectDataFormat);
//                            System.out.println("父亲是" + target.getCell().getTreeItem().getValue().toString());
//
//                            TreeItem<PlanningItem> target = itemTemp;
//                            target.getChildren().add(cell.getTreeItem());


//                            event.setDropCompleted(true);
                        }

                        TreeItem<PlanningItem> target = cell.getTreeItem();

                        System.out.println("源" + itemTemp.getValue().toString());
                        System.out.println("目标" + target.getValue().toString());

//                        target.getChildren().add(itemTemp);

                        event.consume();


                    }
                });

                return cell;
            }

            ;
        });
//        treeView.setCellFactory((TreeView<PlanningItem> p) ->
//                new TextFieldTreeCellImpl());
        StackPane root = new StackPane();
        root.getChildren().add(treeView);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Test TreeView");
        primaryStage.show();
    }
}

class TextFieldTreeCellImpl extends TreeCell<PlanningItem> {


    @Override
    public void updateItem(PlanningItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {

            } else {
                setText(getString());
                final TreeCell<PlanningItem> cell = new TreeCell<>();
                cell.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start drag-and-drop gesture */
                        System.out.println("onDragDetected");

                        /* allow any transfer mode */
                        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

                        /* put a string on dragboard */
                        ClipboardContent content = new ClipboardContent();

//                        content.put(objectDataFormat, cell);
//                        content.put(objectDataFormat,1);
//                        itemTemp = cell.getTreeItem();
                        // content.putString("Hello");// cell.getText());
                        db.setContent(content);

                        event.consume();
                    }
                });

                cell.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
                        System.out.println("onDragOver");

                        /*
                         * accept it only if it is not dragged from the same node and if it has a string data
                         */
                        if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                            /* allow for both copying and moving, whatever user chooses */
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });

                cell.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture entered the target */
                        System.out.println("onDragEntered");
                        /* show to the user that it is an actual gesture target */
                        if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                            // target.setFill(Color.GREEN);
                        }

                        event.consume();
                    }
                });

                cell.setOnDragExited(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        System.out.println("onDragExited");

                        /* mouse moved away, remove the graphical cues */
//                         target.setFill(Color.BLACK);

                        event.consume();
                    }
                });

                cell.setOnDragDropped(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data dropped */
                        System.out.println("onDragDropped");
                        /* if there is a string data on dragboard, read it and use it */
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString()) {
                            cell.setText(db.getString());
                            success = true;
                        }
                        /*
                         * let the source know whether the string was successfully transferred and used
                         */
                        event.setDropCompleted(success);

                        event.consume();
                    }
                });

                cell.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture ended */
                        System.out.println("onDragDone");
                        /* if the data was successfully moved, clear it */
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            cell.setText("");
                        }
//                        cell.setText("放置完");
                        TreeItem source = cell.getTreeItem();
//                        boolean remove = source.getParent().getChildren().remove(source);
//                        System.out.println("Remove");

                        // --------------------
                        Dragboard db = event.getDragboard();
//                        if (db.hasContent(objectDataFormat)) {
////                            CellSerializable target = (CellSerializable)db.getContent(objectDataFormat);
////                            System.out.println("父亲是" + target.getCell().getTreeItem().getValue().toString());
////
//                            TreeItem<PlanningItem> target = itemTemp;
//                            target.getChildren().add(cell.getTreeItem());
//
//
//
////                            event.setDropCompleted(true);
//                        }

                        event.consume();


                    }
                });


            }
        }
    }


    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

}
