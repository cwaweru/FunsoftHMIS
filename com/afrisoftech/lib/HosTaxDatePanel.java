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
public class HosTaxDatePanel extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HosTaxDatePanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();

        setTitle("Reporting data filter dialog");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.add(jTextField1, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(datePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Dates here.."));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT description from deductions_allowances order by description"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jComboBox1, gridBagConstraints);

        jLabel41.setText("Category Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel41, gridBagConstraints);

        jComboBox11.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT company_name from payroll_comsetup order by company_name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jComboBox11, gridBagConstraints);

        jCheckBox1.setText("Ignore Categ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-360)/2, (screenSize.height-246)/2, 360, 246);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        dispose();
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
              
            
            
            case 10:
                
            {
                com.afrisoftech.hospayroll.TaxesPdf policy = new com.afrisoftech.hospayroll.TaxesPdf();
                
                policy.TaxesPdf(connectDB, this.datePicker1.getDate().toLocaleString(), this.datePicker2.getDate().toLocaleString(),jComboBox1.getSelectedItem().toString(),jComboBox11.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
                 case 26:
                
            {
                com.afrisoftech.hospayroll.ApprovedDedPdf policy = new com.afrisoftech.hospayroll.ApprovedDedPdf();
                
                policy.ApprovedDedPdf(connectDB, this.datePicker1.getDate().toLocaleString(), this.datePicker2.getDate().toLocaleString(),jComboBox1.getSelectedItem().toString(),jComboBox11.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
              case 30:
                
            {
                com.afrisoftech.hospayroll.ContriDedComparPdf policy = new com.afrisoftech.hospayroll.ContriDedComparPdf();
                
                policy.ContriDedComparPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString(),jComboBox11.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            
            
            case 2230:
                
            {
                 com.afrisoftech.hospayroll.DeductionsTxt policy = new com.afrisoftech.hospayroll.DeductionsTxt(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString(),jComboBox11.getSelectedItem().toString());
               
                
            } break;

             case 2227:

            {
                com.afrisoftech.hospayroll.DeductionsAnalysisPdf policy = new com.afrisoftech.hospayroll.DeductionsAnalysisPdf();

                policy.DeductionsAnalysisPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());


                this.dispose();

            } break;

              case 2235:

            {

                if(this.jCheckBox1.isSelected()){
                com.afrisoftech.hospayroll.LoanSummDedPdf policy = new com.afrisoftech.hospayroll.LoanSummDedPdf();

                policy.LoanSummDedPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                }else{
                   com.afrisoftech.hospayroll.LoanDedPdf policy = new com.afrisoftech.hospayroll.LoanDedPdf();

                policy.LoanDedPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString(),jComboBox11.getSelectedItem().toString());

                }

                //this.dispose();

            } break;
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
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
