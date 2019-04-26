
## 参考：
[lable](http://www.javafxchina.net/blog/2015/04/doc02_label/)

![](./image/3-2-1-labels-300x141.png)

左边的Label是一个带图标的文本，中间的展示了旋转效果，右边的使用了折叠换行设置。

## 创建Label
```
//一个空Label
Label label1 = new Label();
//一个带文本元素的Label
Label label2 = new Label("Search");
//一个带文本和图标的Label
Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
Label label3 = new Label("Search", new ImageView(image));
```

·setText(String text) method——指定文本内容

·setGraphic(Node graphic)——指定图标

```
Label label1 = new Label("Search");
Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
label1.setGraphic(new ImageView(image));
label1.setTextFill(Color.web("#0076a3"));

Label label1 = new Label("Search");
Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
label1.setGraphic(new ImageView(image));
label1.setTextFill(Color.web("#0076a3"));
```

可以设定文本的填充颜色。

设置字体

选择、缩放
```
Label label2 = new Label("Values");
label2.setFont(new Font("Cambria", 32));
label2.setRotate(270);
label2.setTranslateY(50);
```
