/*
 * DBObject.java
 *
 * Created on January 8, 2004, 8:36 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class DBObject {
    
    /** Creates a new instance of DBObject */
    public DBObject() {
    }
    
    public java.lang.String getDBObject(java.lang.Object queryResultObject, java.lang.String defaultString){
    
        if (queryResultObject == null) {
            
            return defaultString;
            
        }else
             {
            
            return queryResultObject.toString();
            
        }
        
    }
    
}
