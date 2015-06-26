/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory.mtrhreports;

/**
 *
 * @author saleem
 */
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Runnable;

public class taxcertPDF implements java.lang.Runnable {
    
     int cnt = 0;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String selectedSupp = null;
    java.lang.String OrderNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    PdfWriter pdfWriter;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String supplier= null;
    public void taxcert(java.sql.Connection connDb, java.lang.String selectedSupp) {
         
               supplier = selectedSupp;

               
               connectDB = connDb;
               // beginDate = begindate;
               threadSample = new java.lang.Thread(this, "SampleThread");
                  
               System.out.println("threadSample created");

               threadSample.start();

               System.out.println("threadSample fired");
              
               System.out.println("atleast nmetoa   " +supplier);
  
    }
    
    public static void main(java.lang.String[] args) throws DocumentException {
                        
         //  new com.afrisoftech.hospinventory.mtrhreports.taxcertPDF().taxcert(connectDB, "'BA%'");
        
         
    }
 
    
    public void run()  {

        System.out.println("System has entered running mode");

        while (threadCheck) {
            try {            
                System.out.println("O.K. see how we execute target program");
                                
               this.generatePdf(selectedSupp);
                
                  try {
                    System.out.println("Right, let's wait for task to complete of fail");

                    java.lang.Thread.currentThread().sleep(200);

                    System.out.println("It's time for us threads to get back to work after the nap");

                } 
                  catch (java.lang.InterruptedException IntExec) {

                    System.out.println(IntExec.getMessage());

                }

                threadCheck = false;


                System.out.println("We shall be lucky to get back to start in one piece");
                
            } catch (IOException ex) {
                Logger.getLogger(taxcertPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(taxcertPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(taxcertPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void generatePdf(String selectedSupp) throws IOException, DocumentException, SQLException  {
       
        
        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        ////
        
         java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;
            
            ///
            System.out.println("is it here?");
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
           
            System.out.println("docpdfsawa");
            com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
            
            System.out.println("tfilesawa");
            
            java.sql.Statement s = connectDB.createStatement();
            ResultSet f = s.executeQuery("select tax_cert from st_floated_quotation_docs where supplier_name ilike 'BA%'");
            while (f.next()){
            OutputStream t = f.getBlob(1).setBinaryStream(1);
            
            System.out.println("wah imetoka  "+t);
            
            //File nnn = new File(t);
            
            
            ////
            }
            // java.sql.PreparedStatement ps1 = connectDB.prepareStatement("select tax_cert from st_floated_quotation_docs where supplier_name ilike 'BA%' ");
             java.sql.Statement v = connectDB.createStatement();
             ResultSet q = v.executeQuery("select tax_cert from st_floated_quotation_docs where supplier_name ilike '"+selectedSupp+"' ");
              while (q.next()){
              // Blob a = q.getBlob(1);
               String a = q.getBytes(1).toString();
                          
               File  taxcert1 = new File(a);
               
               System.out.println("eh jo niko mbele");
               System.out.println("jo "+a.toString());
               Blob w = (Blob) q.getBinaryStream(1);
               
            //   w = (Blob) tempFile;
               docPdf =  (Document) w;
               docPdf.open();
              }
           
            
         
            
            
         } 

}
       
    
    

