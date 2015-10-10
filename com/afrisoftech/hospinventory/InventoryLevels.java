/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class InventoryLevels {

    private int leadOrderDays = 0;

    private int bufferStockDays = 0;

    private int averagingDays = 0;

    private int averageConsumption = 0;
    
    private int storeAverageConsumption = 0;

    private int expiryDays = 0;

    private int stockLevel = 0;

    private int storeStockLevel = 0;

    private int consumptionNumbers = 0;

    private int storeConsumptionNumbers = 0;

    /**
     * @return the leadOrderDays
     */
    public int getLeadOrderDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT lead_time FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                leadOrderDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return leadOrderDays;
    }

    /**
     * @param leadOrderDays the leadOrderDays to set
     */
    public void setLeadOrderDays(int leadOrderDays) {
        this.leadOrderDays = leadOrderDays;
    }

    /**
     * @return the bufferStockDays
     */
    public int getBufferStockDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT reorder_level FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                bufferStockDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return bufferStockDays;
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
    public int getAveragingDays() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT average_day FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                averagingDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
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

    public boolean getReorderStatus(String item) {

        boolean reorderStatus = false;

        int stockLevel = this.getStockLevel(item);
        
        int reOrderLevel = this.getAverageConsumption(item) * (this.getLeadOrderDays() + this.bufferStockDays);
        
        if(stockLevel > reOrderLevel ){
            
           reorderStatus = false;
           
        } else {
            
           reorderStatus = true; 
           
        }
        
        return reorderStatus;

    }

    /**
     * @return the averageConsumption
     */
    public int getAverageConsumption(String item) {
        
        averageConsumption = getConsumptionNumbers(item)/getAveragingDays();
        
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
    public int getExpiryDays() {

        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT expiry_days FROM st_ordering_constants");

            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                expiryDays = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
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
    public int getStockLevel(String item) {
        //int stockLevel = 0;
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(quantity_received-sub_store_issuing) FROM st_stock_cardex WHERE item_code = ?");
            pstmt.setString(1, item);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
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
    public int getStoreStockLevel(String storeName, String item) {
        //int storeStockLevel = 0;
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(quantity_received-sub_store_issuing) FROM st_stock_cardex WHERE item_code = ? AND upper(store) = upper(?)");
            pstmt.setString(1, item);
            pstmt.setString(2, storeName);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                storeStockLevel = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

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
    public int getConsumptionNumbers(String item) {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(sub_store_issuing) FROM st_stock_cardex WHERE item_code = ?  AND transaction_type ilike 'issuing' AND transaction_no not ilike 't%' AND date between now()::date AND now()::date - "+getAveragingDays());
            pstmt.setString(1, item);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                consumptionNumbers = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
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
    public int getStoreConsumptionNumbers(String storeName, String item) {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT sum(sub_store_issuing) FROM st_stock_cardex WHERE item_code = ? AND upper(store) = upper(?) AND transaction_type ILIKE 'issuing' AND transaction_no NOT ILIKE 't%' AND date BETWEEN now()::date AND now()::date - "+getAveragingDays());
            pstmt.setString(1, item);
            pstmt.setString(2, storeName);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                storeConsumptionNumbers = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return storeConsumptionNumbers;
    }

    /**
     * @param storeConsumptionNumbers the storeConsumptionNumbers to set
     */
    public void setStoreConsumptionNumbers(int storeConsumptionNumbers) {
        this.storeConsumptionNumbers = storeConsumptionNumbers;
    }

    /**
     * @return the storeAverageConsumption
     */
    public int getStoreAverageConsumption(String storeName, String item) {
        
        storeAverageConsumption = getStoreConsumptionNumbers(storeName, item)/getAveragingDays();
        
        return storeAverageConsumption;
    }

    /**
     * @param storeAverageConsumption the storeAverageConsumption to set
     */
    public void setStoreAverageConsumption(int storeAverageConsumption) {
        this.storeAverageConsumption = storeAverageConsumption;
    }

}
