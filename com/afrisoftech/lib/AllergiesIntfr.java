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
public class AllergiesIntfr extends javax.swing.JDialog {

    int reportName;
    java.sql.Connection connectDB = null;
    
    /**
     * Creates new form DatePanel
     */
    public AllergiesIntfr(java.awt.Frame parent, boolean modal, java.sql.Connection connectDb, java.lang.String patNo, String patName) {

        super(parent, modal);

        connectDB = connectDb;

      
        //      dateStartEnd = new java.util.Vector(1,1);

        initComponents();
         patNoTxt.setText(patNo);
       patNameTxt.setText(patName);

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

        patientSearch = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton522 = new javax.swing.JButton();
        passwordChangeScreenPanel = new javax.swing.JPanel();
        newPasswordLabel = new javax.swing.JLabel();
        currentPasswordLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        allergiesTable = new com.afrisoftech.dbadmin.JTable();
        patNameTxt = new javax.swing.JTextField();
        patNoTxt = new javax.swing.JTextField();
        headerPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        patientSearch.setModal(true);
        patientSearch.setUndecorated(true);
        patientSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel2.add(jSearchScrollPane2, gridBagConstraints);

        jButton522.setText("Dispose");
        jButton522.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton522ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton522, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        patientSearch.getContentPane().add(jSearchPanel2, gridBagConstraints);

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

        newPasswordLabel.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        passwordChangeScreenPanel.add(newPasswordLabel, gridBagConstraints);

        currentPasswordLabel.setText("Patient No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        passwordChangeScreenPanel.add(currentPasswordLabel, gridBagConstraints);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Allergies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(153, 153, 0))); // NOI18N

        allergiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Description"
            }
        ));
        allergiesTable.setRowHeight(25);
        allergiesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allergiesTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(allergiesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 2);
        passwordChangeScreenPanel.add(jScrollPane5, gridBagConstraints);

        patNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        passwordChangeScreenPanel.add(patNameTxt, gridBagConstraints);

        patNoTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        passwordChangeScreenPanel.add(patNoTxt, gridBagConstraints);

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
        jLabel3.setText("Record Patient Allergies");
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(closeButton, gridBagConstraints);

        saveButton.setMnemonic('C');
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(saveButton, gridBagConstraints);

        jButton32.setMnemonic('l');
        jButton32.setText("Remove Row");
        jButton32.setToolTipText("Click here to clear textfields");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(jButton32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(actionsPanel, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comments"));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        setSize(new java.awt.Dimension(618, 461));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        this.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (!patNoTxt.getText().isEmpty()) {

            try {
                for (int i = 0; i < allergiesTable.getRowCount(); i++) {

                    if (allergiesTable.getValueAt(i, 0) != null) {
                        java.sql.PreparedStatement pstmtChangeControl = connectDB.prepareStatement("INSERT INTO hp_patient_allergies(\n"
                                + "            patient_no, patient_name, reaction_type, description, comments)   VALUES (?, ?, ?, ?,  ?);");
                        pstmtChangeControl.setString(1, patNoTxt.getText());
                        pstmtChangeControl.setString(2, patNameTxt.getText());
                        pstmtChangeControl.setString(3, "Allergy");
                        pstmtChangeControl.setString(4, allergiesTable.getValueAt(i, 0).toString());
                        pstmtChangeControl.setString(5, jTextArea1.getText());

                        pstmtChangeControl.executeUpdate();
                    }
                }
                //                        pstmt.executeUpdate();

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Insert Successful ");
                
                patNoTxt.setText("");
                patNameTxt.setText("");
                jTextArea1.setText("");
                
                this.dispose();

            } catch (java.sql.SQLException SQLExec) {

                SQLExec.printStackTrace();

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SQLExec.getLocalizedMessage());

            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please note that patient No cannot be empty.");
        }

        // Add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void allergiesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allergiesTableMouseClicked
        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.allergiesTable.getLocationOnScreen();

        patientSearch.setSize(400, 200);

        patientSearch.setLocation(point);

        patientSearch.setVisible(true);
    }//GEN-LAST:event_allergiesTableMouseClicked

    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        if (jTextField113.getText().length() > 2) {
           
            jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                "    SELECT DISTINCT component FROM hp_drug_components WHERE component ILIKE '%" + jTextField113.getText() + "%' UNION SELECT allergy_short_description FROM  pb_patient_allergies WHERE allergy_short_description ILIKE '%" + jTextField113.getText() + "%' ORDER BY 1 "));
        jSearchTable2.setShowHorizontalLines(false);

    

        jSearchScrollPane2.setViewportView(jSearchTable2);
        }
        // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
            
            allergiesTable.setValueAt((jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString()), allergiesTable.getSelectedRow(),0);
            
            patientSearch.dispose();
        
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked

    private void jButton522ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton522ActionPerformed
        this.patientSearch.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton522ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        int rows2Delete = allergiesTable.getSelectedRowCount();

        int[] selectedRows = allergiesTable.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            //javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");
        } else {
                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) allergiesTable.getModel();
                defTableModel.removeRow(allergiesTable.getSelectedRow());
            
        }

        // Add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //  new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JTable allergiesTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel currentPasswordLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton522;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JPanel passwordChangeScreenPanel;
    private javax.swing.JTextField patNameTxt;
    private javax.swing.JTextField patNoTxt;
    private javax.swing.JDialog patientSearch;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}