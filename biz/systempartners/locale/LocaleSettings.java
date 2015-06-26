/*
 * LocaleSettings.java
 *
 * Created on April 28, 2007, 8:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.locale;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jfree.xml.factory.objects.JavaBaseClassFactory;

/**
 *
 * @author funsoft
 */
public class LocaleSettings {
    
    java.io.File resourceFile = null;
    
    java.util.Properties localeResource = null;
    
    javax.swing.JTable resourceEditTable = null;
    /** Creates a new instance of LocaleSettings */
    public LocaleSettings() {
    }
    
    protected javax.swing.JTable loadResourceBundle() {
        
        javax.swing.JFileChooser resourceChooser = new javax.swing.JFileChooser();
        
        resourceChooser.showOpenDialog(new java.awt.Frame());
        
        resourceFile = resourceChooser.getSelectedFile();
        
        java.io.BufferedInputStream resourceIO = null;
        
        localeResource = new java.util.Properties();
        
        try {
            
            try {
                
                resourceIO =  new java.io.BufferedInputStream(new java.io.FileInputStream(resourceFile));
                localeResource.load(resourceIO);
                
            } catch(java.io.FileNotFoundException fnfEx){
                fnfEx.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), fnfEx.getMessage());
            }
        } catch(java.io.IOException ioEx){
            ioEx.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
        }
        
        //       javax.swing.JTable resourceEditTable = new javax.swing.JTable();
        
        java.util.Enumeration keysEnum = localeResource.keys();
        resourceEditTable = new javax.swing.JTable(localeResource.size(),2);
        int i = 0;
        
        while(keysEnum.hasMoreElements()){
            String key = keysEnum.nextElement().toString();
            resourceEditTable.setValueAt(key,i,0);
            resourceEditTable.setValueAt(localeResource.getProperty(key),i,1);
            i++;
        }
        try {
            
            resourceIO.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return resourceEditTable;
        
    }
    
    protected void saveResourceBundle() throws IOException{
        try {
            
            java.io.BufferedOutputStream resourceOutStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(resourceFile));
        
            for(int i = 0; i < resourceEditTable.getRowCount(); i++){
                
                localeResource.setProperty(resourceEditTable.getValueAt(i,0).toString(),resourceEditTable.getValueAt(i,1).toString());
                
                
            }   
            
            try{
            
                localeResource.store(resourceOutStream,"");
            
            } catch(java.io.IOException ioEx){
                ioEx.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioEx.getMessage());
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
        }
        
    }
    
}
