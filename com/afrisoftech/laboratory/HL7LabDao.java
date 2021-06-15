/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.laboratory;

//import com.funsoft.core.Link;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.chis.mes.HL7Messaging;

/**
 *
 * @author Charles
 */
public class HL7LabDao {

  //  private Link main;

    //private boolean runThread = true;
//    public void HL7Dao(Link main) {
//        this.main = main;
//    }

    void getHL7Messaging(java.sql.Connection connectDB, String patientNo,String type) {

//        java.sql.Savepoint savePoint = null;
//        ArrayList revArray = new ArrayList();
        try {
//DBConnection connectDB = new DBConnection("admin", "admin12345", "localhost", "5432", "kiharatwo");

            // java.sql.Connection dbConnection = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/thikalv", "admin", "admin12345");
            System.out.println("started executing query for lab....");

            java.sql.PreparedStatement pstmtHosRev = null;
           
//            if(type.equalsIgnoreCase("IP")){
//                pstmtHosRev = connectDB.prepareStatement(""
//                    // java.sql.PreparedStatement pstmtHosRev = main.getDBConnCHIS().prepareStatement(""
//                    + "SELECT hi.patient_no, first_name, second_name, last_name, hi.nok, hi.residence, \n" +
//"                           hi.address, year_of_birth::DATE AS year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n" +
//"                           description, category, expiry_date, date as last_visit, ward as department, member_name, \n" +
//"                           '' as card_no, '' as emails, id_no, nok_residence as nok_add, '' as old_patient_no, nationality as pat_nationality, \n" +
//"                           tel as nok_telno, nok_relationship, nok_residence, '' as snok_email, marital_status as pat_marital_status, \n" +
//"                           '' as tribe, pat_district as district, pat_location as  locations, hi.residence as  sub_location, chief_name, sub_chief, \n" +
//"                           '' as information_source, education_level, occupation, pat_religion, \n" +
//"                           '' as patient_race, hi.residence as birth_place, '' as waiting_patient,'' as  email,'' as issync,\n" +
//"                           (current_date::date-year_of_birth::date)/365 AS age,  \n" +
//"                           NULLIF(regexp_replace((year_of_birth::DATE)::VARCHAR, '-','','g'), '')::DATE AS yearbirth \n" +
//"                      FROM public.hp_inpatient_register hi,hp_admission\n" +
//"                      WHERE hi.patient_no = ? and hi.patient_no = hp_admission.patient_no\n" +
//"                      ORDER BY 2,1 limit 1");
//            }else{

            System.err.println("SELECT patient_no, first_name, second_name, last_name, nok, residence, \n"
                    + "       address, year_of_birth::DATE AS year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n"
                    + "       description, category, expiry_date, last_visit, department, member_name, \n"
                    + "       card_no, emails, id_no, nok_add, old_patient_no, pat_nationality, \n"
                    + "       nok_telno, nok_relationship, nok_residence, nok_email, pat_marital_status, \n"
                    + "       tribe, district, locations, sub_location, chief_name, sub_chief, \n"
                    + "       information_source, education_level, occupation, pat_religion, \n"
                    + "       patient_race, birth_place, waiting_patient, email,'' as issync,"
                    + "       (current_date::date-year_of_birth::date)/365 AS age,  \n "
                    + "       NULLIF(regexp_replace((year_of_birth::DATE)::VARCHAR, '-','','g'), '')::DATE AS yearbirth \n"
                    + "  FROM public.hp_patient_register"
                    + "  WHERE patient_no = ? "
                    + " UNION SELECT hi.patient_no, first_name, second_name, last_name, hi.nok, hi.residence, \n"
                    + "                           hi.address, year_of_birth::DATE AS year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n"
                    + "                           description, category, expiry_date, date as last_visit, ward as department, member_name, \n"
                    + "                           '' as card_no, '' as emails, id_no, nok_residence as nok_add, '' as old_patient_no, nationality as pat_nationality, \n"
                    + "                           tel as nok_telno, nok_relationship, nok_residence, '' as snok_email, marital_status as pat_marital_status, \n"
                    + "                           '' as tribe, pat_district as district, pat_location as  locations, hi.residence as  sub_location, chief_name, sub_chief, \n"
                    + "                           '' as information_source, education_level, occupation, pat_religion, \n"
                    + "                           '' as patient_race, hi.residence as birth_place, false as waiting_patient,'' as  email,'' as issync,\n"
                    + "                           (current_date::date-year_of_birth::date)/365 AS age,  \n"
                    + "                           NULLIF(regexp_replace((year_of_birth::DATE)::VARCHAR, '-','','g'), '')::DATE AS yearbirth \n"
                    + "                      FROM public.hp_inpatient_register hi,hp_admission\n"
                    + "                      WHERE hi.patient_no = ? and hi.patient_no = hp_admission.patient_no\n"
                    + "                      ORDER BY 2,1 limit 1");
            pstmtHosRev = connectDB.prepareStatement(""
                    // java.sql.PreparedStatement pstmtHosRev = main.getDBConnCHIS().prepareStatement(""
                    + "SELECT patient_no, first_name, second_name, last_name, nok, residence, \n"
                    + "       address, year_of_birth::DATE AS year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n"
                    + "       description, category, expiry_date, last_visit, department, member_name, \n"
                    + "       card_no, emails, id_no, nok_add, old_patient_no, pat_nationality, \n"
                    + "       nok_telno, nok_relationship, nok_residence, nok_email, pat_marital_status, \n"
                    + "       tribe, district, locations, sub_location, chief_name, sub_chief, \n"
                    + "       information_source, education_level, occupation, pat_religion, \n"
                    + "       patient_race, birth_place, waiting_patient, email,'' as issync,"
                    + "       (current_date::date-year_of_birth::date)/365 AS age,  \n "
                    + "       NULLIF(regexp_replace((year_of_birth::DATE)::VARCHAR, '-','','g'), '')::DATE AS yearbirth \n"
                    + "  FROM public.hp_patient_register"
                    + "  WHERE patient_no = ? "
                    + " UNION SELECT hi.patient_no, first_name, second_name, last_name, hi.nok, hi.residence, \n"
                    + "                           hi.address, year_of_birth::DATE AS year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n"
                    + "                           description, category, expiry_date, date as last_visit, ward as department, member_name, \n"
                    + "                           '' as card_no, '' as emails, id_no, nok_residence as nok_add, '' as old_patient_no, nationality as pat_nationality, \n"
                    + "                           tel as nok_telno, nok_relationship, nok_residence, '' as snok_email, marital_status as pat_marital_status, \n"
                    + "                           '' as tribe, pat_district as district, pat_location as  locations, hi.residence as  sub_location, chief_name, sub_chief, \n"
                    + "                           '' as information_source, education_level, occupation, pat_religion, \n"
                    + "                           '' as patient_race, hi.residence as birth_place, false as waiting_patient,'' as  email,'' as issync,\n"
                    + "                           (current_date::date-year_of_birth::date)/365 AS age,  \n"
                    + "                           NULLIF(regexp_replace((year_of_birth::DATE)::VARCHAR, '-','','g'), '')::DATE AS yearbirth \n"
                    + "                      FROM public.hp_inpatient_register hi,hp_admission\n"
                    + "                      WHERE hi.patient_no = ? and hi.patient_no = hp_admission.patient_no\n"
                    + "                      ORDER BY 2,1 limit 1 ");
//            }
            
            

            java.sql.PreparedStatement psHos = connectDB.prepareStatement("SELECT organisation_name, "
                    + "       hospital_name, country, district_branch, main_telno, \n"
                    + "       other_telno, main_faxno, other_faxno, postal_code, box_no, email, \n"
                    + "       website, plot_no, building_name, room_no, floor_no, street, locality, \n"
                    + "       town, pin_no, nssf_no, nhif_no, legal_status, registration_no, \n"
                    + "       licence_no, expiry_date, board_registration_no, board_licence, \n"
                    + "       board_expirydate, category, date_set, nhif_class, class_relief, \n"
                    + "       vat_no, rep_currency, facility_id, region, '' as enable_biometrics, \n"
                    + "       '' as profile_pic, '' as issync \n"
                    + "  FROM public.pb_hospitalprofile;");

            System.out.println("DAO patient no is " + patientNo);
            pstmtHosRev.setString(1, patientNo);
            pstmtHosRev.setString(2, patientNo);
            // pstmtHosRev.setDate(2, com.chis.lib.SQLDateFormat.getSQLDate(hl7messaging.getEndDate()));
            java.sql.ResultSet rsHosRev = pstmtHosRev.executeQuery();
            java.sql.ResultSet rsHos = psHos.executeQuery();
            while (rsHosRev.next()) {
                while (rsHos.next()) {
                    HL7LabMessaging.setPATIENT_ID(rsHosRev.getString("patient_no"));
                    HL7LabMessaging.setPATIENT_FAMILY_SURNAME(rsHosRev.getString("first_name"));
                    HL7LabMessaging.setPATIENT_GIVEN_NAME(rsHosRev.getString("second_name")+" "+rsHosRev.getString("last_name"));
                    HL7LabMessaging.setPATIENT_CITIZENSHIP(rsHosRev.getString("pat_nationality"));
                    HL7LabMessaging.setPATIENT_AGE(rsHosRev.getInt("age"));

                    HL7LabMessaging.setPATIENT_PHYSICAL_ADDRESS(rsHosRev.getString("residence"));

                    HL7LabMessaging.setPATIENT_POSTAL_ADDRESS(rsHosRev.getString("address"));
                    HL7LabMessaging.setPATIENT_TELEPHONE_NO(rsHosRev.getString("tel_no"));
                    HL7LabMessaging.setPATIENT_NOK_NAME(rsHosRev.getString("nok"));

                    HL7LabMessaging.setPATIENT_NOK_RELATIONSHIP(rsHosRev.getString("nok_relationship"));
                    HL7LabMessaging.setPATIENT_NOK_RESIDENCE(rsHosRev.getString("nok_residence"));
                    HL7LabMessaging.setPATIENT_NOK_POSTAL_ADDRESS(rsHosRev.getString("nok_add"));
                    HL7LabMessaging.setPATIENT_NOK_TELEPHONE_NO(rsHosRev.getString("nok_telno"));
                    HL7LabMessaging.setPATIENT_COUNTY_OF_BIRTH(rsHosRev.getString("pat_nationality"));

                    HL7LabMessaging.setPATIENT_COUNTY_OF_RESIDENCE(rsHosRev.getString("pat_religion"));
                    HL7LabMessaging.setPATIENT_RESIDENCE_SPATIAL_CORDINATES(rsHosRev.getString("last_name"));
                    HL7LabMessaging.setPATIENT_NATIONAL_ID(rsHosRev.getString("id_no"));
                    HL7LabMessaging.setPATIENT_PASSPORT_ID(rsHosRev.getString("id_no"));
                    HL7LabMessaging.setPATIENT_DATE_OF_BIRTH(rsHosRev.getDate("yearbirth"));
                    HL7LabMessaging.setPATIENT_DATE_OF_BIRTH_STRING(rsHosRev.getString("yearbirth").replace("-", ""));

                    if (rsHosRev.getString("sex").startsWith("F")) {
                        HL7LabMessaging.setPATIENT_GENDER("F");
                    } else if (rsHosRev.getString("sex").startsWith("M")) {
                        HL7LabMessaging.setPATIENT_GENDER("M");
                    } else {
                        HL7LabMessaging.setPATIENT_GENDER("U");
                    }

                    // HL7LabMessaging.setPATIENT_GENDER(rsHosRev.getString("sex"));
                    HL7LabMessaging.setFACILITY_ID(rsHos.getString("facility_id"));
                    HL7LabMessaging.setFACILITY_NAME(rsHos.getString("hospital_name"));
                    HL7LabMessaging.setSERVICE_DATE(rsHosRev.getDate("date"));
                    HL7LabMessaging.setPATIENT_MARITAL_STATUS(rsHosRev.getString("pat_marital_status"));

                    HL7LabMessaging.setMEDICAL_INSURER(rsHosRev.getString("description"));
                    HL7LabMessaging.setNATIONAL_HEALTH_INSURANCE_NUMBER(rsHosRev.getString("card_no"));
                    HL7LabMessaging.setPATIENT_CLINIC_SPECIALITY(rsHosRev.getString("category"));
                    HL7LabMessaging.setPATIENT_NOK_TELEPHONE_NO(rsHosRev.getString("nok_telno"));
                    HL7LabMessaging.setPATIENT_COUNTY_OF_BIRTH(rsHosRev.getString("pat_nationality"));
                    
                    
                    //----------------------------------------------------------------------------------------
                    
                    
                    HL7LabMessaging25.setPATIENT_ID(rsHosRev.getString("patient_no"));
                    HL7LabMessaging25.setPATIENT_FAMILY_SURNAME(rsHosRev.getString("first_name"));
                    //HL7LabMessaging25.setPATIENT_GIVEN_NAME(rsHosRev.getString("second_name"));
                    HL7LabMessaging25.setPATIENT_GIVEN_NAME(rsHosRev.getString("second_name")+" "+rsHosRev.getString("last_name"));
                    HL7LabMessaging25.setPATIENT_CITIZENSHIP(rsHosRev.getString("pat_nationality"));
                    HL7LabMessaging25.setPATIENT_AGE(rsHosRev.getInt("age"));

                    HL7LabMessaging25.setPATIENT_PHYSICAL_ADDRESS(rsHosRev.getString("residence"));

                    HL7LabMessaging25.setPATIENT_POSTAL_ADDRESS(rsHosRev.getString("address"));
                    HL7LabMessaging25.setPATIENT_TELEPHONE_NO(rsHosRev.getString("tel_no"));
                    HL7LabMessaging25.setPATIENT_NOK_NAME(rsHosRev.getString("nok"));

                    HL7LabMessaging25.setPATIENT_NOK_RELATIONSHIP(rsHosRev.getString("nok_relationship"));
                    HL7LabMessaging25.setPATIENT_NOK_RESIDENCE(rsHosRev.getString("nok_residence"));
                    HL7LabMessaging25.setPATIENT_NOK_POSTAL_ADDRESS(rsHosRev.getString("nok_add"));
                    HL7LabMessaging25.setPATIENT_NOK_TELEPHONE_NO(rsHosRev.getString("nok_telno"));
                    HL7LabMessaging25.setPATIENT_COUNTY_OF_BIRTH(rsHosRev.getString("pat_nationality"));

                    HL7LabMessaging25.setPATIENT_COUNTY_OF_RESIDENCE(rsHosRev.getString("pat_religion"));
                    HL7LabMessaging25.setPATIENT_RESIDENCE_SPATIAL_CORDINATES(rsHosRev.getString("last_name"));
                    HL7LabMessaging25.setPATIENT_NATIONAL_ID(rsHosRev.getString("id_no"));
                    HL7LabMessaging25.setPATIENT_PASSPORT_ID(rsHosRev.getString("id_no"));
                    HL7LabMessaging25.setPATIENT_DATE_OF_BIRTH(rsHosRev.getDate("yearbirth"));
                    HL7LabMessaging25.setPATIENT_DATE_OF_BIRTH_STRING(rsHosRev.getString("yearbirth").replace("-", ""));

                    if (rsHosRev.getString("sex").startsWith("F")) {
                        HL7LabMessaging25.setPATIENT_GENDER("F");
                    } else if (rsHosRev.getString("sex").startsWith("M")) {
                        HL7LabMessaging25.setPATIENT_GENDER("M");
                    } else {
                        HL7LabMessaging25.setPATIENT_GENDER("U");
                    }

                    // HL7LabMessaging25.setPATIENT_GENDER(rsHosRev.getString("sex"));
                    HL7LabMessaging25.setFACILITY_ID(rsHos.getString("facility_id"));
                    HL7LabMessaging25.setFACILITY_NAME(rsHos.getString("hospital_name"));
                    HL7LabMessaging25.setSERVICE_DATE(rsHosRev.getDate("date"));
                    HL7LabMessaging25.setPATIENT_MARITAL_STATUS(rsHosRev.getString("pat_marital_status"));

                    HL7LabMessaging25.setMEDICAL_INSURER(rsHosRev.getString("description"));
                    HL7LabMessaging25.setNATIONAL_HEALTH_INSURANCE_NUMBER(rsHosRev.getString("card_no"));
                    HL7LabMessaging25.setPATIENT_CLINIC_SPECIALITY(rsHosRev.getString("category"));
                    HL7LabMessaging25.setPATIENT_NOK_TELEPHONE_NO(rsHosRev.getString("nok_telno"));
                    HL7LabMessaging25.setPATIENT_COUNTY_OF_BIRTH(rsHosRev.getString("pat_nationality"));
                    
                    
//                System.out.println("No: " + rsHosRev.getString(1) + " Date: " + rsHosRev.getDate(2) + " Dpt: " + rsHosRev.getFloat(5) + " - " + rsHosRev.getString(6) + " Paymode:" + rsHosRev.getString(7) + "  Amt = " + rsHosRev.getDouble(8));
//                     revArray.add(hl7message);
                }
            }
//            java.sql.PreparedStatement pslab;
////            pslab = connectDB.prepareStatement(""
////                    // java.sql.PreparedStatement pstmtHosRev = main.getDBConnCHIS().prepareStatement(""
////                    + "SELECT patient_no, patient_name, payment_mode, doctor, dr.service, quantity, \n"
////                    + "       amount, gl_code, trans_date, inv_no, user_name, paid, revenue_code, \n"
////                    + "       visit_id, requisition_no, collected, results, bed_no, time_due, \n"
////                    + "       curr_date, dosage, rank, age, notes, clinic, diagnosis, gender, \n"
////                    + "       posted_to_lab,op.code as code,op.category as category, row_id \n"
////                    + "  FROM public.pb_doctors_request dr, pb_operating_parameters op "
////                    + "  WHERE patient_no = ? AND revenue_code ILIKE '%X%' and dr.service = op.service_type "
////                    + "  AND trans_date = CURRENT_DATE AND paid = true AND posted_to_lab = 0 "
////                    + "  ORDER BY 2,1");
////            pslab.setString(1, patientNo);
//
//            pslab = connectDB.prepareStatement(""
//                    // java.sql.PreparedStatement pstmtHosRev = main.getDBConnCHIS().prepareStatement(""
//                    + "SELECT DISTINCT patient_no, dealer AS patient_name,  user_name AS doctor, dr.service_type as service, 1 AS  quantity, \n"
//                    + "       credit AS amount, description AS revenue_code, dr.date ,  \n"
//                    + "       transaction_time AS curr_date,'' as code,op.category as category, row_id,'' as notes ,'' as clinic \n"
//                    + "  , universal_code, universal_description FROM public.ac_ledger dr, pb_operating_parameters op "
//                    + "  WHERE patient_no = ? AND activity_code IN (SELECT code FROM pb_activity WHERE department = 'LAB') and dr.service_type = op.service_type "
//                    + "  and activity_code = gl_account AND dr.date = CURRENT_DATE AND service_posted = FALSE and UPPER(dr.service_type) = UPPER(?)  "
//                    + "  ORDER BY 2,1");
//            pslab.setString(1, patientNo);
//            pslab.setString(2, service);
//            
//            System.err.println(patientNo+"<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>"+service);
//            
//            java.sql.ResultSet rslab = pslab.executeQuery();
//            while (rslab.next()) {
//                System.err.println("Service picked------------------"+rslab.getString("universal_description"));
//                HL7LabMessaging.setPATIENT_LAB_SERVICE(rslab.getString("universal_description"));//service
//                HL7LabMessaging.setLAB_DEPARTMENT(rslab.getString("revenue_code"));
//                HL7LabMessaging.setLAB_SERVICE_CODE(rslab.getString("universal_code"));//code
//                HL7LabMessaging.setLAB_REQUEST_NO(rslab.getString("row_id"));
//                HL7LabMessaging.setLAB_REQUESTED_BY(rslab.getString("doctor"));
//                HL7LabMessaging.setLAB_RESULT_NUMBER("");
//                HL7LabMessaging.setLAB_RESULTS_FINDING("");
//                HL7LabMessaging.setLAB_SERVICE_DATE(rslab.getString("date"));
//                HL7LabMessaging.setLAB_REASON(rslab.getString("notes"));
//                HL7LabMessaging.setLAB_REMARKS("");
//                HL7LabMessaging.setLAB_OWN_REMARKS("");
//                HL7LabMessaging.setPOINT_OF_CARE(rslab.getString("clinic"));
//                HL7LabMessaging.setPATIENT_VISIT_ID(rslab.getString("row_id"));
//                HL7LabMessaging.setLAB_CATEGORY(rslab.getString("category"));
//                
//                String activitytime = rslab.getString("curr_date");
//                activitytime = activitytime.replace("-", "").replace(" ", "").replace(":", "");
//                activitytime = activitytime.substring(0, Math.min(activitytime.length(), 14));
//                
//                HL7LabMessaging.setLAB_REQUEST_TIME(activitytime);
//                
//                
//                //---------------------------------------------------------------------------
//                
//                
//                
//                HL7LabMessaging25.setPATIENT_LAB_SERVICE(rslab.getString("universal_description"));//service
//                HL7LabMessaging25.setLAB_DEPARTMENT(rslab.getString("revenue_code"));
//                HL7LabMessaging25.setLAB_SERVICE_CODE(rslab.getString("universal_code"));//code
//                HL7LabMessaging25.setLAB_REQUEST_NO(rslab.getString("row_id"));
//                HL7LabMessaging25.setLAB_REQUESTED_BY(rslab.getString("doctor"));
//                HL7LabMessaging25.setLAB_RESULT_NUMBER("");
//                HL7LabMessaging25.setLAB_RESULTS_FINDING("");
//                HL7LabMessaging25.setLAB_SERVICE_DATE(rslab.getString("date"));
//                HL7LabMessaging25.setLAB_REASON(rslab.getString("notes"));
//                HL7LabMessaging25.setLAB_REMARKS("");
//                HL7LabMessaging25.setLAB_OWN_REMARKS("");
//                HL7LabMessaging25.setPOINT_OF_CARE(rslab.getString("clinic"));
//                HL7LabMessaging25.setPATIENT_VISIT_ID(rslab.getString("row_id"));
//                HL7LabMessaging25.setLAB_CATEGORY(rslab.getString("category"));
//                HL7LabMessaging25.setLAB_REQUEST_TIME(activitytime);
//
//            }

        } catch (SQLException ex) {

            Logger.getLogger(HL7LabDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        // return revArray;

        // return revArray;
    }

}
