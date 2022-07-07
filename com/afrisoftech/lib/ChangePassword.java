/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class ChangePassword extends javax.swing.JDialog {
    
    int reportName;
    java.sql.Connection connectDB = null;
    java.lang.String secrets = null;

    /**
     * Creates new form DatePanel
     */
    public ChangePassword(java.awt.Frame parent, boolean modal, java.sql.Connection connectDb, java.lang.String secreta) {
        
        super(parent, modal);
        
        connectDB = connectDb;
        
        secrets = secreta;
        //      dateStartEnd = new java.util.Vector(1,1);
        
        initComponents();

        //       return dateStartEnd; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        passwordChangeScreenPanel = new javax.swing.JPanel();
        newPasswordLabel = new javax.swing.JLabel();
        newRetypedPasswordLabel = new javax.swing.JLabel();
        currentPasswordLabel = new javax.swing.JLabel();
        currentPasswordField = new javax.swing.JPasswordField();
        newPasswordField = new javax.swing.JPasswordField();
        newRetypedPasswordField = new javax.swing.JPasswordField();
        headerPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        changePasswordButton = new javax.swing.JButton();

        setTitle("Changing User password");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        passwordChangeScreenPanel.setBackground(new java.awt.Color(230, 230, 230));
        passwordChangeScreenPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        passwordChangeScreenPanel.setLayout(new java.awt.GridBagLayout());

        newPasswordLabel.setText("New Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        passwordChangeScreenPanel.add(newPasswordLabel, gridBagConstraints);

        newRetypedPasswordLabel.setText("Retype New Pasword");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        passwordChangeScreenPanel.add(newRetypedPasswordLabel, gridBagConstraints);

        currentPasswordLabel.setText("Current Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        passwordChangeScreenPanel.add(currentPasswordLabel, gridBagConstraints);

        currentPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                currentPasswordFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        passwordChangeScreenPanel.add(currentPasswordField, gridBagConstraints);

        newPasswordField.setToolTipText("Must be aleast 8 characters long.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        passwordChangeScreenPanel.add(newPasswordField, gridBagConstraints);

        newRetypedPasswordField.setToolTipText("Same as \"New Password\" above.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        passwordChangeScreenPanel.add(newRetypedPasswordField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        getContentPane().add(passwordChangeScreenPanel, gridBagConstraints);

        headerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        headerPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Change your password here.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(headerPanel, gridBagConstraints);

        actionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        actionsPanel.setLayout(new java.awt.GridBagLayout());

        closeButton.setMnemonic('n');
        closeButton.setText("Cancel");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(closeButton, gridBagConstraints);

        changePasswordButton.setMnemonic('C');
        changePasswordButton.setText("Confirm");
        changePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(changePasswordButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(actionsPanel, gridBagConstraints);

        setSize(new java.awt.Dimension(366, 219));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        
        this.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed
    
    private void currentPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_currentPasswordFieldFocusLost
        
        java.lang.String currPassWord = null;
        
        currPassWord = this.currentPasswordField.getText();
        
        if (!(currPassWord.equals(secrets))) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "You have typed your current password incorrectly");

            //       jPasswordField1.grabFocus();
            
        }


        // Add your handling code here:
    }//GEN-LAST:event_currentPasswordFieldFocusLost
    
    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordButtonActionPerformed
        if (newPasswordField.getPassword().length > 7 && newRetypedPasswordField.getPassword().length > 7) {
            java.lang.String currPassWord = null;
            
            currPassWord = this.currentPasswordField.getText();
            
            java.lang.String currentUser = null;
            
            if (currPassWord.equals(secrets)) {
                
                
                if (this.newPasswordField.getText().equals(this.newRetypedPasswordField.getText())) {
                    
                    try {
                        
                        java.sql.Statement stmt1 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset1 = stmt1.executeQuery("SELECT CURRENT_USER");
                        
                        while (rset1.next()) {
                            
                            currentUser = rset1.getString(1);
                            
                        }

//                     java.sql.PreparedStatement pstmt = connectDB.prepareStatement("ALTER USER "+currentUser+" WITH PASSWORD '"+jPasswordField2.getText()+"'"); 
                        
                        java.sql.Statement stmt = connectDB.createStatement();
                        
                        stmt.executeUpdate("ALTER USER " + currentUser + " WITH PASSWORD '" + newPasswordField.getText() + "'");
                        
                        java.sql.PreparedStatement pstmtUpdatePassswd = connectDB.prepareStatement("UPDATE passwd_sop SET change_time = now(),changing_user=current_user WHERE user_name = current_user");
                        
                        pstmtUpdatePassswd.executeUpdate();
                        
                        java.sql.PreparedStatement pstmtChangeControl = connectDB.prepareStatement("INSERT INTO account_access_control("
                                + "           account_change_type, user_account_affected"
                                + "            )"
                                + "    VALUES (?, ?)"); 
                                pstmtChangeControl.setString(1, "CHANGE_PASSWORD");
                                pstmtChangeControl.setString(2, currentUser);
                                pstmtChangeControl.execute();
                        //                        pstmt.executeUpdate();
            
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Password changed for user : " + currentUser);
                    com.afrisoftech.hospital.HospitalMain.passwordChange = true;
                        
                        this.dispose();
                        
                    } catch (java.sql.SQLException SQLExec) {
                        
                        SQLExec.printStackTrace();
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SQLExec.getLocalizedMessage());
                        
                    }
                    
                } else {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "New password must be confirmed by an identical entry on the Retyped password entry.");
                    
                }
                
            } else {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "You hava typed your current password incorrectly.");
                
            }
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please note that the password MUST be atleast 8 characters.");
        }

        // Add your handling code here:
    }//GEN-LAST:event_changePasswordButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //  new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JButton changePasswordButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JLabel currentPasswordLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JPasswordField newRetypedPasswordField;
    private javax.swing.JLabel newRetypedPasswordLabel;
    private javax.swing.JPanel passwordChangeScreenPanel;
    // End of variables declaration//GEN-END:variables
}