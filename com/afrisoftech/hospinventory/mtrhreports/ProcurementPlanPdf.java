//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory.mtrhreports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class ProcurementPlanPdf implements java.lang.Runnable {

    int cnt = 0;
    String branch = null;
    String ministry = null;
     String trimmed_subhead=null,trimmed_subitem=null,trimmed_head=null;
     double used_amt_proc_plan=0.0;
     String allocated_amt=null;
     String subhead_quat_amt=null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String selectSupp = null;
    java.lang.String OrderNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    java.lang.String procured_plan_amt=null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    PdfWriter pdfWriter;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader7 = FontFactory.getFont(FontFactory.HELVETICA,8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader9 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
     com.lowagie.text.Font pFontHeader91 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
String financial_yr=null,period=null,votes=null, departmt=null;
    public void ProcurementPlanPdf(java.sql.Connection connDb,String fin_yr,String quarter, String vote,String dept) {
        //  public void OrdersPdf() {
        financial_yr=fin_yr;
        period=quarter;
        votes=vote;
        departmt=dept;
        
        connectDB = connDb;
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

                    // pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    // System.out.println("Current Doc size 1 "+ pdfWriter.getCurrentDocumentSize());


                    String compName = null;
                    String date = null;

                    try {


                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Annual Procurement Plan ("+financial_yr+") - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

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

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table21 = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths21[] = {15, 15, 30, 15, 15, 15};

                        table21.setWidths(headerwidths21);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table21.setWidthPercentage((100));


                        table21.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table21.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table21.getDefaultCell().setColspan(1);
                        //  table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            
                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table21.getDefaultCell().setColspan(6);
                            table21.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table21.getDefaultCell().setFixedHeight(50);
                            table21.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table21.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table21.getDefaultCell().setFixedHeight(25);
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name FROM pb_hospitalprofile");
                            while (rset3.next()) {
                                //ministry = rset3.getObject(1).toString();
                                this.branch = rset3.getObject(1).toString();
                                table21.getDefaultCell().setColspan(6);

                                table21.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table21.addCell(phrase);
                            }
                             table21.getDefaultCell().setColspan(6);
                             table21.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("ANNUAL PROCUREMENT PLAN", pFontHeader11);
                                table21.addCell(phrase);
                                
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table21);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(10);
                        table1.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {15, 10, 10, 10, 10, 10, 10, 10, 10, 12};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));



                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);




                        try {

                            table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Finacial Year  ", pFontHeader3);
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(financial_yr, pFontHeader3);
                            table1.addCell(phrase);
                            
                            




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
                            int num_rows=0;
              
                          table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Quarter:" +period, pFontHeader);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(6);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Department:" +departmt, pFontHeader);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Vote:" +votes, pFontHeader);
                            table1.addCell(phrase);
                            
                            if(period.equals("all")){
                            period="quarter";
                            }
                            else
                            {
                            System.out.println("Do nothing");
                            
                            }
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("#", pFontHeader);  
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Item Code", pFontHeader);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Item ", pFontHeader);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Qty Planned For", pFontHeader);  
                            table1.addCell(phrase);
          
                             table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Qty requisitioned", pFontHeader);  
                            table1.addCell(phrase);
                            
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Qty Bal", pFontHeader);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Est Unit Price", pFontHeader);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Est Total Cost", pFontHeader);  
                            table1.addCell(phrase);

                            java.sql.ResultSet rset41 = st2.executeQuery("select item_code,item_description as item,qty_planned_for as qty_planned, qty_cumulative as qty_requisitioned, estimated_unit_cost as unit_cost,  estimated_total_cost as total_cost \n" +
"from st_procurement_plan where financial_year='"+financial_yr+"' and quarter ilike '%"+period+"%' and  proc_department='"+departmt+"' and vote_no='"+votes+"'");
                            
                            
                    int m=1;
                            while (rset41.next()) {
                              table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            Object on=m;
                            phrase = new Phrase(on.toString(), pFontHeader);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset41.getString("item_code"), pFontHeader9);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(rset41.getString("item"), pFontHeader7); 
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset41.getString("qty_planned"), pFontHeader9);  
                            table1.addCell(phrase);
          
                             table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset41.getString("qty_requisitioned"), pFontHeader9);  
                            table1.addCell(phrase);
                            
                          
                            
                            Object objs=Double.parseDouble(rset41.getString("qty_planned").toString())-Double.parseDouble(rset41.getString("qty_requisitioned").toString());
                           System.out.println("this is the qty balance "+objs);
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(objs.toString(), pFontHeader9);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset41.getString("unit_cost"), pFontHeader9);  
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset41.getString("total_cost"), pFontHeader9);  
                            table1.addCell(phrase);
                            
                             
                            m++;
                            
                            }
                            
                    
                         table1.getDefaultCell().setColspan(10);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader91);  
                            table1.addCell(phrase);
                            
                         table1.getDefaultCell().setBorder(Rectangle.TOP);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                         table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                          table1.getDefaultCell().setColspan(10);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader91);  
                            table1.addCell(phrase);
                            
                              table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                         table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                           table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("ALLOCATED YEARLY AMOUNT: ", pFontHeader91);  
                            table1.addCell(phrase);
               
                            
                            
                            try{
                            /////get the allocated amount
                               
                                   int head_code_pos=votes.indexOf("-",0);
                                   trimmed_head=votes.substring(0,head_code_pos);

                                    /////getting the subhead
                                     int subhead_code_index=votes.indexOf("-",0);
                                   trimmed_subhead=votes.substring(head_code_pos+1,votes.length());
                                 // departmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT item||'-'||subitem  FROM subitems where item='"+trimmed_subhead+"' order by 1"));           
                                   java.sql.Statement stmtss=connectDB.createStatement();
                                   //and period='"+jComboBox1.getSelectedItem()+"'
                                   
                                   java.sql.ResultSet rset = stmtss.executeQuery("select  distinct  subhead_yearly_amt from ac_aie_allocation  where head='"+ trimmed_head+"' and subhead='"+trimmed_subhead+"'  and financial_year='"+financial_yr+"' and subhead_yearly_amt>0 ");
                                    while (rset.next()) {


                                       subhead_quat_amt=rset.getString(1);
                                        if(subhead_quat_amt!=null){
                                        allocated_amt=rset.getString(1);
                                    }
                                    else{
                                    subhead_quat_amt="0.0";

                                    }
            }
                                
                                /////already used amount
                            java.sql.Statement stmtd=connectDB.createStatement();
                            java.sql.ResultSet resd=stmtd.executeQuery("select sum(qty_planned_for*estimated_unit_cost) from st_procurement_plan  where vote_no='"+votes+"' and financial_year='"+financial_yr+"' and  proc_department='"+departmt+"'");
                            while(resd.next()){
                            if(resd.getString(1)!=null){
                            procured_plan_amt=resd.getString(1);

                            }
                            else{
                            procured_plan_amt="0.0";

                            }

                            System.out.println("The balance of this department is "+ procured_plan_amt);

                            }
                            }catch(Exception esd){
                            esd.printStackTrace();

                            }
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(subhead_quat_amt)), pFontHeader91);  
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("PLANNED TOTAL: ", pFontHeader91);  
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(procured_plan_amt)), pFontHeader91);  
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("VOTE BALANCE: ", pFontHeader91);  
                            table1.addCell(phrase);
                            
                             table1.getDefaultCell().setColspan(5);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(subhead_quat_amt)-Double.parseDouble(procured_plan_amt)), pFontHeader91);  
                            table1.addCell(phrase);
docPdf.add(table1);
                            

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }


                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
             docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);




        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
