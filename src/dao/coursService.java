/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Cours;
import utils.DataSource;

/**
 *
 * @author Amine
 */
public class coursService implements Idao<Cours>{
    
    private static coursService instance;
    private Statement st;
    private ResultSet rs;
    
    public coursService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static coursService getInstance(){
        if(instance==null) 
            instance=new coursService();
        return instance;
    }

    @Override
    public void insert(Cours c) {
        String req="insert into cours (title,author,description,date,category,url,image,idU) values ('"+c.getTitle()+"','"+c.getAuthor()+"','"+c.getDescription()+"','"+c.getDate()+"','"+c.getCategory()+"','"+c.getUrl()+"','"+c.getImage()+"','"+c.getIdU()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Cours c) {
        String req="delete from cours where id="+c.getId();
        Cours p=displayById(c.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    
      @Override
    public boolean update(Cours p) {
        String qry = "UPDATE cours SET title = '"+p.getTitle()+"', author = '"+p.getAuthor()+"',description = '"+p.getDescription()+"' ,date = '"+p.getDate()+"' ,category = '"+p.getCategory()+"' , url = '"+p.getUrl()+"',image = '"+p.getImage()+"' WHERE id = "+p.getId();
        
        try {                        
            if (st.executeUpdate(qry) > 0) {                                                       
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Cours> displayAll() {
        String req="select * from cours";
        ObservableList<Cours> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Cours p=new Cours();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setAuthor(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getString(5));
                p.setCategory(rs.getString(6));
                
                p.setUrl(rs.getString(8));
                p.setImage(rs.getString(7));
                
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
   
    
    
    public List<Cours> displayAllList() {
        String req="select * from cours";
        List<Cours> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Cours p=new Cours();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getString(5));
                p.setCategory(rs.getString(6));
                
                p.setUrl(rs.getString(8));
                p.setImage(rs.getString(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
   
    
    @Override
    public Cours displayById(int id) {
           String req="select * from cours where id ="+id;
           Cours p=new Cours();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setCategory(rs.getString("category"));
                
                p.setUrl(rs.getString("url"));
                p.setImage(rs.getString("image"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

      
    public ObservableList<Cours> displayByAu(int user, String author) {
        
           String req="select * from cours where idU ="+user;
           ObservableList<Cours> list=FXCollections.observableArrayList();
           
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
            Cours p=new Cours();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setCategory(rs.getString("category"));
                
                p.setUrl(rs.getString("url"));
                p.setImage(rs.getString("image"));
                p.setIdU(rs.getInt("idU"));
                list.add(p);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }
    
     public List<Cours> displayByA(int user) {
        
           String req="select * from cours where idU ="+user ;
             List<Cours> list=new ArrayList<>();
           Cours p=new Cours();
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
            
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setCategory(rs.getString("category"));
                
                p.setUrl(rs.getString("url"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }

    @Override
    public void ajouter(Cours t) throws SQLException {
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
    public boolean chercher_ajout(Cours t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cours t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cours> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
    
}
