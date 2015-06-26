/*
 * DefaultInternalFrame.java
 *
 * Created on May 31, 2004, 1:53 PM
 */

package com.afrisoftech.hr;

/**
 * private  javax.swing.JComboBox cmbox1;
    private  javax.swing.JComboBox cmbox;
    private  javax.swing.JComboBox cmbox2;
    
    /** Creates new form loanpymntintfr */
    
    


public class StaffResumeDBIntfr extends javax.swing.JInternalFrame {
    
     private  javax.swing.JComboBox cmbox1;
    private  javax.swing.JComboBox cmbox;
    private  javax.swing.JComboBox cmbox2;
    
   
    
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public StaffResumeDBIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
        
                datePicker2.setDateFormatString("yyyy-m-d");
        datePicker3.setDateFormatString("yyyy-m-d");
       
        
        
            nBCachedRowSet1.setCommand("Select distinct(designation) from hr.hr_job_designation");
        nBCachedRowSet1.setConnectionSource(pConnDB);
          jComboBox31.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "designation", null, null, null));
          
           nBCachedRowSet2.setCommand("Select distinct(county_name) from hr.hr_districts");
        nBCachedRowSet2.setConnectionSource(pConnDB);
          jComboBox10.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet2, "county_name", null, null, null));
          
          jComboBox11.addItem("Health");
          jComboBox11.addItem("Management");
            jComboBox11.addItem("Logistics");
        jLabel411.setText("County");
        
    }
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
        try {
            nBCachedRowSet6 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet7 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet8 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet9 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet10 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet11 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet12 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet13 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet14 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet15 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet16 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet18 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet17 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jSearchDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField1111 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new javax.swing.JTable();
        jButton41 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jSearchDialog2 = new javax.swing.JDialog();
        jSearchPanel11 = new javax.swing.JPanel();
        jTextField11111 = new javax.swing.JTextField();
        jSearchScrollPane11 = new javax.swing.JScrollPane();
        jSearchTable11 = new javax.swing.JTable();
        jButton411 = new javax.swing.JButton();
        jButton511 = new javax.swing.JButton();
        try {
            searchRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet19 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet20 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        buttonPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        fieldsPanel3 = new javax.swing.JPanel();
        facilityidLbl1 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel122 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox();
        dataPanel3 = new javax.swing.JPanel();
        firstAction3 = new javax.swing.JButton();
        previousAction3 = new javax.swing.JButton();
        nextAction2 = new javax.swing.JButton();
        lastAction3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jTextField91 = new javax.swing.JTextField();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        datePicker3 = new com.afrisoftech.lib.DatePicker();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox122 = new javax.swing.JComboBox();
        jLabel41111 = new javax.swing.JLabel();
        jComboBox125 = new javax.swing.JComboBox();
        jLabel71111 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jComboBox71 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        mainPanel31 = new javax.swing.JPanel();
        labelPanel31 = new javax.swing.JPanel();
        imgPanel31 = new javax.swing.JPanel();
        jLabel231 = new javax.swing.JLabel();
        actionsPanel31 = new javax.swing.JPanel();
        newAction31 = new javax.swing.JButton();
        updateAction31 = new javax.swing.JButton();
        deleteAction31 = new javax.swing.JButton();
        clearAction31 = new javax.swing.JButton();
        spacerPanel31 = new javax.swing.JPanel();
        helpPanel31 = new javax.swing.JPanel();
        helpAction31 = new javax.swing.JButton();
        subMainPanel31 = new javax.swing.JPanel();
        buttonPanel31 = new javax.swing.JPanel();
        jLabel31141 = new javax.swing.JLabel();
        jPanel61131 = new javax.swing.JPanel();
        jTextField100 = new javax.swing.JTextField();
        searchButton131 = new javax.swing.JButton();
        fieldsPanel31 = new javax.swing.JPanel();
        facilityidLbl11 = new javax.swing.JLabel();
        jLabel1221 = new javax.swing.JLabel();
        facilityidLbl111 = new javax.swing.JLabel();
        facilityidLbl112 = new javax.swing.JLabel();
        facilityidLbl113 = new javax.swing.JLabel();
        facilityidLbl114 = new javax.swing.JLabel();
        facilityidLbl115 = new javax.swing.JLabel();
        facilityidLbl116 = new javax.swing.JLabel();
        facilitynameLbl111 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        dataPanel31 = new javax.swing.JPanel();
        firstAction31 = new javax.swing.JButton();
        previousAction31 = new javax.swing.JButton();
        nextAction21 = new javax.swing.JButton();
        lastAction31 = new javax.swing.JButton();
        jPanel121 = new javax.swing.JPanel();
        jLabel311 = new javax.swing.JLabel();
        jLabel411 = new javax.swing.JLabel();
        jLabel611 = new javax.swing.JLabel();
        jLabel711 = new javax.swing.JLabel();
        jTextField411 = new javax.swing.JTextField();
        jTextField611 = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox();
        jComboBox20 = new javax.swing.JComboBox();
        fieldsPanel311 = new javax.swing.JPanel();
        facilityidLbl117 = new javax.swing.JLabel();
        facilitynameLbl112 = new javax.swing.JLabel();
        facilityidLbl1111 = new javax.swing.JLabel();
        facilityidLbl1121 = new javax.swing.JLabel();
        facilityidLbl1131 = new javax.swing.JLabel();
        facilityidLbl1141 = new javax.swing.JLabel();
        facilityidLbl1151 = new javax.swing.JLabel();
        facilityidLbl1161 = new javax.swing.JLabel();
        facilitynameLbl1111 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        mainPanel1 = new javax.swing.JPanel();
        labelPanel1 = new javax.swing.JPanel();
        imgPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        actionsPanel1 = new javax.swing.JPanel();
        newAction1 = new javax.swing.JButton();
        updateAction1 = new javax.swing.JButton();
        deleteAction1 = new javax.swing.JButton();
        clearAction1 = new javax.swing.JButton();
        spacerPanel1 = new javax.swing.JPanel();
        helpPanel1 = new javax.swing.JPanel();
        helpAction1 = new javax.swing.JButton();
        subMainPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jLabel31142 = new javax.swing.JLabel();
        jPanel61132 = new javax.swing.JPanel();
        jTextField101 = new javax.swing.JTextField();
        searchButton132 = new javax.swing.JButton();
        fieldsPanel = new javax.swing.JPanel();
        qualificationidLbl = new javax.swing.JLabel();
        qualificationdescriptionLbl = new javax.swing.JLabel();
        departmentrequiringqualificationLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        dataPanel = new javax.swing.JPanel();
        firstAction = new javax.swing.JButton();
        previousAction = new javax.swing.JButton();
        nextAction = new javax.swing.JButton();
        lastAction = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        labelPanel = new javax.swing.JPanel();
        imgPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        newAction = new javax.swing.JButton();
        updateAction = new javax.swing.JButton();
        deleteAction = new javax.swing.JButton();
        clearAction = new javax.swing.JButton();
        spacerPanel = new javax.swing.JPanel();
        helpPanel = new javax.swing.JPanel();
        helpAction = new javax.swing.JButton();
        subMainPanel2 = new javax.swing.JPanel();
        buttonPanel2 = new javax.swing.JPanel();
        jPanel611311 = new javax.swing.JPanel();
        jTextField102 = new javax.swing.JTextField();
        searchButton1311 = new javax.swing.JButton();
        jLabel311411 = new javax.swing.JLabel();
        fieldsPanel2 = new javax.swing.JPanel();
        qualificationidLbl2 = new javax.swing.JLabel();
        qualificationidTxt2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        //  jPanel12.setBorder(new javax.swing.border.TitledBorder(""));
        dataPanel2 = new javax.swing.JPanel();
        firstAction2 = new javax.swing.JButton();
        previousAction2 = new javax.swing.JButton();
        nextAction3 = new javax.swing.JButton();
        lastAction2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        mainPanel2 = new javax.swing.JPanel();
        labelPanel2 = new javax.swing.JPanel();
        imgPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        actionsPanel2 = new javax.swing.JPanel();
        newAction2 = new javax.swing.JButton();
        updateAction2 = new javax.swing.JButton();
        deleteAction2 = new javax.swing.JButton();
        clearAction2 = new javax.swing.JButton();
        spacerPanel2 = new javax.swing.JPanel();
        helpPanel2 = new javax.swing.JPanel();
        helpAction2 = new javax.swing.JButton();
        subMainPanel21 = new javax.swing.JPanel();
        buttonPanel21 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        fieldsPanel21 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        dataPanel21 = new javax.swing.JPanel();
        firstAction21 = new javax.swing.JButton();
        previousAction21 = new javax.swing.JButton();
        nextAction31 = new javax.swing.JButton();
        lastAction21 = new javax.swing.JButton();
        jPanel611312 = new javax.swing.JPanel();
        jTextField103 = new javax.swing.JTextField();
        searchButton1312 = new javax.swing.JButton();
        jLabel311412 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        mainPanel311 = new javax.swing.JPanel();
        labelPanel311 = new javax.swing.JPanel();
        imgPanel311 = new javax.swing.JPanel();
        jLabel2311 = new javax.swing.JLabel();
        actionsPanel311 = new javax.swing.JPanel();
        newAction311 = new javax.swing.JButton();
        updateAction311 = new javax.swing.JButton();
        deleteAction311 = new javax.swing.JButton();
        clearAction311 = new javax.swing.JButton();
        spacerPanel311 = new javax.swing.JPanel();
        helpPanel311 = new javax.swing.JPanel();
        helpAction311 = new javax.swing.JButton();
        subMainPanel311 = new javax.swing.JPanel();
        buttonPanel311 = new javax.swing.JPanel();
        jPanel611313 = new javax.swing.JPanel();
        jTextField104 = new javax.swing.JTextField();
        searchButton1313 = new javax.swing.JButton();
        jLabel311413 = new javax.swing.JLabel();
        dataPanel311 = new javax.swing.JPanel();
        firstAction311 = new javax.swing.JButton();
        previousAction311 = new javax.swing.JButton();
        nextAction211 = new javax.swing.JButton();
        lastAction311 = new javax.swing.JButton();
        jPanel1211 = new javax.swing.JPanel();
        jLabel6111 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jLabel31111 = new javax.swing.JLabel();
        jTextField66 = new javax.swing.JTextField();
        jLabel311111 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        jPanel10 = new javax.swing.JPanel();
        fieldsPanel3111 = new javax.swing.JPanel();
        facilityidLbl11111 = new javax.swing.JLabel();
        facilityidLbl11211 = new javax.swing.JLabel();
        facilityidLbl11411 = new javax.swing.JLabel();
        facilityidLbl11611 = new javax.swing.JLabel();
        facilitynameLbl11111 = new javax.swing.JLabel();
        jTextField69 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel12111 = new javax.swing.JPanel();
        jLabel31112 = new javax.swing.JLabel();
        jLabel41112 = new javax.swing.JLabel();
        jLabel61111 = new javax.swing.JLabel();
        jLabel71112 = new javax.swing.JLabel();
        jTextField621 = new javax.swing.JTextField();
        jTextField641 = new javax.swing.JTextField();
        jComboBox511 = new javax.swing.JComboBox();
        jLabel311113 = new javax.swing.JLabel();
        jTextField661 = new javax.swing.JTextField();
        jTextField651 = new javax.swing.JTextField();
        jTextField631 = new javax.swing.JTextField();
        jLabel3111111 = new javax.swing.JLabel();
        jLabel3111121 = new javax.swing.JLabel();
        datePicker41 = new com.afrisoftech.lib.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        mainPanel21 = new javax.swing.JPanel();
        labelPanel21 = new javax.swing.JPanel();
        imgPanel21 = new javax.swing.JPanel();
        jLabel221 = new javax.swing.JLabel();
        actionsPanel21 = new javax.swing.JPanel();
        newAction21 = new javax.swing.JButton();
        updateAction21 = new javax.swing.JButton();
        deleteAction21 = new javax.swing.JButton();
        clearAction21 = new javax.swing.JButton();
        spacerPanel21 = new javax.swing.JPanel();
        helpPanel21 = new javax.swing.JPanel();
        helpAction21 = new javax.swing.JButton();
        subMainPanel211 = new javax.swing.JPanel();
        fieldsPanel211 = new javax.swing.JPanel();
        jScrollPane211 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        dataPanel211 = new javax.swing.JPanel();
        firstAction211 = new javax.swing.JButton();
        previousAction211 = new javax.swing.JButton();
        nextAction311 = new javax.swing.JButton();
        lastAction211 = new javax.swing.JButton();
        jLabel3114 = new javax.swing.JLabel();
        jPanel6113 = new javax.swing.JPanel();
        jTextField105 = new javax.swing.JTextField();
        searchButton13 = new javax.swing.JButton();
        qualifiableamountLbl = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        mainPanel5 = new javax.swing.JPanel();
        labelPanel5 = new javax.swing.JPanel();
        imgPanel5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        actionsPanel5 = new javax.swing.JPanel();
        newAction5 = new javax.swing.JButton();
        updateAction5 = new javax.swing.JButton();
        deleteAction5 = new javax.swing.JButton();
        clearAction5 = new javax.swing.JButton();
        spacerPanel5 = new javax.swing.JPanel();
        helpPanel5 = new javax.swing.JPanel();
        helpAction5 = new javax.swing.JButton();
        subMainPanel22 = new javax.swing.JPanel();
        buttonPanel22 = new javax.swing.JPanel();
        jPanel611314 = new javax.swing.JPanel();
        jTextField106 = new javax.swing.JTextField();
        searchButton1314 = new javax.swing.JButton();
        jLabel311414 = new javax.swing.JLabel();
        fieldsPanel22 = new javax.swing.JPanel();
        qualificationidLbl21 = new javax.swing.JLabel();
        qualificationidTxt21 = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        dataPanel22 = new javax.swing.JPanel();
        firstAction22 = new javax.swing.JButton();
        previousAction22 = new javax.swing.JButton();
        nextAction32 = new javax.swing.JButton();
        lastAction22 = new javax.swing.JButton();
        jPanel71 = new javax.swing.JPanel();
        mainPanel4 = new javax.swing.JPanel();
        labelPanel4 = new javax.swing.JPanel();
        imgPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        actionsPanel4 = new javax.swing.JPanel();
        newAction4 = new javax.swing.JButton();
        deleteAction4 = new javax.swing.JButton();
        clearAction4 = new javax.swing.JButton();
        spacerPanel4 = new javax.swing.JPanel();
        helpPanel4 = new javax.swing.JPanel();
        helpAction4 = new javax.swing.JButton();
        subMainPanel1 = new javax.swing.JPanel();
        fieldsPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane23 = new javax.swing.JScrollPane();
        jList11 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel312 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList12 = new javax.swing.JList();
        jScrollPane221 = new javax.swing.JScrollPane();
        jList111 = new javax.swing.JList();
        jButton21 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jLabel313 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jList13 = new javax.swing.JList();
        jScrollPane25 = new javax.swing.JScrollPane();
        jList112 = new javax.swing.JList();
        jButton22 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel314 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jList14 = new javax.swing.JList();
        jScrollPane26 = new javax.swing.JScrollPane();
        jList113 = new javax.swing.JList();
        jButton23 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel315 = new javax.swing.JLabel();
        jLabel3113 = new javax.swing.JLabel();
        jPanel6112 = new javax.swing.JPanel();
        jTextField363 = new javax.swing.JTextField();
        searchButton12 = new javax.swing.JButton();

        nBCachedRowSet1.setCommand("select distinct upper(specialization) as specialization from hr.hr_appresume_qualif order by specialization");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        nBCachedRowSet2.setCommand("select distinct upper(position) as position from hr.hr_rec_request order by position");
        nBCachedRowSet2.setConnectionSource(pConnDB);
        nBCachedRowSet4.setCommand("select distinct upper(district_name) as district from hr.hr_districts order by district");
        nBCachedRowSet4.setConnectionSource(pConnDB);
        nBCachedRowSet5.setCommand("select distinct upper(town_name) as town from hr.hr_towns order by town");
        nBCachedRowSet5.setConnectionSource(pConnDB);
        nBCachedRowSet6.setCommand("select distinct upper(level_id) as qual from hr.hr_appresume_qualif order by qual");
        nBCachedRowSet6.setConnectionSource(pConnDB);
        nBCachedRowSet7.setCommand("select DISTINCT UPPER (department_name) as name FROM hr.hr_department ORDER BY name");
        nBCachedRowSet7.setConnectionSource(pConnDB
        );
        nBCachedRowSet8.setCommand("select distinct upper(marital_desc) as marital from hr.hr_marital_status order by marital");
        nBCachedRowSet8.setConnectionSource(pConnDB);
        nBCachedRowSet9.setCommand("select distinct upper(tribe_name) as ethnic from hr.hr_ethnic_groups order by ethnic");
        nBCachedRowSet9.setConnectionSource(pConnDB);
        nBCachedRowSet10.setCommand("select distinct upper(department_name) as name from hr.hr_department order by name");
        nBCachedRowSet10.setConnectionSource(pConnDB);
        nBCachedRowSet11.setCommand("select distinct lower(type_desc) as type_desc from hr.hr_sport_type order by type_desc");
        nBCachedRowSet11.setConnectionSource(pConnDB);
        nBCachedRowSet12.setCommand("select distinct lower(type_desc) as type_desc from hr.hr_hobby_type order by type_desc");
        nBCachedRowSet12.setConnectionSource(pConnDB);
        nBCachedRowSet13.setCommand("select distinct lower(language_name) as language_name from hr.hr_languages order by language_name");
        nBCachedRowSet13.setConnectionSource(pConnDB);
        nBCachedRowSet14.setCommand("select distinct lower(club_name) as club_name from hr.hr_social_clubs order by club_name");
        nBCachedRowSet14.setConnectionSource(pConnDB);
        nBCachedRowSet15.setCommand("select DISTINCT UPPER (designation) as design FROM hr.hr_job_designation ORDER BY design");
        nBCachedRowSet15.setConnectionSource(pConnDB);
        nBCachedRowSet16.setCommand("SELECT DISTINCT section FROM hr.hr_sections ORDER BY section");
        nBCachedRowSet16.setConnectionSource(pConnDB);
        nBCachedRowSet18.setCommand("select distinct upper(group_desc) as jgroup from hr.hr_job_groupings order by jgroup");
        nBCachedRowSet18.setConnectionSource(pConnDB);
        nBCachedRowSet17.setCommand("select distinct upper(grade_desc) as jgrade from hr.hr_job_grade order by jgrade");
        nBCachedRowSet17.setConnectionSource(pConnDB);
        crset1.setConnectionSource(pConnDB);
        jSearchDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog1.setModal(true);
        jSearchDialog1.setUndecorated(true);
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(new javax.swing.border.EtchedBorder());
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

        jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(null));
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

        jButton41.setText("Select");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton41, gridBagConstraints);

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

        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchPanel11.setLayout(new java.awt.GridBagLayout());

        jSearchPanel11.setBorder(new javax.swing.border.EtchedBorder());
        jTextField11111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11111CaretUpdate(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel11.add(jTextField11111, gridBagConstraints);

        jSearchTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course", "Title 2"
            }
        ));
        jSearchTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable11MouseClicked(evt);
            }
        });

        jSearchScrollPane11.setViewportView(jSearchTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel11.add(jSearchScrollPane11, gridBagConstraints);

        jButton411.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(jButton411, gridBagConstraints);

        jButton511.setText("Close");
        jButton511.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton511ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(jButton511, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog2.getContentPane().add(jSearchPanel11, gridBagConstraints);

        searchRowSet1.setCommand("select service_type,rate as price,gl_account FROM pb_operating_parameters WHERE main_service =  'Laboratory' order by service_type");
        searchRowSet1.setConnectionSource(pConnDB);
        nBCachedRowSet19.setCommand("select distinct upper(status_desc) as jstatus from hr.hr_job_status order by jstatus");
        nBCachedRowSet19.setConnectionSource(pConnDB);
        nBCachedRowSet20.setCommand("select distinct upper(country_name) as country from hr.hr_country order by country");
        nBCachedRowSet20.setConnectionSource(pConnDB);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("STAFF RESUME DATABASE");
        setVisible(true);
        jTabbedPane1.setBackground(new java.awt.Color(153, 153, 153));
        /*cmbox = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor4 = jTable11.getColumnModel().getColumn(0);
        com.afrisoftech.lib.DateCellEditor dateCellEditor4 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable12);
        dateEditor4.setCellEditor(dateCellEditor4);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer4 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer4.setHorizontalAlignment(cellRenderer4.TRAILING);
        dateEditor4.setCellRenderer(cellRenderer4);

        /*cmbox1 = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor5 = jTable11.getColumnModel().getColumn(5);
        com.afrisoftech.lib.DateCellEditor dateCellEditor5 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable12);
        dateEditor5.setCellEditor(dateCellEditor5);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer5 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer5.setHorizontalAlignment(cellRenderer5.TRAILING);
        dateEditor5.setCellRenderer(cellRenderer5);*/
        jPanel1.setLayout(new java.awt.GridBagLayout());

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
        buttonPanel3.setLayout(new java.awt.GridBagLayout());

        buttonPanel3.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel13.setText("Enter File Information Details");
        buttonPanel3.add(jLabel13, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel3.add(buttonPanel3, gridBagConstraints);

        fieldsPanel3.setLayout(new java.awt.GridBagLayout());

        fieldsPanel3.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        facilityidLbl1.setText("Field of Specialization");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3.add(facilityidLbl1, gridBagConstraints);

        jComboBox11.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "specialization", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3.add(jComboBox11, gridBagConstraints);

        jLabel122.setText("Position Applied For");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3.add(jLabel122, gridBagConstraints);

        jComboBox31.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet2, "position", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3.add(jComboBox31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 5.0;
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
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel3.add(dataPanel3, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        //  jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[])) {}
        jLabel31.setText("File Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel31, gridBagConstraints);

        jLabel41.setText("Staff Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel41, gridBagConstraints);

        jLabel51.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel51, gridBagConstraints);

        jLabel61.setText("Interview date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel61, gridBagConstraints);

        jLabel71.setText("Hire Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel71, gridBagConstraints);

        jLabel81.setText("Second Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel81, gridBagConstraints);

        jLabel91.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel91, gridBagConstraints);

        jLabel101.setText("Other Names");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jTextField61, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel12.add(jTextField91, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel12.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel12.add(datePicker3, gridBagConstraints);

        jComboBox5.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet10, "name", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox5, gridBagConstraints);

        jComboBox122.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet15, "design", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox122, gridBagConstraints);

        jLabel41111.setText("Designation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel41111, gridBagConstraints);

        jComboBox125.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet16, "section", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox125, gridBagConstraints);

        jLabel71111.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel71111, gridBagConstraints);

        jLabel1.setText("Job Group");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel12.add(jLabel1, gridBagConstraints);

        jLabel5.setText("Job Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel12.add(jLabel5, gridBagConstraints);

        jComboBox7.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet19, "jstatus", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox7, gridBagConstraints);

        jComboBox6.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet18, "jgroup", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox6, gridBagConstraints);

        jLabel52.setText("Job Grade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel12.add(jLabel52, gridBagConstraints);

        jComboBox71.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet17, "jgrade", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jComboBox71, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        subMainPanel3.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel3.add(subMainPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(mainPanel3, gridBagConstraints);

        jTabbedPane1.addTab("File Details", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        mainPanel31.setLayout(new java.awt.GridBagLayout());

        labelPanel31.setLayout(new java.awt.GridBagLayout());

        labelPanel31.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel31.setLayout(new java.awt.GridBagLayout());

        imgPanel31.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel231.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel31.add(jLabel231, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel31.add(imgPanel31, gridBagConstraints);

        actionsPanel31.setLayout(new java.awt.GridBagLayout());

        actionsPanel31.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction31.setMnemonic('w');
        newAction31.setText("New");
        newAction31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction31ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel31.add(newAction31, gridBagConstraints);

        updateAction31.setMnemonic('U');
        updateAction31.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel31.add(updateAction31, gridBagConstraints);

        deleteAction31.setMnemonic('D');
        deleteAction31.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel31.add(deleteAction31, gridBagConstraints);

        clearAction31.setMnemonic('C');
        clearAction31.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel31.add(clearAction31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel31.add(spacerPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel31.add(actionsPanel31, gridBagConstraints);

        helpPanel31.setLayout(new java.awt.GridBagLayout());

        helpPanel31.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction31.setMnemonic('H');
        helpAction31.setText("Help");
        helpPanel31.add(helpAction31, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel31.add(helpPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel31.add(labelPanel31, gridBagConstraints);

        subMainPanel31.setLayout(new java.awt.GridBagLayout());

        subMainPanel31.setBorder(new javax.swing.border.TitledBorder(""));
        buttonPanel31.setLayout(new java.awt.GridBagLayout());

        buttonPanel31.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel31141.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel31.add(jLabel31141, gridBagConstraints);

        jPanel61131.setLayout(new java.awt.GridBagLayout());

        jPanel61131.setBorder(new javax.swing.border.EtchedBorder());
        jPanel61131.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField100.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61131.add(jTextField100, gridBagConstraints);

        searchButton131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton131.setToolTipText("Search");
        searchButton131.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton131.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton131.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton131.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton131.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton131ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel61131.add(searchButton131, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        buttonPanel31.add(jPanel61131, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel31.add(buttonPanel31, gridBagConstraints);

        fieldsPanel31.setLayout(new java.awt.GridBagLayout());

        fieldsPanel31.setBorder(new javax.swing.border.TitledBorder("Enter Emergency Contacts"));
        facilityidLbl11.setText("Email Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl11, gridBagConstraints);

        jLabel1221.setText("Town");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jLabel1221, gridBagConstraints);

        facilityidLbl111.setText("Relationship");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl111, gridBagConstraints);

        facilityidLbl112.setText("Tel No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl112, gridBagConstraints);

        facilityidLbl113.setText("Mobile phone  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl113, gridBagConstraints);

        facilityidLbl114.setText("Postal Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl114, gridBagConstraints);

        facilityidLbl115.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl115, gridBagConstraints);

        facilityidLbl116.setText("Postal Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilityidLbl116, gridBagConstraints);

        facilitynameLbl111.setText("Name of Next of Kin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(facilitynameLbl111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField25, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField26, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField27, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel31.add(jTextField29, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel31.add(fieldsPanel31, gridBagConstraints);

        dataPanel31.setLayout(new java.awt.GridBagLayout());

        dataPanel31.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction31.setMnemonic('F');
        firstAction31.setText("First");
        dataPanel31.add(firstAction31, new java.awt.GridBagConstraints());

        previousAction31.setMnemonic('P');
        previousAction31.setText("Previous");
        dataPanel31.add(previousAction31, new java.awt.GridBagConstraints());

        nextAction21.setMnemonic('N');
        nextAction21.setText("Next");
        dataPanel31.add(nextAction21, new java.awt.GridBagConstraints());

        lastAction31.setMnemonic('L');
        lastAction31.setText("Last");
        dataPanel31.add(lastAction31, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel31.add(dataPanel31, gridBagConstraints);

        jPanel121.setLayout(new java.awt.GridBagLayout());

        jPanel121.setBorder(new javax.swing.border.TitledBorder("Applicant Home details"));
        jLabel311.setText("Place of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jLabel311, gridBagConstraints);

        jLabel411.setText("District");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jLabel411, gridBagConstraints);

        jLabel611.setText("Current Residence Locality");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jLabel611, gridBagConstraints);

        jLabel711.setText("Town");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jLabel711, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jTextField411, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jTextField611, gridBagConstraints);

        jComboBox10.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet4, "district", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jComboBox10, gridBagConstraints);

        jComboBox20.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet5, "town", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel121.add(jComboBox20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel31.add(jPanel121, gridBagConstraints);

        fieldsPanel311.setLayout(new java.awt.GridBagLayout());

        fieldsPanel311.setBorder(new javax.swing.border.TitledBorder("Applicant Addresses"));
        facilityidLbl117.setText("Email Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl117, gridBagConstraints);

        facilitynameLbl112.setText("Website");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilitynameLbl112, gridBagConstraints);

        facilityidLbl1111.setText("Tel No. 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1111, gridBagConstraints);

        facilityidLbl1121.setText("Mobile phone 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1121, gridBagConstraints);

        facilityidLbl1131.setText("Mobile phone 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1131, gridBagConstraints);

        facilityidLbl1141.setText("Postal Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1141, gridBagConstraints);

        facilityidLbl1151.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1151, gridBagConstraints);

        facilityidLbl1161.setText("Postal Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilityidLbl1161, gridBagConstraints);

        facilitynameLbl1111.setText("Tel No. 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(facilitynameLbl1111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField19, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel311.add(jTextField15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel31.add(fieldsPanel311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel31.add(subMainPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(mainPanel31, gridBagConstraints);

        jTabbedPane1.addTab("Addresses", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        mainPanel1.setLayout(new java.awt.GridBagLayout());

        labelPanel1.setLayout(new java.awt.GridBagLayout());

        labelPanel1.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel1.setLayout(new java.awt.GridBagLayout());

        imgPanel1.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel1.add(jLabel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel1.add(imgPanel1, gridBagConstraints);

        actionsPanel1.setLayout(new java.awt.GridBagLayout());

        actionsPanel1.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction1.setMnemonic('w');
        newAction1.setText("New");
        newAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(newAction1, gridBagConstraints);

        updateAction1.setMnemonic('U');
        updateAction1.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(updateAction1, gridBagConstraints);

        deleteAction1.setMnemonic('D');
        deleteAction1.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(deleteAction1, gridBagConstraints);

        clearAction1.setMnemonic('C');
        clearAction1.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(clearAction1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel1.add(spacerPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel1.add(actionsPanel1, gridBagConstraints);

        helpPanel1.setLayout(new java.awt.GridBagLayout());

        helpPanel1.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction1.setMnemonic('H');
        helpAction1.setText("Help");
        helpPanel1.add(helpAction1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel1.add(helpPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel1.add(labelPanel1, gridBagConstraints);

        subMainPanel.setLayout(new java.awt.GridBagLayout());

        subMainPanel.setBorder(new javax.swing.border.TitledBorder(""));
        buttonPanel.setLayout(new java.awt.GridBagLayout());

        buttonPanel.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel31142.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jLabel31142, gridBagConstraints);

        jPanel61132.setLayout(new java.awt.GridBagLayout());

        jPanel61132.setBorder(new javax.swing.border.EtchedBorder());
        jPanel61132.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField101.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61132.add(jTextField101, gridBagConstraints);

        searchButton132.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton132.setToolTipText("Search");
        searchButton132.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton132.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton132.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton132.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton132.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton132ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel61132.add(searchButton132, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        buttonPanel.add(jPanel61132, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(buttonPanel, gridBagConstraints);

        fieldsPanel.setLayout(new java.awt.GridBagLayout());

        fieldsPanel.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        qualificationidLbl.setText("Qualification ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(qualificationidLbl, gridBagConstraints);

        qualificationdescriptionLbl.setText("Qualification Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(qualificationdescriptionLbl, gridBagConstraints);

        departmentrequiringqualificationLbl.setText("Department Where Qualification Required");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(departmentrequiringqualificationLbl, gridBagConstraints);

        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        fieldsPanel.add(jScrollPane1, gridBagConstraints);

        jComboBox3.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet6, "qual", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jComboBox3, gridBagConstraints);

        jComboBox4.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet7, "name", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jComboBox4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel.add(fieldsPanel, gridBagConstraints);

        dataPanel.setLayout(new java.awt.GridBagLayout());

        dataPanel.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction.setMnemonic('F');
        firstAction.setText("First");
        dataPanel.add(firstAction, new java.awt.GridBagConstraints());

        previousAction.setMnemonic('P');
        previousAction.setText("Previous");
        dataPanel.add(previousAction, new java.awt.GridBagConstraints());

        nextAction.setMnemonic('N');
        nextAction.setText("Next");
        dataPanel.add(nextAction, new java.awt.GridBagConstraints());

        lastAction.setMnemonic('L');
        lastAction.setText("Last");
        dataPanel.add(lastAction, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(dataPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel1.add(subMainPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(mainPanel1, gridBagConstraints);

        jTabbedPane1.addTab("Educational Qualifications", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel.setLayout(new java.awt.GridBagLayout());

        imgPanel.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(imgPanel, gridBagConstraints);

        actionsPanel.setLayout(new java.awt.GridBagLayout());

        actionsPanel.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction.setMnemonic('w');
        newAction.setText("New");
        newAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newActionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(newAction, gridBagConstraints);

        updateAction.setMnemonic('U');
        updateAction.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(updateAction, gridBagConstraints);

        deleteAction.setMnemonic('D');
        deleteAction.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(deleteAction, gridBagConstraints);

        clearAction.setMnemonic('C');
        clearAction.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(clearAction, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel.add(spacerPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel.add(actionsPanel, gridBagConstraints);

        helpPanel.setLayout(new java.awt.GridBagLayout());

        helpPanel.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction.setMnemonic('H');
        helpAction.setText("Help");
        helpPanel.add(helpAction, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(helpPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(labelPanel, gridBagConstraints);

        subMainPanel2.setLayout(new java.awt.GridBagLayout());

        subMainPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        subMainPanel2.setAlignmentX(10.0F);
        buttonPanel2.setLayout(new java.awt.GridBagLayout());

        buttonPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        jPanel611311.setLayout(new java.awt.GridBagLayout());

        jPanel611311.setBorder(new javax.swing.border.EtchedBorder());
        jPanel611311.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField102.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel611311.add(jTextField102, gridBagConstraints);

        searchButton1311.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton1311.setToolTipText("Search");
        searchButton1311.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1311.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1311.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1311.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1311.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1311ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel611311.add(searchButton1311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        buttonPanel2.add(jPanel611311, gridBagConstraints);

        jLabel311411.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel2.add(jLabel311411, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel2.add(buttonPanel2, gridBagConstraints);

        fieldsPanel2.setLayout(new java.awt.GridBagLayout());

        fieldsPanel2.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        qualificationidLbl2.setText("Course");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel2.add(qualificationidLbl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel2.add(qualificationidTxt2, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date Of Qualification", "Course Title", "Institution", "Grade", "Remarks"
            }
        ));
        //cmbox = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor6 = jTable1.getColumnModel().getColumn(0);
        com.afrisoftech.lib.DateCellEditor dateCellEditor6 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable1);
        dateEditor6.setCellEditor(dateCellEditor6);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer6 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer6.setHorizontalAlignment(cellRenderer6.TRAILING);
        dateEditor6.setCellRenderer(cellRenderer6);
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel2.add(fieldsPanel2, gridBagConstraints);

        dataPanel2.setLayout(new java.awt.GridBagLayout());

        dataPanel2.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction2.setMnemonic('F');
        firstAction2.setText("First");
        dataPanel2.add(firstAction2, new java.awt.GridBagConstraints());

        previousAction2.setMnemonic('P');
        previousAction2.setText("Previous");
        dataPanel2.add(previousAction2, new java.awt.GridBagConstraints());

        nextAction3.setMnemonic('N');
        nextAction3.setText("Next");
        dataPanel2.add(nextAction3, new java.awt.GridBagConstraints());

        lastAction2.setMnemonic('L');
        lastAction2.setText("Last");
        dataPanel2.add(lastAction2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel2.add(dataPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(subMainPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(mainPanel, gridBagConstraints);

        jTabbedPane1.addTab("Proffessional Qualifications", jPanel4);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        mainPanel2.setLayout(new java.awt.GridBagLayout());

        labelPanel2.setLayout(new java.awt.GridBagLayout());

        labelPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel2.setLayout(new java.awt.GridBagLayout());

        imgPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel2.add(jLabel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel2.add(imgPanel2, gridBagConstraints);

        actionsPanel2.setLayout(new java.awt.GridBagLayout());

        actionsPanel2.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction2.setMnemonic('w');
        newAction2.setText("New");
        newAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel2.add(newAction2, gridBagConstraints);

        updateAction2.setMnemonic('U');
        updateAction2.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel2.add(updateAction2, gridBagConstraints);

        deleteAction2.setMnemonic('D');
        deleteAction2.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel2.add(deleteAction2, gridBagConstraints);

        clearAction2.setMnemonic('C');
        clearAction2.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel2.add(clearAction2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel2.add(spacerPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel2.add(actionsPanel2, gridBagConstraints);

        helpPanel2.setLayout(new java.awt.GridBagLayout());

        helpPanel2.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction2.setMnemonic('H');
        helpAction2.setText("Help");
        helpPanel2.add(helpAction2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel2.add(helpPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel2.add(labelPanel2, gridBagConstraints);

        subMainPanel21.setLayout(new java.awt.GridBagLayout());

        subMainPanel21.setBorder(new javax.swing.border.TitledBorder(""));
        subMainPanel21.setAlignmentX(10.0F);
        buttonPanel21.setLayout(new java.awt.GridBagLayout());

        buttonPanel21.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel121.setText("Enter Details Of Previous Employments");
        buttonPanel21.add(jLabel121, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel21.add(buttonPanel21, gridBagConstraints);

        fieldsPanel21.setLayout(new java.awt.GridBagLayout());

        fieldsPanel21.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
                "Begin date", "End date", "Employer", "Position", "Salary", "Remarks"
            }
        ));
        //cmbox = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor2 = jTable11.getColumnModel().getColumn(0);
        com.afrisoftech.lib.DateCellEditor dateCellEditor2 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable11);
        dateEditor2.setCellEditor(dateCellEditor2);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer2 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer2.setHorizontalAlignment(cellRenderer2.TRAILING);
        dateEditor2.setCellRenderer(cellRenderer2);

        //mbox1 = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor3 = jTable11.getColumnModel().getColumn(1);
        com.afrisoftech.lib.DateCellEditor dateCellEditor3 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable11);
        dateEditor3.setCellEditor(dateCellEditor3);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer3 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer3.setHorizontalAlignment(cellRenderer3.TRAILING);
        dateEditor3.setCellRenderer(cellRenderer3);
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });

        jScrollPane21.setViewportView(jTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel21.add(jScrollPane21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel21.add(fieldsPanel21, gridBagConstraints);

        dataPanel21.setLayout(new java.awt.GridBagLayout());

        dataPanel21.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction21.setMnemonic('F');
        firstAction21.setText("First");
        dataPanel21.add(firstAction21, new java.awt.GridBagConstraints());

        previousAction21.setMnemonic('P');
        previousAction21.setText("Previous");
        dataPanel21.add(previousAction21, new java.awt.GridBagConstraints());

        nextAction31.setMnemonic('N');
        nextAction31.setText("Next");
        dataPanel21.add(nextAction31, new java.awt.GridBagConstraints());

        lastAction21.setMnemonic('L');
        lastAction21.setText("Last");
        dataPanel21.add(lastAction21, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel21.add(dataPanel21, gridBagConstraints);

        jPanel611312.setLayout(new java.awt.GridBagLayout());

        jPanel611312.setBorder(new javax.swing.border.EtchedBorder());
        jPanel611312.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField103.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel611312.add(jTextField103, gridBagConstraints);

        searchButton1312.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton1312.setToolTipText("Search");
        searchButton1312.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1312.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1312.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1312.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1312.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1312ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel611312.add(searchButton1312, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        subMainPanel21.add(jPanel611312, gridBagConstraints);

        jLabel311412.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel21.add(jLabel311412, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel2.add(subMainPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(mainPanel2, gridBagConstraints);

        jTabbedPane1.addTab("Work Experience", jPanel5);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        mainPanel311.setLayout(new java.awt.GridBagLayout());

        labelPanel311.setLayout(new java.awt.GridBagLayout());

        labelPanel311.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel311.setLayout(new java.awt.GridBagLayout());

        imgPanel311.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel2311.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel311.add(jLabel2311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel311.add(imgPanel311, gridBagConstraints);

        actionsPanel311.setLayout(new java.awt.GridBagLayout());

        actionsPanel311.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction311.setMnemonic('w');
        newAction311.setText("New");
        newAction311.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction311ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel311.add(newAction311, gridBagConstraints);

        updateAction311.setMnemonic('U');
        updateAction311.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel311.add(updateAction311, gridBagConstraints);

        deleteAction311.setMnemonic('D');
        deleteAction311.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel311.add(deleteAction311, gridBagConstraints);

        clearAction311.setMnemonic('C');
        clearAction311.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel311.add(clearAction311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel311.add(spacerPanel311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel311.add(actionsPanel311, gridBagConstraints);

        helpPanel311.setLayout(new java.awt.GridBagLayout());

        helpPanel311.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction311.setMnemonic('H');
        helpAction311.setText("Help");
        helpPanel311.add(helpAction311, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel311.add(helpPanel311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel311.add(labelPanel311, gridBagConstraints);

        subMainPanel311.setLayout(new java.awt.GridBagLayout());

        subMainPanel311.setBorder(new javax.swing.border.TitledBorder(""));
        buttonPanel311.setLayout(new java.awt.GridBagLayout());

        buttonPanel311.setBorder(new javax.swing.border.TitledBorder(""));
        jPanel611313.setLayout(new java.awt.GridBagLayout());

        jPanel611313.setBorder(new javax.swing.border.EtchedBorder());
        jPanel611313.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField104.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel611313.add(jTextField104, gridBagConstraints);

        searchButton1313.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton1313.setToolTipText("Search");
        searchButton1313.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1313.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1313.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1313.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1313.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1313ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel611313.add(searchButton1313, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        buttonPanel311.add(jPanel611313, gridBagConstraints);

        jLabel311413.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel311.add(jLabel311413, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel311.add(buttonPanel311, gridBagConstraints);

        dataPanel311.setLayout(new java.awt.GridBagLayout());

        dataPanel311.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction311.setMnemonic('F');
        firstAction311.setText("First");
        dataPanel311.add(firstAction311, new java.awt.GridBagConstraints());

        previousAction311.setMnemonic('P');
        previousAction311.setText("Previous");
        dataPanel311.add(previousAction311, new java.awt.GridBagConstraints());

        nextAction211.setMnemonic('N');
        nextAction211.setText("Next");
        dataPanel311.add(nextAction211, new java.awt.GridBagConstraints());

        lastAction311.setMnemonic('L');
        lastAction311.setText("Last");
        dataPanel311.add(lastAction311, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel311.add(dataPanel311, gridBagConstraints);

        jPanel1211.setLayout(new java.awt.GridBagLayout());

        jPanel1211.setBorder(new javax.swing.border.TitledBorder("Enter Spouse Info If Married"));
        jLabel6111.setText("Spouse Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jLabel6111, gridBagConstraints);

        jTextField62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField62ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jTextField62, gridBagConstraints);

        jLabel31111.setText("ID No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jLabel31111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jTextField66, gridBagConstraints);

        jLabel311111.setText("Birth Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jLabel311111, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(new javax.swing.border.TitledBorder(null, "Job Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jCheckBox1.setFont(new java.awt.Font("Dialog", 0, 10));
        jCheckBox1.setText("Employed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setFont(new java.awt.Font("Dialog", 0, 10));
        jCheckBox2.setText("Unemployed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jCheckBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(jPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1211.add(datePicker1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1211.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel311.add(jPanel1211, gridBagConstraints);

        fieldsPanel3111.setLayout(new java.awt.GridBagLayout());

        fieldsPanel3111.setBorder(new javax.swing.border.TitledBorder("Other Details"));
        facilityidLbl11111.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(facilityidLbl11111, gridBagConstraints);

        facilityidLbl11211.setText("Handicup Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(facilityidLbl11211, gridBagConstraints);

        facilityidLbl11411.setText("NHIF No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(facilityidLbl11411, gridBagConstraints);

        facilityidLbl11611.setText("NSSF No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(facilityidLbl11611, gridBagConstraints);

        facilitynameLbl11111.setText("Ethnicity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(facilitynameLbl11111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(jTextField69, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(jTextField67, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(jTextField68, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MALE", "FEMALE", "OTHERS" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet9, "ethnic", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel3111.add(jComboBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel311.add(fieldsPanel3111, gridBagConstraints);

        jPanel12111.setLayout(new java.awt.GridBagLayout());

        jPanel12111.setBorder(new javax.swing.border.TitledBorder("Enter the Required Info"));
        jLabel31112.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel31112, gridBagConstraints);

        jLabel41112.setText("Passport No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel41112, gridBagConstraints);

        jLabel61111.setText("Current Residence");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel61111, gridBagConstraints);

        jLabel71112.setText("Marital Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel71112, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jTextField621, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jTextField641, gridBagConstraints);

        jComboBox511.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet8, "marital", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jComboBox511, gridBagConstraints);

        jLabel311113.setText("Weight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel311113, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jTextField661, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jTextField651, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jTextField631, gridBagConstraints);

        jLabel3111111.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel3111111, gridBagConstraints);

        jLabel3111121.setText("National ID No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel3111121, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(datePicker41, gridBagConstraints);

        jLabel6.setText("Nationality");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jLabel6, gridBagConstraints);

        jComboBox8.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet20, "country", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12111.add(jComboBox8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel311.add(jPanel12111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel311.add(subMainPanel311, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(mainPanel311, gridBagConstraints);

        jTabbedPane1.addTab("Bio Data", jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        mainPanel21.setLayout(new java.awt.GridBagLayout());

        labelPanel21.setLayout(new java.awt.GridBagLayout());

        labelPanel21.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel21.setLayout(new java.awt.GridBagLayout());

        imgPanel21.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel221.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel21.add(jLabel221, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel21.add(imgPanel21, gridBagConstraints);

        actionsPanel21.setLayout(new java.awt.GridBagLayout());

        actionsPanel21.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction21.setMnemonic('w');
        newAction21.setText("New");
        newAction21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction21ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel21.add(newAction21, gridBagConstraints);

        updateAction21.setMnemonic('U');
        updateAction21.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel21.add(updateAction21, gridBagConstraints);

        deleteAction21.setMnemonic('D');
        deleteAction21.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel21.add(deleteAction21, gridBagConstraints);

        clearAction21.setMnemonic('C');
        clearAction21.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel21.add(clearAction21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel21.add(spacerPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel21.add(actionsPanel21, gridBagConstraints);

        helpPanel21.setLayout(new java.awt.GridBagLayout());

        helpPanel21.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction21.setMnemonic('H');
        helpAction21.setText("Help");
        helpPanel21.add(helpAction21, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel21.add(helpPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel21.add(labelPanel21, gridBagConstraints);

        subMainPanel211.setLayout(new java.awt.GridBagLayout());

        subMainPanel211.setBorder(new javax.swing.border.TitledBorder(""));
        subMainPanel211.setAlignmentX(10.0F);
        fieldsPanel211.setLayout(new java.awt.GridBagLayout());

        fieldsPanel211.setBorder(new javax.swing.border.TitledBorder("Refferees' Details"));
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
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
                "Name", "Organisation", "Title", "Postal Address", "Telephone", "Remarks"
            }
        ));
        jScrollPane211.setViewportView(jTable10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel211.add(jScrollPane211, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel211.add(fieldsPanel211, gridBagConstraints);

        dataPanel211.setLayout(new java.awt.GridBagLayout());

        dataPanel211.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction211.setMnemonic('F');
        firstAction211.setText("First");
        dataPanel211.add(firstAction211, new java.awt.GridBagConstraints());

        previousAction211.setMnemonic('P');
        previousAction211.setText("Previous");
        dataPanel211.add(previousAction211, new java.awt.GridBagConstraints());

        nextAction311.setMnemonic('N');
        nextAction311.setText("Next");
        dataPanel211.add(nextAction311, new java.awt.GridBagConstraints());

        lastAction211.setMnemonic('L');
        lastAction211.setText("Last");
        dataPanel211.add(lastAction211, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel211.add(dataPanel211, gridBagConstraints);

        jLabel3114.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel211.add(jLabel3114, gridBagConstraints);

        jPanel6113.setLayout(new java.awt.GridBagLayout());

        jPanel6113.setBorder(new javax.swing.border.EtchedBorder());
        jPanel6113.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField105.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6113.add(jTextField105, gridBagConstraints);

        searchButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton13.setToolTipText("Search");
        searchButton13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton13.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton13.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton13ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6113.add(searchButton13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        subMainPanel211.add(jPanel6113, gridBagConstraints);

        qualifiableamountLbl.setText("Leadership");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel211.add(qualifiableamountLbl, gridBagConstraints);

        jScrollPane7.setViewportBorder(new javax.swing.border.TitledBorder(""));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Club/Office", "Position", "Begin Date", "End Date", "Remarks"
            }
        ));
        //cmbox = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor5 = jTable2.getColumnModel().getColumn(2);
        com.afrisoftech.lib.DateCellEditor dateCellEditor5 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable2);
        dateEditor5.setCellEditor(dateCellEditor5);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer5 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer5.setHorizontalAlignment(cellRenderer5.TRAILING);
        dateEditor5.setCellRenderer(cellRenderer5);

        //mbox1 = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor4 = jTable2.getColumnModel().getColumn(3);
        com.afrisoftech.lib.DateCellEditor dateCellEditor4 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable2);
        dateEditor4.setCellEditor(dateCellEditor4);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer4 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer4.setHorizontalAlignment(cellRenderer4.TRAILING);
        dateEditor4.setCellRenderer(cellRenderer4);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });

        jScrollPane7.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        subMainPanel211.add(jScrollPane7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel21.add(subMainPanel211, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(mainPanel21, gridBagConstraints);

        jTabbedPane1.addTab("Referees & Leadership", jPanel7);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        mainPanel5.setLayout(new java.awt.GridBagLayout());

        labelPanel5.setLayout(new java.awt.GridBagLayout());

        labelPanel5.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel5.setLayout(new java.awt.GridBagLayout());

        imgPanel5.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel5.add(jLabel25, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel5.add(imgPanel5, gridBagConstraints);

        actionsPanel5.setLayout(new java.awt.GridBagLayout());

        actionsPanel5.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction5.setMnemonic('w');
        newAction5.setText("New");
        newAction5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction5ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel5.add(newAction5, gridBagConstraints);

        updateAction5.setMnemonic('U');
        updateAction5.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel5.add(updateAction5, gridBagConstraints);

        deleteAction5.setMnemonic('D');
        deleteAction5.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel5.add(deleteAction5, gridBagConstraints);

        clearAction5.setMnemonic('C');
        clearAction5.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel5.add(clearAction5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel5.add(spacerPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel5.add(actionsPanel5, gridBagConstraints);

        helpPanel5.setLayout(new java.awt.GridBagLayout());

        helpPanel5.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction5.setMnemonic('H');
        helpAction5.setText("Help");
        helpPanel5.add(helpAction5, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel5.add(helpPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel5.add(labelPanel5, gridBagConstraints);

        subMainPanel22.setLayout(new java.awt.GridBagLayout());

        subMainPanel22.setBorder(new javax.swing.border.TitledBorder(""));
        subMainPanel22.setAlignmentX(10.0F);
        buttonPanel22.setLayout(new java.awt.GridBagLayout());

        buttonPanel22.setBorder(new javax.swing.border.TitledBorder(""));
        jPanel611314.setLayout(new java.awt.GridBagLayout());

        jPanel611314.setBorder(new javax.swing.border.EtchedBorder());
        jPanel611314.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField106.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel611314.add(jTextField106, gridBagConstraints);

        searchButton1314.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton1314.setToolTipText("Search");
        searchButton1314.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1314.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1314.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1314.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1314.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1314ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel611314.add(searchButton1314, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        buttonPanel22.add(jPanel611314, gridBagConstraints);

        jLabel311414.setText("Staff Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel22.add(jLabel311414, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel22.add(buttonPanel22, gridBagConstraints);

        fieldsPanel22.setLayout(new java.awt.GridBagLayout());

        fieldsPanel22.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        qualificationidLbl21.setText("Proffession");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel22.add(qualificationidLbl21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel22.add(qualificationidTxt21, gridBagConstraints);

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Regn Issuer", "Licence No.", "Licence Type", "Details", "Expiry date", "Date of Issue", "Condition", "Remarks"
            }
        ));
        //javax.swing.JComboBox cmbox1 = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor8 = jTable12.getColumnModel().getColumn(4);
        com.afrisoftech.lib.DateCellEditor dateCellEditor8 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable12);
        dateEditor8.setCellEditor(dateCellEditor8);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer8 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer8.setHorizontalAlignment(cellRenderer8.TRAILING);
        dateEditor8.setCellRenderer(cellRenderer8);

        //cmbox1 = new javax.swing.JComboBox();
        javax.swing.table.TableColumn dateEditor9 = jTable12.getColumnModel().getColumn(5);
        com.afrisoftech.lib.DateCellEditor dateCellEditor9 = new com.afrisoftech.lib.DateCellEditor(new com.afrisoftech.lib.DatePicker(), jTable12);
        dateEditor9.setCellEditor(dateCellEditor9);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer9 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer9.setHorizontalAlignment(cellRenderer9.TRAILING);
        dateEditor9.setCellRenderer(cellRenderer9);
        jScrollPane22.setViewportView(jTable12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        fieldsPanel22.add(jScrollPane22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel22.add(fieldsPanel22, gridBagConstraints);

        dataPanel22.setLayout(new java.awt.GridBagLayout());

        dataPanel22.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction22.setMnemonic('F');
        firstAction22.setText("First");
        dataPanel22.add(firstAction22, new java.awt.GridBagConstraints());

        previousAction22.setMnemonic('P');
        previousAction22.setText("Previous");
        dataPanel22.add(previousAction22, new java.awt.GridBagConstraints());

        nextAction32.setMnemonic('N');
        nextAction32.setText("Next");
        dataPanel22.add(nextAction32, new java.awt.GridBagConstraints());

        lastAction22.setMnemonic('L');
        lastAction22.setText("Last");
        dataPanel22.add(lastAction22, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel22.add(dataPanel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel5.add(subMainPanel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(mainPanel5, gridBagConstraints);

        jTabbedPane1.addTab("Licences & Regn. Certificates", jPanel8);

        jPanel71.setLayout(new java.awt.GridBagLayout());

        mainPanel4.setLayout(new java.awt.GridBagLayout());

        labelPanel4.setLayout(new java.awt.GridBagLayout());

        labelPanel4.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel4.setLayout(new java.awt.GridBagLayout());

        imgPanel4.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel4.add(jLabel24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel4.add(imgPanel4, gridBagConstraints);

        actionsPanel4.setLayout(new java.awt.GridBagLayout());

        actionsPanel4.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction4.setMnemonic('w');
        newAction4.setText("Insert");
        newAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel4.add(newAction4, gridBagConstraints);

        deleteAction4.setMnemonic('D');
        deleteAction4.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel4.add(deleteAction4, gridBagConstraints);

        clearAction4.setMnemonic('C');
        clearAction4.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel4.add(clearAction4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel4.add(spacerPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel4.add(actionsPanel4, gridBagConstraints);

        helpPanel4.setLayout(new java.awt.GridBagLayout());

        helpPanel4.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction4.setMnemonic('H');
        helpAction4.setText("Help");
        helpPanel4.add(helpAction4, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel4.add(helpPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel4.add(labelPanel4, gridBagConstraints);

        subMainPanel1.setLayout(new java.awt.GridBagLayout());

        subMainPanel1.setBorder(new javax.swing.border.TitledBorder(""));
        fieldsPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(new javax.swing.border.TitledBorder("Enter Applicants' Languages"));
        jLabel3.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Available Languages");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jLabel3, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note[1].gif")));
        jButton1.setMnemonic('e');
        jButton1.setText("Assign Language");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel11.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel4, gridBagConstraints);

        jList1.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList1.setModel(new org.netbeans.lib.sql.models.ListModel (nBCachedRowSet13, "language_name", null, null, null));
        jList1.setDragEnabled(true);
        jList1.setValueIsAdjusting(true);
        jList1.setVisibleRowCount(20);
        jScrollPane11.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel11.add(jScrollPane11, gridBagConstraints);

        jList11.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList11.setDragEnabled(true);
        jList11.setValueIsAdjusting(true);
        jList11.setVisibleRowCount(20);
        jScrollPane23.setViewportView(jList11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel11.add(jScrollPane23, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton2, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton3, gridBagConstraints);

        jLabel312.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel312.setForeground(new java.awt.Color(0, 0, 204));
        jLabel312.setText("Applicant Choosen Languages");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jLabel312, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 20);
        fieldsPanel1.add(jPanel11, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(new javax.swing.border.TitledBorder("Enter Clubs Here"));
        jLabel32.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel32.setForeground(new java.awt.Color(0, 0, 204));
        jLabel32.setText("Available Clubs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel32, gridBagConstraints);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note[1].gif")));
        jButton11.setMnemonic('e');
        jButton11.setText("Assign Club");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jButton11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel13.add(jSeparator11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jLabel42, gridBagConstraints);

        jList12.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList12.setModel(new org.netbeans.lib.sql.models.ListModel (nBCachedRowSet14, "club_name", null, null, null));
        jList12.setDragEnabled(true);
        jList12.setValueIsAdjusting(true);
        jList12.setVisibleRowCount(20);
        jScrollPane12.setViewportView(jList12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel13.add(jScrollPane12, gridBagConstraints);

        jList111.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList111.setDragEnabled(true);
        jList111.setValueIsAdjusting(true);
        jList111.setVisibleRowCount(20);
        jScrollPane221.setViewportView(jList111);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel13.add(jScrollPane221, gridBagConstraints);

        jButton21.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton21.setText("Remove");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jButton21, gridBagConstraints);

        jButton31.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton31.setText("Add");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jButton31, gridBagConstraints);

        jLabel313.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel313.setForeground(new java.awt.Color(0, 0, 204));
        jLabel313.setText("Applicant Choosen Club(s)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel313, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 20, 0, 0);
        fieldsPanel1.add(jPanel13, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel14.setBorder(new javax.swing.border.TitledBorder("Enter Applicants' favourite Sports"));
        jLabel33.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel33.setForeground(new java.awt.Color(0, 0, 204));
        jLabel33.setText("Available Sports");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jLabel33, gridBagConstraints);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note[1].gif")));
        jButton12.setMnemonic('e');
        jButton12.setText("Assign Sports");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jButton12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel14.add(jSeparator12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel43, gridBagConstraints);

        jList13.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList13.setModel(new org.netbeans.lib.sql.models.ListModel (nBCachedRowSet11, "type_desc", null, null, null));
        jList13.setDragEnabled(true);
        jList13.setValueIsAdjusting(true);
        jList13.setVisibleRowCount(20);
        jScrollPane13.setViewportView(jList13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 10.0;
        jPanel14.add(jScrollPane13, gridBagConstraints);

        jList112.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList112.setDragEnabled(true);
        jList112.setValueIsAdjusting(true);
        jList112.setVisibleRowCount(20);
        jScrollPane25.setViewportView(jList112);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 10.0;
        jPanel14.add(jScrollPane25, gridBagConstraints);

        jButton22.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton22.setText("Remove");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jButton22, gridBagConstraints);

        jButton32.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton32.setText("Add");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jButton32, gridBagConstraints);

        jLabel314.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel314.setForeground(new java.awt.Color(0, 0, 204));
        jLabel314.setText("Applicant Choosen Sports");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jLabel314, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 19);
        fieldsPanel1.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(new javax.swing.border.TitledBorder("Enter Applicants' Hobbies"));
        jLabel34.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel34.setForeground(new java.awt.Color(0, 0, 204));
        jLabel34.setText("Available Hobbies");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(jLabel34, gridBagConstraints);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note[1].gif")));
        jButton13.setMnemonic('e');
        jButton13.setText("Assign Hobby");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jButton13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel15.add(jSeparator13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jLabel44, gridBagConstraints);

        jList14.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList14.setModel(new org.netbeans.lib.sql.models.ListModel (nBCachedRowSet12, "type_desc", null, null, null));
        jList14.setDragEnabled(true);
        jList14.setValueIsAdjusting(true);
        jList14.setVisibleRowCount(20);
        jScrollPane14.setViewportView(jList14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel15.add(jScrollPane14, gridBagConstraints);

        jList113.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList113.setDragEnabled(true);
        jList113.setValueIsAdjusting(true);
        jList113.setVisibleRowCount(20);
        jScrollPane26.setViewportView(jList113);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel15.add(jScrollPane26, gridBagConstraints);

        jButton23.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton23.setText("Remove");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jButton23, gridBagConstraints);

        jButton33.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton33.setText("Add");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jButton33, gridBagConstraints);

        jLabel315.setFont(new java.awt.Font("Dialog", 3, 10));
        jLabel315.setForeground(new java.awt.Color(0, 0, 204));
        jLabel315.setText("Applicant Choosen Hobbies");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(jLabel315, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 20, 0, 0);
        fieldsPanel1.add(jPanel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 15.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        subMainPanel1.add(fieldsPanel1, gridBagConstraints);

        jLabel3113.setText("Applicant Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel1.add(jLabel3113, gridBagConstraints);

        jPanel6112.setLayout(new java.awt.GridBagLayout());

        jPanel6112.setBorder(new javax.swing.border.EtchedBorder());
        jPanel6112.setMinimumSize(new java.awt.Dimension(82, 37));
        jTextField363.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6112.add(jTextField363, gridBagConstraints);

        searchButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton12.setToolTipText("Search");
        searchButton12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton12.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton12.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton12ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6112.add(searchButton12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        subMainPanel1.add(jPanel6112, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel4.add(subMainPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel71.add(mainPanel4, gridBagConstraints);

        jTabbedPane1.addTab("Extra Curricular", jPanel71);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 708, 450);
    }//GEN-END:initComponents

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jTable11MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
jTable2.getSelectedColumn();
            
            kiwi.ui.DateChooser dateChooser = new kiwi.ui.DateChooser();
            
            dateChooser.setVisible(true);
            
        // Add your handling code here:    // Add your handling code here:
 
 
 /*if (( jTable2.getSelectedColumn() == 2) || (jTable2.getSelectedColumn() == 3)) {
            
            kiwi.ui.DateChooser dateChooser = new kiwi.ui.DateChooser();
            
            dateChooser.setVisible(true);
            
        }// Add your handling code here:    //*/
    }//GEN-LAST:event_jTable2MouseClicked

    private void searchButton1313ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1313ActionPerformed
searchButton15Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton1313ActionPerformed

    private void searchButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton13ActionPerformed
searchButton16Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton13ActionPerformed

    private void searchButton1314ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1314ActionPerformed
searchButton17Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton1314ActionPerformed

    private void searchButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton12ActionPerformed
searchButton18Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton12ActionPerformed

    private void searchButton1312ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1312ActionPerformed
searchButton14Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton1312ActionPerformed

    private void searchButton1311ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1311ActionPerformed
searchButton13Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton1311ActionPerformed

    private void searchButton132ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton132ActionPerformed
searchButton12Clicked();              // Add your handling code here:
    }//GEN-LAST:event_searchButton132ActionPerformed

    private void jButton511ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton511ActionPerformed
jSearchDialog2.dispose();             // Add your handling code here:
    }//GEN-LAST:event_jButton511ActionPerformed

    private void jSearchTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable11MouseClicked
jTable1.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),0),jTable1.getSelectedRow(), 1);
        jTable1.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),1),jTable1.getSelectedRow(), 2);
       // jTable1.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(),2),jTable1.getSelectedRow(), 4);
        
        jSearchDialog2.dispose();          //              // Add your handling code here:
    }//GEN-LAST:event_jSearchTable11MouseClicked

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jTextField11111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11111CaretUpdate
 try {
            searchRowSet1.execute("SELECT programme_desc,training_type from hr.hr_trainning_programmes where programme_desc ILIKE '"+jTextField1111.getText()+"%' order by programme_desc");
            
            jSearchTable11.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("programme_desc", "Description", false),
                //  new org.netbeans.lib.sql.models.TableModel.Column("price", "Price", false),
                new org.netbeans.lib.sql.models.TableModel.Column("training_type", "Type", false)
            }));
            jSearchScrollPane11.setViewportView(jSearchTable11);
            System.out.println("Cannot sort out");
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
            
        }          // Add your handling code he        // Add your handling code here:
    }//GEN-LAST:event_jTextField11111CaretUpdate

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
 jSearchDialog1.dispose();         // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
jTextField100.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField101.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());      
 jTextField102.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField103.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField104.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField105.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField106.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 //jTextField107.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 jTextField363.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 //jTextField100.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
 
 
 
        jSearchDialog1.dispose();              // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked

    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
try {
           crset1.execute("select staff_id,initcap(first_name||' '||second_name||' '||others) AS staff_names from hr.hr_staffresume_fileinfo where first_name||' '||second_name||' '||others ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY staff_names");
            
            jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(crset1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("staff_id", "Staff no", false),
                new org.netbeans.lib.sql.models.TableModel.Column("staff_names", "Name", false)
                
            }));
            jSearchTable1.setShowHorizontalLines(false);
            jSearchScrollPane1.setViewportView(jSearchTable1);
           
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
            
        }
        // Add your handling code here:        // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate
 private void cmboxMouseClicked() {
        
        System.out.println("Showing dialog");
        java.awt.Point point = jTable1.getLocationOnScreen();
        jSearchDialog2.setSize(400,200);
        jSearchDialog2.setLocation(point);
        jSearchDialog2.setVisible(true);
    }
    private void searchButton131ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton131ActionPerformed
searchButton11Clicked();           // Add your handling code here:
    }//GEN-LAST:event_searchButton131ActionPerformed
         public void searchButton11Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField100.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
 
         
          public void searchButton12Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField101.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
          
           public void searchButton13Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField102.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
           
            public void searchButton14Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField103.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
            
             public void searchButton15Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField104.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
             
              public void searchButton16Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField105.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
              
               public void searchButton17Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField106.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
               
                public void searchButton18Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField363.getLocationOnScreen();
        
        jSearchDialog1.setSize(400,200);
        
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
              
    }     
        private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
if (jTable1.getSelectedColumn() == 1) {
            
            this.cmboxMouseClicked();
            
        }  else if ((jTable1.getSelectedColumn() == 0)) {
            
            kiwi.ui.DateChooser dateChooser = new kiwi.ui.DateChooser();
            
            dateChooser.setVisible(true);
            
        }// Add your handling code her
        }//GEN-LAST:event_jTable1MouseClicked
 public void searchButton2Clicked() {
        
        System.out.println("Showing dialog");
        
        jSearchDialog2.dispose();
        java.awt.Point point = this.jTextField100.getLocationOnScreen();
        
        jSearchDialog2.setSize(400,200);
        
        jSearchDialog2.setLocation(point);
        jSearchDialog2.setVisible(true);
        
        
    }     
        private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
            // Add your handling code here:
        }//GEN-LAST:event_jTable1AncestorMoved

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
 java.lang.Object[] listSel = null;
        
        listSel = jList111.getSelectedValues();
        
        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList111.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            newdfListModel.removeElement(listSel[i]);
            
        }
        
        this.jList111.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
  
        java.lang.Object[] listSel = null;
        
        listSel = jList12.getSelectedValues();
        
        javax.swing.ListModel list111Model = jList111.getModel();
        
        int nofItemsList111 = list111Model.getSize();
        
        javax.swing.DefaultListModel newdfListModel = new javax.swing.DefaultListModel();
        
        for (int j = 0; j < list111Model.getSize(); j++) {
            
            newdfListModel.addElement(list111Model.getElementAt(j));
            
        }
        //        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList11.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            if (!newdfListModel.contains(listSel[i])) {
                
                newdfListModel.addElement(listSel[i].toString());
                
            }
        }
        
        this.jList111.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 java.lang.Object[] listSel = null;
        
        listSel = jList11.getSelectedValues();
        
        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList11.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            newdfListModel.removeElement(listSel[i]);
            
        }
        
        this.jList11.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 java.lang.Object[] listSel = null;
        
        listSel = jList1.getSelectedValues();
        
        javax.swing.ListModel list11Model = jList11.getModel();
        
        int nofItemsList11 = list11Model.getSize();
        
        javax.swing.DefaultListModel newdfListModel = new javax.swing.DefaultListModel();
        
        for (int j = 0; j < list11Model.getSize(); j++) {
            
            newdfListModel.addElement(list11Model.getElementAt(j));
            
        }
        //        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList11.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            if (!newdfListModel.contains(listSel[i])) {
                
                newdfListModel.addElement(listSel[i].toString());
                
            }
        }
        
        this.jList11.setModel(newdfListModel);
                // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
java.lang.Object[] listSel = null;
        
        listSel = jList113.getSelectedValues();
        
        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList113.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            newdfListModel.removeElement(listSel[i]);
            
        }
        
        this.jList113.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
  
        java.lang.Object[] listSel = null;
        
        listSel = jList14.getSelectedValues();
        
        javax.swing.ListModel list113Model = jList113.getModel();
        
        int nofItemsList113 = list113Model.getSize();
        
        javax.swing.DefaultListModel newdfListModel = new javax.swing.DefaultListModel();
        
        for (int j = 0; j < list113Model.getSize(); j++) {
            
            newdfListModel.addElement(list113Model.getElementAt(j));
            
        }
        //        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList11.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            if (!newdfListModel.contains(listSel[i])) {
                
                newdfListModel.addElement(listSel[i].toString());
                
            }
        }
        
        this.jList113.setModel(newdfListModel);
                // Add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
java.lang.Object[] listSel = null;
        
        listSel = jList112.getSelectedValues();
        
        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList112.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            newdfListModel.removeElement(listSel[i]);
            
        }
        
        this.jList112.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
  
        java.lang.Object[] listSel = null;
        
        listSel = jList13.getSelectedValues();
        
        javax.swing.ListModel list112Model = jList112.getModel();
        
        int nofItemsList112 = list112Model.getSize();
        
        javax.swing.DefaultListModel newdfListModel = new javax.swing.DefaultListModel();
        
        for (int j = 0; j < list112Model.getSize(); j++) {
            
            newdfListModel.addElement(list112Model.getElementAt(j));
            
        }
        //        javax.swing.DefaultListModel newdfListModel = (javax.swing.DefaultListModel)jList11.getModel();
        
        for (int i = 0; i < listSel.length; i++) {
            
            if (!newdfListModel.contains(listSel[i])) {
                
                newdfListModel.addElement(listSel[i].toString());
                
            }
        }
        
        this.jList112.setModel(newdfListModel);        // Add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void newAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction4ActionPerformed
  
        
        java.util.Vector item2add = new java.util.Vector(5, 5);
        
        javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
        
        listModel = (javax.swing.DefaultListModel)jList112.getModel();
        
        java.lang.Object[] selectedValues = null;
        
        java.util.Enumeration listEnum =  listModel.elements();
        
        while(listEnum.hasMoreElements()) {
            
            item2add.addElement(listEnum.nextElement());
            
        }
        
        selectedValues = item2add.toArray();
        
        System.out.println("The values selected are : ["+selectedValues.length+"]");
        
        //try {
            //java.sql.Statement pstmt = connectDB.createStatement();
         //   java.sql.PreparedStatement ppstmt = connectDB.prepareStatement("INSERT INTO hr.hr_staffresume_fileinfo (app_id) VALUES(?)");
            // ppstmt.setString(1,"app_id");
           // ppstmt.setString(1,jTextField363.getText());
           // ppstmt.executeUpdate();
            
       // } catch(java.sql.SQLException sq){
          //  javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!!",javax.swing.JOptionPane.ERROR_MESSAGE);
           // System.out.println(sq.getMessage());
       // }
        //this is for sports
        
        
        try {
            
            listModel = (javax.swing.DefaultListModel)jList112.getModel();
            
            
            
            for (int k = 0; k < selectedValues.length; k++) {
                
                System.out.print("Values : "+"\t"+selectedValues[k]+"\t");
                
            }
            
            java.sql.Array menuSqlArray;
            
            java.lang.String str = this.getSqlString(selectedValues);
            
            
            
            
            //  java.sql.Statement prstmt = connectDB.createStatement();
            java.sql.PreparedStatement pmstmt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_fileinfo SET sports = '{"+str+"}' WHERE staff_id = '"+jTextField363.getText()+"'");
            
            
            pmstmt.executeUpdate();
            
            
            
            // this is for hobbies
            
            
            item2add = new java.util.Vector(5, 5);
            
            listModel = new javax.swing.DefaultListModel();
            
            listModel = (javax.swing.DefaultListModel)jList113.getModel();
            
            selectedValues = null;
            
            listEnum =  listModel.elements();
            
            while(listEnum.hasMoreElements()) {
                
                item2add.addElement(listEnum.nextElement());
                
            }
            
            selectedValues = item2add.toArray();
            
            
            
            
            
            
            
            
            for (int j = 0; j < selectedValues.length; j++) {
                
                System.out.print("Values : "+"\t"+selectedValues[j]+"\t");
                
            }
            
            
            java.lang.String str1 = this.getSqlString(selectedValues);
            
            
            
            // java.sql.Statement prpstmt = connectDB.createStatement();
            java.sql.PreparedStatement prpdstmt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_fileinfo SET hobbies = '{"+str1+"}' WHERE staff_id = '"+jTextField363.getText()+"'");
            
            
            
            prpdstmt.executeUpdate();
            
            
            
            
            
            
            
            // this is for languages
            
            
            item2add = new java.util.Vector(5, 5);
            
            listModel = new javax.swing.DefaultListModel();
            
            if (jList11.getModel().getSize() > 0) {
            
            listModel = (javax.swing.DefaultListModel)jList11.getModel();
            
            selectedValues = null;
            
            listEnum =  listModel.elements();
            
            while(listEnum.hasMoreElements()) {
                
                item2add.addElement(listEnum.nextElement());
                
            }
            
            selectedValues = item2add.toArray();
            } else {
             
                selectedValues = new Object[0];
                
            }
            
            
            
            for (int n = 0; n < selectedValues.length; n++) {
                
                System.out.print("Values : "+"\t"+selectedValues[n]+"\t");
                
            }
            
            
            
            java.lang.String str2 = this.getSqlString(selectedValues);
            
            
            
            
            
            // java.sql.Statement pstm = connectDB.createStatement();
            java.sql.PreparedStatement pstmnt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_fileinfo SET languages = '{"+str2+"}' WHERE staff_id = '"+jTextField363.getText()+"'");
            
            
            
            pstmnt.executeUpdate();
            
            
            
            
            
            
            // And finally this is for clubs
            
            
            item2add = new java.util.Vector(5, 5);
            
            listModel = new javax.swing.DefaultListModel();
            
            listModel = (javax.swing.DefaultListModel)jList111.getModel();
            
            selectedValues = null;
            
            listEnum =  listModel.elements();
            
            while(listEnum.hasMoreElements()) {
                
                item2add.addElement(listEnum.nextElement());
                
            }
            
            selectedValues = item2add.toArray();
            
            
            
            
            for (int m = 0; m < selectedValues.length; m++) {
                
                System.out.print("Values : "+"\t"+selectedValues[m]+"\t");
                
            }
            
            
            
            java.lang.String str3 = this.getSqlString(selectedValues);
            
            
            
            
            
            
            // java.sql.Statement prestmt = connectDB.createStatement();
            java.sql.PreparedStatement pmstamt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_fileinfo SET clubs = '{"+str3+"}' WHERE staff_id = '"+jTextField363.getText()+"'");
            
            
            
            
            pmstamt.executeUpdate();
            
            
            System.out.println("----------------------------------------------------------------");
            
            
            
            javax.swing.JOptionPane.showMessageDialog(this, "Successfuly updated the selected items ");
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(sq.getMessage());
        }
        
        
        javax.swing.JOptionPane.showMessageDialog(this, "All the assigned items have been inserted successfully");        // Add your handling code here:
        
        
        
        
                // Add your handling code here:
    }//GEN-LAST:event_newAction4ActionPerformed

    private void newAction21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction21ActionPerformed
 try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_referees_xtras (name,org,title,postal,telephone,remarks,staff_id) values(?,?,?,?,?,?,?)");
            for (int i = 0; i < jTable10.getRowCount(); i++){
                if (jTable10.getModel().getValueAt(i,0) != null){
                    
                    pstmt.setObject(1,jTable10.getModel().getValueAt(i,0).toString());
                    pstmt.setObject(2,jTable10.getModel().getValueAt(i,1).toString());
                    pstmt.setObject(3,jTable10.getModel().getValueAt(i,2).toString());
                    pstmt.setObject(4,jTable10.getModel().getValueAt(i,3).toString());
                    pstmt.setObject(5,jTable10.getModel().getValueAt(i,4).toString());
                    pstmt.setObject(6,jTable10.getModel().getValueAt(i,5).toString());
                    pstmt.setString(7,jTextField105.getText());  
                    
                    pstmt.executeUpdate();
                    
                }
            }
            java.sql.PreparedStatement ppstmt = connectDB.prepareStatement("UPDATE hr.hr_referees_xtras set club_office = ?, post = ?, begindate = ?,enddate = ?, remarks = ? WHERE staff_id = '"+jTextField105.getText()+"'");
            for (int k = 0; k < jTable2.getRowCount(); k++){
                if (jTable2.getModel().getValueAt(k,0) != null){
                    
                    ppstmt.setObject(1,jTable2.getModel().getValueAt(k,0).toString());
                    ppstmt.setObject(2,jTable2.getModel().getValueAt(k,1).toString());
                    ppstmt.setObject(3,jTable2.getModel().getValueAt(k,2).toString());
                    ppstmt.setObject(4,jTable2.getModel().getValueAt(k,3).toString());
                    ppstmt.setObject(5,jTable2.getModel().getValueAt(k,4).toString());
                    
                    
                    
                    ppstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);
                }
            }
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }        // Add your handling code here:
    }//GEN-LAST:event_newAction21ActionPerformed
    
    private void newAction311ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction311ActionPerformed
        String selectedchkbx;
        if (this.jCheckBox1.isSelected()){
            selectedchkbx = jCheckBox1.getText();
            
            
        } else{
            // if (this.jCheckBox3.isSelected())
            selectedchkbx = jCheckBox2.getText();
        }
        
        try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("update hr.hr_staffresume_fileinfo set birth_date=?,current_residence=?,national_id_no=?,passport_no=?,marital_status=?,height=?,weight=?,ethnicity=?,gender=?,handicup_status=?,nssf_no=?,nhif_no=?,nationality=?,initcap(spouse_name)=?,spouse_birthdate=?,spouse_job_status=?,spouse_idno=? where staff_id='"+jTextField104.getText()+"'");
            
             pstmt.setObject(1,datePicker41.getDate());
            pstmt.setString(2,jTextField621.getText());
            pstmt.setString(3,jTextField631.getText());
            pstmt.setString(4,jTextField641.getText());
            pstmt.setString(5,jComboBox511.getSelectedItem().toString());
            pstmt.setString(6,jTextField651.getText());                                                      
            pstmt.setString(7,jTextField661.getText());
            pstmt.setString(8,jComboBox2.getSelectedItem().toString());
            pstmt.setString(9,jComboBox1.getSelectedItem().toString());
            pstmt.setString(10,jTextField69.getText());
            pstmt.setString(11,jTextField67.getText());
            pstmt.setString(12,jTextField68.getText());
            pstmt.setString(13,jComboBox8.getSelectedItem().toString());
            pstmt.setString(14,jTextField62.getText());
              pstmt.setObject(15,datePicker1.getDate());
            pstmt.setString(16,selectedchkbx);
            pstmt.setString(17,jTextField66.getText());
            
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
    }//GEN-LAST:event_newAction311ActionPerformed
    
    private void newAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction5ActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_qualf set regn_issuer= ?,licence_no = ?,licence_type = ?,details = ?, expiry_date = ?, date_of_issue = ?, condition = ?, remarks2 = ?, proffession = ? WHERE staff_id = '"+jTextField106.getText()+"'");
            for (int i = 0; i < jTable11.getRowCount(); i++){
                if (jTable11.getModel().getValueAt(i,0) != null){
                    
                    pstmt.setObject(1,jTable12.getModel().getValueAt(i,0).toString());
                    pstmt.setObject(2,jTable12.getModel().getValueAt(i,1).toString());
                    pstmt.setObject(3,jTable12.getModel().getValueAt(i,2).toString());
                    pstmt.setObject(4,jTable12.getModel().getValueAt(i,3).toString());
                    pstmt.setObject(5,jTable12.getModel().getValueAt(i,4).toString());
                    pstmt.setObject(6,jTable12.getModel().getValueAt(i,5).toString());
                    pstmt.setObject(7,jTable12.getModel().getValueAt(i,6).toString());
                    pstmt.setObject(8,jTable12.getModel().getValueAt(i,6).toString());
                    pstmt.setString(9,jTextField21.getText());
                    
                    pstmt.executeUpdate();
                    
                }
            }
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
    }//GEN-LAST:event_newAction5ActionPerformed
    
    private void newAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction2ActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE hr.hr_staffresume_qualf set begin_date = ?,end_date = ?,employer = ?, positions = ?, salary = ?, remarks = ? WHERE staff_id = '"+jTextField103.getText()+"'");
            for (int i = 0; i < jTable11.getRowCount(); i++){
                if (jTable11.getModel().getValueAt(i,0) != null){
                    
                    pstmt.setObject(1,jTable11.getModel().getValueAt(i,0).toString());
                    pstmt.setObject(2,jTable11.getModel().getValueAt(i,1).toString());
                    pstmt.setObject(3,jTable11.getModel().getValueAt(i,2).toString());
                    pstmt.setObject(4,jTable11.getModel().getValueAt(i,3).toString());
                    pstmt.setObject(5,jTable11.getModel().getValueAt(i,4).toString());
                     pstmt.setObject(6,jTable11.getModel().getValueAt(i,5).toString());
                    
                    pstmt.executeUpdate();
                    
                }
            }
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
    }//GEN-LAST:event_newAction2ActionPerformed
    
    private void newActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_staffresume_qualf set date_of_qualif=?,title_course=?,institution=?,grade=?,remarks1=?,course=? where staff_id='"+jTextField102.getText()+"'");
            for (int k = 0; k < jTable1.getRowCount(); k++){
                if (jTable1.getModel().getValueAt(k,0) != null){
                    
                    
                    prstmt.setObject(1,jTable1.getValueAt(k,0).toString());
                    prstmt.setObject(2,jTable1.getValueAt(k,1).toString());
                    prstmt.setObject(3,jTable1.getValueAt(k,2).toString());
                    prstmt.setObject(4,jTable1.getValueAt(k,3).toString());
                    prstmt.setObject(5,jTable1.getValueAt(k,4).toString());
                    prstmt.setString(6,qualificationidTxt2.getText());
                    
                    prstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);
                }
            }
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            
            
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
        // TODO add your handling code here:         // Add your handling code here:
    }//GEN-LAST:event_newActionActionPerformed
    
    private void newAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction1ActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_staffresume_qualf (qlf_id,qlf_desc,dep_reqd,staff_id) values(?,?,?,?)");
            
            
            pstmt.setString(1,jComboBox10.getSelectedItem().toString());
            pstmt.setString(2,jComboBox20.getSelectedItem().toString());
            pstmt.setString(3,jTextArea1.getText());
            pstmt.setString(4,jTextField101.getText());
            
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
            
        }        // Add your        // Add your handling code here:
    }//GEN-LAST:event_newAction1ActionPerformed
    
    private void newAction31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction31ActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_staffresume_add (place_of_birth,residence,district,town,tel_no1,tel_no2,mobile_1,mobile_2,city,nok_name,relationship_to_nok,website,email,postal_address,postal_code,nok_tel,nok_mobile,nok_email,nok_postal_address,nok_postal_code,nok_city,nok_town,staff_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setString(1,jTextField411.getText());
            pstmt.setString(2,jTextField611.getText());
            pstmt.setString(3,jComboBox10.getSelectedItem().toString());
            pstmt.setString(4,jComboBox20.getSelectedItem().toString());
            pstmt.setString(5,jTextField10.getText());
            pstmt.setString(6,jTextField12.getText());
            pstmt.setString(7,jTextField13.getText());
            pstmt.setString(8,jTextField14.getText());
            pstmt.setString(9,jTextField15.getText());
            pstmt.setString(10,jTextField22.getText());
            pstmt.setString(11,jTextField23.getText());
            pstmt.setString(12,jTextField19.getText());
            pstmt.setString(13,jTextField18.getText());
            pstmt.setString(14,jTextField16.getText());
            pstmt.setString(15,jTextField17.getText());
            pstmt.setString(16,jTextField24.getText());
            pstmt.setString(17,jTextField25.getText());
            pstmt.setString(18,jTextField26.getText());
            pstmt.setString(19,jTextField27.getText());
            pstmt.setString(20,jTextField28.getText());
            pstmt.setString(21,jTextField29.getText());
            pstmt.setString(22,jTextField30.getText());
            pstmt.setString(23,jTextField100.getText());
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
    }//GEN-LAST:event_newAction31ActionPerformed
    
    private void newAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction3ActionPerformed
     try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_staffresume_fileinfo(file_id,interview_date,first_name,second_name,others,staff_id,empmt_date,field_of_spec,position_app,dept,designation,section,job_group,jgrade,jstatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setObject(1,jTextField61.getText());
            //Date d= Date.valueOf();
            pstmt.setObject(2,datePicker2.getDate().toString());
            pstmt.setObject(3,jTextField21.getText());
            pstmt.setObject(4,jTextField11.getText());
            pstmt.setObject(5,jTextField51.getText());
            pstmt.setObject(6,jTextField91.getText());
            // Date dt= Date.valueOf();
            pstmt.setObject(7,datePicker3.getDate().toString());
            pstmt.setObject(8,jComboBox11.getSelectedItem().toString());
            pstmt.setObject(9,jComboBox31.getSelectedItem().toString());
            pstmt.setObject(10,jComboBox5.getSelectedItem().toString());
            pstmt.setObject(11,jComboBox122.getSelectedItem().toString());
            pstmt.setObject(12,jComboBox125.getSelectedItem().toString());
            pstmt.setObject(13,jComboBox6.getSelectedItem().toString());
            pstmt.setObject(14,jComboBox71.getSelectedItem().toString());
              pstmt.setObject(15,jComboBox7.getSelectedItem().toString());
            
            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            jTextField100.setText(jTextField91.getText());
            jTextField101.setText(jTextField91.getText());
            jTextField102.setText(jTextField91.getText());
            jTextField103.setText(jTextField91.getText());
            jTextField104.setText(jTextField91.getText());
            jTextField105.setText(jTextField91.getText());
            jTextField106.setText(jTextField91.getText());
           // jTextField107.setText(jTextField91.getText());
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
        }       // Add your handling code here:
    }//GEN-LAST:event_newAction3ActionPerformed
    
    private void jTextField62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField62ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField62ActionPerformed
                            public String getSqlString(java.lang.Object[] arrayfromJList) {
        
        java.lang.String strToArray = arrayfromJList[0].toString();
        
        for (int i = 1; i <arrayfromJList.length; i++) {
            
            strToArray = strToArray+", "+arrayfromJList[i].toString();
            
        }
        
        System.out.println(strToArray);
        
        return strToArray;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel imgPanel311;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton previousAction31;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel departmentrequiringqualificationLbl;
    private javax.swing.JButton firstAction3;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel imgPanel4;
    private javax.swing.JButton clearAction21;
    private javax.swing.JPanel mainPanel1;
    private javax.swing.JList jList112;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet15;
    private javax.swing.JPanel buttonPanel3;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel3113;
    private javax.swing.JLabel jLabel611;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JButton helpAction311;
    private javax.swing.JLabel jLabel41111;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton helpAction;
    private javax.swing.JPanel mainPanel21;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel facilitynameLbl112;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel fieldsPanel3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JPanel fieldsPanel311;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel31111;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton previousAction;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JPanel subMainPanel211;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JButton newAction21;
    private javax.swing.JButton searchButton132;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JButton firstAction31;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JPanel imgPanel2;
    private javax.swing.JButton jButton13;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet17;
    private javax.swing.JTextField jTextField103;
    private javax.swing.JTextField jTextField102;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JButton firstAction211;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JLabel jLabel61111;
    private javax.swing.JLabel jLabel3111111;
    private javax.swing.JLabel facilityidLbl1131;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JTextField jTextField105;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JPanel actionsPanel21;
    private javax.swing.JButton updateAction3;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel611311;
    private javax.swing.JButton updateAction21;
    private javax.swing.JLabel facilitynameLbl11111;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JButton clearAction;
    private javax.swing.JPanel buttonPanel2;
    private javax.swing.JButton clearAction2;
    private javax.swing.JButton updateAction5;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JPanel actionsPanel4;
    private javax.swing.JButton nextAction211;
    private javax.swing.JLabel facilityidLbl11211;
    private javax.swing.JPanel actionsPanel311;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JButton deleteAction;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JButton deleteAction311;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton updateAction1;
    private javax.swing.JPanel helpPanel3;
    private javax.swing.JPanel mainPanel311;
    private javax.swing.JPanel fieldsPanel1;
    private javax.swing.JLabel jLabel311412;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton helpAction5;
    private javax.swing.JPanel dataPanel2;
    private javax.swing.JPanel labelPanel2;
    private javax.swing.JButton helpAction3;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JLabel facilityidLbl1121;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JButton nextAction2;
    private javax.swing.JButton jButton11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton newAction1;
    private javax.swing.JTextField jTextField641;
    private javax.swing.JPanel dataPanel311;
    private javax.swing.JPanel labelPanel4;
    private javax.swing.JButton newAction;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JButton helpAction1;
    private javax.swing.JPanel imgPanel21;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JPanel spacerPanel311;
    private javax.swing.JButton jButton51;
    private javax.swing.JComboBox jComboBox71;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel subMainPanel2;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JButton deleteAction1;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JComboBox jComboBox125;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet12;
    private javax.swing.JTextField qualificationidTxt21;
    private javax.swing.JButton newAction4;
    private org.netbeans.lib.sql.NBCachedRowSet searchRowSet1;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JPanel spacerPanel3;
    private javax.swing.JPanel helpPanel5;
    private javax.swing.JPanel mainPanel5;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JTextField jTextField19;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet19;
    private javax.swing.JLabel facilityidLbl11411;
    private javax.swing.JPanel jPanel61131;
    private javax.swing.JButton deleteAction5;
    private javax.swing.JPanel jPanel12111;
    private javax.swing.JLabel facilityidLbl117;
    private javax.swing.JLabel jLabel311111;
    private javax.swing.JPanel dataPanel31;
    private javax.swing.JLabel facilityidLbl1161;
    private javax.swing.JPanel labelPanel21;
    private javax.swing.JLabel facilityidLbl115;
    private javax.swing.JPanel mainPanel31;
    private javax.swing.JButton helpAction4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel actionsPanel1;
    private javax.swing.JTextField qualificationidTxt2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton deleteAction3;
    private javax.swing.JLabel qualificationidLbl21;
    private javax.swing.JButton newAction311;
    private javax.swing.JPanel subMainPanel31;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton nextAction31;
    private javax.swing.JTable jTable12;
    private javax.swing.JLabel jLabel31142;
    private javax.swing.JButton previousAction211;
    private javax.swing.JPanel helpPanel1;
    private javax.swing.JPanel dataPanel22;
    private javax.swing.JPanel buttonPanel31;
    private javax.swing.JButton previousAction21;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton firstAction21;
    private javax.swing.JButton lastAction3;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JButton searchButton1313;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JLabel facilityidLbl1111;
    private javax.swing.JList jList14;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel fieldsPanel21;
    private javax.swing.JLabel jLabel311113;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton12;
    private javax.swing.JPanel jPanel6112;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel subMainPanel311;
    private javax.swing.JPanel jPanel611313;
    private javax.swing.JButton firstAction;
    private javax.swing.JLabel facilityidLbl11;
    private javax.swing.JButton clearAction31;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JButton helpAction31;
    private javax.swing.JButton searchButton12;
    private javax.swing.JList jList1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JPanel dataPanel3;
    private javax.swing.JTable jTable10;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet6;
    private javax.swing.JLabel qualifiableamountLbl;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet10;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel spacerPanel31;
    private javax.swing.JButton previousAction2;
    private javax.swing.JList jList12;
    private javax.swing.JButton clearAction4;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JTextField jTextField11111;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchButton1311;
    private javax.swing.JButton jButton31;
    private javax.swing.JPanel actionsPanel3;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel facilityidLbl111;
    private javax.swing.JPanel imgPanel31;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet4;
    private javax.swing.JPanel spacerPanel1;
    private javax.swing.JPanel buttonPanel21;
    private javax.swing.JButton lastAction211;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JButton updateAction31;
    private javax.swing.JLabel facilityidLbl11611;
    private javax.swing.JPanel jPanel1211;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JComboBox jComboBox511;
    private javax.swing.JLabel jLabel711;
    private javax.swing.JTextField jTextField651;
    private javax.swing.JButton lastAction311;
    private javax.swing.JLabel facilityidLbl113;
    private javax.swing.JPanel spacerPanel5;
    private javax.swing.JPanel fieldsPanel211;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet8;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JLabel jLabel411;
    private javax.swing.JTextField jTextField411;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet18;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JPanel dataPanel211;
    private javax.swing.JButton searchButton131;
    private javax.swing.JButton firstAction311;
    private javax.swing.JButton jButton33;
    private javax.swing.JLabel jLabel1221;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel31112;
    private javax.swing.JDialog jSearchDialog1;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet16;
    private javax.swing.JLabel jLabel2311;
    private javax.swing.JList jList111;
    private javax.swing.JButton lastAction21;
    private javax.swing.JPanel helpPanel31;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel facilityidLbl1151;
    private com.afrisoftech.lib.DatePicker datePicker41;
    private javax.swing.JButton deleteAction31;
    private javax.swing.JPanel imgPanel3;
    private javax.swing.JPanel fieldsPanel2;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JButton newAction31;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JTextField jTextField22;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private javax.swing.JLabel jLabel31141;
    private javax.swing.JLabel jLabel3114;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JLabel facilitynameLbl111;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JPanel fieldsPanel3111;
    private javax.swing.JScrollPane jScrollPane25;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet14;
    private javax.swing.JButton searchButton1312;
    private javax.swing.JPanel actionsPanel5;
    private javax.swing.JButton updateAction311;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JButton firstAction2;
    private javax.swing.JButton clearAction311;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTextField jTextField106;
    private javax.swing.JScrollPane jScrollPane221;
    private javax.swing.JButton clearAction3;
    private javax.swing.JPanel actionsPanel31;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JButton updateAction2;
    private javax.swing.JPanel jPanel611312;
    private javax.swing.JLabel jLabel6111;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JButton jButton411;
    private javax.swing.JButton clearAction1;
    private javax.swing.JPanel labelPanel5;
    private javax.swing.JLabel facilityidLbl1141;
    private javax.swing.JPanel helpPanel2;
    private javax.swing.JButton helpAction21;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JButton previousAction311;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lastAction;
    private javax.swing.JLabel jLabel311411;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JList jList113;
    private javax.swing.JList jList11;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton nextAction;
    private javax.swing.JLabel jLabel71111;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel311413;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JButton newAction2;
    private javax.swing.JPanel subMainPanel21;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JLabel jLabel3111121;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JButton nextAction3;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JButton helpAction2;
    private javax.swing.JLabel qualificationdescriptionLbl;
    private javax.swing.JPanel labelPanel3;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JPanel helpPanel311;
    private javax.swing.JTextField jTextField621;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel subMainPanel3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel facilityidLbl116;
    private javax.swing.JTextField jTextField363;
    private javax.swing.JPanel subMainPanel22;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet13;
    private javax.swing.JPanel buttonPanel22;
    private javax.swing.JLabel jLabel71112;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JPanel labelPanel1;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JButton deleteAction4;
    private javax.swing.JPanel buttonPanel311;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet11;
    private javax.swing.JButton updateAction;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel helpPanel4;
    private javax.swing.JPanel labelPanel31;
    private javax.swing.JPanel helpPanel21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton newAction3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton deleteAction2;
    private javax.swing.JLabel jLabel311414;
    private javax.swing.JPanel jPanel61132;
    private javax.swing.JButton newAction5;
    private javax.swing.JButton lastAction31;
    private javax.swing.JButton nextAction21;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet20;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel subMainPanel1;
    private com.afrisoftech.lib.DatePicker datePicker3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel actionsPanel2;
    private javax.swing.JButton previousAction22;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel41112;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTable jTable11;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField661;
    private javax.swing.JButton searchButton1314;
    private javax.swing.JButton jButton41;
    private javax.swing.JComboBox jComboBox31;
    private javax.swing.JButton jButton511;
    private org.netbeans.lib.sql.NBCachedRowSet crset1;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel mainPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton nextAction311;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JButton clearAction5;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JPanel dataPanel21;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JPanel jPanel611314;
    private javax.swing.JButton firstAction22;
    private javax.swing.JPanel fieldsPanel31;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel labelPanel311;
    private javax.swing.JButton nextAction32;
    private javax.swing.JTextField jTextField631;
    private javax.swing.JList jList13;
    private javax.swing.JLabel facilityidLbl1;
    private javax.swing.JPanel spacerPanel21;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JPanel fieldsPanel22;
    private javax.swing.JButton previousAction3;
    private javax.swing.JButton lastAction22;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JScrollPane jScrollPane211;
    private javax.swing.JLabel qualificationidLbl2;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel facilitynameLbl1111;
    private javax.swing.JLabel qualificationidLbl;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet5;
    private javax.swing.JPanel imgPanel5;
    private javax.swing.JPanel spacerPanel2;
    private javax.swing.JComboBox jComboBox122;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton deleteAction21;
    private javax.swing.JPanel spacerPanel4;
    private javax.swing.JLabel facilityidLbl112;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet9;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private javax.swing.JPanel imgPanel1;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JPanel jPanel6113;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel facilityidLbl114;
    private javax.swing.JTextField jTextField611;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet7;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JButton lastAction2;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JButton jButton22;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel facilityidLbl11111;
    private javax.swing.JButton searchButton13;
    // End of variables declaration//GEN-END:variables
    
}
