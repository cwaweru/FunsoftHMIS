/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.theater;

import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MUGISPL
 */
public class openheartsurgerypnl extends javax.swing.JPanel {

    /**
     * Creates new form openheartsurgerypnl
     */
     java.sql.Connection connectDB = null;
      Vector details = new Vector();
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    private JPanel jpaneltoload1;
     com.afrisoftech.lib.DBObject dbObject;
    public openheartsurgerypnl(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB,Vector patDetails) {

        details=patDetails;
        connectDB = connDb;
         dbObject = new com.afrisoftech.lib.DBObject();

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

        jPanel97 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        postbtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        exitbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel97.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Count", "Surgeon", "Assistant Surgeon", "Scrub Nurse", "ECG Technician", "Anaesthetists", "Theatre Nurses", "Perfusionists"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setValueAt(NursingTheatre.surgeon, 0, 1);
        jTable1.setValueAt(NursingTheatre.assistant_surgeon, 0, 2);
        jTable1.setValueAt(NursingTheatre.scrub_nurse, 0, 3);
        jTable1.setValueAt(NursingTheatre.anaesthetists, 0, 5);
        jTable1.setValueAt(NursingTheatre.nurses, 0, 6);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel97.add(jScrollPane1, gridBagConstraints);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Count", "Time Patient Brought In", "start Time", "ON BY-Pass", "BY-Pass Time", "AORTIC CLAMP ON", "CROSS CLAMP TIME", "AORTIC CLAMP OFF", "OFF BY-PASS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel97.add(jScrollPane2, gridBagConstraints);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Count", "END SURGERY", "TOTAL URINE OUTPUT", "PROSTHESIS USED", "SIZE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel97.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel97, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        postbtn.setMnemonic('s');
        postbtn.setText("Save");
        postbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(postbtn, gridBagConstraints);

        cancelbtn.setMnemonic('l');
        cancelbtn.setText("Clear");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(cancelbtn, gridBagConstraints);

        exitbtn.setMnemonic('c');
        exitbtn.setText("Close");
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(exitbtn, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jLabel5, gridBagConstraints);

        jButton1.setMnemonic('r');
        jButton1.setText("Save & Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void postbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postbtnActionPerformed

        
         if(details.isEmpty()==false){
try {
             for(int count=0;count<4;count++){
                  if( jTable1.getValueAt(count, 0) == Boolean.FALSE &&
                  jTable2.getValueAt(count, 0) == Boolean.FALSE &&
                  jTable3.getValueAt(count, 0) == Boolean.FALSE){
                      System.out.println("CANT TELL ME NOTHING");
                    } else
            if( jTable1.getValueAt(count, 0) == Boolean.TRUE ||
                  jTable2.getValueAt(count, 0) == Boolean.TRUE ||
                  jTable3.getValueAt(count, 0) == Boolean.TRUE){
                
                   
                    connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(""
                    + "INSERT INTO nursing.open_heart_surgery( " +
"            patient_no, visit_id, surgeon, assistant_surgeon, scrub_nurse, " +
"            ecg_technician, anaesthetists, theatre_nurses, perfusionists, " +
"            time_patient_brought, start_time, on_by_pass, by_pass_time, aortic_clamp_on, " +
"            cross_clamp_time, aortic_clamp_off, off_by_pass, end_surgery, " +
"            toatal_urine_output, prosthesis_used, size, date, user_name, " +
"            booking_no,time_entered) " +
"    VALUES (?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?::date, ?,?,current_timestamp);");
                pstmt.setObject(1, details.elementAt(0).toString());
                pstmt.setObject(2,details.elementAt(1).toString());
                {
                     pstmt.setObject(3,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 1),""));
                     pstmt.setObject(4,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 2),""));
                     pstmt.setObject(5,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 3),""));
                     pstmt.setObject(6,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 4),""));
                     pstmt.setObject(7,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 5),""));
                     pstmt.setObject(8,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 6),""));
                     pstmt.setObject(9,dbObject.getDBObject(jTable1.getModel().getValueAt(count, 7),""));
                   
                     
                }
                 {
                       pstmt.setObject(10,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 1),""));
                     pstmt.setObject(11,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 2),""));
                     pstmt.setObject(12,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 3),""));
                     pstmt.setObject(13,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 4),""));
                     pstmt.setObject(14,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 5),""));
                     pstmt.setObject(15,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 6),""));
                     pstmt.setObject(16,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 7),""));
                      pstmt.setObject(17,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 8),""));
                   
                     
                }
                  {
                        
                pstmt.setObject(18,dbObject.getDBObject(jTable2.getModel().getValueAt(count, 1),""));   
                    pstmt.setObject(19,dbObject.getDBObject(jTable3.getModel().getValueAt(count, 2),""));
                     pstmt.setObject(20,dbObject.getDBObject(jTable3.getModel().getValueAt(count, 3),""));
                      pstmt.setObject(21,dbObject.getDBObject(jTable3.getModel().getValueAt(count, 4),""));
                     
                }
                
                  pstmt.setObject(22,details.elementAt(2).toString());
                 pstmt.setObject(23,details.elementAt(3).toString());
                 // pstmt.setObject(24,details.elementAt(4).toString());
                  pstmt.setObject(24,details.elementAt(5).toString());
                
                pstmt.executeUpdate();
                 connectDB.commit();
                    connectDB.setAutoCommit(true);
            }
             }
             
                cancelbtn.doClick();

                JOptionPane.showMessageDialog(null,"Data Saved successfully.\n Nice Time.");

            }
            catch(Exception anaesthetic){
                 try {
                        connectDB.rollback();
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                java.util.logging.Logger.getLogger(Implantformpnl.class.getName()).log(Level.SEVERE,null,anaesthetic);
                JOptionPane.showMessageDialog(null,"Data not Saved successfully.\nTry Again ");

            }
        }else
        {
            JOptionPane.showMessageDialog(null,"Please ensure yo select patient No and Date first.\n& CONTINUE");

        }
        
    }//GEN-LAST:event_postbtnActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
     for(int count=0;count<4;count++){
         for(int i=0;i<jTable1.getModel().getColumnCount();i++){
                     jTable1.getModel().setValueAt(null, count, i);
                }
                 for(int i=0;i<jTable2.getModel().getColumnCount();i++){
                    jTable2.getModel().setValueAt(null, count, i);
                }
                  for(int i=0;i<jTable3.getModel().getColumnCount();i++){
                   jTable3.getModel().setValueAt(null, count, i);
                }
     }
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        jpaneltoload1= new JPanel();
           jpaneltoload1 = (JPanel)new CancelTheatre();
              this.removeAll();
                this.setLayout(new java.awt.BorderLayout());
                this.add(jpaneltoload1, java.awt.BorderLayout.CENTER);
                  this.updateUI();
    }//GEN-LAST:event_exitbtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            postbtn.doClick();
            cancelbtn.doClick();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    public static javax.swing.JButton exitbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton postbtn;
    // End of variables declaration//GEN-END:variables
}
