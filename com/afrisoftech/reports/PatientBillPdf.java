//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;


public class PatientBillPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.lang.String endDate = null;
    String ks;
    java.lang.String VisitId = null;
    double osBalance = 0.00;
    double osBalance1 = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //  com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PatientBillPdf(java.sql.Connection connDb, java.lang.String combox,java.util.Date begindate, java.lang.String visitid) {
        MNo = combox;
        
        beginDate = begindate;
        
        connectDB = connDb;
        
        VisitId = visitid;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // beginDate = begindate;
        
        // endDate = endate;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf(MNo);
            
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
    
    
    public void generatePdf(java.lang.String memNo) {
        
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
            int bodySize = 0;
            String bodyFont = null;
            float Widths = 0;
            float Heights = 0;
            float Margins = 0;
            String footers = null;
            try {
                connectDB.setAutoCommit(false);
                
                java.sql.Statement stm1 = connectDB.createStatement();
                java.sql.ResultSet rse1 = stm1.executeQuery("select body_font,body_size from report_pref");
                while (rse1.next()){
                    // titleFont = rse1.getInt(1);
                    bodyFont = rse1.getString(1);
                    bodySize = rse1.getInt(2);
                    // Heights = rse1.getFloat(4);
                    //  Margins = rse1.getFloat(5);
                }
            }catch(java.sql.SQLException sq){
                
                try {
                    connectDB.rollback();
                }catch (java.sql.SQLException sql){
                    //   javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(sq.getMessage());
                //   javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                
            }
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    String Messg = null;
                    
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select name from interim_footer");
                        
                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            Messg = rset2.getObject(1).toString();
                        }
                        
                        
                        //  while(rset4.next())
                        //    date = rset4.getObject(1).toString();
                        
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(""+Messg+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.ALIGN_CENTER;
                        //  headerFoter.setRight(5);
                        docPdf.setFooter(footer);
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {25,25,20,15,15,15};
                        
                        table.setWidths(headerwidths);
                        //table..setWidths(headerwidths);
                        table.setWidthPercentage((100));
                        // table.setHeaderRows(1);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement stc = connectDB.createStatement();
                            java.sql.Statement stb = connectDB.createStatement();
                            java.sql.Statement sta = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st32 = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();
                             //java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                             java.sql.ResultSet rset22 = st32.executeQuery("SELECT DISTINCT header_name from pb_header");
                          
                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2x.next()){
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rsetc = stc.executeQuery("SELECT DISTINCT discharge,date_admitted from hp_admission where patient_no = '"+memNo+"'  AND visit_id = '"+VisitId+"' "
                                    + "UNION ALL SELECT DISTINCT false::boolean,date_received from hp_mortuary WHERE annual_no = '"+VisitId+"' order by 2 desc LIMIT 1");
                            
                            java.sql.ResultSet rsetb = stb.executeQuery("SELECT DISTINCT CASE WHEN (discharge_date::date IS NULL) THEN ('now'::date  - date_admitted) ELSE (discharge_date::date - date_admitted) END AS date from hp_admission WHERE "
                                    + "patient_no = '"+memNo+"' AND visit_id = '"+VisitId+"' "
                                    + "UNION ALL SELECT DISTINCT CASE WHEN (discharge_date::date IS NULL) THEN ('now'::date  - date_received) ELSE (discharge_date::date - date_received) END AS date from hp_mortuary WHERE "
                                    + " annual_no = '"+VisitId+"' GROUP BY 1");
                            
                            java.sql.ResultSet rseta = sta.executeQuery("SELECT DISTINCT ad.ward,ad.bed_no,ad.doctor ,ad.date_admitted,ad.discharge_date::date from hp_admission ad where ad.patient_no = '"+memNo+"'  AND visit_id = '"+VisitId+"' "
                                    + "  UNION ALL SELECT DISTINCT ad.deseased_sourse,'',ad.certified_by ,ad.date_received,ad.discharge_date::date FROM hp_mortuary ad WHERE  annual_no = '"+VisitId+"'");
                            java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT patient_no,initcap(second_name||' '||first_name||' '||last_name),address,residence,tel_no,payer,description,category FROM hp_inpatient_register where patient_no = '"+memNo+"' "
                                    + "UNION ALL SELECT DISTINCT annual_no,initcap(patient_name),address,residence,tel,broughtby,'',deseased_sourse FROM hp_mortuary WHERE annual_no = '"+memNo+"'");
                            java.sql.ResultSet rset1 = st1.executeQuery(" select date::date,initcap(service) as service,dosage::int4,reference,sum(debit-credit) from hp_patient_card where patient_no = '"+memNo+"' "
                                    + " AND visit_id = '"+VisitId+"' and paid = 'false' AND (reference IS NULL OR reference != '') and service not ilike 'Receipt' AND service not ilike 'Receipt Adj'  AND service not ilike 'Discou%' and service != 'N.H.I.F' and paid = 'false' GROUP BY date::date,initcap(service),dosage,reference order by date::date,reference");
                            java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit - credit) from hp_patient_card where patient_no = '"+memNo+"'  AND visit_id = '"+VisitId+"' and (service not ilike 'Receipt' OR service not ilike 'Receipt Adj' ) AND service not ilike 'Discou%' and paid = 'false' and service != 'N.H.I.F'");
                            java.sql.ResultSet rset11 = st11.executeQuery(" select date::date,initcap(service) as service,dosage::int4,requisition_no,sum(credit-debit) from hp_patient_card where patient_no = '"+memNo+"'  AND visit_id = '"+VisitId+"' AND"
                                    + " (service = 'N.H.I.F' OR service ilike 'Receipt' OR service ilike 'Receipt Adj' OR service ilike 'Discou%') AND invoice_no NOT LIKE 'I%' GROUP BY date::date,initcap(service),dosage,requisition_no order by date::date");
                            java.sql.ResultSet rsetTotals1 = st21.executeQuery("select sum(credit-debit) from hp_patient_card where patient_no = '"+memNo+"'  AND visit_id = '"+VisitId+"' and service = 'N.H.I.F' AND service ilike 'Receipt' AND service ilike 'Receipt Adj'  AND service ilike 'Discou%' AND invoice_no NOT LIKE 'I%'");
                            
                            
                            while (rset22.next()){
                                table.getDefaultCell().setColspan(6);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                            }
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Interim Invoice", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            while (rset.next()){
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No:  "+memNo, pFontHeader1);
                                table.addCell(phrase);
                                
                                while (rseta.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Ward: "+dbObject.getDBObject(rseta.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    //  while (rset.next())
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Patient Name: "+dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    // while (rseta.next())
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Bed No: "+dbObject.getDBObject(rseta.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    // while (rset.next())
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Address: "+dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    // while (rseta.next())
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Doctor: "+dbObject.getDBObject(rseta.getObject(3), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    // while (rset.next())
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Town  "+dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //while (rseta.next())
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Adm Date: "+dbObject.getDBObject(rseta.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("TEl No. "+dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    // while (rseta.next())
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Discharge Date: "+dbObject.getDBObject(rseta.getObject(5), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    //while (rset.next())
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payer : "+dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Patient Category : "+dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Scheme Name : "+dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }
                            //      if(rsetc.getBoolean(1) == true){
                            while (rsetb.next()){
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("No.of Days : "+dbObject.getDBObject(rsetb.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            //  phrase = new Phrase("Date",pFontHeader1);
                            phrase = new Phrase("Date ",pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Description",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Qty",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Ref",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amt.",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Bal. "+ks,pFontHeader1);
                            table.addCell(phrase);
                            
                            while (rset1.next()){
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                osBalance = osBalance + rset1.getDouble(5);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                //   current = current + osBalance;
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            while (rsetTotals.next()) {
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Invoice Amount", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);
                                
                                // table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                //phrase = new Phrase(" ");
                                
                                
                                
                            }
                            
                            while (rset11.next()){
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(4), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(5)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                osBalance1 = osBalance1 + rset11.getDouble(5);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);
                                //   current = current + osBalance;
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            while (rsetTotals1.next()) {
                                
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Receipts", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals1.getString(1)),pFontHeader);
                                
                                //table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                //phrase = new Phrase(" ");
                                
                                
                                
                            }
                            
                            //  while (rsetTotals.next()) {
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Amount", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);
                            
                            // table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance - osBalance1)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            //phrase = new Phrase(" ");
                            
                            
                            
                            // }
                            docPdf.add(table);
                            
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
            
            docPdf.close();
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
}





