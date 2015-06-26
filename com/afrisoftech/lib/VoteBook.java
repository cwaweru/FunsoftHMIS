/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPL7
 */
public class VoteBook {

    public static String getVote(java.sql.Connection connDB, String stockItem, String financialYear) {

        String voteItem = null;
        try {
            java.sql.PreparedStatement pstmtGetVote = connDB.prepareStatement("SELECT vote_no FROM st_procurement_plan WHERE item_description = ? AND financial_year = ?");
            pstmtGetVote.setString(1, stockItem);
            pstmtGetVote.setString(2, financialYear);
            java.sql.ResultSet rsetGetVote = pstmtGetVote.executeQuery();
            while (rsetGetVote.next()) {
                voteItem = rsetGetVote.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(VoteBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voteItem;
    }

    public static double getVoteBalance(java.sql.Connection connDB, String stockItem, String financialYear) {

        double voteBalance = 0.00;
        try {
            java.sql.PreparedStatement pstmtGetVoteBalance = connDB.prepareStatement("SELECT vote_balance FROM st_procurement_plan WHERE item_description = ? AND financial_year = ?");
            pstmtGetVoteBalance.setString(1, stockItem);
            pstmtGetVoteBalance.setString(2, financialYear);
            java.sql.ResultSet rsetGetVoteBalance = pstmtGetVoteBalance.executeQuery();
            while (rsetGetVoteBalance.next()) {
                voteBalance = rsetGetVoteBalance.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(VoteBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voteBalance;
    }

    public static void setVoteBalance(java.sql.Connection connDB, double commitedAmount, String voteNo, String financialYear,String item) {
 double voteBalance = 0.00;
        try {
            java.sql.PreparedStatement pstmtSetVoteBalance = connDB.prepareStatement("UPDATE st_procurement_plan SET vote_balance = ?  WHERE vote_no = ? AND financial_year = ? and item_description=? ");
            pstmtSetVoteBalance.setDouble(1, commitedAmount);
            pstmtSetVoteBalance.setString(2, voteNo);
            pstmtSetVoteBalance.setString(3, financialYear);
            pstmtSetVoteBalance.setString(4, item);
            pstmtSetVoteBalance.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
           // javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            //Logger.getLogger(VoteBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 public static String RemoveAppostroph(String mmme){
            String newname="";

            for(int i=0;i<mmme.length();i++){
                if(String.valueOf(mmme.charAt(i)).contains("'")){
                    System.out.println("true "+String.valueOf(mmme.charAt(i)));
                newname=newname.concat(String.valueOf(mmme.charAt(i))+"'");
                }else{
                    System.out.println("false "+String.valueOf(mmme.charAt(i)));
                     newname=newname.concat(String.valueOf(mmme.charAt(i)));
                }
            }
            System.out.println(newname);
        return newname;
    }
    public static void decommitVoteBook(java.sql.Connection connDB, String originalDocumentNumber, double amount2Decommit) {

        try {
            java.sql.PreparedStatement pstmtDecommit = connDB.prepareStatement("INSERT INTO ac_aie_commitment("
                    + "            v_class, vote, subvote, head, subhead, subitem, commitno, supplier, "
                    + "            reftype, refno, committedon, allocated_amount, paid_amount, comtype, "
                    + "            clearedon, cleared, status, reversefor, reversedon, reversedby, "
                    + "            modifiedby, modifiedon, donorcode, donoritem, clearedby, aie_no, "
                    + "            trans_id, commited_by, voucher_no, paid_on, invoice_no, payment_reason)"
                    + "    SELECT v_class, vote, subvote, head, subhead, subitem, commitno, supplier, "
                    + "       reftype, refno, committedon, 0.00, ?, comtype, "
                    + "       clearedon, cleared, status, reversefor, reversedon, reversedby, "
                    + "       modifiedby, modifiedon, donorcode, donoritem, clearedby, aie_no, "
                    + "       trans_id, commited_by, voucher_no, paid_on, invoice_no, payment_reason"
                    + "  FROM ac_aie_commitment WHERE refno = ?");
            pstmtDecommit.setDouble(1, amount2Decommit);
            pstmtDecommit.setString(2, originalDocumentNumber);
            pstmtDecommit.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            Logger.getLogger(VoteBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
