//Author Salim Mwaura
//Purchase Requisition for Orders
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import static com.afrisoftech.hospinventory.mtrhreports.PurchaseReqInternalMtrhPdf.connectDB;
import com.afrisoftech.lib.CurrencyFormatter;
//import com.afrisoftech.reports.pharmacyShiftReport;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TenderSummaryReportPDF implements java.lang.Runnable {
 com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
    int seq = 0;
    //java.lang.String selectSupp= null;
    java.lang.String beginDate = null;
    java.lang.String OrderNo =null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
   
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
    com.lowagie.text.Font pFontHeadertableTitle = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    //String Department=null;
    private int cnt=0;
    private String failed;
    private String total;
    private String won;
    private double total_mark;
    private double passmark;
    private double total_prel;
    private int count;
    private double floated_val;
    private double order_sum;
    private double balance;
    private double total_order;
    //String user =null;
   // private String time=null;
    //private String today=null;
    public void TenderSummaryReportPDF(java.sql.Connection connDb, java.lang.String tenderNo) {
      //  selectSupp = combox;
      //  Department = dept;
        OrderNo = tenderNo;
       // user = UserName;
        
        
        
        
        
        connectDB = connDb;
        
        // beginDate = begindate;
        // endDate = endate;
        dbObject = new com.afrisoftech.lib.DBObject();
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new GlTransactPdf().GlTransactPdf();
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

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4,40,40,40,40);

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            try {

                try {
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                 

                    String compName = null;
                    String date = null;

                    try {


                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                       
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(""+OrderNo+"   Tender Ordering Report - Page: ", pFontHeader1), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    String Username = null;
                    int numColumns = 9;


                    try {

                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);


                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        table1.setWidths(headerwidths);
                       
                        table1.setWidthPercentage((100));


                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();
                        
                        
                         table1.getDefaultCell().setColspan(1);

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
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                                  
                        docPdf.add(table1);
                        
                           //headers
                        try {
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(10);
                        table2.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {24, 10, 8, 8, 6, 8, 6, 10, 12, 8};

                        //  table1.setWidths(headerwidths1);

                        table2.setWidthPercentage((100));



                        table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase1 = new Phrase("", pFontHeader);




                        try {

                            // java.sql.Statement st3 = conDB.createStatement();

                            //  java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/purchase","postgres","pilsiner");
                            //  java.sql.Statement st3 = conDb.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            //java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '"+selectSupp+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery(" SELECT DISTINCT time_entered FROM st_technical_evaluation  WHERE quotation_no = '" + OrderNo + "' limit 1");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select distinct item_desc from st_technical_evaluation where quotation_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            //java.sql.ResultSet rset11 = st11.executeQuery("select initcap(user_name) from st_receive_requisation where requisition_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
//                            while (rset11.next()) {
//                                Username = rset11.getObject(1).toString();
//                            }
                            table2.getDefaultCell().setBorderColor(java.awt.Color.white);

                           // while (rset4.next()) {

                                table2.getDefaultCell().setColspan(10);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("  ", pFontHeader);
                                //table1.addCell(phrase);

                                table2.getDefaultCell().setColspan(10);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("PURCHASE ORDERS REPORT For Financial Year "+com.afrisoftech.lib.FinancialYear.getActiveFinancialYear(connectDB), pFontHeader5);
                                table2.addCell(phrase);
                                
                                 table2.getDefaultCell().setColspan(10);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("TENDER  -    "+OrderNo+"  "+com.afrisoftech.lib.GetItemInfo.getITenderDesc(OrderNo, connectDB).toUpperCase(), pFontHeader5);
                                table2.addCell(phrase);
                                
                                
                                
//                                table2.getDefaultCell().setColspan(10);
//                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase("TECHNICAL EVALUATION RESULTS HELD ON  "+rset4.getDate(1), pFontHeader5);
//                                table2.addCell(phrase);
                                
                                
                                
                               
                                
                                table2.getDefaultCell().setColspan(10);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
//                                

//                                



                            //}

                            docPdf.add(table2);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }


                        
                        
                        
                       

   try {
                        com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(9);
                       
                        int headerwidths3[] = {8, 30, 14, 14, 14, 14, 14, 16, 15};

                        table3.setWidths(headerwidths3);

                        table3.setWidthPercentage((100));
                        

                        table3.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                            
              java.lang.Object[] listofAct = this.getListofActivities();
//                 
               for (int i = 0; i < listofAct.length; i++) {
                 
                   
                            table3.getDefaultCell().setColspan(1);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(i+1+".", pFontHeadertableTitle);
                            table3.addCell(phrase); 
                            
                            table3.getDefaultCell().setColspan(8);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("SUPPLIER: "+listofAct[i].toString(), pFontHeadertableTitle);
                            table3.addCell(phrase);     
                            
                     java.sql.Statement st5 = connectDB.createStatement();
                     java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT order_no,date from st_orders where quotation_no = '" + OrderNo + "' and order_no is not null AND supplier ilike '"+listofAct[i].toString()+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");      

                      while(rset5.next()){
                            
                            
                        
                        
                       //0 su
                        table3.getDefaultCell().setColspan(5);
                        phrase = new Phrase("Order No:"+rset5.getObject(1).toString(), pFontHeader);
                        table3.addCell(phrase);
                        
                        table3.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Order Date:"+rset5.getDate(2).toString(), pFontHeader);
                        table3.addCell(phrase);
                        //----------------------------------
                        
                        table3.getDefaultCell().setColspan(3);
                        phrase = new Phrase("ITEM", pFontHeader);
                        table3.addCell(phrase);
                        //2
                        table3.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Ordered_Qty", pFontHeader);
                        table3.addCell(phrase);
                        //3
                        table3.getDefaultCell().setColspan(2);

                        phrase = new Phrase("Unit_Value", pFontHeader);
                        table3.addCell(phrase);
                        //4
                        table3.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Total_value ", pFontHeader);
                        table3.addCell(phrase);
                        
          
                     
          

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            java.sql.Statement st3 = connectDB.createStatement();
 
//                         
                            

                          //item,quantity,unit_price,total
                            java.sql.ResultSet rset1 = st2.executeQuery("select  item,quantity,unit_price,(unit_price*quantity),SUM(unit_price*quantity) FROM st_orders where quotation_no ='"+OrderNo+"' and order_no ilike  '"+rset5.getObject(1).toString()+"' and supplier = '"+listofAct[i].toString()+"' and quantity>0  group by 1,2,3 order by 1");

                          
                            
                            while (rset1.next()) {
                               System.out.println("select  item,quantity,unit_price,(unit_price*quantity) FROM st_orders where quotation_no ='"+OrderNo+"' and supplier ilike  '"+rset5.getObject(1).toString()+"' and order_no = '"+listofAct[i].toString()+"' order by 1");
                                cnt= cnt +1;
                                total_order=rset1.getDouble(5);
                            //item    
                            table3.getDefaultCell().setColspan(3);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);
                            table3.addCell(phrase);
                            
                            //qty
                            table3.getDefaultCell().setColspan(2);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(rset1.getDouble(2)), pFontHeader);
                            table3.addCell(phrase);
                                
                    
                            
                            //unit_price
                            table3.getDefaultCell().setColspan(2);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(rset1.getDouble(3)), pFontHeader);
                            table3.addCell(phrase);

                            
                            //total
                            table3.getDefaultCell().setColspan(3);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(rset1.getDouble(4)), pFontHeader2);
                            table3.addCell(phrase);
                            
                            //totallpo
                            
                                table3.getDefaultCell().setColspan(5);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Total Order Value ", pFontHeadertableTitle);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setColspan(4);
                                phrase = new Phrase(CurrencyFormatter.getFormattedDouble(total_order), pFontHeadertableTitle);
                                table3.addCell(phrase); 

                              }
                                table3.getDefaultCell().setColspan(9);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeadertableTitle);
                                table3.addCell(phrase);
                            
                 }
                           
                            
                        }
            docPdf.add(table3);
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }
                    
                    
                    
                    try{
                         //remarks
                           Phrase phrase = new Phrase();
                            com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(9);                          
                            table4.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            table4.setWidthPercentage((100));
                            //table4.getDefaultCell().setFixedHeight(350);
                            //table4.addCell(table3);
                                                     

                            //just a white space above text
                            table4.getDefaultCell().setColspan(9);
                            phrase = new Phrase("\n\n\n ", pFontHeader);
                            table4.addCell(phrase);

                            java.sql.PreparedStatement pst;
                            java.sql.PreparedStatement pst2;
                            java.sql.PreparedStatement pst3;
                        try {                     
                            pst = connectDB.prepareStatement("SELECT SUM(quantity*unit_price) from st_orders where order_no IS NULL AND quotation_no= '"+OrderNo+"' ");
                            pst2 = connectDB.prepareStatement("SELECT SUM(quantity*unit_price) from st_orders where order_no IS NOT NULL AND quotation_no = '"+OrderNo+"' ");
                            
                            ResultSet rset = pst.executeQuery();
                            while(rset.next()){
                            floated_val = rset.getDouble(1);
                            
                            }
                            
                            ResultSet rset2 = pst2.executeQuery();
                            while(rset2.next()){
                            
                            order_sum = rset2.getDouble(1);
                            }
                            
                            balance=floated_val-order_sum;
                            
                            
                            
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(TenderSummaryReportPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            
                           
//                         table4.getDefaultCell().setColspan(9);
                            phrase = new Phrase("\n\n\n ", pFontHeader);
                            table4.addCell(phrase);
                            
                            
                            
                            table4.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Total Tender Awards(value per quoted price)", pFontHeader);
                            table4.addCell(phrase);
                            
                            table4.getDefaultCell().setColspan(6);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(floated_val), pFontHeader);
                            table4.addCell(phrase);
                            
                            table4.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Total Order (VALUE) as at Current_date", pFontHeader);
                            table4.addCell(phrase);
                            
                            table4.getDefaultCell().setColspan(6);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(order_sum), pFontHeader);
                            table4.addCell(phrase);
                            
                            
                            
                            table4.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Balance(Kshs)", pFontHeader);
                            table4.addCell(phrase);
                            
                            table4.getDefaultCell().setColspan(6);
                            phrase = new Phrase(CurrencyFormatter.getFormattedDouble(balance), pFontHeader);
                            table4.addCell(phrase); 
                           

                           
                      docPdf.add(table4);

                    }
                    catch(BadElementException bex){
                    
                    bex.printStackTrace();
                    
                    
                    }
                
                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

             
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


           try {

       

        java.sql.Statement stmt1 = connectDB.createStatement();

        java.sql.ResultSet rSet1 = stmt1.executeQuery("select distinct supplier from st_orders  WHERE  quotation_no  = '"+OrderNo+"' AND order_no IS NOT NULL order by 1 ");

        while (rSet1.next()) {

        listActVector.addElement(rSet1.getObject(1).toString());
        System.out.println(rSet1.getObject(1).toString());
       // System.out.println(OrderNo);

        }

        }catch (java.sql.SQLException sqlExec) {
          
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage()+"\n Contact System Administrator");

        }
         

        listofActivities = listActVector.toArray();
        System.out.println("Done list of SUPPLIERS IN st_orders...");
        return listofActivities;
    }

//    
//
//        
//    
     private java.lang.String getBidderName(java.lang.String bidderCode){
        String name= null;
        try {
            java.sql.Statement cd = connectDB.createStatement();
               
                    ResultSet cditm = cd.executeQuery("SELECT distinct supplier FROM st_preliminary_results where quotation_no = '"+OrderNo+"' and supplier_code='"+bidderCode+"' ;");
                    
                    

                      while(cditm.next()){
                    
                         name = cditm.getObject(1).toString();
                         System.out.println(name);
                         
                       }
        } catch (SQLException ex) {
            Logger.getLogger(SSOFInancialEvaluationIntfr.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return name;
    
    
    }
}
