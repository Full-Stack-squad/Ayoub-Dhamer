/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class SignInController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btn_signIn;
    @FXML
    private Label close;
    
    @FXML
    private Hyperlink forget;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_signIn.setOnAction((ActionEvent event) -> {
            
            UserDao udao;
            User user;
            try {
                udao = UserDao.getInstance();
                Object o = udao.SignIn(email.getText(), password.getText());
                if (o != null) {
                    UserDao.connectedUser = (User)o;
                    System.out.println((User)udao.SignIn(email.getText(), password.getText()));
                    if (((User)o).getUserType().equals("Membre")) {
                        Parent membreProfilePage = FXMLLoader.load(SignInController.this.getClass().getResource("/view/firstView.fxml"));
                        Scene scene = new Scene(membreProfilePage);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } else if (((User)o).getUserType().equals("Enseignant")) {
                        Parent adminProfilePage = FXMLLoader.load(SignInController.this.getClass().getResource("/view/FXML.fxml"));
                        Scene scene = new Scene(adminProfilePage);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("email ou password incorrect!");
                    alert.show();
                }
            }catch (SQLException | IOException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
         forget.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
                Scene scene = new Scene(type);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                 stage.setTitle("Gestion de cours"); 
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
