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
public class EyeUnitDetailedReportIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    /**
     * Creates new form EyeUnitRegisterReportIntfr
     */
    public EyeUnitDetailedReportIntfr(java.sql.Connection connDB) {
        
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
        setTitle("Eye Unit Attendance Register");
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

        clinicsCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' AS clinics UNION SELECT DISTINCT clinics FROM pb_clinics WHERE clinics ilike '%eye%' ORDER BY 1")
        );
        clinicsCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Eye Clinic"));
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

        org.openide.awt.Mnemonics.setLocalizedText(generateReportBtn, "Generate Eye Unit Attendance Report");
        generateReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(generateReportBtn, new java.awt.GridBagConstraints());

        org.openide.awt.Mnemonics.setLocalizedText(refreshReportBtn, "Refresh Report");
        buttonPanel.add(refreshReportBtn, new java.awt.GridBagConstraints());

        org.openide.awt.Mnemonics.setLocalizedText(closeReportBtn, "Close form");
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

        registerReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT transaction_time::timestamp(0) as service_time, transaction_id, patient_no, clinic_no, " +
"       patient_name, clinic_name, appointment_no, general_vision_indicator, " +
"       age, gender, operation_type, left_eye_upper::varchar||'/'||left_eye_lower::varchar as LEFT_EYE_ACUITY, " +
"       right_eye_upper::varchar||'/'||right_eye_lower::varchar as RIGHT_EYE_ACUITY, comments, " +
"       recommended_treatment, user_name as clinician" +
"  FROM public.eye_indicators WHERE transaction_time::date BETWEEN '"+startDatePicker.getDate()+"' AND '"+endDatePicker.getDate()+"' ORDER BY 1"));

        // TODO add your handling code here:
    }//GEN-LAST:event_generateReportBtnActionPerformed

    private void closeReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeReportBtnActionPerformed

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_closeReportBtnActionPerformed


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
