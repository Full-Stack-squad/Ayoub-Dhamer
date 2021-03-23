package dao;

import entity.Photo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YACINE
 */
public class PhotoServiceDao implements Idao<Photo> {
    
    private static PhotoServiceDao instance;
    private Statement st;
    private ResultSet rs;

    public PhotoServiceDao() {
        DataSource ds = DataSource.getInstance();
        try {
            st=ds.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PhotoServiceDao getInstance(){
        if(instance==null) 
            instance=new PhotoServiceDao();
        return instance;
    }

    @Override
    public void insert(Photo o) {
        String req="insert into photo (url,titre,theme,date_ajout,couleur,localisation,idU) values ('"+o.geturl()+"','"+o.gettitre()+"','"+o.gettheme()+"','"+o.getdate_ajout()+"','"+o.getcouleur()+"','"+o.getlocalisation()+"','"+o.getid_membre()+"')";
        try {
            st.executeUpdate(req);
            System.out.println("photo inseré");
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void delete(Photo o) {
       String req="delete from photo where id_photo="+o.getid_photo();
        Photo p=displayById(o.getid_photo());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    @Override
    public List<Photo> displayAll() {
        String req="select * from photo";
        List<Photo> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Photo p=new Photo();
                p.setid_photo(rs.getInt(1));
                p.seturl(rs.getString(2));
                p.settitre(rs.getString(3));
                p.settheme(rs.getString(4));
                p.setdate_ajout(rs.getString(5));
                p.setcouleur(rs.getString(6));
                p.setlocalisation(rs.getString(7));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Photo> displayAlll() {
        String req="select * from photo";
        ObservableList<Photo> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Photo p=new Photo();
                p.setid_photo(rs.getInt(1));
                p.seturl(rs.getString(2));
                p.settitre(rs.getString(3));
                p.settheme(rs.getString(4));
                p.setdate_ajout(rs.getString(5));
                p.setcouleur(rs.getString(6));
                p.setlocalisation(rs.getString(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Photo displayById(int id_photo) {
        String req="select * from photo where id_photo ="+id_photo;
           Photo p=new Photo();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                 p.setid_photo(rs.getInt(1));
                p.seturl(rs.getString(2));
                p.settitre(rs.getString(3));
                p.settheme(rs.getString(4));
                p.setdate_ajout(rs.getString(5));
                p.setcouleur(rs.getString(6));
                p.setlocalisation(rs.getString(7));
            //}  
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    public List<Photo> displayByIdMembre(int Id_membre) {
        String req="select * from photo where idU ="+Id_membre;
        List<Photo> list=new ArrayList<>();      
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               Photo p=new Photo();
                p.setid_photo(rs.getInt(1));
                p.seturl(rs.getString(2));
                p.settitre(rs.getString(3));
                p.settheme(rs.getString(4));
                p.setdate_ajout(rs.getString(5));
                p.setcouleur(rs.getString(6));
                p.setlocalisation(rs.getString(7));
                p.setid_membre(rs.getInt(8));
               
                list.add(p);
            }
            
        } 
            //}  
         catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }
    @Override
    public boolean update(Photo os) {
       String qry = "UPDATE photo SET titre = '"+os.gettitre()+"', theme = '"+os.gettheme()+"', couleur = '"+os.getcouleur()+"', localisation = '"+os.getlocalisation()+"' WHERE id_photo = "+os.getid_photo();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void ajouter(Photo t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean chercher(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean chercher_ajout(Photo t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Photo t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Photo> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
