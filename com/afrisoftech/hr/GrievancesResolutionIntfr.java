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
public class GrievancesResolutionIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public GrievancesResolutionIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
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
        try {
            nBCachedRowSet4 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet5 = new org.netbeans.lib.sql.NBCachedRowSet();
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
        jLabel91 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jComboBox121 = new javax.swing.JComboBox();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        fieldspanel111 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel911 = new javax.swing.JLabel();
        jLabel1111 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox111 = new javax.swing.JComboBox();
        jComboBox122 = new javax.swing.JComboBox();
        jLabel2111 = new javax.swing.JLabel();
        jComboBox131 = new javax.swing.JComboBox();
        jLabel2122 = new javax.swing.JLabel();
        jLabel5111 = new javax.swing.JLabel();
        jComboBox123 = new javax.swing.JComboBox();
        datePicker11 = new com.afrisoftech.lib.DatePicker();
        jLabel21211 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        nBCachedRowSet1.setConnectionSource(pConnDB);

        nBCachedRowSet2.setConnectionSource(pConnDB);

        nBCachedRowSet3.setConnectionSource(pConnDB);

        nBCachedRowSet4.setConnectionSource(pConnDB);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("GRIEVANCES RESOLUTION");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        mainpanel.setLayout(new java.awt.GridBagLayout());

        datapanel02.setBorder(new javax.swing.border.MatteBorder(null));
        datapanel02.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        mainpanel.add(datapanel02, gridBagConstraints);

        editpanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editpanel2.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        mainpanel.add(editpanel2, gridBagConstraints);

        helppanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        helppanel2.setLayout(new java.awt.GridBagLayout());

        helpbtn.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        helppanel2.add(helpbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        mainpanel.add(helppanel2, gridBagConstraints);

        fieldspanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Settlement"));
        fieldspanel11.setLayout(new java.awt.GridBagLayout());

        jLabel91.setText("Settlement Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel91, gridBagConstraints);

        jLabel111.setText("Date Settled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel111, gridBagConstraints);

        jLabel211.setText("View Old Memo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel211, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jComboBox121, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        fieldspanel11.add(datePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField7, gridBagConstraints);

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Settlement Description"));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 83;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        fieldspanel11.add(jScrollPane1, gridBagConstraints);

        jScrollPane11.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Grievance Description"));
        jScrollPane11.setViewportView(jTextArea11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 83;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        fieldspanel11.add(jScrollPane11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        mainpanel.add(fieldspanel11, gridBagConstraints);

        fieldspanel111.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter the Required Details"));
        fieldspanel111.setLayout(new java.awt.GridBagLayout());

        jLabel121.setText("Memo ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel121, gridBagConstraints);

        jLabel911.setText("Grievance ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel911, gridBagConstraints);

        jLabel1111.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        fieldspanel111.add(jLabel1111, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jTextField6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jComboBox111, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jComboBox122, gridBagConstraints);

        jLabel2111.setText("Recieving Authority");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel2111, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jComboBox131, gridBagConstraints);

        jLabel2122.setText("Complainant");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel2122, gridBagConstraints);

        jLabel5111.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel5111, gridBagConstraints);

        jComboBox123.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox123.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jComboBox123, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        fieldspanel111.add(datePicker11, gridBagConstraints);

        jLabel21211.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jLabel21211, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel111.add(jTextField5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        mainpanel.add(fieldspanel111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainpanel, gridBagConstraints);

        setBounds(0, 0, 580, 552);
    }// </editor-fold>//GEN-END:initComponents
    
    private void newbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtnActionPerformed
        
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_grievances_res (grievances_id,memo_id,complainant,date,dept,rec_autho,stmt_code,stmt_date,old_memo,stmt_desc,comp_desc,section) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setObject(1,jTextField5.getText());
            pstmt.setObject(2,jComboBox111.getSelectedItem().toString());
            pstmt.setObject(3,jTextField6.getText());
            pstmt.setObject(4,datePicker1.getDate());
            pstmt.setObject(5,jComboBox122.getSelectedItem().toString());
            pstmt.setObject(6,jComboBox123.getSelectedItem().toString());
            pstmt.setObject(7,jTextField7.getText());
            pstmt.setObject(8,datePicker1.getDate());
            pstmt.setObject(9,jComboBox121.getSelectedItem().toString());
            pstmt.setObject(10,jTextArea1.getText());
            pstmt.setObject(11,jTextArea1.getText());
            pstmt.setObject(12,jComboBox131.getSelectedItem().toString());
            
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
            
        }        // Add your handling code here:
    }//GEN-LAST:event_newbtnActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton closebtn;
    private javax.swing.JPanel datapanel02;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker11;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JPanel editpanel2;
    private javax.swing.JPanel fieldspanel11;
    private javax.swing.JPanel fieldspanel111;
    private javax.swing.JButton helpbtn;
    private javax.swing.JPanel helppanel2;
    private javax.swing.JButton insertbtm;
    private javax.swing.JComboBox jComboBox111;
    private javax.swing.JComboBox jComboBox121;
    private javax.swing.JComboBox jComboBox122;
    private javax.swing.JComboBox jComboBox123;
    private javax.swing.JComboBox jComboBox131;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel1111;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel2111;
    private javax.swing.JLabel jLabel21211;
    private javax.swing.JLabel jLabel2122;
    private javax.swing.JLabel jLabel5111;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel911;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPanel mainpanel;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet4;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet5;
    private javax.swing.JButton newbtn;
    private javax.swing.JButton selectbtm;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
    
}