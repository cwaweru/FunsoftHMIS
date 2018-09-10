package com.afrisoftech.lib;

/*
 * BeanTest.java
 *
 * Created on August 21, 2003, 9:20 PM
 */
import java.beans.*;
import java.sql.SQLException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
//public class DatePicker extends com.qt.datapicker.DatePicker implements Runnable {
//public class DatePicker extends com.alee.extended.date. implements java.io.Serializable, Runnable {
public class DatePicker extends com.toedter.calendar.JDateChooser implements java.io.Serializable, Runnable {
//public class DatePicker extends kiwi.ui.DateChooserField implements java.io.Serializable, Runnable {
    
    private static final String PROP_SAMPLE_PROPERTY = "SampleProperty";
    private String sampleProperty;
    private PropertyChangeSupport propertySupport;
    java.sql.Connection dbConnection = null;
    Thread datePickerThread = new Thread(this);

//    public DatePicker(Observer observer) {
//        super(observer);
//        //        super("dd MMM yyyy");
//        java.util.Calendar cal = java.util.Calendar.getInstance();
//        java.util.Date todayDate = cal.getTime();
//      //  this.setDate(todayDate);
//        propertySupport = new PropertyChangeSupport(this);
        
//        datePickerThread.start();
//    }

    /**
     * Creates new BeanTest
     */
    public DatePicker() {
//        super("dd MMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date todayDate = cal.getTime();
        this.setDate(todayDate);
        propertySupport = new PropertyChangeSupport(this);
        dbConnection = com.afrisoftech.hospital.HospitalMain.connectDB;
       // datePickerThread.start();
    }

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    @Override
    public void run() {

        while (true) {

            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.util.Date todayDate = cal.getTime();
            java.sql.Statement stmt;
            try {
                dbConnection = com.afrisoftech.hospital.HospitalMain.connectDB;
                stmt = dbConnection.createStatement();
                java.sql.ResultSet rset = stmt.executeQuery("SELECT current_date");
                while (rset.next()) {
                    todayDate = rset.getDate(1);
                }
                rset.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatePicker.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.setDate(todayDate);
            propertySupport = new PropertyChangeSupport(this);
            try {
                Thread.currentThread().sleep(500000000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Logger.getLogger(DatePicker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }




        // throw new UnsupportedOperationException("Not supported yet.");
    }
}
