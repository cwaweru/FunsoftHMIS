
/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */

package com.afrisoftech.hospinventory;

/**
 *
 * @author  root
 */
public class BillingTasksIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {
    
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
    
    com.afrisoftech.hospital.GeneralBillingIntfr generaBilling = null;
    
    com.afrisoftech.hospital.BillingAdjGen2Intfr billAdjustment = null;
    
    com.afrisoftech.hospinventory.PatientsBillingIntfr_ pharmacyBilling = null;
    
    com.afrisoftech.hospinventory.PharmReturnsIntfr pharmacyReturns = null;
    
    com.afrisoftech.hospinventory.DirectPurchintfr directPurchasing = null;
    
    com.afrisoftech.hospinventory.Receivingintfr receingLpo = null;
    
    com.afrisoftech.hospinventory.Issiuingintfr issuingStock = null;
    
    com.afrisoftech.hospinventory.Stocktransfersintfr interStorTransfer = null;
    
    com.afrisoftech.hospinventory.PosBillingIntfr amendInvoice = null;
    
    private ProgressThread progressThread;
    
    Thread patientThread = null;
    
    Thread cashThread = null;
    
    public final javax.swing.JButton bookBills;
    
    public BillingTasksIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        
        
        loadImage();
        
        desktopPaneIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg","c:/Tests/clouds.jpg"));
        
        // openShiftAction = new OpenShiftAction();
        
        
        
        
        
        initComponents();
        //com.afrisoftech.hospital.HospitalMain.mainSplitPane.setDividerLocation(0);
        mainTaskSplitPane.setDividerLocation(120);
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
        
        bookBills = new javax.swing.JButton("General Billing");
        bookBills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookBillsActionPerformed(evt);
            }
        });
        bookBills.setIcon(new javax.swing.ImageIcon(getClass().getResource("/005_spreadsheet_template.png")));
        
        
        
        javax.swing.JButton approvebill = new javax.swing.JButton("Bill Adjustment");
        approvebill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvebillActionPerformed(evt);
            }
        });
        
        //  approvebill.setA
        
        approvebill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-gmenu.png")));
        
        javax.swing.JButton payButton = new javax.swing.JButton("Pharmacy Billing");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
                
            }
        });
        
        payButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-gnumeric.png")));
        
        javax.swing.JButton adjustPayButton = new javax.swing.JButton("Pharmacy Adjustment");
        adjustPayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjustPayButtonActionPerformed(evt);
            }
        });
        adjustPayButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redhat-tools.png")));
        
        javax.swing.JButton lumpSumButton = new javax.swing.JButton("Receive Orders");
        lumpSumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lumpSumButtonActionPerformed(evt);
            }
        });
        lumpSumButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gedit-icon.png")));
        
        javax.swing.JButton consultantButton = new javax.swing.JButton("Direct Purchasing");
        consultantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultantButtonActionPerformed(evt);
            }
        });
        consultantButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/everything.png")));
        
        //javax.swing.JButton patientdbButton = new javax.swing.JButton("Visitor Activation");
        
        //patientdbButton.setEnabled(false);
        
        //  generaBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server-cfg.png")));
        
        
        javax.swing.JButton journalButton = new javax.swing.JButton("Issuing");
        journalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                journalButtonActionPerformed(evt);
            }
        });
        journalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msgpend.png")));
        
        javax.swing.JButton interStorTransferButton = new javax.swing.JButton("Stores Transfer");
        interStorTransferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interStorTransferButtonActionPerformed(evt);
            }
        });
        interStorTransferButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/applet-okay.png")));
        
        javax.swing.JButton PosBillingButton = new javax.swing.JButton("Pharmacy POS");
        PosBillingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PosBillingButtonActionPerformed(evt);
            }
        });
        PosBillingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-html.png")));//  com.l2fprod.common.swing.icons.EmptyIcon
         
        if (StockMain.patientbillingmnit.isVisible()){
            comboButtonBar.add(payButton);
        }
        if (StockMain.pharmacyoperatinsmnit.isVisible()){
            comboButtonBar.add(PosBillingButton);
        }
        if (StockMain.pharmacyreturnsmnit.isVisible()){
            comboButtonBar.add(adjustPayButton);
        }
        
        /*if (com.afrisoftech.mrservices.ServiceMain.generalmnit.isVisible()){
            comboButtonBar.add(bookBills);
        }
        if (com.afrisoftech.mrservices.ServiceMain.billadjustmentmnit.isVisible()){
            comboButtonBar.add(approvebill);
        }
        
        if (StockMain.directordermnit.isVisible()){
            comboButtonBar.add(consultantButton);
        }*/
        if (StockMain.receivingmnit.isVisible()){
            comboButtonBar.add(lumpSumButton);
        }
        if (StockMain.issuingmnit.isVisible()){
            comboButtonBar.add(journalButton);
        }
      //  if (StockMain.stocktransmnit.isVisible()){
      //      comboButtonBar.add(interStorTransferButton);
      //  }
        
        
        
        // this.mainTaskSplitPane.setLeftComponent(comboButtonBar);
        buttonBarScrollPane.setViewportView(comboButtonBar);
        
        this.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getSize());
        
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
        setTitle("BILLING OPERATIONS");
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
  /*  private void approvebillActionPerformed(java.awt.event.ActionEvent evt) {
   
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
    
    private void approvebillActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        
        comboTaskPanels.removeAll();
        this.setTitle("BILLING OPERATIONS :: Adjust bills".toUpperCase());
        
        if (billAdjustment == null){
            billAdjustment = new com.afrisoftech.hospital.BillingAdjGen2Intfr(connectDB,pConnDB);
        }
        
        billAdjustment.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(billAdjustment.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    
    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        this.setTitle("BILLING OPERATIONS :: Pharmacy Billing".toUpperCase());
        comboTaskPanels.removeAll();
        
        if (pharmacyBilling == null) {
            
            pharmacyBilling = new com.afrisoftech.hospinventory.PatientsBillingIntfr_(connectDB,pConnDB);
        }
        
        pharmacyBilling.closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(pharmacyBilling.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    
    private void adjustPayButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        this.setTitle("BILLING OPERATIONS :: Pharmacy Bill Adjustment".toUpperCase());
        
        comboTaskPanels.removeAll();
        if (pharmacyReturns == null){
            pharmacyReturns = new com.afrisoftech.hospinventory.PharmReturnsIntfr(connectDB,pConnDB);
        }
        
        
        pharmacyReturns.jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(pharmacyReturns.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void bookBillsActionPerformed(java.awt.event.ActionEvent evt) {
        
        //        PatientRegisterIntfr comp = new PatientRegisterIntfr(connectDB,pConnDB);
        
        // comp.getContentPane();
        
        this.invalidate();
        
        comboTaskPanels.removeAll();
        
        this.setTitle("BILLING OPERATIONS :: General Billing".toUpperCase());
        if(generaBilling == null){
            generaBilling = new com.afrisoftech.hospital.GeneralBillingIntfr(connectDB,pConnDB);
        }
        
        generaBilling.closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(generaBilling.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void lumpSumButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("SUPPLIER OPERATIONS :: Book Supplier Invoice [ Receiving ]".toUpperCase());
        if (receingLpo == null){
            receingLpo = new com.afrisoftech.hospinventory.Receivingintfr(connectDB,pConnDB);
        }
        
        receingLpo.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(receingLpo.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void consultantButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("SUPPLIER OPERATIONS :: Book supplier invoice [ Direct Purchasing ]".toUpperCase());
        if(directPurchasing == null){
            directPurchasing = new com.afrisoftech.hospinventory.DirectPurchintfr(connectDB,pConnDB);
        }
        
        directPurchasing.exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(directPurchasing.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        // Add your handling code here:
    }
    private void journalButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("STORE OPERATIONS :: Stock distribution [Issuing]".toUpperCase());
        if (issuingStock == null){
            issuingStock = new com.afrisoftech.hospinventory.Issiuingintfr(connectDB,pConnDB);
        }
        issuingStock.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(issuingStock.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
    }
    private void interStorTransferButtonActionPerformed(java.awt.event.ActionEvent evt) {
        comboTaskPanels.removeAll();
        
        this.setTitle("STORE OPERATIONS :: Inter Store Transfer".toUpperCase());
        if (interStorTransfer == null){
            interStorTransfer = new com.afrisoftech.hospinventory.Stocktransfersintfr(connectDB,pConnDB);
        }
        
        interStorTransfer.jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        
        comboTaskPanels.add(interStorTransfer.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();// saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
    }
    private void PosBillingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        comboTaskPanels.removeAll();
        this.setTitle("SUPPLIER OPERATIONS :: Pharmacy P.O.S".toUpperCase());
        
        if (amendInvoice == null){
            
            amendInvoice = new com.afrisoftech.hospinventory.PosBillingIntfr(connectDB,pConnDB);
        }
        
        amendInvoice.jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(amendInvoice.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();
        
        
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
                
                comboTaskPanels.add(generaBilling.getContentPane(), gridBagConstraints);
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
                
                comboTaskPanels.add(generaBilling.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();
                //saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                
                
                System.setProperty("billpmnt", "showing");
                
                System.out.println(System.getProperty("billpmnt"));
                
                try {
                    generaBilling.setSelected(true);
                    
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
    public com.afrisoftech.hospinventory.BillingTasksIntfr getMainClass() {
        
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
