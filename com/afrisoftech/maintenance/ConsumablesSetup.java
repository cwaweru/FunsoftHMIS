/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.maintenance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;


/**
 *
 * @author charlie
 */
public class ConsumablesSetup extends javax.swing.JInternalFrame {
    private Object[][] batch;
    Connection conn;
    List<String> brandTags= new ArrayList<String>();
    List<String> brandTagsTable= new ArrayList<String>();
    /**
     * Creates new form MachineSetup
     */
    public ConsumablesSetup() {
        setVisible(true);
        initComponents();
        dbConn mpya=new dbConn();
        conn=mpya.connector();
        refreshCbx();
        populateTable();
        
        
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

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTagNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxItemName = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Machine Setup");
        setPreferredSize(new java.awt.Dimension(500, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Store Items"));
        jPanel3.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel3.setMinimumSize(new java.awt.Dimension(140, 60));
        jPanel3.setPreferredSize(new java.awt.Dimension(51, 39));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Item Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel3.add(jLabel1, gridBagConstraints);

        txtTagNo.setColumns(10);
        txtTagNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTagNoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel3.add(txtTagNo, gridBagConstraints);

        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(-10, 0, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        cbxItemName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxItemName.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(-10, 0, 0, 0);
        jPanel3.add(cbxItemName, gridBagConstraints);

        jButton1.setText("Add");
        jButton1.setPreferredSize(new java.awt.Dimension(71, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Laundry Consumables"));
        jPanel4.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel4.setMinimumSize(new java.awt.Dimension(140, 60));
        jPanel4.setPreferredSize(new java.awt.Dimension(51, 39));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable1.setTableHeader(null);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String itemCodeDelete=brandTagsTable.get(jTable1.getSelectedRow());
            String itemcode=brandTags.get(cbxItemName.getSelectedIndex());
            String sql="delete from laundryconsumables WHERE item_code LIKE '"+itemCodeDelete+"' ";
            Statement stmt = conn.createStatement();
            stmt.execute( sql );
            refreshCbx();
            populateTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTagNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTagNoActionPerformed
        String tagNo=txtTagNo.getText();
        if(brandTags.indexOf(tagNo)==-1){
            JOptionPane.showMessageDialog(null,"Invalid Item Code!","Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
            cbxItemName.setSelectedIndex(brandTags.indexOf(tagNo));
        }
        cbxItemName.setSelectedItem(tagNo);
        
    }//GEN-LAST:event_txtTagNoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String itemcode=brandTags.get(cbxItemName.getSelectedIndex());
            String sql="insert into laundryconsumables (item_code) VALUES('"+itemcode+"') ";
            Statement stmt = conn.createStatement();
            stmt.execute( sql );
            refreshCbx();
            populateTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void populateTable(){
        try {
            
            String sql="select item_code, brand from public.st_main_stock_item WHERE item_code IN (SELECT item_code FROM laundryconsumables) order by brand ";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            int r=rs.getRow();
            batch = new Object[r][1];
            brandTagsTable.clear();
            rs.beforeFirst();
            int counter=0;
            while ( rs.next() ) {
                batch[counter][0]=rs.getString("brand");
                brandTagsTable.add(rs.getString("item_code"));
                counter++;
            }
            RefreshClientsTable();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error: "+ex.toString());

       }
        
    }
    
    public void refreshCbx(){
        try {
            String sql2="select brand, item_code from st_main_stock_item where department LIKE 'laundry' AND item_code NOT IN (SELECT item_code FROM laundryconsumables) order by brand";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery(sql2 );
            cbxItemName.removeAllItems();
            brandTags.clear();
            while(rs2.next()){
                cbxItemName.addItem(rs2.getString("brand"));
                brandTags.add(rs2.getString("item_code"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsumablesSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void RefreshClientsTable(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(batch,
    new String [] {
        ""   }
)
{
            @Override
            public boolean isCellEditable(int rowIndex,int colIndex){
                Boolean editable=false;
                if(colIndex==2){
                    editable= true;
                }
                return editable;
            }

        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxItemName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtTagNo;
    // End of variables declaration//GEN-END:variables
}
