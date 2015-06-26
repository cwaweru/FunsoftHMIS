//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.nursingreports;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.util.Vector;


public class PatientHeadToToeAssessment implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
   static java.sql.Connection connectDB = null;
static org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    
    public java.lang.String dbUserName = null;
    
    String ks;
   
    
    
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    Vector nursingcare=new Vector();
    
    public void PatientHeadToToeAssessment(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB,Vector vector) {
       nursingcare=vector;
        dbObject = new com.afrisoftech.lib.DBObject();
       connectDB = connDb;
        pConnDB = pconnDB;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new EntranceFeePdf().EntranceFeePdf();
        
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
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        java.sql.Statement st2 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2.next()){
                            ks = rset2.getObject(1).toString();
                        }
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        //while(rset2.next())
                        //   compName = rset2.getObject(1).toString();
                        
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+"",pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        // headerFoter.setRight(5);
                        // docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("PATIENT SYSTEMIC ASSESSMENT : ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {20,17,20,15,10,15,10,18};
                        
                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));
                        
                        
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table1.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(8);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {8,15,15,15,10,10,8,20};
                        
                        table.setWidths(headerwidths);
                        table.setHeaderRows(8);
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        String header=null;
                        
                        
                             header="PATIENT ASSESSMENT FOR PATIENT NO :- "+nursingcare.elementAt(0)+"  DATE :-"+nursingcare.elementAt(3)+" \n\n"
                                     + ""+nursingcare.elementAt(4)+"\n\n\n";
                        
                        
                        Phrase phrase = new Phrase(header, pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on :\n " +date,pFontHeader);
                        table.addCell(phrase);
                        

                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double occ = 0.00;
                        double total = 0.00;
                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            
                            System.out.println(listofAct.length);
                           ////start of vital signs population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                        + "SELECT  date, vital_signs1, vital_signs2, \n" +
                                        "       vital_signs3, vital_signs4, vital_signs5\n" +
                                        "  FROM nursing.vital_signs where patient_no='"+nursingcare.elementAt(0)+"'and "
                                    + " visit_id='"+nursingcare.elementAt(1)+"' and date>='"+nursingcare.elementAt(3)+"'; ");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("1:- VITAL SIGNS", pFontHeader);
                                table.addCell(phrase);
                               while (rset3.next()){
                                 phrase = new Phrase("\t VITAL SIGNS for  "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                               
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(4)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(5)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(6)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.err.println("the  vital sings exception is "+vitalExec);
                        }
                            ////end of vital signs population in table
                            
                             ////start of neurological population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT  transaction_date, neuro1, neuro2, neuro3, neuro4, neuro5\n" +
                                "  FROM nursing.neurological where patient_no='"+nursingcare.elementAt(0)+"' and transaction_date>='"+nursingcare.elementAt(3)+"' order by server_date desc ;");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("2:- NEUROLOGICAL OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                 
                            while (rset3.next()){
                            phrase = new Phrase("\t NEUROLOGICAL OBSERVATIONS FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(4)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(5)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+dbObject.getDBObject(rset3.getObject(6)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.err.println("the  NEUROLOGICAL OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of neurological population in table

                                ////start of RESPIRATORY population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT  transaction_date,  resp1, resp2\n" +
                                    "  FROM nursing.respiration where transaction_date>='"+nursingcare.elementAt(3)+"'"
                                    + " and patient_no='"+nursingcare.elementAt(0)+"' and visit_id='"+nursingcare.elementAt(1)+"' order by server_date desc;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("3:- RESPIRATION OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                 
                            while (rset3.next()){
                                phrase = new Phrase(" RESPIRATION OBSERVATIONS FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") );
                                table.addCell(phrase);
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.err.println("the  RESPIRATION OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of RESPIRATORY population in table
                            
                             ////start of CADIO VASCULAR population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT  transaction_date,  cadio1 \n" +
                    "  FROM nursing.cadiovascular_view WHERE   transaction_date>='"+nursingcare.elementAt(3)+"' "
                                    + " and  patient_no='"+nursingcare.elementAt(0)+"' and visit_id='"+nursingcare.elementAt(1)+"' ORDER BY server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("4:- CADIO-VASCULAR OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                
                            while (rset3.next()){
                                 phrase = new Phrase("\t CADIO-VASCULAR OBSERVATIONS FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  CADIO VASCULAR OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of cardo vascular population in table
                            
                              ////start of GASTRO-INTESTINAL population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT transaction_date, gastro1, gastro2\n" +
            "  FROM nursing.\"Gastrointestinal\" "
                                    + " WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"'  ORDER BY server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("5:- GASTRO-INTESTINAL OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                
                            while (rset3.next()){
                                 phrase = new Phrase("\t GASTRO-INTESTINAL FOR  "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                            
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  GASTRO-INTESTINAL OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of GASTRO-INTESTINAL population in table
                            
                             ////start oF GENITO URINARY population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT transaction_date,genito1, genito2\n" +
                        "  FROM nursing.genitourinary WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"' ORDER BY  server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("6:- GENITO-URINARY SYSTEM OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                
                            while (rset3.next()){
                                phrase = new Phrase("\t GENITO-URINARY  "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                            
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  GENITO-URINARY OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of GENITO-URINARY population in table
                            
                             ////start of MUSCULO-SKELETAL population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT transaction_date, musco1, musco2 " +
                    "  FROM nursing.\"Musculoskeletal\" "
                                    + " WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"' ORDER BY  server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("7:- MUSCULO-SKELETAL OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                
                            while (rset3.next()){
                                  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("\t MUSCULO-SKELETAL FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  MUSCULO-SKELETAL OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of MUSCULO-SKELETAL population in table
                            
                            ////start of INTEGUMENTARY population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT transaction_date,integumentry\n" +
                                "  FROM nursing.\"Integumantary\" "
                                    + " WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"' ORDER BY  server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("8:- INTEGUMENTARY OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                 
                            while (rset3.next()){
                                  phrase = new Phrase("\t INTEGUMENTARY  "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                           
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                                         
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  INTEGUMENTARY OBSERVATIONS exception is "+vitalExec);
                        }
                            ////end of INTEGUMENTARY population in table
                            
                             ////start of PRESSURE SORE population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT  transaction_date,pressure1, pressure2\n" +
                            "  FROM nursing.\"Pressure_sore\""
                                    + " WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"' ORDER BY  server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("9:- PRESSURE SORE OBSERVATIONS", pFontHeader);
                                table.addCell(phrase);
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                  
                            while (rset3.next()){
                                phrase = new Phrase("\t PRESSURE SORE FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                             
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(3)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  PRESSURE SORE exception is "+vitalExec);
                        }
                            ////end of PRESSURE SORE population in table
                            
                             ////start of TURNING population
                            try{
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                          
                            java.sql.ResultSet rset3 = st3.executeQuery(""
                                    + "SELECT transaction_date, turn1\n" +
                                "  FROM nursing.\"TurningChart\"   "
                                    + " WHERE transaction_date>='"+nursingcare.elementAt(3)+"' AND patient_no='"+nursingcare.elementAt(0)+"' AND visit_id='"+nursingcare.elementAt(1)+"' ORDER BY  server_date DESC;"
                                    + "");
                            
                                                        
                            
                            table.getDefaultCell().setColspan(8);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("10:- TURNING ", pFontHeader);
                                table.addCell(phrase);
                              
                            while (rset3.next()){
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("\t TURNING FOR "+dbObject.getDBObject(rset3.getObject(1)+":-\n","-") , pFontHeader1);
                                table.addCell(phrase);
                                 
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase( ""+dbObject.getDBObject(rset3.getObject(2)+".\n","-"), pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                        }catch(java.sql.SQLException vitalExec) {
                            System.out.println("the  TURNING exception is "+vitalExec);
                        }
                            ////end of TURNING population in table
                            
                            
                            
                            

                            
                            docPdf.add(table);
                            
                        } catch(Exception SqlExec) {
                            System.out.println("the exception is "+SqlExec);
                            
                             System.out.println("SELECT  transaction_time, diagnosis, expected_outcome, plan_of_care, intervention, evaluation, nurse_name,implemented_by  \n" +
                                        " FROM nursing.nursing_care_plan  "
                                    + "where ward='"+nursingcare.elementAt(2)+"' and transaction_time='"+nursingcare.elementAt(3)+"' "
                                    + "and patient_id='"+nursingcare.elementAt(0)+"' and visit_id='"+nursingcare.elementAt(1)+"'  ");
                            
                            
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
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
        
    }
    
    // }
    
    
    
    
    
    
}





