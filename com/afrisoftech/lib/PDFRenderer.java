/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import static com.afrisoftech.hospital.HospitalMain.PDFViewerIntfr;
import static com.afrisoftech.hospital.HospitalMain.saccopn;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author SPL7
 */
public class PDFRenderer {

    public static javax.swing.JPanel viewerComponentPanel;
    
    static java.io.File file2Export = null;

    public static void renderPDF(java.io.File fileName) {
        
        file2Export = fileName;

        org.icepdf.ri.common.SwingController controller = new org.icepdf.ri.common.SwingController();

        // controller.getViewerFrame().getContentPane().add
        org.icepdf.ri.common.SwingViewBuilder factory = new org.icepdf.ri.common.SwingViewBuilder(controller);

        javax.swing.JPanel viewerComponentPanel = factory.buildViewerPanel();

        JToolBar toolBar = factory.buildCompleteToolBar(true);
        toolBar.addSeparator();
        com.afrisoftech.lib.IconFactory imageFactory = new com.afrisoftech.lib.IconFactory();

        javax.swing.ImageIcon iconImage = imageFactory.getIconImage();

        javax.swing.JButton excelButton = new JButton();
        excelButton.setIcon(iconImage);
        excelButton.setToolTipText("Export document to Excel");
        excelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    com.afrisoftech.lib.PDF2ExcelConverter.convertPDf2Excel(file2Export.getPath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        toolBar.add(excelButton);

        viewerComponentPanel.add(toolBar, BorderLayout.NORTH);

        //  viewerComponentPanel.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getParent().getSize());
        org.icepdf.ri.common.ComponentKeyBinding.install(controller, viewerComponentPanel);

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        com.afrisoftech.lib.PDFReportViewer pdfViewer = new com.afrisoftech.lib.PDFReportViewer();

        pdfViewer.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        // gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        pdfViewer.getContentPane().add(viewerComponentPanel, gridBagConstraints);

        controller.openDocument(fileName.getPath());

        pdfViewer.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getParent().getSize());;

        pdfViewer.setVisible(true);

        saccopn.add(pdfViewer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        try {

            pdfViewer.setSelected(true);

            System.out.println("Show PDF viewer...");
        } catch (java.beans.PropertyVetoException pvt) {
        }

    }
    
    public static javax.swing.JPanel renderPDF1(java.io.File fileName, javax.swing.JPanel component) {
        
        
        //  viewerComponentPanel = component;
        org.icepdf.ri.common.SwingController controller = new org.icepdf.ri.common.SwingController();
        
        com.afrisoftech.hr.HRDocumentManager.controller = controller;

        org.icepdf.ri.common.SwingViewBuilder factory = new org.icepdf.ri.common.SwingViewBuilder(controller);

        component = factory.buildViewerPanel();

        //factory.
        org.icepdf.ri.common.ComponentKeyBinding.install(controller, component);

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        controller.openDocument(fileName.getPath());

        return component;
    }

    public static void renderPDF(java.io.File fileName, java.awt.Component component) {

        org.icepdf.ri.common.SwingController controller = new org.icepdf.ri.common.SwingController();

        org.icepdf.ri.common.SwingViewBuilder factory = new org.icepdf.ri.common.SwingViewBuilder(controller);

        component = factory.buildViewerPanel();

        //factory.
        org.icepdf.ri.common.ComponentKeyBinding.install(controller, viewerComponentPanel);

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        controller.openDocument(fileName.getPath());

        //  return viewerComponentPanel;
    }
}
