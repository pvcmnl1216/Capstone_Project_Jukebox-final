package com.niit.jdp.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    // the url of the database
    private static final String URL = "Jdbc:mysql://localhost:3306/jukebox";

    // the credentials of the user trying to log in to the database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "happy";

    // private field to store the reference of the connection object
    private static Connection databaseConnection;


    public  Connection connect() {
        try {
            databaseConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseConnection;
    }


    public Boolean printConnectionStatus() {

        if (databaseConnection == connect()) {
            System.out.println("Connected to the database");
        } else {
            System.err.println(" connected to the database");
        }
        return databaseConnection != null;
    }
}