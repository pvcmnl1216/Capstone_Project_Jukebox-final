package com.niit.jdp.Repository;

import com.niit.jdp.Model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> getAll(Connection connection) throws SQLException;

    T getById(Connection connection, int id) throws SQLException;

    boolean deleteById(Connection connection, int id) throws SQLException;
}
