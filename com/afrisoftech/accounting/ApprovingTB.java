/*
 * ProcessingPayroll.java
 *
 * Created on June 15, 2004, 8:33 PM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  root
 */
public class ApprovingTB {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.lang.String Fcode = "-";
    
    java.lang.String Password = null;
    
    java.lang.String Comp = null;
    
    /** Creates a new instance of ProcessingPayroll */
    public ApprovingTB(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String password, java.lang.String comp) {
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        Password = password;
        
        Comp = comp;
    }
    
    public void ApprovingTB() {
        
        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        java.lang.Object listofAct[] = getListofActivities();
        java.lang.Object items[] = getListofActivitiesq();
        java.lang.Object department[] = getListofDepartments();
        java.lang.Object paymode[] = getListofPaymode();
        
        java.util.Calendar calMonth = java.util.Calendar.getInstance();
        
        calMonth.setTime(endDate);
        
        int currentMonth = calMonth.get(calMonth.MONTH);
        
        java.lang.String monthName = getMonthName(currentMonth);
        
        System.out.println("Current month label is : ["+monthName+"]");
        
        String staffNo = null;
        String staffName = null;
        String desc = null;
        String type = null;
        String date = null;
        String user = null;
        String staffNo1 = null;
        String month = null;
        double gross = 0.00;
        String account = null;
        String invoiceNo = null;
        double amt = 0.00;
        String description = "-";
        String name = null;
        String accountCodes = "-";
        String dess = null;
        String Stock = null;
        String voucNo = null;
        String accountCode = "-";
        String TransNo = null;
        String glType = null;
        String salaryCode = "-";
        String salary = null;
        String userName = null;
        String institution = null;
        String instNo = null;
        int g = 0;
        boolean acc_bal = false;
        double mded = 0.00;
        double bal = 0.00;
        double netPay = 0.00;
        
        try {
            
            connectDB.setAutoCommit(false);
            
            
            java.sql.Statement stmtTable1 = connectDB.createStatement();
            
            java.sql.ResultSet rst141s = stmtTable1.executeQuery("SELECT name FROM defined_contribution");
            
            while (rst141s.next()){
                dess = rst141s.getObject(1).toString();
            }
            
            java.sql.Statement pst1k = connectDB.createStatement();
            java.sql.ResultSet rs1k = pst1k.executeQuery("select code,activity from pb_activity where activity_category ILIKE 'sc%'");
            while (rs1k.next()){
                salaryCode = rs1k.getObject(1).toString();
                salary = rs1k.getObject(2).toString();
            }
            java.sql.Statement ps = connectDB.createStatement();
            java.sql.ResultSet rs2 = ps.executeQuery("select nextval('transaction_no_seq'),current_user");
            while (rs2.next()){
                TransNo = rs2.getObject(1).toString();
                userName = rs2.getObject(2).toString();
            }
            java.sql.Statement pst2 = connectDB.createStatement();
            java.sql.ResultSet rs = pst2.executeQuery("select 'IN'||lpad(nextval('invoice_no_seq'),5,0)");
            while (rs.next()){
                invoiceNo = rs.getObject(1).toString();
            }
            if (dess.equalsIgnoreCase(Password)){
                
                for (int j = 0; j < listofStaffNos.length; j++) {
                    if (listofStaffNos[j] == null) {
                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Please No salaries for Approving","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        for (int i = 0; i < listofAct.length; i++) {
                            java.sql.Statement pstmtgq = connectDB.createStatement();
                            java.sql.ResultSet rstgq = pstmtgq.executeQuery("SELECT month_deduction,balance from sacco_deductions where staff_no = '"+listofStaffNos[j]+"' AND sacco_name ='"+listofAct[i]+"' AND staff_no = posting.staff_no AND sacco_name = posting.description and posting.processed = 'false' and acc_bal = 'false'");
                            while (rstgq.next()){
                                mded = rstgq.getDouble(1);
                                bal = rstgq.getDouble(2);
                            }
                            
                            if (bal < mded){
                                java.sql.PreparedStatement p = connectDB.prepareStatement("UPDATE sacco_deductions SET balance = balance - balance WHERE  sacco_deductions.staff_no = '"+listofStaffNos[j]+"' AND sacco_deductions.sacco_name ='"+listofAct[i]+"' AND staff_no = posting.staff_no AND sacco_name = posting.description and posting.processed = 'false' and acc_bal = 'false'");
                                p.executeUpdate();
                                
                                System.out.println("Has inserted succesfully 6");
                            } else{
                                java.sql.PreparedStatement p = connectDB.prepareStatement("UPDATE sacco_deductions SET balance = balance - month_deduction WHERE  sacco_deductions.staff_no = '"+listofStaffNos[j]+"'  AND sacco_deductions.sacco_name ='"+listofAct[i]+"' AND staff_no = posting.staff_no AND sacco_name = posting.description and posting.processed = 'false' and acc_bal = 'false'");
                                p.executeUpdate();
                            }
                            
                            //  System.out.println("Has inserted succesfully 6");
                            java.sql.Statement pstmtg = connectDB.createStatement();
                            java.sql.ResultSet rstg = pstmtg.executeQuery("SELECT count(st.staff_no) from sacco_deductions st where st.staff_no = '"+listofStaffNos[j]+"' AND sacco_name ='"+listofAct[i]+"' AND st.sacco_name = posting.description and posting.processed = false and st.acc_bal = true AND st.staff_no = posting.staff_no");
                            while (rstg.next()){
                                g = rstg.getInt(1);
                            }
                            
                            System.out.println("Has inserted succesfully 7");
                            if (g > 0){
                                java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("UPDATE sacco_deductions SET balance = balance + posting.amount WHERE staff_no = '"+listofStaffNos[j]+"' AND staff_no = posting.staff_no AND sacco_name ='"+listofAct[i]+"' AND sacco_name = posting.description and posting.processed = false and deductions_allowances.acc_balances = true");
                                pstmt31.executeUpdate();
                            } else {
                                System.out.println("Has inserted succesfully 8");
                                java.sql.Statement pstmt132g = connectDB.createStatement();
                                java.sql.PreparedStatement pstmt142q = connectDB.prepareStatement("insert into sacco_deductions (staff_no,staff_name,amount,month_deduction,sacco_no,date,sacco_name,interest,balance,trans_type,acc_bal) SELECT st.staff_no,st.staff_name,'0.00','0.00','-',date,description,'0.00',amount,allowance_deduction,acc_bal from posting st where st.staff_no = '"+listofStaffNos[j]+"' AND description ='"+listofAct[i]+"' AND  processed = false and acc_bal = true AND (st.allowance_deduction ilike 'Deduct%' or st.allowance_deduction ilike 'less%')");
                                pstmt142q.executeUpdate();
                                System.out.println("Has inserted ");
                            }
                        }
                        
                    }
                }
                String bankName = null;
                
                for (int p = 0; p < paymode.length; p++) {
                    if (paymode[p].toString().equalsIgnoreCase("Bank Transfer")){
                        
                        java.lang.Object[] banks = getListofBank(paymode[p]);
                        for (int b = 0; b < banks.length; b++) {
                            java.sql.Statement pst10 = connectDB.createStatement();
                            java.sql.Statement pst1 = connectDB.createStatement();
                            java.sql.Statement psty = connectDB.createStatement();
                            
                            netPay = 0.00;
                            String payNo = null;
                            
                            java.sql.ResultSet rset10 = pst10.executeQuery("select distinct employee_no from master_file WHERE company_name ilike '"+Comp+"' and bank ILIKE '"+banks[b]+"' AND payment_mode ilike '"+paymode[p]+"%'");
                            while (rset10.next()){
                                payNo = rset10.getObject(1).toString();
                                
                                java.sql.ResultSet rsy = psty.executeQuery("SELECT SUM(amount) from net_pay_view where date = '"+endDate.toString()+"' and staff_no = '"+payNo+"' group by staff_no");
                                while (rsy.next()){
                                    netPay = netPay + rsy.getDouble(1);
                                }
                            }
                            
                            
                            java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_accounts_payable values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                            
                            // java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_payroll_ledger values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                            pstmt1d.setObject(1,salaryCode);
                            pstmt1d.setObject(2,banks[b]);
                            pstmt1d.setObject(3,"Salary "+monthName);
                            pstmt1d.setString(4,"-");
                            pstmt1d.setObject(5,banks[b]);
                            pstmt1d.setString(6,"Salary "+monthName);
                            pstmt1d.setString(7,staffNo);
                            pstmt1d.setString(8,"");
                            pstmt1d.setString(9,"");
                            pstmt1d.setString(10,"");
                            pstmt1d.setString(11,TransNo);
                            pstmt1d.setString(12,"Raise Invoice");
                            pstmt1d.setString(13,"00");
                            pstmt1d.setDouble(14,netPay);
                            pstmt1d.setString(15,endDate.toString());
                            pstmt1d.setObject(16,monthName+" "+invoiceNo);
                            pstmt1d.setString(17,"SALARIES PAYABLE");
                            pstmt1d.setBoolean(18,true);
                            pstmt1d.setBoolean(19,false);
                            pstmt1d.setString(20,userName);
                            pstmt1d.setString(21,"");
                            pstmt1d.setObject(22,endDate.toString());
                            pstmt1d.setObject(23,endDate.toString());
                            pstmt1d.setObject(24,"0.0");
                            pstmt1d.setDouble(25,netPay);
                            pstmt1d.setObject(26,"0.0");
                            pstmt1d.executeUpdate();
                            
                            java.sql.PreparedStatement pstmt281 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_credit = ytd_credit + '"+netPay+"',mtd_credit = mtd_credit + '"+netPay+"'  where code = '"+salaryCode+"' ;commit work");
                            pstmt281.executeUpdate();
                        }
                        
                        
                    }else{
                        if (paymode[p].toString().equalsIgnoreCase("Cheque")){
                            String payNo = null;
                            java.sql.Statement pst10 = connectDB.createStatement();
                            java.sql.Statement psty = connectDB.createStatement();
                            
                            java.sql.ResultSet rset10 = pst10.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name from master_file WHERE company_name ilike '"+Comp+"' and payment_mode ilike '"+paymode[p]+"%' order by employee_no");
                            while (rset10.next()){
                                payNo = rset10.getObject(1).toString();
                                staffName = rset10.getString(2);
                                
                                java.sql.ResultSet rsy = psty.executeQuery("SELECT staff_no,SUM(amount) from net_pay_view where date = '"+endDate.toString()+"' and staff_no = '"+payNo+"' group by staff_no");
                                while (rsy.next()){
                                    staffNo = rsy.getString(1);
                                    netPay = rsy.getDouble(2);
                                    
                                    
                                    java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_accounts_payable values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    
                                    // java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_payroll_ledger values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt1d.setObject(1,salaryCode);
                                    pstmt1d.setObject(2,staffName);
                                    pstmt1d.setObject(3,"Salary "+monthName);
                                    pstmt1d.setString(4,"-");
                                    pstmt1d.setObject(5,staffName);
                                    pstmt1d.setString(6,"Salary "+monthName);
                                    pstmt1d.setString(7,staffNo);
                                    pstmt1d.setString(8,"");
                                    pstmt1d.setString(9,"");
                                    pstmt1d.setString(10,"");
                                    pstmt1d.setString(11,TransNo);
                                    pstmt1d.setString(12,"Raise Invoice");
                                    pstmt1d.setString(13,"00");
                                    pstmt1d.setDouble(14,netPay);
                                    pstmt1d.setString(15,endDate.toString());
                                    pstmt1d.setObject(16,monthName+" "+invoiceNo);
                                    pstmt1d.setString(17,"SALARIES PAYABLE");
                                    pstmt1d.setBoolean(18,true);
                                    pstmt1d.setBoolean(19,false);
                                    pstmt1d.setString(20,userName);
                                    pstmt1d.setString(21,"");
                                    pstmt1d.setObject(22,endDate.toString());
                                    pstmt1d.setObject(23,endDate.toString());
                                    pstmt1d.setObject(24,"0.0");
                                    pstmt1d.setDouble(25,netPay);
                                    pstmt1d.setObject(26,"0.0");
                                    
                                    pstmt1d.executeUpdate();
                                    
                                    java.sql.PreparedStatement pstmt281 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_credit = ytd_credit + '"+netPay+"',mtd_credit = mtd_credit + '"+netPay+"'  where code = '"+salaryCode+"' ;commit work");
                                    pstmt281.executeUpdate();
                                }
                            }
                            
                        }else{
                            if (paymode[p].toString().equalsIgnoreCase("Cash")){
                                
                                String payNo = null;
                                java.sql.Statement pst10 = connectDB.createStatement();
                                java.sql.ResultSet rset10 = pst10.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name from master_file WHERE company_name ilike '"+Comp+"' and payment_mode ilike 'Cash%' order by employee_no");
                                //  String payNo = null;
                                while (rset10.next()){
                                    payNo = rset10.getObject(1).toString();
                                    staffName = rset10.getString(2);
                                    
                                    java.sql.Statement psty = connectDB.createStatement();
                                    //  java.sql.ResultSet rsy = psty.executeQuery("SELECT staff_no,SUM(amount) from net_pay_view where date = '"+endDate.toString()+"' and staff_no = '"+listofStaffNos[j]+"' group by staff_no");
                                    
                                    java.sql.ResultSet rsy = psty.executeQuery("SELECT staff_no,SUM(amount) from net_pay_view where date = '"+endDate.toString()+"' and staff_no = '"+payNo+"' group by staff_no");
                                    while (rsy.next()){
                                        staffNo = rsy.getString(1);
                                        netPay = rsy.getDouble(2);
                                        
                                        java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_accounts_payable values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                        
                                        // java.sql.PreparedStatement pstmt1d = connectDB.prepareStatement("insert into ac_payroll_ledger values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                        pstmt1d.setObject(1,salaryCode);
                                        pstmt1d.setObject(2,staffName);
                                        pstmt1d.setObject(3,"Salary "+monthName);
                                        pstmt1d.setString(4,"-");
                                        pstmt1d.setObject(5,staffName);
                                        pstmt1d.setString(6,"Salary "+monthName);
                                        pstmt1d.setString(7,staffNo);
                                        pstmt1d.setString(8,"");
                                        pstmt1d.setString(9,"");
                                        pstmt1d.setString(10,"");
                                        pstmt1d.setString(11,TransNo);
                                        pstmt1d.setString(12,"Raise Invoice");
                                        pstmt1d.setString(13,"00");
                                        pstmt1d.setDouble(14,netPay);
                                        pstmt1d.setString(15,endDate.toString());
                                        pstmt1d.setObject(16,monthName+" "+invoiceNo);
                                        pstmt1d.setString(17,"SALARIES PAYABLE");
                                        pstmt1d.setBoolean(18,true);
                                        pstmt1d.setBoolean(19,false);
                                        pstmt1d.setString(20,userName);
                                        pstmt1d.setString(21,"");
                                        pstmt1d.setObject(22,endDate.toString());
                                        pstmt1d.setObject(23,endDate.toString());
                                        pstmt1d.setObject(24,"0.0");
                                        pstmt1d.setDouble(25,netPay);
                                        pstmt1d.setObject(26,"0.0");
                                        pstmt1d.executeUpdate();
                                        
                                        java.sql.PreparedStatement pstmt281 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_credit = ytd_credit + '"+netPay+"',mtd_credit = mtd_credit + '"+netPay+"'  where code = '"+salaryCode+"' ;commit work");
                                        pstmt281.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                    
                }
                
                
                
                
                for (int j = 0; j < items.length; j++) {
                    java.sql.Statement pstmtg = connectDB.createStatement();
                    java.sql.ResultSet rstg = pstmtg.executeQuery("SELECT st.description,sum(st.amount) from posting st where st.description  ilike '"+items[j]+"%' and st.processed = false and company_name ilike '"+Comp+"' group by st.description");
                    while (rstg.next()){
                        description = rstg.getString(1);
                        amt = rstg.getDouble(2);
                    }
                    
                    
                    
                    java.sql.Statement pstmtg1 = connectDB.createStatement();
                    java.sql.ResultSet rstg1 = pstmtg1.executeQuery("SELECT st.code,st.activity from pb_activity st where st.code = deductions_allowances.gl_code and deductions_allowances.description ilike '"+items[j]+"%'");
                    // java.sql.ResultSet rstg1 = pstmtg1.executeQuery("SELECT st.code,st.activity from pb_activity st where st.code = '"+Fcode+"'");
                    while (rstg1.next()){
                        accountCodes = rstg1.getString(1);
                        name = rstg1.getString(2);
                    }
                    
                    java.sql.Statement pstmtg11 = connectDB.createStatement();
                    java.sql.ResultSet rstg11 = pstmtg11.executeQuery("SELECT institution_name,institution_no from statutory_institutions where deduction_type ILIKE '"+items[j]+"%'");
                    while (rstg11.next()){
                        institution = rstg11.getString(1);
                        instNo = rstg11.getString(2);
                    }
                    
                    java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into ac_accounts_payable values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?,?,?,?,?,?,?,?)");
                    pstmt1.setObject(1,accountCodes);
                    pstmt1.setObject(2,institution);
                    pstmt1.setObject(3,description);
                    pstmt1.setString(4,"");
                    pstmt1.setObject(5,institution);
                    pstmt1.setString(6,description);
                    pstmt1.setString(7,instNo);
                    pstmt1.setString(8,"");
                    pstmt1.setString(9,"");
                    pstmt1.setString(10,"");
                    pstmt1.setString(11,TransNo);
                    pstmt1.setString(12,"Raise Invoice");
                    pstmt1.setString(13,"00");
                    pstmt1.setDouble(14,amt);
                    pstmt1.setString(15,endDate.toString());
                    pstmt1.setObject(16,invoiceNo);
                    pstmt1.setString(17,"CREDITORS PAYABLE");
                    pstmt1.setBoolean(18,true);
                    pstmt1.setBoolean(19,false);
                    pstmt1.setString(20,userName);
                    pstmt1.setString(21,"");
                    pstmt1.setObject(22,endDate.toString());
                    pstmt1.setObject(23,endDate.toString());
                    pstmt1.setObject(24,"0.0");
                    pstmt1.setDouble(25,amt);
                    pstmt1.setObject(26,"0.0");
                    pstmt1.executeUpdate();
                    
                    java.sql.PreparedStatement pstmt2811 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_credit = ytd_credit + '"+amt+"',mtd_credit = mtd_credit + '"+amt+"'  where code = '"+accountCodes+"' ;commit work");
                    pstmt2811.executeUpdate();
                }
                
                
                String employeeno = null;
                
                for (int d = 0; d < department.length; d++) {
                    java.sql.Statement pst10 = connectDB.createStatement();
                    java.sql.Statement pst1 = connectDB.createStatement();
                    java.sql.ResultSet rs1 = pst1.executeQuery("select employee_no from master_file where department ilike '"+department[d]+"%' and company_name ilike '"+Comp+"'");
                    double grossDept = 0.00;
                    
                    
                    while (rs1.next()){
                        employeeno = rs1.getObject(1).toString();
                        java.sql.ResultSet rset10 = pst10.executeQuery("select sum(amount) from posting WHERE processed = false and allowance_deduction ILIKE 'Earning%' and company_name ilike '"+Comp+"' and staff_no = '"+employeeno+"'");
                        while (rset10.next()){
                            grossDept = grossDept  + rset10.getDouble(1);
                        }
                    }
                    
                    java.sql.Statement pst112 = connectDB.createStatement();
                    java.sql.ResultSet rs112 = pst112.executeQuery("select salary_account from pb_departments where department_name ILIKE '"+department[d]+"'");
                    while (rs112.next()){
                        accountCode = rs112.getObject(1).toString();
                    }
                    
                    java.sql.Statement pst11 = connectDB.createStatement();
                    java.sql.ResultSet rs11 = pst11.executeQuery("select activity from pb_activity where code ILIKE '"+accountCode+"'");
                    while (rs11.next()){
                        glType = rs11.getObject(1).toString();
                    }
                    

                    
                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                    pstmt2.setObject(1,accountCode);
                    pstmt2.setObject(2,glType);
                    pstmt2.setString(3,"-");
                    pstmt2.setObject(4,"Staff");
                    pstmt2.setObject(5,"Staff Salaries");
                    pstmt2.setObject(6,"");
                    pstmt2.setObject(7,"Payroll Staff");
                    pstmt2.setString(8,"");
                    pstmt2.setString(9,"");
                    pstmt2.setString(10,"");
                    pstmt2.setString(11,"");
                    pstmt2.setString(12,"");
                    pstmt2.setString(13,"");
                    pstmt2.setString(14,account);
                    pstmt2.setString(15,"Raise Invoice");
                    pstmt2.setString(17,"0.00");
                    pstmt2.setDouble(16,grossDept);
                    pstmt2.setObject(18,endDate.toString());
                    pstmt2.setString(19,TransNo);
                    pstmt2.setBoolean(20,false);
                    pstmt2.setBoolean(21,false);
                    pstmt2.setBoolean(22,false);
                    pstmt2.setString(23,userName);
                    pstmt2.executeUpdate();
                    java.sql.PreparedStatement pstmt281 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_debit = ytd_debit + '"+grossDept+"',mtd_debit = mtd_debit + '"+grossDept+"'  where code = '"+accountCode+"' ;commit work");
                    pstmt281.executeUpdate();
                    
                }
                
                
                
                
                
                java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("UPDATE posting SET processed ='true' where  company_name ilike '"+Comp+"'");// WHERE staff_no = '"+listofStaffNos[j]+"'");
                pstmt3.executeUpdate();
                
                java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("UPDATE posting_journal SET processed ='true' where  company_name ilike '"+Comp+"'");// WHERE staff_no = '"+listofStaffNos[j]+"'");
                pstmt31.executeUpdate();
                
                connectDB.commit();
                connectDB.setAutoCommit(true);
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Payroll Approved. Proceed to print Payslips?","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Wrong Password !! Contact System Administrator.","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(java.sql.SQLException sqlex){
            System.out.println(sqlex.getMessage());
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(),sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where processed = 'false' and company_name ilike '"+Comp+"' ORDER BY staff_no");
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
    
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where  company_name ilike '"+Comp+"' order by description");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
    public java.lang.Object[] getListofActivitiesq() {
        
        java.lang.Object[] listofActivitiesq = null;
        
        java.util.Vector listActVectorq = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where (allowance_deduction ilike 'deduction%' OR allowance_deduction ilike 'less%') and company_name ilike '"+Comp+"' order by description");
            
            while (rSet1.next()) {
                
                listActVectorq.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivitiesq = listActVectorq.toArray();
        System.out.println("Done list of activities ...");
        return listofActivitiesq;
    }
    
    
    public java.lang.Object[] getListofDepartments() {
        
        java.lang.Object[] listofdepartments = null;
        
        java.util.Vector listActVectordept = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT department FROM master_file WHERE company_name ilike '"+Comp+"' ORDER BY department");
            
            while (rSet1.next()) {
                
                listActVectordept.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofdepartments = listActVectordept.toArray();
        System.out.println("Done list of activities ...");
        return listofdepartments;
    }
    
    
    public java.lang.Object[] getListofPaymode() {
        
        java.lang.Object[] listofpaymode = null;
        
        java.util.Vector listActVectorpaymode = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payment_mode FROM master_file WHERE company_name ilike '"+Comp+"' ORDER BY payment_mode");
            
            while (rSet1.next()) {
                
                listActVectorpaymode.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofpaymode = listActVectorpaymode.toArray();
        System.out.println("Done list of activities ...");
        return listofpaymode;
    }
    
    
    public java.lang.Object[] getListofBank(java.lang.Object paymodes) {
        
        java.lang.Object[] listofbank = null;
        
        java.util.Vector listActVectorbank = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT bank FROM master_file WHERE company_name ilike '"+Comp+"' AND payment_mode = '"+paymodes+"' ORDER BY bank");
            
            while (rSet1.next()) {
                
                listActVectorbank.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofbank = listActVectorbank.toArray();
        System.out.println("Done list of activities ...");
        return listofbank;
    }
    
    
    public java.lang.String getMonthName(int monthInteger) {
        
        java.lang.String month_now_strs = null;
        
        switch (monthInteger) {
            
            case 0 : month_now_strs = "January";
            
            break;
            
            case 1 : month_now_strs = "February";
            
            break;
            
            case 2 : month_now_strs = "March";
            
            break;
            
            case 3 : month_now_strs = "April";
            
            break;
            
            case 4 : month_now_strs = "May";
            
            break;
            
            case 5 : month_now_strs = "June";
            
            break;
            
            case 6 : month_now_strs = "July";
            
            break;
            
            case 7 : month_now_strs = "August";
            
            break;
            
            case 8 : month_now_strs = "September";
            
            break;
            
            case 9 : month_now_strs = "October";
            
            break;
            
            case 10 : month_now_strs = "November";
            
            break;
            
            case 11 : month_now_strs = "December";
            
            break;
            
            default :
                
                month_now_strs = "January";
                
        }
        
        return month_now_strs;
    }
    
}
