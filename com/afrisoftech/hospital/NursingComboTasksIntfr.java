
/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */
package com.afrisoftech.hospital;

/**
 *
 * @author  francis
 */
public class NursingComboTasksIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {

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
    com.afrisoftech.hospinventory.PatientsBillingIpIntfr nurseBillingMedicines = null;
    PatientRegisterIntfr patientRegister = null;
    com.afrisoftech.hospital.GeneralBillingIntfr generalBilling = null;
    com.afrisoftech.accounting.FinSchemeInvsIntfr schemeDebts = null;
    private ProgressThread progressThread;
    Thread patientThread = null;
    Thread cashThread = null;
    com.afrisoftech.laboratory.FluidChartIntfr fluidChart = null;
    com.afrisoftech.accounting.InpatientRecpIntfr inpatientReceipts = null;
    com.afrisoftech.hospinventory.RaiseProcRequisintfr nursingRequisitions = null;
    com.afrisoftech.hospinventory.PatientsBillingIntfr_ billMedicines = null;
    com.afrisoftech.hospital.VitalSignRecIntfr vitalSigns = null;
    com.afrisoftech.accounting.GovBillPaymentsIntfr govPos = null;
    public final javax.swing.JButton registrationButton;// = null;
    javax.swing.JButton adjPatientbillButton = null;

    public NursingComboTasksIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, com.afrisoftech.hospital.HospitalMain parentHospitalFrame) {



        openShiftAction = new OpenShiftAction();

        connectDB = connDb;

        pConnDB = pconnDB;

        loadImage();

        desktopPaneIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg", "c:/Tests/clouds.jpg"));





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

        adjPatientbillButton = new javax.swing.JButton("Triage/Vital Signs");
        adjPatientbillButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientButtonActionPerformed(evt);
            }
        });

        //  adjPatientbillButton.setA

        adjPatientbillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/up2date.png")));

      /*  javax.swing.JButton salesButton = new javax.swing.JButton("POS (RECEIPTs)");
        salesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesButtonActionPerformed(evt);
            }
        });
*/
    //    salesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user_auth.png")));

        javax.swing.JButton gbillingButton = new javax.swing.JButton("Scheme Patient Billing");
        gbillingButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gbillingButtonActionPerformed(evt);
            }
        });
        gbillingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ax-applet.png")));

        javax.swing.JButton schemeButton = new javax.swing.JButton("General Billing");
        schemeButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemeButtonActionPerformed(evt);
            }
        });
        schemeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abiword_48.png")));

        javax.swing.JButton invoicesButton = new javax.swing.JButton("Fluid Chart");
        invoicesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicesButtonActionPerformed(evt);
            }
        });
        invoicesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editors.png")));

        javax.swing.JButton patientdbButton = new javax.swing.JButton("Visitor Activation");

        //patientdbButton.setEnabled(false);

        patientdbButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server-cfg.png")));

        registrationButton = new javax.swing.JButton("Patient Registration");
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

        javax.swing.JButton debtorsReceiptsButton = new javax.swing.JButton("Requisitions");
        debtorsReceiptsButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debtorsReceiptsButtonActionPerformed(evt);
            }
        });
        debtorsReceiptsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/office.png")));

        javax.swing.JButton otherReceiptsButton = new javax.swing.JButton("Billing Medicines");
        otherReceiptsButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherReceiptsButtonActionPerformed(evt);
            }
        });
        otherReceiptsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-html.png")));//  com.l2fprod.common.swing.icons.EmptyIcon


        javax.swing.JButton billingSuppliesButton = new javax.swing.JButton("Supplies & Dressings");
       billingSuppliesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                govReceiptsButtonActionPerformed(evt);
            }
        });
        billingSuppliesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome-laptop.png")));

         if (parentHospitalFrame.billadjustmentmnit.isVisible()) {
            comboButtonBar.add(adjPatientbillButton);
        }
                 if (parentHospitalFrame.Receiptsmnit.isVisible()) {
            comboButtonBar.add(debtorsReceiptsButton);
        }
      //  if (parentHospitalFrame.patientregstmnit.isVisible()) {
      //      comboButtonBar.add(registrationButton);
      //  }

        if (parentHospitalFrame.generalmnit.isVisible()) {
            comboButtonBar.add(schemeButton);
        }


//        if (parentHospitalFrame.receiptsmnit.isVisible()) {
//           comboButtonBar.add(salesButton);
//        }
       if (parentHospitalFrame.inpatcashinvmnit.isVisible()) {
            comboButtonBar.add(invoicesButton);
       }
   //     if (parentHospitalFrame.schememnit.isVisible()) {
   //         comboButtonBar.add(gbillingButton);
   //     }
        //    if (parentHospitalFrame.inReceiptsmnit.isVisible()) {
        //        comboButtonBar.add(inpatientReceiptsButton);
        //    }

        if (parentHospitalFrame.otherrecptmnit.isVisible()) {
            comboButtonBar.add(otherReceiptsButton);
        }

        if (parentHospitalFrame.govPosmnit.isVisible()) {
            comboButtonBar.add(billingSuppliesButton);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
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
        ;

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("NURSING OPERATIONS CENTRE");
        setToolTipText("Front End Operations Desk");
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

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

        setBounds(0, 0, 869, 547);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // Add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated
    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {

        this.invalidate();

        comboTaskPanels.removeAll();
        this.setTitle("NURSING OPERATIONS CENTRE :: Triage/Vital Signs".toUpperCase());
        if (vitalSigns == null) {
            vitalSigns = new com.afrisoftech.hospital.VitalSignRecIntfr(connectDB, pConnDB);

        }

        vitalSigns.closeFormBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });
        comboTaskPanels.add(vitalSigns.getContentPane(), gridBagConstraints);
        this.invalidate();
        this.repaint();
        this.validate();

        // Add your handling code here:
    }

    private void schemeButtonActionPerformed(java.awt.event.ActionEvent evt) {

        //      PatientRegisterIntfr1r1 comp = nePatientRegisterIntfr1fr1(connectDB,pConnDB);

        //   comp.getContentPane();

        this.invalidate();

        comboTaskPanels.removeAll();
        this.setTitle("NURSING OPERATIONS CENTRE :: General Billing".toUpperCase());

        if (generalBilling == null) {
            generalBilling = new com.afrisoftech.hospital.GeneralBillingIntfr(connectDB, pConnDB);

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

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int rowsNo = 0;

        try {

            String cashPointPett = null;
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.lang.Boolean registerReceipt = false;
            java.sql.Statement stm12fd = connectDB.createStatement();
            java.sql.ResultSet rse12fd = stm12fd.executeQuery("SELECT register_receipt FROM pb_patient_names");
            while (rse12fd.next()) {
                registerReceipt = rse12fd.getBoolean(1);
            }
            if (registerReceipt) {
                // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
                java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '" + System.getProperty("currentuser") + "' AND (status = 'Running' OR status = 'Suspended')");

                while (rset1.next()) {

                    rowsNo = rset1.getInt(1);

                }
                if (rowsNo < 1) {

                    int openShift = javax.swing.JOptionPane.showConfirmDialog(this, "Opening new shift", "Starting a new shift ...", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

                    if (openShift == javax.swing.JOptionPane.YES_OPTION) {
                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                        System.out.println(datenowSql1.toString());

                        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                        System.out.println(datenowSql.toString());

                        String current_user;


                        java.sql.Statement stmtf = connectDB.createStatement();
                        java.sql.ResultSet rsetf = stmtf.executeQuery("select code from ac_cash_points_setup where description = current_user");
                        while (rsetf.next()) {
                            //  cashPointPett = rsetf.getObject(1).toString();
                        }


                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO ac_shifts values(?,?)");
                        pstmt.setString(1, System.getProperty("cashpoint"));
                        //pstmt.setString(1, cashPointPett);
                        pstmt.setString(2, "Running");
                        pstmt.executeUpdate();

                    }
                }
            }

        } catch (java.sql.SQLException sql) {
            javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        if (patientRegister == null) {
            patientRegister = new PatientRegisterIntfr(connectDB, pConnDB);
        }
        patientRegister.closeFormBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });

        comboTaskPanels.removeAll();
        this.setTitle("NURSING OPERATIONS CENTRE :: Patient Registration".toUpperCase());
        comboTaskPanels.add(patientRegister.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();

        // Add your handling code here:
    }

    private void gbillingButtonActionPerformed(java.awt.event.ActionEvent evt) {

        //GeneralBillingIntfr(connectDB,pConnDB);

        // comp.getContentPane();
        if (schemeDebts == null) {
            schemeDebts = new com.afrisoftech.accounting.FinSchemeInvsIntfr(connectDB, pConnDB);
        }
        schemeDebts.jButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });

        comboTaskPanels.removeAll();
        this.setTitle("NURSING OPERATIONS CENTRE :: Finalise Scheme Invoices".toUpperCase());
        comboTaskPanels.add(schemeDebts.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();

        // Add your handling code here:
    }

    private void invoicesButtonActionPerformed(java.awt.event.ActionEvent evt) {

        comboTaskPanels.removeAll();
        this.setTitle("NURSING OPERATIONS CENTRE :: Fluid Chart".toUpperCase());
        if (fluidChart == null) {
            fluidChart = new com.afrisoftech.laboratory.FluidChartIntfr(connectDB, pConnDB);
        }
        fluidChart.jButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTaskPanels.removeAll();
                repaint();
            }
        });


        comboTaskPanels.add(fluidChart.getContentPane(), gridBagConstraints);
        this.invalidate();
        // comboTaskPanels.transferFocusUpCycle();
        this.repaint();
        this.validate();

        // Add your handling code here:
    }

    private void inpatientReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;

        try {

            String cashPointPett = null;
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '" + System.getProperty("currentuser") + "' AND (status = 'Running' OR status = 'Suspended')");

            while (rset1.next()) {

                rowsNo = rset1.getInt(1);

            }
            if (rowsNo < 1) {

                int openShift = javax.swing.JOptionPane.showConfirmDialog(this, "Opening new shift", "Starting a new shift ...", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

                if (openShift == javax.swing.JOptionPane.YES_OPTION) {
                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                    System.out.println(datenowSql1.toString());

                    java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                    System.out.println(datenowSql.toString());

                    String current_user;

                    try {

                        java.sql.Statement stmtf = connectDB.createStatement();
                        java.sql.ResultSet rsetf = stmtf.executeQuery("select code from ac_cash_points_setup where description = current_user");
                        while (rsetf.next()) {
                            //  cashPointPett = rsetf.getObject(1).toString();
                        }
                    } catch (java.sql.SQLException sqe) {
                        sqe.printStackTrace();
                        System.out.println("select not successful");
                    }
                    try {
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO ac_shifts values(?,?)");
                        pstmt.setString(1, System.getProperty("cashpoint"));
                        //pstmt.setString(1, cashPointPett);
                        pstmt.setString(2, "Running");
                        pstmt.executeUpdate();
                    } catch (java.sql.SQLException sqE) {
                        sqE.printStackTrace();
                    }
                    System.out.println("Opening shift");
                }
            } else {

                if (inpatientReceipts == null) {
                    inpatientReceipts = new com.afrisoftech.accounting.InpatientRecpIntfr(connectDB, pConnDB);
                    inpatientReceipts.jButton4111.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                this.setTitle("NURSING OPERATIONS CENTRE :: In-Patient Receipts".toUpperCase());
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
        } catch (java.sql.SQLException sqlex) {
            javax.swing.JOptionPane.showMessageDialog(this, sqlex.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());

        }


        // Add your handling code here:
    }

    private void debtorsReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {

                if (nursingRequisitions == null) {
                    nursingRequisitions= new com.afrisoftech.hospinventory.RaiseProcRequisintfr(connectDB, pConnDB);


                    nursingRequisitions.exitbtn.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                this.setTitle("NURSING OPERATIONS CENTRE :: Internal Requisitions".toUpperCase());
                comboTaskPanels.add(nursingRequisitions.getContentPane(), gridBagConstraints);
                this.invalidate();
                // comboTaskPanels.transferFocusUpCycle();
                this.repaint();
                this.validate();
    }

    private void govReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {

                     nurseBillingMedicines = new com.afrisoftech.hospinventory.PatientsBillingIpIntfr(connectDB, pConnDB);

                    nurseBillingMedicines.jButton41.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });

                    comboTaskPanels.removeAll();
                    this.setTitle("NURSING OPERATIONS CENTRE :: BILLING - Supplies and Dressings".toUpperCase());

                    comboTaskPanels.add(nurseBillingMedicines.getContentPane(), gridBagConstraints);
                    this.invalidate();
                    // comboTaskPanels.transferFocusUpCycle();
                    this.repaint();
                    this.validate();
          
    }

    private void otherReceiptsButtonActionPerformed(java.awt.event.ActionEvent evt) {
                if (billMedicines == null) {
                    billMedicines = new com.afrisoftech.hospinventory.PatientsBillingIntfr_(connectDB, pConnDB);
                    billMedicines.closeBtn.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboTaskPanels.removeAll();
                            repaint();
                        }
                    });
                }
                comboTaskPanels.removeAll();
                this.setTitle("NURSING OPERATIONS CENTRE :: Billing for Medicine".toUpperCase());
                comboTaskPanels.add(billMedicines.getContentPane(), gridBagConstraints);
                this.invalidate();
                // comboTaskPanels.transferFocusUpCycle();
                this.repaint();
                this.validate();


    }

    private void salesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowsNo = 0;

        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.Statement stmt1 = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("select user,count(debit),sum(debit + credit) from ac_cash_book where account_no ='"+jComboBox2.getSelectedItem()+"' and description !='' and reconciled = false AND date BETWEEN '"+datePicker1.getDate().toString()+"' AND '"+datePicker2.getDate().toString()+"' group by account_name");
            java.sql.ResultSet rset1 = stmt1.executeQuery("select count(user_name) from ac_shifts where  user_name ilike '" + System.getProperty("currentuser") + "' AND (status = 'Running' OR status = 'Suspended')");

            while (rset1.next()) {

                rowsNo = rset1.getInt(1);

            }
        } catch (java.sql.SQLException sqE) {
            sqE.printStackTrace();
        }
        if (rowsNo == 0) {

            int openShift = javax.swing.JOptionPane.showConfirmDialog(this, "Opening new shift", "Starting a new shift ...", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (openShift == javax.swing.JOptionPane.YES_OPTION) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();

                long dateNow = calendar.getTimeInMillis();

                java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                System.out.println(datenowSql1.toString());

                java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

                System.out.println(datenowSql.toString());

                String current_user;
                try {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO ac_shifts values(?,?)");
                    pstmt.setString(1, System.getProperty("cashpoint"));
                    pstmt.setString(2, "Running");
                    pstmt.executeUpdate();
                } catch (java.sql.SQLException sqE) {
                    sqE.printStackTrace();
                }
                System.out.println("Opening shift");

                //  status = true;


                //     com.afrisoftech.accounting.BillPaymentsIntfr comp = new com.afrisoftech.accounting.BillPaymentsIntfr(connectDB,pConnDB);
                // saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
                cashRunning = true;

                cashThread = new java.lang.Thread(this, "Cash");

                cashThread.start();
            }
        } else {

            //   while (rset.next()){

            //if(this.getShiftStatus()){
            // com.afrisoftech.accounting.BillPaymentsIntfr comp = new com.afrisoftech.accounting.BillPaymentsIntfr(connectDB,pConnDB);
            // saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
            cashRunning = true;

            cashThread = new java.lang.Thread(this, "Cash");

            cashThread.start();

        }

        //    PatientRegisterIntfr1tfr1 comp = PatientRegisterIntfr1ntfr1(connectDB,pConnDB);

        // comp.getContentPane();
        // openShiftAction.

        threadSales = true;

        java.lang.Thread threadSalesOpen = new java.lang.Thread(this, "Sales Open");

        threadSalesOpen.start();


    }

    public void run() {
        //   this.setCursor(javax.swing.JFrame.WAIT_CURSOR);
        System.out.println("Thread is " + java.lang.Thread.currentThread().getName());

        if (java.lang.Thread.currentThread().getName().matches("Patient")) {

            loadingMode = true;

            //progressBarPopup.setVisible(true);

            aliveStatus = true;

            startRunning();
            //            System.out.println("Thread Patient starting...");
            while (patientRunning) {
                this.invalidate();

                comboTaskPanels.removeAll();
                this.setTitle("NURSING OPERATIONS CENTRE :: Pos [Receipts]".toUpperCase());

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

                Thread.currentThread().destroy();
            }

        } else if (java.lang.Thread.currentThread().getName().matches("Cash")) {


            System.out.println("Thread is " + java.lang.Thread.currentThread().getName());

            while (cashRunning) {

                //  if (getShiftStatus()) {

                loadingMode = true;

                // progressBarPopup.setVisible(true);

                aliveStatus = true;

                startRunning();

                if (nurseBillingMedicines == null) {
                    nurseBillingMedicines = new com.afrisoftech.hospinventory.PatientsBillingIpIntfr(connectDB, pConnDB);
                }

                nurseBillingMedicines.jButton41.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboTaskPanels.removeAll();
                       // System.setProperty("billpmnt", "closed");
                        repaint();
                    }
                });
                comboTaskPanels.removeAll();
                this.setTitle("NURSING OPERATIONS CENTRE :: Bill Supplies and Dressings".toUpperCase());
                comboTaskPanels.add(nurseBillingMedicines.getContentPane(), gridBagConstraints);
                this.invalidate();
                this.repaint();
                this.validate();


               // System.setProperty("billpmnt", "showing");

               // System.out.println(System.getProperty("billpmnt"));

                try {
                    nurseBillingMedicines.setSelected(true);

                } catch (java.beans.PropertyVetoException pvt) {
                }
                try {
                    Thread.currentThread().sleep(100);

                } catch (java.lang.InterruptedException IntExec) {
                    System.out.println(IntExec.getMessage());
                }





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

            System.out.println("Progress Monitor object : " + progressMonitor.toString());

            progressMonitor.setMillisToDecideToPopup(1);
        }

        public void setStop(boolean value) {

            stopStatus = value;

            progressMonitor.close();

        }

        public void run() {

            System.out.println("Progress Monitor object 1 : " + progressMonitor.toString());

            progressMonitor.setNote("Loading. Please wait...");

            while (loadingMode) {

                min = 0;

                max = 100000;

                for (int i = min; i <= max; i++) {

                    if (stopStatus) {

                        break;

                    } else {

                        progressMonitor.setProgress(i);

                        progressMonitor.setNote("" + (i * 2) + "%");
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

    public com.afrisoftech.hospital.NursingComboTasksIntfr getMainClass() {

        return this;

    }

    public void loadImage() {

        java.lang.String myAppFileUrl = null;

        myAppFileUrl = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                + "hosprop.properties";

        try {

            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);




            java.util.Properties appProp = new java.util.Properties();

            try {

                appProp.load(propInFile);

                backgrdimg = appProp.getProperty("backgrdimg", "c:/Tests/clouds.jpg");

                System.setProperty("backgrdimg", backgrdimg);

                propInFile.close();

                //  return dbServerIp;


            } catch (java.io.IOException ioExec) {

                System.out.println(ioExec.getMessage());

            }

            // return dbServerIp;

        } catch (java.lang.Exception exc) {

            System.out.println(exc.getMessage());


        }

    }

  /*  private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.CopayReceiptsIntfr other = new com.afrisoftech.accounting.CopayReceiptsIntfr(connectDB, pConnDB, com.afrisoftech.accounting.GovBillPaymentsIntfr() jTextField311.getText());
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);         // Add your handling code here:
    }*/

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.DoctorsFeesRctintfr other = new com.afrisoftech.accounting.DoctorsFeesRctintfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);    // Add your handling code here:
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        com.afrisoftech.accounting.PayerRecptIntfr other = new com.afrisoftech.accounting.PayerRecptIntfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);    // Add your handling code here:
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonBar;
    private javax.swing.JScrollPane buttonBarScrollPane;
    private javax.swing.JPanel comboTaskPanels;
    private javax.swing.JPanel mainComboTasksPane;
    private javax.swing.JSplitPane mainTaskSplitPane;
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
