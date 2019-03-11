/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import Databases.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class LineItem implements IWriteToDB{
    
    private int invoiceNumber, productCode, quantity;

    public LineItem(int invoiceNumber, int productCode, int quantity) {
        this.invoiceNumber = invoiceNumber;
        this.productCode = productCode;
        this.quantity = quantity;
    }
    
    

    @Override
    public void insert() throws SQLException {
        Connection connect = DatabaseConnection.getConnection();
        
        PreparedStatement insert = connect.prepareStatement("Insert into LineItem Values("+this.invoiceNumber+", "+this.productCode+", "+this.quantity+");");
        insert.execute();
    }
    
}
