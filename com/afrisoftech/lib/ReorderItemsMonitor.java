/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class ReorderItemsMonitor implements Runnable {

    java.sql.Connection connectDB = null;
    java.lang.Thread threadReorder = null;

    public void ReorderItemsMonitor(java.sql.Connection connDB) {
        connectDB = connDB;
        threadReorder = new java.lang.Thread(this, "Reorder items thread");

    }

    @Override
    public void run() {
        while (true) {
            try {
                getReorderStatus();
                //Wait for two hours to repeat process
                if(Thread.currentThread().getName().contains("reorder")){
                    threadReorder.sleep(7200000);
                }
                       
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            }
        }
    }

    void getReorderStatus() {

        boolean belowReorderLevel = false;

        double itemBalance = 0.00;

        double reorderLevel = 0.00;

        try {
            java.lang.Object[] listofActivities = this.getListofActivities();

            for (int i = 0; i < listofActivities.length; i++) {

                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT sum(receiving-issuing) FROM st_sub_stores WHERE item_code = ?");

                pstmt.setString(1, listofActivities[i].toString());

                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    itemBalance = rset.getInt(1);

                }
                java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT reorder_level FROM st_stock_item WHERE item_code = ?");

                pstmtR.setString(1, listofActivities[i].toString());

                java.sql.ResultSet rsetR = pstmt.executeQuery();

                while (rsetR.next()) {

                    reorderLevel = rsetR.getDouble(1);

                }

                if (reorderLevel <= itemBalance) {

                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Please note that you have only [" + itemBalance + " units of [" + com.afrisoftech.lib.StockItemFactory.getItemDecription(connectDB, listofActivities[i].toString()) + "] which is below reorder level. \nnPlease initiate the process of ordering more items!");

                }

            }


        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());

                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code, description FROM st_stock_item ORDER BY 2,1");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());

                System.out.println("description>> " + rSet1.getObject(1).toString());
            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();

        System.out.println("Done list of activities ...");

        return listofActivities;
    }

    public void startThread() {
        if (threadReorder != null) {
            if (!threadReorder.isAlive()) {
                threadReorder.start();
            }
        }
    }

    public void stopThread() {
         if (threadReorder != null) {
            if (threadReorder.isAlive()) {
                threadReorder.stop();
            }
        }       
    }
}
