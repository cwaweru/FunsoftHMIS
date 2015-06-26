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
public class CreditAgeingTxt implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    biz.systempartners.txtreports.PlainTextTable table3;
    
    biz.systempartners.txtreports.PlainTextTable table1;
    
    String[] columnModel1;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String memNo = null;
    
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    String ks;
    
    double Credit = 0.00;
    
    int over = 0;
    
    int name = 0;
    
    int cnt = 0;
    
    String activityCode = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.lang.String balnc = null;
    
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    
    com.afrisoftech.timeseries.AgeingSeries ageingSeries= null;
    
    java.util.Date ageingDates[][] = null;
    
    double osBalance = 0.00;
    
    double current = 0.00;
    
    double over30 = 0.00;
    
    double over60 = 0.00;
    
    double over90 = 0.00;
    
    double turnOver = 0.00;
    
    double grandTot = 0.00;
    
    java.io.File tempFile = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    java.lang.Process wait_for_Pdf2Show;
    
    java.lang.Runtime rt = null;
    
    boolean previewPrint;
    
    public CreditAgeingTxt(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String bal, java.lang.String actCode) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // memNo = combox;
        
        beginDate = begindate;
        
        endDate = endate;
        
        connectDB = connDb;
        
        balnc = bal;
        
        activityCode = actCode;
        
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(4, endate);
        
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
        
        ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals[] = new double[ageingDates.length];
        double columnTotals2[] = new double[ageingDates.length];
        
        int interval = 0;
        
        double  osBalance = 0.00;
        
        double osBalance1 = 0.00;
        
        System.setProperty("phrase.separator", "  ");
        
        System.setProperty("line.character", "_");
        
        System.setProperty("new.line.character", " \n ");
        
        int horizontalAlignments[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        // biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(biz.systempartners.txtreports.TextReport.TOTAL_NUM_LINES_PER_PAGE, biz.systempartners.txtreports.TextReport.TOTAL_NUM_CHAR_PER_LINE, txtReportFile);
        biz.systempartners.txtreports.TextReport textReport = new biz.systempartners.txtreports.TextReport(java.lang.Integer.parseInt(System.getProperty("linesperpage")), java.lang.Integer.parseInt(System.getProperty("charactersperline")), txtReportFile);
        
        double floats[] = {20,50,30};
        
        int colSizes[] = textReport.createTableHeader(3, floats);
        
        for (int i = 0; i < colSizes.length; i++) {
            System.out.println(colSizes[i]);
        }
        
        double floats21[] = {100};
        
        int colSizes21[] = textReport.createTableHeader(1, floats21);
        
        for (int i = 0; i < colSizes21.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats2[] = {60,40};
        
        int colSizes2[] = textReport.createTableHeader(2, floats2);
        
        for (int i = 0; i < colSizes2.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats3[] = {20,50,30};
        
        int colSizes3[] = textReport.createTableHeader(3, floats3);
        
        for (int i = 0; i < colSizes3.length; i++) {
            // System.out.println(colSizes2[i]);
        }
        
        double floats1[] = {5,25,10,10,10,10,10,10,10};
        
        int colSizes1[] = textReport.createTableHeader(9, floats1);
        
        for (int i = 0; i < colSizes1.length; i++) {
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
        
        
        table22.addCell(" ");
        
        table22.addCell(" ");
        
        table22.addCell(" ");
        
        table22.addCell(" ");
        
        table22.addCell(compName.toUpperCase());
        
        // table22.addCell("Address : "+Address.toUpperCase());
        
        //  table22.addCell("Tel : "+Tel.toUpperCase());
        
        //  table22.addCell("Fax : "+Fax.toUpperCase());
        
        //  table22.addCell("Email : "+Email);
        //\n Address: "+Address+"\n Tel: "+Tel+" \n Fax: "+Fax+"\n Email: "+Email+""};
        
        double sizeArrayPercent[] = {100};
        
        int colSizeTitle[] = textReport.createTableHeader(1, sizeArrayPercent);
        
        int horizontalAlignmentsTitle[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_CENTER};
        
        
        //   javax.swing.table.DefaultTableModel headerCompany = new javax.swing.table.DefaultTableModel(companyName,1);
        
        //   for (int i = 0; i < companyName.length; i++){
        //       headerCompany.setValueAt(companyName[i], 0, i);
        //   }
        //
        java.text.DateFormat dateFormat = null;
        java.util.Date endDate1 = null;
        try {
            dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
            
            
            endDate1 = dateFormat.parse(endDate.toLocaleString());
        } catch(java.text.ParseException psExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
            
        }
        
        Object reportName[] = { "Creditors Ageing As At : " +dateFormat.format(endDate1).toUpperCase() };
        
        
        
        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
        
        for (int i = 0; i < reportName.length; i++){
            headerTitle.setValueAt(reportName[i], 0, i);
        }
        
        
        
        
        columnModel1 = new java.lang.String[]{"No", "Name", "Current","30 Days","60 Days","90 Days","120 Days","Unalloc","Balance"};
        
        
        String columnModel[] = {"This", "That", "Then"};
        
        String ColumnModelTitle[] = {""};
        
        String ColumnModelTitle2[] = {"",""};
        
        String ColumnModelTitle3[] = {"","",""};
        
        String ColumnModelTitle1[] = {"","","","","","","","",""};
        
        
        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
        biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(3);
        for (int i = 0; i < columnModel1.length; i++){
            headerTableModel.setValueAt(columnModel1[i], 0, i);
            pageHeaderModel.addCell(columnModel1[i]);
        }
        
        int integers[] = colSizes;
        
        table1 = new biz.systempartners.txtreports.PlainTextTable(9);
        
        biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        table3 = new biz.systempartners.txtreports.PlainTextTable(3);
        
        biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(3);
        
        // biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(5);
        
        
        double debit = 0.00;
        double credit = 0.00;
        double debit1 = 0.00;
        double credit2 = 0.00;
        double Totals = 0.00;
        double OS = 0.00;
        int j = 0;
        java.lang.String activity = null;
        java.lang.String code = null;
        
        table1.addCell("No ");
        table1.addCell("Name");
        
        
        for (int x = 0; x < ageingDates.length; x++) {
            
            int days = 30;
            if (x < 1) {
                table1.addCell("Current");
            } else {
                table1.addCell(""+x*days +" Days");
                interval = x;
            }
            
            //   table.addCell(phrase);
            
        }
        
        table1.addCell(""+((interval + 1) * 30)+" Days");
        //  table.addCell(phrase);
        
        table1.addCell("UnAlloc.");
        
        table1.addCell("OS "+ks);
        
        try{
            
            double GrandTotal = 0.00;
            double Over120Total = 0.00;
            java.lang.Object[] listofAct = this.getListofActivities();
            
            System.out.println(listofAct.length);
            
            for (int i = 0; i < listofAct.length; i++) {
                double TurnOver = 0.00;
                double Over120 = 0.00;
                double TotalCount = 0.00;
                double unalloc = 0.00;
                double Curr120 = 0.00;
                
                double bals = 0.00;
                
                java.sql.Statement stmta1 = connectDB.createStatement();
                java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(dealer) FROM ac_accounts_payable where  dealer = ?");
                java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select distinct dealer from ac_accounts_payable where  dealer = ? ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                pset22.setString(1,listofAct[i].toString());
                pSeta1.setString(1,listofAct[i].toString());
                java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                while (rSeta1.next()) {
                    name = rSeta1.getInt(1);
                    
                }
                if (name > 0){
                    java.sql.ResultSet rset22 = pset22.executeQuery();
                    
                    while (rset22.next()){
                        cnt = cnt + 1;
                        table1.addCell(""+cnt);
                        table1.addCell(dbObject.getDBObject(rset22.getObject(1), "-"));
                        
                        java.sql.Statement st2 = connectDB.createStatement();
                        java.sql.Statement st21 = connectDB.createStatement();
                        java.sql.Statement st22 = connectDB.createStatement();
                        java.sql.Statement st23 = connectDB.createStatement();
                        java.sql.Statement st211 = connectDB.createStatement();
                        java.sql.Statement st221 = connectDB.createStatement();
                        java.sql.Statement st231 = connectDB.createStatement();
                        java.sql.Statement st2A = connectDB.createStatement();
                        java.sql.Statement st2B = connectDB.createStatement();
                        java.sql.Statement st2C = connectDB.createStatement();
                        java.sql.Statement stc = connectDB.createStatement();
                        System.out.println("Dealer Is : ["+listofAct[i]+"].");
                        java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(credit-debit) from ac_accounts_payable WHERE dealer = ?  AND date <= '"+ageingDates[0][0]+"' AND activity_code = '"+activityCode+"' ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                        pset1.setString(1,listofAct[i].toString());
                        java.sql.ResultSet rset1 = pset1.executeQuery();
                        
                        java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select sum(credit-debit) from ac_accounts_payable WHERE dealer = ?  AND date <= '"+endDate+"' AND activity_code = '"+activityCode+"' ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                        pset111.setString(1,listofAct[i].toString());
                        java.sql.ResultSet rset111 = pset111.executeQuery();
                        java.sql.Statement st02 = connectDB.createStatement();
                        
                        java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit-debit) from ac_accounts_payable WHERE dealer = ? AND date <= '"+endDate+"' AND activity_code = '"+activityCode+"' ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                        pset112.setString(1,listofAct[i].toString());
                        java.sql.ResultSet rset112 = pset112.executeQuery();
                        
                        for (int t = ageingDates.length - 1; t >= 0; t--) {
                            columnTotals[t] = 0.00;
                            double credo = 0.00;
                            double credo1 = 0.00;
                            Over120 = 0.00;
                            java.sql.Statement st01 = connectDB.createStatement();
                            
                            java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(credit),invoice_no from ac_accounts_payable WHERE dealer = ? AND date between '"+ageingDates[t][0]+"' AND '"+ageingDates[t][1]+"' and credit > 0 AND activity_code = '"+activityCode+"'  group by invoice_no,activity_code");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                            pset.setString(1,listofAct[i].toString().toUpperCase());
                            java.sql.ResultSet rset = pset.executeQuery();
                            
                            
                            while (rset.next()){
                                String invoiceNo = rset.getString(2);
                                
                                java.sql.Statement st01x = connectDB.createStatement();
                                java.sql.PreparedStatement psetx = connectDB.prepareStatement("select sum(debit),invoice_no from ac_accounts_payable WHERE dealer = ?  AND date <= '"+endDate+"' AND invoice_no = '"+invoiceNo+"' and debit > 0 AND activity_code = '"+activityCode+"'  GROUP BY invoice_no");
                                psetx.setString(1,listofAct[i].toString().toUpperCase());
                                java.sql.ResultSet rsetx = psetx.executeQuery();
                                while (rsetx.next()){
                                    credo = rsetx.getDouble(1);
                                }
                                
                                
                                columnTotals[t] = columnTotals[t] + (rset.getDouble(1) - credo);
                                TotalCount = TotalCount + (rset.getDouble(1) - credo);
                                columnTotals2[t] = columnTotals2[t] + (rset.getDouble(1) - credo);
                                
                            }
                            
                        }
                        
                        
                        for (int t = ageingDates.length - 1; t >= 0; t--) {
                            
                            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[t])));
                        }
                        
                        while (rset111.next()){
                            bals = rset111.getDouble(1);
                            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bals-TotalCount)));
                            
                            
                            Over120Total = Over120Total + (bals-TotalCount);
                            // Over120Total = Over120Total+ rset1.getDouble(1);
                            TurnOver = TotalCount + Curr120;
                            
                            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bals-bals)));
                            OS = OS + unalloc;
                            
                            
                            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(1)));
                            
                            Totals = Totals + rset111.getDouble(1) ;
                        }
                    }
                }
            }
            
            table1.addCell(" ");
            table1.addCell("Total");
            
            //    table.addCell(phrase);
            
            
            
            for (int x = ageingDates.length - 1; x >= 0; x--) {
                
                table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals2[x])));
                
                //      table.addCell(phrase);
                grandTot = grandTot + columnTotals2[x];
            }
            
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals - grandTot)));
            
            //  table.addCell(phrase);
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS)));
            
            //   table.addCell(phrase);
            
            table1.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals)));
            
            // table.addCell(phrase);
            
         /*   table3.addCell(" ");
            table3.addCell("INCOME");
            table3.addCell(" ");
          
            while (rset.next()) {
          
                code = rset.getString(1);
          
                table3.addCell(code);
          
                activity = rset.getString(2);
                table3.addCell(activity.toUpperCase());
          
                credit = credit + rset.getDouble(3);
                credit2 = rset.getDouble(3);
          
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(credit2)));
          
          
            }
          
          
            table3.addCell("Total");
          
            table3.addCell("");
          
          
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(credit)));
          
            table3.addCell(" ");
            table3.addCell("EXPENSES");
            table3.addCell(" ");
          
          
            while (rset2.next()) {
          
                code = rset2.getString(1);
          
                table3.addCell(code);
          
                activity = rset2.getString(2);
                table3.addCell(activity.toUpperCase());
          
                debit = debit + rset2.getDouble(3);
                debit1 = rset2.getDouble(3);
          
                table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit1)));
          
          
            }
          
          
            table3.addCell("Total");
          
            table3.addCell("");
          
          
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit)));
          
            table3.addCell("Profit/Loss "+ks);
          
            table3.addCell("");
          
          
            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(credit-debit)));
          */
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        
        
        textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        
        textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        
        //  textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
        
        // textReport.addPageHeader(pageHeaderModel, integers, columnModel, horizontalAlignments);
        
        //  textReport.drawHorizontalLine(integers);
        
        textReport.addTable(table1, colSizes1, ColumnModelTitle1, horizontalAlignments1);
        
        // textReport.drawHorizontalLine(integers);
        
        //textReport.addTable(table2, colSizes21, ColumnModelTitle, horizontalAlignments1);
        
        // textReport.drawHorizontalLine(integers);
        
        //  textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        //  textReport.drawHorizontalLine(integers);
        
        // textReport.addTable(table31, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        // textReport.drawHorizontalLine(integers);
        
        // textReport.addTable(table11, colSizes21, ColumnModelTitle, horizontalAlignments1);
        
        // textReport.drawHorizontalLine(integers);
        
        
        
        //     textReport.addTable(tablePanel.getTableModel(), integers, columnModel, horizontalAlignments);
        
        
        
        //  textReport.addTable(table311, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        //  textReport.drawHorizontalLine(integers);
        
        //  textReport.writeSimpleReportFooter(simpleReportFooter, true);
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
            
            java.lang.String exportFile = javax.swing.JOptionPane.showInputDialog(new java.awt.Frame(), "Please provide a name for the target file.","Specifying target file name",javax.swing.JOptionPane.YES_NO_OPTION);
            
            javax.swing.table.DefaultTableColumnModel columnModel = new javax.swing.table.DefaultTableColumnModel();
            
            // columnModel.
            
            java.util.Vector columnVector = new java.util.Vector(1,1);
            
            // java.lang.String[] columnModel1 = {"Code","Description","Amount"};
            
            for (int i = 0; i < 9; i++){
                
                columnVector.addElement(columnModel1[i].toString());
                
            }
            
            javax.swing.JTable table2Export = new javax.swing.JTable(table1.getDataVector(),columnVector);
            
            com.afrisoftech.dbadmin.ExcelExport export2Excel = new com.afrisoftech.dbadmin.ExcelExport(table2Export,exportFile);
            
            
            
            // export2Excel.createEXCELDoc();
            
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
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT  CASE WHEN (dealer IS NULL) THEN '-' ELSE dealer END AS dealer, sum(credit-debit) FROM ac_accounts_payable WHERE date::date <= '"+endDate+"' AND activity_code = '"+activityCode+"' GROUP BY  dealer,activity_code HAVING sum(credit-debit) <> 0 AND activity_code = '"+activityCode+"' order by 2 desc");
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            /*}else{
                if(balnc.equalsIgnoreCase("neg")){
                    //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal < 0 order by account_no");
             
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,sum(debit-credit) as balance FROM ac_debtors WHERE date::date <= '"+endDate+"'  group by account_no HAVING sum(balance-credit_bal) < 0 order by 2 desc");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
             
                    }
                }else{
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,sum(debit-credit) as balance FROM ac_debtors  WHERE date::date <= '"+endDate+"' group by account_no HAVING sum(balance-credit_bal) > 0 order by 2 desc");
             
                    // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal > 0 order by account_no");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
             
                    }
                }
            }*/
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
        
    }
    
}
