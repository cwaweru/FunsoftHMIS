/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.reports;

import com.afrisoftech.lib.ClearTable;

/**
 *
 * @author olson mutwiri
 */
public class OutpatientAttendanceSummaryIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form OutpatientAttendanceSummaryIntfr
     */
     public static java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    com.afrisoftech.lib.DBObject dbObject;
    
    public OutpatientAttendanceSummaryIntfr(java.sql.Connection connDb) {

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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        summaryTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("OUTPATIENT ATTENDANCE SUMMARY");
        setPreferredSize(new java.awt.Dimension(800, 430));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        summaryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Under 1 Year", null, null, null, null, null, null},
                {"1-4 Years", null, null, null, null, null, null},
                {"5-14 Years", null, null, null, null, null, null},
                {"15-24 Years", null, null, null, null, null, null},
                {"25-34 Years", null, null, null, null, null, null},
                {"35-44 Years", null, null, null, null, null, null},
                {"45-49 Years", "", null, null, null, null, null},
                {"50-54 Years", null, null, null, null, null, null},
                {"55-64 Years", null, null, null, null, null, null},
                {"Over 65 Years", null, null, null, null, null, null},
                {"All Ages", null, null, null, null, null, null}
            },
            new String [] {
                "AGE GROUPS", "Male", "Female", "Male", "Female", "Male", "Female"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("NEW ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 200, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("REVISIT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("TOTAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.setCursor(new java.awt.Cursor(javax.swing.JFrame.WAIT_CURSOR));

        try {
            
            ClearTable.clearthisTable(summaryTbl);
                            int totalMale = 0;
                            int totalFemale = 0;
                            int newMaleTotal = 0;
                            int newFemaleTotal = 0;
                            int oldMaleTotal = 0;
                            int oldFemaleTotal = 0;
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declalition
                                int newCount = 0;
                                int oldCount = 0;
                                int newMale = 0;
                                int newFemale = 0;
                                int oldMale = 0;
                                int oldFemale = 0;
                                
                                String Gender = null;
                                double lowerAge = 0;
                                double upperAge = 0;
                                int patNo1 = 0;
                                java.sql.ResultSet rsetAge = stw.executeQuery("SELECT min_age,max_age FROM patient_age WHERE description ilike '"+listofAct[i]+"'");
                                
                                while(rsetAge.next()){
                                    lowerAge = rsetAge.getDouble(1);
                                    upperAge = rsetAge.getDouble(2);
                                }
                                java.sql.ResultSet rset = st.executeQuery("SELECT SUM(count_no),TRIM(gender) AS gender FROM patient_analysis WHERE input_date::date BETWEEN '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate())+"' AND '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate())+"' AND age::numeric(10,2) BETWEEN '"+lowerAge+"' AND '"+upperAge+"' AND comments ilike 'new' GROUP BY 2 ORDER BY gender DESC");
                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT SUM(count_no),TRIM(gender) AS gender FROM patient_analysis WHERE input_date::date BETWEEN '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate())+"' AND '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker2.getDate())+"' AND age::numeric(10,2) BETWEEN '"+lowerAge+"' AND '"+upperAge+"' AND comments ilike 'old' GROUP BY 2 ORDER BY gender DESC");
         
                                while (rset.next()) {
                                    Gender = rset.getString(2);
                                    newCount = rset.getInt(1);
                                    
                                   if(Gender.equalsIgnoreCase("Male")){
                                        newMale = newCount;
                                    }else{
                                        if(Gender.equalsIgnoreCase("Female")){
                                            newFemale = newCount;
                                        }else{
                                            newFemale = 0;
                                            newMale = 0;
                                        }
                                    }
                                }
                                
                                while (rset1.next()) {
                                    Gender = rset1.getString(2);
                                    oldCount = rset1.getInt(1);
                                    
                                    
                                    if(Gender.equalsIgnoreCase("Male")){
                                        oldMale = oldCount;
                                    }else{
                                        if(Gender.equalsIgnoreCase("Female")){
                                            oldFemale = oldCount;
                                        }else{
                                            oldFemale = 0;
                                            oldMale = 0;
                                        }
                                    }
                                }
                                
                               ////setting the age group
                                summaryTbl.setValueAt(listofAct[i],i,0);
                                ///new male
                                summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale)),i,1);
                                newMaleTotal = newMaleTotal+newMale;
                                ///new Female
                               summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemale)),i,2);
                                newFemaleTotal = newFemaleTotal+newFemale;
       
                                ///old male
                                summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMale)),i,3);
                                oldMaleTotal = oldMaleTotal+oldMale;
                                
                                ///old female
                                summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldFemale)),i,4);
                                oldFemaleTotal = oldFemaleTotal+oldFemale;
                                ///total male
                                summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale+oldMale)),i,5);
                                totalMale = totalMale+(newMale+oldMale);
                                
                                ///total female
                               summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemale+oldFemale)),i,6);
                                totalFemale = totalFemale+(newFemale+oldFemale);
                               
                                
                            }
                            // }
                            
                          /////all ages
                           summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMaleTotal)),listofAct.length,1);
                            
                           summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemaleTotal)),listofAct.length,2);
                           
                           summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMaleTotal)),listofAct.length,3);
                     
                          summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldFemaleTotal)),listofAct.length,4);
                            
                          summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale)),listofAct.length,5);
                            
                            
                          summaryTbl.setValueAt(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalFemale)),listofAct.length,6);
                            
                           this.setCursor(new java.awt.Cursor(javax.swing.JFrame.DEFAULT_CURSOR));
                           
         } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable summaryTbl;
    // End of variables declaration//GEN-END:variables

     public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("select distinct initcap(description),min_age from patient_age order by min_age");
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_admission WHERE discharge = false ORDER BY patient_no");
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
