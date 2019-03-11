/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public interface IWriteToDB {
    
    void insert() throws SQLException;
    
}
