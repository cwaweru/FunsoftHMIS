/*
 * countryintfr.java
 *
 * Created on August 13, 2002, 12:15 PM
 */

package com.afrisoftech.accounting;

import java.sql.SQLException;

/**
 *
 * @author  root
 */
public class ReceiptPrefsIntfr extends javax.swing.JInternalFrame {
    
    /** Creates new form countryintfr */
    java.sql.Connection connectDB = null;
    
    java.util.Properties appProps;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public ReceiptPrefsIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, java.util.Properties props) {
        
        connectDB = connDb;
        
        appProps = props;
        
        pConnDB = pconnDB;
        
        initComponents();
        
        fetchNewPreferences();
/*
        jTextField2.setText(System.getProperty("receiptPageMargin"));
 
        jTextField1.setText(System.getProperty("receiptTitleFontSize"));
 
        jTextField11.setText(System.getProperty("receiptFontSize"));
 
        jTextField12.setText(System.getProperty("papersize_width"));
 
        jTextField4.setText(System.getProperty("papersize_legnth"));
 */
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        paperSizeLegnthTxt = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        paperSizewidthTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        java.awt.Font[] fontList = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

        java.util.Set fontSet = com.lowagie.text.FontFactory.getRegisteredFonts();
        /*

        java.util.Vector fontVector = new java.util.Vector(1,1);

        for (int i = 0; i < fontList.length; i++) {

            fontVector.addElement(fontList[i].getFontName());

        }
        */
        java.lang.Object[] fontNameArray = fontSet.toArray();
        jComboBoxFont = new javax.swing.JComboBox(fontNameArray);

        linesPerPageTxt = new javax.swing.JTextField();
        charactersPerLineTxt = new javax.swing.JTextField();
        linesPerPageLbl = new javax.swing.JLabel();
        charactersPerLineLbl = new javax.swing.JLabel();
        rcptLinesPerPageLbl = new javax.swing.JLabel();
        rcptCharsPerLineLbl = new javax.swing.JLabel();
        rcptLinesPerPageTxt = new javax.swing.JTextField();
        rcptCharsPerLineTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        InvoicesizeXaxis = new javax.swing.JTextField();
        InvoiceSizeYaxis = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ReceiptSizeXaxis = new javax.swing.JTextField();
        ReceiptSizeYaxis = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Receipt Preferences");
        setFrameIcon(null);
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jButton1.setMnemonic('v');
        jButton1.setText("Save Preferences");
        jButton1.setToolTipText("Click here to enter data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton1, gridBagConstraints);

        jButton3.setMnemonic('l');
        jButton3.setText("Clear Values");
        jButton3.setToolTipText("Click here to clear fields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.setToolTipText("Click here to close");
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton4, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel3, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter Receipt Preferences"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Page Size Width (Points)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel2.setText("Title Font Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel4.setText("Margin Allowance (Points)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel6.setText("Body Text Font Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Page Size Length (Points)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(paperSizeLegnthTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jTextField11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(paperSizewidthTxt, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("HINT:  Note that all dimension entries are in points where 1 Inch = 72 Points and 1 CentiMetre = 30 Points.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel8.setText("Font Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jComboBoxFont, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(linesPerPageTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(charactersPerLineTxt, gridBagConstraints);

        linesPerPageLbl.setText("Lines Per Page");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(linesPerPageLbl, gridBagConstraints);

        charactersPerLineLbl.setText("Characters Per Line");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(charactersPerLineLbl, gridBagConstraints);

        rcptLinesPerPageLbl.setText("Receipt Lines Per Page");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(rcptLinesPerPageLbl, gridBagConstraints);

        rcptCharsPerLineLbl.setText("Receipt Characters Per Line");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(rcptCharsPerLineLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(rcptLinesPerPageTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(rcptCharsPerLineTxt, gridBagConstraints);

        jLabel9.setText("Invoice Size X-Axis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Invoice Size Y-Axis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel1.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(InvoicesizeXaxis, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(InvoiceSizeYaxis, gridBagConstraints);

        jLabel11.setText("Receipt Size X-Axis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Receipt Size Y-Axis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(ReceiptSizeXaxis, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(ReceiptSizeYaxis, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Print Header");
        jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jRadioButton1, gridBagConstraints);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Do Not Print Header");
        jRadioButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jRadioButton2, gridBagConstraints);

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("Hide Details");
        jRadioButton3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButton3, gridBagConstraints);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Pdf");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jRadioButton4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRadioButton4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButton4, gridBagConstraints);

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Txt");
        jRadioButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jRadioButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButton5, gridBagConstraints);

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("Print per department");
        jRadioButton6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButton6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButton6, gridBagConstraints);

        buttonGroup3.add(jRadioButton7);
        jRadioButton7.setText("Show Details");
        jRadioButton7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButton7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel1, gridBagConstraints);

        jButton5.setMnemonic('H');
        jButton5.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton5, gridBagConstraints);
        getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

        setBounds(0, 0, 741, 413);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String rHeader = null;
        String rHeaders = null;
        String Format = null;
        if (jRadioButton1.isSelected()){
            rHeader = "True";
        }else{
            if (jRadioButton2.isSelected()){
                rHeader = "False";
                
            }
        }
        if (jRadioButton3.isSelected()){
            rHeaders = "NoDetails";
        }else{
            if (jRadioButton7.isSelected()){
                rHeaders = "Prints";
            }else{
                if (jRadioButton6.isSelected()){
                    rHeaders = "Dept";
                }
            }
        }
        
        if (jRadioButton4.isSelected()){
            Format = jRadioButton4.getText();
        }else{
            Format = jRadioButton5.getText();
        }
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement("delete from receipt_pref");
            pstmt21.executeUpdate();
            java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into receipt_pref values(?,?,?,?,?,?,?,?,?,?)");
            pstmt2.setObject(1,jTextField1.getText());
            pstmt2.setDouble(2,java.lang.Double.valueOf(jTextField11.getText()));
            pstmt2.setDouble(3,java.lang.Double.valueOf(paperSizewidthTxt.getText()));
            pstmt2.setDouble(4,java.lang.Double.valueOf(paperSizeLegnthTxt.getText()));
            pstmt2.setDouble(5,java.lang.Double.valueOf(jTextField2.getText()));
            pstmt2.setInt(6,java.lang.Integer.valueOf(linesPerPageTxt.getText()));
            pstmt2.setInt(7,java.lang.Integer.valueOf(charactersPerLineTxt.getText()));
            pstmt2.setString(8,rHeader);
            pstmt2.setString(9,rHeaders);
            pstmt2.setString(10,Format);
            pstmt2.executeUpdate();
            
            
            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this,"Data Saved Successfully","Comfirmation Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
        }catch(java.sql.SQLException sq){
            
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        System.setProperty("linesperpage",linesPerPageTxt.getText());
        
        System.setProperty("charactersperline",charactersPerLineTxt.getText());
        
        appProps.setProperty("linesperpage",linesPerPageTxt.getText());
        
        appProps.setProperty("charactersperline",charactersPerLineTxt.getText());
        
        System.setProperty("rcptlinesperpage",rcptLinesPerPageTxt.getText());
        
        System.setProperty("rcptcharactersperline",charactersPerLineTxt.getText());
        
        appProps.setProperty("rcptlinesperpage",rcptLinesPerPageTxt.getText());
        
        appProps.setProperty("rcptcharactersperline",rcptCharsPerLineTxt.getText());
        
        System.setProperty("receipt_textpagesize_y", ReceiptSizeYaxis.getText());
        
        System.setProperty("receipt_textpagesize_x", ReceiptSizeXaxis.getText());
        
        appProps.setProperty("receipt_textpagesize_y",ReceiptSizeYaxis.getText());
        
        appProps.setProperty("receipt_textpagesize_x",ReceiptSizeXaxis.getText());
        
        System.setProperty("invoice_textpagesize_y", InvoiceSizeYaxis.getText());

        System.setProperty("invoice_textpagesize_x", InvoicesizeXaxis.getText());

        appProps.setProperty("invoice_textpagesize_y",InvoiceSizeYaxis.getText());

        appProps.setProperty("invoice_textpagesize_x",InvoicesizeXaxis.getText());

        appProps.setProperty("papersize_width",paperSizewidthTxt.getText());

        appProps.setProperty("papersize_legnth",paperSizeLegnthTxt.getText());     
               
        System.setProperty("papersize_width", paperSizewidthTxt.getText());

        System.setProperty("papersize_legnth", paperSizeLegnthTxt.getText());
          
        com.afrisoftech.hospital.HospitalMain.storePreferences();   

        this.fetchNewPreferences();
        

        

        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        paperSizewidthTxt.setText("");
        jTextField11.setText("");
        paperSizeLegnthTxt.setText("");
        
        fetchNewPreferences();
        
        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked
    
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked
    
    private void fetchNewPreferences() {
        
        jTextField2.setText(System.getProperty("receiptPageMargin"));
        
        jTextField1.setText(System.getProperty("receiptTitleFontSize"));
        
        jTextField11.setText(System.getProperty("receiptFontSize"));
        
        paperSizewidthTxt.setText(System.getProperty("papersize_width"));
        
        paperSizeLegnthTxt.setText(System.getProperty("papersize_legnth"));
        
        linesPerPageTxt.setText(System.getProperty("linesperpage"));
        
        charactersPerLineTxt.setText(System.getProperty("charactersperline"));
        
        rcptLinesPerPageTxt.setText(System.getProperty("rcptlinesperpage"));
        
        rcptCharsPerLineTxt.setText(System.getProperty("rcptcharactersperline"));
        
        InvoicesizeXaxis.setText(System.getProperty("invoice_textpagesize_x"));
        
        ReceiptSizeXaxis.setText(System.getProperty("receipt_textpagesize_x"));
        
        InvoiceSizeYaxis.setText(System.getProperty("invoice_textpagesize_y"));
        
        ReceiptSizeYaxis.setText(System.getProperty("receipt_textpagesize_y"));
        
        java.sql.PreparedStatement pstmt;
        
        try {
            pstmt = connectDB.prepareStatement("SELECT print_header, nodetails, rct_format FROM receipt_pref");
            
            
            java.sql.ResultSet rSet = pstmt.executeQuery();
            
            while(rSet.next()){
                if(rSet.getBoolean(1)){
                    jRadioButton1.setSelected(true);
                }
                if(!rSet.getBoolean(1)){
                    jRadioButton2.setSelected(true);
                }

                if(rSet.getString(1).equalsIgnoreCase("nodetails")){
                    jRadioButton3.setSelected(true);
                }
                if(!rSet.getString(3).equalsIgnoreCase("txt")){
                    jRadioButton4.setSelected(true);
                }
                else {
                    jRadioButton5.setSelected(true);
                }
                if(!rSet.getString(1).equalsIgnoreCase("nodetails")){
                    jRadioButton7.setSelected(true);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
//    jComboBoxFont.setSelectedItem((java.lang.Object)System.getProperty("font_type_name"));
        
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField InvoiceSizeYaxis;
    private javax.swing.JTextField InvoicesizeXaxis;
    private javax.swing.JTextField ReceiptSizeXaxis;
    private javax.swing.JTextField ReceiptSizeYaxis;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel charactersPerLineLbl;
    private javax.swing.JTextField charactersPerLineTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBoxFont;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel linesPerPageLbl;
    private javax.swing.JTextField linesPerPageTxt;
    private javax.swing.JTextField paperSizeLegnthTxt;
    private javax.swing.JTextField paperSizewidthTxt;
    private javax.swing.JLabel rcptCharsPerLineLbl;
    private javax.swing.JTextField rcptCharsPerLineTxt;
    private javax.swing.JLabel rcptLinesPerPageLbl;
    private javax.swing.JTextField rcptLinesPerPageTxt;
    // End of variables declaration//GEN-END:variables
    
}