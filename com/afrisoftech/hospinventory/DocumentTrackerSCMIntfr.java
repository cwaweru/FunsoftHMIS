/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import javax.swing.JPanel;

/**
 *
 * @author saleem
 */
public class DocumentTrackerSCMIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form DocumentTrackerSCMIntfr
     */
    java.sql.Connection connectDB = null;
     JPanel jMainPanelToLoad=null;
     org.netbeans.lib.sql.pool.PooledConnectionSource  pConnDB =null;
    public DocumentTrackerSCMIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
     
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

        trackingLoadpnl = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Document Tracking System");
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

        trackingLoadpnl.setBackground(new java.awt.Color(153, 255, 255));
        trackingLoadpnl.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(trackingLoadpnl, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
         jMainPanelToLoad=new JPanel();
                  //jSplitPane1.setDividerLocation(84);
                  jMainPanelToLoad = (JPanel) new UniversalDashboard(connectDB,null);
                  trackingLoadpnl.removeAll();
                  trackingLoadpnl.setLayout(new java.awt.BorderLayout());
                  trackingLoadpnl.add(jMainPanelToLoad, java.awt.BorderLayout.CENTER);
                  trackingLoadpnl.updateUI(); 
    }//GEN-LAST:event_formInternalFrameOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel trackingLoadpnl;
    // End of variables declaration//GEN-END:variables
}