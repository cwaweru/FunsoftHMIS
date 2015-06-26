//Author Francis King'oi
package com.afrisoftech.hospinventory;


//import static com.afrisoftech.hospinventory.mtrhreports.MtrhSthirteenPdf.connectDB;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class ComplianceCertificatePdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    java.lang.String QuotationNo = null;
    java.lang.String MainItem = null;
    java.lang.String CertNo = null;
    java.util.Date CertDate = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.UNDERLINE);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.NORMAL);
        com.lowagie.text.Font pFontHead = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String Supplier;
    java.lang.String suppliers_date=null,technical_date=null,chair_person_date=null;

    public void ComplianceCertificatePdf(java.sql.Connection connDb, java.lang.String quotation, java.lang.String item) {

        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        QuotationNo =quotation;

        MainItem = item;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new EntranceFeePdf().EntranceFeePdf();
    }
public void getSup(){

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

            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            try {
               String remarkz=null;
               String checked_by=null;
               String time_d=null;
                String compName = null;
                String orgName = null;
                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String date = null;
                    try {
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,organisation_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            orgName = rset2.getObject(2).toString();
                        }

                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "   Printed On: " + date + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        //headerFoter.setRight(5);
                        //docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    // com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Technical Specifications - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    //docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(9);

                        int headerwidths1[] = {12, 20, 10, 12, 12, 15, 15, 10, 12};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(2);
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths[] = {10, 15, 30, 5, 20, 20, 20};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(9);

                        int headerwidths2[] = {12, 12, 30, 10, 10, 10, 10, 20, 10};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.setHeaderRows(2);
                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        table2.getDefaultCell().setColspan(9);
                        //table.getDefaultCell().setBorder(3);

                        Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                        //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                        table2.getDefaultCell().setColspan(9);
                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table2.getDefaultCell().setFixedHeight(50);
                        table2.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                        table2.getDefaultCell().setFixedHeight(17);
                        table2.getDefaultCell().setNoWrap(false);
                        Phrase phrase = new Phrase(compName, pFontHeader3);

                        table2.addCell(phrase);
                        try {
                           
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                                phrase = new Phrase("PP Form no 13 : Serial No " +QuotationNo, pFontHeader);

                                table2.addCell(phrase);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("INSPECTION AND ACCEPTANCE REPORT FORM", pFontHeader2);

                            table2.addCell(phrase);

                            

                            int no = 0;
                            java.sql.Statement st1 = connectDB.createStatement();
                          java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT  quotation_no,time_date::DATE,cert_no,supplier "
                                  + "FROM st_certificate WHERE cert_no = '" + QuotationNo + "' AND   lpo = '" + MainItem + "'");
                            java.sql.Statement st11 = connectDB.createStatement();




                            while (rset1.next()) {
                                //stock cardex
                                    
                                
                                //end of stock cardex
                                CertNo = rset1.getString("cert_no");
                                CertDate = rset1.getDate(2);
                                Supplier=rset1.getString("supplier");
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                               
                                table2.getDefaultCell().setColspan(9);
                                table2.getDefaultCell().setColspan(9);
                                //  
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT DISTINCT department FROM st_item_to_quote WHERE  tender_no = '" + rset1.getString(1) + "'");
                                table2.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Supplier : " +Supplier, pFontHeader);
                                table2.addCell(phrase);
                                table2.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Date/Time Issued : " +"........................"  + CertDate, pFontHeader);
                                table2.addCell(phrase);
//                                table2.getDefaultCell().setColspan(2);
//                                phrase = new Phrase("Time out :........................................", pFontHeader);
                               // table2.addCell(phrase);
                                table2.getDefaultCell().setColspan(9);
                                table2.getDefaultCell().setColspan(9);
                                while (rset11.next()) {
                                    phrase = new Phrase("Department / User : " + rset11.getString(1), pFontHeader);
                                    table2.addCell(phrase);
                                }

                            }
                                table2.getDefaultCell().setColspan(9);
                                table2.getDefaultCell().setBorder(Rectangle.TOP);

                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("LPO/LSO/Contract No", pFontHeader);
                                table.addCell(phrase);

                               table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Delivery Note", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Description of Goods/Works/Services", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Qty Received", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Supplies Officer", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Quality Assuarance officer", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Commitee", pFontHeader);
                                table.addCell(phrase);
//this is the start
              
                                
                                //my work
                                      
   
                           // String inv_date=null,delivery_date=null;
                       java.lang.String received=null,remarkSupplier=null,remarkTechnical=null,remarkChair=null,upplier=null,Technical=null,Chair=null;        
                            java.sql.Statement pstmtSupplier = connectDB.createStatement();

                    java.sql.ResultSet rsetSupplier = pstmtSupplier.executeQuery("SELECT * FROM st_certificate WHERE cert_no = '" + QuotationNo + "' AND   lpo = '" + MainItem + "'");
              while (rsetSupplier.next()) {
                  //ordered items
                          checked_by=rsetSupplier.getString("checked_by");
                          time_d=rsetSupplier.getString("time_date");
                          remarkz=rsetSupplier.getString("remarks");
//my work ends
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(MainItem, "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsetSupplier.getString("dr_no"), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsetSupplier.getString("item"), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                     java.sql.Statement pstmtSupplierz = connectDB.createStatement();

                            java.sql.ResultSet rsetSupplierz = pstmtSupplierz.executeQuery("select * from st_stock_cardex where order_no='"+MainItem+"' and supplier='"+Supplier+"' and certficate_no='"+CertNo+"' and item='"+rsetSupplier.getString("item")+"' and delivery_note_no='"+rsetSupplier.getString("dr_no")+"'");
                              while (rsetSupplierz.next()) {
                                received=rsetSupplierz.getString("quantity_received");
                                remarkSupplier=rsetSupplierz.getString("supplies_remarks");
                                remarkTechnical=rsetSupplierz.getString("technical_remarks");
                                remarkChair=rsetSupplierz.getString("chair_remarks");
                                upplier=rsetSupplierz.getString("supplies_name");
                                Technical=rsetSupplierz.getString("technical_name");
                                Chair=rsetSupplierz.getString("chairperson_name");
                             suppliers_date=rsetSupplierz.getString("supplies_date");
                             technical_date=rsetSupplierz.getString("technical_date");
                             chair_person_date=rsetSupplierz.getString("chairperson_date");
  
                              }            
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(received,""), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(upplier+": "+remarkSupplier +"\n On "+suppliers_date,""), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(Technical+": "+remarkTechnical+"\n On "+technical_date,""), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(Chair+": "+remarkChair+"\n On "+chair_person_date,""), pFontHeader1);
                                    table.addCell(phrase);
                               
              }
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
  //this is the end                              
                                
             //last bit                

                            table2.getDefaultCell().setColspan(9);
                            table2.getDefaultCell().setFixedHeight(170);
                            // table.getDefaultCell().setBorder(Rectangle.TOP);
                            // phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(table);


                           table1.getDefaultCell().setColspan(1);
                    


//rubbed                           
                            
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                      
                        com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(6);
                                             int headerwidths4[] = {50, 10, 10, 10, 15, 15};
                                             table4.setWidths(headerwidths4);
                                             table4.setWidthPercentage((100));
//space
                                              table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
  //end of space                                                   
                                           
                                             
                                             
                //comments                             
                                             table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Accept Report/Comments :  "+remarkz, pFontHeader);
                                                     table4.addCell(phrase);
                                                     
                 //date                                    
                                                     
                                                     
                     //space
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     
                       //end space
                       //secretary
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Secretary", pFontHead);
                                                     table4.addCell(phrase);
                                                     
                                                           //space
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     
                       //end space
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Name :..........................................................................................................         Sign :.............................................         Date :.......................................", pFontHeader);
                                                     table4.addCell(phrase);
                                                     
                                                   
                                                     
                                                           //space
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     
                       //end space
                                                     
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Chairman", pFontHead);
                                                     table4.addCell(phrase);
                                                     
                                                           //space
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     
                       //end space
                                                     
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Name :..........................................................................................................         Sign :.............................................         Date :.......................................", pFontHeader);
                                                     table4.addCell(phrase);
                                                     
                                                          //space
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("");
                                                     table4.addCell(phrase);
                                                     
                                                     
                       //end space
                                                     
                                                   
                                                     
                                                     table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                     phrase = new Phrase("Prepared by:   "+checked_by+"        Date:  "+time_d, pFontHeader);
                                                     table4.addCell(phrase);
                                                        
                                                     
                                                     
                                                     
                                                      table4.getDefaultCell().setColspan(6);
                                                     table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                     table4.getDefaultCell().setBorder(0);
                                                      phrase = new Phrase("Distribution of Copies\n 1. Accounts 2. Store 3. User 4. Book Copy", pFontHeader);
                                                     table4.addCell(phrase);
                                                     
                                                     
                                                      docPdf.add(table2);
                            
                            docPdf.add(table1);
                                                     docPdf.add(table4);
                             
                     

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

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
}
