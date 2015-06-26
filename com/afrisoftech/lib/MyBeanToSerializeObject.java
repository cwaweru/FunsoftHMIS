/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author saleem
 */
import java.lang.String;
import java.util.Vector;
public class MyBeanToSerializeObject {
    private String firstName;
    private String lastName;
    private int age;
//    private java.util.Vector data_ = new java.util.Vector(1, 1);
//    private java.util.Vector columnNames_ = new java.util.Vector();
    private Vector data_ ;
    private Vector columnNames_ ;

//    public  MyBeanToSerializeObject(Vector data, Vector columnNames) {
//       
//        data_=data;
//        columnNames_=columnNames;
//    }
 
   public Vector getData() {
        return data_;
    }
 
    public void setData(Vector data_) {
        this.data_ =data_; 
    }  
    
  
   public Vector getColumns() {
       
        return columnNames_;
    }
 
    public void setColumns(Vector columns_) {
        this.columnNames_ =columns_; 
    }  
 
    
    
}
