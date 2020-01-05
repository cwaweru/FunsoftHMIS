/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class MobilePayments {

    public static double getTokenValue(java.sql.Connection connectDB, String txID) {

        double tokenValue = 0.00;

        try {
            java.sql.PreparedStatement pstmtToken = connectDB.prepareStatement("SELECT sum(debit-credit) FROM mobile_payments WHERE mobile_tx_id = ?");

            pstmtToken.setString(1, txID);

            java.sql.ResultSet rsetToken = pstmtToken.executeQuery();

            while (rsetToken.next()) {
                tokenValue = rsetToken.getDouble(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return tokenValue;

    }

    public static void updateTokenValue(java.sql.Connection connectDB, String txID, double billAmount) {

        try {

            java.sql.PreparedStatement pstmtToken = connectDB.prepareStatement("INSERT INTO public.mobile_payments("
                    + "            payment_id, transaction_no, checkout_request_id, checkout_request_id_paid, "
                    + "            paid_amount, mpesa_trx_id, transaction_time, user_name, billed_amount, "
                    + "            patient_no, telephone_number, dealer, pay_mode, payment_mode, "
                    + "            receipt_no, journal_no, mobile_tx_id, account_no, transaction_type, "
                    + "            debit, credit, date, closed, reconciled, withdrawn, cash_point, "
                    + "            date_reconcilled, quantity, mobilepay_alert)"
                    + "SELECT payment_id, transaction_no, checkout_request_id, checkout_request_id_paid, "
                    + "       paid_amount, mpesa_trx_id, now(), current_user, billed_amount, "
                    + "       patient_no, telephone_number, dealer, pay_mode, payment_mode, "
                    + "       receipt_no, journal_no, mobile_tx_id, account_no, transaction_type, "
                    + "       0.00, ?, now()::date, closed, reconciled, withdrawn, cash_point, "
                    + "       date_reconcilled, quantity, false"
                    + "  FROM public.mobile_payments WHERE mobile_tx_id = ? ORDER BY transaction_time LIMIT 1");

            pstmtToken.setDouble(1, billAmount);
            pstmtToken.setString(2, txID);

            pstmtToken.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
     public static boolean isTokenAuthentic(java.sql.Connection connectDB, String txID) {

        boolean tokenAuthentic = false;

        try {
            java.sql.PreparedStatement pstmtToken = connectDB.prepareStatement("SELECT count(checkout_request_id) FROM mobile_payments WHERE mobile_tx_id = ? AND checkout_request_id IS NOT NULL AND credit = 0");

            pstmtToken.setString(1, txID);

            java.sql.ResultSet rsetToken = pstmtToken.executeQuery();

            while (rsetToken.next()) {
                
                int tokenCount = 0;
                
                tokenCount = rsetToken.getInt(1);
                
                if(tokenCount < 1){
                    
                    tokenAuthentic = true;
                    
                }
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return tokenAuthentic;

    }   
    

}
