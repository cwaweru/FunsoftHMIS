/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.kitchen;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author ADMIN BIACH
 */
public class Interface_Alpha extends JInternalFrame {

    protected int id;
    protected String uuid;
    protected int uuid_no;
    protected int user_id;
    private int department_id;
    private int reference_interface_item_id;
    public Connection myConn;
    private Statement stmt;

    public Interface_Alpha() {
        super();
        id = 0;
        uuid = "";
        uuid_no = 0;
        user_id = 1;
        department_id = 0;
        reference_interface_item_id = 0;
//        try {
//            myConn = new dbConn().connector();
//            stmt = myConn.createStatement();
//        } catch (SQLException x) {
//            System.err.println("Database error." + x.getMessage());
//        }
    }

    protected int getUuidNo() {
        if (uuid_no > 0) {
            return uuid_no;
        } else {
            try {
                String sql = "SELECT id FROM catering.\"Interface\" WHERE uuid = '" + this.uuid + "'";
                //System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    uuid_no = rs.getInt("id");
                }
            } catch (SQLException x) {
                System.err.println("Error - UUID No.: " + x.getMessage());
            }
        }
        return uuid_no;
    }

    public String[] getDefaultValueOf(String interface_uuid, String interface_item) {
        String sql = "SELECT value_list_id, default_value, type FROM catering.\"Interface\" i INNER JOIN catering.\"Interface_Items\" ii ON i.id = ii.interface_id AND ii.item_name = '" + interface_item + "'";// AND uuid = '" + interface_uuid ;
        System.out.println(sql);
        String[] arrResult = new String[0];
        try {
             System.out.println(myConn);
            stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getInt("type") == 1) {
                    arrResult = new String[1];
                    arrResult[0] = rs.getString("default_value");
                } else if (rs.getInt("type") == 2) {
                    sql = "SELECT value FROM catering.\"List_Item\" WHERE list_id = " + rs.getString("value_list_id");
                    //System.out.println(sql);
                    arrResult = new String[10];
                     stmt=myConn.createStatement();
                    rs = stmt.executeQuery(sql);
                    int i = 0;
                    while (rs.next()) {
                        arrResult[i] = rs.getString("value");
                        i++;
                    }
                }
            }
        } catch (SQLException x) {
            System.err.println("Database error." + x.getMessage());
        }
        return arrResult;
    }

    public String[][] getApplicationDetails(int appl_id) {
        String[][] details = new String[0][0];
        int interface_id = getUuidNo();
        try {
            String sql = "SELECT item_name, value FROM catering.\"Interface_Items\" ii INNER JOIN catering.\"Application_Details\" ad ON ad.application_id = " + appl_id + " AND ((ad.interface_item_id = ii.id AND ii.interface_id = " + interface_id + ") OR  (ad.interface_item_id = ii.refers_from AND ii.interface_id = " + interface_id + "))";
            System.out.println(sql);
            details = new String[20][2];
             stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                details[i][0] = rs.getString("item_name");
                details[i][1] = rs.getString("value");
                i++;
            }
            //}
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        }
        return details;
    }
        public String[][] getItemList() {
        String[][] details = new String[0][0];
        int interface_id = getUuidNo();
        try {
            String sql = "SELECT * FROM st_stock_item WHERE broad_category= 'FOODSTUFFS'";
            System.out.println(sql);
            details = new String[20][2];
             stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                details[i][0] = rs.getString("description");
                details[i][1] = rs.getString("buying_price");
                i++;
            }
            //}
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        }
        return details;
    }

    public JComboBox loadData(JComboBox combo, String interface_uuid) {
        return loadData(combo, interface_uuid, combo.getName());
    }
     public JComboBox loadDataDynamic(JComboBox combo, int interface_item) {
        //combo.removeAllItems();

        try {
            //Get data from the database
            String sql = "SELECT value FROM catering.\"Application_Details\" WHERE interface_item_id = " + interface_item + " ORDER BY value";
            //String[] arrData = getDefaultValueOf(interface_uuid, interface_item);
             stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("value"));
                i++;
            }
            
        } catch (SQLException x) {
            System.out.println("Error loading dynamic data: " + x.getMessage());
        }
        return combo;
    }

    public JComboBox loadData(JComboBox combo, String interface_uuid, String interface_item) {
        String[] arrData = getDefaultValueOf(interface_uuid, interface_item);
        int i = 0;
        combo.removeAllItems();
        while (i < arrData.length && arrData[i] != null) {
            combo.addItem(arrData[i]);
            i++;
        }
        return combo;
    }

    public JTable loadData(JTable myTable, String interface_uuid) {
        return loadData(myTable, interface_uuid, myTable.getName());
    }

    public JTable loadData(JTable myTable, String interface_uuid, String interface_item) {
        //combo.removeAllItems();
        int type_id = getInterfaceItemType(getInterfaceItemId(interface_uuid, interface_item));

        int myInterfaceId = getInterfaceId(interface_uuid);

        DefaultTableModel oldModel = (DefaultTableModel) myTable.getModel();
        oldModel.setRowCount(0);
        oldModel.setColumnCount(0);
        String[] columnNames;
        String[][] myData;
        columnNames = getColumnNames(myInterfaceId);
        myData = getDataFor(myInterfaceId);

        //System.out.println("Columns: " + columnNames.length + " - rows: " + myData.length);
        //DefaultTableModel myModel = new DefaultTableModel(myData, columnNames);
        oldModel.setDataVector(myData, columnNames);
        oldModel.fireTableStructureChanged();
        oldModel.fireTableDataChanged();
        myTable.setModel(oldModel);

        return myTable;
    }

    public JTable loadData(JTable myTable, String interface_uuid, int application_id) {
        //combo.removeAllItems();
        //int type_id = getInterfaceItemType(getInterfaceItemId(interface_uuid, myTable.getName()));

        int myInterfaceId = getInterfaceId(interface_uuid);

        DefaultTableModel oldModel = (DefaultTableModel) myTable.getModel();
        oldModel.setRowCount(0);
        oldModel.setColumnCount(0);
        String[] columnNames;
        String[][] myData;
        System.out.println("Getting columns :");
        columnNames = getColumnNamesForItems(myInterfaceId);
        System.out.println("Searching columns names :");
        myData = getDataFor(application_id, myInterfaceId);
        System.out.println("Columns: " + columnNames.length + " - rows: " + myData.length);
        //DefaultTableModel myModel = new DefaultTableModel(myData, columnNames);
        oldModel.setDataVector(myData, columnNames);
        oldModel.fireTableStructureChanged();
        oldModel.fireTableDataChanged();
        myTable.setModel(oldModel);

        return myTable;
    }

    public int updateInterface(JTable tblReport, JComponent[] arrComponents) {
        int selected_id = 0;
        try {
            selected_id = Integer.parseInt(tblReport.getValueAt(tblReport.getSelectedRow(), 0).toString());
            System.out.println("Selected item: " + selected_id);
            String[][] details = getApplicationDetails(selected_id);
            for (int i = 0; i < details.length; i++) {
                if (details[i][0] == null) {
                    break;
                }
                for (int d = 0; d < arrComponents.length; d++) {
                    if (arrComponents[d] == null) {
                        break;
                    }
                    //System.out.println("Value at " + i + " is " + details[i][0]);
                    if (details[i][0].equals(arrComponents[d].getName())) {
                        if (arrComponents[d].getName().contains("txt")) {
                            JTextField x = (JTextField) arrComponents[d];
                            x.setText(details[i][1]);
                        } else if (arrComponents[d].getName().contains("txa")) {
                            JTextArea x = (JTextArea) arrComponents[d];
                            x.setText(details[i][1]);
                        } else if (arrComponents[d].getName().contains("lbl")) {
                            JLabel x = (JLabel) arrComponents[d];
                            x.setText(details[i][1]);
                        } else if (arrComponents[d].getName().contains("cbo")) {
                            JComboBox x = (JComboBox) arrComponents[d];
                            x.setSelectedItem(details[i][1]);
                        }
                    }
                }
            }
        } catch (NumberFormatException x) {
            selected_id = 0;
        }
        return selected_id;
    }

    public void approveApplication(int appl_id, int interface_id, boolean status) {
        int approved = 0;
        if (status) {
            approved = 1;
        }
        try {
            String sql = "SELECT id FROM catering.\"Authorization\" a WHERE application_id = " + appl_id + " AND user_id = " + user_id + " AND interface_id = " + interface_id;
            System.out.println(sql);
             stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "This application has already been approved. Contact your solution provider to confirm why it had not been removed.");

            }
            sql = "INSERT INTO catering.\"Authorization\" (application_id, user_id, interface_id, status) VALUES (" + appl_id + ", " + user_id + ", " + interface_id + ", " + approved + ")";
            
            System.out.println(sql);
             stmt=myConn.createStatement();
            stmt.execute(sql);

            //Check if there is any other level of approval to be done. If none, set the 
            sql = "SELECT id FROM catering.\"Process\" p WHERE previous_interface = " + getUuidNo();
             stmt=myConn.createStatement();
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                //Set the application as complete and as authorized or denied.
                sql = "UPDATE catering.\"Application\" SET ";
            }
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        }
    }

    public String[][] getDataFor(int iInterface) {
        String[][] data = new String[75][20];
        String[] arr_column_ids = getColumnIds(iInterface);
        String str_column_ids = arrayToString(arr_column_ids).substring(1);
        System.out.println("Number of columns: " + arr_column_ids.length);
        String sql = "SELECT ad.id, interface_item_id, application_id, value FROM catering.\"Application_Details\" ad INNER JOIN catering.\"Application\" a ON a.id = ad.application_id WHERE interface_item_id IN (" + str_column_ids + ") AND (a.interface_id = " + iInterface + " OR a.id IN (SELECT a.id FROM catering.\"Application\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ") OR a.id IN (SELECT application_id FROM catering.\"Authorization\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ")) AND a.id NOT IN (SELECT application_id FROM catering.\"Authorization\" a WHERE interface_id = " + getUuidNo() + ") ORDER BY a.id, interface_item_id";
        System.out.println(sql);
        try {
            stmt=myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            int row = 0;
            int ap_id = 1;
            int col_id = 1;
            while (rs.next()) {
                if (ap_id != rs.getInt("application_id")) {
                    ap_id = rs.getInt("application_id");
                    row++;
                    i = 1;
                    col_id = 1;
                    data[row][0] = rs.getString("application_id");
                }

                //System.out.println("Comparing: " + data[row][0] + " - " + Integer.parseInt(arr_column_ids[col_id]) + "(" + col_id + ") vs " + rs.getInt("interface_item_id") + " (" + i + ")");
                while (Integer.parseInt(arr_column_ids[col_id]) < rs.getInt("interface_item_id")) {
                    data[row][i] = "";
                    col_id++;
                    i++;

                }
                data[row][i] = rs.getString("value");
                i++;
                col_id++;
            }
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        } catch (ArrayIndexOutOfBoundsException x) {
            System.err.println("One of your arrays is too small: " + x.getMessage());

        }
        return data;
    }

    public String[][] getDataFor(int application_id, int iInterfaceId) {
        String[][] data = new String[10][20];
        try {
            String sql = "SELECT list_id, interface_item_id, value FROM catering.\"Lists\" l INNER JOIN catering.\"List_Item\" li ON l.id = li.list_id AND l.application_id = " + application_id + "  ORDER BY list_id, interface_item_id";
            System.out.println("Here I come "+ sql);
            ResultSet rs = stmt.executeQuery(sql);
            int row = 0;
            int col = 0;
            int i = 0;
            int list_id = 0;
            while (rs.next()) {
                if (list_id != rs.getInt("list_id")) {
                    list_id = rs.getInt("list_id");
                    row++;
                    i = 1;
                    col = 1;
                    data[row][0] = rs.getString("list_id");
                }
                data[row][i] = rs.getString("value");
                i++;
            }
        } catch (SQLException x) {
            System.out.println("Error loading data for combobox: " + x.getMessage());
        }
        return data;
    }

    public String[] shrinkArray(int columns, String[] original) {
        String[] newArray = new String[columns];
        for (int i = 0; i < columns; i++) {
            newArray[i] = original[i];
        }
        return newArray;
    }

    public String[] getColumnIds(int iInterface) {
        String[] columns = new String[20];
        int current_index = 1;
        try {
            //String sqlColumnsInData = "SELECT DISTINCT interface_item_id FROM catering.\"Application_Details\" ad INNER JOIN catering.\"Application\" a ON a.id = ad.application_id INNER JOIN catering.\"Interface_Items\" ii ON ii.id = ad.interface_item_id WHERE refers_from = 0 AND interface_item_id IN (SELECT id FROM catering.\"Interface_Items\" WHERE interface_id = " + iInterface + " OR id IN (SELECT refers_from FROM catering.\"Interface_Items\" WHERE interface_id = " + iInterface + "))  AND (a.interface_id = " + iInterface + " OR a.id IN (SELECT a.id FROM catering.\"Application\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ") OR a.id IN (SELECT application_id FROM catering.\"Authorization\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ")) AND a.id NOT IN (SELECT application_id FROM catering.\"Authorization\" a WHERE interface_id = " + getUuidNo() + ") ORDER BY interface_item_id";
            String sqlColumnsInData = "SELECT id FROM catering.\"Interface_Items\" WHERE (interface_id = " + iInterface + " OR id IN (SELECT refers_from FROM catering.\"Interface_Items\" WHERE interface_id = " + iInterface + ")) AND refers_from = 0 ORDER BY id";// AND (a.interface_id = " + iInterface + " OR a.id IN (SELECT a.id FROM catering.\"Application\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ") OR a.id IN (SELECT application_id FROM catering.\"Authorization\" a INNER JOIN catering.\"Process\" p ON a.interface_id = p.previous_interface AND p.interface_id = " + getUuidNo() + ")) AND a.id NOT IN (SELECT application_id FROM catering.\"Authorization\" a WHERE interface_id = " + getUuidNo() + ") ORDER BY interface_item_id";
            System.out.println(sqlColumnsInData);
            String sql = "SELECT id, human_friendly_name FROM catering.\"Interface_Items\" WHERE id IN (" + sqlColumnsInData + ") AND refers_from = 0 AND type <> 3 ORDER BY id";

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            //rs = stmt.executeQuery(sql);
            columns[0] = "#";
            int i = current_index;
            System.out.println();
            while (rs.next()) {
                columns[i] = rs.getString("id");
                System.out.print(" - " + columns[i]);
                i++;
            }
            if (i == 0) {
            }
            System.out.println();
            columns = shrinkArray(i, columns);
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        }
        return columns;
    }

    public String[] getColumnNamesForItems(int iInterface) {
        //Check if this interface refers to fields from other interfaces
        String[] columns = new String[20];
        int current_index = 1;
        columns[0] = "#";
        try {
            String sql = "SELECT human_friendly_name FROM catering.\"Interface_Items\" ii WHERE interface_id = " + iInterface + " AND type = 3";
           
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                columns[current_index] = rs.getString("human_friendly_name");
                current_index++;
            }
            columns = shrinkArray(current_index, columns);
        } catch (SQLException x) {
            System.out.println("Error getting column names: " + x.getMessage());
        }
        return columns;
    }

    public String[] getColumnNames(int iInterface) {
        //Check if this interface refers to fields from other interfaces
        String[] columns = new String[20];
        int current_index = 1;
        try {
            String[] columnIds_arr = getColumnIds(iInterface);
            String columnIds_str = arrayToString(columnIds_arr).substring(1);

            String sql = "SELECT id, human_friendly_name FROM catering.\"Interface_Items\" WHERE id IN (" + columnIds_str + ") AND refers_from = 0 ORDER BY id";

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            //rs = stmt.executeQuery(sql);
            columns[0] = "#";
            int i = current_index;
            while (rs.next()) {
                columns[i] = rs.getString("human_friendly_name");
                i++;
            }
            columns = shrinkArray(i, columns);
        } catch (SQLException x) {
            System.err.println(x.getMessage());
        }
        return columns;
    }

    public String arrayToString(String[] columnIds_arr) {
        String columnIds_str = "";

        for (int i = 1; i < columnIds_arr.length; i++) {
            columnIds_str += ", " + columnIds_arr[i];
        }
        return columnIds_str;
    }

    public String[][] saveApplication(String[][] input) {
        String[][] report = new String[2][2];
        String sql;
        boolean isUpdating = false;
        try {
            int application_id = 0;
            if (input[0][0].toString().equals("-1")) {
                application_id = Integer.parseInt(input[0][1]);
                isUpdating = true;
            } else {
                //Create a new Application
                sql = "INSERT INTO catering.\"Application\" (interface_id, applicant_id) VALUES ('" + getInterfaceId(input[0][0]) + "','" + Integer.parseInt(input[0][1]) + "')";
                System.out.println(sql);
                stmt.execute(sql);
                sql = "SELECT MAX(id) AS id FROM catering.\"Application\" WHERE interface_id = '" + getInterfaceId(input[0][0]) + "' AND applicant_id = '" + input[0][1] + "'";
                System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    application_id = rs.getInt("id");
                }
               
            }
            if (application_id > 0) //Save the application details
            {
                switch (input[0][0].toString()) {
                    case "ApprovedS11":
                        sql = "INSERT INTO public.\"st_receive_requisation\" ( received_requisation,cost_center,item_description, quantity, user_name,price) VALUES ('0.00','Main Store','"+input[1][1]+"','"+input[3][1]+"','"+input[6][1]+"','"+input[5][1]+"')";
                        System.out.println("WE are here now :"+sql);
                        stmt.execute(sql);
                        break;
                    case "Costandbalancebook":
                        sql = "INSERT INTO public.\"ac_ledger\" (activity_code, description, patient_no, dealer, reason, voucher_no, payee, drawer, drawer_bank, gl_code, cheque_no, receipt_no, journal_no, service_type, transaction_type, debit, credit, date, transaction_no, closed, reconciled, withdrawn, user_name, cash_point, shift_no) VALUES ('600-50500-60069', '"+input[2][1]+"', '', '', '', '', '', '', '', '', '', '', '', '', '', '"+input[7][1]+"', 0.00, '"+input[4][1]+"', '49880', false, false, false, 'My Name', '', '')";
                        System.out.println("WE are here now at ac ledger :"+sql);
                        stmt.execute(sql);
                        sql = "INSERT INTO public.\"st_stock_cardex\" (store, item, quantity_ordered, price_per_item, units, quantity_received, sub_store_receiving, quantity_required,issued_to, issued_by, date, cost_center, debit, credit, activity_code, description, transaction_no, sub_store_issuing, user_name, old_grn_date, item_code) VALUES ('"+input[2][1]+"', '"+input[1][1]+"', '"+input[3][1]+"', '"+input[5][1]+"', '"+input[9][1]+"', '"+input[3][1]+"', 0.00, 0.00, '', 'Name of person', '"+input[4][1]+"', '"+input[2][1]+"', 0.00, '"+input[7][1]+"', '210-70700-21001', 'Main Store',  '49880', '"+input[3][1]+"', 'My Name', '"+input[4][1]+"', '"+input[8][1]+"')";
                        System.out.println("WE are here now at cardex :"+sql);
                        stmt.execute(sql);
                        break;
                   
                }
                sql = "INSERT INTO catering.\"Application_Details\" (application_id, interface_item_id, value) VALUES ";

                int interface_item_id = 0;
                String value = "";
                boolean isFirst = true;
               
                for (int i = 1; i < input.length; i++) {

                    interface_item_id = getInterfaceItemId(this.uuid, input[i][0]);
                    value = input[i][1];
                    System.out.println("Saving " + input[i][1] + " from " + input[i][0] + " with id " + interface_item_id);
                    if (interface_item_id > 0) {
                        int iType = getInterfaceItemType(interface_item_id);
                        int list_id = 0;
                        if (iType == 3) {
                            //Save the new item
                            String sql2 = "SELECT id FROM catering.\"Lists\" l WHERE application_id = " + application_id;
                            ResultSet rs = stmt.executeQuery(sql2);
                            System.out.println("SQL 1 =" + sql2);
                            if (isFirst) {
                                 System.out.println("You are supposed to wro =");
                                sql2 = "INSERT INTO catering.\"Lists\"  (name, application_id) VALUES ('" + application_id + "', " + application_id + ")";
                                 System.out.println("SQL 2 =" + sql2);
                                stmt.execute(sql2);
                                isFirst = false;
                            } 
                            
                                sql2 = "SELECT MAX(id) AS id FROM catering.\"Lists\" l WHERE application_id = " + application_id;
                                System.out.println("SQL 3 =" + sql2);
                                rs = stmt.executeQuery(sql2);
                                
                                if (rs.next()) {
                                    list_id = rs.getInt("id");
                                }
//                            } else {
//                                System.out.println("Am at the else =");
//                                list_id = rs.getInt("id");
//                            }
                            if (list_id > 0) {
                                sql2 = "INSERT INTO catering.\"List_Item\"  (list_id, value, interface_item_id) VALUES (" + list_id + ", '" + value + "', " + interface_item_id + ")";
                                System.out.println("SQL 4 =" + sql2);
                                stmt.execute(sql2);
                               
                            }
                        } else {
                            if (isFirst) {
                                isFirst = false;
                            } else {
                                sql += ", ";
                            }
                            sql += "(" + application_id + ", " + interface_item_id + ", '" + value + "')";
                        }

                    }
                }
                if (isFirst) {
                    System.out.println("There is nothing to save.");
                    report[0][0] = "0";
                    report[0][1] = "There was not data to save.";
                } else {
                    System.out.println(sql);
                    stmt.execute(sql);
                }
            } else {
                report[0][0] = "0";
                report[0][1] = "The application could not be saved.";
            }
        } catch (SQLException x) {
            System.err.println("Database error." + x.getMessage());
        } catch (NumberFormatException x) {
            System.err.println("Error getting the application id.");
        }
        return report;
    }
   
    public int getInterfaceItemType(int iiId) {
        int type = 0;
        try {
            String sql = "SELECT type FROM catering.\"Interface_Items\" i WHERE id = " + iiId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                type = rs.getInt("type");
            }
        } catch (SQLException x) {
            System.out.println("Error getting type: " + x.getMessage());
        }
        return type;
    }

    public int getInterfaceId(String interface_uuid) {
        int interface_id = 0;
        try {
            String sql = "SELECT id FROM catering.\"Interface\" i WHERE i.uuid = '" + interface_uuid + "'";
            //System.out.println(sql);
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                interface_id = rs.getInt("id");
            }
            return interface_id;
        } catch (SQLException x) {
            System.err.println("Database error." + x.getMessage());
            return 0;
        }
    }

    public int getInterfaceItemId(String interface_uuid, String interface_item_name) {
        int interface_id = 0;
        try {
            String sql = "SELECT ii.id FROM catering.\"Interface\" i INNER JOIN catering.\"Interface_Items\" ii ON i.id = ii.interface_id AND i.uuid = '" + interface_uuid + "' AND ii.item_name = '" + interface_item_name + "'";
            System.out.println(sql);
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                interface_id = rs.getInt("id");
            }
            return interface_id;
        } catch (SQLException x) {
            System.err.println("Database error." + x.getMessage());
            return 0;
        }

    }

    public String[] getReport() {
        String data[] = new String[10];

        return data;
    }
}
