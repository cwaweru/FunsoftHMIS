/*
 * License.java
 *
 * Created on August 5, 2004, 5:39 PM
 */

package com.afrisoftech.lic;

/**
 *
 * @author  root
 */
public class License {
    
    /** Creates a new instance of License */
    public License(java.util.Properties licProps) {
        
       licProps.put("license", "myfiles");
        // licProps.put(new java.net.URL("Licfile"), new java.lang.String(getClass().getResource("/licence")));
        
    }
    
}
