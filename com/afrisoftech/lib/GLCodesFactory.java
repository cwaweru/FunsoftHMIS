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
 * @author charles waweru cwaweru@systempartners.biz
 */
public class GLCodesFactory {

    private static String glCode = null;
    private static String activityDescription = null;
    private static String storeSalesGLCode = null;

    /**
     * @return the glCode
     */
    public static String getGlCode(java.sql.Connection connDB, String parameterCode) {
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT code FROM pb_activity WHERE activity ilike ?");
            pstmt.setString(1, parameterCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                glCode = rset.getString(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(GLCodesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!glCode.isEmpty()) {
            return glCode;
        } else {
            return "-";
        }
    }

    /**
     * @param aGlCode the glCode to set
     */
    public static void setGlCode(String aGlCode) {
        glCode = aGlCode;
    }

    /**
     * @return the activityDescription
     */
    public static String getActivityDescription(java.sql.Connection connDB, String glCode) {
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT activity FROM pb_activity WHERE code = ?");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                activityDescription = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(GLCodesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!activityDescription.isEmpty()) {
            return activityDescription;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    /**
     * @param aActivityDescription the activityDescription to set
     */
    public static void setActivityDescription(String aActivityDescription) {
        activityDescription = aActivityDescription;
    }

    /**
     * @return the storeSalesGLCode
     */
    public static String getStoreSalesGLCode(java.sql.Connection connDB, String parameterCode) {
        storeSalesGLCode = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT income_account FROM pb_departments WHERE department_name ilike ?");
            pstmt.setString(1, parameterCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                storeSalesGLCode = rset.getString(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        if (!storeSalesGLCode.isEmpty()) {
            return storeSalesGLCode;
        } else {
            return "-";
        }

    }

    /**
     * @param aStoreSalesGLCode the storeSalesGLCode to set
     */
    public static void setStoreSalesGLCode(String aStoreSalesGLCode) {
        storeSalesGLCode = aStoreSalesGLCode;
    }
}
