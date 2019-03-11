/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import Databases.DatabaseConnection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Customer implements IWriteToDB {

    private int Customer_Number;
    private String Name, Address, City, Province, Zip;
    private double Deposit;

    public Customer(String Name, String Address, String City, String Province, String Zip, double Deposit) {
        this.Customer_Number = newCode() + 1;
        this.Name = Name;
        this.Address = Address;
        this.City = City;
        this.Province = Province;
        this.Zip = Zip;
        this.Deposit = Deposit;
    }
    
    @Override
    public void insert() throws SQLException {
                
        Connection connect = DatabaseConnection.getConnection();        
        PreparedStatement insert = connect.prepareStatement("insert into Customer values("+this.Customer_Number+" ,'"+this.Name+"', '"+this.Address+"', '"+this.City+"', '"+this.Province+"', '"+this.Zip+"', "+this.Deposit+");");        
        
        insert.execute();
    }
    
    private int newCode()
    {
        Connection connect = DatabaseConnection.getConnection();
        int newCode = -1;
        try {
            PreparedStatement select = connect.prepareStatement("SELECT Customer_Number FROM Customer Order by Customer_Number;");
            ResultSet result = select.executeQuery();           
            
            result.afterLast();
            result.previous();
            newCode = result.getInt(1);                               
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }                
        return newCode;
    }

}
