///Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.util.ArrayList;

public class UseraccountsGroupsPdf implements java.lang.Runnable {

    java.lang.String Departments = null;
    java.lang.String MName = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    double osBalance = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeaderxx = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String visitId;
    String groupp = "";

    public void userAccountsPdf(java.sql.Connection connDb, java.lang.String department, String groupN) {

        Departments = department;
        groupp = groupN;

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
        // beginDate = begindate;

        //endDate = endate;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(Departments);

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String date = null;

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("USER GROUPS - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();
//table one
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();
                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));
                        table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                        Phrase phrase = new Phrase();

                        try {
                            java.sql.Statement st4 = connectDB.createStatement();

                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(7);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                            table1.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader11);
                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(7);
                            phrase = new Phrase("DEPARTMENT: " + Departments, pFontHeader11);
                            table1.addCell(phrase);

                            while (rset4.next()) {
                                table1.getDefaultCell().setColspan(7);
                                date = rset4.getObject(1).toString();
                                phrase = new Phrase("SYSTEM USERS AS ON :" + date, pFontHeader11);

                                table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

//table 2    
                    try {

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(12);
                        //table2.setSplitLate(false);

                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        // table.endHeaders();
                        int headerwidths[] = {10, 10, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};

                        //login_name,f_name,l_name,department,group_name
                        table2.setWidths(headerwidths);

                        table2.setWidthPercentage((100));
                        //table2.setHeaderRows(1);
                        table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table2.getDefaultCell().setBorder(Rectangle.BOX);

                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

//                    

                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            for (int i = 0; i < listofAct.length; i++) {

                                table2.getDefaultCell().setBorder(Rectangle.BOX);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table2.getDefaultCell().setColspan(2);
                                Phrase phrase = new Phrase("GROUP NAME", pFontHeader11);
                                table2.addCell(phrase);

                                table2.getDefaultCell().setColspan(10);
                                phrase = new Phrase((i+1)+". "+listofAct[i].toString().toUpperCase(), pFontHeaderxx);
                                table2.addCell(phrase);

                                table2.getDefaultCell().setColspan(2);
                                table2.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                                phrase = new Phrase("USERS", pFontHeader11);
                                table2.addCell(phrase);

                                int h = 1;
                                String con = "";
                                if (!Departments.equalsIgnoreCase("-")) {
                                    con = "AND department = '" + Departments + "'";
                                }
                                java.sql.Statement stmt = connectDB.createStatement();
                                //SELECT distinct upper(login_name) as login_name, upper(f_name||' '||l_name) as user_name, department, false as dormant_account, (CASE WHEN (SELECT rolcanlogin FROM pg_catalog.pg_roles WHERE secure_menu_access.login_name = pg_catalog.pg_roles.rolname) = true THEN 'ACTIVE' ELSE 'DORMANT' END) as ACCOUNT_STATUS, m_name as OTHER_NAMES from secure_menu_access WHERE  login_name IN (SELECT pg_catalog.pg_roles.rolname FROM pg_catalog.pg_roles WHERE rolcanlogin = true) order by 1
                               java.sql.ResultSet rs = stmt.executeQuery("SELECT DISTINCT login_name  FROM public.secure_menu_access WHERE login_name IN (SELECT pg_catalog.pg_roles.rolname  FROM pg_catalog.pg_roles WHERE rolcanlogin  = true) AND upper(group_name) = '" + listofAct[i].toString().toUpperCase() + "' "+con+" ");
                                while (rs.next()) {
                                    table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                    //table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    System.err.println("User " + rs.getString(1));
                                    if ((h - 1) % 5 == 0 && h != 1) {

                                        phrase = new Phrase(" ", pFontHeader);
                                        table2.addCell(phrase);

                                    }
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(h + ". " + rs.getString(1), pFontHeader);
                                    table2.addCell(phrase);

                                    h++;
                                }

                                h--;
                                if (h == 0) {
                                    table2.getDefaultCell().setColspan(10);
                                    phrase = new Phrase(" ", pFontHeader);
                                    table2.addCell(phrase);
                                }

                                boolean eol = false;
                                for (int j = 0; j < 5; j++) {
                                    if (!eol) {
                                        System.err.println("-----" + h);
                                        if (h % 5 != 0) {
                                            System.err.println("Add cell - " + h);
                                            phrase = new Phrase(" ", pFontHeader);
                                            table2.addCell(phrase);
                                            h++;
                                        } else {
                                            System.err.println(" EOL - " + h);
                                            eol = true;
                                        }
                                    }
                                }
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.ResultSet rset3 = null;
                                java.lang.String[] listSet = null;
                                java.sql.ResultSet rsetArray = null;
                                rset3 = st3.executeQuery("SELECT DISTINCT menu_list,sys_name  FROM public.secure_menu_access_group WHERE sys_name not ilike '%hr%' and sys_name not ilike '%payroll%' and UPPER(login_name) = '" + listofAct[i].toString().toUpperCase() + "' order by 2 desc");
                                while (rset3.next()) {
                                    java.sql.Array arraySet = rset3.getArray("menu_list");
                                    java.lang.Object[] listSetTest = (java.lang.Object[]) arraySet.getArray();
                                    if (!(listSetTest == null)) {
                                        listSet = (java.lang.String[]) arraySet.getArray();
                                        rsetArray = arraySet.getResultSet();
                                    } else {
                                        listSet = null;

                                    }

                                    table2.getDefaultCell().setColspan(2);
                                    table2.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                                    phrase = new Phrase("RIGHTS - " + rset3.getString("sys_name").toUpperCase(), pFontHeader11);
                                    table2.addCell(phrase);
                                    
                                    h=0;

                                    for (int j = 1; j <= listSet.length; j++) {
                                        if (j < 6) {
                                            table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                                        } else {
                                            table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        }
                                        if ((j - 1) % 5 == 0 && j != 1) {

                                            phrase = new Phrase(" ", pFontHeader);
                                            table2.addCell(phrase);

                                        }
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(j + ". " + listSet[j - 1], pFontHeader);
                                        table2.addCell(phrase);

                                        h = j;
                                    }

                                    //h--;
                                    if (h == 0) {
                                        table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                                        table2.getDefaultCell().setColspan(10);
                                        phrase = new Phrase(" ", pFontHeader);
                                        table2.addCell(phrase);
                                    }

                                    eol = false;
                                    for (int j = 0; j < 5; j++) {
                                        if (!eol) {
                                            System.err.println("-----" + h);
                                            if (h % 5 != 0) {
                                                System.err.println("Add cell - " + h);
                                                phrase = new Phrase(" ", pFontHeader);
                                                table2.addCell(phrase);
                                                h++;
                                            } else {
                                                System.err.println(" EOL - " + h);
                                                eol = true;
                                            }
                                        }
                                    }

                                }

                                table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                                table2.getDefaultCell().setColspan(48);
                                phrase = new Phrase("     ", pFontHeader);
                                table2.addCell(phrase);
                                table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                table2.addCell(phrase);
                                table2.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
                                table2.addCell(phrase);

                            }



                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table2);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

//table 2 end
                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }


    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;
        String condition = "";

        java.util.Vector listActVector = new java.util.Vector(1, 1);
        if (groupp.equalsIgnoreCase("-")) {

        } else {
            condition = "AND UPPER(login_name) = '" + groupp.toUpperCase() + "' ";
        }

        try {

            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
            if (Departments.equalsIgnoreCase("-")) {
                rSet1 = st3.executeQuery("SELECT DISTINCT login_name as group_name  FROM public.secure_menu_access_group WHERE sys_name not ilike '%hr%' and sys_name not ilike '%payroll%' " + condition + " order by 1 ASC");

            } else {
                rSet1 = st3.executeQuery("SELECT DISTINCT login_name as group_name  FROM public.secure_menu_access_group WHERE sys_name not ilike '%hr%' and sys_name not ilike '%payroll%' " + condition + " AND login_name IN (SELECT DISTINCT group_name  FROM public.secure_menu_access WHERE department = '" + Departments + "') order by 1 ASC");

            }

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }

}
