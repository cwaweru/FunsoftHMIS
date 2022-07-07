/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.laundry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


/**
 *
 * @author charlie
 */
public class linenSetup extends javax.swing.JInternalFrame {
    private Object[][] batch;
    Connection conn;
    String[][] ltid;
    String currFunction="new";
    /**
     * Creates new form MachineSetup
     */
    public linenSetup(Connection passedConn) {
        setVisible(true);
        initComponents();
        conn=passedConn;
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
        btnAdd = new javax.swing.JButton();
        txtLinenType = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLinenDesc = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Linen Type Setup");
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("New Linen Type"));
        jPanel3.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel3.setMinimumSize(new java.awt.Dimension(140, 60));
        jPanel3.setPreferredSize(new java.awt.Dimension(51, 39));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(-20, 0, 0, 0);
        jPanel3.add(jLabel1, gridBagConstraints);

        btnAdd.setText("Save");
        btnAdd.setPreferredSize(new java.awt.Dimension(71, 23));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(btnAdd, gridBagConstraints);

        txtLinenType.setColumns(20);
        txtLinenType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLinenTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(txtLinenType, gridBagConstraints);

        jLabel3.setText("Linen Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel3.add(jLabel3, gridBagConstraints);

        txtLinenDesc.setColumns(20);
        txtLinenDesc.setRows(5);
        jScrollPane2.setViewportView(txtLinenDesc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(-20, 10, 0, 0);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        jButton4.setText("Clear");
        jButton4.setPreferredSize(new java.awt.Dimension(71, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Types"));
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.setMinimumSize(new java.awt.Dimension(71, 23));
        btnEdit.setPreferredSize(new java.awt.Dimension(71, 23));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(btnEdit, gridBagConstraints);

        btnRemove.setText("Remove");
        btnRemove.setMaximumSize(new java.awt.Dimension(100, 23));
        btnRemove.setMinimumSize(new java.awt.Dimension(100, 23));
        btnRemove.setPreferredSize(new java.awt.Dimension(100, 23));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel4.add(btnRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);
        jPanel4.getAccessibleContext().setAccessibleName("Current Types");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(currFunction.equals("new")){
            try {
            String linenType=txtLinenType.getText();
            String desc=txtLinenDesc.getText();
            String sql="insert into laundrylinentype (typename, description, subtype) values('"+linenType+"','"+desc+"', 'general')";
            Statement stmt = conn.createStatement();
            stmt.execute( sql );
            populateTable();
            JOptionPane.showMessageDialog(null,"Linen Type Added!","Success",JOptionPane.INFORMATION_MESSAGE);
            txtLinenType.setText("");
            txtLinenDesc.setText("");
        } 
            catch (SQLException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            try {
            String linentypename=txtLinenType.getText();
            String desc=txtLinenDesc.getText();
            int currFuncInt=Integer.parseInt(currFunction);
            String dutyidedit=ltid[currFuncInt][0];
            String sql="update laundrylinentype set typename='"+linentypename+"', description='"+desc+"' where ltid = "+dutyidedit+"";
            System.out.println(currFuncInt+", "+currFunction+", "+dutyidedit);
            Statement stmt = conn.createStatement();
            stmt.execute( sql );
            populateTable();
            JOptionPane.showMessageDialog(null,"Linen Type Edited!","Success",JOptionPane.INFORMATION_MESSAGE);
            txtLinenType.setText("");
            txtLinenDesc.setText("");
            currFunction="new";
            btnEdit.setEnabled(false);
        } 
            catch (SQLException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtLinenTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLinenTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLinenTypeActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int currFuncInt=Integer.parseInt(currFunction);
        String editCode=(String) batch[currFuncInt][0];
        txtLinenType.setText(editCode);
        txtLinenDesc.setText(ltid[currFuncInt][1]);
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtLinenType.setText("");
        txtLinenDesc.setText("");
        currFunction="new";
        btnEdit.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if(!currFunction.equals("new")){
            try {
                int currFuncInt=Integer.parseInt(currFunction);
                String dutyidedit=ltid[currFuncInt][0];
                String sql="delete from laundrylinentype where ltid = "+dutyidedit+"";
                System.out.println(currFuncInt+", "+currFunction+", "+dutyidedit);
                Statement stmt = conn.createStatement();
                stmt.execute( sql );
                populateTable();
                JOptionPane.showMessageDialog(null,"Linen Type Deleted!","Success",JOptionPane.INFORMATION_MESSAGE);
                txtLinenType.setText("");
                txtLinenDesc.setText("");
                currFunction="new";
                btnEdit.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(linenSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String rowSelected=Integer.toString(jTable1.getSelectedRow());
        btnEdit.setEnabled(true);
        currFunction=rowSelected;
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
       
    }//GEN-LAST:event_jTable1FocusLost

    public void populateTable(){
        try {
            
            String sql2="select typename,description, ltid from laundrylinentype where subtype like 'general' order by typename asc";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            if(rs2.getRow()>0){
                batch=new Object[rs2.getRow()][1];
                ltid=new String[rs2.getRow()][2];
                rs2.beforeFirst();
                int counter=0;
                while(rs2.next()){
                    batch[counter][0]=(rs2.getString("typename"));
                    ltid[counter][0]=rs2.getString("ltid");
                    ltid[counter][1]=rs2.getString("description");
                    counter++;
                }
                
                
            }
            RefreshClientsTable();
            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            //col0.setPreferredWidth(20);
            DefaultTableCellRenderer rrendr=new DefaultTableCellRenderer();
            rrendr.setHorizontalAlignment(JLabel.CENTER);
            rrendr.setOpaque(true);
            

            col0.setCellRenderer(rrendr);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error: "+ex.toString());

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
                return editable;
            }

        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtLinenDesc;
    private javax.swing.JTextField txtLinenType;
    // End of variables declaration//GEN-END:variables
}