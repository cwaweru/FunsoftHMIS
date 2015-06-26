/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package biz.systempartners.reports;

import com.afrisoftech.hospinventory.ShowIssuedItemsDialog;
import com.afrisoftech.lib.ComboBoxModel;
import com.afrisoftech.lib.SQLDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author  funsoft
 */
public class StockItemsTblIntfr extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    
    /** Creates new form ReportIntfr */
    public StockItemsTblIntfr(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
      //  loadReport();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        StoreDialog = new javax.swing.JDialog();
        jSearchPanel4 = new javax.swing.JPanel();
        storeSearchTxt = new javax.swing.JTextField();
        jSearchScrollPane4 = new javax.swing.JScrollPane();
        storeSearchTbl = new com.afrisoftech.dbadmin.JTable();
        jButton93 = new javax.swing.JButton();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        headerPanel = new javax.swing.JPanel();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        stratDatePicker = new com.afrisoftech.lib.DatePicker();
        endDateLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        storeTxt = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        storeGLtxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        categoryCmbx = new javax.swing.JComboBox();

        StoreDialog.setModal(true);
        StoreDialog.setUndecorated(true);
        StoreDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel4.setLayout(new java.awt.GridBagLayout());

        storeSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                storeSearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        jSearchPanel4.add(storeSearchTxt, gridBagConstraints);

        storeSearchTbl.setShowHorizontalLines(false);
        storeSearchTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                storeSearchTblMouseClicked(evt);
            }
        });
        jSearchScrollPane4.setViewportView(storeSearchTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel4.add(jSearchScrollPane4, gridBagConstraints);

        jButton93.setText("Dispose");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jSearchPanel4.add(jButton93, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        StoreDialog.getContentPane().add(jSearchPanel4, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Issued Items Summary");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportBodyTableMouseClicked(evt);
            }
        });
        reportBodyJscrollPane.setViewportView(reportBodyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(reportBodyJscrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(reportBodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(endDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(stratDatePicker, gridBagConstraints);

        endDateLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        endDateLbl.setForeground(new java.awt.Color(0, 0, 204));
        endDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Store Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        headerPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Store GL Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        headerPanel.add(jLabel2, gridBagConstraints);

        jPanel59.setLayout(new java.awt.GridBagLayout());

        storeTxt.setEditable(false);
        storeTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        storeTxt.setForeground(new java.awt.Color(102, 102, 102));
        storeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel59.add(storeTxt, gridBagConstraints);

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jButton23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(jPanel59, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(storeGLtxt, gridBagConstraints);

        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("End date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(jLabel3, gridBagConstraints);

        jButton1.setText("Refresh Records");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        headerPanel.add(jButton1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        headerPanel.add(jLabel4, gridBagConstraints);

        categoryCmbx.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        categoryCmbx.setForeground(new java.awt.Color(0, 0, 204));
        categoryCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        categoryCmbx.setBorder(new javax.swing.border.MatteBorder(null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 80);
        headerPanel.add(categoryCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        setBounds(0, 0, 1012, 481);
    }// </editor-fold>//GEN-END:initComponents

    private void storeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storeTxtActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        System.out.println("Showing dialog");
        java.awt.Point point = storeTxt.getLocationOnScreen();
        StoreDialog.setSize(400, 200);
        StoreDialog.setLocation(point);
        StoreDialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void storeSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_storeSearchTxtCaretUpdate
        // TODO add your handling code here:
        if (storeSearchTxt.getCaretPosition() > 3) {

            //            System.out.println("Nothing");
            //        } else {
            storeSearchTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct department_name,income_account from pb_departments where department_name ilike '" + storeSearchTxt.getText() + "%' order by 1"));

            jSearchScrollPane4.setViewportView(storeSearchTbl);

        }
    }//GEN-LAST:event_storeSearchTxtCaretUpdate

    private void storeSearchTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeSearchTblMouseClicked
        // TODO add your handling code here:
        storeTxt.setText(storeSearchTbl.getValueAt(storeSearchTbl.getSelectedRow(), 0).toString());
        storeGLtxt.setText(storeSearchTbl.getValueAt(storeSearchTbl.getSelectedRow(), 1).toString());
        //refreshRecords();
        categoryCmbx.setModel(ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT sub_cat_code FROM st_stock_item WHERE department='"+storeTxt.getText()+"'  UNION SELECT DISTINCT category FROM st_stock_prices WHERE department ilike '"+storeTxt.getText()+"' ORDER BY 1"));
        StoreDialog.dispose();
        
    }//GEN-LAST:event_storeSearchTblMouseClicked

    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        // TODO add your handling code here:
        StoreDialog.dispose();
    }//GEN-LAST:event_jButton93ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(!categoryCmbx.getSelectedItem().toString().equalsIgnoreCase("-")){
            refreshRecords();
        }
        else{
            JOptionPane.showMessageDialog(this, "Not A Valid category","Check Category", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        // TODO add your handling code here:
        if(reportBodyTable.getSelectedColumn()==0 && reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(),0)!=null){
       this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));     
            String item = reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(),0).toString();
            String date1=SQLDateFormat.getSQLDate(stratDatePicker.getDate()).toString();
            String date2=SQLDateFormat.getSQLDate(endDatePicker.getDate()).toString();
        
            ShowIssuedItemsDialog issued = new ShowIssuedItemsDialog(null, true, connectDB, item, date1, date2);
            issued.setVisible(true);
        
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        
        
        }
    }//GEN-LAST:event_reportBodyTableMouseClicked
   private void refreshRecords(){
   reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item, issuing Qty_issued, sub_store COST_CENTER,transaction_no, manual_transfer_no,user_name, trans_date::date Date_Issued from st_sub_stores where issuing>0 and store_name ilike '"+storeTxt.getText()+"' and trans_date::date between '"+ SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' AND transaction_no ILIKE 'T%' AND item IN(SELECT distinct description from st_stock_item where sub_cat_code='"+categoryCmbx.getSelectedItem()+"' UNION  select distinct product from st_stock_prices where category='"+categoryCmbx.getSelectedItem()+"') group by 1,2,3,4,5,6,7"));
 
   System.err.println("select distinct item, issuing Qty_issued, sub_store COST_CENTER,transaction_no, manual_transfer_no,user_name, trans_date::date Date_ from st_sub_stores where issuing>0 and store_name ilike '"+storeTxt.getText()+"' and trans_date::date between '"+ SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' and initcap(store_name) in (select initcap(store_name)from st_main_stores) AND transaction_no ILIKE 'T%' AND item IN(SELECT distinct description from st_stock_item where sub_cat_code='"+categoryCmbx.getSelectedItem()+"' UNION  select distinct product from st_stock_prices where category='"+categoryCmbx.getSelectedItem()+"') group by 1,2,3,4,5,6,7");
   
   };    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog StoreDialog;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox categoryCmbx;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton93;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jSearchPanel4;
    private javax.swing.JScrollPane jSearchScrollPane4;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTextField storeGLtxt;
    private javax.swing.JTable storeSearchTbl;
    private javax.swing.JTextField storeSearchTxt;
    private javax.swing.JTextField storeTxt;
    private com.afrisoftech.lib.DatePicker stratDatePicker;
    // End of variables declaration//GEN-END:variables
    
}
