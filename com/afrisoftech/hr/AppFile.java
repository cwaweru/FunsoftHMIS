//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hr;

import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.sql.SQLException;

public class AppFile implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    public String SEARCH_BY = "STAFF_ID";
    public String SearchText = "";
    public String Staff_ID="";
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
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void AppFile(java.sql.Connection connDb, String staff) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {

        connectDB = connDb;
        SearchText = staff;
        dbObject = new com.afrisoftech.lib.DBObject();

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new TransactionsListPdf().TransactionsListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf();

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(100);

                System.out.println("It's time for us threads to get back to work after the nap");

            } catch (java.lang.InterruptedException IntExec) {

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

            year_now_strs = "200" + year_now_abs;

        } else {

            year_now_strs = "20" + year_now_abs;

        }

        switch (month_now_str) {

            case 0:
                month_now_strs = "JAN";

                break;

            case 1:
                month_now_strs = "FEB";

                break;

            case 2:
                month_now_strs = "MAR";

                break;

            case 3:
                month_now_strs = "APR";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUN";

                break;

            case 6:
                month_now_strs = "JUL";

                break;

            case 7:
                month_now_strs = "AUG";

                break;

            case 8:
                month_now_strs = "SEP";

                break;

            case 9:
                month_now_strs = "OCT";

                break;

            case 10:
                month_now_strs = "NOV";

                break;

            case 11:
                month_now_strs = "DEC";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = "0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = "0" + date_now_str;

        } else {

            date_now_strs = "" + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = "0" + minute_now_str;

        } else {

            minute_now_strs = "" + minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = "0" + hour_now_str;

        } else {

            hour_now_strs = "" + hour_now_str;

        }

        date_label = date_now_strs + month_now_strs + year_now_strs + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf() {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();


        try {

            java.io.File tempFile = java.io.File.createTempFile("HR_APPLICANT_FILE_" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            //  java.lang.String debitTotal = null;

            // java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();



            try {

                com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                String compName = null;
                String date = null;


                try {



                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    java.sql.ResultSet rset2 = st3.executeQuery("SELECT UPPER(organisation_name) from hr.hr_company_profile");
                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while (rset2.next()) {
                        compName = rset2.getObject(1).toString();
                    }

                    while (rset4.next()) {
                        date = rset4.getObject(1).toString();
                    }

                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + " JOB APPLICANT FILE", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setRight(7);
                    docPdf.setHeader(headerFoter);

                } catch (java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                }

                com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                        "FunSoft Human Resources Management Systems                          Page:             Printed On: " + date + "", pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                docPdf.setFooter(footer);


                docPdf.open();


                try {



                    try {
                        java.sql.Statement st2 = connectDB.createStatement();
                        java.sql.ResultSet rset = null;
                        java.sql.ResultSet rsetAdd = null;
                        //System.out.println("Print this " + this.SEARCH_BY);
                        System.out.println("SELECT  * FROM  hr.hr_appresume_basic where app_id='" + SearchText + "';");
                        if (this.SEARCH_BY.equals("STAFF_ID")) {
                             
                            rset = st2.executeQuery("SELECT  * FROM  hr.hr_appresume_basic where app_id='" + SearchText + "';");
                           
                        } else if (this.SEARCH_BY.equals("NATIONAL_ID")) {
                            rset = st2.executeQuery("SELECT  * FROM  hr.hr_appresume_basic where id_no='" + SearchText + "';");
                        }
                        int counter = 1;

                        while (rset.next()) {













                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);

                            int headerwidths[] = {20, 25, 30, 5, 20};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(2);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(5);

                            Phrase phrase = new Phrase("");

                            //  try {
                            //       java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            //                        java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            //
                            //    System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            //   phrase = new Phrase("Job Postings " , pFontHeader3);

                            table.addCell(phrase);

                            //   table.getDefaultCell().setColspan(2);
                            //    phrase = new Phrase("Printed on : " +date, pFontHeader);

                            //   table.addCell(phrase);
                            // } catch(java.text.ParseException psExec) {

                            //        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            //    }


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);





                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.BLUE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


this.Staff_ID=""+rset.getObject(1);

                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(counter + ". " + dbObject.getDBObject(rset.getObject(3) + " " + rset.getObject(2) + " " + rset.getObject(4), "-"), pFontHeader3);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("Biodata", pFontHeader3);
                            table.addCell(phrase);

                            counter++;
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            ////////////////////row 1

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Applicant ID #:");
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            Phrase staffID = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"));
                            table.addCell(staffID);



                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Position Applied:");
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            Phrase section = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"));
                            table.addCell(section);

                            //////////////////////////////



                            /////////////////////////row 2
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("National ID#:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            Phrase exp_date = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"));

                            table.addCell(exp_date);


                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Ref #:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            Phrase post_date = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"));

                            table.addCell(post_date);




                            ////////////////////////////////////////////row 3

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Birth Date:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(27), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            String ages="";
                            try{
                                 String dob=rset.getObject(27).toString();
                                 String diff=dob.substring(0, 4);
                                 int i=Integer.parseInt(diff+"");
                                 System.out.println("Thestring is "+i);
                         int age=2014-i;
                        ages=""+age+" yrs";
                            }catch(Exception e){
                                
                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Age:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("" + ages);

                            table.addCell(phrase);


                            /////////////////////////////////////////////////





                            ////////////////////////////////////////////row 3

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gender:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Marital Status:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(11), "-"));

                            table.addCell(phrase);


                            /////////////////////////////////////////////////

                            ////////////////////////////////////////////row 4

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.O.B:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Disability?:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(13), "-"));

                            table.addCell(phrase);


                            /////////////////////////////////////////////////


                            ////////////////////////////////////////////row 3

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Residence:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(21), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Ethnicity:");

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(12), "-"));

                            table.addCell(phrase);


                            /////////////////////////////////////////////////

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);




                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);



                            ///////////////////////////////////////////////

                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                           //////////////////////////////////EDUCATIONAL QUALIFICATION                     


                            try {
                                  java.sql.Statement st3 = connectDB.createStatement();
                                rsetAdd = st3.executeQuery("SELECT  * FROM  hr.hr_appresume_qualif where app_id='" + this.Staff_ID + "';");
System.out.println("SELECT  * FROM  hr.hr_referees_xtras where staff_id='" + this.Staff_ID + "';");
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase = new Phrase("Educational Qualifications", pFontHeader3);
                                table.addCell(phrase);
                               
                                table.getDefaultCell().setColspan(1);
 
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
         table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Educ.Level",pFontHeader3);

                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
 
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Institution",pFontHeader3);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                             phrase = new Phrase("Grade",pFontHeader3);

                           
                            table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                             phrase = new Phrase("Remarks",pFontHeader3);

                            table.addCell(phrase);
                                
                                
                                while(rsetAdd.next()){
                                        
                                  ////////////////////////////////////////////row 4

                            table.getDefaultCell().setColspan(1);
 table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(""+rsetAdd.getObject(1));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rsetAdd.getObject(3), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(""+rsetAdd.getObject(4));

                            table.addCell(phrase);

                          
                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rsetAdd.getObject(6), "-"));
                            
                            table.addCell(phrase);

System.out.println("SELECT  * FROM  hr.hr_staffresume_add where staff_id='" + this.Staff_ID + "';"+rsetAdd.getObject(2));
                            /////////////////////////////////////////////////








                                }
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                



                            } catch (SQLException sql) {
                                System.out.println(""+sql);
                            }

                      
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                              table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader3);
                            table.addCell(phrase);
                            
                            
                            
                            
                            
                            
                            

                            //////////////////////////////////ADDRESSING INFORMATION                      


                            try {
                                  java.sql.Statement st3 = connectDB.createStatement();
                                rsetAdd = st3.executeQuery("SELECT  * FROM  hr.hr_referees_xtras where staff_id='" + this.Staff_ID + "';");
System.out.println("SELECT  * FROM  hr.hr_referees_xtras where staff_id='" + this.Staff_ID + "';");
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase = new Phrase("Referees", pFontHeader3);
                                table.addCell(phrase);
                               
                                table.getDefaultCell().setColspan(1);
 
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
         table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Name",pFontHeader3);

                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
 
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Organisation",pFontHeader3);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                             phrase = new Phrase("Contacts",pFontHeader3);

                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(2);
                             phrase = new Phrase("Remarks",pFontHeader3);

                            table.addCell(phrase);
                                
                                
                                while(rsetAdd.next()){
                                        
                                  ////////////////////////////////////////////row 4

                            table.getDefaultCell().setColspan(1);
 table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(rsetAdd.getObject(3)+". "+rsetAdd.getObject(1));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rsetAdd.getObject(2), "-"));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Phone: "+rsetAdd.getObject(4)+"\nPO.Box"+rsetAdd.getObject(5));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(dbObject.getDBObject(rsetAdd.getObject(6), "-"));

                            table.addCell(phrase);

System.out.println("SELECT  * FROM  hr.hr_staffresume_add where staff_id='" + this.Staff_ID + "';"+rsetAdd.getObject(2));
                            /////////////////////////////////////////////////








                                }
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                



                            } catch (SQLException sql) {
                                System.out.println(""+sql);
                            }











                            docPdf.add(table);
                            docPdf.newPage();
                        }

                        //docPdf.addSubject(""+dbObject.getDBObject(rset.getObject(2),"-"));




                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                } catch (com.lowagie.text.BadElementException BadElExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }
    /*  public java.lang.Object[] getListofStaffNos() {
   
     /*       java.lang.Object[] listofStaffNos = null;
   
     java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
   
   
     try {
   
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
   
     java.sql.Statement stmt1 = connectDB.createStatement();
   
     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct staff_no from tax_card WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by staff_no");
   
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
     */
}
