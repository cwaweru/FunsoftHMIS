/*
 * ProcessingPayroll.java
 *
 * Created on June 15, 2004, 8:33 PM
 */
package com.afrisoftech.hospayroll;

/**
 *
 * @author root
 */
public class ProcessingPayroll {

    java.sql.Connection connectDB = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    java.util.Date beginDate = null;

    java.util.Date endDate = null;
    java.lang.String compName = null;
    boolean status;
    String staff_no = "";

    /**
     * Creates a new instance of ProcessingPayroll
     */
    public ProcessingPayroll(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String comp, String staffNoo) {

        connectDB = connDb;

        //  pConnDB = pconnDB;
        beginDate = begindate;
        staff_no = staffNoo;

        endDate = endate;
        compName = comp;

    }

    public void processPayroll() {

        //    int exitOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you posted everything & you want to Process the payroll?", "Caution before Processing Payroll!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        //  if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        java.lang.Object[] listofAct = getListofActivities();
        int confOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you have posted everything", "Caution before Processing Payroll!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);

        if (confOption == javax.swing.JOptionPane.YES_OPTION) {

            java.util.Calendar calMonth = java.util.Calendar.getInstance();

            calMonth.setTime(endDate);

            int currentMonth = calMonth.get(calMonth.MONTH);

            java.lang.String monthName = getMonthName(currentMonth);

            System.out.println("Current month label is : [" + monthName + "]");

            String staffNo = null;
            String staffName = null;
            String desc = null;
            // double Amnt = 0.00;
            String type = null;
            String date = null;
            String user = null;

            String staffNo1 = null;
            String month = null;
            double cash = 0.00;
            double noncash = 0.00;
            double taxamt = 0.00;
            double rate = 0.00;
            double relief = 0.00;
            double fixed = 0.00;
            String totalref = null;
            double netaxable = 0.00;
            double tax = 0.00;
            String prelief = null;
            double paye = 0.00;
            String user1 = null;
            String date1 = null;
            String staffNos = null;
            String staffNames = null;
            String dess = null;

            double amounts = 0.00;

            String transType = null;

            double Amnt = 0.00;
            double benefit = 0.00;
            double lower = 0.00;
            double upper = 0.00;
            double rate1 = 0.00;
            double tax1 = 0.00;
            double charge = 0.00;
            double paye1 = 0.00;
            double basic = 0.00;
            double benefit1 = 0.00;
            double nhifs = 0.00;
            double lower1 = 0.00;
            double upper1 = 0.00;
            double rate11 = 0.00;
            double tax11 = 0.00;
            double charge1 = 0.00;
            double paye11 = 0.00;
            double basic1 = 0.00;
            double benefit11 = 0.00;
            double lower11 = 0.00;
            double upper11 = 0.00;
            double rate111 = 0.00;
            double tax111 = 0.00;
            double charge11 = 0.00;
            double paye111 = 0.00;
            double basic11 = 0.00;
            double Amnt1 = 0.00;
            double Prelief1 = 0.00;
            double Amnt11 = 0.00;
            double Amnt11s = 0.00;
            double Amnt111 = 0.00;
            double paye1111 = 0.00;
            double Amnt1111 = 0.00;
            double benefit1111 = 0.00;
            double Prelief = 0.00;
            double hourss = 0.00;
            double ratae = 0.00;
            int g = 0;
            boolean acc_bal = false;
            double mded = 0.00;
            double bal = 0.00;
            double nhifz = 0.00;
            double unused = 0.00;
            boolean exempt = false;
            boolean payeex = false;
            boolean status1 = true;

            try {
                System.out.println(compName);
                connectDB.setAutoCommit(false);
                java.sql.Statement stmtTable1w1 = connectDB.createStatement();
                java.sql.ResultSet rst141sw1 = stmtTable1w1.executeQuery("SELECT processed FROM posting where company_name = '" + compName + "' and date < '" + beginDate.toString() + "' and processed = false ");

                while (rst141sw1.next()) {
                    status1 = rst141sw1.getBoolean(1);
                }
                if (status1 == false) {
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You MUST approve payroll for the previous MONTHs", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                } else {
                    java.sql.Statement stmtTable1w = connectDB.createStatement();
                    java.sql.ResultSet rst141sw = stmtTable1w.executeQuery("SELECT processed FROM posting where company_name = '" + compName + "' and date between '" + beginDate.toString() + "' AND '" + endDate.toString() + "'");

                    while (rst141sw.next()) {
                        status = rst141sw.getBoolean(1);
                    }

                    if (status == false) {

                        System.err.println("Queries to delete");
                        System.err.println("delete from posting WHERE date between  '" + beginDate.toString() + "' and '" + endDate.toString() + "' AND processed = false and company_name = '" + compName + "'");
                        System.err.println("delete from tax_card WHERE date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' and company_name = '" + compName + "'");

                        java.sql.PreparedStatement pstmt31w1 = null;
                        java.sql.PreparedStatement pstmt311z = null;
                        if (staff_no.isEmpty()) {
                            pstmt31w1 = connectDB.prepareStatement("delete from posting WHERE date between  '" + beginDate.toString() + "' and '" + endDate.toString() + "' AND processed = false and company_name = '" + compName + "' ");
                            pstmt31w1.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from tax_card WHERE date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' and company_name = '" + compName + "'");
                            pstmt311z.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from master_updates WHERE date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' and company_name = '" + compName + "'");
                            pstmt311z.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from sacco_balances WHERE end_date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' ");
                            pstmt311z.executeUpdate();
                        } else {
                            pstmt31w1 = connectDB.prepareStatement("delete from posting WHERE date between  '" + beginDate.toString() + "' and '" + endDate.toString() + "' AND processed = false and company_name = '" + compName + "' AND staff_no = '" + staff_no + "' ");
                            pstmt31w1.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from tax_card WHERE date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' and company_name = '" + compName + "' AND staff_no = '" + staff_no + "' ");
                            pstmt311z.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from master_updates WHERE date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' and company_name = '" + compName + "' AND staff_no = '" + staff_no + "' ");
                            pstmt311z.executeUpdate();

                            pstmt311z = connectDB.prepareStatement("delete from sacco_balances WHERE end_date between '" + beginDate.toString() + "' and '" + endDate.toString() + "' AND staff_no = '" + staff_no + "' ");
                            pstmt311z.executeUpdate();
                        }

                        for (int j = 0; j < listofStaffNos.length; j++) {
                            Prelief1 = 0.00;
                            System.err.println("Working on Employee no " + listofStaffNos[j]);

                            java.sql.PreparedStatement pstmtx = connectDB.prepareStatement("INSERT INTO master_updates(staff_no, staff_name, desgination, grade, department, bank_name,  account_no, date, company_name)\n"
                                    + "SELECT  employee_no, trim(first_name || ' ' || middle_name || ' ' || last_name ),official_desgnation , employee_grade, \n"
                                    + "department ,bank,bank_account_no, '" + endDate.toString() + "' AS date,company_name FROM master_file WHERE employee_no = '" + listofStaffNos[j] + "' and company_name = '" + compName + "' ");
                            pstmtx.executeUpdate();

                            java.sql.Statement stmtTable1 = connectDB.createStatement();

                            java.sql.ResultSet rst141s = stmtTable1.executeQuery("SELECT staff_no,staff_name,description, SUM(amount),trans_type,hours,acc_bal FROM posting_view where staff_no = '" + listofStaffNos[j] + "' GROUP BY 1,2,3,5,6,7");

                            while (rst141s.next()) {
                                staffNos = rst141s.getObject(1).toString();
                                staffNames = rst141s.getObject(2).toString();
                                dess = rst141s.getObject(3).toString();
                                amounts = rst141s.getDouble(4);
                                transType = rst141s.getObject(5).toString();
                                hourss = rst141s.getDouble(6);
                                acc_bal = rst141s.getBoolean(7);
                                //if (amounts > 0){
                                java.sql.PreparedStatement pstmt141 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                pstmt141.setString(1, staffNos);
                                pstmt141.setString(2, staffNames);
                                pstmt141.setString(3, dess.toUpperCase());
                                pstmt141.setDouble(4, amounts);
                                pstmt141.setString(5, transType);
                                pstmt141.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                pstmt141.setBoolean(7, true);
                                pstmt141.setString(8, user);
                                pstmt141.setBoolean(9, false);
                                pstmt141.setDouble(10, hourss);
                                pstmt141.setBoolean(11, acc_bal);
                                pstmt141.setString(12, " ");
                                pstmt141.setString(13, " ");
                                pstmt141.setObject(14, compName);
                                System.out.println("Has inserted succesfully 0" + compName);
                                pstmt141.executeUpdate();
                                // }
                            }

                            benefit11 = 0.00;

                            java.sql.Statement pstmtnh21 = connectDB.createStatement();
                            java.sql.ResultSet rstnh1 = pstmtnh21.executeQuery("SELECT bp.amount from posting bp, master_file  WHERE bp.processed = false AND bp.description::text ILIKE 'BASIC PAY%'::text AND master_file.nssfexempt = false AND master_file.employee_no::text = bp.staff_no AND bp.staff_no ='" + listofStaffNos[j] + "'");
                            while (rstnh1.next()) {
                                benefit11 = rstnh1.getDouble(1);
                            }

                            java.sql.Statement pstmtnh211C = connectDB.createStatement();
                            java.sql.ResultSet rstnh11C = pstmtnh211C.executeQuery(" SELECT  sum(amount)  FROM public.posting_journal WHERE  date between '" + beginDate + "' and '" + endDate + "' and staff_no='" + listofStaffNos[j] + "' and allowance_deduction='Insurance Relief'"
                                    + "   UNION  ALL SELECT amount FROM public.allowances_benefits WHERE staff_no='" + listofStaffNos[j] + "' and trans_type='Insurance Relief' ");
                            System.err.println("SELECT  amount  FROM public.posting_journal WHERE  date between '" + beginDate + "' and '" + endDate + "' and staff_no='" + listofStaffNos[j] + "' and allowance_deduction='Insurance Relief'"
                                    + "   UNION  ALL SELECT amount FROM public.allowances_benefits WHERE staff_no='" + listofStaffNos[j] + "' and trans_type='Insurance Relief' ");
                            while (rstnh11C.next()) {
                                Prelief1 = Prelief1 + rstnh11C.getDouble(1);
                            }
                            System.out.println("Insurance Relief is ..........." + Prelief1);

                            java.sql.Statement pstmtnh211 = connectDB.createStatement();
                            java.sql.ResultSet rstnh11 = pstmtnh211.executeQuery("SELECT personal_relief from defined_contribution");
                            while (rstnh11.next()) {
                                Prelief = rstnh11.getDouble(1);
                            }

                            java.sql.Statement pstmt151 = connectDB.createStatement();
                            java.sql.ResultSet rs121 = pstmt151.executeQuery("select charge from tax_bracket where " + benefit11 + " between lower_limit AND upper_limit and (tax_type ILIKE 'NSSF%' OR tax_type ILIKE 'N.S.F%')");
                            while (rs121.next()) {
                                charge11 = rs121.getDouble(1);
                            }

                            java.sql.Statement pstmt133 = connectDB.createStatement();
                            java.sql.ResultSet rst143 = pstmt133.executeQuery("SELECT nssfexempt from master_file st where st.employee_no ILIKE '" + listofStaffNos[j] + "' ");

                            while (rst143.next()) {
                                exempt = rst143.getBoolean(1);
                                desc = "N.S.S.F";
                                type = "Deduction";
                            }
                            System.out.println(charge);
                            paye111 = charge11;

                            java.sql.Statement pstmt131 = connectDB.createStatement();
                            java.sql.ResultSet rst141 = pstmt131.executeQuery("SELECT st.staff_no,st.staff_name,date('now') AS date,true,user,false from posting st where st.staff_no = '" + listofStaffNos[j] + "' ");
                            while (rst141.next()) {
                                staffNo = rst141.getObject(1).toString();
                                staffName = rst141.getObject(2).toString();
                                desc = "N.S.S.F";
                                Amnt11s = paye111;
                                type = "Less Relief";
                                date = rst141.getObject(3).toString();
                                user = rst141.getObject(5).toString();
                            }
                            if (exempt == true) {

                            } else {
                                if (Amnt11s > 0) {

                                    java.sql.PreparedStatement pstmt141 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmt141.setString(1, staffNo);
                                    pstmt141.setString(2, staffName);
                                    pstmt141.setString(3, desc.toUpperCase());
                                    pstmt141.setDouble(4, Amnt11s);
                                    pstmt141.setString(5, type);
                                    pstmt141.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt141.setBoolean(7, true);
                                    pstmt141.setString(8, user);
                                    pstmt141.setBoolean(9, false);
                                    pstmt141.setDouble(10, 0.00);
                                    pstmt141.setBoolean(11, false);
                                    pstmt141.setString(12, " ");
                                    pstmt141.setString(13, " ");
                                    pstmt141.setObject(14, compName);
                                    pstmt141.executeUpdate();
                                    System.out.println("Has inserted succesfully 1" + compName);
                                }
                            }

                            //--------------------Pension control self-------------------
                            Boolean pension = false;

                            pstmt133 = connectDB.createStatement();
                            rst143 = pstmt133.executeQuery("SELECT pension,basic_pay from master_file st where st.employee_no ILIKE '" + listofStaffNos[j] + "' ");

                            while (rst143.next()) {
                                pension = rst143.getBoolean(1);
                                desc = "Pension Contr. Self";
                                type = "Deduction";
                             //   Amnt11s = rst143.getDouble(2) * 7 / 100; //commeneted by sam
                            }

                            java.sql.Statement stmt1 = connectDB.createStatement();
                            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT amount FROM  allowances_benefits WHERE staff_no = '" + listofStaffNos[j] + "' AND description ilike 'BASIC PAY' ");
                            while (rSet1.next()) {
                                Amnt11s = rSet1.getDouble(1) * 7 / 100;

                            }

                            System.out.println(charge);
                            // paye111 = charge11;

                            pstmt131 = connectDB.createStatement();
                            rst141 = pstmt131.executeQuery("SELECT st.staff_no,st.staff_name,date('now') AS date,true,user,false from posting st where st.staff_no = '" + listofStaffNos[j] + "' ");
                            while (rst141.next()) {
                                staffNo = rst141.getObject(1).toString();
                                staffName = rst141.getObject(2).toString();
                                desc = "PENSION CONTR. SELF";
                                //Amnt11s = paye111;
                                type = "Less Relief";
                                date = rst141.getObject(3).toString();
                                user = rst141.getObject(5).toString();
                            }
                            if (pension == false) {

                            } else {
                                if (Amnt11s > 0) {

                                    java.sql.PreparedStatement pstmt141 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmt141.setString(1, staffNo);
                                    pstmt141.setString(2, staffName);
                                    pstmt141.setString(3, desc.toUpperCase());
                                    pstmt141.setDouble(4, Amnt11s);
                                    pstmt141.setString(5, type);
                                    pstmt141.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt141.setBoolean(7, true);
                                    pstmt141.setString(8, user);
                                    pstmt141.setBoolean(9, false);
                                    pstmt141.setDouble(10, 0.00);
                                    pstmt141.setBoolean(11, false);
                                    pstmt141.setString(12, " ");
                                    pstmt141.setString(13, " ");
                                    pstmt141.setObject(14, compName);
                                    pstmt141.executeUpdate();
                                    System.out.println("Has inserted succesfully 1" + compName);
                                }
                            }

                            //------------------End Pension Control Self------------
                            java.sql.Statement pstmt151q = connectDB.createStatement();
                            java.sql.ResultSet rs121q = pstmt151q.executeQuery("select description,rate,acc_balances from deductions_allowances where allowance_deduction ILIKE 'Less Relief%' and rate >0");
                            while (rs121q.next()) {
                                desc = rs121q.getString(1);
                                ratae = rs121q.getDouble(2);
                                acc_bal = rs121q.getBoolean(3);
                            }

                            Amnt11 = benefit11 * ratae / 100;

                            if (desc.equalsIgnoreCase("Provident Fund")) {
                                java.sql.Statement pstmt13e = connectDB.createStatement();
                                java.sql.ResultSet rst14e = pstmt13e.executeQuery("SELECT payeexempt from master_file st where st.employee_no = '" + listofStaffNos[j] + "' ");
                                while (rst14e.next()) {
                                    payeex = rst14e.getBoolean(1);
                                }
                                if (payeex == false) {
                                    java.sql.PreparedStatement pstmt141 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmt141.setString(1, staffNo);
                                    pstmt141.setString(2, staffName);
                                    pstmt141.setString(3, desc.toUpperCase());
                                    pstmt141.setDouble(4, Amnt11);
                                    pstmt141.setString(5, type);
                                    pstmt141.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt141.setBoolean(7, true);
                                    pstmt141.setString(8, user);
                                    pstmt141.setBoolean(9, false);
                                    pstmt141.setDouble(10, 0.00);
                                    pstmt141.setBoolean(11, acc_bal);
                                    pstmt141.setString(12, " ");
                                    pstmt141.setString(13, " ");
                                    pstmt141.setObject(14, compName);
                                    pstmt141.executeUpdate();
                                    System.out.println("Has inserted succesfully 2" + compName);

                                }
                            } else {
                                if (Amnt11 > 0) {
                                    java.sql.PreparedStatement pstmt141 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmt141.setString(1, staffNo);
                                    pstmt141.setString(2, staffName);
                                    pstmt141.setString(3, desc.toUpperCase());
                                    pstmt141.setDouble(4, Amnt11);
                                    pstmt141.setString(5, type);
                                    pstmt141.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt141.setBoolean(7, true);
                                    pstmt141.setString(8, user);
                                    pstmt141.setBoolean(9, false);
                                    pstmt141.setDouble(10, 0.00);
                                    pstmt141.setBoolean(11, acc_bal);
                                    pstmt141.setString(12, " ");
                                    pstmt141.setString(13, " ");
                                    pstmt141.setObject(14, compName);
                                    pstmt141.executeUpdate();
                                    System.out.println("Has inserted succesfully 21" + compName);
                                }
                            }
                            java.sql.Statement pstmt5 = connectDB.createStatement();
                            java.sql.Statement pstmt62 = connectDB.createStatement();
                            java.sql.Statement pstmt621 = connectDB.createStatement();
                            java.sql.ResultSet rs62 = pstmt621.executeQuery("SELECT unused_relief from master_file where employee_no ilike '" + listofStaffNos[j] + "'");

                            while (rs62.next()) {
                                unused = rs62.getDouble(1);
                            }

                            Boolean disabled = false;
                            Boolean payeeexempt = false;
                            Boolean withholdingTax = false;
                            Boolean withholdingTax2 = false;
                            Boolean policeTax = false;
                            Boolean knunded = false;
                            java.sql.Statement psd = connectDB.createStatement();
                            java.sql.ResultSet rsd = psd.executeQuery("SELECT disabled,payeexempt,withholding_tax,police_tax,knun_ded, withholding_tax2 FROM master_file where employee_no ilike '" + listofStaffNos[j] + "'  ");
                            while (rsd.next()) {
                                disabled = rsd.getBoolean(1);
                                payeeexempt = rsd.getBoolean(2);
                                withholdingTax = rsd.getBoolean(3);
                                withholdingTax2 = rsd.getBoolean(6);
                                policeTax = rsd.getBoolean(4);
                                knunded = rsd.getBoolean(5);
                            }

                            Double rel = 0.00;
                            java.sql.Statement psx = connectDB.createStatement();
                            java.sql.ResultSet rsx = psx.executeQuery("SELECT staff_no,sum(amount) from net_taxable_amount1 where staff_no ilike '" + listofStaffNos[j] + "'  and amount<0 group by staff_no ");
                            while (rsx.next()) {
                                rel = rsx.getDouble(2) * -1;
                            }

                            rsx = psx.executeQuery("SELECT sum(amount) from posting_journal where staff_no ilike '" + listofStaffNos[j] + "'  and description ilike 'ABSENTEESM (DAYS)' and date between '" + beginDate + "' and '" + endDate + "'  ");
                            while (rsx.next()) {
                                rel = rel - rsx.getDouble(1);
                            }

                            java.sql.Statement ps = connectDB.createStatement();
                            java.sql.ResultSet rs = ps.executeQuery("SELECT staff_no,sum(amount) from total_net_taxable_amount1 where staff_no ilike '" + listofStaffNos[j] + "'  group by staff_no");
                            while (rs.next()) {
                                benefit = rs.getDouble(2);
                            }

                            if (rel > 20000) {
                                benefit = benefit + (rel - 20000);
                            }

                            if (disabled) {
                                benefit = benefit - 150000;
                            }
                            java.sql.Statement pstmt1 = connectDB.createStatement();

                            java.sql.ResultSet rs1 = pstmt1.executeQuery("select lower_limit,upper_limit,rate,charge from tax_bracket where  " + benefit + " between lower_limit and upper_limit and (tax_type ilike 'PAYE%' OR tax_type ilike 'P.A.Y.E%')");

                            while (rs1.next()) {
                                lower = rs1.getDouble(1);
                                upper = rs1.getDouble(2);
                                rate1 = rs1.getDouble(3);
                                charge = rs1.getDouble(4);

                                System.out.println(lower + " ," + upper + " , " + rate1);
                            }
                            System.err.println(">>>>>"+benefit);
                            System.err.println(">>>>><<<<"+(benefit- lower));
                            tax1 = (benefit - lower) * rate1 / 100;
                            System.out.println(tax1);

                            /* java.sql.ResultSet rs2 = pstmt5.executeQuery("select  from tax_bracket where rate = '"+rate1+"' and (tax_type ilike 'PAYE%' or tax_type ilike 'P.a.y.%')");
                            
                             while (rs2.next()){
                             charge = rs2.getDouble(1);
                             }
                             System.out.println(charge);
                             */
                            
                             paye1 = (charge + tax1) - (Prelief + Prelief1);
                            System.out.println("--------------------------------------");
                            System.out.println("Paye -- "+paye1);
                            System.out.println("charge -- "+charge);
                            System.out.println("tax1 -- "+tax1);
                            System.out.println("Prelief -- "+Prelief);
                            System.out.println("Prelief 1 -- "+Prelief1);
                           

                            java.sql.Statement pstmt11 = connectDB.createStatement();
                            java.sql.ResultSet rst1 = pstmt11.executeQuery("SELECT distinct st.staff_no,st.staff_name,true,user,false from posting st where st.staff_no ILIKE '" + listofStaffNos[j] + "' ");
                            while (rst1.next()) {
                                staffNo = rst1.getObject(1).toString();
                                staffName = rst1.getObject(2).toString();
                                desc = "P.A.Y.E";
                                if (unused > 0) {
                                    Amnt = 0;
                                } else {
                                    Amnt = Math.round(paye1);
                                }
                                type = "Deduction";
                                // date = rst1.getObject(3).toString();
                                user = rst1.getObject(4).toString();
                            }
                            System.out.println("Amount  -- "+Amnt);

                            if (!payeeexempt) {
                                if (Amnt > 0) {
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting values(?,?,?,CEILING(?),?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, staffNo);
                                    pstmt.setString(2, staffName);
                                    pstmt.setString(3, desc.toUpperCase());
                                    pstmt.setDouble(4, Amnt);
                                    pstmt.setString(5, type);
                                    pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt.setBoolean(7, true);
                                    pstmt.setString(8, user);
                                    pstmt.setBoolean(9, false);
                                    pstmt.setDouble(10, 0.00);
                                    pstmt.setBoolean(11, false);
                                    pstmt.setString(12, " ");
                                    pstmt.setString(13, " ");
                                    pstmt.setObject(14, compName);
                                    pstmt.executeUpdate();

                                    java.sql.PreparedStatement pstmtw = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    pstmtw.setString(1, staffNo);
                                    pstmtw.setString(2, staffName);
                                    pstmtw.setString(3, "Monthly Personal Relief".toUpperCase());
                                    pstmtw.setDouble(4, Prelief);
                                    pstmtw.setString(5, "Monthly Personal Relief");
                                    pstmtw.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmtw.setBoolean(7, true);
                                    pstmtw.setString(8, user);
                                    pstmtw.setBoolean(9, false);
                                    pstmtw.setDouble(10, 0.00);
                                    pstmtw.setBoolean(11, false);
                                    pstmtw.setString(12, " ");
                                    pstmtw.setString(13, " ");
                                    pstmtw.setObject(14, compName);
                                    pstmtw.executeUpdate();
                                    System.out.println("Has inserted succesfully 3" + compName);
                                }
                            }

                            if (withholdingTax) {

                                desc = "Withholding Tax";
                                benefit = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT sum(amount) from payroll_postings where staff_no ilike '" + listofStaffNos[j] + "' and (date between '" + beginDate + "' and '" + endDate + "' OR type='Recurrent') group by staff_no");
                                while (rs.next()) {
                                    benefit = rs.getDouble(1);
                                }

                                Double wthrate = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT rate from tax_bracket where tax_type ilike 'W.H.TAX' and '" + benefit + "'  between lower_limit and upper_limit  ");
                                while (rs.next()) {
                                    wthrate = rs.getDouble(1);
                                }
                                Amnt = benefit * wthrate / 100;
                                if (Amnt > 0) {
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, staffNo);
                                    pstmt.setString(2, staffName);
                                    pstmt.setString(3, desc.toUpperCase());
                                    pstmt.setDouble(4, Amnt);
                                    pstmt.setString(5, type);
                                    pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt.setBoolean(7, true);
                                    pstmt.setString(8, user);
                                    pstmt.setBoolean(9, false);
                                    pstmt.setDouble(10, 0.00);
                                    pstmt.setBoolean(11, false);
                                    pstmt.setString(12, " ");
                                    pstmt.setString(13, " ");
                                    pstmt.setObject(14, compName);
                                    pstmt.executeUpdate();
                                }
                            }

                            if (withholdingTax2) {

                                System.err.println("Deducting withholding tax 2....................");
                                desc = "Paye 3";
                                benefit = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT sum(amount) from payroll_postings where staff_no ilike '" + listofStaffNos[j] + "' and (date between '" + beginDate + "' and '" + endDate + "' OR type='Recurrent') group by staff_no");
                                while (rs.next()) {
                                    benefit = rs.getDouble(1);
                                }
                                System.err.println("Benefit..................." + benefit);

                                Double wthrate = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT rate from tax_bracket where tax_type ilike 'W.H.TAX30' and '" + benefit + "'  between lower_limit and upper_limit  ");
                                while (rs.next()) {
                                    wthrate = rs.getDouble(1);
                                }
                                Amnt = benefit * wthrate / 100;
                                System.err.println("Amt..................." + Amnt);
                                if (Amnt > 0) {
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, staffNo);
                                    pstmt.setString(2, staffName);
                                    pstmt.setString(3, desc.toUpperCase());
                                    pstmt.setDouble(4, Amnt);
                                    pstmt.setString(5, type);
                                    pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt.setBoolean(7, true);
                                    pstmt.setString(8, user);
                                    pstmt.setBoolean(9, false);
                                    pstmt.setDouble(10, 0.00);
                                    pstmt.setBoolean(11, false);
                                    pstmt.setString(12, " ");
                                    pstmt.setString(13, " ");
                                    pstmt.setObject(14, compName);
                                    pstmt.executeUpdate();
                                }
                            }

                            if (knunded) {
                                desc = "KNUN DED 1.5% BASIC";
                                benefit = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT sum(amount) from allowances_benefits where staff_no ilike '" + listofStaffNos[j] + "'   AND description = 'BASIC PAY' ");
                                while (rs.next()) {
                                    benefit = rs.getDouble(1);
                                }

                                Double knunrate = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT rate from tax_bracket where tax_type ilike 'KNUN_DED' ");
                                while (rs.next()) {
                                    knunrate = rs.getDouble(1);
                                }

                                Amnt = benefit * knunrate / 100;
                                if (Amnt > 0) {
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, staffNo);
                                    pstmt.setString(2, staffName);
                                    pstmt.setString(3, desc.toUpperCase());
                                    pstmt.setDouble(4, Amnt);
                                    pstmt.setString(5, type);
                                    pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt.setBoolean(7, true);
                                    pstmt.setString(8, user);
                                    pstmt.setBoolean(9, false);
                                    pstmt.setDouble(10, 0.00);
                                    pstmt.setBoolean(11, false);
                                    pstmt.setString(12, " ");
                                    pstmt.setString(13, " ");
                                    pstmt.setObject(14, compName);
                                    pstmt.executeUpdate();
                                }
                            }

                            if (policeTax) {
                                desc = "Paye 2";
                                benefit = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT sum(amount) from total_net_taxable_amount1 where staff_no ilike '" + listofStaffNos[j] + "'  group by staff_no");
                                while (rs.next()) {
                                    benefit = rs.getDouble(1);
                                }

                                Double policerate = 0.00;
                                ps = connectDB.createStatement();
                                rs = ps.executeQuery("SELECT rate from tax_bracket where tax_type ilike 'POLICE.TAX' ");
                                while (rs.next()) {
                                    policerate = rs.getDouble(1);
                                }

                                Amnt = benefit * policerate / 100;
                                if (Amnt > 0) {
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, staffNo);
                                    pstmt.setString(2, staffName);
                                    pstmt.setString(3, desc.toUpperCase());
                                    pstmt.setDouble(4, Amnt);
                                    pstmt.setString(5, type);
                                    pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                    pstmt.setBoolean(7, true);
                                    pstmt.setString(8, user);
                                    pstmt.setBoolean(9, false);
                                    pstmt.setDouble(10, 0.00);
                                    pstmt.setBoolean(11, false);
                                    pstmt.setString(12, " ");
                                    pstmt.setString(13, " ");
                                    pstmt.setObject(14, compName);
                                    pstmt.executeUpdate();
                                }
                            }

                            //---------------------------INSERT FROM SACCO DEDUCTIONS--------------------------------------------------
                            java.sql.Statement pstmtn = connectDB.createStatement();
                            java.sql.ResultSet rstn = pstmtn.executeQuery("SELECT sacco_name, amount, month_deduction,trans_type,loan_id  FROM sacco_deductions WHERE active_loan=TRUE AND staff_no ILIKE '" + listofStaffNos[j] + "' and sacco_name not ilike 'Pension%' ");
                            while (rstn.next()) {
                                desc = rstn.getString(1);
                                Amnt = rstn.getDouble(3);
                                type = "Deduction";
                                String loan = "";
                                String loan_id = "";
                                loan_id = rstn.getString(5);

//                                java.sql.Statement stmtTable1x = connectDB.createStatement();
                                System.err.println("SELECT balance_category FROM deductions_allowances where upper(description) = '" + desc.toUpperCase() + "' ");
//                                java.sql.ResultSet rsetTable1 = stmtTable1x.executeQuery("SELECT balance_category FROM deductions_allowances where upper(description) = '" + desc.toUpperCase() + "'  ");
//
//                                while (rsetTable1.next()) {
//                                    loan = rsetTable1.getString(1);
//                                    //    monthHours = rsetTable1.getDouble(1);
//                                }

                                String cat = "";
                                java.sql.Statement pss = connectDB.createStatement();
                                java.sql.ResultSet rst = pss.executeQuery("SELECT balance_category,category FROM deductions_allowances where upper(description) = '" + desc.toUpperCase() + "' ");
                                while (rst.next()) {
                                    loan = rst.getObject(1).toString();
                                    cat = rst.getObject(2).toString();
                                }

                                if (loan.equalsIgnoreCase("Bal")) {
                                    //if(!cat.equalsIgnoreCase("Bal_")){
//                                        java.sql.Statement stmtTable11 = connectDB.createStatement();
//                                        java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("SELECT sum(amount) FROM payroll_balances WHERE  sacco_name ILIKE '" + desc + "' and staff_no ILIKE '" + listofStaffNos[j] + "'");
//                                        while (rsetTable11.next()) {
//                                            if (Amnt > rsetTable11.getDouble(1)) {
//                                                Amnt = rsetTable11.getDouble(1);
//                                            }
//                                        }

                                    java.sql.Statement stmtTable11 = connectDB.createStatement();
                                    java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("SELECT sum(amount) FROM payroll_balances WHERE  sacco_name ILIKE '" + desc + "' and staff_no ILIKE '" + listofStaffNos[j] + "'");
                                    while (rsetTable11.next()) {
                                        if (Amnt > rsetTable11.getDouble(1)) {
                                            Amnt = rsetTable11.getDouble(1);
                                        }
                                    }

                                    //}
                                    if (Amnt > 0) {
                                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting(staff_no, staff_name, description, amount, allowance_deduction,  date, approved, approved_by, processed, hoursdays, acc_bal, bank,  acc_no, company_name, loan_id) values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?,?)");
                                        pstmt.setString(1, staffNo);
                                        pstmt.setString(2, staffName);
                                        pstmt.setString(3, desc.toUpperCase());
                                        pstmt.setDouble(4, Amnt);
                                        pstmt.setString(5, type);
                                        pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                        pstmt.setBoolean(7, true);
                                        pstmt.setString(8, user);
                                        pstmt.setBoolean(9, false);
                                        pstmt.setDouble(10, 0.00);
                                        pstmt.setBoolean(11, false);
                                        pstmt.setString(12, " ");
                                        pstmt.setString(13, " ");
                                        pstmt.setObject(14, compName);
                                        pstmt.setString(15, loan_id);
                                        pstmt.executeUpdate();
                                    }
                                } else {

                                    if (Amnt > 0) {
                                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into posting(staff_no, staff_name, description, amount, allowance_deduction,  date, approved, approved_by, processed, hoursdays, acc_bal, bank,  acc_no, company_name, loan_id) values(?,?,?,ROUND(?),?,?,?,?,?,?,?,?,?,?,?)");
                                        pstmt.setString(1, staffNo);
                                        pstmt.setString(2, staffName);
                                        pstmt.setString(3, desc.toUpperCase());
                                        pstmt.setDouble(4, Amnt);
                                        pstmt.setString(5, type);
                                        pstmt.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                        pstmt.setBoolean(7, true);
                                        pstmt.setString(8, user);
                                        pstmt.setBoolean(9, false);
                                        pstmt.setDouble(10, 0.00);
                                        pstmt.setBoolean(11, false);
                                        pstmt.setString(12, " ");
                                        pstmt.setString(13, " ");
                                        pstmt.setObject(14, compName);
                                        pstmt.setString(15, loan_id);
                                        pstmt.executeUpdate();
                                    }
                                }
                            }

                            //----------------------------------------------------------------------------------------------------
                            nhifz = 0.00;
                            java.sql.Statement pstmtnh = connectDB.createStatement();

                            java.sql.ResultSet rstnh = pstmtnh.executeQuery("SELECT sum(bp.amount) from posting bp WHERE bp.processed = false AND (bp.allowance_deduction::text ILIKE 'Earning%'::text OR  bp.allowance_deduction::text ILIKE 'Non Cash%'::text) AND bp.staff_no ILIKE '" + listofStaffNos[j] + "' AND UPPER(bp.description::text) NOT IN (SELECT UPPER(description) FROM public.deductions_allowances WHERE taxable = false ) ");
                            //java.sql.ResultSet rstnh = pstmtnh.executeQuery("SELECT staff_no,sum(amount) from total_net_taxable_amount1 where staff_no ilike '" + listofStaffNos[j] + "'  group by staff_no");
                            while (rstnh.next()) {
                                nhifz = rstnh.getDouble(1);
                            }

                            java.sql.Statement pstmt15 = connectDB.createStatement();
                            java.sql.ResultSet rs12 = pstmt15.executeQuery("select charge from tax_bracket where " + nhifz + " between lower_limit AND upper_limit and (tax_type ILIKE 'NHIF%' OR tax_type ILIKE 'N.H.I.F%')");

                            while (rs12.next()) {
                                nhifs = rs12.getDouble(1);
                            }

                            java.sql.Statement pstmt13 = connectDB.createStatement();
                            java.sql.ResultSet rst14 = pstmt13.executeQuery("SELECT nhifexempt from master_file st where st.employee_no ILIKE '" + listofStaffNos[j] + "' ");

                            while (rst14.next()) {
                                exempt = rst14.getBoolean(1);
                                desc = "N.H.I.F";
                                type = "Deduction";
                            }
//                            if (nhifs > 320) {
//                                nhifs = 320;
//                            } else {
//                                nhifs = nhifs;
//                            }
                            java.sql.PreparedStatement pstmt14 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt14.setString(1, staffNo);
                            pstmt14.setString(2, staffName);
                            pstmt14.setString(3, desc.toUpperCase());
                            if (exempt == false) {
                                pstmt14.setDouble(4, nhifs);
                            } else {
                                pstmt14.setDouble(4, 0.00);
                            }
                            pstmt14.setString(5, type);
                            pstmt14.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                            pstmt14.setBoolean(7, true);
                            pstmt14.setString(8, user);
                            pstmt14.setBoolean(9, false);
                            pstmt14.setDouble(10, 0.00);
                            pstmt14.setBoolean(11, false);
                            pstmt14.setString(12, " ");
                            pstmt14.setString(13, " ");
                            pstmt14.setObject(14, compName);
                            pstmt14.executeUpdate();
                            System.out.println("Has inserted succesfully 4");

                            benefit1111 = 0.00;

                            java.sql.Statement pstmtnh2 = connectDB.createStatement();

                            java.sql.ResultSet rstnh2 = pstmtnh2.executeQuery("SELECT charge from pay_slip_view1 bp WHERE bp.staff_no ='" + listofStaffNos[j] + "' AND (tax_type ILIKE 'UNION%' OR tax_type ILIKE 'U.N.I.O.N%')");

                            while (rstnh2.next()) {
                                benefit1111 = rstnh2.getDouble(1);
                            }

                            paye1111 = benefit1111;

                            java.sql.Statement pstmt132 = connectDB.createStatement();
                            java.sql.ResultSet rst142 = pstmt132.executeQuery("SELECT st.staff_no,st.staff_name,date('now') AS date,true,user,false from posting st, master_file where st.staff_no = '" + listofStaffNos[j] + "' AND master_file.unionexempt = false AND master_file.employee_no::text = st.staff_no");
                            while (rst142.next()) {
                                staffNo = rst142.getObject(1).toString();
                                staffName = rst142.getObject(2).toString();
                                desc = "U.N.I.O.N.";
                                //   Amnt111 = paye111;
                                type = "Deduction";
                                date = rst142.getObject(3).toString();
                                user = rst142.getObject(5).toString();
                            }
                            if (paye1111 > 0) {
                                //java.sql.ResultSet rs = pstmt.executeQuery("INSERT INTO posting (staff_no,staff_name,description,amount,allowance_deduction,date,approved,approved_by,processed) SELECT st.staff_no,st.name,st.tax_type,st.amount,CASE WHEN (st.tax_type = da.description) THEN da.allowance_deduction ELSE null END AS description,date('now'),true,user,false from statutory_deductions_view st,deductions_allowances da where st.tax_type = da.description AND st.staff_no = '"+listofStaffNos[j]+"'");
                                java.sql.PreparedStatement pstmt142 = connectDB.prepareStatement("insert into posting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                pstmt142.setString(1, staffNo);
                                pstmt142.setString(2, staffName.toUpperCase());
                                pstmt142.setString(3, desc.toUpperCase());
                                if (paye1111 > 150) {
                                    pstmt142.setDouble(4, 150);
                                } else {
                                    // pstmt142.setDouble(4,150);
                                    pstmt142.setDouble(4, paye1111);
                                }
                                pstmt142.setString(5, type);
                                pstmt142.setDate(6, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                pstmt142.setBoolean(7, true);
                                pstmt142.setString(8, user);
                                pstmt142.setBoolean(9, false);
                                pstmt142.setDouble(10, 0.00);
                                pstmt142.setBoolean(11, false);
                                pstmt142.setString(12, " ");
                                pstmt142.setString(13, " ");
                                pstmt142.setObject(14, compName);
                                pstmt142.executeUpdate();
                            }

                            java.sql.Statement pstmt1z = connectDB.createStatement();

                            java.sql.ResultSet rs1z = pstmt1z.executeQuery("select rate,fixed_amount from defined_contribution");

                            while (rs1z.next()) {
                                rate = rs1z.getDouble(1);
                                fixed = rs1z.getDouble(2);
                            }

                            java.sql.Statement pstmt = connectDB.createStatement();
                            java.sql.ResultSet rst = pstmt.executeQuery("SELECT st.staff_no,sum(st.amount) from posting st where st.staff_no = '" + listofStaffNos[j] + "' AND st.allowance_deduction ILIKE 'Earning%' AND st.processed = false GROUP BY st.staff_no");

                            while (rst.next()) {
                                staffNo1 = rst.getObject(1).toString();
                                cash = rst.getDouble(2);
                            }

                            java.sql.Statement pstmtzx = connectDB.createStatement();
                            java.sql.ResultSet rstzx = pstmtzx.executeQuery("SELECT sum(st.amount) from posting st where st.staff_no = '" + listofStaffNos[j] + "' AND st.allowance_deduction ILIKE 'Non Cash%' AND st.processed = false ");

                            while (rstzx.next()) {
                                noncash = rstzx.getDouble(1);
                            }
                            //      noncash = rst.getObject(4).toString();
                            taxamt = benefit + charge11;
                            netaxable = benefit;
                            tax = tax1 + charge;
                            paye = paye1;

                            if (staffNo1 != null) {
                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into tax_card values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                pstmt2.setString(1, staffNo1);
                                pstmt2.setString(2, monthName);
                                pstmt2.setDouble(3, cash);
                                pstmt2.setDouble(4, noncash);
                                pstmt2.setDouble(5, 0.00);
                                pstmt2.setDouble(6, cash + noncash);
                                pstmt2.setDouble(7, (rate / 100) * (cash + noncash));
                                pstmt2.setDouble(8, 0.00);
                                pstmt2.setDouble(9, fixed);
                                pstmt2.setDouble(10, 0.00);
                                pstmt2.setDouble(11, Amnt11 + Amnt11s);
                                pstmt2.setDouble(12, benefit);
                                pstmt2.setDouble(13, charge + tax1);
                                pstmt2.setDouble(14, Prelief);
                                pstmt2.setDouble(15, 0.00);
                                pstmt2.setDouble(16, paye);
                                pstmt2.setDate(17, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
                                pstmt2.setString(18, user);
                                pstmt2.setObject(19, compName);
                                pstmt2.executeUpdate();
                            }

                            java.sql.PreparedStatement pstmt311x = connectDB.prepareStatement("UPDATE posting SET bank = master_file.bank, acc_no = master_file.bank_account_no from master_file WHERE staff_no ILIKE '" + listofStaffNos[j] + "' AND staff_no = master_file.employee_no and processed = false");
                            pstmt311x.executeUpdate();
                            System.out.println("Has inserted succesfully 5");

                            pstmt311x = connectDB.prepareStatement("INSERT INTO public.sacco_balances(staff_no, staff_name, amount, month_deduction, deducted_amount,  balance, sacco_name, end_date)\n"
                                    + "SELECT  staff_no, staff_name, amount, month_deduction, deducted_amount, \n"
                                    + "CASE WHEN ((( SELECT deductions_allowances.balance_category  FROM deductions_allowances \n"
                                    + "WHERE deductions_allowances.description::text =  payroll_balances2.sacco_name::text))::text) = 'TOTAL'::text \n"
                                    + "THEN amount+deducted_amount   ELSE amount-deducted_amount END  as   balance, sacco_name ,'" + endDate + "'::date \n"
                                    + "FROM payroll_balances2 where staff_no='" + listofStaffNos[j] + "' AND \n"
                                    + " sacco_name not ilike 'Staff Welfare' AND  sacco_name not ilike 'UNION DUE'\n"
                                    + "    ");
                            pstmt311x.executeUpdate();

                            //  connectDB.commit();
                            //  connectDB.setAutoCommit(true);
                        }
                        connectDB.commit();
                        connectDB.setAutoCommit(true);
                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Processing Payroll Complete", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You cannot process approved payroll \n for '" + beginDate.toString() + "' and '" + endDate.toString() + "'", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }

            } catch (java.sql.SQLException sqlex) {
                sqlex.printStackTrace();
                System.out.println(sqlex.getMessage());
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (confOption == javax.swing.JOptionPane.NO_OPTION) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Thank you for taking caution...", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;

            if (staff_no.isEmpty()) {
                rSet1 = stmt1.executeQuery("(SELECT DISTINCT ab.staff_no FROM allowances_benefits ab where ab.company_name =  '" + compName + "'    UNION SELECT DISTINCT ab.staff_no FROM posting_journal ab where ab.company_name =  '" + compName + "'  ) EXCEPT SELECT employee_no  FROM master_file WHERE suspend=TRUE OR retired = TRUE  ORDER BY 1 ");

            } else {
                rSet1 = stmt1.executeQuery("(SELECT DISTINCT ab.staff_no FROM allowances_benefits ab where ab.staff_no = '" + staff_no + "' AND ab.company_name =  '" + compName + "'    UNION SELECT DISTINCT ab.staff_no FROM posting_journal ab where ab.staff_no = '" + staff_no + "' AND  ab.company_name =  '" + compName + "'  ) EXCEPT SELECT employee_no  FROM master_file WHERE suspend=TRUE OR retired = TRUE ORDER BY 1");

            }
// java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where approved ORDER BY staff_no");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tax_type FROM statutory_deductions_view order by tax_type");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }

    public java.lang.String getMonthName(int monthInteger) {

        java.lang.String month_now_strs = null;

        switch (monthInteger) {

            case 0:
                month_now_strs = "January";

                break;

            case 1:
                month_now_strs = "February";

                break;

            case 2:
                month_now_strs = "March";

                break;

            case 3:
                month_now_strs = "April";

                break;

            case 4:
                month_now_strs = "May";

                break;

            case 5:
                month_now_strs = "June";

                break;

            case 6:
                month_now_strs = "July";

                break;

            case 7:
                month_now_strs = "August";

                break;

            case 8:
                month_now_strs = "September";

                break;

            case 9:
                month_now_strs = "October";

                break;

            case 10:
                month_now_strs = "November";

                break;

            case 11:
                month_now_strs = "December";

                break;

            default:

                month_now_strs = "January";

        }

        return month_now_strs;
    }

}
