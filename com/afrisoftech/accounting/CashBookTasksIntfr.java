
/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  root
 */
public class CashBookTasksIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {
    
    /** Creates new form loanpymntintfr */
    
    java.lang.String backgrdimg;
    
    javax.swing.ImageIcon desktopPaneIcon;
    
    java.sql.Connection connectDB = null;
    
    javax.swing.table.TableModel tableModel = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    java.awt.GridBagConstraints gridBagConstraints = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    OpenShiftAction openShiftAction = null;
    
    com.afrisoftech.accounting.ShiftDepintfr cashRecon = null;
    
    com.afrisoftech.accounting.Bankingintfr banking = null;
    
    com.afrisoftech.accounting.BankReconintfr bankRecon = null;
    
    com.afrisoftech.accounting.OtherChargesintfr bankCharges = null;
    
    com.afrisoftech.accounting.RaiseCashJournalintfr raiseIou = null;
    
    com.afrisoftech.hospital.CashJourAppintfr approvePettyCash = null;
    
    com.afrisoftech.accounting.Journalsintfr journalsEntry = null;
    
    com.afrisoftech.hospital.PettyCashPymIntfr PettyCashPymt = null;
    
    com.afrisoftech.hospital.PettyCashDirectPymIntfr directPettPay = null;
    
    private ProgressThread progressThread;
    
    Thread patientThread = null;
    
    Thread cashThread = null;
    
    public final javax.swing.JButton reconCashButton;
    
    public CashBookTasksIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, com.afrisoftech.hospital.HospitalMain parentHospitalFrame) {
        
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        

        
        loadImage();
        
        desktopPaneIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg","c:/Tests/clouds.jpg"));
        
        // openShiftAction = new OpenShiftAction();
        
        cashRecon = new com.afrisoftech.accounting.ShiftDepintfr(connectDB,pConnDB);
        
        banking = new com.afrisoftech.accounting.Bankingintfr(connectDB,pConnDB);
        
        bankRecon = new com.afrisoftech.accounting.BankReconintfr(connectDB,pConnDB);
        
        bankCharges = new com.afrisoftech.accounting.OtherChargesintfr(connectDB,pConnDB);
        
        raiseIou = new com.afrisoftech.accounting.RaiseCashJournalintfr(connectDB,pConnDB);
        
        approvePettyCash = new com.afrisoftech.hospital.CashJourAppintfr(connectDB,pConnDB);
        
        journalsEntry = new com.afrisoftech.accounting.Journalsintfr(connectDB,pConnDB);
        
        PettyCashPymt = new com.afrisoftech.hospital.PettyCashPymIntfr(connectDB,pConnDB);
        
        directPettPay = new com.afrisoftech.hospital.PettyCashDirectPymIntfr(connectDB,pConnDB);
        
      
        
        initComponents();
        
        parentHospitalFrame.mainSplitPane.setDividerLocation(0);
        
        comboTaskPanels.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        // splashMainPanel.add(spacerLabel, gridBagConstraints);
        
        com.l2fprod.common.swing.JButtonBar comboButtonBar = new com.l2fprod.common.swing.JButtonBar(com.l2fprod.common.swing.JButtonBar.VERTICAL);
        
        //       comboButtonBar.add(parentHospitalFrame.patientregstmnit);
        
        javax.swing.JButton bankingButton = new javax.swing.JButton("Banking");
        bankingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankingButtonActionPerformed(evt);
            }
        });
        
        //  bankingButton.setA
        
        bankingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-gmenu.png")));
        
        javax.swing.JButton reconBankButton = new javax.swing.JButton("Reconcile Bank");
        reconBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reconBankButtonActionPerformed(evt);
                
            }
        });
        
        reconBankButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-gnumeric.png")));
        
        javax.swing.JButton bankChargesButton = new javax.swing.JButton("Bank Charges");
        bankChargesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankChargesButtonActionPerformed(evt);
            }
        });
        bankChargesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redhat-tools.png")));
        
        javax.swing.JButton raiseIouButton = new javax.swing.JButton("Raise I.O.U");
        raiseIouButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raiseIouButtonActionPerformed(evt);
            }
        });
        raiseIouButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gedit-icon.png")));
        
        javax.swing.JButton approveIouButton = new javax.swing.JButton("Approve I.O.U");
        approveIouButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveIouButtonActionPerformed(evt);
            }
        });
        approveIouButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/everything.png")));
        
        //javax.swing.JButton patientdbButton = new javax.swing.JButton("Visitor Activation");
        
        //patientdbButton.setEnabled(false);
        
        //  cashRecon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server-cfg.png")));
        
        reconCashButton = new javax.swing.JButton("Reconcile Cash Point");
        reconCashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reconCashButtonActionPerformed(evt);
            }
        });
        reconCashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/005_spreadsheet_template.png")));
        
        javax.swing.JButton journalButton = new javax.swing.JButton("Journals");
        journalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                journalButtonActionPerformed(evt);
            }
        });
        journalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msgpend.png")));
        
        javax.swing.JButton pettyVoucherButton = new javax.swing.JButton("Petty Cash Voucher");
        pettyVoucherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pettyVoucherButtonActionPerformed(evt);
            }
        });
        pettyVoucherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/applet-okay.png")));
        
        javax.swing.JButton directCashButton = new javax.swing.JButton("Direct Cash Voucher");
        directCashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directCashButtonActionPerformed(evt);
            }
        });
        directCashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-html.png")));//  com.l2fprod.common.swing.icons.EmptyIcon
        
           directPettPay.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
         PettyCashPymt.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
         journalsEntry.closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
         approvePettyCash.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
         raiseIou.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        
        banking.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
//         bankRecon.jButton4.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                comboTaskPanels.removeAll();
//                repaint();
//            }
//        });
        
         cashRecon.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
         bankCharges.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
       
        if (parentHospitalFrame.bankingmnit.isVisible()){
            comboButtonBar.add(reconCashButton);
        }
        if (parentHospitalFrame.Bankingmnit.isVisible()){
            comboButtonBar.add(bankingButton);
        }
        if (parentHospitalFrame.bankreconcmnit.isVisible()){
            comboButtonBar.add(reconBankButton);
        }
        if (parentHospitalFrame.bnkchargesmnit.isVisible()){
            comboButtonBar.add(bankChargesButton);
        }
        if (parentHospitalFrame.raisecashVouchmnit.isVisible()){
            comboButtonBar.add(raiseIouButton);
        }
        if (parentHospitalFrame.payvouchermnit.isVisible()){
            comboButtonBar.add(approveIouButton);
        }
        if (parentHospitalFrame.pettycashpaymnit.isVisible()){
            comboButtonBar.add(pettyVoucherButton);
        }
        if (parentHospitalFrame.pettycashpaywhmnit.isVisible()){
            comboButtonBar.add(directCashButton);
        }
        if (parentHospitalFrame.jrnloansmnit.isVisible()){
            comboButtonBar.add(journalButton);
        }
        
        // this.mainTaskSplitPane.setLeftComponent(comboButtonBar);
        buttonBarScrollPane.setViewportView(comboButtonBar);
        
        this.setSize(parentHospitalFrame.saccopn.getSize());
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainComboTasksPane = new javax.swing.JPanel();
        mainTaskSplitPane = new javax.swing.JSplitPane();
        buttonBar = new javax.swing.JPanel();
        buttonBarScrollPane = new javax.swing.JScrollPane();
        comboTaskPanels = new javax.swing.JPanel() {

            public void paintComponent(java.awt.Graphics g) {

                java.awt.Dimension d = getSize();

                g.drawImage(desktopPaneIcon.getImage(), 0, 0, d.width, d.height,null);
                System.out.println("still drawing");
                setOpaque(false);

                super.paintComponent(g);

            }

        };

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CASH BOOK MANAGEMENT");
        setToolTipText("Front End Operations Desk");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        mainComboTasksPane.setLayout(new java.awt.GridBagLayout());

        mainTaskSplitPane.setDividerLocation(150);
        buttonBar.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonBar.add(buttonBarScrollPane, gridBagConstraints);

        mainTaskSplitPane.setLeftComponent(buttonBar);

        mainTaskSplitPane.setRightComponent(comboTaskPanels);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainComboTasksPane.add(mainTaskSplitPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 30.0;
        getContentPane().add(mainComboTasksPane, gridBagConstraints);

        setBounds(0, 0, 697, 500);
    }//GEN-END:initComponents
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // Add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated
  /*  private void bankingButtonActionPerformed(java.awt.event.ActionEvent evt) {
   
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
   
        // comp.getContentPane();
   
        patientRunning = true;
   
        patientThread = new java.lang.Thread(this, "Patient");
   
        patientThread.start();
/*        this.invalidate();
   
        comboTaskPanels.removeAll();
   
        comboTaskPanels.add(patientRegister.getContentPane(), gridBagConstraints);
         this.invalidate();
         this.repaint();
        this.validate();
   */
    // Add your handling code here:
    //    }
    
    private void bankingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        
        comboTaskPanels.removeAll();
        this.setTitle("CASH BOOK MANAGEMENT :: BANKING".toUpperCase());
        
        comboTaskPanels.add(banking.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    
    private void reconBankButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        this.setTitle("CASH BOOK MANAGEMENT :: BANK RECONCILLIATION".toUpperCase());
        comboTaskPanels.removeAll();
        
        comboTaskPanels.add(bankRecon.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    
    private void raiseIouButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        this.setTitle("CASH BOOK MANAGEMENT :: RAISE I.O.U".toUpperCase());
        comboTaskPanels.removeAll();
        
        comboTaskPanels.add(raiseIou.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void reconCashButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        
        comboTaskPanels.removeAll();
        
        this.setTitle("CASH BOOK MANAGEMENT :: RECONCILE SHIFT COLLECTION".toUpperCase());
        comboTaskPanels.add(cashRecon.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void bankChargesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("CASH BOOK MANAGEMENT :: BANK CHARGES".toUpperCase());
        comboTaskPanels.add(bankCharges.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void approveIouButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("CASH BOOK MANAGEMENT :: APPROVE I.O.U".toUpperCase());
        comboTaskPanels.add(approvePettyCash.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void journalButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;
        
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '"+System.getProperty("currentuser")+"' AND (status = 'Running' OR status = 'Suspended')");
            
            while (rset1.next()) {
                
                rowsNo = rset1.getInt(1);
                
            }
            if (rowsNo < 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "There is no Shift running");
            } else {
                
                if (journalsEntry == null){
                    journalsEntry = new com.afrisoftech.accounting.Journalsintfr(connectDB,pConnDB);
                }
                
                this.setTitle("CASH BOOK MANAGEMENT :: Pass journals here".toUpperCase());
                comboTaskPanels.removeAll();
                
                comboTaskPanels.add(journalsEntry.getContentPane(), gridBagConstraints);
                this.invalidate();
                // comboTaskPanels.transferFocusUpCycle();
                this.repaint();
                this.validate();
                //  saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                // try {
                //    comp.setSelected(true);
                // } catch(java.beans.PropertyVetoException pvt){}
                
            }
        }
        
        catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sqlex.getMessage());
            
        }
        
        
        // Add your handling code here:
    }
    private void pettyVoucherButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;
        
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '"+System.getProperty("currentuser")+"' AND (status = 'Running' OR status = 'Suspended')");
            
            while (rset1.next()) {
                
                rowsNo = rset1.getInt(1);
                
            }
            if (rowsNo < 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "There is no Shift running");
            } else {
                
                //   while (rset.next()){
                
                //if(this.getShiftStatus()){
                if (PettyCashPymt == null) {
                    PettyCashPymt = new com.afrisoftech.hospital.PettyCashPymIntfr(connectDB,pConnDB);;
                }
                
               this.setTitle("CASH BOOK MANAGEMENT :: PETTY CASH VOUCHERS".toUpperCase()); comboTaskPanels.removeAll();
                
                comboTaskPanels.add(PettyCashPymt.getContentPane(), gridBagConstraints);
                this.invalidate();
                // comboTaskPanels.transferFocusUpCycle();
                this.repaint();
                this.validate();// saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                // try {
                //     comp.setSelected(true);
                // } catch(java.beans.PropertyVetoException pvt){}
                //}else{
                //  javax.swing.JOptionPane.showMessageDialog(this,"Access Denied! Refer this matter to system administrator");
            }
        }
        
        catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sqlex.getMessage());
            
        }
        
        
        // Add your handling code here:
    }
    private void directCashButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;
        
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '"+System.getProperty("currentuser")+"' AND (status = 'Running' OR status = 'Suspended')");
            
            while (rset1.next()) {
                
                rowsNo = rset1.getInt(1);
                
            }
            if (rowsNo ==0) {
                javax.swing.JOptionPane.showMessageDialog(this, "There is no Shift running");
            } else {
                
                //   while (rset.next()){
                
                //if(this.getShiftStatus()){
                if(directPettPay == null){
                    directPettPay = new com.afrisoftech.hospital.PettyCashDirectPymIntfr(connectDB,pConnDB);
                }
                comboTaskPanels.removeAll();
               this.setTitle("CASH BOOK MANAGEMENT :: DIRECT PETTY CASH VOUCHERS WITHOUT I.O.U".toUpperCase()); comboTaskPanels.add(directPettPay.getContentPane(), gridBagConstraints);
                this.invalidate();
                // comboTaskPanels.transferFocusUpCycle();
                this.repaint();
                this.validate();
                
                //saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                //try {
                //    comp.setSelected(true);
                //} catch(java.beans.PropertyVetoException pvt){}
                //}else{
                //  javax.swing.JOptionPane.showMessageDialog(this,"Access Denied! Refer this matter to system administrator");
            }
        }
        
        catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sqlex.getMessage());
            
        }
        
        // Add your handling code here:
    }
    
    
    public void run() {
        //   this.setCursor(javax.swing.JFrame.WAIT_CURSOR);
        System.out.println("Thread is "+java.lang.Thread.currentThread().getName());
        
        if (java.lang.Thread.currentThread().getName().matches("Patient")) {
            
            loadingMode = true;
            
            //progressBarPopup.setVisible(true);
            
            aliveStatus = true;
            
            startRunning();
            //            System.out.println("Thread Patient starting...");
            while (patientRunning) {
                this.invalidate();
                
                comboTaskPanels.removeAll();
                
                comboTaskPanels.add(cashRecon.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();
                //                PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
                
                //       saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                
                loadingMode = false;
                
                
                
                stopRunning();
                
                
                patientRunning = false;
            }
            
            
            
            patientRunning = false;
            
            cashRunning = false;
            
            
            
            if (!patientRunning) {
                
                stop();
                
                Thread.currentThread().destroy();
            }
            
        } else if (java.lang.Thread.currentThread().getName().matches("Cash")){
            
            
            System.out.println("Thread is "+java.lang.Thread.currentThread().getName());
            
            while (cashRunning) {
                
                //  if (getShiftStatus()) {
                
                loadingMode = true;
                
                // progressBarPopup.setVisible(true);
                
                aliveStatus = true;
                
                startRunning();
                
                
                comboTaskPanels.removeAll();
                
                comboTaskPanels.add(cashRecon.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();
                //saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                
                
                System.setProperty("billpmnt", "showing");
                
                System.out.println(System.getProperty("billpmnt"));
                
                try {
                    cashRecon.setSelected(true);
                    
                } catch(java.beans.PropertyVetoException pvt){}
                try {
                    Thread.currentThread().sleep(100);
                    
                } catch(java.lang.InterruptedException IntExec){ System.out.println(IntExec.getMessage());}
                
                
                
                
                
                loadingMode = false;
                
                stopRunning();
                
                //   progressBarPopup.hide();
                
                cashRunning = false;
                
                
            }
            if (!cashRunning) {
                
                stop();
                
                Thread.currentThread().destroy();
            }
        }
    }
    public void stop() {
        
        Thread.currentThread().stop();
        
        
    }
    private void stopRunning() {
        
        System.out.println("Stopping run Progress Thread");
        
        progressThread.setStop(true);
        
    }
    class ProgressThread extends java.lang.Thread {
        
        
        
        boolean stopStatus = false;
        
        int min = 0;
        
        int max = 50;
        
        public ProgressThread(java.awt.Component parent) {
            
            progressMonitor = new javax.swing.ProgressMonitor(parent, "Please wait while form initializes...", "About to start...", min, max);
            
            System.out.println("Progress Monitor object : "+progressMonitor.toString());
            
            progressMonitor.setMillisToDecideToPopup(1);
        }
        
        public void setStop(boolean value) {
            
            stopStatus = value;
            
            progressMonitor.close();
            
        }
        
        public void run() {
            
            System.out.println("Progress Monitor object 1 : "+progressMonitor.toString());
            
            progressMonitor.setNote("Loading. Please wait...");
            
            while (loadingMode) {
                
                min = 0;
                
                max = 100000;
                
                for (int i = min; i <= max; i++) {
                    
                    if (stopStatus) {
                        
                        break;
                        
                    } else {
                        
                        progressMonitor.setProgress(i);
                        
                        //  progressBar.setValue(i);
                        progressMonitor.setNote(""+(i*2)+"%");
                        try {
                            
                            java.lang.Thread.sleep(500);
                            
                        } catch (java.lang.InterruptedException intExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Interruption Exception!");
                        }
                        
                        // }
                        
                    }
                    
                }
                aliveStatus = false;
                
            }
            
        }
        
    }
    private void startRunning() {
        
        System.out.println("Starting to run Progress Thread");
        
        if (progressThread == null || !progressThread.isAlive()) {
            
            progressThread = new ProgressThread(this);
            
            progressThread.start();
            
            System.out.println("Progress Thread started");
            
        }
        
    }
    public class OpenShiftAction extends javax.swing.AbstractAction {
        
        public void OpenShiftAction() {
            
        }
        
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
            
            threadSales = true;
            
            java.lang.Thread threadSalesOpen = new java.lang.Thread(getMainClass(), "Sales Open");
            
            threadSalesOpen.start();
            
            //openShiftAction.setEnabled(false);
            
            
        }
        
    }
    public com.afrisoftech.accounting.CashBookTasksIntfr getMainClass() {
        
        return this;
        
    }
    
    public void loadImage() {
        
        java.lang.String myAppFileUrl = null;
        
        myAppFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "hosprop.properties";
        
        try {
            
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);
            
            
            
            //       java.io.FileOutputStream propOutFile = new java.io.FileOutputStream("myapp.properties");
            
            java.util.Properties appProp = new java.util.Properties();
            
            try {
                
                appProp.load(propInFile);
                
                backgrdimg = appProp.getProperty("backgrdimg", "c:/Tests/clouds.jpg");
                
                System.setProperty("backgrdimg",backgrdimg);
                
                propInFile.close();
                
                //  return dbServerIp;
                
                
            } catch (java.io.IOException ioExec){
                
                System.out.println(ioExec.getMessage());
                
            }
            
            // return dbServerIp;
            
        } catch (java.lang.Exception exc){
            
            System.out.println(exc.getMessage());
            
            
            
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonBar;
    private javax.swing.JScrollPane buttonBarScrollPane;
    private javax.swing.JSplitPane mainTaskSplitPane;
    private javax.swing.JPanel mainComboTasksPane;
    private javax.swing.JPanel comboTaskPanels;
    // End of variables declaration//GEN-END:variables
    boolean threadSales = false;
    
    boolean cashierRunning = false;
    
    boolean cashRunning = false;
    
    boolean patientRunning = false;
    
    boolean loadingMode = false;
    
    boolean shiftStatus = false;
    
    boolean stopStatus = false;
    
    boolean aliveStatus = false;
    
    javax.swing.ProgressMonitor progressMonitor;
}
