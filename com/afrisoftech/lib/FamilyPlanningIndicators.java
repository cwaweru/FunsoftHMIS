/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class FamilyPlanningIndicators {

    public static String getIndicatorStatus(java.sql.Connection connectDB, String indicatorCode, String patientNo) {

        String indicatorStatus = null;

        int count = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(cwc_indicator) FROM rh.child_health_follow_up WHERE patient_no = ? AND cwc_indicator = ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                count = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        if (count < 1) {
            return "N";
        } else {
            return "Y";
        }
    }
}
