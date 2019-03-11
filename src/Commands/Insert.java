/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import BusinessObjects.Customer;
import BusinessObjects.IWriteToDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Insert implements Command{
    
    IWriteToDB[] newBusinessObj;

    public Insert(IWriteToDB... newBusinessObj) {
        this.newBusinessObj = newBusinessObj;
    }        
    
    @Override
    public void Execute() throws SQLException{
       
        for (IWriteToDB iWriteToDB : newBusinessObj) {
            iWriteToDB.insert();
        }
    }
}
