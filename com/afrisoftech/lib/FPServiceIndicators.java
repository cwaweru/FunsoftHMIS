/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class FPServiceIndicators {

    public static String getIndicatorStatus(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        String indicatorStatus = null;

        int count = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(service_given) FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                count = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        if (count == 1) {
            return "1";
        } else if (count > 1) {
            return "2";
        } else {
            return "";
        }
    }

    public static String getDosageGiven(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        String dosageGiven = null;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT service_dosage FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                dosageGiven = rset.getString(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return dosageGiven;
    }

    public static int getQuantityDispensed(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        int dispensedQty = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT dispensed_qty FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                dispensedQty = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return dispensedQty;
    }

    public static int getNewANCVisitors(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND first_visit = 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getRevisitingANCVisitors(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND first_visit = 'N'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getIPTANCVisitors(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String IPTState) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND ipt = ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, IPTState);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getHBANCVisitors(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND hb::int < 11 AND hb is not null AND hb != ''");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getCompletedANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND no_visits > 3");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getLITNANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND r_itn = 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getCounselledANCVisitors(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND counselled ILIKE 'hiv'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getHIVANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND hiv_initial NOT ILIKE '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getHIVPositiveANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND hiv_initial ilike '%P%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getNevirapineIssuesANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND nvp ilike '%P%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getVDRLTestedANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND vdrl not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getVDRLResultPositiveANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND vdrl ilike 'reactive'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getSDNVPANCVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND sdnvp ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getARTAssessedWHOVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND who_stage ilike 'Yes'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getARTAssessedCD4Visits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND cd_four ilike 'Yes'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getARTStartedVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND art_start BETWEEN  ? and ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getCounselledFeedingVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND counselled ilike 'infant feeding'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getCounselledPartnersVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND couple_canc not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getTestedPartnersVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND patner_tested not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getPositivePartnersVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND patner_tested ilike 'P'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferredPartnersVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND reffered is not null");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferredMothersVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND referred_out is not null");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getBabyARVprophylaxisVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND nvp ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getBreastExaminationVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND breast_exam ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getANCExerciseVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND anc_exercise ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getAdolescentPregnanciesVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND age::int >= 10 AND age::int <= 17");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getIronIssuedVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND iron ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getFolicIssuedVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND folic ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getFerrousFolateIssuedVisits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND ferrous_folate ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getInfantsHIVTestUnder3Months(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND patner_tested ilike 'P'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getInfantsHIVTestOver3Months(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND patner_tested ilike 'P'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferralsFromOtherFacilities(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND referrals_in ilike 'From Other Healthcare Facility'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferralsToOtherFacilities(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND referrals_out  ilike 'To Other Healthcare Facility'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferralsFromCommunityUnits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND referrals_in ilike 'From Community Facility'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getReferralsToCommunityUnits(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.mother_details WHERE visit_date BETWEEN ? and ? AND referrals_out ilike 'To Community Facility'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getChildLiveryMethodCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String deliveryMethod) {

        int visitCount = 0;

        if (deliveryMethod != null) {

            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND delivery_method ilike ?");

                pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

                pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

                pstmt.setString(3, deliveryMethod);

                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    visitCount = rset.getInt(1);

                }

            } catch (SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        } else {
            //  int visitCount = 0;

            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ?");

                pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

                pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

                //  pstmt.setString(3, deliveryMethod);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    visitCount = rset.getInt(1);

                }

            } catch (SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        }
        return visitCount;
    }

    public static int getChildLiveryComplicationsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String deliveryComplication, String admissionOutcome) {

        int visitCount = 0;

        if (deliveryComplication != null) {

            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND birth_complications ilike ? AND admission_outcome ilike ?");

                pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

                pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

                pstmt.setString(3, deliveryComplication);

                pstmt.setString(4, admissionOutcome);

                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    visitCount = rset.getInt(1);

                }

            } catch (SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        } else {
            //  int visitCount = 0;

            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ?");

                pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

                pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

                //  pstmt.setString(3, deliveryMethod);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    visitCount = rset.getInt(1);

                }

            } catch (SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        }
        return visitCount;
    }

    public static int getChildBirthDeformitiesCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String deliveryDeformity) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND birth_deformities ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, deliveryDeformity);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getChildBirthStatusCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String deliveryStatus) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND state_of_infant_at_birth ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, deliveryStatus);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getMaternalDeathCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String maternalStatus) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND admission_outcome ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, maternalStatus);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getNeonatalStatusCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String neoNatalStatus) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND neo_natal_status ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, neoNatalStatus);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getInitiatedBreastFeedingCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND initiated_breast_feeding = true");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getUnderWeightInfantsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND baby_weight < 2.5");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPreTermCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND duration_of_preg < 39");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getLowAPGARCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_services WHERE service_date BETWEEN ? and ? AND apgar_score_one < 3");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTCounselledCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND hiv_counselled = true AND patient_no in (SELECT DISTINCT file_no FROM hp_maternity_register)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTTestedHIVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND hiv_tested = true AND patient_no in (SELECT DISTINCT file_no FROM hp_maternity_register)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTPositiveHIVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND hiv_status ilike 'P' AND patient_no in (SELECT DISTINCT file_no FROM hp_maternity_register)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTARVGivenCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND arv_prophylaxis = true AND patient_no in (SELECT DISTINCT file_no FROM hp_maternity_register)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTPreventiveARVGivenCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND prophylaxis_regimen ilike 'sdnvp' AND patient_no in (SELECT DISTINCT file_no FROM hp_maternity_register)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTDeliveriesPositiveHIVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND hiv_status ilike 'P' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTInitiatedContrimoxazoleHIVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM key_health_indicators WHERE visit_date BETWEEN ? and ? AND prophylaxis_regimen ilike 'T' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTReferralsINFROMFacilityCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_admission WHERE date_admitted BETWEEN ? and ? AND reffered_from ilike '%facility%' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTReferralsOUTTOFacilityCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_admission WHERE date_admitted BETWEEN ? and ? AND transfered_to ilike '%facility%' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTReferralsINFROMCommunityCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_admission WHERE date_admitted BETWEEN ? and ? AND reffered_from ilike '%community%' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPMTCTReferralsOUTTOCommunityCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_admission WHERE date_admitted BETWEEN ? and ? AND transfered_to ilike '%community%' AND patient_no in (SELECT DISTINCT patient_no FROM rh.post_natal_services WHERE service_date BETWEEN ? AND ?)");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPServicesCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String fpService, String visitType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND service_given ilike ? AND revisit ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, fpService);

            pstmt.setString(4, visitType);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPNaturalMethodsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND natural_fp ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPTotalClientsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String visitType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND revisit ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, visitType);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPCounselledClientsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String visitType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND immune_counselling ilike 'Y' AND revisit ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, visitType);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPHIVPositiveClientsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String visitType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND (immune_result ilike '1%' or immune_result ilike '2%') AND revisit ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, visitType);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPHIVTestedClientsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String visitType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND immune_tested ilike 'Y' AND revisit ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, visitType);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPCervicalCancerResultsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String screeningMethod, String screeningResult) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND cervical_cancer_screening ilike '%" + screeningMethod + "%' AND cervical_cancer_screening_result ilike '%" + screeningResult + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

//            pstmt.setString(3, screeningMethod);
//
//            pstmt.setString(4, screeningResult);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPReferralsINCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String referralType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND referral_in ilike '%" + referralType + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

//            pstmt.setString(3, screeningMethod);
//
//            pstmt.setString(4, screeningResult);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getFPReferralsOUTCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String referralFacilityType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.fp_services_register WHERE service_date BETWEEN ? and ? AND referral_out ilike '%" + referralFacilityType + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesBreastExamCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND breast not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesCounselledCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND counselling not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesHIVTestedCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND hiv_tested_pnc not ilike 'ND%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesHIVPositiveCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND hiv_tested_pnc ilike '%positive%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesMotherPreventiveARVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND mother_preventive ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesInfantPreventiveARVCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND infant_preventive ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesInfantsInitiatedCTXCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND ctx_to_baby ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesMothersInitiatedCTXCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND ctx_to_mother ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesFistulaCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND fistula not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesPNCExerciseGivenCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND pnc_exercise_given ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesCervicalCancerScreeningCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND cancer_screening_method not ilike '-'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesCervicalCancerScreeningResultCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String cancerScreeningResult) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND cancer_screening_results ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, cancerScreeningResult);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesPartnerHIVCounselledCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND partner_hiv_counselled ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesPartnerHIVTestedCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND partner_hiv_tested ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesPartnerHIVPositiveCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND partner_hiv_results ilike 'P'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesReferralsINCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String referralType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND referred_from ilike '%" + referralType + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getPNCServicesReferralsOUTCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String referralType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.post_natal_follow_up_register WHERE service_date BETWEEN ? and ? AND referred_to ilike '%" + referralType + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getRehabilitationAssessmentCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String serviceType) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rehabilitation_services WHERE service_date BETWEEN ? and ? AND service_given ilike '%" + serviceType + "%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getRehabilitationReferralsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rehabilitation_services WHERE service_date BETWEEN ? and ? AND referred_out ilike '%investigations%'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getRehabilitationCommunityIntegrationCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rehabilitation_services WHERE service_date BETWEEN ? and ? AND integrated_to_community ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }

    public static int getSocialWorkServiceCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String serviceGiven) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM social_work_services WHERE service_date BETWEEN ? and ? AND service_given ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            
            pstmt.setString(3, serviceGiven);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }
        public static int getSocialWorkServiceReferralsCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM social_work_services WHERE service_date BETWEEN ? and ? AND referrals ilike 'Y'");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }

        return visitCount;
    }
        
        
}
