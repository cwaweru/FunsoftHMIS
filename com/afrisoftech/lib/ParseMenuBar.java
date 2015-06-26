package com.afrisoftech.lib;



public class ParseMenuBar {
    
    com.afrisoftech.hospital.HospitalMain hospitalMain = null;
    
    Class cl = null;
    
    String tabString = " ";
    
    String space = " ";
    
    String backSpace = "\b";
    
    javax.swing.tree.DefaultMutableTreeNode parentNode = null;
    
    javax.swing.tree.DefaultMutableTreeNode siblingNode = null;
    
    javax.swing.JMenu popupMenu = null;
    
    javax.swing.JMenuItem popupMenuItem = null;
    
    
    javax.swing.JCheckBoxMenuItem popupCheckBoxMenuItem = null;
    
    javax.swing.JRadioButtonMenuItem popupRadioButtonMenuItem = null;
    
    javax.swing.tree.DefaultMutableTreeNode[] subNodes;
    
    int treeRowCount = 0;
    
    int reportsRowCount = 0;
    
    int treeCount = 0;
    
    // public final javax.swing.JMenuBar targetMenuBar1;
    
    // public final javax.swing.AbstractButton actionComponent1;
    
    public void ParseMenuBar() {
        
        
        //     parseHospitalMenus(menuBar2Parse);
        
        
    }
    
    //    public static void main(java.lang.String args[]) {
    
    //        ParseHospital parseHospital = new ParseHospital();
    
    //        parseHospital.parseHospitalMenus();
    
    //    }
    public int parseHospitalMenus(javax.swing.JMenuBar targetMenuBar, javax.swing.tree.DefaultMutableTreeNode topTreeNode, javax.swing.tree.DefaultMutableTreeNode topReports, java.lang.String reportTitleNode, java.lang.String utilitiesNodeTitle) {
        
        com.afrisoftech.lic.License licFile = new com.afrisoftech.lic.License(System.getProperties());
        
        treeCount = 0;
        
        int treeRowCounts = 0;
        /*
        try {
         
            System.out.println("Printing menus.");
         
            cl = Class.forName("com.afrisoftech.hospital.HospitalMain");
         
        } catch (java.lang.ClassNotFoundException cnf) {
         
            System.out.println("Class not found exception!");
         
        }
         
        hospitalMain = new com.afrisoftech.hospital.HospitalMain(connect2DB(), getPooledConnectionSource(), null, null);
         
        javax.swing.JMenuBar hospitalMenuBar = hospitalMain.getJMenuBar();
         */
        treeRowCounts = createTreeFromMenuBar(parseMenuFields(targetMenuBar), topTreeNode, topReports, reportTitleNode, utilitiesNodeTitle);
        
        //  System.out.println("Showing all");
        
        // this.dispose();
        System.out.println("row counts = ["+treeRowCounts+"]");
        
        return treeRowCounts;
    }
    
    public void parseHospitalPopupMenus(javax.swing.JMenuBar targetMenuBar, javax.swing.JComponent topPopupMenu, javax.swing.JComponent popupReports, java.lang.String reportTitleNode, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup) {
/*
        try {
 
            System.out.println("Printing menus.");
 
            cl = Class.forName("com.afrisoftech.hospital.HospitalMain");
 
        } catch (java.lang.ClassNotFoundException cnf) {
 
            System.out.println("Class not found exception!");
 
        }
 
        hospitalMain = new com.afrisoftech.hospital.HospitalMain(connect2DB(), getPooledConnectionSource(), null, null);
 
        javax.swing.JMenuBar hospitalMenuBar = hospitalMain.getJMenuBar();
 */
        createPopupMenuFromMenuBar(targetMenuBar, parseMenuFields(targetMenuBar), topPopupMenu, popupReports, reportTitleNode, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
        
        //  System.out.println("Showing all");
        
        // this.dispose();
        
    }
    
    public void parseHospitalMenuBars(javax.swing.JMenuBar targetMenuBar, javax.swing.JMenuBar payrollMenuBar, javax.swing.JMenuBar stockMenuBar, javax.swing.JComponent topMenuBar, javax.swing.JComponent reportsMenuBar, java.lang.String reportTitleNode, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup) {
/*
        try {
 
            System.out.println("Printing menus.");
 
            cl = Class.forName("com.afrisoftech.hospital.HospitalMain");
 
        } catch (java.lang.ClassNotFoundException cnf) {
 
            System.out.println("Class not found exception!");
 
        }
 
        hospitalMain = new com.afrisoftech.hospital.HospitalMain(connect2DB(), getPooledConnectionSource(), null, null);
 
        javax.swing.JMenuBar hospitalMenuBar = hospitalMain.getJMenuBar();
 */
        createNewMenuBars(targetMenuBar, payrollMenuBar, stockMenuBar, parseMenuFields(targetMenuBar), topMenuBar, reportsMenuBar, reportTitleNode, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
//        recreateNewMenuBars(targetMenuBar, payrollMenuBar, stockMenuBar, parseMenuComponents(targetMenuBar), topMenuBar, reportsMenuBar, reportTitleNode, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
        
        //  System.out.println("Showing all");
        
        // this.dispose();
        
    }
    
    public javax.swing.MenuElement[] parseMenuFields(javax.swing.JMenuBar jMenuBar) {
        
        System.out.println("Start Parsing!!");
        
        javax.swing.MenuElement menuElements[] = jMenuBar.getSubElements();
        
        return menuElements;
        
    }
    
    public javax.swing.JComponent[] parseMenuComponents(javax.swing.JMenuBar jMenuBar) {
        
        System.out.println("Start Parsing!!");
        
        javax.swing.JComponent menuComponents[] = (javax.swing.JComponent[])jMenuBar.getComponents();
        
        return menuComponents;
        
    }
    
    public void printMenuElements(javax.swing.MenuElement[] subElements) {
        // System.out.println(tab+tab);
        //  System.out.println("See whether we are ever called.");
        
        for(int i = 0; i < subElements.length ; i++) {
            
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                // System.out.println("Element printing");
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];
                
                //                System.out.println(subElements[i]);
                System.out.println(tabString + abstractButton.getText());
                   com.afrisoftech.sys.SplashScreenDialog.statusLabel.setText(tabString + abstractButton.getText());
     
            }
            //System.out.println(abstractButton.getText());
            
            if (subElements[i].getSubElements().length > 0) {
                tabString = tabString + " ";
                
                // System.out.println("See this!");
                
                printMenuElements(subElements[i].getSubElements());
                
                
                // tabString = tabString + "\b";
                
            }
            
            
            // System.out.println("Element Closing");
        }
        
        tabString = tabString + "\b";
        //   System.out.println("Sub element closing");
        // }
    }
    
    public int  createTreeFromMenuBar(javax.swing.MenuElement subElements[], javax.swing.tree.DefaultMutableTreeNode treeNodes, javax.swing.tree.DefaultMutableTreeNode topReports, java.lang.String reportNodeTitle, java.lang.String utilitiesNodeTitle){
        
        for(int i = 0; i < subElements.length ; i++) {
            
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];
                
                if (abstractButton.isEnabled()) {
                    
                    siblingNode = new javax.swing.tree.DefaultMutableTreeNode(abstractButton.getText());
                    
                    treeNodes.add(siblingNode);
                    
                    if (treeNodes.getUserObject() == "Reports") {
                        
                        treeNodes.setUserObject(reportNodeTitle);
                        
                        topReports.add(treeNodes);
                        
                    }
                    
                    if ( treeNodes.getUserObject() == "Utility") {
                        
                        treeNodes.setUserObject(utilitiesNodeTitle);
                        
                        topReports.add(treeNodes);
                        
                    }
                    
                }
                
            }
            
            if (subElements[i].getSubElements().length > 0) {
                
                createTreeFromMenuBar(subElements[i].getSubElements(), siblingNode, topReports, reportNodeTitle, utilitiesNodeTitle);
                
                if (treeNodes.isLeaf()) {
                    
                    
                    javax.swing.tree.DefaultMutableTreeNode parentNode = (javax.swing.tree.DefaultMutableTreeNode)siblingNode.getParent();
                    
                    siblingNode.removeFromParent();
                    
                }
                
            }
            
            treeCount++;
            
        }
        
        
        return treeCount;
        
    }
    
    public void getTargetMenuItem(javax.swing.MenuElement[] subElements, java.lang.String treeNode) {
        
        java.lang.String string2search = treeNode;
        
        javax.swing.JMenuItem targetAbstractButton = null;
        
        for(int i = 0; i < subElements.length ; i++) {
            
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                // System.out.println("Element printing");
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];
                
                //                System.out.println(subElements[i]);
                if (abstractButton.getText().equals(string2search)) {
                    
                    abstractButton.doClick();
                    //targetAbstractButton = abstractButton;
                    
                    System.out.println("Printing corresponding menuitem : "+abstractButton);
                   com.afrisoftech.sys.SplashScreenDialog.statusLabel.setText("Printing corresponding menuitem : "+abstractButton); 
                    // break;
                    
                }
                
            }
            //System.out.println(abstractButton.getText());
            
            if (subElements[i].getSubElements().length > 0) {
                tabString = tabString + " ";
                
                // System.out.println("See this!");
                
                getTargetMenuItem(subElements[i].getSubElements(), string2search);
                
                
                // tabString = tabString + "\b";
                
            }
            
            
            // System.out.println("Element Closing");
        }
        
        //   return targetAbstractButton;
        
    }
    
    public void  createPopupMenuFromMenuBar(javax.swing.JMenuBar targetMenuBar, javax.swing.MenuElement subElements[], javax.swing.JComponent popupNodes, javax.swing.JComponent popupReports, java.lang.String reportNodeTitle, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup){
        
        final  javax.swing.JMenuBar jmenubar1 = targetMenuBar;
        // subNodes = new javax.swing.tree.DefaultMutableTreeNode[subElements.length];
        
        // parentNode = treeNodes;
        
        for(int i = 0; i < subElements.length ; i++) {
            
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                // System.out.println("Element printing");
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];
                
                //   javax.swing.tree.DefaultMutableTreeNode parentTreeNode =
                
                //                   System.out.println(subElements[i]);
                //   System.out.println(i);
                
                
                
                if (subElements[i].getClass().getName() == "javax.swing.JMenu") {
                    
                    popupMenu = new javax.swing.JMenu(abstractButton.getText());
                    
                    if (popupMenu.getText() != "Feel") {
                        
                        popupNodes.add(popupMenu);
                        
                    }
                    // popupNodes.add(popupMenu);
                    
                    if (abstractButton.getText() == "Reports") {
                        
                        popupMenu.setText(reportNodeTitle);
                        
                        popupReports.add(popupMenu);
                        
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                        
                        popupMenu.setText(utilitiesNodeTitle);
                        
                        popupReports.add(popupMenu);
                        
                    }
                    
                }
                if (subElements[i].getClass().getName() == "javax.swing.JMenuItem") {
                    
                    popupMenuItem = new javax.swing.JMenuItem(abstractButton.getText());
                    
                    popupMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    //  registerActionListener(popupMenuItem, targetMenuBar);
                    
                    // popupMenuItem = abstractButton;
                    
                    
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (abstractButton.isVisible()) {
                        
                       // popupMenuItem.setEnabled(false);
                        
                   // } else {
                        
                        popupNodes.add(popupMenuItem);
                        
                    }
                    
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                    // }
                    
                    
                    // }
                    //  System.out.println("Node : "+ subNodes[i].toString());
                    
                    //  System.out.println(tabString + abstractButton.getText());
                    
                }
                
                if (subElements[i].getClass().getName() == "javax.swing.JRadioButtonMenuItem") {
                    
                    popupRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem(abstractButton.getText());
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    
                    popupRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupRadioButtonMenuItem = (javax.swing.JRadioButtonMenuItem)abstractButton;
                    
                    popupNodes.add(popupRadioButtonMenuItem);
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
                
                //}
                
                if (subElements[i].getClass().getName() == "javax.swing.JCheckBoxMenuItem") {
                    
                    popupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(abstractButton.getText());
                    
                    popupCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupCheckBoxMenuItem = (javax.swing.JCheckBoxMenuItem)abstractButton;
                    
                    popupNodes.add(popupCheckBoxMenuItem);
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
            }
            //}
            //System.out.println(abstractButton.getText());
            
            if (subElements[i].getSubElements().length > 0) {
                
                //tabString = tabString + " ";
                
                //parentNode = siblingNode;
                
                // System.out.println("See this!");
                
                createPopupMenuFromMenuBar(targetMenuBar, subElements[i].getSubElements(), popupMenu, popupReports, reportNodeTitle, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
                
               /* if (treeNodes.isLeaf()) {
                
                
                    javax.swing.tree.DefaultMutableTreeNode parentNode = (javax.swing.tree.DefaultMutableTreeNode)siblingNode.getParent();
                
                    //                   System.out.println("Parent : ["+parentNode.getUserObject()+"]");// and Sibling : ["+siblingNode.getUserObject()+"]");
                
                    siblingNode.removeFromParent();
                    //                    parentNode.remove(siblingNode);
                
                }
                
                */
                // tabString = tabString + "\b";
                
            }
            
            // System.out.println("Element Closing");
        }
        
        //    parentNode = siblingNode;
       /* treeNodes = (javax.swing.tree.DefaultMutableTreeNode)treeNodes.getParent();
        
        if (treeNodes != null) {
        
            System.out.println(treeNodes.getUserObject().toString());
        
        }
        */
        
        
        
    }
    
    public void  createNewMenuBars(javax.swing.JMenuBar targetMenuBar, javax.swing.JMenuBar payrollMenuBar, javax.swing.JMenuBar stockMenuBar, javax.swing.MenuElement subElements[], javax.swing.JComponent popupNodes, javax.swing.JComponent reportsMenuBar, java.lang.String reportNodeTitle, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup){
        
        final  javax.swing.JMenuBar jmenubar1 = targetMenuBar;
        // subNodes = new javax.swing.tree.DefaultMutableTreeNode[subElements.length];
        
        // parentNode = treeNodes;
        
        for(int i = 0; i < subElements.length ; i++) {
            // System.out.println(subElements[i]);
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                // System.out.println("Element printing");
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];
                
                //   javax.swing.tree.DefaultMutableTreeNode parentTreeNode =
                
                //                   System.out.println(subElements[i]);
                //   System.out.println(i);
                
                if (subElements[i].getClass().getName() == "javax.swing.JSeparator") {
                    
                    popupNodes.add(subElements[i].getComponent());
                    
                }
                
                if (subElements[i].getClass().getName() == "javax.swing.JMenu") {
                    
                    popupMenu = new javax.swing.JMenu(abstractButton.getText());
                    
                    if (popupNodes != null) {
                        
                        if (popupMenu.getText() != "Feel") {
                            
                            popupNodes.add(popupMenu);
                            
                        }
                        
                    }
                    
                    if (abstractButton.getText() == "System") {
                        
                        if (stockMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            stockMenuBar.add(popupMenu);
                            
                        }
                        
                    }
                    if (abstractButton.getText() == "System") {
                        
                        if (payrollMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            
                            payrollMenuBar.add(popupMenu);
                            
                        }
                    }
                    if (abstractButton.getText() == "System") {
                        
                        if (reportsMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            reportsMenuBar.add(popupMenu);
                            
                        }
                        
                    }
                    
                    if (abstractButton.getText() == "Reports") {
                        
                        if (reportsMenuBar != null) {
                            
                            popupMenu.setText(reportNodeTitle);
                            
                            reportsMenuBar.add(popupMenu);
                        }
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                        
                        if (reportsMenuBar != null) {
                            
                            popupMenu.setText(utilitiesNodeTitle);
                            
                            reportsMenuBar.add(popupMenu);
                        }
                    }
                    
                }
                if (subElements[i].getClass().getName() == "javax.swing.JMenuItem") {
                    
                    popupMenuItem = new javax.swing.JMenuItem(abstractButton.getText());
                    
                    popupMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    //  registerActionListener(popupMenuItem, targetMenuBar);
                    
                    // popupMenuItem = abstractButton;
                    
                    
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (abstractButton.isVisible()) {
                        popupNodes.add(popupMenuItem);
                      //  popupMenuItem.setVisible(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                    // }
                    
                    
                    // }
                    //  System.out.println("Node : "+ subNodes[i].toString());
                    
                    //  System.out.println(tabString + abstractButton.getText());
                    
                }
                
                if (subElements[i].getClass().getName() == "javax.swing.JRadioButtonMenuItem") {
                    
                    popupRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem(abstractButton.getText());
                    
                    popupRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupRadioButtonMenuItem = (javax.swing.JRadioButtonMenuItem)abstractButton;
                    
                    popupNodes.add(popupRadioButtonMenuItem);
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
                
                //}
                
                if (subElements[i].getClass().getName() == "javax.swing.JCheckBoxMenuItem") {
                    
                    popupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(abstractButton.getText());
                    
                    popupCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupCheckBoxMenuItem = (javax.swing.JCheckBoxMenuItem)abstractButton;
                    
                    popupNodes.add(popupCheckBoxMenuItem);
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
            }
            //}
            //System.out.println(abstractButton.getText());
            
            if (subElements[i].getSubElements().length > 0) {
                
                //tabString = tabString + " ";
                
                //parentNode = siblingNode;
                
                // System.out.println("See this!");
                
                createNewMenuBars(targetMenuBar, payrollMenuBar, stockMenuBar,  subElements[i].getSubElements(), popupMenu, reportsMenuBar, reportNodeTitle, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
                
               /* if (treeNodes.isLeaf()) {
                
                
                    javax.swing.tree.DefaultMutableTreeNode parentNode = (javax.swing.tree.DefaultMutableTreeNode)siblingNode.getParent();
                
                    //                   System.out.println("Parent : ["+parentNode.getUserObject()+"]");// and Sibling : ["+siblingNode.getUserObject()+"]");
                
                    siblingNode.removeFromParent();
                    //                    parentNode.remove(siblingNode);
                
                }
                
                */
                // tabString = tabString + "\b";
                
            }
            
            // System.out.println("Element Closing");
        }
        
        //    parentNode = siblingNode;
       /* treeNodes = (javax.swing.tree.DefaultMutableTreeNode)treeNodes.getParent();
        
        if (treeNodes != null) {
        
            System.out.println(treeNodes.getUserObject().toString());
        
        }
        */
        
        
        
    }

        public void  createNewMenu(javax.swing.JMenu targetMenu, javax.swing.JMenuBar payrollMenuBar, javax.swing.JMenuBar stockMenuBar, javax.swing.MenuElement subElements[], javax.swing.JComponent popupNodes, javax.swing.JComponent reportsMenuBar, java.lang.String reportNodeTitle, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup){

        final  javax.swing.JMenu jmenubar1 = targetMenu;
        // subNodes = new javax.swing.tree.DefaultMutableTreeNode[subElements.length];

        // parentNode = treeNodes;

        for(int i = 0; i < subElements.length ; i++) {
            // System.out.println(subElements[i]);
            if (subElements[i].getClass().getName() != "javax.swing.JPopupMenu") {

                // System.out.println("Element printing");

                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subElements[i];

                //   javax.swing.tree.DefaultMutableTreeNode parentTreeNode =

                //                   System.out.println(subElements[i]);
                //   System.out.println(i);

                if (subElements[i].getClass().getName() == "javax.swing.JSeparator") {

                    popupNodes.add(subElements[i].getComponent());

                }

                if (subElements[i].getClass().getName() == "javax.swing.JMenu") {

                    popupMenu = new javax.swing.JMenu(abstractButton.getText());

                    if (popupNodes != null) {

                        if (popupMenu.getText() != "Feel") {

                            popupNodes.add(popupMenu);

                        }

                    }

                    if (abstractButton.getText() == "System") {

                        if (stockMenuBar != null) {

                            System.out.println("Doing system"+popupMenu.getName());

                            popupMenu.setText("Control");

                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {

                            stockMenuBar.add(popupMenu);

                        }

                    }
                    if (abstractButton.getText() == "System") {

                        if (payrollMenuBar != null) {

                            System.out.println("Doing system"+popupMenu.getName());

                            popupMenu.setText("Control");

                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {


                            payrollMenuBar.add(popupMenu);

                        }
                    }
                    if (abstractButton.getText() == "System") {

                        if (reportsMenuBar != null) {

                            System.out.println("Doing system"+popupMenu.getName());

                            popupMenu.setText("Control");

                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {

                            reportsMenuBar.add(popupMenu);

                        }

                    }

                    if (abstractButton.getText() == "Reports") {

                        if (reportsMenuBar != null) {

                            popupMenu.setText(reportNodeTitle);

                            reportsMenuBar.add(popupMenu);
                        }
                    }

                    if ( abstractButton.getText() == "Utility") {

                        if (reportsMenuBar != null) {

                            popupMenu.setText(utilitiesNodeTitle);

                            reportsMenuBar.add(popupMenu);
                        }
                    }

                }
                if (subElements[i].getClass().getName() == "javax.swing.JMenuItem") {

                    popupMenuItem = new javax.swing.JMenuItem(abstractButton.getText());

                    popupMenuItem.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");



                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());



                            //componentActionPerformed(targetMenuBar, actionComponent.getText());

                        }

                    });
                    //  registerActionListener(popupMenuItem, targetMenuBar);

                    // popupMenuItem = abstractButton;



                    // popupMenuItem.addActionListener(abstractButton.actionListener);


                    if (abstractButton.isVisible()) {
                        popupNodes.add(popupMenuItem);
                      //  popupMenuItem.setVisible(false);

                    }

                   /* if (abstractButton.getText() == "Reports") {

                        popupMenuItem.setText(reportNodeTitle);

                        popupReports.add(popupMenuItem);

                    }

                    if ( abstractButton.getText() == "Utility") {

                        popupMenuItem.setText(utilitiesNodeTitle);

                        popupReports.add(popupMenuItem);

                    }
                    */
                    // }


                    // }
                    //  System.out.println("Node : "+ subNodes[i].toString());

                    //  System.out.println(tabString + abstractButton.getText());

                }

                if (subElements[i].getClass().getName() == "javax.swing.JRadioButtonMenuItem") {

                    popupRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem(abstractButton.getText());

                    popupRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");



                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());



                            //componentActionPerformed(targetMenuBar, actionComponent.getText());

                        }

                    });
                    // popupRadioButtonMenuItem = (javax.swing.JRadioButtonMenuItem)abstractButton;

                    popupNodes.add(popupRadioButtonMenuItem);

                    if (targetButtonGroup != null) {

                        targetButtonGroup.add(popupRadioButtonMenuItem);

                    }

                    if (reportsButtonGroup != null) {

                        reportsButtonGroup.add(popupRadioButtonMenuItem);

                    }
                    // popupMenuItem.addActionListener(abstractButton.actionListener);


                    if (!abstractButton.isEnabled()) {

                        popupMenuItem.setEnabled(false);

                    }

                   /* if (abstractButton.getText() == "Reports") {

                        popupMenuItem.setText(reportNodeTitle);

                        popupReports.add(popupMenuItem);

                    }

                    if ( abstractButton.getText() == "Utility") {

                        popupMenuItem.setText(utilitiesNodeTitle);

                        popupReports.add(popupMenuItem);

                    }
                    */
                }


                // }
                //  System.out.println("Node : "+ subNodes[i].toString());

                //  System.out.println(tabString + abstractButton.getText());

                //}

                if (subElements[i].getClass().getName() == "javax.swing.JCheckBoxMenuItem") {

                    popupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(abstractButton.getText());

                    popupCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");



                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());



                            //componentActionPerformed(targetMenuBar, actionComponent.getText());

                        }

                    });
                    // popupCheckBoxMenuItem = (javax.swing.JCheckBoxMenuItem)abstractButton;

                    popupNodes.add(popupCheckBoxMenuItem);

                    if (targetButtonGroup != null) {

                        targetButtonGroup.add(popupCheckBoxMenuItem);

                    }

                    if (reportsButtonGroup != null) {

                        reportsButtonGroup.add(popupCheckBoxMenuItem);

                    }

                    // popupMenuItem.addActionListener(abstractButton.actionListener);


                    if (!abstractButton.isEnabled()) {

                        popupMenuItem.setEnabled(false);

                    }

                   /* if (abstractButton.getText() == "Reports") {

                        popupMenuItem.setText(reportNodeTitle);

                        popupReports.add(popupMenuItem);

                    }

                    if ( abstractButton.getText() == "Utility") {

                        popupMenuItem.setText(utilitiesNodeTitle);

                        popupReports.add(popupMenuItem);

                    }
                    */
                }


                // }
                //  System.out.println("Node : "+ subNodes[i].toString());

                //  System.out.println(tabString + abstractButton.getText());
            }
            //}
            //System.out.println(abstractButton.getText());

            if (subElements[i].getSubElements().length > 0) {

                //tabString = tabString + " ";

                //parentNode = siblingNode;

                // System.out.println("See this!");

                createNewMenu(targetMenu, payrollMenuBar, stockMenuBar,  subElements[i].getSubElements(), popupMenu, reportsMenuBar, reportNodeTitle, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);

               /* if (treeNodes.isLeaf()) {


                    javax.swing.tree.DefaultMutableTreeNode parentNode = (javax.swing.tree.DefaultMutableTreeNode)siblingNode.getParent();

                    //                   System.out.println("Parent : ["+parentNode.getUserObject()+"]");// and Sibling : ["+siblingNode.getUserObject()+"]");

                    siblingNode.removeFromParent();
                    //                    parentNode.remove(siblingNode);

                }

                */
                // tabString = tabString + "\b";

            }

            // System.out.println("Element Closing");
        }

        //    parentNode = siblingNode;
       /* treeNodes = (javax.swing.tree.DefaultMutableTreeNode)treeNodes.getParent();

        if (treeNodes != null) {

            System.out.println(treeNodes.getUserObject().toString());

        }
        */



    }


    public void expandCustomTree(javax.swing.JTree jCustomTree, int toptreeRowCount){
        
        int rowCount = 0;
        
        if (toptreeRowCount < 1) {
            
            rowCount = reportsRowCount;
            
        } else {
            
            rowCount = toptreeRowCount;
            
        }
        // int treeRowCount = jCustomTree.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            
            System.out.println("Expanding tree row no ["+i+"]");
            
            jCustomTree.expandRow(i);
            
        }
        
    }
    
/*    public void registerActionListener(javax.swing.AbstractButton actionComponent, javax.swing.JMenuBar targetMenuBar) {
 
   //     targetMenuBar1 = targetMenuBar;
 
   //     actionComponent1 = actionComponent;
 
        actionComponent.addActionListener(new java.awt.event.ActionListener() {
 
            public void actionPerformed(java.awt.event.ActionEvent evt) {
 
 
 
                //componentActionPerformed(targetMenuBar, actionComponent.getText());
 
            }
 
        });
 
    }
 
   public void componentActionPerformed(javax.swing.JMenuBar targetMenuBar, java.lang.String targetAction) {
 
       this.getTargetMenuItem(targetMenuBar.getSubElements(), targetAction);
 
   }
 
/*    public static org.netbeans.lib.sql.pool.PooledConnectionSource getPooledConnectionSource() {
 
        org.netbeans.lib.sql.pool.PooledConnectionSource pooledConnectionSource1 = new org.netbeans.lib.sql.pool.PooledConnectionSource();
 
        pooledConnectionSource1.setDatabase("jdbc:postgresql://localhost:5432/medic");
 
        pooledConnectionSource1.setDriver("org.postgresql.Driver");
 
        pooledConnectionSource1.setPassword("02ac219a6b011019ea966757de3451d2399ce04dd82596d3ba4266a8845ab7f80861", true);
 
        pooledConnectionSource1.setUsername("postgres");
 
        return pooledConnectionSource1;
    }
    public static java.sql.Connection connect2DB() {
 
        java.sql.Connection connDBStock = null;
 
        try {
 
            java.lang.Class.forName("org.postgresql.Driver");
 
        } catch(java.lang.ClassNotFoundException cnf) {
 
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnf.getMessage());
 
        }
 
        try {
 
            connDBStock = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");
 
 
        } catch(java.sql.SQLException sqlExec) {
 
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
 
        }
 
        return connDBStock;
 
    }
 */
    public void  recreateNewMenuBars(javax.swing.JMenuBar targetMenuBar, javax.swing.JMenuBar payrollMenuBar, javax.swing.JMenuBar stockMenuBar, javax.swing.JComponent subComponents[], javax.swing.JComponent popupNodes, javax.swing.JComponent reportsMenuBar, java.lang.String reportNodeTitle, java.lang.String utilitiesNodeTitle, javax.swing.ButtonGroup targetButtonGroup, javax.swing.ButtonGroup reportsButtonGroup){
        
        final  javax.swing.JMenuBar jmenubar1 = targetMenuBar;
        // subNodes = new javax.swing.tree.DefaultMutableTreeNode[subElements.length];
        
        // parentNode = treeNodes;
        
        for(int i = 0; i < subComponents.length ; i++) {
            // System.out.println(subElements[i]);
            
            if (subComponents[i].getClass().getName() != "javax.swing.JPopupMenu") {
                
                if (subComponents[i].getClass().getName() != "javax.swing.JSeparator") {
                    
                    popupNodes.add(subComponents[i]);
                }
                
                // System.out.println("Element printing");
                
                javax.swing.JMenuItem abstractButton = (javax.swing.JMenuItem)subComponents[i];
                
                //   javax.swing.tree.DefaultMutableTreeNode parentTreeNode =
                
                //                   System.out.println(subElements[i]);
                //   System.out.println(i);
                
                if (subComponents[i].getClass().getName() == "javax.swing.JSeparator") {
                    
                    popupNodes.add(subComponents[i]);
                    
                }
                
                if (subComponents[i].getClass().getName() == "javax.swing.JMenu") {
                    
                    popupMenu = new javax.swing.JMenu(abstractButton.getText());
                    
                    if (popupNodes != null) {
                        
                        if (popupMenu.getText() != "Feel") {
                            
                            popupNodes.add(popupMenu);
                            
                        }
                        
                    }
                    
                    if (abstractButton.getText() == "System") {
                        
                        if (stockMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            stockMenuBar.add(popupMenu);
                            
                        }
                        
                    }
                    if (abstractButton.getText() == "System") {
                        
                        if (payrollMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            
                            payrollMenuBar.add(popupMenu);
                            
                        }
                    }
                    if (abstractButton.getText() == "System") {
                        
                        if (reportsMenuBar != null) {
                            
                            System.out.println("Doing system"+popupMenu.getName());
                            
                            popupMenu.setText("Control");
                            
                            //  popupReports.add(popupMenu, 4);
                            //  if (stockMenuBar != null && payrollMenuBar != null) {
                            
                            reportsMenuBar.add(popupMenu);
                            
                        }
                        
                    }
                    
                    if (abstractButton.getText() == "Reports") {
                        
                        if (reportsMenuBar != null) {
                            
                            popupMenu.setText(reportNodeTitle);
                            
                            reportsMenuBar.add(popupMenu);
                        }
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                        
                        if (reportsMenuBar != null) {
                            
                            popupMenu.setText(utilitiesNodeTitle);
                            
                            reportsMenuBar.add(popupMenu);
                        }
                    }
                    
                }
                if (subComponents[i].getClass().getName() == "javax.swing.JMenuItem") {
                    
                    popupMenuItem = new javax.swing.JMenuItem(abstractButton.getText());
                    
                    popupMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    //  registerActionListener(popupMenuItem, targetMenuBar);
                    
                    // popupMenuItem = abstractButton;
                    
                    
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (abstractButton.isVisible()) {
                        
                        popupNodes.add(popupMenuItem);
                     //  popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                    // }
                    
                    
                    // }
                    //  System.out.println("Node : "+ subNodes[i].toString());
                    
                    //  System.out.println(tabString + abstractButton.getText());
                    
                }
                
                if (subComponents[i].getClass().getName() == "javax.swing.JRadioButtonMenuItem") {
                    
                    popupRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem(abstractButton.getText());
                    
                    popupRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupRadioButtonMenuItem = (javax.swing.JRadioButtonMenuItem)abstractButton;
                    
                    popupNodes.add(popupRadioButtonMenuItem);
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupRadioButtonMenuItem);
                        
                    }
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
                
                //}
                
                if (subComponents[i].getClass().getName() == "javax.swing.JCheckBoxMenuItem") {
                    
                    popupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(abstractButton.getText());
                    
                    popupCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            System.out.println("Action Taking Place for action : ["+evt.getActionCommand()+"].");
                            
                            
                            
                            getTargetMenuItem(jmenubar1.getSubElements(), evt.getActionCommand());
                            
                            
                            
                            //componentActionPerformed(targetMenuBar, actionComponent.getText());
                            
                        }
                        
                    });
                    // popupCheckBoxMenuItem = (javax.swing.JCheckBoxMenuItem)abstractButton;
                    
                    popupNodes.add(popupCheckBoxMenuItem);
                    
                    if (targetButtonGroup != null) {
                        
                        targetButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    
                    if (reportsButtonGroup != null) {
                        
                        reportsButtonGroup.add(popupCheckBoxMenuItem);
                        
                    }
                    
                    // popupMenuItem.addActionListener(abstractButton.actionListener);
                    
                    
                    if (!abstractButton.isEnabled()) {
                        
                        popupMenuItem.setEnabled(false);
                        
                    }
                    
                   /* if (abstractButton.getText() == "Reports") {
                    
                        popupMenuItem.setText(reportNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    
                    if ( abstractButton.getText() == "Utility") {
                    
                        popupMenuItem.setText(utilitiesNodeTitle);
                    
                        popupReports.add(popupMenuItem);
                    
                    }
                    */
                }
                
                
                // }
                //  System.out.println("Node : "+ subNodes[i].toString());
                
                //  System.out.println(tabString + abstractButton.getText());
            }
            //}
            //System.out.println(abstractButton.getText());
            
            if (subComponents[i].getComponents().length > 0) {
                
                javax.swing.JComponent subComponentss[] = (javax.swing.JComponent[])subComponents[i].getComponents();
                //tabString = tabString + " ";
                
                //parentNode = siblingNode;
                
                // System.out.println("See this!");
                
                recreateNewMenuBars(targetMenuBar, payrollMenuBar, stockMenuBar,  subComponentss, popupMenu, reportsMenuBar, reportNodeTitle, utilitiesNodeTitle, targetButtonGroup, reportsButtonGroup);
                
               /* if (treeNodes.isLeaf()) {
                
                
                    javax.swing.tree.DefaultMutableTreeNode parentNode = (javax.swing.tree.DefaultMutableTreeNode)siblingNode.getParent();
                
                    //                   System.out.println("Parent : ["+parentNode.getUserObject()+"]");// and Sibling : ["+siblingNode.getUserObject()+"]");
                
                    siblingNode.removeFromParent();
                    //                    parentNode.remove(siblingNode);
                
                }
                
                */
                // tabString = tabString + "\b";
                
            }
            
            // System.out.println("Element Closing");
        }
        
        //    parentNode = siblingNode;
       /* treeNodes = (javax.swing.tree.DefaultMutableTreeNode)treeNodes.getParent();
        
        if (treeNodes != null) {
        
            System.out.println(treeNodes.getUserObject().toString());
        
        }
        */
        
        
        
    }
}
