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
public class HosLabPanel extends javax.swing.JDialog {
    
    int reportName;
    
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HosLabPanel(java.awt.Frame parent, boolean modal, int repName, java.sql.Connection connectDb) {
        
        super(parent, modal);
        
        reportName = repName;
        
        connectDB = connectDb;
        //      dateStartEnd = new java.util.Vector(1,1);
        
        initComponents();
        
        //       return dateStartEnd;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Reporting data filter dialog");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setText("From Lab No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("To Lab No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.add(jTextField1, new java.awt.GridBagConstraints());

        jTextField2.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField2, gridBagConstraints);

        jTextField3.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 10, 20);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Select Lab Results Here");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-360)/2, (screenSize.height-210)/2, 360, 210);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        // Add your handling code here:
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
            
            
            
            
 /*
             case 17:
  
            {
                com.afrisoftech.sacco.VouchersApprListPdf policy = new com.afrisoftech.sacco.VouchersApprListPdf();
  
                policy.VouchersApprListPdf(connectDB, this.beginDateSpinner.getValue().toString(), this.endDateSpinner.getValue().toString());
  
               this.dispose();
  
            } break;
  
  
            case 56:
                
            {
                com.afrisoftech.reports.FinalInPatientlnvPdf policy = new com.afrisoftech.reports.FinalInPatientlnvPdf();
                
                policy.FinalInPatientInvPdf(connectDB, this.jTextField2.getText());
                
                this.dispose();
                
            } break;
  */
            
            //  default :  ;
            
            case 516:
                
            {
               // com.afrisoftech.laboratory.LabDescReportPdf policy = new com.afrisoftech.laboratory.LabDescReportPdf();
                com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();

               // com.afrisoftech.reports.PatientLabResultsPdf
              //  policy.PatientLabResultsPdf(connectDB, this.jTextField2.getText(), this.jTextField3.getText());
                
                this.dispose();
                
            } break;
            
        /*     case 171:
                
            {
                com.afrisoftech.reports.FinalDescInPatientlnvPdf policy = new com.afrisoftech.reports.FinalDescInPatientlnvPdf();
                
                policy.FinalDescInPatientInvPdf(connectDB, this.jTextField2.getText());
                
                this.dispose();
                
            } break;
            */
            
            default :  ;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
}
