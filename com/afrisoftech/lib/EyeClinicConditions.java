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
 * @author Charles
 */
public class EyeClinicConditions {

    public static int getContionRank(java.sql.Connection connectDB, String conditionDescription) {

        int conditionRank = -1;

        try {
            java.sql.PreparedStatement pstmtRank = connectDB.prepareStatement("SELECT condition_rank FROM public.eye_clinic_conditions WHERE condition_description ilike ?");
            pstmtRank.setObject(1, conditionDescription);
            java.sql.ResultSet rsetRank = pstmtRank.executeQuery();
            while (rsetRank.next()) {
                conditionRank = rsetRank.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return conditionRank;
    }

}
