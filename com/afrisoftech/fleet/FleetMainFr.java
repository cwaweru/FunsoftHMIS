/*
 * payroll.java
 *
 * Created on October 21, 2002, 11:26 AM
 */

package com.afrisoftech.fleet;



import java.beans.PropertyVetoException;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.io.IOException;
import javax.help.*;
import javax.help.search.*;
import com.l2fprod.common.swing.plaf.LookAndFeelAddons;

import com.l2fprod.common.swing.JTaskPane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.LookAndFeelTweaks;
import com.l2fprod.common.swing.plaf.LookAndFeelAddons;
import com.l2fprod.common.swing.plaf.metal.MetalLookAndFeelAddons;
import com.l2fprod.common.swing.plaf.windows.WindowsClassicLookAndFeelAddons;
import com.l2fprod.common.swing.plaf.windows.WindowsLookAndFeelAddons;
import com.l2fprod.common.util.ResourceManager;


/*
 * @author  root
 */
public class FleetMainFr extends javax.swing.JFrame implements java.lang.Runnable{
    
    boolean isRunning = true;
    
    static java.lang.String psWord = null;
    
    boolean domainsReset = false;
    
    javax.swing.ImageIcon imgIcon;
    
    /** Creates new form payroll */
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    javax.swing.ImageIcon FleetMainPanelIcon;
    java.lang.String path2Acrobat = null;
    java.lang.String backgrdimg;
    
    javax.swing.ImageIcon desktopPaneIcon;
    javax.help.HelpSet helpHS = null;
    
    javax.help.HelpBroker helpBroker;
    
    javax.help.HelpSet apiHS = null;
    javax.swing.tree.DefaultMutableTreeNode top;
    
    com.afrisoftech.lib.ParseMenuBar parseMenuBar;
    javax.help.HelpBroker apiHB = null;
    //javax.swing.JDesktopPane desktop = new desktopPaneIcon
    static final java.lang.String helpSetName = "EpackSacco";
    
    static final java.lang.String helpSetLable = "E-Pack Sacco System";
    java.lang.String myAppOutFileUrl;
    
    static java.util.Properties appProp;
    
    static java.util.Properties appOutProp;
    int PaySlip = 0;
    int P9 = 1;
    int Deductions = 2;
    int ChqPayments = 3;
    int PayeList = 4;
    int CashPayments = 5;
    int SchemesContr = 6;
    int NetPay = 7;
    int DeptNetPay = 8;
    int PayMaster = 9;
    int Tax = 10;
    int StaffEarning = 11;
    int SaccoList = 12;
    int EmpList = 13;
    int PaySlips = 14;
    int NSSFLIST = 15;
    int NHIFLIST = 16;
    int UNIONLIST = 17;
    int NNAKLIST = 18;
    int SalaryJoun = 19;
    int PostedList = 20;
    int ProssecingPayroll = 21;
    int Summary = 22;
    int ApprovingPayroll = 23;
    int Helb = 24;
    
    
    /** Creates new form kitchen */
    public FleetMainFr(java.sql.Connection connDB, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {//, java.lang.String acrobatPath, java.lang.String psword) {
        psWord = System.getProperty("password");
        
        
        connectDB = connDB;
        
        pConnDB = pconnDB;
        path2Acrobat = System.getProperty("acrobatpath");;
        
        
        
        
        
        try {
            
            java.lang.ClassLoader classLoader = FleetMainFr.class.getClassLoader();
            
            java.net.URL urlHelpSet = javax.help.HelpSet.findHelpSet(classLoader, helpSetName);
            
            helpHS = new javax.help.HelpSet(classLoader, urlHelpSet);
            
        } catch(java.lang.Exception exec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, exec.getMessage());
            
        }
        
        helpBroker = helpHS.createHelpBroker();
        initComponents();
        java.lang.String backgrdimg;
        
        javax.swing.ImageIcon desktopPaneIcon;
        FleetMainPanelIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg","D:\\hospital\\system\\backgrounds\\more backgrounds\\images\\raingutter.JPG"));
        
    }
    public FleetMainFr(java.sql.Connection connDB, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, java.lang.String acrobatPath, java.lang.String psword, com.afrisoftech.hospinventory.StockMain secureStockMain, com.afrisoftech.hospayroll.PayrollMain securePayrollMain, com.afrisoftech.hr.HRMain secureHRMain) {
        try {
            try {
                try {
                    try {
                        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                        LookAndFeelAddons.setAddon(WindowsLookAndFeelAddons.class);
                    } catch(javax.swing.UnsupportedLookAndFeelException uns) {
                        uns.printStackTrace();
                    }
                } catch(java.lang.IllegalAccessException illegal) {
                    illegal.printStackTrace();
                }
            } catch(java.lang.InstantiationException inst) {
                inst.printStackTrace();
            }
        } catch(java.lang.ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        
        LookAndFeelAddons addon = LookAndFeelAddons.getAddon();
        
        
        
        
        
        
        desktopPaneIcon = new javax.swing.ImageIcon(System.getProperty("backgrdimg","c:/Tests/clouds.jpg"));
        
        
        
        
        myAppOutFileUrl = null;
        
        myAppOutFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "hosprop.properties";
        
        System.out.println("Properties file : "+myAppOutFileUrl);
        //        System.setProperty("cashpoint", cashpoint);
        
        System.setProperty("billpmnt", "notShowing");
        
        psWord = psword;
        
        connectDB = connDB;
        
        pConnDB = pconnDB;
        
        
        
        
        
        
        path2Acrobat = acrobatPath;
        
        try {
            
            java.lang.ClassLoader classLoader = FleetMainFr.class.getClassLoader();
            
            java.net.URL urlHelpSet = javax.help.HelpSet.findHelpSet(classLoader, helpSetName);
            
            helpHS = new javax.help.HelpSet(classLoader, urlHelpSet);
            
        } catch(java.lang.Exception exec) {
            
            exec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(this, exec.getMessage());
            
        }
        
        helpBroker = helpHS.createHelpBroker();
        
        initComponents();
        
        
        this.changeThemeByUrl(System.getProperty("defaultlnf", "com.l2fprod.gui.plaf.skin.SkinLookAndFeel"), getClass().getResource("/"+System.getProperty("defaulttheme", "xplunathemepack.zip")));
        
        
        this.FleetSplit1.setOpaque(false);
        
        
        
        
        
        userLabel.setText("Logged-on USER : [ "+System.getProperty("currentuser")+" ]");
        
        connectionLabel.setText("DB Connection Status : [CONNECTED]");
        
        try {
            
            activePane.setText("Database Info. : [ "+connectDB.getMetaData().getDatabaseProductName()+" Ver "+connectDB.getMetaData().getDatabaseProductVersion()+" ]");
            
            activeDatabase.setText("Database URL : [ "+connectDB.getMetaData().getURL()+" ]");
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
            
        }
        
        //        activeDatabase.setText("Active Database : ["+System.getProperty("activedatabase")+"]");
        
        //Set One touch expansion status to true f.
        
        
        
/*        activePaneThread = new java.lang.Thread(this, "activepane");
 
        activePaneThread.start();
 */
        
        //  }
        
        
        // }
        
        
        
        
        
        
        
        
        
        
        //  mainSplitPane1.setVisible(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        fmgrBttn = new javax.swing.JButton();
        calcBttn = new javax.swing.JButton();
        brwsrBttn = new javax.swing.JButton();
        editButtn = new javax.swing.JButton();
        mailBttn = new javax.swing.JButton();
        mtmediaBttn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        //helpButton.addActionListener(new javax.help.CSH.DisplayHelpAfterTracking(helpBroker));
        jPanel4 = new javax.swing.JPanel();
        maintoolbar = new javax.swing.JToolBar();
        tool5 = new javax.swing.JToolBar();
        jPanel5 = new javax.swing.JPanel();
        Addnewvehiclebtn2 = new javax.swing.JButton();
        Editbtn = new javax.swing.JButton();
        Deletevehiclebtn2 = new javax.swing.JButton();
        Workordersbtn2 = new javax.swing.JButton();
        Pmcheckbtn2 = new javax.swing.JButton();
        Pmschedrepairsbtn4 = new javax.swing.JButton();
        Repairsbtn4 = new javax.swing.JButton();
        Driversbtn2 = new javax.swing.JButton();
        Vendorbtn2 = new javax.swing.JButton();
        Partsbtn2 = new javax.swing.JButton();
        Reports1 = new javax.swing.JButton();
        jSeparator51 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        mainPanel = new javax.swing.JPanel() {

            public void paintComponent(java.awt.Graphics g) {

                java.awt.Dimension d = getSize();

                g.drawImage(FleetMainPanelIcon.getImage(), 0, 0, d.width, d.height,null);
                System.out.println("still drawing");
                setOpaque(false);

                super.paintComponent(g);

            }

        };
        ;
        FleetSplit1 = new javax.swing.JSplitPane();
        FleetMainPanel = new javax.swing.JPanel() {

            public void paintComponent(java.awt.Graphics g) {

                java.awt.Dimension d = getSize();

                g.drawImage(FleetMainPanelIcon.getImage(), 0, 0, d.width, d.height,null);
                System.out.println("still drawing");
                setOpaque(false);

                super.paintComponent(g);

            }

        };
        ;
        fleetdesktop = new kiwi.ui.KDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();

        jTree1 = new javax.swing.JTree();

        taskBar = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        connectionLabel = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        activePane = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        activeDatabase = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fleetSetupmn = new javax.swing.JMenu();
        legalstatusmnit = new javax.swing.JMenuItem();
        countrydistrictmn = new javax.swing.JMenu();
        countrymntfr = new javax.swing.JMenuItem();
        districtsmnit = new javax.swing.JMenuItem();
        departmentmnit = new javax.swing.JMenuItem();
        companyprofilemnit = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        driveremployeemnit = new javax.swing.JMenu();
        drivermnit = new javax.swing.JMenuItem();
        Employeemnit = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        vendorDBmn = new javax.swing.JMenu();
        vendormnit = new javax.swing.JMenuItem();
        vehicletypemnit = new javax.swing.JMenuItem();
        partequptmnit = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        exitmnit = new javax.swing.JMenuItem();
        fleetOperationsmn = new javax.swing.JMenu();
        AddVehiclemnit = new javax.swing.JMenuItem();
        Addvehicleduplicate = new javax.swing.JMenuItem();
        Fleetmanagermn = new javax.swing.JMenu();
        registrationpartimnit = new javax.swing.JMenuItem();
        purchaseinformnit = new javax.swing.JMenuItem();
        spescificationmnit = new javax.swing.JMenuItem();
        insurancemnit = new javax.swing.JMenuItem();
        loanleasemnit = new javax.swing.JMenuItem();
        photomnit = new javax.swing.JMenuItem();
        notesmnit = new javax.swing.JMenuItem();
        sepper1 = new javax.swing.JSeparator();
        Vehiclemn = new javax.swing.JMenu();
        issuelworkmnit = new javax.swing.JMenuItem();
        schedulerepairmnit = new javax.swing.JMenuItem();
        vehiclest1 = new javax.swing.JSeparator();
        Maintenanceduemnit = new javax.swing.JMenuItem();
        vehiclest2 = new javax.swing.JSeparator();
        Maintenancehistmnit = new javax.swing.JMenuItem();
        tirehistmnit = new javax.swing.JMenuItem();
        Coststatisticsmnit = new javax.swing.JMenuItem();
        vehiclest3 = new javax.swing.JSeparator();
        Currentmainstatusmnit = new javax.swing.JMenuItem();
        Lastpmsetupmnit = new javax.swing.JMenuItem();
        vehiclest4 = new javax.swing.JSeparator();
        Fuelhistorymnit = new javax.swing.JMenuItem();
        vehiclest5 = new javax.swing.JSeparator();
        Triphist = new javax.swing.JMenuItem();
        Maintenancemn = new javax.swing.JMenu();
        Pmcheckwizmn = new javax.swing.JMenu();
        Selectedvehiclemnit = new javax.swing.JMenuItem();
        Selecteddepmn = new javax.swing.JMenuItem();
        Selectedlocmn = new javax.swing.JMenuItem();
        Allvehiclemnit = new javax.swing.JMenuItem();
        Updatemetermnit = new javax.swing.JMenuItem();
        Maintenancesep1 = new javax.swing.JSeparator();
        Pmschedulesetupmnit = new javax.swing.JMenuItem();
        Pmassociatesmn = new javax.swing.JMenuItem();
        Scheduledrepairmnit = new javax.swing.JMenuItem();
        Maintenancesep2 = new javax.swing.JSeparator();
        IssueWorkordermnit = new javax.swing.JMenuItem();
        Workordermanagmnit = new javax.swing.JMenuItem();
        Inventorymn = new javax.swing.JMenu();
        Partsinventorymnit = new javax.swing.JMenuItem();
        Tireinventorymnit = new javax.swing.JMenuItem();
        sep1 = new javax.swing.JSeparator();
        Purchaseordersmnit = new javax.swing.JMenuItem();
        Purchaseorderaddmnit = new javax.swing.JMenuItem();
        sep2 = new javax.swing.JSeparator();
        Poreceiptbyponummnit = new javax.swing.JMenuItem();
        Poreceiptbypartmnit = new javax.swing.JMenuItem();
        sep3 = new javax.swing.JSeparator();
        Adjustrecievemnit = new javax.swing.JMenuItem();
        Billingmn = new javax.swing.JMenu();
        Generateinvomnit = new javax.swing.JMenuItem();
        Browseinvoicesmnit = new javax.swing.JMenuItem();
        sepat1 = new javax.swing.JSeparator();
        Recordpaymentsmnit = new javax.swing.JMenuItem();
        Browsepaymemnit = new javax.swing.JMenuItem();
        FleetUtilitiesmn = new javax.swing.JMenu();
        backgroundmnit = new javax.swing.JMenuItem();
        FleetReportsmn1 = new javax.swing.JMenu();
        billingmn = new javax.swing.JMenu();
        invoicesoutstandingmnit = new javax.swing.JMenuItem();
        invoicespaidinfullmnit = new javax.swing.JMenuItem();
        invoicessummaryoutstandingmnit = new javax.swing.JMenuItem();
        invoicessummarypaidinfullmnit = new javax.swing.JMenuItem();
        paymentsrecievedmnit = new javax.swing.JMenuItem();
        employeesmn = new javax.swing.JMenu();
        detailedcontactlistmnit = new javax.swing.JMenuItem();
        generalcontactlistmnit = new javax.swing.JMenuItem();
        personalinformationmnit = new javax.swing.JMenuItem();
        licenceinformationmnit = new javax.swing.JMenuItem();
        licencerenewalsduemnit = new javax.swing.JMenuItem();
        certificationsmnit = new javax.swing.JMenuItem();
        certificationsrenewalsmnit = new javax.swing.JMenuItem();
        phsicalinformationsmnit = new javax.swing.JMenuItem();
        physicalrenewalsduemnit = new javax.swing.JMenuItem();
        fleetmn = new javax.swing.JMenu();
        fleetlistingmnit = new javax.swing.JMenuItem();
        generalmnit = new javax.swing.JMenuItem();
        specificationspartsmnit = new javax.swing.JMenuItem();
        purchaseinformationmnit = new javax.swing.JMenuItem();
        registrationinformationmnit = new javax.swing.JMenuItem();
        insurancermnit = new javax.swing.JMenuItem();
        loanleaser = new javax.swing.JMenuItem();
        registrationrenewalduemnit = new javax.swing.JMenuItem();
        stateinspectionrenewalsduemnit = new javax.swing.JMenuItem();
        fuelpurchasemn = new javax.swing.JMenu();
        fuelpurchaseshistorymnit = new javax.swing.JMenuItem();
        fuelcostsummarymnit = new javax.swing.JMenuItem();
        maintanancehistorymn = new javax.swing.JMenu();
        historyoverviewmnit = new javax.swing.JMenuItem();
        completehistorymnit = new javax.swing.JMenuItem();
        pmhistorymnit = new javax.swing.JMenuItem();
        repairhistorymnit = new javax.swing.JMenuItem();
        partshistorymnit = new javax.swing.JMenuItem();
        labourhistorymnit = new javax.swing.JMenuItem();
        externalserviceshistorymnit = new javax.swing.JMenuItem();
        fuelpurchasesrmnit = new javax.swing.JMenuItem();
        historycostsummarymnit = new javax.swing.JMenuItem();
        vendorcostsummarymnit = new javax.swing.JMenuItem();
        partsinventorymn = new javax.swing.JMenu();
        partslistingmnit = new javax.swing.JMenuItem();
        partslistingbyvendormnit = new javax.swing.JMenuItem();
        partslistngcategorymnit = new javax.swing.JMenuItem();
        needreoderedmnit = new javax.swing.JMenuItem();
        recieptsmnit = new javax.swing.JMenuItem();
        partshstoryrmnit = new javax.swing.JMenuItem();
        partsusagemnit = new javax.swing.JMenuItem();
        stockvaluedetailedmnit = new javax.swing.JMenuItem();
        stockvaluesummarymnit = new javax.swing.JMenuItem();
        pmsevicesmn = new javax.swing.JMenu();
        pmschedulesmnit = new javax.swing.JMenuItem();
        pmlastperfomeddatamnit = new javax.swing.JMenuItem();
        currentpmstatusmnit = new javax.swing.JMenuItem();
        maintainanceduemnit = new javax.swing.JMenuItem();
        pmcoststatisticspervehicleequipmnit = new javax.swing.JMenuItem();
        pmcoststatisticsoveralmnit = new javax.swing.JMenuItem();
        purchaseodersmn = new javax.swing.JMenu();
        generateclosedmnit = new javax.swing.JMenuItem();
        generateoutstandingmnit = new javax.swing.JMenuItem();
        Recieptsrmnit = new javax.swing.JMenuItem();
        summaryclosedmnit = new javax.swing.JMenuItem();
        summaryoutstandingmnit = new javax.swing.JMenuItem();
        vendorsummarymnit = new javax.swing.JMenuItem();
        sheduledrepairsmn = new javax.swing.JMenu();
        outstndingallvehiclemnit = new javax.swing.JMenuItem();
        outstandingselectedvehicle = new javax.swing.JMenuItem();
        currentdueallvehiclemnit = new javax.swing.JMenuItem();
        currentlydueselectedvehiclesmnit = new javax.swing.JMenuItem();
        summaryreportsmn = new javax.swing.JMenu();
        pmcoststatisticspervehicleequiprmnit = new javax.swing.JMenuItem();
        pmcoststatisticsoverallmnit = new javax.swing.JMenuItem();
        historycostsummarurmnit = new javax.swing.JMenuItem();
        vendorcostsummaryrmnit = new javax.swing.JMenuItem();
        fuelcostsummaryrmnit = new javax.swing.JMenuItem();
        workordedersummaryopenmnit = new javax.swing.JMenuItem();
        workordersummaryclosedmnit = new javax.swing.JMenuItem();
        fleetlistingrmnit = new javax.swing.JMenuItem();
        travelmn = new javax.swing.JMenu();
        triphistorymnit = new javax.swing.JMenuItem();
        tripticketsmnit = new javax.swing.JMenuItem();
        usagebylocdeptvehicledetailedrmnit = new javax.swing.JMenuItem();
        usagebydriverdeptdetailedmnit = new javax.swing.JMenuItem();
        usagebylocdeptdriveroverviewmnit = new javax.swing.JMenuItem();
        usagebydriveroverviewmnit = new javax.swing.JMenuItem();
        tiresmn = new javax.swing.JMenu();
        tiresinventorylistingallmnit = new javax.swing.JMenuItem();
        tiresinventorylistingmountedmnit = new javax.swing.JMenuItem();
        tiresinventorylistingdismountedmnit = new javax.swing.JMenuItem();
        tirehistorybyvehicleequipmentmnit = new javax.swing.JMenuItem();
        tirehistorybytiremnit = new javax.swing.JMenuItem();
        vendorsmn = new javax.swing.JMenu();
        vendorslistingmnit = new javax.swing.JMenuItem();
        vendorscostsummarymnit = new javax.swing.JMenuItem();
        Workordersmnit = new javax.swing.JMenu();
        workordersopenmnit = new javax.swing.JMenuItem();
        workorderscompletemnit = new javax.swing.JMenuItem();
        worksummaryopenmnit = new javax.swing.JMenuItem();
        worksummaryclosedmnit = new javax.swing.JMenuItem();
        reportgeneratorrmnit = new javax.swing.JMenuItem();
        lnfmn = new javax.swing.JMenu();
        swinglnfmnit1 = new javax.swing.JCheckBoxMenuItem();
        winlnfmnit1 = new javax.swing.JCheckBoxMenuItem();
        motiflnfmnit1 = new javax.swing.JCheckBoxMenuItem();
        macmnit1 = new javax.swing.JCheckBoxMenuItem();
        skinmn = new javax.swing.JMenu();
        xpmnit1 = new javax.swing.JRadioButtonMenuItem();
        macos1mnit1 = new javax.swing.JRadioButtonMenuItem();
        whiltmnit1 = new javax.swing.JRadioButtonMenuItem();
        modernmnit1 = new javax.swing.JRadioButtonMenuItem();
        aquamnit1 = new javax.swing.JRadioButtonMenuItem();
        beosmnit1 = new javax.swing.JRadioButtonMenuItem();
        bbjmnit11 = new javax.swing.JRadioButtonMenuItem();
        FleetHelpmn = new javax.swing.JMenu();
        helpcontentsmnit = new javax.swing.JMenuItem();
        aboutmnit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(this.getCompanyName());
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jToolBar1.addSeparator(new java.awt.Dimension(3,20));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setMinimumSize(new java.awt.Dimension(500, 25));
        jToolBar1.setPreferredSize(new java.awt.Dimension(231, 25));
        jToolBar1.setOpaque(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(219, 35));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        fmgrBttn.setToolTipText("File Manager");
        fmgrBttn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fmgrBttn.setPreferredSize(new java.awt.Dimension(25, 25));
        fmgrBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fmgrBttnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(fmgrBttn, gridBagConstraints);

        calcBttn.setToolTipText("Calculator");
        calcBttn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        calcBttn.setPreferredSize(new java.awt.Dimension(25, 25));
        calcBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcBttnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(calcBttn, gridBagConstraints);

        brwsrBttn.setToolTipText("Internet browser");
        brwsrBttn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        brwsrBttn.setPreferredSize(new java.awt.Dimension(25, 25));
        brwsrBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brwsrBttnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(brwsrBttn, gridBagConstraints);

        editButtn.setToolTipText("Word processor");
        editButtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        editButtn.setPreferredSize(new java.awt.Dimension(25, 25));
        editButtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(editButtn, gridBagConstraints);

        mailBttn.setToolTipText("Email utility");
        mailBttn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mailBttn.setPreferredSize(new java.awt.Dimension(25, 25));
        mailBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailBttnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(mailBttn, gridBagConstraints);

        mtmediaBttn.setToolTipText("Multimedia player");
        mtmediaBttn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mtmediaBttn.setMaximumSize(new java.awt.Dimension(20, 37));
        mtmediaBttn.setMinimumSize(new java.awt.Dimension(30, 37));
        mtmediaBttn.setPreferredSize(new java.awt.Dimension(25, 25));
        mtmediaBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mtmediaBttnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(mtmediaBttn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jToolBar1.add(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jToolBar1, gridBagConstraints);

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setFloatable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(39, 35));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        helpButton.setToolTipText("Get system help");
        helpButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        helpButton.setPreferredSize(new java.awt.Dimension(25, 25));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(helpButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        jToolBar2.add(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jToolBar2, gridBagConstraints);

        maintoolbar.add(tool5);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        Addnewvehiclebtn2.setText("Add Vehicle");
        Addnewvehiclebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Addnewvehiclebtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Addnewvehiclebtn2, gridBagConstraints);

        Editbtn.setText("Edit ");
        Editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Editbtn, gridBagConstraints);

        Deletevehiclebtn2.setText("Delete ");
        Deletevehiclebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Deletevehiclebtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Deletevehiclebtn2, gridBagConstraints);

        Workordersbtn2.setText("Work Order");
        Workordersbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Workordersbtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Workordersbtn2, gridBagConstraints);

        Pmcheckbtn2.setText("PM Check");
        Pmcheckbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pmcheckbtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Pmcheckbtn2, gridBagConstraints);

        Pmschedrepairsbtn4.setText("PM Sched Repair");
        Pmschedrepairsbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pmschedrepairsbtn4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Pmschedrepairsbtn4, gridBagConstraints);

        Repairsbtn4.setText("Repairs");
        Repairsbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Repairsbtn4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Repairsbtn4, gridBagConstraints);

        Driversbtn2.setText("Drivers");
        Driversbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Driversbtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Driversbtn2, gridBagConstraints);

        Vendorbtn2.setText("Vendors");
        Vendorbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vendorbtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Vendorbtn2, gridBagConstraints);

        Partsbtn2.setText("Parts");
        Partsbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Partsbtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Partsbtn2, gridBagConstraints);

        Reports1.setText("Reports");
        Reports1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reports1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(Reports1, gridBagConstraints);
        jPanel5.add(jSeparator51, new java.awt.GridBagConstraints());

        maintoolbar.add(jPanel5);
        maintoolbar.add(jSeparator5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(maintoolbar, gridBagConstraints);

        mainPanel.setLayout(new java.awt.GridBagLayout());

        FleetSplit1.setDividerLocation(151);
        FleetSplit1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                FleetSplit1ComponentShown(evt);
            }
        });

        FleetMainPanel.setLayout(new java.awt.GridBagLayout());

        fleetdesktop.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        FleetMainPanel.add(fleetdesktop, gridBagConstraints);

        FleetSplit1.setRightComponent(FleetMainPanel);

        jTree1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTree1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        FleetSplit1.setLeftComponent(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        mainPanel.add(FleetSplit1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(mainPanel, gridBagConstraints);

        taskBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        taskBar.setLayout(new java.awt.GridBagLayout());

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel31.setLayout(new java.awt.GridBagLayout());

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel31.add(userLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        taskBar.add(jPanel31, gridBagConstraints);

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel41.setLayout(new java.awt.GridBagLayout());

        connectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectionLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel41.add(connectionLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        taskBar.add(jPanel41, gridBagConstraints);

        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel51.setLayout(new java.awt.GridBagLayout());

        activePane.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activePane.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel51.add(activePane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        taskBar.add(jPanel51, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new java.awt.GridBagLayout());

        activeDatabase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeDatabase.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel6.add(activeDatabase, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        taskBar.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(taskBar, gridBagConstraints);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fleet  Management System", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Utopia", 2, 10), new java.awt.Color(51, 0, 153))); // NOI18N

        fleetSetupmn.setText("Setup");

        legalstatusmnit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        legalstatusmnit.setText("Legal Status Setup");
        legalstatusmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalstatusmnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(legalstatusmnit);

        countrydistrictmn.setText("Country/District");

        countrymntfr.setText("Country");
        countrymntfr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countrymntfrActionPerformed(evt);
            }
        });
        countrydistrictmn.add(countrymntfr);

        districtsmnit.setText("Districts");
        districtsmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtsmnitActionPerformed(evt);
            }
        });
        countrydistrictmn.add(districtsmnit);

        fleetSetupmn.add(countrydistrictmn);

        departmentmnit.setText("Department");
        departmentmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentmnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(departmentmnit);

        companyprofilemnit.setText("Company Profile");
        companyprofilemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyprofilemnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(companyprofilemnit);
        fleetSetupmn.add(jSeparator1);

        driveremployeemnit.setText("Driver/Employee");

        drivermnit.setText("New Drivers");
        drivermnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drivermnitActionPerformed(evt);
            }
        });
        driveremployeemnit.add(drivermnit);

        Employeemnit.setText("New Employee");
        Employeemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeemnitActionPerformed(evt);
            }
        });
        driveremployeemnit.add(Employeemnit);

        fleetSetupmn.add(driveremployeemnit);
        fleetSetupmn.add(jSeparator2);

        vendorDBmn.setText("Vendor's DataBase");

        vendormnit.setText("Vendor Setup");
        vendormnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendormnitActionPerformed(evt);
            }
        });
        vendorDBmn.add(vendormnit);

        fleetSetupmn.add(vendorDBmn);

        vehicletypemnit.setText("Vehicle Type");
        vehicletypemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicletypemnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(vehicletypemnit);

        partequptmnit.setText("Parts");
        partequptmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partequptmnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(partequptmnit);
        fleetSetupmn.add(jSeparator4);
        fleetSetupmn.add(jSeparator3);

        exitmnit.setText("EXIT");
        exitmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitmnitActionPerformed(evt);
            }
        });
        fleetSetupmn.add(exitmnit);

        jMenuBar1.add(fleetSetupmn);

        fleetOperationsmn.setText("Operations");

        AddVehiclemnit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        AddVehiclemnit.setText("Add New Vehicle");
        AddVehiclemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVehiclemnitActionPerformed(evt);
            }
        });
        fleetOperationsmn.add(AddVehiclemnit);

        Addvehicleduplicate.setText("Add Duplicate");
        Addvehicleduplicate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddvehicleduplicateActionPerformed(evt);
            }
        });
        fleetOperationsmn.add(Addvehicleduplicate);

        Fleetmanagermn.setText("Fleet Manager");

        registrationpartimnit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        registrationpartimnit.setText("Registration Particulars");
        registrationpartimnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationpartimnitActionPerformed(evt);
            }
        });
        Fleetmanagermn.add(registrationpartimnit);

        purchaseinformnit.setText("Purchase info");
        Fleetmanagermn.add(purchaseinformnit);

        spescificationmnit.setText("Spescification/parts");
        Fleetmanagermn.add(spescificationmnit);

        insurancemnit.setText("Insurance");
        Fleetmanagermn.add(insurancemnit);

        loanleasemnit.setText("Loan/Lease");
        Fleetmanagermn.add(loanleasemnit);

        photomnit.setText("Photo");
        Fleetmanagermn.add(photomnit);

        notesmnit.setText("Notes");
        Fleetmanagermn.add(notesmnit);

        fleetOperationsmn.add(Fleetmanagermn);
        fleetOperationsmn.add(sepper1);

        Vehiclemn.setText("Vehicle");
        Vehiclemn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehiclemnActionPerformed(evt);
            }
        });

        issuelworkmnit.setText("Issuel Work Order");
        issuelworkmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuelworkmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(issuelworkmnit);

        schedulerepairmnit.setText("Schedule Repair");
        schedulerepairmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedulerepairmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(schedulerepairmnit);
        Vehiclemn.add(vehiclest1);

        Maintenanceduemnit.setText("Maintenance Due");
        Maintenanceduemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintenanceduemnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Maintenanceduemnit);
        Vehiclemn.add(vehiclest2);

        Maintenancehistmnit.setText("Maintenance History");
        Maintenancehistmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintenancehistmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Maintenancehistmnit);

        tirehistmnit.setText("Tire History");
        tirehistmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tirehistmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(tirehistmnit);

        Coststatisticsmnit.setText("Cost Statistict");
        Coststatisticsmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoststatisticsmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Coststatisticsmnit);
        Vehiclemn.add(vehiclest3);

        Currentmainstatusmnit.setText("Current Maintenance Status");
        Currentmainstatusmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentmainstatusmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Currentmainstatusmnit);

        Lastpmsetupmnit.setText("Last PM Setup");
        Lastpmsetupmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastpmsetupmnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Lastpmsetupmnit);
        Vehiclemn.add(vehiclest4);

        Fuelhistorymnit.setText("Fuel History");
        Fuelhistorymnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuelhistorymnitActionPerformed(evt);
            }
        });
        Vehiclemn.add(Fuelhistorymnit);
        Vehiclemn.add(vehiclest5);

        Triphist.setText("Trip History");
        Triphist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TriphistActionPerformed(evt);
            }
        });
        Vehiclemn.add(Triphist);

        fleetOperationsmn.add(Vehiclemn);

        Maintenancemn.setText("Maintenance");

        Pmcheckwizmn.setText("PM Check Wizard");

        Selectedvehiclemnit.setText("Selected Vehicle(s)");
        Selectedvehiclemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectedvehiclemnitActionPerformed(evt);
            }
        });
        Pmcheckwizmn.add(Selectedvehiclemnit);

        Selecteddepmn.setText("Selected Department");
        Selecteddepmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecteddepmnActionPerformed(evt);
            }
        });
        Pmcheckwizmn.add(Selecteddepmn);

        Selectedlocmn.setText("Selected Location");
        Selectedlocmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectedlocmnActionPerformed(evt);
            }
        });
        Pmcheckwizmn.add(Selectedlocmn);

        Allvehiclemnit.setText("All Vehicles");
        Allvehiclemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllvehiclemnitActionPerformed(evt);
            }
        });
        Pmcheckwizmn.add(Allvehiclemnit);

        Maintenancemn.add(Pmcheckwizmn);

        Updatemetermnit.setText("Update Meter Readings");
        Updatemetermnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatemetermnitActionPerformed(evt);
            }
        });
        Maintenancemn.add(Updatemetermnit);
        Maintenancemn.add(Maintenancesep1);

        Pmschedulesetupmnit.setText("PM Schedule Setup");
        Pmschedulesetupmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PmschedulesetupmnitActionPerformed(evt);
            }
        });
        Maintenancemn.add(Pmschedulesetupmnit);

        Pmassociatesmn.setText("PM Associates");
        Pmassociatesmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PmassociatesmnActionPerformed(evt);
            }
        });
        Maintenancemn.add(Pmassociatesmn);

        Scheduledrepairmnit.setText("Scheduled Repairs");
        Scheduledrepairmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScheduledrepairmnitActionPerformed(evt);
            }
        });
        Maintenancemn.add(Scheduledrepairmnit);
        Maintenancemn.add(Maintenancesep2);

        IssueWorkordermnit.setText("Issue Work Order");
        IssueWorkordermnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueWorkordermnitActionPerformed(evt);
            }
        });
        Maintenancemn.add(IssueWorkordermnit);

        Workordermanagmnit.setText("Work Order Management");
        Workordermanagmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WorkordermanagmnitActionPerformed(evt);
            }
        });
        Maintenancemn.add(Workordermanagmnit);

        fleetOperationsmn.add(Maintenancemn);

        Inventorymn.setText("Inventory");

        Partsinventorymnit.setText("Parts Inventory");
        Partsinventorymnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartsinventorymnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Partsinventorymnit);

        Tireinventorymnit.setText("Tire Inventory");
        Tireinventorymnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TireinventorymnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Tireinventorymnit);
        Inventorymn.add(sep1);

        Purchaseordersmnit.setText("Purchase Orders - Browse");
        Purchaseordersmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseordersmnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Purchaseordersmnit);

        Purchaseorderaddmnit.setText("Purchase Order - Add");
        Purchaseorderaddmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseorderaddmnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Purchaseorderaddmnit);
        Inventorymn.add(sep2);

        Poreceiptbyponummnit.setText("PO Receipt - By PO #");
        Poreceiptbyponummnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoreceiptbyponummnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Poreceiptbyponummnit);

        Poreceiptbypartmnit.setText("PO Receipt - By Part #");
        Poreceiptbypartmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoreceiptbypartmnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Poreceiptbypartmnit);
        Inventorymn.add(sep3);

        Adjustrecievemnit.setText("Adjust/Recieve Into Inventory");
        Adjustrecievemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdjustrecievemnitActionPerformed(evt);
            }
        });
        Inventorymn.add(Adjustrecievemnit);

        fleetOperationsmn.add(Inventorymn);

        Billingmn.setText("Billing");

        Generateinvomnit.setText("Generate Invoice");
        Generateinvomnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateinvomnitActionPerformed(evt);
            }
        });
        Billingmn.add(Generateinvomnit);

        Browseinvoicesmnit.setText("Browse Invoices");
        Browseinvoicesmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseinvoicesmnitActionPerformed(evt);
            }
        });
        Billingmn.add(Browseinvoicesmnit);
        Billingmn.add(sepat1);

        Recordpaymentsmnit.setText("Record Payments");
        Recordpaymentsmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordpaymentsmnitActionPerformed(evt);
            }
        });
        Billingmn.add(Recordpaymentsmnit);

        Browsepaymemnit.setText("Browse Payments");
        Browsepaymemnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowsepaymemnitActionPerformed(evt);
            }
        });
        Billingmn.add(Browsepaymemnit);

        fleetOperationsmn.add(Billingmn);

        jMenuBar1.add(fleetOperationsmn);

        FleetUtilitiesmn.setText("Utilities");

        backgroundmnit.setText("Background Image");
        backgroundmnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundmnitActionPerformed(evt);
            }
        });
        FleetUtilitiesmn.add(backgroundmnit);

        jMenuBar1.add(FleetUtilitiesmn);

        FleetReportsmn1.setText("Reports");

        billingmn.setText("Billing");

        invoicesoutstandingmnit.setText("Invoices (Outstanding)");
        billingmn.add(invoicesoutstandingmnit);

        invoicespaidinfullmnit.setText("Invoices (paid in full)");
        billingmn.add(invoicespaidinfullmnit);

        invoicessummaryoutstandingmnit.setText("Invoices Summary (Outstanding)");
        billingmn.add(invoicessummaryoutstandingmnit);

        invoicessummarypaidinfullmnit.setText("Invoices Summary (Paid in Full)");
        billingmn.add(invoicessummarypaidinfullmnit);

        paymentsrecievedmnit.setText("Payments Recieved");
        billingmn.add(paymentsrecievedmnit);

        FleetReportsmn1.add(billingmn);

        employeesmn.setText("Employees");

        detailedcontactlistmnit.setText("Detailed Contact List");
        employeesmn.add(detailedcontactlistmnit);

        generalcontactlistmnit.setText("General Contact List");
        employeesmn.add(generalcontactlistmnit);

        personalinformationmnit.setText("Personal Information");
        employeesmn.add(personalinformationmnit);

        licenceinformationmnit.setText("Licence Information");
        employeesmn.add(licenceinformationmnit);

        licencerenewalsduemnit.setText("Licence Renewals Due ");
        employeesmn.add(licencerenewalsduemnit);

        certificationsmnit.setText("Certifications");
        employeesmn.add(certificationsmnit);

        certificationsrenewalsmnit.setText("Certifications Renewals");
        employeesmn.add(certificationsrenewalsmnit);

        phsicalinformationsmnit.setText("Physical Informations");
        employeesmn.add(phsicalinformationsmnit);

        physicalrenewalsduemnit.setText("Physical Renewals Due");
        employeesmn.add(physicalrenewalsduemnit);

        FleetReportsmn1.add(employeesmn);

        fleetmn.setText("Fleet");

        fleetlistingmnit.setText("Fleet Listing ");
        fleetmn.add(fleetlistingmnit);

        generalmnit.setText("General");
        fleetmn.add(generalmnit);

        specificationspartsmnit.setText("Specifications/ Parts");
        fleetmn.add(specificationspartsmnit);

        purchaseinformationmnit.setText("Purchase Information");
        fleetmn.add(purchaseinformationmnit);

        registrationinformationmnit.setText("Registration Information");
        fleetmn.add(registrationinformationmnit);

        insurancermnit.setText("Insurance");
        fleetmn.add(insurancermnit);

        loanleaser.setText("Loan/ Lease");
        fleetmn.add(loanleaser);

        registrationrenewalduemnit.setText("Registration Renewal Due");
        fleetmn.add(registrationrenewalduemnit);

        stateinspectionrenewalsduemnit.setText("State Inspection Renewals Due");
        fleetmn.add(stateinspectionrenewalsduemnit);

        FleetReportsmn1.add(fleetmn);

        fuelpurchasemn.setText("Fuel Purchase");

        fuelpurchaseshistorymnit.setText("Fuel Purchases History");
        fuelpurchasemn.add(fuelpurchaseshistorymnit);

        fuelcostsummarymnit.setText("Fuel Cost Summary");
        fuelpurchasemn.add(fuelcostsummarymnit);

        FleetReportsmn1.add(fuelpurchasemn);

        maintanancehistorymn.setText("Maintance History");

        historyoverviewmnit.setText("History Overview");
        maintanancehistorymn.add(historyoverviewmnit);

        completehistorymnit.setText("Complete History");
        maintanancehistorymn.add(completehistorymnit);

        pmhistorymnit.setText("PM History");
        maintanancehistorymn.add(pmhistorymnit);

        repairhistorymnit.setText("Repair History");
        maintanancehistorymn.add(repairhistorymnit);

        partshistorymnit.setText("Parts History");
        maintanancehistorymn.add(partshistorymnit);

        labourhistorymnit.setText("Labour History");
        maintanancehistorymn.add(labourhistorymnit);

        externalserviceshistorymnit.setText("External services History");
        maintanancehistorymn.add(externalserviceshistorymnit);

        fuelpurchasesrmnit.setText("Fuel Purchases History");
        maintanancehistorymn.add(fuelpurchasesrmnit);

        historycostsummarymnit.setText("History cost Summary");
        maintanancehistorymn.add(historycostsummarymnit);

        vendorcostsummarymnit.setText("Vendor Cost Summary");
        maintanancehistorymn.add(vendorcostsummarymnit);

        FleetReportsmn1.add(maintanancehistorymn);

        partsinventorymn.setText("Parts Inventory");

        partslistingmnit.setText("Parts Listing");
        partsinventorymn.add(partslistingmnit);

        partslistingbyvendormnit.setText("Parts Listing (by vendor)");
        partsinventorymn.add(partslistingbyvendormnit);

        partslistngcategorymnit.setText("Parts Listing (category)");
        partsinventorymn.add(partslistngcategorymnit);

        needreoderedmnit.setText("Need Reodered");
        partsinventorymn.add(needreoderedmnit);

        recieptsmnit.setText("Reciepts");
        partsinventorymn.add(recieptsmnit);

        partshstoryrmnit.setText("Parts History");
        partsinventorymn.add(partshstoryrmnit);

        partsusagemnit.setText("Parts Usage");
        partsinventorymn.add(partsusagemnit);

        stockvaluedetailedmnit.setText("Stock Value (Detailed)");
        partsinventorymn.add(stockvaluedetailedmnit);

        stockvaluesummarymnit.setText("Stock Value (Summary)");
        partsinventorymn.add(stockvaluesummarymnit);

        FleetReportsmn1.add(partsinventorymn);

        pmsevicesmn.setText("PM Services");

        pmschedulesmnit.setText("PM Scedules");
        pmsevicesmn.add(pmschedulesmnit);

        pmlastperfomeddatamnit.setText("PM Last Performed Data");
        pmsevicesmn.add(pmlastperfomeddatamnit);

        currentpmstatusmnit.setText("Current PM Status");
        pmsevicesmn.add(currentpmstatusmnit);

        maintainanceduemnit.setText("Maintanance Due");
        pmsevicesmn.add(maintainanceduemnit);

        pmcoststatisticspervehicleequipmnit.setText("PM Cost Statistics- (per vehicle/equip)");
        pmsevicesmn.add(pmcoststatisticspervehicleequipmnit);

        pmcoststatisticsoveralmnit.setText("PM Cost statistics (overall)");
        pmsevicesmn.add(pmcoststatisticsoveralmnit);

        FleetReportsmn1.add(pmsevicesmn);

        purchaseodersmn.setText("Purchase Orders");

        generateclosedmnit.setText("Generate -(closed)");
        purchaseodersmn.add(generateclosedmnit);

        generateoutstandingmnit.setText("Generate -(outstanding)");
        purchaseodersmn.add(generateoutstandingmnit);

        Recieptsrmnit.setText("Reciepts");
        purchaseodersmn.add(Recieptsrmnit);

        summaryclosedmnit.setText("Summary-(closed)");
        purchaseodersmn.add(summaryclosedmnit);

        summaryoutstandingmnit.setText("Summary -(outstanding)");
        purchaseodersmn.add(summaryoutstandingmnit);

        vendorsummarymnit.setText("Vendor Summary");
        purchaseodersmn.add(vendorsummarymnit);

        FleetReportsmn1.add(purchaseodersmn);

        sheduledrepairsmn.setText("Scheduled Repairs");

        outstndingallvehiclemnit.setText("Outstanding -All vehicle(s)");
        sheduledrepairsmn.add(outstndingallvehiclemnit);

        outstandingselectedvehicle.setText("Outstanding Selected Vehicle (s)");
        sheduledrepairsmn.add(outstandingselectedvehicle);

        currentdueallvehiclemnit.setText("Currently Due -All vehicle");
        sheduledrepairsmn.add(currentdueallvehiclemnit);

        currentlydueselectedvehiclesmnit.setText("Currently Due Selected Vehicle (s)");
        sheduledrepairsmn.add(currentlydueselectedvehiclesmnit);

        FleetReportsmn1.add(sheduledrepairsmn);

        summaryreportsmn.setText("Summary Reports");

        pmcoststatisticspervehicleequiprmnit.setText("PM Cost Statistics (per vehicle/equip)");
        summaryreportsmn.add(pmcoststatisticspervehicleequiprmnit);

        pmcoststatisticsoverallmnit.setText("PM Cost Statistics (Overall)");
        summaryreportsmn.add(pmcoststatisticsoverallmnit);

        historycostsummarurmnit.setText("History Cost Summary");
        summaryreportsmn.add(historycostsummarurmnit);

        vendorcostsummaryrmnit.setText("Vendor Cost Summary");
        summaryreportsmn.add(vendorcostsummaryrmnit);

        fuelcostsummaryrmnit.setText("Fuel Cost Summary");
        summaryreportsmn.add(fuelcostsummaryrmnit);

        workordedersummaryopenmnit.setText("Work Order Summary (open)");
        summaryreportsmn.add(workordedersummaryopenmnit);

        workordersummaryclosedmnit.setText("Work Order Summary-(closed)");
        summaryreportsmn.add(workordersummaryclosedmnit);

        fleetlistingrmnit.setText("Fleet Listing");
        summaryreportsmn.add(fleetlistingrmnit);

        FleetReportsmn1.add(summaryreportsmn);

        travelmn.setText("Travel");

        triphistorymnit.setText("Trip History");
        travelmn.add(triphistorymnit);

        tripticketsmnit.setText("Trip Tickets");
        travelmn.add(tripticketsmnit);

        usagebylocdeptvehicledetailedrmnit.setText("Usage By Loc /dept/vehicle (detailed)");
        travelmn.add(usagebylocdeptvehicledetailedrmnit);

        usagebydriverdeptdetailedmnit.setText("Usage By Driver /dept (Detailed)");
        travelmn.add(usagebydriverdeptdetailedmnit);

        usagebylocdeptdriveroverviewmnit.setText("Usage By Loc/Dept / Driver (overview)");
        travelmn.add(usagebylocdeptdriveroverviewmnit);

        usagebydriveroverviewmnit.setText("Usage By Driver (Overview)");
        travelmn.add(usagebydriveroverviewmnit);

        FleetReportsmn1.add(travelmn);

        tiresmn.setText("Tires");

        tiresinventorylistingallmnit.setText("Tires Inventory Listing -(All)");
        tiresmn.add(tiresinventorylistingallmnit);

        tiresinventorylistingmountedmnit.setText("Tires Inventory Listing -(Mounted)");
        tiresmn.add(tiresinventorylistingmountedmnit);

        tiresinventorylistingdismountedmnit.setText("Tires Inventory Listing -(Dismounted)");
        tiresmn.add(tiresinventorylistingdismountedmnit);

        tirehistorybyvehicleequipmentmnit.setText("Tire History -(by vehicle/equipment)");
        tiresmn.add(tirehistorybyvehicleequipmentmnit);

        tirehistorybytiremnit.setText("Tire History -(ByTire)");
        tiresmn.add(tirehistorybytiremnit);

        FleetReportsmn1.add(tiresmn);

        vendorsmn.setText("Vendors");

        vendorslistingmnit.setText("Vendors Listing");
        vendorsmn.add(vendorslistingmnit);

        vendorscostsummarymnit.setText("Vendors Cost Summary ");
        vendorsmn.add(vendorscostsummarymnit);

        FleetReportsmn1.add(vendorsmn);

        Workordersmnit.setText("Work Orders");

        workordersopenmnit.setText("Work Orders (Open)");
        Workordersmnit.add(workordersopenmnit);

        workorderscompletemnit.setText("Work Orders (Complete)");
        Workordersmnit.add(workorderscompletemnit);

        worksummaryopenmnit.setText("Work Summary (Open)");
        Workordersmnit.add(worksummaryopenmnit);

        worksummaryclosedmnit.setText("Work Summary (Closed)");
        Workordersmnit.add(worksummaryclosedmnit);

        FleetReportsmn1.add(Workordersmnit);

        reportgeneratorrmnit.setText("Report Generator");
        FleetReportsmn1.add(reportgeneratorrmnit);

        jMenuBar1.add(FleetReportsmn1);

        lnfmn.setMnemonic('F');
        lnfmn.setText("Look&Feel");

        buttonGroup2.add(swinglnfmnit1);
        swinglnfmnit1.setText("Metal");
        swinglnfmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swinglnfmnit1ActionPerformed(evt);
            }
        });
        lnfmn.add(swinglnfmnit1);

        buttonGroup2.add(winlnfmnit1);
        winlnfmnit1.setText("Windows");
        winlnfmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                winlnfmnit1ActionPerformed(evt);
            }
        });
        lnfmn.add(winlnfmnit1);

        buttonGroup2.add(motiflnfmnit1);
        motiflnfmnit1.setText("Motif");
        motiflnfmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motiflnfmnit1ActionPerformed(evt);
            }
        });
        lnfmn.add(motiflnfmnit1);

        buttonGroup2.add(macmnit1);
        macmnit1.setText("Mac OS");
        macmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macmnit1ActionPerformed(evt);
            }
        });
        lnfmn.add(macmnit1);

        skinmn.setText("Themes");

        buttonGroup3.add(xpmnit1);
        xpmnit1.setText("XP Luna");
        xpmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xpmnit1ActionPerformed(evt);
            }
        });
        skinmn.add(xpmnit1);

        buttonGroup3.add(macos1mnit1);
        macos1mnit1.setText("Macintosh");
        macos1mnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macos1mnit1ActionPerformed(evt);
            }
        });
        skinmn.add(macos1mnit1);

        buttonGroup3.add(whiltmnit1);
        whiltmnit1.setText("Whistler");
        whiltmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiltmnit1ActionPerformed(evt);
            }
        });
        skinmn.add(whiltmnit1);

        buttonGroup3.add(modernmnit1);
        modernmnit1.setText("Modern");
        modernmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modernmnit1ActionPerformed(evt);
            }
        });
        skinmn.add(modernmnit1);

        buttonGroup3.add(aquamnit1);
        aquamnit1.setText("Aqua");
        aquamnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aquamnit1ActionPerformed(evt);
            }
        });
        skinmn.add(aquamnit1);

        buttonGroup3.add(beosmnit1);
        beosmnit1.setText("BEOS");
        beosmnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beosmnit1ActionPerformed(evt);
            }
        });
        skinmn.add(beosmnit1);

        buttonGroup3.add(bbjmnit11);
        bbjmnit11.setText("BBJ");
        bbjmnit11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbjmnit11ActionPerformed(evt);
            }
        });
        skinmn.add(bbjmnit11);

        lnfmn.add(skinmn);

        jMenuBar1.add(lnfmn);

        FleetHelpmn.setText("Help");

        helpcontentsmnit.setText("Help Contents");
        FleetHelpmn.add(helpcontentsmnit);

        aboutmnit.setText("About");
        FleetHelpmn.add(aboutmnit);

        jMenuBar1.add(FleetHelpmn);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTree1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTree1ComponentShown
        // Add your handling code here:
    }//GEN-LAST:event_jTree1ComponentShown
    
    private void FleetSplit1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_FleetSplit1ComponentShown
        
        this.setTitle(getCompanyName());
        
        this.invalidate();
        
        setJMenuBar(jMenuBar1);
        
        this.validate();        // Add your handling code here:
    }//GEN-LAST:event_FleetSplit1ComponentShown
    
    private void backgroundmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundmnitActionPerformed
        
     invalidate();
        
        javax.swing.JFileChooser chooserImages = new javax.swing.JFileChooser(new java.io.File(System.getProperty("user.dir")));
        
        com.afrisoftech.lib.ExampleFileFilter filter = new com.afrisoftech.lib.ExampleFileFilter();
        
        filter.addExtension("jpg");
        
        filter.addExtension("gif");
        
        filter.addExtension("png");
        
        filter.setDescription("Image Files");
        
        chooserImages.setFileFilter(filter);
        
        int returnVal = chooserImages.showOpenDialog(this);
        
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            
            System.out.println("You chose to open this file: " +
            
            chooserImages.getSelectedFile().getPath());
            
            // filePortrait = chooserPortrait.getSelectedFile();
            
            // this.jTextField1.setText(filePortrait.getNam);
            
            //  processBrRequisitionFile(filePortrait);
            
            appProp.setProperty("backgrdimg", chooserImages.getSelectedFile().getPath());
            
            desktopPaneIcon = new javax.swing.ImageIcon(chooserImages.getSelectedFile().getPath());
            
            //jPanel.validate();
            
            this.FleetMainPanel.updateUI();
            
            
            
            //updateUI();
            
            validate();
        }
        
        //addyour handling code here:
    }//GEN-LAST:event_backgroundmnitActionPerformed
    
    private void Reports1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reports1ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_Reports1ActionPerformed
    
    private void Partsbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Partsbtn2ActionPerformed
        javax.swing.JInternalFrame partsInvent = new PartsInventManIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(partsInvent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        partsInvent.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Partsbtn2ActionPerformed
    
    private void Vendorbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vendorbtn2ActionPerformed
        javax.swing.JInternalFrame vendorDBman = new VendorDBmanIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(vendorDBman, javax.swing.JLayeredPane.DEFAULT_LAYER);
        vendorDBman.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Vendorbtn2ActionPerformed
    
    private void Driversbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Driversbtn2ActionPerformed
        javax.swing.JInternalFrame drivermanscreen = new DrivermanscreenIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(drivermanscreen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        drivermanscreen.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Driversbtn2ActionPerformed
    
    private void Repairsbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Repairsbtn4ActionPerformed
        javax.swing.JInternalFrame schedrepairman = new SchedRepairmanIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(schedrepairman, javax.swing.JLayeredPane.DEFAULT_LAYER);
        schedrepairman.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Repairsbtn4ActionPerformed
    
    private void Pmschedrepairsbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pmschedrepairsbtn4ActionPerformed
        javax.swing.JInternalFrame Pmschedulesetup = new PmScheduleSetupIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(Pmschedulesetup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Pmschedulesetup.setVisible(true); // Add your handling code here:
    }//GEN-LAST:event_Pmschedrepairsbtn4ActionPerformed
    
    private void Pmcheckbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pmcheckbtn2ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_Pmcheckbtn2ActionPerformed
    
    private void Workordersbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Workordersbtn2ActionPerformed
        javax.swing.JInternalFrame workorderman = new WorkOrderManIntfr(connectDB, pConnDB) ;
        fleetdesktop.add(workorderman, javax.swing.JLayeredPane.DEFAULT_LAYER);
        workorderman.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Workordersbtn2ActionPerformed
    
    private void Deletevehiclebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Deletevehiclebtn2ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_Deletevehiclebtn2ActionPerformed
    
    private void EditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditbtnActionPerformed
        javax.swing.JInternalFrame editVehicle = new Editvehicleintfr(connectDB, pConnDB) ;
        fleetdesktop.add(editVehicle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editVehicle.setVisible(true); // Add your handling code here:
    }//GEN-LAST:event_EditbtnActionPerformed
    
    private void Addnewvehiclebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Addnewvehiclebtn2ActionPerformed
        javax.swing.JInternalFrame newVehicle = new AddNewvehicleintfr(connectDB, pConnDB) ;
        fleetdesktop.add(newVehicle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        newVehicle.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_Addnewvehiclebtn2ActionPerformed
    
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_helpButtonActionPerformed
    
    private void GenerateinvomnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateinvomnitActionPerformed
        GenerateInvoiceIntfr GenerateInvoice = new  GenerateInvoiceIntfr(connectDB,pConnDB);
        fleetdesktop.add(GenerateInvoice, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            GenerateInvoice.setSelected(true);
            System.out.println("Generate Invoice loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_GenerateinvomnitActionPerformed
    
    private void BrowseinvoicesmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseinvoicesmnitActionPerformed
        BrowseInvoiceIntfr BrowseInvoice = new  BrowseInvoiceIntfr(connectDB,pConnDB);
        fleetdesktop.add(BrowseInvoice, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            BrowseInvoice.setSelected(true);
            System.out.println("BrowseI nvoice loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_BrowseinvoicesmnitActionPerformed
    
    private void RecordpaymentsmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordpaymentsmnitActionPerformed
        AddPaymentIntfr AddPayment = new  AddPaymentIntfr(connectDB,pConnDB);
        fleetdesktop.add(AddPayment, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            AddPayment.setSelected(true);
            System.out.println("Add Payment loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_RecordpaymentsmnitActionPerformed
    
    private void BrowsepaymemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowsepaymemnitActionPerformed
        BrowsePaymentIntfr BrowsePayment = new  BrowsePaymentIntfr(connectDB,pConnDB);
        fleetdesktop.add(BrowsePayment, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            BrowsePayment.setSelected(true);
            System.out.println("Browse Payment loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_BrowsepaymemnitActionPerformed
    
    private void PartsinventorymnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PartsinventorymnitActionPerformed
        
        PartsInventManIntfr PartsInventMan = new  PartsInventManIntfr(connectDB,pConnDB);
        fleetdesktop.add(PartsInventMan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            PartsInventMan.setSelected(true);
            System.out.println("PartsInventMan loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PartsinventorymnitActionPerformed
    
    private void TireinventorymnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TireinventorymnitActionPerformed
        WorkOrderManIntfr TireInventory = new  WorkOrderManIntfr(connectDB,pConnDB);
        fleetdesktop.add(TireInventory, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            TireInventory.setSelected(true);
            System.out.println("TireInventory loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_TireinventorymnitActionPerformed
    
    private void PurchaseordersmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseordersmnitActionPerformed
        BrowsePuchOrdersIntfr BrowsePuchOrders = new  BrowsePuchOrdersIntfr(connectDB,pConnDB);
        fleetdesktop.add(BrowsePuchOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            BrowsePuchOrders.setSelected(true);
            System.out.println("BrowsePuchOrders loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PurchaseordersmnitActionPerformed
    
    private void PurchaseorderaddmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseorderaddmnitActionPerformed
        AddPurchOrderIntfr AddPurchOrder = new  AddPurchOrderIntfr(connectDB,pConnDB);
        fleetdesktop.add(AddPurchOrder, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            AddPurchOrder.setSelected(true);
            System.out.println("AddPurchOrder loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PurchaseorderaddmnitActionPerformed
    
    private void PoreceiptbyponummnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoreceiptbyponummnitActionPerformed
        OrReceivedIntfr OrReceived = new  OrReceivedIntfr(connectDB,pConnDB);
        fleetdesktop.add(OrReceived, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            OrReceived.setSelected(true);
            System.out.println("OrReceived loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PoreceiptbyponummnitActionPerformed
    
    private void PoreceiptbypartmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoreceiptbypartmnitActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_PoreceiptbypartmnitActionPerformed
    
    private void AdjustrecievemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdjustrecievemnitActionPerformed
        AdjustPartRcvdIntfr AdjustPart = new  AdjustPartRcvdIntfr(connectDB,pConnDB);
        fleetdesktop.add(AdjustPart, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            AdjustPart.setSelected(true);
            System.out.println("Adjust Part Receipved loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_AdjustrecievemnitActionPerformed
    
    private void SelectedvehiclemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectedvehiclemnitActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_SelectedvehiclemnitActionPerformed
    
    private void SelecteddepmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecteddepmnActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_SelecteddepmnActionPerformed
    
    private void SelectedlocmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectedlocmnActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_SelectedlocmnActionPerformed
    
    private void AllvehiclemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllvehiclemnitActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_AllvehiclemnitActionPerformed
    
    private void UpdatemetermnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatemetermnitActionPerformed
        UpDateMeterintfr updatemeter = new  UpDateMeterintfr(connectDB,pConnDB);
        fleetdesktop.add(updatemeter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            updatemeter.setSelected(true);
            System.out.println("update meter loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_UpdatemetermnitActionPerformed
    
    private void PmschedulesetupmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PmschedulesetupmnitActionPerformed
        PmScheduleSetupIntfr pmSchsetup = new  PmScheduleSetupIntfr(connectDB,pConnDB);
        fleetdesktop.add(pmSchsetup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            pmSchsetup.setSelected(true);
            System.out.println("pmsetuploaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PmschedulesetupmnitActionPerformed
    
    private void PmassociatesmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PmassociatesmnActionPerformed
        PmAssociationIntfr pmAssociation = new  PmAssociationIntfr(connectDB,pConnDB);
        fleetdesktop.add(pmAssociation, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            pmAssociation.setSelected(true);
            System.out.println("pmAssociation loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_PmassociatesmnActionPerformed
    
    private void ScheduledrepairmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScheduledrepairmnitActionPerformed
        ScheduleRepairintfr schedule = new ScheduleRepairintfr(connectDB,pConnDB);
        fleetdesktop.add(schedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            schedule.setSelected(true);
            System.out.println("1 loaded");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_ScheduledrepairmnitActionPerformed
    
    private void IssueWorkordermnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueWorkordermnitActionPerformed
        NewWorkOrderintfr issuel = new NewWorkOrderintfr(connectDB,pConnDB);
        fleetdesktop.add(issuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            issuel.setSelected(true);
            System.out.println("new work order loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_IssueWorkordermnitActionPerformed
    
    private void WorkordermanagmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WorkordermanagmnitActionPerformed
        WorkOrderManIntfr WorkOrder = new  WorkOrderManIntfr(connectDB,pConnDB);
        fleetdesktop.add(WorkOrder, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            WorkOrder.setSelected(true);
            System.out.println("Work Order Managment loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_WorkordermanagmnitActionPerformed
    
    private void MaintenancehistmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintenancehistmnitActionPerformed
        MaintenanceHistintfr maintehist = new  MaintenanceHistintfr(connectDB,pConnDB);
        fleetdesktop.add(maintehist, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            maintehist.setSelected(true);
            System.out.println("adv loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_MaintenancehistmnitActionPerformed
    
    private void tirehistmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tirehistmnitActionPerformed
        TireHistoryintfr tirehist = new  TireHistoryintfr(connectDB,pConnDB);
        fleetdesktop.add(tirehist, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            tirehist.setSelected(true);
            System.out.println("tirehist loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_tirehistmnitActionPerformed
    
    private void CoststatisticsmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoststatisticsmnitActionPerformed
        CostStatisticsintfr coststat = new  CostStatisticsintfr(connectDB,pConnDB);
        fleetdesktop.add(coststat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            coststat.setSelected(true);
            System.out.println("tirehist loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_CoststatisticsmnitActionPerformed
    
    private void CurrentmainstatusmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentmainstatusmnitActionPerformed
        CurrentMaintenanceintfr currentmainte = new  CurrentMaintenanceintfr(connectDB,pConnDB);
        fleetdesktop.add(currentmainte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            currentmainte.setSelected(true);
            System.out.println("current Maintenance status loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_CurrentmainstatusmnitActionPerformed
    
    private void LastpmsetupmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastpmsetupmnitActionPerformed
        LastPMsetupintfr lastPM = new  LastPMsetupintfr(connectDB,pConnDB);
        fleetdesktop.add(lastPM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            lastPM.setSelected(true);
            System.out.println("LastPM loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_LastpmsetupmnitActionPerformed
    
    private void FuelhistorymnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuelhistorymnitActionPerformed
        FuelHistotryintfr fuelhist = new FuelHistotryintfr(connectDB,pConnDB);
        fleetdesktop.add(fuelhist, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            fuelhist.setSelected(true);
            System.out.println("fuelhist");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_FuelhistorymnitActionPerformed
    
    private void TriphistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TriphistActionPerformed
        TripHistIntfr Triphist = new  TripHistIntfr(connectDB,pConnDB);
        fleetdesktop.add(Triphist, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            Triphist.setSelected(true);
            System.out.println("trip history loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_TriphistActionPerformed
    
    private void MaintenanceduemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintenanceduemnitActionPerformed
        Maintenancedueintfr maintedue = new  Maintenancedueintfr(connectDB,pConnDB);
        fleetdesktop.add(maintedue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            maintedue.setSelected(true);
            System.out.println("maintedue loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_MaintenanceduemnitActionPerformed
    
    private void schedulerepairmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedulerepairmnitActionPerformed
        ScheduleRepairintfr schedule = new ScheduleRepairintfr(connectDB,pConnDB);
        fleetdesktop.add(schedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            schedule.setSelected(true);
            System.out.println("1 loaded");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_schedulerepairmnitActionPerformed
    
    private void issuelworkmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuelworkmnitActionPerformed
        NewWorkOrderintfr issuel1 = new NewWorkOrderintfr(connectDB,pConnDB);
        fleetdesktop.add(issuel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            issuel1.setSelected(true);
            System.out.println("newworkoder1");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_issuelworkmnitActionPerformed
    
    private void VehiclemnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehiclemnActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_VehiclemnActionPerformed
    
    private void registrationpartimnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationpartimnitActionPerformed
        FleetManager flmanager = new FleetManager(connectDB,pConnDB);
        fleetdesktop.add(flmanager, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            flmanager.setSelected(true);
            System.out.println("fleetmanager");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_registrationpartimnitActionPerformed
    
    private void AddvehicleduplicateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddvehicleduplicateActionPerformed
        AddDublicateintfr addnewvehicled = new  AddDublicateintfr(connectDB,pConnDB);
        fleetdesktop.add(addnewvehicled, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            addnewvehicled.setSelected(true);
            System.out.println("adddubvehi loaded...");
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_AddvehicleduplicateActionPerformed
    
    private void AddVehiclemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddVehiclemnitActionPerformed
        AddNewvehicleintfr addnewvehicle = new  AddNewvehicleintfr(connectDB,pConnDB);
        fleetdesktop.add(addnewvehicle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            addnewvehicle.setSelected(true);
            System.out.println("add new vehicle loaded...");
        } catch(java.beans.PropertyVetoException pvt){}        // Add your handling code here:
    }//GEN-LAST:event_AddVehiclemnitActionPerformed
    
    private void exitmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitmnitActionPerformed
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "Do you really want to exit application?", "Caution before closing application!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
            
            
            this.dispose();
            
        }
        // Add your handling code here:
    }//GEN-LAST:event_exitmnitActionPerformed
    
    private void vendormnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendormnitActionPerformed
        VendorDB_setupIntfr vendor = new VendorDB_setupIntfr(connectDB,pConnDB);
        fleetdesktop.add(vendor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            vendor.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        // Add your handling code here:
    }//GEN-LAST:event_vendormnitActionPerformed
    
    private void partequptmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partequptmnitActionPerformed
        AddNewPartIntfr newpart = new AddNewPartIntfr(connectDB,pConnDB);
        fleetdesktop.add(newpart, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            newpart.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        // Add your handling code here:
    }//GEN-LAST:event_partequptmnitActionPerformed
    
    private void vehicletypemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicletypemnitActionPerformed
        VehicleTypeSetupintfr typevehicle = new VehicleTypeSetupintfr(connectDB,pConnDB);
        fleetdesktop.add(typevehicle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            typevehicle.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        // Add your handling code here:
    }//GEN-LAST:event_vehicletypemnitActionPerformed
    
    private void EmployeemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeemnitActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_EmployeemnitActionPerformed
    
    private void drivermnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drivermnitActionPerformed
        AddNewDriverintfr ndriver = new AddNewDriverintfr(connectDB,pConnDB);
        fleetdesktop.add(ndriver, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            ndriver.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        // Add your handling code here:
    }//GEN-LAST:event_drivermnitActionPerformed
    
    private void companyprofilemnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyprofilemnitActionPerformed
        CompanyProfIntfr companypro = new CompanyProfIntfr(connectDB,pConnDB);
        fleetdesktop.add(companypro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            companypro.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_companyprofilemnitActionPerformed
    
    private void departmentmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentmnitActionPerformed
        DepartmentsIntfr departe = new DepartmentsIntfr(connectDB,pConnDB);
        fleetdesktop.add(departe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            departe.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_departmentmnitActionPerformed
    
    private void districtsmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtsmnitActionPerformed
        DistrictsIntfr distri = new DistrictsIntfr(connectDB,pConnDB);
        fleetdesktop.add(distri, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            distri.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}// Add your handling code here:
    }//GEN-LAST:event_districtsmnitActionPerformed
    
    private void countrymntfrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countrymntfrActionPerformed
        CountryIntfr countryd = new CountryIntfr(connectDB,pConnDB);
        fleetdesktop.add(countryd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            countryd.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        // Add your handling code here:
    }//GEN-LAST:event_countrymntfrActionPerformed
    
    private void legalstatusmnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalstatusmnitActionPerformed
        LegalStatusIntfr comp = new LegalStatusIntfr(connectDB,pConnDB);
        fleetdesktop.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            comp.setSelected(true);
        } catch(java.beans.PropertyVetoException pvt){}
        
        // Add your handling code here:
    }//GEN-LAST:event_legalstatusmnitActionPerformed
                                                                                                                                    /*    public java.lang.Object[] getListofStaffNos() {
                                                                                                                                     
        java.lang.Object[] listofStaffNos = null;
                                                                                                                                     
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
                                                                                                                                     
                                                                                                                                     
        try {
                                                                                                                                     
            //    java.sql.Connection ConnDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                                                                                                                     
            java.sql.Statement stmt1 = ConnDB.createStatement();
                                                                                                                                     
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM total_net_taxable_amount1 ORDER BY staff_no");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where approved ORDER BY staff_no");
                                                                                                                                     
            while (rSet1.next()) {
                                                                                                                                     
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                                                                                                                                     
            }
                                                                                                                                     
        }catch (java.sql.SQLException sqlExec) {
                                                                                                                                     
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
                                                                                                                                     
        }
                                                                                                                                     
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }*/
    
    private void bbjmnit11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbjmnit11ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/bbjthemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_bbjmnit11ActionPerformed
    
    private void beosmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beosmnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/beosthemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_beosmnit1ActionPerformed
    
    private void aquamnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aquamnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/aquathemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_aquamnit1ActionPerformed
    
    private void modernmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modernmnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/modernthemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_modernmnit1ActionPerformed
    
    private void whiltmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiltmnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/whistlerthemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_whiltmnit1ActionPerformed
    
    private void macos1mnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macos1mnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/macosthemepack.zip"));
        // Add your handling code here:
    }//GEN-LAST:event_macos1mnit1ActionPerformed
    
    private void xpmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xpmnit1ActionPerformed
        this.changeThemeByUrl("com.l2fprod.gui.plaf.skin.SkinLookAndFeel", getClass().getResource("/xplunathemepack.zip"));
        
        appProp.setProperty("defaultlnf","com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
        
        appProp.setProperty("defaulttheme", "xplunathemepack.zip");
        // Add your handling code here:
    }//GEN-LAST:event_xpmnit1ActionPerformed
    
    private void macmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macmnit1ActionPerformed
        this.changeTheme("it.unitn.ing.swing.plaf.macos.MacOSLookAndFeel", null);
        // Add your handling code here:
    }//GEN-LAST:event_macmnit1ActionPerformed
    
    private void motiflnfmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motiflnfmnit1ActionPerformed
        this.changeTheme("com.sun.java.swing.plaf.motif.MotifLookAndFeel", null);
        // Add your handling code here:
    }//GEN-LAST:event_motiflnfmnit1ActionPerformed
    
    private void winlnfmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_winlnfmnit1ActionPerformed
        this.changeTheme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", null);
        // Add your handling code here:
    }//GEN-LAST:event_winlnfmnit1ActionPerformed
    
    private void swinglnfmnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swinglnfmnit1ActionPerformed
        this.changeTheme("javax.swing.plaf.metal.MetalLookAndFeel", null);
        // Add your handling code here:
    }//GEN-LAST:event_swinglnfmnit1ActionPerformed
    
    private void fmgrBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fmgrBttnActionPerformed
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("konqueror");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Windows 2000")) {
                
                rt.exec("explorer");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }         // Add your handling code here:
    }//GEN-LAST:event_fmgrBttnActionPerformed
    
    private void calcBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcBttnActionPerformed
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("kcalc");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Windows 2002")) {
                
                rt.exec("calc.exe");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }         // Add your handling code here:
    }//GEN-LAST:event_calcBttnActionPerformed
    
    private void brwsrBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brwsrBttnActionPerformed
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("netscape");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Windows 2000")) {
                
                rt.exec("c:/Program Files/Internet Explorer/iexplore.exe");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }         // Add your handling code here:
    }//GEN-LAST:event_brwsrBttnActionPerformed
    
    private void editButtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtnActionPerformed
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("kedit");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Windows 2000")) {
                
                rt.exec("D:/Program Files/Microsoft Office/Office/winword.exe");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }         // Add your handling code here:
    }//GEN-LAST:event_editButtnActionPerformed
    
    private void mailBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailBttnActionPerformed
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("kmail");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Windowsxp")) {
                
                rt.exec("D:/Program Files/Microsoft Office/Office/outlook");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }          // Add your handling code here:
    }//GEN-LAST:event_mailBttnActionPerformed
    
    private void mtmediaBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mtmediaBttnActionPerformed
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        
        try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                
                rt.exec("kscd");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
            }
            else if (System.getProperty("os.name").equalsIgnoreCase("Microsoft Windows XP")) {
                
                rt.exec("c:\\Program Files\\Windows Media Player\\wmplayer.exe");
                
                System.out.print(System.getProperty("os.name") + "  "+System.getProperty("os.version"));
                
            }
            
            
            
            
        } catch(IOException IOExec){
            
            JOptionPane.showMessageDialog(this, "Sorry, can't get path to Help utility", "Error Message", JOptionPane.ERROR_MESSAGE);
            
        }           // Add your handling code here:
    }//GEN-LAST:event_mtmediaBttnActionPerformed
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //this.FleetMainFr.setSize(this.getContentPane().getSize());   // Add your handling code here:
    }//GEN-LAST:event_formComponentResized
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "Do you really want to exit application?", "Caution before closing application!", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
            
            this.dispose();
            
        }
        
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    //  public static void main(String args[]) {
    //      new KitchenMain().setVisible(true);
    // }
    
    
    
    public void changeThemeByUrl(java.lang.String lnf, java.net.URL themepack) {
        
        java.lang.String themepackPath = null;
        
        
        com.l2fprod.gui.plaf.skin.Skin skin = null;
        
        try {
            
            skin = com.l2fprod.gui.plaf.skin.SkinLookAndFeel.loadThemePack(themepack);
            
        } catch(java.lang.Exception exec) {
            
            
        }
        
        com.l2fprod.gui.plaf.skin.SkinLookAndFeel.setSkin(skin);
        
        java.awt.Component[] component_array = null;
        
        
        component_array = this.getComponents();
        
        try {
            
            try {
                
                try {
                    
                    try {
                        
                        
                        javax.swing.UIManager.setLookAndFeel(lnf);
                        
                        jToolBar1.updateUI();
                        
                        //   this.jPopupMenu1.updateUI();
                        
                        javax.swing.SwingUtilities.updateComponentTreeUI(this);
                        
                        javax.swing.SwingUtilities.updateComponentTreeUI(this.getRootPane());
                        
                        //    javax.swing.SwingUtilities.updateComponentTreeUI(this.jPopupMenu1);
                        
                        this.jMenuBar1.setBorder(new javax.swing.border.TitledBorder(null, "Fleet Management System", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier", 2, 8), new java.awt.Color(0, 0, 153)));
                        
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(this, cnfExec.getMessage());
                        
                    }
                    
                    
                } catch(java.lang.InstantiationException instExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(this, instExec.getMessage());
                    
                }
                
            } catch(java.lang.IllegalAccessException illegExec) {
                
                javax.swing.JOptionPane.showMessageDialog(this, illegExec.getMessage());
                
            }
            
        } catch(javax.swing.UnsupportedLookAndFeelException unsuppExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, unsuppExec.getMessage());
            
        }
        
    }
    
    
      public java.lang.String getCompanyName() {
    
        java.lang.String title = null;
        

        try {
        
    
        java.sql.Statement stmt1 = this.connectDB.createStatement();
        
        java.sql.ResultSet rs1 = stmt1.executeQuery("select company_name from fleet_schema.company_profile");
        
        while (rs1.next()) {
        
            title = (rs1.getString(1));
        }
        
        }catch (java.sql.SQLException sqlEx){
        
        
    }
      return title;      
    }
    private void changeTheme(java.lang.String lnf, java.lang.String themepack) {
        
        java.lang.String themepackPath = null;
        
        
        com.l2fprod.gui.plaf.skin.Skin skin = null;
        
        
        try {
            
            skin = com.l2fprod.gui.plaf.skin.SkinLookAndFeel.loadThemePack(themepack);
            
        } catch(java.lang.Exception exec) {
            
            
        }
        
        com.l2fprod.gui.plaf.skin.SkinLookAndFeel.setSkin(skin);
        
        java.awt.Component[] component_array = null;
        
        
        component_array = this.getComponents();
        
        try {
            
            try {
                
                try {
                    
                    try {
                        
                        
                        javax.swing.UIManager.setLookAndFeel(lnf);
                        
                        jToolBar1.updateUI();
                        
                        //   this.jPopupMenu1.updateUI();
                        
                        javax.swing.SwingUtilities.updateComponentTreeUI(this);
                        
                        javax.swing.SwingUtilities.updateComponentTreeUI(this.getRootPane());
                        
                        
                        
                        this.jMenuBar1.setBorder(new javax.swing.border.TitledBorder(null, "Fleet Management System", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier", 2, 8), new java.awt.Color(0, 0, 153)));
                        
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(this, cnfExec.getMessage());
                        
                    }
                    
                    
                } catch(java.lang.InstantiationException instExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(this, instExec.getMessage());
                    
                }
                
            } catch(java.lang.IllegalAccessException illegExec) {
                
                javax.swing.JOptionPane.showMessageDialog(this, illegExec.getMessage());
                
            }
            
        } catch(javax.swing.UnsupportedLookAndFeelException unsuppExec) {
            
            javax.swing.JOptionPane.showMessageDialog(this, unsuppExec.getMessage());
            
        }
        
    }
    
    public static void main(String args[]) {
        
        FleetMainFr stockInstance = new FleetMainFr(connect2DB(), getPooledConnectionSource());//, getPath2Acrobat(), psWord);
        
        stockInstance.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        stockInstance.setVisible(true);
        
    }
    
    public static java.sql.Connection connect2DB() {
        
        java.sql.Connection connDb = null;
        
        try {
            
            java.lang.Class.forName("org.postgresql.Driver");
            
        } catch(java.lang.ClassNotFoundException cnf) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnf.getMessage());
            
        }
        
        try {
            
            connDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/fleet","postgres","funsoft");
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        return connDb;
        
    }
    
    public static org.netbeans.lib.sql.pool.PooledConnectionSource getPooledConnectionSource() {
        
        org.netbeans.lib.sql.pool.PooledConnectionSource pooledConnectionSource1 = new org.netbeans.lib.sql.pool.PooledConnectionSource();
        
        pooledConnectionSource1.setDatabase("jdbc:postgresql://localhost:5432/fleet");
        
        pooledConnectionSource1.setDriver("org.postgresql.Driver");
        
        pooledConnectionSource1.setPassword("02ac219a6b011019ea966757de3451d2399ce04dd82596d3ba4266a8845ab7f80861", true);
        
        pooledConnectionSource1.setUsername("postgres");
        
        return pooledConnectionSource1;
    }
    
    public static java.lang.String getPath2Acrobat() {
        //        java.lang.String dbServerIp = null;
        java.lang.String acroPath = null;
        
        java.lang.String myAppFileUrl = null;
        
        myAppFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "myapp.properties";
        
        try {
            
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);
            
            
            
            
            
            java.util.Properties appProp = new java.util.Properties();
            
            try {
                
                appProp.load(propInFile);
                
                
                
                acroPath = appProp.getProperty("acrobatPath","D:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe ");
                
                
                System.out.println(acroPath);
                
                
                
                
            } catch (java.io.IOException ioExec){
                
                System.out.println(ioExec.getMessage());
                
            }
            
            
            
        } catch (java.lang.Exception exc){
            
            System.out.println(exc.getMessage());
            
            
            
        }
        
        return acroPath;
        
    }
    
    public void run() {
    }
    
    
    
    
    public void updateActivityDomains() {
        
        if (domainsReset) {
            
            try {
                
                java.sql.PreparedStatement pstmt_del = this.connectDB.prepareStatement("DELETE FROM menu_item_list WHERE app_name = 'hospital_payroll'");
                
                pstmt_del.execute();
                
            } catch(java.sql.SQLException sqlExec) {
                
                javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
                
            }
            
            try {
                
                java.lang.Class classMain = java.lang.Class.forName("com.afrisoftech.hospayroll.FleetMainFr");
                
                java.lang.reflect.Field[] fieldsArray = classMain.getDeclaredFields();
                
                for (int n = 0; n  < fieldsArray.length; n++) {
                    
                    if (fieldsArray[n].getName().endsWith("mnit")) {
                        
                        
                        try {
                            
                            
                            javax.swing.JMenuItem disMenuItems = (javax.swing.JMenuItem)fieldsArray[n].get(this);
                            
                            
                            try {
                                
                                
                                Class.forName("org.postgresql.Driver");
                                
                                
                            } catch (java.lang.ClassNotFoundException cnfExec) {
                                
                                
                                System.out.println("Can't locate PostgreSQL driver");
                                
                                
                            }
                            
                            try {
                                
                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into menu_item_list values(?, ?, ?)");
                                
                                System.out.println("Ready to insert menu items");
                                
                                pstmt.setString(1,fieldsArray[n].getName());
                                
                                pstmt.setString(2,disMenuItems.getText());
                                
                                pstmt.setString(3,"hospital_payroll");
                                
                                pstmt.executeUpdate();
                                
                            } catch(java.sql.SQLException sq){
                                
                                javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                                
                                System.out.println(sq.getMessage());
                                
                            }
                            
                            
                            
                        } catch(java.lang.IllegalAccessException illAcc){
                            
                            System.out.println(illAcc.getMessage());
                            
                        }
                    }
                    
                }
                
            } catch(java.lang.ClassNotFoundException clsNotFnd){
                
                javax.swing.JOptionPane.showMessageDialog(this, "Class hospital.hospital not found in the classpath");
                
            }
        }
        
    }
    public void menuItemsDisabler(java.lang.Object[] menuList2Disable) {
        
        java.lang.reflect.Field[] fieldsArray = null;
        
        javax.swing.JMenuItem disMenuItems = null;
        
        
        try {
            java.lang.Class classMain = java.lang.Class.forName("com.afrisoftech.fleet.FleetMainFr");
            
            try {
                
                fieldsArray = classMain.getDeclaredFields();
                
                for (int n = 0; n  < fieldsArray.length; n++) {
                    
                    if (fieldsArray[n].getName().endsWith("mnit")) {
                        
                        disMenuItems = (javax.swing.JMenuItem)fieldsArray[n].get(this);
                        
                        System.out.println(fieldsArray[n].getName()+"\t"+"\t"+"\t"+disMenuItems.getText());
                        
                        
                        disMenuItems.setEnabled(false);
                        
                        for (int m = 0; m < menuList2Disable.length; m++) {
                            
                            if (fieldsArray[n].getName().equals(menuList2Disable[m])) {
                                
                                java.lang.Object obj = (java.lang.Object)fieldsArray[n];
                                
                                try{
                                    
                                    javax.swing.JMenuItem men2dis = (javax.swing.JMenuItem)fieldsArray[n].get(this);
                                    
                                    men2dis.setEnabled(true);
                                    
                                } catch(java.lang.IllegalAccessException illAcc){
                                    
                                    System.out.println(illAcc.getMessage());
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
                
            } catch(java.lang.IllegalAccessException illAcc){
                
                System.out.println(illAcc.getMessage());
                
            }
            
        } catch(java.lang.ClassNotFoundException clsNotFnd){
            
            javax.swing.JOptionPane.showMessageDialog(this, "Class hospital.hospital not found in the classpath");
            
        }
        
    }
    public void updateNewComponentsUI(){
        
        
    }
    class ConnectionStatus extends java.lang.Thread {
        
        boolean runningStatus = true;
        
        java.sql.Statement stmtConnectionStatus;
        
        public ConnectionStatus(java.awt.Component parent) {
            
            try {
                
                System.out.println(connectDB);
                
                stmtConnectionStatus = connectDB.createStatement();
                
                
            } catch (java.sql.SQLException sqlExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Connection not established.");
                
            }
            
        }
        
        
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddVehiclemnit;
    private javax.swing.JButton Addnewvehiclebtn2;
    private javax.swing.JMenuItem Addvehicleduplicate;
    private javax.swing.JMenuItem Adjustrecievemnit;
    private javax.swing.JMenuItem Allvehiclemnit;
    private javax.swing.JMenu Billingmn;
    private javax.swing.JMenuItem Browseinvoicesmnit;
    private javax.swing.JMenuItem Browsepaymemnit;
    private javax.swing.JMenuItem Coststatisticsmnit;
    private javax.swing.JMenuItem Currentmainstatusmnit;
    private javax.swing.JButton Deletevehiclebtn2;
    private javax.swing.JButton Driversbtn2;
    private javax.swing.JButton Editbtn;
    private javax.swing.JMenuItem Employeemnit;
    private javax.swing.JMenu FleetHelpmn;
    javax.swing.JPanel FleetMainPanel;
    private javax.swing.JMenu FleetReportsmn1;
    private javax.swing.JSplitPane FleetSplit1;
    private javax.swing.JMenu FleetUtilitiesmn;
    private javax.swing.JMenu Fleetmanagermn;
    private javax.swing.JMenuItem Fuelhistorymnit;
    private javax.swing.JMenuItem Generateinvomnit;
    private javax.swing.JMenu Inventorymn;
    private javax.swing.JMenuItem IssueWorkordermnit;
    private javax.swing.JMenuItem Lastpmsetupmnit;
    private javax.swing.JMenuItem Maintenanceduemnit;
    private javax.swing.JMenuItem Maintenancehistmnit;
    private javax.swing.JMenu Maintenancemn;
    private javax.swing.JSeparator Maintenancesep1;
    private javax.swing.JSeparator Maintenancesep2;
    private javax.swing.JButton Partsbtn2;
    private javax.swing.JMenuItem Partsinventorymnit;
    private javax.swing.JMenuItem Pmassociatesmn;
    private javax.swing.JButton Pmcheckbtn2;
    private javax.swing.JMenu Pmcheckwizmn;
    private javax.swing.JButton Pmschedrepairsbtn4;
    private javax.swing.JMenuItem Pmschedulesetupmnit;
    private javax.swing.JMenuItem Poreceiptbypartmnit;
    private javax.swing.JMenuItem Poreceiptbyponummnit;
    private javax.swing.JMenuItem Purchaseorderaddmnit;
    private javax.swing.JMenuItem Purchaseordersmnit;
    private javax.swing.JMenuItem Recieptsrmnit;
    private javax.swing.JMenuItem Recordpaymentsmnit;
    private javax.swing.JButton Repairsbtn4;
    private javax.swing.JButton Reports1;
    private javax.swing.JMenuItem Scheduledrepairmnit;
    private javax.swing.JMenuItem Selecteddepmn;
    private javax.swing.JMenuItem Selectedlocmn;
    private javax.swing.JMenuItem Selectedvehiclemnit;
    private javax.swing.JMenuItem Tireinventorymnit;
    private javax.swing.JMenuItem Triphist;
    private javax.swing.JMenuItem Updatemetermnit;
    private javax.swing.JMenu Vehiclemn;
    private javax.swing.JButton Vendorbtn2;
    private javax.swing.JMenuItem Workordermanagmnit;
    private javax.swing.JButton Workordersbtn2;
    private javax.swing.JMenu Workordersmnit;
    private javax.swing.JMenuItem aboutmnit;
    private javax.swing.JLabel activeDatabase;
    private javax.swing.JLabel activePane;
    private javax.swing.JRadioButtonMenuItem aquamnit1;
    private javax.swing.JMenuItem backgroundmnit;
    private javax.swing.JRadioButtonMenuItem bbjmnit11;
    private javax.swing.JRadioButtonMenuItem beosmnit1;
    private javax.swing.JMenu billingmn;
    private javax.swing.JButton brwsrBttn;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton calcBttn;
    private javax.swing.JMenuItem certificationsmnit;
    private javax.swing.JMenuItem certificationsrenewalsmnit;
    private javax.swing.JMenuItem companyprofilemnit;
    private javax.swing.JMenuItem completehistorymnit;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JMenu countrydistrictmn;
    private javax.swing.JMenuItem countrymntfr;
    private javax.swing.JMenuItem currentdueallvehiclemnit;
    private javax.swing.JMenuItem currentlydueselectedvehiclesmnit;
    private javax.swing.JMenuItem currentpmstatusmnit;
    private javax.swing.JMenuItem departmentmnit;
    private javax.swing.JMenuItem detailedcontactlistmnit;
    private javax.swing.JMenuItem districtsmnit;
    private javax.swing.JMenu driveremployeemnit;
    private javax.swing.JMenuItem drivermnit;
    private javax.swing.JButton editButtn;
    private javax.swing.JMenu employeesmn;
    private javax.swing.JMenuItem exitmnit;
    private javax.swing.JMenuItem externalserviceshistorymnit;
    private javax.swing.JMenu fleetOperationsmn;
    private javax.swing.JMenu fleetSetupmn;
    public javax.swing.JDesktopPane fleetdesktop;
    private javax.swing.JMenuItem fleetlistingmnit;
    private javax.swing.JMenuItem fleetlistingrmnit;
    private javax.swing.JMenu fleetmn;
    private javax.swing.JButton fmgrBttn;
    private javax.swing.JMenuItem fuelcostsummarymnit;
    private javax.swing.JMenuItem fuelcostsummaryrmnit;
    private javax.swing.JMenu fuelpurchasemn;
    private javax.swing.JMenuItem fuelpurchaseshistorymnit;
    private javax.swing.JMenuItem fuelpurchasesrmnit;
    private javax.swing.JMenuItem generalcontactlistmnit;
    private javax.swing.JMenuItem generalmnit;
    private javax.swing.JMenuItem generateclosedmnit;
    private javax.swing.JMenuItem generateoutstandingmnit;
    private javax.swing.JButton helpButton;
    private javax.swing.JMenuItem helpcontentsmnit;
    private javax.swing.JMenuItem historycostsummarurmnit;
    private javax.swing.JMenuItem historycostsummarymnit;
    private javax.swing.JMenuItem historyoverviewmnit;
    private javax.swing.JMenuItem insurancemnit;
    private javax.swing.JMenuItem insurancermnit;
    private javax.swing.JMenuItem invoicesoutstandingmnit;
    private javax.swing.JMenuItem invoicespaidinfullmnit;
    private javax.swing.JMenuItem invoicessummaryoutstandingmnit;
    private javax.swing.JMenuItem invoicessummarypaidinfullmnit;
    private javax.swing.JMenuItem issuelworkmnit;
    public static javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator51;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem labourhistorymnit;
    private javax.swing.JMenuItem legalstatusmnit;
    private javax.swing.JMenuItem licenceinformationmnit;
    private javax.swing.JMenuItem licencerenewalsduemnit;
    private javax.swing.JMenu lnfmn;
    private javax.swing.JMenuItem loanleasemnit;
    private javax.swing.JMenuItem loanleaser;
    private javax.swing.JCheckBoxMenuItem macmnit1;
    private javax.swing.JRadioButtonMenuItem macos1mnit1;
    private javax.swing.JButton mailBttn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem maintainanceduemnit;
    private javax.swing.JMenu maintanancehistorymn;
    private javax.swing.JToolBar maintoolbar;
    private javax.swing.JRadioButtonMenuItem modernmnit1;
    private javax.swing.JCheckBoxMenuItem motiflnfmnit1;
    private javax.swing.JButton mtmediaBttn;
    private javax.swing.JMenuItem needreoderedmnit;
    private javax.swing.JMenuItem notesmnit;
    private javax.swing.JMenuItem outstandingselectedvehicle;
    private javax.swing.JMenuItem outstndingallvehiclemnit;
    private javax.swing.JMenuItem partequptmnit;
    private javax.swing.JMenuItem partshistorymnit;
    private javax.swing.JMenuItem partshstoryrmnit;
    private javax.swing.JMenu partsinventorymn;
    private javax.swing.JMenuItem partslistingbyvendormnit;
    private javax.swing.JMenuItem partslistingmnit;
    private javax.swing.JMenuItem partslistngcategorymnit;
    private javax.swing.JMenuItem partsusagemnit;
    private javax.swing.JMenuItem paymentsrecievedmnit;
    private javax.swing.JMenuItem personalinformationmnit;
    private javax.swing.JMenuItem photomnit;
    private javax.swing.JMenuItem phsicalinformationsmnit;
    private javax.swing.JMenuItem physicalrenewalsduemnit;
    private javax.swing.JMenuItem pmcoststatisticsoverallmnit;
    private javax.swing.JMenuItem pmcoststatisticsoveralmnit;
    private javax.swing.JMenuItem pmcoststatisticspervehicleequipmnit;
    private javax.swing.JMenuItem pmcoststatisticspervehicleequiprmnit;
    private javax.swing.JMenuItem pmhistorymnit;
    private javax.swing.JMenuItem pmlastperfomeddatamnit;
    private javax.swing.JMenuItem pmschedulesmnit;
    private javax.swing.JMenu pmsevicesmn;
    private javax.swing.JMenuItem purchaseinformationmnit;
    private javax.swing.JMenuItem purchaseinformnit;
    private javax.swing.JMenu purchaseodersmn;
    private javax.swing.JMenuItem recieptsmnit;
    private javax.swing.JMenuItem registrationinformationmnit;
    private javax.swing.JMenuItem registrationpartimnit;
    private javax.swing.JMenuItem registrationrenewalduemnit;
    private javax.swing.JMenuItem repairhistorymnit;
    private javax.swing.JMenuItem reportgeneratorrmnit;
    private javax.swing.JMenuItem schedulerepairmnit;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JSeparator sep3;
    private javax.swing.JSeparator sepat1;
    private javax.swing.JSeparator sepper1;
    private javax.swing.JMenu sheduledrepairsmn;
    private javax.swing.JMenu skinmn;
    private javax.swing.JMenuItem specificationspartsmnit;
    private javax.swing.JMenuItem spescificationmnit;
    private javax.swing.JMenuItem stateinspectionrenewalsduemnit;
    private javax.swing.JMenuItem stockvaluedetailedmnit;
    private javax.swing.JMenuItem stockvaluesummarymnit;
    private javax.swing.JMenuItem summaryclosedmnit;
    private javax.swing.JMenuItem summaryoutstandingmnit;
    private javax.swing.JMenu summaryreportsmn;
    private javax.swing.JCheckBoxMenuItem swinglnfmnit1;
    private javax.swing.JPanel taskBar;
    private javax.swing.JMenuItem tirehistmnit;
    private javax.swing.JMenuItem tirehistorybytiremnit;
    private javax.swing.JMenuItem tirehistorybyvehicleequipmentmnit;
    private javax.swing.JMenuItem tiresinventorylistingallmnit;
    private javax.swing.JMenuItem tiresinventorylistingdismountedmnit;
    private javax.swing.JMenuItem tiresinventorylistingmountedmnit;
    private javax.swing.JMenu tiresmn;
    private javax.swing.JToolBar tool5;
    private javax.swing.JMenu travelmn;
    private javax.swing.JMenuItem triphistorymnit;
    private javax.swing.JMenuItem tripticketsmnit;
    private javax.swing.JMenuItem usagebydriverdeptdetailedmnit;
    private javax.swing.JMenuItem usagebydriveroverviewmnit;
    private javax.swing.JMenuItem usagebylocdeptdriveroverviewmnit;
    private javax.swing.JMenuItem usagebylocdeptvehicledetailedrmnit;
    private javax.swing.JLabel userLabel;
    private javax.swing.JSeparator vehiclest1;
    private javax.swing.JSeparator vehiclest2;
    private javax.swing.JSeparator vehiclest3;
    private javax.swing.JSeparator vehiclest4;
    private javax.swing.JSeparator vehiclest5;
    private javax.swing.JMenuItem vehicletypemnit;
    private javax.swing.JMenu vendorDBmn;
    private javax.swing.JMenuItem vendorcostsummarymnit;
    private javax.swing.JMenuItem vendorcostsummaryrmnit;
    private javax.swing.JMenuItem vendormnit;
    private javax.swing.JMenuItem vendorscostsummarymnit;
    private javax.swing.JMenuItem vendorslistingmnit;
    private javax.swing.JMenu vendorsmn;
    private javax.swing.JMenuItem vendorsummarymnit;
    private javax.swing.JRadioButtonMenuItem whiltmnit1;
    private javax.swing.JCheckBoxMenuItem winlnfmnit1;
    private javax.swing.JMenuItem workordedersummaryopenmnit;
    private javax.swing.JMenuItem workorderscompletemnit;
    private javax.swing.JMenuItem workordersopenmnit;
    private javax.swing.JMenuItem workordersummaryclosedmnit;
    private javax.swing.JMenuItem worksummaryclosedmnit;
    private javax.swing.JMenuItem worksummaryopenmnit;
    private javax.swing.JRadioButtonMenuItem xpmnit1;
    // End of variables declaration//GEN-END:variables
    
}
