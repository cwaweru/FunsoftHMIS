/*
 * DocumentPrintOut.java
 *
 * Created on August 16, 2003, 6:10 PM
 */

package com.afrisoftech.lib;

/**
 *
 * @author  root
 */
public class DocumentPrintOut {
    
    /** Creates a new instance of DocumentPrintOut */
    public DocumentPrintOut() {
        
        System.out.println("Printing pdf docs ....");
        
        javax.print.DocFlavor flavor = javax.print.DocFlavor.INPUT_STREAM.POSTSCRIPT;
        
        System.out.println(flavor.getRepresentationClassName());
        
        System.out.println(flavor.getMediaSubtype());
        System.out.println(flavor.getMimeType());
        //        System.out.println(flavor.);
        javax.print.attribute.PrintRequestAttributeSet aset = new javax.print.attribute.HashPrintRequestAttributeSet();
        
        aset.add(javax.print.attribute.standard.MediaSizeName.ISO_A4);
        
        //        asset.add(javax.print.attribute.standard.MediaSize.findMedia(11.9,8.3,javax.print.attribute.standard.MediaSize.INCH));
        
        // aset.add(javax.print.attribute.standard.MediaSize.findMedia(,);
        
        javax.print.PrintService[] pservices = javax.print.PrintServiceLookup.lookupPrintServices(flavor, aset);
        
        System.out.println("Print Services available are "+pservices.length);
        
        for (int i = 0; i < pservices.length; i++) {
            
            System.out.println("Print Services available are ("+(i+1)+") "+pservices[i].toString());
            
            javax.print.DocFlavor docFlavorsSupported[] = pservices[i].getSupportedDocFlavors();
            
            for (int j = 0; j < docFlavorsSupported.length; j++) {
                
                System.out.println("Doc Flavors for "+pservices[i].toString()+" available are ("+(j+1)+") "+docFlavorsSupported[j].toString());
                
            }
            
        }
        
        
        if (pservices.length > 0) {
            
            javax.print.DocPrintJob pj = pservices[0].createPrintJob();
            
            //javax.print.Doc doc = new java.io.InputStreamDoc("test.ps", flavor);
            try {
                
                java.io.File file = new java.io.File("/root/sample.pdf");
                
                javax.print.SimpleDoc doc = new javax.print.SimpleDoc(new java.io.BufferedInputStream(new java.io.FileInputStream(file)),flavor, null);
                
                try {
                    
                    pj.print(doc, aset);
                    
                } catch(javax.print.PrintException prexec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), prexec.getMessage());
                    
                }
                
            } catch(java.io.FileNotFoundException fnf) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnf.getMessage());
                
            }
            
            
        }
    }
    
    public static void main(java.lang.String args[]) {
        
        DocumentPrintOut docPrint = new DocumentPrintOut();
        
        try {
            
            docPrint.finalize();
            
            //  this.
            
        } catch(java.lang.Throwable thr) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), thr.getMessage());
            
        }
        
    }
}
