/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author Charles Waweru <<cwaweru@systempartners.biz>>
 */
public class StockControlIndicators {

    public static boolean canDispenseNegativeStock(java.sql.Connection connectDB) {

        boolean neg = false;

        try {

            java.sql.Statement pstmt1 = connectDB.createStatement();

            java.sql.ResultSet rs1 = pstmt1.executeQuery("select neg_allow from st_stock_control"); //from orders where supplier ='"+jTable1.getValueAt(i,4).toString()+"'");

            while (rs1.next()) {

                neg = rs1.getBoolean(1);
            }

        } catch (java.sql.SQLException sq) {

            sq.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sq.getMessage());

        }

        return neg;
    }

}
