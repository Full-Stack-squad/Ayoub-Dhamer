/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Reaction;
import utils.Connexion;
import java.sql.Connection;
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
public class ReactionDAO implements IReactionDAO<Reaction> {

    //private static ReclamationDao instance;
    private final Connection cnx;
    private final Statement ste;

    public ReactionDAO() throws SQLException {
        cnx = Connexion.getInstance().getConnection();
        ste = cnx.createStatement();
    }

    @Override
    public void add(Reaction f) {
        String req = "insert into reaction () values ('" + f.getDateAjoutReaction() + "','" + f.getContenuReaction() + "','" + f.getRating() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from reaction where id=" + id;
        Reaction r = displayById(id);

        if (r != null) {
            try {

                ste.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ReactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public boolean update(int id, Reaction f) {
        String req = "UPDATE reaction SET date = '" + f.getDateAjoutReaction() + "', description = '" + f.getContenuReaction() + "', rating = '" + f.getRating() + "' WHERE id = " + f.getId();

        try {
            if (ste.executeUpdate(req) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Reaction> list() {
        String req = "select * from reaction";
        ObservableList<Reaction> list = FXCollections.observableArrayList();

        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Reaction r = new Reaction();
                r.setId(rs.getInt("id_reaction"));
                r.setDateAjoutReaction(rs.getDate("date_ajout"));
                r.setContenuReaction(rs.getString("description"));
                r.setRating(rs.getInt("rating"));
                r.setIdImage(rs.getInt("id_image"));

                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private Reaction displayById(int id) {
        String req = "select * from reaction where id =" + id;
        Reaction r = new Reaction();
        try {
            ResultSet rs = ste.executeQuery(req);
            rs.next();
            r.setId(rs.getInt("id_reaction"));
            r.setDateAjoutReaction(rs.getDate("date_ajout"));
            r.setContenuReaction(rs.getString("description"));
            r.setRating(rs.getInt("rating"));
            r.setIdImage(rs.getInt("id_image"));
        } catch (SQLException ex) {
            Logger.getLogger(ReactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

}
