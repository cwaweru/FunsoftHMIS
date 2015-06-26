/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class ClaimNumberFactory {

    static java.sql.Connection connectDB = null;

    public static String getClaimNumber(java.sql.Connection connDB) {
        connectDB = connDB;
        
        String claimNumber = null;
        
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT regexp_replace(date_part('year', current_date)::text, 0::varchar, ''::varchar) ||''|| lpad(date_part('month', current_date)::text, 2, '0') ||''|| lpad(nextval('claim_no_seq')::text, 5, '0')");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while(rset.next()){
               claimNumber = rset.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ClaimNumberFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claimNumber;

    }
}
