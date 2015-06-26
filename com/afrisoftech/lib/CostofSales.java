/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <<cwaweru@systempartners.biz>>
 *
 * Dated January 31, 2015
 *
 */
public class CostofSales {

    static java.sql.Connection connectDB;

    public static double getWeightedCostOfSales(java.sql.Connection connDB, String stockItem, double qtySupplied, double newSupplyVal) {

        double newCostofSale = 0.00;
        int ctrl = 0;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT current_cost_of_sale, (SELECT sum(qty) FROM stock_balance_qty WHERE stock_balance_qty.item_code = cost_of_sale_average.item_code) as stock_balance FROM cost_of_sale_average WHERE item_code = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmt.setString(1, stockItem);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                double currentCOS = rset.getDouble(1);
                double stockBalance = rset.getDouble(2);
                if ((stockBalance + qtySupplied) > 0.00) {
                    newCostofSale = ((currentCOS * stockBalance) + (qtySupplied * newSupplyVal)) / (stockBalance + qtySupplied);
                    ctrl++;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        if (newCostofSale > 0.00) {
            return newCostofSale;
        } else {
            return newSupplyVal;
        }

    }

    static void setCostofSale(java.sql.Connection connDB, String stockItem, double qtySupplied, double newSupplyVal) {
        double newCostofSale = getWeightedCostOfSales(connDB, stockItem, qtySupplied, newSupplyVal);
        double currentCOS = 0.00;
        try {
            java.sql.PreparedStatement pstmtCOS = connectDB.prepareStatement("SELECT current_cost_of_sale, (SELECT sum(qty) FROM stock_balance_qty WHERE stock_balance_qty.item_code = cost_of_sale_average.item_code) as stock_balance FROM cost_of_sale_average WHERE item_code = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmtCOS.setString(1, stockItem);
            java.sql.ResultSet rsetCOS = pstmtCOS.executeQuery();
            while (rsetCOS.next()) {
                currentCOS = rsetCOS.getDouble(1);
            }

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO cost_of_sale_average("
                    + "            item_code, item_description, current_cost_of_sale, "
                    + "            last_used_cost_of_sale)"
                    + "    VALUES (?, ?, ?, ?)");
            pstmt.setObject(1, stockItem);
            pstmt.setObject(2, com.afrisoftech.lib.StockItemFactory.getItemDecription(connDB, stockItem));
            pstmt.setDouble(3, newCostofSale);
            pstmt.setDouble(4, currentCOS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
    }
}
