
  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.commentaire;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author YACINE
 */
public class CommentaireServiceDao implements Idao<commentaire>{
    private static CommentaireServiceDao instance;
    private Statement st;
    private ResultSet rs;
    
    
    public CommentaireServiceDao() {
        DataSource ds = DataSource.getInstance();
        try {
            st=ds.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static CommentaireServiceDao getInstance(){
        if(instance==null) 
            instance=new CommentaireServiceDao();
        return instance;
    }

    @Override
    public void insert(commentaire o) {
        String req="insert into commentaire (comm,nom_user,id_photo,idU) values ('"+o.getcomm()+"','"+o.getnom_user()+"','"+o.getid_photo()+"','"+o.getidUser()+"')";
        try {
            st.executeUpdate(req);
            System.out.println("commentaire inseré");
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(commentaire o) {
        String req="delete from commentaire where id_comm="+o.getid_comm();
        commentaire c=displayById(o.getid_photo());
        
          if(c!=null)
              try {
           
            st.executeUpdate(req);
                  System.out.println("fassa5");
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<commentaire> displayAll() {
        String req="select * from commentaire";
        List<commentaire> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                commentaire p=new commentaire();
                p.setid_comm(rs.getInt(1));
                p.setcomm(rs.getString(2));
                p.setnom_user(rs.getString(3));
                p.setid_photo(rs.getInt(4));
                             
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public commentaire displayById(int id_comm) {
        String req="select * from commentaire where id_comm ="+id_comm;
           commentaire p=new commentaire();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
                p.setid_comm(rs.getInt(1));
                p.setcomm(rs.getString(2));
                p.setnom_user(rs.getString(3));
                p.setid_photo(rs.getInt(4));
                p.setidUser(rs.getInt(5));
             
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(commentaire os) {
        String qry = "UPDATE commentaire SET comm = '"+os.getcomm()+"'WHERE id_comm = "+os.getid_comm();
        
        try {
            if (st.executeUpdate(qry) > 0) {
               
                return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public List<commentaire> displaycomms (int id_photo){
    String req="select * from commentaire where id_photo ="+id_photo;
     List<commentaire> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                commentaire p=new commentaire();
                p.setid_comm(rs.getInt(1));
                p.setcomm(rs.getString(2));
                p.setnom_user(rs.getString(3));
                p.setid_photo(rs.getInt(4));
                
                             
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    
    }
    
    public List<commentaire> owndisplaycomms (int id_photo,int idU){
    String req="select * from commentaire where id_photo ='"+id_photo+"'and idU="+idU;
     List<commentaire> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                commentaire p=new commentaire();
                p.setid_comm(rs.getInt(1));
                p.setcomm(rs.getString(2));
                p.setnom_user(rs.getString(3));
                p.setid_photo(rs.getInt(4));
                             
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotoServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    
    }

    @Override
    public void ajouter(commentaire t) throws SQLException {
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
    public boolean chercher_ajout(commentaire t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(commentaire t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<commentaire> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

