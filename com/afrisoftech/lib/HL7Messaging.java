/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import java.io.IOException;
import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class HL7Messaging {

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

    public static ADT_A01 generateHL7Message(java.sql.Connection connectDB, String patientNo) {
        ADT_A01 adt = new ADT_A01();
        try {
            adt.initQuickstart("ADT", "A01", "P");
            // Populate the MSH Segment
            MSH mshSegment = adt.getMSH();
            mshSegment.getSendingApplication().getNamespaceID().setValue("Funsoft I-HMIS HL7 Messaging System");
            mshSegment.getSequenceNumber().setValue(getHL7SequenceNumber(connectDB));
            // mshSegment.getSendingFacility()
            // Populate the PID Segment
            PID pid = adt.getPID();
            pid.getPatientName(0).getFamilyName().getSurname().setValue(getPATIENT_FAMILY_SURNAME());
            pid.getPatientName(0).getGivenName().setValue(getPATIENT_GIVEN_NAME());
            pid.getPatientIdentifierList(0).getID().setValue(getPATIENT_ID());
            pid.getCitizenship(0).getCe1_Identifier().setValue(getPATIENT_CITIZENSHIP());

            //adt.insertAL1(PATIENT_AGE).
            // Now, let's encode the message and look at the output
            HapiContext context = new DefaultHapiContext();
            Parser parser = context.getPipeParser();
            String encodedMessage = parser.encode(adt);
            System.out.println("Printing ER7 Encoded Message:");
            System.out.println(encodedMessage);
            // Next, let's use the XML parser to encode as XML
            parser = context.getXMLParser();
            encodedMessage = parser.encode(adt);
            System.out.println("Printing XML Encoded Message:");
            System.out.println(encodedMessage);

        } catch (HL7Exception ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        return adt;

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
            Exceptions.printStackTrace(ex);
        }

        return (String.valueOf(hl7SequenceID));

    }

    /**
     * @return the PATIENT_CITIZENSHIP
     */
    public static String getPATIENT_CITIZENSHIP() {
        return PATIENT_CITIZENSHIP;
    }

    /**
     * @param aPATIENT_CITIZENSHIP the PATIENT_CITIZENSHIP to set
     */
    public static void setPATIENT_CITIZENSHIP(String aPATIENT_CITIZENSHIP) {
        PATIENT_CITIZENSHIP = aPATIENT_CITIZENSHIP;
    }
}
