/*
 * DBObject.java
 *
 * Created on January 8, 2004, 8:36 PM
 */

package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;






/**
 *
 * @author  root
 */
public class DBAllObjects {
    //OceanTheme
    /** Creates a new instance of DBObject */
    public DBAllObjects() {
    }
    //GovBillPaymentsIntfr
    public static Object getDBObject(java.lang.Object queryResultObject, java.lang.Object defaultobject){
       // InpatientDepositIntfr;
        if (queryResultObject == null) {
            
            return defaultobject;
            //HospitalMain
        } else {
            
            return queryResultObject;
            
        }
        
        
        
        
    }
    
    public static Object getDBObject(java.lang.Object queryResultObject, java.lang.String defaultstring){
       // InpatientDepositIntfr;
        if (queryResultObject == null) {
            
            return defaultstring;
            //HospitalMain
        } else {
            
            return queryResultObject;
            
        }
        
        
        
        
    }
    
    
     
    
}
