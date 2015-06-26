/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class NutritionCompareGenReport extends javax.swing.JDialog {

    int reportName;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.sql.Connection connectDB = null;
    java.util.Vector dateStartEnd = null;
    javax.swing.JSpinner beginDateSpinner = null;
    javax.swing.JSpinner endDateSpinner = null;
    
   
    
    public NutritionCompareGenReport(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {

        super(parent, modal);

        reportName = repName;

        connectDB = connectDb;

        pConnDB = pconnDB;

        initComponents();
        
//        populateCmbox();
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonsPanel = new javax.swing.JPanel();
        wardNutCnclBtn = new javax.swing.JButton();
        wardNutPRBtn = new javax.swing.JButton();
        departmentPanel = new javax.swing.JPanel();
        wardNutResultsLbl = new javax.swing.JLabel();
        departmentNutNamCbx = new javax.swing.JComboBox();
        departmentNutNameLbl = new javax.swing.JLabel();
        datePanel1 = new javax.swing.JPanel();
        dateFromPanel = new javax.swing.JPanel();
        wardNutNameBDLbl = new javax.swing.JLabel();
        wardNutNameEDLbl = new javax.swing.JLabel();
        departNutNameBDdatePicker = new com.afrisoftech.lib.DatePicker();
        departNutNameEDdatePicker = new com.afrisoftech.lib.DatePicker();
        dateToPanel = new javax.swing.JPanel();
        wardNutNameBDLbl1 = new javax.swing.JLabel();
        wardNutNameEDLbl1 = new javax.swing.JLabel();
        departNutNameBDdatePicker1 = new com.afrisoftech.lib.DatePicker();
        departNutNameEDdatePicker1 = new com.afrisoftech.lib.DatePicker();

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        wardNutCnclBtn.setMnemonic('n');
        wardNutCnclBtn.setText("Cancel");
        wardNutCnclBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wardNutCnclBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(wardNutCnclBtn, gridBagConstraints);

        wardNutPRBtn.setMnemonic('o');
        wardNutPRBtn.setText("Preview Report");
        wardNutPRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wardNutPRBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(wardNutPRBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        departmentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Department Here"));
        departmentPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        departmentPanel.add(wardNutResultsLbl, gridBagConstraints);

        departmentNutNamCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nutrition Department" }));
        departmentNutNamCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentNutNamCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        departmentPanel.add(departmentNutNamCbx, gridBagConstraints);

        departmentNutNameLbl.setText("Department Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        departmentPanel.add(departmentNutNameLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(departmentPanel, gridBagConstraints);

        datePanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Analysis Period"));
        datePanel1.setLayout(new java.awt.GridBagLayout());

        dateFromPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the First Period"));
        dateFromPanel.setLayout(new java.awt.GridBagLayout());

        wardNutNameBDLbl.setText("From");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        dateFromPanel.add(wardNutNameBDLbl, gridBagConstraints);

        wardNutNameEDLbl.setText("To ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        dateFromPanel.add(wardNutNameEDLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        dateFromPanel.add(departNutNameBDdatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        dateFromPanel.add(departNutNameEDdatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        datePanel1.add(dateFromPanel, gridBagConstraints);

        dateToPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the Second Period"));
        dateToPanel.setLayout(new java.awt.GridBagLayout());

        wardNutNameBDLbl1.setText("From");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        dateToPanel.add(wardNutNameBDLbl1, gridBagConstraints);

        wardNutNameEDLbl1.setText("To ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        dateToPanel.add(wardNutNameEDLbl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        dateToPanel.add(departNutNameBDdatePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        dateToPanel.add(departNutNameEDdatePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        datePanel1.add(dateToPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(datePanel1, gridBagConstraints);

        setSize(new java.awt.Dimension(641, 317));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void departmentNutNamCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentNutNamCbxActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_departmentNutNamCbxActionPerformed

    private void wardNutCnclBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wardNutCnclBtnActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_wardNutCnclBtnActionPerformed

    private void wardNutPRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wardNutPRBtnActionPerformed
        this.getReport(reportName);
        
    }//GEN-LAST:event_wardNutPRBtnActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    public java.util.Vector getBeginEndDates() {

        dateStartEnd = new java.util.Vector(1, 1);

        dateStartEnd.addElement(beginDateSpinner.getValue().toString());

        dateStartEnd.addElement(endDateSpinner.getValue().toString());

        return dateStartEnd;

    }

    public void getReport(int reportName) {

        switch (reportName) {
            case 7777: {
                com.afrisoftech.hospinventory.AnalysisNutriDepartReportsPdf policy = new com.afrisoftech.hospinventory.AnalysisNutriDepartReportsPdf();
                
                policy.AnalysisNutriDepartItemsPdf(connectDB, this.departNutNameBDdatePicker.getDate(), this.departNutNameEDdatePicker.getDate(), this.departNutNameBDdatePicker1.getDate(), this.departNutNameEDdatePicker1.getDate(), departmentNutNamCbx.getSelectedItem().toString());
                //public void AnalysisNutriDepartItemsPdf(java.sql.Connection connDb,java.lang.String begindate, java.lang.String endate, java.lang.String begindate1, java.lang.String endate1, java.lang.String store){
                
                this.dispose(); 
                
                
            }
        }
           
        
    }
    
//    private void populateCmbox() {
//        departmentNutNamCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct ward_name as name from hp_wards order by name"));
//        departmentNutNamCbx.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    //wardNutNamCbxActionPerformed(evt);
//                }
////            });
//    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel dateFromPanel;
    private javax.swing.JPanel datePanel1;
    private javax.swing.JPanel dateToPanel;
    private com.afrisoftech.lib.DatePicker departNutNameBDdatePicker;
    private com.afrisoftech.lib.DatePicker departNutNameBDdatePicker1;
    private com.afrisoftech.lib.DatePicker departNutNameEDdatePicker;
    private com.afrisoftech.lib.DatePicker departNutNameEDdatePicker1;
    private javax.swing.JComboBox departmentNutNamCbx;
    private javax.swing.JLabel departmentNutNameLbl;
    private javax.swing.JPanel departmentPanel;
    private javax.swing.JButton wardNutCnclBtn;
    private javax.swing.JLabel wardNutNameBDLbl;
    private javax.swing.JLabel wardNutNameBDLbl1;
    private javax.swing.JLabel wardNutNameEDLbl;
    private javax.swing.JLabel wardNutNameEDLbl1;
    private javax.swing.JButton wardNutPRBtn;
    private javax.swing.JLabel wardNutResultsLbl;
    // End of variables declaration//GEN-END:variables
}
