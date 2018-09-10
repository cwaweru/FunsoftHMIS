/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;

/**
 *
 * @author saleem
 */
public class StockFormulae {

    public StockFormulae() {

    }

    public static Double stockBalance(java.sql.Connection connectDB, String store, String item, java.sql.Date date1) {
        Double bal = 0.00;
        try {
            java.sql.Statement pstmtxx = connectDB.createStatement();

            java.sql.ResultSet rsxx = pstmtxx.executeQuery("select case when qty=0 or qty IS NULL then 0 ELSE qty END AS qty from st_balance_qty( '" + item + "',  '" +date1+ "' , '" + store + "') ");
                    //java.sql.ResultSet rs = pstmt.executeQuery("select sum(qty) from stock_balance_qty where description ilike '"+jSearchTable3.getValueAt(jSearchTable3.getSelectedRow(),0)+"' and department ilike '"+jTextField42.getText()+"' AND dates <= '"+datePicker1.getDate()+"'");
            //java.sql.ResultSet rs = pstmt.executeQuery("select sum(receiving-issuing) As qty from  st_sub_stores where item = '"+jSearchTable3.getValueAt(jSearchTable3.getSelectedRow(),0)+"' and department like '"+jTextField42.getText()+"' AND dates <= '"+datePicker1.getDate()+"'");
            while (rsxx.next()) {

                bal = rsxx.getDouble(1);

            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        }

        return bal;

    }
    
    public static Double stockBalanceAll(java.sql.Connection connectDB,  String item) {
        Double bal = 0.00;
        try {
            java.sql.Statement pstmtxx = connectDB.createStatement();

            java.sql.ResultSet rsxx = pstmtxx.executeQuery("select case when qty=0 or qty IS NULL then 0 ELSE qty END AS qty from st_balance_qty( '" + item + "') ");
                    //java.sql.ResultSet rs = pstmt.executeQuery("select sum(qty) from stock_balance_qty where description ilike '"+jSearchTable3.getValueAt(jSearchTable3.getSelectedRow(),0)+"' and department ilike '"+jTextField42.getText()+"' AND dates <= '"+datePicker1.getDate()+"'");
            //java.sql.ResultSet rs = pstmt.executeQuery("select sum(receiving-issuing) As qty from  st_sub_stores where item = '"+jSearchTable3.getValueAt(jSearchTable3.getSelectedRow(),0)+"' and department like '"+jTextField42.getText()+"' AND dates <= '"+datePicker1.getDate()+"'");
            while (rsxx.next()) {

                bal = rsxx.getDouble(1);

            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        }

        return bal;

    }

}
