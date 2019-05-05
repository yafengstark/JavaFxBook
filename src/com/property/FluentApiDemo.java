package com.property;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * 绑定
 *
 *
 * @author tony
 * @date 2019/5/5 21:16
 */
public class FluentApiDemo {

    public static void main(String[] args) {
        // 两个依赖变量
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        // 绑定变量
        NumberBinding sum = num1.add(num2);
        System.out.println(sum.getValue());
        num1.set(2);
        // 绑定变量自动更改
        System.out.println(sum.getValue());
    }
}
