/*
 * GenerateLicence.java
 *
 * Created on March 23, 2006, 12:44 PM
 */
package com.afrisoftech.lic;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class GenerateLicence {

    protected static java.util.Date licenceDate;

    protected static java.lang.String oAuthKey;

    protected static java.lang.String shortCode;

    protected static java.lang.String passKey;

    protected static java.lang.String callBackURL;

    /**
     * Creates a new instance of GenerateLicence
     */
    public GenerateLicence() {

        com.afrisoftech.lic.LicDateDialog licDateDialog = new com.afrisoftech.lic.LicDateDialog(new java.awt.Frame(), true);

        licDateDialog.setVisible(true);

        makeLicenceFile(licenceDate, oAuthKey, passKey, callBackURL);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new GenerateLicence();

    }

    private static void makeLicenceFile(java.util.Date licDate, java.lang.String oAuthKeys, java.lang.String passKeys, java.lang.String callBackURLs) {

        try {

            java.io.File licFile = new java.io.File(new java.io.File(System.getProperty("user.dir")), "licencefile.spl");

            licFile.delete();

            licFile.createNewFile();

            java.io.FileOutputStream licOutputStream = new java.io.FileOutputStream(licFile);

            java.io.ObjectOutputStream licObjectputStream = new java.io.ObjectOutputStream(licOutputStream);

            java.util.Vector licVector = new java.util.Vector<Object>(1,1);
            
            licVector.addElement(licDate);
            
            licVector.addElement(oAuthKeys);
            
            licVector.addElement(passKeys);
            
            licVector.addElement(callBackURLs);
            
            licObjectputStream.writeObject(licVector);
//            
//            licObjectputStream.writeObject(oAuthKeys);
//            
//            licObjectputStream.writeObject(passKeys);
//            
//            licObjectputStream.writeObject(callBackURLs);

        } catch (java.io.IOException ioEx) {

            ioEx.printStackTrace();

        }

    }

}
