package com.event.dragDemo;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JavaFxTestMain extends Application
{
    @FXML
    private ListView<String> listView1;
    
    @FXML
    private Pane pane1;
    
    private ObservableList<String> items;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Drag test");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    protected void initialize()
    {
        //定义列表的数据
        items = FXCollections.observableArrayList();
        items.addAll("Libgdx", "Unity3D", "Cocos2d", "Jpct", "Angle");
        listView1.setItems(items);

        //设置列表的构造器
        listView1.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> list)
            {
                return new CustomListCell(JavaFxTestMain.this);
            }
        });

        //pane监听拖拽
        pane1.setOnDragOver(new DragMoveHandler());
        pane1.setOnDragDropped(new DragOverHandler(this));
    }
    
    public void deleteListItem(String item)
    {
        items.remove(item);
    }
    
    public void addTextToPane(double x, double y, String str)
    {
        Text text = new Text(x, y, str);
        pane1.getChildren().add(text);
    }
}
