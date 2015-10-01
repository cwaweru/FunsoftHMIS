/*
 * XMLExport.java
 *
 * Created on March 26, 2004, 9:34 AM
 */
package com.afrisoftech.dbadmin;

import java.io.IOException;
import org.openide.util.Exceptions;

/**
 *
 * @author root
 */
public class XMLExport implements java.lang.Runnable {

    java.lang.String tableName;

    boolean export2XML = true;

    java.lang.String fileString = null;

    org.w3c.dom.Document initXmlDoc = null;

    java.io.BufferedOutputStream xmlOutpuStream = null;

    java.lang.Thread threadExport2XML = null;

    javax.swing.JTable export2XMLTable = null;

    /**
     * Creates a new instance of XMLExport
     */
    public XMLExport(javax.swing.JTable xmlTable2Export, java.lang.String name2XmlTable) {

        tableName = name2XmlTable;

        export2XMLTable = xmlTable2Export;

        javax.swing.table.TableModel tableModel2XML = export2XMLTable.getModel();

        threadExport2XML = new java.lang.Thread(this, "Export2XML");

        threadExport2XML.run();

    }

    public void run() {

        while (export2XML) {

            createXMLDoc();

            try {

                Thread.currentThread().sleep(100);

            } catch (java.lang.InterruptedException IntExec) {
                System.out.println(IntExec.getMessage());
            }

            export2XML = false;

        }

    }

    public void createXMLDoc() {

        java.io.File xmlDocFile = null;

        org.w3c.dom.Document xmlDocument = null;

        System.out.println("Started XML business ...");

        try {

            xmlDocFile = java.io.File.createTempFile("ExportXMLDoc", ".xml", new java.io.File(System.getProperty("docsdir")));

            xmlDocFile.deleteOnExit();

            xmlOutpuStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(xmlDocFile));

            System.out.println("File name [" + xmlDocFile.getAbsolutePath() + "]");

            fileString = xmlDocFile.getAbsolutePath();

            javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();

            try {

                javax.xml.parsers.DocumentBuilder xmlDocBuilder = documentBuilderFactory.newDocumentBuilder();

                //        try {
                initXmlDoc = xmlDocBuilder.newDocument();

                //     xmlDocument = xmlDocBuilder.parse(xmlDocFile);
                //        } catch(org.xml.sax.SAXException saxExec) {
                //            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), saxExec.getMessage());
                //        }
                workonXmlDocument(initXmlDoc);

                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table [" + tableName.toUpperCase() + "] to file [" + xmlDocFile.getAbsolutePath() + "]");

            } catch (javax.xml.parsers.ParserConfigurationException parserExec) {

                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), parserExec.getMessage());

            }

        } catch (java.io.IOException ioExec) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());

        }
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop.getDesktop().open(xmlDocFile);
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public void workonXmlDocument(org.w3c.dom.Document xmlDoc) {

        java.lang.Object[] tableColumns = this.getTableColumns(export2XMLTable);

        int rowCount = export2XMLTable.getRowCount();

        int columnCount = export2XMLTable.getColumnCount();
        //       System.out.println("Printing the value of xmlDoc ["+xmlDoc+"]");

        //   try {
        org.w3c.dom.Element rootElement = xmlDoc.createElement("ExportXMLDoc");

        org.w3c.dom.Element tableElement = xmlDoc.createElement("xmlTable");

        org.w3c.dom.Element tableColumnModelElement = xmlDoc.createElement("tableColumnModel");

        org.w3c.dom.Element tableColumnElement = xmlDoc.createElement("tableColumn");

        org.w3c.dom.Element tableRowElement = xmlDoc.createElement("tableRow");

        org.w3c.dom.Element tableCellElement = xmlDoc.createElement("tableCell");

        org.w3c.dom.Element tableDataElement = xmlDoc.createElement("tableData");

        xmlDoc.appendChild(rootElement);

        rootElement.appendChild(xmlDoc.createComment("This is XML document for table : [" + tableName + "]"));

        tableElement.appendChild(tableColumnModelElement);

        rootElement.appendChild(tableElement);

        tableElement.appendChild(tableDataElement);

        for (int i = 0; i < tableColumns.length; i++) {

            javax.swing.table.TableColumn xmlTableColumn = (javax.swing.table.TableColumn) tableColumns[i];

            tableColumnElement = xmlDoc.createElement("tableColumn");

            tableColumnElement.appendChild(xmlDoc.createTextNode(xmlTableColumn.getHeaderValue().toString()));

            tableColumnModelElement.appendChild(tableColumnElement);

        }

        for (int j = 0; j < rowCount; j++) {

            tableRowElement = xmlDoc.createElement("tableRow");

            for (int k = 0; k < columnCount; k++) {

                tableCellElement = xmlDoc.createElement("tableCell");

                if (export2XMLTable.getValueAt(j, k) != null) {

                    tableCellElement.appendChild(xmlDoc.createTextNode(export2XMLTable.getValueAt(j, k).toString()));

                } else if (export2XMLTable.getValueAt(j, k) == "") {

                    tableCellElement.appendChild(xmlDoc.createTextNode("-"));

                } else {

                    tableCellElement.appendChild(xmlDoc.createTextNode("-"));

                }

                tableRowElement.appendChild(tableCellElement);

            }

            tableDataElement.appendChild(tableRowElement);

        }
        /*Element.appendChild(goodElement);
         formElement.appendChild(badElement);
         otherElement.appendChild(xmlDoc.createTextNode("Papa"));
         badElement.appendChild(xmlDoc.createTextNode("Dada"));
         goodElement.appendChild(xmlDoc.createTextNode("Kaka"));
         otherElement.appendChild(xmlDoc.createTextNode("someElement"));
         otherElement.appendChild(xmlDoc.createEntityReference("Other_Things"));
         otherElement.appendChild(xmlDoc.createComment("This is my first comment"));

         */
        // someElement.
        org.apache.xml.serialize.OutputFormat format = new org.apache.xml.serialize.OutputFormat();

        format.setIndenting(true);

        org.apache.xml.serialize.XMLSerializer xmlSerializer = new org.apache.xml.serialize.XMLSerializer(xmlOutpuStream, format);

        try {

            xmlSerializer.asDOMSerializer();

            xmlSerializer.serialize(xmlDoc);

        } catch (java.io.IOException ioExec) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());

        }

        /*   } catch(org.xml.sax.SAXException saxExec) {
      
         javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), saxExec.getMessage());
      
         this.dispose();
         }   */
        //        org.w3c.dom.Node rootNode = domResult.getNode();
        //        javax.imageio.metadata.IIOMetadataNode ioMetadataNode = new  javax.imageio.metadata.IIOMetadataNode("Charles");
        //        xmlDoc.appendChild(ioMetadataNode);
        //        System.out.println("Print root element"+ioMetadataNode);
        //        ioMetadataNode.appendChild(new  javax.imageio.metadata.IIOMetadataNode("Charles"));
        //        ioMetadataNode.appendChild(new  javax.imageio.metadata.IIOMetadataNode("Michael"));
        //        ioMetadataNode.appendChild(new  javax.imageio.metadata.IIOMetadataNode("Simon"));
        //        rootElement.insertBefore(ioMetadataNode, rootNode);
        //        initXmlDoc.importNode(ioMetadataNode, true);
        //        initXmlDoc.createElement("papa");
        //        initXmlDoc.createDocumentFragment();
/*        javax.xml.transform.sax.SAXResult saxResult = new javax.xml.transform.sax.SAXResult(new org.xml.sax.helpers.DefaultHandler());
 
         saxResult.setSystemId(fileString);
 
         org.xml.sax.ContentHandler contentHandler = saxResult.getHandler();
 
         try {
 
         contentHandler.startDocument();
         System.out.println("Done starting doc ...");
         contentHandler.startElement(null, "Papa", "papas", null);
         System.out.println("Done starting element ...");
         contentHandler.endElement(null, "Papa", "papas");
         System.out.println("Done ending element ...");
 
         contentHandler.endDocument();
         System.out.println("Done closing doc ...");
 
 
         } catch(org.xml.sax.SAXException saxExec) {
 
         javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), saxExec.getMessage());
 
         this.dispose();
         }
         */
        printNodeList(rootElement);

    }

    public void printNodeList(org.w3c.dom.Element rootElement) {

        org.w3c.dom.NodeList docNodeList = rootElement.getChildNodes();

        //        if (rootElement.getClass().getName().match ) {
        //        System.out.println("Printing Node List."+docNodeList.getLength());
        for (int i = 0; i < docNodeList.getLength(); i++) {

            System.out.println("Node type [" + docNodeList.item(i).getNodeType() + "]");

            if (docNodeList.item(i).getNodeType() != org.w3c.dom.Document.NOTATION_NODE) {

                System.out.println(docNodeList.item(i).getNodeValue());

                if (docNodeList.item(i).hasChildNodes()) {

                    printNodeList((org.w3c.dom.Element) docNodeList.item(i));

                }
            }
        }
    }

    private java.lang.Object[] getTableColumns(javax.swing.JTable exportedTable) {

        java.util.Vector columnVector = new java.util.Vector(1, 1);

        java.lang.Object[] columnArray = null;

        java.util.Enumeration columnsEnumeration = exportedTable.getColumnModel().getColumns();

        while (columnsEnumeration.hasMoreElements()) {

            columnVector.addElement(columnsEnumeration.nextElement());

        }

        columnArray = columnVector.toArray();

        return columnArray;
    }

}
