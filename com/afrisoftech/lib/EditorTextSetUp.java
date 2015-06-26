/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author SaQlever
 */
public class EditorTextSetUp {

    static String editorText = null;
    static java.util.ArrayList columnVector;
    static com.afrisoftech.lib.DBObject dbObject;
    static java.util.ArrayList dataViewVector;
    static java.util.ArrayList childVector;
    static java.util.ArrayList sizeVector;
    static java.util.HashMap <String,java.util.ArrayList> data = new HashMap();
    static java.util.ArrayList QueryString = new java.util.ArrayList();

    public static java.util.HashMap editorGetText(java.sql.Connection connectDB, java.lang.String queryString) {
        dbObject = new com.afrisoftech.lib.DBObject();
 

        dataViewVector = new java.util.ArrayList();

        javax.swing.table.DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel();

        try {

            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(queryString);

            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

            java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

            int columnCount = rsetMetaData.getColumnCount();
            //int rowCount = defaultTableModel.getRowCount();

            columnVector = new java.util.ArrayList();

            for (int i = 0; i < columnCount; i++) {

                columnVector.add(i, rsetMetaData.getColumnName(i + 1).toUpperCase());

            }

            while (rsetVector.next()) {

                childVector  = new java.util.ArrayList(columnCount);

                for (int j = 0; j < columnCount; j++) {

                    if (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.NUMERIC) {

                        childVector.add(new java.lang.Double(dbObject.getDBObject(rsetVector.getObject(j + 1), "0.00")));

                    } else if ((rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BOOLEAN) || (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BIT)) {

                        System.out.println("We have boolean field");

                        childVector.add(new java.lang.Boolean(dbObject.getDBObject(rsetVector.getBoolean(j + 1), "")));

                    } else {
                        childVector.add(dbObject.getDBObject(rsetVector.getString(j + 1), ""));

                    }
                }

                dataViewVector.add(childVector);

            }

            rsetVector.close();

            pstmtVector.close();

            for (int i = 0; i < columnVector.size(); i++) {
                System.out.print(" " + columnVector.get(i) + " :");

            }
            System.out.println("end of columns");
            sizeVector =new java.util.ArrayList();
            boolean print = false;
            for (int j = 0; j < dataViewVector.size(); j++) {
                childVector =  (ArrayList) dataViewVector.get(j);

                for (int i = 0; i < childVector.size(); i++) {
                    if (print == false) {
                        if (j == 0) {
                            if ( String.valueOf(childVector.get(i)).length()<String.valueOf(columnVector.get(i)).length() ) {
                                sizeVector.add(String.valueOf(columnVector.get(i)).length());
                            } else if (String.valueOf(childVector.get(i)).length() == String.valueOf(columnVector.get(i)).length()) {
                                sizeVector.add(String.valueOf(childVector.get(i)).length());
                            }else if (String.valueOf(childVector.get(i)).length() > String.valueOf(columnVector.get(i)).length()) {
                                    if(String.valueOf(childVector.get(i)).length()>60){
                                        sizeVector.add(60);
                                    }else
                                         if(String.valueOf(childVector.get(i)).length()<=60){
                                        sizeVector.add(String.valueOf(childVector.get(i)).length());
                                    }
                                   
                                }

                        } else if (j > 0) {
                            print = true;
                            j = 0;
                            for (int k = 0; k < sizeVector.size(); k++) {
                                System.out.print(" " + sizeVector.get(k) + "  ");
                            }
                            System.out.println("");
                        }

                    } else if (print == true) {
                        System.out.print(" " + childVector.get(i).toString() + "  ");
                    }
                }
                System.out.println("");
            }
            /**
             * key 1 header key2 no of columns/columns key 3 column span key 4
             * data
             *
             */
            // data.put("header", "");
            data.put("columns", columnVector);
            data.put("columnspan", sizeVector);
            data.put("data", dataViewVector);

        } catch (Exception sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

        }
        return data;
    }
////THE SECOND METHOD
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
////////////////////////////////////////////////////    
    
    public static java.util.HashMap editorGetText(java.sql.Connection connectDB, java.util.ArrayList queryString) {
        dbObject = new com.afrisoftech.lib.DBObject();

    
        QueryString = queryString;
        dataViewVector = new java.util.ArrayList();

     //   javax.swing.table.DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel();

        try {
         //   data.put("multi", null);
            for (int v = 0; v < QueryString.size(); v++) {
                System.out.println("the string is "+QueryString.get(v));
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(String.valueOf(QueryString.get(v)));

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

                java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

                int columnCount = rsetMetaData.getColumnCount();
                //int rowCount = defaultTableModel.getRowCount();

                columnVector = new java.util.ArrayList(columnCount);

                for (int i = 0; i < columnCount; i++) {

                    columnVector.add(i, rsetMetaData.getColumnName(i + 1).toUpperCase());

                }

                while (rsetVector.next()) {

                    childVector =new java.util.ArrayList(columnCount);

                    for (int j = 0; j < columnCount; j++) {

                        if (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.NUMERIC) {

                            childVector.add(new java.lang.Double(dbObject.getDBObject(rsetVector.getObject(j + 1), "0.00")));

                        } else if ((rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BOOLEAN) || (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BIT)) {

                            System.out.println("We have boolean field");

                            childVector.add(new java.lang.Boolean(dbObject.getDBObject(rsetVector.getBoolean(j + 1), "")));

                        } else {
                            childVector.add(dbObject.getDBObject(rsetVector.getString(j + 1), ""));

                        }
                    }

                    dataViewVector.add(childVector);

                }

                rsetVector.close();

                pstmtVector.close();

                
                data.put("columns:-" + v, columnVector);
               
                data.put("data:-" + v, dataViewVector);
                
                for (int i = 0; i < columnVector.size(); i++) {
                    System.out.print(" " + columnVector.get(i) + " :");

                }
            
                sizeVector = new java.util.ArrayList(columnCount);
                boolean print = false;
                 java.util.ArrayList comparator= new java.util.ArrayList<>();
                     comparator.addAll((Collection) dataViewVector.get(0));
                for (int j = 0; j < columnVector.size(); j++) {
                    
                    System.out.println("");
                    if(String.valueOf(columnVector.get(j)).length()>=String.valueOf(comparator.get(j)).length()){
                        sizeVector.add(String.valueOf(columnVector.get(j)).length());
                    }
                    else
                        if(String.valueOf(columnVector.get(j)).length()<String.valueOf(comparator.get(j)).length()){
                         sizeVector.add(String.valueOf(comparator.get(j)).length());   
                        }
                }
                 data.put("columnspan:-" + v, sizeVector);
                    System.out.println("\n\n\n\n\n\n\n\n\n");
                 for (int j = 0; j < sizeVector.size(); j++) {
                     System.out.print(" "+sizeVector.get(j));
                 }
                  System.out.println("\ncolumn span");
                   for (int j = 0; j < columnVector.size(); j++) {
                     System.out.print(" "+columnVector.get(j));
                 }
                  System.out.println("\ncolumns");
                     for (int j = 0; j < dataViewVector.size(); j++) {
                         comparator.clear();
                            comparator.addAll((Collection) dataViewVector.get(j));                           
                          for (int k = 0; k < comparator.size(); k++) {
                     System.out.print("      "+comparator.get(k));
                          }
                 }
                      System.out.println("\n\n\n\n\n\n\n\n\n");
                /**
                 * key 1 header key2 no of columns/columns key 3 column span key
                 * 4 data
                 *
                 */

                columnVector.clear();
                sizeVector.clear();
                dataViewVector.clear();
                childVector.clear();
            }
               for (Map.Entry<String, ArrayList> entry : data.entrySet()) {
                            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                        }
                                     System.out.println("\n\n\n\n\n\n\n\n\n");

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

        }
        return data;
    }
}
