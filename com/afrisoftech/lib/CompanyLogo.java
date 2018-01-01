/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**
 *
 * @author Charles
 */
public class CompanyLogo {
    
    public static String getPath2Logo(){
        
        String path2Logo = null;
        
        String fileName = null;
        
        path2Logo = System.getProperty("user.dir")+System.getProperty("file.separator")+"COMPANY-LOGO.jpg";
        
        return path2Logo;
        
    }
    
}
