/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.nursing;


import static com.afrisoftech.hospital.HospitalMain.saccopn;

/**
 *
 * @author MUGISPL
 */
public class NursingWards extends javax.swing.JDialog {

    /**
     * Creates new form NursingWards
     */
    public static java.sql.Connection connectDB = null;
    public NursingWards(java.awt.Frame parent, boolean modal,java.sql.Connection connDb) {
        super(parent, modal);
        connectDB = connDb;
        initComponents();
      this.setBounds( 450,156,415, 150);
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

        jSearchPanel3 = new javax.swing.JPanel();
        jButton524 = new javax.swing.JButton();
        occupancywardCMB = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel3.setBackground(new java.awt.Color(51, 102, 255));
        jSearchPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("IN-PATIENTS"));
        jSearchPanel3.setLayout(new java.awt.GridBagLayout());

        jButton524.setText("Dispose");
        jButton524.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton524ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(jButton524, gridBagConstraints);

        occupancywardCMB.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, " (select '0SELECT' UNION SELECT distinct ward  FROM nurse_allocation where status=true and ward in (select distinct ward_name FROM hp_wards) and name=(select current_user)) ORDER BY 1 asc"));
        occupancywardCMB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                occupancywardCMBItemStateChanged(evt);
            }
        });
        occupancywardCMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                occupancywardCMBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(occupancywardCMB, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSearchPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton524ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton524ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton524ActionPerformed

    private void occupancywardCMBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_occupancywardCMBItemStateChanged

      
       
    }//GEN-LAST:event_occupancywardCMBItemStateChanged

    private void occupancywardCMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_occupancywardCMBActionPerformed
                          
       com.afrisoftech.nursing.NursingCasualty dismt = new com.afrisoftech.nursing.NursingCasualty(connectDB,occupancywardCMB.getSelectedItem().toString());
              dismt.setVisible(true);
        saccopn.add(dismt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            dismt.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }  
        
    }//GEN-LAST:event_occupancywardCMBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NursingWards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NursingWards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NursingWards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NursingWards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NursingWards dialog = new NursingWards(new javax.swing.JFrame(), true,connectDB);
              
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton524;
    private javax.swing.JPanel jSearchPanel3;
    private javax.swing.JComboBox occupancywardCMB;
    // End of variables declaration//GEN-END:variables
}
