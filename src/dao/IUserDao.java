/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javafx.collections.ObservableList;

/**
 *
 * @author hp
 * @param <M>
 */
public interface IUserDao<T> {

    public Object SignIn(String email, String password);

    public void SignUp(T o);

    public void delete(int id);

    public ObservableList<T> displayAll();

    public T displayByIdM(int id);

}
