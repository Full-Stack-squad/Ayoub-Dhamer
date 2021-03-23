/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField TF_nom;
    @FXML
    private TextField TF_presnom;
    @FXML
    private TextField TF_email;
    @FXML
    private PasswordField TF_password;
    @FXML
    private TextField TF_age;
    @FXML
    private Button btn_signup;
    @FXML
    private Button btn_signin_google;
    @FXML
    private TextField TF_tel;
    @FXML
    private RadioButton homme_rb;
    @FXML
    private RadioButton femme_rb;

    private String str = "Homme";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        homme_rb.setSelected(true);

        femme_rb.setOnAction((ActionEvent arg0) -> {
            if (femme_rb.isSelected()) {
                str = "Femme";
                homme_rb.setSelected(false);
                System.out.println("asba");
            }
        });

        homme_rb.setOnAction((ActionEvent arg0) -> {
            if (homme_rb.isSelected()) {
                str = "Homme";
                femme_rb.setSelected(false);
            }
        });
        
        btn_signup.setOnAction(
                (ActionEvent event) -> {
                    try {
                        UserDao udao = UserDao.getInstance();
                        udao.SignUp(new User(1,TF_nom.getText(), TF_presnom.getText(), str, Integer.parseInt(TF_age.getText()), Integer.parseInt(TF_tel.getText()), TF_email.getText(), TF_password.getText(), "Membre"));
                    } catch (SQLException ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );
    }

}
