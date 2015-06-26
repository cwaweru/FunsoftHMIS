/*
 * DefaultInternalFrame.java
 *
 * Created on May 31, 2004, 1:53 PM
 */

package com.afrisoftech.hr;

/**
 *
 * @author  postgres
 */
public class BranchesIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    javax.swing.table.TableModel tableModel = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    /** Creates new form DefaultInternalFrame */
    public BranchesIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
        nBCachedRowSet1.setCommand("Select distinct(location_name ) from hr.hr_geographical_locations");
        nBCachedRowSet1.setConnectionSource(pConnDB);
        
        geographicallocationCbx.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (nBCachedRowSet1, "location_name", null, null, null));
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        try {
            nBCachedRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
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
        subMainPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldsPanel = new javax.swing.JPanel();
        branchidLbl = new javax.swing.JLabel();
        branchidTxt = new javax.swing.JTextField();
        branchnameLbl = new javax.swing.JLabel();
        branchnameTxt = new javax.swing.JTextField();
        geographicallocationLbl = new javax.swing.JLabel();
        geographicallocationCbx = new javax.swing.JComboBox();
        dataPanel = new javax.swing.JPanel();
        firstAction = new javax.swing.JButton();
        previousAction = new javax.swing.JButton();
        nextAction = new javax.swing.JButton();
        lastAction = new javax.swing.JButton();

        nBCachedRowSet1.setConnectionSource(pConnDB);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Branches");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel.setLayout(new java.awt.GridBagLayout());

        imgPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel.setLayout(new java.awt.GridBagLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif"))); // NOI18N
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

        actionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel.setLayout(new java.awt.GridBagLayout());

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
        updateAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(updateAction, gridBagConstraints);

        deleteAction.setMnemonic('D');
        deleteAction.setText("Delete");
        deleteAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(deleteAction, gridBagConstraints);

        clearAction.setMnemonic('C');
        clearAction.setText("Clear");
        clearAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionActionPerformed(evt);
            }
        });
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

        helpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel.setLayout(new java.awt.GridBagLayout());

        helpAction.setMnemonic('H');
        helpAction.setText("Help");
        helpAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionActionPerformed(evt);
            }
        });
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

        subMainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        subMainPanel.setLayout(new java.awt.GridBagLayout());

        buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Please register branches here");
        buttonPanel.add(jLabel1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(buttonPanel, gridBagConstraints);

        fieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data manipulation utility"));
        fieldsPanel.setLayout(new java.awt.GridBagLayout());

        branchidLbl.setText("Branch ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(branchidLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(branchidTxt, gridBagConstraints);

        branchnameLbl.setText("Branch Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(branchnameLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(branchnameTxt, gridBagConstraints);

        geographicallocationLbl.setText("Geographical Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(geographicallocationLbl, gridBagConstraints);

        geographicallocationCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geographicallocationCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(geographicallocationCbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel.add(fieldsPanel, gridBagConstraints);

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data navagation actions"));
        dataPanel.setLayout(new java.awt.GridBagLayout());

        firstAction.setMnemonic('F');
        firstAction.setText("First");
        firstAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionActionPerformed(evt);
            }
        });
        dataPanel.add(firstAction, new java.awt.GridBagConstraints());

        previousAction.setMnemonic('P');
        previousAction.setText("Previous");
        previousAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionActionPerformed(evt);
            }
        });
        dataPanel.add(previousAction, new java.awt.GridBagConstraints());

        nextAction.setMnemonic('N');
        nextAction.setText("Next");
        nextAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionActionPerformed(evt);
            }
        });
        dataPanel.add(nextAction, new java.awt.GridBagConstraints());

        lastAction.setMnemonic('L');
        lastAction.setText("Last");
        lastAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionActionPerformed(evt);
            }
        });
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
        mainPanel.add(subMainPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

        setBounds(0, 0, 637, 300);
    }// </editor-fold>//GEN-END:initComponents
    
    private void helpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpActionActionPerformed
    
    private void clearActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionActionPerformed
    
    private void deleteActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionActionPerformed
    
    private void updateActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionActionPerformed
    
    private void newActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionActionPerformed
try {
            
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_branches (branch_id,geographical_location,branch_name) values(?,upper(?),?)");
            pstmt.setObject(1,branchidTxt.getText());
            pstmt.setObject(2,geographicallocationCbx.getSelectedItem().toString());
            pstmt.setObject(3,branchnameTxt.getText());
           // pstmt.setObject(4,continentCbx.getSelectedItem().toString());
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
}// TODO add your handling code here:
    }//GEN-LAST:event_newActionActionPerformed
    
    private void lastActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastActionActionPerformed
    
    private void nextActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextActionActionPerformed
    
    private void previousActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previousActionActionPerformed
    
    private void firstActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstActionActionPerformed

    private void geographicallocationCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geographicallocationCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_geographicallocationCbxActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JLabel branchidLbl;
    private javax.swing.JTextField branchidTxt;
    private javax.swing.JLabel branchnameLbl;
    private javax.swing.JTextField branchnameTxt;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton clearAction;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton deleteAction;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JButton firstAction;
    private javax.swing.JComboBox geographicallocationCbx;
    private javax.swing.JLabel geographicallocationLbl;
    private javax.swing.JButton helpAction;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JButton lastAction;
    private javax.swing.JPanel mainPanel;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JButton newAction;
    private javax.swing.JButton nextAction;
    private javax.swing.JButton previousAction;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JButton updateAction;
    // End of variables declaration//GEN-END:variables
    
}
