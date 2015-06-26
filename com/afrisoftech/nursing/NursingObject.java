/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.nursing;

/**
 *
 * @author SAQ-LEVER
 */
public class NursingObject {
    
    public java.lang.Object NursingObject(java.lang.Object queryResultObject){
     switch(String.valueOf(queryResultObject).toLowerCase().trim()){
         case"-":
             queryResultObject=null;
             break;
               case"":
             queryResultObject=null;
             break;
             case"select":
                 queryResultObject=null;
             break;
              case"-select-":
                 queryResultObject=null;
             break;
          
     }
       
        return queryResultObject;
    }
     public java.lang.Object NursingNullObject(java.lang.Object queryResultObject){
         if(queryResultObject==null){
             queryResultObject="";
         }
         
     switch(String.valueOf(queryResultObject).toLowerCase().trim()){
         case"-":
             queryResultObject="";
             break;
               case"":
             queryResultObject="";
             break;
             case"select":
                 queryResultObject="";
             break;
              case"-select-":
                 queryResultObject="";
             break;
             
          
     }
       
        return queryResultObject;
    }
}
