/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.laundry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author charlie
 */
public class ConsumablesEdit extends javax.swing.JDialog {
int totalNumRows;
int totalNumCols;
Connection conn;
String[] itemCodes;
String[] activityCodes;
String[] issuedTo;
String unitsAvailable[];
String dateDbFormat;
private Object[][] batch;
String[] transNumbers;
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
String doneLoading="no";
int selectedRow=0;

    /**
     * Creates new form ConsumablesEdit
     */
    public ConsumablesEdit(java.awt.Frame parent, boolean modal, String[] params, Connection passedConn) {
        super(parent, modal);
        conn=passedConn;
        initComponents();
        txtAvailable.setEditable(false);
        
        txtActivity.setText(params[0]);
        txtStore.setText(params[1]);
        txtDate.setText(params[2]);
        txtShift.setText(params[3]);
        dateDbFormat=params[4];
        initialise();
        
        
    }
    
    public void initialise(){
        
            
            
        try {    
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            
            
            String sql="select CONCAT(first_name,' ',middle_name) AS \"Name\", employee_no from master_file where department ~*'laundry' order by first_name asc";
            rs = stmt.executeQuery( sql );
            rs.last();
            issuedTo=new String[rs.getRow()];
            rs.beforeFirst();
            int r;
            r=0;
            while(rs.next()){
                cbxIssuedTo.addItem(rs.getString("Name"));
                issuedTo[r]=rs.getString("employee_no");
                r++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesUse.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshCbx();
        populateTable();
        doneLoading="yes";
        if(cbxItem.getSelectedIndex()>-1){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
            int indexYake=cbxItem.getSelectedIndex();
            txtCf.setText((String) jTable1.getValueAt(indexYake, 1));
            txtIssued.setText((String) jTable1.getValueAt(indexYake, 2));
            cbxIssuedTo.setSelectedItem((String) jTable1.getValueAt(indexYake, 4));
        }
        
    }
    
    public void refreshCbx(){
        cbxItem.removeAllItems();
        try {
            String sql="SELECT DISTINCT description, item_code FROM st_stock_item WHERE sub_cat_code = 'LAUNDRY DETERGENTS' ORDER BY st_stock_item.description ASC";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            itemCodes=new String[rs.getRow()];
            unitsAvailable=new String[rs.getRow()];
            rs.beforeFirst();
            int r=0;
            while(rs.next()){
                cbxItem.addItem(rs.getString("description"));
                itemCodes[r]=rs.getString("item_code");
                String sql2="select (COALESCE(sum (receiving),0) - COALESCE(sum (issuing),0)) AS \"Available\"  from st_sub_stores where item_code like '"+rs.getString("item_code")+"'";
                Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs2 = stmt2.executeQuery( sql2 );
                rs2.next();
                double available=Double.parseDouble(rs2.getString("Available"));
                unitsAvailable[r]=(int)available+"";
                if(unitsAvailable[r].equals("")){
                    unitsAvailable[r]="0";
                }
                r++;
            }
            if(cbxItem.getSelectedIndex()>-1){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
        }

        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesUse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void populateTable(){
        batch = new Object[0][5];
        RefreshClientsTable();
        try {
            //here
            String selectedDate=dateDbFormat;
            String selectedShift=txtShift.getText();
            String sql="SELECT DISTINCT st_stock_item.item_code, st_stock_item.description, detergentuse.unitsissued, detergentuse.unitscf, detergentuse.issuedto, detergentuse.transaction_no \n" +
                        "FROM st_stock_item\n" +
                        "LEFT join detergentuse\n" +
                        "ON st_stock_item.item_code=detergentuse.itemcode AND detergentuse.datefor = '"+selectedDate+"' AND detergentuse.shift = '"+selectedShift+"'\n" +
                        "WHERE st_stock_item.sub_cat_code = 'LAUNDRY DETERGENTS'\n" +
                        "ORDER BY st_stock_item.description ASC";
            System.out.println(sql);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            totalNumRows=rs.getRow();
            batch = new Object[totalNumRows][5];
            transNumbers=new String[totalNumRows];
            int r;
            r=0;
            rs.beforeFirst();
            while (rs.next()){
                batch[r][0]=rs.getString("description");
                batch[r][1]=rs.getString("unitscf");
                batch[r][2]=rs.getString("unitsissued");
                batch[r][3]=rs.getInt("unitscf")+rs.getInt("unitsissued");
                batch[r][4]=rs.getString("issuedto");
                transNumbers[r]=rs.getString("transaction_no");
                r++;
                RefreshClientsTable();
            }
            
if(cbxItem.getSelectedIndex()>-1){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
        }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error: "+ex.toString());

       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtShift = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtActivity = new javax.swing.JTextField();
        txtStore = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxItem = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtIssued = new javax.swing.JTextField();
        txtCf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtAvailable = new javax.swing.JTextField();
        cbxIssuedTo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Detergent Use");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Shift");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        txtDate.setEditable(false);
        txtDate.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel1.add(txtDate, gridBagConstraints);

        txtShift.setEditable(false);
        txtShift.setColumns(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(txtShift, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("Store");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 10);
        jPanel4.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Activity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel9, gridBagConstraints);

        txtActivity.setEditable(false);
        txtActivity.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel4.add(txtActivity, gridBagConstraints);

        txtStore.setEditable(false);
        txtStore.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel4.add(txtStore, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(jLabel3, gridBagConstraints);

        cbxItem.setPreferredSize(new java.awt.Dimension(150, 20));
        cbxItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(cbxItem, gridBagConstraints);

        jLabel5.setText("Units Available");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabel5, gridBagConstraints);

        txtIssued.setColumns(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        jPanel5.add(txtIssued, gridBagConstraints);

        txtCf.setColumns(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel5.add(txtCf, gridBagConstraints);

        jLabel6.setText("Units c/f");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabel6, gridBagConstraints);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jButton1, gridBagConstraints);

        jLabel7.setText("Units issued");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabel7, gridBagConstraints);

        txtAvailable.setColumns(5);
        txtAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAvailableActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        jPanel5.add(txtAvailable, gridBagConstraints);

        cbxIssuedTo.setPreferredSize(new java.awt.Dimension(170, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbxIssuedTo, gridBagConstraints);

        jLabel10.setText("Issued To");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(cbxItem.getSelectedIndex()>-1 && doneLoading.equals("yes")){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
            int indexYake=cbxItem.getSelectedIndex();
            txtCf.setText((String) jTable1.getValueAt(indexYake, 1));
            txtIssued.setText((String) jTable1.getValueAt(indexYake, 2));
            cbxIssuedTo.setSelectedItem((String) jTable1.getValueAt(indexYake, 4));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cbxItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxItemActionPerformed
       if(cbxItem.getSelectedIndex()>-1 && doneLoading.equals("yes")){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
            int indexYake=cbxItem.getSelectedIndex();
            txtCf.setText((String) jTable1.getValueAt(indexYake, 1));
            txtIssued.setText((String) jTable1.getValueAt(indexYake, 2));
            cbxIssuedTo.setSelectedItem((String) jTable1.getValueAt(indexYake, 4));
        }
    }//GEN-LAST:event_cbxItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
            String itemCode=itemCodes[cbxItem.getSelectedIndex()];
            int unitsIssued=Integer.parseInt(txtIssued.getText());

            String sqlPrep="select current_user";
            Statement stmtPrep = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            ResultSet rsPrep = stmtPrep.executeQuery( sqlPrep );
            rsPrep.next();
            String currentUser=rsPrep.getString("current_user");

            sqlPrep="select buying_price, units from st_stock_item where item_code like '"+itemCode+"'";
            stmtPrep = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            rsPrep = stmtPrep.executeQuery( sqlPrep );
            rsPrep.next();
            double price=rsPrep.getDouble("buying_price");
            double totalAmount=price*unitsIssued;
            String transNo=transNumbers[cbxItem.getSelectedIndex()];

            //, '"++"'
            String issuedToString=(String) cbxIssuedTo.getSelectedItem();

            String updateSqlLedger="update ac_ledger set debit= "+totalAmount+", user_name= '"+currentUser+"' where transaction_no like '"+transNo+"' ";
            Statement stmtacledger = conn.createStatement();
            stmtacledger.executeUpdate(updateSqlLedger);

            String updateSqlstockCardex="update st_stock_cardex set quantity_ordered='"+totalAmount+"', price_per_item='"+price+"', quantity_received= '"+unitsIssued+"', issued_to= '"+issuedToString+"', issued_by='"+currentUser+"', credit= '"+totalAmount+"', sub_store_issuing= '"+unitsIssued+"', user_name='"+currentUser+"' where transaction_no like '"+transNo+"' ";
            Statement stmtstockCardex = conn.createStatement();
            stmtstockCardex.executeUpdate(updateSqlstockCardex);

            String updateSqlsubStores="update st_sub_stores set issuing= '"+unitsIssued+"', price= '"+price+"', total= '"+totalAmount+"' , sub_store_issiuing='"+unitsIssued+"', user_name= '"+currentUser+"', issiued_to= '"+issuedToString+"', buying_price='"+price+"' where transaction_no like '"+transNo+"'";
            Statement stmtsubstores = conn.createStatement();
            stmtsubstores.execute( updateSqlsubStores );

            Statement stmt3 = conn.createStatement();
            stmt3.execute( "update detergentuse set unitsissued= '"+unitsIssued+"', unitscf='"+txtCf.getText()+"', issuedto='"+issuedToString+"' where  transaction_no like '"+transNo+"'" );
            refreshCbx();
            populateTable();
            txtCf.setText("");
            txtIssued.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesUse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAvailableActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    
    private void RefreshClientsTable(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(batch,
   new String[]{"Item", "Units Carried Forward", "Units Issued", "Total Units Available", "Issued To"}
)
{
         @Override
            public boolean isCellEditable(int rowIndex,int colIndex){
                
                return false;
            }   

        });
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        col0.setPreferredWidth(350);
        
        
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        
        DefaultTableCellRenderer rrendr=new DefaultTableCellRenderer();
        rrendr.setHorizontalAlignment(JLabel.CENTER);
        rrendr.setOpaque(true);
        
        col1.setCellRenderer(rrendr);
        col2.setCellRenderer(rrendr);
        col3.setCellRenderer(rrendr);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxIssuedTo;
    private javax.swing.JComboBox cbxItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtActivity;
    private javax.swing.JTextField txtAvailable;
    private javax.swing.JTextField txtCf;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtIssued;
    private javax.swing.JTextField txtShift;
    private javax.swing.JTextField txtStore;
    // End of variables declaration//GEN-END:variables
}
