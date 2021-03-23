/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PhotoServiceDao;
import entity.Photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Amine
 */
public class ListPhoto<Photo> {
    
    private ObservableList<Photo> phs=FXCollections.observableArrayList();
    public  ListPhoto(){
        PhotoServiceDao pdao = PhotoServiceDao.getInstance();
        phs= (ObservableList<Photo>) pdao.displayAlll();
        System.out.println(phs);
    }
            
public ObservableList<Photo> getPhoto(){
        return phs;
    }
    
}
