//Author Amimo Benja & Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import static com.afrisoftech.records.reports.OutPatientAttendaceDeptPdf.connectDB;
import java.awt.Color;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//


public class HRDutyRostertPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    String [] details;
    
    java.lang.String bank = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void StaffListPdf(java.sql.Connection connDb, String lcDetails[]) {
        
        connectDB = connDb;
        details=lcDetails;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
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
    
    
    public void generatePdf(java.util.Date tarehedb, String tarehe, String division, String department, String section, java.sql.Connection connDb) {
                
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("HR_REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
                        
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());
            
            
            
            try {
                
                com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                
                String user = null;
                String date = null;
                connectDB = connDb;
                
                try {                        
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) "
                                    + "FROM secure_menu_access where login_name = current_user order by 1 limit 1), "
                                    + "date_part('day', now()::date) ||'-'||date_part('month', now()::date) "
                                    + "||'-'||date_part('year', now()::date)");
                            
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                while (rsetUser.next()) {
                                    user = rsetUser.getString(1).toUpperCase();

                                    date = rsetUser.getString(2);

                                }

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                
                
                
                com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                "DUTY ROSTER                                                                       Printed On: "+date+"                                         "
                        + " By: "+user+"                      Page: ", pFontHeader2), true);
                
                docPdf.setFooter(footer);
                
                
                docPdf.open();
                java.util.List<String> staffID = new ArrayList<String>();
                java.util.List<String> dates = new ArrayList<String>();
                
                try {
                    staffID.clear();
                    dates.clear();
                    Calendar cal2 = Calendar.getInstance();
                    
                    cal2.setTime(tarehedb);
                    cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, MMM d yyyy");
                    String headers[]=new String[15];
                    headers[0]="name";
                    String firstDate=sdf3.format(cal2.getTime());
                    String lastDate="";
                    headers[1]=sdf.format(cal2.getTime());
                    dates.add(sdf2.format(cal2.getTime()));
                     for(int i=2; i<15; i++){
                         System.out.println("Get start of this week in milliseconds");
                        cal2.add(Calendar.DATE, 1);
                        headers[i]=sdf.format(cal2.getTime());
                        dates.add(sdf2.format(cal2.getTime()));
                        if(i==14){
                            lastDate=sdf3.format(cal2.getTime());
                        }
                     }
                    
                    
                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(15);
                    
                    float headerwidths[]= new float[15];
                    
                    headerwidths[0]=15;
                    
                    float restWidth=85/(15-1);
                    
                    for(int headerCounter=1; headerCounter<15;headerCounter++){
                        headerwidths[headerCounter]=restWidth;
                        
                    }
                    
                    com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);
                    int headerwidths2[] = {30, 70};

                    table2.setWidths(headerwidths2);
                    table2.setWidthPercentage((100));
                    table2.getDefaultCell().setFixedHeight(30);
                    table2.getDefaultCell().setColspan(2);
                    table2.getDefaultCell().setBorderColor(Color.WHITE);
                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                    img.scalePercent(40);
                    table2.addCell(img);
                    String compName = null;
                    String District = null;
                    String Region = null;
//                    String date = null;
                    try {
                        java.sql.Statement st3 = connectDB.createStatement();

                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch,region FROM pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                            Region = rset2.getObject(3).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                    } catch (SQLException ex) {
                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                                    ex.printStackTrace();             //ex.printStackTrace();
                    }
                    table.getDefaultCell().setColspan(15);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);                        
                    Phrase phrase = new Phrase(compName.toUpperCase(), pFontHeader3);
                    table2.addCell(phrase);

                    table.getDefaultCell().setColspan(15);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);                        
                    phrase = new Phrase("DUTY ROSTER", pFontHeader);
                    table2.addCell(phrase);

                    table.addCell(table2);
                    
                    table.setWidths(headerwidths);
                    table.setWidthPercentage((100));
                    
                    table.setHeaderRows(2);
                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    table.getDefaultCell().setColspan(5);
                    
                    table.getDefaultCell().setColspan(15);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase(" " , pFontHeader);
                    table.addCell(phrase);
                    
                    
                    table.getDefaultCell().setColspan(15);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase("DIVISION - "+division+"  DEPARTMENT - "+department+"  SECTION - "+section+" "
                            + "\nDuty Roster from "+firstDate+" to "+lastDate , pFontHeader);
                    table.addCell(phrase);                    
                    
                    phrase = new Phrase(" " , pFontHeader);
                    table.addCell(phrase);
                    
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    
                    
                    
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                    phrase = new Phrase("Emp. Name ",pFontHeader5);
                    table.addCell(phrase);
                    
                    for(int fillHeaders=1;fillHeaders<15;fillHeaders++){
                        phrase = new Phrase(headers[fillHeaders],pFontHeader5);
                        table.addCell(phrase);
                    }
                    
                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    
                    
                    try{
                        
                        java.sql.Statement st2 = connectDB.createStatement();
                        
                        
                        java.sql.ResultSet rset = st2.executeQuery("SELECT initcap(first_name) || ' '|| initcap(middle_name) || ' '|| initcap(last_name) AS employee_name, "
                            + "employee_no FROM master_file WHERE unit_division = '"+division+"' "
                            + "AND department = '"+department+"' "
                            + "AND section = '"+section+"' "
                            + "AND initcap(first_name) || ' '|| initcap(middle_name) || ' '|| initcap(last_name) NOT LIKE '' "
                            + "ORDER BY first_name ASC");
                        
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        
                        
                        while (rset.next()) {
                            
                            table.getDefaultCell().setColspan(1);                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(rset.getString("employee_name"), pFontHeader5);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            System.out.println("Division - '"+division+"'  , Department - '"+department+"' , Section - '"+section+"' ----------- "
                                    + "Employee Number - "+rset.getString("employee_no"));
                            
                            cal2 = Calendar.getInstance();
                            cal2.setTime(tarehedb);
                            cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
                            
                            int i=1;
                            
                            do{
                                String sql2 = "SELECT duty, staffid FROM hr.hr_duty_assignment WHERE staffid = '"+rset.getString("employee_no")+"' "
                                        + "AND date_assigned = '"+sdf2.format(cal2.getTime())+"' ";
                                
                                System.out.println("Entered at Do while");
                                
                                Statement stmt2 = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                
                                ResultSet rs2 = stmt2.executeQuery( sql2 );
                                rs2.last();
                                
                                
                                if (rs2.getRow() > 0){
                                    System.out.println("Entered rs2 > 0");
                                    rs2.beforeFirst();
                                    rs2.next();
                                    phrase = new Phrase(rs2.getString("duty"), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    System.out.println("Division - '"+division+"'  , Department - '"+department+"' , Section - '"+section+"' ----------- "
                                    + "Staff ID - "+rs2.getString("staffid")+" --- Duties - '"+rs2.getString("duty")+"'");
                                } else{
                                    System.out.println("Entered rs2 else");
                                    phrase = new Phrase(rs2.getString("duty"), pFontHeader2);
                                    table.addCell(phrase);
                                }
                                
                                System.out.println("Finished Do while Loop");
                                cal2.add(Calendar.DATE, 1);
                                i++;
                             }while(i<15);
                            System.out.println("Outside Do While Loop");
                            
                        }
                        
                        docPdf.add(table);
                        
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        System.out.println("1st Exception - "+SqlExec.getMessage());
                                                
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                } catch(com.lowagie.text.BadElementException BadElExec) {
                    System.out.println("2nd Exception - "+BadElExec.getMessage());
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                System.out.println("3rd Exception - "+lwDocexec.getMessage());
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
            
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            System.out.println("4th Exception - "+IOexec.getMessage());
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
    }
    
}





