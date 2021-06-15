/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author System Partners
 */
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExportData {

    public static Vector read(String fileName) {
        Vector cellVectorHolder = new Vector();
        try {

            FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                Vector cellStoreVector = new Vector();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    //Object obj=myCell.getStringCellValue();
                    System.out.print(myCell.getCellType() + " -");
                    if (myCell.getCellType() == 0) {
                        cellStoreVector.addElement(myCell.getNumericCellValue());

                    } else if (myCell.getCellType() == 1) {
                        cellStoreVector.addElement(myCell.getStringCellValue());

                    }
                    //cellStoreVector.addElement(myCell.getStringCellValue());
                }
                System.out.println();
                cellVectorHolder.addElement(cellStoreVector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }

    public static Vector readMpesaXls(String fileName) {
        Vector cellVectorHolder = new Vector();
        try {

            FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                Vector cellStoreVector = new Vector();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    //Object obj=myCell.getStringCellValue();
                    System.out.print(myCell.getCellType() + " -");
                    if (myCell.getCellType() == 0) {
                        cellStoreVector.addElement(myCell.getNumericCellValue());

                    } else if (myCell.getCellType() == 1) {
                        cellStoreVector.addElement(myCell.getStringCellValue());

                    }
                    //cellStoreVector.addElement(myCell.getStringCellValue());
                }
                System.out.println();
                cellVectorHolder.addElement(cellStoreVector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }
}
