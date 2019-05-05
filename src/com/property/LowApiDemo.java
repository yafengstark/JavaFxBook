package com.property;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * 低级API
 * @author tony
 * @date 2019/5/5 21:27
 */
public class LowApiDemo {
    public static void main(String[] args) {

        final DoubleProperty a = new SimpleDoubleProperty(1);
        final DoubleProperty b = new SimpleDoubleProperty(2);
        final DoubleProperty c = new SimpleDoubleProperty(3);
        final DoubleProperty d = new SimpleDoubleProperty(4);

        // 对某一个绑定类进行扩展并重写其computeValue()方法以返回绑定的当前值
        DoubleBinding db = new DoubleBinding() {

            {
                super.bind(a, b, c, d);
            }

            // 子类中调用super.bind()方法将依赖变量向上传递给了DoubleBinding类，所以默认的失效行为会被保留。
            @Override
            protected double computeValue() {
                return (a.get() * b.get()) + (c.get() * d.get());
            }
        };

        System.out.println(db.get());
        b.set(3);
        System.out.println(db.get());
    }
}
