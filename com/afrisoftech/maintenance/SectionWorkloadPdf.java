//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.maintenance;

import com.afrisoftech.hospinventory.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

public class SectionWorkloadPdf implements java.lang.Runnable {

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
    private String department=null;

    //public void StockItemsPdf(java.sql.Connection connDb, java.lang.String combox) {
    public void SectionWorkloadPdf(java.sql.Connection connDb, String departm, java.lang.String bDate, java.lang.String eDate){
        
        connectDB = connDb;
        department=departm;
        beginDate=bDate;
        endDate=eDate;
        
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
        int columns=8;
        PdfPTable table=new com.lowagie.text.pdf.PdfPTable(columns);
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
                table.setWidths(new float[]{0.8f, 0.8f, 1.4f, 1.8f, 0.7f, 0.5f, 1f, 1f});
                
                    chunk=new Chunk("Section: "+department,columnHeaders);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setColspan(columns);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorderColor(Color.white);
                    table.addCell(cell);
            
                    chunk=new Chunk("WORKLOAD BETWEEN "+beginDate+" AND "+endDate+".",columnHeaders);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setColspan(columns);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorderColor(Color.white);
                    table.addCell(cell);
                
                chunk=new Chunk("   ",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(columns);
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                
                chunk=new Chunk("Date ",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Job Card No",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Activity",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Location",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Time",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Days",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Staff",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                chunk=new Chunk("Remarks",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
        
           
                
                java.sql.Statement dataStt=connectDB.createStatement();
                java.sql.ResultSet dataSet=dataStt.executeQuery("SELECT maintenance.job_card_details.date_job_card_created, maintenance.job_card_details.job_card_no, maintenance.e1_details.description, 'Building: '||maintenance.e1_details.building||', Floor: '||maintenance.e1_details.floor||', Room: '||maintenance.e1_details.room as location, maintenance.job_card_details.officers_assigned, '' as remarks FROM maintenance.job_card_details "
                                                                +"INNER JOIN maintenance.e1_details ON maintenance.job_card_details.e1_id=maintenance.e1_details.e1_id "
                                                                +"WHERE section_assigned='"+department+"' "
                                                                +"AND officers_assigned IS NOT NULL " 
                                                                +"AND maintenance.job_card_details.date_job_card_created BETWEEN '"+beginDate+"' AND '"+endDate+"' "
                                                                +"ORDER BY date_job_card_created, job_card_no" );
                
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                while(dataSet.next()){
                    
                    System.err.println("Huh? ");
                    chunk=new Chunk(dataSet.getString(1),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataSet.getString(2),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataSet.getString(3),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataSet.getString(4),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    
                    chunk=new Chunk("  ",normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    chunk=new Chunk("  ",normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    
                    
                    chunk=new Chunk(dbObject.getDBObject(dataSet.getString(5), "-"),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataSet.getString(6),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
               
                   
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
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
