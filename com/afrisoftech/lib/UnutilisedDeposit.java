/*
 * DBObject.java
 *
 * Created on January 8, 2004, 8:36 PM
 */

package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  saleem :::Sunday May 18 2014
 */
public class UnutilisedDeposit {
    
  //  FinancialYear
    //OceanTheme
    /** Creates a new instance of DBObject */
    com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
    public UnutilisedDeposit() {
    }
    //GovBillPaymentsIntfr
    public static java.lang.Double getDepositBalance(java.lang.String patient_no, java.sql.Connection connectDB){
       Double depositBalance = 0.00;
       
        try {//
            java.sql.PreparedStatement pst = connectDB.prepareStatement("(select sum(credit-debit) from ac_ledger where patient_no ='"+patient_no+"' and description ilike 'Un-utili%deposit%' ");
            java.sql.ResultSet rsetCode = pst.executeQuery();
             while (rsetCode.next()){
             
             depositBalance = rsetCode.getDouble(1);
             
             
             }       
        } 
        
        
        
        catch (SQLException ex) {
            Logger.getLogger(UnutilisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return depositBalance;
    
    }
    
    public void utiliseOPatientDeposit(java.lang.String patient_no, java.sql.Connection connectDB){
        try {
                                    java.sql.Statement stm1211x = connectDB.createStatement();
                                    java.sql.ResultSet rse1211x = stm1211x.executeQuery("SELECT distinct description, activity_code FROM ac_ledger WHERE patient_no = '" + patient_no + "' and transaction_type = 'Unutilized patient deposit'");
                                               String rnamexs=null,rcodexs = null;
                                    while (rse1211x.next()) {
                                       rnamexs = rse1211x.getObject(1).toString();
                                       rcodexs = rse1211x.getObject(2).toString();
                                    }   
            
//             /*
//              Outpatient bill payment by deposit
//              */
//                                    //debit deposit
//            java.sql.PreparedStatement pstmt1q1 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
//                                    pstmt1q1.setObject(1, rcodexs);
//                                    pstmt1q1.setString(2, rnamexs);
//                                    pstmt1q1.setString(3, schemeInvoicingTable.getModel().getValueAt(i, 0).toString());
//                                    pstmt1q1.setString(4, schemeInvoicingTable.getModel().getValueAt(i, 1).toString());
//                                    pstmt1q1.setObject(6, visitIDTxt.getText());
//                                    pstmt1q1.setString(5, "Patient deposit");
//                                    pstmt1q1.setString(7, visitid);
//                                    pstmt1q1.setString(8, "SCHEME");
//                                    pstmt1q1.setString(9, "");
//                                    pstmt1q1.setString(10, payModeTxt.getText().toString());
//                                    pstmt1q1.setString(11, "");
//                                    pstmt1q1.setString(12, receiptNo);
//                                    pstmt1q1.setString(13, "");
//                                    pstmt1q1.setString(14, "Receipt");
//                                    pstmt1q1.setString(15, "Unutilized patient deposit");
//                                    pstmt1q1.setDouble(17, 0.00);
//                                    pstmt1q1.setDouble(16, balAfterSchemeUtilization);
//                                    pstmt1q1.setDate(18, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));
//                                    pstmt1q1.setString(19, transNo);
//                                    pstmt1q1.setBoolean(20, false);
//                                    pstmt1q1.setBoolean(21, true);
//                                    pstmt1q1.setBoolean(22, false);
//                                    pstmt1q1.setString(23, com.afrisoftech.lib.UserName.getLoginName(connectDB).toLowerCase());
//                                    pstmt1q1.setString(24, "");//cash point
//                                    pstmt1q1.setString(25, "");//shift
//                                    pstmt1q1.setTimestamp(26, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));
//                                    pstmt1q1.executeUpdate();
//            
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UnutilisedDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    
    
    }
    
   
}
