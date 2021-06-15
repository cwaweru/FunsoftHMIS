/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//

/**
 *
 * @author Charles Waweru <<cwaweru@systempartners.biz>>
 *
 */
public class PatientFile {

    private static java.sql.Connection connectDB = com.afrisoftech.hospital.HospitalMain.connectDB;
    private static String patientNumber = null;
    private static String surName = null;
    private static String firstName = null;
    private static String otherName = null;
    private static String patientFullName = null;
    private static String patientResidence = null;
    private static String patientVisitID = null;
    private static java.util.Date patientDateofBith = null;
    private static String patientAdmissionNumber = null;
    private static String patientAdmissionDate = null;
    private static String patientDischargeDate = null;
    private static String patientPaymentMode = null;
    private static double billAmount = 0.00;
    private static String schemeInsurer = null;
    private static String wardName = null;
    private static String visitNumber = null;
    private static java.util.Vector diagnosticsInfo = null;
    private static java.util.Vector medicationInfo = null;
    private static int patientAge = 0;
    private static String maritalStatus = null;
    private static String patientOccupation = null;
    private static boolean patientNHIFStatus = false;
    private static String patientGender = null;
    private static String countyofBirth = null;
    private static String admissionOutcome = null;
    private static String specialtyClinic = null;
    private static String caseNumber = null;
    private static String hivStatus = null;
    private static String hivCounselled = null;
    private static String sourceofInformation = null;
    private static String patientCategory = null;
    private static String patientNationality = null;
    private static String religionGrouping = null;
    private static String educationLebvel = null;
    private static String referredFrom = null;
    private static String refrredTo = null;
    private static String nationalIDNumber = null;
    private static String passportNumber = null;
    private static java.util.Vector knownAllergies = null;
    private static java.util.Vector treatedDiseases = null;
    private static java.util.Vector theatreOperations = null;
    private static java.util.Date dateofLMP = null;
    private static double bloodPressureDiastolic = 0;
    private static double bloodPressureSystolic = 0;
    private static double bodyWeight = 0;
    private static double bodyHeight = 0;
    private static double bmisStatus = 0;
    private static String bloodGroup = null;
    private static String rhesusFactor = null;
    private static double bodyTemparature = 0;
    private static double bloodGlucose = 0;
    private static double painScale = 0;
    private static double apgarScale = 0;
    private static boolean isPregnant = false;
    private static String telephoneNumber = null;
    private static String nokName = null;
    private static String nokResidence = null;
    private static String nokTelephone = null;
    private static String emailAddress = null;
    private static String nokEmailAddress = null;
    private static String bedNumber = null;
    private static String wardWingFirm = null;
    private static String patientStatus = null;

    /**
     * @return the patientNumber
     */
    public static String getPatientNumber() {
        return patientNumber;
    }

    /**
     * @param aPatientNumber the patientNumber to set
     */
    public static void setPatientNumber(String aPatientNumber) {
        patientNumber = aPatientNumber;
    }

    /**
     * @return the surName
     */
    public static String getSurName() {
        return surName;
    }

    /**
     * @param aSurName the surName to set
     */
    public static void setSurName(String aSurName) {
        surName = aSurName;
    }

    /**
     * @return the firstName
     */
    public static String getFirstName() {
        return firstName;
    }

    /**
     * @param aFirstName the firstName to set
     */
    public static void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * @return the otherName
     */
    public static String getOtherName() {
        return otherName;
    }

    /**
     * @param aOtherName the otherName to set
     */
    public static void setOtherName(String aOtherName) {
        otherName = aOtherName;
    }

    /**
     * @return the patientFullName
     */
    public static String getPatientFullName() {
        return patientFullName;
    }

    /**
     * @param aPatientFullName the patientFullName to set
     */
    public static void setPatientFullName(String aPatientFullName) {
        patientFullName = aPatientFullName;
    }

    /**
     * @return the patientResidence
     */
    public static String getPatientResidence() {
        return patientResidence;
    }

    /**
     * @param aPatientResidence the patientResidence to set
     */
    public static void setPatientResidence(String aPatientResidence) {
        patientResidence = aPatientResidence;
    }

    /**
     * @return the patientVisitID
     */
    public static String getPatientVisitID() {
        return patientVisitID;
    }

    /**
     * @param aPatientVisitID the patientVisitID to set
     */
    public static void setPatientVisitID(String aPatientVisitID) {
        patientVisitID = aPatientVisitID;
    }

    /**
     * @return the patientDateofBith
     */
    public static java.util.Date getPatientDateofBith() {
        return patientDateofBith;
    }

    /**
     * @param aPatientDateofBith the patientDateofBith to set
     */
    public static void setPatientDateofBith(java.util.Date aPatientDateofBith) {
        patientDateofBith = aPatientDateofBith;
    }

    /**
     * @return the patientAdmissionNumber
     */
    public static String getPatientAdmissionNumber() {
        return patientAdmissionNumber;
    }

    /**
     * @param aPatientAdmissionNumber the patientAdmissionNumber to set
     */
    public static void setPatientAdmissionNumber(String aPatientAdmissionNumber) {
        patientAdmissionNumber = aPatientAdmissionNumber;
    }

    /**
     * @return the patientDischargeDate
     */
    public static String getPatientDischargeDate() {
        return patientDischargeDate;
    }

    /**
     * @param aPatientDischargeDate the patientDischargeDate to set
     */
    public static void setPatientDischargeDate(String aPatientDischargeDate) {
        patientDischargeDate = aPatientDischargeDate;
    }

    /**
     * @return the patientPaymentMode
     */
    public static String getPatientPaymentMode() {
        return patientPaymentMode;
    }

    /**
     * @param aPatientPaymentMode the patientPaymentMode to set
     */
    public static void setPatientPaymentMode(String aPatientPaymentMode) {
        patientPaymentMode = aPatientPaymentMode;
    }

    /**
     * @return the billAmount
     */
    public static double getBillAmount() {
        return billAmount;
    }

    /**
     * @param aBillAmount the billAmount to set
     */
    public static void setBillAmount(double aBillAmount) {
        billAmount = aBillAmount;
    }

    /**
     * @return the schemeInsurer
     */
    public static String getSchemeInsurer() {
        return schemeInsurer;
    }

    /**
     * @param aSchemeInsurer the schemeInsurer to set
     */
    public static void setSchemeInsurer(String aSchemeInsurer) {
        schemeInsurer = aSchemeInsurer;
    }

    /**
     * @return the wardName
     */
    public static String getWardName() {
        return wardName;
    }

    /**
     * @param aWardName the wardName to set
     */
    public static void setWardName(String aWardName) {
        wardName = aWardName;
    }

    /**
     * @return the visitNumber
     */
    public static String getVisitNumber() {
        return visitNumber;
    }

    /**
     * @param aVisitNumber the visitNumber to set
     */
    public static void setVisitNumber(String aVisitNumber) {
        visitNumber = aVisitNumber;
    }

    /**
     * @return the diagnosticsInfo
     */
    public static java.util.Vector getDiagnosticsInfo() {
        return diagnosticsInfo;
    }

    /**
     * @param aDiagnosticsInfo the diagnosticsInfo to set
     */
    public static void setDiagnosticsInfo(java.util.Vector aDiagnosticsInfo) {
        diagnosticsInfo = aDiagnosticsInfo;
    }

    /**
     * @return the medicationInfo
     */
    public static java.util.Vector getMedicationInfo() {
        return medicationInfo;
    }

    /**
     * @param aMedicationInfo the medicationInfo to set
     */
    public static void setMedicationInfo(java.util.Vector aMedicationInfo) {
        medicationInfo = aMedicationInfo;
    }

    /**
     * @return the patientAge
     */
    public static int getPatientAge() {
        return patientAge;
    }

    /**
     * @param aPatientAge the patientAge to set
     */
    public static void setPatientAge(int aPatientAge) {
        patientAge = aPatientAge;
    }

    /**
     * @return the maritalStatus
     */
    public static String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param aMaritalStatus the maritalStatus to set
     */
    public static void setMaritalStatus(String aMaritalStatus) {
        maritalStatus = aMaritalStatus;
    }

    /**
     * @return the patientOccupation
     */
    public static String getPatientOccupation() {
        return patientOccupation;
    }

    /**
     * @param aPatientOccupation the patientOccupation to set
     */
    public static void setPatientOccupation(String aPatientOccupation) {
        patientOccupation = aPatientOccupation;
    }

    /**
     * @return the patientNHIFStatus
     */
    public static boolean isPatientNHIFStatus() {
        return patientNHIFStatus;
    }

    /**
     * @param aPatientNHIFStatus the patientNHIFStatus to set
     */
    public static void setPatientNHIFStatus(boolean aPatientNHIFStatus) {
        patientNHIFStatus = aPatientNHIFStatus;
    }

    /**
     * @return the patientGender
     */
    public static String getPatientGender() {
        return patientGender;
    }

    /**
     * @param aPatientGender the patientGender to set
     */
    public static void setPatientGender(String aPatientGender) {
        patientGender = aPatientGender;
    }

    /**
     * @return the countyofBirth
     */
    public static String getCountyofBirth() {
        return countyofBirth;
    }

    /**
     * @param aCountyofBirth the countyofBirth to set
     */
    public static void setCountyofBirth(String aCountyofBirth) {
        countyofBirth = aCountyofBirth;
    }

    /**
     * @return the admissionOutcome
     */
    public static String getAdmissionOutcome() {
        return admissionOutcome;
    }

    /**
     * @param aAdmissionOutcome the admissionOutcome to set
     */
    public static void setAdmissionOutcome(String aAdmissionOutcome) {
        admissionOutcome = aAdmissionOutcome;
    }

    /**
     * @return the specialtyClinic
     */
    public static String getSpecialtyClinic() {
        return specialtyClinic;
    }

    /**
     * @param aSpecialtyClinic the specialtyClinic to set
     */
    public static void setSpecialtyClinic(String aSpecialtyClinic) {
        specialtyClinic = aSpecialtyClinic;
    }

    /**
     * @return the caseNumber
     */
    public static String getCaseNumber() {
        return caseNumber;
    }

    /**
     * @param aCaseNumber the caseNumber to set
     */
    public static void setCaseNumber(String aCaseNumber) {
        caseNumber = aCaseNumber;
    }

    /**
     * @return the hivStatus
     */
    public static String getHivStatus() {
        return hivStatus;
    }

    /**
     * @param aHivStatus the hivStatus to set
     */
    public static void setHivStatus(String aHivStatus) {
        hivStatus = aHivStatus;
    }

    /**
     * @return the hivCounselled
     */
    public static String getHivCounselled() {
        return hivCounselled;
    }

    /**
     * @param aHivCounselled the hivCounselled to set
     */
    public static void setHivCounselled(String aHivCounselled) {
        hivCounselled = aHivCounselled;
    }

    /**
     * @return the sourceofInformation
     */
    public static String getSourceofInformation() {
        return sourceofInformation;
    }

    /**
     * @param aSourceofInformation the sourceofInformation to set
     */
    public static void setSourceofInformation(String aSourceofInformation) {
        sourceofInformation = aSourceofInformation;
    }

    /**
     * @return the patientCategory
     */
    public static String getPatientCategory() {
        return patientCategory;
    }

    /**
     * @param aPatientCategory the patientCategory to set
     */
    public static void setPatientCategory(String aPatientCategory) {
        patientCategory = aPatientCategory;
    }

    /**
     * @return the patientNationality
     */
    public static String getPatientNationality() {
        return patientNationality;
    }

    /**
     * @param aPatientNationality the patientNationality to set
     */
    public static void setPatientNationality(String aPatientNationality) {
        patientNationality = aPatientNationality;
    }

    /**
     * @return the religionGrouping
     */
    public static String getReligionGrouping() {
        return religionGrouping;
    }

    /**
     * @param aReligionGrouping the religionGrouping to set
     */
    public static void setReligionGrouping(String aReligionGrouping) {
        religionGrouping = aReligionGrouping;
    }

    /**
     * @return the educationLevel
     */
    public static String getEducationLevel() {
        return getEducationLebvel();
    }

    /**
     * @param aEducationLebvel the educationLebvel to set
     */
    public static void setEducationLebvel(String aEducationLevel) {
        educationLebvel = aEducationLevel;
    }

    /**
     * @return the referredFrom
     */
    public static String getReferredFrom() {
        return referredFrom;
    }

    /**
     * @param aReferredFrom the referredFrom to set
     */
    public static void setReferredFrom(String aReferredFrom) {
        referredFrom = aReferredFrom;
    }

    /**
     * @return the refrredTo
     */
    public static String getRefrredTo() {
        return refrredTo;
    }

    /**
     * @param aRefrredTo the refrredTo to set
     */
    public static void setRefrredTo(String aRefrredTo) {
        refrredTo = aRefrredTo;
    }

    /**
     * @return the nationalIDNumber
     */
    public static String getNationalIDNumber() {
        return nationalIDNumber;
    }

    /**
     * @param aNationalIDNumber the nationalIDNumber to set
     */
    public static void setNationalIDNumber(String aNationalIDNumber) {
        nationalIDNumber = aNationalIDNumber;
    }

    /**
     * @return the passportNumber
     */
    public static String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param aPassportNumber the passportNumber to set
     */
    public static void setPassportNumber(String aPassportNumber) {
        passportNumber = aPassportNumber;
    }

    /**
     * @return the knownAllergies
     */
    public static java.util.Vector getKnownAllergies() {
        return knownAllergies;
    }

    /**
     * @param aKnownAllergies the knownAllergies to set
     */
    public static void setKnownAllergies(java.util.Vector aKnownAllergies) {
        knownAllergies = aKnownAllergies;
    }

    /**
     * @return the treatedDiseases
     */
    public static java.util.Vector getTreatedDiseases() {
        return treatedDiseases;
    }

    /**
     * @param aTreatedDiseases the treatedDiseases to set
     */
    public static void setTreatedDiseases(java.util.Vector aTreatedDiseases) {
        treatedDiseases = aTreatedDiseases;
    }

    /**
     * @return the theatreOperations
     */
    public static java.util.Vector getTheatreOperations() {
        return theatreOperations;
    }

    /**
     * @param aTheatreOperations the theatreOperations to set
     */
    public static void setTheatreOperations(java.util.Vector aTheatreOperations) {
        theatreOperations = aTheatreOperations;
    }

    /**
     * @return the dateofLMP
     */
    public static java.util.Date getDateofLMP() {
        return dateofLMP;
    }

    /**
     * @param aDateofLMP the dateofLMP to set
     */
    public static void setDateofLMP(java.util.Date aDateofLMP) {
        dateofLMP = aDateofLMP;
    }

    /**
     * @return the bloodPressureDiastolic
     */
    public static double getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }

    /**
     * @param aBloodPressureDiastolic the bloodPressureDiastolic to set
     */
    public static void setBloodPressureDiastolic(double aBloodPressureDiastolic) {
        bloodPressureDiastolic = aBloodPressureDiastolic;
    }

    /**
     * @return the bloodPressureSystolic
     */
    public static double getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    /**
     * @param aBloodPressureSystolic the bloodPressureSystolic to set
     */
    public static void setBloodPressureSystolic(double aBloodPressureSystolic) {
        bloodPressureSystolic = aBloodPressureSystolic;
    }

    /**
     * @return the bodyWeight
     */
    public static double getBodyWeight() {
        return bodyWeight;
    }

    /**
     * @param aBodyWeight the bodyWeight to set
     */
    public static void setBodyWeight(double aBodyWeight) {
        bodyWeight = aBodyWeight;
    }

    /**
     * @return the bodyHeight
     */
    public static double getBodyHeight() {
        return bodyHeight;
    }

    /**
     * @param aBodyHeight the bodyHeight to set
     */
    public static void setBodyHeight(double aBodyHeight) {
        bodyHeight = aBodyHeight;
    }

    /**
     * @return the bmisStatus
     */
    public static double getBmisStatus() {
        return bmisStatus;
    }

    /**
     * @param aBmisStatus the bmisStatus to set
     */
    public static void setBmisStatus(double aBmisStatus) {
        bmisStatus = aBmisStatus;
    }

    /**
     * @return the bloodGroup
     */
    public static String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param aBloodGroup the bloodGroup to set
     */
    public static void setBloodGroup(String aBloodGroup) {
        bloodGroup = aBloodGroup;
    }

    /**
     * @return the rhesusFactor
     */
    public static String getRhesusFactor() {
        return rhesusFactor;
    }

    /**
     * @param aRhesusFactor the rhesusFactor to set
     */
    public static void setRhesusFactor(String aRhesusFactor) {
        rhesusFactor = aRhesusFactor;
    }

    /**
     * @return the bodyTemparature
     */
    public static double getBodyTemparature() {
        return bodyTemparature;
    }

    /**
     * @param aBodyTemparature the bodyTemparature to set
     */
    public static void setBodyTemparature(double aBodyTemparature) {
        bodyTemparature = aBodyTemparature;
    }

    /**
     * @return the bloodGlucose
     */
    public static double getBloodGlucose() {
        return bloodGlucose;
    }

    /**
     * @param aBloodGlucose the bloodGlucose to set
     */
    public static void setBloodGlucose(double aBloodGlucose) {
        bloodGlucose = aBloodGlucose;
    }

    /**
     * @return the painScale
     */
    public static double getPainScale() {
        return painScale;
    }

    /**
     * @param aPainScale the painScale to set
     */
    public static void setPainScale(double aPainScale) {
        painScale = aPainScale;
    }

    /**
     * @return the apgarScale
     */
    public static double getApgarScale() {
        return apgarScale;
    }

    /**
     * @param aApgarScale the apgarScale to set
     */
    public static void setApgarScale(double aApgarScale) {
        apgarScale = aApgarScale;
    }

    /**
     * @return the isPregnant
     */
    public static boolean isIsPregnant() {
        return isPregnant;
    }

    /**
     * @param aIsPregnant the isPregnant to set
     */
    public static void setIsPregnant(boolean aIsPregnant) {
        isPregnant = aIsPregnant;
    }

    /**
     * @return the telephoneNumber
     */
    public static String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param aTelephoneNumber the telephoneNumber to set
     */
    public static void setTelephoneNumber(String aTelephoneNumber) {
        telephoneNumber = aTelephoneNumber;
    }

    /**
     * @return the nokName
     */
    public static String getNokName() {
        return nokName;
    }

    /**
     * @param aNokName the nokName to set
     */
    public static void setNokName(String aNokName) {
        nokName = aNokName;
    }

    /**
     * @return the nokResidence
     */
    public static String getNokResidence() {
        return nokResidence;
    }

    /**
     * @param aNokResidence the nokResidence to set
     */
    public static void setNokResidence(String aNokResidence) {
        nokResidence = aNokResidence;
    }

    /**
     * @return the nokTelephone
     */
    public static String getNokTelephone() {
        return nokTelephone;
    }

    /**
     * @param aNokTelephone the nokTelephone to set
     */
    public static void setNokTelephone(String aNokTelephone) {
        nokTelephone = aNokTelephone;
    }

    /**
     * @return the emailAddress
     */
    public static String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param aEmailAddress the emailAddress to set
     */
    public static void setEmailAddress(String aEmailAddress) {
        emailAddress = aEmailAddress;
    }

    /**
     * @return the nokEmailAddress
     */
    public static String getNokEmailAddress() {
        return nokEmailAddress;
    }

    /**
     * @param aNokEmailAddress the nokEmailAddress to set
     */
    public static void setNokEmailAddress(String aNokEmailAddress) {
        nokEmailAddress = aNokEmailAddress;
    }

    /**
     * @return the bedNumber
     */
    public static String getBedNumber() {
        return bedNumber;
    }

    /**
     * @param aBedNumber the bedNumber to set
     */
    public static void setBedNumber(String aBedNumber) {
        bedNumber = aBedNumber;
    }

    /**
     * @return the wardWingFirm
     */
    public static String getWardWingFirm() {
        return wardWingFirm;
    }

    /**
     * @param aWardWingFirm the wardWingFirm to set
     */
    public static void setWardWingFirm(String aWardWingFirm) {
        wardWingFirm = aWardWingFirm;
    }

    /**
     * @return the patientStatus
     */
    public static String getPatientStatus() {
        return patientStatus;
    }

    /**
     * @param aPatientStatus the patientStatus to set
     */
    public static void setPatientStatus(String aPatientStatus) {
        patientStatus = aPatientStatus;
    }

    /**
     * @return the connectDB
     */
    public static java.sql.Connection getConnectDB() {
        return connectDB;
    }

    /**
     * @param aConnectDB the connectDB to set
     */
    public static void setConnectDB(java.sql.Connection aConnectDB) {
        connectDB = aConnectDB;
    }

    /**
     * @return the educationLebvel
     */
    public static String getEducationLebvel() {
        return educationLebvel;
    }

    /**
     * @return the patientAdmissionDate
     */
    public static String getPatientAdmissionDate() {
        return patientAdmissionDate;
    }

    /**
     * @param aPatientAdmissionDate the patientAdmissionDate to set
     */
    public static void setPatientAdmissionDate(String aPatientAdmissionDate) {
        patientAdmissionDate = aPatientAdmissionDate;
    }

    static public void setPatientFileData(String patientNo, String patientType) {

        try {
            if (patientType == "IP") {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT patient_name as patient_names,"
                        + " date_admitted, visit_id,"
                        + "(SELECT year_of_birth::date FROM hp_inpatient_register WHERE hp_inpatient_register.patient_no = ? ORDER BY 1 DESC LIMIT 1) as date_of_birth"
                        + " FROM hp_admission WHERE patient_no = ?");
                pstmt.setString(1, patientNo);
                pstmt.setString(2, patientNo);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    setPatientFullName(rset.getString(1));

                    setPatientAdmissionDate(new com.afrisoftech.lib.DBObject().getDBObject(rset.getString(2), "-"));

                    setVisitNumber(new com.afrisoftech.lib.DBObject().getDBObject(rset.getString(3), "3"));

                    setPatientDateofBith(rset.getDate(4));

                }

            } else {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT name as patient_names,"
                        + " date, '',"
                        + "(SELECT year_of_birth::date FROM hp_patient_register WHERE hp_patient_register.patient_no = ? ORDER BY 1 DESC LIMIT 1) as date_of_birth"
                        + " FROM hp_patient_visit WHERE patient_no = ?");
                pstmt.setString(1, patientNo);
                pstmt.setString(2, patientNo);
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {

                    setPatientFullName(rset.getString(1));

                    setPatientAdmissionDate(new com.afrisoftech.lib.DBObject().getDBObject(rset.getString(2), "-"));

                    setVisitNumber(new com.afrisoftech.lib.DBObject().getDBObject(rset.getString(3), "3"));

                    setPatientDateofBith(rset.getDate(4));

                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
            ex.printStackTrace();
        }

    }
}
