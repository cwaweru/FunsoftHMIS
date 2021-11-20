
/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */

package com.afrisoftech.hospital;

/**
 *
 * @author  root
 */
public class InpatientTasksIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {
    java.lang.String backgrdimg;
    
    javax.swing.ImageIcon desktopPaneIcon;
    /** Creates new form loanpymntintfr */
    java.sql.Connection connectDB = null;
    
    javax.swing.table.TableModel tableModel = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    java.awt.GridBagConstraints gridBagConstraints = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    OpenShiftAction openShiftAction = null;
    
    com.afrisoftech.hospital.PatientDischargeIntfr PatientDischarge = null;
    
    AdmissionIntfr patientRegister = null;
    
    com.afrisoftech.hospital.GeneralBillingIntfr generalBilling = null;
    
    com.afrisoftech.accounting.FinSchemeInvsNhifIntfr schemeDebts = null;
    
    private ProgressThread progressThread;
    
    Thread patientThread = null;
    
    Thread cashThread = null;
    
    //com.afrisoftech.hospital.FinalisingInpatientBillIntfr cashInvoice = null;
    
    com.afrisoftech.accounting.InpatientRecpIntfr inpatientReceipts = null;
    
    com.afrisoftech.accounting.InpatientDepositIntfr BedCharges = null;
    
    //com.afrisoftech.hospital.NhifDebtsIntfr NhifRebeat = null;
    
    com.afrisoftech.hospital.BillingAdjGen2Intfr adjPatientbill = null;
    
    public final javax.swing.JButton registrationButton;// = null;
    
    javax.swing.JButton adjPatientbillButton = null;
    
    public InpatientTasksIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, com.afrisoftech.hospital.HospitalMain parentHospitalFrame) {
        
        
        
        openShiftAction = new OpenShiftAction();
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        loadImage();
        
        desktopPaneIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg","c:/Tests/clouds.jpg"));
        
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
        
        adjPatientbillButton = new javax.swing.JButton("Bill Adjustment");
        adjPatientbillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientButtonActionPerformed(evt);
            }
        });
        
        //  adjPatientbillButton.setA
        
        adjPatientbillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/up2date.png")));
        
        javax.swing.JButton patientDischarge = new javax.swing.JButton("Patent Discharge");
        patientDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientDischargeActionPerformed(evt);
            }
        });
        
        patientDischarge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_auth.png")));
        
        javax.swing.JButton schemeInvoices = new javax.swing.JButton("Finalize Invoice");
        schemeInvoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemeInvoicesActionPerformed(evt);
            }
        });
        schemeInvoices.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ax-applet.png")));
        
        javax.swing.JButton billingButton = new javax.swing.JButton("General Billing");
        billingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billingButtonActionPerformed(evt);
            }
        });
        billingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abiword_48.png")));
        
       /* javax.swing.JButton invoicesButton = new javax.swing.JButton("Cash Invoice Finalising");
        invoicesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicesButtonActionPerformed(evt);
            }
        });
        invoicesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editors.png")));
        */
        javax.swing.JButton patientdbButton = new javax.swing.JButton("Visitor Activation");
        
        //patientdbButton.setEnabled(false);
        
        patientdbButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server-cfg.png")));
        
        registrationButton = new javax.swing.JButton("Patient Admission");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });
        registrationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smb-server.png")));
        
        javax.swing.JButton inpatientReceiptsButton = new javax.swing.JButton("In-Patient Receipts");
        inpatientReceiptsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpatientReceiptsButtonActionPerformed(evt);
            }
        });
        inpatientReceiptsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msgpend.png")));
        
        javax.swing.JButton BedChargesButton = new javax.swing.JButton("Patient Deposit");
        BedChargesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherrecptmnitActionPerformed(evt);
            }
        });
        BedChargesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/office.png")));
        
     /*   javax.swing.JButton NhifRebeatButton = new javax.swing.JButton("Nhif Rebate");
        NhifRebeatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhifRebeatButtonActionPerformed(evt);
            }
        });
        NhifRebeatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-html.png")));//  com.l2fprod.common.swing.icons.EmptyIcon
        */

        if (parentHospitalFrame.patientregstmnit.isVisible()){
            
            comboButtonBar.add(registrationButton);
            
        }
        if (parentHospitalFrame.generalmnit.isVisible()){
            comboButtonBar.add(billingButton);
        }
        
        if (parentHospitalFrame.Receiptsmnit.isVisible()){
            comboButtonBar.add(BedChargesButton);
        }
        
         if (parentHospitalFrame.billadjustmentmnit.isVisible()){
            comboButtonBar.add(adjPatientbillButton);
        }
        if (parentHospitalFrame.inReceiptsmnit.isVisible()){
            comboButtonBar.add(inpatientReceiptsButton);
        }
        if (parentHospitalFrame.receiptsmnit.isVisible()){
            comboButtonBar.add(patientDischarge);
        }
        
        /*if (parentHospitalFrame.otherrecptmnit.isVisible()){
            comboButtonBar.add(NhifRebeatButton);
        }
        if (parentHospitalFrame.inpatcashinvmnit.isVisible()){
            comboButtonBar.add(invoicesButton);
        }*/
        if (parentHospitalFrame.finaliseipmnit.isVisible()){
            comboButtonBar.add(schemeInvoices);
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
        setTitle("INPATIENT OPERATIONS");
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
    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
         this.invalidate();
        
        comboTaskPanels.removeAll();
         this.setTitle("IN-PATIENT OPERATIONS :: Patient bill adjustment".toUpperCase());
        if(adjPatientbill == null){
            adjPatientbill = new com.afrisoftech.hospital.BillingAdjGen2Intfr(connectDB,pConnDB);
            
        }
        
        adjPatientbill.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(adjPatientbill.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void billingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //      AdmissionIntfr1r1 comp = neAdmissionIntfr1fr1(connectDB,pConnDB);
        
        //   comp.getContentPane();
        
        this.invalidate();
        
        comboTaskPanels.removeAll();
        
         this.setTitle("IN-PATIENT OPERATIONS :: General Billing".toUpperCase());
        
        if(generalBilling == null){
            generalBilling = new com.afrisoftech.hospital.GeneralBillingIntfr(connectDB,pConnDB);
            
        }
        
        generalBilling.closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(generalBilling.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
 /*   private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
  
       AdmissionIntfr1tfr1 comp = AdmissionIntfr1ntfr1(connectDB,pConnDB);
  
          comp.getContentPane();
  
        this.invalidate();
  
        comboTaskPanels.removeAll();
  
        if(patientRegister == null){
            patientRegister =AdmissionIntfr1Intfr1(connectDB,pConnDB);
       //     patientRegister.jButton2.setVisible(false);
        }
  
  
     /*   patientRegister.close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        })
        patientRegister.jButton252.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
  
  
      /* comboTaskPanels.add(patientRegister.jDialog3.getContentPane(), gridBagConstraints);
        patientRegister.jButton30111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
                adjPatientbillButton.doClick();
  
            }
        });
  
  
        this.invalidate();
        this.repaint();
        this.validate();
  
  
        // Add your handling code here:
    }
  */
    
    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //GeneralBillingIntfrcurringIntfrcurrGeneralBillingIntfrcurrBillingIntfrcurr(connectDB,pConnDB);
        
        // comp.getContentPane();
        if(patientRegister == null){
            patientRegister = new com.afrisoftech.hospital.AdmissionIntfr(connectDB,pConnDB);
        }
        patientRegister.jButton4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
               comboTaskPanels.removeAll();
              repaint();
          }
         });
        
        comboTaskPanels.removeAll();
         this.setTitle("IN-PATIENT OPERATIONS :: Patient Admission".toUpperCase());
        comboTaskPanels.add(patientRegister.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    
    private void schemeInvoicesActionPerformed(java.awt.event.ActionEvent evt) {
        
  //GeneralBillingIntfrcurreralBillingIGeneralBillingIntfrcurr GeneralBillingIntfrcurr(connectDB,pConnDB);
        
        // comp.getContentPane();
        if(schemeDebts == null){
            schemeDebts = new com.afrisoftech.accounting.FinSchemeInvsNhifIntfr(connectDB,pConnDB);
        }
        schemeDebts.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.removeAll();
         this.setTitle("IN-PATIENT OPERATIONS :: Finalise patient invoices".toUpperCase());
        comboTaskPanels.add(schemeDebts.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
   /* private void invoicesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
         this.setTitle("IN-PATIENT OPERATIONS :: Finalise cash invoice".toUpperCase());
        if(cashInvoice == null){
            cashInvoice = new com.afrisoftech.hospital.FinalisingInpatientBillIntfr(connectDB,pConnDB);
        }
        cashInvoice.jButton4111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        
        comboTaskPanels.add(cashInvoice.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }*/
    private void inpatientReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
                
                if (inpatientReceipts == null){
                    inpatientReceipts = new com.afrisoftech.accounting.InpatientRecpIntfr(connectDB,pConnDB);
                    inpatientReceipts.jButton4111.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                 this.setTitle("IN-PATIENT OPERATIONS :: In-patient Receipts".toUpperCase());
                comboTaskPanels.add(inpatientReceipts.getContentPane(), gridBagConstraints);
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
    private void otherrecptmnitActionPerformed(java.awt.event.ActionEvent evt) {
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
                if (BedCharges == null) {
                    BedCharges = new com.afrisoftech.accounting.InpatientDepositIntfr(connectDB,pConnDB);
                   /* BedCharges.jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jCheckBox1ActionPerformed(evt);
                        }
                    });*/
                    
                    BedCharges.jButton4111.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                 this.setTitle("IN-PATIENT OPERATIONS :: Patient Deposit".toUpperCase());
                comboTaskPanels.add(BedCharges.getContentPane(), gridBagConstraints);
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
    /*private void NhifRebeatButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
                if(NhifRebeat == null){
                    NhifRebeat = new com.afrisoftech.hospital.NhifDebtsIntfr(connectDB,pConnDB);
                    NhifRebeat.jButton41.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                 this.setTitle("IN-PATIENT OPERATIONS :: nhif rebeat".toUpperCase());
                comboTaskPanels.add(NhifRebeat.getContentPane(), gridBagConstraints);
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
    }*/
    private void patientDischargeActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;
        
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '"+System.getProperty("currentuser")+"' AND (status = 'Running' OR status = 'Suspended')");
            
            while (rset1.next()) {
                
                rowsNo = rset1.getInt(1);
                
            }
        }catch (java.sql.SQLException sqE) {
            sqE.printStackTrace();
        }
        if (rowsNo ==0) {
            
            int openShift = javax.swing.JOptionPane.showConfirmDialog(this, "Opening new shift", "Starting a new shift ...", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
            
            if (openShift == javax.swing.JOptionPane.YES_OPTION) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                
                long dateNow = calendar.getTimeInMillis();
                
                java.sql.Date datenowSql1= new java.sql.Date(dateNow);
                
                System.out.println(datenowSql1.toString());
                
                java.sql.Timestamp datenowSql= new java.sql.Timestamp(dateNow);
                
                System.out.println(datenowSql.toString());
                
                String current_user;
                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO ac_shifts values(?,?)");
                    pstmt.setString(1, System.getProperty("cashpoint"));
                    pstmt.setString(2,"Running");
                    pstmt.executeUpdate();
                }catch (java.sql.SQLException sqE) {
                    sqE.printStackTrace();
                }
                System.out.println("Opening shift");
                
                //  status = true;
                
                
                //     com.afrisoftech.accounting.PatientDischargeIntfr comp = new com.afrisoftech.accounting.PatientDischargeIntfr(connectDB,pConnDB);
                // saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                cashRunning = true;
                
                cashThread = new java.lang.Thread(this, "Cash");
                
                cashThread.start();
            }
        } else {
            
            //   while (rset.next()){
            
            //if(this.getShiftStatus()){
            // com.afrisoftech.accounting.PatientDischargeIntfr comp = new com.afrisoftech.accounting.PatientDischargeIntfr(connectDB,pConnDB);
            // saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
            cashRunning = true;
            
            cashThread = new java.lang.Thread(this, "Cash");
            
            cashThread.start();
            
        }
        
        // AdmissionIntfr1nIntfr1 compAdmissionIntfr1onIntfr1(connectDB,pConnDB);
        
        // comp.getContentPane();
        // openShiftAction.
        
        threadSales = true;
        
        java.lang.Thread threadSalesOpen = new java.lang.Thread(this, "Sales Open");
        
        threadSalesOpen.start();
        
        
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
                 this.setTitle("IN-PATIENT OPERATIONS :: Patient discharge".toUpperCase());
                
                //   comboTaskPanels.add(patientRegister.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();
                
                loadingMode = false;
                
                
                
                stopRunning();
                
                
                patientRunning = false;
            }
            
            
            patientRunning = false;
            
            cashRunning = false;
            
            
            
            if (!patientRunning) {
                
                stop();
                
              //  Thread.currentThread().destroy();
            }
            
        } else if (java.lang.Thread.currentThread().getName().matches("Cash")){
            
            
            System.out.println("Thread is "+java.lang.Thread.currentThread().getName());
            
            while (cashRunning) {
                
                //  if (getShiftStatus()) {
                
                loadingMode = true;
                
                // progressBarPopup.setVisible(true);
                
                aliveStatus = true;
                
                startRunning();
                
                if (PatientDischarge == null){
                    PatientDischarge = new com.afrisoftech.hospital.PatientDischargeIntfr(connectDB,pConnDB);
                }
               /* PatientDischarge.jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jCheckBox3ActionPerformed(evt);
                    }
                });
                *
                
                PatientDischarge.jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jCheckBox6ActionPerformed(evt);
                    }
                });
                */
                
                
                PatientDischarge.jButton4.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboTaskPanels.removeAll();
                        System.setProperty("billpmnt","closed");
                        repaint();
                    }
                });
                comboTaskPanels.removeAll();
                 this.setTitle("IN-PATIENT OPERATIONS :: Patient discharge".toUpperCase());
                comboTaskPanels.add(PatientDischarge.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();
                
                
                System.setProperty("billpmnt", "showing");
                
                System.out.println(System.getProperty("billpmnt"));
                
                try {
                    PatientDischarge.setSelected(true);
                    
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
                
               // Thread.currentThread().destroy();
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
    public com.afrisoftech.hospital.InpatientTasksIntfr getMainClass() {
        
        return this;
        
    }
    public void loadImage() {
        
        java.lang.String myAppFileUrl = null;
        
        myAppFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "hosprop.properties";
        
        try {
            
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);
            
            
            
            
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
    
 /*   private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.CopayReceiptsIntfr other = new com.afrisoftech.accounting.CopayReceiptsIntfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        other.setVisible(true);         // Add your handling code here:
    }
    
    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.DoctorsFeesRctintfr other = new com.afrisoftech.accounting.DoctorsFeesRctintfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        other.setVisible(true);    // Add your handling code here:
    }
    
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.PayerRecptIntfr other = new com.afrisoftech.accounting.PayerRecptIntfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        other.setVisible(true);    // Add your handling code here:
    }
  */
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
