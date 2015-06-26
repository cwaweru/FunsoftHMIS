/*
 * DefaultInternalFrame.java
 *
 * Created on May 31, 2004, 1:53 PM
 */
package com.afrisoftech.hr;

import com.afrisoftech.dbadmin.TableModel;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author postgres
 */
public class DutyRotaIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    private int duty_rota_id;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    public DutyRotaIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        connectDB = connDb;

        pConnDB = pconnDB;

        initComponents();
        datePicker2.setDateFormatString("yyyy-MMM-d");
        datePicker3.setDateFormatString("yyyy-MMM-d");

        nBCachedRowSet6.setCommand("Select distinct(department_name) from hr.hr_department where department_name<>'' order by department_name asc");
        nBCachedRowSet6.setConnectionSource(pConnDB);
        dptcbx.setModel(new org.netbeans.lib.sql.models.ComboBoxModel(nBCachedRowSet6, "department_name", null, null, null));

        nBCachedRowSet7.setCommand("Select distinct(section) from hr.hr_sections order by section asc");
        nBCachedRowSet7.setConnectionSource(pConnDB);
        sectcbx.setModel(new org.netbeans.lib.sql.models.ComboBoxModel(nBCachedRowSet7, "section", null, null, null));
        //  jComboBox11121.addItem("Taveta");
        //  jComboBox11121.addItem("Other");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField1111 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new javax.swing.JTable();
        jButton41 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
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
        jSearchDialog11 = new javax.swing.JDialog();
        jSearchPanel12 = new javax.swing.JPanel();
        jTextField11112 = new javax.swing.JTextField();
        jSearchScrollPane12 = new javax.swing.JScrollPane();
        jSearchTable12 = new javax.swing.JTable();
        jButton412 = new javax.swing.JButton();
        jButton512 = new javax.swing.JButton();
        try {
            crset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jSearchDialog12 = new javax.swing.JDialog();
        jSearchPanel13 = new javax.swing.JPanel();
        jTextField11113 = new javax.swing.JTextField();
        jSearchScrollPane13 = new javax.swing.JScrollPane();
        jSearchTable13 = new javax.swing.JTable();
        jButton413 = new javax.swing.JButton();
        jButton513 = new javax.swing.JButton();
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
        dataPanel3 = new javax.swing.JPanel();
        firstAction3 = new javax.swing.JButton();
        previousAction3 = new javax.swing.JButton();
        nextAction2 = new javax.swing.JButton();
        lastAction3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        rotatitle = new javax.swing.JTextField();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        datePicker3 = new com.afrisoftech.lib.DatePicker();
        dptcbx = new javax.swing.JComboBox();
        sectcbx = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        desccbx = new javax.swing.JTextArea();
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
        fieldsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        dataPanel = new javax.swing.JPanel();
        firstAction = new javax.swing.JButton();
        previousAction = new javax.swing.JButton();
        nextAction = new javax.swing.JButton();
        lastAction = new javax.swing.JButton();
        jLabel311 = new javax.swing.JLabel();
        jPanel611 = new javax.swing.JPanel();
        jTextField361 = new javax.swing.JTextField();
        searchButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        mainPanel5 = new javax.swing.JPanel();
        subMainPanel2 = new javax.swing.JPanel();
        fieldsPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel316 = new javax.swing.JLabel();
        jPanel612 = new javax.swing.JPanel();
        jTextField365 = new javax.swing.JTextField();
        searchButton2 = new javax.swing.JButton();

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
        jTextField1111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1111ActionPerformed(evt);
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

        jButton41.setText("Select");
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

        nBCachedRowSet1.setConnectionSource(pConnDB);

        nBCachedRowSet2.setConnectionSource(pConnDB);

        nBCachedRowSet3.setConnectionSource(pConnDB);

        nBCachedRowSet4.setConnectionSource(pConnDB);

        nBCachedRowSet5.setConnectionSource(pConnDB);

        nBCachedRowSet6.setConnectionSource(pConnDB);

        nBCachedRowSet8.setConnectionSource(pConnDB);

        nBCachedRowSet9.setConnectionSource(pConnDB);

        nBCachedRowSet10.setConnectionSource(pConnDB);

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel11.setLayout(new java.awt.GridBagLayout());

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

        searchRowSet1.setConnectionSource(pConnDB);

        jSearchDialog11.setModal(true);
        jSearchDialog11.setUndecorated(true);
        jSearchDialog11.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel12.setLayout(new java.awt.GridBagLayout());

        jTextField11112.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11112CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel12.add(jTextField11112, gridBagConstraints);

        jSearchTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jSearchTable12.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable12.setShowHorizontalLines(false);
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
        jSearchTable12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable12MouseClicked(evt);
            }
        });
        jSearchScrollPane12.setViewportView(jSearchTable12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel12.add(jSearchScrollPane12, gridBagConstraints);

        jButton412.setText("Select");
        jButton412.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton412ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel12.add(jButton412, gridBagConstraints);

        jButton512.setText("Close");
        jButton512.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton512ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel12.add(jButton512, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog11.getContentPane().add(jSearchPanel12, gridBagConstraints);

        crset1.setConnectionSource(pConnDB);

        jSearchDialog12.setModal(true);
        jSearchDialog12.setUndecorated(true);
        jSearchDialog12.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel13.setLayout(new java.awt.GridBagLayout());

        jTextField11113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11113CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel13.add(jTextField11113, gridBagConstraints);

        jSearchTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jSearchTable13.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable13.setShowHorizontalLines(false);
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
        jSearchTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable13MouseClicked(evt);
            }
        });
        jSearchScrollPane13.setViewportView(jSearchTable13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel13.add(jSearchScrollPane13, gridBagConstraints);

        jButton413.setText("Select");
        jButton413.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton413ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel13.add(jButton413, gridBagConstraints);

        jButton513.setText("Close");
        jButton513.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton513ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel13.add(jButton513, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog12.getContentPane().add(jSearchPanel13, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FILE INFORMATION");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        mainPanel3.setLayout(new java.awt.GridBagLayout());

        labelPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel3.setLayout(new java.awt.GridBagLayout());

        imgPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif"))); // NOI18N
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

        actionsPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel3.setLayout(new java.awt.GridBagLayout());

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

        helpPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel3.setLayout(new java.awt.GridBagLayout());

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

        subMainPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        subMainPanel3.setLayout(new java.awt.GridBagLayout());

        dataPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data navagation actions"));
        dataPanel3.setLayout(new java.awt.GridBagLayout());

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

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel31.setText("Rota Title:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel31, gridBagConstraints);

        jLabel61.setText("Valid From");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel61, gridBagConstraints);

        jLabel71.setText("Valid Till");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel71, gridBagConstraints);

        jLabel81.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel81, gridBagConstraints);

        jLabel91.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel91, gridBagConstraints);

        jLabel101.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel101, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(rotatitle, gridBagConstraints);

        datePicker2.setDateFormatString("yyyy-MMM-d ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel12.add(datePicker2, gridBagConstraints);

        datePicker3.setDateFormatString("yyyy-MMM-d ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel12.add(datePicker3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel12.add(dptcbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel12.add(sectcbx, gridBagConstraints);

        desccbx.setColumns(20);
        desccbx.setRows(5);
        jScrollPane4.setViewportView(desccbx);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel12.add(jScrollPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
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

        jTabbedPane1.addTab("Create Duty Rota", jPanel1);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        mainPanel1.setLayout(new java.awt.GridBagLayout());

        labelPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel1.setLayout(new java.awt.GridBagLayout());

        imgPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif"))); // NOI18N
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

        actionsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel1.setLayout(new java.awt.GridBagLayout());

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

        helpPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel1.setLayout(new java.awt.GridBagLayout());

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

        subMainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        subMainPanel.setLayout(new java.awt.GridBagLayout());

        fieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N
        fieldsPanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter details of the selected Level", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N
        jScrollPane23.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Shift", "From", "To", "Shift ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable13MouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(jTable13);

        jScrollPane1.setViewportView(jScrollPane23);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        fieldsPanel.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel.add(fieldsPanel, gridBagConstraints);

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data navagation actions"));
        dataPanel.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(dataPanel, gridBagConstraints);

        jLabel311.setText("Select Duty Rota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(jLabel311, gridBagConstraints);

        jPanel611.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel611.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel611.setLayout(new java.awt.GridBagLayout());

        jTextField361.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel611.add(jTextField361, gridBagConstraints);

        searchButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton1.setToolTipText("Search");
        searchButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel611.add(searchButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        subMainPanel.add(jPanel611, gridBagConstraints);

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

        jTabbedPane1.addTab("Update Time Bands", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        mainPanel5.setLayout(new java.awt.GridBagLayout());

        subMainPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        subMainPanel2.setLayout(new java.awt.GridBagLayout());

        fieldsPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N
        fieldsPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter details of the selected Level", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11))); // NOI18N
        jScrollPane27.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "From", "To", "MON", "TUE", "WED", "THUR", "FRI", "SAT", "SUN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable15MouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(jTable15);
        jTable15.getColumnModel().getColumn(0).setMaxWidth(60);

        jScrollPane5.setViewportView(jScrollPane27);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        fieldsPanel2.add(jScrollPane5, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("GENERATE DUTY ROTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        fieldsPanel2.add(jButton1, gridBagConstraints);

        jCheckBox1.setText("Repeat Monday Selection ");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 2.0;
        fieldsPanel2.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel2.add(fieldsPanel2, gridBagConstraints);

        jLabel316.setText("Select Duty Rota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel2.add(jLabel316, gridBagConstraints);

        jPanel612.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel612.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel612.setLayout(new java.awt.GridBagLayout());

        jTextField365.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel612.add(jTextField365, gridBagConstraints);

        searchButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton2.setToolTipText("Search");
        searchButton2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton2.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton2.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel612.add(searchButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        subMainPanel2.add(jPanel612, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel5.add(subMainPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(mainPanel5, gridBagConstraints);

        jTabbedPane1.addTab("Populate Rota", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 1025, 661);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton512ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton512ActionPerformed
        jSearchDialog11.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton512ActionPerformed

    private void jSearchTable12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable12MouseClicked
//jTextField36.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());
        //jTextField361.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());      
// jTextField362.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());
        //jTextField363.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());
        //jTextField364.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());

        jTextField361.setText(jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString());
        String rota_name = jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 0).toString();
        String dept_name = jSearchTable12.getValueAt(jSearchTable12.getSelectedRow(), 1).toString();
        populateTimeBands(jTable13, rota_name, dept_name);
//System.out.println("The selected Value is: "+jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),0).toString());
        //jTable13.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),1),jTable13.getSelectedRow(), 2);
        // jTable13.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),2),jTable13.getSelectedRow(), 4);


        jSearchDialog11.dispose();              // Add your ha        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable12MouseClicked

    private void populateTimeBands(javax.swing.JTable jt, String rota_name, String dept_name) {

        for (int i = 0; i < jt.getRowCount(); i++) {

            jt.setValueAt("", i, 0);
            jt.setValueAt("", i, 1);
            jt.setValueAt("", i, 2);
            jt.setValueAt("", i, 3);

        }

        try {
            System.out.println("select * from hr.hr_duty_rota_shifts left join hr.hr_duty_rota on hr.hr_duty_rota_shifts.duty_rota_id=hr.hr_duty_rota.duty_rota_id where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "' and hr.hr_duty_rota_shifts.status='1'");

            java.sql.Statement st2 = connectDB.createStatement();


            java.sql.ResultSet rs = st2.executeQuery("select * from hr.hr_duty_rota_shifts left join hr.hr_duty_rota on hr.hr_duty_rota_shifts.duty_rota_id=hr.hr_duty_rota.duty_rota_id where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "' and hr.hr_duty_rota_shifts.status='1'");




            int i = 0;
            while (rs.next()) {

                jt.getModel().setValueAt(rs.getObject(2), i, 0);
                jt.getModel().setValueAt(rs.getObject(6), i, 1);
                jt.getModel().setValueAt(rs.getObject(3), i, 2);
                jt.getModel().setValueAt(rs.getObject(1), i, 3);
                this.duty_rota_id = Integer.parseInt(rs.getObject(5).toString());
                System.out.println("Changing duty rota ID to "+Integer.parseInt(rs.getObject(5).toString())+" New rota is "+ this.duty_rota_id);
                System.out.println("Setting values for " + rs.getObject(2) + " on " + i);
                i++;
            }

            if(rs.next()){
                
            }else{
                java.sql.ResultSet rs3 = st2.executeQuery("select * from hr.hr_duty_rota where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "'");
                while(rs3.next()){
                    this.duty_rota_id = Integer.parseInt(rs3.getObject(1).toString());
                }
            }





            /*      java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_appresume_qualif set qual_date=?,institution2=?,course=?,grade2=?,course_rmks=? where app_id='"+jTextField361.getText()+"'");
             for (int k = 0; k < jTable14.getRowCount(); k++){
             if (jTable14.getModel().getValueAt(k,0) != null){

             prstmt.setObject(1,jTable14.getValueAt(k,0).toString());
             prstmt.setObject(2,jTable14.getValueAt(k,1).toString());
             prstmt.setObject(3,jTable14.getValueAt(k,2).toString());
             prstmt.setObject(4,jTable14.getValueAt(k,3).toString());
             prstmt.setObject(5,jTable14.getValueAt(k,4).toString());

             prstmt.executeUpdate();
             connectDB.commit();
             connectDB.setAutoCommit(true);

             javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
             }

             }
             */
        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

        // TODO add your handling code here:        // Add your handling code here:




    }

    private void populateAssignedTimeBands(javax.swing.JTable jt, String rota_name, String dept_name) {

        for (int i = 0; i < jt.getRowCount(); i++) {

            jt.setValueAt("", i, 0);
            jt.setValueAt("", i, 1);
            jt.setValueAt("", i, 2);
            jt.setValueAt("", i, 3);
            jt.setValueAt("", i, 4);
            jt.setValueAt("", i, 5);
            jt.setValueAt("", i, 6);
            jt.setValueAt("", i, 7);
            jt.setValueAt("", i, 8);
            jt.setValueAt("", i, 9);
            jt.setValueAt("", i, 10);


        }

        try {
            System.out.println("select * from hr.hr_duty_rota_shifts left join hr.hr_duty_rota on hr.hr_duty_rota_shifts.duty_rota_id=hr.hr_duty_rota.duty_rota_id where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "' and hr.hr_duty_rota_shifts.status='1'");

            java.sql.Statement st2 = connectDB.createStatement();


            java.sql.ResultSet rs = st2.executeQuery("select * from hr.hr_duty_rota_shifts left join hr.hr_duty_rota on hr.hr_duty_rota_shifts.duty_rota_id=hr.hr_duty_rota.duty_rota_id where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "' and hr.hr_duty_rota_shifts.status='1'");




            int i = 0;
            while (rs.next()) {

                jt.getModel().setValueAt(rs.getObject(1), i, 0);
                jt.getModel().setValueAt(rs.getObject(2), i, 1);
                jt.getModel().setValueAt(rs.getObject(6), i, 2);
                jt.getModel().setValueAt(rs.getObject(3), i, 3);
                this.duty_rota_id = Integer.parseInt(rs.getObject(5).toString());
                System.out.println("Setting values for " + rs.getObject(2) + " on " + i);
                i++;
            }



  if(rs.next()){
                
            }else{
                java.sql.ResultSet rs3 = st2.executeQuery("select * from hr.hr_duty_rota where hr.hr_duty_rota.duty_rota_title='" + rota_name + "' and hr.hr_duty_rota.department='" + dept_name + "'");
                while(rs3.next()){
                    this.duty_rota_id = Integer.parseInt(rs3.getObject(1).toString());
                }
            }



            /*      java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_appresume_qualif set qual_date=?,institution2=?,course=?,grade2=?,course_rmks=? where app_id='"+jTextField361.getText()+"'");
             for (int k = 0; k < jTable14.getRowCount(); k++){
             if (jTable14.getModel().getValueAt(k,0) != null){

             prstmt.setObject(1,jTable14.getValueAt(k,0).toString());
             prstmt.setObject(2,jTable14.getValueAt(k,1).toString());
             prstmt.setObject(3,jTable14.getValueAt(k,2).toString());
             prstmt.setObject(4,jTable14.getValueAt(k,3).toString());
             prstmt.setObject(5,jTable14.getValueAt(k,4).toString());

             prstmt.executeUpdate();
             connectDB.commit();
             connectDB.setAutoCommit(true);

             javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
             }

             }
             */
            populateWorksheet(jt);
            
            
            
            
        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

        // TODO add your handling code here:        // Add your handling code here:




    }

    private void jTextField11112CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11112CaretUpdate
        try {
            crset1.execute("select duty_rota_title,duty_rota_id,department from hr.hr_duty_rota where duty_rota_title ||' '||duty_rota_id ILIKE '" + jTextField11112.getText().toString() + "%' ORDER BY duty_rota_title");
            // System.out.println("select duty_rota_title,duty_rota_id from hr.hr_duty_rota where duty_rota_id ||' '||duty_rota_title ILIKE '"+jTextField11112.getText().toString()+"%' ORDER BY duty_rota_title");
            jSearchTable12.setModel(new org.netbeans.lib.sql.models.TableModel(crset1, new org.netbeans.lib.sql.models.TableModel.Column[]{
                        //  new org.netbeans.lib.sql.models.TableModel.Column("duty_rota_id", "ID", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("duty_rota_title", "Title", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("department", "Department", false)
                    }));
            jSearchTable12.setShowHorizontalLines(false);
            jSearchScrollPane12.setViewportView(jSearchTable12);

            System.out.println("Cannot sort out");
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }
        // Add your handling code he        // Add your handling code here:

        /*
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
         */

    }//GEN-LAST:event_jTextField11112CaretUpdate

    private void jTextField1111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1111ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField1111ActionPerformed

    private void jButton511ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton511ActionPerformed
        jSearchDialog2.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jButton511ActionPerformed

    private void jSearchTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable11MouseClicked
        jTable13.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 0), jTable13.getSelectedRow(), 0);
        System.out.println("The selected Value is: " + jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 0).toString());
        jTable13.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 1), jTable13.getSelectedRow(), 2);
        jTable13.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 2), jTable13.getSelectedRow(), 4);

        jSearchDialog2.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jSearchTable11MouseClicked
    private void cmboxMouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = jTable13.getLocationOnScreen();
        jSearchDialog2.setSize(400, 200);
        jSearchDialog2.setLocation(point);
        jSearchDialog2.setVisible(true);
    }

    private void jTextField11111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11111CaretUpdate

        //select distinct upper(qualification_desc) as qualif from hr.hr_education_qlf order by qualif        // Add your handling code here:
        try {
            searchRowSet1.execute("SELECT qualification_desc from hr.hr_education_qlf where qualification_desc ILIKE '" + jTextField11111.getText() + "%' order by qualification_desc asc");
            System.out.println("SELECT qualification_desc from hr.hr_education_qlf where qualification_desc ILIKE '" + jTextField11111.getText() + "%' order by qualification desc");
            jSearchTable11.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[]{
                        new org.netbeans.lib.sql.models.TableModel.Column("qualification_desc", "Description", false)
                    //  new org.netbeans.lib.sql.models.TableModel.Column("price", "Price", false),
                    //  new org.netbeans.lib.sql.models.TableModel.Column("training_type", "Type", false)
                    }));
            jSearchScrollPane11.setViewportView(jSearchTable11);
            System.out.println("Cannot sort out");
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }          // Add your handling code he 
    }//GEN-LAST:event_jTextField11111CaretUpdate

    public void searchButtonClicked() {
        /*  System.out.println("Showing dialog");
        
         jSearchDialog11.dispose();
         java.awt.Point point = this.jTextField36.getLocationOnScreen();
        
         jSearchDialog11.setSize(400,200);
        
         jSearchDialog11.setLocation(point);
         jSearchDialog11.setVisible(true);*/
    }

    public void jTable13MouseClicked(MouseEvent e) {
    }

    public void searchButton1Clicked() {

        System.out.println("Showing dialog");

        jSearchDialog11.dispose();
        java.awt.Point point = this.jTextField361.getLocationOnScreen();

        jSearchDialog11.setSize(400, 200);

        jSearchDialog11.setLocation(point);
        jSearchDialog11.setVisible(true);

    }

    public void searchButton11Clicked() {
        System.out.println("Showing dialog");

        jSearchDialog1.dispose();
        java.awt.Point point = this.jTextField365.getLocationOnScreen();

        jSearchDialog1.setSize(400, 200);

        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
    }

    public void searchButton12Clicked() {
        /* System.out.println("Showing dialog");
        
         jSearchDialog11.dispose();
         java.awt.Point point = this.jTextField363.getLocationOnScreen();
        
         jSearchDialog11.setSize(400,200);
        
         jSearchDialog11.setLocation(point);
         jSearchDialog11.setVisible(true);*/
    }

    public void searchButton13Clicked() {

        System.out.println("Showing dialog");

        jSearchDialog11.dispose();
        java.awt.Point point = this.jTextField361.getLocationOnScreen();

        jSearchDialog11.setSize(400, 200);

        jSearchDialog11.setLocation(point);
        jSearchDialog11.setVisible(true);

    }
    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
        searchButton1Clicked();        // Add your handling code here:
    }//GEN-LAST:event_searchButton1ActionPerformed

//GEN-FIRST:event_jTable13MouseClicked
 
//GEN-LAST:event_jTable13MouseClicked

    private void newAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction1ActionPerformed
        try {

            connectDB.setAutoCommit(false);
            int updates = 0;
            int inserts = 0;
            String dept = "";
            String rota = "";
            for (int i = 0; i < jTable13.getRowCount(); i++) {

                if (jTable13.getModel().getValueAt(i, 0) != "" && jTable13.getModel().getValueAt(i, 3) != "") {
                     System.out.println("The value at column 3 is '" +jTable13.getModel().getValueAt(i, 3) + "'");
                    int shift = Integer.parseInt(jTable13.getModel().getValueAt(i, 3).toString());
                    java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_duty_rota_shifts set shift_title = ?,_from = ?,_to = ? where shift_id='" + shift + "'");
                    System.out.println("update hr.hr_duty_rota_shifts set shift_title = ?,_from = ?,_to = ? where shift_id='" + shift + "'");
                    //pstmt.setString(1,jComboBox4.getSelectedItem().toString());
                    prstmt.setObject(1, jTable13.getValueAt(i, 0).toString());
                    prstmt.setObject(2, jTable13.getValueAt(i, 1).toString());
                    prstmt.setObject(3, jTable13.getValueAt(i, 2).toString());

                    //prstmt.setObject(4,"1");
                    //         pstmt.setString(8,jTextArea1.getText());
                    updates++;
                    prstmt.executeUpdate();
                    connectDB.commit();

                } else if (jTable13.getModel().getValueAt(i, 0).toString().trim() != "" && jTable13.getModel().getValueAt(i, 3).toString().trim() == "") {

                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_duty_rota_shifts (shift_title,_to,_from,status,duty_rota_id) values(?,?,?,?,?)");

                    pstmt.setObject(1, jTable13.getValueAt(i, 0).toString());
                    pstmt.setObject(2, jTable13.getValueAt(i, 1).toString());
                    pstmt.setObject(3, jTable13.getValueAt(i, 2).toString());
                    System.out.println("Inserting " + jTable13.getValueAt(i, 0) + " in " + i);

                    pstmt.setObject(4, "1");
                    pstmt.setObject(5, this.duty_rota_id);
                    //         pstmt.setString(8,jTextArea1.getText());

                    pstmt.executeUpdate();

                    inserts++;
                    connectDB.commit();
                    // javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);




                }

            }

            //connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Data Updated successfully\n" + inserts + " New Shifts recorded\n" + updates + " Existing Shifts Updated.", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            /*for (int k = 0; k < jTable14.getRowCount(); k++){
             if (jTable14.getModel().getValueAt(k,0) != null){

             prstmt.setObject(1,jTable14.getValueAt(k,0).toString());
             prstmt.setObject(2,jTable14.getValueAt(k,1).toString());
             prstmt.setObject(3,jTable14.getValueAt(k,2).toString());
             prstmt.setObject(4,jTable14.getValueAt(k,3).toString());
             prstmt.setObject(5,jTable14.getValueAt(k,4).toString());

             prstmt.executeUpdate();
             connectDB.commit();
             connectDB.setAutoCommit(true);

             javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
             }

             }*/

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

        // TODO add your handling code here:        // Add your handling code here:
    }//GEN-LAST:event_newAction1ActionPerformed

    private void newAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction3ActionPerformed
        try {
            if (dptcbx.getSelectedItem().toString().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Departments MUST be set up!! ", "Operation Failed", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } else {
                connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_duty_rota (duty_rota_title,duty_rota_description,department,section,valid_from,valid_to) values(?,?,?,?,?,?)");

                pstmt.setObject(1, rotatitle.getText());
                pstmt.setObject(2, desccbx.getText());

                pstmt.setObject(3, dptcbx.getSelectedItem().toString());
                pstmt.setObject(4, sectcbx.getSelectedItem().toString());

                pstmt.setObject(5, datePicker2.getDate().toString());
                pstmt.setObject(6, datePicker3.getDate().toString());


                pstmt.executeUpdate();
                connectDB.commit();
                connectDB.setAutoCommit(true);
                javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }


        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }        // Add your handling code here:
    }//GEN-LAST:event_newAction3ActionPerformed

    private void jTable15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable15MouseClicked
        // TODO add your handling code here:
        if (jTable15.getSelectedColumn() > 3) {
            //System.out.println("Dia log value is row:"+jTable15.getSelectedColumn()+" and column: 0===> "+jTable15.getValueAt(jTable15.getSelectedColumn(),0).toString());
            String pivot = "";
            try {

                pivot = jTable15.getValueAt(jTable15.getSelectedRow(), 0).toString();

                System.out.println("The pivot is " + pivot);
                if (pivot.equals("")) {
                } else {


                    jSearchDialog12.dispose();
                    java.awt.Point point = this.jTable15.getLocationOnScreen();

                    jSearchDialog12.setSize(400, 200);

                    jSearchDialog12.setLocation(point);
                    jSearchDialog12.setVisible(true);
                }
            } catch (Exception e) {
            }


        } else {
//       System.out.println("Failed Dia log value is row:"+jTable15.getSelectedColumn()+" and column: 0===> "+jTable15.getValueAt(jTable15.getSelectedColumn(),0).toString());
        }

    }//GEN-LAST:event_jTable15MouseClicked

    private void searchButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton2ActionPerformed
        searchButton11Clicked();        // TODO add your handling code here:
    }//GEN-LAST:event_searchButton2ActionPerformed

    private void jButton412ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton412ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton412ActionPerformed

    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        try {
            System.out.println("select duty_rota_title,duty_rota_id,department from hr.hr_duty_rota where duty_rota_title ||' '||duty_rota_id ILIKE '" + jTextField1111.getText().toString() + "%' ORDER BY duty_rota_title");

            nBCachedRowSet1.execute("select duty_rota_title,duty_rota_id,department from hr.hr_duty_rota where duty_rota_title ||' '||duty_rota_id ILIKE '" + jTextField1111.getText().toString() + "%' ORDER BY duty_rota_title");
            jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(nBCachedRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[]{
                        //  new org.netbeans.lib.sql.models.TableModel.Column("duty_rota_id", "ID", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("duty_rota_title", "Title", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("department", "Department", false)
                    }));
            jSearchTable1.setShowHorizontalLines(true);
            jSearchScrollPane1.setViewportView(jSearchTable1);

            System.out.println("Cannot sort out");
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate

    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        jTextField365.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());
        String rota_name = jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString();
        String dept_name = jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1).toString();
        populateAssignedTimeBands(jTable15, rota_name, dept_name);
        jSearchDialog1.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked

    private void jTextField11113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11113CaretUpdate
        try {
            nBCachedRowSet2.execute("select staff_id,initcap(first_name||' '||second_name||' '||others) AS staff_names from hr.hr_staffresume_fileinfo where first_name||' '||second_name||' '||others ILIKE '" + jTextField11113.getText().toString() + "%' ORDER BY staff_names");
            // System.out.println("select duty_rota_title,duty_rota_id from hr.hr_duty_rota where duty_rota_id ||' '||duty_rota_title ILIKE '"+jTextField11112.getText().toString()+"%' ORDER BY duty_rota_title");
            jSearchTable13.setModel(new org.netbeans.lib.sql.models.TableModel(nBCachedRowSet2, new org.netbeans.lib.sql.models.TableModel.Column[]{
                        new org.netbeans.lib.sql.models.TableModel.Column("staff_names", "Names", false),
                        new org.netbeans.lib.sql.models.TableModel.Column("staff_id", "Staff Number", false), //   new org.netbeans.lib.sql.models.TableModel.Column("department", "Department", false)
                    }));
            jSearchTable13.setShowHorizontalLines(false);
            jSearchScrollPane13.setViewportView(jSearchTable13);
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11113CaretUpdate

    private void jSearchTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable13MouseClicked
        jTable15.setValueAt(jSearchTable13.getValueAt(jSearchTable13.getSelectedRow(), 1), jTable15.getSelectedRow(), jTable15.getSelectedColumn());
        System.out.println("The selected Value is: " + jSearchTable13.getValueAt(jSearchTable13.getSelectedRow(), 0).toString());
        //jTable15.setValueAt(jSearchTable13.getValueAt(jSearchTable13.getSelectedRow(), 1), jTable15.getSelectedRow(), 2);


        jSearchDialog12.dispose();






        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchTable13MouseClicked

    private void jButton413ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton413ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton413ActionPerformed

    private void jButton513ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton513ActionPerformed
        jSearchDialog12.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton513ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed



  int updates = 0;
            int inserts = 0;


int x=4;


while(x<11){
        try {

            connectDB.setAutoCommit(false);
          
            String dept = "";
            String rota = "";
            for (int i = 0; i < jTable15.getRowCount(); i++) {
                
                 
                        java.sql.Statement st2 = connectDB.createStatement();
                        
                        
                        java.sql.ResultSet rset = st2.executeQuery("select * from hr.hr_duty_rota_reports where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jTable15.getModel().getValueAt(i, 0)+"' and day='"+x+"'");                  
                
                
                
 
                        
                        
                        

                if (rset.next()) {
                   // int shift = Integer.parseInt(jTable15.getModel().getValueAt(i, 3).toString());
                    java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_duty_rota_reports set staff_id = '"+jTable15.getModel().getValueAt(i, x)+"' where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jTable15.getModel().getValueAt(i, 0)+"' and day='"+x+"'");
                    updates++;
                    prstmt.executeUpdate();
                    connectDB.commit();
                    System.out.println("update hr.hr_duty_rota_reports set staff_id = '"+jTable15.getModel().getValueAt(i, x)+"' where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jTable15.getModel().getValueAt(i, 0)+"' and day='"+x+"'");

                } else {
if(jTable15.getValueAt(i, 0).toString()!=""){
    
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_duty_rota_reports (duty_rota_id,shift_id,day,staff_id) values(?,?,?,?)");
                   
                    pstmt.setObject(1, this.duty_rota_id);
                    pstmt.setObject(2, jTable15.getValueAt(i, 0).toString());
                    pstmt.setObject(3, x);
                    pstmt.setObject(4, jTable15.getModel().getValueAt(i, x));
                    System.out.println("Inserting Staff" + jTable15.getValueAt(i, x) + " in " + i);

                  

                    pstmt.executeUpdate();

                    inserts++;
                    connectDB.commit();
                 
}


                }

            }

            //connectDB.setAutoCommit(true);
          
            /*for (int k = 0; k < jTable14.getRowCount(); k++){
             if (jTable14.getModel().getValueAt(k,0) != null){

             prstmt.setObject(1,jTable14.getValueAt(k,0).toString());
             prstmt.setObject(2,jTable14.getValueAt(k,1).toString());
             prstmt.setObject(3,jTable14.getValueAt(k,2).toString());
             prstmt.setObject(4,jTable14.getValueAt(k,3).toString());
             prstmt.setObject(5,jTable14.getValueAt(k,4).toString());

             prstmt.executeUpdate();
             connectDB.commit();
             connectDB.setAutoCommit(true);

             javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
             }

             }*/

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

x++;
}
  javax.swing.JOptionPane.showMessageDialog(this, "Data Updated successfully\n" + inserts + " New Shifts recorded\n" + updates + " Existing Shifts Updated.", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

com.afrisoftech.hr.DutyRota app = new com.afrisoftech.hr.DutyRota();
app.duty_rota_id=this.duty_rota_id;
    app.AllApplicantResumePdf(connectDB);












        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
    
    
    
    
    
public void populateWorksheet(javax.swing.JTable jt){
    
    int x=4;


while(x<11){
        try {

            connectDB.setAutoCommit(false);
          
            String dept = "";
            String rota = "";
            for (int i = 0; i < jt.getRowCount(); i++) {
                
                 
                        java.sql.Statement st2 = connectDB.createStatement();
                        
                        
                        java.sql.ResultSet rset = st2.executeQuery("select * from hr.hr_duty_rota_reports where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jt.getModel().getValueAt(i, 0)+"' and day='"+x+"'");                  
                
                
                
 
                        System.out.println("select * from hr.hr_duty_rota_reports where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jt.getModel().getValueAt(i, 0)+"' and day='"+x+"'");
                        
                        

               while(rset.next()) {
                   // int shift = Integer.parseInt(jTable15.getModel().getValueAt(i, 3).toString());
                   // java.sql.PreparedStatement prstmt = connectDB.prepareStatement("update hr.hr_duty_rota_reports set staff_id = '"+jt.getModel().getValueAt(i, x)+"' where duty_rota_id='"+this.duty_rota_id+"' and shift_id='"+jt.getModel().getValueAt(i, 0)+"' and day='"+x+"'");
                  jt.getModel().setValueAt(rset.getObject(4),i,x);
                   // prstmt.executeUpdate();
                 //   connectDB.commit();

                } 
            }

            //connectDB.setAutoCommit(true);
          
            /*for (int k = 0; k < jTable14.getRowCount(); k++){
             if (jTable14.getModel().getValueAt(k,0) != null){

             prstmt.setObject(1,jTable14.getValueAt(k,0).toString());
             prstmt.setObject(2,jTable14.getValueAt(k,1).toString());
             prstmt.setObject(3,jTable14.getValueAt(k,2).toString());
             prstmt.setObject(4,jTable14.getValueAt(k,3).toString());
             prstmt.setObject(5,jTable14.getValueAt(k,4).toString());

             prstmt.executeUpdate();
             connectDB.commit();
             connectDB.setAutoCommit(true);

             javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
             }

             }*/

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

x++;
}
//  javax.swing.JOptionPane.showMessageDialog(this, "Data Updated successfully\n" + inserts + " New Shifts recorded\n" + updates + " Existing Shifts Updated.", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);









    
    
    
}    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed


        for (int i = 0; i < jTable15.getRowCount(); i++) {

            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 5);
            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 6);
            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 7);
            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 8);
            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 9);
            jTable15.setValueAt(jTable15.getValueAt(i, 4), i, 10);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    public String getSqlString(java.lang.Object[] arrayfromJList) {
        java.lang.String strToArray = null;
        if (arrayfromJList.length > 0) {
            strToArray = arrayfromJList[0].toString();



            for (int i = 1; i < arrayfromJList.length; i++) {

                strToArray = strToArray + ", " + arrayfromJList[i].toString();

            }
        } else {

            strToArray = "";

        }
        System.out.println(strToArray);

        return strToArray;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel1;
    private javax.swing.JPanel actionsPanel3;
    private javax.swing.JButton clearAction1;
    private javax.swing.JButton clearAction3;
    private org.netbeans.lib.sql.NBCachedRowSet crset1;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel dataPanel3;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private com.afrisoftech.lib.DatePicker datePicker3;
    private javax.swing.JButton deleteAction1;
    private javax.swing.JButton deleteAction3;
    private javax.swing.JTextArea desccbx;
    private javax.swing.JComboBox dptcbx;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JPanel fieldsPanel2;
    private javax.swing.JButton firstAction;
    private javax.swing.JButton firstAction3;
    private javax.swing.JButton helpAction1;
    private javax.swing.JButton helpAction3;
    private javax.swing.JPanel helpPanel1;
    private javax.swing.JPanel helpPanel3;
    private javax.swing.JPanel imgPanel1;
    private javax.swing.JPanel imgPanel3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton411;
    private javax.swing.JButton jButton412;
    private javax.swing.JButton jButton413;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton511;
    private javax.swing.JButton jButton512;
    private javax.swing.JButton jButton513;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel611;
    private javax.swing.JPanel jPanel612;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JDialog jSearchDialog11;
    private javax.swing.JDialog jSearchDialog12;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JPanel jSearchPanel12;
    private javax.swing.JPanel jSearchPanel13;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JScrollPane jSearchScrollPane12;
    private javax.swing.JScrollPane jSearchScrollPane13;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JTable jSearchTable12;
    private javax.swing.JTable jSearchTable13;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable15;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField11111;
    private javax.swing.JTextField jTextField11112;
    private javax.swing.JTextField jTextField11113;
    private javax.swing.JTextField jTextField361;
    private javax.swing.JTextField jTextField365;
    private javax.swing.JPanel labelPanel1;
    private javax.swing.JPanel labelPanel3;
    private javax.swing.JButton lastAction;
    private javax.swing.JButton lastAction3;
    private javax.swing.JPanel mainPanel1;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel mainPanel5;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet10;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet4;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet5;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet6;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet7;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet8;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet9;
    private javax.swing.JButton newAction1;
    private javax.swing.JButton newAction3;
    private javax.swing.JButton nextAction;
    private javax.swing.JButton nextAction2;
    private javax.swing.JButton previousAction;
    private javax.swing.JButton previousAction3;
    private javax.swing.JTextField rotatitle;
    private javax.swing.JButton searchButton1;
    private javax.swing.JButton searchButton2;
    private org.netbeans.lib.sql.NBCachedRowSet searchRowSet1;
    private javax.swing.JComboBox sectcbx;
    private javax.swing.JPanel spacerPanel1;
    private javax.swing.JPanel spacerPanel3;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JPanel subMainPanel2;
    private javax.swing.JPanel subMainPanel3;
    private javax.swing.JButton updateAction1;
    private javax.swing.JButton updateAction3;
    // End of variables declaration//GEN-END:variables
}
