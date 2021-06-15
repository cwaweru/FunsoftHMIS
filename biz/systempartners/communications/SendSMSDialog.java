/*
 * SendSMSDialog.java
 *
 * Created on July 16, 2008, 7:13 PM
 */

package biz.systempartners.communications;

/**
 *
 * @author  funsoft
 */
public class SendSMSDialog extends javax.swing.JDialog {
    
    /** Creates new form SendSMSDialog */
    public SendSMSDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        phoneNumberLbl = new javax.swing.JLabel();
        messageLbl = new javax.swing.JLabel();
        messageJscrl = new javax.swing.JScrollPane();
        messageTxt = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        sendSmsBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        phoneNumberTxt = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Short Message Service (SMS)  Composer");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        phoneNumberLbl.setText("Phone Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(phoneNumberLbl, gridBagConstraints);

        messageLbl.setText("Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(messageLbl, gridBagConstraints);

        messageTxt.setColumns(20);
        messageTxt.setRows(5);
        messageTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Type your message here.")))));
        messageJscrl.setViewportView(messageTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        getContentPane().add(messageJscrl, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        sendSmsBtn.setMnemonic('S');
        sendSmsBtn.setText("Send SMS");
        sendSmsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendSmsBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(sendSmsBtn, gridBagConstraints);

        closeBtn.setMnemonic('O');
        closeBtn.setText("Close SMS Dialog");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(closeBtn, gridBagConstraints);

        clearBtn.setMnemonic('R');
        clearBtn.setText("Reset SMS Form");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(clearBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        phoneNumberTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Receiver Telephone No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        phoneNumberTxt.setForeground(new java.awt.Color(0, 0, 255));
        try {
            phoneNumberTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("254-###-######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        phoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(phoneNumberTxt, gridBagConstraints);

        setSize(new java.awt.Dimension(527, 254));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sendSmsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendSmsBtnActionPerformed

        if(this.phoneNumberTxt.getText().toCharArray().length > 0){
        
        biz.systempartners.claims.SendSMS.SendSMS(this.phoneNumberTxt.getText(),"Funsoft I-HMIS Messaging:\n\n"+this.messageTxt.getText()+"\n\nFrom:\n"+com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName());
        
        this.dispose();
          
        javax.swing.JOptionPane.showMessageDialog(null, "Message sent to ["+this.phoneNumberTxt.getText()+"]");
        
        } else {
            
          javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You MUST provide a phone number.");

            
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_sendSmsBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed

        this.phoneNumberTxt.setText(null);
        
        this.messageTxt.setText(null);
        
// TODO add your handling code here:
    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed

        this.dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed

    private void phoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTxtActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTxtActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendSMSDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane messageJscrl;
    private javax.swing.JLabel messageLbl;
    private javax.swing.JTextArea messageTxt;
    private javax.swing.JLabel phoneNumberLbl;
    private javax.swing.JFormattedTextField phoneNumberTxt;
    private javax.swing.JButton sendSmsBtn;
    // End of variables declaration//GEN-END:variables
    
}
