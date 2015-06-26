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
public class ShiftReportTxt implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String MNo = null;
    
    java.lang.String Name = null;
    
    java.lang.String Amount = null;
    
    java.lang.String Receipt = null;
    
    java.lang.String Paymode = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    boolean previewPrint = true;
    
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
    
    public ShiftReportTxt(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint,java.lang.String userName) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        MNo = combox;
        
        Name = userName;
        
        Receipt = cashPoint;
        
        //    Receipt = receipt;
        
        connectDB = connDb;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        com.afrisoftech.lib.DateLables dateLabels = new com.afrisoftech.lib.DateLables();
        
        try {
            
            tempFile = java.io.File.createTempFile("REP"+dateLabels.getDateLabel()+"_", ".txt");
            
            tempFile.deleteOnExit();
            
        } catch(java.io.IOException ioEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            
        }
        
        try {
            
            rt = java.lang.Runtime.getRuntime();
            //   }
            
            //  beginDate = inv1;
            
            //  endDate = inv2;
            
            
            //    try {
            
            txtReportFile= new java.io.RandomAccessFile(tempFile,"rw");
            
            threadSample = new java.lang.Thread(this,"Plain Text Report Writer");
            
            System.out.println("threadSample created");
            
            threadSample.start();
            
            System.out.println("threadSample fired");
            
            
            //  writeReport(txtReportFile);
            
            
        } catch (java.io.FileNotFoundException fnf){
            
            fnf.printStackTrace();
            
        }
        
    }
    
    
    
    private void writeReport(java.io.RandomAccessFile txtRandomAccessFile){
        
        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        
        
        // for (int j = 0; j < listofStaffNos.length; j++) {
        
        double  osBalance = 0.00;
        double  osBalance1 = 0.00;
        double  osBalance2 = 0.00;
        
        System.setProperty("phrase.separator", "  ");
        
        System.setProperty("line.character", "-");
        
        System.setProperty("new.line.character", " \n ");
        
        int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
        
        int horizontalAlignments21[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments4[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("rcptlinesperpage")), java.lang.Integer.parseInt(System.getProperty("rcptcharactersperline")), txtReportFile);
        
        double floats[] = {50,10,20,20};
        
        int colSizes[] = textReport.createTableHeader(4, floats);
        
        for (int i = 0; i < colSizes.length; i++) {
            System.out.println(colSizes[i]);
        }
        
        
        double floats2[] = {50,50};
        
        int colSizes2[] = textReport.createTableHeader(2, floats2);
        
        for (int i = 0; i < colSizes2.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats21[] = {100};
        
        int colSizes21[] = textReport.createTableHeader(1, floats21);
        
        for (int i = 0; i < colSizes21.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats3[] = {70,30};
        
        int colSizes3[] = textReport.createTableHeader(2, floats3);
        
        for (int i = 0; i < colSizes3.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        
        double floats4[] = {50,50};
        
        int colSizes4[] = textReport.createTableHeader(2, floats4);
        
        for (int i = 0; i < colSizes4.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats5[] = {10,15,30,15,15,15};
        
        int colSizes5[] = textReport.createTableHeader(6, floats5);
        
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
            
            while(rset21.next()){
                rHeader = rset21.getString(1);
            }
            
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st4 = connectDB.createStatement();
            java.sql.Statement st2x = connectDB.createStatement();
            
            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
            while(rset2x.next()){
                ks = rset2x.getObject(1).toString();
            }
            java.sql.ResultSet rset2 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");
            
            while(rset2.next()){
                compName = rset2.getObject(1).toString();
                Address = rset2.getObject(2).toString();
                Tel = rset2.getObject(3).toString();
                Fax = rset2.getObject(5).toString();
                Email = rset2.getObject(6).toString();
            }
            
            
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        
        biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
        // Object companyName[] = { ""+compName+""};
        
        biz.systempartners.txtreports.PlainTextTable table22 = new biz.systempartners.txtreports.PlainTextTable(1);
        if (rHeader.equalsIgnoreCase("True")){
            table22.addCell(compName.toUpperCase());
            
            table22.addCell("Address : "+Address.toUpperCase()+ ", Tel : "+Tel.toUpperCase());
            
            //   table22.addCell("Tel : "+Tel.toUpperCase());
            
            //   table22.addCell("Fax : "+Fax.toUpperCase());
            
            //   table22.addCell("Email : "+Email);
            
            
        } else {
            
            
            table22.addCell("  ");
            
            table22.addCell("  ");
            
            table22.addCell("  ");
            
            // table22.addCell("  ");
        }
        
        Object companyName[] = { compName };
        
        double sizeArrayPercent[] = {100};
        
        int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);
        
        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
        
        
        javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName,1);
        
        for (int i = 0; i < companyName.length; i++){
            headerCompany.setValueAt(companyName[i], 0, i);
        }
        //String ColumnModelTitle3[] = {"","","",""};
        
        
        // String ColumnModelTitle4[] = { "    ", "   ", "   " };
        String ColumnModelTitle4[] = { "Shift No", "Date" };
        Object reportName[] = { "SHIFT REPORT"};
        
        
        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
        
        for (int i = 0; i < reportName.length; i++){
            headerTitle.setValueAt(reportName[i], 0, i);
        }
        
        
        String columnModel1[] = {"    ", "     ","    ", "     ","    ","  "};
        //  String columnModel1[] = {"Rev.Code","Description", "Qty", "Price @", "Amt"};
        
        
        String columnModel[] = {"This", "That", "Then", "when"};
        
        String ColumnModelTitle[] = {""};
        
        String ColumnModelTitle2[] = {"",""};
        
        String ColumnModelTitle3[] = {"",""};
        
        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(ColumnModelTitle,1);
        for (int i = 0; i < ColumnModelTitle.length; i++){
            headerTableModel.setValueAt(ColumnModelTitle[i], 0, i);
        }
        
        
        
        int integers[] = colSizes;
        
        biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(6);
        
        biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(6);
        
        biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);
        
        biz.systempartners.txtreports.PlainTextTable table21 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        biz.systempartners.txtreports.PlainTextTable table3 = new biz.systempartners.txtreports.PlainTextTable(1);
        
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
        
        String simpleReportFooter = null;
        
        String cash_words = null;
        
        double credits = 0.00;
        
        
        
        try{
            
            java.sql.Statement st = connectDB.createStatement();
            java.sql.Statement st1 = connectDB.createStatement();
            java.sql.Statement st2 = connectDB.createStatement();
            java.sql.Statement st5 = connectDB.createStatement();
            java.sql.Statement st51 = connectDB.createStatement();
            java.sql.Statement st6 = connectDB.createStatement();
            java.sql.Statement st7 = connectDB.createStatement();
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.ResultSet rset3 = st3.executeQuery("select header from ac_receipt_header");
            java.sql.ResultSet rset1 = st1.executeQuery("select CURRENT_TIMESTAMP(0)::date");
            java.sql.ResultSet rset53 = st51.executeQuery("select distinct shift_no,cash_point,user_name from ac_cash_collection where shift_no = '"+MNo+"' AND cash_point = '"+Receipt+"'");
            
            while (rset53.next()){
                table2.addCell("Shift No : "+dbObject.getDBObject(rset53.getObject(1), "-"));
                table2.addCell("Cash Point : "+dbObject.getDBObject(rset53.getObject(2), "-"));
                table2.addCell("Cashier : "+dbObject.getDBObject(rset53.getObject(3), "-"));
                // table2.addCell("");
            }
            
            while (rset1.next()){
                table2.addCell("Date : "+dbObject.getDBObject(rset1.getObject(1), "-"));
                // table2.addCell("");
            }
            table3.addCell(dbObject.getDBObject(compName, "-"));
            
            // }
            
            table11.addCell("Rct No");
            table11.addCell("Date");
            table11.addCell("Name");
            table11.addCell("Amt");
            table11.addCell("Ref/Wav");
            table11.addCell("Amt "+ks);
            
            java.lang.Object[] listofAct = this.getListofActivities();
            for (int i = 0; i < listofAct.length; i++) {
                double balance = 0.00;
                double balance1 = 0.00;
                double balance2 = 0.00;
                
                java.sql.ResultSet rset5 = st5.executeQuery("select receipt_no,date,dealer,sum(debit),sum(credit),sum(debit-credit) from ac_cash_collection where shift_no = '"+MNo+"' AND cash_point = '"+Receipt+"' and payment_mode ILIKE '"+listofAct[i].toString()+"' group by receipt_no,date,dealer order by receipt_no");
                
                
                
                table1.addCell("");
                table1.addCell("");
                table1.addCell(dbObject.getDBObject(listofAct[i].toString(), "-"));
                table1.addCell("");
                table1.addCell("");
                table1.addCell("");
                while (rset5.next()){
                    
                    table1.addCell(dbObject.getDBObject(rset5.getObject(1), "-"));
                    table1.addCell(dbObject.getDBObject(rset5.getObject(2), "-"));
                    
                    table1.addCell(dbObject.getDBObject(rset5.getObject(3), "-"));
                    
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(4)));
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(5)));
                    
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(6)));
                    osBalance1 = osBalance + rset5.getDouble(4);
                    osBalance2 = osBalance + rset5.getDouble(5);
                    osBalance = osBalance + rset5.getDouble(6);
                    
                    balance1 = balance1 + rset5.getDouble(4);
                    balance2 = balance2 + rset5.getDouble(5);
                    balance = balance + rset5.getDouble(6);
                    
                    
                    System.out.println("This is the description "+rset5.getString(1));
                    
                }
                
                table1.addCell(dbObject.getDBObject("", "-"));
                table1.addCell(dbObject.getDBObject("", "-"));
                
                table1.addCell(dbObject.getDBObject("Sub Total", "-"));
                
                table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance1)));
                table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance2)));
                
                table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance)));
                
            }
            table1.addCell(dbObject.getDBObject("", "-"));
            table1.addCell(dbObject.getDBObject("", "-"));
            
            table1.addCell(dbObject.getDBObject("Net Total", "-"));
            
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)));
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2)));
            
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));
            
       /*  //   tableF.addCell("Pymt Mode: "+Paymode);
          //
         //   tableF.addCell("Cash Point: "+Cashpoint);
        
         //   tableF.addCell("Cashier: "+  Cashier.toUpperCase());
        
         //   tableF.addCell( " ");
        
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
            tableF.addCell( " ");
        
        */
            
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        // if (rHeader.equalsIgnoreCase("false")){
        textReport.addTable(table3,  colSizes21, ColumnModelTitle, horizontalAlignments21);
        
        textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        textReport.addTable(table2, colSizes4, ColumnModelTitle4, horizontalAlignments4);
        textReport.addTable(table4, colSizes4, ColumnModelTitle4, horizontalAlignments4);
        textReport.addTable(table11, colSizes5, columnModel1, horizontalAlignments1);
        
        textReport.drawHorizontalLine(integers);
        textReport.addTable(table21, colSizes21, ColumnModelTitle, horizontalAlignments21);
        
        textReport.addTable(table1, colSizes5, columnModel1, horizontalAlignments1);
        
        textReport.drawHorizontalLine(integers);
        
        textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        textReport.drawHorizontalLine(integers);
        textReport.addTable(tableF, colSizes4, ColumnModelTitle3, horizontalAlignments3);
      /*
        }else{
       
       
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
            //   textReport.writeSimpleReportFooter(simpleReportFooter, false);
            //  textReport.drawHorizontalLine(integers);
       
        }
       */
        try {
            textReport.getReportAccessFile().close();
        } catch(java.io.IOException ioEx){
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        try{
            
            try {
                
                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                    
                    if (previewPrint) {
                        
                        System.out.println(tempFile);
                        
                        wait_for_Pdf2Show = rt.exec("lp "+tempFile+"");
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    } else {
                        
                        System.out.println(tempFile);
                        
                        wait_for_Pdf2Show = rt.exec("kwrite "+tempFile+"");
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows 98")) {
                    
                    if (previewPrint) {
                        
                        wait_for_Pdf2Show = rt.exec("print /D:"+System.getProperty("defaultprinter")+" "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    } else {
                        
                        wait_for_Pdf2Show = rt.exec("wordpad "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                } else {
                    
                    if (previewPrint) {
                        
                        wait_for_Pdf2Show = rt.exec("print "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    } else {
                        wait_for_Pdf2Show = rt.exec("wordpad "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                }
                
            } catch(java.lang.InterruptedException intrExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                
            }
            
            
        } catch(java.io.IOException ioEx){
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            
        }
        
        
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
                
            } catch(java.lang.InterruptedException IntExec) {
                
                System.out.println(IntExec.getMessage());
                
            }
            
            threadCheck = false;
            
            
            System.out.println("We shall be lucky to get back to start in one piece");
            
        }
        
        if (!threadCheck) {
            
            
            
            Thread.currentThread().stop();
            
        }
        
    }
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payment_mode FROM ac_cash_collection where shift_no = '"+MNo+"' order by payment_mode");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
}
