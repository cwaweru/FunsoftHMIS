/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.records;

/**
 *
 * @author Charles
 */
public class PaedEtatIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form PaedEtatIntfr
     */
    java.sql.Connection connectDB = null;
    java.lang.String patientNumber = null;
    
    public PaedEtatIntfr(java.sql.Connection connDB, String patientNo) {
        connectDB = connDB;
        patientNumber = patientNo;
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

        mainPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Paediatric Examination and Clinical Records : Form - 732");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(mainPanel, new java.awt.GridBagConstraints());

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Save clinical details");
        buttonPanel.add(jButton1, new java.awt.GridBagConstraints());

        jButton2.setText("Close form");
        buttonPanel.add(jButton2, new java.awt.GridBagConstraints());

        jButton3.setText("Clear form");
        buttonPanel.add(jButton3, new java.awt.GridBagConstraints());

        getContentPane().add(buttonPanel, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
