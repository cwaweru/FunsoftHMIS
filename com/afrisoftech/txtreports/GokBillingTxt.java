package com.afrisoftech.txtreports;

import com.afrisoftech.hospinventory.RcvBranchRequisintfr;

/*
 * SampleTxtReport.java
 *
 * Created on August 20, 2005, 4:39 PM


 package biz.systempartners.txtreports;

 /**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class GokBillingTxt implements java.lang.Runnable {

    /**
     * @return the connectDB
     */
    public static java.sql.Connection getConnectDB() {
        return connectDB;
    }

    /**
     * @param aConnectDB the connectDB to set
     */
    public static void setConnectDB(java.sql.Connection aConnectDB) {
        connectDB = aConnectDB;
    }
    private java.io.RandomAccessFile txtReportFile = null;
    private com.afrisoftech.lib.DBObject dbObject;
    private java.lang.String MNo = "";
    private java.lang.String Name = "";
    private java.lang.String Cashier = null;
    private java.lang.String Amount = null;
    private java.lang.String Receipt = null;
    private java.lang.String Cash = null;
    private java.lang.String Refund = null;
    private java.lang.String Paymode = null;
    private java.lang.String beginDate = null;
    private java.lang.String endDate = null;
    private double waiver = 0.00;
    private double total = 0.00;
    private String ks;
    private java.lang.String rHeader = null;
    private static java.sql.Connection connectDB = null;
    private java.lang.String dbUserName = null;
    private org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    private java.io.File tempFile = null;
    private boolean threadCheck = true;
    private java.lang.Thread threadSample;
    private java.lang.Process wait_for_Pdf2Show;
    private java.lang.Runtime rt = null;
    private String shiftNumber;
    private String unitNumber;

    public GokBillingTxt(java.sql.Connection connDb, java.lang.String combox, java.lang.String amount, java.lang.String transNo, java.lang.String paymode) {

        dbObject = new com.afrisoftech.lib.DBObject();

        MNo = combox;

        Amount = amount;

        Receipt = transNo;

        connectDB = connDb;

        Paymode = paymode;

        String ks;
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

            txtReportFile = new java.io.RandomAccessFile(getTempFile(), "rw");

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
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};

        int horizontalAlignments21[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};

        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        int horizontalAlignmentsFooter[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};

        int horizontalAlignments4[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("rcptlinesperpage")), java.lang.Integer.parseInt(System.getProperty("rcptcharactersperline")), getTxtReportFile());

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

        double floats3[] = {70, 30};

        int colSizes3[] = textReport.createTableHeader(2, floats3);

        for (int i = 0; i < colSizes3.length; i++) {
            // System.out.println(colSizes2[i]);
        }


        double floats4[] = {50, 50};

        int colSizes4[] = textReport.createTableHeader(2, floats4);

        for (int i = 0; i < colSizes4.length; i++) {
            // System.out.println(colSizes2[i]);
        }

        double floats5[] = {70, 30};
        double floatsFooter[] = {100};
        int colSizeFooter[] = textReport.createTableHeader(1, floatsFooter);

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

            java.sql.Statement st41 = getConnectDB().createStatement();
            java.sql.ResultSet rset21 = st41.executeQuery("select print_header from receipt_pref");

            while (rset21.next()) {
                setRHeader(rset21.getString(1));
            }

            java.sql.Statement st3 = getConnectDB().createStatement();
            java.sql.Statement st4 = getConnectDB().createStatement();
            java.sql.Statement st2x = getConnectDB().createStatement();

            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
            while (rset2x.next()) {
                setKs(rset2x.getObject(1).toString());
            }
            java.sql.ResultSet rset2 = st3.executeQuery("SELECT DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");

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
        if (getRHeader().equalsIgnoreCase("True")) {
            table22.addCell(compName.toUpperCase());

            table22.addCell("P.O. BOX : " + Address.toUpperCase());

            table22.addCell("Tel : " + Tel.toUpperCase());

            // table22.addCell("Fax : "+Fax.toUpperCase());

            //  table22.addCell("Email : "+Email);


        } else {


            table22.addCell("  ");

            table22.addCell("  ");

            table22.addCell("  ");

            table22.addCell("  ");
        }
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


        // String ColumnModelTitle4[] = { "    ", "   ", "   " };
        String ColumnModelTitle4[] = {"BILL No.", "Date"};
        Object reportName[] = {"BILLED ITEMS, THIS IS NOT A RECEIPT!"};


        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName, 1);

        for (int i = 0; i < reportName.length; i++) {
            headerTitle.setValueAt(reportName[i], 0, i);
        }


        String columnModel1[] = {"    ", "     "};
        //  String columnModel1[] = {"Rev.Code","Description", "Qty", "Price @", "Amt"};


        String columnModel[] = {"This", "That", "Then", "when"};

        String ColumnModelTitle[] = {""};

        String ColumnModelTitle2[] = {"", ""};

        String ColumnModelTitle3[] = {"", ""};

        String ColumnModelTitleFooter[] = {""};

        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(ColumnModelTitle, 1);
        for (int i = 0; i < ColumnModelTitle.length; i++) {
            headerTableModel.setValueAt(ColumnModelTitle[i], 0, i);
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

        biz.systempartners.txtreports.PlainTextTable tableFooter = new biz.systempartners.txtreports.PlainTextTable(1);


        String Cashpoint = null;

        String invNo = null;

        String payee = null;

        String admno = null;

        String mno = null;

        String Cashier = null;

        String mname = null;

        String pNumber = null;

        String dates = null;

        String simpleReportFooter = null;

        String cash_words = null;

        double credits = 0.00;
        double balance = 0.00;

        String exemptionNumber = "-";



        try {

            java.sql.Statement st = getConnectDB().createStatement();
            java.sql.Statement st1 = getConnectDB().createStatement();
            java.sql.Statement st2 = getConnectDB().createStatement();
            java.sql.Statement st5 = getConnectDB().createStatement();
            java.sql.Statement st51 = getConnectDB().createStatement();
            java.sql.Statement st6 = getConnectDB().createStatement();
            java.sql.Statement st61 = getConnectDB().createStatement();

            java.sql.Statement st7 = getConnectDB().createStatement();
            java.sql.Statement st3 = getConnectDB().createStatement();
            java.sql.PreparedStatement stFooter = getConnectDB().prepareStatement("SELECT footer FROM ac_receipt_header");
            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT header,footer from ac_receipt_header");
            java.sql.ResultSet rset1 = st1.executeQuery("SELECT date_part('day', "
                    + "billing_time)||'-'||date_part('month', billing_time)||'-'||date_part('year', billing_time)||'@ '||date_part('hour', billing_time)||':'||date_part('minute', billing_time) AS receipt_time "
                    + "FROM hp_patient_card WHERE reference = '" + getReceipt() + "'");
            java.sql.ResultSet rset5 = st5.executeQuery("SELECT initcap(service),sum(dosage),sum(debit), round(sum(debit)/sum(dosage)) from hp_patient_card where reference = '" + getReceipt() + "' and transaction_type = 'Billing' group by service");
            java.sql.ResultSet rset6 = st6.executeQuery("SELECT distinct user_name, '' as cash_point, '' as journal_no, patient_no from hp_patient_card where reference = '" + getReceipt() + "'");
//            java.sql.ResultSet rset51 = st51.executeQuery("SELECT sum(credit) from ac_cash_collection where receipt_no = '" + getReceipt() + "' and credit > 0 AND (transaction_type ILIKE 'Waive%' OR transaction_type ILIKE 'Exempti%')");
//            java.sql.ResultSet rset6d = st2.executeQuery("SELECT distinct journal_no from ac_cash_collection where receipt_no = '" + getReceipt() + "' AND credit > 0");

            java.sql.ResultSet rsetFooter = stFooter.executeQuery();

            if (getRHeader().equalsIgnoreCase("True")) {

                table11.addCell("DESCRIPTION  QTY");

                table11.addCell("AMT(" + getKs() + ")");


                while (rset5.next()) {

                    table1.addCell(getDbObject().getDBObject(rset5.getObject(1) + " " + rset5.getObject(2), "-"));

                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(3)));

                    setTotal(getTotal() + rset5.getDouble(3));

                    System.out.println("This is the description " + rset5.getString(1));

                }

                while (rset6.next()) {
                    System.out.println("Cashier = " + Cashier);
                    System.out.println("Cashpoint " + Cashpoint);
                    Cashier = getDbObject().getDBObject(rset6.getObject(1).toString().toUpperCase(), "-");

                    Cashpoint = getDbObject().getDBObject(rset6.getObject(2), "-");


                    pNumber = getDbObject().getDBObject(rset6.getObject(4), "-");

                }

//                while (rset6d.next()) {
//                    
//                    exemptionNumber = getDbObject().getDBObject(rset6d.getObject(1), "-");
//
//                }
                java.sql.ResultSet rset61 = st61.executeQuery("SELECT sum(debit-credit) FROM hp_patient_card WHERE patient_no = '" + pNumber + "' AND patient_no !=''");
                while (rset61.next()) {

                    balance = rset61.getDouble(1);
                    if (balance < 0) {
                        balance = 0;
                    } else {
                        balance = balance;
                    }

                }
 
                while (rset1.next()) {
                    dates = getDbObject().getDBObject(rset1.getObject(1), "-");
                }




                table4.addCell("Bill No: " + getReceipt());

                //  table4.addCell(" ");

                table4.addCell("Date: " + dates);



                table21.addCell("Client No: " + pNumber);

                table21.addCell("Unit Number : " + new com.afrisoftech.lib.DBObject().getDBObject(unitNumber, ""));

                table21.addCell("Received from: " + getMNo().toUpperCase());

                java.sql.ResultSet rset_cash = st7.executeQuery("SELECT DISTINCT initcap(replace(cash_words('" + getTotal() + "'),'dollars','" + getKs() + "'))");



                while (rset_cash.next()) {

                    cash_words = getDbObject().getDBObject(rset_cash.getString(1), "-");

                }

                table3.addCell("Bill Amount");

                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(getDbObject().getDBObject(java.lang.String.valueOf(getTotal()), "0.00")));


                tableF.addCell("Pymt Mode: " + getPaymode());

                tableF.addCell("Cash Pnt: " + Cashpoint);

                tableF.addCell("Cashier: " + Cashier);

              //  tableF.addCell("Shift No: " + shiftNumber);


                tableF.addCell(" ");
                tableF.addCell(" ");


            } else {
                while (rset5.next()) {

                    //table1.addCell("  ");


                    table1.addCell(getDbObject().getDBObject("              " + rset5.getObject(1), "-"));


                    // table1.addCell("  ");

                    // table1.addCell("   ");

                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(3)));

                    osBalance = osBalance + rset5.getDouble(3);

                    System.out.println("This is the description " + rset5.getString(1));

                }

                while (rset1.next()) {
                    dates = getDbObject().getDBObject(rset1.getObject(1), "-");
                }


                while (rset6.next()) {
                    System.out.println("Operator = " + Cashier);
                    System.out.println("Cashpoint " + Cashpoint);
                    Cashier = getDbObject().getDBObject(rset6.getObject(1), "-");

                    Cashpoint = getDbObject().getDBObject(rset6.getObject(2), "-");

                    //  exemptionNumber = getDbObject().getDBObject(rset6.getObject(3), "-");

                }

//                while (rset6d.next()) {
//                    exemptionNumber = getDbObject().getDBObject(rset6d.getObject(1), "-");
//
//                }

                table4.addCell("          " + getReceipt());

                //  table4.addCell();

                table4.addCell("          " + dates);

                table21.addCell("              " + getName());

                java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + getTotal() + "'),'dollars','" + getKs() + "')");



                while (rset_cash.next()) {

                    cash_words = getDbObject().getDBObject(rset_cash.getString(1).toUpperCase(), "-");

                }

                //  table21.addCell(" - ");

                table21.addCell("              " + cash_words);


                table21.addCell("  ");

                table21.addCell("              " + getMNo() + "  " + getName().toUpperCase());

                // table3.addCell("");

                // table3.addCell("");

                table3.addCell("                      Total");

                // table3.addCell("");

                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));




                simpleReportFooter = "               " + getPaymode() + "                                      " + Cashpoint + "\n" + "                                                                          " + Cashier.toUpperCase();
                //  simpleReportFooter = "Payment Mode: "+Paymode+"    Cash Point:    "+Cashpoint+ "\n"+ "   Cashier: "+  Cashier.toUpperCase();

            }
            java.sql.PreparedStatement pstmtServiceCharter = connectDB.prepareStatement("SELECT service_charter FROM pb_patient_names");
            // pstmtCharter.setString(1, getReceipt());
            java.sql.ResultSet rsetServiceCharter = pstmtServiceCharter.executeQuery();
            Boolean serviceCharter = false;
            while (rsetServiceCharter.next()) {
                serviceCharter = rsetServiceCharter.getBoolean("service_charter");
            }
            if (serviceCharter) {
                //  String nextCareStation = "Casualty";
                //  int waitingTime = 5;
                java.sql.PreparedStatement pstmtCharter = connectDB.prepareStatement("SELECT DISTINCT activity_code FROM ac_cash_collection WHERE receipt_no = ?");
                pstmtCharter.setString(1, getReceipt());
                java.sql.ResultSet rsetCharter = pstmtCharter.executeQuery();
                while (rsetCharter.next()) {
                    java.sql.PreparedStatement pstmtCharterDept = connectDB.prepareStatement("SELECT DISTINCT main_service, waiting_time FROM pb_operating_parameters WHERE  gl_account = ?");
                    pstmtCharterDept.setString(1, rsetCharter.getString(1));
                    java.sql.ResultSet rsetCharterDept = pstmtCharterDept.executeQuery();
                    while (rsetCharterDept.next()) {
                        tableFooter.addCell(new String("Where to go next (Enda hapa) : [" + dbObject.getDBObject(rsetCharterDept.getString(1), "-").toUpperCase() + "]"));
                        tableFooter.addCell(new String("Expected waiting time (Muda wa kungonja): [" + com.afrisoftech.lib.CurrencyFormatter.parseFormattedString(rsetCharterDept.getString(2)) + "] Minutes"));
                    }
                }
            }
            tableFooter.addCell(" ");
            while (rsetFooter.next()) {
                tableFooter.addCell(rsetFooter.getString(1));
            }
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");
            tableFooter.addCell(" ");

        } catch (java.sql.SQLException SqlExec) {
            SqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

        }
        if (getRHeader().equalsIgnoreCase("True")) {
            textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            //   textReport.drawHorizontalLine(integers);

            textReport.addTable(table4, colSizes4, ColumnModelTitle4, horizontalAlignments4);

            //    textReport.drawHorizontalLine(integers);

            textReport.addTable(table21, colSizes21, ColumnModelTitle, horizontalAlignments21);

            //     textReport.drawHorizontalLine(integers);

            //     textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
            textReport.addTable(table11, colSizes5, columnModel1, horizontalAlignments1);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table1, colSizes5, columnModel1, horizontalAlignments1);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);

            textReport.drawHorizontalLine(integers);
            textReport.addTable(tableF, colSizes4, ColumnModelTitle3, horizontalAlignments3);

            textReport.addTable(tableFooter, colSizeFooter, ColumnModelTitleFooter, horizontalAlignmentsFooter);

            // textReport.addTable(table23, colSizes2, ColumnModelTtaitle2, horizontalAlignments2);
            //  textReport.writeSimpleReportFooter(simpleReportFooter, false);
            //  textReport.drawHorizontalLine(integers);

        } else {


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

        }

        try {
            textReport.getReportAccessFile().close();
        } catch (java.io.IOException ioEx) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        //  try{

        //      try {

        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {

            System.out.println(getTempFile());
            com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(getTempFile().getPath());
            //   wait_for_Pdf2Show = rt.exec("kwrite "+tempFile+"");

            //   wait_for_Pdf2Show.waitFor();

        } else {
            com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(getTempFile().getPath());

            // // wait_for_Pdf2Show = rt.exec("wordpad "+tempFile);
            // print directly to printer in dos
            // wait_for_Pdf2Show = rt.exec("print "+tempFile);

            //  wait_for_Pdf2Show.waitFor();

        }
        /*
         } catch(java.lang.InterruptedException intrExec) {

         javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());

         }


         } catch(java.io.IOException ioEx){

         javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());

         }
         */

    }

    public void run() {

        System.out.println("System has entered running mode");

        while (isThreadCheck()) {

            System.out.println("O.K. see how we execute target program");

            // this.generatePdf(MNo);
            writeReport(getTxtReportFile());

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(100);

                System.out.println("It's time for us threads to get back to work after the nap");

            } catch (java.lang.InterruptedException IntExec) {

                System.out.println(IntExec.getMessage());

            }

            setThreadCheck(false);


            System.out.println("We shall be lucky to get back to start in one piece");

        }

        if (!isThreadCheck()) {



            Thread.currentThread().stop();

        }

    }

    /**
     * @return the txtReportFile
     */
    public java.io.RandomAccessFile getTxtReportFile() {
        return txtReportFile;
    }

    /**
     * @param txtReportFile the txtReportFile to set
     */
    public void setTxtReportFile(java.io.RandomAccessFile txtReportFile) {
        this.txtReportFile = txtReportFile;
    }

    /**
     * @return the dbObject
     */
    public com.afrisoftech.lib.DBObject getDbObject() {
        return dbObject;
    }

    /**
     * @param dbObject the dbObject to set
     */
    public void setDbObject(com.afrisoftech.lib.DBObject dbObject) {
        this.dbObject = dbObject;
    }

    /**
     * @return the MNo
     */
    public java.lang.String getMNo() {
        return MNo;
    }

    /**
     * @param MNo the MNo to set
     */
    public void setMNo(java.lang.String MNo) {
        this.MNo = MNo;
    }

    /**
     * @return the Name
     */
    public java.lang.String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(java.lang.String Name) {
        this.Name = Name;
    }

    /**
     * @return the Cashier
     */
    public java.lang.String getCashier() {
        return Cashier;
    }

    /**
     * @param Cashier the Cashier to set
     */
    public void setCashier(java.lang.String Cashier) {
        this.Cashier = Cashier;
    }

    /**
     * @return the Amount
     */
    public java.lang.String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(java.lang.String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the Receipt
     */
    public java.lang.String getReceipt() {
        return Receipt;
    }

    /**
     * @param Receipt the Receipt to set
     */
    public void setReceipt(java.lang.String Receipt) {
        this.Receipt = Receipt;
    }

    /**
     * @return the Cash
     */
    public java.lang.String getCash() {
        return Cash;
    }

    /**
     * @param Cash the Cash to set
     */
    public void setCash(java.lang.String Cash) {
        this.Cash = Cash;
    }
   // RcvBranchRequisintfr
    /**
     * @return the Refund
     */
    public java.lang.String getRefund() {
        return Refund;
    }

    /**
     * @param Refund the Refund to set
     */
    public void setRefund(java.lang.String Refund) {
        this.Refund = Refund;
    }

    /**
     * @return the Paymode
     */
    public java.lang.String getPaymode() {
        return Paymode;
    }

    /**
     * @param Paymode the Paymode to set
     */
    public void setPaymode(java.lang.String Paymode) {
        this.Paymode = Paymode;
    }

    /**
     * @return the beginDate
     */
    public java.lang.String getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(java.lang.String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the waiver
     */
    public double getWaiver() {
        return waiver;
    }

    /**
     * @param waiver the waiver to set
     */
    public void setWaiver(double waiver) {
        this.waiver = waiver;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the ks
     */
    public String getKs() {
        return ks;
    }

    /**
     * @param ks the ks to set
     */
    public void setKs(String ks) {
        this.ks = ks;
    }

    /**
     * @return the rHeader
     */
    public java.lang.String getRHeader() {
        return rHeader;
    }

    /**
     * @param rHeader the rHeader to set
     */
    public void setRHeader(java.lang.String rHeader) {
        this.rHeader = rHeader;
    }

    /**
     * @return the dbUserName
     */
    public java.lang.String getDbUserName() {
        return dbUserName;
    }

    /**
     * @param dbUserName the dbUserName to set
     */
    public void setDbUserName(java.lang.String dbUserName) {
        this.dbUserName = dbUserName;
    }

    /**
     * @return the pConnDB
     */
    public org.netbeans.lib.sql.pool.PooledConnectionSource getPConnDB() {
        return pConnDB;
    }

    /**
     * @param pConnDB the pConnDB to set
     */
    public void setPConnDB(org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB) {
        this.pConnDB = pConnDB;
    }

    /**
     * @return the tempFile
     */
    public java.io.File getTempFile() {
        return tempFile;
    }

    /**
     * @param tempFile the tempFile to set
     */
    public void setTempFile(java.io.File tempFile) {
        this.tempFile = tempFile;
    }

    /**
     * @return the threadCheck
     */
    public boolean isThreadCheck() {
        return threadCheck;
    }

    /**
     * @param threadCheck the threadCheck to set
     */
    public void setThreadCheck(boolean threadCheck) {
        this.threadCheck = threadCheck;
    }

    /**
     * @return the threadSample
     */
    public java.lang.Thread getThreadSample() {
        return threadSample;
    }

    /**
     * @param threadSample the threadSample to set
     */
    public void setThreadSample(java.lang.Thread threadSample) {
        this.threadSample = threadSample;
    }

    /**
     * @return the wait_for_Pdf2Show
     */
    public java.lang.Process getWait_for_Pdf2Show() {
        return wait_for_Pdf2Show;
    }

    /**
     * @param wait_for_Pdf2Show the wait_for_Pdf2Show to set
     */
    public void setWait_for_Pdf2Show(java.lang.Process wait_for_Pdf2Show) {
        this.wait_for_Pdf2Show = wait_for_Pdf2Show;
    }

    /**
     * @return the rt
     */
    public java.lang.Runtime getRt() {
        return rt;
    }

    /**
     * @param rt the rt to set
     */
    public void setRt(java.lang.Runtime rt) {
        this.rt = rt;
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
