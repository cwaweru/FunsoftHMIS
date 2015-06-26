/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */

package com.afrisoftech.hrlib;

/**
 *
 * @author  root
 */
public class HrAppSelbyGen extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HrAppSelbyGen(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
        super(parent, modal);
        
        reportName = repName;
        
        connectDB = connectDb;
        
        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);
        
        initComponents();
        
        jPanel4.setBorder(new javax.swing.border.TitledBorder("Select Education Level"));
        jLabel4.setText("Get Applicants by education Level");
          setTitle("Applicants");
           jComboBox1.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (crset1, "name", null, null, null));
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

        try {
            crset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        crset1.setConnectionSource(pConnDB);

        setTitle("Department Selection ");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setMnemonic('n');
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

        jButton1.setMnemonic('o');
        jButton1.setText("Ok");
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
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Department here"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jComboBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(366, 218));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.dispose();            // Add your handling code here:
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
    
   /* public java.util.Vector getBeginEndDates() {
        
        dateStartEnd = new java.util.Vector(1,1);
        
        dateStartEnd.addElement(beginDateSpinner.getValue().toString());
        
        dateStartEnd.addElement(endDateSpinner.getValue().toString());
        
        return dateStartEnd;
        
    }*/
    
    public void getReport(int reportName) {
        
        switch (reportName) {
              
            
            
            case 1:
                
            {
                com.afrisoftech.hr.DepartmentStaffPdf policy = new com.afrisoftech.hr.DepartmentStaffPdf();
                
                policy.DepartmentStaffPdf(connectDB, jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 2:
                
            {
                com.afrisoftech.hr.AppListbyQual policy = new com.afrisoftech.hr.AppListbyQual();
                
                   policy.DepartmentStaffPdf(connectDB, jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            /*
            case 32:
                
            {
                com.afrisoftech.hospital.DetailedDepartmentRevPdf policy = new com.afrisoftech.hospital.DetailedDepartmentRevPdf();
                
                policy.DetailedDepartmentRevPdf(connectDB,this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            case 74:
                
            {
                com.afrisoftech.hospital.IpRevBrdwnPdf policy = new com.afrisoftech.hospital.IpRevBrdwnPdf();
                
                policy.IpRevBrdwnPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 75:
                
            {
                com.afrisoftech.hospital.OpRevBrdwnPdf policy = new com.afrisoftech.hospital.OpRevBrdwnPdf();
                
                policy.OpRevBrdwnPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
             case 88:
                
            {
                com.afrisoftech.hospital.DepartmtPerfomRepPdf policy = new com.afrisoftech.hospital.DepartmtPerfomRepPdf();
                
                policy.DepartmtPerfomRepPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;*/
      /*      
            case 10:
                
            {
                com.afrisoftech.sacco.MemberStatementPdf policy = new com.afrisoftech.sacco.MemberStatementPdf();
                
                policy.MemberStatementPdf(connectDB, this.beginDateSpinner.getValue().toString(), this.endDateSpinner.getValue().toString(),this.jComboBox1.getSelectedItem().toString());
                
                this.dispose();
                
            }*/
            default :  ;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.netbeans.lib.sql.NBCachedRowSet crset1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
    
}
