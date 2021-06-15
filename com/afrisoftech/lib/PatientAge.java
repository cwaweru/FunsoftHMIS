/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MUGISPL
 */
public class PatientAge {

    public static String getPatientActualAge(java.sql.Connection connectDB, java.util.Date date) {
        String actualAge = null;
        try {
            java.sql.PreparedStatement pstmtAge = connectDB.prepareStatement("select funsoft_patient_age('" + date + "'::date)");
            java.sql.ResultSet rstAge = pstmtAge.executeQuery();
            while (rstAge.next()) {
                actualAge = rstAge.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(PatientAge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualAge;

    }

    public static float getPatientAge(java.sql.Connection connectDB, java.util.Date date) {
        float age = 0;
        try {
            java.sql.PreparedStatement pstmtAge = connectDB.prepareStatement("select date_part('year',age('" + date + "'::date))");
            java.sql.ResultSet rstAge = pstmtAge.executeQuery();
            while (rstAge.next()) {
                age = rstAge.getFloat(1);
            }

            if (age < 1) {
                java.sql.PreparedStatement pstmtMnth = connectDB.prepareStatement("select date_part('month',age('" + date + "'::date))");
                java.sql.ResultSet rstMnth = pstmtMnth.executeQuery();
                while (rstMnth.next()) {
                    age = rstMnth.getFloat(1);
                }

                if (age < 2) {
                    age = (float) 0.1;
                } else if (age < 10) {
                    age = age / 10;
                } else {
                    age = age / 100;
                }

            } 

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(PatientAge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return age;
    }
}
