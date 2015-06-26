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
public class VoucherTxt implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    java.lang.String vouchNo = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String beginDate = null;
    
    java.lang.String memNo = null;
    
    
    
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.io.File tempFile = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    java.lang.Process wait_for_Pdf2Show;
    
    java.lang.Runtime rt = null;
    
    boolean previewPrint;
    
    public VoucherTxt(java.sql.Connection connDb, java.lang.String voucno) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        
        connectDB = connDb;
        
        vouchNo = voucno;
        
        //previewPrint = printPreview;
        
        //endDate = inv2;
        
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
        
        //        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        
        
        //        for (int j = 0; j < listofStaffNos.length; j++) {
        
        double  osBalance = 0.00;
        
        double osBalance1 = 0.00;
        
        System.setProperty("phrase.separator", "  ");
        
        System.setProperty("line.character", "_");
        
        System.setProperty("new.line.character", " \n ");
        
        int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        // biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(biz.systempartners.txtreports.TextReport.TOTAL_NUM_LINES_PER_PAGE, biz.systempartners.txtreports.TextReport.TOTAL_NUM_CHAR_PER_LINE, txtReportFile);
        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("linesperpage")), java.lang.Integer.parseInt(System.getProperty("charactersperline")), txtReportFile);
        
        double floats[] = {20,40,20,20};
        
        int colSizes[] = textReport.createTableHeader(4, floats);
        
        for (int i = 0; i < colSizes.length; i++) {
            System.out.println(colSizes[i]);
        }
        
        
        double floats2[] = {60,40};
        
        int colSizes2[] = textReport.createTableHeader(2, floats2);
        
        for (int i = 0; i < colSizes2.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats3[] = {20,40,20,20};
        
        int colSizes3[] = textReport.createTableHeader(4, floats3);
        
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
            java.sql.ResultSet rset2 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");
            
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
        //\n Address: "+Address+"\n Tel: "+Tel+" \n Fax: "+Fax+"\n Email: "+Email+""};
        
        double sizeArrayPercent[] = {100};
        
        int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);
        
        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
        
        
        
        Object reportName[] = { "Payment Voucher".toUpperCase() };
        
        
        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
        
        for (int i = 0; i < reportName.length; i++){
            headerTitle.setValueAt(reportName[i], 0, i);
        }
        
        
        
        Object columnModel1[] = {"Date", "Description", "Qty", "Amount"};
        
        
        String columnModel[] = {"This", "That", "Then", "when"};
        
        String ColumnModelTitle[] = {""};
        
        String ColumnModelTitle2[] = {"",""};
        
        String ColumnModelTitle3[] = {"","","",""};
        
        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
        biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(5);
        for (int i = 0; i < columnModel1.length; i++){
            headerTableModel.setValueAt(columnModel1[i], 0, i);
            pageHeaderModel.addCell(columnModel1[i]);
        }
        
        int integers[] = colSizes;
        
        biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(4);
        
        biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);
        
        biz.systempartners.txtreports.PlainTextTable table3 = new biz.systempartners.txtreports.PlainTextTable(4);
        
        biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(4);
        
        biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(4);
        
        biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(4);
        
        String suppName = null;
        compName = null;
        String date = null;
        double Vat = 0.00;
        double Net = 0.00;
        double Net1 = 0.00;
        
        
        try{
            java.sql.Statement st = connectDB.createStatement();
            java.sql.Statement st1 = connectDB.createStatement();
            java.sql.Statement st2 = connectDB.createStatement();
            java.sql.Statement st5 = connectDB.createStatement();
            java.sql.Statement st6 = connectDB.createStatement();
            java.sql.Statement st7 = connectDB.createStatement();
            java.sql.Statement st61 = connectDB.createStatement();
            java.sql.Statement st611 = connectDB.createStatement();
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st33 = connectDB.createStatement();
            java.sql.Statement st3w = connectDB.createStatement();
            java.sql.Statement st4 = connectDB.createStatement();
            
            java.sql.ResultSet rset2 = st3w.executeQuery("SELECT user_name,dealer from ac_cash_book where voucher_no = '"+vouchNo+"'");
            //java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
            while(rset2.next()){
                compName = rset2.getObject(1).toString();
                suppName = rset2.getObject(2).toString();
            }
            java.sql.ResultSet rset33 = st33.executeQuery("SELECT DISTINCT pin_no,vat_no from st_suppliers where supplier_name ilike '"+suppName+"'");
            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no,pin_no,vat_no  from pb_hospitalprofile");
            java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT cb.dealer,cb.description from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
            java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT cb.voucher_no,date,cb.cheque_no,initcap(cb.payment_mode) from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
            java.sql.ResultSet rset5 = st5.executeQuery("select sum(cb.credit) from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
            java.sql.ResultSet rset6 = st6.executeQuery("select cb.inv_no,cb.amount_paid,vat_amount,cb.reason from cr_allocation cb where cb.voucher_no = '"+vouchNo+"'");
            java.sql.ResultSet rset7 = st7.executeQuery("SELECT DISTINCT upper(cb.account_no) ||'   '|| upper(cb.bank_name) from ac_cash_book cb where cb.voucher_no = '"+vouchNo+"'");
            java.sql.ResultSet rset61 = st61.executeQuery("select description,credit from ac_creditors_deductions  where voucher_no = '"+vouchNo+"' UNION select cb.reference_no,cb.debit from ac_accounts_payable cb where cb.voucher_no = '"+vouchNo+"' and transaction_type ilike 'Stock Returns%'");
            java.sql.ResultSet rset611 = st611.executeQuery("SELECT DISTINCT distinct cb.receipt_no from ac_accounts_payable cb where cb.voucher_no = '"+vouchNo+"' and transaction_type ilike 'payment%'");
            
            while (rset3.next()){
                while (rs.next()){
                    while (rset1.next()){
                        while (rset33.next()){
                            while (rset611.next()){
                                while (rset7.next()){
                                    
                                    table3.addCell("Pin No :  ");
                                    table3.addCell(rset3.getObject(9).toString().toUpperCase());
                                    table3.addCell("Agency No :");
                                    table3.addCell(rset3.getObject(10).toString().toUpperCase());
                                    
                                    table3.addCell("");
                                    table3.addCell("");
                                    table3.addCell("Voucher No : ");
                                    table3.addCell(rs.getString(1));
                                    
                                    table3.addCell("");
                                    table3.addCell("");
                                    table3.addCell("Date : ");
                                    table3.addCell(rs.getObject(2).toString().toUpperCase());
                                    
                                    table3.addCell("");
                                    table3.addCell("");
                                    table3.addCell("Cheque No. :");
                                    table3.addCell(dbObject.getDBObject(rs.getObject(3), "-"));
                                    
                                    
                                    table3.addCell("Payment Mode :");
                                    
                                    table3.addCell(dbObject.getDBObject(rs.getObject(4), "-"));
                                    table3.addCell("PIN No. :");
                                    
                                    table3.addCell(dbObject.getDBObject(rset33.getObject(1), "-"));
                                    
                                    
                                    table3.addCell("Payee :");
                                    table3.addCell(rset1.getObject(1).toString());
                                    
                                    table3.addCell("VAT No. :");
                                    
                                    table3.addCell(dbObject.getDBObject(rset33.getObject(2), "-"));
                                    
                                    table3.addCell("Towards :" );
                                    table3.addCell(dbObject.getDBObject(rset1.getObject(2), "-"));
                                    
                                    table3.addCell("Vat Cert. No. : ");
                                    table3.addCell(rset611.getObject(1).toString());
                                    
                                    table3.addCell("Account :");
                                    table3.addCell(dbObject.getDBObject(rset7.getObject(1), "-"));
                                    table3.addCell("");
                                    table3.addCell("");
                                }
                            }
                        }
                    }
                }
            }
            table3.addCell("");
            table3.addCell("Description");
            table3.addCell("Amount");
            table3.addCell("Vat");
            
            
            while (rset6.next()){
                
                table3.addCell(rset6.getObject(1).toString());
                
                //  table.getDefaultCell().setColspan(2);
                table3.addCell(rset6.getObject(4).toString());
                
                // table.getDefaultCell().setColspan(2);
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rset6.getObject(2).toString())));
                
                Net = Net + rset6.getDouble(2);
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rset6.getObject(3).toString())));
                
                Vat = Vat + rset6.getDouble(3);
            }
            
            table3.addCell(" ");
            
            table3.addCell("Total");
            
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(java.lang.String.valueOf(Net))));
            
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(java.lang.String.valueOf(Vat))));
            
            table3.addCell(" ");
            
            table3.addCell("Gross Pay");
            table3.addCell(" ");
            
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(java.lang.String.valueOf(Net))));
            
            //table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(java.lang.String.valueOf(Vat))));
            table3.addCell(" ");
            
            table3.addCell("Deductions");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            
            table3.addCell(" ");
            
            table3.addCell("Vat Amount : ");
            
            table3.addCell(" ");
            
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(java.lang.String.valueOf(Vat))));
            
            table3.addCell(" ");
            
            table3.addCell("Credit Note(s)");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            while (rset61.next()){
                table3.addCell(" ");
                
                table3.addCell(rset61.getObject(1).toString());
                
                table3.addCell(" ");
                
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset61.getObject(2).toString()));
                
                Net1 = Net1 + rset61.getDouble(2);
                
            }
            
            table3.addCell(" ");
            
            table3.addCell("Net Amount :");
            
            table3.addCell(" ");
            
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net-Vat-Net1)));
            
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell("Prepared By : ");
            
            table3.addCell(compName.toUpperCase());
            
            table3.addCell("Verified By : ");
            
            table3.addCell("-------------------- ");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell(" ");
            
            table3.addCell("Authorized By : ");
            
            table3.addCell("-------------------- ");
            
            table3.addCell("Received By: ");
            
            table3.addCell("-------------------- ");
            
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        
        //   for (int i = 0; i < 1; i++) {
        
        textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        
        textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
     /*
        textReport.addTable(table2, colSizes2, ColumnModelTitle2, horizontalAlignments2);
      
        textReport.drawHorizontalLine(integers);
      
        textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
      
        textReport.addPageHeader(pageHeaderModel, integers, columnModel, horizontalAlignments);
      
        textReport.drawHorizontalLine(integers);
      
        textReport.addTable(table1, integers, columnModel, horizontalAlignments1);
      
        textReport.drawHorizontalLine(integers);*/
        
        textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        textReport.drawHorizontalLine(integers);
        
        
      /*  textReport.addTable(table11, integers, columnModel, horizontalAlignments1);
       
        textReport.drawHorizontalLine(integers);
       
        //     textReport.addTable(tablePanel.getTableModel(), integers, columnModel, horizontalAlignments);
       
       
        textReport.addTable(table31, colSizes3, ColumnModelTitle3, horizontalAlignments3);
       
        textReport.drawHorizontalLine(integers);
       
        textReport.addTable(table311, colSizes3, ColumnModelTitle3, horizontalAlignments3);
       
        textReport.drawHorizontalLine(integers);
       */
        
        // textReport.writeSimpleReportFooter(simpleReportFooter, true);
        //  textReport.screenNewPage(textReport.remainingLines);
        // }
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
                    
                    // wait_for_Pdf2Show.waitFor();
                    
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
