package com.example.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author tony
 * @date 2019/5/18 12:21
 */
public class LoginViewController {

    @FXML
    private TextField loginCodeTextField;
    @FXML
    private Button okButton;

    @FXML
    protected  void login(ActionEvent event) {
        System.out.println("点击了login");
    }


}
