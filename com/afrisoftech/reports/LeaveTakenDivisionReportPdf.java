//Author Charles Waweru & Amimo Benja

//Made to test Java support for Threads.

//Revision : Ver 1.0b

//import java.lang.*;

package com.afrisoftech.reports;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.text.SimpleDateFormat;

public class LeaveTakenDivisionReportPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null; 
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    public java.lang.String dbUserName = null;  
    java.lang.String div = null;
     
    
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    public void LeaveTakenDetailsPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String division) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        div = division;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete or fail");
                
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
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        try {
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            
                            
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            Phrase phrase  = new Phrase("");
                            while (rset3.next()) {
                                table.getDefaultCell().setColspan(8);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(" \n\n"+rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                                
                            }
                            table.addCell(phrase);
                        } catch (java.sql.SQLException SqlExect) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExect.getMessage());

                        }
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                        
                        table.getDefaultCell().setColspan(10);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase phrase = new Phrase("LEAVE DAYS TAKEN BETWEEN "+sdf.format(beginDate)+" AND "+sdf.format(endDate)+""
                                + "\nDIVISION REPORT - "+div.toUpperCase(), 
                                pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Employee Number", pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Name", pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Department", pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Section", pFontHeader);
                        table.addCell(phrase); 
                                                
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Job Group", pFontHeader);
                        table.addCell(phrase);
                                                
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave Days Entitled", pFontHeader);
                        table.addCell(phrase);
                                                
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave Days Taken", pFontHeader);
                        table.addCell(phrase);
                        
                        System.out.println("Will we populate this table, let's try...");
                        
                        try {

                            java.sql.Statement stOne = connectDB.createStatement();
                            java.sql.ResultSet rsetOne = stOne.executeQuery("SELECT employee_no, department, section FROM master_file "
                                    + "WHERE division = '"+div+"' ");

                            while(rsetOne.next()) {
                                System.out.println("Found Employee Number - "+dbObject.getDBObject(rsetOne.getObject(1), "0000"));
                                try {

                                    java.sql.Statement st = connectDB.createStatement();
                                    java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT emp_no, emp_name, job_group, "
                                            + "leave_days, sum(applied_leave_days) FROM hr.hr_leave_approval "
                                            + "WHERE emp_no = '"+dbObject.getDBObject(rsetOne.getObject(1), "0000")+"' AND type_of_leave = 'Annual Leave' "
                                            + "AND date_approved BETWEEN '"+beginDate+"' AND '"+endDate+"' "
                                            + "GROUP BY emp_no, emp_name, job_group, "
                                            + "leave_days");

                                    while(rset.next()) {
                                        
                                        System.out.println("Leave Approved Number - "+dbObject.getDBObject(rset.getObject(1), "None"));

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "None"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "Unknown"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rsetOne.getObject(2), "---"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rsetOne.getObject(3), "---"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "K"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "K"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), " -- "), pFontHeader1);
                                        table.addCell(phrase);
                                    }


                                } catch(java.sql.SQLException SqlExec) {

                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                }
                                
                            }


                        } catch(java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        
                        table.getDefaultCell().setBorderColor(Color.WHITE);
                        table.getDefaultCell().setColspan(10);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("", pFontHeader);
                        table.addCell(phrase);
                        
                        try {                        
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                while (rsetUser.next()) {
                                    table.getDefaultCell().setColspan(5);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Generated By : ".toUpperCase() + rsetUser.getString(1), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Signature : " , pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader);
                                    table.addCell(phrase);

                                }

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        
                        
                        docPdf.add(table);
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
    
    
}





