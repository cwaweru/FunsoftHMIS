/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.laundry;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
public class RecordWashingData extends javax.swing.JInternalFrame {
    SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, MMM d yyyy");
    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    Connection conn;
    List<String> assetTags = new ArrayList<String>();
    List<String> laundryTags = new ArrayList<String>();
    private Object[][] batch;
    int[] dataID;
    int totalNumRows;
    String doneloading="no";
    /**
     * Creates new form MachineSetup
     */
    public RecordWashingData(Connection passedConn) {
        setVisible(true);
        initComponents();
        conn=passedConn;
        datePicker.setDate(new java.util.Date() );
        Calendar cal = Calendar.getInstance();
        cbxhour.setSelectedItem(""+cal.get(Calendar.HOUR));
        cbxAMorPM.setSelectedItem("AM");
        if(cal.get(Calendar.AM_PM)==1){
            cbxAMorPM.setSelectedItem("PM");
        }cbxMin.removeAllItems();
        for(int i=0; i<60; i++){
            if(i<10){
                cbxMin.addItem("0"+i);
            }
            else{
                cbxMin.addItem(""+i);
            }
        }
        cbxMin.setSelectedItem(""+cal.get(Calendar.MINUTE));
        
        cbxhourout.setSelectedItem(""+cal.get(Calendar.HOUR));
        cbxAMorPMout.setSelectedItem("AM");
        if(cal.get(Calendar.AM_PM)==1){
            cbxAMorPMout.setSelectedItem("PM");
        }cbxMinout.removeAllItems();
        for(int i=1; i<=60; i++){
            if(i<10){
                cbxMinout.addItem("0"+i);
            }
            else{
                cbxMinout.addItem(""+i);
            }
        }
        cbxMinout.setSelectedItem(""+cal.get(Calendar.MINUTE));
        
        try {
            machineCbx.removeAllItems();
            String sql="select DISTINCT upper(asset_name), tag_no from public.asset_registration WHERE tag_no in (select tagno from laundryequipment where type like 'Washer')";
            System.out.println("machines available : " + sql);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                
                machineCbx.addItem(rs.getObject(1).toString());
                String tagNo=rs.getString("tag_no");
                assetTags.add(tagNo);
                
            }
            cbxLinenType.removeAllItems();
            sql="select upper(typename), ltid from laundrylinentype order by typename asc";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                
                cbxLinenType.addItem(rs.getObject(1).toString());
                laundryTags.add(rs.getString("ltid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateTable();
        doneloading="yes";
        
    }
    
    
    public void populateTable(){
        batch = new Object[0][5];
        dataID=new int[0];
        RefreshClientsTable();
        try {
            //here
            String selectedDate=sdf2.format(datePicker.getDate());
            String selectedmachine=assetTags.get(machineCbx.getSelectedIndex());
            String sql="select * from laundryprocessdata WHERE datedone like '"+selectedDate+"' and machineid like '"+selectedmachine+"' and process like 'washing' order by timein asc";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            totalNumRows=rs.getRow();
            batch = new Object[totalNumRows][5];
            dataID=new int[totalNumRows];
            int r;
            r=0;
            rs.beforeFirst();
            while (rs.next()){
                String linenTypeID=rs.getString("typeid");
                String linenType=(String) cbxLinenType.getItemAt(laundryTags.indexOf(linenTypeID));
                //"Linen Type", "Load", "Time In", "Time Out", "Remarks"
                batch[r][0]=linenType;
                batch[r][1]=rs.getString("load");
                batch[r][2]=rs.getString("timein");
                batch[r][3]=rs.getString("timeout");
                batch[r][4]=rs.getString("remarks");
                dataID[r]=rs.getInt("dataid");
                r++;
                RefreshClientsTable();
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
        machineCbx = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbxLinenType = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtLoad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        datePicker = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtRemarks = new javax.swing.JTextField();
        btnCommit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbxhour = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbxMin = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbxAMorPM = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cbxhourout = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cbxMinout = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbxAMorPMout = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        lblMachine = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Washing Data");
        setPreferredSize(new java.awt.Dimension(916, 560));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Machine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        machineCbx.setPreferredSize(new java.awt.Dimension(100, 20));
        machineCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machineCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(machineCbx, gridBagConstraints);

        jLabel2.setText("Type of Linen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        cbxLinenType.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(cbxLinenType, gridBagConstraints);

        jLabel3.setText("Load (kg)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        txtLoad.setColumns(4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(txtLoad, gridBagConstraints);

        jLabel4.setText("Remarks");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Date");
        jPanel5.add(jLabel5);

        datePicker.setPreferredSize(new java.awt.Dimension(110, 20));
        datePicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                datePickerMouseReleased(evt);
            }
        });
        jPanel5.add(datePicker);

        jButton1.setText("Go");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel5, gridBagConstraints);

        txtRemarks.setColumns(40);
        txtRemarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRemarksActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(txtRemarks, gridBagConstraints);

        btnCommit.setText("Save");
        btnCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(btnCommit, gridBagConstraints);

        jLabel6.setText("Time in");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        cbxhour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel3.add(cbxhour, gridBagConstraints);

        jLabel7.setText(":");
        jPanel3.add(jLabel7, new java.awt.GridBagConstraints());

        jPanel3.add(cbxMin, new java.awt.GridBagConstraints());

        jLabel8.setText(" ");
        jPanel3.add(jLabel8, new java.awt.GridBagConstraints());

        cbxAMorPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        jPanel3.add(cbxAMorPM, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jLabel9.setText("Time Out");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        cbxhourout.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel6.add(cbxhourout, gridBagConstraints);

        jLabel10.setText(":");
        jPanel6.add(jLabel10, new java.awt.GridBagConstraints());

        jPanel6.add(cbxMinout, new java.awt.GridBagConstraints());

        jLabel11.setText(" ");
        jPanel6.add(jLabel11, new java.awt.GridBagConstraints());

        cbxAMorPMout.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        jPanel6.add(cbxAMorPMout, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jPanel7.add(lblMachine);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete, new java.awt.GridBagConstraints());

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void machineCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machineCbxActionPerformed
        if(machineCbx.getSelectedIndex()>=0){
            lblMachine.setText((String) machineCbx.getSelectedItem()+" Date: "+sdf3.format(datePicker.getDate()));
            if(doneloading.equals("yes")){
                populateTable();
            }
        }
    }//GEN-LAST:event_machineCbxActionPerformed

    private void btnCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommitActionPerformed
        try {
            Statement stmt3 = conn.createStatement();
            String tarehe=sdf2.format(datePicker.getDate());
            String typeID=laundryTags.get(cbxLinenType.getSelectedIndex());
            String remarks=txtRemarks.getText();
            String machineID=assetTags.get(machineCbx.getSelectedIndex());
            String load=txtLoad.getText();

            String timein=(String) cbxhour.getSelectedItem()+":"+(String)cbxMin.getSelectedItem()+" "+(String)cbxAMorPM.getSelectedItem();
            String timeout=(String) cbxhourout.getSelectedItem()+":"+(String)cbxMinout.getSelectedItem()+" "+(String)cbxAMorPMout.getSelectedItem();
            stmt3.execute( "insert into laundryprocessdata (process, typeid, timein, timeout, remarks, datedone, machineid, load) "
                    + "values('washing','"+typeID+"', '"+timein+"','"+timeout+"','"+remarks+"','"+tarehe+"','"+machineID+"','"+load+"'  )" );
            
            populateTable();
        } catch (SQLException ex) {
            Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                

    }//GEN-LAST:event_btnCommitActionPerformed

    private void txtRemarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRemarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemarksActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_jPanel1FocusGained

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            Statement stmt3 = conn.createStatement();
            int dataid=dataID[jTable1.getSelectedRow()];
            stmt3.execute( "delete from laundryprocessdata where dataid ="+dataid );
            System.out.println("delete from laundryprocessdata where dataid ="+dataid);
            populateTable();
        } catch (SQLException ex) {
            Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void datePickerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePickerMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_datePickerMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(machineCbx.getSelectedIndex()>=0){
            lblMachine.setText((String) machineCbx.getSelectedItem()+" Date: "+sdf3.format(datePicker.getDate()));
            if(doneloading.equals("yes")){
                populateTable();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void RefreshClientsTable(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(batch,
    new String[]{"Linen Type", "Load", "Time In", "Time Out", "Remarks"}
)
{
         @Override
            public boolean isCellEditable(int rowIndex,int colIndex){
                Boolean editable=false;
                
                return editable;
            }   

        });
        
        
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            col0.setPreferredWidth(20);
            TableColumn col1=jTable1.getColumnModel().getColumn(1);
            col1.setPreferredWidth(5);
            TableColumn col2=jTable1.getColumnModel().getColumn(2);
            TableColumn col3=jTable1.getColumnModel().getColumn(3);
            TableColumn col4=jTable1.getColumnModel().getColumn(4);
            col4.setPreferredWidth(55);
            DefaultTableCellRenderer rrendr=new DefaultTableCellRenderer();
            rrendr.setHorizontalAlignment(JLabel.CENTER);
            rrendr.setOpaque(true);
            
            col2.setCellRenderer(rrendr);
            col3.setCellRenderer(rrendr);
            col1.setCellRenderer(rrendr);
            

    }
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCommit;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox cbxAMorPM;
    private javax.swing.JComboBox cbxAMorPMout;
    private javax.swing.JComboBox cbxLinenType;
    private javax.swing.JComboBox cbxMin;
    private javax.swing.JComboBox cbxMinout;
    private javax.swing.JComboBox cbxhour;
    private javax.swing.JComboBox cbxhourout;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblMachine;
    private javax.swing.JComboBox machineCbx;
    private javax.swing.JTextField txtLoad;
    private javax.swing.JTextField txtRemarks;
    // End of variables declaration//GEN-END:variables
}
