/*
 * JTable.java
 *
 * Created on April 1, 2006, 4:37 PM
 */
package com.afrisoftech.dbadmin;

/**
 *
 * @author root
 */
public class JTable extends org.jdesktop.swing.JXTable implements java.lang.Runnable {//javax.swing.JTable {

    javax.swing.JPopupMenu tablePopupMenu;
    boolean exportTableBoolean = true;
    int tableColorA;
    int tableColorB;
    int tableColorHeader;
    java.util.Properties appProp;
    java.lang.Thread threadExportTable = null;
    com.afrisoftech.dbadmin.XMLExport xmlExport;
    com.afrisoftech.dbadmin.ExcelExport excelExport;
    com.afrisoftech.dbadmin.HTMLExport htmlExport;
    com.afrisoftech.dbadmin.PDFExport pdfExport;
    java.lang.String tableName = null;
    java.awt.Color colorA;
    java.awt.Color colorB;
    java.awt.Color colorHeader;
    //        org.jdesktop.swing.JXTable jxTable = null;

    /**
     * Creates a new instance of JTable
     */
    public JTable() {
        super();
        //                jxTable = new org.jdesktop.swing.JXTable(3,10);
        this.setRowSelectionAllowed(true);
        this.setColumnSelectionAllowed(true);
        this.setCellSelectionEnabled(true);
    //    this.setSortable(true);
        this.createPopupMenu();

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        appProp = new java.util.Properties();
        getProperties();

        if (colorA == null) {

            colorA = new java.awt.Color(tableColorA);
            // colorA = java.awt.Color[r=234,g=244,b=244];
        }

        if (colorB == null) {

            colorB = new java.awt.Color(tableColorB);

        }

        if (colorHeader == null) {

            colorHeader = new java.awt.Color(tableColorHeader);

        }

        setHighLighter();


        this.setRowHeight(20);

        this.getTableHeader().setBackground(colorHeader);

        this.getTableHeader().setForeground(java.awt.Color.BLACK);

        tableName = "MyTable";
    }

    public Class getColumnClass(int c) {

        java.lang.Object cellValue = new java.lang.Object();

        if (getValueAt(0, c) != null) {

            //    cellValue = getValueAt(0, c);
            return getValueAt(0, c).getClass();

        } else {

            return cellValue.getClass();

        }
    }

    public void run() {

        if (exportTableBoolean == false) {

            exportTableBoolean = true;

        }

        while (exportTableBoolean) {

            System.out.println("Export process started for thread [" + Thread.currentThread().getName() + "]");

            if (Thread.currentThread().getName().matches("Export2Excel")) {

                System.out.println("Exporting Export2Excel");

                excelExport = new com.afrisoftech.dbadmin.ExcelExport(this.getExportTable(), tableName);

            } else if (Thread.currentThread().getName().matches("Export2Xml")) {

                xmlExport = new com.afrisoftech.dbadmin.XMLExport(this.getExportTable(), tableName);

            } else if (Thread.currentThread().getName().matches("Export2HTML")) {

                htmlExport = new com.afrisoftech.dbadmin.HTMLExport(this.getExportTable(), tableName);

            } else if (Thread.currentThread().getName().matches("Export2PDF")) {

                pdfExport = new com.afrisoftech.dbadmin.PDFExport(this.getExportTable(), tableName);

            } else if (Thread.currentThread().getName().matches("PrintTable")) {

                printTable();
            }

            try {

                Thread.currentThread().sleep(100);

            } catch (java.lang.InterruptedException IntExec) {
                System.out.println(IntExec.getMessage());
            }

            exportTableBoolean = false;

        }


    }

    public javax.swing.JTable getExportTable() {
        /*
         javax.swing.JTable table2Export = null;
 
         if (exportTable != null) {
 
         table2Export = exportTable;
 
         }
         */
        return this;

    }

    private void createPopupMenu() {

        tablePopupMenu = new javax.swing.JPopupMenu("Export Table");

        javax.swing.JMenuItem xmlMenuItem = new javax.swing.JMenuItem("XML Export");
        xmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export2XmlActionPerformed(evt);
            }
        });
        tablePopupMenu.add(xmlMenuItem);

        javax.swing.JMenuItem excelMenuItem = new javax.swing.JMenuItem("Excel Export");
        excelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export2ExcelActionPerformed(evt);
            }
        });
        tablePopupMenu.add(excelMenuItem);

        javax.swing.JMenuItem htmlMenuItem = new javax.swing.JMenuItem("HTML Export");
        htmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export2HtmlActionPerformed(evt);
            }
        });
        tablePopupMenu.add(htmlMenuItem);

        javax.swing.JMenuItem pdfMenuItem = new javax.swing.JMenuItem("PDF Export");
        pdfMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export2PdfActionPerformed(evt);
            }
        });
        tablePopupMenu.add(pdfMenuItem);

        javax.swing.JMenuItem printMenuItem = new javax.swing.JMenuItem("Print table ...");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        tablePopupMenu.add(new javax.swing.JSeparator());
        tablePopupMenu.add(printMenuItem);
        //}
        javax.swing.JMenu prefMenu = new javax.swing.JMenu("Set Preferences");

        javax.swing.JMenuItem prefMenuItemColorA = new javax.swing.JMenuItem("Table Highlighter Color A");
        prefMenuItemColorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefColorAActionPerformed(evt);
            }
        });
        //tablePopupMenu.add(new javax.swing.JSeparator());
        prefMenu.add(prefMenuItemColorA);

        javax.swing.JMenuItem prefMenuItemColorB = new javax.swing.JMenuItem("Table Highlighter Color B");
        prefMenuItemColorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefColorBActionPerformed(evt);
            }
        });
        //tablePopupMenu.add(new javax.swing.JSeparator());
        prefMenu.add(prefMenuItemColorB);

        javax.swing.JMenuItem prefMenuItemColorHeader = new javax.swing.JMenuItem("Table Header Background");
        prefMenuItemColorHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefColorHeaderActionPerformed(evt);
            }
        });
        //tablePopupMenu.add(new javax.swing.JSeparator());
        prefMenu.add(prefMenuItemColorHeader);

        tablePopupMenu.add(prefMenu);

    }

    private void prefColorHeaderActionPerformed(java.awt.event.ActionEvent evt) {

        // threadExportTable = new java.lang.Thread(this, "PrintTable");

        // threadExportTable.start();
        setPreferencesColorHeader();

        // Add your handling code here:
    }

    private void prefColorAActionPerformed(java.awt.event.ActionEvent evt) {

        // threadExportTable = new java.lang.Thread(this, "PrintTable");

        // threadExportTable.start();
        setPreferencesColorA();

        // Add your handling code here:
    }

    private void prefColorBActionPerformed(java.awt.event.ActionEvent evt) {

        // threadExportTable = new java.lang.Thread(this, "PrintTable");

        // threadExportTable.start();
        setPreferencesColorB();

        // Add your handling code here:
    }

    private void printActionPerformed(java.awt.event.ActionEvent evt) {

        threadExportTable = new java.lang.Thread(this, "PrintTable");

        threadExportTable.start();

        // Add your handling code here:
    }

    private void export2XmlActionPerformed(java.awt.event.ActionEvent evt) {

        threadExportTable = new java.lang.Thread(this, "Export2Xml");

        threadExportTable.start();

        // Add your handling code here:
    }

    private void export2HtmlActionPerformed(java.awt.event.ActionEvent evt) {

        threadExportTable = new java.lang.Thread(this, "Export2HTML");

        threadExportTable.start();

        // Add your handling code here:
    }

    private void export2ExcelActionPerformed(java.awt.event.ActionEvent evt) {

        threadExportTable = new java.lang.Thread(this, "Export2Excel");

        threadExportTable.start();

        // Add your handling code here:
    }

    private void export2PdfActionPerformed(java.awt.event.ActionEvent evt) {

        threadExportTable = new java.lang.Thread(this, "Export2PDF");

        threadExportTable.start();

        // Add your handling code here:
    }

    private void tableMousePressed(java.awt.event.MouseEvent evt) {

        if (evt.getModifiers() == java.awt.event.MouseEvent.BUTTON3_MASK) {

            tablePopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

        }
    }

    public void printTable() {
        try {
            this.print(javax.swing.JTable.PrintMode.FIT_WIDTH);
        } catch (java.awt.print.PrinterException prEx) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), prEx.getMessage(), "ERROR: Printing problem!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setPreferencesColorA() {
        //   try{
        java.awt.Color prefColor = javax.swing.JColorChooser.showDialog(this, "Select Prefered Colour", java.awt.Color.CYAN);
//        System.out.println("You have selected [{"+prefColor.getRed()+","+prefColor.getGreen()+","+prefColor.getBlue()+"}]");
        System.out.println("RGB Index : [" + prefColor.getRGB() + "]");
        colorA = prefColor;
        setHighLighter();
        appProp.setProperty("tableColorA", java.lang.String.valueOf(prefColor.getRGB()));
        System.setProperty("tableColorA", java.lang.String.valueOf(prefColor.getRGB()));

//        com.afrisoftech.hospital.HospitalMain.storeProperties();
        //   } catch(java.awt.print.PrinterException prEx){
        //       javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), prEx.getMessage(), "ERROR: Printing problem!", javax.swing.JOptionPane.ERROR_MESSAGE);
        //   }
    }

    private void setPreferencesColorHeader() {
        //   try{
        java.awt.Color prefColor = javax.swing.JColorChooser.showDialog(this, "Select Prefered Colour", java.awt.Color.CYAN);
        colorHeader = prefColor;
        System.out.println("You have selected [" + prefColor.toString() + "]");
        setHighLighter();
        appProp.setProperty("tableColorHeader", java.lang.String.valueOf(prefColor.getRGB()));
        System.setProperty("tableColorHeader", java.lang.String.valueOf(prefColor.getRGB()));
        storeProperties();
        //   } catch(java.awt.print.PrinterException prEx){
        //       javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), prEx.getMessage(), "ERROR: Printing problem!", javax.swing.JOptionPane.ERROR_MESSAGE);
        //   }
    }

    private void setPreferencesColorB() {
        //   try{
        java.awt.Color prefColor = javax.swing.JColorChooser.showDialog(this, "Select Prefered Colour", java.awt.Color.CYAN);
        colorB = prefColor;
        System.out.println("You have selected [" + prefColor.toString() + "]");
        setHighLighter();
        appProp.setProperty("tableColorB", java.lang.String.valueOf(prefColor.getRGB()));
        System.setProperty("tableColorB", java.lang.String.valueOf(prefColor.getRGB()));
        storeProperties();
        //   } catch(java.awt.print.PrinterException prEx){
        //       javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), prEx.getMessage(), "ERROR: Printing problem!", javax.swing.JOptionPane.ERROR_MESSAGE);
        //   }
    }

    private void setHighLighter() {
        this.getTableHeader().setBackground(colorHeader);
        org.jdesktop.swing.decorator.Highlighter[] highlighters = new org.jdesktop.swing.decorator.Highlighter[]{
            new org.jdesktop.swing.decorator.AlternateRowHighlighter(colorA, colorB, null),
            //            new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.pink, java.awt.Color.pink, "[*].*", 0, 2,0)
            // new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.cyan, java.awt.Color.RED,".*",1,2, 0),*/
            new org.jdesktop.swing.decorator.HierarchicalColumnHighlighter()
        };
        org.jdesktop.swing.decorator.PatternHighlighter highlighter = new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.cyan, java.awt.Color.RED, ".*", 1, 2, 0);

        org.jdesktop.swing.decorator.HighlighterPipeline highlighterPipeline = new org.jdesktop.swing.decorator.HighlighterPipeline(highlighters);

        this.setHighlighters(highlighterPipeline);

        this.repaint();

    }

    public void getProperties() {
        java.lang.String myAppFileUrl = null;

        myAppFileUrl = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                + "hosprop.properties";

        try {
            //catch java.lang.ClassNotFoundException(this.show(This is not true));
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);



            //       java.io.FileOutputStream propOutFile = new java.io.FileOutputStream("myapp.properties");



            try {
                System.out.println("Properties file : " + myAppFileUrl);

                appProp.load(propInFile);

                tableColorA = java.lang.Integer.parseInt(System.getProperty("tableColorA"));
                tableColorB = java.lang.Integer.parseInt(System.getProperty("tableColorB"));
                //System.out.println("Database Server IP : "+dbServerIp);
                tableColorHeader = java.lang.Integer.parseInt(System.getProperty("tableColorHeader"));


                propInFile.close();

                //  return dbServerIp;


            } catch (java.io.IOException ioExec) {

                System.out.println(ioExec.getMessage());

            }

            // return dbServerIp;

        } catch (java.lang.Exception exc) {

            System.out.println(exc.getMessage());

            //    javax.swing.JOptionPane.showMessageDialog(this, "Properties file not found!");

        }
    }

    private void storeProperties() {
        java.lang.String myAppFileUrl = null;

        myAppFileUrl = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                + "hosprop.properties";

        try {
            //catch java.lang.ClassNotFoundException(this.show(This is not true));
            java.io.FileOutputStream propOutFile = new java.io.FileOutputStream(myAppFileUrl);



            //       java.io.FileOutputStream propOutFile = new java.io.FileOutputStream("myapp.properties");



            try {
                System.out.println("Properties file : " + myAppFileUrl);

                //appProp.load(propOutFile);

                tableColorA = java.lang.Integer.parseInt(appProp.getProperty("tableColorA"));
                tableColorB = java.lang.Integer.parseInt(appProp.getProperty("tableColorB"));
                //System.out.println("Database Server IP : "+dbServerIp);

                appProp.store(propOutFile, "My properties file");

                propOutFile.close();

                //  return dbServerIp;


            } catch (java.io.IOException ioExec) {

                System.out.println(ioExec.getMessage());

            }

            // return dbServerIp;

        } catch (java.lang.Exception exc) {

            System.out.println(exc.getMessage());

            //    javax.swing.JOptionPane.showMessageDialog(this, "Properties file not found!");

        }
    }

    public void setHighlighterPipeline(javax.swing.JTable targetTable, org.jdesktop.swing.decorator.PatternHighlighter patterHighlighterArray[]) {

        tableColorA = java.lang.Integer.parseInt(System.getProperty("tableColorA"));

        tableColorB = java.lang.Integer.parseInt(System.getProperty("tableColorB"));
        //System.out.println("Database Server IP : "+dbServerIp);
        tableColorHeader = java.lang.Integer.parseInt(System.getProperty("tableColorHeader"));

        if (colorA == null) {

            colorA = new java.awt.Color(tableColorA);
            // colorA = java.awt.Color[r=234,g=244,b=244];
        }

        if (colorB == null) {

            colorB = new java.awt.Color(tableColorB);

        }

        if (colorHeader == null) {

            colorHeader = new java.awt.Color(tableColorHeader);

        }
        java.util.Vector<org.jdesktop.swing.decorator.Highlighter> highlightersVector = new java.util.Vector(1, 1);
        highlightersVector.add(new org.jdesktop.swing.decorator.AlternateRowHighlighter(colorA, colorB, null));
        highlightersVector.add(new org.jdesktop.swing.decorator.HierarchicalColumnHighlighter());
        for (int i = 0; i < patterHighlighterArray.length; i++) {
            highlightersVector.add(patterHighlighterArray[i]);
        }
       
        targetTable.getTableHeader().setBackground(colorHeader);
       
        org.jdesktop.swing.decorator.Highlighter[] highlighters1 = new org.jdesktop.swing.decorator.Highlighter[2+patterHighlighterArray.length];
       
        org.jdesktop.swing.decorator.Highlighter[] highlighters = highlightersVector.toArray(highlighters1);

        org.jdesktop.swing.decorator.HighlighterPipeline highlighterPipeline = new org.jdesktop.swing.decorator.HighlighterPipeline(highlighters);

        org.jdesktop.swing.JXTable highlightedTable = (org.jdesktop.swing.JXTable) targetTable;

        highlightedTable.setHighlighters(highlighterPipeline);

        highlightedTable.repaint();

    }
}
