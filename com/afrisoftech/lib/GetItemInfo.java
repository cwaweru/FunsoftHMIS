/*
 * DBObject.java
 *
 * Created on January 8, 2004, 8:36 PM
 */
package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
//

/**
 *
 * @author saleem :::Sunday May 18 2014
 */
public class GetItemInfo {

    //  FinancialYear
    //OceanTheme
    /**
     * Creates a new instance of DBObject
     */
    com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();

    public GetItemInfo() {
    }

    //GovBillPaymentsIntfr
    public static java.lang.String getItemCode(java.lang.String item_desc, java.sql.Connection connectDB) {
        String code = null;

        try {//
            java.sql.PreparedStatement pst = connectDB.prepareStatement("(SELECT distinct item_code FROM st_stock_item WHERE description ILIKE '" + item_desc + "'   union SELECT  distinct item_code from st_sub_stores where item ilike '" + item_desc + "') order by 1");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                code = rsetCode.getString(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;

    }

    public static String checkDrugReactions(java.sql.Connection connectDB, String patNo,String patName, JTable jt, int column) {
        String info = "";

        try {//
            for (int i = 0; i < jt.getRowCount(); i++) {
                if (jt.getValueAt(i, column) != null) {
                    String drugName = jt.getValueAt(i, column).toString();
                    
                    //Check Drug Reactions
                    for (int p = i + 1; p < jt.getRowCount(); p++) {
                        if (jt.getValueAt(p, column) != null) {
                            String drugName2 = jt.getValueAt(p, column).toString();
                            java.sql.PreparedStatement pst = connectDB.prepareStatement("select * from hp_drug_reactions where  \n" +
                                "('"+drugName+"' ilike '%' || description || '%' AND '"+drugName2+"' ilike '%' || reacting_item || '%')  OR \n" +
                                "('"+drugName2+"' ilike '%' || description || '%' AND '"+drugName+"' ilike '%' || reacting_item || '%') ");
                            java.sql.ResultSet rsetCode = pst.executeQuery();
                            while (rsetCode.next()) {

                                info += drugName +" reacts with "+drugName2+"\n";

                            }

                        }

                    }
                    
                    
                    //Check Patient Reaction
                    java.sql.PreparedStatement pst = connectDB.prepareStatement("select description from hp_patient_allergies   WHERE patient_no = '"+patNo+"'"
                            + " AND UPPER(description) IN (select DISTINCT  UPPER(component) from hp_drug_components   WHERE  '"+drugName+"' ilike '%' || description || '%' ) ");
                            java.sql.ResultSet rsetCode = pst.executeQuery();
                            int p =0;
                            while (rsetCode.next()) {
                                

                                info += drugName +" contains "+rsetCode.getString(1)+" which selected patient ("+patName+") reacts with\n";
                                

                            }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!info.isEmpty()){
             //JOptionPane.showMessageDialog(new JFrame(), "Wrong Password, Please enter right password to unlock!");
             javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), info +".\n\nKindly check the prescription again.", "Warning Message", javax.swing.JOptionPane.WARNING_MESSAGE);

        }

        return info;

    }

    public static boolean byPassVotingForDirectOrdering(java.sql.Connection connectDB) {
        boolean bypass = false;

        try {//
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT bypass_voting   FROM pb_patient_names ");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                bypass = rsetCode.getBoolean(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bypass;

    }

    public static Double itemBalance(java.sql.Connection connectDB, String itemcode, String Store, Date date1) {
        Double bal = 0.0;
        try {
            PreparedStatement pst = connectDB.prepareStatement("select case when qty=0 or qty IS NULL then 0 ELSE qty END AS qty,1 from st_balance_qty( '" + itemcode + "',  '" + date1 + "' , '" + Store + "') ");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                bal = rst.getDouble(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bal;

    }

    public static boolean checkNegativestoreBalances(java.sql.Connection connectDB, String store, javax.swing.JTable itemsTable, int balColumn, int qtyToDeductColumn) {
        boolean neg = true;
        try {

            java.sql.Statement pstmt1 = connectDB.createStatement();
            java.sql.ResultSet rs1 = pstmt1.executeQuery("select neg_allow from st_stock_control WHERE UPPER(store) =  '" + store.toUpperCase() + "' "); //from orders where supplier ='"+jTable1.getValueAt(i,4).toString()+"'");

            while (rs1.next()) {
                neg = rs1.getBoolean(1);
            }

        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();

            System.out.println(sq.getMessage());

        }
        if (!neg) {
            for (int i = 0; i < itemsTable.getRowCount(); i++) {
                if (Double.valueOf(itemsTable.getValueAt(i, balColumn).toString()) < Double.valueOf(itemsTable.getValueAt(i, qtyToDeductColumn).toString())) {
                    neg = false;
                }
            }
        }

        return neg;

    }

    public static Double itemPrice(java.sql.Connection connectDB, String itemcode) {
        Double price = 0.0;
        try {
            PreparedStatement pst = connectDB.prepareStatement("SELECT (buying_price/packaging)::numeric(15,0) FROM st_stock_item WHERE st_stock_item.item_code ILIKE '" + itemcode + "' LIMIT 1");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                price = rst.getDouble(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;

    }

    public static java.lang.String getExpiryByCode(java.lang.String itemcode, java.sql.Connection connectDB) {

        String expiryDate = null;

        try {//
//            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT * FROM expirybyitem('" + itemcode + "')");
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT expiry_date FROM st_stock_cardex WHERE upper(item_code) = upper(?) AND order_no is not null AND expiry_date is not null ORDER BY 1 DESC LIMIT 1");
            pst.setString(1, itemcode);
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                expiryDate = new com.afrisoftech.lib.DBObject().getDBObject(rsetCode.getDate(1), "-");

            }

        } catch (SQLException ex) {

            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return new com.afrisoftech.lib.DBObject().getDBObject(expiryDate, "-");

    }

    public static java.lang.String getItemCodeByConcatenatedDesc(java.lang.String item_desc, java.sql.Connection connectDB) {
        String code = null;

        try {//
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT distinct product_id FROM st_stock_prices WHERE product||' '||strength ILIKE '" + item_desc + "' ORDER BY 1");
            System.err.println("SELECT distinct product_code FROM st_stock_prices WHERE product||' '||strength ILIKE '" + item_desc + "' ORDER BY 1");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                code = rsetCode.getString(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;

    }

    public static Double geTPerformanceBond(java.lang.String tender, java.lang.String supp, java.sql.Connection connectDB) {
        Double code = null;
        try {//
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT (cr_performance)::numeric(15,2) from st_contract_mgt where tender_no='" + tender + "' AND supplier ILIKE '" + supp + "' ");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                code = rsetCode.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;

    }

    public static java.lang.String getItemDescription(java.lang.String item_code, java.sql.Connection connectDB) {
        String desc = null;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT  distinct item from st_sub_stores where item_code ILIKE '" + item_code + "' LIMIT 1");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                desc = rsetCode.getString(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return desc;

    }

    public static java.lang.String getItemUnits(java.lang.String item, java.sql.Connection connectDB) {
        String units = "NO";
        DBObject DBObject = new DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT  distinct units from st_stock_item where trim(item_code) ILIKE '" + item + "' order by 1 limit 1 ");

            java.sql.ResultSet rsetCode = pst.executeQuery();
            System.err.println("SELECT  distinct units from st_stock_item where item_code ILIKE '" + item + "' order by 1 limit 1 ");

            while (rsetCode.next()) {

                units = DBObject.getDBObject(rsetCode.getObject(1), units);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return units;

    }

    public static java.lang.String getITenderDesc(java.lang.String Tender_No, java.sql.Connection connectDB) {
        String desc = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT distinct initcap(description) FROM st_tenders  WHERE ref_no ILIKE '" + Tender_No + "'  order by 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                desc = DBObject.getDBObject(rsetdesc.getObject(1), "N/A");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return desc;

    }

    public static java.lang.String getSupp(java.lang.String order, java.lang.String quote_no, java.sql.Connection connectDB) {
        String supplier = null;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT  distinct supplier from st_orders where order_no = '" + order + "' and quotation_no = '" + quote_no + "' LIMIT 1");
            java.sql.ResultSet rsetCode = pst.executeQuery();
            while (rsetCode.next()) {

                supplier = rsetCode.getString(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return supplier;

    }

    //SELECT  DISTINCT sub_cat_code FROM st_stock_item WHERE description = 'ACID ETCH MATERIAL' UNION SELECT DISTINCT category FROM st_stock_prices WHERE product = 'ACID ETCH MATERIAL' LIMIT 1
    public static java.lang.String getITemCategory(java.lang.String Item_desc, java.sql.Connection connectDB) {
        String desc = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT  DISTINCT sub_cat_code FROM st_stock_item WHERE description = '" + Item_desc + "' UNION SELECT DISTINCT category FROM st_stock_prices WHERE product = '" + Item_desc + "' LIMIT 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                desc = DBObject.getDBObject(rsetdesc.getObject(1), "N/A");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return desc;

    }

    public static java.lang.Double getOrderedBalance(java.lang.String Tender_no, java.lang.String Item_desc, java.lang.String supp, java.sql.Connection connectDB) {
        Double qty = 0.0;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("select SUM(quantity-ordered_qty) FROM st_recommendation where quotation_no = '" + Tender_no + "' and trim(initcap(supplier)) ilike '" + supp + "' and trim(item_code) ilike '" + Item_desc + "'");

            System.err.println("select SUM(quantity-ordered_qty) FROM st_recommendation where quotation_no = '" + Tender_no + "' and trim(initcap(supplier)) ilike '" + supp + "' and trim(item_code) ilike '" + Item_desc + "'");
            java.sql.ResultSet rsetqty = pst.executeQuery();
            while (rsetqty.next()) {

                qty = rsetqty.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return qty;

    }

    public static java.lang.Double getTenderprice(java.lang.String Tender_no, java.lang.String code, java.sql.Connection connectDB) {
        Double price = 0.0;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("select distinct price FROM st_recommendation where quotation_no = '" + Tender_no + "' and item_code ilike '%" + code + "%'");
            System.err.println("select distinct price FROM st_recommendation where quotation_no = '" + Tender_no + "' and item_code ilike '%" + code + "%'");
            java.sql.ResultSet rsetprice = pst.executeQuery();
            while (rsetprice.next()) {

                price = rsetprice.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return price;

    }

    public static void updateTrail(java.lang.String activity, java.sql.Connection connectDB) {

        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("INSERT INTO st_user_activity (activity_description) VALUES (?)");
            pst.setObject(1, activity);

            pst.executeUpdate();

            //        connectDB.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public static void closeTender(java.lang.String tender, java.sql.Connection connectDB) {

        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("update st_tenders set closed=true where ref_no=?");
            pst.setObject(1, tender);

            pst.executeUpdate();

            connectDB.commit();

        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public static java.lang.String getStoreGL(java.lang.String store_name, java.sql.Connection connectDB) {
        String code = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(code) from st_main_stores where store_name ilike '" + store_name + "' UNION  SELECT  DISTINCT  income_account from pb_departments WHERE department_name  ilike '" + store_name + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            System.err.println("SELECT DISTINCT upper(code) from st_main_stores where store_name ilike '" + store_name + "' UNION  SELECT  DISTINCT  income_account from pb_departments WHERE department_name  ilike '" + store_name + "'  ORDER BY 1 limit 1");

            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                code = DBObject.getDBObject(rsetdesc.getObject(1), "");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;

    }

    public static java.lang.String getStoreMainGL(java.lang.String store_name, java.sql.Connection connectDB) {
        String code = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(glstock_code) from st_stores where store_name ilike '" + store_name + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                code = DBObject.getDBObject(rsetdesc.getObject(1), "");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return code;

    }

    public static void autoUpdateInspection(java.lang.String lpo_no, java.sql.Connection connectDB) {
        // String status = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("update st_stock_cardex set supplies =true , technical=true,chairperson=true,certficate_no='00' where order_no= '" + lpo_no + "'  ");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where lpo_no ilike '"+lpo_no+"' union  select  distinct  upper(cs_code) as status from st_stores where lpo_no ilike '"+lpo_no+"' ORDER BY 1 limit 1");
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return status;
    }
    public static java.lang.Boolean checkRadiologyItems(java.lang.String requestNo,String patientNo, java.sql.Connection connectDB) {
        Boolean status = false;
        int count = 0;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            
            System.err.println("SELECT COUNT(*) FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'XRY')"
                    + " UNION "
                    + "SELECT COUNT(*) FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'XRY')");
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT COUNT(*) FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'XRY')"
                    + " UNION "
                    + "SELECT COUNT(*) FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'XRY')");
            
            
            
            
            pst.setString(1, requestNo);
            pst.setString(2, patientNo);
            pst.setString(3, requestNo);
            pst.setString(4, patientNo);
            java.sql.ResultSet rsetdesc = pst.executeQuery();

            while (rsetdesc.next()) {

                if(rsetdesc.getInt(1) > 0){
                    status = true;                   
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(status){
            System.err.println("Radiology Items Present ......Forward to PACs");
        }else{
            System.err.println("No Radiology Items Found .....Abort");
        }

        return status;

    }

    
    public static boolean AutoDepartmentalServiceControl(java.sql.Connection connectDB) {
        boolean allow = false;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT auto_departmental_service_control from pb_patient_names ");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                allow = rsetdesc.getBoolean(1);

            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allow;

    }

    public static boolean allowOutOfSockPrescription(java.sql.Connection connectDB) {
        boolean allow = true;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT prescribe_unavailable_stock from pb_patient_names ");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                allow = rsetdesc.getBoolean(1);

            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allow;

    }

    public static boolean showDrugsBalance(java.sql.Connection connectDB) {
        boolean allow = true;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT show_drug_balance from pb_hospitalprofile ");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                allow = rsetdesc.getBoolean(1);

            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allow;

    }

    public static java.lang.String getIRQapprover(java.lang.String irq, java.sql.Connection connectDB) {
        String user = "";
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(irq_approved_by) from st_receive_requisation where requisition_no ilike '" + irq + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                user = DBObject.getDBObject(rsetdesc.getObject(1), "N/A");

            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;

    }

    public static java.lang.String getIRQUser(java.lang.String irq, java.sql.Connection connectDB) {
        String user = "";
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(doctor) from st_sub_stores where transaction_no ilike '" + irq + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                user = DBObject.getDBObject(rsetdesc.getObject(1), "N/A");

            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;

    }

    public static java.lang.Boolean procSuperuser(java.lang.String user, java.sql.Connection connectDB) {
        Boolean status = false;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("select case when (select count(distinct(section)) "
                    + "from section_allocation where status= true and user_name ilike '" + status + "')>1 \n"
                    + "then true else false end as superuser");

            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                status = rsetdesc.getBoolean(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;

    }

    public static java.lang.Boolean checkLabItems(java.lang.String requestNo, String patientNo, java.sql.Connection connectDB) {
        Boolean status = false;
        int count = 0;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {

            System.err.println("SELECT COUNT(*) FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION "
                    + "SELECT COUNT(*) FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')");
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT COUNT(*) FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION "
                    + "SELECT COUNT(*) FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')");

            pst.setString(1, requestNo);
            pst.setString(2, patientNo);
            pst.setString(3, requestNo);
            pst.setString(4, patientNo);
            java.sql.ResultSet rsetdesc = pst.executeQuery();

            while (rsetdesc.next()) {

                if (rsetdesc.getInt(1) > 0) {
                    status = true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (status) {
            System.err.println("Lab Items Present ......Forward to LIMS");
        } else {
            System.err.println("No Lab Items Found .....Abort");
        }

        return status;

    }

    public static java.lang.Double getConversion(String issuingUnit, java.lang.String bulkUnit, java.sql.Connection connectDB) {
        Double unit = 1.00;

        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT conversion_unit FROM st_packing WHERE genre_desc ilike '" + issuingUnit + "' AND bulk_supply_unit ilike '" + bulkUnit + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                unit = rsetdesc.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unit;

    }

}
