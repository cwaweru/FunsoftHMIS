/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

/**
 *
 * @author saleem
 */
public class ContractsReportIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form ContractsReportIntfr
     */
     java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    public ContractsReportIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        initComponents();
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new com.afrisoftech.dbadmin.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Procurement Transactions Reporter");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTable1.setForeground(new java.awt.Color(102, 102, 102));
        jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct quotation_no CONTRACT_NO, openning_date OPENING_DATE, count(distinct item_description) ITEMS_ON_TENDER,count(distinct supplier) NO_OF_BIDDERS from st_floated_quotations group by 1,2 order by 1 desc"));
        jTable1.setGridColor(new java.awt.Color(0, 0, 255));
        jTable1.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jTabbedPane1.addTab("CONTRACT SUMMARY", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct supplier, description ITEM,unit UNITS,ordered_qty QTY ,user_name,date::timestamp Trans_date from st_recommendation  where  ordered_qty>0  AND quotation_no  IN (select DISTINCT quotation_no FROM st_orders) group by 1,2,3,4,5,6 order by 6 DESC "));
        jScrollPane2.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        jTabbedPane1.addTab("QUANTITIES ORDERED PER SUPPLIER", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct supplier, quotation_no CONTRACT, description ITEM,unit UNITS,quantity AWARDED_QTY from st_recommendation  where quantity>0  and quotation_no  IN (select DISTINCT quotation_no FROM st_orders)"));
        jScrollPane3.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane3, gridBagConstraints);

        jTabbedPane1.addTab("AWARDED ITEMS REPORT", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
