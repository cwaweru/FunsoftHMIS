/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;
import javax.swing.*;
/**
 *
 * @author saleem
 */
public class scmOperationsIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form scmOperationsIntfr
     */
    java.sql.Connection connectDB = null;
     JPanel jMainPanelToLoad=null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    public scmOperationsIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
     
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
        jSplitPane1 = new javax.swing.JSplitPane();
        scmOperationMainPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        scmReports = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Supply Chain Management Operations");
        setMinimumSize(new java.awt.Dimension(600, 350));
        setPreferredSize(new java.awt.Dimension(1100, 520));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setDividerLocation(85);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        scmOperationMainPanel.setBackground(java.awt.Color.cyan);
        scmOperationMainPanel.setLayout(new java.awt.GridBagLayout());
        jSplitPane1.setRightComponent(scmOperationMainPanel);

        jToolBar1.setBackground(java.awt.Color.white);
        jToolBar1.setRollover(true);

        jButton2.setBackground(java.awt.Color.white);
        jButton2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/prOperations.jpg"))); // NOI18N
        jButton2.setText("     Purchase Operations ");
        jButton2.setBorder(null);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton6.setBackground(java.awt.Color.white);
        jButton6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/operations management.jpg"))); // NOI18N
        jButton6.setText("         Approvals      ");
        jButton6.setBorder(null);
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        scmReports.setBackground(java.awt.Color.white);
        scmReports.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        scmReports.setForeground(new java.awt.Color(0, 0, 204));
        scmReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/survey.jpg"))); // NOI18N
        scmReports.setText("   Management Reports        ");
        scmReports.setBorder(null);
        scmReports.setFocusable(false);
        scmReports.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        scmReports.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        scmReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scmReportsActionPerformed(evt);
            }
        });
        jToolBar1.add(scmReports);

        jButton7.setBackground(java.awt.Color.white);
        jButton7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/supplierappraisal.jpg"))); // NOI18N
        jButton7.setText("       Supplier Listing        ");
        jButton7.setBorder(null);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/CSO/dashboard.jpg"))); // NOI18N
        jButton4.setText("       DashBoard       ");
        jButton4.setBorder(null);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(117, 81));
        jPanel2.setPreferredSize(new java.awt.Dimension(117, 81));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        jToolBar1.add(jPanel2);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Help 2.png"))); // NOI18N
        jButton3.setText("                 Help                   ");
        jButton3.setBorder(null);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jSplitPane1.setTopComponent(jToolBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jSplitPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // load the PRDiseminations panel  
       // load the PRDiseminations panel  
        jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new AwardApprovalsIntfr(connectDB,null);
                  scmOperationMainPanel.removeAll();
                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  scmOperationMainPanel.updateUI();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new dashboardpnl();
                  scmOperationMainPanel.removeAll();
                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  scmOperationMainPanel.updateUI();
//                  scmOperationMainPanel.removeAll();
//                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
//                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
//                  scmOperationMainPanel.updateUI(); 
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // load the PRMemoranda panel  
        jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new PROperationpnl(connectDB,null);
                  scmOperationMainPanel.removeAll();
                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  scmOperationMainPanel.updateUI();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //setting  the DashBoard

        jMainPanelToLoad=new JPanel();
        jSplitPane1.setDividerLocation(84);
        jMainPanelToLoad = (JPanel) new UniversalDashboard(connectDB,null);
        scmOperationMainPanel.removeAll();
        scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
        scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
        scmOperationMainPanel.updateUI();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void scmReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scmReportsActionPerformed
        // TODO add your handling code here:
        
         jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new SCMReportsIntfr(connectDB,null);
                  scmOperationMainPanel.removeAll();
                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  scmOperationMainPanel.updateUI();
    }//GEN-LAST:event_scmReportsActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new SSOSupplierViewer(connectDB,null);
                  scmOperationMainPanel.removeAll();
                  scmOperationMainPanel.setLayout(new java.awt.BorderLayout());
                  scmOperationMainPanel.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  scmOperationMainPanel.updateUI();
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JPanel scmOperationMainPanel;
    private javax.swing.JButton scmReports;
    // End of variables declaration//GEN-END:variables
}
