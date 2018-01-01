/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.enterprise;

/**
 *
 * @author Charles
 */
public class EnterpriseMain extends javax.swing.JFrame {

    static java.sql.Connection connectDB = null;
    /**
     * Creates new form EnterpriseMain
     */
    public EnterpriseMain(java.sql.Connection connDB) {
        
        connectDB = connDB;
        
        initComponents();
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

        funsoftSplitPane = new javax.swing.JSplitPane();
        sidePanel = new javax.swing.JPanel();
        sideScrollPane = new javax.swing.JScrollPane();
        enterpriseTree = new javax.swing.JTree();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        topToolBar = new javax.swing.JToolBar();
        homeBtn = new javax.swing.JButton();
        treeBtn = new javax.swing.JButton();
        expandTreeBtn = new javax.swing.JButton();
        collapseTreeBtn = new javax.swing.JButton();
        dashboardMenuBar = new javax.swing.JMenuBar();
        setupmn = new javax.swing.JMenu();
        setupContinentmnit = new javax.swing.JMenuItem();
        setupCountrymnit = new javax.swing.JMenuItem();
        setupRegionCountymnit = new javax.swing.JMenuItem();
        setupDistrictmnit = new javax.swing.JMenuItem();
        accessControlmn = new javax.swing.JMenu();
        createUsermnit = new javax.swing.JMenuItem();
        accessManagement = new javax.swing.JMenuItem();
        userSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMnit = new javax.swing.JMenuItem();
        operationsmn = new javax.swing.JMenu();
        enterpriseDashboardmnit = new javax.swing.JMenuItem();
        hrmmnit = new javax.swing.JMenuItem();
        clinicalsDashboardmnit = new javax.swing.JMenuItem();
        referralMnit = new javax.swing.JMenuItem();
        supplychainMnit = new javax.swing.JMenuItem();
        facilitiesManagementmnit = new javax.swing.JMenuItem();
        utilitiesmn = new javax.swing.JMenu();
        preferencesMnit = new javax.swing.JMenuItem();
        printersetupMnit = new javax.swing.JMenuItem();
        helpmn = new javax.swing.JMenu();
        help = new javax.swing.JMenuItem();
        aboutFunsoftmnit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Funsoft I-HMIS Ver 8.0 R 3.2");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        funsoftSplitPane.setDividerLocation(200);

        sidePanel.setLayout(new java.awt.GridBagLayout());

        enterpriseTree.setModel(getTreeModel());
        sideScrollPane.setViewportView(enterpriseTree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sidePanel.add(sideScrollPane, gridBagConstraints);

        funsoftSplitPane.setLeftComponent(sidePanel);

        jDesktopPane1.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        funsoftSplitPane.setRightComponent(jDesktopPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 500.0;
        getContentPane().add(funsoftSplitPane, gridBagConstraints);

        topToolBar.setRollover(true);
        topToolBar.setAutoscrolls(true);
        topToolBar.setInheritsPopupMenu(true);

        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Home 2.png"))); // NOI18N
        homeBtn.setText("Home");
        homeBtn.setFocusable(false);
        homeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        topToolBar.add(homeBtn);

        treeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Globe 1.png"))); // NOI18N
        treeBtn.setText("Explore");
        treeBtn.setFocusable(false);
        treeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        treeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        topToolBar.add(treeBtn);

        expandTreeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/The Internet.png"))); // NOI18N
        expandTreeBtn.setText("Expand Tree");
        expandTreeBtn.setFocusable(false);
        expandTreeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        expandTreeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        topToolBar.add(expandTreeBtn);

        collapseTreeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Folders/Folder Yellow Software 2.png"))); // NOI18N
        collapseTreeBtn.setText("Collapse Tree");
        collapseTreeBtn.setFocusable(false);
        collapseTreeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collapseTreeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        topToolBar.add(collapseTreeBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(topToolBar, gridBagConstraints);

        setupmn.setText("Setup");

        setupContinentmnit.setText("Continent");
        setupmn.add(setupContinentmnit);

        setupCountrymnit.setText("Country");
        setupCountrymnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setupCountrymnitActionPerformed(evt);
            }
        });
        setupmn.add(setupCountrymnit);

        setupRegionCountymnit.setText("Region/County");
        setupmn.add(setupRegionCountymnit);

        setupDistrictmnit.setText("Facility Setup");
        setupmn.add(setupDistrictmnit);

        accessControlmn.setText("Access Control");

        createUsermnit.setText("User Creation");
        accessControlmn.add(createUsermnit);

        accessManagement.setText("Access Management");
        accessControlmn.add(accessManagement);

        setupmn.add(accessControlmn);
        setupmn.add(userSeparator1);

        exitMnit.setText("Exit");
        setupmn.add(exitMnit);

        dashboardMenuBar.add(setupmn);

        operationsmn.setText("Operations");

        enterpriseDashboardmnit.setText("Enterprise Dashboard");
        operationsmn.add(enterpriseDashboardmnit);

        hrmmnit.setText("HR and Payroll Dashboard");
        operationsmn.add(hrmmnit);

        clinicalsDashboardmnit.setText("Clinicals Dashboard");
        operationsmn.add(clinicalsDashboardmnit);

        referralMnit.setText("Referrals Management");
        operationsmn.add(referralMnit);

        supplychainMnit.setText("Supply Chain Management");
        operationsmn.add(supplychainMnit);

        facilitiesManagementmnit.setText("Facilities Management");
        operationsmn.add(facilitiesManagementmnit);

        dashboardMenuBar.add(operationsmn);

        utilitiesmn.setText("Utilities");

        preferencesMnit.setText("User Preferences");
        utilitiesmn.add(preferencesMnit);

        printersetupMnit.setText("Printer Setup");
        utilitiesmn.add(printersetupMnit);

        dashboardMenuBar.add(utilitiesmn);

        helpmn.setText("Help");

        help.setText("Help");
        helpmn.add(help);

        aboutFunsoftmnit.setText("About Funsoft I-HMIS");
        helpmn.add(aboutFunsoftmnit);

        dashboardMenuBar.add(helpmn);

        setJMenuBar(dashboardMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupCountrymnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setupCountrymnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setupCountrymnitActionPerformed

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
            java.util.logging.Logger.getLogger(EnterpriseMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterpriseMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterpriseMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterpriseMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterpriseMain(connectDB).setVisible(true);
            }
        });
    }

    private javax.swing.tree.DefaultTreeModel getTreeModel() {
       
        javax.swing.tree.DefaultTreeModel treeModel = null;

        return treeModel;
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutFunsoftmnit;
    private javax.swing.JMenu accessControlmn;
    private javax.swing.JMenuItem accessManagement;
    private javax.swing.JMenuItem clinicalsDashboardmnit;
    private javax.swing.JButton collapseTreeBtn;
    private javax.swing.JMenuItem createUsermnit;
    private javax.swing.JMenuBar dashboardMenuBar;
    private javax.swing.JMenuItem enterpriseDashboardmnit;
    private javax.swing.JTree enterpriseTree;
    private javax.swing.JMenuItem exitMnit;
    private javax.swing.JButton expandTreeBtn;
    private javax.swing.JMenuItem facilitiesManagementmnit;
    private javax.swing.JSplitPane funsoftSplitPane;
    private javax.swing.JMenuItem help;
    private javax.swing.JMenu helpmn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JMenuItem hrmmnit;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu operationsmn;
    private javax.swing.JMenuItem preferencesMnit;
    private javax.swing.JMenuItem printersetupMnit;
    private javax.swing.JMenuItem referralMnit;
    private javax.swing.JMenuItem setupContinentmnit;
    private javax.swing.JMenuItem setupCountrymnit;
    private javax.swing.JMenuItem setupDistrictmnit;
    private javax.swing.JMenuItem setupRegionCountymnit;
    private javax.swing.JMenu setupmn;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JScrollPane sideScrollPane;
    private javax.swing.JMenuItem supplychainMnit;
    private javax.swing.JToolBar topToolBar;
    private javax.swing.JButton treeBtn;
    private javax.swing.JPopupMenu.Separator userSeparator1;
    private javax.swing.JMenu utilitiesmn;
    // End of variables declaration//GEN-END:variables
}
