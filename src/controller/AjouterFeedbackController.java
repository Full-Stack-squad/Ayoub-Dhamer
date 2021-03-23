/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FeedbackDAO;
import entity.Feedback;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class AjouterFeedbackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField feedback_description;
    @FXML
    private Button btn_ajouterFeedback;
    @FXML
    private Button btn_listerfeedback;
    @FXML
    private Rating feedback_rating;
    @FXML
    private Button btn_ProfileMembre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //SpinnerValueFactory<Integer> rating = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 3);
        //this.feedback_rating.setValueFactory(rating);

        btn_ProfileMembre.setOnAction(event -> {
            Parent ajouterFeedbackPage;
            try {
                ajouterFeedbackPage = FXMLLoader.load(AjouterFeedbackController.this.getClass().getResource("/view/AjouterFeedback.fxml"));
                Scene scene = new Scene(ajouterFeedbackPage);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btn_ajouterFeedback.setOnAction(event -> {
            if (isNullOrEmpty(feedback_description.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Champ Description doit etre remplie!");
                alert.show();
            } else {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                Date now = new Date();

                Feedback f = new Feedback(1, String.valueOf(now), feedback_description.getText(), (Integer) Math.round((float) feedback_rating.getRating()), 1, 1);
                FeedbackDAO fdao;
                try {

                    fdao = FeedbackDAO.getInstance();
                    fdao.add(f);
                    feedback_description.setText("");

                } catch (SQLException ex) {
                    Logger.getLogger(AjouterFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Feedback insérée avec succés!");
                alert.show();

            }
        });

        btn_listerfeedback.setOnAction(event -> {
            Parent listFeedbackPage;
            try {
                listFeedbackPage = FXMLLoader.load(getClass().getResource("/view/ListerFeedback.fxml"));
                Scene scene = new Scene(listFeedbackPage);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
