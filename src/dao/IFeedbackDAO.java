/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ayoub
 * @param <T>
 */
public interface IFeedbackDAO<T> {
   void add(T f);
   void delete(int id);
   boolean update(int id, T f);
   ObservableList<T> list();
   ObservableList<T> feedbackMembre();
}
