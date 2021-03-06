/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.hospinventory;

import static com.afrisoftech.hospinventory.scmOperationsIntfr.scmOperationMainPanel;
import com.afrisoftech.hospital.HospitalMain;
import javax.swing.*;

/**
 *
 * @author sytem partners
 */
public class CsoMainIntFr extends javax.swing.JInternalFrame {

    /**
     * Creates new form CsoMainIntFr
     */
     java.sql.Connection connectDB = null;
     JPanel jMainPanelToLoad=null;
     java.lang.String designation = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    public CsoMainIntFr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB,java.lang.String Designation) {
     
        connectDB = connDb;
        pConnDB = pconnDB;
        designation = Designation;
        
        initComponents();
        System.out.println(com.afrisoftech.lib.UserName.getUserName(connectDB)+" Logged In as "+designation);
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

        jSplitPane1 = new javax.swing.JSplitPane();
        MainPane = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        assignBuyerbtn = new javax.swing.JButton();
        stockReplenishmentBtn = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        trackingBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CHIEF SUPPLY OFFICER OPERATIONS");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1000, 500));
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

        jSplitPane1.setDividerSize(0);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setAlignmentX(5.0F);

        MainPane.setBackground(new java.awt.Color(255, 255, 255));
        MainPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        MainPane.setLayout(new java.awt.GridBagLayout());
        jSplitPane1.setRightComponent(MainPane);

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(null);
        jToolBar2.setRollover(true);
        jToolBar2.setAlignmentX(20.0F);
        jToolBar2.setMargin(new java.awt.Insets(0, 50, 0, 0));

        assignBuyerbtn.setBackground(new java.awt.Color(255, 255, 255));
        assignBuyerbtn.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        assignBuyerbtn.setForeground(new java.awt.Color(0, 0, 204));
        assignBuyerbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/CSO/buyers.jpg"))); // NOI18N
        assignBuyerbtn.setText("           Assign External PRs to Buyers        ");
        assignBuyerbtn.setActionCommand("     Asign Buyers");
        assignBuyerbtn.setAlignmentX(20.0F);
        assignBuyerbtn.setBorder(null);
        assignBuyerbtn.setFocusable(false);
        assignBuyerbtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        assignBuyerbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        assignBuyerbtn.setMargin(new java.awt.Insets(2, 100, 2, 14));
        assignBuyerbtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        assignBuyerbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        assignBuyerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignBuyerbtnActionPerformed(evt);
            }
        });
        jToolBar2.add(assignBuyerbtn);

        stockReplenishmentBtn.setBackground(new java.awt.Color(255, 255, 255));
        stockReplenishmentBtn.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        stockReplenishmentBtn.setForeground(new java.awt.Color(0, 0, 204));
        stockReplenishmentBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/CSO/approve.jpg"))); // NOI18N
        stockReplenishmentBtn.setText("           Stock Replenishment Requisitions              ");
        stockReplenishmentBtn.setBorder(null);
        stockReplenishmentBtn.setFocusable(false);
        stockReplenishmentBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stockReplenishmentBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        stockReplenishmentBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stockReplenishmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockReplenishmentBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(stockReplenishmentBtn);

        jButton7.setBackground(java.awt.Color.white);
        jButton7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/supplierappraisal.jpg"))); // NOI18N
        jButton7.setText("          Supplier Listing           ");
        jButton7.setBorder(null);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton7);

        trackingBtn.setBackground(new java.awt.Color(255, 255, 255));
        trackingBtn.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        trackingBtn.setForeground(new java.awt.Color(0, 0, 204));
        trackingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/CSO/dashboard.jpg"))); // NOI18N
        trackingBtn.setText("       Work In Progress      ");
        trackingBtn.setBorder(null);
        trackingBtn.setFocusable(false);
        trackingBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        trackingBtn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        trackingBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        trackingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trackingBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(trackingBtn);

        jSplitPane1.setLeftComponent(jToolBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stockReplenishmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockReplenishmentBtnActionPerformed
        //setting  the Forwarding Pane
      
         jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new CsoRequisitionApproval(connectDB,null,designation);
                  MainPane.removeAll();
                  MainPane.setLayout(new java.awt.BorderLayout());
                  MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  MainPane.updateUI(); 
    }//GEN-LAST:event_stockReplenishmentBtnActionPerformed

    private void trackingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trackingBtnActionPerformed
        //setting  the Forwarding Pane
        
         jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new UniversalDashboard(connectDB,null);
                  MainPane.removeAll();
                  MainPane.setLayout(new java.awt.BorderLayout());
                  MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  MainPane.updateUI();
    }//GEN-LAST:event_trackingBtnActionPerformed

    private void assignBuyerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignBuyerbtnActionPerformed
          //setting  the CSOAssigning Buyers  Pane
       
         jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new CSOAssignBuyers(connectDB,null,designation);
                  MainPane.removeAll();
                  MainPane.setLayout(new java.awt.BorderLayout());
                  MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  MainPane.updateUI();
    }//GEN-LAST:event_assignBuyerbtnActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       //setting  the Mode Of purchase pane by default when the Internal frame opens
        jMainPanelToLoad=new JPanel();
                  jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new dashboardpnl();
                  MainPane.removeAll();
                  MainPane.setLayout(new java.awt.BorderLayout());
                  MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  MainPane.updateUI();
//         jMainPanelToLoad=new JPanel();
//                  jSplitPane1.setDividerLocation(84);
//                  jMainPanelToLoad = (JPanel) new UniversalDashboard(connectDB,null);
//                  MainPane.removeAll();
//                  MainPane.setLayout(new java.awt.BorderLayout());
//                  MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
//                  MainPane.updateUI(); 
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jMainPanelToLoad=new JPanel();
        jSplitPane1.setDividerLocation(84);
        jMainPanelToLoad = (JPanel) new SSOSupplierViewer(connectDB,null);
        MainPane.removeAll();
        MainPane.setLayout(new java.awt.BorderLayout());
        MainPane.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
        MainPane.updateUI();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel MainPane;
    private javax.swing.JButton assignBuyerbtn;
    private javax.swing.JButton jButton7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton stockReplenishmentBtn;
    private javax.swing.JButton trackingBtn;
    // End of variables declaration//GEN-END:variables
}
