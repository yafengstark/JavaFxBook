package com.property;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 属性绑定
 * 监听属性改变事件
 * @author tony
 * @date 2019/5/5 21:11
 */
public class ChangeListenerDemo {
    public static void main(String[] args) {

        Bill electricBill = new Bill();

        electricBill.amountDueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o, Object oldVal,
                                          Object newVal){
                System.out.println("Electric bill has changed!");
            }
        });

        electricBill.setAmountDue(100.00);

    }
}
