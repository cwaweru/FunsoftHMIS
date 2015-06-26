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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;


/**
 *
 * @author charlie
 */
public final class ViewDistributionData extends javax.swing.JInternalFrame {
    //Map<String, String[]> mapAll = new HashMap<String, String[]>();
    List<String> wards= new ArrayList<String>();
    String [] linenTypeID;
    HashMap attendantsHash = new HashMap();
    private Object[][] batch;
    String doneLoading="no";
    Connection conn;
    int totalNumRows;
    int totalNumCols;
    SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, MMM d yyyy");
    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String lcid[];
    String[] headers;
    /**
     * Creates new form MachineSetup
     * @param passedConn
     */
    @SuppressWarnings("empty-statement")
    public ViewDistributionData(Connection passedConn) {
        
        
        setVisible(true);
        conn= passedConn;
        initComponents();
        initialise();
        refreshRoundCbx();
        
    }

    public void initialise(){
        datePicker.setDate(new java.util.Date() );
        
        
        try {
            
            String sql2="select upper(typename), ltid from laundrylinentype order by typename asc";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            headers=new String[rs2.getRow()+2];
            linenTypeID=new String[rs2.getRow()+1];
            totalNumCols=rs2.getRow()+2;
            headers[0]="Ward";
            linenTypeID[0]="";
            int counter1=1;
            if(rs2.getRow()>0){
                rs2.beforeFirst();
                
                while(rs2.next()){
                    headers[counter1]=rs2.getObject(1).toString();
                    linenTypeID[counter1]=rs2.getString("ltid");
                    counter1++;
                    
                }
            headers[counter1]="Delivered By";    
            counter1++;    
            }
            
            
            doneLoading="yes";

        } catch (SQLException ex) {
            Logger.getLogger(ViewDistributionData.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel3 = new javax.swing.JPanel();
        cbxRound = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Linen Distribution Data Report");
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

        jLabel2.setText("Round");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        cbxRound.setPreferredSize(new java.awt.Dimension(200, 20));
        cbxRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRoundActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel3.add(cbxRound, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
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
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
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
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
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
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
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

    private void cbxRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRoundActionPerformed
        System.out.println(cbxRound.getSelectedIndex());
        if(cbxRound.getSelectedIndex()>=0){
            generateTable(lcid[cbxRound.getSelectedIndex()]);
        }
    }//GEN-LAST:event_cbxRoundActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        refreshRoundCbx();
    }//GEN-LAST:event_datePickerPropertyChange

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        DistributionReportPdf newPdf= new DistributionReportPdf();
        if(cbxRound.getSelectedIndex()>=0){
            newPdf.generatePdfDistribution(lcid[cbxRound.getSelectedIndex()], (String) cbxRound.getSelectedItem(), conn);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    public void refreshRoundCbx(){
        cbxRound.removeAllItems();
        try {
            String tarehe=sdf2.format(datePicker.getDate());
            String sql2="select lcid,timestamp, time from linendistribution where date like '"+tarehe+"' order by lcid desc";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            if(rs2.getRow()>0){
                batch=new Object[0][totalNumCols];
                lcid=new String[rs2.getRow()];
                rs2.beforeFirst();
                int counter=0;
                while(rs2.next()){
                    lcid[counter]=rs2.getString("lcid");
                    cbxRound.addItem(rs2.getString("timestamp")+" "+rs2.getString("time"));
                    System.out.println(rs2.getString("lcid"));
                    counter++;
                }
                
                
            }
            RefreshClientsTable();
        } catch (SQLException ex) {
            Logger.getLogger(ViewDistributionData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void RefreshClientsTable(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(batch,
    headers
)
{
         @Override
            public boolean isCellEditable(int rowIndex,int colIndex){
                Boolean editable=true;
                if(colIndex==0){
                    editable= false;
                }
                return editable;
            }   

        });
        
        

    }
    private void generateTable(String colID){
        System.out.println(colID);
        String collectionID=colID;
        if(collectionID.equals("")){
            
        }
        else{
            jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsDistribution(conn,colID));     
        
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            col0.setPreferredWidth(250);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbxRound;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
