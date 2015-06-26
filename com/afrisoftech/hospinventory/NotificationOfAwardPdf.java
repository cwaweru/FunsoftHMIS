//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;



public class NotificationOfAwardPdf implements java.lang.Runnable {

    java.lang.String selectSupp = null;
    java.lang.String OrderNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    java.io.File tempFile =null;
    boolean isSent=false;
    String[] strings;
    String[] tosendStr;
    
     javax.swing.JLabel attachmentLabels[] = null;
    java.util.Vector attachmentsVector = null;
    
    java.lang.Thread sendMailThread = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    PdfWriter pdfWriter;
     java.sql.Date datenowSql;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader56 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String ddate,cont_no=null;
    private int coNo;

    public void NotificationOfAwardPdf(java.sql.Connection connDb, java.lang.String combox,java.lang.String contract_no) {
        //  public void OrdersPdf() {
        selectSupp = combox;

       

        connectDB = connDb;
        cont_no=contract_no;
        // beginDate = begindate;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        // public static void main() {
        //	new OrdersPdf().OrdersPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(selectSupp);

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(200);

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

    public void generatePdf(java.lang.String memNo) {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();


        try {
             String contr_no="olsons67"; 
          tempFile = java.io.File.createTempFile("NOTIFICATION-" +contr_no+ "-",".pdf");
            System.out.println("This is the path "+tempFile.getAbsolutePath());
            try{

                
            }
            catch(Exception esd){
            esd.printStackTrace();
            
            }
           
            //java.io.File tobesaved = new java.io.File("D:/Dispatched_RFQS/notification.pdf");            
           // tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4,40,40,40,40);

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            try {

                try {
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    // pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    // System.out.println("Current Doc size 1 "+ pdfWriter.getCurrentDocumentSize());


                    String compName = null;
                    String date = null;

                    try {


                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Notification Of Award - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        //docPdf.setFooter(footer);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    docPdf.open();

                    String Username = null;
                    int numColumns = 9;

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                         datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));


                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table1.getDefaultCell().setColspan(1);
                        //  table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table1.getDefaultCell().setColspan(10);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(50);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(25);
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name FROM pb_hospitalprofile");
                            while (rset3.next()) {

                                table1.getDefaultCell().setColspan(6);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                                
                                docPdf.add(table1);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                      
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(9);
                        table1.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {24, 10, 10, 10, 6, 10, 6, 10, 14};

                        //  table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));
                        
                        com.lowagie.text.pdf.PdfPTable table11 = new com.lowagie.text.pdf.PdfPTable(9);
                        table11.getDefaultCell().setPadding(3);

                        int headerwidths11[] = {24, 10, 10, 10, 6, 10, 6, 10, 14};

                        //  table1.setWidths(headerwidths1);

                        table11.setWidthPercentage((100));

                        double Total = 0.00;
                        double discount = 0.00;
                        double vat = 0.00;
                        
                        //try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        table.getDefaultCell().setPadding(3);

                        int headerwidths[] = {10, 20, 35, 15, 20, 20, 20, 25};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);

                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);




                        try {

                            // java.sql.Statement st3 = conDB.createStatement();

                            //  java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/purchase","postgres","pilsiner");
                            //  java.sql.Statement st3 = conDb.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            String vote_class = null;
                            String vote = null;
                            String head=null,head1 = null;
                            String sub_head=null,sub_head1 = null;
                            String itemDet = "";
                            String depart = "";
                            String box = "";
                            String tel = "";
                            String shorName = "";
                            String dateDue = "";
                            String contract_nos=null;
                            String requisation_nos=null;
                            String req_date=null;


                  
                            java.sql.Statement st32 = connectDB.createStatement();

                            //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '" + selectSupp + "'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select DISTINCT order_no,date,quotation_no,date_due,ordering_store FROM st_orders where order_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("SELECT DISTINCT organisation_name,box_no,main_telno  FROM pb_hospitalprofile");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset1 = st1.executeQuery("select current_user");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            while (rset1.next()) {
                                Username = rset1.getObject(1).toString();
                            }
                            while (rset5.next()) {
                                depart = rset5.getObject(1).toString();
                                box = rset5.getObject(2).toString();
                                tel = rset5.getObject(3).toString();
                            }

//                            while (rset4.next()) {
//
//
//                               
//                                while (rset2.next()) {

                                  

                                      


                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("NOTIFICATION OF AWARD FOR ", pFontHeader4);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setBorderColor(java.awt.Color.white);


                                        table1.getDefaultCell().setColspan(9);
                                        
                                         table1.getDefaultCell().setColspan(6);
                                         table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("Supplier "+selectSupp, pFontHeader4);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                                        
                                         table1.getDefaultCell().setColspan(3);
                                         table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("Contract No "+cont_no, pFontHeader4);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                                        docPdf.add(table1);
                                       

                                      
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("Item No. ", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(3);

                                        // table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Item Description", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Unit ", pFontHeader);
                                        table.addCell(phrase);
                                        // table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Qty ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Unit Cost ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Total Value", pFontHeader);
                                        table.addCell(phrase);


                                        // table.getDefaultCell().setBottom(1);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.black);

                                       
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                                       

                                        table.getDefaultCell().setColspan(1);

                                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);




                                        // java.sql.Statement st6 = connectDB.createStatement();
                                        java.sql.Statement st11 = connectDB.createStatement();

                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT initcap(item),units,quantity,unit_price,(quantity*unit_price)net_value FROM st_orders WHERE supplier = '" +selectSupp+ "' and quotation_no='"+cont_no+"' and ordered=false");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");

                                        // table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);sdfsdfs
                                        
                                        //table1.getDefaultCell().setFixedHeight(200);
                                       coNo = 0;         
                                        while (rset11.next()) {
                                            //   table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                            coNo+= 1;
                                            table.getDefaultCell().setColspan(1);
                                            // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                                            //
                                            // table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            
                                            phrase = new Phrase(java.lang.String.valueOf(coNo), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                            phrase = new Phrase(rset11.getObject(1).toString(), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);

                                            phrase = new Phrase(rset11.getObject(2).toString(), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)), pFontHeader2);

                                            table.addCell(phrase);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(4)), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(5)), pFontHeader2);

                                            table.addCell(phrase);



                                            Total = Total + rset11.getDouble(5);
                                        }

                                        docPdf.add(table1);

                                        //table1.getDefaultCell().setFixedHeight(320);
                                        table1.getDefaultCell().setColspan(9);
                                        table1.addCell(phrase);
                                        //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        //phrase = new Phrase("             ", pFontHeader);

                                        //table1.addCell(table);

                                        // docPdf.add(table);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                                        table1.getDefaultCell().setColspan(6);
                                        table1.getDefaultCell().setFixedHeight(15);
                                        table1.getDefaultCell().setColspan(3);
                                        phrase = new Phrase(" ", pFontHeader);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Sub Total ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(3);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Total)), pFontHeader);

                                        table1.addCell(phrase);
                                        docPdf.add(table1);

                                        
//                                    }
                                
                            //}
//                                        docPdf.add(table);
//                                          docPdf.add(table1);
                                            //docPdf.add(table11);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }




                    // }



                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
        
         
             
            docPdf.close(); 
            
             //sendMailToSuppliers(tempFile);
             
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
           String tobemailedpath=tempFile.getAbsolutePath();
           System.out.println("This is the path "+tobemailedpath);
            //com.afrisoftech.lib.PDFRenderer.renderPDF(tobesaved);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
    
   
    
    
  
    
   
    
    private void sendMailToSuppliers(java.io.File tempFiles){
    String to = "olsonmutwiris@gmail.com";
  String from = "olsonmutwiri2009@gmail.com";
  String host = "smtp.gmail.com";
  String filename = tempFiles.getAbsolutePath();
  String msgText1 = "Sending a file.\n";
  String subject = "Sending a file";
  java.lang.String[] listSet = null;  
   java.lang.String[] ccAddresses = null; 
   java.lang.String[] toAddresses = null; 
  java.sql.ResultSet rsetArray = null;
  
  
  // create some properties and get the default Session
  Properties props = System.getProperties();
//  props.put("mail.smtp.host", host);
//  props.put("mail.smtp.starttls.enable","true");
//  props.put("mail.smtp.starttls.required","true");
  
  props.put("mail.smtp.user","olsonmutwiris@gmail.com");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "25");
props.put("mail.debug", "true");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.EnableSSL.enable","true");

props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.setProperty("mail.smtp.socketFactory.fallbac k", "false");
props.setProperty("mail.smtp.port", "465");
props.setProperty("mail.smtp.socketFactory.port", "465"); 
  
  //Session session = Session.getInstance(props, null);
  Session session = Session.getInstance(props, new SocialAuth());  
  try 
  {
      // create a message
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      
      ///creating CCs
      try {
            
            //          java.sql.Connection cn = java.sql.DriverManager.getConnection("jdbc:postgresql://192.168.0.100:5432/purchase","postgres", "pilsiner");
            
            java.sql.Statement stmt = connectDB.createStatement();
            
            java.sql.ResultSet rs = stmt.executeQuery("select distinct c.email_mail_address  from mailers_list_setup c,mailers_group_setup p where p.sender_mailer_code=c.sender_mailer_code and  p.mailer_category='Dispatch Quotations'");
            
            while (rs.next()) {
                
                java.sql.Array arraySet = rs.getArray(1);
                
                java.lang.Object[] listSetTest = (java.lang.Object[])arraySet.getArray();
                
                if (!(listSetTest == null)) {
                    
                    listSet = (java.lang.String[])arraySet.getArray();
                    
                    //rsetArray = arraySet.getResultSet();
                    
                } else {
                    
                    listSet = null;
                    
                }
            }
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(null, sqlExec.getMessage());
            
        }
      
//      InternetAddress[] address = {new InternetAddress(to)};
//      msg.setRecipients(Message.RecipientType.TO, address);
//      
      InternetAddress[] ccAddress = new InternetAddress[listSet.length];
            for(int j = 0; j < listSet.length; j++){
                ccAddress[j] = new InternetAddress(listSet[j]);
            }
            msg.setRecipients(Message.RecipientType.CC, ccAddress);
      
            ////end
      
      msg.setSubject(subject);

      // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1);

      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
      
      File attachmentFiles = tempFiles.getAbsoluteFile();

            //attachmentsVector = new java.util.Vector(1,1);

            

                //attachmentsVector.addElement(attachmentFiles.getPath());
                
      FileDataSource fds = new FileDataSource(attachmentFiles.getPath());
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(String.valueOf(fds.getName()));
      
//      MimeBodyPart mbpAttachments[] =new  MimeBodyPart[1];
//            
//            // attach the file to the message
//            //for(int m =0; m < mbpAttachments.length; m++){
//                FileDataSource fds = new FileDataSource(filename);
//                mbpAttachments[1] = new MimeBodyPart();
//                mbpAttachments[1].setDataHandler(new DataHandler(fds));
//                mbpAttachments[1].setFileName(fds.getName());
//            //}
      
      //System.out.println("xxxxxxxxxxx "+fds.getName());

      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);
      
     

      // add the Multipart to the message
      msg.setContent(mp);

      // set the Date: header
      msg.setSentDate(new Date());
      
      // send the message
      Transport.send(msg);
      
  } 
  catch (MessagingException mex) 
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
    
    
    
    }
    
   
}
}   
class SocialAuth extends Authenticator {  
  
        private String PASSWORD = "olsona130";
        private String FROM_ADDRESS = "olsonmutwiri2009@gmail.com";
        protected PasswordAuthentication getPasswordAuthentication() {  
  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  
  
        }  
    }  
