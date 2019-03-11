/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import Databases.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class Invoice implements IWriteToDB{

    private int invoiceNumber, customerNumber;
    private double payment;

    public Invoice(int customerNumber, double payment) {
        this.invoiceNumber = newCode() + 1;
        this.customerNumber = customerNumber;
        this.payment = payment;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }        

    @Override
    public String toString() {
        return "Invoice{" + "invoiceNumber=" + invoiceNumber + ", customerNumber=" + customerNumber + ", payment=" + payment + '}';
    }
            
    @Override
    public void insert() throws SQLException {
        Connection connect = DatabaseConnection.getConnection();
        
        PreparedStatement insert = connect.prepareStatement("Insert into Invoice Values("+this.invoiceNumber+", "+this.customerNumber+", "+this.payment+");");
        insert.execute();
    }
    
    private int newCode()
    {
        Connection connect = DatabaseConnection.getConnection();
        int newCode = -1;
        try {
            PreparedStatement select = connect.prepareStatement("SELECT Invoice_Number FROM Invoice Order by Invoice_Number;");
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
