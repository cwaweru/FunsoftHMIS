/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

 
/**
 *
 * @author MUGISPL
 */
public class CheckTaxStatus {
    
       public static ArrayList calculateTax(java.sql.Connection connectDB,double rate,double Total){
        double amount=Total,taxDeduction=0.0;
       
         
        ArrayList taxReturns = new ArrayList();
        com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
        taxDeduction=(rate*Total)/100;
          amount=(Total-taxDeduction);
           try {
            java.sql.PreparedStatement pstmtPrepay = connectDB.prepareStatement(""
                    + "SELECT  round("+amount+",2),round("+taxDeduction+",2);");
            
            java.sql.ResultSet rsetTax = pstmtPrepay.executeQuery();
            
            if (rsetTax.next()) {
               taxReturns.add(0,rsetTax.getDouble(2));
               taxReturns.add(1,rsetTax.getDouble(1));
            }
            System.out.println("the amount are"+taxReturns);

        } catch (SQLException ex) {
            Logger.getLogger(CheckTaxStatus.class.getName()).log(Level.SEVERE, null, ex);
          
        }
            
        return taxReturns;
    }
    
    public static   ArrayList  returnTax(java.sql.Connection connectDB,String item ,String glcode){
           ArrayList taxDetails = new ArrayList();
        com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
         try {
            java.sql.PreparedStatement pstmtPrepay = connectDB.prepareStatement(""
                    + "SELECT   input_tax, output_tax, tax_gl_code   FROM taxe_rate"
                    + " where  gl_code='"+glcode.trim()+"' and "
                    + "item ilike '%"+item.trim()+"%' and  tax_set=true;");
            
            java.sql.ResultSet rsetTax = pstmtPrepay.executeQuery();
            
            if (rsetTax.next()) {
                 taxDetails.add(0,dbObject.getDBObject(rsetTax.getObject(1),"0.0"));
                 taxDetails.add(1, dbObject.getDBObject(rsetTax.getObject(2),"0.0"));
                  taxDetails.add(2,dbObject.getDBObject(rsetTax.getObject(3),""));
            }
            System.out.println("the tax elelmts are"+taxDetails);

        } catch (SQLException ex) {
            Logger.getLogger(CheckTaxStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return taxDetails;
    }
    
    public static   ArrayList  taxStatus(java.sql.Connection connectDB,String item,String glcode){
       
        ArrayList taxDetails = new ArrayList();
        com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
         try {
            java.sql.PreparedStatement pstmtPrepay = connectDB.prepareStatement(""
                    + "SELECT tax_set,  input_tax, output_tax, tax_gl_code   FROM taxe_rate"
                    + " where  gl_code='"+glcode.trim()+"' and "
                    + "item ilike '%"+item.trim()+"%' and   tax_set=true;");
            
            java.sql.ResultSet rsetTax = pstmtPrepay.executeQuery();
            
            if (rsetTax.next()) {
               taxDetails.add(0,dbObject.getDBObject(rsetTax.getObject(1),"false"));
                taxDetails.add(1,dbObject.getDBObject(rsetTax.getObject(2),"0.0"));
                 taxDetails.add(2, dbObject.getDBObject(rsetTax.getObject(3),"0.0"));
                  taxDetails.add(3,dbObject.getDBObject(rsetTax.getObject(4),"-"));
            }
            System.out.println("the tax elelmts are"+taxDetails);

        } catch (SQLException ex) {
            Logger.getLogger(CheckTaxStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return taxDetails;
    }
    
}
