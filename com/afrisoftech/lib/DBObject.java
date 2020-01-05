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
    
    public static java.lang.String addColumnCondition(String column, String dataType){
    
        if(dataType.equalsIgnoreCase("Numeric")){
            return column + " ~ '^([0-9]+[.]?[0-9]*|[.][0-9]+)$' " ;
        }else if(dataType.equalsIgnoreCase("Text")){
            return column + " !~ '^([0-9]+[.]?[0-9]*|[.][0-9]+)$' " ;
        }else{
           return "(" + column + " ~ '^([0-9]+[.]?[0-9]*|[.][0-9]+)$' OR " + column + " !~ '^([0-9]+[.]?[0-9]*|[.][0-9]+)$' )" ; 
        }
        
    }
    
}
