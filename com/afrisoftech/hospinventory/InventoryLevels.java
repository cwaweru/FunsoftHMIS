/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.StockFormulae;
import java.sql.SQLException;
//

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class InventoryLevels {

    private static int leadOrderDays = 0;

    private static int bufferStockDays = 0;

    private static int averagingDays = 0;

    private static double averageConsumption = 0.0;

    private static int storeAverageConsumption = 0;

    private static int expiryDays = 0;

    private static int stockLevel = 0;

    private static int storeStockLevel = 0;

    private static int consumptionNumbers = 0;

    private static int storeConsumptionNumbers = 0;

    private static long reOrderQuantity = 0;

    private static long storeReorderQuantity = 0;

    private static long optimumStockLevel = 0;

    private static long storeOptimumStockLevel = 0;

    /**
     * @return the leadOrderDays
     */
    public static int getLeadOrderDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT lead_time FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                leadOrderDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return leadOrderDays;
    }

    /**
     * @param leadOrderDays the leadOrderDays to set
     */
    public static void setLeadOrderDays(int leadOrderDays) {
        leadOrderDays = leadOrderDays;
    }

    /**
     * @return the bufferStockDays
     */
    public static int getBufferStockDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT reorder_level FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                bufferStockDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return bufferStockDays;
    }

    /**
     * @return the reOrderQuantity
     */
    public static long getReOrderQuantity(String item) {

        stockLevel = getStockLevel(item);

        long reOrderLevel = Math.round(getAverageConsumption(item) * (getLeadOrderDays() + InventoryLevels.getBufferStockDays()));

        if (reOrderLevel > stockLevel) {

            reOrderQuantity = reOrderLevel - stockLevel;

        } else {

            reOrderQuantity = 0;
        }

        return reOrderQuantity;
    }

    /**
     * @param aReOrderQuantity the reOrderQuantity to set
     */
    public static void setReOrderQuantity(int aReOrderQuantity) {
        reOrderQuantity = aReOrderQuantity;
    }

    /**
     * @return the storeReorderQuantity
     */
    public static long getStoreReOrderQuantity(String storeName, String item) {

        stockLevel = getStoreStockLevel(storeName, item);

        long reOrderLevel = Math.round(getStoreAverageConsumption(storeName, item) * (getLeadOrderDays() + InventoryLevels.getBufferStockDays()));

        if (reOrderLevel > stockLevel) {

            storeReorderQuantity = reOrderLevel - stockLevel;

        } else {

            storeReorderQuantity = 0;
        }

        return storeReorderQuantity;
    }

    /**
     * @param aStoreReorderQuantity the storeReorderQuantity to set
     */
    public static void setStoreReorderQuantity(long aStoreReorderQuantity) {
        storeReorderQuantity = aStoreReorderQuantity;
    }

    /**
     * @return the storeOptimumStockLevel
     */
    public static long getStoreOptimumStockLevel(String storeName, String item) {

        storeOptimumStockLevel = Math.round(getStoreAverageConsumption(storeName, item) * (getLeadOrderDays() + InventoryLevels.bufferStockDays));

        return storeOptimumStockLevel;
    }

    /**
     * @param aStoreOptimumStockLevel the storeOptimumStockLevel to set
     */
    public static void setStoreOptimumStockLevel(long aStoreOptimumStockLevel) {
        storeOptimumStockLevel = aStoreOptimumStockLevel;
    }

    /**
     * @param bufferStockDays the bufferStockDays to set
     */
    public void setBufferStockDays(int bufferStockDays) {
        this.bufferStockDays = bufferStockDays;
    }

    /**
     * @return the averagingDays
     */
    public static int getAveragingDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT average_day FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                averagingDays = Math.round(rset.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return averagingDays;
    }

    /**
     * @param averagingDays the averagingDays to set
     */
    public void setAveragingDays(int averagingDays) {
        this.averagingDays = averagingDays;
    }

    public static boolean getReorderStatus(String item) {

        boolean reorderStatus = false;

        int stockLevel = InventoryLevels.getStockLevel(item);

        double reOrderLevel = getAverageConsumption(item) * (getLeadOrderDays() + InventoryLevels.bufferStockDays);

        if (stockLevel > reOrderLevel) {

            reorderStatus = false;

        } else {

            reorderStatus = true;

        }

        return reorderStatus;

    }

    public static boolean getStoreReorderStatus(String storeName, String item) {

        boolean storeReorderStatus = false;

        int stockLevel = InventoryLevels.getStoreAverageConsumption(storeName, item);

        double reOrderLevel = getStoreAverageConsumption(storeName, item) * (getLeadOrderDays() + InventoryLevels.bufferStockDays);

        if (stockLevel > reOrderLevel) {

            storeReorderStatus = false;

        } else {

            storeReorderStatus = true;

        }

        return storeReorderStatus;

    }

    /**
     * @return the averageConsumption
     */
    public static double getAverageConsumption(String item) {

        averageConsumption = getConsumptionNumbers(item) / getAveragingDays();

        return averageConsumption;
    }

    /**
     * @param averageConsumption the averageConsumption to set
     */
    public void setAverageConsumption(int averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    /**
     * @return the expiryDays
     */
    public static int getExpiryDays() {

        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT expiry_days FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                expiryDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return expiryDays;
    }

    /**
     * @param expiryDays the expiryDays to set
     */
    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }

    /**
     * @return the stockLevel
     */
    public static int getStockLevel(String item) {
        int stockLevel = 0;
//        try {
//            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(quantity_received-sub_store_issuing) FROM st_stock_cardex WHERE item_code = ?");
//            pstmt.setString(1, item);
//            java.sql.ResultSet rset = pstmt.executeQuery();
//            while (rset.next()) {
//                stockLevel = rset.getInt(1);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
        stockLevel = (int) Math.round(StockFormulae.stockBalanceAll(com.afrisoftech.hospital.HospitalMain.connectDB,  item));
        return stockLevel;
    }

    /**
     * @param stockLevel the stockLevel to set
     */
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    /**
     * @return the storeStockLevel
     */
    public static int getStoreStockLevel(String storeName, String item) {
        int storeStockLevel = 0;
//        try {
//            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(quantity_received-sub_store_issuing) FROM st_stock_cardex WHERE item_code = ? AND upper(store) = upper(?)");
//            pstmt.setString(1, item);
//            pstmt.setString(2, storeName);
//            java.sql.ResultSet rset = pstmt.executeQuery();
//            while (rset.next()) {
//                storeStockLevel = rset.getInt(1);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
        System.out.println("Doing store : ["+storeName+"] stock item : ["+item+"]");
        storeStockLevel = (int) Math.round(StockFormulae.stockBalance(com.afrisoftech.hospital.HospitalMain.connectDB, storeName, item, com.afrisoftech.lib.ServerTime.getSQLDate(com.afrisoftech.hospital.HospitalMain.connectDB)));
        return storeStockLevel;
    }

    /**
     * @param storeStockLevel the storeStockLevel to set
     */
    public void setStoreStockLevel(int storeStockLevel) {
        this.storeStockLevel = storeStockLevel;
    }

    /**
     * @return the consumptionNumbers
     */
    public static int getConsumptionNumbers(String item) {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(sub_store_issuing) FROM st_stock_cardex WHERE item_code = ?  AND transaction_type ilike 'issuing' AND transaction_no not ilike 't%' AND date between now()::date - " + getAveragingDays() + " AND now()::date");
            pstmt.setString(1, item);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                consumptionNumbers = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return consumptionNumbers;
    }

    /**
     * @param consumptionNumbers the consumptionNumbers to set
     */
    public void setConsumptionNumbers(int consumptionNumbers) {
        this.consumptionNumbers = consumptionNumbers;
    }

    /**
     * @return the storeConsumptionNumbers
     */
    public static int getStoreConsumptionNumbers(String storeName, String item) {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(sub_store_issuing) FROM st_stock_cardex WHERE item_code = ? AND upper(store) = upper(?) AND transaction_type ILIKE 'issuing' AND transaction_no NOT ILIKE 't%' AND date BETWEEN now()::date - " + getAveragingDays() + " AND now()::date");
            pstmt.setString(1, item);
            pstmt.setString(2, storeName);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                storeConsumptionNumbers = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return storeConsumptionNumbers;
    }

    /**
     * @param storeConsumptionNumbers the storeConsumptionNumbers to set
     */
    public static void setStoreConsumptionNumbers(int storeConsumptionNumbers) {
        storeConsumptionNumbers = storeConsumptionNumbers;
    }

    /**
     * @return the storeAverageConsumption
     */
    public static int getStoreAverageConsumption(String storeName, String item) {

        storeAverageConsumption = getStoreConsumptionNumbers(storeName, item) / getAveragingDays();

        return storeAverageConsumption;
    }

    /**
     * @param storeAverageConsumption the storeAverageConsumption to set
     */
    public static void setStoreAverageConsumption(int storeAverageConsumption) {
        InventoryLevels.storeAverageConsumption = storeAverageConsumption;
    }

    /**
     * @return the optimumStockLevel
     */
    public static long getOptimumStockLevel(String item) {

        optimumStockLevel = Math.round(getAverageConsumption(item) * (getLeadOrderDays() + InventoryLevels.bufferStockDays));

        return optimumStockLevel;
    }

    /**
     * @param optimumStockLevel the optimumStockLevel to set
     */
    public static void setOptimumStockLevel(long optimumStockLevel) {
        optimumStockLevel = optimumStockLevel;
    }
    public static boolean getExpiryStatus(String expiryDate) {

        boolean expiryStatus = false;

        try {

            java.sql.PreparedStatement pstmtExpiry = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT '" + expiryDate + "'::date - current_date  >= (SELECT expiry_days FROM st_ordering_constants)");

            java.sql.ResultSet rsetExpiry = pstmtExpiry.executeQuery();

            while (rsetExpiry.next()) {
                    expiryStatus = rsetExpiry.getBoolean(1);
            }

        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
        }
        return expiryStatus;
    }

}
