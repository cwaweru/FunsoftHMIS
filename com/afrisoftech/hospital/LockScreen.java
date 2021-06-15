/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LockScreen.java
 *
 * Created on Mar 22, 2011, 3:30:20 PM
 */

package com.afrisoftech.hospital;
/*
import com.chis.hospayroll.PayrollMain;
import com.chis.hrm.HrMain;
import com.chis.hospinventory.StockMain;
import com.chis.hrm.Hrm_Main;*/
import com.afrisoftech.hospital.HospitalMain;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kelsas
 */
public class LockScreen extends javax.swing.JDialog {
    private String dbserver;
    private String dbport;
    private String dbschema;
    private String activeUser;
    private HospitalMain hosMain;
    private static LockScreen instance;
    private Connection connect;
    public LockScreen(HospitalMain parent, boolean modal,String user) {
        super(parent, modal);
        this.activeUser=user;
        this.hosMain=parent;
        getSystemInfo();
        initComponents();
        hosMain.lockScreen(true);
        hosMain.stopWindowsEvent();
//        hosMain.stopConnectionThread();
    }
    public static LockScreen getInstance(HospitalMain parent, boolean modal,String user){
        if(instance==null){
            instance=new LockScreen(parent,modal, user);
        }
        return instance;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        unlockButtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Unlock Funsoft Session");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enter Password for : "+activeUser+" To Restore Session");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Your Funsoft session has timed out, please log in again!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        unlockButtn.setText("Unlock");
        unlockButtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unlockButtnActionPerformed(evt);
            }
        });
        jPanel2.add(unlockButtn);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(418, 161));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void unlockButtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unlockButtnActionPerformed
        String str=String.valueOf(jPasswordField1.getPassword());
        if(isValidPassword(str)){
           
                hosMain.lockScreen(false);
                hosMain.idleTimer=null;
                hosMain.configIdleListener();
                instance=null;
                
            try { 
                if(connect!=null){
                connect.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LockScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            LockScreen.this.dispose();
        }

    }//GEN-LAST:event_unlockButtnActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        boolean caps;
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER){
            unlockButtn.doClick();
        }
        caps=Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if(caps==true){
            JOptionPane.showMessageDialog(this, "Please note caps are on");
        }
        
        
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "Do you really want to exit application?", "Caution before closing application!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);

        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton unlockButtn;
    // End of variables declaration//GEN-END:variables

    public Connection getDbConnection(String userName, String passWord){

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = java.sql.DriverManager.getConnection("jdbc:postgresql://"+dbserver+":"+dbport+"/"+dbschema, userName, passWord);

          } catch(SQLException sq) {
            JOptionPane.showMessageDialog(new JFrame(), "Wrong Password, Please enter right password to unlock!");
          } catch(ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(new JFrame(), cnf.getMessage());
        }

        return conn;
    }
    public void getSystemInfo(){
       FileInputStream prop = null;
        try {
            String filename = System.getProperty("user.dir") + System.getProperty("file.separator") + "hosprop.properties";
            prop = new FileInputStream(filename);
            Properties appProp = new Properties();
            appProp.load(prop);

             dbserver=appProp.getProperty("dbServerIpAdd");
             dbport=appProp.getProperty("dbPort");
             dbschema=appProp.getProperty("activeDatabase");
//             activeUser=appProp.getProperty("currentuser");

            // Statemen++t st=


        } catch (IOException ex) {
            Logger.getLogger(LockScreen.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                prop.close();
            } catch (IOException ex) {
                Logger.getLogger(LockScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean isValidPassword(String passwrd){
        connect=getDbConnection(activeUser, passwrd);
        if(connect!=null){
            /*hosMain.setConnectDB(connect);
            hosMain.restartConnectionThread();

        nnect    Hrm_Main.setConnectDB(connect);
            HRMain.setConnectDB(connect);
            PayrollMain.setConnectDB(connect);
            StockMain.setConnectDB(connect);
             */
           
            return true;
        }
        return false;
    }
}
