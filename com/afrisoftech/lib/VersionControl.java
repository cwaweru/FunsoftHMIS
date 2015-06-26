/*
 * SQLDateFormat.java
 *
 * Created on September 13, 2006, 9:46 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author root
 */
public class VersionControl {
    
    /**
     * Creates a new instance of SQLDateFormat
     */
    
    public static String VersionControl(java.sql.Connection connectDB){
        String version=null;
        try {
            
            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement("select version from version_control where active = true");
            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
            while(rsetVector.next()){
                version=rsetVector.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Exceptions.printStackTrace(ex);
        }
        return version;
     }
    
       public static boolean ActiveVersion(java.sql.Connection connectDB){
        boolean active=false;
        try {
            
            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement("select active from version_control");
            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
            while(rsetVector.next()){
                active=rsetVector.getBoolean(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Exceptions.printStackTrace(ex);
        }
        return active;
     }
  
}
