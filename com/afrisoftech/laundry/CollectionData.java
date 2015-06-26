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
public final class CollectionData extends javax.swing.JInternalFrame {
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
    /**
     * Creates new form MachineSetup
     * @param passedConn
     */
    @SuppressWarnings("empty-statement")
    public CollectionData(Connection passedConn) {
        
        
        setVisible(true);
        conn= passedConn;
        initComponents();
        initialise();
        
    }

    public void initialise(){
        datePicker.setDate(new java.util.Date() );
        Calendar cal = Calendar.getInstance();
        cbxhour.setSelectedItem(""+cal.get(Calendar.HOUR));
        cbxMin.setSelectedItem(""+cal.get(Calendar.MINUTE));
        cbxAMorPM.setSelectedItem("AM");
        if(cal.get(Calendar.AM_PM)==1){
            cbxAMorPM.setSelectedItem("PM");
        }
        cbxMin.removeAllItems();
        for(int i=0; i<60; i++){
            if(i<10){
                cbxMin.addItem("0"+i);
            }
            else{
                cbxMin.addItem(""+i);
            }
        }
        cbxMin.setSelectedItem(""+cal.get(Calendar.MINUTE));
        
        if(cal.get(Calendar.MINUTE)<10){
                cbxMin.setSelectedItem("0"+cal.get(Calendar.MINUTE));
            }
        try {
            String[] headers;
            
            String sql2="select upper(typename), ltid from laundrylinentype order by typename asc";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            headers=new String[rs2.getRow()+2];
            linenTypeID=new String[rs2.getRow()+1];
            totalNumCols=rs2.getRow()+1;
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
            String sql="select ward_name, ward_code from public.hp_wards order by ward_name desc";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            batch=new Object[rs.getRow()][counter1];
            totalNumRows=rs.getRow();
            int counter=0;
            rs.beforeFirst();
            while ( rs.next() ) {
                String wardName=rs.getString("ward_name");
                for(int counter1b=1; counter1b<counter1; counter1b++){
                    batch[counter][counter1b]="";
                }
                //mapAll.put(rs.getString("ward_name"), blank);
                batch[counter][0]=wardName;
                wards.add(rs.getString("ward_code"));
                counter++;
                
            }
            RefreshClientsTable(headers);
            JComboBox cbx=new JComboBox();
            sql2="select CONCAT(first_name,' ',middle_name) AS \"Name\", employee_no from master_file where department = 'Laundry Unit' order by first_name asc";
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            if(rs2.getRow()>0){
                rs2.beforeFirst();
                while(rs2.next()){
                    cbx.addItem(rs2.getString("Name"));
                    attendantsHash.put(rs2.getString("Name"), rs2.getString("employee_no"));
                }
                
                
            }
            
                jTable1.getColumnModel().getColumn(totalNumCols).setCellEditor(new DefaultCellEditor(cbx));
            
            doneLoading="yes";

        } catch (SQLException ex) {
            Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
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
        cbxhour = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbxMin = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbxAMorPM = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Linen Collection Data");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(datePicker, gridBagConstraints);

        jLabel2.setText("Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        cbxhour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel3.add(cbxhour, gridBagConstraints);

        jLabel3.setText(":");
        jPanel3.add(jLabel3, new java.awt.GridBagConstraints());

        jPanel3.add(cbxMin, new java.awt.GridBagConstraints());

        jLabel4.setText(" ");
        jPanel3.add(jLabel4, new java.awt.GridBagConstraints());

        cbxAMorPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        jPanel3.add(cbxAMorPM, new java.awt.GridBagConstraints());

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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSave.setText("Save");
        btnSave.setPreferredSize(new java.awt.Dimension(65, 23));
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

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(datePicker.getDate()!=null){
            
                int r2=0;
                String lcid=sdf4.format(new java.util.Date());
            try {
                Statement stmt3 = conn.createStatement();
                String tarehe=sdf2.format(datePicker.getDate());
                
                String currTime=(String) cbxhour.getSelectedItem()+":"+(String)cbxMin.getSelectedItem()+" "+(String)cbxAMorPM.getSelectedItem();
                String currTimeStamp=sdf3.format(datePicker.getDate());
                stmt3.execute( "insert into linencollection (date, time, timestamp, lcid) values('"+tarehe+"','"+currTime+"', '"+currTimeStamp+"','"+lcid+"'  )" );
                while(r2<totalNumRows){
                    //save all ward collections
                    String attendantID=(String) attendantsHash.get(jTable1.getValueAt(r2, totalNumCols));
                    for(int allCols=1; allCols<totalNumCols; allCols++){
                        String countValue=(String) jTable1.getValueAt(r2, allCols);
                        
                        if(countValue!=null&&!countValue.equals("")){
                            try {
                                Statement stmt2 = conn.createStatement();
                                stmt2.execute( "insert into linencollectionitem (count, wardid, typeid, lcolid, linenattendantid) values('"+countValue+"','"+wards.get(r2)+"', '"+linenTypeID[allCols]+"','"+lcid+"', '"+attendantID+"'  )" );

                            } catch (SQLException ex) {
                                Logger.getLogger(dutyAllocation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    
                    }
                    r2++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CollectionData.class.getName()).log(Level.SEVERE, null, ex);
            }
                Object[] options={"Continue","Exit"};
                int another=JOptionPane.showOptionDialog(null,"Collection Round Saved!","Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                
                if(another==1){
                try {
                    setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                else{
                    initialise();
                }

            }
        else{
            JOptionPane.showMessageDialog(null,"Please specify a date and time!","Error",JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void RefreshClientsTable(String[] headers){
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
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        col0.setPreferredWidth(250);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbxAMorPM;
    private javax.swing.JComboBox cbxMin;
    private javax.swing.JComboBox cbxhour;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
