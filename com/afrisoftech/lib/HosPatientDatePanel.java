/*
 * DatePanel.java
 *
 *
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author  Francis Waweru
 */
public class HosPatientDatePanel extends javax.swing.JDialog {

    int reportName;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.sql.Connection connectDB = null;
    java.util.Vector dateStartEnd = null;
    javax.swing.JSpinner beginDateSpinner = null;
    javax.swing.JSpinner endDateSpinner = null;
    String visitID=null;

    /** Creates new form DatePanel */
    public HosPatientDatePanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {

        super(parent, modal);

        reportName = repName;

        connectDB = connectDb;

        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);

        initComponents();

        //       return dateStartEnd;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField1111 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new com.afrisoftech.dbadmin.JTable();
        jButton51 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        previousVisitIDDialog = new javax.swing.JDialog();
        jSearchPanel3 = new javax.swing.JPanel();
        jTextField1114 = new javax.swing.JTextField();
        jSearchScrollPane3 = new javax.swing.JScrollPane();
        jSearchTable3 = new com.afrisoftech.dbadmin.JTable();
        jButton412 = new javax.swing.JButton();
        jButton513 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField36 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        nochkbx = new javax.swing.JCheckBox();
        jRadioButton2 = new javax.swing.JRadioButton();
        namechkbx = new javax.swing.JCheckBox();

        jSearchDialog1.setModal(true);
        jSearchDialog1.setUndecorated(true);
        jSearchDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        jTextField1111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel1.add(jTextField1111, gridBagConstraints);

        jSearchTable1.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable1.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable1MouseClicked(evt);
            }
        });
        jSearchScrollPane1.setViewportView(jSearchTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel1.add(jSearchScrollPane1, gridBagConstraints);

        jButton51.setText("Close");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog1.getContentPane().add(jSearchPanel1, gridBagConstraints);

        previousVisitIDDialog.setModal(true);
        previousVisitIDDialog.setUndecorated(true);
        previousVisitIDDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel3.setLayout(new java.awt.GridBagLayout());

        jTextField1114.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1114CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel3.add(jTextField1114, gridBagConstraints);

        jSearchTable3.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable3.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = ipSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable3MouseClicked(evt);
            }
        });
        jSearchScrollPane3.setViewportView(jSearchTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel3.add(jSearchScrollPane3, gridBagConstraints);

        jButton412.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(jButton412, gridBagConstraints);

        jButton513.setText("Close");
        jButton513.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton513ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(jButton513, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        previousVisitIDDialog.getContentPane().add(jSearchPanel3, gridBagConstraints);

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.add(jTextField1, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(datePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setMnemonic('c');
        jButton2.setText("Close dialog");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setMnemonic('P');
        jButton1.setText("Preview Detailed Statement");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Patient here", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel4.add(jLabel4, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jTextField36.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jTextField36, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton.setToolTipText("Search Patient's Name");
        searchButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel6, gridBagConstraints);

        jLabel21.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel4.add(jLabel21, gridBagConstraints);

        jTextField11.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jTextField11, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Out Patient");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(jRadioButton1, gridBagConstraints);

        buttonGroup1.add(nochkbx);
        nochkbx.setSelected(true);
        nochkbx.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(nochkbx, gridBagConstraints);

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("In Patient");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(jRadioButton2, gridBagConstraints);

        buttonGroup1.add(namechkbx);
        namechkbx.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(namechkbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(471, 288));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        jTextField36.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
        jTextField11.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1).toString());

        jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        this.jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        if (this.jTextField1111.getCaretPosition() < 3) {
            System.out.print("Nothing");
        } else {
            if (this.nochkbx.isSelected()) {
                if (jRadioButton1.isSelected()) {
                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select trim(patient_no) as patient_no,second_name||' '||first_name||' '||last_name as name from hp_patient_register where patient_no ILIKE '" + jTextField1111.getText().toString() + "%' ORDER BY second_name"));

                    
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);

                   

                } else {
                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select trim(patient_no) as patient_no,second_name||' '||first_name||' '||last_name as name from hp_inpatient_register where patient_no ILIKE '%" + jTextField1111.getText().toString() + "%' ORDER BY second_name"));

                  
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);


                    /* } catch(java.sql.SQLException sqlExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                    
                    }
                     */
                }
            } else {
                if (jRadioButton1.isSelected()) {
                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select trim(patient_no) as patient_no,second_name||' '||first_name||' '||last_name as name from hp_patient_register where second_name||' '||first_name||' '||last_name ILIKE '" + jTextField1111.getText().toString() + "%' ORDER BY second_name"));

                    /*try {
                    searchRowSet1.execute("select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_patient_register where second_name||' '||first_name||' '||last_name ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name");
                    
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                    new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Patient_no", false),
                    new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
                    
                    }));
                     */
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);


                    /*  } catch(java.sql.SQLException sqlExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                    
                    }
                     */
                } else {
                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select trim(patient_no) as patient_no,second_name||' '||first_name||' '||last_name as name from hp_inpatient_register where second_name||' '||first_name||' '||last_name ILIKE '%" + jTextField1111.getText().toString() + "%' ORDER BY second_name"));

                    /* try {
                    searchRowSet1.execute("select trim(patient_no) as scheme,second_name||' '||first_name||' '||last_name as name from hp_inpatient_register where second_name||' '||first_name||' '||last_name ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY second_name");
                    
                    jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                    new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Patient_no", false),
                    new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false)
                    
                    }));
                     */
                    jSearchTable1.setShowHorizontalLines(false);
                    jSearchScrollPane1.setViewportView(jSearchTable1);


                    /*  } catch(java.sql.SQLException sqlExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                    
                    }
                     */

                }

            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        if(reportName==12){
            
        System.out.println("Showing dialog");

        jSearchDialog1.dispose();
        if (this.nochkbx.isSelected()) {
            java.awt.Point point = this.jTextField36.getLocationOnScreen();
            previousVisitIDDialog.setLocation(point);
        } else {

            java.awt.Point point = this.jTextField11.getLocationOnScreen();
            previousVisitIDDialog.setLocation(point);
        }
        previousVisitIDDialog.setSize(400, 200);


       previousVisitIDDialog.setVisible(true);
        }
        
        else{
            
        
        if (!this.nochkbx.isSelected() && !(this.namechkbx.isSelected())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select Patient No. OR Name", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {
            searchButton11Clicked();
        }   
        }// Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed
    private void searchButton11Clicked() {

        System.out.println("Showing dialog");

        jSearchDialog1.dispose();
        if (this.nochkbx.isSelected()) {
            java.awt.Point point = this.jTextField36.getLocationOnScreen();
            jSearchDialog1.setLocation(point);
        } else {

            java.awt.Point point = this.jTextField11.getLocationOnScreen();
            jSearchDialog1.setLocation(point);
        }
        jSearchDialog1.setSize(400, 200);


        jSearchDialog1.setVisible(true);


    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.getReport(reportName);

        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jTextField1114CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1114CaretUpdate
        if (jTextField1114.getCaretPosition() < 4) {

            System.out.println("Nothing");
        } else {
            if (this.namechkbx.isSelected()) {
                jSearchTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, ""
                    + "SELECT hd.patient_no, (upper(hd.second_name||' '||hd.last_name)) as name, hd.first_name AS surname, "
                    + "(SELECT hp.sub_chief FROM hp_admission hp WHERE hp.patient_no = hd.patient_no order by 1 LIMIT 1) as unit_number,"
                    + "(SELECT hp.visit_id FROM hp_admission hp WHERE hp.patient_no = hd.patient_no order by 1 LIMIT 1) as VisitId"
                    + " from hp_inpatient_register hd where (upper(hd.second_name||' '||hd.first_name||' '||hd.last_name)) ILIKE '%" + jTextField1114.getText().toString() + "%' ORDER BY second_name"));
            jSearchTable3.setShowHorizontalLines(false);
            jSearchScrollPane3.setViewportView(jSearchTable3);

        } else {
            if (this.nochkbx.isSelected()) {
                jSearchTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, ""
                    + "SELECT hd.patient_no, (upper(hd.second_name||' '||hd.last_name)) as name,hd.first_name AS surname, "
                    + "(SELECT hp.sub_chief FROM hp_admission hp WHERE hp.patient_no = hd.patient_no order by 1 LIMIT 1) as unit_number, "
                    + "(SELECT hp.visit_id FROM hp_admission hp WHERE hp.patient_no = hd.patient_no order by 1 LIMIT 1) as VisitId "
                    + "from hp_inpatient_register hd where hd.patient_no ILIKE '%" + jTextField1114.getText().toString() + "%' or (SELECT hp.sub_chief FROM hp_admission hp WHERE hp.patient_no = hd.patient_no order by 1 LIMIT 1) ilike '%" + jTextField1114.getText().toString() + "%' ORDER BY second_name"));
            jSearchTable3.setShowHorizontalLines(false);
            jSearchScrollPane3.setViewportView(jSearchTable3);

        }
        }

        //            if (this.searchbyPatientNameChkbx.isSelected()) {
            //                jSearchTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, patient_name as name,visit_id from hp_admission where (upper(patient_name)) ILIKE '" + jTextField1114.getText().toString() + "%' AND visit_id IS NOT NULL  ORDER BY patient_name"));
            //
            //                jSearchTable3.setShowHorizontalLines(false);
            //                jSearchScrollPane3.setViewportView(jSearchTable3);
            //
            //
            //
            //            } else {
            //                if (this.searchbyPatientNumberChkbx.isSelected()) {
                //                    jSearchTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, patient_name as name,visit_id from hp_admission where patient_no ILIKE '" + jTextField1114.getText().toString() + "%' AND visit_id IS NOT NULL ORDER BY patient_no"));
                //
                //                    jSearchTable3.setShowHorizontalLines(false);
                //                    jSearchScrollPane3.setViewportView(jSearchTable3);
                //
                //
                //                }
            //            }
        }   // Add your handling code here:
    }//GEN-LAST:event_jTextField1114CaretUpdate

    private void jSearchTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable3MouseClicked

        visitID=this.jSearchTable3.getValueAt(this.jSearchTable3.getSelectedRow(), 4).toString().trim();
        this.jTextField36.setText(this.jSearchTable3.getValueAt(this.jSearchTable3.getSelectedRow(), 0).toString().trim());
        
            previousVisitIDDialog.dispose();
            // Add your handling code here:
    }//GEN-LAST:event_jSearchTable3MouseClicked

    private void jButton513ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton513ActionPerformed
        previousVisitIDDialog.dispose();    // Add your handling code here:
    }//GEN-LAST:event_jButton513ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //    new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }

    public java.util.Vector getBeginEndDates() {

        dateStartEnd = new java.util.Vector(1, 1);

        dateStartEnd.addElement(beginDateSpinner.getValue().toString());

        dateStartEnd.addElement(endDateSpinner.getValue().toString());

        return dateStartEnd;

    }

    public void getReport(int reportName) {

        switch (reportName) {






            case 12: {
                com.afrisoftech.reports.PatientStatementPdf policy = new com.afrisoftech.reports.PatientStatementPdf();

                policy.PatientStatementPdf(connectDB,this.visitID, this.datePicker2.getDate(),  this.jTextField36.getText());


                this.dispose();

            }
            break;

            /*  case 7:
            
            {
            com.afrisoftech.reports.ReceiptsperPatientPdf policy = new com.afrisoftech.reports.ReceiptsperPatientPdf();
            
            policy.ReceiptsperPatientPdf(connectDB, this.datePicker1.getDate().toString(), this.datePicker2.getDate().toString(),jComboBox1.getSelectedItem().toString());
            
            
            this.dispose();
            
            } break;
             */
            case 10: {
                com.afrisoftech.reports.DetailedPatientStatementPdf policy = new com.afrisoftech.reports.DetailedPatientStatementPdf();

                policy.DetailedPatientStatementPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), this.jTextField36.getText());

                this.dispose();

            }
            break;

            case 20: {
                com.afrisoftech.hospinventory.DrugsPerPatientPdf policy = new com.afrisoftech.hospinventory.DrugsPerPatientPdf();

                policy.DrugsPerPatientPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), this.jTextField36.getText(), jTextField11.getText());

                this.dispose();

            }
            break;


            case 5267: {
                com.afrisoftech.reports.PatientCardPdf policy = new com.afrisoftech.reports.PatientCardPdf();

                policy.PatientCardPdf(connectDB, datePicker1.getDate(), datePicker2.getDate(), jTextField36.getText());

                this.dispose();

            }
            break;

            case 90: {
                // com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();

                //policy.PatientLabResultsPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),this.jTextField36.getText());

                //this.dispose();

                com.afrisoftech.reports.PatientLabResultsDetPdf policy = new com.afrisoftech.reports.PatientLabResultsDetPdf();

                policy.PatientLabResultsDetPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), this.jTextField36.getText());


            }

            default:
                ;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton412;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton513;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JPanel jSearchPanel3;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JScrollPane jSearchScrollPane3;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTable jSearchTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField1114;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JCheckBox namechkbx;
    private javax.swing.JCheckBox nochkbx;
    private javax.swing.JDialog previousVisitIDDialog;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
