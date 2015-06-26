/*
 * DepreciationClass.java
 *
 * Created on June 15, 2004, 8:33 PM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  root
 */
public class DepreciationClass {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    boolean status;
    
    /** Creates a new instance of DepreciationClass */
    public DepreciationClass(java.sql.Connection connDb,  org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
     //   beginDate = begindate;
        
     //   endDate = endate;
        
        
    }
    
    public void DepreciationClass(){
        
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you want to transact depreciation?", "Caution before Depreciation!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
          if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
        java.lang.Object[] listofAct = getListofActivities();
        int confOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you want to activate Depreciation", "Caution before depreciation!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (confOption == javax.swing.JOptionPane.YES_OPTION) {
            
          //  java.util.Calendar calMonth = java.util.Calendar.getInstance();
            
        //    calMonth.setTime(endDate);
            
        //    int currentMonth = calMonth.get(calMonth.MONTH);
            
        //    java.lang.String monthName = getMonthName(currentMonth);
            
            
            float charges = 00;
            float Amount = 00;
            String depreciationmethod = null;
            
            try {
                connectDB.setAutoCommit(false);
             //   java.sql.PreparedStatement pstm = connectDB.prepareStatement("INSERT INTO depreciation_charge (description,buying_price,depreciation_charge,transaction_no,date) SELECT description,amount,amount-amount,transaction_no,date from depreciation_assets_view EXCEPT select description,buying_price,depreciation_charge,transaction_no,date from depreciation_charge");
             //   pstm.executeUpdate();
                
                
                
                
                java.sql.Statement pstmt2 = connectDB.createStatement();
                
                java.sql.ResultSet rs2 = pstmt2.executeQuery("select depreciation_method from depreciation_method");
                // java.sql.ResultSet rs1 = pstmt1.executeQuery("select sum(amount) from depreciation_charges");
                
                while (rs2.next()){
                    //jTextField4.setText(rs.getObject(1).toString());
                    depreciationmethod = rs2.getString(1);
                    // System.out.println(amt);
                }
                
                if(depreciationmethod.equals("Straight Line Method")){
                    
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    
                    long dateNow = calendar.getTimeInMillis();
                    
                    java.sql.Date datenowSql= new java.sql.Date(dateNow);
                    String rCode = null;
                    String rName = null;
                    String accountCode = null;
                    String TransNo = null;
                    String glType = null;
                    String asset = null;
                    String rate = null;
                    String userName = null;
                    
                    java.sql.Statement stm22 = connectDB.createStatement();
                    
                    java.sql.ResultSet rs22 = stm22.executeQuery("select sum((dr.cost)*dr.depreciation_rate/100)::numeric(30,2) from ac_asset_allocation dr WHERE dr.cost > dr.depreciation and approved = false");
                    while (rs22.next())
                        Amount = rs22.getFloat(1);
                    
                    java.sql.Statement stm1211 = connectDB.createStatement();
                    java.sql.ResultSet rse1211 = stm1211.executeQuery("select activity,code from pb_activity where activity_category ='DP'");
                    while (rse1211.next())
                        rName = rse1211.getObject(1).toString();
                    rCode = rse1211.getObject(2).toString();
                    java.sql.Statement ps = connectDB.createStatement();
                    java.sql.ResultSet rs26 = ps.executeQuery("select nextval('transaction_no_seq'),current_user");
                    while (rs26.next())
                        TransNo = rs26.getObject(1).toString();
                    userName = rs26.getObject(2).toString();
                    //  for (int i = 0; i< jTable1.getRowCount(); i++) {
                    //     if (jTable1.getModel().getValueAt(i,3) != null){
                    java.sql.Statement pst1 = connectDB.createStatement();
                    java.sql.ResultSet rs1 = pst1.executeQuery("select dr.gl_code,dr.asset_category,dr.asset_type,sum((cost)*dr.depreciation_rate/100)::numeric(30,2) from ac_asset_allocation dr WHERE dr.cost > dr.depreciation and approved = false group by dr.asset_type,dr.gl_code,dr.asset_category");
                    // java.sql.ResultSet rs1 = pst1.executeQuery("select dr.code,dr.name,gl.description,(sum((gl.debit - gl.credit)*dr.rate/100)::numeric(30,2)) from pb_depreciation_rates dr,ac_ledger gl WHERE gl.description = dr.asset_type group by gl.description,dr.code,dr.name");
                    
                    while (rs1.next()){
                        accountCode = rs1.getObject(1).toString();
                        glType = rs1.getObject(2).toString();
                        asset = rs1.getObject(3).toString();
                        rate = rs1.getObject(4).toString();
                        
    /*                    java.sql.PreparedStatement pstmt25 = connectDB.prepareStatement("insert into ac_ledger values(?, ?, ?, ?, initcap(?), ?, initcap(?), ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                        pstmt25.setObject(1,accountCode);
                        pstmt25.setObject(2,glType);
                        pstmt25.setString(3,"");
                        pstmt25.setObject(4,"");
                        pstmt25.setObject(5,"");
                        pstmt25.setString(6,"");
                        pstmt25.setObject(7,"");
                        pstmt25.setString(8,"");
                        pstmt25.setString(9,"");
                        pstmt25.setString(10,"");
                        pstmt25.setString(11,"");
                        pstmt25.setString(12,"");
                        pstmt25.setString(13,"");
                        pstmt25.setString(14,asset);
                        pstmt25.setString(15,"Depreciation");
                        pstmt25.setString(16,rate);
                        pstmt25.setObject(17,"00");
                        pstmt25.setString(18,"'now'");
                        pstmt25.setString(19,""+TransNo+"");
                        pstmt25.setBoolean(20,false);
                        pstmt25.setBoolean(21,false);
                        pstmt25.setString(23,userName);
                        pstmt25.setBoolean(22,false);
                        pstmt25.executeUpdate();*/
                        
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("begin work; update ac_asset_allocation set amount_depreciated = '"+rate+"' WHERE asset_category = '"+glType+"';commit work");
                        pstmt.executeUpdate();
                        
                    }
     /*               java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement("insert into ac_ledger values(?, ?, ?, ?, initcap(?), ?, initcap(?), ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                    pstmt21.setObject(1,rCode);
                    pstmt21.setObject(2,"Assets");
                    pstmt21.setString(3,"");
                    pstmt21.setObject(4,"");
                    pstmt21.setObject(5,"");
                    pstmt21.setString(6,"");
                    pstmt21.setObject(7,"");
                    pstmt21.setString(8,"");
                    pstmt21.setString(9,"");
                    pstmt21.setString(10,"");
                    pstmt21.setString(11,"");
                    pstmt21.setString(12,"");
                    pstmt21.setString(13,"");
                    pstmt21.setString(14,rName);
                    pstmt21.setString(15,"Depreciation");
                    pstmt21.setFloat(17,Amount);
                    pstmt21.setObject(16,"00");
                    pstmt21.setString(18,"'now'");
                    pstmt21.setString(19,""+TransNo+"");
                    pstmt21.setBoolean(20,false);
                    pstmt21.setBoolean(21,false);
                    pstmt21.setString(23,userName);
                    
                    pstmt21.setBoolean(22,false);
                    pstmt21.executeUpdate();
                    
                  */  
                }else{
                    if(depreciationmethod.equals("Reducing Instalments Method")){
                        String rCode = null;
                        String rName = null;
                        String accountCode = null;
                        String TransNo = null;
                        String glType = null;
                        String asset = null;
                        String rate = null;
                        String userName = null;
                        java.sql.Statement stm22 = connectDB.createStatement();
                        
                        java.sql.ResultSet rs22 = stm22.executeQuery("select sum((dr.cost-dr.depreciation)*dr.depreciation_rate/100)::numeric(30,2) from ac_asset_allocation dr WHERE dr.cost > dr.depreciation and approved = false");
                        while (rs22.next())
                            Amount = rs22.getFloat(1);
                        
                        java.sql.Statement stm1211 = connectDB.createStatement();
                        java.sql.ResultSet rse1211 = stm1211.executeQuery("select activity,code from pb_activity where activity_category ='DP'");
                        while (rse1211.next())
                            rName = rse1211.getObject(1).toString();
                        rCode = rse1211.getObject(2).toString();
                        java.sql.Statement ps = connectDB.createStatement();
                        java.sql.ResultSet rs23 = ps.executeQuery("select nextval('transaction_no_seq'),current_user");
                        while (rs23.next())
                            
                            TransNo = rs23.getObject(1).toString();
                        userName = rs23.getObject(2).toString();
                          
                        java.sql.Statement pst1 = connectDB.createStatement();
                        java.sql.ResultSet rs1 = pst1.executeQuery("select dr.gl_code,dr.asset_category,dr.asset_type,sum((cost-dr.depreciation)*dr.depreciation_rate/100)::numeric(30,2) from ac_asset_allocation dr WHERE dr.cost > dr.depreciation and approved = false group by dr.asset_type,dr.gl_code,dr.asset_category");
                        
                        while (rs1.next()){
                            accountCode = rs1.getObject(1).toString();
                            glType = rs1.getObject(2).toString();
                            asset = rs1.getObject(3).toString();
                            rate = rs1.getObject(4).toString();
                            
           /*                 java.sql.PreparedStatement pstmt27 = connectDB.prepareStatement("insert into ac_ledger values(?, ?, ?, ?, initcap(?), ?, initcap(?), ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                            pstmt27.setObject(1,accountCode);
                            pstmt27.setObject(2,glType);
                            pstmt27.setString(3,"");
                            pstmt27.setObject(4,"");
                            pstmt27.setObject(5,"");
                            pstmt27.setString(6,"");
                            pstmt27.setObject(7,"");
                            pstmt27.setString(8,"");
                            pstmt27.setString(9,"");
                            pstmt27.setString(10,"");
                            pstmt27.setString(11,"");
                            pstmt27.setString(12,"");
                            pstmt27.setString(13,"");
                            pstmt27.setString(14,asset);
                            pstmt27.setString(15,"Depreciation");
                            pstmt27.setString(16,rate);
                            pstmt27.setObject(17,"00");
                            pstmt27.setString(18,"'now'");
                            pstmt27.setString(19,""+TransNo+"");
                            pstmt27.setBoolean(20,false);
                            pstmt27.setBoolean(21,false);
                            pstmt27.setString(23,userName);
                            pstmt27.setBoolean(22,false);
                            pstmt27.executeUpdate();
            */
                            
                            java.sql.PreparedStatement pstmt28 = connectDB.prepareStatement("begin work; update ac_asset_allocation set amount_depreciated = '"+rate+"' WHERE asset_category = '"+glType+"';commit work");
                            pstmt28.executeUpdate();
                        }
      /*                  java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement("insert into ac_ledger values(?, ?, ?, ?, initcap(?), ?, initcap(?), ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");
                        pstmt21.setObject(1,rCode);
                        pstmt21.setObject(2,"assets");
                        pstmt21.setString(3,"");
                        pstmt21.setObject(4,"");
                        pstmt21.setObject(5,"");
                        pstmt21.setString(6,"");
                        pstmt21.setObject(7,"");
                        pstmt21.setString(8,"");
                        pstmt21.setString(9,"");
                        pstmt21.setString(10,"");
                        pstmt21.setString(11,"");
                        pstmt21.setString(12,"");
                        pstmt21.setString(13,"");
                        pstmt21.setString(14,rName);
                        pstmt21.setString(15,"Depreciation");
                        pstmt21.setFloat(17,Amount);
                        pstmt21.setObject(16,"00");
                        pstmt21.setString(18,"'now'");
                        pstmt21.setString(19,""+TransNo+"");
                        pstmt21.setBoolean(20,false);
                        pstmt21.setBoolean(21,false);
                        pstmt21.setString(23,userName);
                        pstmt21.setBoolean(22,false);
                        pstmt21.executeUpdate();
                        
                        */
                        
                    }
                }
                connectDB.commit();
                connectDB.setAutoCommit(true);
                
               // javax.swing.JOptionPane.showConfirmDialog(this, "Depreciation completed successfully","Comfirmation",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
                
                
                //  javax.swing.JOptionPane.showMessageDialog(this, "Enter quantity received","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                
                
            } catch(java.sql.SQLException sq){
//                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                
                System.out.println(sq.getMessage());
                try {
                    connectDB.rollback();
                }catch (java.sql.SQLException sql){
//                    javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            
            
        }
        else
            if (confOption == javax.swing.JOptionPane.NO_OPTION) {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Thank you for taking caution...","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
            if (exitOption == javax.swing.JOptionPane.NO_OPTION) {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Make sure you have posted every thing correctly.","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        
    }
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM allowances_benefits ORDER BY staff_no");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where approved ORDER BY staff_no");
            
            while (rSet1.next()) {
                
//                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tax_type FROM statutory_deductions_view order by tax_type");
            
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
