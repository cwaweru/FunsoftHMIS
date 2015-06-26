/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.nursing;

import java.util.Vector;

/**
 *
 * @author SPLMUGI
 */
public class NursingCareImplementation extends javax.swing.JDialog {

    /**
     * Creates new form NursingCareImplementation
     */
      static java.sql.Connection connectDB = null;
static org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    private static Vector formDetails = new Vector(); // patient no index=0 , visit id index=1 , ward index=2 , done by index=3 ,patient details index=4
   private static Vector implemented = new Vector();
    public NursingCareImplementation(java.awt.Frame parent, boolean modal,java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB,Vector vector) {
        super(parent, modal);
         formDetails=vector;
          connectDB = connDb;
        pConnDB = pconnDB;
        initComponents();
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        implementdate = new com.afrisoftech.lib.DatePicker();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        implementcolstable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        implementTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setDividerLocation(160);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NURSING CARE IMPLEMENTATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 254))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nursing/save.jpg"))); // NOI18N
        update.setText("IMPLEMENT");
        update.setFocusable(false);
        update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        update.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(update, gridBagConstraints);

        back.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nursing/EXIT.jpg"))); // NOI18N
        back.setText("Close");
        back.setFocusable(false);
        back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        back.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(back, gridBagConstraints);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("NURSING CARE IMPLEMENTATION PLAN FOR\n"+formDetails.elementAt(4).toString()
        );
        jScrollPane3.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(implementdate, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jSplitPane2.setDividerLocation(90);
        jSplitPane2.setDividerSize(0);

        implementcolstable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        implementcolstable.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        implementcolstable.setModel(new javax.swing.table.DefaultTableModel(
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
                "IMPLEMENT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        implementcolstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                implementcolstableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(implementcolstable);

        jSplitPane2.setLeftComponent(jScrollPane1);

        implementTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT  server_time,diagnosis, expected_outcome, plan_of_care, intervention, evaluation   FROM nursing.nursing_care_plan where implementation=false and patient_id='"+formDetails.elementAt(0).toString().trim()+"' and visit_id='"+formDetails.elementAt(1).toString().trim()+"' and  ward='"+formDetails.elementAt(2).toString().trim()+"' order by  server_time desc limit 24 ;")
        );
        jScrollPane2.setViewportView(implementTable);

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
        gridBagConstraints.ipadx = 538;
        gridBagConstraints.ipady = 313;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
               
                        try{
                            System.out.println("to be implemented is  "+implemented.size());
                             for(int i=0;i<implemented.size();i++){
                            String implement="UPDATE nursing.nursing_care_plan " +
                "   SET  implementation=?, implemented_by=?,implemented_on=? " +
                " WHERE server_time='"+implementTable.getModel().getValueAt(Integer.valueOf(implemented.elementAt(i).toString().trim()),0)+"' and implementation=false and patient_id='"+formDetails.elementAt(0).toString().trim()+"' and \n" +
                " visit_id='"+formDetails.elementAt(1).toString().trim()+"' and  ward='"+formDetails.elementAt(2).toString().trim()+"';";
                           System.out.println("implementation nursing care query ni "+implement);
                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement(implement);
                            pstmt.setBoolean(1, true);
                            pstmt.setObject(2, formDetails.elementAt(3));
                             pstmt.setDate(3,com.afrisoftech.lib.SQLDateFormat.getSQLDate(implementdate.getDate()));
                            pstmt.executeUpdate();
                            
                            implementcolstable.setValueAt(false, Integer.valueOf(implemented.elementAt(i).toString().trim()), 0);
                                }
                             implementTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT  server_time,diagnosis, expected_outcome, plan_of_care, intervention, evaluation   FROM nursing.nursing_care_plan where implementation=false and patient_id='"+formDetails.elementAt(0).toString().trim()+"' and visit_id='"+formDetails.elementAt(1).toString().trim()+"' and  ward='"+formDetails.elementAt(2).toString().trim()+"' order by  server_time desc limit 24 ;"));

                        }
                        catch(Exception implement){
                            System.out.println("implementation nursing care error ni "+implement);
                        }
                       
                  javax.swing.JOptionPane.showMessageDialog(this,"IMPLEMENTATION DONE \n Successfully","",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                  
    }//GEN-LAST:event_updateActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    private void implementcolstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_implementcolstableMouseClicked
       int rowindex=implementcolstable.getSelectedRow();
      
           if(implementcolstable.getModel().getValueAt(implementcolstable.getSelectedRow(),0).equals(true)){
              
                 System.out.println("the row value  is "+implementcolstable.getModel().getValueAt(implementcolstable.getSelectedRow(),0));
                   if(!implemented.contains(rowindex)){
                implemented.addElement(rowindex);
                            }
                                    
             }else
                  if(implementcolstable.getModel().getValueAt(implementcolstable.getSelectedRow(),0).equals(false)){
                 System.out.println("the row value  is "+implementcolstable.getModel().getValueAt(implementcolstable.getSelectedRow(),0));
                   if(implemented.contains(rowindex)){
                implemented.remove(rowindex);
                            }
                                    
             }
    }//GEN-LAST:event_implementcolstableMouseClicked

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
            java.util.logging.Logger.getLogger(NursingCareImplementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NursingCareImplementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NursingCareImplementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NursingCareImplementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NursingCareImplementation dialog = new NursingCareImplementation(new javax.swing.JFrame(), true,connectDB,pConnDB,formDetails);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTable implementTable;
    private javax.swing.JTable implementcolstable;
    private com.afrisoftech.lib.DatePicker implementdate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
