
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
