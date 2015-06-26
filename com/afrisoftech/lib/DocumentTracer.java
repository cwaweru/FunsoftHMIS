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
public class DocumentTracer {

    private static String documentLocation = null;
    public static final String SUPPLIES_STORES = "SUPPLIES_STORES";
    public static final String SUPPLIES_PROCUREMENT = "SUPPLIES_PROCUREMENT";
    public static final String EXPENDITURE_EXAMINATION = "EXPENDITURE_EXAMINATION";
    public static final String EXPENDITURE_VOTEBOOK = "EXPENDITURE_VOTEBOOK";
    public static final String EXPENDITURE_APPROVAL = "EXPENDITURE_APPROVAL";
    public static final String PAYMENTS_TREASURY = "PAYMENTS_TREASURY";
    public static final String PAYMENTS_APPROVAL = "PAYMENTS_APPROVAL";
    
    
            
    /**
     *
     * @param connDB
     */
    public static void setDocumentLocation(java.sql.Connection connDB, String documentNumber, String referComment, String currentLocation, String nextLocation) {
        try {
            java.sql.PreparedStatement pstmtDoc = connDB.prepareStatement("INSERT INTO ac_trace_document("
                    + "document_no, doc_comment, current_location, next_location) VALUES (?, ?, ?, ?)");
            pstmtDoc.setString(1, documentNumber);
            pstmtDoc.setString(2, referComment);
            pstmtDoc.setString(3, currentLocation);
            pstmtDoc.setString(4, nextLocation);
            pstmtDoc.execute();
            pstmtDoc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(DocumentTracer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param connDB
     * @return
     */
    public static String getDocumentLocation(java.sql.Connection connDB, String documentNumber) {
        try {
            java.sql.PreparedStatement pstmtDocLocator = connDB.prepareStatement("SELECT next_location,"
                    + " FROM ac_trace_document WHERE document_no = ? order by 4 DESC LIMIT 1");
           java.sql.ResultSet rsetDoc = pstmtDocLocator.executeQuery();
            while(rsetDoc.next()){
                documentLocation = rsetDoc.getString(1);
            }
            rsetDoc.close();
            pstmtDocLocator.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(DocumentTracer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return documentLocation;

    }
}
