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
public class MonthlyWorkLoads {

    public static int getWorkloadsCountByGender(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String gender, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(ac.admission_no) FROM hp_patient_visit hp, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.gender) = UPPER(?) AND hp.patient_no = ac.admission_no AND ac.date BETWEEN ? AND ? AND hp.payment != 'Scheme' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, gender);

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByGenderANDAttendanceType(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String gender, String attendanceType, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT "
                    + "count(distinct hv.date), "
                    + "hv.patient_no,hv.gender,hv.comments,ad.payee "
                    + "FROM hp_patient_visit hv,ac_debtors ad "
                    + "WHERE UPPER(hv.gender) = UPPER(?) AND UPPER(hv.comments) = UPPER(?) "
                    + "AND UPPER(ad.payee) = UPPER(?) AND "
                    + "hv.date between ? AND ? AND hv.patient_no = ad.admission_no "
                    + "AND ad.payee IS NOT NULL GROUP BY 2,3,4,5 ORDER BY 1");

            pstmtWorkLoads.setString(1, gender);

            pstmtWorkLoads.setString(2, attendanceType);

            pstmtWorkLoads.setString(3, schemeName);

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = workLoadCount + rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByClinicAndAttendanceType(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String clinicName, String attendanceType, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT "
                    + "count(distinct hv.date), "
                    + "hv.patient_no,hv.clinic,hv.comments,ad.payee "
                    + "FROM hp_patient_visit hv,ac_debtors ad, pb_clinics pc "
                    + "WHERE UPPER(pc.clinic_category) = UPPER(?) AND UPPER(pc.clinics) = UPPER(hv.clinic) AND UPPER(hv.comments) = UPPER(?) "
                    + "AND UPPER(ad.payee) = UPPER(?) AND "
                    + "hv.date between ? AND ? AND hv.patient_no = ad.admission_no "
                    + "AND ad.payee IS NOT NULL GROUP BY 2,3,4,5 ORDER BY 1");

            pstmtWorkLoads.setString(1, clinicName);

            pstmtWorkLoads.setString(2, attendanceType);

            pstmtWorkLoads.setString(3, schemeName);

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = workLoadCount + rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByDepartment(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND ac.date BETWEEN ? AND ? AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, departmentName);

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByDepartmentAndService(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, java.lang.String service, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND ac.date BETWEEN ? AND ? AND hp.service ILIKE '%" + service + "%' AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 AND hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, departmentName);

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByDepartmentAndServiceAttendanceType(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, java.lang.String service, String attendanceType, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, hp_patient_visit hv, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND hv.comments = ? AND ac.date BETWEEN ? AND ? AND hp.service ILIKE '%" + service + "%'  AND hp.date::date = hv.date AND hp.patient_no = hv.patient_no AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 AND hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, departmentName);

            pstmtWorkLoads.setString(3, attendanceType);

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByWardTypeAndExitType(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String wardType, String exitType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(ward) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?)) AND UPPER(transaction_type) = UPPER(?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            pstmtWorkLoads.setString(5, exitType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByWardTypeAndCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String wardType, String exitType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(ward) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?)) AND UPPER(speciality) = UPPER(?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            pstmtWorkLoads.setString(5, exitType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByWardType(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String wardType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND discharge = true AND UPPER(ward) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?))");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByAdmissions(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String wardType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(ward) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?))");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByAdmissionsALL(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByExitType(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String exitType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND transaction_type = UPPER(?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, exitType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String category) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_admission WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND speciality = UPPER(?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, category);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByDepartmentAndServiceAttendanceTypeExl(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, String attendanceType, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

//            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, hp_patient_visit hv, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND hv.comments = ? AND ac.date BETWEEN ? AND ? AND hp.service NOT ILIKE '%filling%' AND hp.service NOT ILIKE '%extraction%' AND hp.date::date = hv.date AND hp.patient_no = hv.patient_no AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 AND hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");
            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, hp_patient_visit hv, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND hv.comments = ? AND ac.date BETWEEN ? AND ? AND hp.service NOT ILIKE '%filling%' AND hp.service NOT ILIKE '%extraction%' AND hp.date::date = hv.date AND hp.patient_no = hv.patient_no AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 AND hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, departmentName);

            pstmtWorkLoads.setString(3, attendanceType);

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountMedicalExaminations(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(hp.patient_no) FROM hp_patient_card hp, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.main_service) = UPPER(?) AND ac.date BETWEEN ? AND ? AND hp.invoice_no = ac.invoice_no AND hp.debit > 0 AND hp.service NOT ILIKE 'P3' AND hp.service != 'Invoice' AND hp.transaction_type != 'Receipts' AND ac.debit > 0.00");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setString(2, departmentName);

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByIndicator(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String departmentName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        return workLoadCount;

    }

    public static int getWorkloadsCostByGender(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String gender, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT count(distinct ad.date),"
                    + "admission_no,sum(ad.debit - ad.credit)::NUMERIC(30,0) AS amt,pr.sex from ac_debtors ad,hp_patient_register pr"
                    + " WHERE UPPER(ad.payee) = UPPER(?) AND UPPER(pr.sex) = UPPER(?) AND ad.date between ? AND ? AND ad.payee IS NOT "
                    + "NULL AND ad.admission_no = pr.patient_no GROUP BY 2,4 ORDER BY 1");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setString(2, gender);

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                System.out.println("Amount is : [" + rsetWorkLoadsCost.getInt(3) + "]");

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(3);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByClinic(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String clinicName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT count(distinct ad.date),"
                    + "admission_no,sum(ad.debit - ad.credit)::NUMERIC(30,0) AS amt, pr.clinic from ac_debtors ad,hp_patient_visit pr, pb_clinics pc"
                    + " WHERE UPPER(ad.payee) = UPPER(?) AND UPPER(pc.clinic_category) = UPPER(?) AND ad.date between ? AND ? AND UPPER(pr.clinic) = UPPER(pc.clinics) AND ad.payee IS NOT "
                    + "NULL AND ad.admission_no = pr.patient_no GROUP BY 2,4 ORDER BY 1");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setString(2, clinicName);

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(3);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByClinicAndAttendanceType(java.sql.Connection connectDB, java.lang.String schemeName, java.lang.String clinicName, java.lang.String attendanceType, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT count(ac.admission_no) FROM hp_patient_visit hp, ac_debtors ac WHERE UPPER(ac.payee) = UPPER(?) AND UPPER(hp.clinic) = UPPER(?) AND UPPER(hp.comments) = UPPER(?) AND hp.patient_no = ac.admission_no AND ac.date BETWEEN ? AND ? AND hp.payment != 'Scheme' AND ac.debit > 0.00");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setString(2, clinicName);

            pstmtWorkLoadsCost.setString(3, attendanceType);

            pstmtWorkLoadsCost.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByWardAndExitType(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String wardType, java.lang.String exitType) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND invoice_no IN (SELECT invoice_no FROM hp_admission WHERE UPPER(transaction_type) = UPPER(?)) AND UPPER(main_service) IN (SELECT ward_name FROM hp_wards WHERE ward_type = ?) AND UPPER(service) != UPPER('Invoice') AND service != UPPER('Receipts') AND service != UPPER('Receipt')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoadsCost.setString(4, exitType);

            pstmtWorkLoadsCost.setString(5, wardType);

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByExitType(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String exitType) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND invoice_no IN (SELECT invoice_no FROM hp_admission WHERE UPPER(transaction_type) = UPPER(?)) AND UPPER(service) != UPPER('Invoice') AND service != UPPER('Receipts') AND service != UPPER('Receipt')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoadsCost.setString(4, exitType);

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, java.lang.String category) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND invoice_no IN (SELECT invoice_no FROM hp_admission WHERE UPPER(speciality) = UPPER(?)) AND UPPER(service) != UPPER('Invoice') AND service != UPPER('Receipts') AND service != UPPER('Receipt')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoadsCost.setString(4, category);

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCostByExitTypeALL(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?)  AND UPPER(service) != UPPER('Invoice') AND service != UPPER('Receipts') AND service != UPPER('Receipt')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCountByBedDaysNHIFALL(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND (scheme ILIKE 'NHIF%' OR scheme ILIKE 'N.H.I.F') AND (service ILIKE '%bed%' OR service ILIKE '%accom%')");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByBedDaysNONNHIFALL(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND scheme NOT ILIKE 'NHIF%' AND scheme NOT ILIKE 'N.H.I.F%' AND (service ILIKE '%bed%' OR service ILIKE '%accom%')");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByBedDaysNHIF(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String wardType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND (scheme ILIKE 'NHIF%' OR scheme ILIKE 'N.H.I.F') AND (service ILIKE '%bed%' OR service ILIKE '%accom%') AND UPPER(main_service) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?))");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByBedDaysNONNHIF(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String wardType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND scheme NOT ILIKE 'NHIF%' AND scheme NOT ILIKE 'N.H.I.F%' AND (service ILIKE '%bed%' OR service ILIKE '%accom%') AND UPPER(main_service) IN (SELECT UPPER(ward_name) FROM hp_wards WHERE UPPER(ward_type) = UPPER(?))");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, wardType);

            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCostByDedDaysNHIF(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT SUM(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND (scheme ILIKE 'NHIF%' OR scheme ILIKE 'N.H.I.F') AND (service ILIKE '%bed%' OR service ILIKE '%accom%')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }
    public static int getWorkloadsCostByDedDaysNONNHIF(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoadsCost = connectDB.prepareStatement("SELECT SUM(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND scheme NOT ILIKE 'NHIF%' AND scheme NOT ILIKE 'N.H.I.F%' AND (service ILIKE '%bed%' OR service ILIKE '%accom%')");

            pstmtWorkLoadsCost.setString(1, schemeName);

            pstmtWorkLoadsCost.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoadsCost.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rsetWorkLoadsCost = pstmtWorkLoadsCost.executeQuery();

            while (rsetWorkLoadsCost.next()) {

                workLoadCost = workLoadCost + rsetWorkLoadsCost.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCountByPharmacy(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(description) FROM st_stock_item WHERE item_type = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCostByPharmacy(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT SUM(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(description) FROM st_stock_item WHERE item_type = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCost = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCountByServiceCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByServiceCategoryBooked(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String serviceType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?) AND patient_no IN (SELECT patient_no FROM pb_bookings WHERE appointment_date = hp_patient_card.date::date)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, serviceType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCostByServiceCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT SUM(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing')  AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCost = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

    public static int getWorkloadsCountByReferralsCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCountByReferralsCategoryAmbulance(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String serviceType) {

        int workLoadCount = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?) AND patient_no IN (SELECT patient_no FROM pb_bookings WHERE appointment_date = hp_patient_card.date::date)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, serviceType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCount = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCount;

    }

    public static int getWorkloadsCostByReferralsCategory(java.sql.Connection connectDB, java.lang.String schemeName, java.util.Date beginDate, java.util.Date endDate, String itemType) {

        int workLoadCost = 0;

        try {

            java.sql.PreparedStatement pstmtWorkLoads = connectDB.prepareStatement("SELECT SUM(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE UPPER(payee) = UPPER(?) AND date BETWEEN ? AND ?) AND UPPER(transaction_type) = UPPER('Billing')  AND UPPER(service) IN (SELECT UPPER(service_type) FROM pb_operating_parameters WHERE sub_category = ?)");

            pstmtWorkLoads.setString(1, schemeName);

            pstmtWorkLoads.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmtWorkLoads.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmtWorkLoads.setString(4, itemType);

            //   pstmtWorkLoads.setString(5, service);
            java.sql.ResultSet rsetWorkLoads = pstmtWorkLoads.executeQuery();

            while (rsetWorkLoads.next()) {

                workLoadCost = rsetWorkLoads.getInt(1);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return workLoadCost;

    }

}
