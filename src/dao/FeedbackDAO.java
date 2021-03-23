/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Feedback;
import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ayoub
 */
public class FeedbackDAO implements IFeedbackDAO<Feedback> {

    private static FeedbackDAO instance;
    private final Connection cnx;
    private final Statement ste;

    public FeedbackDAO() throws SQLException {
        cnx = Connexion.getInstance().getConnection();
        ste = cnx.createStatement();
    }

    public static FeedbackDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new FeedbackDAO();
        }
        return instance;
    }

    @Override
    public void add(Feedback f) {
        String req = "insert into feedback (date,description,rating,id_abonne,id_membre) values (?,?,?,?,?)";
        System.out.println(String.valueOf(f.getDateAjoutFeedBack()) + "  " + f.getContenuFeedBack() + "   " + f.getRating() + "   " + f.getIdMembreAbonne());
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(req);

            statement.setString(1, String.valueOf(f.getDateAjoutFeedBack()));
            statement.setString(2, f.getContenuFeedBack());
            statement.setInt(3, f.getRating());
            statement.setInt(4, f.getIdMembreAbonne());
            statement.setInt(5, f.getIdMembre());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from feedback where id_membre = " + UserDao.connectedUser.getUserId() + " AND id_abonne = " + id;
        Feedback f = displayById(id);

        if (f != null) {
            try {
                ste.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public boolean update(int id, Feedback f) {
        String req = "UPDATE feedback SET date = '" + String.valueOf(f.getDateAjoutFeedBack()) + "', description = '" + f.getContenuFeedBack() + "', rating = '" + f.getRating() + "' WHERE id_membre = " + UserDao.connectedUser.getUserId() + " AND id_abonne = 2";

        try {
            if (ste.executeUpdate(req) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Feedback> list() {
        String req = "select * from feedback";
        ObservableList<Feedback> list = FXCollections.observableArrayList();

        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id_feedback"));
                f.setDateAjoutFeedBack(rs.getString("date"));
                f.setContenuFeedBack(rs.getString("description"));
                f.setRating(rs.getInt("rating"));
                f.setIdMembreAbonne(rs.getInt("id_abonne"));
                f.setIdMembre(rs.getInt("id_membre"));

                list.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public ObservableList<Feedback> feedbackMembre() {
        String req = "select * from feedback where id_membre = " + UserDao.connectedUser.getUserId();
        ObservableList<Feedback> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id_feedback"));
                f.setDateAjoutFeedBack(rs.getString("date"));
                f.setContenuFeedBack(rs.getString("description"));
                f.setRating(rs.getInt("rating"));
                f.setIdMembreAbonne(rs.getInt("id_abonne"));
                f.setIdMembre(rs.getInt("id_membre"));

                list.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Feedback displayById(int id) {
        String req = "select * from feedback where id_feedback =" + id;
        Feedback f = new Feedback();
        try {
            ResultSet rs = ste.executeQuery(req);
            rs.next();
            f.setId(rs.getInt("id_feedback"));
            f.setDateAjoutFeedBack(rs.getString("date"));
            f.setContenuFeedBack(rs.getString("description"));
            f.setRating(rs.getInt("rating"));
            f.setIdMembreAbonne(rs.getInt("id_abonne"));
            f.setIdMembre(rs.getInt("id_membre"));
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public Feedback findFeedback() {
        String req = "select * from feedback where id_membre =" + UserDao.connectedUser.getUserId() + " AND id_abonne =" + 2 ;
        Feedback f = new Feedback();
        try {
            ResultSet rs = ste.executeQuery(req);
            rs.next();
            f.setId(rs.getInt("id_feedback"));
            f.setDateAjoutFeedBack(rs.getString("date"));
            f.setContenuFeedBack(rs.getString("description"));
            f.setRating(rs.getInt("rating"));
            f.setIdMembreAbonne(rs.getInt("id_abonne"));
            f.setIdMembre(rs.getInt("id_membre"));
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

}
