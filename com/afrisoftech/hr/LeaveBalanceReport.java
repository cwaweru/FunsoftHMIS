/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.hr;


/**
 *
 * @author  root
 */
public class LeaveBalanceReport extends javax.swing.JDialog {

    int reportName;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.sql.Connection connectDB = null;
    java.util.Vector dateStartEnd = null;
    javax.swing.JSpinner beginDateSpinner = null;
    javax.swing.JSpinner endDateSpinner = null;
    
   
    
    public LeaveBalanceReport(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {

        super(parent, modal);

        reportName = repName;

        connectDB = connectDb;

        pConnDB = pconnDB;

        initComponents();
        
        populateCmbox();
        
        divisionPanel.setVisible(false);
        departmentPanel.setVisible(false);
        sectionPanel.setVisible(false);
        
        
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
        cancelBtn = new javax.swing.JButton();
        previewBtn = new javax.swing.JButton();
        sectionPanel = new javax.swing.JPanel();
        wardNutResultsLbl = new javax.swing.JLabel();
        sectionCbx = new javax.swing.JComboBox();
        sectionLbl = new javax.swing.JLabel();
        datePanel = new javax.swing.JPanel();
        wardNutNameBDLbl = new javax.swing.JLabel();
        wardNutNameEDLbl = new javax.swing.JLabel();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        departmentPanel = new javax.swing.JPanel();
        departmentLabel = new javax.swing.JLabel();
        departmentCmbx = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        departmentChckBox = new javax.swing.JCheckBox();
        sectionChckBox = new javax.swing.JCheckBox();
        divisionChkBx = new javax.swing.JCheckBox();
        divisionPanel = new javax.swing.JPanel();
        divisionLabel = new javax.swing.JLabel();
        divisionCmbx = new javax.swing.JComboBox();

        setTitle("Leave Balance Report - Select Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        cancelBtn.setMnemonic('n');
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(cancelBtn, gridBagConstraints);

        previewBtn.setMnemonic('o');
        previewBtn.setText("Preview Report");
        previewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(previewBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        sectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Section Here"));
        sectionPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sectionPanel.add(wardNutResultsLbl, gridBagConstraints);

        sectionCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        sectionPanel.add(sectionCbx, gridBagConstraints);

        sectionLbl.setText("Section Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sectionPanel.add(sectionLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(sectionPanel, gridBagConstraints);

        datePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Dates"));
        datePanel.setLayout(new java.awt.GridBagLayout());

        wardNutNameBDLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(wardNutNameBDLbl, gridBagConstraints);

        wardNutNameEDLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(wardNutNameEDLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(endDatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(datePanel, gridBagConstraints);

        departmentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the Department Here"));
        departmentPanel.setLayout(new java.awt.GridBagLayout());

        departmentLabel.setText("Department Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        departmentPanel.add(departmentLabel, gridBagConstraints);

        departmentCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        departmentPanel.add(departmentCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(departmentPanel, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Report Options Below"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        departmentChckBox.setText("Department");
        departmentChckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentChckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(departmentChckBox, gridBagConstraints);

        sectionChckBox.setText("Section");
        sectionChckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionChckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(sectionChckBox, gridBagConstraints);

        divisionChkBx.setText("Division");
        divisionChkBx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionChkBxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(divisionChkBx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        divisionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the Division Here"));
        divisionPanel.setLayout(new java.awt.GridBagLayout());

        divisionLabel.setText("Division Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        divisionPanel.add(divisionLabel, gridBagConstraints);

        divisionCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        divisionPanel.add(divisionCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(divisionPanel, gridBagConstraints);

        setSize(new java.awt.Dimension(631, 350));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sectionCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionCbxActionPerformed
        
    }//GEN-LAST:event_sectionCbxActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        this.getReport(reportName);
        
    }//GEN-LAST:event_previewBtnActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void departmentCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentCmbxActionPerformed
        sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE department = '"+departmentCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
    }//GEN-LAST:event_departmentCmbxActionPerformed

    private void departmentChckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentChckBoxActionPerformed
        fillCombOne();
    }//GEN-LAST:event_departmentChckBoxActionPerformed

    private void sectionChckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionChckBoxActionPerformed
        fillCombOne();
    }//GEN-LAST:event_sectionChckBoxActionPerformed

    private void divisionCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionCmbxActionPerformed
        if ((divisionChkBx.isSelected()) && (departmentChckBox.isSelected()) && (sectionChckBox.isSelected())) {
            departmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY department"));
            
            departmentCmbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        departmentChckBoxActionPerformed(evt);
                    }
                });
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' AND department = '"+departmentCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        } else if ((divisionChkBx.isSelected()) && (departmentChckBox.isSelected())) {
            departmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY department"));
            
            departmentCmbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        departmentChckBoxActionPerformed(evt);
                    }
                });
        } else if ((divisionChkBx.isSelected()) && (sectionChckBox.isSelected())) {
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        }        
    }//GEN-LAST:event_divisionCmbxActionPerformed

    private void divisionChkBxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionChkBxActionPerformed
        fillCombOne();
    }//GEN-LAST:event_divisionChkBxActionPerformed

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
            case 6: {
                if (divisionChkBx.isSelected() && departmentChckBox.isSelected() && sectionChckBox.isSelected()) {
                    System.out.println("Both the Department and Section have been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDivDepSecReportPdf policyOne = new com.afrisoftech.reports.LeaveBalanceDivDepSecReportPdf();
                
                    policyOne.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            divisionCmbx.getSelectedItem().toString(), departmentCmbx.getSelectedItem().toString(), 
                            sectionCbx.getSelectedItem().toString());

                    this.dispose();
                } else if (divisionChkBx.isSelected() && departmentChckBox.isSelected()) {
                    System.out.println("Both the Division and Department have been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDivDepReportPdf policyOne = new com.afrisoftech.reports.LeaveBalanceDivDepReportPdf();
                
                    policyOne.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            divisionCmbx.getSelectedItem().toString(), departmentCmbx.getSelectedItem().toString());

                    this.dispose();
                } else if (divisionChkBx.isSelected() && sectionChckBox.isSelected()) {
                    System.out.println("Both the Division and Section have been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDivSecReportPdf policyOne = new com.afrisoftech.reports.LeaveBalanceDivSecReportPdf();
                
                    policyOne.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            divisionCmbx.getSelectedItem().toString(), sectionCbx.getSelectedItem().toString());

                    this.dispose();
                }  else if (departmentChckBox.isSelected() && sectionChckBox.isSelected()) {
                    System.out.println("Both the Department and Section have been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDepSecReportPdf policyOne = new com.afrisoftech.reports.LeaveBalanceDepSecReportPdf();
                
                    policyOne.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            departmentCmbx.getSelectedItem().toString(), sectionCbx.getSelectedItem().toString());

                    this.dispose();
                } else if ((divisionChkBx.isSelected())) {
                    System.out.println("Only the Division has been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDivisionReportPdf policyTwo = new com.afrisoftech.reports.LeaveBalanceDivisionReportPdf();
                
                    policyTwo.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            divisionCmbx.getSelectedItem().toString());
                    
                    this.dispose();
                    
                }  else if ((departmentChckBox.isSelected())) {
                    System.out.println("Only the Department has been Selected.");
                    com.afrisoftech.reports.LeaveBalanceDepartmentReportPdf policyTwo = new com.afrisoftech.reports.LeaveBalanceDepartmentReportPdf();
                
                    policyTwo.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            departmentCmbx.getSelectedItem().toString());
                    
                    this.dispose();
                    
                }  else if ((sectionChckBox.isSelected())) {
                    System.out.println("Only the Section has been Selected.");
                    com.afrisoftech.reports.LeaveBalanceSectionReportPdf policyThree = new com.afrisoftech.reports.LeaveBalanceSectionReportPdf();
                
                    policyThree.LeaveBalanceDetailsPdf(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate(), 
                            sectionCbx.getSelectedItem().toString());
                    
                    this.dispose();
                    
                }
                
                
            }
        }
           
        
    }
    
    private void populateCmbox() {
        divisionCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT division FROM master_file "
                + "ORDER BY division"));
        divisionCmbx.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    departmentCmbxActionPerformed(evt);
                }
            }); 
        
        
        departmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                + "ORDER BY department"));
        departmentCmbx.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    departmentCmbxActionPerformed(evt);
                }
            });   
        
        sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "ORDER BY section"));
            
        sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sectionCbxActionPerformed(evt);
                }
            });
        
    }
    
    
    public void fillCombOne() {
        if ((divisionChkBx.isSelected()) && (departmentChckBox.isSelected()) && (sectionChckBox.isSelected())) {
            
            divisionPanel.setVisible(true);
            departmentPanel.setVisible(true);
            sectionPanel.setVisible(true);
            
            departmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY department"));
            
            departmentCmbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        departmentChckBoxActionPerformed(evt);
                    }
                });
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE department = '"+departmentCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        } else if ((divisionChkBx.isSelected()) && (departmentChckBox.isSelected())) {
            
            divisionPanel.setVisible(true);
            departmentPanel.setVisible(true);
            sectionPanel.setVisible(false);
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY department"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        } else if ((divisionChkBx.isSelected()) && (sectionChckBox.isSelected())) {
            
            divisionPanel.setVisible(true);
            departmentPanel.setVisible(false);
            sectionPanel.setVisible(true);
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE division = '"+divisionCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        } else if ((departmentChckBox.isSelected()) && (sectionChckBox.isSelected())) {
            
            divisionPanel.setVisible(false);
            departmentPanel.setVisible(true);
            sectionPanel.setVisible(true);
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "WHERE department = '"+departmentCmbx.getSelectedItem()+"' ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        } else if ((divisionChkBx.isSelected())){
            System.out.println("Division is Selected.");
            divisionPanel.setVisible(true);
            departmentPanel.setVisible(false);
            sectionPanel.setVisible(false);
        }   else if ((departmentChckBox.isSelected())){
            System.out.println("Department is Selected.");
            divisionPanel.setVisible(false);
            departmentPanel.setVisible(true);
            sectionPanel.setVisible(false);
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
            
        }  else if ((sectionChckBox.isSelected())){
            System.out.println("Section is Selected.");
            divisionPanel.setVisible(false);
            departmentPanel.setVisible(false);
            sectionPanel.setVisible(true);
            
            sectionCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT section FROM master_file "
                    + "ORDER BY section"));
            
            sectionCbx.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        sectionCbxActionPerformed(evt);
                    }
                });
        }  else if ((!divisionChkBx.isSelected()) && (!departmentChckBox.isSelected()) && (!sectionChckBox.isSelected())) {
            System.out.println("None is Selected.");
            divisionPanel.setVisible(false);
            departmentPanel.setVisible(false);
            sectionPanel.setVisible(false);
            
            previewBtn.enable(false);
        }
    }
    

    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel datePanel;
    private javax.swing.JCheckBox departmentChckBox;
    private javax.swing.JComboBox departmentCmbx;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JPanel departmentPanel;
    private javax.swing.JCheckBox divisionChkBx;
    private javax.swing.JComboBox divisionCmbx;
    private javax.swing.JLabel divisionLabel;
    private javax.swing.JPanel divisionPanel;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton previewBtn;
    private javax.swing.JComboBox sectionCbx;
    private javax.swing.JCheckBox sectionChckBox;
    private javax.swing.JLabel sectionLbl;
    private javax.swing.JPanel sectionPanel;
    private javax.swing.JLabel wardNutNameBDLbl;
    private javax.swing.JLabel wardNutNameEDLbl;
    private javax.swing.JLabel wardNutResultsLbl;
    // End of variables declaration//GEN-END:variables
}
