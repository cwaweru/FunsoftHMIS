/*
 * CafeteriaBillPaymentsIntfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */
package com.afrisoftech.accounting;

import com.afrisoftech.lib.ServerTime;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>, Francis K. Waweru
 * <francis@systempartners.biz>, Peter Ndung'u Waweru
 * <pndungu@systempartners.biz>
 */
public class GovBillPaymentsIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {

    private javax.swing.JComboBox cmbox;
    private javax.swing.JComboBox cmbox2;
    private javax.swing.JComboBox cmbox3;
    private javax.swing.JComboBox cmbox4;
    private javax.swing.JComboBox cmbox21;
    private final javax.swing.JTextField comboTextField = new javax.swing.JTextField();
    DispatchThread dispatchThread;
    javax.swing.ComboBoxModel mainComboBoxModel;
    javax.swing.ComboBoxModel oldComboBox1311Model;
    private boolean threadCheck = false;
    private boolean refreshStatus;
    String Categ = null;
    String desc = null;
    String billNo = null;
    String transNo = null;
    String payMode = null;
    String patientAcc = null;
    String cardNo = null;
    String AccDesc = null;
    String scheme = null;
    String cardName = null;
    String isurer = null;
    String expDate = null;
    String staffNo = null;
    String glAcc = null;
    String receiptNo1 = null;
    public static String receiptNo2 = null;
    String actCode1 = null;
    String glCode1 = null;
    String bankAcc1 = null;
    String transNo1 = null;
    String actNames1 = null;
    String patCat1 = null;
    String patientAcc1 = null;
    String user = null;
    String glAcc1 = null;
    String accDesc1 = null;
    String name = null;
    String rname = null;
    String rcode = null;
    String cashPoint = null;
    String cash_no = null;
    int mobile_tx_validity_days = 1;
    java.sql.Connection connectDB = null;
    private java.lang.String shift_no = null;
    javax.swing.table.TableModel tableModel;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.lang.Thread shiftThread = null;
    java.lang.Boolean processReceipt = false;
    private boolean check4Exemptions = false;
    boolean priceEdit = false;
    boolean rePrints = false;
    boolean exemption_mode = false;
    boolean cashpointWalkins = true;
    public static String checkoutRequestID;
    int grace_period = 1;

    public GovBillPaymentsIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        refreshStatus = true;

        connectDB = connDb;

        pConnDB = pconnDB;

        shiftThread = new java.lang.Thread(this, "SHIFT_MONITOR");

        shiftThread.start();

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT edit_prices, re_prints,waivers,cashpoint_walkins FROM sales_prefs");
            java.sql.ResultSet rsetSales = pstmt.executeQuery();
            while (rsetSales.next()) {
                priceEdit = rsetSales.getBoolean(1);
                rePrints = rsetSales.getBoolean(2);
                exemption_mode = rsetSales.getBoolean(3);
                cashpointWalkins = rsetSales.getBoolean(4);
            }

            pstmt = connectDB.prepareStatement("SELECT review_grace_period FROM pb_patient_names ");
            rsetSales = pstmt.executeQuery();
            while (rsetSales.next()) {
                grace_period = rsetSales.getInt(1);
            }

            pstmt = connectDB.prepareStatement("SELECT mobile_tx_validity_days FROM pb_hospitalprofile ");
            rsetSales = pstmt.executeQuery();
            while (rsetSales.next()) {
                mobile_tx_validity_days = rsetSales.getInt(1);
            }

            rsetSales.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        paymentModeCmbx.setSelectedItem("Cash");
        //if (System.getProperty("exemptions.mode").equalsIgnoreCase("false")) {
        if (!exemption_mode) {

            exemptionChkbx.setEnabled(false);
            waivedAmountTxt.setEnabled(false);
            exemptionNumberTxt.setEditable(false);
            check4Exemptions = false;

        } else {

            exemptionChkbx.setEnabled(true);
            waivedAmountTxt.setEnabled(true);
            exemptionNumberTxt.setEditable(true);
            check4Exemptions = true;

        }
        walkINChkbx.setEnabled(cashpointWalkins);

        System.out.println("Cashpoint : " + System.getProperty("cashpoint"));

        //        System.out.println("Shift No. : "+getShiftMunber());
        //cashPointTxt.setText(System.getProperty("cashpoint"));
        cashPointTxt.setText(this.getCashPoint());

        shiftNoTxt.setText(getShiftNumber());

        reprintReceiptBtn.setVisible(rePrints);

        this.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getSize());
        //        startRunning();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable(){
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            /*    false, true, priceEdit, false, false, false
        };*/

        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    };
    jButton9 = new javax.swing.JButton();
    jSearchDialog1 = new javax.swing.JDialog();
    jSearchPanel1 = new javax.swing.JPanel();
    jTextField112 = new javax.swing.JTextField();
    jSearchScrollPane1 = new javax.swing.JScrollPane();
    jSearchTable1 = new com.afrisoftech.dbadmin.JTable(){
        Class[] types = new Class [] {
            java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
        };
        /*    false, true, priceEdit, false, false, false
    };*/

    boolean[] canEdit = new boolean [] {
        false, false, false, false, false
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
    };
    jButton91 = new javax.swing.JButton();
    jSearchDialog2 = new javax.swing.JDialog();
    jSearchPanel2 = new javax.swing.JPanel();
    jTextField113 = new javax.swing.JTextField();
    jSearchScrollPane2 = new javax.swing.JScrollPane();
    jSearchTable2 = new javax.swing.JTable();
    jButton42 = new javax.swing.JButton();
    jButton52 = new javax.swing.JButton();
    patientSearchDialog = new javax.swing.JDialog();
    patientSearchPanel = new javax.swing.JPanel();
    patientSearchTxt = new javax.swing.JTextField();
    patientSearchScrollPane = new javax.swing.JScrollPane();
    patientSearchTable = new com.afrisoftech.dbadmin.JTable();
    jButton421 = new javax.swing.JButton();
    jButton521 = new javax.swing.JButton();
    jDialog31 = new javax.swing.JDialog();
    //jDialog3.setSize(600,400);
    jPanel121 = new javax.swing.JPanel();
    jPanel1111 = new javax.swing.JPanel();
    jLabel66111 = new javax.swing.JLabel();
    jTextField14111 = new javax.swing.JTextField();
    jLabel3111 = new javax.swing.JLabel();
    jTextField4111 = new javax.swing.JTextField();
    jPanel312 = new javax.swing.JPanel();
    jLabel582211 = new javax.swing.JLabel();
    jLabel5821111 = new javax.swing.JLabel();
    jTextField3111 = new javax.swing.JTextField();
    jPanel41 = new javax.swing.JPanel();
    jTextField1611 = new javax.swing.JTextField();
    searchButton13 = new javax.swing.JButton();
    jSeparator1111 = new javax.swing.JSeparator();
    jButton301111 = new javax.swing.JButton();
    jButton252111 = new javax.swing.JButton();
    jButton1111 = new javax.swing.JButton();
    jSearchDialog11 = new javax.swing.JDialog();
    jSearchPanel11 = new javax.swing.JPanel();
    jTextField1111 = new javax.swing.JTextField();
    jSearchScrollPane11 = new javax.swing.JScrollPane();
    jSearchTable11 = new javax.swing.JTable();
    dispose11 = new javax.swing.JButton();
    buttonGroup2 = new javax.swing.ButtonGroup();
    mobilepayTxSearchDialog = new javax.swing.JDialog();
    jSearchPanel22 = new javax.swing.JPanel();
    mobilepayTxSearchTxt = new javax.swing.JTextField();
    jSearchScrollPane22 = new javax.swing.JScrollPane();
    mobilepayTxtSearchTable = new com.afrisoftech.dbadmin.JTable();
    jButton422 = new javax.swing.JButton();
    jButton522 = new javax.swing.JButton();
    addedMobileTxDialogue = new javax.swing.JDialog();
    jSearchPanel23 = new javax.swing.JPanel();
    jSearchScrollPane23 = new javax.swing.JScrollPane();
    addedMobilepayTxtTable = new com.afrisoftech.dbadmin.JTable();
    jButton523 = new javax.swing.JButton();
    jLabel10 = new javax.swing.JLabel();
    totalTokensAmountTxt = new javax.swing.JTextField();
    jButton524 = new javax.swing.JButton();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel8 = new javax.swing.JPanel();
    jPanel71 = new javax.swing.JPanel();
    jLabel71 = new javax.swing.JLabel();
    jTextField61 = new javax.swing.JTextField();
    jPanel111 = new javax.swing.JPanel();
    jPanel211 = new javax.swing.JPanel();
    jLabel111 = new javax.swing.JLabel();
    jComboBox111 = new javax.swing.JComboBox();
    jLabel2121 = new javax.swing.JLabel();
    jTextField151 = new javax.swing.JTextField();
    searchPatientPanel = new javax.swing.JPanel();
    patientNameTxt = new javax.swing.JTextField();
    searchPatientButton = new javax.swing.JButton();
    jPanel14 = new javax.swing.JPanel();
    searchNoChbx = new javax.swing.JCheckBox();
    searchNameChbx = new javax.swing.JCheckBox();
    walkINChkbx = new javax.swing.JCheckBox();
    clinicChbx = new javax.swing.JCheckBox();
    mchfpChbx = new javax.swing.JCheckBox();
    jLabel112 = new javax.swing.JLabel();
    patientNumberTxt = new javax.swing.JTextField();
    exemptionNumberLbl = new javax.swing.JLabel();
    exemptionNumberTxt = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jTextField7 = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    unitNumberTxt = new javax.swing.JTextField();
    payerMobileTelephoneNumberTxt = new javax.swing.JFormattedTextField();
    searchMobilePayTxPanel = new javax.swing.JPanel();
    mobilepayTxNoTxt = new javax.swing.JTextField();
    searchMobileTxButton = new javax.swing.JButton();
    jLabel7 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    viewMobileBtn = new javax.swing.JButton();
    mobilePayTokenBalanceTxt = new javax.swing.JTextField();
    addMobileBtn = new javax.swing.JButton();
    jSeparator111 = new javax.swing.JSeparator();
    jPanel32 = new javax.swing.JPanel();
    jPanel32.setVisible(false);
    jLabel22 = new javax.swing.JLabel();
    jTextField22 = new javax.swing.JTextField();
    jLabel32 = new javax.swing.JLabel();
    jTextField42 = new javax.swing.JTextField();
    jLabel41 = new javax.swing.JLabel();
    jTextField51 = new javax.swing.JTextField();
    jPanel311 = new javax.swing.JPanel();
    jPanel311.setVisible(false);
    jLabel213 = new javax.swing.JLabel();
    jTextField212 = new javax.swing.JTextField();
    jLabel313 = new javax.swing.JLabel();
    jTextField411 = new javax.swing.JTextField();
    jLabel422 = new javax.swing.JLabel();
    jTextField521 = new javax.swing.JTextField();
    jLabel4112 = new javax.swing.JLabel();
    jTextField5111 = new javax.swing.JTextField();
    jPanel9 = new javax.swing.JPanel();
    jScrollPane211 = new javax.swing.JScrollPane();
    billTable = new com.afrisoftech.dbadmin.JTable(){
        Class[] types = new Class [] {
            java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
        };
        /*    false, true, priceEdit, false, false, false
    };*/

    boolean[] canEdit = new boolean [] {
        false, true, false, false, false, false, false, false
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
    };
    jPanel13 = new javax.swing.JPanel();
    jLabel1411 = new javax.swing.JLabel();
    waivedAmountTxt = new javax.swing.JTextField();
    jLabel14 = new javax.swing.JLabel();
    lessDiscountedAmountTxt = new javax.swing.JTextField();
    jLabel141 = new javax.swing.JLabel();
    amountPaidTxt = new javax.swing.JTextField();
    jLabel151 = new javax.swing.JLabel();
    changeTxt = new javax.swing.JTextField();
    jLabel5311 = new javax.swing.JLabel();
    billAmountTxt = new javax.swing.JTextField();
    exemptionChkbx = new javax.swing.JCheckBox();
    jLabel5 = new javax.swing.JLabel();
    totalBilledAmountTxt = new javax.swing.JTextField();
    jPanel12 = new javax.swing.JPanel();
    jCheckBox4 = new javax.swing.JCheckBox();
    jCheckBox5 = new javax.swing.JCheckBox();
    jRadioButton1 = new javax.swing.JRadioButton();
    jCheckBox7 = new javax.swing.JCheckBox();
    jPanel10 = new javax.swing.JPanel();
    try {
        java.lang.Class.forName("org.postgresql.Driver");
    }catch (java.lang.ClassNotFoundException sl){
        System.out.println(sl.getMessage());
    }

    postSaleDataBtn = new javax.swing.JButton();
    try {
        java.lang.Class.forName("org.postgresql.Driver");
    }catch (java.lang.ClassNotFoundException sl){
        System.out.println(sl.getMessage());
    }

    generateReceiptBtn = new javax.swing.JButton();
    reprintReceiptBtn = new javax.swing.JButton();
    removeRowBtn = new javax.swing.JButton();
    clearFormBtn = new javax.swing.JButton();
    closePosBtn = new javax.swing.JButton();
    helpBtn = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    stkPushBtn = new javax.swing.JButton();
    jPanel51 = new javax.swing.JPanel();
    jLabel121 = new javax.swing.JLabel();
    paymentModeCmbx = new javax.swing.JComboBox();
    jLabel132 = new javax.swing.JLabel();
    glAccountTxt = new javax.swing.JTextField();
    datePicker1 = new com.afrisoftech.lib.DatePicker();
    jLabel8 = new javax.swing.JLabel();
    jLabel101 = new javax.swing.JLabel();
    cashPointTxt = new javax.swing.JTextField();
    jLabel1311 = new javax.swing.JLabel();
    shiftNoTxt = new javax.swing.JTextField();
    jLabel113 = new javax.swing.JLabel();
    receiptNumberTxt = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    paybillNumberCmbx = new javax.swing.JComboBox<>();

    jSearchDialog.setModal(true);
    jSearchDialog.setUndecorated(true);
    jSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

    jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jSearchPanel.setLayout(new java.awt.GridBagLayout());

    jTextField11.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            jTextField11CaretUpdate(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 10.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
    jSearchPanel.add(jTextField11, gridBagConstraints);

    jSearchScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jSearchScrollPaneMouseClicked(evt);
        }
    });

    jSearchTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Service Descr.", "Rate", "GL Account", "Code", "Main Service"
        }
    ));
    jSearchTable.setShowHorizontalLines(false);
    javax.swing.table.TableColumn column2 = null;
    for (int i = 0; i < 5; i++) {
        column2 = jSearchTable.getColumnModel().getColumn(i);
        if (i == 0) {

            column2.setPreferredWidth(300); //sport column is bigger
        } else {

            //if (i == 2) {
                column2.setPreferredWidth(150);
                // }  else
            //column.setPreferredWidth(50);

        }
    }
    jSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jSearchTableMouseClicked(evt);
        }
    });
    jSearchScrollPane.setViewportView(jSearchTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

    jButton9.setText("Dispose");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jSearchPanel.add(jButton9, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jSearchDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

    jSearchDialog1.setModal(true);
    jSearchDialog1.setUndecorated(true);
    jSearchDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

    jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jSearchPanel1.setLayout(new java.awt.GridBagLayout());

    jTextField112.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            jTextField112CaretUpdate(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 10.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
    jSearchPanel1.add(jTextField112, gridBagConstraints);

    jSearchTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "SERVICE TYPE", "RATE", "GL ACCOUNT", "CODE", "MAIN SERVICE"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jSearchTable1.setShowHorizontalLines(false);
    javax.swing.table.TableColumn column3 = null;
    for (int i = 0; i < 5; i++) {
        column3 = jSearchTable1.getColumnModel().getColumn(i);
        if (i == 0) {

            column3.setPreferredWidth(300); //sport column is bigger
        } else {

            //if (i == 2) {
                column3.setPreferredWidth(150);
                // }  else
            //column.setPreferredWidth(50);

        }
    }
    jSearchTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jSearchTable1MouseClicked(evt);
        }
    });
    jSearchTable1.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jSearchTable1KeyReleased(evt);
        }
    });
    jSearchScrollPane1.setViewportView(jSearchTable1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    jSearchPanel1.add(jSearchScrollPane1, gridBagConstraints);

    jButton91.setText("Dispose");
    jButton91.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton91ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jSearchPanel1.add(jButton91, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jSearchDialog1.getContentPane().add(jSearchPanel1, gridBagConstraints);

    jSearchDialog2.setModal(true);
    jSearchDialog2.setUndecorated(true);
    jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

    jSearchPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jSearchPanel2.setLayout(new java.awt.GridBagLayout());

    jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            jTextField113CaretUpdate(evt);
        }
    });
    jTextField113.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField113ActionPerformed(evt);
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

    jButton42.setText("Select");
    jButton42.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton42ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jSearchPanel2.add(jButton42, gridBagConstraints);

    jButton52.setText("Dispose");
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

    patientSearchDialog.setModal(true);
    patientSearchDialog.setUndecorated(true);
    patientSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

    patientSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    patientSearchPanel.setLayout(new java.awt.GridBagLayout());

    patientSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            patientSearchTxtCaretUpdate(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 300.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
    patientSearchPanel.add(patientSearchTxt, gridBagConstraints);

    patientSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
    patientSearchTable.setShowHorizontalLines(false);
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
    patientSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            patientSearchTableMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            patientSearchTableMouseEntered(evt);
        }
    });
    patientSearchScrollPane.setViewportView(patientSearchTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    patientSearchPanel.add(patientSearchScrollPane, gridBagConstraints);

    jButton421.setText("Select");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    patientSearchPanel.add(jButton421, gridBagConstraints);

    jButton521.setText("Dispose");
    jButton521.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton521ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    patientSearchPanel.add(jButton521, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    patientSearchDialog.getContentPane().add(patientSearchPanel, gridBagConstraints);

    jDialog31.setTitle("Enter Doctors Fee");
    jDialog31.setModal(true);
    jDialog31.setSize(400,200);
    jDialog31.getContentPane().setLayout(new java.awt.GridBagLayout());

    jPanel121.setLayout(new java.awt.GridBagLayout());

    jPanel1111.setBackground(new java.awt.Color(130, 232, 255));
    jPanel1111.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter Doctor's details here"));
    jPanel1111.setLayout(new java.awt.GridBagLayout());

    jLabel66111.setText("Amount");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    jPanel1111.add(jLabel66111, gridBagConstraints);

    jTextField14111.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    jPanel1111.add(jTextField14111, gridBagConstraints);

    jLabel3111.setText("Category");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1.0;
    jPanel1111.add(jLabel3111, gridBagConstraints);

    jTextField4111.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    jPanel1111.add(jTextField4111, gridBagConstraints);

    jPanel312.setBackground(new java.awt.Color(204, 204, 255));
    jPanel312.setLayout(new java.awt.GridBagLayout());

    jLabel582211.setText("Doctor's No.");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel312.add(jLabel582211, gridBagConstraints);

    jLabel5821111.setText("Doc. Name");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel312.add(jLabel5821111, gridBagConstraints);

    jTextField3111.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 4.0;
    gridBagConstraints.weighty = 1.0;
    jPanel312.add(jTextField3111, gridBagConstraints);

    jPanel41.setLayout(new java.awt.GridBagLayout());

    jTextField1611.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel41.add(jTextField1611, gridBagConstraints);

    searchButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
    searchButton13.setToolTipText("Search");
    searchButton13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    searchButton13.setMaximumSize(new java.awt.Dimension(74, 53));
    searchButton13.setMinimumSize(new java.awt.Dimension(74, 53));
    searchButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    jPanel41.add(searchButton13, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel312.add(jPanel41, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 2.0;
    gridBagConstraints.weighty = 1.0;
    jPanel1111.add(jPanel312, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    gridBagConstraints.weightx = 5.0;
    gridBagConstraints.weighty = 10.0;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
    jPanel121.add(jPanel1111, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.gridwidth = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel121.add(jSeparator1111, gridBagConstraints);

    jButton301111.setMnemonic('O');
    jButton301111.setText("Ok");
    jButton301111.setToolTipText("Click here to enter data");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 10;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel121.add(jButton301111, gridBagConstraints);

    jButton252111.setMnemonic('c');
    jButton252111.setText("Close");
    jButton252111.setToolTipText("Click here to close window");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 10;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel121.add(jButton252111, gridBagConstraints);

    jButton1111.setMnemonic('h');
    jButton1111.setText("Help");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 10;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel121.add(jButton1111, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jDialog31.getContentPane().add(jPanel121, gridBagConstraints);

    jSearchDialog11.setModal(true);
    jSearchDialog11.setUndecorated(true);
    jSearchDialog11.getContentPane().setLayout(new java.awt.GridBagLayout());

    jSearchPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jSearchPanel11.setLayout(new java.awt.GridBagLayout());

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
    jSearchPanel11.add(jTextField1111, gridBagConstraints);

    jSearchTable11.setToolTipText("Click on the target row to select the patient from the search.");
    jSearchTable11.setShowHorizontalLines(false);
    /*int rowsNo = 0;
    //try {

        crset1.setCommand("SELECT patient_no, (upper(first_name||' '||second_name||' '||last_name)) as name, year_of_birth, residence from hp_patient_register order by name");

        crset1.setConnectionSource(pConnDB);

        // try {
            //crset1.execute();

            // crset2.setExecuteOnLoad(true);
            jSearchTable.setModel(new org.netbeans.lib.sql.models.TableModel(crset1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("patient_no", "Patient No.", false),
                new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false),

                new org.netbeans.lib.sql.models.TableModel.Column("year_of_birth", "Birth date", true),
                new org.netbeans.lib.sql.models.TableModel.Column("residence", "Residence", true)

            }));

            tableModel = jSearchTable.getModel();

            jSearchScrollPane.setViewportView(jSearchTable);

            /*   }catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());

        }

        javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }*/
        jSearchScrollPane11.setViewportView(jSearchTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel11.add(jSearchScrollPane11, gridBagConstraints);

        dispose11.setMnemonic('l');
        dispose11.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(dispose11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog11.getContentPane().add(jSearchPanel11, gridBagConstraints);

        mobilepayTxSearchDialog.setModal(true);
        mobilepayTxSearchDialog.setUndecorated(true);
        mobilepayTxSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel22.setLayout(new java.awt.GridBagLayout());

        mobilepayTxSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                mobilepayTxSearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel22.add(mobilepayTxSearchTxt, gridBagConstraints);

        mobilepayTxtSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        mobilepayTxtSearchTable.setShowHorizontalLines(false);
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
        mobilepayTxtSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mobilepayTxtSearchTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobilepayTxtSearchTableMouseEntered(evt);
            }
        });
        jSearchScrollPane22.setViewportView(mobilepayTxtSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel22.add(jSearchScrollPane22, gridBagConstraints);

        jButton422.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel22.add(jButton422, gridBagConstraints);

        jButton522.setText("Dispose");
        jButton522.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton522ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel22.add(jButton522, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mobilepayTxSearchDialog.getContentPane().add(jSearchPanel22, gridBagConstraints);

        addedMobileTxDialogue.setModal(true);
        addedMobileTxDialogue.setUndecorated(true);
        addedMobileTxDialogue.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel23.setLayout(new java.awt.GridBagLayout());

        addedMobilepayTxtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction No", "Mobile No", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addedMobilepayTxtTable.setToolTipText("Click on the target row to select the patient from the search.");
        addedMobilepayTxtTable.setShowHorizontalLines(false);
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
        addedMobilepayTxtTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addedMobilepayTxtTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addedMobilepayTxtTableMouseEntered(evt);
            }
        });
        jSearchScrollPane23.setViewportView(addedMobilepayTxtTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel23.add(jSearchScrollPane23, gridBagConstraints);

        jButton523.setText("Dispose");
        jButton523.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton523ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel23.add(jButton523, gridBagConstraints);

        jLabel10.setText("Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel23.add(jLabel10, gridBagConstraints);

        totalTokensAmountTxt.setEditable(false);
        totalTokensAmountTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        totalTokensAmountTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTokensAmountTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel23.add(totalTokensAmountTxt, gridBagConstraints);

        jButton524.setText("Remove Row");
        jButton524.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton524ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel23.add(jButton524, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        addedMobileTxDialogue.getContentPane().add(jSearchPanel23, gridBagConstraints);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("POINT OF SALE : Cash Sales (CASH REGISTER/POS)");
        setFrameIcon(null);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel71.setLayout(new java.awt.GridBagLayout());

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel71.add(jLabel71, gridBagConstraints);

        jTextField61.setEditable(false);
        jTextField61.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField61.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel71.add(jTextField61, gridBagConstraints);

        jPanel111.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel111.setLayout(new java.awt.GridBagLayout());

        jPanel211.setBackground(new java.awt.Color(204, 255, 204));
        jPanel211.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient File Billing Details (PLEASE NOTE THAT ALL SPACES HIGHLIGHTED IN RED ARE MANDATORY)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        jPanel211.setLayout(new java.awt.GridBagLayout());

        jLabel111.setForeground(new java.awt.Color(255, 0, 51));
        jLabel111.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel111, gridBagConstraints);

        jComboBox111.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct upper(main_service)  as main_service from pb_operating_parameters  order by main_service"));
        jComboBox111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox111ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(jComboBox111, gridBagConstraints);

        jLabel2121.setText("Revenue Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel2121, gridBagConstraints);

        jTextField151.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField151.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel211.add(jTextField151, gridBagConstraints);

        searchPatientPanel.setMinimumSize(new java.awt.Dimension(82, 40));
        searchPatientPanel.setPreferredSize(new java.awt.Dimension(41, 27));
        searchPatientPanel.setLayout(new java.awt.GridBagLayout());

        patientNameTxt.setEditable(false);
        patientNameTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        patientNameTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                patientNameTxtCaretUpdate(evt);
            }
        });
        patientNameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientNameTxtFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1000.0;
        gridBagConstraints.weighty = 1.0;
        searchPatientPanel.add(patientNameTxt, gridBagConstraints);

        searchPatientButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Find 2.png"))); // NOI18N
        searchPatientButton.setToolTipText("Search");
        searchPatientButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchPatientButton.setIconTextGap(0);
        searchPatientButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchPatientButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchPatientButton.setPreferredSize(new java.awt.Dimension(33, 23));
        searchPatientButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchPatientPanel.add(searchPatientButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(searchPatientPanel, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        searchNoChbx.setBackground(new java.awt.Color(153, 255, 255));
        buttonGroup1.add(searchNoChbx);
        searchNoChbx.setSelected(true);
        searchNoChbx.setText("Search By No.");
        searchNoChbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNoChbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel14.add(searchNoChbx, gridBagConstraints);

        searchNameChbx.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroup1.add(searchNameChbx);
        searchNameChbx.setText("Search By Name");
        searchNameChbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameChbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel14.add(searchNameChbx, gridBagConstraints);

        buttonGroup1.add(walkINChkbx);
        walkINChkbx.setText("Walk In");
        walkINChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                walkINChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel14.add(walkINChkbx, gridBagConstraints);

        buttonGroup1.add(clinicChbx);
        clinicChbx.setText("Clinics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel14.add(clinicChbx, gridBagConstraints);

        buttonGroup1.add(mchfpChbx);
        mchfpChbx.setText("MCH/FP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(mchfpChbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel211.add(jPanel14, gridBagConstraints);

        jLabel112.setForeground(new java.awt.Color(255, 0, 51));
        jLabel112.setText("Patient No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel112, gridBagConstraints);

        patientNumberTxt.setEditable(false);
        patientNumberTxt.setMinimumSize(new java.awt.Dimension(80, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(patientNumberTxt, gridBagConstraints);

        exemptionNumberLbl.setText("Exemption/Waiver Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(exemptionNumberLbl, gridBagConstraints);

        exemptionNumberTxt.setMinimumSize(new java.awt.Dimension(80, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(exemptionNumberTxt, gridBagConstraints);

        jLabel2.setText("File Selection Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel2, gridBagConstraints);

        jLabel16.setText("A/C Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel16, gridBagConstraints);

        jTextField7.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(jTextField7, gridBagConstraints);

        jLabel3.setText("Patient Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel3, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setText("Cash");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(jTextField1, gridBagConstraints);

        jLabel4.setText("Unit Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel4, gridBagConstraints);

        unitNumberTxt.setEditable(false);
        unitNumberTxt.setMinimumSize(new java.awt.Dimension(80, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(unitNumberTxt, gridBagConstraints);

        payerMobileTelephoneNumberTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill Payer Telephone No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        payerMobileTelephoneNumberTxt.setForeground(new java.awt.Color(0, 0, 255));
        try {
            payerMobileTelephoneNumberTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("254-###-######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        payerMobileTelephoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payerMobileTelephoneNumberTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(payerMobileTelephoneNumberTxt, gridBagConstraints);

        searchMobilePayTxPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchMobilePayTxPanel.setMinimumSize(new java.awt.Dimension(82, 40));
        searchMobilePayTxPanel.setPreferredSize(new java.awt.Dimension(41, 27));
        searchMobilePayTxPanel.setLayout(new java.awt.GridBagLayout());

        mobilepayTxNoTxt.setEditable(false);
        mobilepayTxNoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        mobilepayTxNoTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                mobilepayTxNoTxtCaretUpdate(evt);
            }
        });
        mobilepayTxNoTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mobilepayTxNoTxtFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1000.0;
        gridBagConstraints.weighty = 1.0;
        searchMobilePayTxPanel.add(mobilepayTxNoTxt, gridBagConstraints);

        searchMobileTxButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Find 2.png"))); // NOI18N
        searchMobileTxButton.setToolTipText("Search");
        searchMobileTxButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchMobileTxButton.setIconTextGap(0);
        searchMobileTxButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchMobileTxButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchMobileTxButton.setPreferredSize(new java.awt.Dimension(33, 23));
        searchMobileTxButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchMobileTxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMobileTxButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchMobilePayTxPanel.add(searchMobileTxButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(searchMobilePayTxPanel, gridBagConstraints);

        jLabel7.setText("Mobile Transaction No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel7, gridBagConstraints);

        jLabel9.setText("Token Balance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel211.add(jLabel9, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        viewMobileBtn.setForeground(new java.awt.Color(255, 51, 51));
        viewMobileBtn.setMnemonic('C');
        viewMobileBtn.setText("VIEW");
        viewMobileBtn.setToolTipText("Click here to close window");
        viewMobileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMobileBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(viewMobileBtn, gridBagConstraints);

        mobilePayTokenBalanceTxt.setEditable(false);
        mobilePayTokenBalanceTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        mobilePayTokenBalanceTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mobilePayTokenBalanceTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(mobilePayTokenBalanceTxt, gridBagConstraints);

        addMobileBtn.setForeground(new java.awt.Color(255, 51, 51));
        addMobileBtn.setMnemonic('C');
        addMobileBtn.setText("ADD");
        addMobileBtn.setToolTipText("Click here to close window");
        addMobileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMobileBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(addMobileBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel211.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel111.add(jPanel211, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel111.add(jSeparator111, gridBagConstraints);

        jPanel32.setBackground(new java.awt.Color(204, 255, 204));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Cheque Details"));
        jPanel32.setLayout(new java.awt.GridBagLayout());

        jLabel22.setText("Cheque No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel32.add(jLabel22, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jTextField22, gridBagConstraints);

        jLabel32.setText("Drawer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel32.add(jLabel32, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jTextField42, gridBagConstraints);

        jLabel41.setText("Drawer's Bank");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jLabel41, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel32.add(jTextField51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel111.add(jPanel32, gridBagConstraints);

        jPanel311.setBorder(javax.swing.BorderFactory.createTitledBorder("Card Details"));
        jPanel311.setLayout(new java.awt.GridBagLayout());

        jLabel213.setText("Card No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jLabel213, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jTextField212, gridBagConstraints);

        jLabel313.setText("Card Company");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jLabel313, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jTextField411, gridBagConstraints);

        jLabel422.setText("Card Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jLabel422, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel311.add(jTextField521, gridBagConstraints);

        jLabel4112.setText("Expiry Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel311.add(jLabel4112, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel311.add(jTextField5111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel111.add(jPanel311, gridBagConstraints);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jScrollPane211.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cash Sale Particulars - Select item/service (by clicking once on the ITEM/SERVICE column) and specify the quantity. Remember to press the TAB key to validate your entries.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        billTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        billTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null},
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
                "Service", "Quantity", "Price", "Total", "Gl Account", "Billed", "Item ID", "Transaction No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billTable.setRowHeight(22);
        javax.swing.table.TableColumn column1 = null;
        for (int i = 0; i < 6; i++) {
            column1 = billTable.getColumnModel().getColumn(i);
            if (i == 0) {

                column1.setPreferredWidth(300); //sport column is bigger
            } else {

                //if (i == 2) {
                    column1.setPreferredWidth(150);
                    // }  else
                //column.setPreferredWidth(50);

            }
        }
        billTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billTableMouseClicked(evt);
            }
        });
        billTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                billTableComponentShown(evt);
            }
        });
        billTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                billTableKeyReleased(evt);
            }
        });
        jScrollPane211.setViewportView(billTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 30.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel9.add(jScrollPane211, gridBagConstraints);

        jPanel13.setBackground(new java.awt.Color(204, 255, 204));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel1411.setText("Waiver");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel1411, gridBagConstraints);

        waivedAmountTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        waivedAmountTxt.setText("0.00");
        waivedAmountTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                waivedAmountTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(waivedAmountTxt, gridBagConstraints);

        jLabel14.setText("Amt - Waiver");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel14, gridBagConstraints);

        lessDiscountedAmountTxt.setEditable(false);
        lessDiscountedAmountTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lessDiscountedAmountTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(lessDiscountedAmountTxt, gridBagConstraints);

        jLabel141.setForeground(new java.awt.Color(255, 51, 51));
        jLabel141.setText("Money Paid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel141, gridBagConstraints);

        amountPaidTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountPaidTxt.setText("0.00");
        amountPaidTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                amountPaidTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(amountPaidTxt, gridBagConstraints);

        jLabel151.setText("Change");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabel151, gridBagConstraints);

        changeTxt.setEditable(false);
        changeTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        changeTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(changeTxt, gridBagConstraints);

        jLabel5311.setText("Net Amt KSH.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jLabel5311, gridBagConstraints);

        billAmountTxt.setEditable(false);
        billAmountTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        billAmountTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(billAmountTxt, gridBagConstraints);

        exemptionChkbx.setBackground(new java.awt.Color(204, 255, 255));
        exemptionChkbx.setForeground(new java.awt.Color(0, 51, 255));
        exemptionChkbx.setText("Exemption?");
        exemptionChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exemptionChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(exemptionChkbx, gridBagConstraints);

        jLabel5.setText("Total Billed Amt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jLabel5, gridBagConstraints);

        totalBilledAmountTxt.setEditable(false);
        totalBilledAmountTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        totalBilledAmountTxt.setText("0.00");
        totalBilledAmountTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalBilledAmountTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(totalBilledAmountTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jPanel13, gridBagConstraints);

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jCheckBox4.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup2.add(jCheckBox4);
        jCheckBox4.setText("Item Code");
        jCheckBox4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel12.add(jCheckBox4, new java.awt.GridBagConstraints());

        jCheckBox5.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup2.add(jCheckBox5);
        jCheckBox5.setSelected(true);
        jCheckBox5.setText("Description");
        jCheckBox5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel12.add(jCheckBox5, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jPanel12, gridBagConstraints);

        jRadioButton1.setText("Waived/Exempted");
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jRadioButton1, gridBagConstraints);

        jCheckBox7.setBackground(new java.awt.Color(255, 204, 204));
        jCheckBox7.setForeground(new java.awt.Color(0, 0, 255));
        jCheckBox7.setMnemonic('y');
        jCheckBox7.setText("Copay");
        jCheckBox7.setToolTipText("Click here to receipt Co_payment Patients");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jCheckBox7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        jPanel111.add(jPanel9, gridBagConstraints);

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        postSaleDataBtn.setBackground(new java.awt.Color(230, 230, 230));
        postSaleDataBtn.setMnemonic('O');
        postSaleDataBtn.setText("Post data");
        postSaleDataBtn.setToolTipText("Click here enter data");
        postSaleDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postSaleDataBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(postSaleDataBtn, gridBagConstraints);

        generateReceiptBtn.setBackground(new java.awt.Color(230, 230, 230));
        generateReceiptBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Devices & Hardware/Printer 2.png"))); // NOI18N
        generateReceiptBtn.setMnemonic('O');
        generateReceiptBtn.setText("Print Receipt");
        generateReceiptBtn.setToolTipText("Click here enter data");
        generateReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReceiptBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(generateReceiptBtn, gridBagConstraints);

        reprintReceiptBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Devices & Hardware/Printer 3.png"))); // NOI18N
        reprintReceiptBtn.setMnemonic('p');
        reprintReceiptBtn.setText("Re-Print Receipt");
        reprintReceiptBtn.setToolTipText("Click here to edit data");
        reprintReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reprintReceiptBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(reprintReceiptBtn, gridBagConstraints);

        removeRowBtn.setMnemonic('r');
        removeRowBtn.setText("Remove Row");
        removeRowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel10.add(removeRowBtn, gridBagConstraints);

        clearFormBtn.setMnemonic('l');
        clearFormBtn.setText("Clear data");
        clearFormBtn.setToolTipText("Click here to clear textfields");
        clearFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(clearFormBtn, gridBagConstraints);

        closePosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Devices & Hardware/Computer 1.png"))); // NOI18N
        closePosBtn.setMnemonic('C');
        closePosBtn.setText("Close POS");
        closePosBtn.setToolTipText("Click here to close window");
        closePosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closePosBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(closePosBtn, gridBagConstraints);

        helpBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Document.png"))); // NOI18N
        helpBtn.setMnemonic('h');
        helpBtn.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(helpBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3000.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel1, gridBagConstraints);

        stkPushBtn.setText("Mobile Pay");
        stkPushBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stkPushBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(stkPushBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel111.add(jPanel10, gridBagConstraints);

        jPanel51.setBackground(new java.awt.Color(204, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder("Shift Session Identity"));
        jPanel51.setLayout(new java.awt.GridBagLayout());

        jLabel121.setText("Payment Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel121, gridBagConstraints);

        paymentModeCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT initcap(payment_mode) as payment_mode from pb_paymentmodes WHERE payment_mode NOT ILIKE 'SCHEME' order by payment_mode"));
        paymentModeCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentModeCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(paymentModeCmbx, gridBagConstraints);

        jLabel132.setText("Gl Acc. No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel132, gridBagConstraints);

        glAccountTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(glAccountTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(datePicker1, gridBagConstraints);

        jLabel8.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel8, gridBagConstraints);

        jLabel101.setText("Cash Point");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel101, gridBagConstraints);

        cashPointTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(cashPointTxt, gridBagConstraints);

        jLabel1311.setText("Shift No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel1311, gridBagConstraints);

        shiftNoTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(shiftNoTxt, gridBagConstraints);

        jLabel113.setText("Receipt No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel113, gridBagConstraints);

        receiptNumberTxt.setEditable(false);
        receiptNumberTxt.setForeground(new java.awt.Color(255, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 9.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(receiptNumberTxt, gridBagConstraints);

        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Selected Paybill");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel51.add(jLabel6, gridBagConstraints);

        paybillNumberCmbx.setForeground(new java.awt.Color(0, 0, 0));
        paybillNumberCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '' as paybill_no UNION SELECT DISTINCT paybill_no FROM ac_cash_points ORDER BY 1"));
        paybillNumberCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paybillNumberCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel51.add(paybillNumberCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel111.add(jPanel51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 30.0;
        jPanel71.add(jPanel111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jPanel71, gridBagConstraints);

        jTabbedPane1.addTab("OUT-Patient Cash payments", jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 1151, 597);
    }// </editor-fold>//GEN-END:initComponents

    private void exemptionChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exemptionChkbxActionPerformed
        if (check4Exemptions) {
            if (exemptionNumberTxt.getText().toCharArray().length > 0) {
                if (exemptionChkbx.isSelected()) {
                    amountPaidTxt.setText("0.00");
                    amountPaidTxt.setEditable(false);
                    waivedAmountTxt.setEditable(false);
                } else {
                    amountPaidTxt.setEditable(true);
                    waivedAmountTxt.setEditable(true);
                    amountPaidTxt.setText(billAmountTxt.getText());
                }
            } else {
                if (jTextField1.getText().startsWith("Cash") || jTextField1.getText().startsWith("Staff")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "You MUST provide a reference number for the Exemption!\n"
                            + "Type the Exemption Reference Number and select this checkbox again.", "Exemption Number!", javax.swing.JOptionPane.ERROR_MESSAGE);

                    exemptionChkbx.setSelected(false);
                } else {
                }
            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_exemptionChkbxActionPerformed

    private void patientNameTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientNameTxtFocusGained
        // Add your handling code here:
    }//GEN-LAST:event_patientNameTxtFocusGained

    private void patientNameTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_patientNameTxtCaretUpdate
        // Add your handling code here:
    }//GEN-LAST:event_patientNameTxtCaretUpdate

    private void waivedAmountTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_waivedAmountTxtCaretUpdate
        //if (check4Exemptions) {
        if (exemptionNumberTxt.getText().toCharArray().length > 0) {
            if (waivedAmountTxt.getCaretPosition() >= 1) {
                double total = java.lang.Double.parseDouble(totalBilledAmountTxt.getText());
                double paid = java.lang.Double.parseDouble(amountPaidTxt.getText());
                double waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());
                if (total >= waiver) {
                    lessDiscountedAmountTxt.setText(java.lang.String.valueOf(total - waiver));
                    amountPaidTxt.setText(java.lang.String.valueOf(total - waiver));
                } else {
                    if (waiver > total) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Waiver Cannot be more than Total Bill !", "Caution message", javax.swing.JOptionPane.ERROR_MESSAGE);
                        waiver = 0.00;
                    }
                }
            }

        } else {
            if (jTextField1.getText().startsWith("Cash") || jTextField1.getText().startsWith("Staff") && java.lang.Double.parseDouble(waivedAmountTxt.getText()) > 0) {
                //javax.swing.JOptionPane.showMessageDialog(this, "You MUST provide a reference number for the Waiver!\n"
                //        + "Type the Waiver Reference Number and select this checkbox again.", "Waiver Number!", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {

                if (waivedAmountTxt.getCaretPosition() >= 1) {
                    double total = java.lang.Double.parseDouble(billAmountTxt.getText()); //d
                    double paid = java.lang.Double.parseDouble(amountPaidTxt.getText());
                    double waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());
                    if (total >= waiver) {
                        lessDiscountedAmountTxt.setText(java.lang.String.valueOf(total - waiver));
                        amountPaidTxt.setText(java.lang.String.valueOf(total - waiver));
                    } else {
                        if (waiver > total) {
                            javax.swing.JOptionPane.showMessageDialog(this, "Waiver Cannot be more than Total Bill !", "Caution message", javax.swing.JOptionPane.ERROR_MESSAGE);
                            waiver = 0.00;
                        }
                    }
                }
            }
        }
        //}
        // Add your handling code here:
    }//GEN-LAST:event_waivedAmountTxtCaretUpdate

    private void amountPaidTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_amountPaidTxtCaretUpdate
        if (amountPaidTxt.getCaretPosition() >= 1) {
            double totalBilled = java.lang.Double.parseDouble(totalBilledAmountTxt.getText().replace(",", ""));
            double total = java.lang.Double.parseDouble(billAmountTxt.getText());
            double waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());

            lessDiscountedAmountTxt.setText(java.lang.String.valueOf(totalBilled - waiver));
            double paid = java.lang.Double.parseDouble(amountPaidTxt.getText());
            double diff = java.lang.Double.parseDouble(lessDiscountedAmountTxt.getText());
            if (paid >= diff) {
                changeTxt.setText(java.lang.String.valueOf(paid - diff));
            } else {
                changeTxt.setText(java.lang.String.valueOf(0.00));
            }

        }         // Add your handling code here:
    }//GEN-LAST:event_amountPaidTxtCaretUpdate

    private void generateReceiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReceiptBtnActionPerformed

        if (Double.parseDouble(billAmountTxt.getText().replace(",", "")) < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please ensure that the receipt value has a valid amount.");

        } else {
            if (walkINChkbx.isSelected()) {
                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT 'WLKN'||lpad(nextval('walkin_no_seq')::varchar,8,'0')");

                    java.sql.ResultSet rset = pstmt.executeQuery();

                    while (rset.next()) {
                        patientNumberTxt.setText(rset.getString(1));
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();             //ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
            /////          if ((Double.parseDouble(amountPaidTxt.getText()) >= Double.parseDouble(billAmountTxt.getText())) && (Double.parseDouble(billAmountTxt.getText()) > 0.00) & (patientNumberTxt.getText().toCharArray().length > 0)) {

//        if ((Double.parseDouble(amountPaidTxt.getText()) >= Double.parseDouble(billAmountTxt.getText()) || jCheckBox6.isSelected()) && (patientNumberTxt.getText().toCharArray().length > 0)) {
            if ((Double.parseDouble(amountPaidTxt.getText()) >= Double.parseDouble(billAmountTxt.getText()) || exemptionChkbx.isSelected()) && (patientNumberTxt.getText().toCharArray().length > 0)) {

                receiptNumberTxt.setText(null);
                System.out.println("Printing the receipt.");
                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(status) FROM ac_shifts WHERE status ilike 'running' AND user_name = current_user");
                    java.sql.ResultSet rset = pstmt.executeQuery();
                    int i = 0;
                    while (rset.next()) {
                        i = rset.getInt(1);
                    }
                    if (i < 1) {
                        com.afrisoftech.hospital.HospitalMain.saccopn.remove(this);
                        //  thisgetParent().remove(this);
                        javax.swing.JOptionPane.showMessageDialog(this, "You MUST open a new shift in order to continue.\n Please close Front End Operations and open afresh.");
                    } else {
                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                        System.out.println(datenowSql1.toString());

                        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                        System.out.println(datenowSql.toString());
                        int k = 1;
                        String receiptNo1 = null;

                        postSaleDataBtnActionPerformed(evt);

                        System.out.println("PROCESS RECEIPT BOOLEAN [" + processReceipt + "]");

                        if (processReceipt) {

                            try {

//try {
                                connectDB.setAutoCommit(false);

                                System.out.println("This is the receipt no " + receiptNo2);

                                //receiptNo1 = jTextField1113.getText();
                                if (receiptNumberTxt.getText().toCharArray().length > 0) {
                                    String receiptNo = jTextField1.getText();
                                    String rct = null;
                                    java.sql.Statement ps112 = connectDB.createStatement();
                                    java.sql.ResultSet rst112 = ps112.executeQuery("select rct_format from receipt_pref");
                                    while (rst112.next()) {
                                        rct = rst112.getObject(1).toString();
                                    }
                                    if (rct.equalsIgnoreCase("Pdf")) {
                                        com.afrisoftech.reports.ReceiptsPdf policy = new com.afrisoftech.reports.ReceiptsPdf();
                                        policy.ReceiptsPdf(connectDB, receiptNo2);
                                    } else {
                                        com.afrisoftech.txtreports.GokReceiptsTxt policy = new com.afrisoftech.txtreports.GokReceiptsTxt(connectDB, patientNameTxt.getText(), billAmountTxt.getText(), receiptNo2, this.paymentModeCmbx.getSelectedItem().toString(), amountPaidTxt.getText(), changeTxt.getText(), shiftNoTxt.getText(), unitNumberTxt.getText());

                                    }
                                    // jTextField1211.setText("0.00");
                                    lessDiscountedAmountTxt.setText("0.00");
                                    amountPaidTxt.setText("0.00");
                                    changeTxt.setText("0.00");
                                    billAmountTxt.setText("0.00");
                                    patientNameTxt.setText(null);
                                    patientNumberTxt.setText(null);
                                    exemptionNumberTxt.setText(null);
                                    exemptionChkbx.setSelected(false);
                                    for (int m = 0; m < billTable.getRowCount(); m++) {
                                        for (int j = 0; j < billTable.getColumnCount(); j++) {
                                            billTable.setValueAt(null, m, j);
                                        }
                                    }
                                    lessDiscountedAmountTxt.setText("0.00");
                                } else {
                                    javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all the details including the patient name.");
                                }

                            } catch (java.sql.SQLException sq) {
                                sq.printStackTrace();
                                try {

                                    connectDB.rollback();
                                } catch (java.sql.SQLException sql) {
                                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                                }
                                System.out.println(sq.getMessage());
                                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                            }
                        }

                        ////}
                        rset.close();
                        pstmt.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "ERROR: Can't close the cash collection form : Details : " + ex.getMessage());
                    //Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ((Double.parseDouble(amountPaidTxt.getText()) < Double.parseDouble(billAmountTxt.getText()) || Double.parseDouble(waivedAmountTxt.getText()) > 0.00) && (patientNumberTxt.getText().toCharArray().length > 0)) {

                receiptNumberTxt.setText(null);
                System.out.println("Printing the receipt.");
                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(status) FROM ac_shifts WHERE status ilike 'running' AND user_name = current_user");
                    java.sql.ResultSet rset = pstmt.executeQuery();
                    int i = 0;
                    while (rset.next()) {
                        i = rset.getInt(1);
                    }
                    if (i < 1) {
                        com.afrisoftech.hospital.HospitalMain.saccopn.remove(this);
                        //  thisgetParent().remove(this);
                        javax.swing.JOptionPane.showMessageDialog(this, "You MUST open a new shift in order to continue.\n Please close Front End Operations and open afresh.");
                    } else {
                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                        System.out.println(datenowSql1.toString());

                        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                        System.out.println(datenowSql.toString());
                        int k = 1;
                        String receiptNo1 = null;

                        postSaleDataBtnActionPerformed(evt);

                        System.out.println("PROCESS RECEIPT BOOLEAN [" + processReceipt + "]");

                        if (processReceipt) {

                            try {

//try {
                                connectDB.setAutoCommit(false);

                                System.out.println("This is the receipt no " + receiptNo2);

                                //receiptNo1 = jTextField1113.getText();
                                if (receiptNumberTxt.getText().toCharArray().length > 0) {
                                    String receiptNo = jTextField1.getText();
                                    String rct = null;
                                    java.sql.Statement ps112 = connectDB.createStatement();
                                    java.sql.ResultSet rst112 = ps112.executeQuery("select rct_format from receipt_pref");
                                    while (rst112.next()) {
                                        rct = rst112.getObject(1).toString();
                                    }
                                    if (rct.equalsIgnoreCase("Pdf")) {
                                        com.afrisoftech.reports.ReceiptsPdf policy = new com.afrisoftech.reports.ReceiptsPdf();
                                        policy.ReceiptsPdf(connectDB, receiptNo2);
                                    } else {
                                        com.afrisoftech.txtreports.GokReceiptsTxt policy = new com.afrisoftech.txtreports.GokReceiptsTxt(connectDB, patientNameTxt.getText(), billAmountTxt.getText(), receiptNo2, this.paymentModeCmbx.getSelectedItem().toString(), amountPaidTxt.getText(), changeTxt.getText(), shiftNoTxt.getText(), unitNumberTxt.getText());

                                    }
                                    // jTextField1211.setText("0.00");
                                    lessDiscountedAmountTxt.setText("0.00");
                                    amountPaidTxt.setText("0.00");
                                    changeTxt.setText("0.00");
                                    billAmountTxt.setText("0.00");
                                    patientNameTxt.setText(null);
                                    patientNumberTxt.setText(null);
                                    exemptionNumberTxt.setText(null);
                                    exemptionChkbx.setSelected(false);
                                    for (int m = 0; m < billTable.getRowCount(); m++) {
                                        for (int j = 0; j < billTable.getColumnCount(); j++) {
                                            billTable.setValueAt(null, m, j);
                                        }
                                    }
                                    lessDiscountedAmountTxt.setText("0.00");
                                } else {
                                    javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all the details including the patient name.");
                                }

                            } catch (java.sql.SQLException sq) {
                                sq.printStackTrace();
                                try {

                                    connectDB.rollback();
                                } catch (java.sql.SQLException sql) {
                                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                                }
                                System.out.println(sq.getMessage());
                                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                            }
                        }

                        ////}
                        rset.close();
                        pstmt.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "ERROR: Can't close the cash collection form : Details : " + ex.getMessage());
                    //Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
                }
//            javax.swing.JOptionPane.showMessageDialog(this, "Please double check your entries.\nThere must be a selected patient file. \nThe cash indicated should be equal or more than the bill raised. \n"
//                    + "You must also indicate the amount that the patient has given you. Check to ensure the field for \"Money Paid\" is correct. \n"
//                    + "Click on the table on the row which contains the bill which the patient cannot pay and use \"Remove Row\" button to remove it.");
            }

        }
        // Add your handling code here:

        // Add your handling code here:
    }//GEN-LAST:event_generateReceiptBtnActionPerformed
    private void searchButton111Clicked() {

        System.out.println("Showing dialog");

        jSearchDialog2.dispose();
        java.awt.Point point = this.jTextField3111.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);
        jSearchDialog2.setVisible(true);

    }
    private void patientSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientSearchTableMouseClicked

        com.afrisoftech.hospital.HospitalMain.mobileTxID = null;
        com.afrisoftech.hospital.HospitalMain.mobileTelephone = null;
        mobilepayTxNoTxt.setText(null);

        patientNumberTxt.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 0).toString());
        patientNameTxt.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 1).toString());
        unitNumberTxt.setText(patientSearchTable.getValueAt(patientSearchTable.getSelectedRow(), 2).toString());
        payerMobileTelephoneNumberTxt.setText("254-7XX-XXXXXX");
        for (int s = 0; s < billTable.getRowCount(); s++) {
            for (int r = 0; r < billTable.getColumnCount(); r++) {
                billTable.getModel().setValueAt(null, s, r);
            }

        }

        for (int i = addedMobilepayTxtTable.getRowCount() - 1; i >= 0; i--) {
            javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) addedMobilepayTxtTable.getModel();
            defTableModel.removeRow(i);
        }
        totalTokensAmountTxt.setText("0.00");
        int age = 6;

        try {

            java.sql.Statement stx1 = connectDB.createStatement();
            java.sql.ResultSet rsx1 = stx1.executeQuery("SELECT DISTINCT payment,age,oid FROM hp_patient_visit WHERE patient_no ILIKE '" + patientNumberTxt.getText() + "' order by oid desc limit 1 ");
            while (rsx1.next()) {
                jTextField1.setText(rsx1.getString(1));
                age = rsx1.getInt(2);
                ;
            }

            stx1.close();
            rsx1.close();

        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
        }

        if (age < 5) {
            exemptionChkbx.setEnabled(true);
            exemptionNumberTxt.setEditable(true);
        }

        this.populateTable(this.patientNumberTxt.getText());
        billAmountTxt.setText(java.lang.String.valueOf(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3)));
        totalBilledAmountTxt.setText(java.lang.String.valueOf(com.afrisoftech.lib.TableColumnTotal.getGrossTableColumnTotal(billTable, 3)));
        waivedAmountTxt.setText(java.lang.String.valueOf(com.afrisoftech.lib.TableColumnTotal.getGrossTableColumnTotal(billTable, 3) - com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3)));
        amountPaidTxt.setText(billAmountTxt.getText());
        if (com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3) >= 0) {
            generateReceiptBtn.setEnabled(true);
            this.postSaleDataBtn.setEnabled(true);
            this.reprintReceiptBtn.setEnabled(true);
        } else {
            generateReceiptBtn.setEnabled(false);
            this.postSaleDataBtn.setEnabled(false);
            //this.reprintReceiptBtn.setEnabled(true);
        }
        patientSearchDialog.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_patientSearchTableMouseClicked

    private void searchPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientButtonActionPerformed
        searchButton212Clicked();        // Add your handling code here:
    }//GEN-LAST:event_searchPatientButtonActionPerformed
    private void searchButton212Clicked() {

        com.afrisoftech.hospital.HospitalMain.mobileTxID = null;
        com.afrisoftech.hospital.HospitalMain.mobileTelephone = null;

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.patientNameTxt.getLocationOnScreen();

        patientSearchDialog.setSize(700, 200);

        patientSearchDialog.setLocation(point);

        patientSearchDialog.setVisible(true);
    }
    private void jButton521ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton521ActionPerformed
        this.patientSearchDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton521ActionPerformed

    private void patientSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_patientSearchTxtCaretUpdate
        if (this.clinicChbx.isSelected()) {
            if (patientSearchTxt.getCaretPosition() > 3) {
                patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT patient_no, p_name, booking_no from pb_bookings  where patient_no ILIKE '%" + patientSearchTxt.getText() + "%' or p_name  ILIKE '%" + patientSearchTxt.getText() + "%' or booking_no  ILIKE '%" + patientSearchTxt.getText() + "%' ORDER BY 2"));
            }
        } else if (this.mchfpChbx.isSelected()) {
            if (patientSearchTxt.getCaretPosition() > 3) {
                patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT anc as anc_no, first_name||' '||middle_name||' '||last_name, telephone from rh.mother_details where anc ILIKE '%" + patientSearchTxt.getText() + "%' or first_name||' '||middle_name||' '||last_name  ILIKE '%" + patientSearchTxt.getText() + "%' "
                        + " UNION SELECT DISTINCT fp_clinic_no as anc_no, full_name, telephone_no from rh.fp_services_register where fp_clinic_no ILIKE '%" + patientSearchTxt.getText() + "%' or full_name  ILIKE '%" + patientSearchTxt.getText() + "%'"
                        + " UNION SELECT DISTINCT pnc_no as anc_no, full_name, telephone from rh.post_natal_follow_up_register where pnc_no ILIKE '%" + patientSearchTxt.getText() + "%' or full_name  ILIKE '%" + patientSearchTxt.getText() + "%' ORDER BY 2"));
            }
        } else {
            if (this.searchNoChbx.isSelected()) {
                if (patientSearchTxt.getCaretPosition() > 3) {

                    System.out.println("SELECT "
                            + " DISTINCT patient_no,(second_name||' '||first_name) as patient_name, patient_race as unit_number FROM hp_patient_register "
                            + "WHERE patient_no ILIKE '%" + patientSearchTxt.getText() + "%' or patient_race ILIKE '%" + patientSearchTxt.getText() + "%' AND last_visit >= (current_date - " + grace_period + ") "
                            + " UNION ALL SELECT "
                            + " DISTINCT patient_no, patient_name, '' as unit_number from patient_bill WHERE patient_no ILIKE '" + patientSearchTxt.getText() + "%' "
                            + " AND date_prescribed >= (current_date - 3)"
                            + " UNION ALL SELECT patient_no,patient_name, sub_chief as unit_number FROM hp_admission WHERE "
                            + " patient_no ILIKE '%" + patientSearchTxt.getText() + "%' or sub_chief ilike '%" + patientSearchTxt.getText() + "%'  AND  "
                            + " discharge_date::DATE = ('now'::text)::date ORDER BY patient_no");
//            } else {

                    patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT "
                            + " DISTINCT patient_no,(second_name||' '||first_name) as patient_name, patient_race as unit_number FROM hp_patient_register "
                            + "WHERE (patient_no ILIKE '%" + patientSearchTxt.getText() + "%' or patient_race ILIKE '%" + patientSearchTxt.getText() + "%' ) AND last_visit >= (current_date - " + grace_period + ") "
                            + " UNION ALL SELECT "
                            + " DISTINCT patient_no, patient_name, '' as unit_number from patient_bill WHERE patient_no ILIKE '" + patientSearchTxt.getText() + "%' "
                            + " AND date_prescribed >= (current_date - 3)  and  patient_no ILIKE 'WK%' "
                            + " UNION ALL SELECT patient_no,patient_name, sub_chief as unit_number FROM hp_admission WHERE "
                            + " patient_no ILIKE '%" + patientSearchTxt.getText() + "%' or sub_chief ilike '%" + patientSearchTxt.getText() + "%'  AND  "
                            + " discharge_date::DATE = ('now'::text)::date ORDER BY patient_no"));

                    patientSearchTable.setShowHorizontalLines(false);

                    patientSearchScrollPane.setViewportView(patientSearchTable);

                }

            } else {

                if (patientSearchTxt.getCaretPosition() > 2) {

                    System.out.println("SELECT "
                            + "distinct patient_no, (second_name||' '||first_name)  as patient_name, patient_race as unit_number FROM hp_patient_register"
                            + " WHERE second_name||' '||first_name ILIKE '%" + patientSearchTxt.getText() + "%' and "
                            + "last_visit >= (current_date - 3) UNION ALL select DISTINCT patient_no,patient_name, '' as unit_number from hp_pharmacy "
                            + "where date_prescribed >= (current_date - " + grace_period + ") and patient_no ilike 'wk%' and patient_name "
                            + "ILIKE '%" + patientSearchTxt.getText() + "%'  AND paid = false UNION ALL SELECT DISTINCT patient_no,"
                            + "patient_name, sub_chief as unit_number FROM hp_admission WHERE patient_name ILIKE '%" + patientSearchTxt.getText() + "%' or sub_chief ilike '%" + patientSearchTxt.getText() + "%'  "
                            + "AND discharge_date::DATE = now() order by patient_name");
//            } else {
                    // try {
                    patientSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT "
                            + "distinct patient_no, (second_name||' '||first_name)  as patient_name, patient_race as unit_number FROM hp_patient_register"
                            + " WHERE second_name||' '||first_name ILIKE '%" + patientSearchTxt.getText() + "%' and "
                            + "last_visit >= (current_date - " + grace_period + ") UNION ALL select DISTINCT patient_no,patient_name, '' as unit_number from hp_pharmacy "
                            + "where date_prescribed >= (current_date - 3) and patient_no ilike 'wk%' and patient_name "
                            + "ILIKE '%" + patientSearchTxt.getText() + "%'  AND paid = false UNION ALL SELECT DISTINCT patient_no,"
                            + "patient_name, sub_chief as unit_number FROM hp_admission WHERE patient_name ILIKE '%" + patientSearchTxt.getText() + "%' or sub_chief ilike '%" + patientSearchTxt.getText() + "%'  "
                            + "AND discharge_date::DATE = now() order by patient_name"));

                    patientSearchTable.setShowHorizontalLines(false);
                    patientSearchScrollPane.setViewportView(patientSearchTable);

                }
            }
        }
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < patientSearchTable.getColumnCount(); i++) {
            System.out.println("Resizing table ...");
            column = this.patientSearchTable.getColumnModel().getColumn(i);
            if (i == 1) {

                column.setPreferredWidth(500); // item description column is bigger
            } else {
                column.setPreferredWidth(200);
            }
        }
        patientSearchScrollPane.setViewportView(patientSearchTable);
// Add your handling code here:
    }//GEN-LAST:event_patientSearchTxtCaretUpdate

    public class ComboTextField extends javax.swing.JTextField implements javax.swing.ComboBoxEditor {

        public ComboTextField() {

            initComponents();

        }

        void initComponents() {

            // oldComboBox1311Model = jComboBox1311.getModel();
            // comboTextField = new javax.swing.JTextField();
            comboTextField.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent e) {

                    if (e.getID() == java.awt.event.KeyEvent.VK_BACK_SPACE) {

                        changeComboBoxModel(comboTextField.getText());

                        System.out.println(comboTextField.getText() + " Pressed Key " + e.getKeyText(e.getKeyCode()));
                    }
                }
            });

        }

        public java.awt.Component getEditorComponent() {

            return comboTextField;

        }

        public Object getItem() {

            return comboTextField.getText();

        }

        public void setItem(Object obj) {
            //    comboTextField.setText(jComboBox1311.getSelectedItem().toString());
        }

        public void changeComboBoxModel(java.lang.String typedLetters) {

            javax.swing.DefaultComboBoxModel newComboBox1311Model = new javax.swing.DefaultComboBoxModel();

            System.out.println("ComboBox Item No. " + oldComboBox1311Model.getSize());

            for (int i = 0; i < oldComboBox1311Model.getSize(); i++) {

                if (oldComboBox1311Model.getElementAt(i).toString().startsWith(typedLetters)) {

                    newComboBox1311Model.addElement(oldComboBox1311Model.getElementAt(i));

                    //                    jComboBox1311.setModel(newComboBox1311Model);
                }

            }

        }
        //      }
    }
    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        billTable.setValueAt(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0), billTable.getSelectedRow(), 0);
        billTable.setValueAt(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1), billTable.getSelectedRow(), 2);
        billTable.setValueAt(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 2), billTable.getSelectedRow(), 4);
        billTable.setValueAt(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 3), billTable.getSelectedRow(), 6);
        billTable.setValueAt(1, billTable.getSelectedRow(), 1);

        billTable.setValueAt(false, billTable.getSelectedRow(), 5);

        jSearchDialog1.dispose();

        float qty = java.lang.Float.parseFloat(billTable.getValueAt(billTable.getSelectedRow(), 1).toString());
        float price = java.lang.Float.parseFloat(billTable.getValueAt(billTable.getSelectedRow(), 2).toString());
        float total = qty * price;
        billTable.setValueAt(total, billTable.getSelectedRow(), 3);
        double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3);
        billAmountTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(totalSum));
        billAmountTxt.setText(java.lang.String.valueOf(totalSum));
        double totals = java.lang.Double.parseDouble(billAmountTxt.getText());
        totalBilledAmountTxt.setText(String.valueOf(com.afrisoftech.lib.TableColumnTotal.getGrossTableColumnTotal(billTable, 3)));
        double waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());
        lessDiscountedAmountTxt.setText(java.lang.String.valueOf(totals - waiver));
        amountPaidTxt.setText(java.lang.String.valueOf(totals - waiver));

        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked
    private void cmbox221MouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = jScrollPane211.getLocationOnScreen();
        jSearchDialog1.setSize(700, 200);
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);

    }
    private void jButton91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton91ActionPerformed
        this.jSearchDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton91ActionPerformed

    private void jTextField112CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField112CaretUpdate

        /*
         * if (jTable3.isEditing()) { jTable3.getCellEditor().stopCellEditing();
         * }
         */
        searchButton2122Clicked();
        // Add your handling code here:
    }//GEN-LAST:event_jTextField112CaretUpdate
    private void searchButton2122Clicked() {

        try {
            String patNationality = "";
            String patCateg = "";
            java.sql.Statement stmtTable11 = connectDB.createStatement();

            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("SELECT pat_nationality,category FROM hp_patient_register WHERE patient_no = '" + patientNumberTxt.getText() + "'");

            while (rsetTable11.next()) {
                patNationality = rsetTable11.getString(1);
                patCateg = rsetTable11.getString(2);
            }
            if (this.jCheckBox4.isSelected()) {

                if (jTextField112.getCaretPosition() < 1) {

                    System.out.println("Nothing");
                } else {
                    // try {
                    if (patNationality.startsWith("NON")) {
                        jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                                + "SELECT DINSTINCT service_type,other_prices::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE code ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND (  direct_cash_payment = true) ORDER BY 1"));
                    } else {
                        if (patCateg.startsWith("PRI")) {
                            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT service_type,anaesthetist_rate::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE code ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND ( direct_cash_payment = true) ORDER BY 1"));
                        } else {
                            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT service_type,rate::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE code ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND (  direct_cash_payment = true) ORDER BY 1"));// UNION ALL select distinct product,selling_price,gl_code,product_id,department FROM st_stock_prices WHERE product_id ILIKE '" + jTextField112.getText() + "%' and gl_code = '" + jTextField7.getText() + "' ORDER BY 1 LIMIT 30"));
                        }
                    }
                    /*
                     * {
                     * boolean[] canEdit = new boolean[]{ false, false, false,
                     * false, false };
                     *
                     *
                     * boolean isCellEditable(int rowIndex, int columnIndex) {
                     * return canEdit [columnIndex]; } });
                     */
                    jSearchScrollPane1.setViewportView(jSearchTable1);
                    System.out.println("Cannot sort out");

                }

            } else {
                if (jTextField112.getCaretPosition() < 3) {

                    System.out.println("Nothing");
                } else {

                    if (patNationality.startsWith("NON")) {
                        jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT service_type,other_prices::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND (  direct_cash_payment = true) ORDER BY 1"));
                    } else {
                        if (patCateg.startsWith("PRI")) {
                            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT service_type,anaesthetist_rate::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND (  direct_cash_payment = true) ORDER BY 1"));
                        } else {
                            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT service_type,rate::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%' and status = 'Active' AND (  direct_cash_payment = true) ORDER BY 1"));// UNION ALL select distinct product,selling_price,gl_code,product_id,department FROM st_stock_prices WHERE product_id ILIKE '" + jTextField112.getText() + "%' and gl_code = '" + jTextField7.getText() + "' ORDER BY 1 LIMIT 30"));
                        }
                    }
                    //jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,rate::numeric,gl_account,code,main_service FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%'"));// UNION ALL select product,selling_price,gl_code,product_id,department  FROM st_stock_prices WHERE product ILIKE '%" + jTextField112.getText() + "%'  order by service_type  LIMIT 30"));

                    jSearchScrollPane1.setViewportView(jSearchTable1);
                    System.out.println("Cannot sort out");

                }

            }
            int i = jSearchTable1.getRowCount();
            int k = jSearchTable1.getColumnCount();

            jSearchTable1.editCellAt(-1, -1);

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }
        //jSearchTable1.setEnabled(false);
    }

    private void populateTable(java.lang.String patient_no) {
        int j = 0;
        int i = 0;

        try {
            java.sql.Statement stmtTable11 = connectDB.createStatement();

            // java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("SELECT count(patient_no) FROM patient_bill WHERE "
            //         + "patient_no = '" + patient_no + "'");
            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select count(*) from funsoft_outpatient_bills('" + datePicker1.getDate() + "', '" + patientNumberTxt.getText() + "')");
            while (rsetTable11.next()) {
                j = rsetTable11.getInt(1);
            }

            if (j > 0) {
                double waivexe = 0;
                java.sql.Statement stmtTable1c = connectDB.createStatement();

                java.sql.ResultSet rsetTable1c = stmtTable1c.executeQuery(""
                        + "SELECT DISTINCT visit_id,revenue_code,trans_date,sum(amount)*-1 "
                        + "FROM hp_patient_billing "
                        + "WHERE patient_no = '" + patient_no + "' AND amount < 0 and "
                        + "(revenue_code ILIKE 'exe%' OR revenue_code ILIKE 'wai%') AND trans_date > "
                        + "(current_date-2) GROUP BY visit_id,revenue_code,trans_date ORDER BY visit_id DESC LIMIT 1");

                while (rsetTable1c.next()) {
                    this.exemptionNumberTxt.setText(rsetTable1c.getString(1));
                    if (rsetTable1c.getString(2).startsWith("EXE")) {
                        exemptionChkbx.setSelected(true);
                        jRadioButton1.setSelected(true);
                    } else {
                        waivedAmountTxt.setText(rsetTable1c.getString(4));
                        jRadioButton1.setSelected(true);
                    }
                    waivexe = rsetTable1c.getDouble(4);
                }
                java.sql.Statement stmtTable1 = connectDB.createStatement();

                //  java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery(""
                //          + "SELECT description, quantity,price,amount,gl_code,true,item_code  FROM patient_bill "
                //          + "WHERE patient_no = '" + patient_no + "'");
                java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select * from funsoft_outpatient_bills('" + datePicker1.getDate() + "', '" + patientNumberTxt.getText() + "')");

                while (rsetTable1.next()) {

                    System.out.println("Working at table row " + i);
                    billTable.setValueAt(rsetTable1.getObject(1), i, 0);
                    billTable.setValueAt(rsetTable1.getObject(2), i, 1);
                    billTable.setValueAt(rsetTable1.getObject(3), i, 2);
                    billTable.setValueAt(rsetTable1.getObject(4), i, 3);
                    billTable.setValueAt(rsetTable1.getObject(5), i, 4);
                    billTable.setValueAt(true, i, 5);
                    billTable.setValueAt(rsetTable1.getObject(8), i, 6);
                    billTable.setValueAt(rsetTable1.getObject(9), i, 7);

                    i++;

                }
                billAmountTxt.setText(String.valueOf(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3)));

                java.sql.Statement stmtTable111 = connectDB.createStatement();

                java.sql.ResultSet rsetTable111 = stmtTable111.executeQuery("SELECT sum(amount)+'" + waivexe + "' FROM patient_bill WHERE patient_no = '" + patient_no + "'");//AND paid = false UNION SELECT sum(amount) FROM hp_patient_billing WHERE patient_name = '"+patient_no+"' AND paid = false");

                while (rsetTable111.next()) {
                    billAmountTxt.setText(String.valueOf(rsetTable111.getDouble(1)));
                }
                rsetTable111.close();
                stmtTable111.close();
                rsetTable1.close();
                stmtTable1.close();
                rsetTable1c.close();
                stmtTable1c.close();

            }
            rsetTable11.close();
            stmtTable11.close();

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

    }
    private void reprintReceiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reprintReceiptBtnActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());

        try {
            connectDB.setAutoCommit(false);

            /*
             * java.sql.Statement ps1 = connectDB.createStatement();
             * java.sql.ResultSet rst1 = ps1.executeQuery("select
             * currval('receipt_no_seq')"); while (rst1.next()){
             */
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("");

            receiptNo1 = receiptNumberTxt.getText();
            // }
            //            CashReceiptsPdf policy = new CashReceiptsPdf();
            //            policy.CashReceiptsPdf(connectDB, jTextField91.getText().toUpperCase(),jTextField311.getText(),receiptNo1,this.jComboBox41.getSelectedItem().toString(),jTextField121.getText(),jTextField131.getText());

            String clientReceipt = null;

            java.sql.PreparedStatement pstmtReprint = connectDB.prepareStatement("SELECT dealer FROM ac_cash_collection WHERE receipt_no = ?");

            pstmtReprint.setString(1, receiptNumberTxt.getText());
            java.sql.ResultSet rsetReprint = pstmtReprint.executeQuery();

            while (rsetReprint.next()) {
                clientReceipt = rsetReprint.getString(1);
            }
            //String receiptNo = jTextField1.getText();
            String rct = null;
            java.sql.Statement ps112 = connectDB.createStatement();
            java.sql.ResultSet rst112 = ps112.executeQuery("select rct_format from receipt_pref");
            while (rst112.next()) {
                rct = rst112.getObject(1).toString();
            }
            if (rct.equalsIgnoreCase("Pdf")) {
                if (receiptNo1.length() > 1) {
                    com.afrisoftech.reports.ReceiptsPdf policy = new com.afrisoftech.reports.ReceiptsPdf();
                    policy.ReceiptsPdf(connectDB, receiptNo1);
                }
            } else {
                if (receiptNo1.length() > 1) {
                    com.afrisoftech.txtreports.GokReceiptsRTxt policy = new com.afrisoftech.txtreports.GokReceiptsRTxt(connectDB, clientReceipt, billAmountTxt.getText(), receiptNo1, this.paymentModeCmbx.getSelectedItem().toString(), amountPaidTxt.getText(), changeTxt.getText(), shiftNoTxt.getText());
                }
            }
            rsetReprint.close();
            pstmtReprint.close();

        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }          // Add your handling code here:
    }//GEN-LAST:event_reprintReceiptBtnActionPerformed

    private void billTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billTableMouseClicked

        if (billTable.getSelectedColumn() == 0) {

            this.cmbox221MouseClicked();
        }

        //jButton111.setEnabled(true);
        generateReceiptBtn.setEnabled(true);
        reprintReceiptBtn.setEnabled(false);
        jTextField112.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_billTableMouseClicked

    private void billTableComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_billTableComponentShown
        // Add your handling code here:
    }//GEN-LAST:event_billTableComponentShown

    private void removeRowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowBtnActionPerformed

        int rows2Delete = billTable.getSelectedRowCount();

        int[] selectedRows = billTable.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

        } else {

            if (rows2Delete > 1) {

                for (int i = 0; i
                        < selectedRows.length; i++) {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) billTable.getModel();

                    defTableModel.removeRow(selectedRows[i]);

                }

            } else {

                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) billTable.getModel();

                defTableModel.removeRow(billTable.getSelectedRow());
            }

        }

        totalBilledAmountTxt.setText(String.valueOf(com.afrisoftech.lib.TableColumnTotal.getGrossTableColumnTotal(billTable, 3)));

        double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3);
        billAmountTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(totalSum));
        billAmountTxt.setText(java.lang.String.valueOf(totalSum));
        double totals = java.lang.Double.parseDouble(billAmountTxt.getText());
        double waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());
        lessDiscountedAmountTxt.setText(java.lang.String.valueOf(totals - waiver));
        amountPaidTxt.setText(java.lang.String.valueOf(totals - waiver));

        // Add your handling code here:
    }//GEN-LAST:event_removeRowBtnActionPerformed

    private void clearFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFormBtnActionPerformed

        exemptionChkbx.setSelected(false);
        patientNameTxt.setText("");
        payerMobileTelephoneNumberTxt.setText("254-7  -      ");
        for (int k = 0; k
                < billTable.getRowCount(); k++) {
            for (int r = 0; r
                    < billTable.getColumnCount(); r++) {
                billTable.getModel().setValueAt(null, k, r);
            }

        }
        billAmountTxt.setText("0.00");
        ///       jTextField1211.setText("0.00");
        amountPaidTxt.setText("0.00");
        //       jTextField12.setText("0.00");
        changeTxt.setText("0.00");
        // Add your handling code here:
    }//GEN-LAST:event_clearFormBtnActionPerformed

    private void closePosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closePosBtnActionPerformed
        this.dispose();       // Add your handling code here:
    }//GEN-LAST:event_closePosBtnActionPerformed

    private void billTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billTableKeyReleased
        if (billTable.getModel().getValueAt(billTable.getSelectedRow(), 1) != null) {
            if (billTable.getSelectedColumn() == billTable.getSelectedColumn()) {
                float qty = java.lang.Float.parseFloat(billTable.getValueAt(billTable.getSelectedRow(), 1).toString());
                float price = java.lang.Float.parseFloat(billTable.getValueAt(billTable.getSelectedRow(), 2).toString());
                float total = qty * price;
                billTable.setValueAt(total, billTable.getSelectedRow(), 3);
                double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(billTable, 3);
                totalBilledAmountTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getGrossTableColumnTotal(billTable, 3)));
                billAmountTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(totalSum));
                billAmountTxt.setText(java.lang.String.valueOf(totalSum));
                amountPaidTxt.setText(java.lang.String.valueOf(totalSum));
            }

        }

        // Add your handling code here:
    }//GEN-LAST:event_billTableKeyReleased
    private void cmbox21ActionPerformed(java.awt.event.ActionEvent evt) {
        java.lang.Object selectedGuaran = cmbox21.getSelectedItem();
        int i = billTable.getSelectedRow();

        if (selectedGuaran != null) {

            try {
                java.sql.Statement pstmt = connectDB.createStatement();
                java.sql.ResultSet rs = pstmt.executeQuery("select rate::numeric as rate,gl_account from pb_operating_parameters where service_type = '" + selectedGuaran + "' UNION ALL select sp.selling_price,sp.gl_code from st_stock_prices sp where sp.product = '" + selectedGuaran + "'");
                while (rs.next()) {
                    billTable.setValueAt(rs.getObject(1), i, 2);
                    billTable.setValueAt(rs.getObject(2), i, 4);
                }
                rs.close();
                pstmt.close();

            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }

        }

    }
    private void postSaleDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postSaleDataBtnActionPerformed

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(status) FROM ac_shifts WHERE status ilike 'running' AND user_name = current_user");
            java.sql.ResultSet rset = pstmt.executeQuery();
            int i = 0;
            while (rset.next()) {
                i = rset.getInt(1);
            }
            if (i < 1) {
                com.afrisoftech.hospital.HospitalMain.saccopn.remove(this);
                //  thisgetParent().remove(this);
                javax.swing.JOptionPane.showMessageDialog(this, "You MUST open a new shift in order to continue.\n Please close Front End Operations and open afresh.");
            } else {
                if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
                    if (mobilepayTxNoTxt.getText().length() > 0) {
                        if (addedMobilepayTxtTable.getRowCount() > 1) {//If several paymodes
                            //------------------------------------------------------
                            //for (int p = 0; p < addedMobilepayTxtTable.getRowCount(); p++) {

                            if (Double.valueOf(totalTokensAmountTxt.getText().replace(",", "")) >= Double.parseDouble(billAmountTxt.getText())) {
                                boolean tokenAuthentic = true;
                                for (int p = 0; p < addedMobilepayTxtTable.getRowCount(); p++) {
                                    if (!com.afrisoftech.lib.MobilePayments.isTokenAuthentic(connectDB, addedMobilepayTxtTable.getValueAt(p, 0).toString())) {
                                        tokenAuthentic = false;

                                        String phoneNumber = "254714433693";

                                        //    if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {
                                        biz.systempartners.claims.SendSMS.SendSMS(phoneNumber, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + addedMobilepayTxtTable.getValueAt(p, 0).toString() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());

                                        String phoneNumberAdmin = "254721425877";

                                        if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {

                                            biz.systempartners.claims.SendSMS.SendSMS(phoneNumberAdmin, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + addedMobilepayTxtTable.getValueAt(p, 0).toString() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());

                                        }
                                    }
                                }

                                if (tokenAuthentic) {

                                    this.saveData();

                                } else {

//                                    String phoneNumber = "254714433693";
//
//                                    //    if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {
//                                    biz.systempartners.claims.SendSMS.SendSMS(phoneNumber, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + mobilepayTxNoTxt.getText() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());
//
//                                    String phoneNumberAdmin = "254721425877";
//
//                                    if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {
//
//                                        biz.systempartners.claims.SendSMS.SendSMS(phoneNumberAdmin, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + mobilepayTxNoTxt.getText() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());
//
//                                    }
                                }
                            } else {
                                javax.swing.JOptionPane.showMessageDialog(this, "The token amount is exhausted!");
                            }

                            //-----------------------------------------------------
                        } else {
                            if (com.afrisoftech.lib.MobilePayments.getTokenValue(connectDB, mobilepayTxNoTxt.getText()) >= Double.parseDouble(billAmountTxt.getText())) {
                                if (com.afrisoftech.lib.MobilePayments.isTokenAuthentic(connectDB, mobilepayTxNoTxt.getText())) {

                                    this.saveData();

                                } else {

                                    String phoneNumber = "254714433693";

                                    //    if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {
                                    biz.systempartners.claims.SendSMS.SendSMS(phoneNumber, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + mobilepayTxNoTxt.getText() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());

                                    String phoneNumberAdmin = "254721425877";

                                    if (com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase().contains("COAST")) {

                                        biz.systempartners.claims.SendSMS.SendSMS(phoneNumberAdmin, "Funsoft I-HMIS Messaging:\n\n" + "This is an alert for suspect token! Patient No.:" + patientNumberTxt.getText() + " Name: " + patientNameTxt.getText().toUpperCase() + "Token: " + mobilepayTxNoTxt.getText() + "\n\nFrom:\n" + com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase());

                                    }
                                }
                            } else {
                                javax.swing.JOptionPane.showMessageDialog(this, "The token amount is exhausted!");
                            }
                        }
                    } else {
                        this.saveData();
                    }
                } else if (!paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
                    this.saveData();
                }
                //      dispatchThread = new DispatchThread();

                //     dispatchThread.start();
                if (processReceipt) {
                    postSaleDataBtn.setEnabled(false);

                    generateReceiptBtn.setEnabled(false);
                }
            }
            rset.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR: Can't close the cash collection form : Details : " + ex.getMessage());
            //Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Add your handling code here:
    }//GEN-LAST:event_postSaleDataBtnActionPerformed

    private void jComboBox111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox111ActionPerformed
        postSaleDataBtn.setEnabled(true);
        generateReceiptBtn.setEnabled(true);
        reprintReceiptBtn.setEnabled(true);

        try {
            java.sql.Statement ps11 = connectDB.createStatement();
            java.sql.ResultSet rst11 = ps11.executeQuery("select code,sub_code from pb_activity WHERE activity ilike '" + jComboBox111.getSelectedItem() + "'");
            while (rst11.next()) {

                jTextField7.setText(rst11.getString(1));
                //jTextField22.setText(rst11.getString(2));

            }
            ps11.close();
            ps11.close();
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("Select not successful");
        }

        // Add your handling code here:
    }//GEN-LAST:event_jComboBox111ActionPerformed

    private void paymentModeCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentModeCmbxActionPerformed
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select code from pb_activity where activity_category   ILIKE 'RC'");
            while (rset1.next()) {
                glAccountTxt.setText(rset1.getObject(1).toString());
            }

            stmt1.close();
            rset1.close();
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();

        }

        if (paymentModeCmbx.getSelectedItem().equals("Credit Card")) {
            // jDialog3.setVisible(true);
            // int resOptt =  javax.swing.JOptionPane.showOptionDialog(this, "Select Account Type", "Select Account Type Here", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE, null, new java.lang.String[]{"Credit Card", "Scheme", "Cancel"}, "");

            //  if (resOptt == javax.swing.JOptionPane.YES_OPTION) {
            jPanel32.setVisible(false);
            // jPanel41.setVisible(true);
            jPanel311.setVisible(true);
            jTextField42.setText("");
            jTextField51.setText("");
            jTextField22.setText("");
        } else {
            if (paymentModeCmbx.getSelectedItem().equals("Cheque")) {
                //                jPanel41.setVisible(false);
                jPanel311.setVisible(false);
                jPanel32.setVisible(true);
                jTextField212.setText("");
                jTextField521.setText("");
                jTextField411.setText("");
            } else {

                jPanel311.setVisible(false);
                jPanel32.setVisible(false);
                jTextField212.setText("");
                jTextField521.setText("");
                jTextField411.setText("");
                jTextField42.setText("");
                jTextField51.setText("");
                jTextField22.setText("");
            }

        }

        postSaleDataBtn.setEnabled(true);
        generateReceiptBtn.setEnabled(true);
        reprintReceiptBtn.setEnabled(true);

        if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
            javax.swing.JOptionPane.showMessageDialog(this, "You must enter a valid client Mpesa telephone number in the format : 254-7xx-xxxxxx on the Bill Payer Telephone No field");
        }
        // Add your handling code here:
    }//GEN-LAST:event_paymentModeCmbxActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

        System.setProperty("billpmnt", "closed");

        System.out.println("Form closed");

        System.out.println(System.getProperty("billpmnt"));// Add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

        System.setProperty("billpmnt", "closed");

        System.out.println("Form closed");
        // Add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void walkINChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walkINChkbxActionPerformed
        if (walkINChkbx.isSelected()) {
            patientNameTxt.setEditable(true);
            searchPatientButton.setEnabled(false);
            patientNumberTxt.setText("");
        } else {

        }
// TODO add your handling code here:
    }//GEN-LAST:event_walkINChkbxActionPerformed

    private void searchNoChbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNoChbxActionPerformed
        patientNameTxt.setEditable(false);
        searchPatientButton.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_searchNoChbxActionPerformed

    private void searchNameChbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNameChbxActionPerformed
        patientNameTxt.setEditable(false);
        searchPatientButton.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_searchNameChbxActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void patientSearchTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientSearchTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_patientSearchTableMouseEntered

    private void jSearchTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchTable1KeyReleased
        // for (int i = 0; i  < jTable111.getRowCount(); i++) {

        if (jSearchTable1.editCellAt(jSearchTable1.getSelectedRow(), jSearchTable1.getSelectedColumn())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Be warned  you cannot alter date".toUpperCase(), "Warning Message", javax.swing.JOptionPane.WARNING_MESSAGE);
            for (int k = 0; k < jSearchTable1.getRowCount(); k++) {
                for (int r = 0; r < jSearchTable1.getColumnCount(); r++) {
                    jSearchTable1.setValueAt(null, k, r);
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchTable1KeyReleased

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        jSearchDialog2.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        //     jComboBox1311.setSelectedItem(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0));
        //      jTextField111.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        //     jSearchDialog2.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.jSearchDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jSearchScrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchScrollPaneMouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jSearchScrollPaneMouseClicked

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
        //      jT.setSelectedItem(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0));
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jTextField11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11CaretUpdate
        // Add your handling code here:
    }//GEN-LAST:event_jTextField11CaretUpdate

    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        com.afrisoftech.accounting.CopayReceiptsIntfr other = new com.afrisoftech.accounting.CopayReceiptsIntfr(connectDB, pConnDB, billAmountTxt.getText(), this.shiftNoTxt.getText(), this.glAccountTxt.getText(), this.patientNumberTxt.getText(), this.patientNameTxt.getText(), this.cashPointTxt.getText());
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);         // Add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void payerMobileTelephoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payerMobileTelephoneNumberTxtActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_payerMobileTelephoneNumberTxtActionPerformed

    private void totalBilledAmountTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalBilledAmountTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalBilledAmountTxtActionPerformed

    private void paybillNumberCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paybillNumberCmbxActionPerformed
        if (paybillNumberCmbx.getSelectedItem() != null) {
            com.afrisoftech.hospital.HospitalMain.payBillNumber = paybillNumberCmbx.getSelectedItem().toString();
            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT merchant_id, consumer_secrets FROM ac_cash_points WHERE paybill_no = ?");

                pstmt.setString(1, paybillNumberCmbx.getSelectedItem().toString());
                java.sql.ResultSet rset = pstmt.executeQuery();
                while (rset.next()) {
                    try {
                        com.afrisoftech.hospital.HospitalMain.passKey = new String(rset.getBytes(1), "UTF-8");
                        com.afrisoftech.hospital.HospitalMain.oAuthKey = new String(rset.getBytes(2), "UTF-8");
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                ex.printStackTrace();
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST select a paybill in order to proceed");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_paybillNumberCmbxActionPerformed

    private void stkPushBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stkPushBtnActionPerformed
        if (billTable.isEditing()) {
            billTable.getCellEditor().stopCellEditing();
        }

        int billedItems = 0;
        boolean isBilled = false;
        try {
            for (int j = 0; j < billTable.getRowCount(); j++) {
                if (billTable.getValueAt(j, 0) != null) {
                    if (billTable.getValueAt(j, 7) != null) {
                        billedItems++;
                    }

                }

            }
            if (billedItems == 0) {
                isBilled = false;
                java.sql.Statement ps = connectDB.createStatement();
                java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");

                while (rst.next()) {
                    rst.getObject(1).toString();

                    transNo = rst.getObject(1).toString();
                }

                for (int j = 0; j < billTable.getRowCount(); j++) {
                    if (billTable.getValueAt(j, 0) != null) {
                        if (billTable.getValueAt(j, 7) == null) {
                            billTable.setValueAt(transNo, j, 7);
                        }

                    }

                }

            } else {

                isBilled = true;

            }
            if (patientNameTxt.getText().length() > 3 && patientNumberTxt.getText().length() > 3 && !isBilled && Double.parseDouble(billAmountTxt.getText()) > 0.00) {
                for (int i = 0; i < billTable.getRowCount(); i++) {
                    if (billTable.getValueAt(i, 0) != null) {
                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");
                        pstmt2.setString(1, patientNumberTxt.getText());
                        pstmt2.setString(10, transNo);
                        pstmt2.setString(2, patientNameTxt.getText());
                        pstmt2.setString(3, paymentModeCmbx.getSelectedItem().toString());
                        pstmt2.setString(4, "OP");
                        pstmt2.setString(5, billTable.getValueAt(i, 0).toString());
                        pstmt2.setDouble(6, java.lang.Double.valueOf(billTable.getValueAt(i, 1).toString()));
                        pstmt2.setDouble(7, java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString()));
                        pstmt2.setObject(8, billTable.getValueAt(i, 4).toString());
                        pstmt2.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",datePicker1.getDate())));
                        pstmt2.setBoolean(12, false);
                        pstmt2.setString(11, com.afrisoftech.lib.UserName.getLoginName(connectDB));
                        pstmt2.setString(13, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connectDB, billTable.getValueAt(i, 4).toString()));
                        pstmt2.setInt(14, Integer.parseInt(transNo));
                        pstmt2.setString(15, null);
                        pstmt2.executeUpdate();
                    }
                }
            } else {
                if (!isBilled) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Please double check your entries");
                }
            }
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqe.getMessage());
        }
        com.afrisoftech.hospital.HospitalMain.patientID = patientNumberTxt.getText();
        com.afrisoftech.hospital.HospitalMain.patientName = patientNameTxt.getText();
        com.afrisoftech.hospital.HospitalMain.mobileTxType = "OPD";

        String payerTelephoneNumber = null;

        System.out.println("Payer Mobile Telephone Number : [" + payerMobileTelephoneNumberTxt.getText().replace(" ", "").replace("-", "").length() + "]");

        if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
            if (payerMobileTelephoneNumberTxt.getText().replace(" ", "").replace("-", "").length() == 12) {
                payerTelephoneNumber = payerMobileTelephoneNumberTxt.getText().replace(" ", "").replace("-", "");
                String transactionNo = null;
                for (int n = 0; n < billTable.getRowCount(); n++) {
                    if (billTable.getValueAt(n, 0) != null) {
                        transactionNo = billTable.getValueAt(n, 7).toString();
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful.Bill No. " + transactionNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa") && payerMobileTelephoneNumberTxt.getText().replace("-", "").length() == 12) {
                    boolean checkoutReturn = com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendProcessRequest(com.afrisoftech.funsoft.mobilepay.Base64Encoding.encodetoBase64String("A3AuQyjuuDNejTAd19oRfpozPBD098L6:IrQkzTfJBSNHXQif"), transactionNo, payerTelephoneNumber, String.valueOf(Math.round(Double.parseDouble(billAmountTxt.getText()))), com.afrisoftech.hospital.HospitalMain.payBillNumber);
                    if (checkoutReturn) {
                        try {
                            java.sql.PreparedStatement pstmtCheckout = null;
                            for (int n = 0; n < billTable.getRowCount(); n++) {
                                if (billTable.getValueAt(n, 0) != null) {
                                    transactionNo = billTable.getValueAt(n, 7).toString();

                                    pstmtCheckout = connectDB.prepareStatement("UPDATE hp_patient_billing SET checkout_request_id = ? WHERE inpatient_no = ? AND upper(service) = ?");
                                    pstmtCheckout.setString(1, checkoutRequestID);

                                    pstmtCheckout.setString(2, transactionNo);
                                    pstmtCheckout.setString(3, billTable.getValueAt(n, 0).toString().toUpperCase());

                                    pstmtCheckout.executeUpdate();
                                    pstmtCheckout.close();

                                    pstmtCheckout = connectDB.prepareStatement("UPDATE hp_pharmacy SET checkout_request_id = ? WHERE prescription_no = ? AND upper(description) = ?");
                                    pstmtCheckout.setString(1, checkoutRequestID);

                                    pstmtCheckout.setString(2, transactionNo);
                                    pstmtCheckout.setString(3, billTable.getValueAt(n, 0).toString().toUpperCase());

                                    pstmtCheckout.executeUpdate();
                                    pstmtCheckout.close();

                                }
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(GovBillPaymentsIntfr.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "There is a problem connecting to the mobile payments service provider. Please contact system administrator!");
                    }
                }
            } else if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa") && payerMobileTelephoneNumberTxt.getText().replace("-", "").replace(" ", "").length() != 12) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please check telephone number! It should be formatted as follows : 2547xxxxxxxx");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_stkPushBtnActionPerformed

    private void mobilepayTxNoTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_mobilepayTxNoTxtCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilepayTxNoTxtCaretUpdate

    private void mobilepayTxNoTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobilepayTxNoTxtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilepayTxNoTxtFocusGained

    private void searchMobileTxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMobileTxButtonActionPerformed

        com.afrisoftech.hospital.HospitalMain.mobileTxID = null;
        com.afrisoftech.hospital.HospitalMain.mobileTelephone = null;

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.mobilepayTxNoTxt.getLocationOnScreen();

        mobilepayTxSearchDialog.setSize(700, 200);

        mobilepayTxSearchDialog.setLocation(point);

        mobilepayTxSearchDialog.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_searchMobileTxButtonActionPerformed

    private void mobilepayTxSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_mobilepayTxSearchTxtCaretUpdate

        if (mobilepayTxSearchTxt.getText().length() > 2) {
            mobilepayTxtSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT transaction_time::time(0), mobile_tx_id, account_no, date, paid_amount, upper(dealer) as client_name, journal_no as paybill_no, mobilepay_alert as processed FROM public.mobile_payments WHERE "
                    + "  mobile_tx_id NOT IN (SELECT transaction_id  FROM mobile_payment_deactivations) AND mobilepay_alert = false AND (account_no ilike '%" + mobilepayTxSearchTxt.getText() + "%' OR mobile_tx_id ilike '%" + mobilepayTxSearchTxt.getText() + "%' )  AND (date::date >= current_date - " + mobile_tx_validity_days + " OR mobile_tx_id IN (SELECT transaction_id FROM mobile_payament_activations WHERE date_active = current_date)) ORDER BY account_no"));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilepayTxSearchTxtCaretUpdate

    private void mobilepayTxtSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobilepayTxtSearchTableMouseClicked

        mobilepayTxNoTxt.setText(mobilepayTxtSearchTable.getValueAt(mobilepayTxtSearchTable.getSelectedRow(), 1).toString());

        com.afrisoftech.hospital.HospitalMain.mobileTxID = mobilepayTxtSearchTable.getValueAt(mobilepayTxtSearchTable.getSelectedRow(), 1).toString();

        com.afrisoftech.hospital.HospitalMain.mobileTelephone = mobilepayTxtSearchTable.getValueAt(mobilepayTxtSearchTable.getSelectedRow(), 2).toString();

        payerMobileTelephoneNumberTxt.setText(mobilepayTxtSearchTable.getValueAt(mobilepayTxtSearchTable.getSelectedRow(), 2).toString());

        paymentModeCmbx.setSelectedItem("M-Pesa");

        paybillNumberCmbx.setSelectedItem(mobilepayTxtSearchTable.getValueAt(mobilepayTxtSearchTable.getSelectedRow(), 6).toString());

        mobilePayTokenBalanceTxt.setText(String.valueOf(com.afrisoftech.lib.MobilePayments.getTokenValue(connectDB, mobilepayTxNoTxt.getText())));

        mobilepayTxSearchDialog.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_mobilepayTxtSearchTableMouseClicked

    private void mobilepayTxtSearchTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobilepayTxtSearchTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilepayTxtSearchTableMouseEntered

    private void jButton522ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton522ActionPerformed

        mobilepayTxSearchDialog.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton522ActionPerformed

    private void addedMobilepayTxtTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addedMobilepayTxtTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addedMobilepayTxtTableMouseClicked

    private void addedMobilepayTxtTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addedMobilepayTxtTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_addedMobilepayTxtTableMouseEntered

    private void jButton523ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton523ActionPerformed
        addedMobileTxDialogue.dispose(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton523ActionPerformed

    private void viewMobileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMobileBtnActionPerformed
        java.awt.Point point = this.viewMobileBtn.getLocationOnScreen();

        addedMobileTxDialogue.setSize(400, 150);
        addedMobileTxDialogue.setLocation(point);
        addedMobileTxDialogue.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_viewMobileBtnActionPerformed

    private void addMobileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMobileBtnActionPerformed
        if (patientNameTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select/Provide the Patient details before ADDING!!", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else if (mobilepayTxNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a Mobile Transaction before ADDING!", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else if (Double.valueOf(billAmountTxt.getText()) <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ensure that the Item(s) to be settled are populated!!", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            boolean added = false;
            for (int k = 0; k < addedMobilepayTxtTable.getModel().getRowCount(); k++) {
                System.err.println(addedMobilepayTxtTable.getValueAt(k, 0).toString() + "<<<>>>" + mobilepayTxNoTxt.getText());
                if (mobilepayTxNoTxt.getText().equalsIgnoreCase(addedMobilepayTxtTable.getValueAt(k, 0).toString())) {
                    added = true;

                }
            }
            if (!added) {
                double txAmount = 0.00;//mobilePayTokenBalanceTxt
                double currentTxAmount = Double.valueOf(mobilePayTokenBalanceTxt.getText());
                double billtotal = Double.valueOf(billAmountTxt.getText());
                double addedTxAmount = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(addedMobilepayTxtTable, 2);
                if ((billtotal - (addedTxAmount + currentTxAmount)) >= 0) {
                    txAmount = currentTxAmount;
                } else {
                    txAmount = billtotal - addedTxAmount;
                }

                if (txAmount > 0) {
                    Object[] items = new Object[]{mobilepayTxNoTxt.getText(), payerMobileTelephoneNumberTxt.getText(), txAmount};
                    com.afrisoftech.lib.ClearTable.addRowData(addedMobilepayTxtTable, items);
                    totalTokensAmountTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(addedMobilepayTxtTable, 2)));
                    javax.swing.JOptionPane.showMessageDialog(this, "Transaction " + mobilepayTxNoTxt.getText() + "(Amount - " + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(txAmount) + ") Added", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "The bill Amount has been exhausted by the Added Tokens !!", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);

                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Transaction Already Added!!", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_addMobileBtnActionPerformed

    private void jButton524ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton524ActionPerformed
        javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) addedMobilepayTxtTable.getModel();
        defTableModel.removeRow(addedMobilepayTxtTable.getSelectedRow());  // TODO add your handling code here:
    }//GEN-LAST:event_jButton524ActionPerformed

    public void tableModelTableChanged1() {
        System.out.println("Calculating totals for table 11 and 2.");
        //        double resFloat = 0.00;
        double resFloat = 0.00;

        for (int i = 0; i < billTable.getRowCount(); i++) {

            if (billTable.getModel().getValueAt(i, 0) != null) {

                if (billTable.getSelectedColumn() == 1) {

                    resFloat = resFloat + Double.parseDouble(billTable.getModel().getValueAt(i, 3).toString());

                    billAmountTxt.setText(java.lang.String.valueOf(resFloat));
                } else {
                    resFloat = resFloat + Double.parseDouble(billTable.getModel().getValueAt(i, 3).toString());
                }

                billAmountTxt.setText(java.lang.String.valueOf(resFloat));
            }

        }

        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }

    public void tableModelTableChanged1(javax.swing.event.TableModelEvent evt) {

        //        double resFloat = 0.00;
        double resFloat = 0.00;

        for (int i = 0; i
                < billTable.getRowCount(); i++) {

            if (billTable.getModel().getValueAt(i, 0) != null) {

                if (billTable.getSelectedColumn() == 1) {

                    resFloat = resFloat + Double.parseDouble(billTable.getModel().getValueAt(i, 3).toString());

                    billAmountTxt.setText(java.lang.String.valueOf(resFloat));
                } else {
                    resFloat = resFloat + Double.parseDouble(billTable.getModel().getValueAt(i, 3).toString());
                }

                billAmountTxt.setText(java.lang.String.valueOf(resFloat));

            }

        }

        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }

    public java.lang.String getShiftNumber() {

        try {

            java.sql.Statement stmtf = connectDB.createStatement();
            java.sql.ResultSet rsetf = stmtf.executeQuery("select code from ac_cash_points_setup where description = current_user");
            while (rsetf.next()) {
                // cashPoint = rsetf.getObject(1).toString();
            }
            rsetf.close();
            stmtf.close();
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("select not successful");
        }

        try {

            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.ResultSet rset = stmt.executeQuery("SELECT shift_no FROM ac_shifts WHERE user_name = current_user AND (status = 'Running' OR status = 'Suspended')");

            while (rset.next()) {

                shift_no = rset.getString(1);

            }
            stmt.close();
            rset.close();
        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        return shift_no;

    }

    public java.lang.String getCashPoint() {

        try {

            java.sql.Statement stmt = connectDB.createStatement();

            // java.sql.ResultSet rset = stmt.executeQuery("SELECT shift_no FROM ac_shifts WHERE cash_point = '"+System.getProperty("cashpoint")+"' AND user_name = current_user AND (status = 'Running' OR status = 'Suspended')");
            java.sql.ResultSet rset = stmt.executeQuery("SELECT cash_point FROM ac_shifts WHERE user_name = current_user AND (status = 'Running' OR status = 'Suspended')");

            while (rset.next()) {

                cash_no = rset.getString(1);
                cashPointTxt.setText(cash_no);

            }
            rset.close();
            stmt.close();

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        return cash_no;

    }

    private class DispatchThread extends java.lang.Thread {

        public synchronized void run() {
            saveData();
            ////  processReceipt = true;
        }
    }

    private void saveData() {

        if ((exemptionChkbx.isSelected() || Double.parseDouble(waivedAmountTxt.getText()) > 0.00) && exemptionNumberTxt.getText().toCharArray().length < 1) {

            //System.out.println("Can't process receipt...");
            // processReceipt = false;
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST provide Exemption/Waiver number.\n Please type the reference number for the Exemption/Waiver\n on the [Exemption/Waiver Number] field on the POS form.", "WARNING: EXEMPTION/WAIVER NUMBER!", javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {

            processReceipt = true;

            ///  System.
            if (patientNameTxt.getText().equalsIgnoreCase("")) {
                javax.swing.JOptionPane.showMessageDialog(this, "You cannot save without the name", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } else {
                if (shiftNoTxt.getText().equalsIgnoreCase("")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "You cannot save without the shift No \n enquire if you are created as a shift operator", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                } else {
                    receiptNumberTxt.setText("");

                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                    System.out.println("RECEIPT DATE : [" + datenowSql1.toString() + "]");

                    java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                    System.out.println(datenowSql.toString());
                    String desc = null;
                    String billNo = null;
                    String transNo = null;
                    String payMode = null;
                    String patientAcc = null;
                    String cardNo = null;
                    String AccDesc = null;
                    String scheme = null;
                    String cardName = null;
                    String isurer = null;
                    String expDate = null;
                    String staffNo = null;
                    String glAcc = null;
                    String receiptNo1 = null;
                    String actCode1 = null;
                    String glCode1 = null;
                    String bankAcc1 = null;
                    String transNo1 = null;
                    String actNames1 = null;
                    String patCat1 = null;
                    String patientAcc1 = null;
                    String user = null;
                    String glAcc1 = null;
                    String priceCategory = null;
                    String name = null;
                    String mdepartment = null;
                    java.sql.Date dates = null;
                    receiptNo2 = null;
                    java.sql.Savepoint registerSavePoint = null;
                    try {
                        connectDB.setAutoCommit(false);
                        registerSavePoint
                                = connectDB.setSavepoint("registration");
                    } catch (java.sql.SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        java.sql.Statement stm1211 = connectDB.createStatement();
                        //    java.sql.Statement ps1 = connectDB.createStatement();
                        java.sql.Statement pss = connectDB.createStatement();
                        //   java.sql.Statement stm122 = connectDB.createStatement();
                        java.sql.Statement pss1 = connectDB.createStatement();
                        java.sql.Statement stm121 = connectDB.createStatement();
                        java.sql.ResultSet rse121 = null;
                        java.sql.Statement stm121s = connectDB.createStatement();
                        java.sql.ResultSet rse121s = null;
                        java.sql.Statement stm121w = connectDB.createStatement();
                        java.sql.Statement stm121r = connectDB.createStatement();
                        java.sql.Statement ps1q = connectDB.createStatement();
                        java.sql.PreparedStatement pstmt112F = null;
                        java.sql.PreparedStatement pstmt112 = null;
                        java.sql.ResultSet rst1q = null;
                        java.sql.PreparedStatement psr = null;
                        java.sql.PreparedStatement pstmt25 = null;
                        java.sql.PreparedStatement pstmt212 = null;
                        java.sql.ResultSet rse121w = null;
                        java.sql.ResultSet rse121r = null;
                        java.sql.PreparedStatement pstmt41d = null;
                        java.sql.PreparedStatement pstmt41 = null;
                        java.sql.PreparedStatement pstmt411 = null;
                        java.sql.PreparedStatement pstmt25e = null;
                        java.sql.Statement ps = connectDB.createStatement();
                        java.sql.ResultSet rst = null;
                        java.sql.Statement stm12fx = connectDB.createStatement();
                        java.sql.ResultSet rse12fx = null;
                        java.sql.Statement stm12f = connectDB.createStatement();
                        java.sql.ResultSet rse12f = null;
                        java.sql.Statement stm12 = connectDB.createStatement();
                        java.sql.ResultSet rse12 = null;
                        java.sql.ResultSet rse1211 = null;
                        java.sql.ResultSet rsts = null;
                        java.sql.ResultSet rss = null;

//select current_timestamp
                        rse12fx = stm12fx.executeQuery("select cash_point,start_date::DATE from ac_shifts WHERE shift_no = '" + shiftNoTxt.getText() + "'");
                        while (rse12fx.next()) {
                            cashPointTxt.setText(rse12fx.getString(1));
                            dates = rse12fx.getDate(2);
                        }
                        dates = com.afrisoftech.lib.ServerTime.getSQLDate(connectDB);
                        rse12f = stm12f.executeQuery("select now()");
                        while (rse12f.next()) {
                            datenowSql = rse12f.getTimestamp(1);
                        }

                        rse12 = stm12.executeQuery("select code,activity,current_user from pb_activity where activity_category ='PR'");
                        while (rse12.next()) {

                            patientAcc = rse12.getObject(1).toString();
                            AccDesc = rse12.getObject(2).toString();
                            user = rse12.getObject(3).toString();
                        }

                        rse1211 = stm1211.executeQuery("select activity,code from pb_activity where activity_category ='RC'");
                        while (rse1211.next()) {
                            rname = rse1211.getObject(1).toString();
                            rcode = rse1211.getObject(2).toString();
                        }

                        /*
                             * java.sql.ResultSet rst1 = ps1.executeQuery("select
                             * nextval('receipt_no_seq')"); while (rst1.next()){
                             * receiptNo1 = rst1.getObject(1).toString(); }
                         */
                        //receiptNo1 = datenowSql.toString();//com.afrisoftech.lib.DateLables.getDateLabel();
                        rss = pss.executeQuery("select nextval('transaction_no_seq')");
                        while (rss.next()) {
                            transNo1 = rss.getObject(1).toString();
                            receiptNo1 = "O" + rss.getObject(1).toString();
                        }

                        rsts = pss1.executeQuery("select code,activity from pb_activity where activity_category ='PR'");
                        while (rsts.next()) {
                            actCode1 = rsts.getObject(1).toString();
                            actNames1 = rsts.getObject(2).toString();
                        }

                        for (int i = 0; i < billTable.getRowCount(); i++) {
                            if (billTable.getModel().getValueAt(i, 0) != null) {
                                double waived = java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString());

                                System.out.println("Bill No 2 " + billNo);

                                rse121 = stm121.executeQuery("select activity,code,department from pb_activity where code ='" + billTable.getModel().getValueAt(i, 4).toString() + "'");
                                while (rse121.next()) {

                                    glAcc = rse121.getObject(1).toString();
                                    if (rse121.getObject(3) != null) {
                                        mdepartment = rse121.getObject(3).toString();
                                    } else {
                                        mdepartment = "-";
                                    }
                                }
                                rse121s = stm121s.executeQuery("SELECT prov_code FROM st_stock_item WHERE item_code = '" + billTable.getModel().getValueAt(i, 6).toString() + "' "
                                        + "UNION ALL SELECT price_category FROM pb_operating_parameters WHERE code = '" + billTable.getModel().getValueAt(i, 6).toString() + "'");
                                while (rse121s.next()) {

                                    if (rse121s.getObject(1) == null) {
                                        priceCategory = "0";
                                    } else {
                                        priceCategory = rse121s.getObject(1).toString();
                                    }
                                }

                                if (waived > 0) {
                                    if (billTable.getValueAt(i, 0).toString().equalsIgnoreCase("NHIF PAYMENTS") || billTable.getValueAt(i, 0).toString().equalsIgnoreCase("OTHER DEBTORS PAYMENTS")) {
                                    } else {

                                        System.out.println("Bill No 4" + billNo);
                                        pstmt212 = connectDB.prepareStatement("INSERT INTO ac_ledger values(?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                        pstmt212.setObject(1, billTable.getValueAt(i, 4).toString());
                                        pstmt212.setString(2, glAcc);
                                        pstmt212.setString(3, patientNumberTxt.getText());
                                        if (patientNameTxt.getText().equals("") || patientNameTxt.getText().equals(null)) {
                                            javax.swing.JOptionPane.showMessageDialog(this, "PATIENT NAME MISSING !!", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            pstmt212.setString(4, patientNameTxt.getText());
                                        }

                                        pstmt212.setString(5, "");
                                        pstmt212.setString(6, "");
                                        pstmt212.setString(7, "");
                                        pstmt212.setString(8, "OP");
                                        pstmt212.setString(9, jTextField51.getText());
                                        pstmt212.setString(10, paymentModeCmbx.getSelectedItem().toString());
                                        pstmt212.setString(11, jTextField22.getText());
                                        pstmt212.setString(12, receiptNo1);
                                        pstmt212.setString(13, "");
                                        pstmt212.setString(14, billTable.getValueAt(i, 0).toString());
                                        pstmt212.setString(15, "Revenue");
                                        pstmt212.setDouble(16, 0.00);
                                        pstmt212.setDouble(17, java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString()));
                                        pstmt212.setDate(18, dates);//.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt212.setString(19, transNo1);
                                        pstmt212.setBoolean(20, false);
                                        pstmt212.setBoolean(21, false);
                                        pstmt212.setBoolean(22, false);
                                        pstmt212.setString(23, user);
                                        pstmt212.setString(24, cashPointTxt.getText());
                                        pstmt212.setString(25, shiftNoTxt.getText());
                                        pstmt212.executeUpdate();
                                        pstmt212.close();
                                        //}
                                    }

                                    pstmt25 = connectDB.prepareStatement("INSERT INTO "
                                            + "ac_cash_collection values(?,?,?,?,?,?,?,?, "
                                            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                                            + "?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmt25.setObject(1, billTable.getValueAt(i, 4).toString());
                                    pstmt25.setObject(2, billTable.getValueAt(i, 0).toString());
                                    pstmt25.setString(3, patientNumberTxt.getText());
                                    pstmt25.setString(4, patientNameTxt.getText().toUpperCase());
                                    pstmt25.setString(5, paymentModeCmbx.getSelectedItem().toString());
                                    pstmt25.setString(6, jTextField212.getText());
                                    pstmt25.setString(7, jTextField521.getText());
                                    pstmt25.setString(8, jTextField42.getText());
                                    if (jTextField51.getText().equals("")) {
                                        pstmt25.setString(9, jTextField411.getText());
                                    } else {
                                        pstmt25.setString(9, jTextField51.getText());
                                    }

                                    pstmt25.setString(10, rcode);
                                    pstmt25.setString(11, jTextField22.getText());
                                    pstmt25.setString(12, receiptNo1);
                                    if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
                                        if (addedMobilepayTxtTable.getRowCount() > 1) {//If several paymodes
                                            String txID = "";
                                            String telNo = "";
                                            for (int p = 0; p < addedMobilepayTxtTable.getRowCount(); p++) {
                                                txID = txID + addedMobilepayTxtTable.getValueAt(p, 0) + " :";
                                                telNo = telNo + addedMobilepayTxtTable.getValueAt(p, 1) + " :";
                                            }
                                            pstmt25.setString(13, txID);
                                            pstmt25.setString(14, telNo);
                                        } else {
                                            pstmt25.setString(13, com.afrisoftech.hospital.HospitalMain.mobileTxID);
                                            pstmt25.setString(14, com.afrisoftech.hospital.HospitalMain.mobileTelephone);
                                        }
                                    } else {
                                        pstmt25.setString(13, exemptionNumberTxt.getText());
                                        pstmt25.setString(14, patientNameTxt.getText().toUpperCase());
                                    }
                                    pstmt25.setString(15, "Receipts");
                                    pstmt25.setDouble(16, java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString()));
                                    pstmt25.setDouble(17, 0.00);
                                    pstmt25.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmt25.setString(19, "" + transNo1 + "");
                                    pstmt25.setBoolean(20, false);
                                    pstmt25.setBoolean(21, false);
                                    pstmt25.setBoolean(22, false);
                                    pstmt25.setString(23, user);
                                    pstmt25.setString(24, cashPointTxt.getText());
                                    pstmt25.setString(25, shiftNoTxt.getText());
                                    pstmt25.setDate(26, null);
                                    pstmt25.setDouble(27, java.lang.Double.valueOf(billTable.getValueAt(i, 1).toString()));
                                    pstmt25.setDate(28, null);
                                    pstmt25.setTimestamp(29, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));
                                    pstmt25.setString(30, mdepartment);
                                    pstmt25.setBoolean(31, false);
                                    pstmt25.setObject(32, priceCategory);
                                    pstmt25.setObject(33, billTable.getValueAt(i, 6).toString());
                                    pstmt25.setDouble(34, java.lang.Double.valueOf(billTable.getValueAt(i, 2).toString()));
                                    if (paymentModeCmbx.getSelectedItem().toString().contains("Pesa")) {
                                        pstmt25.setString(35, checkoutRequestID);
                                        pstmt25.setObject(36, paybillNumberCmbx.getSelectedItem());
                                    } else {
                                        pstmt25.setString(35, null);
                                        pstmt25.setString(36, null);
                                    }
                                    pstmt25.executeUpdate();
//
//                                    pstmt41d = connectDB.prepareStatement("UPDATE pb_doctors_request SET paid = true WHERE patient_no = '" + patientNumberTxt.getText() + "' AND service = '" + jTable111.getValueAt(i, 0) + "' AND trans_date >= current_date - 1");
//                                    pstmt41d.executeUpdate();
//                                    pstmt41 = connectDB.prepareStatement("UPDATE hp_pharmacy SET paid = true, collected = false WHERE patient_no = '" + patientNumberTxt.getText() + "' AND description = '" + jTable111.getValueAt(i, 0) + "' AND date_prescribed > current_date - 2");
//                                    pstmt41.executeUpdate();
//
//                                    pstmt411 = connectDB.prepareStatement("UPDATE hp_patient_billing SET paid = true, collected = false WHERE patient_no = '" + patientNumberTxt.getText() + "' AND service = '" + jTable111.getValueAt(i, 0) + "' AND trans_date > current_date - 2");
//                                    pstmt411.executeUpdate();

                                    if (billTable.getValueAt(i, 0).toString().equalsIgnoreCase("NHIF PAYMENTS")) {
                                        pstmt25e = connectDB.prepareStatement("INSERT INTO ac_debtors values(?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)");

                                        pstmt25e.setObject(1, rcode);
                                        pstmt25e.setObject(2, jComboBox111.getSelectedItem().toString());
                                        pstmt25e.setString(3, patientNameTxt.getText());
                                        pstmt25e.setString(4, "");
                                        pstmt25e.setString(5, billTable.getValueAt(i, 0).toString());
                                        pstmt25e.setString(6, "");
                                        pstmt25e.setString(7, "");
                                        pstmt25e.setString(8, receiptNo1);
                                        pstmt25e.setString(9, "");
                                        pstmt25e.setString(10, "");
                                        pstmt25e.setString(11, transNo1);
                                        pstmt25e.setString(12, "Debt Payments");
                                        pstmt25e.setDouble(13, 0.00);
                                        pstmt25e.setDouble(14, java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString()));
                                        pstmt25e.setDate(15, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt25e.setString(16, "");
                                        pstmt25e.setObject(17, "DEBTORS ACCOUNT");
                                        pstmt25e.setBoolean(18, false);
                                        pstmt25e.setBoolean(19, false);
                                        pstmt25e.setString(20, user);
                                        pstmt25e.setString(21, "");
                                        pstmt25e.setDouble(22, 0.00);
                                        pstmt25e.setBoolean(23, false);
                                        pstmt25e.setDouble(24, 0.00);
                                        pstmt25e.setString(25, "");
                                        pstmt25e.setDate(26, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt25e.setBoolean(27, false);
                                        pstmt25e.executeUpdate();

                                    }

                                    if (billTable.getValueAt(i, 0).toString().equalsIgnoreCase("OTHER DEBTORS PAYMENTS")) {
                                        pstmt25e = connectDB.prepareStatement("INSERT INTO ac_debtors VALUES(?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)");

                                        pstmt25e.setObject(1, rcode);
                                        pstmt25e.setObject(2, jComboBox111.getSelectedItem().toString());
                                        pstmt25e.setString(3, patientNameTxt.getText());
                                        pstmt25e.setString(4, "");
                                        pstmt25e.setString(5, billTable.getValueAt(i, 0).toString());
                                        pstmt25e.setString(6, "");
                                        pstmt25e.setString(7, "");
                                        pstmt25e.setString(8, receiptNo1);
                                        pstmt25e.setString(9, "");
                                        pstmt25e.setString(10, "");
                                        pstmt25e.setString(11, transNo1);
                                        pstmt25e.setString(12, "Debt Payments");
                                        pstmt25e.setDouble(13, 0.00);
                                        pstmt25e.setDouble(14, java.lang.Double.valueOf(billTable.getValueAt(i, 3).toString()));
                                        pstmt25e.setDate(15, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt25e.setString(16, "");
                                        pstmt25e.setObject(17, "DEBTORS ACCOUNT");
                                        pstmt25e.setBoolean(18, false);
                                        pstmt25e.setBoolean(19, false);
                                        pstmt25e.setString(20, user);
                                        pstmt25e.setString(21, "");
                                        pstmt25e.setDouble(22, 0.00);
                                        pstmt25e.setBoolean(23, false);
                                        pstmt25e.setDouble(24, 0.00);
                                        pstmt25e.setString(25, "");
                                        pstmt25e.setDate(26, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt25e.setBoolean(27, false);
                                        pstmt25e.executeUpdate();
                                        pstmt25e.close();
                                    } else {
                                    }

                                }

                            }

                        }
                        double waiver = 0.00;
                        if (waivedAmountTxt.getText().toCharArray().length > 0) {
                            waiver = java.lang.Double.parseDouble(waivedAmountTxt.getText());
                        }

                        String glAcc1q = null;
                        String glAcc2 = null;
                        String glAcc11 = null;
                        String glAcc21 = null;

                        rse121r = stm121r.executeQuery("select activity,code from pb_activity where activity_category ILIKE 'RC'");
                        while (rse121r.next()) {
                            glAcc21 = rse121r.getObject(2).toString();
                            glAcc11 = rse121r.getObject(1).toString();
                        }
                        rse121w = stm121w.executeQuery("select activity,code,department from pb_activity where activity_category ILIKE 'IEXE'");
                        while (rse121w.next()) {
                            glAcc2 = rse121w.getObject(1).toString();
                            glAcc1q = rse121w.getObject(2).toString();
                            mdepartment = rse121w.getObject(3).toString();
                        }
                        if (exemptionChkbx.isSelected()) {
                            System.err.println("Exempting..............................");

                            if (exemptionNumberTxt.getText().toCharArray().length < 1) {
                                ps = connectDB.createStatement();
                                rst = ps.executeQuery("select 'E'||lpad(nextval('exemption')::text, 7, 0::text)");
                                while (rst.next()) {
                                    exemptionNumberTxt.setText(rst.getString(1));
                                }
                                rst.close();
                            }
                            //          javax.swing.JOptionPane.showMessageDialog(this, "You MUST provide the reference number for the exemption or waiver.",  "WARNING: EXEMPTION NUMBER!", javax.swing.JOptionPane.WARNING_MESSAGE);
                            //      } else {
                            for (int k = 0; k < billTable.getRowCount(); k++) {
                                if (billTable.getModel().getValueAt(k, 0) != null) {

                                    double waived = java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString());
                                    if (jRadioButton1.isSelected()) {
                                        if (waived < 0) {

                                            rse121s = stm121s.executeQuery("SELECT prov_code FROM st_stock_item WHERE item_code = '" + billTable.getModel().getValueAt(k, 6).toString() + "'");
                                            while (rse121s.next()) {

                                                if (priceCategory == null) {
                                                    priceCategory = "0";
                                                } else {
                                                    priceCategory = rse121s.getObject(1).toString();
                                                }
                                            }

                                            System.out.println("Bill No 44" + billNo);
                                            pstmt212 = connectDB.prepareStatement("INSERT INTO ac_ledger values(?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                            pstmt212.setString(1, glAcc1q);
                                            pstmt212.setString(2, billTable.getValueAt(k, 0).toString());
                                            pstmt212.setString(3, patientNumberTxt.getText());
                                            pstmt212.setString(4, patientNameTxt.getText().toUpperCase());
                                            pstmt212.setString(5, "");
                                            pstmt212.setString(6, "");
                                            pstmt212.setString(7, "");
                                            pstmt212.setString(8, "OP");
                                            pstmt212.setString(9, jTextField51.getText());
                                            pstmt212.setString(10, paymentModeCmbx.getSelectedItem().toString());
                                            pstmt212.setString(11, jTextField22.getText());
                                            pstmt212.setString(12, receiptNo1);
                                            pstmt212.setObject(13, billTable.getValueAt(k, 6));
                                            pstmt212.setString(14, "Exemption");
                                            pstmt212.setString(15, "Exemption");
                                            pstmt212.setDouble(16, -1 * java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                            pstmt212.setDouble(17, 0.00);
                                            pstmt212.setDate(18, dates);// com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                            pstmt212.setString(19, transNo1);
                                            pstmt212.setBoolean(20, false);
                                            pstmt212.setBoolean(21, false);
                                            pstmt212.setBoolean(22, false);
                                            pstmt212.setString(23, user);
                                            pstmt212.setString(24, cashPointTxt.getText());
                                            pstmt212.setString(25, shiftNoTxt.getText());
                                            pstmt212.executeUpdate();

                                            pstmt25 = connectDB.prepareStatement("INSERT INTO ac_cash_collection values(?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)");

                                            pstmt25.setObject(1, glAcc1q);
                                            pstmt25.setObject(2, billTable.getValueAt(k, 0).toString());
                                            pstmt25.setString(3, patientNumberTxt.getText());
                                            pstmt25.setString(4, patientNameTxt.getText().toUpperCase());
                                            pstmt25.setString(5, paymentModeCmbx.getSelectedItem().toString());
                                            pstmt25.setString(6, jTextField212.getText());
                                            pstmt25.setString(7, jTextField521.getText());
                                            pstmt25.setString(8, jTextField42.getText());
                                            if (jTextField51.getText().equals("")) {
                                                pstmt25.setString(9, jTextField411.getText());
                                            } else {
                                                pstmt25.setString(9, jTextField51.getText());
                                            }

                                            pstmt25.setString(10, rcode);
                                            pstmt25.setString(11, jTextField22.getText());
                                            pstmt25.setString(12, receiptNo1);
                                            pstmt25.setString(13, exemptionNumberTxt.getText());
                                            pstmt25.setString(14, patientNameTxt.getText().toUpperCase());
                                            pstmt25.setString(15, "Exemptions");
                                            pstmt25.setDouble(16, 0.00);
                                            pstmt25.setDouble(17, -1 * java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                            pstmt25.setDate(18, dates); //com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                            pstmt25.setString(19, "" + transNo1 + "");
                                            pstmt25.setBoolean(20, false);
                                            pstmt25.setBoolean(21, false);
                                            pstmt25.setBoolean(22, false);
                                            pstmt25.setString(23, user);
                                            pstmt25.setString(24, cashPointTxt.getText());
                                            pstmt25.setString(25, shiftNoTxt.getText());
                                            pstmt25.setDate(26, null);
                                            pstmt25.setDouble(27, 1);
                                            pstmt25.setDate(28, null);
                                            pstmt25.setTimestamp(29, datenowSql);
                                            pstmt25.setString(30, mdepartment);
                                            pstmt25.setBoolean(31, false);
                                            pstmt25.setObject(32, priceCategory);
                                            pstmt25.setObject(33, billTable.getValueAt(k, 6).toString());
                                            pstmt25.setDouble(34, java.lang.Double.valueOf(billTable.getValueAt(k, 2).toString()));

                                            pstmt25.executeUpdate();

                                        }
                                    } else {
                                        System.err.println("SSSSSSSSSSSSSSSSSS----");
                                        //if (waived < 0) {

                                        rse121w = stm121w.executeQuery("select activity,code,department from pb_activity where activity_category ILIKE 'IEXE'");
                                        while (rse121w.next()) {
                                            glAcc2 = rse121w.getObject(1).toString();
                                            glAcc1q = rse121w.getObject(2).toString();
                                            mdepartment = rse121w.getObject(3).toString();
                                        }

                                        rse121r = stm121r.executeQuery("select activity,code from pb_activity where activity_category ILIKE 'RC'");
                                        while (rse121r.next()) {
                                            glAcc21 = rse121r.getObject(2).toString();
                                            glAcc11 = rse121r.getObject(1).toString();
                                        }

                                        System.out.println("Bill No 4" + billNo);

                                        pstmt212 = connectDB.prepareStatement("INSERT INTO ac_ledger values(?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                        pstmt212.setString(1, glAcc1q);
                                        pstmt212.setString(2, billTable.getValueAt(k, 0).toString());
                                        pstmt212.setString(3, patientNumberTxt.getText());
                                        pstmt212.setString(4, patientNameTxt.getText().toUpperCase());
                                        pstmt212.setString(5, "");
                                        pstmt212.setString(6, "");
                                        pstmt212.setString(7, "");
                                        pstmt212.setString(8, "OP");
                                        pstmt212.setString(9, jTextField51.getText());
                                        pstmt212.setString(10, paymentModeCmbx.getSelectedItem().toString());
                                        pstmt212.setString(11, jTextField22.getText());
                                        pstmt212.setString(12, receiptNo1);
                                        //pstmt212.setString(13, "");
                                        pstmt212.setObject(13, billTable.getValueAt(k, 6));
                                        pstmt212.setString(14, "Exemption");
                                        pstmt212.setString(15, "Exemption");
                                        pstmt212.setDouble(16, java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                        pstmt212.setDouble(17, 0.00);
                                        pstmt212.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt212.setString(19, transNo1);
                                        pstmt212.setBoolean(20, false);
                                        pstmt212.setBoolean(21, false);
                                        pstmt212.setBoolean(22, false);
                                        pstmt212.setString(23, user);
                                        pstmt212.setString(24, cashPointTxt.getText());
                                        pstmt212.setString(25, shiftNoTxt.getText());
                                        pstmt212.executeUpdate();

                                        pstmt25 = connectDB.prepareStatement("INSERT INTO ac_cash_collection values(?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");

                                        pstmt25.setObject(1, glAcc1q);
                                        pstmt25.setObject(2, billTable.getValueAt(k, 0).toString());
                                        pstmt25.setString(3, patientNumberTxt.getText());
                                        pstmt25.setString(4, patientNameTxt.getText().toUpperCase());
                                        pstmt25.setString(5, paymentModeCmbx.getSelectedItem().toString());
                                        pstmt25.setString(6, jTextField212.getText());
                                        pstmt25.setString(7, jTextField521.getText());
                                        pstmt25.setString(8, jTextField42.getText());
                                        if (jTextField51.getText().equals("")) {
                                            pstmt25.setString(9, jTextField411.getText());
                                        } else {
                                            pstmt25.setString(9, jTextField51.getText());
                                        }

                                        pstmt25.setString(10, rcode);
                                        pstmt25.setString(11, jTextField22.getText());
                                        pstmt25.setString(12, receiptNo1);
                                        pstmt25.setString(13, exemptionNumberTxt.getText());
                                        pstmt25.setString(14, patientNameTxt.getText().toUpperCase());
                                        pstmt25.setString(15, "Exemptions");
                                        pstmt25.setDouble(16, 0.00);
                                        pstmt25.setDouble(17, java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                        pstmt25.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt25.setString(19, "" + transNo1 + "");
                                        pstmt25.setBoolean(20, false);
                                        pstmt25.setBoolean(21, false);
                                        pstmt25.setBoolean(22, false);
                                        pstmt25.setString(23, user);
                                        pstmt25.setString(24, cashPointTxt.getText());
                                        pstmt25.setString(25, shiftNoTxt.getText());
                                        pstmt25.setDate(26, null);
                                        pstmt25.setDouble(27, 1);
                                        pstmt25.setDate(28, null);
                                        pstmt25.setTimestamp(29, datenowSql);
                                        pstmt25.setString(30, mdepartment);
                                        pstmt25.setBoolean(31, false);
                                        pstmt25.setObject(32, priceCategory);
                                        pstmt25.setObject(33, billTable.getValueAt(k, 6).toString());
                                        pstmt25.setDouble(34, java.lang.Double.valueOf(billTable.getValueAt(k, 2).toString()));

                                        pstmt25.executeUpdate();

                                        //}
                                    }
                                }

                            }
                        } else {
                            if (waiver > 0) {
                                if (exemptionNumberTxt.getText().toCharArray().length < 1) {
                                    ps = connectDB.createStatement();
                                    rst = ps.executeQuery("select 'W'||lpad(nextval('waiver')::text, 8, 0::text)");
                                    while (rst.next()) {
                                        exemptionNumberTxt.setText(rst.getString(1));
                                    }

                                }

                                rse121w = stm121w.executeQuery("select activity,code,department from pb_activity where activity_category ILIKE 'IEDS'");
                                while (rse121w.next()) {

                                    glAcc2 = rse121w.getObject(1).toString();
                                    glAcc1q = rse121w.getObject(2).toString();
                                    mdepartment = rse121w.getObject(3).toString();
                                }

                                rse121r = stm121r.executeQuery("select activity,code from pb_activity where activity_category ILIKE 'RC'");
                                while (rse121r.next()) {

                                    glAcc21 = rse121r.getObject(2).toString();
                                    glAcc11 = rse121r.getObject(1).toString();
                                }

                                if (jRadioButton1.isSelected()) {
                                    for (int k = 0; k < billTable.getRowCount(); k++) {
                                        if (billTable.getModel().getValueAt(k, 0) != null) {

                                            rse121 = stm121s.executeQuery("SELECT prov_code FROM st_stock_item WHERE item_code = '" + billTable.getModel().getValueAt(k, 6).toString() + "'");
                                            while (rse121.next()) {

                                                if (priceCategory == null) {
                                                    priceCategory = "0";
                                                } else {
                                                    priceCategory = rse121.getObject(1).toString();
                                                }
                                            }
                                            //        if (exemptionNumberTxt.getText().toCharArray().length < 1) {
                                            //            javax.swing.JOptionPane.showMessageDialog(this,  "You MUST provide the reference number for the exemption or waiver.", "WARNING: EXEMPTION NUMBER!", javax.swing.JOptionPane.WARNING_MESSAGE);
                                            //        } else {

                                            double waived = java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString());

                                            if (waived < 0) {

                                                System.out.println("Bill No 4" + billNo);

                                                pstmt212 = connectDB.prepareStatement("INSERT INTO ac_ledger values(?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                                pstmt212.setObject(1, glAcc1q);
                                                pstmt212.setString(2, billTable.getValueAt(k, 0).toString());
                                                pstmt212.setString(3, patientNumberTxt.getText());
                                                pstmt212.setString(4, patientNameTxt.getText().toUpperCase());
                                                pstmt212.setString(5, "");
                                                pstmt212.setString(6, "");
                                                pstmt212.setString(7, "");
                                                pstmt212.setString(8, "OP");
                                                pstmt212.setString(9, jTextField51.getText());
                                                pstmt212.setString(10, paymentModeCmbx.getSelectedItem().toString());
                                                pstmt212.setString(11, jTextField22.getText());
                                                pstmt212.setString(12, receiptNo1);
                                                pstmt212.setObject(13, billTable.getValueAt(k, 6));
                                                pstmt212.setString(14, "Waiver");
                                                pstmt212.setString(15, "Waiver");
                                                pstmt212.setDouble(16, -1 * java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                                pstmt212.setDouble(17, 0.00);
                                                pstmt212.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                                pstmt212.setString(19, transNo1);
                                                pstmt212.setBoolean(20, false);
                                                pstmt212.setBoolean(21, false);
                                                pstmt212.setBoolean(22, false);
                                                pstmt212.setString(23, user);
                                                pstmt212.setString(24, cashPointTxt.getText());
                                                pstmt212.setString(25, shiftNoTxt.getText());
                                                pstmt212.executeUpdate();

                                                pstmt25 = connectDB.prepareStatement("INSERT INTO ac_cash_collection values(?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                                                pstmt25.setObject(1, glAcc1q);
                                                pstmt25.setObject(2, billTable.getValueAt(k, 0).toString());
                                                pstmt25.setString(3, patientNumberTxt.getText());
                                                pstmt25.setString(4, patientNameTxt.getText().toUpperCase());
                                                pstmt25.setString(5, paymentModeCmbx.getSelectedItem().toString());
                                                pstmt25.setString(6, jTextField212.getText());
                                                pstmt25.setString(7, jTextField521.getText());
                                                pstmt25.setString(8, jTextField42.getText());
                                                if (jTextField51.getText().equals("")) {
                                                    pstmt25.setString(9, jTextField411.getText());
                                                } else {
                                                    pstmt25.setString(9, jTextField51.getText());
                                                }

                                                pstmt25.setString(10, rcode);
                                                pstmt25.setString(11, jTextField22.getText());
                                                pstmt25.setString(12, receiptNo1);
                                                pstmt25.setString(13, exemptionNumberTxt.getText());
                                                pstmt25.setString(14, patientNameTxt.getText().toUpperCase());
                                                pstmt25.setString(15, "Waiver");
                                                pstmt25.setDouble(16, 0.00);
                                                pstmt25.setDouble(17, -1 * java.lang.Double.valueOf(billTable.getValueAt(k, 3).toString()));
                                                pstmt25.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                                pstmt25.setString(19, "" + transNo1 + "");
                                                pstmt25.setBoolean(20, false);
                                                pstmt25.setBoolean(21, false);
                                                pstmt25.setBoolean(22, false);
                                                pstmt25.setString(23, user);
                                                pstmt25.setString(24, cashPointTxt.getText());
                                                pstmt25.setString(25, shiftNoTxt.getText());
                                                pstmt25.setDate(26, null);
                                                pstmt25.setDouble(27, 1);
                                                pstmt25.setDate(28, null);
                                                pstmt25.setTimestamp(29, datenowSql);
                                                pstmt25.setString(30, mdepartment);
                                                pstmt25.setBoolean(31, false);
                                                pstmt25.setObject(32, priceCategory);
                                                pstmt25.setObject(33, billTable.getValueAt(k, 6).toString());
                                                pstmt25.setDouble(34, java.lang.Double.valueOf(billTable.getValueAt(k, 2).toString()));

                                                pstmt25.executeUpdate();
                                                pstmt25.close();
                                                pstmt212.close();

                                            }
                                        }
                                    }

                                } else {
                                    System.out.println("Bill No 4" + billNo);

                                    pstmt212 = connectDB.prepareStatement("INSERT INTO ac_ledger values(?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt212.setObject(1, glAcc1q);
                                    pstmt212.setString(2, "Waiver");
                                    pstmt212.setString(3, patientNumberTxt.getText());
                                    pstmt212.setString(4, patientNameTxt.getText().toUpperCase());
                                    pstmt212.setString(5, "");
                                    pstmt212.setString(6, "");
                                    pstmt212.setString(7, "");
                                    pstmt212.setString(8, "OP");
                                    pstmt212.setString(9, jTextField51.getText());
                                    pstmt212.setString(10, paymentModeCmbx.getSelectedItem().toString());
                                    pstmt212.setString(11, jTextField22.getText());
                                    pstmt212.setString(12, receiptNo1);
                                    pstmt212.setString(13, "");
                                    pstmt212.setString(14, "Waiver");
                                    pstmt212.setString(15, "Waiver");
                                    pstmt212.setDouble(16, java.lang.Double.valueOf(waivedAmountTxt.getText().toString()));
                                    pstmt212.setDouble(17, 0.00);
                                    pstmt212.setDate(18, dates);//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmt212.setString(19, transNo1);
                                    pstmt212.setBoolean(20, false);
                                    pstmt212.setBoolean(21, false);
                                    pstmt212.setBoolean(22, false);
                                    pstmt212.setString(23, user);
                                    pstmt212.setString(24, cashPointTxt.getText());
                                    pstmt212.setString(25, shiftNoTxt.getText());
                                    pstmt212.executeUpdate();

                                    pstmt25 = connectDB.prepareStatement("INSERT INTO ac_cash_collection values(?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                                    pstmt25.setObject(1, glAcc1q);
                                    pstmt25.setObject(2, "Waiver");
                                    pstmt25.setString(3, patientNumberTxt.getText());
                                    pstmt25.setString(4, patientNameTxt.getText().toUpperCase());
                                    pstmt25.setString(5, paymentModeCmbx.getSelectedItem().toString());
                                    pstmt25.setString(6, jTextField212.getText());
                                    pstmt25.setString(7, jTextField521.getText());
                                    pstmt25.setString(8, jTextField42.getText());
                                    if (jTextField51.getText().equals("")) {
                                        pstmt25.setString(9, jTextField411.getText());
                                    } else {
                                        pstmt25.setString(9, jTextField51.getText());
                                    }

                                    pstmt25.setString(10, rcode);
                                    pstmt25.setString(11, jTextField22.getText());
                                    pstmt25.setString(12, receiptNo1);
                                    pstmt25.setString(13, exemptionNumberTxt.getText());
                                    pstmt25.setString(14, patientNameTxt.getText().toUpperCase());
                                    pstmt25.setString(15, "Waiver");
                                    pstmt25.setDouble(16, 0.00);
                                    pstmt25.setDouble(17, java.lang.Double.valueOf(waivedAmountTxt.getText().toString()));
                                    pstmt25.setDate(18, dates); //com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmt25.setString(19, "" + transNo1 + "");
                                    pstmt25.setBoolean(20, false);
                                    pstmt25.setBoolean(21, false);
                                    pstmt25.setBoolean(22, false);
                                    pstmt25.setString(23, user);
                                    pstmt25.setString(24, cashPointTxt.getText());
                                    pstmt25.setString(25, shiftNoTxt.getText());
                                    pstmt25.setDate(26, null);
                                    pstmt25.setDouble(27, 1);
                                    pstmt25.setDate(28, null);
                                    pstmt25.setTimestamp(29, datenowSql);
                                    pstmt25.setString(30, mdepartment);
                                    // pstmt25.executeUpdate();
                                    pstmt25.executeUpdate();

                                }
                            }
                        }


                        /*
                             psr = connectDB.prepareStatement("INSERT INTO ac_receipts_no("
                             + "account_no, account_name, date, amount, shift_no, "
                             + "cash_point, reference, user_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                             psr.setString(1, patientNumberTxt.getText());
                             psr.setString(2, patientNameTxt.getText());
                             psr.setDate(3, dates); //com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                             psr.setDouble(4, java.lang.Double.valueOf(billAmountTxt.getText()));
                             psr.setInt(5, java.lang.Integer.valueOf(jTextField811.getText()));
                             psr.setString(6, jTextField141.getText());
                             psr.setString(7, receiptNo1);
                             psr.setString(8, user);
                             psr.executeUpdate();
                         */
                        psr = connectDB.prepareStatement("INSERT INTO ac_receipts_no("
                                + "account_no, account_name, date, amount, shift_no, "
                                + "cash_point, reference) VALUES (?, ?, ?, ?, ?, ?, ?)");
                        psr.setString(1, patientNumberTxt.getText());
                        psr.setString(2, patientNameTxt.getText());
                        psr.setDate(3, dates); //com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                        psr.setDouble(4, java.lang.Double.valueOf(billAmountTxt.getText()));
                        psr.setInt(5, java.lang.Integer.valueOf(shiftNoTxt.getText()));
                        psr.setString(6, cashPointTxt.getText());
                        psr.setString(7, receiptNo1);
                        // psr.setString(8, user);
                        psr.executeUpdate();

                        rst1q = ps1q.executeQuery("SELECT LPAD(receipt_no::TEXT,8,'0'::TEXT) FROM ac_receipts_no WHERE reference = '" + receiptNo1 + "'");
                        while (rst1q.next()) {
                            receiptNo2 = rst1q.getObject(1).toString();
                        }

                        pstmt112 = connectDB.prepareStatement("UPDATE ac_cash_collection SET receipt_no = '" + receiptNo2 + "' WHERE receipt_no = '" + receiptNo1 + "'");
                        pstmt112.executeUpdate();

                        //PreparedStatement pstmt112a = connectDB.prepareStatement(user);
                        PreparedStatement pstmt112a = connectDB.prepareStatement("UPDATE ac_ledger SET receipt_no = '" + receiptNo2 + "' WHERE receipt_no = '" + receiptNo1 + "'");
                        pstmt112a.executeUpdate();

                        pstmt112F = connectDB.prepareStatement("UPDATE ac_debtors SET receipt_no = '" + receiptNo2 + "' WHERE receipt_no = '" + receiptNo1 + "'");
                        pstmt112F.executeUpdate();

//                        for(int i=0;i<jTable111.getRowCount();i++){
//                            if(jTable111.getValueAt(i, 0)!=null){
//                        java.sql.PreparedStatement pstPh = connectDB.prepareStatement("Update hp_pharmacy   set manual_pr ='"+receiptNo2+"' where patient_no ='"+patientNumberTxt.getText()+"' AND description = '" + jTable111.getValueAt(i, 0) + "' AND date_prescribed > current_date - 2");
//                        pstPh.executeUpdate();
//                            }
//                        }
                        for (int i = 0; i < billTable.getRowCount(); i++) {
                            if (billTable.getValueAt(i, 0) != null) {
                                pstmt41d = connectDB.prepareStatement("UPDATE pb_doctors_request SET paid = true WHERE patient_no = '" + patientNumberTxt.getText() + "' AND service = '" + billTable.getValueAt(i, 0) + "' AND trans_date >= current_date - 1");
                                pstmt41d.executeUpdate();
                                pstmt41 = connectDB.prepareStatement("UPDATE hp_pharmacy SET manual_pr='" + receiptNo2 + "', paid = true, collected = false WHERE collected = true and patient_no = '" + patientNumberTxt.getText() + "' AND description = '" + billTable.getValueAt(i, 0) + "' AND date_prescribed > current_date - 2 AND paid = false AND collected = true");
                                pstmt41.executeUpdate();

                                pstmt411 = connectDB.prepareStatement("UPDATE hp_patient_billing SET doctor='" + receiptNo2 + "', paid = true, collected = false WHERE patient_no = '" + patientNumberTxt.getText() + "' AND service = '" + billTable.getValueAt(i, 0) + "' AND trans_date > current_date - 2");
                                pstmt411.executeUpdate();
                            }
                        }

                        if (addedMobilepayTxtTable.getRowCount() > 1) {//If several paymodes
                            for (int i = 0; i < addedMobilepayTxtTable.getRowCount(); i++) {
                                com.afrisoftech.lib.MobilePayments.updateTokenValue(connectDB, addedMobilepayTxtTable.getValueAt(i, 0).toString(), Double.parseDouble(addedMobilepayTxtTable.getValueAt(i, 2).toString()));
                                if (com.afrisoftech.lib.MobilePayments.getTokenValue(connectDB, addedMobilepayTxtTable.getValueAt(i, 0).toString()) <= 1.00) {
                                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("UPDATE mobile_payments SET mobilepay_alert = true WHERE mobile_tx_id = ?");
                                    pstmt2.setString(1, addedMobilepayTxtTable.getValueAt(i, 0).toString());
                                    pstmt2.executeUpdate();
                                    pstmt2.close();
                                }
                            }

                        } else {
                            com.afrisoftech.lib.MobilePayments.updateTokenValue(connectDB, com.afrisoftech.hospital.HospitalMain.mobileTxID, Double.parseDouble(billAmountTxt.getText()));
                            if (com.afrisoftech.lib.MobilePayments.getTokenValue(connectDB, com.afrisoftech.hospital.HospitalMain.mobileTxID) <= 1.00) {
                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("UPDATE mobile_payments SET mobilepay_alert = true WHERE mobile_tx_id = ?");
                                pstmt2.setString(1, com.afrisoftech.hospital.HospitalMain.mobileTxID);
                                pstmt2.executeUpdate();
                                pstmt2.close();
                            }
                        }

                        for (int i = addedMobilepayTxtTable.getRowCount() - 1; i >= 0; i--) {
                            javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) addedMobilepayTxtTable.getModel();
                            defTableModel.removeRow(i);
                        }
                        totalTokensAmountTxt.setText("0.00");
                        mobilePayTokenBalanceTxt.setText("0.00");
                        paymentModeCmbx.setSelectedItem(com.afrisoftech.lib.GLCodesFactory.getDefaultPaymode(connectDB));
                        exemptionChkbx.setEnabled(false);
                        exemptionNumberTxt.setEditable(false);
                        exemptionChkbx.setSelected(false);
                        connectDB.commit();
                        connectDB.setAutoCommit(true);
                        if (processReceipt) {
                            receiptNumberTxt.setText(receiptNo2);
                            postSaleDataBtn.setEnabled(false);
                            generateReceiptBtn.setEnabled(false);
                            reprintReceiptBtn.setEnabled(true);
                        }

                        // Forward to PACs  receiptNo2 instead of transNo for Receipting module
                        if (com.afrisoftech.lib.RadiologyRequestJSON.isPacsEnabled(connectDB)){
                            System.err.println("PACs is enabled.....");
                            if (com.afrisoftech.lib.GetItemInfo.checkRadiologyItems(receiptNo2, patientNumberTxt.getText(), connectDB)) {
                                String patType = null;
                                patType = "OP";
                                com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendRadiologyRequestToPACs(connectDB, "eJGuuIQvhjHiqM5W1f9cFavsH39Wjcs3", receiptNo2, patientNumberTxt.getText(), patientNameTxt.getText(), paymentModeCmbx.getSelectedItem().toString(), null, com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, receiptNo2, patientNumberTxt.getText()), patType, receiptNo2);
                            }
                        }

                        // Forward LIMS requests
                        if (com.afrisoftech.lib.LabRequestJSON.isLIMSEnabled(connectDB)) {
                            if (com.afrisoftech.lib.GetItemInfo.checkLabItems(receiptNo2, patientNumberTxt.getText(), connectDB)) {
                                String limsSystem = com.afrisoftech.lib.LabRequestJSON.getLIMSSystemName(connectDB);
                                if (limsSystem.equalsIgnoreCase("BLIS")) {
                                    com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequestBlis(connectDB, "eJGuuIQvhjHiqM5W1f9cFavsH39Wjcs3", receiptNo2, patientNumberTxt.getText(), patientNameTxt.getText(), paymentModeCmbx.getSelectedItem().toString(), null, com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, receiptNo2, patientNumberTxt.getText()), "OP");
                                } else {
                                    com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequest(connectDB, "bGltc19hY2Nlc3M6MTJAITIzIzQk", receiptNo2, patientNumberTxt.getText(), patientNameTxt.getText(), paymentModeCmbx.getSelectedItem().toString(), null, com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, receiptNo2, patientNumberTxt.getText()), "OUT");
                                }

                            }
                        }

                    } catch (java.sql.SQLException sq) {
                        sq.printStackTrace();
                        try {
                            connectDB.rollback(registerSavePoint);
                        } catch (java.sql.SQLException sql) {
                            javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }

                        System.out.println(sq.getMessage());
                        javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                    }

                }
            }

        }

    }

    public void run() {
        while (true) {
            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(status) FROM ac_shifts WHERE status ilike 'running' AND user_name = current_user");
                java.sql.ResultSet rset = pstmt.executeQuery();
                int i = 0;
                while (rset.next()) {
                    i = rset.getInt(1);
                }
                if (i < 1) {
                    this.dispose();
                    javax.swing.JOptionPane.showMessageDialog(this, "You MUST open a new shift in order to continue.\n Please close Front End Operations and open afresh.");
                }
                rset.close();
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "ERROR: Can't close the cash collection form : Details : " + ex.getMessage());
                //Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "ERROR: Can't close the cash collection form : Details : " + ex.getMessage());

                // Logger.getLogger(CafeteriaBillPaymentsIntfr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addMobileBtn;
    private javax.swing.JDialog addedMobileTxDialogue;
    private javax.swing.JTable addedMobilepayTxtTable;
    private javax.swing.JTextField amountPaidTxt;
    private javax.swing.JTextField billAmountTxt;
    private javax.swing.JTable billTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField cashPointTxt;
    private javax.swing.JTextField changeTxt;
    private javax.swing.JButton clearFormBtn;
    private javax.swing.JCheckBox clinicChbx;
    public javax.swing.JButton closePosBtn;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JButton dispose11;
    private javax.swing.JCheckBox exemptionChkbx;
    private javax.swing.JLabel exemptionNumberLbl;
    private javax.swing.JTextField exemptionNumberTxt;
    public static javax.swing.JButton generateReceiptBtn;
    private javax.swing.JTextField glAccountTxt;
    private javax.swing.JButton helpBtn;
    private javax.swing.JButton jButton1111;
    private javax.swing.JButton jButton252111;
    private javax.swing.JButton jButton301111;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton421;
    private javax.swing.JButton jButton422;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton521;
    private javax.swing.JButton jButton522;
    private javax.swing.JButton jButton523;
    private javax.swing.JButton jButton524;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton91;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    public javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JComboBox jComboBox111;
    private javax.swing.JDialog jDialog31;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel1311;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel1411;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel2121;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3111;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel4112;
    private javax.swing.JLabel jLabel422;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5311;
    private javax.swing.JLabel jLabel5821111;
    private javax.swing.JLabel jLabel582211;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66111;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel1111;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel211;
    private javax.swing.JPanel jPanel311;
    private javax.swing.JPanel jPanel312;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane211;
    private javax.swing.JDialog jSearchDialog;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JDialog jSearchDialog11;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JPanel jSearchPanel22;
    private javax.swing.JPanel jSearchPanel23;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JScrollPane jSearchScrollPane22;
    private javax.swing.JScrollPane jSearchScrollPane23;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JSeparator jSeparator111;
    private javax.swing.JSeparator jSeparator1111;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField112;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField14111;
    private javax.swing.JTextField jTextField151;
    private javax.swing.JTextField jTextField1611;
    private javax.swing.JTextField jTextField212;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField3111;
    private javax.swing.JTextField jTextField411;
    private javax.swing.JTextField jTextField4111;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField5111;
    private javax.swing.JTextField jTextField521;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField lessDiscountedAmountTxt;
    private javax.swing.JCheckBox mchfpChbx;
    private javax.swing.JTextField mobilePayTokenBalanceTxt;
    private javax.swing.JTextField mobilepayTxNoTxt;
    private javax.swing.JDialog mobilepayTxSearchDialog;
    private javax.swing.JTextField mobilepayTxSearchTxt;
    private javax.swing.JTable mobilepayTxtSearchTable;
    private javax.swing.JTextField patientNameTxt;
    private javax.swing.JTextField patientNumberTxt;
    private javax.swing.JDialog patientSearchDialog;
    private javax.swing.JPanel patientSearchPanel;
    private javax.swing.JScrollPane patientSearchScrollPane;
    private javax.swing.JTable patientSearchTable;
    private javax.swing.JTextField patientSearchTxt;
    private javax.swing.JComboBox<String> paybillNumberCmbx;
    private javax.swing.JFormattedTextField payerMobileTelephoneNumberTxt;
    private javax.swing.JComboBox paymentModeCmbx;
    private javax.swing.JButton postSaleDataBtn;
    private javax.swing.JTextField receiptNumberTxt;
    private javax.swing.JButton removeRowBtn;
    private javax.swing.JButton reprintReceiptBtn;
    private javax.swing.JButton searchButton13;
    private javax.swing.JPanel searchMobilePayTxPanel;
    private javax.swing.JButton searchMobileTxButton;
    private javax.swing.JCheckBox searchNameChbx;
    private javax.swing.JCheckBox searchNoChbx;
    private javax.swing.JButton searchPatientButton;
    private javax.swing.JPanel searchPatientPanel;
    private javax.swing.JTextField shiftNoTxt;
    private javax.swing.JButton stkPushBtn;
    private javax.swing.JTextField totalBilledAmountTxt;
    private javax.swing.JTextField totalTokensAmountTxt;
    private javax.swing.JTextField unitNumberTxt;
    public javax.swing.JButton viewMobileBtn;
    private javax.swing.JTextField waivedAmountTxt;
    private javax.swing.JCheckBox walkINChkbx;
    // End of variables declaration//GEN-END:variables
}
