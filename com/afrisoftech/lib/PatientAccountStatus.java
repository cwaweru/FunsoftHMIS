/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class PatientAccountStatus {

    public static double getOutstandillBill(java.sql.Connection connDB, String patientNumber) {

        double outstandingBill = 0.00;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE patient_no = ? AND date < current_date");

            pstmt.setString(1, patientNumber);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                outstandingBill = rset.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return outstandingBill;

    }

    public static double getAbscondmentStatus(java.sql.Connection connDB, String patientNumber) {

        double abscondmentAmount = 0.00;

        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT sum(debit-credit) FROM ac_debtors WHERE admission_no = ? AND (dealer ilike '%abscond%' OR payee ilike '%abscond%' or item ilike '%abscond%')");

            pstmt.setString(1, patientNumber);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                abscondmentAmount = rset.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return abscondmentAmount;

    }
    
        public static double getPersonalDebtStatus(java.sql.Connection connDB, String patientNumber) {

        double abscondmentAmount = 0.00;

        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT sum(debit-credit) FROM ac_debtors WHERE admission_no = ? AND (dealer ilike '%dmu%' OR payee ilike '%dmu%' or item ilike '%dmu%')");

            pstmt.setString(1, patientNumber);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                abscondmentAmount = rset.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return abscondmentAmount;

    }

}
