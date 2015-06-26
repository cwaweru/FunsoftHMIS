//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.records.reports;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;


public class MidwivesReturnsPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    int numberSeq = 0;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void MidwivesReturnsPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
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
                        
                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
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
                        //docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    int dayT = 0;
                    int monT = 0;
                    int cashT = 0;
                    int schemeT = 0;
                    int selfT = 0;
                    int copT = 0;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {40,10,10,10,10,10,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        
                        
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths2[] = {30,10,10,10,10,10,10};
                        
                        table2.setWidths(headerwidths2);
                        
                        table2.setWidthPercentage((100));
                        
                        table2.setHeaderRows(1);
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        try {
                            int deliveries = 0;
                            int babies = 0;
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");
                                
                                java.lang.String monthString = dateFormatter.getDateString();
                                
                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");
                                
                                java.lang.String yearString = dateFormatters.getDateString();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.ResultSet rset12 = st31.executeQuery("SELECT DISTINCT count(mother_serial_no) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                while(rset12.next()){
                                    deliveries = rset12.getInt(1);
                                }
                                
                                java.sql.Statement st311 = connectDB.createStatement();
                                java.sql.ResultSet rset121 = st311.executeQuery("SELECT count(state_of_infant_at_birth) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                while(rset121.next()){
                                    babies = rset121.getInt(1);
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(""+compName.toUpperCase(),pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("MIDWIVES MONTHLY RETURNS", pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                phrase = new Phrase("For the Month of "+monthString+", "+yearString,pFontHeader1);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("No. of Deliveries - "+deliveries ,pFontHeader1);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("No. of Babies - "+babies ,pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("",pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Infants",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("",pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("",pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Male",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Female",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Total",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("",pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                phrase = new Phrase("Type of Birth",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("No. of Mothers",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Live \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Still \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Live \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Still \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Live \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Still \n Births",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Maternal Deaths",pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Neo Natal Deaths",pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                
                                
                                //phrase = new Phrase("Budget Cumm. Adm. for the month",pFontHeader);
                                //table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // table.addCell("Amount KShs.");
                            
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            
                            int totalMaleL = 0;
                            int totalFemaleL = 0;
                            int MaleTotalInf = 0;
                            int FemaleTotalInf = 0;
                            int totalLive = 0;
                            int totalInfant = 0;
                            int totalMothers = 0;
                            int totalMatDeaths = 0;
                            int totalNeoDeaths = 0;
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st31 = connectDB.createStatement();
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declalition
                                int newCount = 0;
                                int oldCount = 0;
                                int maleLive = 0;
                                int femaleLive = 0;
                                int maleInfant = 0;
                                int femaleInfant = 0;
                                String Gender = null;
                                int motherCount = 0;
                                int matDeaths = 0;
                                int neoDeaths = 0;
                                String babyState = null;
                                
                                java.sql.ResultSet rset = st.executeQuery("SELECT count(state_of_infant_at_birth),baby_gender,state_of_infant_at_birth FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND type_of_birth = '"+listofAct[i]+"' GROUP BY 2,3 ORDER BY baby_gender DESC");
                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT count(mother_serial_no) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND type_of_birth = '"+listofAct[i]+"'");
                                java.sql.ResultSet rset21 = st21.executeQuery("SELECT DISTINCT count(mother_serial_no) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND type_of_birth = '"+listofAct[i]+"' AND admission_outcome ilike 'Died'");
                                java.sql.ResultSet rset31 = st31.executeQuery("SELECT count(neo_natal_status) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND type_of_birth = '"+listofAct[i]+"' AND neo_natal_status ilike 'Died'");
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+listofAct[i], pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                while (rset.next()) {
                                    Gender = rset.getString(2);
                                    newCount = rset.getInt(1);
                                    babyState = rset.getString(3);
                                    if(Gender.equalsIgnoreCase("Male") && babyState.equalsIgnoreCase("Live Birth")){
                                        maleLive = newCount;
                                    }else{
                                        if(Gender.equalsIgnoreCase("Male") && babyState.equalsIgnoreCase("Still Birth")){
                                            maleInfant = newCount;
                                        }else{
                                            if(Gender.equalsIgnoreCase("Female") && babyState.equalsIgnoreCase("Live Birth")){
                                                femaleLive = newCount;
                                            }else{
                                                if(Gender.equalsIgnoreCase("Female") && babyState.equalsIgnoreCase("Still Birth")){
                                                    femaleInfant = newCount;
                                                }else{
                                                    if(Gender.equalsIgnoreCase("Female") && babyState.equalsIgnoreCase("One Dead")){
                                                        femaleInfant = newCount;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                while (rset1.next()) {
                                    motherCount = rset1.getInt(1);
                                }
                                
                                while (rset21.next()) {
                                    matDeaths = rset21.getInt(1);
                                }
                                
                                while (rset31.next()) {
                                    neoDeaths = rset31.getInt(1);
                                }
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(motherCount)),pFontHeader1);
                                totalMothers = totalMothers+motherCount;
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(maleLive)),pFontHeader1);
                                totalMaleL = totalMaleL+maleLive;
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(maleInfant)),pFontHeader1);
                                MaleTotalInf = MaleTotalInf+maleInfant;
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(femaleLive)),pFontHeader1);
                                totalFemaleL = totalFemaleL+femaleLive;
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(femaleInfant)),pFontHeader1);
                                FemaleTotalInf = FemaleTotalInf+femaleInfant;
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(maleLive+femaleLive)),pFontHeader1);
                                totalLive = totalLive+(maleLive+femaleLive);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(maleInfant+femaleInfant)),pFontHeader1);
                                totalInfant = totalInfant+(maleInfant+femaleInfant);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matDeaths)),pFontHeader1);
                                totalMatDeaths = totalMatDeaths+matDeaths;
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(neoDeaths)),pFontHeader1);
                                totalNeoDeaths = totalNeoDeaths+neoDeaths;
                                table.addCell(phrase);
                                
                            }
                            // }
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                            table.getDefaultCell().setMinimumHeight(20);
                            phrase = new Phrase("Total", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMothers)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMaleL)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(MaleTotalInf)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalFemaleL)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(FemaleTotalInf)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalLive)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalInfant)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMatDeaths)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalNeoDeaths)),pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(10);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" Cases Reffered to Hospital or Medical Officer (Not Applicable to Hospital)", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            docPdf.add(table);
                            //docPdf.add(table1);
                            //docPdf.add(table2);
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
    
    
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT delivery_status_desc FROM rh.delivery_status ORDER BY delivery_status_desc");
            
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





