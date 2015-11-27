/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author Charles
 */
public class TableTransactionCheck {

    public static boolean checkTableEntries(javax.swing.JTable targetTable, int baseColumn, int quantityColumn) {

        if (targetTable.isEditing()) {
            targetTable.getCellEditor().stopCellEditing();
        }
        int countCheck = 0;
        boolean checkStatus = false;
        for (int j = 0; j < targetTable.getRowCount(); j++) {
            if (targetTable.getValueAt(j, quantityColumn) != null) {
                if (targetTable.getValueAt(j, baseColumn) != null && Double.parseDouble(targetTable.getValueAt(j, quantityColumn).toString()) > 0) {
                    countCheck = countCheck + 0;
                } else {
                    countCheck = countCheck + 1;
                }

            }
        }
        if (countCheck < 1) {
            checkStatus = true;
        } else {
            checkStatus = false;
        }
        return checkStatus;
    }

}
