/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.systempartners.reports;

/**
 *
 * @author Charles
 */
public class TBScreeningRegisterReportIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    /**
     * Creates new form EyeUnitRegisterReportIntfr
     */
    public TBScreeningRegisterReportIntfr(java.sql.Connection connDB) {
        
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

        headerPanel = new javax.swing.JPanel();
        startDatePicker = new com.afrisoftech.lib.DatePicker();
        clinicsCmbx = new javax.swing.JComboBox();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        bodyPanel = new javax.swing.JPanel();
        reportScrollPane = new javax.swing.JScrollPane();
        registerReportTable = new com.afrisoftech.dbadmin.JXTable();
        buttonPanel = new javax.swing.JPanel();
        spacerLabel = new javax.swing.JLabel();
        generateReportBtn = new javax.swing.JButton();
        refreshReportBtn = new javax.swing.JButton();
        closeReportBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("TB Screening Register");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        startDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Start Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(startDatePicker, gridBagConstraints);

        clinicsCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' AS clinics UNION SELECT DISTINCT clinics FROM pb_clinics ORDER BY 1")
        );
        clinicsCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Clinic"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(clinicsCmbx, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Report End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        registerReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reportScrollPane.setViewportView(registerReportTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bodyPanel.add(reportScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        getContentPane().add(bodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spacerLabel, gridBagConstraints);

        generateReportBtn.setText("Generate TB Screening Register Report");
        generateReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(generateReportBtn, new java.awt.GridBagConstraints());

        refreshReportBtn.setText("Refresh report");
        refreshReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshReportBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(refreshReportBtn, new java.awt.GridBagConstraints());

        closeReportBtn.setText("Close form");
        closeReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeReportBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(closeReportBtn, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
        if(clinicsCmbx.getSelectedItem().toString().contains("--ALL--")){
        registerReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT visit_date::date as service_date, visit_date::time(0) as service_time , data_index as entry_id,  patient_no, initcap(patient_name) as patient_name, clinic_attended, indicator_checked as diagnosed_condition,  "
                + "        gender as gender, patient_age, "
                + "       emergency, remarks,"
                + " user_name as clinician FROM public.hp_screening_data WHERE visit_date::date BETWEEN '"+startDatePicker.getDate()+"' AND '"+endDatePicker.getDate()+"' ORDER BY 1"));
        } else {
              registerReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT visit_date::date as service_date, visit_date::time(0) as service_time , data_index as entry_id,  patient_no, initcap(patient_name) as patient_name, indicator_checked as diagnosed_condition,  "
                + "        gender as gender, patient_age, "
                + "       emergency, remarks,"
                + " user_name as clinician FROM public.hp_screening_data WHERE upper(clinic_attended) = '"+clinicsCmbx.getSelectedItem().toString().toUpperCase()+"' AND visit_date::date BETWEEN '"+startDatePicker.getDate()+"' AND '"+endDatePicker.getDate()+"' ORDER BY 1"));
    
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_generateReportBtnActionPerformed

    private void closeReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeReportBtnActionPerformed

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_closeReportBtnActionPerformed

    private void refreshReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshReportBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox clinicsCmbx;
    private javax.swing.JButton closeReportBtn;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton refreshReportBtn;
    private javax.swing.JTable registerReportTable;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JLabel spacerLabel;
    private com.afrisoftech.lib.DatePicker startDatePicker;
    // End of variables declaration//GEN-END:variables
}
