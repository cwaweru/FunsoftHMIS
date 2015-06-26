/*
 * GetInstitutionInfo.java
 *
 * Created on May 20, 2014, 6:52 AM
 */

package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  saleem 
 */
public class GetInstitutionInfo {
    //OceanTheme
    /** Creates a new instance of DBObject */
    public GetInstitutionInfo() {
    }
    //GovBillPaymentsIntfr
    public static java.lang.String HeaderName(java.sql.Connection connectDB){
       String Name = null;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT header_name from pb_header LIMIT 1");
            java.sql.ResultSet rsetCode = pst.executeQuery();
             while (rsetCode.next()){
             
             Name = rsetCode.getObject(1).toString();
           
             
             }       
        } 
   
        catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Name;
    
     }
  
 }
