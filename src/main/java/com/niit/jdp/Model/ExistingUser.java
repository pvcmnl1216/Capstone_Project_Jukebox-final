/*
 * Author Name: Philip Meshach
 * Date: 04-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Model;

import com.niit.jdp.Service.DatabaseService;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExistingUser {
    String userName;
    String password;
    Connection con;
    void inputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the UserName : ");
        userName = scanner.nextLine();
        System.out.println("Enter the PassWord : ");
        password = scanner.nextLine();
    }

    void login()  {
        DatabaseService makeConnection = new DatabaseService();
         con=makeConnection.connect();
        inputFromUser();
        int found = 1;
        {
            try {
                String sql = "select * from userDetails ";
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next() && found == 1) {
                    try {
                        String s1 = resultSet.getString(2);
                        String s2 = resultSet.getString(3);

                        if (s1.equalsIgnoreCase(userName) && s2.equalsIgnoreCase(password))
                            found = 0;

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (found == 0) {
                System.out.println("Welcome "+userName);
                Menu menu=new Menu();
            }
            else
            {
                System.out.println("UserName or Password invalid");
            }
        }
    }
}
