/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hmis.automatedoperations;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 *
 */
public class DeployItemListing implements Runnable {

    static java.sql.Connection connectDB = null;
    javax.swing.table.DefaultTableModel drugsTableModel = null;
    javax.swing.JTable jTable1 = new javax.swing.JTable();
    com.afrisoftech.lib.DBObject dbObject;
    com.afrisoftech.lib.DatePicker datePicker1 = new com.afrisoftech.lib.DatePicker();
    java.lang.Thread getListThread;
    boolean getList;
    private String mainCategory;

    public DeployItemListing(java.sql.Connection connDB) {

        connectDB = connDB;

    }
    private java.lang.String[] getPharmacyList() {



        java.util.Vector pharmaciesVector = new java.util.Vector(1, 1);

        try {

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT store_name FROM st_stores WHERE patient_store = true and store_name ilike '%pharmacy%'");

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                pharmaciesVector.addElement(rset.getString(1));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DeployItemListing.class.getName()).log(Level.SEVERE, null, ex);

        }

        java.lang.String pharmacyList[] = new String[pharmaciesVector.capacity()];
        for (int i = 0; i < pharmacyList.length; i++) {
            pharmacyList[i] = pharmaciesVector.elementAt(i).toString();
        }
        return pharmacyList;

    }
    
    private java.lang.String getPharmacyGLCode(String pharmacyName) {


       String pharmacyGL = null;

        try {

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT sales_code FROM st_stores WHERE store_name = '"+pharmacyName+"'");

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                pharmacyGL = rset.getString(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            
            Logger.getLogger(DeployItemListing.class.getName()).log(Level.SEVERE, null, ex);

        }

        return pharmacyGL;

    }

    private java.lang.String[] getDrugCategoriesList() {

        java.util.Vector categoriesVector = new java.util.Vector(1, 1);

        try {

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT description FROM st_main_category");

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                categoriesVector.addElement(rset.getString(1));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DeployItemListing.class.getName()).log(Level.SEVERE, null, ex);

        }

        java.lang.String drugCategoriesList[] = new String[categoriesVector.capacity()];
        for (int i = 0; i < drugCategoriesList.length; i++) {
            drugCategoriesList[i] = categoriesVector.elementAt(i).toString();
        }

        return drugCategoriesList;

    }

    public javax.swing.table.DefaultTableModel loadUpdateItems() {

        String pharmacyGLCode = null;

        javax.swing.table.DefaultTableModel newDrugsTableModel = null;

        java.util.Vector drugsRowVector = new java.util.Vector(1, 1);
        java.util.Vector drugsHeaderRowVector = new java.util.Vector(1, 1);

        drugsHeaderRowVector.addElement("Item Code");
        drugsHeaderRowVector.addElement("Description");
        drugsHeaderRowVector.addElement("Stregnth");
        drugsHeaderRowVector.addElement("Units");
        drugsHeaderRowVector.addElement("Dose");
        drugsHeaderRowVector.addElement("Buying Price");
        drugsHeaderRowVector.addElement("Selling");
        drugsHeaderRowVector.addElement("Pharmacy");
        drugsHeaderRowVector.addElement("Category");
        drugsHeaderRowVector.addElement("GL Code");

        java.lang.String pharmaciesArray[] = this.getPharmacyList();

        java.lang.String categoriesList[] = this.getDrugCategoriesList();

        for (int i = 0; i < pharmaciesArray.length; i++) {

            for (int j = 0; j < categoriesList.length; j++) {

                pharmacyGLCode = this.getPharmacyGLCode(pharmaciesArray[i]);//com.afrisoftech.lib.GLCodesFactory.getGlCode(connectDB, pharmaciesArray[i]);

                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement(
                            "select DISTINCT item_code,'' as description, '' as strength,"
                            + "'' as units,0::numeric AS Dose_Qty, 0::numeric as buying_price, 0::numeric as selling FROM st_stock_item "
                            + "WHERE (department ILIKE '%drug%' or department ILIKE '%pharm%' OR department ILIKE '%bulk%') and sub_cat_code ilike '" + categoriesList[j] + "' "
                            + "EXCEPT SELECT product_id,'' as product,'' as strength,'' as units,0::numeric AS Dose_Qty,0::numeric,0::numeric as selling "
                            + "FROM st_stock_prices WHERE department ilike '"+pharmaciesArray[i]+"' AND category ilike '" + categoriesList[j] + "'");

                    java.sql.ResultSet rsetTable1 = pstmt.executeQuery();
                    while (rsetTable1.next()) {
                        System.out.println("populating table done ....");

                        java.util.Vector columnDataVector = new java.util.Vector(1, 1);
                        columnDataVector.addElement(rsetTable1.getObject(1));
                        columnDataVector.addElement(rsetTable1.getObject(2));
                        columnDataVector.addElement(rsetTable1.getObject(3));
                        columnDataVector.addElement(rsetTable1.getObject(4));
                        columnDataVector.addElement(rsetTable1.getObject(5));
                        columnDataVector.addElement(rsetTable1.getObject(6));
                        columnDataVector.addElement(rsetTable1.getObject(7));
                        columnDataVector.addElement(pharmaciesArray[i]);
                        columnDataVector.addElement(categoriesList[j]);
                        columnDataVector.addElement(pharmacyGLCode);
                        drugsRowVector.addElement(columnDataVector);
                    }
                    System.out.println("Final : Done populating items ...");
                    newDrugsTableModel = new javax.swing.table.DefaultTableModel(drugsRowVector, drugsHeaderRowVector);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(DeployItemListing.class.getName()).log(Level.SEVERE, null, ex);
                }

                jTable1.setModel(newDrugsTableModel);

                getList = true;

                getListThread = new java.lang.Thread(this, "get_list_Thread");

                getListThread.start();

            }

        }
        return newDrugsTableModel;
    }

    @Override
    public void run() {

        while (getList) {

            runGetListThread();


            try {

                Thread.currentThread().sleep(100);

            } catch (java.lang.InterruptedException IntExec) {

                IntExec.printStackTrace();
                //   javax.swing.JOptionPane.showMessageDialog(this, IntExec.getMessage(), "Interruption Error!", javax.swing.JOptionPane.ERROR_MESSAGE);

            }

            getList = false;

        }

    }

    public void runGetListThread() {
        //   this.jButton1.setEnabled(true);
        double markup = 0.00;
        double buying = 0.00;

        int j = 0;
        //int i = 0;
        int n = 0;

        try {
            java.sql.Statement pstmt1 = connectDB.createStatement();
            java.sql.Statement pstmtCategory = connectDB.createStatement();
            markup = 1;

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                java.sql.ResultSet rsetTableCategory = pstmt1.executeQuery("select sub_cat_code FROM st_stock_item WHERE item_code ilike '" + jTable1.getValueAt(i, 0).toString() + "'");
                while (rsetTableCategory.next()) {

                    mainCategory = rsetTableCategory.getString(1);

                }
                //   java.sql.Statement pstmt1 = connectDB.createStatement();
                java.sql.ResultSet rsetTable11 = pstmt1.executeQuery("select markups FROM st_main_category WHERE description ilike '" + mainCategory + "'");
                while (rsetTable11.next()) {
                    if (rsetTable11.getDouble(1) > 0) {
                        markup = rsetTable11.getDouble(1);
                    } else {
                        markup = 1;
                    }
                }
                java.sql.Statement pstmt = connectDB.createStatement();

                java.sql.ResultSet rsetTable1 = pstmt.executeQuery("select round((buying_price/packaging)::numeric),ROUND((buying_price/packaging)::numeric*'" + markup + "') as selling,description,units,strength FROM st_stock_item WHERE item_code ilike '" + jTable1.getValueAt(i, 0) + "'");
                while (rsetTable1.next()) {
                    jTable1.setValueAt(rsetTable1.getObject(3), i, 1);
                    jTable1.setValueAt(rsetTable1.getObject(5), i, 2);
                    jTable1.setValueAt(rsetTable1.getObject(4), i, 3);
                    jTable1.setValueAt(rsetTable1.getObject(1), i, 5);
                    jTable1.setValueAt(rsetTable1.getObject(2), i, 6);

                    //jTable1.setValueAt(rsetTable1.getObject(2), i, 4);
                }
            }
        } catch (java.sql.SQLException sqlex) {
            sqlex.printStackTrace();
            System.out.println(sqlex.getMessage());
        }

    }
}
