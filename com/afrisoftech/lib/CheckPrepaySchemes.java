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
public class CheckPrepaySchemes {
   
    public static boolean checkPrepayStatus(java.sql.Connection connectDB, String schemeAccount) {

        boolean checkPrepay = false;

        try {
            java.sql.PreparedStatement pstmtPrepay = connectDB.prepareStatement("SELECT pre_paid FROM ac_schemes WHERE account_no = ?");
            pstmtPrepay.setString(1, schemeAccount);
            java.sql.ResultSet rsetPrepay = pstmtPrepay.executeQuery();
            while (rsetPrepay.next()) {
                checkPrepay = rsetPrepay.getBoolean(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CheckPrepaySchemes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkPrepay;
    }
    public static double checkLimitExceeded(java.sql.Connection connectDB, String schemeAccount) {

        double checkLimit = 0.00;

        boolean checkPrepay = false;

        try {
            java.sql.PreparedStatement pstmtPrepay = connectDB.prepareStatement("SELECT pre_paid FROM ac_schemes WHERE account_no = ?");
            pstmtPrepay.setString(1, schemeAccount);
            java.sql.ResultSet rsetPrepay = pstmtPrepay.executeQuery();
            while (rsetPrepay.next()) {
                checkPrepay = rsetPrepay.getBoolean(1);
            }
            if (checkPrepay) {
                java.sql.PreparedStatement pstmtLimit = connectDB.prepareStatement("SELECT sum(credit-debit) FROM ac_debtors WHERE account_no = ?");
                pstmtLimit.setString(1, schemeAccount);
                java.sql.ResultSet rsetLimit = pstmtLimit.executeQuery();
                while (rsetLimit.next()) {
                    checkLimit = rsetLimit.getDouble(1);
                }
                
                   java.sql.PreparedStatement pstmtLimitcard = connectDB.prepareStatement(""
                           + "select sum(debit-credit) from hp_patient_card where card_no=?");
                pstmtLimitcard.setString(1, schemeAccount);
                java.sql.ResultSet rsetLimitcard = pstmtLimitcard.executeQuery();
                while (rsetLimitcard.next()) {
                    checkLimit = checkLimit-(rsetLimitcard.getDouble(1));
                }
                System.out.println("\t\t\t\t\t\t\t\t\t\n\n\n\n\n\t\t\t\t\t\t\t\tthe check limit is "+checkLimit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CheckPrepaySchemes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkLimit;
    }
   
    
    
}
