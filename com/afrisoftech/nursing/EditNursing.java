/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.nursing;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SPLMugi
 */
public class EditNursing extends javax.swing.JDialog {

    /**
     * Creates new form EditNursing
     */
    public static Vector editdata=new Vector();
     private static Vector editedRows = new Vector();
     private static Vector updateDetails = new Vector();
     private static HashMap updatedInfo= new HashMap();
     
    //N/B ----- Index0=table query Index1=delete query Index2=update index 3=visit id index 4=user index 5=reference index 6=reference
    public static Connection connectDB=null;
    public EditNursing(java.awt.Frame parent, boolean modal,Vector tableQuery,Connection conn) {
        super(parent, modal);
         initComponents();
         editdata=tableQuery;
         String editTableQuery=editdata.elementAt(0).toString().trim();
         connectDB=conn;
//          editTable = new com.afrisoftech.dbadmin.JTable();
         editTable.setShowGrid(true);

                editTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, editTableQuery));
                jTable1.setShowGrid(true);
        
    
    
    
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        editTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(new java.awt.Rectangle(150, 50, 1000, 700));
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setDividerLocation(120);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 254))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel1.setText("                            ");
        jToolBar1.add(jLabel1);

        delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nursing/DELETE.jpg"))); // NOI18N
        delete.setText("DELETE");
        delete.setBorder(null);
        delete.setFocusable(false);
        delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jToolBar1.add(delete);

        jLabel2.setText("        ");
        jLabel2.setToolTipText("");
        jToolBar1.add(jLabel2);

        update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nursing/save.jpg"))); // NOI18N
        update.setText("UPDATE");
        update.setBorder(null);
        update.setFocusable(false);
        update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        update.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jToolBar1.add(update);

        jLabel3.setText("        ");
        jToolBar1.add(jLabel3);

        back.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nursing/EXIT.jpg"))); // NOI18N
        back.setText("BACK");
        back.setBorder(null);
        back.setFocusable(false);
        back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jToolBar1.add(back);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 250, 0, 250);
        jPanel2.add(jToolBar1, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jSplitPane2.setDividerLocation(90);
        jSplitPane2.setDividerSize(0);

        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "SELECT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSplitPane2.setLeftComponent(jScrollPane1);

        editTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(editTable);

        jSplitPane2.setRightComponent(jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jSplitPane2, gridBagConstraints);

        jSplitPane1.setBottomComponent(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jSplitPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
                 javax.swing.JOptionPane dialog=null;
            
            int option= dialog.showConfirmDialog(this,"Are you sure to UPDATE?","CONFIRMATION",dialog.YES_NO_OPTION);
                      switch(option){
                case JOptionPane.YES_OPTION:
                {
                    if(editedRows.isEmpty()){
                        javax.swing.JOptionPane.showMessageDialog(this, "Please \nSELECT ROWS to UPDATE"
                                + "\nOn the SELECT COLUMN. ","",javax.swing.JOptionPane.ERROR_MESSAGE);
   
                    }
                    else if(!editedRows.isEmpty())
                    {
                         
        try{
            connectDB.setAutoCommit(false);
        String editTableQuery=editdata.elementAt(0).toString();
         int tblNmInt= editTableQuery.indexOf("FROM");
   String tblNm=editTableQuery.substring(tblNmInt+4,editTableQuery.indexOf("where"));
   System.out.println("Table Name  "+tblNm.trim());
        
         
        
        for(int y=0;y<editedRows.size();y++){
                System.out.println("the action will take place on this row is "+editedRows.elementAt(y));
           
        
        try{
    
     try{
         
         Vector delDetails= new Vector();
         delDetails=(Vector) updatedInfo.get(editedRows.elementAt(y));
         //patient_id, visit_id, action_user, action_time, action, reference_table, reference, details)

                 java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO nursing.deletion  VALUES ("
                         + "?, ?, ?,localtimestamp, ?,?, ?, ?);");
                             
                                pstmt.setObject(1,editTable.getModel().getValueAt((int) editedRows.elementAt(y),0).toString());
                                pstmt.setObject(2,editdata.elementAt(3).toString().trim());
                                pstmt.setObject(3,editdata.elementAt(4).toString().trim());
                               pstmt.setObject(4,"Update");
                                pstmt.setObject(5,editdata.elementAt(5).toString().trim());
                                pstmt.setObject(6,editdata.elementAt(6).toString().trim());
                                String details="";
                                for(int i=0;i<delDetails.size();i++){
                                   details= details.concat(delDetails.elementAt(i)+" ");
                                }
                                pstmt.setObject(7,details);
                               
                                pstmt.executeUpdate();
      System.out.println("iko shambamba kwa delete table waaaaaaa \n "+details);
     }
     catch(Exception del){
         System.out.println("Haiko shambamba kwa delete table waaaaaaa "+del);
     }
     /**
     * This is the end to insert into the delete table
     *The next step is deleting the information from the appropriate table
     *The update starts from below
     */
    
     
            
      /**
     * This is the end of deletion from the appropriate table
     *The next step is updating the information to the appropriate table
     *The update starts from below
     */
     
          try{
              
      
  PreparedStatement prest1 = connectDB.prepareStatement(editdata.elementAt(0).toString().trim());
  ResultSet rs = prest1.executeQuery();
  ResultSetMetaData rsMetaData = rs.getMetaData();
        int numberOfColumns= rsMetaData.getColumnCount();
        
        
    java.sql.PreparedStatement pstmt = connectDB.prepareStatement(editdata.elementAt(2).toString().trim()+"'"+editTable.getModel().getValueAt((int) editedRows.elementAt(y),0).toString()+"'");
                
                             
                                               for (int j = 0; j < numberOfColumns; j++) {

                    if (rs.getMetaData().getColumnType(j + 1) == java.sql.Types.NUMERIC) {

                        pstmt.setDouble(j + 1,new java.lang.Double(editTable.getModel().getValueAt((int) editedRows.elementAt(y),j).toString().trim()));

                    } else if ((rs.getMetaData().getColumnType(j + 1) == java.sql.Types.BOOLEAN) || (rs.getMetaData().getColumnType(j + 1) == java.sql.Types.BIT)) {

                        pstmt.setBoolean(j + 1, new java.lang.Boolean(editTable.getModel().getValueAt((int) editedRows.elementAt(y),j).toString().trim()));
                        
                         } 
                    else if ((rs.getMetaData().getColumnType(j + 1) == java.sql.Types.DATE)) {

                        pstmt.setDate(j + 1, Date.valueOf(editTable.getModel().getValueAt((int) editedRows.elementAt(y),j).toString().trim()));
                        
                         }
                    else 
                                 {
                        pstmt.setObject(j + 1, editTable.getModel().getValueAt((int) editedRows.elementAt(y),j).toString().trim());
                        }
                }
                               
                pstmt.executeUpdate();
      System.out.println("iko shambamba kwa update table waaaaaaa \n ");
    
    
    
    
          }
          catch(java.sql.SQLException sq){
              javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Update Error Message!first",javax.swing.JOptionPane.ERROR_MESSAGE);
               System.out.println("haiko shambamba kwa update table waaaaaaa  "+sq);
          }
     
     
     
    

}catch(Exception sq){
    System.out.println("haiko shambamba kwa update table waaaaaaa EEXCEptiion "+sq);
}
     ///////end of for loop                   
    }
               connectDB.commit();
    connectDB.setAutoCommit(true);
        
               
               javax.swing.JOptionPane.showMessageDialog(this,"Updated Successfully","",javax.swing.JOptionPane.INFORMATION_MESSAGE);
         editTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, editdata.elementAt(0).toString().trim()));
    }
    catch(Exception upda){
    javax.swing.JOptionPane.showMessageDialog(this,upda.getMessage(),"UPDATING  Error Message!second",javax.swing.JOptionPane.ERROR_MESSAGE);
    
    
   
    try {
        connectDB.rollback();
    }catch (java.sql.SQLException sql){
        javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!second",javax.swing.JOptionPane.ERROR_MESSAGE);
    }

}
            for(int g=0;g<editedRows.size();g++){
                
                jTable1.getModel().setValueAt(false,Integer.valueOf(editedRows.elementAt(g).toString().trim()),0);
            }
       
            editedRows.removeAllElements();
        
                        
                        
                       System.out.println("YES OPTION "+option);
                }
                }
                    break;
                case JOptionPane.NO_OPTION:
                {
                    javax.swing.JOptionPane.showMessageDialog(this,"UPDATE Action aborted Successfully!!!","",javax.swing.JOptionPane.ERROR_MESSAGE);
   
                     System.out.println("NO OPTION");
                       for(int g=0;g<editedRows.size();g++){
                
                 jTable1.getModel().setValueAt(false,Integer.valueOf(editedRows.elementAt(g).toString().trim()),0);
            }
        editedRows.removeAllElements();
                }
                    break;
             
                      ////end of switch 
                      }
        
        
       
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       javax.swing.JOptionPane dialog=null;
            
            int option= dialog.showConfirmDialog(this,"Are you sure to DELETE?","CONFIRMATION",dialog.YES_NO_OPTION);
                      switch(option){
                case JOptionPane.YES_OPTION:
                {
                    if(editedRows.isEmpty()){
                        javax.swing.JOptionPane.showMessageDialog(this, "Please \nSELECT ROWS to DELETE"
                                + "\nOn the SELECT COLUMN. ","",javax.swing.JOptionPane.ERROR_MESSAGE);
   
                    }
                    else if(!editedRows.isEmpty())
                    {
                           
        
        try{   
            
            connectDB.setAutoCommit(false);
     for(int y=0;y<editedRows.size();y++)
        {
                System.out.println(Integer.valueOf(editedRows.elementAt(y).toString().trim())+"the action will take place on this row is "+editedRows.elementAt(y));
           
        
        try{
    
     try{
                 java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO nursing.deletion    VALUES ("
                         + " ?, ?, ?,localtimestamp, ?,?, ?, ?);");
                             
                                pstmt.setObject(1,editTable.getModel().getValueAt(Integer.valueOf(editedRows.elementAt(y).toString().trim()),0).toString());
                                pstmt.setObject(2,editdata.elementAt(3).toString().trim());
                                pstmt.setObject(3,editdata.elementAt(4).toString().trim());
                               pstmt.setObject(4,"Delete");
                                pstmt.setObject(5,editdata.elementAt(5).toString().trim());
                                pstmt.setObject(6,editdata.elementAt(6).toString().trim());
                                String details="";
                                for(int i=0;i<editTable.getModel().getColumnCount();i++){
                                   details= details.concat(editTable.getModel().getValueAt(Integer.valueOf(editedRows.elementAt(y).toString().trim()) ,i).toString()+" ");
                                }
                                pstmt.setObject(7,details);
                                pstmt.executeUpdate();
      System.out.println("iko shambamba kwa delete table waaaaaaa \n "+details);
     }
     catch(Exception del){
         System.out.println("Haiko shambamba kwa delete table waaaaaaa "+del);
     }
     /**
     * This is the end to insert into the delete table
     *The next step is deleting the information from the appropriate table
     *The deletion starts from below
     */
     
    
    java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement(editdata.elementAt(1).toString().trim()+"'"+editTable.getModel().getValueAt(Integer.valueOf(editedRows.elementAt(y).toString().trim()),0).toString()+"'");
    pstmt31.executeUpdate();
   
    
  


}catch(java.sql.SQLException sq){
    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!first",javax.swing.JOptionPane.ERROR_MESSAGE);
   

}
     ///////end of for loop                   
    }    connectDB.commit();
    connectDB.setAutoCommit(true);
     
               
              
                javax.swing.JOptionPane.showMessageDialog(this,"Deletion Successfull");
                  ////refresh the table data
    editTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, editdata.elementAt(0).toString().trim()));
    
    }
    catch(Exception del){
     javax.swing.JOptionPane.showMessageDialog(this, del.getMessage(),"DELETION  Error Message!first",javax.swing.JOptionPane.ERROR_MESSAGE);
     
      try {
        connectDB.rollback();
    }catch (java.sql.SQLException sql){
        javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Roll back  Error Message!second",javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
         for(int g=0;g<editedRows.size();g++){
                
                 jTable1.getModel().setValueAt(false,Integer.valueOf(editedRows.elementAt(g).toString().trim()),0);
            }
        editedRows.removeAllElements();
        
                    }
                    
                    
                    System.out.println("YES OPTION "+option);
                }
                    break;
                case JOptionPane.NO_OPTION:
                {
                    javax.swing.JOptionPane.showMessageDialog(this,"DELETE Action aborted Successfully!!!","",javax.swing.JOptionPane.ERROR_MESSAGE);
   
                     System.out.println("NO OPTION");
                       for(int g=0;g<editedRows.size();g++){
                
                 jTable1.getModel().setValueAt(false,Integer.valueOf(editedRows.elementAt(g).toString().trim()),0);
            }
        editedRows.removeAllElements();
                }
                    break;
             
                      ////end of switch 
                      }
        
        
     
    }//GEN-LAST:event_deleteActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
             int rowindex=jTable1.getSelectedRow();
             updateDetails.removeAllElements();
             editTable.setRowSelectionInterval(rowindex,rowindex );
              System.out.println("the row value  is "+jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0));
             if(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).equals(true)){
                 System.out.println("the row value  is "+jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0));
                   if(!editedRows.contains(rowindex)){
                editedRows.addElement(rowindex);
                            }
                   for(int k=0;k<editTable.getColumnCount();k++){
                       updateDetails.addElement(editTable.getModel().getValueAt(rowindex,k));
                   }
                   if(!updatedInfo.containsKey(rowindex)){
                       updatedInfo.put(rowindex, updateDetails);
                   }
                   
             }
             else 
                   if(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).equals(false)){
                 System.out.println("the row value  is "+jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0));
                   if(editedRows.contains(rowindex)){
                editedRows.removeElement(rowindex);
                            }
                   if(updatedInfo.containsKey(rowindex)){
                       updatedInfo.remove(rowindex);
                   }
                   
             }
          
            for(int y=0;y<editedRows.size();y++){
                System.out.println("the action will take place on this row is "+editedRows.elementAt(y));
            }
             
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditNursing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditNursing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditNursing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditNursing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
              //  Index0=table query Index1=delete query Index2=update index 3=visit id index 4=user index 5=reference_table index 6=reference
                Vector vee = new Vector();
                vee.addElement("SELECT patient_no, patient_name, admission_reason, ward,bed_no, wing,reffered_from, date_admitted, admission_no,invoice_no,diagnosed  FROM hp_admission where discharge=false limit 25 ;");
              vee.addElement("delete from hp_admission where patient_no=");
                vee.addElement("update hp_admission SET "
                        + " patient_no=?, patient_name=?, admission_reason=?, ward=?,bed_no=?, wing=?,"
                        + "reffered_from=?, date_admitted=?, admission_no=?,invoice_no=?,diagnosed=? "
                        + " where patient_no=");
                vee.addElement("6933");
                vee.addElement("Admin");
                vee.addElement("hp_admission");
                vee.addElement("hp admission");
                
                EditNursing dialog = new EditNursing(new javax.swing.JFrame(), true,vee,getconnection());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                 
                });
                dialog.setVisible(true);
            }
        });
    }
   public void mimi(){
   
   
   }
     public static Connection getconnection() {
        try {
           
             Class.forName("org.postgresql.Driver");
         
            Connection connection = null;
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/funsoft", "postgres","");
                    System.out.println("connection successsful");
              connectDB= connection;
        } catch (SQLException ex) {
            Logger.getLogger(EditNursing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditNursing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connectDB;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton delete;
    private javax.swing.JTable editTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
