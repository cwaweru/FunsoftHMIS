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
public final class EditCollectionData extends javax.swing.JInternalFrame {
    private Object[][] batch;
    Connection conn;
    String[][] lcids;
    String currFunction="new";
    /**
     * Creates new form MachineSetup
     * @param passedConn
     */
    public EditCollectionData(Connection passedConn) {
        setVisible(true);
        initComponents();
        conn= passedConn;
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

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Collection Rounds");
        setPreferredSize(new java.awt.Dimension(500, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        btnEdit.setText("Edit");
        btnEdit.setMaximumSize(new java.awt.Dimension(100, 23));
        btnEdit.setMinimumSize(new java.awt.Dimension(71, 23));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 23));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(btnEdit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        jLabel2.setText("Linen Collection Rounds");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jLabel2, gridBagConstraints);

        getAccessibleContext().setAccessibleName("Collection Rounds");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String[] currLcid=lcids[jTable1.getSelectedRow()];
        EditCollectionData1 mpya=new EditCollectionData1(null, true, currLcid, conn );
        mpya.setVisible(true);
        populateTable();
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String rowSelected=Integer.toString(jTable1.getSelectedRow());
        btnEdit.setEnabled(true);
        currFunction=rowSelected;
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
       
    }//GEN-LAST:event_jTable1FocusLost

    public void populateTable(){
        try {
            
            String sql2="select lcid,timestamp, time from linencollection order by lcid desc";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            if(rs2.getRow()>0){
                batch=new Object[rs2.getRow()][1];
                lcids=new String[rs2.getRow()][3];
                rs2.beforeFirst();
                int counter=0;
                while(rs2.next()){
                    batch[counter][0]=(rs2.getString("timestamp")+" "+rs2.getString("time"));
                    lcids[counter][0]=rs2.getString("lcid");
                    lcids[counter][1]=rs2.getString("timestamp");
                    lcids[counter][2]=rs2.getString("time");
                    counter++;
                }
                
                
            }
            RefreshClientsTable();
            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            col0.setPreferredWidth(20);
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
        "Linen Collection Rounds"   }
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
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
