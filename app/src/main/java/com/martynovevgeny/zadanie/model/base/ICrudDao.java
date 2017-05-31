package com.martynovevgeny.zadanie.model.base;

import java.util.ArrayList;

/**
 * Created by websu on 30.05.2017.
 */

public interface ICrudDao<T> {

    T get(int id);

    ArrayList<T> get();

    long add(T entity);

    void update(T entity);

    void deleteById(int id);

}
