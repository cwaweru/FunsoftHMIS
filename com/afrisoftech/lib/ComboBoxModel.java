/*
 * ComboBoxModel.java
 *
 * version .1.1a
 *
 * This class provides a data model for a JCombomBox.
 *
 * Created on February 24, 2006, 8:38 AM
 */
package com.afrisoftech.lib;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 * Copyright System Partners Limited 2001 - 2013. All rights reserved.
 *
 * Copying this class is hereby granted as long as this and the above paragraph
 * are included in the distribution and the source code as is made freely
 * available to anyone wanting to use the code.
 *
 */
public class ComboBoxModel {

    /**
     * Creates a new instance of ComboBoxModel
     *
     * Contructs a class for the JComboBox component.
     *
     */
    public ComboBoxModel() {
    }

    /**
     *
     * Contructs a class for the JComboBox component. This memthod statically
     * collect data from the database.
     *
     * @param connectDB : The database connection referenced from the calling
     * class.
     *
     * @param comboDBDataQuery : The SQL string conveying the query to be sent
     * to the database to return the combo box data.
     *
     * It return the javax.swing.DefaultComboBoxModel to the calling JComboBox.
     */
    public static javax.swing.DefaultComboBoxModel ComboBoxModel(java.sql.Connection connectDB, java.lang.String comboDBDataQuery) {

        javax.swing.DefaultComboBoxModel comboBoxModel = new javax.swing.DefaultComboBoxModel(submitQuery(connectDB, comboDBDataQuery));

        return comboBoxModel;

    }

    /**
     * The submitQuery method is responsible for buliding up the resultset as a
     * vector that is returned to the ComboBoxModel() method.
     *
     * @param dbConnection: Database connection.
     *
     * @param comboDBDataQuery: SQL query string.
     *
     */
    private static java.util.Vector submitQuery(java.sql.Connection dbConnection, java.lang.String comboDBDataQuery) {

        java.util.Vector comboDataVector = new java.util.Vector(1, 1); // construct a place holder vector for the data model

        try {

            java.sql.PreparedStatement pstmtComboData = dbConnection.prepareStatement(comboDBDataQuery);

            java.sql.ResultSet comboDataRset = pstmtComboData.executeQuery();

            while (comboDataRset.next()) {

                comboDataVector.addElement(comboDataRset.getObject(1)); // populate vector with result set dataset.

            }

            comboDataRset.close();
            pstmtComboData.close();

        } catch (java.sql.SQLException sqlEx) {

            sqlEx.printStackTrace(); // print stack trace for debugginh purposes.

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlEx.getMessage(), "ERROR: Data fetch error!", javax.swing.JOptionPane.ERROR_MESSAGE); // popup an error message for the user

        }

        return comboDataVector;

    }
    
    public static javax.swing.DefaultComboBoxModel ComboBoxYearsModel(java.sql.Connection connectDB) {
        String comboDBDataQuery = "SELECT  '-' union  SELECT '2021' union SELECT '2022' union SELECT '2023' order by 1";

        javax.swing.DefaultComboBoxModel comboBoxModel = new javax.swing.DefaultComboBoxModel(submitQuery(connectDB, comboDBDataQuery));

        return comboBoxModel;

    }
}
