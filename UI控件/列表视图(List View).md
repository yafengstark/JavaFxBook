
### 创建一个List View

```java
public class Main{
    public static void main(String[] args){
        
        ListView<String> list = new ListView<>();
        ObservableList<String> items =FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App");
        list.setItems(items);
        
    }
}

```

设置List View的宽度和高度
```java
public class Main{
    public static void main(String[] args){
       
       list.setPrefWidth(100);
       list.setPrefHeight(70);
    }
}

```

设置水平排列
```
	
list.setOrientation(Orientation.HORIZONTAL);
```

启用多选


[源码：带有Combo Box列表单元的List View](../src/com/ListViewSample.java)

![](./image/3-12-4-list-combobox.png)













