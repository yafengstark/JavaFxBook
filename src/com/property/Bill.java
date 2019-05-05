package com.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author tony
 * @date 2019/5/5 21:12
 */
public class Bill {
    // Define a variable to store the property
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    // Define a getter for the property's value
    public final double getAmountDue(){return amountDue.get();}

    // Define a setter for the property's value
    public final void setAmountDue(double value){amountDue.set(value);}

    // Define a getter for the property itself
    public DoubleProperty amountDueProperty() {return amountDue;}
}
