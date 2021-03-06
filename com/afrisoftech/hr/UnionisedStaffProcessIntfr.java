/*
 * StaffLoanProcessingIntfr.java
 *
 * Created on February 1, 2005, 6:24 PM
 */

package com.afrisoftech.hr;

/**
 *
 * @author  root
 */
public class UnionisedStaffProcessIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public UnionisedStaffProcessIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
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

        try {
            nBCachedRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet2 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainPanel3 = new javax.swing.JPanel();
        labelPanel3 = new javax.swing.JPanel();
        imgPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        actionsPanel3 = new javax.swing.JPanel();
        newAction3 = new javax.swing.JButton();
        updateAction3 = new javax.swing.JButton();
        deleteAction3 = new javax.swing.JButton();
        clearAction3 = new javax.swing.JButton();
        spacerPanel3 = new javax.swing.JPanel();
        helpPanel3 = new javax.swing.JPanel();
        helpAction3 = new javax.swing.JButton();
        subMainPanel3 = new javax.swing.JPanel();
        fieldsPanel3 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dataPanel3 = new javax.swing.JPanel();
        firstAction3 = new javax.swing.JButton();
        previousAction3 = new javax.swing.JButton();
        nextAction2 = new javax.swing.JButton();
        lastAction3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jTextField63 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jComboBox111 = new javax.swing.JComboBox();
        jLabel311 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jTextField66 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();

        nBCachedRowSet1.setCommand("SELECT DISTINCT  union_id FROM hr.hr_union_info ORDER BY union_id");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        nBCachedRowSet2.setCommand("SELECT DISTINCT  union_type FROM hr.hr_union_info ORDER BY union_type");
        nBCachedRowSet2.setConnectionSource(pConnDB);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("UNION INFORMATION");
        setVisible(true);
        mainPanel3.setLayout(new java.awt.GridBagLayout());

        labelPanel3.setLayout(new java.awt.GridBagLayout());

        labelPanel3.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel3.setLayout(new java.awt.GridBagLayout());

        imgPanel3.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel3.add(jLabel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel3.add(imgPanel3, gridBagConstraints);

        actionsPanel3.setLayout(new java.awt.GridBagLayout());

        actionsPanel3.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction3.setMnemonic('w');
        newAction3.setText("New");
        newAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(newAction3, gridBagConstraints);

        updateAction3.setMnemonic('U');
        updateAction3.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(updateAction3, gridBagConstraints);

        deleteAction3.setMnemonic('D');
        deleteAction3.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(deleteAction3, gridBagConstraints);

        clearAction3.setMnemonic('C');
        clearAction3.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(clearAction3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel3.add(spacerPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel3.add(actionsPanel3, gridBagConstraints);

        helpPanel3.setLayout(new java.awt.GridBagLayout());

        helpPanel3.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction3.setMnemonic('H');
        helpAction3.setText("Help");
        helpPanel3.add(helpAction3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel3.add(helpPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel3.add(labelPanel3, gridBagConstraints);

        subMainPanel3.setLayout(new java.awt.GridBagLayout());

        subMainPanel3.setBorder(new javax.swing.border.TitledBorder(""));
        fieldsPanel3.setLayout(new java.awt.GridBagLayout());

        fieldsPanel3.setBorder(new javax.swing.border.TitledBorder("Bearer's Information"));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Staff ID.", "Staff Name", "Date", "Union Reg. No.", "Registration Fee", "Subscription Fee"
            }
        ));
        jScrollPane11.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        fieldsPanel3.add(jScrollPane11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        subMainPanel3.add(fieldsPanel3, gridBagConstraints);

        dataPanel3.setLayout(new java.awt.GridBagLayout());

        dataPanel3.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction3.setMnemonic('F');
        firstAction3.setText("First");
        dataPanel3.add(firstAction3, new java.awt.GridBagConstraints());

        previousAction3.setMnemonic('P');
        previousAction3.setText("Previous");
        dataPanel3.add(previousAction3, new java.awt.GridBagConstraints());

        nextAction2.setMnemonic('N');
        nextAction2.setText("Next");
        dataPanel3.add(nextAction2, new java.awt.GridBagConstraints());

        lastAction3.setMnemonic('L');
        lastAction3.setText("Last");
        dataPanel3.add(lastAction3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel3.add(dataPanel3, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(new javax.swing.border.TitledBorder("Enter Details About The Union"));
        jLabel31.setText("Union ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel31, gridBagConstraints);

        jLabel41.setText("Union Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel41, gridBagConstraints);

        jLabel61.setText("Union Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel61, gridBagConstraints);

        jLabel71.setText("Geographical");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel71, gridBagConstraints);

        jLabel81.setText("Union Certificate ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel81, gridBagConstraints);

        jLabel91.setText("Union Offices");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel91, gridBagConstraints);

        jLabel101.setText("Office Bearers");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField63, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField61, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField64, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField65, gridBagConstraints);

        jComboBox111.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet2, "union_type", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox111, gridBagConstraints);

        jLabel311.setText("Chapter/Region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel311, gridBagConstraints);

        jComboBox11.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "union_id", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField66, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField62, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        subMainPanel3.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel3.add(subMainPanel3, gridBagConstraints);

        jTabbedPane1.addTab("", mainPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 682, 503);
    }//GEN-END:initComponents
    
    private void newAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction3ActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_union_info (union_id,union_type,office_bearers,name,offices,geographical,chapter,staff_id,staff_name,date,reg_no,regn_fee,subscription,union_cert_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if (jTable1.getModel().getValueAt(i,0) != null){
                    pstmt.setString(1,jComboBox11.getSelectedItem().toString());
                    pstmt.setString(2,jComboBox111.getSelectedItem().toString());                   
                    pstmt.setString(3,jTextField62.getText());
                    pstmt.setString(4,jTextField63.getText());
                    pstmt.setString(5,jTextField64.getText());
                    pstmt.setString(6,jTextField65.getText());
                    pstmt.setString(7,jTextField66.getText());
                    pstmt.setString(8,jTable1.getValueAt(i,0).toString());
                    pstmt.setString(9,jTable1.getValueAt(i,1).toString());
                    pstmt.setObject(10,jTable1.getValueAt(i,2).toString());
                    pstmt.setString(11,jTable1.getValueAt(i,3).toString());
                    pstmt.setString(12,jTable1.getValueAt(i,4).toString());
                    pstmt.setString(13,jTable1.getValueAt(i,5).toString());
                    pstmt.setObject(14,jTextField61.getText());
                    pstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);
                    javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
                    
                }   catch(java.sql.SQLException sq){
                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                    try {
                        connectDB.rollback();
                    }catch (java.sql.SQLException sql){
                        javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    
                }        // Add your handling code here:    // Add your handling code here:
    }//GEN-LAST:event_newAction3ActionPerformed
            
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JButton lastAction3;
    private javax.swing.JPanel imgPanel3;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JPanel actionsPanel3;
    private javax.swing.JButton updateAction3;
    private javax.swing.JButton clearAction3;
    private javax.swing.JPanel fieldsPanel3;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JPanel spacerPanel3;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JButton helpAction3;
    private javax.swing.JButton firstAction3;
    private javax.swing.JButton deleteAction3;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JButton nextAction2;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JComboBox jComboBox111;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JPanel dataPanel3;
    private javax.swing.JButton newAction3;
    private javax.swing.JButton previousAction3;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JLabel jLabel41;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel labelPanel3;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel subMainPanel3;
    private javax.swing.JPanel helpPanel3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
        }
