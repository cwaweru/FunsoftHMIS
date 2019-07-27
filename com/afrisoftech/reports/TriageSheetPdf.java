//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import java.awt.Desktop;

public class TriageSheetPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    // java.awt.Desktop deskTop = Desktop.getDesktop();
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    java.lang.String patCat = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.afrisoftech.lib.DBObject dbObject;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void TriageSheetPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String patCats) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        patCat = patCats;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberListPdf().MemberListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf();

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(50);

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

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String date = null;
                   

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Triage List - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));


                        //table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {
                            
                            Image img = Image.getInstance(System.getProperty("company.logo"));
                            Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            
                                     
                            java.sql.Statement st4 = connectDB.createStatement();

                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                             table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(50);
                            table1.addCell(Image.getInstance(System.getProperty("company.logo")));
                            
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(5);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                                
                                
                            }
                            while (rset4.next()) {
                                table1.getDefaultCell().setColspan(3);
                                date = rset4.getObject(1).toString();
                                //  phrase = new Phrase("Printed On  :" +date , pFontHeader);

                                table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }        
                    
                    


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);

                        int headerwidths[] = {7, 13, 25, 6, 8, 40, 10,10,10};

                        table.setWidths(headerwidths);
                        table.setHeaderRows(2);
                        table.setWidthPercentage((100));


                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        // table.getDefaultCell().setColspan(5);


                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(9);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Triage List for ".toUpperCase() + patCat.toUpperCase(), pFontHeader);
                            table.addCell(phrase);
                                
                            table.getDefaultCell().setColspan(7);

                            
                                phrase = new Phrase("Triage List  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);

                                table.addCell(phrase);
                            

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(1);

                        //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("No.", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Patient No.", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Name", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Age", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Gender", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Vital Signs", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Date", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Time ", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Username", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st11 = connectDB.createStatement();
                            int cash = 0;
                            int scheme = 0;
                            int noSeq = 0;

                            java.sql.PreparedStatement st1 = null;
                            java.sql.PreparedStatement st = null;
                            java.sql.PreparedStatement st2 = null;
                            java.sql.ResultSet rset = null;
                            java.sql.ResultSet rset1 = null;
                            java.sql.ResultSet rset2 = null;
                            // if (patCat.equalsIgnoreCase("All")) {
                            // rset1 = st11.executeQuery("select count(distinct(patient_no)) from hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                            //java.sql.Statement st = connectDB.createStatement();

                            double ages = 0;
//                            for (int i = 0; i < listofAct.length; i++) {
//                                String coTime = "-";
//                                String diagnosis = "-";
//                                System.out.println("item" + listofAct[i]);
                                
                                st1 = connectDB.prepareStatement("SELECT patient_no, patient_name, date, gender, input_date::TIME(0), user_name,input_date FROM hp_signs_record WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by 3,5 ");
                                rset = st1.executeQuery();
                                
                                

                                if(patCat.equalsIgnoreCase("-")){
                                        while (rset.next()) {
                                            

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    
                                     st = connectDB.prepareStatement("SELECT (current_date-year_of_birth::date)/365  FROM hp_patient_register where patient_no='"+rset.getString(1)+"'");
                               rset1 = st.executeQuery();
                               String age="";
                               while(rset1.next()){
                                   age =rset1.getString(1);
                               }
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                
                                    phrase = new Phrase(dbObject.getDBObject(age, "-"), pFontHeader1);
                                    table.addCell(phrase);
                                   
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //  phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader1);
                                    String vsign="";
                                    vsign=vitalsigns(rset.getString(1), rset.getString(7));
                                    phrase = new Phrase(vsign, pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                        }

                                        }else{
                                    
                                        while (rset.next()) {
                                            
                                            java.sql.Statement stmt1c = connectDB.createStatement();
                                            java.sql.ResultSet rset1c = stmt1c.executeQuery("SELECT department FROM hp_patient_visit where date='"+rset.getDate(3)+"' and patient_no='"+rset.getString(1)+"'  order by input_date::time(0) desc limit 1");
                                            
                                            while (rset1c.next())  {
                                            
                                                if(rset1c.getString(1).equalsIgnoreCase(patCat)){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    
                                     st = connectDB.prepareStatement("SELECT (current_date-year_of_birth::date)/365  FROM hp_patient_register where patient_no='"+rset.getString(1)+"'");
                               rset1 = st.executeQuery();
                               String age="";
                               while(rset1.next()){
                                   age =rset1.getString(1);
                               }
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                
                                    phrase = new Phrase(dbObject.getDBObject(age, "-"), pFontHeader1);
                                    table.addCell(phrase);
                                   
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //  phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader1);
                                    String vsign="";
                                    vsign=vitalsigns(rset.getString(1), rset.getString(7));
                                    phrase = new Phrase(vsign, pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                                }
                                    
                                        }
                                    }
                                }

                                
                            
                            table.getDefaultCell().setColspan(9);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        //
                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();

            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

            //deskTop.open(tempFile);


        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
    
    
    
    private String vitalsigns(String patNo,String time){
    String vitalsign="";
    try{    
           
            java.sql.Statement st12 = connectDB.createStatement();
            java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT weight,height,diastolic,"
            + "systolic,pulse,temp,resp,"
            + "blood_group,rhesus,rbs,initcap(comments),input_date::time(0),user_name,bmi,urgency "
            + "from hp_signs_record where patient_no = '" + patNo + "' and input_date='"+time+"' "
            + "  ");
            while(rset12.next()){
      if(rset12.getString(1) != null)  vitalsign=vitalsign+"Weight : " + dbObject.getDBObject(rset12.getObject(1), "-") ;
      if(rset12.getString(2) != null)  vitalsign=vitalsign+ "   Height : " + dbObject.getDBObject(rset12.getObject(2), "-") ;
      if(rset12.getString(14) != null && rset12.getString(2) != null )  vitalsign=vitalsign+ "   BMI : " + dbObject.getDBObject(rset12.getObject(14), "-") ;
      if(rset12.getString(3) != null)  vitalsign=vitalsign+"   Diastolic : " + dbObject.getDBObject(rset12.getObject(3), "-") ;
      if(rset12.getString(4)!= null)  vitalsign=vitalsign+ "   Systolic : " + dbObject.getDBObject(rset12.getObject(4), "-") ;
      if(rset12.getString(5) != null)  vitalsign=vitalsign+"   Pulse : " + dbObject.getDBObject(rset12.getObject(5), "-") ;
      if(rset12.getString(6)!= null)  vitalsign=vitalsign+ "   Temp : " + dbObject.getDBObject(rset12.getObject(6), "-") ;
      if(rset12.getString(7) != null)  vitalsign=vitalsign+ "   Resp : " + dbObject.getDBObject(rset12.getObject(7), "-") ;
      if(rset12.getString(9) != null)  vitalsign=vitalsign+ "   Rhesus : " + dbObject.getDBObject(rset12.getObject(9), "-") ;
      if(rset12.getString(8) != null)  vitalsign=vitalsign+"   Blood Group : " + dbObject.getDBObject(rset12.getObject(8), "-") ;
      if(rset12.getString(10) != null)  vitalsign=vitalsign+ "   Rbs : " + dbObject.getDBObject(rset12.getObject(10), "-") ;
      if(rset12.getString(11) != null)  vitalsign=vitalsign+ "   Comments : " + dbObject.getDBObject(rset12.getObject(11), "-") ;
      if(rset12.getObject(15)!=null){
          System.out.println("Here is the boolean........."+rset12.getObject(15));
      if(rset12.getString(15).startsWith("t")) vitalsign=vitalsign+ "   Emergency : YES"  ;
      
      }

            }
    }catch (java.sql.SQLException sql){
        sql.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sql.getMessage());
            }
     return vitalsign;
}

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
            
                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  order by 2 ASC");
            
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}

                System.out.println("description" + rSet1.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
