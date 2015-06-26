//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;


public class DailyWardStatementPdf implements java.lang.Runnable {
    
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    int numberSeq = 0;
    
    java.lang.String wardName = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DailyWardStatementPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String ward) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        wardName = ward;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberListPdf().MemberListPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(200);
                
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
    
    public java.lang.String getDateLable() {
        
        java.lang.String date_label = null;
        
        java.lang.String month_now_strs = null;
        
        java.lang.String date_now_strs = null;
        
        java.lang.String year_now_strs = null;
        
        java.lang.String minute_now_strs = null;
        
        java.lang.String hour_now_strs = null;
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        java.util.Calendar calinst = java.util.Calendar.getInstance();
        
        java.util.Date date_now = calinst.getTime();
        
        int date_now_str = date_now.getDate();
        
        int month_now_str = date_now.getMonth();
        
        int year_now_str = date_now.getYear();
        
        int hour_now_str = date_now.getHours();
        
        int minute_now_str = date_now.getMinutes();
        
        int year_now_abs = year_now_str - 100;
        
        if (year_now_abs < 10) {
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    
    public void generatePdf() {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String District = null;
                    String Region = null;
                    
                    String date = null;
                    try {
                        
                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {10,40,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths1[] = {6,30,10,10};
                        
                        table1.setWidths(headerwidths1);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(1);
                        
                        
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(6);
                            
                            phrase = new Phrase(compName,pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("DAILY WARD STATEMENT", pFontHeader2);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("WARD : "+wardName,pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("DATE : "+dateFormat.format(endDate1),pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        try {
                            int totalMale = 0;
                            int totalFemale = 0;
                            int newMaleTotal = 0;
                            int newFemaleTotal = 0;
                            int oldMaleTotal = 0;
                            int oldFemaleTotal = 0;
                            //java.lang.Object[] listofAct = this.getListofStaffNos();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st8 = connectDB.createStatement();
                            
                            // for (int i = 0; i < listofAct.length; i++) {
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("UNIT \n NUMBER",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("ADMISSIONS \n Name",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AGE",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("SEX",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("STATUS",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("REMARKS",pFontHeader);
                            table.addCell(phrase);
                            
                            // variables declalition
                            String pNo = "-";
                            String pName = "-";
                            int ages = 0;
                            String pGender = "-";
                            String pStatus = "-";
                            
                            int unDischarged = 0;
                            int tAdmissions = 0;
                            int tDischarges = 0;
                            int tDeaths = 0;
                            int bedsAvail = 0;
                            int tIn = 0;
                            int tOut = 0;
                            
                            
                            
                            java.sql.ResultSet rset = st.executeQuery("SELECT distinct patient_no,patient_name,ROUND(pat_age) as pat_age,gender,admission_reason FROM hp_admission WHERE date_admitted::date = '"+endDate+"' AND ward = '"+wardName+"' ORDER BY patient_no ASC");
                            // java.sql.ResultSet rset1 = st1.executeQuery("SELECT SUM(count_no),gender FROM patient_analysis WHERE input_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND age::numeric BETWEEN '"+lowerAge+"' AND '"+upperAge+"' AND comments ilike 'old' GROUP BY 2 ORDER BY gender DESC");
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            while (rset.next()) {
                                pNo = rset.getString(1);
                                pName = rset.getString(2);
                                ages = rset.getInt(3);
                                pGender = rset.getString(4);
                                pStatus = rset.getString(5);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase(pNo,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(pName,pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                if (ages < 1) {
                                    phrase = new Phrase("<1", pFontHeader1);

                                    table.addCell(phrase);
                                } else {
                                    //     phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ages)), pFontHeader1);

                                    table.addCell(phrase);
                                }
                                phrase = new Phrase(pGender,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(pStatus,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(" ",pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("UNIT \n NUMBER",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("TRANSFERS INTO WARD \n Name",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AGE",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("GENDER",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("STATUS",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("REMARKS",pFontHeader);
                            table.addCell(phrase);
                            
                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT distinct patient_no,patient_name,ROUND(pat_age),gender,reason_for_transfer FROM hp_ward_to_ward_transfer WHERE date::date = '"+endDate+"' AND transfered_to = '"+wardName+"' ORDER BY patient_no ASC");
                            
                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            while (rset1.next()) {
                                pNo = rset1.getString(1);
                                pName = rset1.getString(2);
                                ages = rset1.getInt(3);
                                pGender = rset1.getString(4);
                                pStatus = rset1.getString(5);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase(pNo,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(pName,pFontHeader1);
                                table.addCell(phrase);
                                if (ages < 1) {
                                    phrase = new Phrase("<1", pFontHeader1);

                                    table.addCell(phrase);
                                } else {
                                    //     phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ages)), pFontHeader1);

                                    table.addCell(phrase);
                                }
                                phrase = new Phrase(pGender,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(pStatus,pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(" ",pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Remained \n Previous Day",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Total \n Admissions",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Total \n Discharges",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Total \n Deaths",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Remained at \n Midnight",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Empty \n Beds",pFontHeader);
                            table.addCell(phrase);
                            
                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE date_admitted::date < '"+beginDate+"' AND (discharge_date::date >= '"+endDate+"' OR discharge_date::date IS NULL) AND ward = '"+wardName+"'");
                            while(rset2.next()){
                                unDischarged = rset2.getInt(1);
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE date_admitted::date = '"+endDate+"' AND ward = '"+wardName+"' AND discharge = false");//  AND transaction_type  ILIKE 'Admission' OR transaction_type  ILIKE 'Transfer In'");
                            while(rset3.next()){
                                tAdmissions = rset3.getInt(1);
                            }
                            
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '"+endDate+"' AND ward = '"+wardName+"'  AND transaction_type  ILIKE 'Discharged'");
                            while(rset4.next()){
                                tDischarges = rset4.getInt(1);
                            }
                            
                            java.sql.ResultSet rset5 = st5.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '"+endDate+"' AND ward = '"+wardName+"'  AND transaction_type  ILIKE 'Died'");
                            while(rset5.next()){
                                tDeaths = rset5.getInt(1);
                            }
                            
                            java.sql.ResultSet rset6 = st6.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '"+endDate+"' AND transfered_to = '"+wardName+"'");
                            while(rset6.next()){
                                tIn = rset6.getInt(1);
                            }
                            
                            java.sql.ResultSet rset7 = st7.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '"+endDate+"' AND transfered_from = '"+wardName+"'");
                            while(rset7.next()){
                                tOut = rset7.getInt(1);
                            }
                            
                            java.sql.ResultSet rset8 = st8.executeQuery("SELECT distinct count(bed_no) FROM hp_bed_setup WHERE occupied = false AND ward = '"+wardName+"'");
                            while(rset8.next()){
                                bedsAvail = rset8.getInt(1);
                            }
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischarged)),pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tAdmissions)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tDischarges)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tDeaths)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf((unDischarged+tAdmissions)-(tDischarges+tDeaths))),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bedsAvail)),pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase(" ",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Transfer In "+new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tIn)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Dangerously-ill Patients "+new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Transfer Out "+new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tOut)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Floor Patients "+new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)),pFontHeader1);
                            table.addCell(phrase);
                            
                            docPdf.add(table);
                            // docPdf.add(table1);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        // }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                } catch(java.io.FileNotFoundException fnfExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
            
             
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
    /*public java.lang.Object[] getListofStaffNos1() {
     
        java.lang.Object[] listofStaffNos1 = null;
     
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
     
     
        try {
     
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
     
            java.sql.Statement stmt1 = connectDB.createStatement();
     
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ward_code FROM hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY ward_code");
     
            while (rSet1.next()) {
     
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
     
            }
     
        }catch (java.sql.SQLException sqlExec) {
     
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
     
        }
     
     
        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
    }
     
     
    public java.lang.Object[] getListofStaffNos11() {
     
        java.lang.Object[] listofStaffNos11 = null;
     
        java.util.Vector listStaffNoVector11 = new java.util.Vector(1,1);
     
     
        try {
     
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
     
            java.sql.Statement stmt1 = connectDB.createStatement();
     
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT mode_of_payment FROM hp_admission WHERE discharge_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY mode_of_payment");
     
            while (rSet1.next()) {
     
                listStaffNoVector11.addElement(rSet1.getObject(1).toString());
     
            }
     
        }catch (java.sql.SQLException sqlExec) {
     
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
     
        }
     
     
        listofStaffNos11 = listStaffNoVector11.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos11;
    }*/
    
}





