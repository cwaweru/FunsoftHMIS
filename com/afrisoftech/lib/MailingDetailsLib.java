/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import javax.swing.JOptionPane;

/**
 *
 * @author sytem partners
 */
public class MailingDetailsLib {
      //public static java.sql.Connection connectDB = null;
    //org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    public MailingDetailsLib() {

     //connectDB = connDb;
        //pConnDB = pconnDB;
        //java.sql.Connection connDb,org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB
    }

    ////getting the sender password
    public static String getSenderPassword(java.sql.Connection connectDB, String category) {
        String pass = null;
        try {
            java.sql.Statement stsmtsss = connectDB.createStatement();
            java.sql.ResultSet res = stsmtsss.executeQuery("SELECT   sender_password  FROM mailers_group_setup where mailer_category='" + category + "' ");

            while (res.next()) {
                pass = res.getString(1);
                //System.out.println("THE TO PASSWORD IS " +pass);
            }
        } catch (Exception esd) {
            esd.printStackTrace();
            esd.getMessage();

        }

        return pass;
    }

    ////returns the sender of the mail
    public static String getSenderFromAddress(java.sql.Connection connectDB, String category) {
        String from_Address = null;
        try {
            java.sql.Statement stsmtss = connectDB.createStatement();
            java.sql.ResultSet res = stsmtss.executeQuery("SELECT  sender_email, mailer_category, sender_password  FROM mailers_group_setup where mailer_category='" + category + "' ");

            while (res.next()) {
                from_Address = res.getString(1);

                System.out.println("THE FROM ADDRESS IS " + from_Address);

            }
        } catch (Exception esd) {
            esd.printStackTrace();
            esd.getMessage();

        }

        return from_Address;
    }

    //////returns the to address
    public static String getTheToAddress(java.sql.Connection connectDB, String Supplier) {
        String to_Address = null;
        try {
            java.sql.Statement stsmts = connectDB.createStatement();
            java.sql.ResultSet res = stsmts.executeQuery("SELECT distinct email_address from st_suppliers where supplier_name='" + Supplier + "' group by code,supplier_name");

            while (res.next()) {
                if (res.getString(1) != null || !res.getString(1).equals("-")) {
                    to_Address = res.getString(1);

                } else {
                    to_Address = null;

                    JOptionPane.showMessageDialog(null, "THE SUPPLIER NEEDS TO HAVE THE EMAIL ADDRESS SETUP!", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);

                }
                System.out.println("THE TO ADDRESS IS " + to_Address);

            }
        } catch (Exception esd) {
            esd.printStackTrace();
            esd.getMessage();

        }

        return to_Address;
    }

}
