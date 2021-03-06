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
public class ViewWashingData extends javax.swing.JInternalFrame {
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
    public ViewWashingData(Connection passedConn) {
        setVisible(true);
        initComponents();
        conn=passedConn;
        datePicker.setDate(new java.util.Date() );
        populateTable();
        
    }
    
    
    public void populateTable(){
        batch = new Object[0][5];
        dataID=new int[0];
        RefreshClientsTable();
        try {
            String selectedDate=sdf2.format(datePicker.getDate());
            String sqlMachine="select upper(asset_name), tag_no from public.asset_registration WHERE asset_type ~* 'Plant, mach. and Equip.' AND issued = 'TRUE' AND  location ~*'Laundry' AND tag_no in (select tagno from laundryequipment where type like 'Washer') AND tag_no in (select machineid from laundryprocessdata WHERE datedone like '"+selectedDate+"' and process like 'washing')";
            Statement stmtMachine = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            System.out.println(sqlMachine);
            ResultSet rsMachine = stmtMachine.executeQuery( sqlMachine );
            rsMachine.last();
            int noOfMachines=rsMachine.getRow();
            rsMachine.beforeFirst();
            String sql="select * from laundryprocessdata WHERE datedone like '"+selectedDate+"' and process like 'washing' order by dataid asc";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            totalNumRows=rs.getRow();
            batch = new Object[totalNumRows+noOfMachines][5];
            int machineCounter=0;
            while(rsMachine.next()){
                //"Linen Type", "Load", "Time In", "Time Out", "Remarks"
                batch[machineCounter][2]=rsMachine.getObject(1).toString();
                machineCounter++;
                sql="select * from laundryprocessdata WHERE datedone like '"+selectedDate+"' and machineid like '"+rsMachine.getString("tag_no")+"' and process like 'washing' order by dataid asc";
                System.out.println(sql);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery( sql );
                while (rs.next()){
                    String linenTypeID=rs.getString("typeid");
                    String sqllinen="select upper(typename) from laundrylinentype WHERE ltid = "+linenTypeID+"";
                    Statement stmtLinen = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
                    System.out.println(sqllinen);
                    ResultSet rsLinen = stmtLinen.executeQuery( sqllinen );
                    String linenType="";
                    if(rsLinen.next()){
                        linenType=rsLinen.getObject(1).toString();
                    }
                    
                    
                    //"Linen Type", "Load", "Time In", "Time Out", "Remarks"
                    batch[machineCounter][0]=linenType;
                    batch[machineCounter][1]=rs.getString("load");
                    batch[machineCounter][2]=rs.getString("timein");
                    batch[machineCounter][3]=rs.getString("timeout");
                    batch[machineCounter][4]=rs.getString("remarks");
                    machineCounter++;
                }
                
            }
            RefreshClientsTable();
            

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
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        datePicker = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Washing Report");
        setPreferredSize(new java.awt.Dimension(916, 560));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText(" ");
        jPanel3.add(jLabel8, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText(" ");
        jPanel6.add(jLabel11, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel6, gridBagConstraints);

        jLabel5.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 10;
        jPanel1.add(jLabel5, gridBagConstraints);

        datePicker.setPreferredSize(new java.awt.Dimension(110, 20));
        datePicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                datePickerMouseReleased(evt);
            }
        });
        datePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickerPropertyChange(evt);
            }
        });
        jPanel1.add(datePicker, new java.awt.GridBagConstraints());

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
        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSave.setText("To PDF");
        btnSave.setPreferredSize(new java.awt.Dimension(75, 23));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel4.add(btnSave, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.setPreferredSize(new java.awt.Dimension(75, 23));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel4.add(btnCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
        
    }//GEN-LAST:event_jPanel1FocusGained

    private void datePickerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePickerMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_datePickerMouseReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        WashingReportPdf newPdf= new WashingReportPdf();
        
        newPdf.generatePdf(sdf2.format(datePicker.getDate()), sdf3.format(datePicker.getDate()), conn);
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        populateTable();
    }//GEN-LAST:event_datePickerPropertyChange

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
    private javax.swing.JButton btnSave;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
