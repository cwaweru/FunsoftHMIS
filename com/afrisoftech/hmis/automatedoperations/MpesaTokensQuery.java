/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hmis.automatedoperations;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class MpesaTokensQuery implements Runnable{

    static java.sql.Connection connectDB;

    public static void main(String args[]) {

        connectDB = getDBConnection();
        
        MpesaTokensQuery mpesaThread = new MpesaTokensQuery();
        
        mpesaThread.run();
       
    }

    private static java.sql.Connection getDBConnection() {

        java.sql.Connection connDB = null;

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(AutomatedIPCharges.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
        try {

            connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/funsoft", "postgres", "africana");

            System.out.println("Connection object [" + connDB.toString() + "]");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AutomatedIPCharges.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }

        return connDB;

    }


    @Override
        public void run() {
            while (true) {

                if (checkMpesaTokenMessages()) {

                    String phoneNumber = "254714433693";

                    biz.systempartners.claims.SendSMS.SendSMS(phoneNumber, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token!" + "\n\nFrom:\n CPGH" );

                }

                try {
                    Thread.currentThread().sleep(3600000);//java.lang.Integer.parseInt(System.getProperty("sms.fetch.snooze.period", "5000")));
                } catch (java.lang.InterruptedException intEx) {
                    intEx.printStackTrace();
                }

            }

        }


    private boolean checkMpesaTokenMessages() {

        boolean checkItems = false;

        try {

            java.sql.PreparedStatement pstmtMpesaTokens = connectDB.prepareStatement("SELECT count(*) FROM mobile_payments WHERE checkout_request_id IS NOT NULL AND date = now()::date AND mobilepay_alert = false AND (SELECT current_user) = 'postgres' AND credit = 0");

            java.sql.ResultSet rsetMpesaTokens = pstmtMpesaTokens.executeQuery();

            while (rsetMpesaTokens.next()) {

                int tokens = 0;

                tokens = rsetMpesaTokens.getInt(1);

                if (tokens > 0) {

                    checkItems = true;

                }
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return checkItems;
    }

}
