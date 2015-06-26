/*
 * ClearTable.java
 *
 * Created on May 8, 2013, 7:24 PM
 */

package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;






/**
 * 
 * @author  saleem
 */
public class TenderTrack {
    //OceanTheme
    /** Creates a new instance of ClearTable */
    public TenderTrack() {
    }
    
    public static void TrackTable(java.sql.Connection con ,  java.lang.String tableColumn, java.lang.String stage, java.lang.String contract_no){
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE st_pr_progress SET "+tableColumn+" ='"+stage+"' WHERE approved_by_tc='"+contract_no+"'  ");
           
            pst.executeUpdate();
            System.out.println("UPDATE st_pr_progress SET "+tableColumn+" ='"+stage+"' WHERE approved_by_tc='"+contract_no+"'  ");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TenderTrack.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
     
    }  
    
    
        public static void TrackTablePR(java.sql.Connection con,java.lang.String tableColumn,java.lang.String stage,java.lang.String contract_no) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE st_pr_progress SET "+tableColumn+" = '"+stage+"' WHERE purchase_req='"+contract_no+"'  ");
           
            pst.executeUpdate();
            System.out.println("UPDATE st_pr_progress SET "+tableColumn+" ='"+stage+"' WHERE purchase_req='"+contract_no+"' ");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TenderTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public static void TrackContract(java.sql.Connection con ,  java.lang.String tableColumn, java.lang.String item, java.lang.String contract_no){
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE st_pr_progress SET "+tableColumn+" ='"+item+"' WHERE approved_by_tc='"+contract_no+"'  ");
           
            pst.executeUpdate();
            System.out.println("UPDATE st_pr_progress SET "+tableColumn+" ='"+item+"' WHERE approved_by_tc='"+contract_no+"'  ");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TenderTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }  
    
    
    
}
