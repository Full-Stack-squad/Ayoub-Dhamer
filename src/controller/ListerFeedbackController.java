/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FeedbackDAO;
import dao.UserDao;
import entity.Feedback;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class ListerFeedbackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();

    @FXML
    private VBox vb;
    @FXML
    private ScrollPane scroll_feedback;
    @FXML
    private PieChart feedback_stats;
    @FXML
    private Label feedback_nb;
    @FXML
    private Label images_nb;
    @FXML
    private ImageView images;
    @FXML
    private ImageView coms;
    @FXML
    private Button add_feedback_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Rectangle clip = new Rectangle(
                images.getFitWidth(), images.getFitHeight()
        );
        clip.setArcWidth(60);
        clip.setArcHeight(60);

        images.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage img = images.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        images.setClip(null);

        // apply a shadow effect.
        images.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        images.setImage(img);

        Rectangle clipC = new Rectangle(
                coms.getFitWidth(), coms.getFitHeight()
        );
        clipC.setArcWidth(300);
        clipC.setArcHeight(80);

        coms.setClip(clipC);

        // snapshot the rounded image.
        parameters.setFill(Color.TRANSPARENT);
        WritableImage imgC = coms.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        coms.setClip(null);

        // apply a shadow effect.
        coms.setEffect(new DropShadow(30, Color.BLACK));

        // store the rounded image in the imageView.
        coms.setImage(imgC);

        try {
            UserDao ud = UserDao.getInstance();

            if (ud.verify() == 1) {
                add_feedback_btn.setText("Modifier Commentaire");
            } else {
                add_feedback_btn.setVisible(false);
            }

            feedback_nb.setText("" + ud.feedbackCount() + " commentaires");
            images_nb.setText("" + ud.imageCount() + " images");

            ObservableList<PieChart.Data> pc = FXCollections.observableArrayList(
                    new PieChart.Data("1 star rating", ud.starsCount(1)),
                    new PieChart.Data("2 star rating", ud.starsCount(2)),
                    new PieChart.Data("3 star rating", ud.starsCount(3)),
                    new PieChart.Data("4 star rating", ud.starsCount(4)),
                    new PieChart.Data("5 star rating", ud.starsCount(5))
            );
            feedback_stats.setData(pc);

            feedback_stats.getData().stream().map((data) -> {
                data.nameProperty().set(data.getName() + " : " + (int) data.getPieValue() + " commentaires");
                return data;
            }).forEachOrdered((data) -> {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    JOptionPane.showMessageDialog(null, data.getName() + "\nTotal Commentaires --" + (int) data.getPieValue());
                });
            });
        } catch (SQLException ex) {
            Logger.getLogger(ProfileMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Node n;
        try {

            Label title_date = new Label("Date d'ajout");
            title_date.setMinWidth(250);
            title_date.setMaxWidth(250);
            title_date.setAlignment(Pos.CENTER);
            Label title_description = new Label("Description");
            title_description.setMinWidth(300);
            title_description.setMaxWidth(300);
            title_description.setAlignment(Pos.CENTER);
            Label title_rating = new Label("Notation");
            title_rating.setMinWidth(80);
            title_rating.setMaxWidth(80);

            n = (Node) FXMLLoader.load(getClass().getResource("/view/Item.fxml"));
            ((HBox) n).setFillHeight(true);
            ((HBox) n).setAlignment(Pos.CENTER);

            //((HBox) n).setStyle("-fx-background-color: #FFFACD;");
            ((HBox) n).getChildren().addAll(title_date, title_description, title_rating);
            vb.getChildren().add(n);
        } catch (IOException ex) {
            Logger.getLogger(ListerFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            FeedbackDAO fb = FeedbackDAO.getInstance();
            feedbacks = fb.feedbackMembre();
            TextField field = new TextField();
            Node[] nodes = new Node[feedbacks.size()];
            for (int i = 0; i < nodes.length; i++) {

                Label label_date = new Label("" + feedbacks.get(i).getDateAjoutFeedBack());
                label_date.setMinWidth(250);
                label_date.setMaxWidth(250);
                label_date.setAlignment(Pos.CENTER);
                Label label_description = new Label("" + feedbacks.get(i).getContenuFeedBack());
                label_description.setMinWidth(300);
                label_description.setMaxWidth(300);
                Label label_rating = new Label("" + feedbacks.get(i).getRating());
                label_rating.setMinWidth(10);
                label_rating.setMaxWidth(10);

                Image image = new Image("/utils/star.png");
                ImageView iv = new ImageView();
                iv.setFitWidth(20);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);

                iv.setImage(image);
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource("/view/Item.fxml"));
                ((HBox) nodes[i]).setFillHeight(true);
                ((HBox) nodes[i]).setAlignment(Pos.CENTER);

                ((HBox) nodes[i]).getChildren().addAll(label_date, label_description, label_rating, iv);
                vb.getChildren().add(nodes[i]);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListerFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
