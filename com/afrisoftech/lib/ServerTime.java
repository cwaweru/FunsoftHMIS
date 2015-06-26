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
 * @author SPL7
 */
public class ServerTime {

    public static String serverDate(java.sql.Connection connDB) {
        String serverDateString = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT concat(EXTRACT(DAY FROM TIMESTAMP "
                    + "'now')::VARCHAR, '-', EXTRACT(MONTH FROM TIMESTAMP 'now')::VARCHAR, '-', "
                    + "EXTRACT(YEAR FROM TIMESTAMP 'now')::VARCHAR, ' ',"
                    + " lpad(EXTRACT(HOUR FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':', "
                    + "lpad(EXTRACT(MINUTE FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':',"
                    + " LPAD(ROUND(EXTRACT(SECOND FROM TIMESTAMP 'now')::NUMERIC, 0)::VARCHAR,2,'0'))");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverDateString = rset.getString(1);

            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverDateString;
    }

    public static String serverTime(java.sql.Connection connDB) {
        String serverTimeString = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT concat(EXTRACT(DAY FROM TIMESTAMP "
                    + "'now')::VARCHAR, '-', EXTRACT(MONTH FROM TIMESTAMP 'now')::VARCHAR, '-', "
                    + "EXTRACT(YEAR FROM TIMESTAMP 'now')::VARCHAR, ' ',"
                    + " lpad(EXTRACT(HOUR FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':', "
                    + "lpad(EXTRACT(MINUTE FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':',"
                    + " LPAD(ROUND(EXTRACT(SECOND FROM TIMESTAMP 'now')::NUMERIC, 0)::VARCHAR,2,'0'))");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverTimeString = rset.getString(1);

            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverTimeString;
    }

    public static String serverTimeStamp(java.sql.Connection connDB) {
        String serverTimeStampString = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT concat(EXTRACT(DAY FROM TIMESTAMP "
                    + "'now')::VARCHAR, '-', EXTRACT(MONTH FROM TIMESTAMP 'now')::VARCHAR, '-', "
                    + "EXTRACT(YEAR FROM TIMESTAMP 'now')::VARCHAR, ' ',"
                    + " lpad(EXTRACT(HOUR FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':', "
                    + "lpad(EXTRACT(MINUTE FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':',"
                    + " LPAD(ROUND(EXTRACT(SECOND FROM TIMESTAMP 'now')::NUMERIC, 0)::VARCHAR,2,'0'))");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverTimeStampString = rset.getString(1);
            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverTimeStampString;
    }

    public static java.sql.Timestamp getSQLTimeStamp(java.sql.Connection connDB) {
        java.sql.Timestamp serverTimeStamp = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT now()");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverTimeStamp = rset.getTimestamp(1);
            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serverTimeStamp;
    }

    public static java.sql.Date getSQLDate(java.sql.Connection connDB) {
        java.sql.Date serverDate = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT current_date");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverDate = rset.getDate(1);
            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serverDate;
    }

    public static java.sql.Date getServerTrans_time(java.sql.Connection connDB) {
        java.sql.Date serverDate = null;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("select concat(  lpad(EXTRACT(HOUR FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':', \n"
                    + "                   lpad(EXTRACT(MINUTE FROM TIMESTAMP 'now')::VARCHAR, 2, '0'), ':',\n"
                    + "                     LPAD(ROUND(EXTRACT(SECOND FROM TIMESTAMP 'now')::NUMERIC, 0)::VARCHAR,2,'0'))");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                serverDate = rset.getDate(1);
            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(ServerTime.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serverDate;
    }
}
