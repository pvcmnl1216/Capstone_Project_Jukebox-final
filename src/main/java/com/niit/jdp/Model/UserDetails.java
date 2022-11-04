/*
 * Author Name: Philip Meshach
 * Date: 04-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Model;

import com.niit.jdp.Service.DatabaseService;

import java.sql.*;
import java.util.Scanner;

public class UserDetails {
    String userName;
    String password;
    String email;
    double mobileNo;
    Connection con;
    DatabaseService makeConnection=null;



    public void userDisplay()
    {
        int num;
        Scanner scanner=new Scanner(System.in);
        System.out.println("1. New User");
        System.out.println("2. Existing User");
        num=scanner.nextInt();
        switch (num)
        {
            case 1: {
                UserDetails newUser = new UserDetails();
                newUser.createNewUser();
                break;
            }
            case 2: {
                ExistingUser existingUser=new ExistingUser();
                existingUser.login();
                break;
            }
            default:
                System.out.println("Wrong Number Entered");

        }
    }
    int checkUser() {
        int found = 0;
        try {

            makeConnection = new DatabaseService();
            con=makeConnection.connect();
            String sql = "select * from userdetais ";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                try {
                    String s = resultSet.getString(2);
                    if (s.equalsIgnoreCase(userName)) {
                        found = 1;
                    }
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return found;
    }

    int inputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the UserName : ");
        userName = scanner.nextLine();
        int exists = checkUser();
        switch (exists) {
            case 1: {
                break;
            }
            default: {
                System.out.println("Enter the PassWord : ");
                password = scanner.nextLine();
                System.out.println("Enter the email : ");
                email = scanner.nextLine();
                System.out.println("Enter the Mobile Number : ");
                mobileNo = scanner.nextDouble();
            }
        }
        return exists;
    }

    void createNewUser() {

        int exists = inputFromUser();
        switch (exists) {
            case 1: {
                System.out.println("User already exists");
                break;
            }
            default: {
                try {
                    String sql = "insert into userDetais (username,password,email,mobileNo) values (?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);
                    preparedStatement.setDouble(4, mobileNo);
                    preparedStatement.executeUpdate();
                    System.out.println("User created");
                    System.out.println("Please login to use the application");
                    UserDetails user=new UserDetails();
                    user.userDisplay();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

