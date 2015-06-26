/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.laundry;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.openide.util.Exceptions;


/**
 *
 * @author charlie
 */
public class ConsumablesUse extends javax.swing.JInternalFrame {
    //Map<String, String[]> mapAll = new HashMap<String, String[]>();
    /*List<String> wards= new ArrayList<String>();
    String [] linenTypeID;
    HashMap attendantsHash = new HashMap();
    private Object[][] batch;
    String doneLoading="no";
    Connection conn;
    int totalNumRows;
    int totalNumCols;
    SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, MMM d yyyy");
    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMddHHmmssSSS
    
    /**
     * Creates new form MachineSetup
     */
    int totalNumRows;
    int totalNumCols;
    Connection conn;
    String[] itemCodes;
    String[] activityCodes;
    String[] issuedTo;
    String unitsAvailable[];
    private Object[][] batch;
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdfForParam = new SimpleDateFormat("MMM d, yyyy");
    String doneLoading="no";
    int selectedRow=0;
    @SuppressWarnings("empty-statement")
    public ConsumablesUse(Connection passedConn) {
        
        
        setVisible(true);
        conn=passedConn;
        initComponents();
        initialise();
        txtAvailable.setEditable(false);
        
        
    }

    public void initialise(){
        
            datePicker.setDate(new java.util.Date() );
            
            
        try {    
            String sql="select * from pb_activity where activity ~*'Laundry Detergents Expenses'";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            activityCodes=new String[rs.getRow()];
            rs.beforeFirst();
            int r=0;
            while(rs.next()){
                cbxActivity.addItem(rs.getString("activity"));
                activityCodes[r]=rs.getString("code");
                r++;
            }
            sql="select * from st_stores where store_name ~*'Laundry' or store_name ~*'%Laundry%' or store_name ~*'%Laundry' or store_name ~*'Laundry%'";
            rs = stmt.executeQuery( sql );
            rs.last();
            rs.beforeFirst();
            r=0;
            while(rs.next()){
                cbxStore.addItem(rs.getString("store_name"));
                r++;
            }
            
            sql="SELECT CONCAT(first_name,' ',middle_name) AS \"Name\", employee_no FROM master_file WHERE department ~*'Laundry Unit' ORDER BY first_name ASC";
            rs = stmt.executeQuery( sql );
            rs.last();
            issuedTo=new String[rs.getRow()];
            rs.beforeFirst();
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
        }
        
    }
    
    public void refreshCbx(){
        cbxItem.removeAllItems();
        try {
            String tarehe=sdf2.format(datePicker.getDate());
            int shift=Integer.parseInt((String) cbxShift.getSelectedItem());
            String sql="SELECT DISTINCT description, item_code FROM st_stock_item WHERE sub_cat_code LIKE 'LAUNDRY DETERGENTS' AND item_code NOT IN (SELECT itemcode FROM detergentuse WHERE datefor LIKE '"+tarehe+"' AND shift ="+shift+" AND unitscf !='') ORDER BY st_stock_item.description ASC";
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
            String selectedDate=sdf2.format(datePicker.getDate());
            String selectedShift=(String) cbxShift.getSelectedItem();
            String sql="SELECT DISTINCT st_stock_item.item_code, st_stock_item.description, detergentuse.unitsissued, detergentuse.unitscf, detergentuse.issuedto\n" +
                        "FROM st_stock_item\n" +
                        "LEFT JOIN detergentuse\n" +
                        "ON st_stock_item.item_code=detergentuse.itemcode AND detergentuse.datefor = '"+selectedDate+"' AND detergentuse.shift = '"+selectedShift+"'\n" +
                        "WHERE st_stock_item.sub_cat_code = 'LAUNDRY DETERGENTS'\n" +
                        "ORDER BY st_stock_item.description ASC";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            totalNumRows=rs.getRow();
            batch = new Object[totalNumRows][5];
            int r;
            r=0;
            rs.beforeFirst();
            while (rs.next()){
                batch[r][0]=rs.getString("description");
                batch[r][1]=rs.getString("unitscf");
                batch[r][2]=rs.getString("unitsissued");
                batch[r][3]=rs.getInt("unitscf")+rs.getInt("unitsissued");
                batch[r][4]=rs.getString("issuedto");
                r++;
                RefreshClientsTable();
            }
            
if(cbxItem.getSelectedIndex()>-1){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
        }
        }catch (Exception ex) {
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
        datePicker = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cbxShift = new javax.swing.JComboBox();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbxActivity = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbxStore = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consumables Use");
        setPreferredSize(new java.awt.Dimension(968, 513));
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

        datePicker.setPreferredSize(new java.awt.Dimension(110, 20));
        datePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickerPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(datePicker, gridBagConstraints);

        jLabel2.setText("Shift");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        cbxShift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cbxShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxShiftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(cbxShift, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.05;
        getContentPane().add(jPanel1, gridBagConstraints);

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
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.05;
        getContentPane().add(jPanel5, gridBagConstraints);

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

        cbxActivity.setPreferredSize(new java.awt.Dimension(170, 20));
        cbxActivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxActivityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel4.add(cbxActivity, gridBagConstraints);

        jLabel9.setText("Activity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel9, gridBagConstraints);

        cbxStore.setPreferredSize(new java.awt.Dimension(170, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel4.add(cbxStore, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.05;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.05;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        if(doneLoading.equals("yes")){
            refreshCbx();
            populateTable();
        }
    }//GEN-LAST:event_datePickerPropertyChange

    private void cbxShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxShiftActionPerformed
        if(doneLoading.equals("yes")){
            refreshCbx();
            populateTable();
        }
    }//GEN-LAST:event_cbxShiftActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.sql.Savepoint savePoint = null; //conn.setSavepoint("Savepoint");
        try {
            //java.sql.Savepoint savePoint = conn.setSavepoint("Savepoint");
            conn.setAutoCommit(false);
            String itemCode=itemCodes[cbxItem.getSelectedIndex()];
            int unitsIssued=Integer.parseInt(txtIssued.getText());
            
            Object dateSelected=com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker.getDate());
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
            String unitOfMeas=rsPrep.getString("units");
            double totalAmount=price*unitsIssued;
            String transNo="";
            
            java.sql.Statement ps = conn.createStatement();
            java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");
            while (rst.next()){
                rst.getObject(1).toString();

                transNo = rst.getObject(1).toString();
            }
            //, '"++"'
            String activityCode=activityCodes[cbxActivity.getSelectedIndex()];
            String activity= (String) cbxActivity.getSelectedItem();
            String store=(String) cbxStore.getSelectedItem();
            String item=(String) cbxItem.getSelectedItem();
            String issuedToString=(String) cbxIssuedTo.getSelectedItem();
            
            String sqlacLedger="insert into ac_ledger"
                    + " (activity_code, description, patient_no, dealer, reason, voucher_no, payee, drawer, drawer_bank, gl_code, cheque_no, receipt_no, journal_no, service_type, transaction_type, debit, credit, date, transaction_no, closed, reconciled, withdrawn, user_name, cash_point, shift_no)"
                    + "values ('"+activityCode+"', '"+activity+"', '', '', '', '', '', '', '', '', '', '', '', '', '', "+totalAmount+", 0.00, '"+dateSelected+"', '"+transNo+"', false, false, false, '"+currentUser+"', '', '')";
            Statement stmtacledger = conn.createStatement();
            stmtacledger.execute( sqlacLedger);
            System.out.println(transNo);
            
            
            
            
            String sqlstockCardex="insert into st_stock_cardex "
                    + "(store, item, quantity_ordered, price_per_item, units, quantity_received, sub_store_receiving, quantity_required,issued_to, issued_by, date, cost_center, debit, credit, activity_code, description, transaction_no, sub_store_issuing, user_name, old_grn_date, item_code) "
                    + "values ('"+store+"', '"+item+"', '"+totalAmount+"', '"+price+"', '"+unitOfMeas+"', '"+unitsIssued+"', 0.00, 0.00, '"+issuedToString+"', '"+currentUser+"', '"+dateSelected+"', '"+store+"', 0.00, '"+totalAmount+"', '"+activityCode+"', 'Laundry Detergents Used',  '"+transNo+"', '"+unitsIssued+"', '"+currentUser+"', '"+dateSelected+"', '"+itemCode+"')";
            
            Statement stmtstockCardex = conn.createStatement();
            stmtstockCardex.execute( sqlstockCardex);
            
            
            
            String sqlsubStores="insert into st_sub_stores "
                    + "(store_name, item, receiving, issuing, price, total, sub_store_issiuing, transaction_no, trans_date, user_name, units, sub_store, issiued_to, buying_price, item_code) "
                    + " values ('"+store+"', '"+item+"', 0.00, '"+unitsIssued+"' , '"+price+"', '"+totalAmount+"', '"+unitsIssued+"', '"+transNo+"', '"+dateSelected+"', '"+currentUser+"', '"+unitOfMeas+"', '"+store+"', '"+issuedToString+"', '"+price+"', '"+itemCode+"')";
            Statement stmtsubstores = conn.createStatement();
            stmtsubstores.execute( sqlsubStores );
            
            
            String tarehe=sdf2.format(datePicker.getDate());
            Statement stmt3 = conn.createStatement();
            stmt3.execute( "insert into detergentuse (itemcode, unitsissued, datefor, shift, unitscf, issuedto, transaction_no ) "
                    + "values('"+itemCode+"','"+unitsIssued+"', '"+tarehe+"','"+cbxShift.getSelectedItem()+"','"+txtCf.getText()+"', '"+issuedToString+"', '"+transNo+"')" );
            conn.commit();
            conn.setAutoCommit(true);
            refreshCbx();
            populateTable();
            txtCf.setText("");
            txtIssued.setText("");
        } catch (SQLException ex) {
            try {
                conn.rollback(savePoint);
                ex.printStackTrace();
                Logger.getLogger(ConsumablesUse.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Exceptions.printStackTrace(ex1);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxItemActionPerformed
        if(cbxItem.getSelectedIndex()>-1){
            txtAvailable.setText(unitsAvailable[cbxItem.getSelectedIndex()]);
        }
    }//GEN-LAST:event_cbxItemActionPerformed

    private void txtAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAvailableActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //"Item", "Units Carried Forward", "Units Issued", "Total Units Available", "Issued To"
        String [] params= new String[5];
        params[0]=(String) cbxActivity.getSelectedItem();
        params[1]=(String) cbxStore.getSelectedItem();
        params[2]=sdfForParam.format(datePicker.getDate());
        params[3]=(String) cbxShift.getSelectedItem();
        params[4]=sdf2.format(datePicker.getDate());
        ConsumablesEdit mpya=new ConsumablesEdit(null, true, params, conn );
        mpya.setVisible(true);
        populateTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void cbxActivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxActivityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxActivityActionPerformed

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
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox cbxActivity;
    private javax.swing.JComboBox cbxIssuedTo;
    private javax.swing.JComboBox cbxItem;
    private javax.swing.JComboBox cbxShift;
    private javax.swing.JComboBox cbxStore;
    private com.toedter.calendar.JDateChooser datePicker;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAvailable;
    private javax.swing.JTextField txtCf;
    private javax.swing.JTextField txtIssued;
    // End of variables declaration//GEN-END:variables
}
