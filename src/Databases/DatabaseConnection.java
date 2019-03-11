/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Alex
 */
public class DatabaseConnection {

    private static Connection connection;

    private DatabaseConnection() {
    }

    ;

    public static Connection getConnection() {

        if (connection == null) {
            connect();
            return connection;
        } else {
            return connection;
        }
        
    }

    private static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/orion";
        

        try {
            connection = DriverManager.getConnection(url, "root", "password");
            System.out.println("Connected to DB");
            return connection;

        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        return null;
    }
}
