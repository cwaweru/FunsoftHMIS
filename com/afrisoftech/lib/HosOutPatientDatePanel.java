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
public class HosOutPatientDatePanel extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HosOutPatientDatePanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
        super(parent, modal);
        
        reportName = repName;
        
        connectDB = connectDb;
        
        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);
        
        initComponents();
        
        //       return dateStartEnd;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField1111 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new com.afrisoftech.dbadmin.JTable();
        jButton41 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField36 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jLabel22 = new javax.swing.JLabel();
        datePicker21 = new com.afrisoftech.lib.DatePicker();

        jSearchDialog1.setModal(true);
        jSearchDialog1.setUndecorated(true);
        jSearchDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        jTextField1111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel1.add(jTextField1111, gridBagConstraints);

        jSearchTable1.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable1.setShowHorizontalLines(false);
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
        jSearchTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable1MouseClicked(evt);
            }
        });
        jSearchScrollPane1.setViewportView(jSearchTable1);

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
        jSearchDialog1.getContentPane().add(jSearchPanel1, gridBagConstraints);

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setMnemonic('c');
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setMnemonic('O');
        jButton1.setText("Preview Interim Invoice");
        jButton1.setSelected(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select patient  here", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("O/P No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jTextField36.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jTextField36, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton.setToolTipText("Search Patient's Name");
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
        jPanel4.add(jPanel6, gridBagConstraints);

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jCheckBox2, gridBagConstraints);

        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jCheckBox1, gridBagConstraints);

        jLabel2.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel2, gridBagConstraints);

        jTextField1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jTextField1, gridBagConstraints);

        jLabel21.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jLabel21, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(datePicker2, gridBagConstraints);

        jLabel22.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jLabel22, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(datePicker21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(563, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        jSearchDialog1.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed
    
    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        jTextField36.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
        
        jTextField1.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1).toString());
        
        jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked
    
    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        if(this.jTextField1111.getCaretPosition() < 2){
            System.out.print("Nothing");
        }else{
            if(this.jCheckBox1.isSelected()){
                jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_patient_register where patient_no ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name"));
                
       /* try {
                    searchRowSet1.execute("select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_patient_register where patient_no ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name");
        
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                        new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Patient_no", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
        
                    }));
        */
                jSearchTable1.setShowHorizontalLines(false);
                jSearchScrollPane1.setViewportView(jSearchTable1);
                
                
             /*   } catch(java.sql.SQLException sqlExec) {
              
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
              
                }
              */
            }else{
                
                jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_patient_register where second_name||' '||first_name||' '||last_name ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name"));
                
     /* try {
                    searchRowSet1.execute("select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_patient_register where second_name||' '||first_name||' '||last_name ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name");
      
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                        new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Patient_no", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
      
                    }));
      */
                jSearchTable1.setShowHorizontalLines(false);
                jSearchScrollPane1.setViewportView(jSearchTable1);
                
                
              /*  } catch(java.sql.SQLException sqlExec) {
               
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
               
                }
               */
            }
            
            
     /*   try {
                    searchRowSet1.execute("select trim(patient_no) as scheme,first_name||' '||second_name||' '||last_name as name from hp_patient_register where first_name||' '||second_name||' '||last_name ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY name");
      
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                        new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Patient_no", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
      
                    }));
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);
      
      
                } catch(java.sql.SQLException sqlExec) {
      
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
      
                }
      */
        }      // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate
    private void searchButton11Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        if(this.jCheckBox1.isSelected()){
            java.awt.Point point = this.jTextField36.getLocationOnScreen();
            jSearchDialog1.setLocation(point);
        }else{
            
            java.awt.Point point = this.jTextField1.getLocationOnScreen();
            jSearchDialog1.setLocation(point);
        }
        jSearchDialog1.setSize(600,200);
        
        
        jSearchDialog1.setVisible(true);
        
        
    }
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if(!this.jCheckBox1.isSelected() && !(this.jCheckBox2.isSelected())){
            javax.swing.JOptionPane.showMessageDialog(this,"Select Patient No. OR Name","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
        }else{
            searchButton11Clicked();
        }
        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.getReport(reportName);
        
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //    new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    public java.util.Vector getBeginEndDates() {
        
        dateStartEnd = new java.util.Vector(1,1);
        
        dateStartEnd.addElement(beginDateSpinner.getValue().toString());
        
        dateStartEnd.addElement(endDateSpinner.getValue().toString());
        
        return dateStartEnd;
        
    }
    
    public void getReport(int reportName) {
        
        switch (reportName) {
            
            
            
          /*  case 29:
           
            {
                com.afrisoftech.reports.InpatientDiagPdf policy = new com.afrisoftech.reports.InpatientDiagPdf();
           
                policy.InpatientDiagPdf(connectDB,jTextField36.getText());
           
           
                this.dispose();
           
            } break;
           */
            case 38:
                
            {
                com.afrisoftech.reports.OutPatientBillPdf policy = new com.afrisoftech.reports.OutPatientBillPdf();
                
                policy.OutPatientBillPdf(connectDB,datePicker2.getDate(),datePicker21.getDate(),jTextField36.getText());
                
                
                this.dispose();
                
            } break;
            /*
             
            case 12:
             
            {
                com.afrisoftech.reports.PatientStatementPdf policy = new com.afrisoftech.reports.PatientStatementPdf();
             
                policy.PatientStatementPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),jComboBox1.getSelectedItem().toString());
             
             
                this.dispose();
             
            } break;
             
             case 7:
             
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
            default :  ;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private com.afrisoftech.lib.DatePicker datePicker21;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton51;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
    
}
