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

    private int expiryDays = 0;

    private int stockLevel = 0;

    private int storeStockLevel = 0;

    /**
     * @return the leadOrderDays
     */
    public int getLeadOrderDays() {
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

        return reorderStatus;

    }

    /**
     * @return the averageConsumption
     */
    public int getAverageConsumption(String item) {
        
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

}
