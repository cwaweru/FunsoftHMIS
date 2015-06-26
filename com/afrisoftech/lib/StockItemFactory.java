/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <<cwaweru@systempartners.biz>>
 */
public class StockItemFactory {

    private static String itemCode = null;
    private static String itemDescription = null;
    private static String itemStregnth = null;

    /**
     * @param connDB
     * @param itemdescription
     * @return the itemStregnth
     */
    public static String getItemStregnth(java.sql.Connection connDB, String itemdescription) {

        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT strength FROM st_stock_item WHERE description ||' '||strength ilike ?");
            pstmt.setString(1, itemdescription);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                itemStregnth = rset.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StockItemFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemStregnth;
    }

    /**
     * @param aItemStregnth the itemStregnth to set
     */
    public static void setItemStregnth(String aItemStregnth) {


        itemStregnth = aItemStregnth;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param connDB
     * @param itemcode
     * @return the itemDecription
     */
    public static String getItemDecription(java.sql.Connection connDB, String itemcode) {

        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT description FROM st_stock_item WHERE item_code ilike ?");
            pstmt.setString(1, itemcode);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                itemDescription = rset.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StockItemFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemDescription;

    }

    /**
     * @param itemDescription
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @param connDB
     * @param itemdescription
     * @return the itemCode
     */
    public static String getItemCode(java.sql.Connection connDB, String itemdescription) {
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT item_code FROM st_stock_item WHERE description ||' '||strength ilike ?");
            pstmt.setString(1, itemdescription);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                itemCode = rset.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(StockItemFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemCode;
    }

    // Examines whether the store is a store for consumables and not for sale to clients.
    /**
     * @param connDB : Database connection.
     *
     * @param storeName
     * @return boolean isConsumable : Whether store is a consumable items store,
     * true if it is a consumable store.
     *
     */
    public static boolean isConsumable(java.sql.Connection connDB, String storeName) {
        boolean isConsumable = false;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT patient_store FROM st_stores WHERE upper(store_name) = upper(?)");
            pstmt.setString(1, storeName);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                isConsumable = !rset.getBoolean(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return isConsumable;
    }

    public static boolean isStockItem(java.sql.Connection connDB, String itemName) {
        boolean isConsumable = false;
        int i = 0;
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT count(description) FROM st_stock_item WHERE upper(description) = upper(?)");
            pstmt.setString(1, itemName);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                i = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        if (i > 0) {
            isConsumable = true;
        } else {
            isConsumable = false;
        }
        return isConsumable;
    }
}
