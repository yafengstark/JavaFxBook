
### 创建一个List View

```
ListView<String> list = new ListView<>();
ObservableList<String> items =FXCollections.observableArrayList (
    "Single", "Double", "Suite", "Family App");
list.setItems(items);
```
