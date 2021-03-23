/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import entity.Cours;
import dao.coursService;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class HomeController implements Initializable {

    @FXML
    private FlowPane fp;
    private ArrayList<ImageView> img=new ArrayList<>();
    private ArrayList<Hyperlink> hp=new ArrayList<>();
    public ArrayList<Label>title=new ArrayList<>();
     public ArrayList<Image>arl=new ArrayList<>();
     public ArrayList<Label>pnl=new ArrayList<>();
        private ArrayList<Cours> cours=new ArrayList<>();
        private ArrayList<String> xx=new ArrayList<>();
        
        
         private List<Cours> list=new ArrayList();
   private  List<Cours> listt=new ArrayList();
@FXML
    private TextField search;

    private  ListData listdata = new ListData();
    
      @FXML
    private Hyperlink b;
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
    /**
     * Initializes the controller class.
     */
  
        @FXML
    void search() {
        
        search.setOnKeyReleased(event -> {
        coursService pdao =new coursService();
        listt=pdao.displayAll();
        search.textProperty().addListener(( observableValue,oldValue,newValue) -> {
                                 
           list=listt.stream().filter(e->e.getTitle().contains(newValue)).distinct().collect(Collectors.toList());
           System.out.println(list); 
           
                 
            });
            
   
        
        
        

    });

    }
    
    @FXML
    void retour() {
         b.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/firstView.fxml"));
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
          coursService pdao =new coursService();
                   
                   for(Cours c : pdao.displayAllList()){
                   title.add(new Label(c.getTitle()));
                   arl.add(new Image(c.getImage()));
                   hp.add(new Hyperlink(c.getUrl()));
                   
                   xx.add(c.getUrl());
                   
                   pnl.add(new Label("By : Mr "+c.getAuthor()));
                   }
                   final WebView wb = new WebView();
                            
                  
                          
                  
                   for (int i=0; i<title.size(); i++){
                        img.add(new ImageView(arl.get(i)));
                        img.get(i).setFitWidth(250);
                        img.get(i).setFitHeight(180);
                        
                        title.get(i).setWrapText(true);
                        title.get(i).setMaxWidth(150);
                        title.get(i).setTextFill(Color.WHITE);
                        
                        pnl.get(i).setWrapText(true);
                        pnl.get(i).setMaxWidth(150);
                        pnl.get(i).setTextFill(Color.WHITE);
                        
                         String x= xx.get(i);
        
                        
                    
                   
                  
                       hp.get(i).setOnMouseClicked(e->{
                           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/webview.fxml"));
                Region root = (Region) loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                WebviewController spc = loader.getController();
                spc.setIdd(x);//envoie de l'ID de la photo   
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                        });}
                     for (int i=0; i<title.size(); i++){
                         BorderPane bp = new BorderPane();
                         VBox vb= new VBox();
                         vb.getChildren().addAll(title.get(i),pnl.get(i));
                         vb.setAlignment(Pos.CENTER_RIGHT);
                         vb.setSpacing(20);
                         
                       bp.setLeft(img.get(i));
                       bp.setCenter(vb);
                      
                       bp.setBottom(hp.get(i));
                       //bp.setRight(pnl.get(i));
                       bp.setPrefSize(400, 200);
                       
                        BorderPane.setAlignment(img.get(i), Pos.CENTER);
                        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                       fp.getChildren().addAll(bp);
                   
                    
                       
                        
                    }
                   
                       
                       
                    
                
                      
                      
                   
    }           


    @FXML
    private void gererFeedback() {
        System.out.println("fuck");
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
    private void gerergalerie() {
         ab.setOnMouseClicked(event -> {
            try {

                Parent type = FXMLLoader.load(getClass().getResource("/view/ListerFeedback.fxml"));
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
}           

        
        
    

