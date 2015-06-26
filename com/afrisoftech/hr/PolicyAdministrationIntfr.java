/*
 * PolicyAdministrationIntfr.java
 *
 * Created on January 27, 2005, 4:07 PM
 */

package com.afrisoftech.hr;

/**
 *
 * @author  Administrator
 */
public class PolicyAdministrationIntfr extends javax.swing.JInternalFrame {
       
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public PolicyAdministrationIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
}
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField1 = new javax.swing.JTextField();
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
        try {
            nBCachedRowSet3 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        mainpanel = new javax.swing.JPanel();
        datapanel02 = new javax.swing.JPanel();
        insertbtm = new javax.swing.JButton();
        selectbtm = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        closebtn = new javax.swing.JButton();
        editpanel2 = new javax.swing.JPanel();
        newbtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        helppanel2 = new javax.swing.JPanel();
        helpbtn = new javax.swing.JButton();
        fieldspanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jTextField241 = new javax.swing.JTextField();
        jTextField251 = new javax.swing.JTextField();
        jTextField261 = new javax.swing.JTextField();
        jTextField271 = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        datePicker3 = new com.afrisoftech.lib.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");
        nBCachedRowSet1.setCommand("SELECT DISTINCT staff_name FROM hr.hr_policy_accesstaff ORDER BY staff_name");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        nBCachedRowSet2.setCommand("SELECT DISTINCT UPPER(institution) as payer FROM hr.hr_policy_covers ORDER BY payer");
        nBCachedRowSet2.setConnectionSource(pConnDB);
        nBCachedRowSet3.setCommand("SELECT DISTINCT UPPER(job_title) as description FROM hr.hr_job_descriptions ORDER BY description");
        nBCachedRowSet3.setConnectionSource(pConnDB);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("POLICY ADMINISTRATION OPERATIONS");
        setVisible(true);
        mainpanel.setLayout(new java.awt.GridBagLayout());

        mainpanel.setBorder(new javax.swing.border.TitledBorder(""));
        datapanel02.setLayout(new java.awt.GridBagLayout());

        datapanel02.setBorder(new javax.swing.border.MatteBorder(null));
        insertbtm.setText("Insert");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        datapanel02.add(insertbtm, gridBagConstraints);

        selectbtm.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        datapanel02.add(selectbtm, gridBagConstraints);

        deletebtn.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        datapanel02.add(deletebtn, gridBagConstraints);

        updatebtn.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        datapanel02.add(updatebtn, gridBagConstraints);

        closebtn.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        datapanel02.add(closebtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        mainpanel.add(datapanel02, gridBagConstraints);

        editpanel2.setLayout(new java.awt.GridBagLayout());

        editpanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        newbtn.setText("New");
        newbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        editpanel2.add(newbtn, gridBagConstraints);

        cancelbtn.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        editpanel2.add(cancelbtn, gridBagConstraints);

        editbtn.setText("Edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        editpanel2.add(editbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        mainpanel.add(editpanel2, gridBagConstraints);

        helppanel2.setLayout(new java.awt.GridBagLayout());

        helppanel2.setBorder(new javax.swing.border.TitledBorder(""));
        helpbtn.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        helppanel2.add(helpbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        mainpanel.add(helppanel2, gridBagConstraints);

        fieldspanel11.setLayout(new java.awt.GridBagLayout());

        fieldspanel11.setBorder(new javax.swing.border.TitledBorder("Enter Insurance Covers And Insurers Details"));
        jLabel12.setText("Policy Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel12, gridBagConstraints);

        jLabel21.setText("Insurance Agent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel21, gridBagConstraints);

        jLabel31.setText("Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel31, gridBagConstraints);

        jLabel51.setText("Job Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel51, gridBagConstraints);

        jLabel61.setText("Payer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel61, gridBagConstraints);

        jLabel91.setText("Policy No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel91, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField241, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField251, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField261, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField271, gridBagConstraints);

        jLabel111.setText("Mature Date");
        fieldspanel11.add(jLabel111, new java.awt.GridBagConstraints());

        jComboBox2.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet2, "payer", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jComboBox2, gridBagConstraints);

        jComboBox3.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet3, "description", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jComboBox3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(datePicker3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.weighty = 1.0;
        mainpanel.add(fieldspanel11, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Enter the Required Details"));
        jLabel1.setText("Intervention Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Date Of comment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel6.setText("Medical");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel6, gridBagConstraints);

        jComboBox1.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "staff_name", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jComboBox1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10));
        jLabel4.setText("(Staff Accessing the Cover)\n\n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(datePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(datePicker2, gridBagConstraints);

        jScrollPane1.setBorder(new javax.swing.border.TitledBorder(null, "Comments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jPanel1.add(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainpanel.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainpanel, gridBagConstraints);

        setBounds(0, 0, 600, 412);
    }//GEN-END:initComponents
    
    private void newbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtnActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_policy_admin(policy_no,policy_type,insurance_agent,amount,job_type,mature_date,payer,staff_acc,intervention_date,comment_date,remarks) values(?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setObject(1,jTextField241.getText());
            pstmt.setObject(2,jTextField251.getText());
            pstmt.setObject(3,jTextField261.getText());
            pstmt.setObject(4,jTextField271.getText());
            pstmt.setObject(5,jComboBox3.getSelectedItem().toString());
             pstmt.setObject(6,datePicker3.getDate());
            pstmt.setObject(7,jComboBox2.getSelectedItem().toString());
            pstmt.setObject(8,jComboBox1.getSelectedItem().toString());
            pstmt.setObject(9,datePicker1.getDate());
            pstmt.setObject(10,datePicker2.getDate());
            pstmt.setObject(11,jTextArea1.getText());
            
            
            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
        }        // Add your h        // Add your handling code here:
    }//GEN-LAST:event_newbtnActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JTextField jTextField271;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JButton selectbtm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField241;
    private javax.swing.JLabel jLabel1;
    private com.afrisoftech.lib.DatePicker datePicker3;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private javax.swing.JTextField jTextField251;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton insertbtm;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel fieldspanel11;
    private javax.swing.JPanel datapanel02;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton helpbtn;
    private javax.swing.JButton newbtn;
    private javax.swing.JPanel editpanel2;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel jLabel111;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JButton updatebtn;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton editbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jComboBox2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JLabel jLabel6;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField261;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JButton closebtn;
    private javax.swing.JPanel helppanel2;
    // End of variables declaration//GEN-END:variables
    
}
