/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import java.sql.SQLException;
import java.util.List;

import utils.DataSource;
import entity.Participer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oumaima
 */
public class ServiceParticiper implements Idao<Participer> {

     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServiceParticiper(){
    con = DataSource.getInstance().getCnx();
    }
    public void ajouter(Participer t) throws SQLException {
         if(!chercher(t.getId_participer())){
         pre=con.prepareStatement("INSERT INTO `library`.`participer` ( `id_evenement`, `id_user`, `date`) VALUES ( ?, ?, ?);");
    pre.setInt(1, t.getId_evenement());
    pre.setInt(2, t.getId_user());
    Date sDate = new java.sql.Date(t.getDate().getTime());

    pre.setDate(3, sDate);
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");
    }

    public boolean delete(int id) throws SQLException {
        if(chercher(id))
        {pre=con.prepareStatement("delete from `library`.`participer` where id_participer  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;}
        System.out.println("n'existe pas");
        return false;
    }
     public boolean delete1(int id) throws SQLException {
      
        pre=con.prepareStatement("delete from `library`.`participer` where id_participer  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;
    }

    public boolean chercher(int id) throws SQLException {
 String req="select * from participer";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);    }

    public boolean chercher_ajout(Participer t) throws SQLException {
  String req="select * from participer where id_evenement= '"+t.getId_evenement()+ "' AND id_user ='"+t.getId_user()+ "'";
        List<Participer> list = new ArrayList<>();
       
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                 java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3),d1));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (list.size()!=0);
    }    

    public boolean update(Participer t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Participer> readAll() throws SQLException {
  String req="select * from participer  ";
        List<Participer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3),d1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;    }

    @Override
    public void insert(Participer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Participer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participer> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Participer displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Participer os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
