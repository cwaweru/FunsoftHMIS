package com.afrisoftech.hospayroll;

/*
 * SampleTxtReport.java
 *
 * Created on August 20, 2005, 4:39 PM
 
 
package biz.systempartners.txtreports;
 
/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class DeductionsTxt implements java.lang.Runnable {
    
    java.io.RandomAccessFile txtReportFile = null;
    /** Creates a new instance of SampleTxtReport */
    
    
    biz.systempartners.txtreports.PlainTextTable table3;
    
    String[] columnModel1;
    
    public static java.sql.Connection connectDB = null;
    
    java.lang.String bank = null;
    
    java.lang.String bank1 = null;
    
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    int numberSeq = 0;
    
    double total = 0.00;
    
    double total1 = 0.00;
    
    double balance = 0.00;
    
    double balance1 = 0.00;
    
    double rates = 0.00;
    
    
    
    boolean bal = false;
    java.lang.String memNo = null;
    
    String ks;
    int dataTypeModel[];
    
    
    
    
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.io.File tempFile = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    java.lang.Process wait_for_Pdf2Show;
    
    java.lang.String bankName = null;
    
    java.lang.Runtime rt = null;
    
    boolean previewPrint;
    
    public DeductionsTxt(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox,java.lang.String combox1) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        bank = combox;
        
        bank1 = combox1;
        
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
        
        dataTypeModel = new int[]{
            org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING,
            org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING,
            org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC,
            org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC
                    
        };
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
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments1[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        //  biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_RIGHT};
        
        int horizontalAlignments2[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT};
        
        int horizontalAlignments3[] = {biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
        biz.systempartners.txtreports.Phrase.HORIZONTAL_ALIGNMENT_LEFT,
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
        
        double floats3[] = {5,15,35,15,20,10};
        
        int colSizes3[] = textReport.createTableHeader(6, floats3);
        
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
        String date = null;
        try {
            
            //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");
            
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st4 = connectDB.createStatement();
            
            java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
            
            while(rset4.next()){
                date = rset4.getObject(1).toString();
            }
            try {
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toString().trim()), "MMMM yyyy");
                
                java.lang.String monthString = dateFormatter.getDateString();
                
                Object reportName[] = {monthString.toUpperCase() +" " +bank +  "  LIST: ".toUpperCase()+ "   Printed On " +date  };
                
                
                javax.swing.table.DefaultTableModel headerTitle = new javax.swing.table.DefaultTableModel(reportName,1);
                
                for (int i = 0; i < reportName.length; i++){
                    headerTitle.setValueAt(reportName[i], 0, i);
                }
                
                
                
                columnModel1 = new java.lang.String[]{"No","Staff No", "Staff Name", "Id No", "Amount "+ks, "HRS"};
                
                
                String columnModel[] = {"This", "That", "Then", "when"};
                
                String ColumnModelTitle[] = {""};
                
                String ColumnModelTitle2[] = {"",""};
                
                String ColumnModelTitle3[] = {"","","","","",""};
                
                javax.swing.table.DefaultTableModel headerTableModel = new javax.swing.table.DefaultTableModel(columnModel1,1);
                biz.systempartners.txtreports.PageHeader pageHeaderModel = new biz.systempartners.txtreports.PageHeader(4);
                for (int i = 0; i < columnModel1.length; i++){
                    headerTableModel.setValueAt(columnModel1[i], 0, i);
                    pageHeaderModel.addCell(columnModel1[i]);
                }
                
                int integers[] = colSizes;
                
                //  biz.systempartners.txtreports.PlainTextTable table1 = new biz.systempartners.txtreports.PlainTextTable(5);
                
                // biz.systempartners.txtreports.PlainTextTable table2 = new biz.systempartners.txtreports.PlainTextTable(2);
                
                table3 = new biz.systempartners.txtreports.PlainTextTable(6);
                
                // biz.systempartners.txtreports.PlainTextTable table11 = new biz.systempartners.txtreports.PlainTextTable(5);
                
                biz.systempartners.txtreports.PlainTextTable table31 = new biz.systempartners.txtreports.PlainTextTable(4);
                
                // biz.systempartners.txtreports.PlainTextTable table311 = new biz.systempartners.txtreports.PlainTextTable(5);
                
                
                double debit = 0.00;
                double credit = 0.00;
                double debit1 = 0.00;
                double credit2 = 0.00;
                int j = 0;
                java.lang.String activity = null;
                java.lang.String code = null;
                
                
                try{
                    
                    double diff = 0.00;
                    
                    
                    java.sql.Statement st = connectDB.createStatement();
                    java.sql.Statement st2 = connectDB.createStatement();
                    java.sql.Statement st21 = connectDB.createStatement();
                    java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                    int numberSeq = 0;
                    for (int x = 0; x < listofStaffNos.length; x++) {
                        debit1 = 0;
                        double rate = 0.00;
                        int staff = 0;
                        
                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st311 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset = st.executeQuery("select np.employee_no,np.first_name||' '||np.middle_name||' '||np.last_name,np.id_no,'' from master_file np where np.employee_no = '"+listofStaffNos[x]+"' order by np.employee_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                        java.sql.ResultSet rset1 = st3.executeQuery("select sum(np.amount),sum(hoursdays) as days from posting np where np.description = '"+bank+"' and np.staff_no = '"+listofStaffNos[x]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank1+"' group by staff_no order by np.staff_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                        java.sql.ResultSet rset31 = st31.executeQuery("select sum(np.balance),np.acc_bal from sacco_deductions np where np.sacco_name = '"+bank+"' and np.staff_no = '"+listofStaffNos[x]+"' group by np.staff_no,np.acc_bal");
                        java.sql.ResultSet rset311 = st311.executeQuery("select count(staff_no) from sacco_deductions np where np.sacco_name = '"+bank+"' and np.staff_no = '"+listofStaffNos[x]+"' group by np.staff_no");
                        
                        
                        while (rset.next()) {
                            numberSeq = numberSeq+1;
                            
                            code = rset.getString(1);
                            
                            table3.addCell(numberSeq);
                            table3.addCell(dbObject.getDBObject(rset.getObject(1).toString(),"-"));
                            table3.addCell(dbObject.getDBObject(rset.getObject(2).toString(),"-"));
                            table3.addCell(dbObject.getDBObject(rset.getObject(3).toString(),"-"));
                            
                        
                        while (rset1.next()){
                            debit1 = rset1.getDouble(1);
                            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit1)));
                            debit = debit + debit1;
                            total = total + rset1.getDouble(1);
                            total1 = rset1.getDouble(1);
                            rate = rset1.getDouble(2);
                            table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rate)));
                        }
                    }
                    }
                    table3.addCell("");
                    table3.addCell("");
                    table3.addCell("Total");
                    table3.addCell("");
                    
                    table3.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit)));
                    table3.addCell("");
                    
                } catch(java.sql.SQLException SqlExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                    
                }
                
                //   for (int i = 0; i < 1; i++) {
                
                textReport.addTable(table22, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
                
                textReport.drawHorizontalLine(integers);
                
                textReport.addTable(headerTitle, colSizeTitle, ColumnModelTitle, horizontalAlignmentsTitle);
                
                textReport.drawHorizontalLine(integers);
                
                //  textReport.addTable(table2, colSizes2, ColumnModelTitle2, horizontalAlignments2);
                
                //  textReport.drawHorizontalLine(integers);
                
                textReport.addTable(headerTableModel, colSizes3, ColumnModelTitle3, horizontalAlignments3);
                
                textReport.addPageHeader(pageHeaderModel, integers, columnModel, horizontalAlignments);
                
                textReport.drawHorizontalLine(integers);
                
                //  textReport.addTable(table1, integers, columnModel, horizontalAlignments1);
                
                // textReport.drawHorizontalLine(integers);
                
                textReport.addTable(table3, colSizes3, ColumnModelTitle3, horizontalAlignments3);
                
                textReport.drawHorizontalLine(integers);
                
                
                
                
            } catch(java.text.ParseException prs) {
                prs.printStackTrace();
            }
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
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
            
            java.util.Vector columnVector = new java.util.Vector(1,1);
            
            
            for (int i = 0; i < 6; i++){
                
                columnVector.addElement(columnModel1[i].toString());
                
            }
            
            javax.swing.JTable table2Export = new javax.swing.JTable(table3.getDataVector(),columnVector);
            
            com.afrisoftech.dbadmin.ExcelExport export2Excel = new com.afrisoftech.dbadmin.ExcelExport(table2Export,exportFile, dataTypeModel);
            
            
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
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where description ='"+bank+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank1+"'  and amount > 0 order by staff_no");
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                System.out.println(rSet1.getObject(1).toString());
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        System.out.println(listofStaffNos);
        
        
        return listofStaffNos;
        
        
        
    }
    
}
