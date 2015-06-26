/*
 * AgeingSeries.java
 *
 * Created on September 30, 2004, 1:31 PM
 */

package com.afrisoftech.timeseries;

/**
 *
 * @author  root
 */
public class AgeingSeries {
    
    int noOfIterations = 0;
    
    java.util.Date endDate = null;
    
    /** Creates a new instance of AgeingSeries */
    public AgeingSeries(int iterations, java.util.Date enddate) {
        
        noOfIterations = iterations;
        
        endDate = enddate;
        
    }
    
    public java.util.Date[][]  getAgeingDateSeries(){
        
        org.jfree.data.time.Day currentDay = new org.jfree.data.time.Day(endDate);
        
        org.jfree.data.time.Month regularDates = new org.jfree.data.time.Month(endDate);
        
        org.jfree.data.time.RegularTimePeriod months = regularDates;
        
        java.util.Date targetDateArray[][] = new java.util.Date[noOfIterations][2];
        
        targetDateArray[0][1] = new java.util.Date(currentDay.toString());
        
     //   targetDateArray[0][0] = regularDates.getStart();
        
         targetDateArray[targetDateArray.length - 1][0] = regularDates.getStart();
        
       // for (int j = 1; j < noOfIterations; j++) {
         for (int j = noOfIterations - 1; j >= 0; j--) {            
           
             if (j != noOfIterations -1) {
             
            months = months.previous();
            
             }
            
            for (int k = 0; k < 2; k++) {
                
                if (k == 0) {
                    
                    targetDateArray[j][k] = months.getStart();
                    
                } else {
                    
                    targetDateArray[j][k] = months.getEnd();
                    
                }
                
            }
            
        }
        
        return targetDateArray;
        
    }
    
    
}
