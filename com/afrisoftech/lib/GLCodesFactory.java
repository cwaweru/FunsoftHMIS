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
    
    public static boolean getServiceSchemeExclusionStatus(java.sql.Connection connDB, String scheme , String service , String glAccount) {
        boolean execluded = false;
        java.lang.String[] listSet = null;
        service = service.trim();
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT   exclusions , initcap('"+service+"') as services FROM public.ac_schemes_exclusions WHERE scheme_name ILIKE ? AND gl_account = ?");
            pstmt.setString(1, scheme);
            pstmt.setString(2, glAccount);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                java.sql.Array arraySet = rset.getArray(1);
               // service = rset.getString(2);
                java.lang.Object[] listSetTest = (java.lang.Object[]) arraySet.getArray();
                if (!(listSetTest == null)) {
                    listSet = (java.lang.String[]) arraySet.getArray();
                    execluded = java.util.Arrays.asList(listSet).contains(service);
                }       
                
            }
            
            
            System.err.println("Service - "+service +" Scheme - "+scheme +" Exclusion Status - "+execluded);

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        
            return execluded;
            
        
    }
    
    public static String getDefaultPaymode(java.sql.Connection connDB) {
        java.lang.String paymode = "Cash";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT default_pay_mode FROM sales_prefs ");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                paymode = rset.getString(1);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

            System.err.println("Default Pay mode >>"+paymode);
            return paymode;
            
        
    }
    
    

    /**
     * @param aStoreSalesGLCode the storeSalesGLCode to set
     */
    public static void setStoreSalesGLCode(String aStoreSalesGLCode) {
        storeSalesGLCode = aStoreSalesGLCode;
    }
}
