/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import javax.swing.*;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;


import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.formatter.SVGFormatter;
import net.sourceforge.barbecue.formatter.FormattingException;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterJob;

/**
 *
 * @author Mustaphatwo
 */
public class BarcodeGenerator {

    private static final String[] barcodeData = {
        "Code128", "Creates a Code 128 barcode that dynamically switches between character sets to "
        + "give the smallest possible encoding. This will encode  all numeric characters, "
        + "upper and lower case alpha characters and control characters  from the standard "
        + "ASCII character set. The size of the barcode created will be the  smallest possible "
        + "for the given data, and use of this \"optimal\" encoding will  generally give smaller "
        + "barcodes than any of the other 3 \"vanilla\" encodings.",
        "Code128A", "Creates a Code 128 barcode using the A character set. This will encode  all numeric characters, upper case alpha characters and control characters  from the standard ASCII character set. The Code 128 barcode supports on-the-fly  character set changes using the appropriate code change symbol. The type A barcode  also supports a one character 'shift' to set B.",
        "Code128B", "Creates a Code 128 barcode using the B character set. This will encode  all numeric characters and upper and lower case alpha characters  from the standard ASCII character set. The Code 128 barcode supports on-the-fly  character set changes using the appropriate code change symbol. The type B barcode  also supports a one character 'shift' to set A.",
        "Code128C", "Creates a Code 128 barcode using the C character set. This will encode  only numeric characters in a double density format (e.g. 1 digit in the barcode  encodes two digits in the data). The Code 128 barcode supports on-the-fly  character set changes using the appropriate code change symbol. No shifts are  possible with the type C barcode.",
        "EAN128", "Creates an EAN128 barcode",
        "GlobalTradeItemNumber", "Creates a Global Treade Item Number (GTIN) based on the UCC/EAN 128 symbology.",
        "SCC14ShippingCode", "Creates an SCC-14 shipping code number based on the UCC/EAN 128 symbology.",
        "ShipmentIdentificationNumber", "Creates a shipment identification number based on the UCC/EAN 128 symbology.",
        "SSCC18", "Creates an SSCC-18 number based on the UCC/EAN 128 symbology.",
        "UCC128", "Creates a UCC 128 barcode. This will encode numeric characters and must  include the correct application identifier for the application domain in which  you wish to use the barcode.",
        "USPS", "Creates a US Postal Service barcode based on the UCC/EAN 128 symbology.",
        /*"Code39", "Creates a Code 39 barcode.",
        "Codabar", "Creates a Codabar barcode.",*/
        "PDF417", "Creates a PDF417 (2 dimensional) barcode."
    };
    static Barcode barcode = null;

    public static Image barcodeImage(String barcodeDetail) {


        Image barcodeImage = null;

        String barcodeText = barcodeDetail;

        String currentValue = barcodeText;

        if ((barcodeText == null) || (barcodeText.length() == 0)) {
            barcodeText = " ";
        }

        /* boolean appIDVisible= currentValue.equals("UCC128");
        if(appIDVisible != appIDTextField.isVisible()) {
        appIDTextField.setVisible(appIDVisible);
        appIDLabel.setVisible(appIDVisible);
        }
         */
        if (currentValue.equals("UCC128")) {
            try {
                barcode = BarcodeFactory.createUCC128(currentValue/*appIDTextField.getText()*/, barcodeText);
                // setBarcode(barcode);
            } catch (BarcodeException ex) {
                ex.printStackTrace();
            }
        } else if (currentValue.equals("Code39")) {
            try {
                barcode = BarcodeFactory.createCode39(barcodeText, true);
                // setBarcode(barcode);
            } catch (BarcodeException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Class factory = net.sourceforge.barbecue.BarcodeFactory.class;
                Method createMethod = factory.getMethod("create" + currentValue, new Class[]{"".getClass()});
                System.out.print("Method Generated : " + createMethod.toString());
                Object result = createMethod.invoke(null, new Object[]{barcodeText});
                System.out.print("Barcode Generated : " + result.toString());
                barcode = (Barcode) result;
                // setBarcode(barcode);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            } catch (java.lang.reflect.InvocationTargetException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


        return barcodeImage;
    }
}
