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
public class BsheetTxt implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    biz.systempartners.txtreports.PlainTextTable table3;
    
    String[] columnModel1;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String memNo = null;
    
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
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
    
    public BsheetTxt(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // memNo = combox;
        
        beginDate = begindate;
        
        endDate = endate;
        
        connectDB = connDb;
        
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
        // biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
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
        
        Object reportName[] = { "PROFIT & LOSS AS AT : " +dateFormat.format(endDate1).toUpperCase() };
        
        
        
        javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
        
        for (int i = 0; i < reportName.length; i++){
            headerTitle.setValueAt(reportName[i], 0, i);
        }
        
        
        
        
        columnModel1 = new java.lang.String[]{"Code", "Description", "Amount"};
        
        
        String columnModel[] = {"This", "That", "Then"};
        
        String ColumnModelTitle[] = {""};
        
        String ColumnModelTitle2[] = {"",""};
        
        String ColumnModelTitle3[] = {"","",""};
        
        javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
        biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(3);
        for (int i = 0; i < columnModel1.length; i++){
            headerTableModel.setValueAt(columnModel1[i], 0, i);
            pageHeaderModel.addCell(columnModel1[i]);
        }
        
        int integers[] = colSizes;
        
        biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        table3 = new biz.systempartners.txtreports.PlainTextTable(3);
        
        biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(1);
        
        biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(3);
        
        // biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(5);
        
        
        double debit = 0.00;
        double credit = 0.00;
        double debit1 = 0.00;
        double credit2 = 0.00;
        int j = 0;
        java.lang.String activity = null;
        java.lang.String code = null;
        
        
        try{
            java.sql.Statement st = connectDB.createStatement();
            
            java.sql.Statement st2 = connectDB.createStatement();
            
            java.sql.Statement st3 = connectDB.createStatement();
            
            java.sql.Statement st7 = connectDB.createStatement();
            
            java.sql.ResultSet rset = st.executeQuery("select pb.code,pb.activity,sum(ac.credit-ac.debit) from tb_itemized_summary ac,pb_activity pb where pb.code = ac.gl_code and  pb.category_class ilike 'pli%' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  group by pb.code,pb.activity order by pb.code");
            java.sql.ResultSet rset2 = st2.executeQuery("select pb.code,pb.activity,sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_activity pb where pb.code = ac.gl_code and pb.category_class ilike 'ple%' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  group by pb.code,pb.activity order by pb.code");
            
            table3.addCell(" ");
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
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        
        //   for (int i = 0; i < 1; i++) {
        
        textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        
        textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
        
        textReport.drawHorizontalLine(integers);
        
        textReport.addTable(headerTableModel, integers, columnModel, horizontalAlignments);
        
        textReport.addPageHeader(pageHeaderModel, integers, columnModel, horizontalAlignments);
        
        textReport.drawHorizontalLine(integers);
        
        // textReport.addTable(table1, integers, columnModel, horizontalAlignments1);
        
        // textReport.drawHorizontalLine(integers);
        
        //textReport.addTable(table2, colSizes21, ColumnModelTitle, horizontalAlignments1);
        
        // textReport.drawHorizontalLine(integers);
        
        textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
        
        textReport.drawHorizontalLine(integers);
        
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
            
            for (int i = 0; i < 3; i++){
                
                columnVector.addElement(columnModel1[i].toString());
                
            }
            
            javax.swing.JTable table2Export = new javax.swing.JTable(table3.getDataVector(),columnVector);
            
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ba' order by main_code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        
        return listofActivities;
    }
    
    
    public java.lang.Object[] getListofActivities1() {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        
        try {
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'bl' order by main_code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities1 = listActVector.toArray();
        
        return listofActivities1;
    }
    
    public java.lang.Object[] getListofActivities2() {
        
        java.lang.Object[] listofActivities2 = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        
        try {
            
            //  java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/insurance","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ble' order by main_code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities2 = listActVector.toArray();
        
        System.out.println("Total list of activities = "+listofActivities2.length);
        
        return listofActivities2;
    }
    
}
