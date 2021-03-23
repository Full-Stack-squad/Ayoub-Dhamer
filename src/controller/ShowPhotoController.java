package controller;

import dao.CommentaireServiceDao;
import dao.PhotoServiceDao;
import entity.commentaire;
import entity.Photo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YACINE
 */
public class ShowPhotoController implements Initializable {
    
    public int g;
    @FXML
    private Label tftitre;
    @FXML
    private Label tfcol;
    @FXML
    private ImageView ima;
    @FXML
    private Button btnProfil;
    @FXML
    private Button BtnRech;
    @FXML
    private ScrollPane sp;
    @FXML
    private Button btncomm;
    public ArrayList comments;
    public ArrayList<Label> comms = new ArrayList<>();
    public ArrayList<String> usernames = new ArrayList<>();
    public ArrayList<Label> imaa =new ArrayList<>();
    @FXML
    private ScrollPane sp1;
    public int idU=2;
    @FXML
    private Button btnmodif;
    @FXML
    private Pane pane;
    @FXML
    private Button ab51;
    @FXML
    private Button ab3;
    @FXML
    private Button ab4;
    @FXML
    private Button ab2;
    @FXML
    private Button ab;
    @FXML
    private Button ab1;
    @FXML
    private Button feedback_window_btn;

    
    
    
    
    public void getcomms(int iidd){
        
    CommentaireServiceDao cc = new CommentaireServiceDao();
     
imaa.clear();
    for (commentaire c : cc.displaycomms(iidd)){
               
               imaa.add(new Label("  "+c.getnom_user()+" :    "+c.getcomm()));
               VBox vBox=new VBox();
               vBox.getChildren().clear();
               imaa.forEach( e-> vBox.getChildren().add(e));
               imaa.forEach(e->e.setMinHeight(50));
               String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 2;\n" +
                   "-fx-border-width: 2;\n" +
                   "-fx-border-style: solid;\n"+
                   "-fx-border-radius: 10;\n";
               imaa.forEach(e->e.setMaxWidth(Double.MAX_VALUE));
               imaa.forEach(e->e.setStyle(cssLayout));
               sp.setContent(vBox);   
    }}
    public void mycomms(int iidd){
        
    CommentaireServiceDao ccc = new CommentaireServiceDao();
     
comms.clear();
    for (commentaire c : ccc.owndisplaycomms(iidd, idU)){
               
               comms.add(new Label("  "+c.getnom_user()+" :    "+c.getcomm()));
               VBox vB=new VBox();
               vB.getChildren().clear();
               comms.forEach( e-> vB.getChildren().add(e));
               comms.forEach(e->e.setMinHeight(50));
               String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 2;\n" +
                   "-fx-border-width: 2;\n" +
                   "-fx-border-style: solid;\n"+
                   "-fx-border-radius: 10;\n";
               comms.forEach(e->e.setMaxWidth(Double.MAX_VALUE));
               comms.forEach(e->e.setStyle(cssLayout));
               sp1.setContent(vB);   
               
    }}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

BtnRech.setOnAction(e->{
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/RechercheView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
        
         btnProfil.setOnAction(e->{
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ProfileView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
    }    
    
    
    
public int setIdd(int id){
    
        PhotoServiceDao ps1 = new PhotoServiceDao();
        Photo photo1 = new Photo();
        photo1=ps1.displayById(id);//recuperer la photo avec son id
        g=id;
        tftitre.setText(photo1.gettitre());
        Image image = new Image(photo1.geturl());
        ima.setImage(image);
        ima.setFitHeight(300);
        ima.setFitWidth(300);
        sp.setContent(null);
        getcomms(id);
        mycomms(id);

    
     //le Bouton Ajouter Commentaire Lambda expression 🙂🙂   
        
    btncomm.setOnAction(e->{
               CommentaireServiceDao ccc =CommentaireServiceDao.getInstance();
                    TextInputDialog dialog = new TextInputDialog("walter");
                    dialog.setTitle("ajouter un commentaire");
                    
                    dialog.setContentText("ajoutez votre commentaire:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                    commentaire c1 = new commentaire(result.get(),"user",id,idU);
                    ccc.insert(c1);
                    sp.setContent(null);
                    getcomms(id);
                    mycomms(id);                   
}                    
});
    
    btnmodif.setOnAction(e->{
     try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GererComm.fxml"));
                Region root = (Region) loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                GererCommController gcc = loader.getController();
                gcc.setIdd(id,idU);//envoie de l'ID de la photo  
                System.err.println(id+"+"+idU);
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
  


return id; 

};

    




    @FXML
    private void gerercours() {
        
        ab4.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
                Scene scene = new Scene(type);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                 stage.setTitle("Gestion de cours"); 
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void gererevenement() {
        ab2.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/AfficherEvenement.fxml"));
                Scene scene = new Scene(type);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                 stage.setTitle("Gestion de cours"); 
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void gerergalerie() {
          ab.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/ProfileView.fxml"));
                Scene scene = new Scene(type);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                 stage.setTitle("Gestion de cours"); 
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }


    @FXML
    private void gererprofile(ActionEvent event) {
    }

    @FXML
    private void abonnementt(ActionEvent event) {
    }

    @FXML
    private void gererreclamation(ActionEvent event) {
    }

    @FXML
    private void gererFeedback() {
        feedback_window_btn.setOnMouseClicked(event -> {
            System.out.println("hey");
           try {
                Parent type = FXMLLoader.load(getClass().getResource("/view/ListerFeedback.fxml"));
                Scene scene = new Scene(type);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                System.out.println("hey");
                 stage.setTitle("Gestion des commentaires"); 
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
    }


    

}


