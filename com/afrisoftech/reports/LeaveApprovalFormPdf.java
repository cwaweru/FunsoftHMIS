//Author Charles Waweru && Amimo Benja

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class LeaveApprovalFormPdf implements java.lang.Runnable {
    
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
    com.lowagie.text.Font pFontHeader9 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);    
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
    
    
    public void LeaveApprovalPdf(java.sql.Connection connDb, java.lang.String empno, 
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
                    String userName = null;
                    String dateNow = null;
                    String family_leave_days = null;
                  
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
                        
                        try {                        
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access "
                                    + "where login_name = current_user order by 1 limit 1), "
                                    + "date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                while (rsetUser.next()) {
                                    userName = rsetUser.getString(1);

                                    dateNow = rsetUser.getString(2);

                                }

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        
                        String department = "Null", employee_grade = "Null";
        
                        try {
                            connectDB.setAutoCommit(false);

                            System.out.println("About to Get the Patient Details");

                            java.sql.Statement stm = connectDB.createStatement();
                            java.sql.ResultSet rse = stm.executeQuery(
                                    "SELECT department, employee_grade FROM master_file WHERE employee_no = '" + emp_no + "' ");

                            System.out.println("To Get the Patient Details");

                            while (rse.next()) {

                                System.out.println("Getting the user's Details");

                                department = rse.getObject(1).toString();
                                employee_grade = rse.getObject(2).toString();

                            }

                            System.out.println("The user's Details should be set!");

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("KNH/HR/HRE/114A", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Date: "+dateNow, pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);   
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Dr/Mr/Mrs/Miss  - "+emp_name, pFontHeader9);
                        table.addCell(phrase); 
                                            
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Job Group - "+employee_grade, pFontHeader9);
                        table.addCell(phrase); 
                                            
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Department/Ward/Unit - "+department, pFontHeader9);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase); 
                                            
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Thro'\n\n\n", pFontHeader9);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Dear Sir/Madam, ", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("RE: "+type_leave.toUpperCase()+" APPROVAL FORM ", pFontHeader2);
                        table.addCell(phrase); 
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Reference is made to "+type_leave+" application dated "+dateNow+". "
                                + "Approval is hereby granted to proceed on "+leave_days+" days "
                                + ""+type_leave+" with effect from "+leave_date+" ", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        String applied_leave_days = null, leave_end_date = null, leave_return_date = null;
                        String leave_remaining_days = null, leave_start_date = null, sum_leave_days = null, B = null;
                                                
                        try {
                                connectDB.setAutoCommit(false);

                                java.sql.Statement stmABC = connectDB.createStatement();
                                java.sql.Statement stmA = connectDB.createStatement();
                                java.sql.ResultSet rseABC = stmABC.executeQuery("SELECT leave_days, applied_leave_days, leave_start_date, "
                                        + "leave_end_date, leave_return_date, leave_remaining_days, "
                                        + "to_char(date_approved, 'YYYY') as output FROM hr.hr_leave_approval "
                                        + "WHERE emp_no = '"+emp_no+"' AND job_group = '"+employee_grade+"' "
                                        + "AND type_of_leave = '"+type_leave+"' AND approved = 'TRUE'");
                                java.sql.ResultSet rseA = stmA.executeQuery("SELECT SUM(applied_leave_days) as A FROM hr.hr_leave_approval "
                                        + "WHERE emp_no = '"+emp_no+"' AND job_group = '"+employee_grade+"' "
                                        + "AND type_of_leave = '"+type_leave+"' AND approved = 'TRUE'");

                                while (rseABC.next()) {
                                    family_leave_days = dbObject.getDBObject(rseABC.getObject(1), "0"); 
                                    applied_leave_days = dbObject.getDBObject(rseABC.getObject(2), "0"); 
                                    leave_start_date = dbObject.getDBObject(rseABC.getObject(3), "0"); 
                                    leave_end_date = dbObject.getDBObject(rseABC.getObject(4), "0"); 
                                    leave_return_date = dbObject.getDBObject(rseABC.getObject(5), "0"); 
                                    leave_remaining_days = dbObject.getDBObject(rseABC.getObject(6), "0");                                     
                                    B = dbObject.getDBObject(rseABC.getObject(7), "20__");
                                }
                                
                                while (rseA.next()) {
                                    sum_leave_days = dbObject.getDBObject(rseA.getObject(1), "0"); 
                                }


                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }
                        
                        
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Current Leave Entitlement ", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase(family_leave_days+" days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave carried foward from previous year", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("---- days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave previously taken", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase(Integer.valueOf(sum_leave_days) - Integer.valueOf(applied_leave_days)+" days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Total Leave", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase(Integer.valueOf(sum_leave_days)+" days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave now granted", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase(Integer.valueOf(leave_days)+" days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Balance", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase(Integer.valueOf(leave_remaining_days)+" days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Leave forfeited", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("NILL days.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Please note that your leave balance must be taken before 30th November "+B+" "
                                + "which you will only be allowed to carry forward to the next year a maximum of ___ days.", pFontHeader9);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Your "+leave_days+" days "+type_leave+" expires on "+leave_end_date+" and you will be expected to resume duty"
                                + " on "+leave_return_date+".", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("Yours faithfully, ", pFontHeader9);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("FOR: HUMAN RESOURCE MANAGER.", pFontHeader9);
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





