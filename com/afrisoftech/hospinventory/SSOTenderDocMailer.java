/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.hospinventory;

import javax.mail.PasswordAuthentication;
import javax.swing.JOptionPane;



/**
 *
 * @author sytem partners
 */
public class SSOTenderDocMailer extends javax.swing.JDialog implements java.lang.Runnable{

    /**
     * Creates new form NewJDialog
     */
    boolean isSent=false;
    String[] strings;
    String[] tosendStr;
     javax.swing.JLabel attachmentLabels[] = null;
    java.util.Vector attachmentsVector = null;
    java.lang.Thread sendMailThread = null;
    public static     java.sql.Connection connectDB = null;
 public static  org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
 String Supplier, Quotation;
    public SSOTenderDocMailer(java.awt.Frame parent, boolean modal,java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB,String Q) {
      super(parent, modal);
      
        connectDB = connDb;

        pConnDB = pconnDB;
     
        Quotation=Q;
        
        
       
        initComponents();
        setSize(700,400);
        setLocation(250,150);
        int rows=0;
      try{
  java.sql.Statement pst21 = connectDB.createStatement();
            java.sql.ResultSet rs = pst21.executeQuery(" SELECT  DISTINCT T.quotation_no ,S.tel1,  T.country_origin ,  T.supplier, T.supplier_address,S.email_address, T.date_returned, T.ordered   FROM st_floated_quotations T,(SELECT  supplier_name, tel1,  email_address, postal_address\n" +
"  FROM st_suppliers) as S WHERE  T.supplier=S.supplier_name and  T.quotation_no='" + Quotation + "' ");
            while (rs.next()) {
                
//if (rs.getString("supplier_address")==null){
//  jTable1.setValueAt("-",rows,1);
//
//}
               
                
                
                 
                 mailingTable.setValueAt(rs.getString("supplier"),rows,0);

                mailingTable.setValueAt("olsonmutwiri2009@gmail.com",rows,1);
                  mailingTable.setValueAt("789",rows,5);
                  
                   rows++; 
                 System.out.println(rs.getString("supplier").toString()+" "+rs.getString("supplier_address").toString()+" "+rs.getString("country_origin").toString()+" "+rs.getString("tel1").toString());
               
            
        }
  }
  catch(Exception es){
  System.out.println(es.getMessage());
  
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

        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mailingTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtfrom = new javax.swing.JTextField();
        txtsubject = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcontent = new javax.swing.JTextArea();
        attachmentsPanel = new javax.swing.JPanel();
        attachmentsJscrl = new javax.swing.JScrollPane();
        attachmentsPane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bidder's Mailer");
        setBackground(new java.awt.Color(204, 255, 204));
        setBounds(new java.awt.Rectangle(0, 0, 700, 500));
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Send Mail");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jButton1, gridBagConstraints);

        jButton2.setText("Edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jButton2, gridBagConstraints);

        jButton3.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 9.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        mailingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bidder/Supplier", "Email Address", "Alternative Email", "Sending Option"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(mailingTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 8.0;
        gridBagConstraints.weighty = 5.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mailing Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Mail From");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Mail Subject");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtfrom, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtsubject, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mail Content", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        txtcontent.setColumns(20);
        txtcontent.setRows(5);
        jScrollPane1.setViewportView(txtcontent);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 9.0;
        jPanel5.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel5, gridBagConstraints);

        attachmentsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mail attachments. Click on the space provided below to select files to attach.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));
        attachmentsPanel.setLayout(new java.awt.GridBagLayout());

        attachmentsJscrl.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        attachmentsJscrl.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        attachmentsJscrl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attachmentsJscrlMouseClicked(evt);
            }
        });

        attachmentsPane.setBackground(new java.awt.Color(204, 255, 204));
        attachmentsPane.setToolTipText("Click here to add files to attach to the message.");
        attachmentsPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attachmentsPaneMouseClicked(evt);
            }
        });
        attachmentsJscrl.setViewportView(attachmentsPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        attachmentsPanel.add(attachmentsJscrl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(attachmentsPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     java.lang.String attachmentFiles[] = null;
        
        java.lang.String addreses[] = null;
        
        // java.lang.String ccAddreses[] = null
        
        // java.lang.String bccAddreses[] = null
        
        sendMailThread = new java.lang.Thread(this, "Send Mail");
        
        sendMailThread.start();
        
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void attachmentsPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attachmentsPaneMouseClicked

        attachmentsPane.invalidate();

        javax.swing.JFileChooser attachmentChooser = new javax.swing.JFileChooser(new java.io.File(System.getProperty("docsdir")));

        attachmentChooser.setMultiSelectionEnabled(true);//AcceptAllFileFilterUsed(true);

        attachmentChooser.setDragEnabled(true);

        int returnVal = attachmentChooser.showDialog(this,"Select files to attach");

        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {

            //  System.out.println("selected files = :"+attachmentChooser.getSelectedFile().getAbsoluteFile());

            java.io.File attachmentFiles[] = attachmentChooser.getSelectedFiles();

            attachmentsVector = new java.util.Vector(1,1);

            attachmentLabels = new javax.swing.JLabel[attachmentFiles.length];

            System.out.println("Attachments number["+attachmentFiles.length+"]");

            for(int i = 0; i < attachmentFiles.length; i++){

                attachmentLabels[i] = new javax.swing.JLabel();

                attachmentsVector.addElement(attachmentFiles[i].getPath());

                attachmentLabels[i].setText(attachmentFiles[i].getPath());

                attachmentLabels[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Email 3.png")));

                attachmentsPane.add(attachmentLabels[i]);

                this.attachmentsJscrl.setViewportView(attachmentsPane);

                System.out.println("Attachment done ...");
            }

            attachmentsJscrl.validate();

            com.afrisoftech.hospital.HospitalMain.storePreferences();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_attachmentsPaneMouseClicked

    private void attachmentsJscrlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attachmentsJscrlMouseClicked
        ///loading an attachment
          attachmentsPane.invalidate();
        
        javax.swing.JFileChooser attachmentChooser = new javax.swing.JFileChooser(new java.io.File(System.getProperty("docsdir")));
        
        attachmentChooser.setMultiSelectionEnabled(true);//AcceptAllFileFilterUsed(true);
        
        attachmentChooser.setDragEnabled(true);
        
        int returnVal = attachmentChooser.showDialog(this,"Select files to attach");
        
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            
            //  System.out.println("selected files = :"+attachmentChooser.getSelectedFile().getAbsoluteFile());
            
            java.io.File attachmentFiles[] = attachmentChooser.getSelectedFiles();
            
            attachmentsVector = new java.util.Vector(1,1);
            
            attachmentLabels = new javax.swing.JLabel[attachmentFiles.length];
            
            System.out.println("Attachments number["+attachmentFiles.length+"]");
            
            for(int i = 0; i < attachmentFiles.length; i++){
                
                attachmentLabels[i] = new javax.swing.JLabel();
                
                attachmentsVector.addElement(attachmentFiles[i].getPath());
                
                attachmentLabels[i].setText(attachmentFiles[i].getPath());
                
                attachmentLabels[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Email 3.png")));
                
                attachmentsPane.add(attachmentLabels[i]);
                
                this.attachmentsJscrl.setViewportView(attachmentsPane);
                
                System.out.println("Attachment done ...");
            }
            
            attachmentsJscrl.validate();
            
            com.afrisoftech.hospital.HospitalMain.storePreferences();
        }
    }//GEN-LAST:event_attachmentsJscrlMouseClicked

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
            java.util.logging.Logger.getLogger(SSOTenderDocMailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SSOTenderDocMailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SSOTenderDocMailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SSOTenderDocMailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SSOTenderDocMailer dialog = new SSOTenderDocMailer(new javax.swing.JFrame(), true,connectDB,null,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane attachmentsJscrl;
    private javax.swing.JPanel attachmentsPane;
    private javax.swing.JPanel attachmentsPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable mailingTable;
    private javax.swing.JTextArea txtcontent;
    private javax.swing.JTextField txtfrom;
    private javax.swing.JTextField txtsubject;
    // End of variables declaration//GEN-END:variables

 private java.lang.String[] getAddresses(String addressString){
        
        String addresses[] = null;
        
        java.util.Vector addressesVector = new java.util.Vector(1,1);
        
        java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(addressString,",");
        
        addresses = new java.lang.String[tokenizer.countTokens()];
        
        while(tokenizer.hasMoreTokens()){
            String address = tokenizer.nextToken();
            // if(address.ma){
            addressesVector.addElement(address);
            // }
            
        }
        
        addresses = (String[]) addressesVector.toArray(addresses);
        
        return addresses;
        
    }
    
    public void run(){
        

        //(String[]) attachmentsVector.toArray(new java.lang.String[attachmentsVector.size()])
         
        for(int i=0;i<mailingTable.getRowCount();i++){
        biz.systempartners.claims.AdvancedSendFile.SendFile(connectDB,(String[]) attachmentsVector.toArray(new java.lang.String[attachmentsVector.size()]), (java.lang.String[])this.getAddresses(mailingTable.getValueAt(i,1).toString()),null,null, txtsubject.getText(), txtcontent.getText());
        isSent=true; 
   
        if(isSent == true) {
     //sendButton.setEnabled(false);
     JOptionPane.showMessageDialog(null,"Your e-mail has been sent.");
}
        }
    }
}


//class MailAuthenticator extends javax.mail.Authenticator {
// 
//private PasswordAuthentication authentication = null;
//private String username;
//private String password;
// 
//public MailAuthenticator() {
//this.username = "omicheni@systempartners.biz";
////new String(txtPassword.getPassword()
//this.password = "olsona130";
//this.authentication = new PasswordAuthentication(username,password);
//}
// 
//@Override
//protected PasswordAuthentication getPasswordAuthentication() {
//return authentication;
//}
//public String getUsername() {
//return username;
//}
//public void setUsername(String username) {
//this.username = username;
//}
//public String getPassword() {
//return password;
//}
//public void setPassword(String password) {
//this.password = password;
//}
 
//}