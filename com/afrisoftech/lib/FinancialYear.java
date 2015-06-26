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
 * @author SPL7
 */
public class FinancialYear {

    public static String getActiveFinancialYear(java.sql.Connection connDB) {

        String activeYear = null;

        java.sql.PreparedStatement pstmtActiveYear;
        try {
            pstmtActiveYear = connDB.prepareStatement("SELECT financial_year_desc FROM pb_financial_year WHERE active_year = true");
            java.sql.ResultSet rsetActiveYear = pstmtActiveYear.executeQuery();
            while (rsetActiveYear.next()) {
                activeYear = rsetActiveYear.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(FinancialYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activeYear;
    }
    public static String getCompanyInitials(java.sql.Connection connDB) {
//by saleem
        String initials = null;

        java.sql.PreparedStatement pstmtInitials;
        try {
            pstmtInitials = connDB.prepareStatement("select distinct building_name from pb_hospitalprofile limit 1");
            //SELECT DISTINCT organization_name from pb_hospitalprofile limit 1
            java.sql.ResultSet rsetInitials = pstmtInitials.executeQuery();
            while (rsetInitials.next()) {
                initials = rsetInitials.getString(1);
                
                System.out.println(initials);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage()+"\n Contact Your System Administrator");
            Logger.getLogger(FinancialYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return initials;
    }
    
    
        public static String getCompanyName(java.sql.Connection connDB) {
//by saleem
        String name = null;

        java.sql.PreparedStatement pstmtName;
        try {
            pstmtName = connDB.prepareStatement("SELECT distinct organisation_name from pb_hospitalprofile limit 1");
            
            java.sql.ResultSet rsetName = pstmtName.executeQuery();
            while (rsetName.next()) {
                name = rsetName.getString(1);
                
                System.out.println(name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage()+"\n Contact Your System Administrator");
            Logger.getLogger(FinancialYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
}
