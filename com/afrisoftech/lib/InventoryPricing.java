/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *The purpose of this class is to help in determining the cost of sale of inventory items, their
 *packaging, units of issue per client and the pricing formaulae based on set markups.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//import org.openide.util.Exceptions;

/**
 * Created on May 1, 2016, 10:55 AM
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>; done on a cold Labour day in Eldoret, KENYA
 *
 */
public class InventoryPricing {

    public static double getSellingPrice(java.sql.Connection connectDB, String itemCode, String storeName) {

        double sellingPrice = 0;

        java.sql.PreparedStatement pstmt;

        String pricingModel = getPricingModel(connectDB, storeName);
        
        if (pricingModel.equalsIgnoreCase("DYNAMIC")) {
            try {
                pstmt = connectDB.prepareCall("SELECT (buying_price/packaging) FROM st_stock_item WHERE upper(item_code) = upper(?)");
                pstmt.setObject(1, itemCode);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    sellingPrice = Math.round(rset.getDouble(1) * getMarkup(connectDB, storeName));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                pstmt = connectDB.prepareCall("SELECT selling_price::numeric(15,0) FROM st_stock_prices WHERE product_id = ? AND department ilike ?");
                pstmt.setObject(1, itemCode);
                pstmt.setObject(2, storeName);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    sellingPrice = rset.getDouble(1);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        System.out.println("Computed Item selling price : ["+sellingPrice+"]");
        return sellingPrice;
    }

    public static double getMarkup(java.sql.Connection connectDB, String store) {
        
        double markup = 0;

        java.sql.PreparedStatement pstmt;
        try {
            pstmt = connectDB.prepareCall("SELECT mark_up FROM st_stores WHERE upper(store_name) = upper(?)");
            pstmt.setObject(1, store);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                markup = rset.getDouble(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return markup;
    }
    public static double costofSale(java.sql.Connection connectDB, String itemCode, String store) {
       
        double cos = 0;

        java.sql.PreparedStatement pstmt;
        try {
            pstmt = connectDB.prepareCall("SELECT (buying_price/packaging)::numeric(15,0) FROM st_stock_item WHERE item_code = ? AND upper(store_name) ilike upper(?)");
            pstmt.setObject(1, store);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                cos = rset.getDouble(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return cos;
    }
    public static String getPricingModel(java.sql.Connection connectDB, String store) {

        String pricingModel = "STATIC";

        java.sql.PreparedStatement pstmt;

        try {
            pstmt = connectDB.prepareCall("SELECT pricing_model FROM st_stores WHERE upper(store_name) = upper(?)");
            pstmt.setObject(1, store);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                pricingModel = rset.getString(1).toUpperCase();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return pricingModel;
    }
}
