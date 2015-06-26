//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;


public class SecurityStoresReleasePdf implements java.lang.Runnable {
    
    java.lang.String userName = null;
    java.util.Vector dateVector=new java.util.Vector();
    int seq=0;
    java.lang.String endDate = null;
    java.lang.String beginDate = null;
    //String ks;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font storeNameHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    //public void OrderedItemsPdf(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate) {                  
    public void SecurityStoresReleasePdf(java.sql.Connection connDb,java.lang.String begindate, java.lang.String endate, java.lang.String user){
        
        connectDB = connDb;
        
        userName = user;
        
        endDate = endate;
        
        beginDate=begindate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
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
        
        com.lowagie.text.Document document=new com.lowagie.text.Document();
        document.setPageSize(com.lowagie.text.PageSize.A4.rotate());
        
        
        
        try{
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            com.lowagie.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(tempFile));
            
            java.sql.Statement hospStatement=connectDB.createStatement();
            java.sql.ResultSet hospName = hospStatement.executeQuery("SELECT hospital_name,rep_currency FROM pb_hospitalprofile");
            String institution="";
            java.text.SimpleDateFormat simpleDate=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date dateToday=new java.util.Date();
            
                        while(hospName.next()){
                            
                            institution = hospName.getObject(1).toString();
                            
                        }
            
            //com.lowagie.text.HeaderFooter headerFooter = new com.lowagie.text.HeaderFooter(new Phrase(""+institution+"                                                 Report Printed On: "+simpleDate.format(dateToday)+"",storeNameHeader),false);
            com.lowagie.text.HeaderFooter headerFooter = new com.lowagie.text.HeaderFooter(new Phrase(""+institution+"Stock Item Release Report \t Report Printed On: "+simpleDate.format(dateToday)+"",storeNameHeader),false);
            document.setHeader(headerFooter);
            
            com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Stock Balance - Page: ",storeNameHeader), true);
            document.setFooter(footer);
                    
            
            document.open();
            
            com.lowagie.text.pdf.PdfPTable table=new com.lowagie.text.pdf.PdfPTable(8);
            float tableWidths[]={1,0.7f,2.2f,0.8f,1,0.5f,1,0.8f};
            table.setWidths(tableWidths);
            table.setWidthPercentage(100);
            
            table.setHeaderRows(3);
            table.getDefaultCell().setColspan(8);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            table.addCell(new com.lowagie.text.Phrase("Stock Items Released By "+userName+".",storeNameHeader));
            
            table.getDefaultCell().setColspan(8);
            table.getDefaultCell().setBorderColor(Color.WHITE);
            table.addCell(new com.lowagie.text.Phrase(""));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Date & Time of Issue",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Requisition Number",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Item Description",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Issuing Store",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Recepient Store",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Qty Issued",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Issued By",storeNameHeader)));
            
            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
            table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase("Issued To",storeNameHeader)));
            
            getStockItemReleaseDates();
            
            java.sql.Statement statement=connectDB.createStatement();
            /*java.sql.ResultSet rs=statement.executeQuery("SELECT trans_date, manual_transfer_no, item, store_name as issuing_store, sub_store as recepient_store, " +
                                                        "issuing as qty_issued, user_name as Issued_by, issiued_to FROM st_sub_stores " +
                                                        "WHERE security_release='true' AND issuing>0 AND security_officer='"+userName+"' "+
                                                        "AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY trans_date,manual_transfer_no,recepient_store");*/
            
            for(int i=0;i<dateVector.size();i++){
                
                java.sql.ResultSet rs=statement.executeQuery("SELECT trans_date, manual_transfer_no, item, store_name as issuing_store, sub_store as recepient_store, " +
                                                        "issuing as qty_issued, user_name as Issued_by, issiued_to FROM st_sub_stores " +
                                                        "WHERE security_release='true' AND issuing>0 AND security_officer='"+userName+"' "+
                                                        "AND trans_date = '"+dateVector.elementAt(i)+"' ORDER BY trans_date,manual_transfer_no,recepient_store");
                
                com.lowagie.text.pdf.PdfPCell cellie=new com.lowagie.text.pdf.PdfPCell(
                                                            new com.lowagie.text.Phrase(""+dateVector.elementAt(i),
                                                                FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD+Font.UNDERLINE)));
                cellie.setColspan(8);
                cellie.setHorizontalAlignment(com.lowagie.text.pdf.PdfPCell.ALIGN_CENTER);
                table.addCell(cellie);
                
                while(rs.next()){
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(1),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(2),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(3),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(4),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(5),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(6),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(7),normalFont)));
                
                    table.getDefaultCell().setColspan(1);
                    table.getDefaultCell().setHorizontalAlignment(com.lowagie.text.pdf.PdfCell.ALIGN_LEFT);
                    table.addCell(new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(""+rs.getString(8),normalFont)));
                      
                
                    System.err.println("The value of seq is "+seq);
                    seq+=1;
                }
                
            }
            
            
            
            document.add(table);
            
            document.close();
            
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
        }
        catch(com.lowagie.text.DocumentException de){
            javax.swing.JOptionPane.showMessageDialog(null, de);
        }
        catch(java.io.FileNotFoundException fnfe){
            javax.swing.JOptionPane.showMessageDialog(null, fnfe);
        }
        catch(java.sql.SQLException sqle){
            javax.swing.JOptionPane.showMessageDialog(null, sqle);
        }
        catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    public void getStockItemReleaseDates() throws java.sql.SQLException{
        
        java.sql.Statement stat=connectDB.createStatement();
        
        java.sql.ResultSet rs=stat.executeQuery("SELECT DISTINCT trans_date::date FROM st_sub_stores " +
                                                        "WHERE security_release='true' AND issuing>0 AND security_officer='"+userName+"' "+
                                                        "AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY trans_date");
        
        while(rs.next()){
            dateVector.add(rs.getString(1));
        }
        
    }
    
    
    
}





