/*
 * DBObject.java
 *
 * Created on January 8, 2004, 8:36 PM
 */
package com.afrisoftech.lib;

import com.afrisoftech.hospital.HospitalMain;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.openide.util.Exceptions;

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

                code = rsetCode.getObject(1).toString();

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

                desc = rsetCode.getObject(1).toString();

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
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT  distinct units from st_stock_item where item_code ILIKE '%" + item + "%' order by 1 limit 1 ");

            java.sql.ResultSet rsetCode = pst.executeQuery();
            System.err.println("SELECT  units from st_sub_stores where item ILIKE '%" + item + "%' and units is not null limit 1 ");

            while (rsetCode.next()) {

                units = DBObject.getDBObject(rsetCode.getObject(1).toString(), units);

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

                desc = DBObject.getDBObject(rsetdesc.getObject(1).toString(), "N/A");

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

                supplier = rsetCode.getObject(1).toString();

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

                desc = DBObject.getDBObject(rsetdesc.getObject(1).toString(), "N/A");

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
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pst = connectDB.prepareStatement("INSERT INTO st_user_activity (activity_description) VALUES (?)");
            pst.setObject(1, activity);

            pst.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
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
                        java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(code) from st_main_stores where store_name ilike '" + store_name + "' UNION  SELECT  DISTINCT  sales_code from st_stores WHERE store_name  ilike '" + store_name + "'  ORDER BY 1 limit 1");

          //  java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(code) from st_main_stores where store_name ilike '" + store_name + "' UNION  SELECT  DISTINCT  income_account from pb_departments WHERE department_name  ilike '" + store_name + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                code = DBObject.getDBObject(rsetdesc.getObject(1).toString(), "");

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

                code = DBObject.getDBObject(rsetdesc.getObject(1).toString(), "");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetItemInfo.class.getName()).log(Level.SEVERE, null, ex);
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

    public static java.lang.String getIRQapprover(java.lang.String irq, java.sql.Connection connectDB) {
        String user = null;
        com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT lower(irq_approved_by) from st_receive_requisation where requisition_no ilike '" + irq + "'  ORDER BY 1 limit 1");
            //java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT upper(status) from st_main_stores where store_name ilike '"+store_name+"' union  select  distinct  upper(cs_code) as status from st_stores where store_name ilike '"+store_name+"' ORDER BY 1 limit 1");
            java.sql.ResultSet rsetdesc = pst.executeQuery();
            while (rsetdesc.next()) {

                user = DBObject.getDBObject(rsetdesc.getObject(1), "N/A");

            }
        } catch (SQLException ex) {
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
    
    
    
    
    public static java.lang.Double getConversion(String issuingUnit ,java.lang.String bulkUnit,java.sql.Connection connectDB) {
        Double unit = 1.00;
        
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT conversion_unit FROM st_packing WHERE genre_desc ilike '"+issuingUnit+"' AND bulk_supply_unit ilike '"+bulkUnit+"'  ORDER BY 1 limit 1");
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
