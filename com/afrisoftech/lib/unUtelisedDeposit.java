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
 * @author SPLgithinji
 */
public class unUtelisedDeposit {
    static double balance = 0.00;
     public static double getDeposit(java.sql.Connection connectDB, String patientNo){
         if(patientNo.contains("INT")){
            try {
                java.sql.Statement returnPatientNo = connectDB.createStatement();
                java.sql.ResultSet rsetPatientNo = returnPatientNo.executeQuery("select patient_no from hp_mortuary where annual_no = '"+patientNo+"'");
                while(rsetPatientNo.next()){
                  patientNo = rsetPatientNo.getString(1);
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        try {
            java.sql.Statement stmtdeposit = connectDB.createStatement();
            java.sql.ResultSet rsetdeposit = stmtdeposit.executeQuery("select round(sum(credit),2) from ac_ledger "
                    + "where patient_no = '" +patientNo+ "' AND transaction_type='Unutilized patient deposit'");
            while (rsetdeposit.next()) {
                balance=  rsetdeposit.getDouble(1);
                               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return balance;
         
     }
    public static double getBalanceDeposit(java.sql.Connection connectDB, String patientNo){
        
        if(patientNo.contains("INT")){
            try {
                java.sql.Statement returnPatientNo = connectDB.createStatement();
                java.sql.ResultSet rsetPatientNo = returnPatientNo.executeQuery("select patient_no from hp_mortuary where annual_no = '"+patientNo+"'");
                while(rsetPatientNo.next()){
                  patientNo = rsetPatientNo.getString(1);
                }
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        try {
            java.sql.Statement stmtdeposit = connectDB.createStatement();
            java.sql.ResultSet rsetdeposit = stmtdeposit.executeQuery("select round(sum(credit-debit),2) from ac_ledger "
                    + "where patient_no = '" +patientNo+ "' AND transaction_type='Unutilized patient deposit'");
            while (rsetdeposit.next()) {
                balance=  rsetdeposit.getDouble(1);
                               
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       return balance; 
    }
     public static double getBalanceAfterDepositUtilization(java.sql.Connection connectDB, String patientNo, String visitID){
       try {
           
            java.sql.Statement stmtdeposit = connectDB.createStatement();
            java.sql.ResultSet rsetdeposit = stmtdeposit.executeQuery("select round(sum(debit-credit),2) from hp_patient_card "
                    + "where patient_no = '" +patientNo+ "' AND visit_id = '"+visitID+"'");
            while (rsetdeposit.next()) {
                balance=  rsetdeposit.getDouble(1);
                               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return balance;
         
     }
     public static double getDmuBalanceAmount(java.sql.Connection connectDB, String patientNo, String accountNo){
           if(patientNo.contains("INT")){
            try {
                java.sql.Statement returnPatientNo = connectDB.createStatement();
                java.sql.ResultSet rsetPatientNo = returnPatientNo.executeQuery("select patient_no from hp_mortuary where annual_no = '"+patientNo+"'");
                while(rsetPatientNo.next()){
                  patientNo = rsetPatientNo.getString(1);
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
         
         try {
           
            java.sql.Statement stmtdeposit = connectDB.createStatement();
            java.sql.ResultSet rsetdeposit = stmtdeposit.executeQuery("SELECT sum(debit - credit) FROM ac_debtors WHERE  account_no  = '" +accountNo+ "' and admission_no = '" +patientNo+ "' ");
            while (rsetdeposit.next()) {
                balance=  rsetdeposit.getDouble(1);
                               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(unUtelisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return balance;
        
         
     }
     
}
