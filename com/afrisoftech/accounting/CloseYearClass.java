/*
 * DepreciationClass.java
 *
 * Created on June 15, 2004, 8:33 PM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  f.k.waweru
 */
public class CloseYearClass {
    
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    boolean status;
    
    /** Creates a new instance of CloseYearClass */
    public CloseYearClass(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        
        
    }
    
    public void CloseYearClass(){
        
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you want to Close the year?", "Caution before Depreciation!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
            java.lang.Object[] listofAct = getListofActivities();
            int confOption = javax.swing.JOptionPane.showConfirmDialog(new java.awt.Frame(), "Are you sure you want to activate Close the year", "Caution before depreciation!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (confOption == javax.swing.JOptionPane.YES_OPTION) {
                
                
                float charges = 00;
                float Amount = 00;
                String depreciationmethod = null;
                
                try {
                    connectDB.setAutoCommit(false);
                    
                    java.sql.PreparedStatement pstmt28 = connectDB.prepareStatement("begin work; INSERT INTO ac_trial_balance (sub_code,code,activity,date,payment_modes,activity_category,mtd_debit,mtd_credit,ytd_debit,ytd_credit,mtd_bfwd,ytd_bfwd) SELECT sub_code,code,activity,date,payment_modes,activity_category,mtd_debit,mtd_credit,ytd_debit,ytd_credit,mtd_bfwd,ytd_bfwd from pb_activity;commit work");
                    pstmt28.executeUpdate();
                    
                    java.sql.PreparedStatement pstmt281 = connectDB.prepareStatement("begin work; UPDATE pb_activity set ytd_bfwd = (ytd_credit-ytd_debit),mtd_bfwd = (mtd_credit-mtd_debit) ;commit work");
                    pstmt281.executeUpdate();
                    
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
