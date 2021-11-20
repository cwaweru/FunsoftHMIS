/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib.biometric;

import SecuGen.FDxSDKPro.jni.JSGFPLib;
import SecuGen.FDxSDKPro.jni.SGDeviceInfoParam;
import SecuGen.FDxSDKPro.jni.SGFDxDeviceName;
import SecuGen.FDxSDKPro.jni.SGFDxErrorCode;
import SecuGen.FDxSDKPro.jni.SGFDxSecurityLevel;
import SecuGen.FDxSDKPro.jni.SGFingerInfo;
import SecuGen.FDxSDKPro.jni.SGFingerPosition;
import SecuGen.FDxSDKPro.jni.SGImpressionType;
import SecuGen.FDxSDKPro.jni.SGPPPortAddr;
import static SecuGen.FDxSDKPro.jni.SGPPPortAddr.USB_AUTO_DETECT;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.sql.SQLException;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class SecuGen {

    private static long ret;

    public void initializeDevice() {

    }
public byte[] registerFingerPrint(java.sql.Connection connectDB, String clientID) {
        byte[] regMin1 = new byte[400];
        byte[] regMin2 = new byte[400];
        byte[] vrfMin = new byte[400];
        long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

        BufferedImage imgRegistration1;
        BufferedImage imgRegistration2;
        BufferedImage imgVerification;

        byte[] imageBuffer1 = null;
        byte[] imageBuffer2 = null;
        byte[] imageBufferVerification = null;

        int[] quality = new int[1];
        int[] numOfMinutiae = new int[1];

        JSGFPLib sgfplib = new JSGFPLib();//(UsbManager)getSystemService(Context.USB_SERVICE));

        SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();

        sgfplib.SetLedOn(true);

        ret = sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);

        if ((sgfplib != null) && (ret == SGFDxErrorCode.SGFDX_ERROR_NONE)) {

            System.out.println("Device initialization success ...");

            ret = sgfplib.OpenDevice(SGPPPortAddr.USB_AUTO_DETECT);
            
            System.out.println("OpenDevice() Success for address : [" + ret + "]");

            if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                System.out.println("OpenDevice() Success [" + ret + "]");
                ret = sgfplib.GetDeviceInfo(deviceInfo);
                if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                    imgRegistration1 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgRegistration2 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgVerification = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);

                    imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration1.getRaster().getDataBuffer()).getData();
                    iError = sgfplib.GetImageEx(imageBuffer1, 80 * 1000, 0, 80);
                    com.afrisoftech.hospital.HospitalMain.fingerPrintDialog.setSize(150, 240);
                    com.afrisoftech.hospital.HospitalMain.fingerPrintDialog.setVisible(true);
                    com.afrisoftech.hospital.HospitalMain.fingerprintIconLabel.setIcon(new ImageIcon(imgRegistration1.getScaledInstance(140, 240, Image.SCALE_DEFAULT)));
                    //    com.afrisoftech.hospinventory.SuppliesSectionsIntfr.fingerprintImageLbl.setIcon(new ImageIcon(imgRegistration1.getScaledInstance(140, 240, Image.SCALE_DEFAULT)));

                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Fingerprint reader device initialisation failed! Please consult system administrator.");
        }

        return imageBuffer1;

    }
    
    

    public static boolean verifyFingerprints(java.sql.Connection connectDB, String clientID) {
        boolean verified = false;
        byte[] regMin1 = new byte[400];
        byte[] regMin2 = new byte[400];
        byte[] vrfMin = new byte[400];
        long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

        BufferedImage imgRegistration1;
        BufferedImage imgRegistration2;
        BufferedImage imgVerification;

        byte[] imageBuffer1 = null;
        byte[] imageBuffer2 = null;
        byte[] imageBufferVerification = null;

        int[] quality = new int[1];
        int[] numOfMinutiae = new int[1];

        JSGFPLib sgfplib = new JSGFPLib();//(UsbManager)getSystemService(Context.USB_SERVICE));

        SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();

        sgfplib.SetLedOn(true);

        ret = sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);

        if ((sgfplib != null) && (ret == SGFDxErrorCode.SGFDX_ERROR_NONE)) {

            System.out.println("Device initialization success ...");

            ret = sgfplib.OpenDevice(SGPPPortAddr.AUTO_DETECT);

            if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                System.out.println("OpenDevice() Success [" + ret + "]");
                ret = sgfplib.GetDeviceInfo(deviceInfo);
                if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                    imgRegistration1 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgRegistration2 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgVerification = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);

                    imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration1.getRaster().getDataBuffer()).getData();
                    iError = sgfplib.GetImageEx(imageBuffer1, 80 * 1000, 0, 80);
                    com.afrisoftech.hospital.PatientRegisterIntfr.fingerPrintDialog.setSize(150, 240);
                    com.afrisoftech.hospital.PatientRegisterIntfr.fingerPrintDialog.setVisible(true);
                    com.afrisoftech.hospital.PatientRegisterIntfr.fingerprintIconLabel.setIcon(new ImageIcon(imgRegistration1.getScaledInstance(140, 240, Image.SCALE_DEFAULT)));

                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Fingerprint reader device initialisation failed! Please consult system administrator.");
        }

        SGFingerInfo fingerInfo = new SGFingerInfo();
        fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
        fingerInfo.ImageQuality = quality[0];
        fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
        fingerInfo.ViewNumber = 1;
        iError = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, regMin1);
        if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
            imageBuffer2 = com.afrisoftech.lib.SaveBytea2DB.getImageByteArray(connectDB, clientID);

            long secuLevel = 80;//(long) (this.jComboBoxVerifySecurityLevel.getSelectedIndex() + 1);

            boolean[] matched = new boolean[1];

            matched[0] = false;

            iError = sgfplib.CreateTemplate(fingerInfo, imageBuffer2, regMin2);

            if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                iError = sgfplib.MatchTemplate(regMin1, regMin2, secuLevel, matched);

                if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                    if (matched[0]) {

                        verified = true;
                        
                        System.out.println("Verification success!");
                        
                    } else {
                        System.out.println("Verification failed!");
                    }

                }

            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Error in veryfying fingerprints. Please try once more.");
        }
        return verified;
    }
    
    public String verifyFingerRecord(java.sql.Connection connectDB, String docType) {

        String verified = null;
        byte[] regMin1 = new byte[400];
        byte[] regMin2 = new byte[400];
        byte[] vrfMin = new byte[400];
        long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

        BufferedImage imgRegistration1;
        BufferedImage imgRegistration2;
        BufferedImage imgVerification;

        byte[] imageBuffer1 = null;
        byte[] imageBuffer2 = null;
        byte[] imageBufferVerification = null;

        int[] quality = new int[1];
        int[] numOfMinutiae = new int[1];

        JSGFPLib sgfplib = new JSGFPLib();//(UsbManager)getSystemService(Context.USB_SERVICE));

        SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();

        sgfplib.SetLedOn(true);

        ret = sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);

        if ((sgfplib != null) && (ret == SGFDxErrorCode.SGFDX_ERROR_NONE)) {

            System.out.println("Device initialization success ...");

            ret = sgfplib.OpenDevice(SGPPPortAddr.USB_AUTO_DETECT);
            
            System.out.println("OpenDevice() Success device ID : [" + ret + "]");

            if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                System.out.println("OpenDevice() Success [" + ret + "]");
                ret = sgfplib.GetDeviceInfo(deviceInfo);
                if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                    imgRegistration1 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgRegistration2 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
                    imgVerification = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);

                    imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration1.getRaster().getDataBuffer()).getData();
                    iError = sgfplib.GetImageEx(imageBuffer1, 80 * 1000, 0, 80);
//                    com.afrisoftech.hospital.PatientRegisterIntfr.fingerPrintDialog.setSize(150, 240);
//                    com.afrisoftech.hospital.PatientRegisterIntfr.fingerPrintDialog.setVisible(true);
//                    com.afrisoftech.hospinventory.SuppliesSectionsIntfr.fingerprintImageLbl.setIcon(new ImageIcon(imgRegistration1.getScaledInstance(140, 240, Image.SCALE_DEFAULT)));

                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Fingerprint reader device initialisation failed! Please consult system administrator.");
        }

        SGFingerInfo fingerInfo = new SGFingerInfo();
        fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
        fingerInfo.ImageQuality = quality[0];
        fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
        fingerInfo.ViewNumber = 1;

        iError = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, regMin1);

        System.out.println("Created first template 1 and minutae 1 ....");

        if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

            try {
                
                String documentID = null;

                java.sql.PreparedStatement pstmtGetFile = connectDB.prepareStatement("SELECT document_ref_no FROM funsoft_image_graphics WHERE UPPER(document_source) = ? AND document_ref_no IS NOT NULL");

                pstmtGetFile.setString(1, docType.toUpperCase());

                java.sql.ResultSet rsetGetFile = pstmtGetFile.executeQuery();

                while (rsetGetFile.next()) {

                    documentID = rsetGetFile.getString(1);

                    imageBuffer2 = com.afrisoftech.lib.SaveBytea2DB.getFingerprintImageByteArray(connectDB, rsetGetFile.getString(1));
                    //imageBuffer2 = com.afrisoftech.lib.SaveBytea2DB.getImageByteArray(connectDB, docType);

                    System.out.println("Retrieved fingerprint image from database ...");

                    long secuLevel = SGFDxSecurityLevel.SL_NORMAL;//(long) (this.jComboBoxVerifySecurityLevel.getSelectedIndex() + 1);

                    boolean[] matched = new boolean[1];

                    matched[0] = false;

                    iError = sgfplib.CreateTemplate(fingerInfo, imageBuffer2, regMin2);

                    System.out.println("Registered stored minutae 2 from database ...ERROR [" + iError + "]");

                    if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                        iError = sgfplib.MatchTemplate(regMin1, regMin2, SGFDxSecurityLevel.SL_NORMAL, matched);
                        // iError = sgfplib.MatchTemplate(regMin1, regMin2, secuLevel, matched);

                        for (int k = 0; k < matched.length; k++) {
                            System.out.println("Matching status for [" + k + "] is : [" + matched[k] + "] ... ERROR [" + iError + "]");
                        }

                        if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

                            if (matched[0]) {

                                verified = documentID;

                                System.out.println("Verification success!");
                                
                                com.afrisoftech.lib.PatientFile.setPatientNumber(documentID);
                                
                              //  com.afrisoftech.
                                
                                javax.swing.JOptionPane.showMessageDialog(null, "Verification successful!");// for patient number : ["+verified+"] and name : "+com.afrisoftech.lib.PatientFile.getPatientFullName()+"]");
                                
                                
                                
                                break;

                            } else {
                                System.out.println("Verification failed!");
                                //javax.swing.JOptionPane.showMessageDialog(null, "Verification failed, please try again");
                            }

                        }

                    }

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else {
            System.out.println("Verification failed!");
            javax.swing.JOptionPane.showMessageDialog(null, "Verification failed, please try again");
        }

        System.out.println("Record returned is : ["+verified+"]");
        return verified;
    }

}
