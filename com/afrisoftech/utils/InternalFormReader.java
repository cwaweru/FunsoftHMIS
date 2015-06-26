/*
 * InternalFormReader.java
 *
 * Created on October 27, 2003, 10:03 PM
 */

package com.afrisoftech.utils;

/**
 *
 * @author  root
 */
public class InternalFormReader {
    
    java.util.jar.JarFile jarFile;
    
    java.util.Enumeration jarFileEnumeration = null;
    
    /** Creates a new instance of InternalFormReader */
    public InternalFormReader() {
        
        try {
        
       
            jarFile = new java.util.jar.JarFile(new java.io.File("/root/ffjuser40ce_28072003/hospital_021003.jar"), false);
        
            jarFileEnumeration = jarFile.entries();
            
            int i = 0;
            
            for (jarFileEnumeration.nextElement(); jarFileEnumeration.hasMoreElements();) {
                
                if (jarFileEnumeration.nextElement().toString().endsWith("ntfr.class")){
                
                    System.out.println(jarFileEnumeration.nextElement().toString());
                
                    i++;
                }
            }
            
            System.out.println("Found "+i+" matching entries.");

       
        } catch (java.io.IOException ioExec) {
         ioExec.printStackTrace();
           // javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.printStackTrace());
            
        }
    }
    
    public static void main(java.lang.String args[]) {
     
        new InternalFormReader();
        
    }
    
}
