/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import static com.afrisoftech.hospital.HospitalMain.PDFViewerIntfr;
import static com.afrisoftech.hospital.HospitalMain.saccopn;

/**
 *
 * @author SPL7
 */
public class PDFRenderer {

    public static void renderPDF(java.io.File fileName) {

        org.icepdf.ri.common.SwingController controller = new org.icepdf.ri.common.SwingController();

        org.icepdf.ri.common.SwingViewBuilder factory = new org.icepdf.ri.common.SwingViewBuilder(controller);

        javax.swing.JPanel viewerComponentPanel = factory.buildViewerPanel();

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
}
