/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author ayoub
 */
public class Reaction {
    private int id;
    private Date dateAjoutReaction;
    private String contenuReaction;
    private int rating;
    private int idImage;

    public Reaction(int id, Date dateAjoutReaction, String contenuReaction, int rating, int idImage) {
        this.id = id;
        this.dateAjoutReaction = dateAjoutReaction;
        this.contenuReaction = contenuReaction;
        this.rating = rating;
        this.idImage = idImage;
    }

    public Reaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAjoutReaction() {
        return dateAjoutReaction;
    }

    public void setDateAjoutReaction(Date dateAjoutReaction) {
        this.dateAjoutReaction = dateAjoutReaction;
    }

    public String getContenuReaction() {
        return contenuReaction;
    }

    public void setContenuReaction(String contenuReaction) {
        this.contenuReaction = contenuReaction;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    @Override
    public String toString() {
        return "Reaction{" + "id=" + id + ", dateAjoutReaction=" + dateAjoutReaction + ", contenuReaction=" + contenuReaction + ", rating=" + rating + ", idImage=" + idImage + '}';
    }
    
    
}
