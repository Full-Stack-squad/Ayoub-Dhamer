/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.DataSource;


/**
 *
 * @author asus
 */
public class ServiceEvenement implements Idao<evenement> {
    private  Connection con;
     private Statement ste;
    private PreparedStatement pre;
    
     public ServiceEvenement(){
    con = DataSource.getInstance().getCnx();
    }

    public void ajouter(evenement t) throws SQLException {
         pre=con.prepareStatement("INSERT INTO `library`.`evenement` ( `titre`, `contenu`, `image`, `dateajout`,`datemodif`,`idUser`,`nb_participer`) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, t.getTitre());
    pre.setString(2, t.getContenu());
    pre.setString(6, "0");
    pre.setString(7,"0");
    Date sDate = new java.sql.Date(t.getDateajout().getTime());
    Date sDate1 = new java.sql.Date(t.getDatemodif().getTime());
    pre.setString(3, t.getImage());
    pre.setDate(4,sDate);
     pre.setDate(5,sDate1);
   
    pre.executeUpdate();
    }

    public boolean delete(int id) throws SQLException {
        if(chercher(id)){
            System.out.println("exist");
        pre=con.prepareStatement("delete from `library`.`evenement` where id  = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;}
        else 
        {System.out.println("nexiste pas");
            return false;}
    }

    public boolean chercher(int id) throws SQLException {
         String req="select * from evenement";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }

    public boolean chercher_ajout(evenement t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(evenement t, int id) throws SQLException {
       if(chercher(id)){
       
        pre=con.prepareStatement("UPDATE evenement SET   titre =?,contenu =?,image =?,dateajout =?,datemodif =? WHERE id = ?");
         
    pre.setString(1, t.getTitre());
    
    
    pre.setString(2, t.getContenu());
  
    Date sDate = new java.sql.Date(t.getDateajout().getTime());
    Date sDate1 = new java.sql.Date(t.getDatemodif().getTime());
    pre.setString(3, t.getImage());
    pre.setDate(4, sDate);
     pre.setDate(5, sDate1);
    pre.setInt(6, id);
    pre.executeUpdate();
  pre.executeUpdate();
     
         return true;}
        System.out.println("update invalid: evenement nexiste pas");
        return false;
    }

    public List<evenement> readAll() throws SQLException {
        String req="select * from evenement  ";
        List<evenement> list = new ArrayList<>();
         ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
                ImageView v = new ImageView();
                System.out.println(rs.getString(4));
                
                v.setImage(new Image("http://127.0.0.1/doc/"+rs.getString(4)));
                v.setFitWidth(100);
                v.setFitHeight(100);
               
                //ystem.out.println(v.getImage().toString());
                evenement p2=new evenement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5),rs.getDate(6));
                
                p2.setPhoto(v);
                list.add(p2);
            
    
}
        return list;
    }

    @Override
    public void insert(evenement o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(evenement o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<evenement> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public evenement displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(evenement os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
