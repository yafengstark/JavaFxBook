package com.event.treeviewDragDrop;

/**
 * @author tony
 * @date 2019/5/7 1:25
 */
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

public final class TestplaygroundUi extends Application {

    private final DataFormat objectDataFormat = new DataFormat("application/x-java-serialized-object");

    /**
     * Constructor.
     */
    public TestplaygroundUi() {
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
                final TreeCell<PlanningItem> cell = new TreeCell<PlanningItem>(){
                    protected void updateItem(PlanningItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null) {
                            setText(item.toString());
                            setGraphic(getTreeItem().getGraphic());
                        }else{
                            setText(null);
                            setGraphic(null);
                        }

                    }
                };

                cell.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start drag-and-drop gesture */
                        System.out.println("onDragDetected");

                        /* allow any transfer mode */
                        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

                        /* put a string on dragboard */
                        ClipboardContent content = new ClipboardContent();

                        content.put(objectDataFormat, cell.getItem().toString());
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
                        // target.setFill(Color.BLACK);

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

                        if (db.hasContent(objectDataFormat)) {
                            cell.setText("更改成功了");
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

                        event.consume();
                    }
                });

                return cell;
            };
        });

        StackPane root = new StackPane();
        root.getChildren().add(treeView);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Test TreeView");
        primaryStage.show();
    }
}