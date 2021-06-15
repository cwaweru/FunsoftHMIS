/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.laboratory;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v24.datatype.XCN;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_INSURANCE;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.message.ORM_O01;
import ca.uhn.hl7v2.model.v24.message.ORU_R01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.OBR;
import ca.uhn.hl7v2.model.v24.segment.OBX;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.model.v24.datatype.CE;
import ca.uhn.hl7v2.parser.Parser;
//import com.funsoft.core.Link;
//import static com.funsoft.mes.HL7Utils.getHl7DateFormat;
//import static com.funsoft.mes.HL7Utils.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class HL7LabMessaging {

    private static final org.apache.log4j.Logger log = Logger.getLogger(HL7LabMessaging.class);
    private static String PATIENT_ID = null;
    private static String PATIENT_FAMILY_SURNAME = null;
    private static String PATIENT_GIVEN_NAME = null;
    private static String PATIENT_PHYSICAL_ADDRESS = null;
    private static String PATIENT_CITIZENSHIP = null;
    private static String PATIENT_VISIT_ID = null;
    private static String PATIENT_POSTAL_ADDRESS = null;
    private static String PATIENT_TELEPHONE_NO = null;
    private static String PATIENT_NOK_NAME = null;
    private static String PATIENT_NOK_RELATIONSHIP = null;
    private static String PATIENT_NOK_RESIDENCE = null;
    private static String PATIENT_NOK_POSTAL_ADDRESS = null;
    private static String PATIENT_NOK_TELEPHONE_NO = null;
    private static String PATIENT_COUNTY_OF_BIRTH = null;
    private static String PATIENT_COUNTY_OF_RESIDENCE = null;
    private static String PATIENT_RESIDENCE_SPATIAL_CORDINATES = null;
    private static String PATIENT_NATIONAL_ID = null;
    private static String PATIENT_PASSPORT_ID = null;
    private static java.util.Date PATIENT_DATE_OF_BIRTH = null;
    private static String PATIENT_DATE_OF_BIRTH_STRING = null;
    private static String PATIENT_GENDER = null;
    private static int PATIENT_AGE = 0;
    private static String PATIENT_MARITAL_STATUS = null;
    private static String FACILITY_ID = null;
    private static java.util.Date SERVICE_DATE = null;
    private static String FACILITY_NAME = null;
    private static String COUNTRY = null;
    private static String REGION = null;
    private static java.util.Vector DIAGNOSTICT_SERVICES = null;
    private static java.util.Vector MEDICATION = null;
    private static String REFER_FROM = null;
    private static String REFER_TO = null;
    private static String REASON_FOR_REFER_IN = null;
    private static String REASON_FOR_REFER_OUT = null;
    private static java.util.Date ADMISSION_DATE = null;
    private static java.util.Date DISCHARGE_DATE = null;
    private static java.util.Date CASE_TYPE = null;
    private static String PAYMENT_TYPE = null;
    private static double BILL_AMOUNT = 0.00;
    private static String MEDICAL_INSURER = null;
    private static String NATIONAL_HEALTH_INSURANCE_NUMBER = null;
    private static String PATIENT_CLINIC_SPECIALITY = null;
    private static double PATIENT_BMI_STATUS = 0.00;
    private static String PATIENT_CANCER_TESTED = null;
    private static String PATIENT_CANCER_STATUS = null;
    private static String PATIENT_CANCER_DIABETES = null;
    private static double PATIENT_HEIGHT = 0.00;
    private static double PATIENT_WEIGHT = 0.00;
    private static double APGAR_SCORE = 0.00;
    private static String PATIENT_DIABETES_STATUS = null;
    private static String PATIENT_HIV_TESTED = null;
    private static String PATIENT_HIV_STATUS = null;
    private static String PATIENT_HIV_STATUS_KNOWN = null;
    private static String PATIENT_ADMISSION_WARD = null;
    private static String PATIENT_ADMISSION_OUTCOME = null;
    private static String VICTIM_OF_GENDER_VIOLENCE = null;
    private static String PATIENT_LAB_SERVICE = null;
    private static String LAB_DEPARTMENT = null;
    private static String LAB_SERVICE_CODE = null;
    private static String LAB_REQUEST_NO = null;
    private static String LAB_REQUESTED_BY = null;
    private static String LAB_RADIOGRAPHER = null;
    private static String LAB_RESULT_NUMBER = null;
    private static String LAB_RESULTS_FINDING = null;
    private static String LAB_SERVICE_DATE = null;
    private static String LAB_REASON = null;
    private static String LAB_REMARKS = null;
    private static String LAB_OWN_REMARKS = null;
    private static String POINT_OF_CARE = null;
    private static String LAB_CATEGORY = null;
    private static String LAB_REQUEST_TIME = null;
    private static String SERVER_IP_ADDRESS = null;
    private static int SERVER_PORT = 0;

    // private static String VICTIM_OF_GENDER_VIOLENCE = null;
    ADT_A01 adt = null;

    HapiContext context = null;
    Parser parser = null;
    MSH mshSegment = null;
//    private static Link main;

//    public void HL7Messaging(Link main) {
//        this.main = main;
//    }
    public static void main(String args[]) throws LLPException {

//        Link link = new Link();
//        link.loadProperties();
//        link.start();

        HL7LabMessaging hl7Messaging = new HL7LabMessaging();

    }

    public HL7LabMessaging() {
    }

    /**
     * @return the PATIENT_ID
     */
    public static String getPATIENT_ID() {
        return PATIENT_ID;
    }

    /**
     * @param aPATIENT_ID the PATIENT_ID to set
     */
    public static void setPATIENT_ID(String aPATIENT_ID) {
        PATIENT_ID = aPATIENT_ID;
    }

    /**
     * @return the PATIENT_FAMILY_SURNAME
     */
    public static String getPATIENT_FAMILY_SURNAME() {
        return PATIENT_FAMILY_SURNAME;
    }

    /**
     * @param aPATIENT_FAMILY_SURNAME the PATIENT_FAMILY_SURNAME to set
     */
    public static void setPATIENT_FAMILY_SURNAME(String aPATIENT_FAMILY_SURNAME) {
        PATIENT_FAMILY_SURNAME = aPATIENT_FAMILY_SURNAME;
    }

    /**
     * @return the PATIENT_PHYSICAL_ADDRESS
     */
    public static String getPATIENT_PHYSICAL_ADDRESS() {
        return PATIENT_PHYSICAL_ADDRESS;
    }

    /**
     * @param aPATIENT_PHYSICAL_ADDRESS the PATIENT_PHYSICAL_ADDRESS to set
     */
    public static void setPATIENT_PHYSICAL_ADDRESS(String aPATIENT_PHYSICAL_ADDRESS) {
        PATIENT_PHYSICAL_ADDRESS = aPATIENT_PHYSICAL_ADDRESS;
    }

    /**
     * @return the PATIENT_POSTAL_ADDRESS
     */
    public static String getPATIENT_POSTAL_ADDRESS() {
        return PATIENT_POSTAL_ADDRESS;
    }

    /**
     * @param aPATIENT_POSTAL_ADDRESS the PATIENT_POSTAL_ADDRESS to set
     */
    public static void setPATIENT_POSTAL_ADDRESS(String aPATIENT_POSTAL_ADDRESS) {
        PATIENT_POSTAL_ADDRESS = aPATIENT_POSTAL_ADDRESS;
    }

    /**
     * @return the PATIENT_TELEPHONE_NO
     */
    public static String getPATIENT_TELEPHONE_NO() {
        return PATIENT_TELEPHONE_NO;
    }

    /**
     * @param aPATIENT_TELEPHONE_NO the PATIENT_TELEPHONE_NO to set
     */
    public static void setPATIENT_TELEPHONE_NO(String aPATIENT_TELEPHONE_NO) {
        PATIENT_TELEPHONE_NO = aPATIENT_TELEPHONE_NO;
    }

    /**
     * @return the PATIENT_NOK_NAME
     */
    public static String getPATIENT_NOK_NAME() {
        return PATIENT_NOK_NAME;
    }

    /**
     * @param aPATIENT_NOK_NAME the PATIENT_NOK_NAME to set
     */
    public static void setPATIENT_NOK_NAME(String aPATIENT_NOK_NAME) {
        PATIENT_NOK_NAME = aPATIENT_NOK_NAME;
    }

    /**
     * @return the PATIENT_NOK_RELATIONSHIP
     */
    public static String getPATIENT_NOK_RELATIONSHIP() {
        return PATIENT_NOK_RELATIONSHIP;
    }

    /**
     * @param aPATIENT_NOK_RELATIONSHIP the PATIENT_NOK_RELATIONSHIP to set
     */
    public static void setPATIENT_NOK_RELATIONSHIP(String aPATIENT_NOK_RELATIONSHIP) {
        PATIENT_NOK_RELATIONSHIP = aPATIENT_NOK_RELATIONSHIP;
    }

    /**
     * @return the PATIENT_NOK_RESIDENCE
     */
    public static String getPATIENT_NOK_RESIDENCE() {
        return PATIENT_NOK_RESIDENCE;
    }

    /**
     * @param aPATIENT_NOK_RESIDENCE the PATIENT_NOK_RESIDENCE to set
     */
    public static void setPATIENT_NOK_RESIDENCE(String aPATIENT_NOK_RESIDENCE) {
        PATIENT_NOK_RESIDENCE = aPATIENT_NOK_RESIDENCE;
    }

    /**
     * @return the PATIENT_NOK_POSTAL_ADDRESS
     */
    public static String getPATIENT_NOK_POSTAL_ADDRESS() {
        return PATIENT_NOK_POSTAL_ADDRESS;
    }

    /**
     * @param aPATIENT_NOK_POSTAL_ADDRESS the PATIENT_NOK_POSTAL_ADDRESS to set
     */
    public static void setPATIENT_NOK_POSTAL_ADDRESS(String aPATIENT_NOK_POSTAL_ADDRESS) {
        PATIENT_NOK_POSTAL_ADDRESS = aPATIENT_NOK_POSTAL_ADDRESS;
    }

    /**
     * @return the PATIENT_NOK_TELEPHONE_NO
     */
    public static String getPATIENT_NOK_TELEPHONE_NO() {
        return PATIENT_NOK_TELEPHONE_NO;
    }

    /**
     * @param aPATIENT_NOK_TELEPHONE_NO the PATIENT_NOK_TELEPHONE_NO to set
     */
    public static void setPATIENT_NOK_TELEPHONE_NO(String aPATIENT_NOK_TELEPHONE_NO) {
        PATIENT_NOK_TELEPHONE_NO = aPATIENT_NOK_TELEPHONE_NO;
    }

    /**
     * @return the PATIENT_COUNTY_OF_BIRTH
     */
    public static String getPATIENT_COUNTY_OF_BIRTH() {
        return PATIENT_COUNTY_OF_BIRTH;
    }

    /**
     * @param aPATIENT_COUNTY_OF_BIRTH the PATIENT_COUNTY_OF_BIRTH to set
     */
    public static void setPATIENT_COUNTY_OF_BIRTH(String aPATIENT_COUNTY_OF_BIRTH) {
        PATIENT_COUNTY_OF_BIRTH = aPATIENT_COUNTY_OF_BIRTH;
    }

    /**
     * @return the PATIENT_COUNTY_OF_RESIDENCE
     */
    public static String getPATIENT_COUNTY_OF_RESIDENCE() {
        return PATIENT_COUNTY_OF_RESIDENCE;
    }

    /**
     * @param aPATIENT_COUNTY_OF_RESIDENCE the PATIENT_COUNTY_OF_RESIDENCE to
     * set
     */
    public static void setPATIENT_COUNTY_OF_RESIDENCE(String aPATIENT_COUNTY_OF_RESIDENCE) {
        PATIENT_COUNTY_OF_RESIDENCE = aPATIENT_COUNTY_OF_RESIDENCE;
    }

    /**
     * @return the PATIENT_RESIDENCE_SPATIAL_CORDINATES
     */
    public static String getPATIENT_RESIDENCE_SPATIAL_CORDINATES() {
        return PATIENT_RESIDENCE_SPATIAL_CORDINATES;
    }

    /**
     * @param aPATIENT_RESIDENCE_SPATIAL_CORDINATES the
     * PATIENT_RESIDENCE_SPATIAL_CORDINATES to set
     */
    public static void setPATIENT_RESIDENCE_SPATIAL_CORDINATES(String aPATIENT_RESIDENCE_SPATIAL_CORDINATES) {
        PATIENT_RESIDENCE_SPATIAL_CORDINATES = aPATIENT_RESIDENCE_SPATIAL_CORDINATES;
    }

    /**
     * @return the PATIENT_NATIONAL_ID
     */
    public static String getPATIENT_NATIONAL_ID() {
        return PATIENT_NATIONAL_ID;
    }

    /**
     * @param aPATIENT_NATIONAL_ID the PATIENT_NATIONAL_ID to set
     */
    public static void setPATIENT_NATIONAL_ID(String aPATIENT_NATIONAL_ID) {
        PATIENT_NATIONAL_ID = aPATIENT_NATIONAL_ID;
    }

    /**
     * @return the PATIENT_PASSPORT_ID
     */
    public static String getPATIENT_PASSPORT_ID() {
        return PATIENT_PASSPORT_ID;
    }

    /**
     * @param aPATIENT_PASSPORT_ID the PATIENT_PASSPORT_ID to set
     */
    public static void setPATIENT_PASSPORT_ID(String aPATIENT_PASSPORT_ID) {
        PATIENT_PASSPORT_ID = aPATIENT_PASSPORT_ID;
    }

    /**
     * @return the PATIENT_DATE_OF_BIRTH
     */
    public static java.util.Date getPATIENT_DATE_OF_BIRTH() {
        return PATIENT_DATE_OF_BIRTH;
    }

    /**
     * @param aPATIENT_DATE_OF_BIRTH the PATIENT_DATE_OF_BIRTH to set
     */
    public static void setPATIENT_DATE_OF_BIRTH(java.util.Date aPATIENT_DATE_OF_BIRTH) {
        PATIENT_DATE_OF_BIRTH = aPATIENT_DATE_OF_BIRTH;
    }

    /**
     * @return the PATIENT_GENDER
     */
    public static String getPATIENT_GENDER() {
        return PATIENT_GENDER;
    }

    /**
     * @param aPATIENT_GENDER the PATIENT_GENDER to set
     */
    public static void setPATIENT_GENDER(String aPATIENT_GENDER) {
        PATIENT_GENDER = aPATIENT_GENDER;
    }

    /**
     * @return the PATIENT_AGE
     */
    public static int getPATIENT_AGE() {
        return PATIENT_AGE;
    }

    /**
     * @param aPATIENT_AGE the PATIENT_AGE to set
     */
    public static void setPATIENT_AGE(int aPATIENT_AGE) {
        PATIENT_AGE = aPATIENT_AGE;
    }

    /**
     * @return the PATIENT_GIVEN_NAME
     */
    public static String getPATIENT_GIVEN_NAME() {
        return PATIENT_GIVEN_NAME;
    }

    /**
     * @param aPATIENT_GIVEN_NAME the PATIENT_GIVEN_NAME to set
     */
    public static void setPATIENT_GIVEN_NAME(String aPATIENT_GIVEN_NAME) {
        PATIENT_GIVEN_NAME = aPATIENT_GIVEN_NAME;
    }

    /**
     * @return the PATIENT_MARITAL_STATUS
     */
    public static String getPATIENT_MARITAL_STATUS() {
        return PATIENT_MARITAL_STATUS;
    }

    /**
     * @param aPATIENT_MARITAL_STATUS the PATIENT_MARITAL_STATUS to set
     */
    public static void setPATIENT_MARITAL_STATUS(String aPATIENT_MARITAL_STATUS) {
        PATIENT_MARITAL_STATUS = aPATIENT_MARITAL_STATUS;
    }

    /**
     * @return the FACILITY_ID
     */
    public static String getFACILITY_ID() {
        return FACILITY_ID;
    }

    /**
     * @param aFACILITY_ID the FACILITY_ID to set
     */
    public static void setFACILITY_ID(String aFACILITY_ID) {
        FACILITY_ID = aFACILITY_ID;
    }

    /**
     * @return the SERVICE_DATE
     */
    public static java.util.Date getSERVICE_DATE() {
        return SERVICE_DATE;
    }

    /**
     * @param aSERVICE_DATE the SERVICE_DATE to set
     */
    public static void setSERVICE_DATE(java.util.Date aSERVICE_DATE) {
        SERVICE_DATE = aSERVICE_DATE;
    }

    /**
     * @return the FACILITY_NAME
     */
    public static String getFACILITY_NAME() {
        return FACILITY_NAME;
    }

    /**
     * @param aFACILITY_NAME the FACILITY_NAME to set
     */
    public static void setFACILITY_NAME(String aFACILITY_NAME) {
        FACILITY_NAME = aFACILITY_NAME;
    }

    /**
     * @return the COUNTRY
     */
    public static String getCOUNTRY() {
        return COUNTRY;
    }

    /**
     * @param aCOUNTRY the COUNTRY to set
     */
    public static void setCOUNTRY(String aCOUNTRY) {
        COUNTRY = aCOUNTRY;
    }

    /**
     * @return the REGION
     */
    public static String getREGION() {
        return REGION;
    }

    /**
     * @param aREGION the REGION to set
     */
    public static void setREGION(String aREGION) {
        REGION = aREGION;
    }

    /**
     * @return the DIAGNOSTICT_SERVICES
     */
    public static java.util.Vector getDIAGNOSTICT_SERVICES() {
        return DIAGNOSTICT_SERVICES;
    }

    /**
     * @param aDIAGNOSTICT_SERVICES the DIAGNOSTICT_SERVICES to set
     */
    public static void setDIAGNOSTICT_SERVICES(java.util.Vector aDIAGNOSTICT_SERVICES) {
        DIAGNOSTICT_SERVICES = aDIAGNOSTICT_SERVICES;
    }

    /**
     * @return the MEDICATION
     */
    public static java.util.Vector getMEDICATION() {
        return MEDICATION;
    }

    /**
     * @param aMEDICATION the MEDICATION to set
     */
    public static void setMEDICATION(java.util.Vector aMEDICATION) {
        MEDICATION = aMEDICATION;
    }

    /**
     * @return the REFER_FROM
     */
    public static String getREFER_FROM() {
        return REFER_FROM;
    }

    /**
     * @param aREFER_FROM the REFER_FROM to set
     */
    public static void setREFER_FROM(String aREFER_FROM) {
        REFER_FROM = aREFER_FROM;
    }

    /**
     * @return the REFER_TO
     */
    public static String getREFER_TO() {
        return REFER_TO;
    }

    /**
     * @param aREFER_TO the REFER_TO to set
     */
    public static void setREFER_TO(String aREFER_TO) {
        REFER_TO = aREFER_TO;
    }

    /**
     * @return the REASON_FOR_REFER_IN
     */
    public static String getREASON_FOR_REFER_IN() {
        return REASON_FOR_REFER_IN;
    }

    /**
     * @param aREASON_FOR_REFER_IN the REASON_FOR_REFER_IN to set
     */
    public static void setREASON_FOR_REFER_IN(String aREASON_FOR_REFER_IN) {
        REASON_FOR_REFER_IN = aREASON_FOR_REFER_IN;
    }

    /**
     * @return the REASON_FOR_REFER_OUT
     */
    public static String getREASON_FOR_REFER_OUT() {
        return REASON_FOR_REFER_OUT;
    }

    /**
     * @param aREASON_FOR_REFER_OUT the REASON_FOR_REFER_OUT to set
     */
    public static void setREASON_FOR_REFER_OUT(String aREASON_FOR_REFER_OUT) {
        REASON_FOR_REFER_OUT = aREASON_FOR_REFER_OUT;
    }

    /**
     * @return the ADMISSION_DATE
     */
    public static java.util.Date getADMISSION_DATE() {
        return ADMISSION_DATE;
    }

    /**
     * @param aADMISSION_DATE the ADMISSION_DATE to set
     */
    public static void setADMISSION_DATE(java.util.Date aADMISSION_DATE) {
        ADMISSION_DATE = aADMISSION_DATE;
    }

    /**
     * @return the DISCHARGE_DATE
     */
    public static java.util.Date getDISCHARGE_DATE() {
        return DISCHARGE_DATE;
    }

    /**
     * @param aDISCHARGE_DATE the DISCHARGE_DATE to set
     */
    public static void setDISCHARGE_DATE(java.util.Date aDISCHARGE_DATE) {
        DISCHARGE_DATE = aDISCHARGE_DATE;
    }

    /**
     * @return the CASE_TYPE
     */
    public static java.util.Date getCASE_TYPE() {
        return CASE_TYPE;
    }

    /**
     * @param aCASE_TYPE the CASE_TYPE to set
     */
    public static void setCASE_TYPE(java.util.Date aCASE_TYPE) {
        CASE_TYPE = aCASE_TYPE;
    }

    /**
     * @return the PAYMENT_TYPE
     */
    public static String getPAYMENT_TYPE() {
        return PAYMENT_TYPE;
    }

    /**
     * @param aPAYMENT_TYPE the PAYMENT_TYPE to set
     */
    public static void setPAYMENT_TYPE(String aPAYMENT_TYPE) {
        PAYMENT_TYPE = aPAYMENT_TYPE;
    }

    /**
     * @return the BILL_AMOUNT
     */
    public static double getBILL_AMOUNT() {
        return BILL_AMOUNT;
    }

    /**
     * @param aBILL_AMOUNT the BILL_AMOUNT to set
     */
    public static void setBILL_AMOUNT(double aBILL_AMOUNT) {
        BILL_AMOUNT = aBILL_AMOUNT;
    }

    /**
     * @return the MEDICAL_INSURER
     */
    public static String getMEDICAL_INSURER() {
        return MEDICAL_INSURER;
    }

    /**
     * @param aMEDICAL_INSURER the MEDICAL_INSURER to set
     */
    public static void setMEDICAL_INSURER(String aMEDICAL_INSURER) {
        MEDICAL_INSURER = aMEDICAL_INSURER;
    }

    /**
     * @return the NATIONAL_HEALTH_INSURANCE_NUMBER
     */
    public static String getNATIONAL_HEALTH_INSURANCE_NUMBER() {
        return NATIONAL_HEALTH_INSURANCE_NUMBER;
    }

    /**
     * @param aNATIONAL_HEALTH_INSURANCE_NUMBER the
     * NATIONAL_HEALTH_INSURANCE_NUMBER to set
     */
    public static void setNATIONAL_HEALTH_INSURANCE_NUMBER(String aNATIONAL_HEALTH_INSURANCE_NUMBER) {
        NATIONAL_HEALTH_INSURANCE_NUMBER = aNATIONAL_HEALTH_INSURANCE_NUMBER;
    }

    /**
     * @return the PATIENT_CLINIC_SPECIALITY
     */
    public static String getPATIENT_CLINIC_SPECIALITY() {
        return PATIENT_CLINIC_SPECIALITY;
    }

    /**
     * @param aPATIENT_CLINIC_SPECIALITY the PATIENT_CLINIC_SPECIALITY to set
     */
    public static void setPATIENT_CLINIC_SPECIALITY(String aPATIENT_CLINIC_SPECIALITY) {
        PATIENT_CLINIC_SPECIALITY = aPATIENT_CLINIC_SPECIALITY;
    }

    /**
     * @return the PATIENT_BMI_STATUS
     */
    public static double getPATIENT_BMI_STATUS() {
        return PATIENT_BMI_STATUS;
    }

    /**
     * @param aPATIENT_BMI_STATUS the PATIENT_BMI_STATUS to set
     */
    public static void setPATIENT_BMI_STATUS(double aPATIENT_BMI_STATUS) {
        PATIENT_BMI_STATUS = aPATIENT_BMI_STATUS;
    }

    /**
     * @return the PATIENT_CANCER_TESTED
     */
    public static String getPATIENT_CANCER_TESTED() {
        return PATIENT_CANCER_TESTED;
    }

    /**
     * @param aPATIENT_CANCER_TESTED the PATIENT_CANCER_TESTED to set
     */
    public static void setPATIENT_CANCER_TESTED(String aPATIENT_CANCER_TESTED) {
        PATIENT_CANCER_TESTED = aPATIENT_CANCER_TESTED;
    }

    /**
     * @return the PATIENT_CANCER_STATUS
     */
    public static String getPATIENT_CANCER_STATUS() {
        return PATIENT_CANCER_STATUS;
    }

    /**
     * @param aPATIENT_CANCER_STATUS the PATIENT_CANCER_STATUS to set
     */
    public static void setPATIENT_CANCER_STATUS(String aPATIENT_CANCER_STATUS) {
        PATIENT_CANCER_STATUS = aPATIENT_CANCER_STATUS;
    }

    /**
     * @return the PATIENT_CANCER_DIABETES
     */
    public static String getPATIENT_CANCER_DIABETES() {
        return PATIENT_CANCER_DIABETES;
    }

    /**
     * @param aPATIENT_CANCER_DIABETES the PATIENT_CANCER_DIABETES to set
     */
    public static void setPATIENT_CANCER_DIABETES(String aPATIENT_CANCER_DIABETES) {
        PATIENT_CANCER_DIABETES = aPATIENT_CANCER_DIABETES;
    }

    /**
     * @return the PATIENT_HEIGHT
     */
    public static double getPATIENT_HEIGHT() {
        return PATIENT_HEIGHT;
    }

    /**
     * @param aPATIENT_HEIGHT the PATIENT_HEIGHT to set
     */
    public static void setPATIENT_HEIGHT(double aPATIENT_HEIGHT) {
        PATIENT_HEIGHT = aPATIENT_HEIGHT;
    }

    /**
     * @return the PATIENT_WEIGHT
     */
    public static double getPATIENT_WEIGHT() {
        return PATIENT_WEIGHT;
    }

    /**
     * @param aPATIENT_WEIGHT the PATIENT_WEIGHT to set
     */
    public static void setPATIENT_WEIGHT(double aPATIENT_WEIGHT) {
        PATIENT_WEIGHT = aPATIENT_WEIGHT;
    }

    /**
     * @return the APGAR_SCORE
     */
    public static double getAPGAR_SCORE() {
        return APGAR_SCORE;
    }

    /**
     * @param aAPGAR_SCORE the APGAR_SCORE to set
     */
    public static void setAPGAR_SCORE(double aAPGAR_SCORE) {
        APGAR_SCORE = aAPGAR_SCORE;
    }

    /**
     * @return the PATIENT_DIABETES_STATUS
     */
    public static String getPATIENT_DIABETES_STATUS() {
        return PATIENT_DIABETES_STATUS;
    }

    /**
     * @param aPATIENT_DIABETES_STATUS the PATIENT_DIABETES_STATUS to set
     */
    public static void setPATIENT_DIABETES_STATUS(String aPATIENT_DIABETES_STATUS) {
        PATIENT_DIABETES_STATUS = aPATIENT_DIABETES_STATUS;
    }

    /**
     * @return the PATIENT_HIV_TESTED
     */
    public static String getPATIENT_HIV_TESTED() {
        return PATIENT_HIV_TESTED;
    }

    /**
     * @param aPATIENT_HIV_TESTED the PATIENT_HIV_TESTED to set
     */
    public static void setPATIENT_HIV_TESTED(String aPATIENT_HIV_TESTED) {
        PATIENT_HIV_TESTED = aPATIENT_HIV_TESTED;
    }

    /**
     * @return the PATIENT_HIV_STATUS
     */
    public static String getPATIENT_HIV_STATUS() {
        return PATIENT_HIV_STATUS;
    }

    /**
     * @param aPATIENT_HIV_STATUS the PATIENT_HIV_STATUS to set
     */
    public static void setPATIENT_HIV_STATUS(String aPATIENT_HIV_STATUS) {
        PATIENT_HIV_STATUS = aPATIENT_HIV_STATUS;
    }

    /**
     * @return the PATIENT_HIV_STATUS_KNOWN
     */
    public static String getPATIENT_HIV_STATUS_KNOWN() {
        return PATIENT_HIV_STATUS_KNOWN;
    }

    /**
     * @param aPATIENT_HIV_STATUS_KNOWN the PATIENT_HIV_STATUS_KNOWN to set
     */
    public static void setPATIENT_HIV_STATUS_KNOWN(String aPATIENT_HIV_STATUS_KNOWN) {
        PATIENT_HIV_STATUS_KNOWN = aPATIENT_HIV_STATUS_KNOWN;
    }

    /**
     * @return the PATIENT_ADMISSION_WARD
     */
    public static String getPATIENT_ADMISSION_WARD() {
        return PATIENT_ADMISSION_WARD;
    }

    /**
     * @param aPATIENT_ADMISSION_WARD the PATIENT_ADMISSION_WARD to set
     */
    public static void setPATIENT_ADMISSION_WARD(String aPATIENT_ADMISSION_WARD) {
        PATIENT_ADMISSION_WARD = aPATIENT_ADMISSION_WARD;
    }

    /**
     * @return the PATIENT_ADMISSION_OUTCOME
     */
    public static String getPATIENT_ADMISSION_OUTCOME() {
        return PATIENT_ADMISSION_OUTCOME;
    }

    /**
     * @param aPATIENT_ADMISSION_OUTCOME the PATIENT_ADMISSION_OUTCOME to set
     */
    public static void setPATIENT_ADMISSION_OUTCOME(String aPATIENT_ADMISSION_OUTCOME) {
        PATIENT_ADMISSION_OUTCOME = aPATIENT_ADMISSION_OUTCOME;
    }

    /**
     * @return the VICTIM_OF_GENDER_VIOLENCE
     */
    public static String getVICTIM_OF_GENDER_VIOLENCE() {
        return VICTIM_OF_GENDER_VIOLENCE;
    }

    /**
     * @param aVICTIM_OF_GENDER_VIOLENCE the VICTIM_OF_GENDER_VIOLENCE to set
     */
    public static void setVICTIM_OF_GENDER_VIOLENCE(String aVICTIM_OF_GENDER_VIOLENCE) {
        VICTIM_OF_GENDER_VIOLENCE = aVICTIM_OF_GENDER_VIOLENCE;
    }

    /**
     * @return the PATIENT_VISIT_ID
     */
    public static String getPATIENT_VISIT_ID() {
        return PATIENT_VISIT_ID;
    }

    /**
     * @param aPATIENT_VISIT_ID the PATIENT_VISIT_ID to set
     */
    public static void setPATIENT_VISIT_ID(String aPATIENT_VISIT_ID) {
        PATIENT_VISIT_ID = aPATIENT_VISIT_ID;
    }

    public static String getPATIENT_CITIZENSHIP() {
        return PATIENT_CITIZENSHIP;
    }

    public static void setPATIENT_CITIZENSHIP(String PATIENT_CITIZENSHIP) {
        HL7LabMessaging.PATIENT_CITIZENSHIP = PATIENT_CITIZENSHIP;
    }

    public static String getPATIENT_LAB_SERVICE() {
        return PATIENT_LAB_SERVICE;
    }

    public static void setPATIENT_LAB_SERVICE(String PATIENT_LAB_SERVICE) {
        HL7LabMessaging.PATIENT_LAB_SERVICE = PATIENT_LAB_SERVICE;
    }

    public static String getLAB_DEPARTMENT() {
        return LAB_DEPARTMENT;
    }

    public static void setLAB_DEPARTMENT(String LAB_DEPARTMENT) {
        HL7LabMessaging.LAB_DEPARTMENT = LAB_DEPARTMENT;
    }

    public static String getLAB_SERVICE_CODE() {
        return LAB_SERVICE_CODE;
    }

    public static void setLAB_SERVICE_CODE(String LAB_SERVICE_CODE) {
        HL7LabMessaging.LAB_SERVICE_CODE = LAB_SERVICE_CODE;
    }

    public static String getLAB_REQUEST_NO() {
        return LAB_REQUEST_NO;
    }

    public static void setLAB_REQUEST_NO(String LAB_REQUEST_NO) {
        HL7LabMessaging.LAB_REQUEST_NO = LAB_REQUEST_NO;
    }

    public static String getLAB_REQUESTED_BY() {
        return LAB_REQUESTED_BY;
    }

    public static void setLAB_REQUESTED_BY(String LAB_REQUESTED_BY) {
        HL7LabMessaging.LAB_REQUESTED_BY = LAB_REQUESTED_BY;
    }

    public static String getLAB_RADIOGRAPHER() {
        return LAB_RADIOGRAPHER;
    }

    public static void setLAB_RADIOGRAPHER(String LAB_RADIOGRAPHER) {
        HL7LabMessaging.LAB_RADIOGRAPHER = LAB_RADIOGRAPHER;
    }

    public static String getLAB_RESULT_NUMBER() {
        return LAB_RESULT_NUMBER;
    }

    public static void setLAB_RESULT_NUMBER(String LAB_RESULT_NUMBER) {
        HL7LabMessaging.LAB_RESULT_NUMBER = LAB_RESULT_NUMBER;
    }

    public static String getLAB_RESULTS_FINDING() {
        return LAB_RESULTS_FINDING;
    }

    public static void setLAB_RESULTS_FINDING(String LAB_RESULTS_FINDING) {
        HL7LabMessaging.LAB_RESULTS_FINDING = LAB_RESULTS_FINDING;
    }

    public static String getLAB_SERVICE_DATE() {
        return LAB_SERVICE_DATE;
    }

    public static void setLAB_SERVICE_DATE(String LAB_SERVICE_DATE) {
        HL7LabMessaging.LAB_SERVICE_DATE = LAB_SERVICE_DATE;
    }

    public static String getLAB_REASON() {
        return LAB_REASON;
    }

    public static void setLAB_REASON(String LAB_REASON) {
        HL7LabMessaging.LAB_REASON = LAB_REASON;
    }

    public static String getLAB_REMARKS() {
        return LAB_REMARKS;
    }

    public static void setLAB_REMARKS(String LAB_REMARKS) {
        HL7LabMessaging.LAB_REMARKS = LAB_REMARKS;
    }

    public static String getLAB_OWN_REMARKS() {
        return LAB_OWN_REMARKS;
    }

    public static void setLAB_OWN_REMARKS(String LAB_OWN_REMARKS) {
        HL7LabMessaging.LAB_OWN_REMARKS = LAB_OWN_REMARKS;
    }

    public static String getPOINT_OF_CARE() {
        return POINT_OF_CARE;
    }

    public static void setPOINT_OF_CARE(String POINT_OF_CARE) {
        HL7LabMessaging.POINT_OF_CARE = POINT_OF_CARE;
    }

    public static String getLAB_CATEGORY() {
        return LAB_CATEGORY;
    }

    public static void setLAB_CATEGORY(String LAB_CATEGORY) {
        HL7LabMessaging.LAB_CATEGORY = LAB_CATEGORY;
    }

    public static String getPATIENT_DATE_OF_BIRTH_STRING() {
        return PATIENT_DATE_OF_BIRTH_STRING;
    }

    public static void setPATIENT_DATE_OF_BIRTH_STRING(String PATIENT_DATE_OF_BIRTH_STRING) {
        HL7LabMessaging.PATIENT_DATE_OF_BIRTH_STRING = PATIENT_DATE_OF_BIRTH_STRING;
    }

    public static String getLAB_REQUEST_TIME() {
        return LAB_REQUEST_TIME;
    }

    public static void setLAB_REQUEST_TIME(String LAB_REQUEST_TIME) {
        HL7LabMessaging.LAB_REQUEST_TIME = LAB_REQUEST_TIME;
    }

    public static String getFacilityCode(java.sql.Connection connectDB, String patientNo, String type) {
        String code = "CPGH-4333.2333";

        try {

            java.sql.Statement stmtf = connectDB.createStatement();
            java.sql.ResultSet rsetf = null;
            
            if (type.equalsIgnoreCase("IP")) {
                rsetf = stmtf.executeQuery("select facility_name,facility_code,ward from hp_admission,hp_wards where "
                        + " patient_no = '" + patientNo + "'  and ward = ward_name order by date_admitted desc limit 1"); //and check_out = false
            } else {
                rsetf = stmtf.executeQuery("select facility_name,facility_code,clinic,date from hp_patient_visit,pb_clinics "
                        + "where patient_no = '" + patientNo + "'  and clinics = clinic order by date desc limit 1");
            }
            while (rsetf.next()) {
                code = rsetf.getString(1) + "-" + rsetf.getString(2);
                HL7LabMessaging.setPOINT_OF_CARE(rsetf.getString(3));
                HL7LabMessaging25.setPOINT_OF_CARE(rsetf.getString(3));
            }
            rsetf.close();
            stmtf.close();
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("select not successful");
        }

        return code;
    }

    public static void logRequest(java.sql.Connection connectDB, String patNo, String name, String service, String response,String msg_type,String msg) {
        try {
            java.sql.PreparedStatement pstmt25e = connectDB.prepareStatement("INSERT INTO public.pb_hl7_requests(patient_no, patient_name, service,  response, msg_type,msg) "
                    + "  VALUES (?, ?, ?, ?,?,?)");

            pstmt25e.setObject(1, patNo);
            pstmt25e.setObject(2, name);
            pstmt25e.setString(3, service);
            pstmt25e.setString(4, response);
            pstmt25e.setString(5, msg_type);
            pstmt25e.setString(6, msg);
            pstmt25e.executeUpdate();
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
    }

    public ADT_A01 generateHL7Message(java.sql.Connection connectDB, String patientNo, String type) throws LLPException {

        System.out.println("---------------------------------------"+patientNo+"--------------------------------------------------------");
        final char END_OF_BLOCK = 28; //'\u001c';
        final char START_OF_BLOCK = '\u000b';
        final char CARRIAGE_RETURN = 13;
//        SERVER_IP_ADDRESS = com.funsoft.system.core.Link.SERVER_IP_ADDRESS;
 //       SERVER_PORT = com.funsoft.system.core.Link.SERVER_PORT;

        System.err.println("SERVER_IP_ADDRESS -- " + SERVER_IP_ADDRESS);

//
        StringBuilder testHL7MessageToTransmit = new StringBuilder();

        HL7LabDao hl7Dao = new HL7LabDao();
        hl7Dao.getHL7Messaging(connectDB, patientNo, type);

        // return revArray;
        adt = new ADT_A01();
        ORU_R01 oru = new ORU_R01();
        ORM_O01 orm = new ORM_O01();
        try {
            PATIENT_ID = patientNo;
            setFACILITY_ID(FACILITY_ID);
            setFACILITY_NAME(FACILITY_NAME);
            System.out.println("Jina ya hosi " + FACILITY_NAME);
            setPATIENT_FAMILY_SURNAME(PATIENT_FAMILY_SURNAME.trim());
            setPATIENT_GIVEN_NAME(PATIENT_GIVEN_NAME.trim());
            setPATIENT_CITIZENSHIP(PATIENT_CITIZENSHIP);

            //adt.initQuickstart("ADT", "A01", "P");
            // Populate the MSH Segment
            mshSegment = adt.getMSH();
            mshSegment.getSendingApplication().getNamespaceID().setValue("Funsoft I-HMIS");
            mshSegment.getSequenceNumber().setValue(getHL7SequenceNumber(connectDB));
            mshSegment.getMessageControlID().setValue("MESSAGE_CONTROL_ID_1");
            mshSegment.getCountryCode().setValue("254");
            mshSegment.getDateTimeOfMessage().getTimeOfAnEvent().setValue(getCurrentTimeStamp());
//            HL7Utils.populateMessageHeader(mshSegment, new Date(), "ADT", "A01", "CPGH");//FACILITY_NAME

//            mshSegment.getSendingFacility().getUniversalID().setValue(getFACILITY_NAME());
//            mshSegment.getReceivingFacility().getUniversalID().setValue(getFACILITY_NAME());
            mshSegment.getSendingFacility().getNamespaceID().setValue("CPGH");//getFACILITY_NAME()
            mshSegment.getSendingFacility().getUniversalID().setValue("4333.2333");

            String receivingFacility = getFacilityCode(connectDB, patientNo, type);

            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>" + receivingFacility);
            String[] parts = receivingFacility.split("-");
            String facilityName = parts[0]; // 004
            String facilityCode = parts[1];

            System.err.println(facilityName + "<<<<<<<<<<<<>>>>>>>>>>>>>>>" + facilityCode);

            mshSegment.getReceivingFacility().getNamespaceID().setValue("Laboratory");//facilityName getFACILITY_NAME()
            //mshSegment.getReceivingFacility().getUniversalID().setValue(facilityCode);

            mshSegment.getReceivingApplication().getNamespaceID().setValue("LABWARE");
            mshSegment.getMsh3_SendingApplication().getUniversalID().setValue("9.0 R 3.0");

            // Populate the PID Segment
            PID pid = adt.getPID();
            pid.getPatientName(0).getFamilyName().getSurname().setValue(PATIENT_FAMILY_SURNAME);
            pid.getPatientName(0).getGivenName().setValue(PATIENT_GIVEN_NAME);
            pid.getPatientIdentifierList(0).getID().setValue(PATIENT_ID);
            pid.getAlternatePatientIDPID(0).getCx1_ID().setValue(PATIENT_ID);

            pid.getCitizenship(0).getCe1_Identifier().setValue(PATIENT_CITIZENSHIP);
            pid.getLastUpdateFacility().getUniversalID().setValue(FACILITY_ID);
            pid.getAdministrativeSex().setValue(PATIENT_GENDER);
            pid.getBirthOrder().setValue("2");
            pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(PATIENT_DATE_OF_BIRTH_STRING);
            pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue("CPGH");//FACILITY_NAME
            pid.getPatientAddress(0).getZipOrPostalCode().setValue(PATIENT_POSTAL_ADDRESS);
            pid.getPatientAddress(0).getCity().setValue(PATIENT_NOK_RESIDENCE);
            pid.getMaritalStatus().getText().setValue(PATIENT_MARITAL_STATUS);
            //pid.getPhoneNumberHome(0).getPhoneNumber().setValue(PATIENT_NOK_TELEPHONE_NO);
            //pid.getCitizenship(0).
            createPv1Segment();
//            OBX obx = adt.getOBX();
//            obx.getObservationMethod(0).getText().setValue("TRIAGE_TEMPERATURE");
            ca.uhn.hl7v2.model.v24.segment.EVN evn = adt.getEVN();
            evn.getEvn1_EventTypeCode().setValue("A01"); //Was A08
            evn.getRecordedDateTime().getTimeOfAnEvent().setValue(LAB_REQUEST_TIME);

            ADT_A01_INSURANCE adt_ins = adt.getINSURANCE(0);
            adt_ins.getIN1().getGroupName(0).getOrganizationName().setValue(MEDICAL_INSURER);
            adt_ins.getIN1().getAuthorizationInformation().getAuthorizationNumber().setValue(NATIONAL_HEALTH_INSURANCE_NUMBER);
            adt_ins.getIN1().getPlanType().setValue(""); //"FULL PLAN"
            adt_ins.getIN1().insertIn116_NameOfInsured(0).getGivenName().setValue(PATIENT_FAMILY_SURNAME);
            adt_ins.getIN1().getIn119_InsuredSAddress(0).getZipOrPostalCode().setValue(PATIENT_POSTAL_ADDRESS);

//            adt.insertAL1(PATIENT_AGE);
            // Now, let's encode the message and look at the output
            //ADT_A01_O
            context = new DefaultHapiContext();
            parser = context.getPipeParser();
            String encodedMessage = parser.encode(adt);
//            System.out.println("Printing ER7 Encoded Message:");
//            System.out.println(encodedMessage);
//            writeToFile(encodedMessage, "/root/ADTMessage.er7");
//            parser = context.getPipeParser();
            // parser = context.getXMLParser();

            //String encodedMessage = parser.encode(message);
            testHL7MessageToTransmit.append(START_OF_BLOCK)
                    .append(encodedMessage)
                    .append(CARRIAGE_RETURN)
                    .append(END_OF_BLOCK)
                    .append(CARRIAGE_RETURN);

            System.out.println("Printing ER7 ADT Encoded Message 4:");
            System.out.println(encodedMessage);
            // System.out.println("Printing MLLP Encoded Message 3: " + new String(testHL7MessageToTransmit.toString().getBytes()));

//            writeToFile(testHL7MessageToTransmit.toString(), "D:\\HL7/ADTMessage.er7");
            //writeToFile(encodedMessage.toString(), "D:\\HL7/ADTMessage.er7");
            testHL7MessageToTransmit.toString();
            //Socket socketADT1 = new Socket("127.0.0.1", 6333);`
            //Socket socketADT1 = new Socket("10.12.2.14", 6002);
            Socket socketADT1 = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
            socketADT1.setSoTimeout(30 * 1000);
            System.out.println("Connected to " + SERVER_IP_ADDRESS + " - " + SERVER_PORT);
            InputStream in = socketADT1.getInputStream();
            OutputStream out = socketADT1.getOutputStream();
            // Send the MLLP-wrapped HL7 message to the server

            out.write(testHL7MessageToTransmit.toString().getBytes());
            
            //out.write(encodedMessage.getBytes());

            byte[] byteBuffer = new byte[200];

            in.read(byteBuffer);
            System.out.println("Received from Server ADT: " + new String(byteBuffer));
            logRequest(connectDB, PATIENT_ID, PATIENT_FAMILY_SURNAME+" "+PATIENT_GIVEN_NAME, "", "","ADT",encodedMessage);

            
            // Next, let's use the XML parser to encode as XML
//            parser = context.getXMLParser();
//            encodedMessage = parser.encode(adt);
//            System.out.println("Printing XML Encoded Message:");
//            System.out.println(encodedMessage);
            //http://www.dicomserver.co.uk/
            //"10.12.2.14", 6002
            //Socket socket = new Socket("127.0.0.1", 6333);
            //Socket socket = new Socket( "10.12.2.14", 6002);
            //Commented ORU Message SAM
            //Socket socket = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
            //generateORUMessage(socket, connectDB);
            //createRadiologyOrderMessage(socket);
            //Socket socketORM = new Socket("127.0.0.1", 6333);
            //Socket socketORM = new Socket( "10.12.2.14", 6002);
            Socket socketORM = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);

//            com.afrisoftech.laboratory.HL7LabMessaging25.createLaboratoryOrderMessage(socketORM, connectDB, patientNo, type);

            // Close the socket and its streams
            //return testHL7MessageToTransmit.toString();
            //return message;
            //
            // Close the socket and its streams
            socketADT1.close();
        } catch (java.net.UnknownHostException u) {
            u.printStackTrace();
            System.out.println(u);
        } catch (HL7Exception ex) {
            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            //javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            //javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        return adt;

    }

    private void createPv1Segment() throws DataTypeException {
        PV1 pv1 = adt.getPV1();
        pv1.getPatientClass().setValue("O"); // to represent an 'Outpatient'
//            PL assignedPatientLocation = pv1.getAssignedPatientLocation();
//            assignedPatientLocation.getFacility().getNamespaceID().setValue("Some Treatment Facility Name");
//            assignedPatientLocation.getPointOfCare().setValue("Some Point of Care");
        pv1.getAssignedPatientLocation().getPointOfCare().setValue(POINT_OF_CARE);
        pv1.getAdmissionType().setValue("ALERT");
        XCN referringDoctor = pv1.getReferringDoctor(0);
        referringDoctor.getIDNumber().setValue(PATIENT_ID);
        referringDoctor.getFamilyName().getSurname().setValue(PATIENT_FAMILY_SURNAME);
        referringDoctor.getGivenName().setValue(PATIENT_GIVEN_NAME);
        referringDoctor.getIdentifierTypeCode().setValue(FACILITY_ID);
        // pv1.getAdmitDateTime().getTimeOfAnEvent().setValue(getCurrentTimeStamp());
        pv1.getAdmitDateTime().getTimeOfAnEvent().setValue(getCurrentTimeStamp());
    }

    private static String getHL7SequenceNumber(java.sql.Connection connectDB) {

        int hl7SequenceID = 0;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT nextval('hl7sequence_no')");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                hl7SequenceID = rset.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex);
            ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }

        return (String.valueOf(hl7SequenceID));

    }

    /**
     * @return the PATIENT_CITIZENSHIP
     */
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private ORU_R01 generateORUMessage(Socket socket, java.sql.Connection connectDB) throws HL7Exception, IOException {

        // final char END_OF_BLOCK = '\u001c';
        // final char START_OF_BLOCK = '\u000b';
        final char END_OF_BLOCK = '\u001c';
        final char START_OF_BLOCK = '\u000b';
        final char CARRIAGE_RETURN = 13;
//
        StringBuilder testHL7MessageToTransmit = new StringBuilder();

        ORU_R01 message = new ORU_R01();

        mshSegment = message.getMSH();
        //        mshSegment = adt.getMSH();
        mshSegment.getSendingApplication().getNamespaceID().setValue("Funsoft I-HMIS");
        mshSegment.getSequenceNumber().setValue(getHL7SequenceNumber(connectDB));
        mshSegment.getCountryCode().setValue("254");
        mshSegment.getSendingFacility().getUniversalID().setValue(FACILITY_NAME);
        mshSegment.getMsh3_SendingApplication().getUniversalID().setValue("9.0 R 3.0");
        // pv1.getAssignedPatientLocation().getPersonLocationType().setValue("Funsoft EMR");

        message.initQuickstart("ORU", "R01", "T");

        ca.uhn.hl7v2.model.v24.group.ORU_R01_ORDER_OBSERVATION orderObservation = message.getPATIENT_RESULT().getORDER_OBSERVATION();

        ca.uhn.hl7v2.model.v24.group.ORU_R01_PATIENT patient = message.getPATIENT_RESULT().getPATIENT();
        ca.uhn.hl7v2.model.v24.segment.PID pid = patient.getPID();
        //PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue(PATIENT_FAMILY_SURNAME);
        pid.getPatientName(0).getGivenName().setValue(PATIENT_GIVEN_NAME);
        pid.getPatientIdentifierList(0).getID().setValue(PATIENT_ID);
        pid.getCitizenship(0).getCe1_Identifier().setValue(PATIENT_CITIZENSHIP);
        pid.getLastUpdateFacility().getUniversalID().setValue(FACILITY_ID);
        pid.getAdministrativeSex().setValue(PATIENT_GENDER);
        pid.getBirthOrder().setValue("2");
        pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(PATIENT_DATE_OF_BIRTH_STRING);
        pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(FACILITY_NAME);
        pid.getPatientAddress(0).getZipOrPostalCode().setValue(PATIENT_POSTAL_ADDRESS);
        pid.getPatientAddress(0).getCity().setValue(PATIENT_NOK_RESIDENCE);
        pid.getMaritalStatus().getText().setValue(PATIENT_MARITAL_STATUS);
        pid.getPhoneNumberHome(0).getPhoneNumber().setValue(PATIENT_NOK_TELEPHONE_NO);
        ca.uhn.hl7v2.model.v24.segment.PV1 pv1 = patient.getVISIT().getPV1();
        pv1.getAssignedPatientLocation().getPointOfCare().setValue(POINT_OF_CARE);
        pv1.getAssignedPatientLocation().getPersonLocationType().setValue("Funsoft EMR");
        pv1.getPreadmitNumber().getID().setValue(PATIENT_ID);
        pv1.getPv119_VisitNumber().getID().setValue(PATIENT_VISIT_ID);
        //pv1.getAlternateVisitID().getID().setValue(PATIENT_VISIT_ID);

        // Populate the OBR
        OBR obr = orderObservation.getOBR();
        obr.getSetIDOBR().setValue("1");
        obr.getPlacerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID);
        obr.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_VISIT_ID);
        obr.getUniversalServiceIdentifier().getIdentifier().setValue(LAB_SERVICE_CODE);
        obr.getUniversalServiceIdentifier().getText().setValue(PATIENT_LAB_SERVICE);
        obr.getResultStatus().setValue("F");

//        obr.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_VISIT_ID);
//        obr.getUniversalServiceIdentifier().getIdentifier().setValue(LAB_SERVICE_CODE);
//        obr.getUniversalServiceIdentifier().getText().setValue(PATIENT_LAB_SERVICE);
        ca.uhn.hl7v2.model.v24.group.ORU_R01_OBSERVATION observation = orderObservation.getOBSERVATION(0);
        // Populate the first OBX
        OBX obx = observation.getOBX();
        obx.getSetIDOBX().setValue("1");
        // The first OBX has a value type of CE. So first, we populate OBX-2 with "CE"...
        obx.getValueType().setValue("TX");
        // ... then we create a CE instance to put in OBX-5.
        // ce.getIdentifier().setValue("T57000");
        obx.getObservationIdentifier().getIdentifier().setValue("Xray Results");
        // obx.getObservationSubId().setValue("1");
        obx.getObservationSubId().setValue("Name: " + PATIENT_GIVEN_NAME);
        obx.getObservationResultStatus().setValue("F");
        // ce.getNameOfCodingSystem().setValue("SNM");
        Varies value = obx.getObservationValue(0);
        CE ce = new CE(message);
        value.setData(ce);

        // Now we populate the second OBX
//        obx = orderObservation.getOBSERVATION(1).getOBX();
//        obx.getSetIDOBX().setValue("2");
//        // obx.getObservationSubId().setValue("1");
//
//        // The second OBX in the sample message has an extra subcomponent at
//        // OBX-3-1. This component is actually an ST, but the HL7 specification allows
//        // extra subcomponents to be tacked on to the end of a component. This is
//        // uncommon, but HAPI nontheless allows it.
//        ca.uhn.hl7v2.model.v24.datatype.ST observationIdentifier = obx.getObservationIdentifier().getIdentifier();
//        observationIdentifier.setValue("88304");
//        ST extraSubcomponent = new ST(message);
//        extraSubcomponent.setValue("MDT");
//        observationIdentifier.getExtraComponents().getComponent(0).setData(extraSubcomponent);
//
//        // The first OBX has a value type of TX. So first, we populate OBX-2 with "TX"...
//        obx.getValueType().setValue("TX");
//
//        // ... then we create a CE instance to put in OBX-5.
//        TX tx = new TX(message);
//        tx.setValue("LAB SHOWS HAZY PATCHMENTATION");
//        value = obx.getObservationValue(0);
//        value.setData(tx);
        // Print the message (remember, the MSH segment was not fully or correctly populated)
        parser = context.getPipeParser();
        // parser = context.getXMLParser();
        String encodedMessage = parser.encode(message);
        System.out.println("Printing ORU XML Encoded Message:");
        System.out.println(encodedMessage);

        //  String encodedMessage = parser.encode(message);
        testHL7MessageToTransmit.append(START_OF_BLOCK)
                .append(encodedMessage)
                //.append(encodedMessage)
                .append(CARRIAGE_RETURN)
                .append(END_OF_BLOCK)
                .append(CARRIAGE_RETURN);

        System.out.println("Printing ER7 ORU Encoded Message 3:");
        System.out.println(encodedMessage);
        // System.out.println("Printing MLLP Encoded Message 3: " + new String(testHL7MessageToTransmit.toString().getBytes()));
        writeToFile(testHL7MessageToTransmit.toString(), "D:\\HL7/ORUMessage.er7");
        //   writeToFile(encodedMessage.toString(), "/root/ORUMessage.er7");
        testHL7MessageToTransmit.toString();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        // Send the MLLP-wrapped HL7 message to the server
        out.write(testHL7MessageToTransmit.toString().getBytes());
        //     out.write(encodedMessage.getBytes());

        byte[] byteBuffer = new byte[200];
        in.read(byteBuffer);

        System.out.println("Received from Server ORU: " + new String(byteBuffer));
        // Close the socket and its streams
        socket.close();
        //return testHL7MessageToTransmit.toString();
        return message;
    }

//    private void generateORMessage(java.sql.Connection connectDB) throws HL7Exception, IOException {
//
//        // ORU_R01 message = new ORU_R01();
//        ORM_O01 message = new ORM_O01();
//
//        // mshSegment = message.getMSH();
//        //        mshSegment = adt.getMSH();
//        mshSegment.getSendingApplication().getNamespaceID().setValue("Funsoft I-HMIS HL7 Messaging System");
//        mshSegment.getSequenceNumber().setValue(getHL7SequenceNumber(connectDB));
//        mshSegment.getCountryCode().setValue("254");
//        mshSegment.getSendingFacility().getUniversalID().setValue(FACILITY_NAME);
//        mshSegment.getMsh3_SendingApplication().getUniversalID().setValue("FUNSOFT I-HMIS");
//
//        message.initQuickstart("ORM", "R01", "T");
//
//        //    message.getORDER(1).getORC().getPlacerOrderNumber().getUniversalID().setValue("XR0001");
//        // Now we populate the second OBX
////        obx = orderObservation.getOBSERVATION(1).getOBX();
////        obx.getSetIDOBX().setValue("2");
////        obx.getObservationSubId().setValue("1");
//        //obr.getExtraComponents().getComponent(0).setData(extraSubcomponent);
//        // The first OBX has a value type of TX. So first, we populate OBX-2 with "TX"...
//        // obx.getValueType().setValue("TX");
//        // ... then we create a CE instance to put in OBX-5.
//        TX tx = new TX(message);
//        tx.setValue("LAB SHOWS HAZY PATCHMENTATION");
//        // value = obx.getObservationValue(0);
//        // value.setData(tx);
//
//        // Print the message (remember, the MSH segment was not fully or correctly populated)
//        parser = context.getXMLParser();
//        String encodedMessage = parser.encode(message);
//        System.out.println("Printing ORM XML Encoded Message:");
//        System.out.println(encodedMessage);
//
//        // Print the message (remember, the MSH segment was not fully or correctly populated)
//        parser = context.getGenericParser();
//        encodedMessage = parser.encode(message);
//        System.out.println("Printing ORM ER7 Encoded Message:");
//        System.out.println(encodedMessage);
//        // System.out.println(message.encode());
//        writeToFile(encodedMessage, "/root/ORMMessage.er7");
////        try {
////            createRemoteOrder("10.12.2.15", 6002);
////        } catch (LLPException ex) {
////            Exceptions.printStackTrace(ex);
////        }
//    }
    public void writeObjectToFile(Object serObj, String fileName) {

        try {

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeToFile(String message, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createRemoteOrder(String remoteHost, int remotePort) throws HL7Exception, LLPException, IOException {
        //Creating client to accept Message i.e PACS server here
        System.out.println("Host " + remoteHost + " Port " + remotePort);

        Socket socket = new Socket(remoteHost, remotePort);
        //Message response = initiator.sendAndReceive(createRadiologyOrderMessage());
        createRadiologyOrderMessage_24(socket);

        //createRadiologyOrderMessage(socket);
        //   Connection newClientConnection = null;
//        try {
//            ConnectionHub connectionHub;
//            connectionHub = ConnectionHub.getNewInstance(context);
//            newClientConnection = connectionHub.attach(remoteHost, remotePort, new PipeParser(), MinLowerLayerProtocol.class);
//            Initiator initiator = newClientConnection.getInitiator();
//            // Initiator initiator1 = newClientConnection.getInitiator();
//            //Socket sockect;
//            Message response = initiator.sendAndReceive(createRadiologyOrderMessage(socket));
//           // createRadiologyOrderMessage(new Socket(remoteHost, remotePort));
//            String responseString = new PipeParser().encode(response);
//
//            log.info("Received response ORM: \n" + responseString);
//        } finally {
//            if (newClientConnection != null) {
//                newClientConnection.close();
//            }
//        }
    }

//    private void createORURemoteOrder(String remoteHost, int remotePort, java.sql.Connection connectDB) throws HL7Exception, LLPException, IOException {
//        //Creating client to accept Message i.e PACS server here
//        Socket socket = new Socket(remoteHost, remotePort);
//        //Message response = initiator.sendAndReceive(createRadiologyOrderMessage());
//        
//        generateORUMessage(socket, connectDB);
//        //createRadiologyOrderMessage(socket);
//        Connection newClientConnection1 = null;
//        try {
//            ConnectionHub connectionHub;
//            connectionHub = ConnectionHub.getNewInstance(context);
//            newClientConnection1 = connectionHub.attach(remoteHost, remotePort, new PipeParser(), MinLowerLayerProtocol.class);
//            Initiator initiator = newClientConnection1.getInitiator();
//            Initiator initiator1 = newClientConnection1.getInitiator();
//            //Socket sockect;
//            //Message response = initiator.sendAndReceive(createRadiologyOrderMessage(socket));
//            //createRadiologyOrderMessage(new Socket(remoteHost, remotePort));
//            Message response1;
//            response1 = initiator1.sendAndReceive(generateORUMessage(socket, connectDB));
//            generateORUMessage(new Socket(remoteHost, remotePort), connectDB);
//            //String responseString = new PipeParser().encode(response);
//            String responseString1 = new PipeParser().encode(response1);
//
//            //log.info("Received response ORM:\n" + responseString);
//            log.info("Received response ORU: \n" + responseString1);
//        } finally {
//            if (newClientConnection1 != null) {
//                newClientConnection1.close();
//            }
//        }
//    }
//    private void createADTRemoteOrder(String remoteHost, int remotePort, java.sql.Connection connectDB, String patientNo) throws HL7Exception, LLPException, IOException {
//        //Creating client to accept Message i.e PACS server here
//        Socket socket = new Socket(remoteHost, remotePort);
//        //Message response = initiator.sendAndReceive(createRadiologyOrderMessage());
//
//        generateHL7Message(connectDB, patientNo);
//        //createRadiologyOrderMessage(socket);
//        Connection newClientConnection1 = null;
//        try {
//            ConnectionHub connectionHub;
//            connectionHub = ConnectionHub.getNewInstance(context);
//            newClientConnection1 = connectionHub.attach(remoteHost, remotePort, new PipeParser(), MinLowerLayerProtocol.class);
//            // Initiator initiator = newClientConnection1.getInitiator();
//            Initiator initiator1 = newClientConnection1.getInitiator();
//            //Socket sockect;
//            //Message response = initiator.sendAndReceive(createRadiologyOrderMessage(socket));
//            //createRadiologyOrderMessage(new Socket(remoteHost, remotePort));
//            Message response1;
//            response1 = initiator1.sendAndReceive(generateHL7Message(connectDB, patientNo));
//            generateHL7Message(connectDB, patientNo);
//            //String responseString = new PipeParser().encode(response);
//            String responseString1 = new PipeParser().encode(response1);
//
//            //log.info("Received response ORM:\n" + responseString);
//            log.info("Received response ADT: \n" + responseString1);
//        } finally {
//            if (newClientConnection1 != null) {
//                newClientConnection1.close();
//            }
//        }
//    }
    public ORM_O01 createRadiologyOrderMessage_24(Socket socket) throws HL7Exception, IOException {
        //  getHL7Messanging(java.sql.Connection connectDB, String patientNo)

        final char END_OF_BLOCK = 28;//'\u001c';
        final char START_OF_BLOCK = '\u000b';
        final char CARRIAGE_RETURN = 13;

//
        StringBuilder testHL7MessageToTransmit = new StringBuilder();

        ORM_O01 message = new ORM_O01();

        // handle the MSH component
        ca.uhn.hl7v2.model.v24.segment.MSH msh = message.getMSH();
        msh.getMessageControlID().setValue("MESSAGE_CONTROL_ID_1");
        msh.getSendingApplication().getNamespaceID().setValue("Funsoft I-HMIS");
        msh.getSendingApplication().getUniversalID().setValue("9.0 R 3.0");

        msh.getReceivingApplication().getNamespaceID().setValue("LABWARE");
        msh.getReceivingFacility().getNamespaceID().setValue("SHOE4AFRICA");
        msh.getReceivingFacility().getUniversalID().setValue("4626.6254");
//        HL7Utils.populateMessageHeader(msh, new Date(), "ORM", "O01", "CPGH");//FACILITY_NAME
        msh.getSendingFacility().getUniversalID().setValue("4333.2333");
        msh.getDateTimeOfMessage().getTimeOfAnEvent().setValue(getCurrentTimeStamp());

        //HL7Utils.populateMessageHeader(msh, new Date(), "ORM", "O01", "Funsoft EMR");
        //message.initQuickstart("ORM", "001", "FUNSOFT EMR");
        // handle the patient PID component
        ca.uhn.hl7v2.model.v24.group.ORM_O01_PATIENT patient = message.getPATIENT();
        ca.uhn.hl7v2.model.v24.segment.PID pid = patient.getPID();
        //pid.getPatientID().getIDNumber().setValue("GAN00001");
        pid.getPatientIdentifierList(0).getID().setValue(PATIENT_ID);
        pid.getAlternatePatientIDPID(0).getCx1_ID().setValue(PATIENT_ID);//added by sam
        pid.getPatientName(0).getFamilyName().getSurname().setValue(PATIENT_FAMILY_SURNAME.trim());
        pid.getPatientName(0).getGivenName().setValue(PATIENT_GIVEN_NAME.trim());
        pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(PATIENT_DATE_OF_BIRTH_STRING);
        pid.getAdministrativeSex().setValue(PATIENT_GENDER);
        // TODO: do we need patient admission ID / account number

//        patient.getNTE(0).insertComment(0).setValue("Radiology Order Remarks/Nursing");
//        patient.getNTE(0).getSourceOfComment().setValue("L");
//        patient.getNTE(0).getSetIDNTE().setValue("0");
//
//        patient.getNTE(1).insertComment(0).setValue("Mpre Comment/Clinician");
//        patient.getNTE(1).getSourceOfComment().setValue("P");
//        patient.getNTE(1).getSetIDNTE().setValue("1");
        // handle patient visit component
        ca.uhn.hl7v2.model.v24.segment.PV1 pv1 = patient.getPATIENT_VISIT().getPV1();
        pv1.getAssignedPatientLocation().getPointOfCare().setValue(POINT_OF_CARE);
        pv1.getAssignedPatientLocation().getPersonLocationType().setValue("Funsoft EMR");
        // pv1.getPreadmitNumber().getID().setValue(PATIENT_ID);
        pv1.getPv119_VisitNumber().getID().setValue(PATIENT_VISIT_ID);
        // pv1.getAlternateVisitID().getID().setValue(PATIENT_VISIT_ID);

        // handle ORC component
        ca.uhn.hl7v2.model.v24.segment.ORC orc = message.getORDER().getORC();
        orc.getPlacerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID);
        orc.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_VISIT_ID);
        //orc.getOrc5_OrderStatus().setValue("IP"); // Commented by sam
        //orc.getPv119_VisitNumber().getIDNumber().setValue(PATIENT_VISIT_ID);
        // orc.getPlacerOrderNumber().getNamespaceID().setValue(LAB_REQUESTED_BY);
        //orc.getEnteredBy(0).getFamilyName().getSurname().setValue(LAB_REQUESTED_BY);

        // orc.getFillerOrderNumber().// Accession number in Imagesuite
        //orc.getVerifiedBy(0).getGivenName().setValue(LAB_REQUESTED_BY);
        //orc.getOrderingProvider(0).getFamilyName().getSurname().setValue(LAB_REQUESTED_BY);
        orc.getOrderControl().setValue("NW");

        //orc.getOrderingProvider(0).getGivenName().setValue("LABWARE");
        orc.getOrderingProvider(0).getIdentifierTypeCode().setValue("LABWARE");
        orc.getOrderingProvider(0).getIDNumber().setValue("00000000"); //ORDERING PHYSICIAON

        //orc.getOrc21_OrderingFacilityName(0).getIDNumber().setValue("CPGH");
        orc.getOrc21_OrderingFacilityName(0).getNameRepresentationCode().setValue("CPGH");

//        orc.getOrderControl().setValue("CA");
//        orc.getOrderStatus().setValue("DC");
        // orc.getOrderingProvider(0).getGivenName().setValue(LAB_DEPARTMENT);
        // orc.getOrderingProvider(0).getIDNumber().setValue("1");
        // orc.getOrderingProvider(0).getFamilyName().getSurname().setValue(LAB_REQUESTED_BY);
        // handle OBR component
        ca.uhn.hl7v2.model.v24.segment.OBR obr = message.getORDER().getORDER_DETAIL().getOBR(); // http://www.mexi.be/documents/hl7/ch400024.htm  http://www.mexi.be/documents/hl7/ch700010.htm
////        obr.getPlacerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID);
////        obr.getFillerOrderNumber().getEntityIdentifier().setValue(LAB_REQUEST_NO);
////        obr.getUniversalServiceIdentifier().getIdentifier().setValue(LAB_SERVICE_CODE);
////        obr.getUniversalServiceIdentifier().getText().setValue(PATIENT_LAB_SERVICE);
////       // OBR obr = orderObservation.getOBR();
        // obr.getSetIDOBR().setValue("1");

        obr.getPlacerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID);
        obr.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_VISIT_ID);
        obr.getUniversalServiceIdentifier().getIdentifier().setValue("903"); //LAB_SERVICE_CODE
        obr.getUniversalServiceIdentifier().getText().setValue("MPS");//PATIENT_LAB_SERVICE
        obr.getResultStatus().setValue("F");

        // obr.getPlacerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID);
//        obr.getPlacerOrderNumber().getNamespaceID().setValue(LAB_DEPARTMENT);
//        obr.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_VISIT_ID);
//        obr.getUniversalServiceIdentifier().getIdentifier().setValue(LAB_REQUEST_NO);
//        obr.getResultStatus().setValue("F");
        //obr.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue("");
        // obr.getProcedureCode().getIdentifier().setValue(LAB_SERVICE_CODE);
        // obr.getPlacerOrderNumber().getEntityIdentifier().setValue("A00");
        // obr.getSetIDOBR().setValue(PATIENT_ID);
//        obr.getFillerOrderNumber().getEntityIdentifier().setValue("ORNO1");
        // note that we are just sending modality here, not the device location
        obr.getPlacerField2().setValue(LAB_CATEGORY);
        obr.getQuantityTiming(0).getPriority().setValue("STAT");
        obr.getPlacerField2().setValue("HE");
        obr.getScheduledDateTime().getTimeOfAnEvent().setValue(getCurrentDate());
//        obr.

        // break the reason for study up by lines
        //obr.getReasonForStudy(0).getText().setValue(LAB_REASON);
        //obr.getReasonForStudy(1).getText().setValue(LAB_REMARKS);
        //obr.getCollectorSComment(0).getText().setValue(LAB_OWN_REMARKS);
        // ca.uhn.hl7v2.model.v24.segment.NTE nte = message.getNTE();
//        nte.getComment(1).setValue(LAB_REMARKS);
        //nte.getComment(0).setValue(LAB_REASON);
        parser = context.getPipeParser();
        String encodedMessage = parser.encode(message);
        System.out.println("Printing ER7 Encoded Message 1:");
        System.out.println(encodedMessage);
        //  writeToFile(encodedMessage, "/root/ORMMessage.er7");

        parser = context.getPipeParser();
        //String encodedMessage = parser.encode(message);
        testHL7MessageToTransmit.append(START_OF_BLOCK)
                .append(encodedMessage)
                .append(CARRIAGE_RETURN)
                .append(END_OF_BLOCK)
                .append(CARRIAGE_RETURN);

        System.out.println("Printing ER7 Encoded Message 2:");
        System.out.println(encodedMessage);
        // System.out.println("Printing MLLP Encoded Message 3: " + new String(testHL7MessageToTransmit.toString().getBytes()));
        writeToFile(testHL7MessageToTransmit.toString(), "D:\\HL7/ORMMessage.er7");
        //writeToFile(encodedMessage.toString(), "D:\\HL7/ORMMessage.er7");
        testHL7MessageToTransmit.toString();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        // Send the MLLP-wrapped HL7 message to the server

        out.write(testHL7MessageToTransmit.toString().getBytes());
        //out.write(encodedMessage.getBytes());

        byte[] byteBuffer = new byte[200];
        in.read(byteBuffer);

        System.out.println("Received from Server ORM: " + new String(byteBuffer));
        // Close the socket and its streams
        socket.close();
        //return testHL7MessageToTransmit.toString();
        return message;

    }

//    public void createRadiologyOrderMessage(Socket socket) throws HL7Exception, IOException {
//
//        final char END_OF_BLOCK = '\u001c';
//        final char START_OF_BLOCK = '\u000b';
//        final char CARRIAGE_RETURN = 13;
//
//        StringBuilder testHL7MessageToTransmit = new StringBuilder();
//
//        ORM_O01 message = new ORM_O01();
//
//        // handle the MSH component
//        ca.uhn.hl7v2.model.v24.segment.MSH msh = message.getMSH();
//        msh.getMessageControlID().setValue("MESSAGE_CONTROL_ID_1");
//        HL7Utils.populateMessageHeader(msh, new Date(), "ORM", "O01", "Funsoft EMR");
//        //message.initQuickstart("ORM", "001", "FUNSOFT EMR");
//
//        // handle the patient PID component
//        ca.uhn.hl7v2.model.v24.group.ORM_O01_PATIENT patient = message.getPATIENT();
//        ca.uhn.hl7v2.model.v24.segment.PID pid = patient.getPID();
//        //pid.getPatientID().getIDNumber().setValue("GAN00001");
//        pid.getPatientIdentifierList(0).getID().setValue(PATIENT_ID);
//        pid.getPatientName(0).getFamilyName().getSurname().setValue(PATIENT_FAMILY_SURNAME);
//        pid.getPatientName(0).getGivenName().setValue(PATIENT_GIVEN_NAME);
//        pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(getHl7DateFormat().format(new Date()));
//        pid.getAdministrativeSex().setValue(PATIENT_GENDER);
//        // TODO: do we need patient admission ID / account number
//
////        patient.getNTE(0).insertComment(0).setValue("Radiology Order Remarks/Nursing");
////        patient.getNTE(0).getSourceOfComment().setValue("L");
////        patient.getNTE(0).getSetIDNTE().setValue("0");
////
////        patient.getNTE(1).insertComment(0).setValue("Mpre Comment/Clinician");
////        patient.getNTE(1).getSourceOfComment().setValue("P");
////        patient.getNTE(1).getSetIDNTE().setValue("1");
//        // handle patient visit component
//        ca.uhn.hl7v2.model.v24.segment.PV1 pv1 = patient.getPATIENT_VISIT().getPV1();
//        pv1.getAssignedPatientLocation().getPointOfCare().setValue(POINT_OF_CARE);
//        pv1.getAssignedPatientLocation().getPersonLocationType().setValue("Funsoft EMR");
//        pv1.getReferringDoctor(0).getIDNumber().setValue("1");
//        pv1.getReferringDoctor(0).getFamilyName().getSurname().setValue(LAB_REQUESTED_BY);
//        pv1.getReferringDoctor(0).getGivenName().setValue(LAB_REQUESTED_BY);
//
//        // handle ORC component
//        ca.uhn.hl7v2.model.v24.segment.ORC orc = message.getORDER().getORC();
//        orc.getPlacerOrderNumber().getEntityIdentifier().setValue("A00");
//        orc.getFillerOrderNumber().getEntityIdentifier().setValue(PATIENT_ID); // Accession number in Imagesuite
//        orc.getEnteredBy(0).getGivenName().setValue("");
//        orc.getOrderControl().setValue("NW");
//
////        orc.getOrderControl().setValue("CA");
////        orc.getOrderStatus().setValue("DC");
//        orc.getOrderingProvider(0).getGivenName().setValue(LAB_DEPARTMENT);
//        orc.getOrderingProvider(0).getIDNumber().setValue("1");
//        orc.getOrderingProvider(0).getFamilyName().getSurname().setValue(LAB_REQUESTED_BY);
//
//        // handle OBR component
//        ca.uhn.hl7v2.model.v24.segment.OBR obr = message.getORDER().getORDER_DETAIL().getOBR(); // http://www.mexi.be/documents/hl7/ch400024.htm  http://www.mexi.be/documents/hl7/ch700010.htm
//        obr.getUniversalServiceIdentifier().getIdentifier().setValue(LAB_SERVICE_CODE);
//        obr.getUniversalServiceIdentifier().getText().setValue(PATIENT_LAB_SERVICE);
//        //obr.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue("");
//        obr.getProcedureCode().getIdentifier().setValue(LAB_SERVICE_CODE);
//
////        obr.getFillerOrderNumber().getEntityIdentifier().setValue("ORNO1");
//        // note that we are just sending modality here, not the device location
//        obr.getPlacerField2().setValue("CR");
//        obr.getQuantityTiming(0).getPriority().setValue("STAT");
//        obr.getScheduledDateTime().getTimeOfAnEvent().setValue(HL7Utils.getHl7DateFormat().format(new Date()));
//        obr.getCollectorIdentifier(0).getIDNumber().setValue(PATIENT_ID);
//        // break the reason for study up by lines
//        obr.getReasonForStudy(0).getText().setValue(LAB_REASON);
//        obr.getReasonForStudy(1).getText().setValue(LAB_REMARKS);
//
//        obr.getCollectorSComment(0).getText().setValue(LAB_OWN_REMARKS);
//
//        parser = context.getPipeParser();
//        String encodedMessage = parser.encode(message);
//        //// testHL7MessageToTransmit.append(START_OF_BLOCK)
//        //////         .append(encodedMessage)
//        //        .append(CARRIAGE_RETURN)
//        /////        .append(END_OF_BLOCK);
//        //        .append(CARRIAGE_RETURN);
//        System.out.println("Printing ER7 Encoded Message:");
//        System.out.println(encodedMessage);
//        System.out.println("Printing MLLP Encoded Message: " + new String(testHL7MessageToTransmit.toString().getBytes()));
//        // writeToFile(testHL7MessageToTransmit.toString(), "/root/ORMMessage.er7");
//        writeToFile(encodedMessage.toString(), "/root/ORMMessage.er7");
//        // testHL7MessageToTransmit.toString();
//        InputStream in = socket.getInputStream();
//        OutputStream out = socket.getOutputStream();
//        // Send the MLLP-wrapped HL7 message to the server
//        out.write(encodedMessage.toString().getBytes());
//
//        byte[] byteBuffer = new byte[200];
//        in.read(byteBuffer);
//
//        System.out.println("Received from Server: " + new String(byteBuffer));
//        // Close the socket and its streams
//        socket.close();
//        //  return testHL7MessageToTransmit.toString();
//
//    }
    public static Message editRadiologyOrderMessage(ORM_O01 message) throws DataTypeException {
        ca.uhn.hl7v2.model.v24.segment.ORC orc = message.getORDER().getORC();
        orc.getOrderControl().setValue("XO");

        return message;
    }

    public static Message changeStatusRadiologyOrderMessage(ORM_O01 message) throws DataTypeException {
        ca.uhn.hl7v2.model.v24.segment.ORC orc = message.getORDER().getORC(); //http://www.mexi.be/documents/hl7/ch400009.htm
        orc.getOrderControl().setValue("SC");
        orc.getOrderStatus().setValue("CM"); //CM Order is complete
        return message;
    }

    public static Message cancelRadiologyOrderMessage(ORM_O01 message) throws DataTypeException {
        ca.uhn.hl7v2.model.v24.segment.ORC orc = message.getORDER().getORC();
        orc.getOrderControl().setValue("CA");
        return message;
    }

}
