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
public class InjuryIncidentInfoIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public InjuryIncidentInfoIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
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
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel211 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel212 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel511 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");
        nBCachedRowSet1.setCommand("select DISTINCT UPPER (res_code) as res_code FROM hr.hr_resource_acctype ORDER BY res_code");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        nBCachedRowSet2.setCommand("select DISTINCT UPPER (res_name) as res_name FROM hr.hr_resource_acctype ORDER BY res_name");
        nBCachedRowSet2.setConnectionSource(pConnDB);
        nBCachedRowSet3.setCommand("select DISTINCT UPPER (department_name) as name FROM hr.hr_department ORDER BY name");
        nBCachedRowSet3.setConnectionSource(pConnDB);
        nBCachedRowSet4.setCommand("SELECT DISTINCT  section FROM hr.hr_sections ORDER BY section");
        nBCachedRowSet4.setConnectionSource(pConnDB);
        nBCachedRowSet5.setCommand("select DISTINCT UPPER (station_name) as station FROM hr.hr_duty_stations ORDER BY station");
        nBCachedRowSet5.setConnectionSource(pConnDB);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INJURY AND INCIDENT IN FORMATION");
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
        gridBagConstraints.gridy = 2;
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

        fieldspanel11.setBorder(new javax.swing.border.TitledBorder("Enter the Required Details"));
        jLabel12.setText("Part Of Body Affected");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel12, gridBagConstraints);

        jLabel21.setText("Action Taken");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel21, gridBagConstraints);

        jLabel51.setText("Accident Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel51, gridBagConstraints);

        jLabel91.setText("Staff");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel91, gridBagConstraints);

        jLabel111.setText("Illness /Injury Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        fieldspanel11.add(jLabel111, gridBagConstraints);

        jComboBox2.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "res_code", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jComboBox2, gridBagConstraints);

        jLabel211.setText("Date Of Action");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel211, gridBagConstraints);

        jComboBox13.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet3, "name", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jComboBox13, gridBagConstraints);

        jLabel212.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel212, gridBagConstraints);

        jScrollPane1.setViewportBorder(new javax.swing.border.TitledBorder("Brief Illness/Injury Description"));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jScrollPane1, gridBagConstraints);

        jLabel511.setText("Case Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel511, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(datePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField4, gridBagConstraints);

        jLabel2.setText("Person Reported To");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Restricted Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel3, gridBagConstraints);

        jTextField5.setToolTipText("null");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField6, gridBagConstraints);

        jLabel5.setText("Time Of Day");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField8, gridBagConstraints);

        jLabel6.setText("Active Injury Agent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField9, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Injury Type", "Hospital Taken", "Physician Name", "Postal Address", "Telephone", "Return Date", "Lost Time"
            }
        ));
        jTable1.setToolTipText("null");
        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldspanel11.add(jTextField7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        mainpanel.add(fieldspanel11, gridBagConstraints);

        getContentPane().add(mainpanel, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 554, 412);
    }//GEN-END:initComponents
    
    private void newbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtnActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_res_access_control (res_id,res_type,dept,section,begin_date,exp_date,inforce,duty_station,remarks) values(?,?,?,?,?,?,?,?,?)");
            
            
            pstmt.setObject(1,jComboBox2.getSelectedItem().toString());
          //  pstmt.setObject(2,jComboBox11.getSelectedItem().toString());
            pstmt.setObject(3,jComboBox13.getSelectedItem().toString());
//            pstmt.setObject(4,jComboBox12.getSelectedItem().toString());
            pstmt.setObject(5,datePicker1.getDate());
            pstmt.setObject(6,datePicker2.getDate());
//            pstmt.setBoolean(7,jCheckBox1.isSelected());
//            pstmt.setObject(8,jComboBox1.getSelectedItem().toString());
            pstmt.setObject(9,jTextArea1.getText());
            
            
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
            
        }        // Add        // Add your handling code here:
    }//GEN-LAST:event_newbtnActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel jLabel511;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JButton insertbtm;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JPanel fieldspanel11;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JPanel datapanel02;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton helpbtn;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JButton updatebtn;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton editbtn;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JButton selectbtm;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel3;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainpanel;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JScrollPane jScrollPane2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet4;
    private javax.swing.JButton newbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JPanel editpanel2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private javax.swing.JLabel jLabel211;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JButton closebtn;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel helppanel2;
    // End of variables declaration//GEN-END:variables
    
}
