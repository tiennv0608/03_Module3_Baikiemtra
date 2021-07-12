package DAO;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDAO<T> {
    List<T> findAll() throws SQLException, ClassNotFoundException;

    T findById(int id) throws SQLException, ClassNotFoundException;

    List<T> findByName(String name) throws SQLException, ClassNotFoundException;

    void add(T t) throws SQLException, ClassNotFoundException;

    void edit(int id, T t) throws SQLException, ClassNotFoundException;

    void delete(int id) throws SQLException, ClassNotFoundException;
}
