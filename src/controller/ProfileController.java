package controller;
import dao.PhotoServiceDao;
import entity.Photo;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YACINE
 */
public class ProfileController implements Initializable {

    @FXML
    private Button btnAjPh;
    @FXML
    private Button btnrech;
    @FXML
    private ScrollPane sp;
    @FXML
    private ImageView phProfil;
    @FXML
    private GridPane gp; 
    public ImageView iv ;
    public BorderPane bp;
    private HBox hb;
    public ArrayList<String> imagess = new ArrayList<>();
    public ArrayList<Image> ima = new ArrayList<>();
    public ArrayList<Label> imaa =new ArrayList<>();
    public ArrayList<Integer> imaaa =new ArrayList<>();
    public ArrayList<ImageView> pics = new ArrayList();
    public ArrayList<String> pic = new ArrayList();
    public int Id_membre=1;
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
    
    public String cssLayout = "-fx-background-color:#fabe2e;\n" +
                   "-fx-text-fill: #0a0400;\n"+"-fx-font-weight: bold;\n" ;
    @FXML
    private Button feedback_window_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         PhotoServiceDao ps1 = new PhotoServiceDao();      
         Image image = new Image("/img/logo.png");
         phProfil.setImage(image);
         phProfil.setFitWidth(80);
         phProfil.setFitHeight(80);
         
   
      
    for(Photo j : ps1.displayByIdMembre(Id_membre)){//extrarire les photo apparartenant a un seul utilisateur
            ima.add(new Image(j.geturl()));//Arraylist avec les urls des photo
            imaa.add(new Label(j.gettitre()));//ArrayList avec les titres des photos
            imaaa.add(j.getid_photo());//ArrayList avec les id des photos
        }  
    
    for(int i=0;i<ima.size();i++){              
            pics.add(new ImageView(ima.get(i)));//ArrayList des photo
            pics.get(i).setFitWidth(250);
            pics.get(i).setFitHeight(250);}
        
    for(int i=0;i<ima.size();i++){  
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(pics.get(i));
            //borderPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
            borderPane.setBottom(imaa.get(i));//insertion du titre de l'image au dessus de chaque photo
            BorderPane.setMargin(imaa.get(i), new Insets(10, 10, 10, 10));
            BorderPane.setAlignment(imaa.get(i),Pos.TOP_CENTER);
            imaa.get(i).setAlignment(Pos.CENTER);
            imaa.get(i).setStyle(cssLayout);
            imaa.get(i).setMinHeight(20);
            imaa.get(i).setMinWidth(250);
            BorderPane.setAlignment(imaa.get(i),Pos.TOP_CENTER);

            String s =imaa.get(i).getText();
            int n = imaaa.get(i);
            
 // redirection vers view de modification lors du clic sur une photo           
 borderPane.setOnMouseClicked(e->{
    try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdatePhotoView.fxml"));
                Region root = (Region) loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                UpdatePhotoViewController spc = loader.getController();
                spc.setIdd(n);//envoie de l'ID de la photo   
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
  
});
        gp.add(borderPane,i+2,1);//inserer les images dans un gridpane avec une seul ligne et i colonnes
       sp.setContent(gp);}//inserer le gridpane dans un scrollpane pour pouvoir scroller les images
    
         
//redirection vers la view d'ajout           
btnAjPh.setOnAction(e->{
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AjouterPhotoView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
//redirection vers la view de recherche
btnrech.setOnAction(e->{
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
      
        
    }    
    


    @FXML
    private void gererprofile(ActionEvent event) {
    }

    @FXML
    private void abonnementt(ActionEvent event) {
    }

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
    private void gerergalerie() throws IOException {
        
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