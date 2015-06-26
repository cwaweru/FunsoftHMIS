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
public class GRNTxtReport implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String beginDate = null;
    
    java.lang.String memNo = null;
    
    java.lang.String endDate = null;
    
    java.lang.String invNo = null;
    
    java.lang.String suppName = null;
    
    double amountReceived = 0.00;
    
    String ks;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.io.File tempFile = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    java.lang.Process wait_for_Pdf2Show;
    
    java.lang.Runtime rt = null;
    
    boolean previewPrint;
    
    public GRNTxtReport(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate, java.lang.String supp,java.lang.String invno, boolean printPreview) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        previewPrint = printPreview;
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        suppName = supp;
        
        invNo = invno;
        
        //       java.lang.Process wait_for_Pdf2Show;
        
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
        
        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        
        biz.systempartners.txtreports.TextReport textReport = null;
        for (int j = 0; j < listofStaffNos.length; j++) {
            
            double  osBalance = 0.00;
            
            System.getProperty("phrase.separator");
            
            System.getProperty("line.character");
            
            System.getProperty("line.character");
            
            System.getProperty("new.line.character");
            
            int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
            
            int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
            biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
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
            
            textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("linesperpage")), java.lang.Integer.parseInt(System.getProperty("charactersperline")), txtReportFile);
            
            double floats[] = {42,18,10,15,15};
            
            int colSizes[] = textReport.createTableHeader(5, floats);
            
            for (int i = 0; i < colSizes.length; i++) {
                System.out.println(colSizes[i]);
            }
            
            
            double floats2[] = {60,40};
            
            int colSizes2[] = textReport.createTableHeader(2, floats2);
            
            for (int i = 0; i < colSizes2.length; i++) {
                // System.out.println(colSizes2[i]);
            }
            
            double floats3[] = {20,40,10,15,15};
            
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
                while(rset2x.next()){
                    ks = rset2x.getObject(1).toString();
                }
                java.sql.ResultSet rset2 = st3.executeQuery("SELECT DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");
                
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
            
            table22.addCell(compName.toUpperCase());
            
            table22.addCell("Address : "+Address.toUpperCase());
            
            table22.addCell("Tel : "+Tel.toUpperCase());
            
            table22.addCell("Fax : "+Fax.toUpperCase());
            
            table22.addCell("Email : "+Email);
            
            //            biz.systempartners.txtreports.TableModelPanel tablePanel = new biz.systempartners.txtreports.TableModelPanel();
            Object companyName[] = { compName };
            
            double sizeArrayPercent[] = {100};
            
            int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);
            
            int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
            
            
            javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName,1);
            
            for (int i = 0; i < companyName.length; i++){
                headerCompany.setValueAt(companyName[i], 0, i);
            }
            
            
            Object reportName[] = { "G.R.N" };
            
            
            javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
            
            for (int i = 0; i < reportName.length; i++){
                headerTitle.setValueAt(reportName[i], 0, i);
            }
            
            Object columnModel1[] = {"Description", "Unit", "Qty", "Unit Price", "Total"};
            
            
            String columnModel[] = {"This", "That", "Then", "when", ""};
            
            String ColumnModelTitle[] = {""};
            
            String ColumnModelTitle2[] = {"",""};
            
            String ColumnModelTitle3[] = {"","","","",""};
            
            
            javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
            biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(5);
            for (int i = 0; i < columnModel1.length; i++){
                headerTableModel.setValueAt(columnModel1[i], 0, i);
                pageHeaderModel.addCell(columnModel1[i]);
            }
            
            int integers[] = colSizes;
            
            biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(5);
            
            biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);
            
            biz.systempartners.txtreports.PlainTextTable table3 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(5);
            biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(5);
            
            
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
            
            
            try{
                
                java.sql.Statement st = connectDB.createStatement();
                
                java.sql.Statement st11 = connectDB.createStatement();
                
                java.sql.Statement st1x = connectDB.createStatement();
                
                java.sql.Statement st12 = connectDB.createStatement();
                
                java.sql.Statement st21 = connectDB.createStatement();
                
                java.sql.ResultSet rset12 = st12.executeQuery("select item,units,quantity_received,price_per_item,debit from st_stock_cardex WHERE  supplier ilike '"+suppName+"%' and invoice_no ilike '"+invNo+"%' and transaction_type ilike 'Receiving%' ORDER BY item");
                //           java.sql.ResultSet rset12 = st12.executeQuery("select item,units,quantity_received,price_per_item,debit from st_stock_cardex WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND supplier ilike '"+suppName+"%' and invoice_no ilike '"+invNo+"%' and transaction_type ilike 'Receiving%' ORDER BY item");
                
                java.sql.ResultSet rset1x = st1x.executeQuery("select name from interim_footer");
                
                while (rset1x.next()){
                    simpleReportFooter = dbObject.getDBObject(rset1x.getObject(1), "-");
                    
                }
                
                while (rset12.next()){
                    
                    table1.addCell(dbObject.getDBObject(rset12.getObject(1), "-"));
                    
                    table1.addCell(dbObject.getDBObject(rset12.getObject(2), "-"));
                    
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset12.getString(3)));
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset12.getString(4)));
                    
                    
                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset12.getString(5)));
                    osBalance = osBalance + rset12.getDouble(5);
                    
                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                    //    table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)));
                    
                    
                }
                
             /*   while (rset1d.next()){
              
                    table11.addCell(dbObject.getDBObject(rset1d.getObject(1), "-"));
              
                    table11.addCell(dbObject.getDBObject(rset1d.getObject(2), "-"));
              
                    table11.addCell(dbObject.getDBObject(rset1d.getObject(3), "-"));
              
              
                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                    table11.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1d.getString(5)));
                    osBalanceq = osBalance - rset1d.getDouble(5);
              
                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                    table11.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceq)));
              
              
                }
              */
                
                
               /*
                while (rset4.next()){
                    dealer = dbObject.getDBObject(rset4.getObject(1), "-");
                }
                
                while (rset5.next()){
                    mno = dbObject.getDBObject(rset5.getObject(1), "-");
                    mname = dbObject.getDBObject(rset5.getObject(2), "-");
                
                }
                
                while (rset.next()){
                    admno = dbObject.getDBObject(rset.getObject(1), "-");
                    item = dbObject.getDBObject(rset.getObject(2), "-");
                
                }
                
                while (rsetTotals.next()){
                    credits = rsetTotals.getDouble(1);
                }
                
                while (rset12.next()){
                    dates = dbObject.getDBObject(rset12.getObject(1), "-");
                }
                
                while (rset11.next()){
                    invNo = dbObject.getDBObject(rset11.getObject(1), "-");
                }
                
                
                table2.addCell("Payer : " +dealer);
                
                table2.addCell("Invoice No : "+invNo);
                
                table2.addCell("Scheme Name : "+payee);
                
                table2.addCell("Patient No : "+admno);
                
                table2.addCell("Member No : "+mno);
                
                table2.addCell("Patient Name : "+item.toUpperCase());
                
                table2.addCell("Member Name : "+mname);
                */
                table3.addCell("Total ");
                
                table3.addCell(" ");
                
                table3.addCell(" ");
                
                table3.addCell(" ");
                
                
                
                table311.addCell("Patient Sign");
                
                table311.addCell(".....................");
                
                table311.addCell(" ");
                
                table311.addCell(" ");
                
                table311.addCell(" ");
                
                
                table2.addCell("Invoice Date : " +dates);
                
                
                
                
            } catch(java.sql.SQLException SqlExec) {
                
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
            
            textReport.addTable(table1, integers, columnModel, horizontalAlignments1);
            
            textReport.drawHorizontalLine(integers);
            
            
            
            //     textReport.addTable(tablePanel.getTableModel(), integers, columnModel, horizontalAlignments);
            
            
            textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
            
            // textReport.drawHorizontalLine(integers);
            
            //     textReport.addTable(table11, integers, columnModel, horizontalAlignments1);
            
            //     textReport.drawHorizontalLine(integers);
            
            //       textReport.addTable(table31, colSizes3, ColumnModelTitle3, horizontalAlignments3);
            
            //        textReport.drawHorizontalLine(integers);
            
            textReport.addTable(table311, colSizes3, ColumnModelTitle3, horizontalAlignments3);
            
            
            textReport.writeSimpleReportFooter(simpleReportFooter, true);
            
            //  textReport.screenNewPage(textReport.remainingLines);
            
            //   }
            //  textReport.writeSimpleReportFooter(simpleReportFooterModel);
            //   try {
            //  textReport.getReportAccessFile().close();
            //   } catch(java.io.IOException ioEx){
            //    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            //  }
        }
        
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
                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());
                     /*
                        wait_for_Pdf2Show = rt.exec("lp "+tempFile+"");
                      
                        wait_for_Pdf2Show.waitFor();
                      */
                    } else {
                        
                        System.out.println(tempFile);
                        
                        wait_for_Pdf2Show = rt.exec("kwrite "+tempFile+"");
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows 98")) {
                    
                    if (previewPrint) {
                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());
                        
                        /*
                        wait_for_Pdf2Show = rt.exec("print /D:"+System.getProperty("defaultprinter")+" "+tempFile);
                         
                        wait_for_Pdf2Show.waitFor();
                         */
                    } else {
                        
                        wait_for_Pdf2Show = rt.exec("wordpad "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                    // wait_for_Pdf2Show.waitFor();
                    
                } else {
                    
                    if (previewPrint) {
                        com.afrisoftech.lib.PrintTextFiles.printInvoiceTextFile(tempFile.getPath());
                     /*
                        wait_for_Pdf2Show = rt.exec("print "+tempFile);
                      
                        wait_for_Pdf2Show.waitFor();
                      */
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
        
        //        }
        
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
    
    public java.lang.Object[] getListofStaffNos() {
        
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
    
}
