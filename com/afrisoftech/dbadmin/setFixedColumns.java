/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.dbadmin;

/**
 * 10 May 2014 3.42 p.m
 * @author saleem
 */
public class setFixedColumns {
    
    public setFixedColumns(){
    }
    
    public static void DisallowReordering(javax.swing.JTable tableName){
  
    tableName.getTableHeader().setReorderingAllowed(false);
  
    }
    
    public static void DisallowResizing(javax.swing.JTable tableName){
    
        tableName.getTableHeader().setResizingAllowed(false);
    
    }
}

