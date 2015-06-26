/*
 * Generated by JDBC Form Wizard
 *
 * ReceivedItems.java
 *
 * Created on Oct 23, 2003, 06:46 PM
*/

package com.afrisoftech.hospinventory;

/**
 *
 * @author  root
 * @version 
 */

public class ReceivedItems extends javax.swing.JFrame
{

     java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public ReceivedItems(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        try {
            nBCachedRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        dataNavigator1 = new org.netbeans.lib.sql.DataNavigator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        nBCachedRowSet1.setCommand("select * from st_stock_cardex where debit>0 and date = current_date order by supplier");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        try {
            nBCachedRowSet1.setTableName("st_stock_cardex");
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }

        setTitle("Direct Purchasing");
        try {
            dataNavigator1.setLayoutButtons(1);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        dataNavigator1.setRowSet(nBCachedRowSet1);
        getContentPane().add(dataNavigator1, java.awt.BorderLayout.SOUTH);

        jTable1.setModel(new org.netbeans.lib.sql.models.TableModel(nBCachedRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
            new org.netbeans.lib.sql.models.TableModel.Column("date", "Date", false),
            new org.netbeans.lib.sql.models.TableModel.Column("supplier", "Supplier", false),
            new org.netbeans.lib.sql.models.TableModel.Column("invoice_no", "Invoice no", false),
            new org.netbeans.lib.sql.models.TableModel.Column("store", "Store", false),
            new org.netbeans.lib.sql.models.TableModel.Column("item", "Item", false),
            new org.netbeans.lib.sql.models.TableModel.Column("units", "Units", false),
            new org.netbeans.lib.sql.models.TableModel.Column("quantity_received", "Quantity received", false),
            new org.netbeans.lib.sql.models.TableModel.Column("price_per_item", "Price per item", false),
            new org.netbeans.lib.sql.models.TableModel.Column("received_by", "Received by", false),
            new org.netbeans.lib.sql.models.TableModel.Column("delivered_by", "Delivered by", false),
            new org.netbeans.lib.sql.models.TableModel.Column("debit", "Amount", false)
        }));
        jTable1.setSelectionModel(new org.netbeans.lib.sql.models.SQLListSelectionModel (nBCachedRowSet1));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(740, 300));
        setLocation((screenSize.width-740)/2,(screenSize.height-300)/2);
    }//GEN-END:initComponents

    public static void main(String[] args)
    {
      //  new ReceivedItems().setVisible(true);    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.netbeans.lib.sql.DataNavigator dataNavigator1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

