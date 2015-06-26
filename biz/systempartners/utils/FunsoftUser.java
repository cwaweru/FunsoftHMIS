/*
 * FunsoftUser.java
 *
 * Created on July 16, 2008, 6:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.utils;

import java.sql.SQLException;

/**
 *
 * @author funsoft
 */
public class FunsoftUser {
    
    /** Creates a new instance of FunsoftUser */
    public FunsoftUser() {
        
    }
    
    public static String getEmailAddress(java.sql.Connection connectDB){
        
        String userEmail = null;
        
        
        userEmail = getUserMailAddress(connectDB);
        
        return userEmail;
        
    }
    
    public static String[] getCellPhones(){
        
        String cellPhones[] = null;
        
        return cellPhones;
        
    }
    
    private static java.lang.String getUserMailAddress(java.sql.Connection connexion){
        
        String email2fetch = null;
        
        java.sql.PreparedStatement pstmtMail;
        
        try {
            pstmtMail = connexion.prepareStatement("SELECT email_address FROM secure_password WHERE login_name = current_user");

        
        java.sql.ResultSet rsetMail = pstmtMail.executeQuery();
        
        while(rsetMail.next()){
            
            email2fetch = rsetMail.getString(1);
            
        }
                } catch (SQLException ex) {
                    
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        return email2fetch;
        
    }
    
}
