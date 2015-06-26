/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */

package com.afrisoftech.lib;

import static com.afrisoftech.hospital.HospitalMain.connectDB;

/**
 *
 * @author  root
 */
public class HosBudgetPanel extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public HosBudgetPanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
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
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField36 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setMnemonic('p');
        jButton1.setText("Preview");
        jButton1.setSelected(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        jButton11.setMnemonic('o');
        jButton11.setText("Send to printer");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Year here", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Financial Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(354, 212));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        this.getReport(reportName, true);        // Add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
    
    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        jSearchDialog1.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed
    
    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        jTextField36.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
        
        jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked
    
    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct financial_year from ac_aie_allocation where financial_year ILIKE '%"+jTextField1111.getText().toString()+"%'"));
        jSearchTable1.setShowHorizontalLines(false);
        jSearchScrollPane1.setViewportView(jSearchTable1);
        
                /*    searchRowSet1.execute("select (voucher_no) as scheme from ac_cash_book where voucher_no ILIKE '"+jTextField1111.getText().toString()+"%' and transaction_type ='Payment' ORDER BY voucher_no");
                 
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                        new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Acc no.", false),
                       // new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
                 
                    }));
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);
                 
                 
                } catch(java.sql.SQLException sqlExec) {
                 
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                 
                }
                 */
        // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate
    
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchButton11Clicked();        // Add your handling code here:
        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed
    private void searchButton11Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField36.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
        
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.getReport(reportName, false);
        
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
    
   // public void getReport(int reportName) {
        
   //     switch (reportName) {
         public void getReport(int reportName, boolean printState) {
        
        switch (reportName) {
            
     
            case 20:
                
            {
                com.afrisoftech.votebook.BudgetPdf policy = new com.afrisoftech.votebook.BudgetPdf();

                policy.BudgetPdf(connectDB,jTextField36.getText());
                
                
                  this.dispose();
                
            } break;
            
            case 21:    
            {
                com.afrisoftech.votebook.BudgetQuaterlyBalancePdf policy = new com.afrisoftech.votebook.BudgetQuaterlyBalancePdf();

                policy.BudgetQuaterlyBalancePdf(connectDB,jTextField36.getText());
                
                  this.dispose();
            }break;
                case 22:    
            {
                com.afrisoftech.votebook.BudgetBalancePdf policy = new com.afrisoftech.votebook.BudgetBalancePdf();

                policy.BudgetBalancePdf(connectDB,jTextField36.getText());
                
                  this.dispose();
            }break;
                
            default :  ;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton51;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
    
}
