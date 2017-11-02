package by.grechishnikov.dao;

import by.grechishnikov.entity.Bean;

import java.util.List;

public interface IBaseDAO<T extends Bean> {
    void saveOrUpdate(T t);

    T get(int id);

    void delete(T t);

    List<T> getAll();
}
