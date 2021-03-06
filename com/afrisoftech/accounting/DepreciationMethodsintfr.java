/*
 * branchintfr.java
 *
 * Created on August 13, 2002, 10:14 AM
 */
package com.afrisoftech.accounting;

/**
 *
 * @author root
 */
public class DepreciationMethodsintfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    public DepreciationMethodsintfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        connectDB = connDb;

        pConnDB = pconnDB;

        initComponents();
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        registerDepreciationMethodBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        depreciationPanel = new javax.swing.JPanel();
        reducingInstalmentsChkbx = new javax.swing.JCheckBox();
        straightLineChkbx = new javax.swing.JCheckBox();
        selectPeriodicityPanel = new javax.swing.JPanel();
        quartelyRbtn = new javax.swing.JRadioButton();
        yearlyRbtn = new javax.swing.JRadioButton();
        monthlyRbtn = new javax.swing.JRadioButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Setting up depreciation methods");
        setDoubleBuffered(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ColorPreview.gif"))); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        registerDepreciationMethodBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/check.gif"))); // NOI18N
        registerDepreciationMethodBtn.setMnemonic('O');
        registerDepreciationMethodBtn.setText("Register depreciation method");
        registerDepreciationMethodBtn.setToolTipText("Click here to enter data");
        registerDepreciationMethodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerDepreciationMethodBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(registerDepreciationMethodBtn, gridBagConstraints);

        closeFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BD14755_.GIF"))); // NOI18N
        closeFormBtn.setMnemonic('C');
        closeFormBtn.setText("Close form");
        closeFormBtn.setToolTipText("Click here to close window");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        closeFormBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeFormBtnMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(closeFormBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel4, gridBagConstraints);

        depreciationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tick depreciation mehod here"));
        depreciationPanel.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(reducingInstalmentsChkbx);
        reducingInstalmentsChkbx.setText("Reducing Instalments Method");
        reducingInstalmentsChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        depreciationPanel.add(reducingInstalmentsChkbx, gridBagConstraints);

        buttonGroup1.add(straightLineChkbx);
        straightLineChkbx.setText("Straight Line Method");
        straightLineChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        depreciationPanel.add(straightLineChkbx, gridBagConstraints);

        selectPeriodicityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        selectPeriodicityPanel.setLayout(new java.awt.GridBagLayout());

        buttonGroup2.add(quartelyRbtn);
        quartelyRbtn.setText("Quartely");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        selectPeriodicityPanel.add(quartelyRbtn, gridBagConstraints);

        buttonGroup2.add(yearlyRbtn);
        yearlyRbtn.setText("Yearly");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        selectPeriodicityPanel.add(yearlyRbtn, gridBagConstraints);

        buttonGroup2.add(monthlyRbtn);
        monthlyRbtn.setText("Monthly");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        selectPeriodicityPanel.add(monthlyRbtn, gridBagConstraints);

        depreciationPanel.add(selectPeriodicityPanel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(depreciationPanel, gridBagConstraints);

        setBounds(0, 0, 600, 199);
    }// </editor-fold>//GEN-END:initComponents

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed
        this.setVisible(false);// Add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //try {
        this.getDesktopPane().setSelectedFrame(this);
        //}catch (java.beans.PropertyVetoException pr){
        //    System.out.println("Not Selected");
        //}
        // Add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void registerDepreciationMethodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerDepreciationMethodBtnActionPerformed
        String selectedchkbx;

        if (this.reducingInstalmentsChkbx.isSelected()) {

            selectedchkbx = reducingInstalmentsChkbx.getText();

        } else {

            selectedchkbx = straightLineChkbx.getText();
        }

        String depreciationDuration;

        if (this.yearlyRbtn.isSelected()) {
            depreciationDuration = yearlyRbtn.getText();

        } else {
            // if (this.jCheckBox3.isSelected())
            depreciationDuration = monthlyRbtn.getText();
        }

        System.out.println(depreciationDuration);

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql = new java.sql.Date(dateNow);

        System.out.println(datenowSql.toString());

        java.sql.Connection con;

        boolean isRegistered = false;

        try {
            int caution = javax.swing.JOptionPane.showConfirmDialog(this, "ARE YOU SURE THAT YOU WANT TO CHANGE THE DEPRECIATION METHOD!");
            if (caution == 0) {
                java.sql.Statement checkStmt = connectDB.createStatement();
                java.sql.ResultSet rsetCheck = checkStmt.executeQuery("SELECT count(*) FROM depreciation_method");
                while (rsetCheck.next()) {
                    if (rsetCheck.getInt(1) > 0) {
                        isRegistered = true;
                    }
                }
                if (!isRegistered) {
                    java.sql.PreparedStatement pstmtInsert = connectDB.prepareStatement("insert into depreciation_method values(initcap(?), ?)");
                    pstmtInsert.setString(1, selectedchkbx);
                    pstmtInsert.setString(2, depreciationDuration);
                    pstmtInsert.executeUpdate();
                    jLabel4.setForeground(java.awt.Color.blue);
                    jLabel4.setText("Insert successful");
                } else {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("begin work;"
                            + " update depreciation_method set depreciation_method = '" + selectedchkbx + "', "
                            + "deprection_period = '" + depreciationDuration + "';commit work");
                    //java.sql.PreparedStatement pstmt = con.prepareStatement ("begin work; update shares_receiptsb set deposit = (deposit + '"+jTextField3.getText()+"' + '"+jTextField12.getText()+"') where code = '"+jComboBox1.getSelectedItem()+"'and deposit = '"+jCheckBox1.getText().toString()+"';commit work");
                    pstmt.executeUpdate();
                    jLabel4.setForeground(java.awt.Color.blue);
                    jLabel4.setText("Update successful");
                }
            } else {
                jLabel4.setForeground(java.awt.Color.blue);
                jLabel4.setText("Exisiting depreciation method retained");
            }
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

        // Add your handling code here:
    }//GEN-LAST:event_registerDepreciationMethodBtnActionPerformed

    private void closeFormBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFormBtnMouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_closeFormBtnMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton closeFormBtn;
    private javax.swing.JPanel depreciationPanel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton monthlyRbtn;
    private javax.swing.JRadioButton quartelyRbtn;
    private javax.swing.JCheckBox reducingInstalmentsChkbx;
    private javax.swing.JButton registerDepreciationMethodBtn;
    private javax.swing.JPanel selectPeriodicityPanel;
    private javax.swing.JCheckBox straightLineChkbx;
    private javax.swing.JRadioButton yearlyRbtn;
    // End of variables declaration//GEN-END:variables
}
