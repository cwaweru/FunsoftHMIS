/*
 * TableModel.java
 *
 * Created on April 1, 2006, 2:32 PM
 */
package com.afrisoftech.dbadmin;

import com.afrisoftech.hospinventory.AnalyseRequisitionIntfr;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author  root
 */
public class TableModel {

    //    static java.sql.Connection connectDB = null;
    javax.swing.JTable dataViewTable = null;
    static java.util.Vector dataViewVector;
    static javax.swing.JTable searchTable;
    static java.util.Vector columnVector;
    static com.afrisoftech.lib.DBObject DBObject;
    static java.util.Vector childVector;
    java.awt.GridBagConstraints gridBagConstraints;
    static java.sql.Connection connectDB = null;
    static java.lang.String qString = null;
    static javax.swing.table.DefaultTableModel defaultTableModel = null;
    

    /** Creates a new instance of TableModel */
    /*    public TableModel(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

    connectDB = connDb;

    //        pConnDB = pconnDB;

    }
     */
    public static javax.swing.table.DefaultTableModel createTableVectors(java.sql.Connection connectDB, java.lang.String queryString) {

        DBObject = new com.afrisoftech.lib.DBObject();

        dataViewVector = new java.util.Vector(1, 1);

        javax.swing.table.DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel();

        try {

            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(queryString);

            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

            java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

            int columnCount = rsetMetaData.getColumnCount();
            //int rowCount = defaultTableModel.getRowCount();

            columnVector = new java.util.Vector(columnCount);

            for (int i = 0; i < columnCount; i++) {

                columnVector.add(i, rsetMetaData.getColumnName(i + 1).toUpperCase());

            }

            while (rsetVector.next()) {

                childVector = new java.util.Vector(columnCount);

                for (int j = 0; j < columnCount; j++) {

                    if (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.NUMERIC) {

                        childVector.addElement(new java.lang.Double(DBObject.getDBObject(rsetVector.getObject(j + 1), "0.00")));
                    
                    } else if(rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.INTEGER){
                        
                         childVector.addElement(new java.lang.Integer(DBObject.getDBObject(rsetVector.getObject(j + 1), "0")));

                        
                    } else if ((rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BOOLEAN) || (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BIT)) {

                        System.out.println("We have boolean field");

                        childVector.addElement(new java.lang.Boolean(DBObject.getDBObject(rsetVector.getBoolean(j + 1), "")));

                    } else {
                        childVector.addElement(DBObject.getDBObject(rsetVector.getString(j + 1), ""));

                    }
                }

                dataViewVector.add(childVector);

            }
            
            rsetVector.close();
            
            pstmtVector.close();

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

        }

        defaultTableModel.setDataVector(dataViewVector, columnVector);
        
        
       /* int c = defaultTableModel.getColumnCount();
        int a = defaultTableModel.getRowCount();
        if (defaultTableModel.isCellEditable(a, c)) {
           // dataViewTable.
        };*/


        /*
        searchTable = new javax.swing.JTable(defaultTableModel){

        public Class getColumnClass(int c) {

        return getValueAt(0, c).getClass();
        }


        };
         */
        // return searchTable;
       
        return defaultTableModel;
       

    }
    
//    //the asynchronous search by Charles Waweru
//    //Starts Here V V V---------------------------------------------------------------------------------------------
    public synchronized static javax.swing.table.DefaultTableModel createTableVectorsCaret(java.sql.Connection connDB, java.lang.String queryString) {

        defaultTableModel = new javax.swing.table.DefaultTableModel();

        TableModel.QueryThread queryThread = new TableModel.QueryThread();

        connectDB = connDB;

        qString = queryString;

        queryThread.start();

        return defaultTableModel;

    }
    
    
     static class QueryThread extends java.lang.Thread {

        @Override
        public synchronized void  run() {

            java.lang.System.out.println("Testing");

            DBObject = new com.afrisoftech.lib.DBObject();

            dataViewVector = new java.util.Vector(1, 1);

            try {

                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(qString);

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

                java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

                int columnCount = rsetMetaData.getColumnCount();

                columnVector = new java.util.Vector(columnCount);

                for (int i = 0; i < columnCount; i++) {

                    columnVector.add(i, rsetMetaData.getColumnName(i + 1).toUpperCase());

                }

                while (rsetVector.next()) {

                    childVector = new java.util.Vector(columnCount);

                    for (int j = 0; j < columnCount; j++) {

                        if (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.NUMERIC) {

                            childVector.addElement(new java.lang.Double(DBObject.getDBObject(rsetVector.getObject(j + 1), "0.00")));

                        } else if ((rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BOOLEAN) || (rsetVector.getMetaData().getColumnType(j + 1) == java.sql.Types.BIT)) {

                            System.out.println("We have boolean field");

                            childVector.addElement(new java.lang.Boolean(DBObject.getDBObject(rsetVector.getBoolean(j + 1), "")));

                        } else {
                            childVector.addElement(DBObject.getDBObject(rsetVector.getString(j + 1), ""));

                        }
                    }

                    dataViewVector.add(childVector);

                }

                rsetVector.close();

                pstmtVector.close();

            } catch (java.sql.SQLException sqlExec) {

                sqlExec.printStackTrace();

                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

            }

            defaultTableModel.setDataVector(dataViewVector, columnVector);
        }
    }
    
    //Ends Here-----------------------------------------------------------------------------
    public static javax.swing.table.DefaultTableModel createTableVectorsCollection(java.sql.Connection connectDB, String lcolID) {

        DBObject = new com.afrisoftech.lib.DBObject();

        dataViewVector = new java.util.Vector(1, 1);

        javax.swing.table.DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel();

        try {
            //step one, get and load headers
            String[] headers;
            String [] linenTypeID;
            
            String sql2="select typename, ltid from laundrylinentype order by typename asc";
            Statement stmt2 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            headers=new String[rs2.getRow()+2];
            linenTypeID=new String[rs2.getRow()+1];
            int columnCount=rs2.getRow()+1;
            headers[0]="Ward";
            linenTypeID[0]="";
            int counter1=1;
            if(rs2.getRow()>0){
                rs2.beforeFirst();
                
                while(rs2.next()){
                    headers[counter1]=rs2.getString("typename");
                    linenTypeID[counter1]=rs2.getString("ltid");
                    counter1++;
                    
                }
                headers[counter1]="Delivered By";
                columnCount=counter1+1;
            }
            
            //step 2, get and load wards
            
            //step 3, get and load values

//            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(queryString);
//
//            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
//
//            java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

            
            columnVector = new java.util.Vector(columnCount);

            for (int i = 0; i < columnCount; i++) {

                columnVector.add(i, headers[i]);

            }
            
            String sql="select ward_name, ward_code from public.hp_wards order by ward_name desc";
            Statement stmt = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            int counter=0;
            
            while (rs.next()) {

                childVector = new java.util.Vector(columnCount);
                
                childVector.addElement(DBObject.getDBObject(rs.getString("ward_name"), ""));
                
                String attendant="";
                for (int j = 1; j < columnCount-1; j++) {
                    String sql3="select count, linenattendantid from linencollectionitem where wardid like'"+rs.getString("ward_code")+"' AND typeid like '"+linenTypeID[j]+"' and lcolid like '"+lcolID+"'";
                    Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs3 = stmt3.executeQuery( sql3 );
                    rs3.last();
                    if(rs3.getRow()>0){
                        childVector.addElement(DBObject.getDBObject(rs3.getString("count"), ""));
                        
                        attendant=rs3.getString("linenattendantid");
                    }
                    else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                    }
                    
                    
                    
                }
                if(!attendant.equals("")){
                    String sql3="select CONCAT(first_name,' ',middle_name) AS \"Name\" from master_file where employee_no like '"+attendant+"'";
                    Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs3 = stmt3.executeQuery( sql3 );
                    rs3.last();
                    if(rs3.getRow()>0){
                       childVector.addElement(DBObject.getDBObject(rs3.getString("Name"), ""));
                        
                    }
                    else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                        
                    }
                }
                else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                        
                    }
                
                dataViewVector.add(childVector);

            }
            

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

        }

        defaultTableModel.setDataVector(dataViewVector, columnVector);
        
        
       /* int c = defaultTableModel.getColumnCount();
        int a = defaultTableModel.getRowCount();
        if (defaultTableModel.isCellEditable(a, c)) {
           // dataViewTable.
        };*/


        /*
        searchTable = new javax.swing.JTable(defaultTableModel){

        public Class getColumnClass(int c) {

        return getValueAt(0, c).getClass();
        }


        };
         */
        // return searchTable;
        return defaultTableModel;

    }
    public static javax.swing.table.DefaultTableModel createTableVectorsDistribution(java.sql.Connection connectDB, String lcolID) {

        DBObject = new com.afrisoftech.lib.DBObject();

        dataViewVector = new java.util.Vector(1, 1);

        javax.swing.table.DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel();

        try {
            //step one, get and load headers
            String[] headers;
            String [] linenTypeID;
            
            String sql2="select typename, ltid from laundrylinentype order by typename asc";
            Statement stmt2 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            headers=new String[rs2.getRow()+2];
            linenTypeID=new String[rs2.getRow()+1];
            int columnCount=rs2.getRow()+1;
            headers[0]="Ward";
            linenTypeID[0]="";
            int counter1=1;
            if(rs2.getRow()>0){
                rs2.beforeFirst();
                
                while(rs2.next()){
                    headers[counter1]=rs2.getString("typename");
                    linenTypeID[counter1]=rs2.getString("ltid");
                    counter1++;
                    
                }
                headers[counter1]="Delivered By";
                columnCount=counter1+1;
            }
            
            //step 2, get and load wards
            
            //step 3, get and load values

//            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(queryString);
//
//            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
//
//            java.sql.ResultSetMetaData rsetMetaData = rsetVector.getMetaData();

            
            columnVector = new java.util.Vector(columnCount);

            for (int i = 0; i < columnCount; i++) {

                columnVector.add(i, headers[i]);

            }
            
            String sql="select ward_name, ward_code from public.hp_wards order by ward_name desc";
            Statement stmt = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            int counter=0;
            
            while (rs.next()) {

                childVector = new java.util.Vector(columnCount);
                
                childVector.addElement(DBObject.getDBObject(rs.getString("ward_name"), ""));
                
                String attendant="";
                for (int j = 1; j < columnCount-1; j++) {
                    String sql3="select count, linenattendantid from linendistributionitem where wardid like'"+rs.getString("ward_code")+"' AND typeid like '"+linenTypeID[j]+"' and lcolid like '"+lcolID+"'";
                    Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs3 = stmt3.executeQuery( sql3 );
                    rs3.last();
                    if(rs3.getRow()>0){
                        childVector.addElement(DBObject.getDBObject(rs3.getString("count"), ""));
                        
                        attendant=rs3.getString("linenattendantid");
                    }
                    else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                        
                    }
                    
                    
                    
                }
                if(!attendant.equals("")){
                    String sql3="select CONCAT(first_name,' ',middle_name) AS \"Name\" from master_file where employee_no like '"+attendant+"'";
                    Statement stmt3 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs3 = stmt3.executeQuery( sql3 );
                    rs3.last();
                    if(rs3.getRow()>0){
                       childVector.addElement(DBObject.getDBObject(rs3.getString("Name"), ""));
                        
                    }
                    else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                        
                    }
                }
                else{
                        childVector.addElement(DBObject.getDBObject("", ""));
                        
                        
                    }
                
                dataViewVector.add(childVector);

            }
            

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());

        }

        defaultTableModel.setDataVector(dataViewVector, columnVector);
        
        
       /* int c = defaultTableModel.getColumnCount();
        int a = defaultTableModel.getRowCount();
        if (defaultTableModel.isCellEditable(a, c)) {
           // dataViewTable.
        };*/


        /*
        searchTable = new javax.swing.JTable(defaultTableModel){

        public Class getColumnClass(int c) {

        return getValueAt(0, c).getClass();
        }


        };
         */
        // return searchTable;
        return defaultTableModel;

    }
    
    public static void TableGroupedBooleanColumn(javax.swing.JTable Table, Integer boolean_column){
    
    for(int i=0;i<Table.getRowCount();i++){ 
        
        if(i!=Table.getSelectedRow()){
  
         Table.setValueAt(false, i, boolean_column);
        
        }
               
      }
 
    }
}
