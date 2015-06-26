//Author Charles Waweru && Amimo Benja

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class LeaveApplicationFormPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    
    java.lang.String emp_no = null;
    java.lang.String leave_date = null;
    java.lang.String type_leave = null;
    java.lang.String emp_name = null;
    java.lang.String leave_days = null;
    
    public static java.sql.Connection connectDB = null;    
    public java.lang.String dbUserName = null;    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);    
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader7 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader8 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLDITALIC);
    
    
    java.lang.String bank = null;
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();    
    java.lang.Process prThread;
    
    
    public void LeaveApplicationPdf(java.sql.Connection connDb, java.lang.String empno, 
            java.lang.String leavedate, java.lang.String typeleave, java.lang.String empname, java.lang.String leavedays) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        emp_no = empno;
        leave_date = leavedate;   
        type_leave = typeleave;
        emp_name = empname;
        leave_days = leavedays;
       
        
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
               
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            
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
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {20, 20, 20, 20};
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        try {
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            Phrase phrase  = new Phrase("");
                            while (rset3.next()) {
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("\n\n"+rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                                
                            }
                            
                            table.addCell(phrase);
                        } catch (java.sql.SQLException SqlExect) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExect.getMessage());

                        }
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase phrase = new Phrase("", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("KNH/HR/HRE/114A", pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase(type_leave.toUpperCase()+" APPLICATION FORM", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("The leave application form duly completed by the employee and the Head of Department/Unit should"
                                + " be submitted to the Human Resource Manager for leave processing and record purpose.", pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);                        
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("PART I: APPLICATION PARTICULARS", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("", pFontHeader);
                        table.addCell(phrase); 
                        
                        String official_desgnation = "Null", department = "Null", section = "Null";

                        String branch = "Null", employee_grade = "Null", employee_group = "Null";
        
                        try {
                            connectDB.setAutoCommit(false);

                            System.out.println("About to Get the Patient Details");

                            java.sql.Statement stm = connectDB.createStatement();
                            java.sql.ResultSet rse = stm.executeQuery(
                                    "SELECT official_desgnation, department, section, "
                                    + "branch, employee_grade, employee_group FROM master_file "
                                    + "WHERE employee_no = '" + emp_no + "' ");

                            System.out.println("To Get the Patient Details");

                            while (rse.next()) {

                                System.out.println("Getting the user's Details");

                                official_desgnation = rse.getObject(1).toString();
                                department = rse.getObject(2).toString();
                                section = rse.getObject(3).toString();
                                branch = rse.getObject(4).toString();
                                employee_grade = rse.getObject(5).toString();
                                employee_group = rse.getObject(6).toString();

                            }

                            System.out.println("The user's Details should be set!");

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }
                        
                        
                        String leave_end_date = null, local_address = null, contact = null, tel_exnt = null, dte = null, name_of_employee = "Unknown";
                        String recom_reason = null, hod_recomm = null, recom_date = null, accompanied_by = null;
                    
                        try {
                                connectDB.setAutoCommit(false);

                                java.sql.Statement stm1 = connectDB.createStatement();
                                java.sql.ResultSet rse1 = stm1.executeQuery("SELECT end_date, local_address, contact, tel_exnt, "
                                        + "date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date), "
                                        + "name_of_employee, recom_reason, hod_recomm, date, accompanied_by FROM hr.hr_leave_application WHERE "
                                        + "emp_no = '"+emp_no+"' "
                                        + "AND start_date = '"+leave_date+"' "
                                        + "AND duration = '"+leave_days+"' "
                                        + "AND recommended = TRUE ");

                                while (rse1.next()) {
                                    leave_end_date = rse1.getObject(1).toString();
                                    local_address = rse1.getObject(2).toString();
                                    contact = rse1.getObject(3).toString();
                                    tel_exnt = rse1.getObject(4).toString();
                                    dte = rse1.getObject(5).toString();
                                    name_of_employee = rse1.getObject(6).toString();
                                    recom_reason = rse1.getObject(7).toString();
                                    hod_recomm = rse1.getObject(8).toString();
                                    recom_date = rse1.getObject(9).toString();
                                    accompanied_by = rse1.getObject(10).toString();
                               }

                                connectDB.commit();
                                connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }
                        
                        System.out.println("Leave Days - "+leave_days+" Leave End Date "+leave_end_date);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("I, "+emp_name.toUpperCase() +" P/No. - "+emp_no+" Designation - "+official_desgnation+" Job Group - "
                                + employee_grade+" Dept/Ward/Unit - "+department+" do hereby apply for "+leave_days+" days of "
                                + type_leave.toUpperCase()+" with effect from "+leave_date+" to "+leave_end_date+" I will be accompanied "
                                + "by: "+accompanied_by, pFontHeader1);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ");
                        table.addCell(phrase); 
                        
                                                
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Email/Local Address ", pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Overseas Leave Address ", pFontHeader);
                        table.addCell(phrase); 
                                                
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(local_address, pFontHeader1);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(" ");
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Tel: "+contact, pFontHeader1);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Name: "+name_of_employee, pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date: "+dte, pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Sign: ", pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Tel Ext: "+tel_exnt, pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ");
                        table.addCell(phrase); 
                                                
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("(Note: Application is considered invalid without address)", pFontHeader8);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);      
                        
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("PART II: APPROVAL OF HEAD OF DEPARTMENT/UNIT", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ");
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Leave approved (remarks if any) - "+recom_reason,pFontHeader8);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);  
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Name: "+hod_recomm, pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Date: "+recom_date, pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Sign: ", pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Tel Ext: ", pFontHeader1);
                        table.addCell(phrase);                        
                        
                        
                        
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





