//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.maintenance;

import com.afrisoftech.hospinventory.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

public class LastCodePdf implements java.lang.Runnable {

    int seq = 0;
    java.lang.String bank = null;
    java.lang.String beginDate = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String jobCardNo=null;

    //public void StockItemsPdf(java.sql.Connection connDb, java.lang.String combox) {
    public void JobCardPdf(java.sql.Connection connDb, String jobNo){
        
        connectDB = connDb;
        jobCardNo=jobNo;
        
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
        
        Document doc=new Document();
        PdfPTable table=new com.lowagie.text.pdf.PdfPTable(2);
        PdfPCell cell;
        //Paragraph para;
        Chunk chunk;
        com.lowagie.text.Font reportTitle = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD+Font.UNDERLINE);
        com.lowagie.text.Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
        com.lowagie.text.Font columnHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
        
        
        try{
            java.io.File tempFile=java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
            
            tempFile.deleteOnExit();
            
            
            
            com.lowagie.text.pdf.PdfWriter.getInstance(doc, new java.io.FileOutputStream(tempFile));
            
            doc.open();
            
                table.setWidthPercentage(100);
                //table.setWidths(new float[]{0.7f,1.3f,1.0f,0.5f,0.5f,1f});
                    chunk=new Chunk("Last Codes  "+jobCardNo,columnHeaders);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setColspan(6);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorderColor(Color.white);
            
                table.addCell(cell);
                
                
            java.util.Vector v=new java.util.Vector();
                
            java.sql.Statement stat =connectDB.createStatement();
            java.sql.ResultSet categs=stat.executeQuery("SELECT DISTINCT sub_cat_code FROM st_stock_item ORDER BY 1");
            
            while(categs.next()){
                v.add(categs.getString(1));
            }
            
            v.trimToSize();
            
            java.sql.Statement stat1 =connectDB.createStatement();
            java.sql.ResultSet lastCode=null;
            
            for(int i=0;i<v.size();i++){
                lastCode=stat1.executeQuery("SELECT item_code FROM st_stock_item WHERE sub_cat_code ='"+v.elementAt(i)+"' ORDER BY 1 DESC LIMIT 1");
                
                while(lastCode.next()){
                    chunk=new Chunk(v.elementAt(i).toString());
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                
                chunk=new Chunk(lastCode.getString(1));
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                }
                
                    
            }
                    
                
                
            doc.add(table);
            doc.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
        }
        catch(com.lowagie.text.DocumentException de){
            de.printStackTrace();
        }
        catch(java.io.IOException ioe){
            ioe.printStackTrace();
        }
        catch(java.sql.SQLException sqle){
            sqle.printStackTrace();
        }

    }

}
