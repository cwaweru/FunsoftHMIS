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
public class HosStoresDatePanel_1 extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HosStoresDatePanel_1(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Reporting data filter dialog");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Store here"));
        jLabel4.setText("Store Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT store_name from st_stores order by store_name"));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jComboBox1, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Dates"));
        jLabel1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(datePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-391)/2, (screenSize.height-245)/2, 391, 245);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            // if(this.jCheckBox1.isSelected()){
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select distinct gl_account from pb_operating_parameters where main_service  ilike '"+this.jComboBox1.getSelectedItem()+"'");
            while (rset1.next()){
                jTextField1.setText(rset1.getObject(1).toString());
                //jTextField4.setText(rset1.getObject(2).toString());
            }
            
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        } // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
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
            
              
            case 13:
                
            {
                com.afrisoftech.hospinventory.SubStoresAuditPdf policy = new com.afrisoftech.hospinventory.SubStoresAuditPdf();
                
                policy.SubStoresAuditPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
         
           /* case 8:
                
            {
                com.afrisoftech.hospinventory.StoresBalPdf policy = new com.afrisoftech.hospinventory.StoresBalPdf();
                
                policy.StoresBalPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            /*
            case 12:
                
            {
                com.afrisoftech.hospinventory.IssuedItemsPerStorePdf policy = new com.afrisoftech.hospinventory.IssuedItemsPerStorePdf();
                
                policy.IssuedItemsPerStorePdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            
            case 11:
                
            {
                 
                com.afrisoftech.reports.SalesReportPdf policy = new com.afrisoftech.reports.SalesReportPdf();
                
                policy.SalesReportPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString(),jTextField1.getText());
                
                this.dispose();
                
            } break;
            
            case 57:
                
            {
                
                com.afrisoftech.reports.SalesReportPdf policy = new com.afrisoftech.reports.SalesReportPdf();
                
                policy.SalesReportPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString(),jTextField1.getText());
                
                this.dispose();
                
            } break;
            
            case 26:
                
            {
                com.afrisoftech.hospinventory.StoresStockItemsPdf policy = new com.afrisoftech.hospinventory.StoresStockItemsPdf();
                
                policy.StoresStockItemsPdf(connectDB, jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
          
          
            
            case 94:
                
            {
                com.afrisoftech.hospinventory.PrescPerPatPdf policy = new com.afrisoftech.hospinventory.PrescPerPatPdf();
                
                policy.PrescPerPatPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 160:
                
            {
                com.afrisoftech.hospinventory.TransfersPerStorePdf policy = new com.afrisoftech.hospinventory.TransfersPerStorePdf();
                
                policy.TransfersPerStorePdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 165:
                
            {
                com.afrisoftech.hospinventory.StatisticalSalesPerUserPdf policy = new com.afrisoftech.hospinventory.StatisticalSalesPerUserPdf();
                
                policy.StatisticalSalesPerUserPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 5273 :
                
            {
                com.afrisoftech.hospinventory.WardsCreditTransfersPdf policy = new com.afrisoftech.hospinventory.WardsCreditTransfersPdf();
                
                policy.WardsCreditTransfersPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
           */ 
            case 200:
                
            {
                com.afrisoftech.hospinventory.PriceListPdf policy = new com.afrisoftech.hospinventory.PriceListPdf();
                
                policy.PriceListPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;

            case 202:

            {
                com.afrisoftech.hospinventory.StockRecSchPerStorePdf policy = new com.afrisoftech.hospinventory.StockRecSchPerStorePdf();

                policy.StockRecSchPerStorePdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());


                this.dispose();

            } break;
         /*   
            case 201:
                
            {
                com.afrisoftech.hospinventory.PrescriptionFeePdf policy = new com.afrisoftech.hospinventory.PrescriptionFeePdf();
                
                policy.PrescriptionFeePdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            
            case 215:
                
            {
                com.afrisoftech.hospinventory.AnalysedReqPerDepPdf policy = new com.afrisoftech.hospinventory.AnalysedReqPerDepPdf();
                
                policy.AnalysedReqPerDepPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            
            case 220:
                
            {
                com.afrisoftech.hospinventory.WeeklyStoresDrugMvtPdf policy = new com.afrisoftech.hospinventory.WeeklyStoresDrugMvtPdf();
                
                policy.WeeklyStoresDrugMvtPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            case 221:
                
            {
                com.afrisoftech.hospinventory.MonthlyStoresDrugMvtPdf policy = new com.afrisoftech.hospinventory.MonthlyStoresDrugMvtPdf();
                
                policy.MonthlyStoresDrugMvtPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 238:
                
            {
                com.afrisoftech.hospinventory.PendingReqPerDepPdf policy = new com.afrisoftech.hospinventory.PendingReqPerDepPdf();
                
                policy.PendingReqPerDepPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            case 240:
                
            {
                com.afrisoftech.hospinventory.SubStoresCostPdf policy = new com.afrisoftech.hospinventory.SubStoresCostPdf();
                
                policy.SubStoresCostPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            case 516:
                
            {
                com.afrisoftech.hospinventory.ItemsDeuForOrderPdf policy = new com.afrisoftech.hospinventory.ItemsDeuForOrderPdf();
                
                policy.ItemsDeuForOrderPdf(connectDB, jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
            
            
        
            
            case 519:
                
            {
                com.afrisoftech.hospinventory.DetailedPrescPerCategoryPdf policy = new com.afrisoftech.hospinventory.DetailedPrescPerCategoryPdf();
                
                policy.DetailedPrescPerCategoryPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jComboBox1.getSelectedItem().toString());
                
                
                this.dispose();
                
            } break;
          
            case 5271:
                
            {
                
                com.afrisoftech.hospinventory.StockBalTxt policy = new com.afrisoftech.hospinventory.StockBalTxt(connectDB,jComboBox1.getSelectedItem().toString(), this.datePicker2.getDate());
                
                this.dispose();
                
            } break;
   */
            default :  ;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
