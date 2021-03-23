/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PhotoServiceDao;
import entity.Photo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class BackGallController implements Initializable {

    @FXML
    private TableView<?> tvphoto;
    @FXML
    private TableColumn<Photo, String> coltitre;
    @FXML
    private TableColumn<Photo, String> coltheme;
    @FXML
    private TableColumn<Photo, String> colcouleur;
    @FXML
    private TableColumn<Photo, String> coldate;
    @FXML
    private Button btnsupp;
    @FXML
    private ImageView imgv;
    private  ListPhoto listPhoto = new ListPhoto();
      private ObservableList<Photo> phs=FXCollections.observableArrayList(); 
    

    /**
     * Initializes the controller class.
     */
   public void clear(){
       tvphoto.getItems().clear();
   }
      public void remplir(){
           PhotoServiceDao pdao = PhotoServiceDao.getInstance();
           
        tvphoto.setItems(listPhoto.getPhoto());
        
       
        coltitre.setCellValueFactory(cell -> cell.
                getValue().getTitreProperty());
        coltheme.setCellValueFactory(cell -> cell.
                getValue().getThemeProperty());
        colcouleur.setCellValueFactory(cell -> cell.
                getValue().getcouleurProperty());
        coldate.setCellValueFactory(cell -> cell.
                getValue().getdateeProperty());
        
      }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        PhotoServiceDao pdao = PhotoServiceDao.getInstance();
       
        remplir();
        
        // TODO
    
    
    
    tvphoto.setOnMouseClicked(e->{
    ObservableList<Photo> photo = (ObservableList<Photo>) tvphoto.getSelectionModel().getSelectedItems();
    for(int i=0;i<photo.size();i++){
    imgv.setImage(new Image(photo.get(i).geturl()));}
    
    
    });
    
    btnsupp.setOnAction(e->{
    ObservableList<Photo> photo = (ObservableList<Photo>) tvphoto.getSelectionModel().getSelectedItems();
    pdao.delete(photo.get(0));
        tvphoto.getItems().removeAll((ObservableList<Photo>) tvphoto.getSelectionModel().getSelectedItems()
        );
    });
    
    
    }    
    
}
