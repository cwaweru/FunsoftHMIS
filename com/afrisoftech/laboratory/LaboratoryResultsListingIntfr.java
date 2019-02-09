/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.laboratory;

/**
 *
 * @author root
 */
public class LaboratoryResultsListingIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB;

    /**
     * Creates new form LaboratoryResultsListingIntfr
     */
    public LaboratoryResultsListingIntfr(java.sql.Connection connDB) {

        connectDB = connDB;

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

        clinicsButtonGroup = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        outPatientChkbx = new javax.swing.JCheckBox();
        inPatientChkbx = new javax.swing.JCheckBox();
        clinicsCmbx = new javax.swing.JComboBox<>();
        bodyPanel = new javax.swing.JPanel();
        resultsScrollPane = new javax.swing.JScrollPane();
        labResultsTable = new com.afrisoftech.dbadmin.JTable();
        actionPanel = new javax.swing.JPanel();
        refreshBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();
        spacerLabel = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Results for processed Laboratory requests");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        headerPanel.setLayout(new java.awt.GridBagLayout());

        beginDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Begin Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        clinicsButtonGroup.add(outPatientChkbx);
        outPatientChkbx.setText("OUT-Patient");
        outPatientChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        outPatientChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outPatientChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(outPatientChkbx, gridBagConstraints);

        clinicsButtonGroup.add(inPatientChkbx);
        inPatientChkbx.setSelected(true);
        inPatientChkbx.setText("IN-Patient");
        inPatientChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        inPatientChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inPatientChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(inPatientChkbx, gridBagConstraints);

        clinicsCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clinicsCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(clinicsCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        bodyPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        labResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Patient No.", "Patient Name", "Request No.", "Procedure No.", "Procedure Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < labResultsTable.getColumnCount(); i++) {
            column = labResultsTable.getColumnModel().getColumn(i);
            if (i == 2) {

                column.setPreferredWidth(500); // item description column is bigger
            } else {

                column.setPreferredWidth(100);

            }
        }
        resultsScrollPane.setViewportView(labResultsTable);
        labResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labResultsTableMouseClicked(evt);
            }
        });
        resultsScrollPane.setViewportView(labResultsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bodyPanel.add(resultsScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(bodyPanel, gridBagConstraints);

        actionPanel.setLayout(new java.awt.GridBagLayout());

        refreshBtn.setText("Refresh Results listing");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        actionPanel.add(refreshBtn, gridBagConstraints);

        closeFormBtn.setText("Close form");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        actionPanel.add(closeFormBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(spacerLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(actionPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void outPatientChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outPatientChkbxActionPerformed

        clinicsCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT UPPER(clinics) FROM pb_clinics ORDER BY 1"));
        // TODO add your handling code here:
    }//GEN-LAST:event_outPatientChkbxActionPerformed

    private void inPatientChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inPatientChkbxActionPerformed

        clinicsCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT UPPER(ward_name) FROM hp_wards ORDER BY 1"));

        // TODO add your handling code here:
    }//GEN-LAST:event_inPatientChkbxActionPerformed

    private void clinicsCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clinicsCmbxActionPerformed
        if (clinicsCmbx.getSelectedItem() != null) {
            if (inPatientChkbx.isSelected()) {
                labResultsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT input_date::date as date, patient_no, patient_name, request_id as request_no, lab_no, typeof_test as procedure_name FROM hp_lab_results WHERE patient_no IN (SELECT DISTINCT patient_no FROM hp_admission WHERE date_admitted::date >= '" + beginDatePicker.getDate() + "' AND (discharge_date::date <= '" + endDatePicker.getDate() + "' OR discharge_date IS NULL) AND ward = '" + clinicsCmbx.getSelectedItem().toString().toUpperCase() + "') AND input_date::date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' ORDER BY 1,2,3"));
            } else {
                labResultsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT input_date::date as date, patient_no, patient_name, request_id as request_no, lab_no, typeof_test as procedure_name FROM hp_lab_results WHERE patient_no IN (SELECT DISTINCT patient_no FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND UPPER(clinic) = '" + clinicsCmbx.getSelectedItem().toString().toUpperCase() + "') AND input_date::date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' ORDER BY 1,2,3"));

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_clinicsCmbxActionPerformed

    private void labResultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labResultsTableMouseClicked

        String labNo = labResultsTable.getValueAt(labResultsTable.getSelectedRow(), 4).toString();

        com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();

        policy.PatientLabResultsPdf(connectDB, labNo, labNo);

        // TODO add your handling code here:
    }//GEN-LAST:event_labResultsTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.ButtonGroup clinicsButtonGroup;
    private javax.swing.JComboBox<String> clinicsCmbx;
    private javax.swing.JButton closeFormBtn;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JCheckBox inPatientChkbx;
    private javax.swing.JTable labResultsTable;
    private javax.swing.JCheckBox outPatientChkbx;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JScrollPane resultsScrollPane;
    private javax.swing.JLabel spacerLabel;
    // End of variables declaration//GEN-END:variables
}
