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
public class CashShiftNumberFactory {

    static java.sql.Connection connectDB = null;

    public static String newShiftNumber(java.sql.Connection connDB) {

        String shiftNumber = null;

        String tempShiftNumber = "SHIFT_LABEL_" + com.afrisoftech.lib.DateLables.getDateLabel();

        try {

            connectDB = connDB;

            java.sql.PreparedStatement pstmtShiftNumber = connectDB.prepareStatement("INSERT INTO ac_shifts (cash_point, status, start_date, end_date, shift_no) VALUES (?,?,?,?)");

            pstmtShiftNumber.setString(1, shiftNumber);

            pstmtShiftNumber.setString(2, shiftNumber);

            pstmtShiftNumber.setString(3, shiftNumber);

            pstmtShiftNumber.setString(4, shiftNumber);

            pstmtShiftNumber.executeQuery();

        } catch (SQLException ex) {

            ex.printStackTrace();

            Logger.getLogger(CashShiftNumberFactory.class.getName()).log(Level.SEVERE, null, ex);

        }

        return shiftNumber;
    }

    public static String getPayBillNumber(java.sql.Connection connDB) {

        String payBillNumber = null;

        try {

            connectDB = connDB;

            java.sql.PreparedStatement pstmtPayBillNumber = connectDB.prepareStatement("SELECT DISTINCT paybill_no FROM ac_cash_points_setup WHERE description = current_user");

            java.sql.ResultSet rsetPayBillNumber = pstmtPayBillNumber.executeQuery();

            while (rsetPayBillNumber.next()) {

                payBillNumber = rsetPayBillNumber.getString(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

           // Logger.getLogger(CashShiftNumberFactory.class.getName()).log(Level.SEVERE, null, ex);

        }

        return payBillNumber;
    }

}
