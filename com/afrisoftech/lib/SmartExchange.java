/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class SmartExchange {

    public static java.sql.Connection exchangeDBConn = null;

    public static java.sql.Connection getDBConnection(java.sql.Connection connectDB) {
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT exchange_host_ip, db_port, user_name, pass_code, schema_db"
                    + "  FROM public.smart_links");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                System.out.println("Exchange DB Host : ["+rset.getString(1)+"]");
                exchangeDBConn = java.sql.DriverManager.getConnection("jdbc:mysql://" + rset.getString(1) + ":" + rset.getString(2) + "/" + rset.getString(5), rset.getString(3), rset.getString(4));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return exchangeDBConn;
    }

}
