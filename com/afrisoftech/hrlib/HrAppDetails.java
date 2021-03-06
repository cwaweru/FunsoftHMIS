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
public class HrAppDetails extends javax.swing.JDialog {
    
    int reportName;
    String SEARCH_BY="";
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HrAppDetails(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
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

        try {
            crset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        searchtext = new javax.swing.JTextField();

        crset1.setConnectionSource(pConnDB);

        setTitle("Staff File Report");
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
        jButton1.setText("Get Applicant Information");
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Get Applicant File By"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        searchLabel.setText("Enter Applicant Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(searchLabel, gridBagConstraints);

        jCheckBox1.setText("Applicant ID");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setText("National ID");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 2.0;
        jPanel4.add(jCheckBox2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        jPanel4.add(searchtext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(388, 218));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.dispose();            // Add your handling code here:
        }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int report=0;
        if(SEARCH_BY.equals("NATIONAL_ID")){
           report=1; 
        }else{
            report=2;
        }
        this.getReport(report);
        
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        searchLabel.setText("Enter Applicant ID Number:");
  jCheckBox2.setSelected(false);
        SEARCH_BY="STAFF_ID";
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
         searchLabel.setText("Enter National ID Number:");
         jCheckBox1.setSelected(false);
        SEARCH_BY="NATIONAL_ID";
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    
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
                com.afrisoftech.hr.AppFile policy = new com.afrisoftech.hr.AppFile();
                policy.SEARCH_BY=SEARCH_BY;
                policy.SearchText=searchtext.getText();
                policy.AppFile(connectDB,searchtext.getText());
                
                
                this.dispose();
                
            } break;
            
            case 2:
                
            {
                 com.afrisoftech.hr.AppFile policy = new com.afrisoftech.hr.AppFile();
                policy.SEARCH_BY=SEARCH_BY;
                policy.SearchText=searchtext.getText();
                policy.AppFile(connectDB,searchtext.getText());
               //    policy.DepartmentStaffPdf(connectDB, jComboBox1.getSelectedItem().toString());
                
                
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchtext;
    // End of variables declaration//GEN-END:variables
    
}
