/*
 * LastPMsetupintfr.java
 *
 * Created on February 11, 2005, 1:19 PM
 */

package com.afrisoftech.fleet;

/**
 *
 * @author  Raphael Mbubua.
 */
public class LastPMsetupintfr extends javax.swing.JInternalFrame {
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    /** Creates new form LastPMsetupintfr */
    public LastPMsetupintfr(java.sql.Connection connDB, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
       connectDB = connDB;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        Inspectionchb1 = new javax.swing.JCheckBox();
        Normalchb1 = new javax.swing.JCheckBox();
        Otherschb1 = new javax.swing.JCheckBox();
        Warrantychb1 = new javax.swing.JCheckBox();
        Replacechb1 = new javax.swing.JCheckBox();
        fluidschb1 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jTable1 = new javax.swing.JTable();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setTitle("Configer Last Performed Parameters.");
        setVisible(true);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(new javax.swing.border.TitledBorder("PM Type Viewing"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFocusable(false);
        jScrollPane2.setEnabled(false);
        jScrollPane2.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridBagLayout());

        Inspectionchb1.setText("Inspection");
        Inspectionchb1.setContentAreaFilled(false);
        Inspectionchb1.setFocusable(false);
        Inspectionchb1.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(Inspectionchb1, gridBagConstraints);

        Normalchb1.setText("Normal");
        Normalchb1.setContentAreaFilled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(Normalchb1, gridBagConstraints);

        Otherschb1.setText("Others");
        Otherschb1.setContentAreaFilled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(Otherschb1, gridBagConstraints);

        Warrantychb1.setContentAreaFilled(false);
        Warrantychb1.setLabel("Warranty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(Warrantychb1, gridBagConstraints);

        Replacechb1.setText("Replace");
        Replacechb1.setContentAreaFilled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(Replacechb1, gridBagConstraints);

        fluidschb1.setText("Fruids");
        fluidschb1.setContentAreaFilled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(fluidschb1, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(new javax.swing.border.TitledBorder("Current Information"));
        jTextArea1.setColumns(1);
        jTextArea1.setRows(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jTextArea1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 100.0;
        jPanel4.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Schedule Setup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jButton1, gridBagConstraints);

        jButton2.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jButton2, gridBagConstraints);

        jButton3.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel6, gridBagConstraints);

        jLabel7.setText("jLabel7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel7, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jTable1, gridBagConstraints);

        jScrollPane4.setViewportView(jPanel11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        jPanel10.add(jScrollPane4, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        setBounds(0, 0, 539, 300);
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox Normalchb1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox fluidschb1;
    private javax.swing.JCheckBox Inspectionchb1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox Replacechb1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JCheckBox Warrantychb1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JCheckBox Otherschb1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}