package com.afrisoftech.txtreports;

/*
 * SampleTxtReport.java
 *
 * Created on August 20, 2005, 4:39 PM
 
 
package biz.systempartners.txtreports;
 
/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class DebtorsReceiptsTxt implements java.lang.Runnable {

    java.io.RandomAccessFile txtReportFile = null;
    /**
     * Creates a new instance of SampleTxtReport
     */

    com.afrisoftech.lib.DBObject dbObject;

    java.lang.String MNo = null;

    java.lang.String Name = null;

    java.lang.String Amount = null;

    java.lang.String Receipt = null;

    java.lang.String Paymode = null;

    java.lang.String beginDate = null;

    java.lang.String endDate = null;

    java.lang.String CHQ = null;

    java.lang.String Payer = null;

    java.lang.String rHeader = null;

    String ks;

    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    java.io.File tempFile = null;

    boolean threadCheck = true;

    java.lang.Thread threadSample;

    java.lang.Process wait_for_Pdf2Show;

    java.lang.Runtime rt = null;
    private String txNo;
    private String telNo;

    public DebtorsReceiptsTxt(java.sql.Connection connDb, java.lang.String combox, java.lang.String payer, java.lang.String name, java.lang.String amount, java.lang.String receipt, java.lang.String paymode, java.lang.String cheque) {

        dbObject = new com.afrisoftech.lib.DBObject();

        CHQ = cheque;

        MNo = combox;

        Payer = payer;

        Name = name;

        Amount = amount;

        Receipt = receipt;

        connectDB = connDb;

        Paymode = paymode;

        //       java.lang.Process wait_for_Pdf2Show;
        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        com.afrisoftech.lib.DateLables dateLabels = new com.afrisoftech.lib.DateLables();

        try {

            tempFile = java.io.File.createTempFile("REP" + dateLabels.getDateLabel() + "_", ".txt");

            tempFile.deleteOnExit();

        } catch (java.io.IOException ioEx) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());

        }

        try {

            rt = java.lang.Runtime.getRuntime();
            //   }

            //  beginDate = inv1;
            //  endDate = inv2;
            //    try {
            txtReportFile = new java.io.RandomAccessFile(tempFile, "rw");

            threadSample = new java.lang.Thread(this, "Plain Text Report Writer");

            System.out.println("threadSample created");

            threadSample.start();

            System.out.println("threadSample fired");

            //  writeReport(txtReportFile);
        } catch (java.io.FileNotFoundException fnf) {

            fnf.printStackTrace();

        }

    }

    private void writeReport(java.io.RandomAccessFile txtRandomAccessFile) {

        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        // for (int j = 0; j < listofStaffNos.length; j++) {
        double osBalance = 0.00;

        System.setProperty("phrase.separator", "  ");

        System.setProperty("line.character", "-");

        System.setProperty("new.line.character", " \n ");

        int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};

        int horizontalAlignments21[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};

        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        int horizontalAlignments4[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("rcptlinesperpage")), java.lang.Integer.parseInt(System.getProperty("rcptcharactersperline")), txtReportFile);

        double floats[] = {50, 10, 20, 20};

        int colSizes[] = textReport.createTableHeader(4, floats);

        for (int i = 0; i < colSizes.length; i++) {
            System.out.println(colSizes[i]);
        }

        double floats2[] = {50, 50};

        int colSizes2[] = textReport.createTableHeader(2, floats2);

        for (int i = 0; i < colSizes2.length; i++) {
            // System.out.println(colSizes2[i]);
        }

        double floats21[] = {100};

        int colSizes21[] = textReport.createTableHeader(1, floats21);

        for (int i = 0; i < colSizes21.length; i++) {
            // System.out.println(colSizes2[i]);
        }

        double floats3[] = {65, 35};

        int colSizes3[] = textReport.createTableHeader(2, floats3);

        for (int i = 0; i < colSizes3.length; i++) {
            // System.out.println(colSizes2[i]);
        }

        double floats4[] = {50, 50};

        int colSizes4[] = textReport.createTableHeader(2, floats4);

        for (int i = 0; i < colSizes4.length; i++) {
            // System.out.println(colSizes2[i]);
        }

        double floats5[] = {65, 35};

        int colSizes5[] = textReport.createTableHeader(2, floats5);

        for (int i = 0; i < colSizes5.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        String compName = null;
        String Address = null;
        String Tel = null;
        String Fax = null;
        String Email = null;

        try {

            java.sql.Statement st41 = connectDB.createStatement();
            java.sql.ResultSet rset21 = st41.executeQuery("select print_header from receipt_pref");

            while (rset21.next()) {
                rHeader = rset21.getString(1);
            }

            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st4 = connectDB.createStatement();
            java.sql.ResultSet rset2 = st3.executeQuery("SELECT DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");

            while (rset2.next()) {
                compName = rset2.getObject(1).toString();
                Address = rset2.getObject(2).toString();
                Tel = rset2.getObject(3).toString();
                Fax = rset2.getObject(5).toString();
                Email = rset2.getObject(6).toString();
            }

        } catch (java.sql.SQLException SqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

        }

        biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
        // Object companyName[] = { ""+compName+""};

        biz.systempartners.txtreports.PlainTextTable table22 = new biz.systempartners.txtreports.PlainTextTable(1);
        if (rHeader.equalsIgnoreCase("True")) {
            table22.addCell(compName.toUpperCase());

            table22.addCell("Address : " + Address.toUpperCase());

            table22.addCell("Tel : " + Tel.toUpperCase());

            table22.addCell("Fax : " + Fax.toUpperCase());

            table22.addCell("Email : " + Email);
        } else {

            table22.addCell("  ");

            table22.addCell("  ");

            table22.addCell("  ");

            table22.addCell("  ");
        }
        // table22.addCell("Fax : "+Fax.toUpperCase());

        //  table22.addCell("Email : "+Email);
        //            biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
        Object companyName[] = {compName};

        double sizeArrayPercent[] = {100};

        int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);

        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};

        javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName, 1);

        for (int i = 0; i < companyName.length; i++) {
            headerCompany.setValueAt(companyName[i], 0, i);
        }
        //String ColumnModelTitle3[] = {"","","",""};

        String ColumnModelTitle4[] = {"   ", "   "};
        //  String ColumnModelTitle4[] = { "Receipt No", "RECEIPT", "Date" };

        //    javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,3);
        //    for (int i = 0; i < reportName.length; i++){
        //        headerTitle.setValueAt(reportName[i], 0, i);
        //    }
        String columnModel1[] = {"    ", "     "};
        //  String columnModel1[] = {"Rev.Code","Description", "Qty", "Price @", "Amt"};

        String columnModel[] = {"This", "That", "Then", "when"};

        String ColumnModelTitle[] = {""};

        String ColumnModelTitle2[] = {"", ""};

        String ColumnModelTitle3[] = {"", ""};

        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(ColumnModelTitle, 1);
        for (int i = 0; i < ColumnModelTitle.length; i++) {
            headerTableModel.setValueAt(ColumnModelTitle[i], 0, i);
        }

        Object reportName[] = {"RECEIPT"};

        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName, 1);

        for (int i = 0; i < reportName.length; i++) {
            headerTitle.setValueAt(reportName[i], 0, i);
        }

        int integers[] = colSizes;

        biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable table21 = new biz.systempartners.txtreports.PlainTextTable(1);

        biz.systempartners.txtreports.PlainTextTable table3 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable table23 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable table4 = new biz.systempartners.txtreports.PlainTextTable(2);

        biz.systempartners.txtreports.PlainTextTable tableF = new biz.systempartners.txtreports.PlainTextTable(2);

        String Cashpoint = null;

        String invNo = null;

        String payee = null;

        String admno = null;

        String mno = null;

        String Cashier = null;

        String mname = null;

        String dates = null;

        String cash_words = null;

        String simpleReportFooter = null;

        double credits = 0.00;

        try {

            java.sql.Statement st = connectDB.createStatement();
            java.sql.Statement st1 = connectDB.createStatement();
            java.sql.Statement st2 = connectDB.createStatement();
            java.sql.Statement st5 = connectDB.createStatement();
            java.sql.Statement st6 = connectDB.createStatement();
            java.sql.Statement st7 = connectDB.createStatement();
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st2x = connectDB.createStatement();

            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
            while (rset2x.next()) {
                ks = rset2x.getObject(1).toString();
            }
            java.sql.ResultSet rset3 = st3.executeQuery("select header,footer from ac_receipt_header");
            java.sql.ResultSet rset1 = st1.executeQuery("select CURRENT_TIMESTAMP(0)::date");
            java.sql.ResultSet rset5 = st5.executeQuery("select description,sum(debit)::numeric  from ac_cash_collection where receipt_no = '" + Receipt + "' group by description");
            java.sql.ResultSet rset6 = st6.executeQuery("select distinct user_name,cash_point, journal_no, account_no from ac_cash_collection where receipt_no = '" + Receipt + "'");

            if (rHeader.equalsIgnoreCase("FALSE")) {

                while (rset5.next()) {

                    //  table1.addCell("  ");
                    table1.addCell("               " + MNo);

                    // table1.addCell("  ");
                    // table1.addCell("  ");
                    // table1.addCell("  ");
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(2)));

                    osBalance = osBalance + rset5.getDouble(2);

                    System.out.println("This is the description " + rset5.getString(2));

                }

                while (rset1.next()) {
                    dates = dbObject.getDBObject(rset1.getObject(1), "-");
                }

                while (rset6.next()) {

                    Cashier = dbObject.getDBObject(rset6.getObject(1), "-");

                    Cashpoint = dbObject.getDBObject(rset6.getObject(2), "-");

                    txNo = dbObject.getDBObject(rset6.getObject(3), "-");

                    telNo = dbObject.getDBObject(rset6.getObject(4), "-");

                }

                table4.addCell("Receipt No : " + Receipt);

                // table4.addCell("                ");
                table4.addCell("                " + dates);

                table21.addCell("              " + Payer.toUpperCase());

                java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + osBalance + "'),'dollars','shillings')");

                while (rset_cash.next()) {

                    cash_words = dbObject.getDBObject(rset_cash.getString(1).toUpperCase(), "-");

                }

                table21.addCell("              " + cash_words);

                table21.addCell("              ");
                table21.addCell("              " + Name.toUpperCase());
                table21.addCell("              ");

                //  table3.addCell("");
                //  table3.addCell("");
                table3.addCell("                       Total");

                // table3.addCell("");
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));

                System.out.println("Checking Values [" + Paymode + "]" + "[" + CHQ + "]" + "[" + Cashpoint + "]" + "[" + Cashier + "]");
                simpleReportFooter = "               " + Paymode + " " + CHQ + "                                     " + Cashpoint + "\n" + "                                                                  " + Cashier.toUpperCase();

            } else {

                while (rset5.next()) {

                    //   table11.addCell("CODE ");
                    table11.addCell("DESCRIPTION");

                    // table11.addCell("  ");
                    //  table11.addCell("  ");
                    //  table11.addCell("   ");
                    table11.addCell("AMOUNT " + ks);

                    /// table1.addCell("  ");
                    table1.addCell("" + MNo);

                    // table1.addCell("  ");
                    //   table1.addCell("  ");
                    // table1.addCell("  ");
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(2)));

                    osBalance = osBalance + rset5.getDouble(2);

                    System.out.println("This is the description " + rset5.getString(1));

                }

                while (rset1.next()) {
                    dates = dbObject.getDBObject(rset1.getObject(1), "-");
                }

                while (rset6.next()) {

                    Cashier = dbObject.getDBObject(rset6.getObject(1), "-");

                    Cashpoint = dbObject.getDBObject(rset6.getObject(2), "-");

                    txNo = dbObject.getDBObject(rset6.getObject(3), "-");

                    telNo = dbObject.getDBObject(rset6.getObject(4), "-");

                }

                table4.addCell("Receipt : " + Receipt);

                // table4.addCell(" ");
                table4.addCell("Date : " + dates);

                table21.addCell("Received from " + Payer.toUpperCase());

                table21.addCell("A sum of " + ks);

                System.out.println("This is the OS balance : [" + osBalance + "]");

                //   java.sql.ResultSet rset_cash = st7.executeQuery("select initcap(replace(cash_words('"+osBalance+"'),'dollars','shillings'))");
                //  while(rset_cash.next()){
                //     cash_words = dbObject.getDBObject(rset_cash.getString(1), "-");
                //   }
                //    table21.addCell(cash_words);
                table21.addCell("In respect of :- " + Name.toUpperCase());

                //  table3.addCell("");
                /// table3.addCell("");
                table3.addCell("    Total");

                //table3.addCell("");
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));
                tableF.addCell("Pymt Mode: " + Paymode + " " + CHQ + "");

                tableF.addCell("Cash Point: " + Cashpoint);

                tableF.addCell("Cashier: " + Cashier.toUpperCase());

                if (Paymode.toLowerCase().contains("pesa")) {
                    tableF.addCell("Tx. No: " + txNo);

                    tableF.addCell("Tel. No: " + telNo);
                }

                tableF.addCell(" ");

                tableF.addCell(" ");
                tableF.addCell(" ");

                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");

                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");
                tableF.addCell(" ");

                System.out.println("Checking Values [" + Paymode + "]" + "[" + CHQ + "]" + "[" + Cashpoint + "]" + "[" + Cashier + "]");
                //  simpleReportFooter = "Payment Mode   "+Paymode+" " +CHQ+"   Cash Point       "+Cashpoint+ "\n"+ "    Cashier   "+  Cashier.toUpperCase();

            }

        } catch (java.sql.SQLException SqlExec) {

            SqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

        }

        if (rHeader.equalsIgnoreCase("FALSE")) {
            textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            // textReport.drawHorizontalLine(integers);
            textReport.addTable(table4, colSizes4, ColumnModelTitle4, horizontalAlignments4);

            // textReport.drawHorizontalLine(integers);
            textReport.addTable(table21, colSizes21, ColumnModelTitle, horizontalAlignments21);

            // textReport.drawHorizontalLine(integers);
            textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);

            // textReport.drawHorizontalLine(integers);
            textReport.addTable(table1, colSizes5, columnModel1, horizontalAlignments1);

            // textReport.drawHorizontalLine(integers);
            textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);

            //  textReport.drawHorizontalLine(integers);
            //  textReport.addTable(table23, colSizes2, ColumnModelTitle2, horizontalAlignments2);
            textReport.writeSimpleReportFooter(simpleReportFooter, false);
            //  textReport.drawHorizontalLine(integers);

        } else {
            textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table4, colSizes4, ColumnModelTitle4, horizontalAlignments4);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table21, colSizes21, ColumnModelTitle, horizontalAlignments21);

            textReport.drawHorizontalLine(integers);

            //textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
            // textReport.drawHorizontalLine(integers);
            textReport.addTable(table11, colSizes5, columnModel1, horizontalAlignments1);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table1, colSizes5, columnModel1, horizontalAlignments1);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);

            textReport.drawHorizontalLine(integers);
            textReport.addTable(tableF, colSizes4, ColumnModelTitle3, horizontalAlignments3);

            //  textReport.addTable(table23, colSizes2, ColumnModelTitle2, horizontalAlignments2);
            //           textReport.writeSimpleReportFooter(simpleReportFooter, false);
            //
        }

        try {
            textReport.getReportAccessFile().close();
        } catch (java.io.IOException ioEx) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        //   try{

        //       try {
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {

            /*    System.out.println(tempFile);
                 //   com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());
                 
                    wait_for_Pdf2Show = rt.exec("kwrite "+tempFile+"");
                 
                    wait_for_Pdf2Show.waitFor();*/
            com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());

        } else {
            //  com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());

            //  wait_for_Pdf2Show = rt.exec("wordpad "+tempFile);
            // print directly to printer in dos
            /*  wait_for_Pdf2Show = rt.exec("print "+tempFile);
                   
                    wait_for_Pdf2Show.waitFor();*/
            com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());

        }

        /*  } catch(java.lang.InterruptedException intrExec) {
          
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
          
            }
          
          
        } catch(java.io.IOException ioEx){
          
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
          
        }
         */
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            // this.generatePdf(MNo);
            writeReport(txtReportFile);

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(100);

                System.out.println("It's time for us threads to get back to work after the nap");

            } catch (java.lang.InterruptedException IntExec) {

                System.out.println(IntExec.getMessage());

            }

            threadCheck = false;

            System.out.println("We shall be lucky to get back to start in one piece");

        }

        if (!threadCheck) {

            Thread.currentThread().stop();

        }

    }

    /*  public java.lang.Object[] getListofStaffNos() {
   
        java.lang.Object[] listofStaffNos = null;
   
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
   
   
        try {
   
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT invoice_no FROM ac_debtors WHERE invoice_no  BETWEEN ? AND ? order by invoice_no");
   
            //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct patient_no FROM hp_patient_register WHERE last_visit  BETWEEN ? AND ? AND pay_mode = ? order by patient_no");
            pSet1.setString(1,beginDate.toString());
            pSet1.setString(2,endDate.toString());
   
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
   
            // java.sql.Statement stmt1 = connectDB.createStatement();
   
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' order by patient_no");
   
            while (rSet1.next()) {
   
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
   
            }
   
        }catch (java.sql.SQLException sqlExec) {
   
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
   
        }
   
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
     */
}
