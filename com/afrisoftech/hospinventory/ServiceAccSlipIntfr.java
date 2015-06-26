/*
 * companyprflintfr.java
 *
 * Created on August 13, 2002, 11:36 AM
 */

package com.afrisoftech.hospinventory;

/**
 *
 * @author  root
 */
public class ServiceAccSlipIntfr extends javax.swing.JInternalFrame {
    
    private  javax.swing.JComboBox cmbox = new javax.swing.JComboBox();
    
    private javax.swing.JComboBox cmbox2;
    /** Creates new form companyprflintfr */
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    javax.swing.JSpinner dateSpinner = null;
    javax.swing.JSpinner dateSpinner1 = null;
    
    public ServiceAccSlipIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog2 = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton52 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton512 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchPanel2.setLayout(new java.awt.GridBagLayout());

        jSearchPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField113CaretUpdate(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel2.add(jTextField113, gridBagConstraints);

        jSearchTable2.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable2.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable2.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable2MouseClicked(evt);
            }
        });

        jSearchScrollPane2.setViewportView(jSearchTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel2.add(jSearchScrollPane2, gridBagConstraints);

        jButton52.setText("Close");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog2.getContentPane().add(jSearchPanel2, gridBagConstraints);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setBackground(new java.awt.Color(238, 241, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Service Acceptance Slip");
        setToolTipText("");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jButton1.setMnemonic('O');
        jButton1.setText("Ok");
        jButton1.setToolTipText("click to store data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton1, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.setToolTipText("Click to close");
        jButton4.setSelected(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton4, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Utopia", 3, 18));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        getContentPane().add(jLabel12, gridBagConstraints);

        jButton31.setMnemonic('l');
        jButton31.setText("Clear");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton31, gridBagConstraints);

        jButton512.setMnemonic('R');
        jButton512.setText("RemoveRow");
        jButton512.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton512ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jButton512, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 245, 208));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Department", "Acceptance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setShowHorizontalLines(false);
        jTable1.setSurrendersFocusOnKeystroke(true);

        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(177, 224, 181));
        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jCheckBox3.setBackground(new java.awt.Color(255, 181, 255));
        jCheckBox3.setText("Search By  No.");
        buttonGroup1.add(jCheckBox3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jCheckBox3, gridBagConstraints);

        jCheckBox4.setBackground(new java.awt.Color(255, 153, 255));
        jCheckBox4.setText("Search By Name");
        buttonGroup1.add(jCheckBox4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jCheckBox4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(244, 237, 255));
        jPanel3.setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jLabel9.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel9, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(new javax.swing.border.EtchedBorder());
        jTextField9.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jTextField9, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton.setToolTipText("Search");
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
        gridBagConstraints.gridy = 2;
        jPanel4.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel4, gridBagConstraints);

        jLabel1.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel1, gridBagConstraints);

        jTextField1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel3.add(jTextField1, gridBagConstraints);

        jCheckBox2.setText("Refferal");
        buttonGroup3.add(jCheckBox2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jCheckBox2, gridBagConstraints);

        jCheckBox5.setText("Review");
        buttonGroup3.add(jCheckBox5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jCheckBox5, gridBagConstraints);

        jCheckBox6.setText("Normal ");
        buttonGroup3.add(jCheckBox6);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jCheckBox6, gridBagConstraints);

        jLabel2.setText("Payment Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel3.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jTextField2, gridBagConstraints);

        jLabel3.setText("Doctor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel3.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jCheckBox1.setText("Click to View Departments");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel1, gridBagConstraints);

        jButton2.setMnemonic('h');
        jButton2.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton2, gridBagConstraints);

        jRadioButton1.setText("Outpatients");
        buttonGroup2.add(jRadioButton1);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setText("Others");
        buttonGroup2.add(jRadioButton2);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        getContentPane().add(jRadioButton2, new java.awt.GridBagConstraints());

        setBounds(0, 0, 579, 389);
    }//GEN-END:initComponents
    
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        this.jTextField1.setEditable(false);     // Add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed
    
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        this.jTextField1.setEditable(true);        // Add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed
    
    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
        jTextField1.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        jTextField9.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());
           jTextField2.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString());
   
        this.jSearchDialog2.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked
    
    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        jSearchDialog2.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed
    
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchButtonClicked2();          // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed
    
    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        
        
        if(jTextField113.getCaretPosition() < 3){
            System.out.println("Nothing");
        }else{
            if(this.jCheckBox3.isSelected()){
                     jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT patient_no, (upper(second_name||' '||first_name||' '||last_name)) as name, pay_mode from hp_patient_register where patient_no ILIKE '"+jTextField113.getText()+"%' and last_visit > current_date - 20 order by second_name"));
       
             
                    jSearchTable2.setShowHorizontalLines(false);
                    jSearchScrollPane2.setViewportView(jSearchTable2);
                    
                    
              
                
            }else{
                
                 jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT patient_no, (upper(second_name||' '||first_name||' '||last_name)) as name,pay_mode from hp_patient_register where second_name||' '||first_name||' '||last_name ILIKE '"+jTextField113.getText()+"%' and last_visit > current_date - 20 order by second_name"));
       
                    jSearchTable2.setShowHorizontalLines(false);
                    jSearchScrollPane2.setViewportView(jSearchTable2);
                    
             
            }
        }        // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate
    private void searchButtonClicked2() {
        
        System.out.println("Showing dialog");
        
        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField9.getLocationOnScreen();
        
        jSearchDialog2.setSize(400,200);
        
        jSearchDialog2.setLocation(point);
        
        jSearchDialog2.setVisible(true);
        
        
        
    }
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        jButton1.setEnabled(true);
        int i = 0;
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            
            
            java.sql.Statement stmtTable1 = connectDB.createStatement();
            
            java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT revenue_type,null as boolean FROM pb_revenues");
            
            while (rsetTable1.next()) {
                
                System.out.println("Working at table row "+i);
                jTable1.setValueAt(rsetTable1.getObject(1), i, 0);
                jTable1.setValueAt(rsetTable1.getObject(2), i, 1);
                
                i++;
                
                //                i = i + i;
                //            jTable1.setValueAt(rsetTable1.getObject(1), i, 0);
            }
            
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
            
        }      // Add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setVisible(false);     // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        for (int k = 0; k < jTable1.getRowCount(); k++ ) {
            for (int r = 0; r < jTable1.getColumnCount(); r++ ) {
                jTable1.getModel().setValueAt(null,k,r);
            }
        }
        //   this.jComboBox1.setSelectedItem(null);
        //this.jComboBox13.setSelectedItem(null);
        //        this.jComboBox11.setSelectedItem(null);
        this.jTextField9.setText("");
        this.jTextField1.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed
    
    private void jButton512ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton512ActionPerformed
        int rows2Delete = jTable1.getSelectedRowCount();
        
        int[] selectedRows = jTable1.getSelectedRows();
        
        if (rows2Delete < 1) {
            
            java.awt.Toolkit.getDefaultToolkit().beep();
            
            javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");
            
        } else {
            
            if (rows2Delete > 1) {
                
                for (int i = 0; i < selectedRows.length; i++) {
                    
                    
                    
                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel)jTable1.getModel();
                    
                    defTableModel.removeRow(selectedRows[i]);
                    
                }
                
                
                
            } else {
                
                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel)jTable1.getModel();
                
                defTableModel.removeRow(jTable1.getSelectedRow());
            }
        }  // Add your handling code here:
    }//GEN-LAST:event_jButton512ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        long dateNow = calendar.getTimeInMillis();
        
        java.sql.Date datenowSql= new java.sql.Date(dateNow);
        String Stock = null;
        String actCode = null;
        String transNo = null;
        
        String userName = null;
        
        
        
        double debitsTable1 = 0.00;
        
        double totalDebits = 0.00;
        
        double totalCredits = 0.00;
     /*
        for(int j = 0; j < jTable1.getRowCount(); j++) {
      
            if (jTable1.getModel().getValueAt(j, 1) != null) {
      
                debitsTable1 = debitsTable1 + Double.parseDouble(jTable1.getModel().getValueAt(j, 1).toString());
      
            }
      
        }
      
      
      */
        
        totalDebits = debitsTable1 + 0;
        System.out.println(totalDebits);
        
        //  totalCredits = creditsTable1 + creditsTable11 + creditsTable12 + creditsTable13;
        
        //  System.out.println(totalCredits);
        
        //System.out.println("Difference Debits V Credits = "+(totalDebits - totalCredits));
        
        //    if (totalDebits < 100) {
        
        //   javax.swing.JOptionPane.showMessageDialog(this, "Rate MUST balance! Out of balance by "+new com.afrisoftech.sys.Format2Currency().Format2Currency(Double.toString(100 - totalDebits)));
        
        /// } else {
        try {
            connectDB.setAutoCommit(false);
            java.sql.Statement ps = connectDB.createStatement();
            java.sql.ResultSet rs2 = ps.executeQuery("select current_user");
            while (rs2.next())
                userName = rs2.getObject(1).toString();
            
                        java.sql.ResultSet rst = ps.executeQuery("select nextval('visit_no_seq')");
            while (rst.next())
                rst.getObject(1).toString();
            
            transNo = rst.getObject(1).toString();
            
            for (int i = 0; i< jTable1.getRowCount(); i++) {
                if (jTable1.getModel().getValueAt(i,1) != null){
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_visit values(? , ?, ?, ?, ?, ? , ?, ?, ?, ?, ? , ?, ?, ?, ?)");
                    
                    pstmt.setString(1,jTextField9.getText());
                    pstmt.setString(2,jTextField1.getText());
                    pstmt.setString(3,"OP");
                    pstmt.setString(5,transNo);
                    pstmt.setString(6,"");
                    pstmt.setString(7,"0");
                    pstmt.setString(12,"0");
                    pstmt.setString(9,"0");
                    pstmt.setString(11,"'now'");
                    pstmt.setString(10,"");
                    pstmt.setString(13,jTextField3.getText());
                    pstmt.setString(4,jTextField2.getText());
                    pstmt.setObject(8,jTable1.getValueAt(i,0));
                    //  pstmt1.setObject(13,jTable1.getValueAt(i,1));
                    pstmt.setString(14,userName);
                    pstmt.setString(15,"'now'");
                     pstmt.setString(16,"Visit");
                    pstmt.executeUpdate();
                    
                }
                
            }
            
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully","Comfirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            for (int k = 0; k < jTable1.getRowCount(); k++ ) {
                for (int r = 0; r < jTable1.getColumnCount(); r++ ) {
                    jTable1.getModel().setValueAt(null,k,r);
                }
            }
            //   this.jComboBox1.setSelectedItem(null);
            //                this.jComboBox13.setSelectedItem(null);
            
            //  this.jButton1.setEnabled(false);
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
        }
    
    
    // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed




    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked
    
    private void populateTable2(java.lang.String patient_no) {
        
        
        
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton searchButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jButton52;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JButton jButton31;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton jButton512;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jSearchTable2;
    // End of variables declaration//GEN-END:variables
    
}
