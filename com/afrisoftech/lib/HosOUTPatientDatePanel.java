/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class HosOUTPatientDatePanel extends javax.swing.JDialog {

    int reportName;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    java.sql.Connection connectDB = null;

    java.util.Vector dateStartEnd = null;

    javax.swing.JSpinner beginDateSpinner = null;

    javax.swing.JSpinner endDateSpinner = null;

    /**
     * Creates new form DatePanel
     */
    public HosOUTPatientDatePanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {

        super(parent, modal);

        reportName = repName;

        connectDB = connectDb;

        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);

        initComponents();
        if (reportName == 20012 || reportName == 202 || reportName == 203) {
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            invoicesCMBX.setVisible(true);
            payerCMBX.setVisible(true);
        } else {
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            invoicesCMBX.setVisible(false);
            payerCMBX.setVisible(false);
        }
        //       return dateStartEnd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        patientSearchdialog = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        patientSearchTxt = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        patientSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton41 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        actionPanel = new javax.swing.JPanel();
        closeFormBtn = new javax.swing.JButton();
        previewBtn = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        spacerLbl = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        patNoTxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        opChkbx = new javax.swing.JCheckBox();
        ipChkbx = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        visitIDTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        invoicesCMBX = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        payerCMBX = new javax.swing.JComboBox();

        patientSearchdialog.setModal(true);
        patientSearchdialog.setUndecorated(true);
        patientSearchdialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        patientSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                patientSearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel1.add(patientSearchTxt, gridBagConstraints);

        patientSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        patientSearchTable.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        patientSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientSearchTableMouseClicked(evt);
            }
        });
        jSearchScrollPane1.setViewportView(patientSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel1.add(jSearchScrollPane1, gridBagConstraints);

        jButton41.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton41, gridBagConstraints);

        jButton51.setText("Close");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        patientSearchdialog.getContentPane().add(jSearchPanel1, gridBagConstraints);

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        actionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        actionPanel.setLayout(new java.awt.GridBagLayout());

        closeFormBtn.setText("Cancel");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(closeFormBtn, gridBagConstraints);

        previewBtn.setText("Preview report");
        previewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(previewBtn, gridBagConstraints);

        printBtn.setMnemonic('o');
        printBtn.setText("Send to printer");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(printBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(spacerLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(actionPanel, gridBagConstraints);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Patient File No. here", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N
        mainPanel.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("I/P No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 0);
        mainPanel.add(jLabel4, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        patNoTxt.setEditable(false);
        patNoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patNoTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(patNoTxt, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton.setToolTipText("Search");
        searchButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(jPanel6, gridBagConstraints);

        jLabel1.setText("From Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 0);
        mainPanel.add(jLabel1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel1.add(datePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(jPanel1, gridBagConstraints);

        buttonGroup1.add(opChkbx);
        opChkbx.setSelected(true);
        opChkbx.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(opChkbx, gridBagConstraints);

        buttonGroup1.add(ipChkbx);
        ipChkbx.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(ipChkbx, gridBagConstraints);

        jLabel2.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 0);
        mainPanel.add(jLabel2, gridBagConstraints);

        jTextField1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        mainPanel.add(jTextField1, gridBagConstraints);

        jLabel3.setText("Visit Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(jLabel3, gridBagConstraints);

        visitIDTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        mainPanel.add(visitIDTxt, gridBagConstraints);

        jLabel5.setText("Invoice No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        mainPanel.add(jLabel5, gridBagConstraints);

        invoicesCMBX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicesCMBXActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(invoicesCMBX, gridBagConstraints);

        jLabel6.setText("Scheme");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        mainPanel.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(payerCMBX, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(mainPanel, gridBagConstraints);

        setSize(new java.awt.Dimension(464, 272));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        this.getReport(reportName, true);        // Add your handling code here:
    }//GEN-LAST:event_printBtnActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        patientSearchdialog.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void patientSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientSearchTableMouseClicked

        java.text.DateFormat df = java.text.DateFormat.getDateInstance();

        java.text.SimpleDateFormat sdf = (java.text.SimpleDateFormat) df;

        sdf.applyPattern("yyyy-MM-dd");

        patNoTxt.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 0).toString());
        jTextField1.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 1).toString());
        visitIDTxt.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 3).toString());
        invoicesCMBX.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, ""
                + "SELECT distinct  invoice_no  FROM hp_patient_card where patient_no='" + patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 0).toString().trim() + "'"
                + "  group by invoice_no order by 1 desc"));
//        invoicesCMBX.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, ""
//                + "SELECT distinct  invoice_no  FROM hp_patient_card where patient_no='" + jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString().trim() + "'"
//                + "  group by invoice_no having sum(debit-credit)<=0 order by 1 desc"));

        try {
            datePicker1.setDate(sdf.parse(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 2).toString()));
        } catch (java.text.ParseException pe) {
            javax.swing.JOptionPane.showMessageDialog(this, pe.getMessage());
        }

        patientSearchdialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_patientSearchTableMouseClicked

    private void patientSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_patientSearchTxtCaretUpdate
        if (this.patientSearchTxt.getCaretPosition() < 5) {
            System.out.print("Nothing");
        } else {

            if (this.opChkbx.isSelected()) {
                if (reportName == 20012) {
                    patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT trim(patient_no) as patient_no, funsoft_get_patient_name(patient_no) as name,date::date as visit_date, patient_race as unit_no from hp_patient_register where( patient_no ILIKE '%" + patientSearchTxt.getText().toString() + "%' or patient_race ilike '%" + patientSearchTxt.getText().toString() + "%'  ) "
                            + " AND patient_no  IN  (SELECT DISTINCT admission_no FROM ac_debtors WHERE invoice_no IS NOT NULL) ORDER BY 1,2"));

                    patientSearchTable.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(patientSearchTable);
                }
                /*
                 } catch(java.sql.SQLException sqlExec) {
                 
                 javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                 
                 }
                 */
            } else {
                if (reportName == 20012) {
                    patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT DISTINCT trim(patient_no) as patient_no, funsoft_get_patient_name(patient_no) as name,date as visit_date, patient_race as unit_no from hp_patient_register where funsoft_get_patient_name ILIKE '%" + patientSearchTxt.getText().toString() + "%' "
                            + " AND patient_no  IN  (SELECT DISTINCT admission_no FROM ac_debtors WHERE invoice_no IS NOT NULL) ORDER BY 1,2"));

                    patientSearchTable.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(patientSearchTable);

                }
            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_patientSearchTxtCaretUpdate

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (!this.opChkbx.isSelected() && !(this.ipChkbx.isSelected())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select Patient Number or Name", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {
            searchButton11Clicked();
        }
        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed
    private void searchButton11Clicked() {

        System.out.println("Showing dialog");

        patientSearchdialog.dispose();

        if (this.opChkbx.isSelected()) {
            java.awt.Point point = this.patNoTxt.getLocationOnScreen();
            patientSearchdialog.setLocation(point);
        } else {

            java.awt.Point point = this.jTextField1.getLocationOnScreen();
            patientSearchdialog.setLocation(point);
        }
        patientSearchdialog.setSize(400, 200);

        patientSearchdialog.setVisible(true);

    }
    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed

        this.getReport(reportName, false);

        // Add your handling code here:
    }//GEN-LAST:event_previewBtnActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void patNoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patNoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patNoTxtActionPerformed

    private void invoicesCMBXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicesCMBXActionPerformed
        payerCMBX.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, ""
                + "SELECT distinct  dealer  FROM ac_debtors where  invoice_no='" + invoicesCMBX.getSelectedItem().toString().trim() + "' group by 1 having sum(debit-credit)>0 "
                + " UNION "
                + "SELECT distinct  service  FROM hp_patient_card where invoice_no='" + invoicesCMBX.getSelectedItem().toString().trim() + "'  and transaction_type ilike 'Receipt' "
                + "order by 1 asc"));
    }//GEN-LAST:event_invoicesCMBXActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //    new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }

    public java.util.Vector getBeginEndDates() {

        dateStartEnd = new java.util.Vector(1, 1);

        dateStartEnd.addElement(beginDateSpinner.getValue().toString());

        dateStartEnd.addElement(endDateSpinner.getValue().toString());

        return dateStartEnd;

    }

    public void getReport(int reportName, boolean printState) {

        switch (reportName) {

            case 29: {
                com.afrisoftech.reports.InpatientDiagPdf policy = new com.afrisoftech.reports.InpatientDiagPdf();

                policy.InpatientDiagPdf(connectDB, patNoTxt.getText());

                this.dispose();

            }
            break;

            case 16: {
                com.afrisoftech.reports.PatientBillPdf policy = new com.afrisoftech.reports.PatientBillPdf();

                policy.PatientBillPdf(connectDB, patNoTxt.getText(), this.datePicker1.getDate(), visitIDTxt.getText());

                //this.dispose();
            }
            break;
            case 163: {
                com.afrisoftech.reports.FinalInterimPatientlnvSummPdf policy = new com.afrisoftech.reports.FinalInterimPatientlnvSummPdf();

                policy.FinalInterimPatientlnvSummPdf(connectDB, visitIDTxt.getText(), this.patNoTxt.getText());

                // this.dispose();
            }
            break;
            case 200: {
                com.afrisoftech.reports.StatementPatientPdf policy = new com.afrisoftech.reports.StatementPatientPdf();

                policy.StatementPatientPdf(connectDB, visitIDTxt.getText());

                // this.dispose();
            }
            break;
            case 20012: {
                com.afrisoftech.reports.UHCNHIFClaimCardPdf policy = new com.afrisoftech.reports.UHCNHIFClaimCardPdf();

                policy.UHCNHIFClaimCardPdf(connectDB, invoicesCMBX.getSelectedItem().toString(), invoicesCMBX.getSelectedItem().toString());

                // this.dispose();
            }
            break;

            case 201: {
                com.afrisoftech.reports.StatementPatientInvoicePdf policy = new com.afrisoftech.reports.StatementPatientInvoicePdf();

                policy.StatementPatientInvoicePdf(connectDB, visitIDTxt.getText().trim(), invoicesCMBX.getSelectedItem().toString().trim(), payerCMBX.getSelectedItem().toString().trim());

                // this.dispose();
            }
            break;

            case 202: {
                com.afrisoftech.reports.DetailedPatientFinalInvpdf policy = new com.afrisoftech.reports.DetailedPatientFinalInvpdf();

                policy.DetailedPatientFinalInvpdf(connectDB, visitIDTxt.getText().trim(), invoicesCMBX.getSelectedItem().toString().trim(), payerCMBX.getSelectedItem().toString().trim());

                // this.dispose();
            }
            break;

            case 203: {
                com.afrisoftech.reports.ItemisedPatientFinalInvoicePdf policy = new com.afrisoftech.reports.ItemisedPatientFinalInvoicePdf();

                policy.ItemisedPatientFinalInvoicePdf(connectDB, visitIDTxt.getText().trim(), invoicesCMBX.getSelectedItem().toString().trim(), payerCMBX.getSelectedItem().toString().trim());

                // this.dispose();
            }
            break;

            case 5272: {
                com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf policy = new com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf();

                policy.FinalDescInPatientIntmlnvPdf(connectDB, visitIDTxt.getText(), patNoTxt.getText());

                // this.dispose();
            }
            break;
            case 5275: {
                com.afrisoftech.reports.InPatientIntmDatelnvPdf policy = new com.afrisoftech.reports.InPatientIntmDatelnvPdf();

                policy.InPatientIntmDatelnvPdf(connectDB, visitIDTxt.getText(), patNoTxt.getText());

                this.dispose();

            }
            break;

            case 52752: {
                com.afrisoftech.reports.InPatientIntmExDatelnvPdf policy = new com.afrisoftech.reports.InPatientIntmExDatelnvPdf();

                policy.InPatientIntmExDatelnvPdf(connectDB, visitIDTxt.getText(), patNoTxt.getText());

                this.dispose();

            }
            break;

            case 52751: {
                com.afrisoftech.reports.InPatientIntmDateBflnvPdf policy = new com.afrisoftech.reports.InPatientIntmDateBflnvPdf();

                policy.InPatientIntmDateBflnvPdf(connectDB, visitIDTxt.getText(), this.datePicker1.getDate(), patNoTxt.getText());

                this.dispose();

            }
            break;

            case 520: {
                com.afrisoftech.txtreports.InterimPatientInvTxtReport policy = new com.afrisoftech.txtreports.InterimPatientInvTxtReport(connectDB, patNoTxt.getText(), this.datePicker1.getDate(), printState, visitIDTxt.getText());

                //  policy.PatientStatementPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),jComboBox1.getSelectedItem().toString());
                this.dispose();

            }
            break;

            /*   case 7:
           
             {
             com.afrisoftech.reports.ReceiptsperPatientPdf policy = new com.afrisoftech.reports.ReceiptsperPatientPdf();
           
             policy.ReceiptsperPatientPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),jComboBox1.getSelectedItem().toString());
           
           
             this.dispose();
           
             } break;
             case 10:
           
             {
             com.afrisoftech.reports.DetailedPatientStatementPdf policy = new com.afrisoftech.reports.DetailedPatientStatementPdf();
           
             policy.DetailedPatientStatementPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),this.jComboBox1.getSelectedItem().toString());
           
             this.dispose();
           
             }
             */
            default:  ;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton closeFormBtn;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JComboBox invoicesCMBX;
    private javax.swing.JCheckBox ipChkbx;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton51;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JCheckBox opChkbx;
    private javax.swing.JTextField patNoTxt;
    private javax.swing.JTable patientSearchTable;
    private javax.swing.JTextField patientSearchTxt;
    private javax.swing.JDialog patientSearchdialog;
    private javax.swing.JComboBox payerCMBX;
    private javax.swing.JButton previewBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel spacerLbl;
    private javax.swing.JTextField visitIDTxt;
    // End of variables declaration//GEN-END:variables

}