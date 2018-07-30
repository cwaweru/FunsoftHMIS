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
import SecuGen.FDxSDKPro.jni.SGFingerInfo;
import SecuGen.FDxSDKPro.jni.SGFingerPosition;
import SecuGen.FDxSDKPro.jni.SGImpressionType;
import SecuGen.FDxSDKPro.jni.SGPPPortAddr;
import static SecuGen.FDxSDKPro.jni.SGPPPortAddr.USB_AUTO_DETECT;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class SecuGen {

    private static long ret;

    public void initializeDevice() {

    }

    public void registerFingerPrint(java.sql.Connection connectDB, String clientID) {
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

}
