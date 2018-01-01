/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import com.afrisoftech.dbadmin.TableModel;
import com.afrisoftech.lib.GetItemInfo;
import com.afrisoftech.lib.SQLDateFormat;
import com.afrisoftech.lib.UserName;
import com.lowagie.text.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.JTableHeader;
//import org.openide.util.Exceptions;

/**
 *
 * @author sytem partners
 */
public class UniversalDashboard extends javax.swing.JPanel {

    /**
     * Creates new form UniversalDashboard
     */
    int answer = 0;
    int gauging = 0;
    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    JProgressBar progressObj1 = null;
    public static Hashtable renderers = new Hashtable();
    String aie = null, receiving_grn = null, dispatch_lpo_lso = null, forward_to_cso = null, determine_mode_of_pur = null, assign_buyer, approved_by_tc, evaluation, awarding, contract, raise_lpo_lso;
    String agenda_gen = null, chief_approval = null, sad_approval = null, miu_approval = null, dept_approval = null, committment = null;
    private String pr;
    private String raised;
    private String date_;
    private String aie_;
    private int approved;
    private String po;
    private String quantity;
    private int sel;
    private String item_cod;
    private String store_name;
    private String grndate;

    public Boolean wholeTrail = true;
    private String type;

    public UniversalDashboard(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        connectDB = connDb;

        pConnDB = pconnDB;

        initComponents();

       // jCheckBox3.doClick();
        final WaitingPRsThread wprs = new WaitingPRsThread();
        wprs.start();

        ///changing the header size
        JTableHeader header = dashTbl.getTableHeader();
        header.setFont(header.getFont().deriveFont(10));

        //dashTbl.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 12));
        PRCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(SELECT '-' UNION SELECT   purchase_req    FROM st_pr_progress)   order by  1 "));

        try {
            int rows = 0;
            int num_rows = 0;

            ///checking if their is a row that exists//checking the former comment-->*their lol
            java.sql.Statement ps2 = connectDB.createStatement();

            java.sql.ResultSet rs3 = ps2.executeQuery("SELECT count(*)  FROM st_pr_progress");
            while (rs3.next()) {
                num_rows = rs3.getInt(1);

            }
            for (int k = 0; k < dashTbl.getRowCount(); k++) {
                for (int r = 0; r < dashTbl.getColumnCount(); r++) {
                    dashTbl.getModel().setValueAt(null, k, r);
                }
            }
            if (num_rows > 0) {
                java.sql.Statement ps1 = connectDB.createStatement();
                //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
                java.sql.ResultSet rs2 = ps1.executeQuery("SELECT purchase_req, aie_approval, forward_to_cso, determine_mode_of_pur,assign_buyer, approved_by_tc, evaluation, awarding, raise_lpo_lso,dispatch_lpo_lso, receiving_grn, dept_approval, agenda_gen, chief_approval,sad_approval, miu_approval, committment   FROM st_pr_progress where receiving_grn ='-' ");
                while (rs2.next()) {

                    // dashTbl.getModel().setValueAt(rs2.getString("purchase_req"),rows, 0);
                    aie = rs2.getString("aie_approval");
                    forward_to_cso = rs2.getString("forward_to_cso");
//                System.out.println("hehehe "+forward_to_cso);
                    determine_mode_of_pur = rs2.getString("determine_mode_of_pur");
                    assign_buyer = rs2.getString("assign_buyer");
                    approved_by_tc = rs2.getString("approved_by_tc");
                    evaluation = rs2.getString("evaluation");
                    awarding = rs2.getString("awarding");
                    //contract=rs2.getString("contract");
                    raise_lpo_lso = rs2.getString("raise_lpo_lso");
                    dispatch_lpo_lso = rs2.getString("dispatch_Lpo_Lso");
                    receiving_grn = rs2.getString("receiving_grn");
                    chief_approval = rs2.getString("chief_approval");
                    sad_approval = rs2.getString("sad_approval");
                    miu_approval = rs2.getString("miu_approval");
                    dept_approval = rs2.getString("dept_approval");
                    agenda_gen = rs2.getString("agenda_gen");

                    ////RETRIEVING THE ProgressBar
                    dashTbl.getModel().setValueAt(rs2.getString("purchase_req"), rows, 0);
                    dashTbl.getModel().setValueAt(dept_approval, rows, 1);
                    //dashTbl.getModel().setValueAt(aie,rows,12);

                    dashTbl.getModel().setValueAt(forward_to_cso, rows, 2);

                    dashTbl.getModel().setValueAt(determine_mode_of_pur, rows, 3);
                    dashTbl.getModel().setValueAt(assign_buyer, rows, 4);
                    dashTbl.getModel().setValueAt(approved_by_tc, rows, 5);
                    dashTbl.getModel().setValueAt(evaluation, rows, 6);
                    dashTbl.getModel().setValueAt(awarding, rows, 8);
                    dashTbl.getModel().setValueAt(agenda_gen, rows, 7);
                    dashTbl.getModel().setValueAt(raise_lpo_lso, rows, 9);

                    dashTbl.getModel().setValueAt(chief_approval, rows, 10);
                    dashTbl.getModel().setValueAt(aie, rows, 11);
                    dashTbl.getModel().setValueAt(sad_approval, rows, 12);
                    dashTbl.getModel().setValueAt(miu_approval, rows, 13);
                    dashTbl.getModel().setValueAt(committment, rows, 14);
                    dashTbl.getModel().setValueAt(dispatch_lpo_lso, rows, 15);
                    dashTbl.getModel().setValueAt(receiving_grn, rows, 16);

                    rows++;

                    System.out.println("numnber of rows " + rows);

                }
            } else {
                System.out.println("DO NOTHING");

            }

        } catch (Exception eax) {
            eax.printStackTrace();
            System.out.println(eax.getMessage());

        }

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
        mooreDetDialog = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        aieTxt = new javax.swing.JTextField();
        countApprtxt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new com.afrisoftech.dbadmin.JTable();
        jLabel13 = new javax.swing.JLabel();
        reqdbyTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        reqdateTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        mooreLPODetDialog = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        aieTxt1 = new javax.swing.JTextField();
        countApprtxt1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new com.afrisoftech.dbadmin.JTable();
        jLabel18 = new javax.swing.JLabel();
        reqdbyTxt1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        reqdateTxt1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        userdialog = new javax.swing.JDialog();
        jSearchPanel4 = new javax.swing.JPanel();
        usersearchTxt = new javax.swing.JTextField();
        jSearchScrollPane4 = new javax.swing.JScrollPane();
        jSearchTable4 = new com.afrisoftech.dbadmin.JTable();
        jButton93 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        datePicker3 = new com.afrisoftech.lib.DatePicker();
        jPanel4 = new javax.swing.JPanel();
        filterbydatesChx = new javax.swing.JCheckBox();
        filterbyprocessesChx = new javax.swing.JCheckBox();
        filterbyordersChx = new javax.swing.JCheckBox();
        filterbyprsChx = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        processCbx = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        ordersCbx = new javax.swing.JComboBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        PRCbx = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dashTbl = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        begindate = new com.afrisoftech.lib.DatePicker();
        enddate = new com.afrisoftech.lib.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable()
        ;
        jPanel59 = new javax.swing.JPanel();
        userTxt = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemsTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        begindate1 = new com.afrisoftech.lib.DatePicker();
        enddate1 = new com.afrisoftech.lib.DatePicker();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        itemsTbl1 = new com.afrisoftech.dbadmin.JTable();
        jPanel11 = new javax.swing.JPanel();
        lpoNumbertxt = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        begindate2 = new com.afrisoftech.lib.DatePicker();
        enddate2 = new com.afrisoftech.lib.DatePicker();
        viewlposbtn = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        itemReftxt = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dateItem = new com.afrisoftech.lib.DatePicker();
        enddate3 = new com.afrisoftech.lib.DatePicker();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        itemsStatustbl = new com.afrisoftech.dbadmin.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        reqNotxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        orderNotxt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        currentTender = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        storeTxt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        QtyIntender = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();

        mooreDetDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("AIE HOLDER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreDetDialog.getContentPane().add(jLabel10, gridBagConstraints);

        jLabel12.setText("ITEMS APPROVED FOR ORDER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreDetDialog.getContentPane().add(jLabel12, gridBagConstraints);

        aieTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreDetDialog.getContentPane().add(aieTxt, gridBagConstraints);

        countApprtxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreDetDialog.getContentPane().add(countApprtxt, gridBagConstraints);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Order Details"), "Order Details"));

        jTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item_code,item_description,old_req_no TENDER_OR_QUOTATION,terms ORDER_NO from st_receive_requisation where requisition_no ilike '"+pr+"' and terms != '-' "));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreDetDialog.getContentPane().add(jScrollPane4, gridBagConstraints);

        jLabel13.setText("Requisitioned by");
        mooreDetDialog.getContentPane().add(jLabel13, new java.awt.GridBagConstraints());

        reqdbyTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mooreDetDialog.getContentPane().add(reqdbyTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        mooreDetDialog.getContentPane().add(jLabel14, gridBagConstraints);

        reqdateTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mooreDetDialog.getContentPane().add(reqdateTxt, gridBagConstraints);

        jButton1.setText("Close Dialog");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        mooreDetDialog.getContentPane().add(jButton1, gridBagConstraints);

        mooreLPODetDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel16.setText("Quotation / Tender No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreLPODetDialog.getContentPane().add(jLabel16, gridBagConstraints);

        jLabel17.setText("LPO Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreLPODetDialog.getContentPane().add(jLabel17, gridBagConstraints);

        aieTxt1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreLPODetDialog.getContentPane().add(aieTxt1, gridBagConstraints);

        countApprtxt1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreLPODetDialog.getContentPane().add(countApprtxt1, gridBagConstraints);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Order Details"), "Order Details"));

        jTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item_code,item_description,old_req_no TENDER_OR_QUOTATION,terms ORDER_NO from st_receive_requisation where requisition_no ilike '"+pr+"' and terms != '-' "));
        jScrollPane6.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mooreLPODetDialog.getContentPane().add(jScrollPane6, gridBagConstraints);

        jLabel18.setText("Ordered By");
        mooreLPODetDialog.getContentPane().add(jLabel18, new java.awt.GridBagConstraints());

        reqdbyTxt1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mooreLPODetDialog.getContentPane().add(reqdbyTxt1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        mooreLPODetDialog.getContentPane().add(jLabel19, gridBagConstraints);

        reqdateTxt1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mooreLPODetDialog.getContentPane().add(reqdateTxt1, gridBagConstraints);

        jButton2.setText("Close Dialog");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        mooreLPODetDialog.getContentPane().add(jButton2, gridBagConstraints);

        userdialog.setModal(true);
        userdialog.setUndecorated(true);
        userdialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel4.setLayout(new java.awt.GridBagLayout());

        usersearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                usersearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        jSearchPanel4.add(usersearchTxt, gridBagConstraints);

        jSearchTable4.setShowHorizontalLines(false);
        jSearchTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable4MouseClicked(evt);
            }
        });
        jSearchScrollPane4.setViewportView(jSearchTable4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel4.add(jSearchScrollPane4, gridBagConstraints);

        jButton93.setText("Dispose");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jSearchPanel4.add(jButton93, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        userdialog.getContentPane().add(jSearchPanel4, gridBagConstraints);

        setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(0, 0, 204), null, null));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter  Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 102)));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Begin Date");
        jLabel5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel5, gridBagConstraints);

        datePicker1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(datePicker1, gridBagConstraints);

        jLabel6.setText("End Date");
        jLabel6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel6, gridBagConstraints);

        datePicker3.setEnabled(false);
        datePicker3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datePicker3MouseClicked(evt);
            }
        });
        datePicker3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                datePicker3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(datePicker3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(filterbydatesChx);
        filterbydatesChx.setText("Filter By Dates");
        filterbydatesChx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterbydatesChxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(filterbydatesChx, gridBagConstraints);

        buttonGroup1.add(filterbyprocessesChx);
        filterbyprocessesChx.setText("Filter By Processes");
        filterbyprocessesChx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterbyprocessesChxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(filterbyprocessesChx, gridBagConstraints);

        buttonGroup1.add(filterbyordersChx);
        filterbyordersChx.setText("Filter By Orders");
        filterbyordersChx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterbyordersChxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel4.add(filterbyordersChx, gridBagConstraints);

        buttonGroup1.add(filterbyprsChx);
        filterbyprsChx.setText("Filter By PRs");
        filterbyprsChx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterbyprsChxActionPerformed(evt);
            }
        });
        jPanel4.add(filterbyprsChx, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel4, gridBagConstraints);

        jLabel7.setText("Processes");
        jLabel7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel7, gridBagConstraints);

        processCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "SCM", "Mode of Purchase", "Assigned  buyer", "Contract No", "Evaluation", "Awarding", "Raise LPO/LSO(#)", "Dispatch LPO/LSO", "Receiving /GRN", "Aie Approval", "Chief Approval", "Sad Approval", "Miu Approval", "Agenda Generation", "Departmental Prq Approval         " }));
        processCbx.setEnabled(false);
        processCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(processCbx, gridBagConstraints);

        jLabel8.setText("Order Options");
        jLabel8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel8, gridBagConstraints);

        ordersCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "LPO", "LSO" }));
        ordersCbx.setEnabled(false);
        ordersCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(ordersCbx, gridBagConstraints);

        jCheckBox4.setToolTipText("Filter By Dates");
        jCheckBox4.setEnabled(false);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(jCheckBox4, gridBagConstraints);

        jLabel9.setText("  PRs ");
        jLabel9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel9, gridBagConstraints);

        PRCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        PRCbx.setEnabled(false);
        PRCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(PRCbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 9.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        dashTbl.setFont(new java.awt.Font("Garamond", 0, 12)); // NOI18N
        dashTbl.setForeground(new java.awt.Color(102, 0, 102));
        dashTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PRs", "Dept Approval", "To SCM", "Mode of Purchase", "Assign Buyer", "Contract No", "Evaluation", "Agenda Gen", "Awarding", "Raised LPO/LSO(#)", "Chief Approval", "AIE Approval", "SAD Approval", "MIU Approval", "Commitment", "Dispatch LPO/LSO", "Receiving /GRN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dashTbl.setGridColor(new java.awt.Color(0, 153, 153));
        dashTbl.setSelectionBackground(new java.awt.Color(204, 255, 204));
        dashTbl.setSelectionForeground(new java.awt.Color(255, 0, 0));
        /*for(int m=0;m<dashTbl.getRowCount();m++){
            if(dashTbl.getValueAt(m,0)!=null){
                dashTbl.getColumn("Progress").setCellRenderer(new ProgressCellRenderer());
            }
        }*/
        dashTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashTblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dashTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 9.0;
        jPanel5.add(jPanel2, gridBagConstraints);

        jTabbedPane1.addTab("Work in Progress", jPanel5);

        jPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 80);
        jPanel6.add(begindate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 80);
        jPanel6.add(enddate, gridBagConstraints);

        jLabel1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jLabel1, gridBagConstraints);

        jLabel2.setText("End date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jLabel2, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(20);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane1, gridBagConstraints);

        jPanel59.setLayout(new java.awt.GridBagLayout());

        userTxt.setEditable(false);
        userTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        userTxt.setForeground(new java.awt.Color(102, 102, 102));
        userTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel59.add(userTxt, gridBagConstraints);

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jButton23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel6.add(jPanel59, gridBagConstraints);

        jLabel29.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel6.add(jLabel29, gridBagConstraints);

        buttonGroup2.add(jCheckBox1);
        jCheckBox1.setText("View Trail Per User");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel6.add(jCheckBox1, gridBagConstraints);

        buttonGroup2.add(jCheckBox2);
        jCheckBox2.setText("View All Trail");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel6.add(jCheckBox2, gridBagConstraints);

        jCheckBox3.setForeground(new java.awt.Color(0, 0, 255));
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Disable threaded refresh");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel6.add(jCheckBox3, gridBagConstraints);

        jTabbedPane1.addTab("SCM Audit trails", jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        itemsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        itemsTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsTblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemsTblMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(itemsTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jScrollPane3, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search a particular PRQ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jTextField1.setText("Type PRQ Number and press enter....");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel8, gridBagConstraints);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add a date filter and click \"View\" to view multiple PRQs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jLabel3, gridBagConstraints);

        jLabel4.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel9.add(begindate1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel9.add(enddate1, gridBagConstraints);

        jRadioButton1.setText("View All");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jRadioButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel9, gridBagConstraints);

        jTabbedPane1.addTab("Internal PR Locator", jPanel7);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        itemsTbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        itemsTbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsTbl1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemsTbl1MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(itemsTbl1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jScrollPane5, gridBagConstraints);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search a particular Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        lpoNumbertxt.setText("Type LPO Number and press enter....");
        lpoNumbertxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpoNumbertxtMouseClicked(evt);
            }
        });
        lpoNumbertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpoNumbertxtActionPerformed(evt);
            }
        });
        lpoNumbertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lpoNumbertxtKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(lpoNumbertxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel11, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add a date filter and click \"View\" to view multiple LPOs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jLabel11, gridBagConstraints);

        jLabel15.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel12.add(begindate2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel12.add(enddate2, gridBagConstraints);

        viewlposbtn.setText("View All");
        viewlposbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlposbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(viewlposbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel12, gridBagConstraints);

        jTabbedPane1.addTab("Purchase order tracker", jPanel10);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search a particular item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        itemReftxt.setText("Type item code or description and press enter....");
        itemReftxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemReftxtMouseClicked(evt);
            }
        });
        itemReftxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReftxtActionPerformed(evt);
            }
        });
        itemReftxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemReftxtKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(itemReftxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jPanel15, gridBagConstraints);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add a date filter and click \"View\" to view multiple PRQs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel20.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel16.add(jLabel20, gridBagConstraints);

        jLabel21.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel16.add(jLabel21, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel16.add(dateItem, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel16.add(enddate3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jPanel16, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Results", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 0)));

        itemsStatustbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        itemsStatustbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsStatustblMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(itemsStatustbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jScrollPane8, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel22.setText("Most Recent Requisition No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel22, gridBagConstraints);

        reqNotxt.setEditable(false);
        reqNotxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reqNotxt.setText("-");
        reqNotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reqNotxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(reqNotxt, gridBagConstraints);

        jLabel23.setText("Most recent Order No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel23, gridBagConstraints);

        orderNotxt.setEditable(false);
        orderNotxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        orderNotxt.setText("-");
        orderNotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderNotxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(orderNotxt, gridBagConstraints);

        jLabel24.setText("Current Tender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel24, gridBagConstraints);

        currentTender.setEditable(false);
        currentTender.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        currentTender.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(currentTender, gridBagConstraints);

        jLabel25.setText("Store Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel25, gridBagConstraints);

        storeTxt.setEditable(false);
        storeTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        storeTxt.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(storeTxt, gridBagConstraints);

        jLabel26.setText("Qty Available in tender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel26, gridBagConstraints);

        QtyIntender.setEditable(false);
        QtyIntender.setForeground(new java.awt.Color(255, 0, 51));
        QtyIntender.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(QtyIntender, gridBagConstraints);

        jLabel27.setText("Most recent GRN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel27, gridBagConstraints);

        jTextField8.setEditable(false);
        jTextField8.setForeground(new java.awt.Color(255, 0, 51));
        jTextField8.setText("DD/MM/YYYY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jTextField8, gridBagConstraints);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jComboBox1, gridBagConstraints);

        jLabel28.setText("Supplier(s)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel18.add(jLabel28, gridBagConstraints);

        jButton3.setText("Sync Section ");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton3, new java.awt.GridBagConstraints());

        jButton4.setText("Sync Orders");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton4, new java.awt.GridBagConstraints());

        jCheckBox5.setText("View all RQs");
        jCheckBox5.setEnabled(false);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel18.add(jCheckBox5, gridBagConstraints);

        jCheckBox6.setText("View all LPOs");
        jCheckBox6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel18.add(jCheckBox6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jPanel18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jPanel14, gridBagConstraints);

        jTabbedPane1.addTab("Item Status", jPanel13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshTable2() {

        jTable1.setModel(TableModel.createTableVectors(connectDB, "SELECT * from st_user_activity WHERE activity_timestamp::date BETWEEN '" + SQLDateFormat.getSQLDate(begindate.getDate()) + "' AND '" + SQLDateFormat.getSQLDate(enddate.getDate()) + "' order by 2 desc"));

    }
    private void datePicker3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePicker3MouseClicked

    }//GEN-LAST:event_datePicker3MouseClicked

    private void datePicker3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datePicker3CaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_datePicker3CaretPositionChanged

    private void filterbydatesChxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterbydatesChxActionPerformed
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        datePicker1.setEnabled(true);
        datePicker3.setEnabled(true);
        jLabel7.setEnabled(false);
        jLabel9.setEnabled(false);
        PRCbx.setEnabled(false);
        jLabel7.setEnabled(false);
        processCbx.setEnabled(false);
        jCheckBox4.setEnabled(true);
        ordersCbx.setEnabled(false);
        jLabel8.setEnabled(false);
    }//GEN-LAST:event_filterbydatesChxActionPerformed

    private void filterbyprocessesChxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterbyprocessesChxActionPerformed
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        datePicker1.setEnabled(false);
        datePicker3.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel9.setEnabled(false);
        PRCbx.setEnabled(false);
        jLabel7.setEnabled(false);
        processCbx.setEnabled(true);
        jCheckBox4.setEnabled(true);
        ordersCbx.setEnabled(false);
        jLabel8.setEnabled(false);
    }//GEN-LAST:event_filterbyprocessesChxActionPerformed
    private void populateOtherdetails(String pr) {
        try {
            PreparedStatement prpop = connectDB.prepareStatement("select distinct user_name,date::date, received_requisation , \n"
                    + "(select count(item_code) from st_receive_requisation where requisition_no ilike '" + pr + "' and analysed =true) \n"
                    + "from st_receive_requisation where requisition_no ilike '" + pr + "' GROUP BY 1,2,3 ORDER BY 3 DESC LIMIT 1");
            ResultSet rset = prpop.executeQuery();
            while (rset.next()) {
                raised = rset.getString(1);
                date_ = rset.getString(2);
                aie_ = rset.getString(3);
                approved = rset.getInt(4);

            }

            reqdbyTxt.setText(raised);
            reqdateTxt.setText(date_);
            aieTxt.setText(aie_);
            countApprtxt.setText(String.valueOf(approved));

        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        jTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item_code,item_description,old_req_no TENDER_OR_QUOTATION,terms ORDER_NO from st_receive_requisation where requisition_no ilike '" + pr + "' and terms != '-' "));
    }

    private void populateOtherdetails1(String po) {
        try {
            PreparedStatement prpop = connectDB.prepareStatement("select distinct ordered_by,date::date, quotation_no , \n"
                    + "(select sum(quantity*unit_price) from st_orders where order_no ilike '%" + po + "%' )\n"
                    + "from st_orders where order_no ilike '%" + po + "%' GROUP BY 1,2,3 ORDER BY 3 DESC LIMIT 1");
            ResultSet rset = prpop.executeQuery();
            while (rset.next()) {
                raised = rset.getString(1);
                date_ = rset.getString(2);
                aie_ = rset.getString(3);
                quantity = com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(rset.getDouble(4));

            }

            reqdbyTxt1.setText(raised);
            reqdateTxt1.setText(date_);
            aieTxt1.setText(aie_);
            countApprtxt1.setText(String.valueOf(quantity));

        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        jTable3.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct code,item,units,quantity,unit_price from st_orders where order_no ilike '" + po + "'  "));
    }
    private void filterbyordersChxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterbyordersChxActionPerformed
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        datePicker1.setEnabled(false);
        datePicker3.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel9.setEnabled(false);
        PRCbx.setEnabled(false);
        jLabel7.setEnabled(false);
        processCbx.setEnabled(false);
        jCheckBox4.setEnabled(false);
        ordersCbx.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel7.setEnabled(false);
    }//GEN-LAST:event_filterbyordersChxActionPerformed

    private void processCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processCbxActionPerformed
        // TODO add your handling code here:
        try {
            int rows = 0;
            int num_rows = 0;
            String captured_col = null;

            if (processCbx.getSelectedItem().equals("SCM")) {

                captured_col = "forward_to_cso";

            } //            else if(processCbx.getSelectedItem().equals("To Proc. Officers")){
            //                captured_col="forward_to_cso";
            //            }
            else if (processCbx.getSelectedItem().equals("Mode of Purchase")) {
                captured_col = "determine_mode_of_pur";
            } else if (processCbx.getSelectedItem().equals("Assigned  buyer")) {
                captured_col = "assign_buyer";
            } else if (processCbx.getSelectedItem().equals("Contract No")) {
                captured_col = "approved_by_tc";
            } else if (processCbx.getSelectedItem().equals("Evaluation")) {
                captured_col = "evaluation";
            } else if (processCbx.getSelectedItem().equals("Awarding")) {
                captured_col = "awarding";
            } else if (processCbx.getSelectedItem().equals("Raise LPO/LSO(#)")) {
                captured_col = "raise_lpo_lso";
            } else if (processCbx.getSelectedItem().equals("Dispatch LPO/LSO")) {
                captured_col = "dispatch_lpo_lso";
            } else if (processCbx.getSelectedItem().equals("Receiving /GRN")) {
                captured_col = "receiving_grn";
            } else if (processCbx.getSelectedItem().equals("Aie Approval")) {
                captured_col = "aie_approval";
            } else if (processCbx.getSelectedItem().equals("Chief Approval")) {
                captured_col = "chief_approval";
            } else if (processCbx.getSelectedItem().equals("Sad Approval")) {
                captured_col = "sad_approval";
            } else if (processCbx.getSelectedItem().equals("Miu Approval")) {
                captured_col = "miu_approval";
            } else if (processCbx.getSelectedItem().equals("Agenda Generation")) {
                captured_col = "agenda_gen";
            } else if (processCbx.getSelectedItem().equals("Departmental Prq Approval")) {
                captured_col = "dept_approval";
            }

            ///checking if their is a row that exists
            java.sql.Statement ps2 = connectDB.createStatement();
            String query_count = "SELECT count(*)  FROM st_pr_progress  where " + captured_col + " !='-'";
            String query_dash = "SELECT purchase_req, aie_approval, forward_to_cso, determine_mode_of_pur,assign_buyer, approved_by_tc, evaluation, awarding, raise_lpo_lso,dispatch_lpo_lso, receiving_grn, dept_approval, agenda_gen, chief_approval,sad_approval, miu_approval, committment   FROM st_pr_progress where  " + captured_col + " !='-'";
            //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
            java.sql.ResultSet rs3 = ps2.executeQuery(query_count);
            while (rs3.next()) {
                num_rows = rs3.getInt(1);
                System.out.println("NUMBERSSS " + num_rows);

            }
            for (int k = 0; k < dashTbl.getRowCount(); k++) {
                for (int r = 0; r < dashTbl.getColumnCount(); r++) {
                    dashTbl.getModel().setValueAt(null, k, r);
                }
            }

            if (num_rows > 0) {
                java.sql.Statement ps1 = connectDB.createStatement();
                //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
                java.sql.ResultSet rs2 = ps1.executeQuery(query_dash);
                while (rs2.next()) {

                    // dashTbl.getModel().setValueAt(rs2.getString("purchase_req"),rows, 0);
                    aie = rs2.getString("aie_approval");
                    forward_to_cso = rs2.getString("forward_to_cso");
//                System.out.println("hehehe "+forward_to_cso);
                    determine_mode_of_pur = rs2.getString("determine_mode_of_pur");
                    assign_buyer = rs2.getString("assign_buyer");
                    approved_by_tc = rs2.getString("approved_by_tc");
                    evaluation = rs2.getString("evaluation");
                    awarding = rs2.getString("awarding");
                    //contract=rs2.getString("contract");
                    raise_lpo_lso = rs2.getString("raise_lpo_lso");
                    dispatch_lpo_lso = rs2.getString("dispatch_Lpo_Lso");
                    receiving_grn = rs2.getString("receiving_grn");
                    chief_approval = rs2.getString("chief_approval");
                    sad_approval = rs2.getString("sad_approval");
                    miu_approval = rs2.getString("miu_approval");
                    dept_approval = rs2.getString("dept_approval");
                    agenda_gen = rs2.getString("agenda_gen");

                    ////RETRIEVING THE ProgressBar
                    dashTbl.getModel().setValueAt(rs2.getString("purchase_req"), rows, 0);
                    dashTbl.getModel().setValueAt(dept_approval, rows, 1);
                    //dashTbl.getModel().setValueAt(aie,rows,12);

                    dashTbl.getModel().setValueAt(forward_to_cso, rows, 2);

                    dashTbl.getModel().setValueAt(determine_mode_of_pur, rows, 3);
                    dashTbl.getModel().setValueAt(assign_buyer, rows, 4);
                    dashTbl.getModel().setValueAt(approved_by_tc, rows, 5);
                    dashTbl.getModel().setValueAt(evaluation, rows, 6);
                    dashTbl.getModel().setValueAt(awarding, rows, 8);
                    dashTbl.getModel().setValueAt(agenda_gen, rows, 7);
                    dashTbl.getModel().setValueAt(raise_lpo_lso, rows, 9);

                    dashTbl.getModel().setValueAt(chief_approval, rows, 10);
                    dashTbl.getModel().setValueAt(aie, rows, 11);
                    dashTbl.getModel().setValueAt(sad_approval, rows, 12);
                    dashTbl.getModel().setValueAt(miu_approval, rows, 13);
                    dashTbl.getModel().setValueAt(committment, rows, 14);
                    dashTbl.getModel().setValueAt(dispatch_lpo_lso, rows, 15);
                    dashTbl.getModel().setValueAt(receiving_grn, rows, 16);

                    rows++;

                }

            } else {
                System.out.println("DO NOTHING");

            }

        } catch (Exception eax) {
            eax.printStackTrace();
            System.out.println(eax.getMessage());

        }
    }//GEN-LAST:event_processCbxActionPerformed

    private void ordersCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersCbxActionPerformed
        // TODO add your handling code here:
        try {
            int rows = 0;
            int num_rows = 0;
            String captured_col = null;

            if (ordersCbx.getSelectedItem().equals("LPO")) {

                captured_col = "raise_lpo_lso ilike 'LPO%'";

            } else if (ordersCbx.getSelectedItem().equals("LSO")) {
                captured_col = "raise_lpo_lso ilike 'LSO%'";
            }

            ///checking if their is a row that exists
            java.sql.Statement ps2 = connectDB.createStatement();
            String query_count = "SELECT count(*)  FROM st_pr_progress  where " + captured_col;
            String query_dash = "SELECT purchase_req, aie_approval, forward_to_cso, determine_mode_of_pur,assign_buyer, approved_by_tc, evaluation, awarding, raise_lpo_lso,dispatch_lpo_lso, receiving_grn, dept_approval, agenda_gen, chief_approval,sad_approval, miu_approval, committment   FROM st_pr_progress where " + captured_col;
            //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
            java.sql.ResultSet rs3 = ps2.executeQuery(query_count);
            while (rs3.next()) {
                num_rows = rs3.getInt(1);
                System.out.println("NUMBERSSS " + num_rows);

            }
            for (int k = 0; k < dashTbl.getRowCount(); k++) {
                for (int r = 0; r < dashTbl.getColumnCount(); r++) {
                    dashTbl.getModel().setValueAt(null, k, r);
                }
            }

            if (num_rows > 0) {
                java.sql.Statement ps1 = connectDB.createStatement();
                //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
                java.sql.ResultSet rs2 = ps1.executeQuery(query_dash);
                while (rs2.next()) {

                    // dashTbl.getModel().setValueAt(rs2.getString("purchase_req"),rows, 0);
                    aie = rs2.getString("aie_approval");
                    forward_to_cso = rs2.getString("forward_to_cso");
//                System.out.println("hehehe "+forward_to_cso);
                    determine_mode_of_pur = rs2.getString("determine_mode_of_pur");
                    assign_buyer = rs2.getString("assign_buyer");
                    approved_by_tc = rs2.getString("approved_by_tc");
                    evaluation = rs2.getString("evaluation");
                    awarding = rs2.getString("awarding");
                    //contract=rs2.getString("contract");
                    raise_lpo_lso = rs2.getString("raise_lpo_lso");
                    dispatch_lpo_lso = rs2.getString("dispatch_Lpo_Lso");
                    receiving_grn = rs2.getString("receiving_grn");
                    chief_approval = rs2.getString("chief_approval");
                    sad_approval = rs2.getString("sad_approval");
                    miu_approval = rs2.getString("miu_approval");
                    dept_approval = rs2.getString("dept_approval");
                    agenda_gen = rs2.getString("agenda_gen");

                    ////RETRIEVING THE ProgressBar
                    dashTbl.getModel().setValueAt(rs2.getString("purchase_req"), rows, 0);
                    dashTbl.getModel().setValueAt(dept_approval, rows, 1);
                    //dashTbl.getModel().setValueAt(aie,rows,12);

                    dashTbl.getModel().setValueAt(forward_to_cso, rows, 2);

                    dashTbl.getModel().setValueAt(determine_mode_of_pur, rows, 3);
                    dashTbl.getModel().setValueAt(assign_buyer, rows, 4);
                    dashTbl.getModel().setValueAt(approved_by_tc, rows, 5);
                    dashTbl.getModel().setValueAt(evaluation, rows, 6);
                    dashTbl.getModel().setValueAt(awarding, rows, 8);
                    dashTbl.getModel().setValueAt(agenda_gen, rows, 7);
                    dashTbl.getModel().setValueAt(raise_lpo_lso, rows, 9);

                    dashTbl.getModel().setValueAt(chief_approval, rows, 10);
                    dashTbl.getModel().setValueAt(aie, rows, 11);
                    dashTbl.getModel().setValueAt(sad_approval, rows, 12);
                    dashTbl.getModel().setValueAt(miu_approval, rows, 13);
                    dashTbl.getModel().setValueAt(committment, rows, 14);
                    dashTbl.getModel().setValueAt(dispatch_lpo_lso, rows, 15);
                    dashTbl.getModel().setValueAt(receiving_grn, rows, 16);

                    rows++;

                }

            } else {
                System.out.println("DO NOTHING");

            }

        } catch (Exception eax) {
            eax.printStackTrace();
            System.out.println(eax.getMessage());

        }
    }//GEN-LAST:event_ordersCbxActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        try {
            System.out.println("DGFDGDG DFG WEEEE ");
            int rows = 0;
            int num_rows = 0;

            ///checking if their is a row that exists
            java.sql.Statement ps2 = connectDB.createStatement();

            // String query_dash="SELECT supplier, invoice_no, creditor_created, creditor_created_by,commited, commited_by, voucher_no, examination, examination_by, approval, approval_by, cash, cash_by  FROM ac_dashboard where creditor_created::date between '"+this.datePicker1.getDate().toString()+"' and '"+this.datePicker2.getDate().toString()+"'";
            //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
            java.sql.ResultSet rs3 = ps2.executeQuery("SELECT count(*)  FROM st_pr_progress  where prq_date::date between '" + this.datePicker1.getDate().toString() + "' and '" + this.datePicker3.getDate().toString() + "'");
            while (rs3.next()) {
                num_rows = rs3.getInt(1);
                System.out.println("NUMBERSSS " + num_rows);

            }
            for (int k = 0; k < dashTbl.getRowCount(); k++) {
                for (int r = 0; r < dashTbl.getColumnCount(); r++) {
                    dashTbl.getModel().setValueAt(null, k, r);
                }
            }

            if (num_rows > 0) {
                java.sql.Statement ps1 = connectDB.createStatement();
                //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
                java.sql.ResultSet rs2 = ps1.executeQuery("SELECT purchase_req, aie_approval, forward_to_cso, determine_mode_of_pur,assign_buyer, approved_by_tc, evaluation, awarding, raise_lpo_lso,dispatch_lpo_lso, receiving_grn, dept_approval, agenda_gen, chief_approval,sad_approval, miu_approval, committment   FROM st_pr_progress where prq_date::date between '" + this.datePicker1.getDate().toString() + "' and '" + this.datePicker3.getDate().toString() + "'");
                while (rs2.next()) {

                    // dashTbl.getModel().setValueAt(rs2.getString("purchase_req"),rows, 0);
                    aie = rs2.getString("aie_approval");
                    forward_to_cso = rs2.getString("forward_to_cso");
//                System.out.println("hehehe "+forward_to_cso);
                    determine_mode_of_pur = rs2.getString("determine_mode_of_pur");
                    assign_buyer = rs2.getString("assign_buyer");
                    approved_by_tc = rs2.getString("approved_by_tc");
                    evaluation = rs2.getString("evaluation");
                    awarding = rs2.getString("awarding");
                    //contract=rs2.getString("contract");
                    raise_lpo_lso = rs2.getString("raise_lpo_lso");
                    dispatch_lpo_lso = rs2.getString("dispatch_Lpo_Lso");
                    receiving_grn = rs2.getString("receiving_grn");
                    chief_approval = rs2.getString("chief_approval");
                    sad_approval = rs2.getString("sad_approval");
                    miu_approval = rs2.getString("miu_approval");
                    dept_approval = rs2.getString("dept_approval");
                    agenda_gen = rs2.getString("agenda_gen");

                    ////RETRIEVING THE ProgressBar
                    dashTbl.getModel().setValueAt(rs2.getString("purchase_req"), rows, 0);
                    dashTbl.getModel().setValueAt(dept_approval, rows, 1);
                    //dashTbl.getModel().setValueAt(aie,rows,12);

                    dashTbl.getModel().setValueAt(forward_to_cso, rows, 2);

                    dashTbl.getModel().setValueAt(determine_mode_of_pur, rows, 3);
                    dashTbl.getModel().setValueAt(assign_buyer, rows, 4);
                    dashTbl.getModel().setValueAt(approved_by_tc, rows, 5);
                    dashTbl.getModel().setValueAt(evaluation, rows, 6);
                    dashTbl.getModel().setValueAt(awarding, rows, 8);
                    dashTbl.getModel().setValueAt(agenda_gen, rows, 7);
                    dashTbl.getModel().setValueAt(raise_lpo_lso, rows, 9);

                    dashTbl.getModel().setValueAt(chief_approval, rows, 10);
                    dashTbl.getModel().setValueAt(aie, rows, 11);
                    dashTbl.getModel().setValueAt(sad_approval, rows, 12);
                    dashTbl.getModel().setValueAt(miu_approval, rows, 13);
                    dashTbl.getModel().setValueAt(committment, rows, 14);
                    dashTbl.getModel().setValueAt(dispatch_lpo_lso, rows, 15);
                    dashTbl.getModel().setValueAt(receiving_grn, rows, 16);

                    rows++;

                }

            } else {
                System.out.println("DO NOTHING");

            }

        } catch (Exception eax) {
            eax.printStackTrace();
            System.out.println(eax.getMessage());

        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void filterbyprsChxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterbyprsChxActionPerformed
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        datePicker1.setEnabled(false);
        datePicker3.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel9.setEnabled(true);
        PRCbx.setEnabled(true);
        jLabel7.setEnabled(false);
        processCbx.setEnabled(false);
        jCheckBox4.setEnabled(false);
        ordersCbx.setEnabled(false);
        jLabel8.setEnabled(false);

    }//GEN-LAST:event_filterbyprsChxActionPerformed

    private void PRCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRCbxActionPerformed
        try {
            int rows = 0;
            int num_rows = 0;
            String captured_col = null;

            captured_col = "'%" + PRCbx.getSelectedItem().toString() + "%'";

            ///checking if their is a row that exists
            java.sql.Statement ps2 = connectDB.createStatement();
            String query_count = "SELECT count(*)  FROM st_pr_progress  where purchase_req ilike " + captured_col;
            String query_dash = "SELECT purchase_req, aie_approval, forward_to_cso, determine_mode_of_pur,assign_buyer, approved_by_tc, evaluation, awarding, raise_lpo_lso,dispatch_lpo_lso, receiving_grn, dept_approval, agenda_gen, chief_approval,sad_approval, miu_approval, committment   FROM st_pr_progress where purchase_req ilike " + captured_col;
            //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
            java.sql.ResultSet rs3 = ps2.executeQuery(query_count);
            while (rs3.next()) {
                num_rows = rs3.getInt(1);
                System.out.println("NUMBERSSS " + num_rows);

            }
            for (int k = 0; k < dashTbl.getRowCount(); k++) {
                for (int r = 0; r < dashTbl.getColumnCount(); r++) {
                    dashTbl.getModel().setValueAt(null, k, r);
                }
            }

            if (num_rows > 0) {
                java.sql.Statement ps1 = connectDB.createStatement();
                //java.sql.ResultSet rs2 = ps1.executeQuery("SELECT distinct purchase_req,aie_approval::date,forward_to_cso::date,determine_mode_of_pur::date,assign_buyer::date,approved_by_tc::date,evaluation::date,awarding::date,contract::date,raise_lpo_lso::date FROM st_pr_progress");
                java.sql.ResultSet rs2 = ps1.executeQuery(query_dash);
                while (rs2.next()) {

                    // dashTbl.getModel().setValueAt(rs2.getString("purchase_req"),rows, 0);
                    aie = rs2.getString("aie_approval");
                    forward_to_cso = rs2.getString("forward_to_cso");
//                System.out.println("hehehe "+forward_to_cso);
                    determine_mode_of_pur = rs2.getString("determine_mode_of_pur");
                    assign_buyer = rs2.getString("assign_buyer");
                    approved_by_tc = rs2.getString("approved_by_tc");
                    evaluation = rs2.getString("evaluation");
                    awarding = rs2.getString("awarding");
                    //contract=rs2.getString("contract");
                    raise_lpo_lso = rs2.getString("raise_lpo_lso");
                    dispatch_lpo_lso = rs2.getString("dispatch_Lpo_Lso");
                    receiving_grn = rs2.getString("receiving_grn");
                    chief_approval = rs2.getString("chief_approval");
                    sad_approval = rs2.getString("sad_approval");
                    miu_approval = rs2.getString("miu_approval");
                    dept_approval = rs2.getString("dept_approval");
                    agenda_gen = rs2.getString("agenda_gen");

                    ////RETRIEVING THE ProgressBar
                    dashTbl.getModel().setValueAt(rs2.getString("purchase_req"), rows, 0);
                    dashTbl.getModel().setValueAt(dept_approval, rows, 1);
                    //dashTbl.getModel().setValueAt(aie,rows,12);

                    dashTbl.getModel().setValueAt(forward_to_cso, rows, 2);

                    dashTbl.getModel().setValueAt(determine_mode_of_pur, rows, 3);
                    dashTbl.getModel().setValueAt(assign_buyer, rows, 4);
                    dashTbl.getModel().setValueAt(approved_by_tc, rows, 5);
                    dashTbl.getModel().setValueAt(evaluation, rows, 6);
                    dashTbl.getModel().setValueAt(awarding, rows, 8);
                    dashTbl.getModel().setValueAt(agenda_gen, rows, 7);
                    dashTbl.getModel().setValueAt(raise_lpo_lso, rows, 9);

                    dashTbl.getModel().setValueAt(chief_approval, rows, 10);
                    dashTbl.getModel().setValueAt(aie, rows, 11);
                    dashTbl.getModel().setValueAt(sad_approval, rows, 12);
                    dashTbl.getModel().setValueAt(miu_approval, rows, 13);
                    dashTbl.getModel().setValueAt(committment, rows, 14);
                    dashTbl.getModel().setValueAt(dispatch_lpo_lso, rows, 15);
                    dashTbl.getModel().setValueAt(receiving_grn, rows, 16);

                    rows++;

                }

            } else {
                System.out.println("DO NOTHING");

            }

        } catch (Exception eax) {
            eax.printStackTrace();
            System.out.println(eax.getMessage());

        }
    }//GEN-LAST:event_PRCbxActionPerformed

    private void dashTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashTblMouseClicked
        if (dashTbl.getSelectedColumn() == 9) {

            if (!dashTbl.getValueAt(dashTbl.getSelectedRow(), 9).toString().equals("-")) {
                String getlpo = dashTbl.getValueAt(dashTbl.getSelectedRow(), 9).toString();
                int lpo_index = getlpo.indexOf("-");
                System.out.println("THE INDEX IS " + lpo_index);
                String lpo_lso = getlpo.substring(0, lpo_index);
                System.out.println(lpo_lso);
                ShowLpoItemsDialog mn = new ShowLpoItemsDialog(new javax.swing.JFrame(), true, connectDB, lpo_lso);
                mn.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(null, "The PRQ has not yet developed to an LPO/LSO", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (dashTbl.getSelectedColumn() == 5) {
            if (dashTbl.getValueAt(dashTbl.getSelectedRow(), 5) != null) {
                if (!dashTbl.getValueAt(dashTbl.getSelectedRow(), 5).toString().equals("-")) {
                    String gettender = dashTbl.getValueAt(dashTbl.getSelectedRow(), 5).toString();
//        int lpo_index=getlpo.indexOf("-");
//        String lpo_lso=getlpo.substring(lpo_index+1, getlpo.length());
                    //System.out.println("lpolso "+lpo_lso);
                    ShowTenderedItemsDialog mn = new ShowTenderedItemsDialog(new javax.swing.JFrame(), true, connectDB, gettender);
                    mn.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(null, "The PRQ has not yet developed to a Tender/Quation/Contract", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "You cannot view a BLANK Contract No.", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);

            }
        }

    }//GEN-LAST:event_dashTblMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {

//            itemsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT\n"
//                    + "CASE WHEN analysed=true THEN true ELSE false END as AIE_APPROVAL,\n"
//                    + "CASE WHEN approval_status=true THEN true ELSE false END as SAD_APPROVAL,\n"
//                    + "CASE WHEN processed=true THEN true ELSE false END as CSO_ASSIGN_BUYERS,\n"
//                    + "CASE WHEN terms != '-' THEN true ELSE false END as ITEMS_ORDERDED\n"
//                    + "\n"
//                    + "FROM st_receive_requisation WHERE requisition_no ilike  '%" + jTextField1.getText() + "%' order by analysed  desc,terms desc limit 1"));
            itemsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " SELECT\n"
                    + "DISTINCT requisition_no PRQ,\n"
                    + "CASE WHEN (analysed=true OR approval_status=true) THEN true ELSE false END as AIE_APPROVAL,\n"
                    + "CASE WHEN approval_status=true THEN true ELSE false END as SAD_APPROVAL,\n"
                    + "CASE WHEN processed=true THEN true ELSE false END as CSO_ASSIGN_BUYERS,\n"
                    + "CASE WHEN terms != '-' THEN true ELSE false END as ITEMS_ORDERDED\n"
                    + "FROM st_receive_requisation \n"
                    + "WHERE requisition_no NOT like 'IRQ%' and type_of_pr ilike 'Internal Requisition'\n"
                    + "AND requisition_no ilike  '%" + jTextField1.getText() + "%'"
                    + " order by 2  desc,5 desc,1,3,4 "));
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        if (jTextField1.getText().contains("press enter")) {
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        itemsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " SELECT\n"
                + "DISTINCT requisition_no PRQ,\n"
                + "CASE WHEN (analysed=true OR approval_status=true) THEN true ELSE false END as AIE_APPROVAL,\n"
                + "CASE WHEN approval_status=true THEN true ELSE false END as SAD_APPROVAL,\n"
                + "CASE WHEN processed=true THEN true ELSE false END as CSO_ASSIGN_BUYERS,\n"
                + "CASE WHEN terms != '-' THEN true ELSE false END as ITEMS_ORDERDED\n"
                + "FROM st_receive_requisation \n"
                + "WHERE requisition_no NOT like 'IRQ%' and type_of_pr ilike 'Internal Requisition'\n"
                + "AND DATE::date BETWEEN   '" + SQLDateFormat.getSQLDate(begindate1.getDate()) + "' and '" + SQLDateFormat.getSQLDate(enddate1.getDate()) + "'"
                + " order by 2  desc,5 desc,1,3,4 "));

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void itemsTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTblMouseClicked
        // TODO add your handling code here:

        if (itemsTbl.getSelectedColumn() == 0 && itemsTbl.getValueAt(itemsTbl.getSelectedRow(), 0).toString().contains("RQ")) {
            pr = itemsTbl.getValueAt(itemsTbl.getSelectedRow(), 0).toString();
            java.awt.Point p = jScrollPane3.getLocationOnScreen();
            mooreDetDialog.setLocation(p);
            mooreDetDialog.setSize(jScrollPane3.getWidth() * 3 / 4, 500);
            mooreDetDialog.setVisible(true);
            //itemsuploadtxt.setText(null);

            populateOtherdetails(pr);

        }
    }//GEN-LAST:event_itemsTblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mooreDetDialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void itemsTbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTbl1MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:

        if (itemsTbl1.getSelectedColumn() == 0 && itemsTbl1.getValueAt(itemsTbl1.getSelectedRow(), 0).toString().length() > 5) {
            po = itemsTbl1.getValueAt(itemsTbl1.getSelectedRow(), 0).toString();
            java.awt.Point p = jScrollPane5.getLocationOnScreen();
            mooreLPODetDialog.setLocation(p);
            mooreLPODetDialog.setSize(jScrollPane3.getWidth() * 3 / 4, 500);
            mooreLPODetDialog.setVisible(true);
            //itemsuploadtxt.setText(null);

            populateOtherdetails1(po);

        }
    }//GEN-LAST:event_itemsTbl1MouseClicked

    private void lpoNumbertxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpoNumbertxtMouseClicked
        // TODO add your handling code here:
        if (lpoNumbertxt.getText().contains("press enter")) {
            lpoNumbertxt.setText("");
        }
    }//GEN-LAST:event_lpoNumbertxtMouseClicked

    private void lpoNumbertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpoNumbertxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lpoNumbertxtActionPerformed

    private void lpoNumbertxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lpoNumbertxtKeyTyped
        // TODO add your handling code here:
        sel = 0;
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {

//            itemsTbl1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT\n"
//                    + "CASE WHEN chief_approval=true THEN true ELSE false END as AIE_APPROVAL,\n"
//                    + "CASE WHEN aie_holder_approval=true THEN true ELSE false END as CHIEF_APPROVAL,\n"
//                    + "CASE WHEN sad_approval=true THEN true ELSE false END as SUPPLIES_MANAGER_APPROVAL,\n"
//                    + "CASE WHEN miu_approval =true THEN true ELSE false END as MIU_APPROVAL \n"
//                    + "\n"
//                    + "FROM st_orders WHERE (order_no ilike  '%" + lpoNumbertxt.getText() + "%' OR supplier ilike '%" + lpoNumbertxt.getText() + "%' ) limit 1"));
            itemsTbl1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " SELECT\n"
                    + "DISTINCT order_no,supplier,\n"
                    + "CASE WHEN chief_approval=true THEN true ELSE false END as AIE_APPROVAL,\n"
                    + "CASE WHEN aie_holder_approval=true THEN true ELSE false END as CHIEF_APPROVAL,\n"
                    + "CASE WHEN sad_approval=true THEN true ELSE false END as SUPPLIES_MANAGER_APPROVAL,\n"
                    + "CASE WHEN miu_approval =true THEN true ELSE false END as MIU_APPROVAL ,\n"
                    + "CASE WHEN remarks IS NULL OR remarks ='' THEN order_no ELSE remarks END as temp_no\n"
                    + "\n"
                    + "FROM st_orders \n"
                    + "WHERE (order_no IS NOT NULL OR order_no != '') \n"
                    + "AND  order_no ilike  '%" + lpoNumbertxt.getText() + "%' "));

        }
    }//GEN-LAST:event_lpoNumbertxtKeyTyped

    private void viewlposbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlposbtnActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        sel = 1;
        itemsTbl1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " SELECT\n"
                + "DISTINCT order_no,supplier,\n"
                + "CASE WHEN chief_approval=true THEN true ELSE false END as AIE_APPROVAL,\n"
                + "CASE WHEN aie_holder_approval=true THEN true ELSE false END as CHIEF_APPROVAL,\n"
                + "CASE WHEN sad_approval=true THEN true ELSE false END as SUPPLIES_MANAGER_APPROVAL,\n"
                + "CASE WHEN miu_approval =true THEN true ELSE false END as MIU_APPROVAL ,\n"
                + "CASE WHEN remarks IS NULL OR remarks ='' THEN order_no ELSE remarks END as temp_no\n"
                + "\n"
                + "FROM st_orders \n"
                + "WHERE (order_no IS NOT NULL OR order_no != '') \n"
                + "AND DATE::date BETWEEN   '" + SQLDateFormat.getSQLDate(begindate2.getDate()) + "' and '" + SQLDateFormat.getSQLDate(enddate2.getDate()) + "'"));
    }//GEN-LAST:event_viewlposbtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mooreLPODetDialog.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void itemsTbl1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTbl1MousePressed
        // TODO add your handling code here:
        if (sel == 1 || itemsTbl1.getColumnCount() == 6) {
            if (itemsTbl1.getSelectedColumn() == 6) {
                int row = itemsTbl1.getSelectedRow();
                System.err.println(itemsTbl1.getValueAt(row, 0));
                System.err.println(itemsTbl1.getValueAt(row, 1));
                System.err.println(itemsTbl1.getValueAt(row, 2));
                System.err.println(itemsTbl1.getValueAt(row, 3));
                System.err.println(itemsTbl1.getValueAt(row, 4));
                System.err.println(itemsTbl1.getValueAt(row, 5));

                try {
                    PreparedStatement pst = connectDB.prepareStatement("UPDATE st_orders SET "
                            + "order_no='" + itemsTbl1.getValueAt(row, 0) + "' ,"
                            + "chief_approval='" + itemsTbl1.getValueAt(row, 2) + "',"
                            + "aie_holder_approval='" + itemsTbl1.getValueAt(row, 3) + "',"
                            + "sad_approval='" + itemsTbl1.getValueAt(row, 4) + "',"
                            + "miu_approval='" + itemsTbl1.getValueAt(row, 5) + "'"
                            + "WHERE (remarks='" + itemsTbl1.getValueAt(row, 6) + "' "
                            + "OR order_no='" + itemsTbl1.getValueAt(row, 6) + "') ");
                    pst.executeUpdate();

                    pst = connectDB.prepareStatement("UPDATE st_receive_requisation SET terms='" + itemsTbl1.getValueAt(row, 0) + "' "
                            + "WHERE terms ilike '" + itemsTbl1.getValueAt(row, 6) + "' ");

                    pst.executeUpdate();
                    GetItemInfo.updateTrail("Auto-Updated approvals for order: " + itemsTbl1.getValueAt(row, 0) + " ,aie " + itemsTbl1.getValueAt(row, 2) + ",chief " + itemsTbl1.getValueAt(row, 3) + ",sad " + itemsTbl1.getValueAt(row, 4) + ",miu " + itemsTbl1.getValueAt(row, 5), connectDB);
                    connectDB.commit();
                    System.err.println("Updated");
                    viewlposbtn.doClick();

                } catch (SQLException ex) {
                                ex.printStackTrace();             //Exceptions.printStackTrace(ex);
                    try {
                        connectDB.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_itemsTbl1MousePressed

    private void itemsTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTblMousePressed
        // TODO add your handling code here:
        int row = itemsTbl.getSelectedRow();
        if (itemsTbl.getColumnCount() == 5 || itemsTbl.getValueAt(row, 0).toString().startsWith("PR")) {
            PreparedStatement pst;
            try {
                pst = connectDB.prepareStatement("UPDATE st_receive_requisation SET analysed= '" + itemsTbl.getValueAt(row, 1) + "' "
                        + "WHERE requisition_no ilike '" + itemsTbl.getValueAt(row, 0) + "' and approval_status =false ");

                pst.executeUpdate();
                System.err.println("updated");
            } catch (SQLException ex) {
                            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
                try {
                    connectDB.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }

        }
    }//GEN-LAST:event_itemsTblMousePressed

    private void itemReftxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemReftxtMouseClicked
        // TODO add your handling code here:
        if (itemReftxt.getText().contains("press enter")) {
            itemReftxt.setText("");
        }
    }//GEN-LAST:event_itemReftxtMouseClicked

    private void itemReftxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReftxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemReftxtActionPerformed

    private void itemReftxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemReftxtKeyTyped
        // TODO add your handling code here:
        sel = 0;
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {

            itemsStatustbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT\n"
                    + "DISTINCT item_code,description,units,sub_cat_code as category,department as store \n"
                    + "\n"
                    + "FROM st_stock_item WHERE (description ilike  '%" + itemReftxt.getText() + "%' or item_code ilike '%" + itemReftxt.getText() + "%'  )"));
        }

    }//GEN-LAST:event_itemReftxtKeyTyped
    private String getPR(String code) {
        String prq = "N/A";
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT requisition_no,date::date "
                    + "FROM st_receive_requisation WHERE item_code ='" + code + "' and date::date>='" + SQLDateFormat.getSQLDate(dateItem.getDate()) + "' and requisition_no ilike 'PRQ%' order by 2 desc limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                prq = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return prq;
    }

    private String getLPO(String code) {

        String LPO = "N/A";

        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT order_no,date::date "
                    + "FROM st_orders WHERE code ='" + code + "' and date::date>='" + SQLDateFormat.getSQLDate(dateItem.getDate()) + "' and (order_no !='' OR order_no IS NOT NULL) order by 2 desc limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                LPO = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return LPO;
    }

    private String getTender(String code) {

        String tender = "N/A";

        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT quotation_no,date::date "
                    + "FROM st_recommendation WHERE item_code ='" + code + "' AND ordered_qty<1 order by 2 desc limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                tender = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return tender;
    }

    private String getLPOBySupplier(String code, String supplier) {

        String LPO = "N/A";

        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT order_no,date::date "
                    + "FROM st_orders WHERE code ='" + code + "' and date::date>='" + SQLDateFormat.getSQLDate(dateItem.getDate()) + "' and (order_no !='' OR order_no IS NOT NULL) and supplier ilike '" + supplier + "' order by 2 desc limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                LPO = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return LPO;
    }

    private String recentGRN(String code) {
        String grndate1 = "N/A";

        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT trans_date::date "
                    + "FROM st_sub_stores "
                    + "WHERE item_code ='" + code + "' AND receiving>0 "
                    + "AND sub_store in (SELECT distinct supplier_name from st_suppliers) order by 1 desc limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                grndate1 = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return grndate1;
    }
    private void itemsStatustblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsStatustblMouseClicked
        // TODO add your handling code here:
        item_cod = itemsStatustbl.getValueAt(itemsStatustbl.getSelectedRow(), 0).toString();

        store_name = itemsStatustbl.getValueAt(itemsStatustbl.getSelectedRow(), 4).toString();
        reqNotxt.setText(getPR(item_cod));

        orderNotxt.setText(getLPO(item_cod));

        currentTender.setText(getTender(item_cod));

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '-' union select distinct trim(initcap(supplier)) FROM st_recommendation WHERE quotation_no ilike '" + currentTender.getText() + "' AND trim(item_code) = '" + item_cod + "'"));

        jComboBox1.setSelectedItem(getSupporder(orderNotxt.getText()));

        QtyIntender.setText(com.afrisoftech.lib.GetItemInfo.getOrderedBalance(currentTender.getText(), item_cod, jComboBox1.getSelectedItem().toString(), connectDB).toString());

        storeTxt.setText(store_name);

        jTextField8.setText(recentGRN(item_cod));
    }//GEN-LAST:event_itemsStatustblMouseClicked
    private String getSupporder(String order) {
        String supplier = "-";
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT trim(initcap(supplier)) "
                    + "FROM st_orders WHERE order_no ='" + order + "' limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                supplier = rset.getString(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return supplier;
    }
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        QtyIntender.setText(com.afrisoftech.lib.GetItemInfo.getOrderedBalance(currentTender.getText(), item_cod, jComboBox1.getSelectedItem().toString(), connectDB).toString());

        orderNotxt.setText(getLPOBySupplier(item_cod, jComboBox1.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void orderNotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderNotxtMouseClicked
        // TODO add your handling code here:
        OrdersPdf new_ = new OrdersPdf();
        new_.OrdersPdf(connectDB, jComboBox1.getSelectedItem().toString(), orderNotxt.getText());
    }//GEN-LAST:event_orderNotxtMouseClicked

    private void reqNotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reqNotxtMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_reqNotxtMouseClicked
    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ordered_by from st_orders WHERE order_no is not null and ordering_store is null order by 1");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());
                //  System.out.println(rSet1.getObject(1).toString());
                // System.out.println(OrderNo);

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of users");
        return listofActivities;
    }

    public java.lang.Object[] getListofActivities2() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("select distinct refno from ac_aie_commitment where refno IN (SELECT distinct order_no FROM st_orders) order by 1");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());
                //  System.out.println(rSet1.getObject(1).toString());
                // System.out.println(OrderNo);

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of users");
        return listofActivities;
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        java.lang.Object[] listofAct = this.getListofActivities();
//                             
        for (int i = 0; i < listofAct.length; i++) {

            System.err.println(listofAct[i]);
            try {
                if (!UserName.getUserAllocatedSection(connectDB, listofAct[i].toString()).equalsIgnoreCase("-")) {
                    PreparedStatement pst = connectDB.prepareStatement("UPDATE st_orders SET  ordering_store ='" + UserName.getUserAllocatedSection(connectDB, listofAct[i].toString()) + "' WHERE ordered_by ='" + listofAct[i].toString() + "'");
                    pst.executeUpdate();
                    System.out.println("UPDATE st_orders SET  ordering_store ='" + UserName.getUserAllocatedSection(connectDB, listofAct[i].toString()) + "' WHERE ordered_by ='" + listofAct[i].toString() + "'");
                }
            } catch (SQLException ex) {
                            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        java.lang.Object[] listofAct = this.getListofActivities2();
//                             
        for (int i = 0; i < listofAct.length; i++) {

            //  System.err.println(listofAct[i]);
            try {
                // if (!UserName.getUserAllocatedSection(connectDB, listofAct[i].toString()).equalsIgnoreCase("-")) {
                PreparedStatement pst = connectDB.prepareStatement("UPDATE st_orders SET  "
                        + "head ='" + getHead(listofAct[i].toString()) + "',"
                        + "subhead='" + getsubHead(listofAct[i].toString()) + "' ,"
                        + "main_department='" + getMaindept(getHead(listofAct[i].toString())) + "' WHERE order_no ='" + listofAct[i].toString() + "'"
                );
                pst.executeUpdate();
                System.out.println(listofAct[i] + "\nUPDATE st_orders SET  "
                        + "head ='" + getHead(listofAct[i].toString()) + "',"
                        + "subhead='" + getsubHead(listofAct[i].toString()) + "', "
                        + "main_department='" + getMaindept(getHead(listofAct[i].toString())) + "' WHERE order_no ='" + listofAct[i].toString() + "'"
                );
                //}
            } catch (SQLException ex) {
                            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void userTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        System.out.println("Showing dialog");
        java.awt.Point point = userTxt.getLocationOnScreen();
        userdialog.setSize(400, 200);
        userdialog.setLocation(point);
        userdialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void usersearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_usersearchTxtCaretUpdate
        // TODO add your handling code here:
        if (usersearchTxt.getCaretPosition() > 3) {

            //            System.out.println("Nothing");
            //        } else {
            jSearchTable4.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select usename FROM pg_user WHERE usename ILIKE '" + usersearchTxt.getText() + "%' order by usename"));

            jSearchScrollPane4.setViewportView(jSearchTable4);

        }
    }//GEN-LAST:event_usersearchTxtCaretUpdate

    private void jSearchTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable4MouseClicked
        // TODO add your handling code here:
        userTxt.setText(jSearchTable4.getValueAt(jSearchTable4.getSelectedRow(), 0).toString());

        userdialog.dispose();

        jCheckBox1.doClick();

    }//GEN-LAST:event_jSearchTable4MouseClicked

    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        // TODO add your handling code here:
        userdialog.dispose();
    }//GEN-LAST:event_jButton93ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (userTxt.getText().length() > 0) {
            wholeTrail = false;

            jTable1.setModel(TableModel.createTableVectors(connectDB, "SELECT activity_description,activity_timestamp from st_user_activity WHERE activity_timestamp::date BETWEEN '" + SQLDateFormat.getSQLDate(begindate.getDate()) + "' AND '" + SQLDateFormat.getSQLDate(enddate.getDate()) + "' AND user_name ilike '" + userTxt.getText() + "'  order by 2 desc"));

        } else {
            JOptionPane.showMessageDialog(this, "Select a user first");
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        wholeTrail = true;
        //     refreshTable2();

        WaitingPRsThread wp = new WaitingPRsThread();
        wp.start();

    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        wholeTrail = jCheckBox3.isSelected();

        System.err.println(wholeTrail.toString());

        WaitingPRsThread wp = new WaitingPRsThread();
        if (wholeTrail == Boolean.FALSE) {
            jCheckBox3.setText("Enable threaded refresh");

            wp.stop();
        } else {
            jCheckBox3.setText("Disable threaded refresh");

            wp.start();
        }

    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        po = jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString();
        java.awt.Point p = jScrollPane3.getLocationOnScreen();
        mooreLPODetDialog.setLocation(p);
        mooreLPODetDialog.setSize(jScrollPane3.getWidth() * 3 / 4, 500);
        mooreLPODetDialog.setVisible(true);
        //itemsuploadtxt.setText(null);

        populateOtherdetails1(po);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
        type="RQ";
       // po = jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString();
//        java.awt.Point p = itemReftxt.getLocationOnScreen();
//        com.afrisoftech.hospinventory.AlldataIntfr AlldataIntfr = new AlldataIntfr(new java.awt.Frame(),true,type,connectDB);
//        AlldataIntfr.setLocation(p);
//        AlldataIntfr.setSize(jScrollPane3.getWidth() * 3 / 4, 500);
//        AlldataIntfr.setVisible(true);
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private String getMaindept(String code) {
        String dept = "-";
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT distinct headname from heads where head ilike '" + code + "'");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                dept = rset.getObject(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderingTenderItemsintfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dept;

    }

    private String getHead(String lpo) {
        String dept = "-";
        try {
            PreparedStatement pst = connectDB.prepareStatement("select distinct head from ac_aie_commitment where refno ilike '" + lpo + "' limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                dept = rset.getObject(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderingTenderItemsintfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dept;

    }

    private String getsubHead(String lpo) {
        String dept = "-";
        try {
            PreparedStatement pst = connectDB.prepareStatement("select distinct subhead from ac_aie_commitment where refno ilike '" + lpo + "' limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()) {
                dept = rset.getObject(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderingTenderItemsintfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dept;

    }

    class WaitingPRsThread extends Thread {

        public WaitingPRsThread() {
        }

        public void run() {
            while (wholeTrail) {
                refreshTable2();
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();

                    //Logger.getLogger(VitalSignRecIntfr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        //    public class MyCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
    //    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    //        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    //        cellComponent.setBackground(java.awt.Color.RED);
    //        return cellComponent;
    //    }
    //}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox PRCbx;
    private javax.swing.JTextField QtyIntender;
    private javax.swing.JTextField aieTxt;
    private javax.swing.JTextField aieTxt1;
    private com.afrisoftech.lib.DatePicker begindate;
    private com.afrisoftech.lib.DatePicker begindate1;
    private com.afrisoftech.lib.DatePicker begindate2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField countApprtxt;
    private javax.swing.JTextField countApprtxt1;
    private javax.swing.JTextField currentTender;
    private javax.swing.JTable dashTbl;
    private com.afrisoftech.lib.DatePicker dateItem;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker3;
    private com.afrisoftech.lib.DatePicker enddate;
    private com.afrisoftech.lib.DatePicker enddate1;
    private com.afrisoftech.lib.DatePicker enddate2;
    private com.afrisoftech.lib.DatePicker enddate3;
    private javax.swing.JCheckBox filterbydatesChx;
    private javax.swing.JCheckBox filterbyordersChx;
    private javax.swing.JCheckBox filterbyprocessesChx;
    private javax.swing.JCheckBox filterbyprsChx;
    private javax.swing.JTextField itemReftxt;
    private javax.swing.JTable itemsStatustbl;
    private javax.swing.JTable itemsTbl;
    private javax.swing.JTable itemsTbl1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton93;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPanel jSearchPanel4;
    private javax.swing.JScrollPane jSearchScrollPane4;
    private javax.swing.JTable jSearchTable4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField lpoNumbertxt;
    private javax.swing.JDialog mooreDetDialog;
    private javax.swing.JDialog mooreLPODetDialog;
    private javax.swing.JTextField orderNotxt;
    private javax.swing.JComboBox ordersCbx;
    private javax.swing.JComboBox processCbx;
    private javax.swing.JTextField reqNotxt;
    private javax.swing.JTextField reqdateTxt;
    private javax.swing.JTextField reqdateTxt1;
    private javax.swing.JTextField reqdbyTxt;
    private javax.swing.JTextField reqdbyTxt1;
    private javax.swing.JTextField storeTxt;
    private javax.swing.JTextField userTxt;
    private javax.swing.JDialog userdialog;
    private javax.swing.JTextField usersearchTxt;
    private javax.swing.JRadioButton viewlposbtn;
    // End of variables declaration//GEN-END:variables
}

//  class ProgressCellRenderer extends JProgressBar implements TableCellRenderer {
//  public ProgressCellRenderer(){
//    super(0, 100);
//    setValue(0);
//    setString("0%");
//    setStringPainted(true);
//  }
// 
//  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus,int row, int column) {
// 
//    //value is a percentage e.g. 95%
//    final String sValue = value.toString();
//    int index = sValue.indexOf('%');
//    if (index != -1) {
//      int p = 0;
//      try{
//        p = Integer.parseInt(sValue.substring(0, index));
//      }
//      catch(NumberFormatException e){
//      }
//      setValue(p);
//      setString(sValue);
//    }
//    return this;
//  }
//}
