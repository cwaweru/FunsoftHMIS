/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.reports;

import com.afrisoftech.lib.ClearTable;

/**
 *
 * @author sytem partners
 */
public class AttendanceSheetViewIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form AttendanceSheetViewIntfr
     */
    public static java.sql.Connection connectDB = null;
    java.lang.String patCat = null;

     com.afrisoftech.lib.DBObject dbObject;
    public AttendanceSheetViewIntfr(java.sql.Connection connDb) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        summaryTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        newPatientsCbx = new javax.swing.JCheckBox();
        revisitsCbx = new javax.swing.JCheckBox();
        allPatientsCbx = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ATTENDANCE REPORT VIEW");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        summaryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Patient", "Name", "Age", "Gender", "Diagnosis", "Residence", "Date", "Time Registered", "Time Seen", "Registered By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(summaryTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jTextField1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(datePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(datePicker2, gridBagConstraints);

        jButton1.setMnemonic('o');
        jButton1.setText("Preview Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(newPatientsCbx);
        newPatientsCbx.setText("New Patients");
        newPatientsCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPatientsCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(newPatientsCbx, gridBagConstraints);

        buttonGroup1.add(revisitsCbx);
        revisitsCbx.setText("Revisits");
        revisitsCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisitsCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(revisitsCbx, gridBagConstraints);

        buttonGroup1.add(allPatientsCbx);
        allPatientsCbx.setSelected(true);
        allPatientsCbx.setText("All Patients");
        allPatientsCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPatientsCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(allPatientsCbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setCursor(new java.awt.Cursor(javax.swing.JFrame.WAIT_CURSOR));

        try {

            ClearTable.clearthisTable(summaryTbl);
            
           
            java.sql.Statement st11 = connectDB.createStatement();
                            int cash = 0;
                            int scheme = 0;
                            int noSeq = 0;

                            java.sql.PreparedStatement st1 = null;
                            java.sql.PreparedStatement st = null;
                            java.sql.PreparedStatement st2 = null;
                            java.sql.ResultSet rset = null;
                            java.sql.ResultSet rset1 = null;
                            java.sql.ResultSet rset2 = null;

                            double ages = 0;
                           // System.out.println("THIS IS THE LENGTH  "+listofAct.length);
                            
                            if(newPatientsCbx.isSelected()){
                            patCat="New";
                            }
                            else if(revisitsCbx.isSelected()){
                            patCat="Old";
                            } 
                            else if(allPatientsCbx.isSelected()){
                            patCat="All";
                            
                            }
                            
                             java.lang.Object[] listofAct = this.getListofActivities();
                            for (int i = 0; i < listofAct.length; i++) {
                                String coTime = "-";
                                String diagnosis = "-";
                                System.out.println("item" + listofAct[i]);
                                if (patCat.equalsIgnoreCase("All")) {
                                    st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),pb.age, pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name) FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.date::date BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no ORDER BY pb.date::date DESC limit 1");
                                } else {
                                    if (patCat.equalsIgnoreCase("New")) {
                                        st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),pb.age, pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name) FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.date::date BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'New' ORDER BY pb.date::date DESC limit 1");
                                    } else {
                                        if (patCat.equalsIgnoreCase("Old")) {
                                            st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),pb.age, pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name) FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.date::date BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'Old'  ORDER BY pb.date::date DESC limit 1");
                                        }
                                    }
                                }
                                st = connectDB.prepareStatement("SELECT INITCAP(disease) AS disease FROM hp_patient_diagnosis pr WHERE pr.date_discharged::date BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "' and pr.patient_no = ?");
                                st2 = connectDB.prepareStatement("SELECT curr_date::TIME(0) FROM pb_doctors_request pr WHERE pr.curr_date::date BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "' and pr.patient_no = ? ORDER BY 1 ASC LIMIT 1");

                                st1.setString(1, listofAct[i].toString());
                                rset = st1.executeQuery();

                                st.setString(1, listofAct[i].toString());
                                rset1 = st.executeQuery();

                                st2.setString(1, listofAct[i].toString());
                                rset2 = st2.executeQuery();

                                while (rset1.next()) {

                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    diagnosis = dbObject.getDBObject(rset1.getObject(1), "-");
                                    //      table.addCell(phrase);
                                }

                                while (rset2.next()) {

                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    coTime = dbObject.getDBObject(rset2.getObject(1), "-");
                                    //      table.addCell(phrase);
                                }
                                while (rset.next()) {

                                    
                                    noSeq = noSeq + 1;
                                    summaryTbl.setValueAt(noSeq, i, 0);
                                    
                                    summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(1), "-"),i,1);

                                    summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(2), "-"), i,2);

                                    ages = rset.getDouble(3);
                                    if (ages < 1) {
                                        summaryTbl.setValueAt("<1",i,3);

                                       
                                    } else {
                                        //     phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                        summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ages)),i,3);

                                    }
                                    
                                    summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(4), "-"),i,4);

                                    
                                    summaryTbl.setValueAt(diagnosis, i,5);

                                     summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(6), "-"), i,6);

                                   summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(8), "-"), i,7);

                                    summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(9), "-"),i,8);

                                    summaryTbl.setValueAt(coTime, i,9);

                                    summaryTbl.setValueAt(dbObject.getDBObject(rset.getObject(10), "-"), i,10);

                                    

                                }
                            }
        this.setCursor(new java.awt.Cursor(javax.swing.JFrame.DEFAULT_CURSOR));

        } catch(java.sql.SQLException SqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

        }
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void newPatientsCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPatientsCbxActionPerformed
        // TODO add your handling code here:
         ClearTable.clearthisTable(summaryTbl);
    }//GEN-LAST:event_newPatientsCbxActionPerformed

    private void revisitsCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisitsCbxActionPerformed
        // TODO add your handling code here:
         ClearTable.clearthisTable(summaryTbl);
    }//GEN-LAST:event_revisitsCbxActionPerformed

    private void allPatientsCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPatientsCbxActionPerformed
        // TODO add your handling code here:
         ClearTable.clearthisTable(summaryTbl);
    }//GEN-LAST:event_allPatientsCbxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox allPatientsCbx;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox newPatientsCbx;
    private javax.swing.JCheckBox revisitsCbx;
    private javax.swing.JTable summaryTbl;
    // End of variables declaration//GEN-END:variables

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
            if (patCat.equalsIgnoreCase("All")) {
                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date::date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "'  order by 2 ASC");
            } else {
                if (patCat.equalsIgnoreCase("New")) {
                    rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date::date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "'  AND comments = 'New' order by 2 ASC");
                } else {
                    if (patCat.equalsIgnoreCase("Old")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date::date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()) + "' AND '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate()) + "'  AND comments = 'Old' order by 2 ASC");
                    }
                }
            }
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}

                System.out.println("description" + rSet1.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }

}
