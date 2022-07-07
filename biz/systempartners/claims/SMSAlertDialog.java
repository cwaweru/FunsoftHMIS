/*
 * SMSAlertDialog.java
 *
 * Created on March 10, 2008, 10:43 AM
 */

package biz.systempartners.claims;

/**
 *
 * @author  funsoft
 */
public class SMSAlertDialog extends javax.swing.JDialog {
    
    /** Creates new form SMSAlertDialog */
    public SMSAlertDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        detailPanel = new javax.swing.JPanel();
        phoneNumberLbl = new javax.swing.JLabel();
        smsDetailsLbl = new javax.swing.JLabel();
        smsDetailsJscl = new javax.swing.JScrollPane();
        smsDetailsTxt = new javax.swing.JTextArea();
        phoneNumberTxt = new javax.swing.JTextField();
        buttonsPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        spacerLbl = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New SMS Alert!");
        setAlwaysOnTop(true);
        mainPanel.setLayout(new java.awt.GridBagLayout());

        detailPanel.setLayout(new java.awt.GridBagLayout());

        phoneNumberLbl.setText("Phone Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        detailPanel.add(phoneNumberLbl, gridBagConstraints);

        smsDetailsLbl.setText("SMS Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        detailPanel.add(smsDetailsLbl, gridBagConstraints);

        smsDetailsJscl.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        smsDetailsTxt.setColumns(20);
        smsDetailsTxt.setEditable(false);
        smsDetailsTxt.setRows(5);
        smsDetailsJscl.setViewportView(smsDetailsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        detailPanel.add(smsDetailsJscl, gridBagConstraints);

        phoneNumberTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        detailPanel.add(phoneNumberTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        mainPanel.add(detailPanel, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        saveBtn.setMnemonic('v');
        saveBtn.setText("Save Information");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(saveBtn, gridBagConstraints);

        closeBtn.setText("Close dialog");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(closeBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(spacerLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(buttonsPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-465)/2, (screenSize.height-257)/2, 465, 257);
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        javax.swing.JOptionPane.showMessageDialog(this, "Please provide the host database through system settings.");

// TODO add your handling code here:
    }//GEN-LAST:event_saveBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed

        this.dispose();
     //   this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SMSAlertDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel phoneNumberLbl;
    public static javax.swing.JTextField phoneNumberTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane smsDetailsJscl;
    private javax.swing.JLabel smsDetailsLbl;
    public javax.swing.JTextArea smsDetailsTxt;
    private javax.swing.JLabel spacerLbl;
    // End of variables declaration//GEN-END:variables
    
}