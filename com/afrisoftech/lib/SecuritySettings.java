/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPL7
 */
public class SecuritySettings {

    public static void setUserAccessControl(java.sql.Connection connectDB, String userName) {

        int noofUsers = getDBUsers(connectDB).length;

        int noofTables = getDBTables(connectDB).length;

        Object dbTables[] = getDBTables(connectDB);

        //  Object dbUsers[] = getDBUsers(connectDB);
        try {

            for (int i = 0; i < noofUsers; i++) {

                System.out.println("User [" + userName + "]");

                java.sql.PreparedStatement pstmtUsers = connectDB.prepareStatement("ALTER ROLE " + userName + "  NOSUPERUSER NOCREATEDB NOCREATEROLE");

                pstmtUsers.execute();

                pstmtUsers.close();
            }

            //      for (int j = 0; j < noofUsers; j++) {
            for (int k = 0; k < noofTables; k++) {

                System.err.println("Changing DB security settings for User : [" + userName + "] on table [" + dbTables[k] + "]");

                java.sql.PreparedStatement pstmtRevoke = connectDB.prepareStatement("REVOKE DELETE ON \"" + dbTables[k] + "\" FROM \"" + userName + "\"");

                pstmtRevoke.execute();

                java.sql.PreparedStatement pstmtInsert = connectDB.prepareStatement("GRANT INSERT ON \"" + dbTables[k] + "\" TO \"" + userName + "\"");

                pstmtInsert.execute();

                java.sql.PreparedStatement pstmtUpdate = connectDB.prepareStatement("GRANT UPDATE ON \"" + dbTables[k] + "\" TO \"" + userName + "\"");

                pstmtUpdate.execute();

                java.sql.PreparedStatement pstmtSelect = connectDB.prepareStatement("GRANT SELECT ON \"" + dbTables[k] + "\" TO \"" + userName + "\"");

                pstmtSelect.execute();

                pstmtRevoke.close();

                pstmtInsert.close();

                pstmtUpdate.close();

                pstmtSelect.close();

                //     }
            }

        } catch (java.sql.SQLException sqlEx) {

            sqlEx.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlEx.getMessage());

        }

    }

    public static void setUserAccessControl(java.sql.Connection connectDB) {

        int noofUsers = getDBUsers(connectDB).length;

        int noofTables = getDBTables(connectDB).length;

        int noofViews = getDBViews(connectDB).length;

        int noofSequences = getDBSequences(connectDB).length;

    //    int noofFunctions = getDBFunctions(connectDB).length;

        Object dbTables[] = getDBTables(connectDB);

        Object dbViews[] = getDBViews(connectDB);

        Object dbSequences[] = getDBSequences(connectDB);

        Object dbUsers[] = getDBUsers(connectDB);

    //    Object dbFunctions[] = getDBFunctions(connectDB);

        try {

            for (int i = 0; i < noofUsers; i++) {

                System.out.println("User [" + dbUsers[i].toString() + "]");

                java.sql.PreparedStatement pstmtUsers = connectDB.prepareStatement("ALTER ROLE " + dbUsers[i].toString() + "  NOSUPERUSER NOCREATEDB NOCREATEROLE");

                pstmtUsers.execute();

                pstmtUsers.close();
            }

            for (int k = 0; k < noofTables; k++) {

                System.err.println("Changing DB security settings for User : public  on table [" + dbTables[k] + "]");

                java.sql.PreparedStatement pstmtInsert = connectDB.prepareStatement("GRANT INSERT, UPDATE, SELECT, REFERENCES, TRIGGER ON TABLE " + dbTables[k].toString().toLowerCase() + " TO public");

                pstmtInsert.execute();

                pstmtInsert.close();

            }

            //           }
            for (int k = 0; k < noofViews; k++) {

                System.err.println("Changing DB security settings for User : public on view [" + dbViews[k] + "]");

                java.sql.PreparedStatement pstmtSelect = connectDB.prepareStatement("GRANT SELECT, TRIGGER ON TABLE " + dbViews[k].toString().toLowerCase() + "  TO public");

                pstmtSelect.execute();

                pstmtSelect.close();

            }

            for (int k = 0; k < noofSequences; k++) {

                System.err.println("Changing DB security settings for User : public on sequences [" + dbSequences[k] + "]");

                java.sql.PreparedStatement pstmtSelect = connectDB.prepareStatement(" GRANT USAGE ON SEQUENCE " + dbSequences[k].toString().toLowerCase() + " TO public");

                pstmtSelect.execute();

                pstmtSelect.close();

            }

//            for (int k = 0; k < noofFunctions; k++) {
//
//                System.err.println("Changing DB security settings for Function : public on sequences [" + dbFunctions[k] + "]");
//
//                java.sql.PreparedStatement pstmtSelect = connectDB.prepareStatement(" GRANT EXECUTE ON  " + dbFunctions[k].toString().toLowerCase() + " TO public");
//
//                pstmtSelect.execute();
//
//                pstmtSelect.close();
//
//            }

        } catch (java.sql.SQLException sqlEx) {

            sqlEx.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlEx.getMessage());

        }

    }

    public static Object[] getDBUsers(java.sql.Connection connDB) {

        java.lang.Object userList[] = null;

        java.util.Vector<String> listofUsers = new java.util.Vector<>(1, 1);

        try {

            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT usename FROM pg_user WHERE usename not ilike '%postgres%' and usename != 'admin' order by usename");

            //    java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT groname FROM pg_group order by groname where groname != 'sys_admin'");// WHERE usename not ilike '%postgres%' and usename != 'admin' order by usename");
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                listofUsers.addElement(rset.getString(1));

            }
        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());

            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);

        }

        return userList = listofUsers.toArray();
    }

    public static Object[] getDBTables(java.sql.Connection connDB) {

        java.lang.Object[] tablesList = null;

        java.util.Vector<String> listofTables = new java.util.Vector<>(1, 1);

        try {

            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT schemaname||'.'||relname FROM pg_statio_user_tables WHERE relname not ilike '%pg_%' order by 1");

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                listofTables.addElement(rset.getString(1));

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());

            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);

        }

        return tablesList = listofTables.toArray();

    }

    /**
     *
     *
     * @param connDB
     * @return viewsList
     *
     * This method takes a connection and returns the list of views created for
     * the Funsoft system
     *
     * The viewsList is an Object array of the list of views
     *
     */
    public static Object[] getDBViews(java.sql.Connection connDB) {

        java.lang.Object[] tablesList = null; // create the object array to be return after the fetch for views from the database

        java.util.Vector<String> listofTables = new java.util.Vector<>(1, 1); // create a cevtor to hold the list of views as they are fetched from the database

        try {

            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT schemaname||'.'||viewname FROM pg_views where viewname not ilike '%pg_%' order by 1");

            java.sql.ResultSet rset = pstmt.executeQuery();  // executing the query to fetch the views available on the database

            while (rset.next()) { //iterate through the resultset to get all the available views

                listofTables.addElement(rset.getString(1)); //add views from the query to the listofTables vector

            }

        } catch (SQLException ex) {

            ex.printStackTrace();  // print the error message on standard output

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage()); //show popup message on the user interface

            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);  // take log of the exception on the operating system file system

        }

        return tablesList = listofTables.toArray(); // converting the vector to the array to be returned

    }

    //pg_statio_all_sequences
    /**
     *
     *
     * @param connDB
     * @return viewsList
     *
     * This method takes a connection and returns the list of views created for
     * the Funsoft system
     *
     * The viewsList is an Object array of the list of views
     *
     */
    public static Object[] getDBSequences(java.sql.Connection connDB) {

        java.lang.Object[] tablesList = null; // create the object array to be return after the fetch for views from the database

        java.util.Vector<String> listofTables = new java.util.Vector<>(1, 1); // create a cevtor to hold the list of views as they are fetched from the database

        try {

            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT relname FROM pg_statio_all_sequences WHERE schemaname = 'public' ORDER By 1");

            java.sql.ResultSet rset = pstmt.executeQuery();  // executing the query to fetch the views available on the database

            while (rset.next()) { //iterate through the resultset to get all the available views

                listofTables.addElement(rset.getString(1)); //add views from the query to the listofTables vector

            }

        } catch (SQLException ex) {

            ex.printStackTrace();  // print the error message on standard output

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage()); //show popup message on the user interface

            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);  // take log of the exception on the operating system file system

        }

        return tablesList = listofTables.toArray(); // converting the vector to the array to be returned

    }
//
//    public static Object[] getDBFunctions(java.sql.Connection connDB) {
//
//        java.lang.Object[] tablesList = null; // create the object array to be return after the fetch for views from the database
//
//        java.util.Vector<String> listofTables = new java.util.Vector<>(1, 1); // create a cevtor to hold the list of views as they are fetched from the database
//
//        try {
//
//            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT routine_name FROM information_schema.routines ORDER By 1");
//
//            java.sql.ResultSet rset = pstmt.executeQuery();  // executing the query to fetch the views available on the database
//
//            while (rset.next()) { //iterate through the resultset to get all the available views
//
//                listofTables.addElement(rset.getString(1)); //add views from the query to the listofTables vector
//
//            }
//
//        } catch (SQLException ex) {
//
//            ex.printStackTrace();  // print the error message on standard output
//
//            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage()); //show popup message on the user interface
//
//            Logger.getLogger(SecuritySettings.class.getName()).log(Level.SEVERE, null, ex);  // take log of the exception on the operating system file system
//
//        }
//
//        return tablesList = listofTables.toArray(); // converting the vector to the array to be returned
//
//    }

}
