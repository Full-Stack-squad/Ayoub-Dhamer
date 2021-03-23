/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author ayoub
 * @param <T>
 */
public interface IReactionDAO<T> {

    void add(T f);

    void delete(int id);

    boolean update(int id, T f);

    List<T> list();

}
