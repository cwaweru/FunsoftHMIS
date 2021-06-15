/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 * 
 */
public class UserName {

    public static String getUserName(java.sql.Connection connectDB) {

        String userName = "-";
        try {
            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT distinct f_name || ' '|| l_name from secure_menu_access where login_name = current_user order by 1 limit 1");

            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

            while (rsetUser.next()) {

                userName = rsetUser.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userName;

    }
    
    public static String getPayrollAuthorization() {
        java.sql.Connection connectDB = HospitalMain.connectDB;
        String passwd ="ALLC123";      
       
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT authorisation FROM secure_levels_access WHERE login_username = current_user AND menu_access ='Payroll'");
            ResultSet rset = pst.executeQuery();
            while(rset.next()){
                passwd = rset.getString(1);
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        
        
        
        System.err.println(getLoginName(connectDB)+ "  pwd  ["+passwd+"]");
        return passwd;
        
    }
    
    public static String getRefundsAuthorization() {
        java.sql.Connection connectDB = HospitalMain.connectDB;
        String passwd ="ALLC123";      
       
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT authorisation FROM secure_levels_access WHERE login_username = current_user AND menu_access ='M-Pesa Refunds'");
            ResultSet rset = pst.executeQuery();
            while(rset.next()){
                passwd = rset.getString(1);
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        
        
        
        System.err.println(getLoginName(connectDB)+ "  pwd  ["+passwd+"]");
        return passwd;
        
    }
    
    public static String getPayrollApprovalAuthorization() {
        java.sql.Connection connectDB = HospitalMain.connectDB;
        String passwd ="ALLC123";      
       
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT authorisation FROM secure_levels_access WHERE login_username = current_user AND menu_access ='Payroll Approval'");
            ResultSet rset = pst.executeQuery();
            while(rset.next()){
                passwd = rset.getString(1);
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        
        
        
        System.err.println(getLoginName(connectDB)+ "  pwd  ["+passwd+"]");
        return passwd;
        
    }

    public static String getLoginName(java.sql.Connection connectDB) {

        String userName = "-";
    
        try {
            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT current_user");

            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

            while (rsetUser.next()) {

                userName = rsetUser.getString(1); //.toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userName;

    }

    public static String getAllocatedStore(java.sql.Connection connectDB, java.lang.String type) {

        String store = "-";
        String type_ = type;
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select stores from store_allocation where user_name = current_user and type ilike '" + type_ + "'");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                store = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return store;

    }

    public static String getUserAllocatedStore(java.sql.Connection connectDB, java.lang.String user) {

        String store = "-";
        //String type_=store;
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select distinct initcap(stores) from store_allocation where user_name ilike  '" + user + "' limit 1");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                store = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return store;

    }

    public static String canIApprove(java.sql.Connection connectDB, java.lang.String user) {

        String store = "NO";
        //String type_=store;
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select distinct initcap(stores) from store_allocation where user_name ilike  '" + user + "' and (type ilike 'Approve IRQ' or type ilike 'Approve SRQ/PRQ') limit 1");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                store = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return store;

    }

    public static String getUserAllocatedSection(java.sql.Connection connectDB, java.lang.String user) {

        String section = "-";
        //String type_=store;
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select distinct initcap(section) from section_allocation where user_name ilike  '" + user + "' and status=true limit 1");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                section = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return section;

    }

    public static Date getServerDate(java.sql.Connection connectDB) {
        //By Saleem

        Date serverDate = null;
        Time serveTime = null;
        try {
            java.sql.PreparedStatement pstmtDate = connectDB.prepareStatement("SELECT current_date");

            java.sql.ResultSet rsetDate = pstmtDate.executeQuery();

            while (rsetDate.next()) {

                serverDate = rsetDate.getDate(1);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serverDate;

    }

    public static String getUserFromTrail(String activity, java.sql.Connection connectDB) {
         //To change body of generated methods, choose Tools | Templates.
        // java.
        String user = "-";
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select distinct user_name from st_user_activity where substring (activity_description ,38 ,50) ilike  '" + activity + "' ");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                user = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public static String getTimestampFromTrail(String activity, java.sql.Connection connectDB) {
        //To change body of generated methods, choose Tools | Templates.
        String timestamp = "-";
        try {
            java.sql.PreparedStatement pstmtStore = connectDB.prepareStatement("select distinct activity_timestamp::timestamp(2) from st_user_activity where substring (activity_description ,38 ,50) ilike  '" + activity + "' ");

            java.sql.ResultSet rsetStore = pstmtStore.executeQuery();

            while (rsetStore.next()) {

                timestamp = rsetStore.getString(1).toUpperCase();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(UserName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return timestamp;
    }

    
    
}

