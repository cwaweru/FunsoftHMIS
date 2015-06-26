package com.afrisoftech.txtreports;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * SampleTxtReport.java
 *
 * Created on August 20, 2005, 4:39 PM


package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class StatementPerSchemeTxtReport implements java.lang.Runnable {

    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    biz.systempartners.txtreports.PlainTextTable table3;
    biz.systempartners.txtreports.PlainTextTable table4;
//    String[] columnModel1;
    String[] columnModel;
    java.lang.String memNo = null;
    java.lang.String memNo1 = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    String ks;
    com.afrisoftech.dbadmin.ExcelExport export2Excel = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.io.File tempFile = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    java.lang.Process wait_for_Pdf2Show;
    java.lang.Runtime rt = null;
    boolean previewPrint;
    Object columnModel1[] = null;
    java.lang.String exportFile = "Debtors Statement";

    public StatementPerSchemeTxtReport(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox, boolean printPreview) {


        previewPrint = printPreview;

        dbObject = new com.afrisoftech.lib.DBObject();

        memNo = combox;

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;
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

    @SuppressWarnings("empty-statement")
    private void writeReport(java.io.RandomAccessFile txtRandomAccessFile) {

//        com.afrisoftech.dbadmin.ExcelExport export2Excel = new com.afrisoftech.dbadmin.ExcelExport();

        java.io.File excelDocFile = null;

//        org.w3c.dom.Document excelDocument = null;

        //       System.out.println("Started EXCEL business ...");



        // excelDocFile = java.io.File.createTempFile(fileString, ".xls", new java.io.File(System.getProperty("docsdir")));
        if (excelDocFile == null) {
            excelDocFile = new java.io.File(System.getProperty("docsdir"), exportFile + ".xls");
        }

       // com.afrisoftech.dbadmin.ExcelExport.excelDocFile = excelDocFile;

        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

        biz.systempartners.txtreports.TextReport textReport = null;
        //  for (int j = 0; j < listofStaffNos.length; j++) {
        for (int j = 0; j < listofStaffNos.length; j++) {
            double osBalance = 0.00;
            double totalMonth = 0.00;
            double Balance = 0.00;

            System.getProperty("phrase.separator");

            System.getProperty("line.character");

            System.getProperty("line.character");

            System.getProperty("new.line.character");

            int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

            int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

            int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};

            int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
                biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};

            System.out.println("Number of chars per line [" + java.lang.Integer.parseInt(System.getProperty("charactersperline")) + "]");

            textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("linesperpage")), java.lang.Integer.parseInt(System.getProperty("charactersperline")), txtReportFile);

            double floats[] = {16, 10, 24, 12, 12, 12, 14};

            int colSizes[] = textReport.createTableHeader(7, floats);

            for (int i = 0; i < colSizes.length; i++) {
                System.out.println(colSizes[i]);
            }


            double floats2[] = {70, 30};

            int colSizes2[] = textReport.createTableHeader(2, floats2);

            for (int i = 0; i < colSizes2.length; i++) {
                // System.out.println(colSizes2[i]);
            }

            double floats3[] = {20, 40, 10, 15, 15};

            int colSizes3[] = textReport.createTableHeader(5, floats3);

            for (int i = 0; i < colSizes3.length; i++) {
                // System.out.println(colSizes2[i]);
            }

            String compName = null;
            String Address = null;
            String Tel = null;
            String Fax = null;
            String Email = null;
            String simpleReportFooter = null;

            try {


                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.Statement st4 = connectDB.createStatement();
                java.sql.Statement st2x = connectDB.createStatement();

                java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                while (rset2x.next()) {
                    ks = rset2x.getObject(1).toString();
                }
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

            table22.addCell(compName.toUpperCase());

            table22.addCell("Address : " + Address.toUpperCase());

            table22.addCell("Tel : " + Tel.toUpperCase());

            table22.addCell("Fax : " + Fax.toUpperCase());

            table22.addCell("Email : " + Email);

            //            biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
            Object companyName[] = {compName};

            double sizeArrayPercent[] = {100};

            int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);

            int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};


            javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName, 1);

            for (int i = 0; i < companyName.length; i++) {
                headerCompany.setValueAt(companyName[i], 0, i);
            }


            Object reportName[] = {"Statement Of Account"};


            javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName, 1);

            for (int i = 0; i < reportName.length; i++) {
                headerTitle.setValueAt(reportName[i], 0, i);
            }



            columnModel1 = new Object[]{"Date", "Patient No", "Names", "Invoice No", "Orig. Amt.", "Balance", "Running " + ks};

            columnModel = new String[]{"This", "That", "Then", "when", "This", "That", "Then"};

            String ColumnModelTitle[] = {""};

            String ColumnModelTitle2[] = {"", ""};

            String ColumnModelTitle3[] = {"", "", "", "", ""};

            String ColumnModelTitle4[] = {"", "", "", "", "", "", ""};
            String ColumnModelTitle5[] = {"", "", "", "", "", "", ""};

            javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1, 1);
            biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(5);
            for (int i = 0; i < columnModel1.length; i++) {
                headerTableModel.setValueAt(columnModel1[i], 0, i);
                pageHeaderModel.addCell(columnModel1[i]);
            }

            int integers[] = colSizes;

            biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(5);

            biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);
            //  biz.systempartners.txtreports.PlainTextTable table3 = new biz.systempartners.txtreports.PlainTextTable(5);

            table3 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(5);

            table4 = new biz.systempartners.txtreports.PlainTextTable(7);
            biz.systempartners.txtreports.PlainTextTable table5 = new biz.systempartners.txtreports.PlainTextTable(7);


            String dealer = null;

            String invNo = null;

            String payee = null;

            String admno = null;

            String mno = null;

            String item = null;

            String mname = null;

            String dates = null;

            double credits = 0.00;

            double osBalanceq = 0.00;


            try {
                java.sql.Statement st22 = connectDB.createStatement();
                java.sql.Statement st11 = connectDB.createStatement();
                java.sql.Statement st = connectDB.createStatement();
                java.sql.Statement st1 = connectDB.createStatement();
                java.sql.Statement st2 = connectDB.createStatement();
                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.Statement st4 = connectDB.createStatement();
                java.sql.Statement st41 = connectDB.createStatement();
                java.sql.Statement st5 = connectDB.createStatement();
                // java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                java.sql.ResultSet rset41 = st41.executeQuery("select payer_name from ac_schemes where account_no = '" + listofStaffNos[j] + "'");
                java.sql.ResultSet rset4 = st4.executeQuery("select scheme_name,account_no from ac_schemes where account_no = '" + listofStaffNos[j] + "'");
                java.sql.ResultSet rset1 = st1.executeQuery("select date,admission_no, INITCAP(item) as item,invoice_no ||' '||receipt_no,journal_no,debit,balance-credit_bal from ac_debtors where account_no = '" + listofStaffNos[j] + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and (balance > 0 or credit_bal > 0) order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                java.sql.ResultSet rsetTotals2 = st22.executeQuery("select sum(balance-credit_bal) from ac_debtors where account_no = '" + listofStaffNos[j] + "' AND date::date < '" + beginDate + "'");


                simpleReportFooter = "-";

                while (rsetTotals2.next()) {
                    osBalance = rsetTotals2.getDouble(1);
                }

                while (rset41.next()) {
                    dealer = dbObject.getDBObject(rset41.getObject(1), "-");
                }

                while (rset4.next()) {
                    payee = dbObject.getDBObject(rset4.getObject(1), "-");
                    mno = dbObject.getDBObject(rset4.getObject(2), "-");
                }

                table2.addCell("Payer : " + dbObject.getDBObject(dealer, "-").toUpperCase());

                table2.addCell(" ");

                table2.addCell("Scheme Name : " + dbObject.getDBObject(payee, "-").toUpperCase());

                table2.addCell(" ");

                table2.addCell("A/C No : " + dbObject.getDBObject(mno, "-").toUpperCase());

                table2.addCell(" ");


                try {
                    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                    java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                    java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                    System.out.println("" + endDate1);
                    //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                    //  table.addCell(phrase);


                    table2.addCell("Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1));

                    table2.addCell(" ");

                    System.out.println("Writing table [" + table2 + "] : file [" + exportFile + "]");
 
                } catch (java.text.ParseException psExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                }

                table4.addCell(" ");

                table4.addCell(" ");

                table4.addCell("BAL/BF ");

                table4.addCell(" ");

                //  table4.addCell(dbObject.getDBObject(rset1.getObject(5), "-"));
                table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalMonth)));


                table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Balance)));


                table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));


                while (rset1.next()) {
                    table4.addCell(dbObject.getDBObject(rset1.getObject(1), "-"));

                    table4.addCell(dbObject.getDBObject(rset1.getObject(2), "-"));

                    table4.addCell(dbObject.getDBObject(rset1.getObject(3), "-"));

                    table4.addCell(dbObject.getDBObject(rset1.getObject(4), "-"));

                    //  table4.addCell(dbObject.getDBObject(rset1.getObject(5), "-"));

                    table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)));
                    totalMonth = totalMonth + rset1.getDouble(6);


                    table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)));
                    osBalance = osBalance + rset1.getDouble(7);
                    Balance = Balance + rset1.getDouble(7);

                    table4.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));


                    System.out.println(Balance);
                }


                table5.addCell(" ");

                table5.addCell(" ");

                table5.addCell("Total ");

                table5.addCell(" ");

                //  table4.addCell(dbObject.getDBObject(rset1.getObject(5), "-"));
                table5.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalMonth)));


                table5.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Balance)));


                table5.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));



            } catch (java.sql.SQLException SqlExec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

            }

            //   for (int i = 0; i < 1; i++) {

            textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);

            textReport.drawHorizontalLine(integers);

            textReport.addPageHeader(pageHeaderModel, integers, columnModel, horizontalAlignments);


            textReport.addTable(table2, colSizes2, ColumnModelTitle2, horizontalAlignments2);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table4, integers, columnModel, horizontalAlignments1);


/*
            export2Excel.excelExport(new javax.swing.JTable(headerTitle), exportFile);


            export2Excel.excelExport(new javax.swing.JTable(headerTableModel), exportFile);

            export2Excel.excelExport(new javax.swing.JTable(pageHeaderModel), exportFile);

            export2Excel.excelExport(new javax.swing.JTable(table2), exportFile);

            export2Excel.excelExport(new javax.swing.JTable(table4), exportFile);

            export2Excel.excelExport(new javax.swing.JTable(table5), exportFile);
 */

            textReport.drawHorizontalLine(integers);

            textReport.addTable(table5, integers, columnModel, horizontalAlignments1);

            textReport.drawHorizontalLine(integers);

            textReport.writeSimpleReportFooter(simpleReportFooter, true);
           /* try {
                export2Excel.excelOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(StatementPerSchemeTxtReport.class.getName()).log(Level.SEVERE, null, ex);
           }
           */

        }

        try {
            textReport.getReportAccessFile().close();
        } catch (java.io.IOException ioEx) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        try {

            try {

                if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {

                    if (previewPrint) {

                        System.out.println(tempFile);

                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());

                    // wait_for_Pdf2Show = rt.exec("lp "+tempFile+"");

                    //  wait_for_Pdf2Show.waitFor();

                    } else {

                        System.out.println(tempFile);

                        wait_for_Pdf2Show = rt.exec("kwrite " + tempFile + "");

                        wait_for_Pdf2Show.waitFor();

                    }

                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows 98")) {

                    if (previewPrint) {

                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());
                    //  wait_for_Pdf2Show = rt.exec("print /D:"+System.getProperty("defaultprinter")+" "+tempFile);

                    //  wait_for_Pdf2Show.waitFor();

                    } else {

                      //  wait_for_Pdf2Show = rt.exec("wordpad " + tempFile);

                        //java.awt.Desktop.getDesktop().open(tempFile);

                        wait_for_Pdf2Show.waitFor();

                    }

                // wait_for_Pdf2Show.waitFor();

                } else {

                    if (previewPrint) {

                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());
                    // wait_for_Pdf2Show = rt.exec("print "+tempFile);

                    //  wait_for_Pdf2Show.waitFor();

                    } else {
                        wait_for_Pdf2Show = rt.exec("wordpad " + tempFile);

                        wait_for_Pdf2Show.waitFor();

                    }

                }

            } catch (java.lang.InterruptedException intrExec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());

            }


        } catch (java.io.IOException ioEx) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());

        }

    //        }

    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {
         ///   try {
                System.out.println("O.K. see how we execute target program");
                // this.generatePdf(MNo);
                writeReport(txtReportFile);
                //   java.lang.String exportFile = javax.swing.JOptionPane.showInputDialog(new java.awt.Frame(), "Please provide a name for the target file.","Specifying target file name",javax.swing.JOptionPane.YES_NO_OPTION);
                //  javax.swing.table.DefaultTableColumnModel columnModel = new javax.swing.table.DefaultTableColumnModel();
                // columnModel.
                java.util.Vector columnVector = new java.util.Vector(1, 1);
                // java.lang.String[] columnModel1 = {"Code","Description","Amount"};
                for (int i = 0; i < 7; i++) {
                    System.out.println("Column Vector : [" + columnVector + "]"); // & Column Model Objects : ["+columnModel1[i]+"]");
                    columnVector.addElement(columnModel1[i].toString());
                //  System.out.println("Column Model Objects : ["+columnModel1[i]+"]");
                }
//                .excelOutputStream.close();
                ////           javax.swing.JTable table2Export = new javax.swing.JTable(table4.getDataVector(),columnVector);
////            com.afrisoftech.dbadmin.ExcelExport export2Excel = new com.afrisoftech.dbadmin.ExcelExport(table2Export,exportFile);
                try {
                    System.out.println("Right, let's wait for task to complete of fail");
                    java.lang.Thread.currentThread().sleep(100);
                    System.out.println("It's time for us threads to get back to work after the nap");
                } catch (java.lang.InterruptedException IntExec) {
                    System.out.println(IntExec.getMessage());
                }
                threadCheck = false;
                System.out.println("We shall be lucky to get back to start in one piece");
          //  } catch (IOException ex) {
          //      Logger.getLogger(StatementPerSchemeTxtReport.class.getName()).log(Level.SEVERE, null, ex);
          //  }

        }

        if (!threadCheck) {



            Thread.currentThread().stop();

        }

    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT account_no FROM ac_debtors WHERE  dealer ilike '" + memNo + "%' and date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  order by account_no");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
