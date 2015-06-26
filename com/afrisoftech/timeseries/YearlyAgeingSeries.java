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
public class YearlyAgeingSeries {
    
    int noOfIterations = 0;
    
    java.util.Date endDate = null;
    
    /** Creates a new instance of AgeingSeries */
    public YearlyAgeingSeries(int iterations, java.util.Date enddate) {
        
        noOfIterations = iterations;
        
        endDate = enddate;
        
    }
    
    public java.util.Date[][]  getAgeingDateSeries(){
        
        org.jfree.data.time.Day currentDay = new org.jfree.data.time.Day(endDate);
        
        org.jfree.data.time.Month regularDates = new org.jfree.data.time.Month(endDate);
        
        org.jfree.data.time.RegularTimePeriod months = regularDates;
        
        
        java.util.Date targetDateArray[][] = new java.util.Date[noOfIterations][2];
        
        
        // for (int j = 1; j < noOfIterations; j++) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(months.getStart());
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal2.setTime(months.getEnd());
        for (int j = noOfIterations - 1; j >= 0; j--) {
            
            if (j == noOfIterations - 1) {
                
                targetDateArray[targetDateArray.length - 1][1] = new java.util.Date(currentDay.toString());
                
                //   targetDateArray[0][0] = regularDates.getStart();
                
                targetDateArray[targetDateArray.length - 1][0] = regularDates.getStart();
                
                
            } else {
                
                for (int k = 0; k < 2; k++) {
                    
                    if (k == 0) {
                        
                        
                        cal.roll(cal.YEAR, false);
                        targetDateArray[j][k] = cal.getTime();
                        
                    } else {
                        
                        cal2.roll(cal.YEAR, false);
                        targetDateArray[j][k] = cal2.getTime();
                        
                    }
                    
                }
            }
            
        }
        
        for(int i = 0; i < targetDateArray.length; i++) {
            
            for (int j = 0; j < targetDateArray[i].length; j++) {
                
                System.out.print("Data Values are : ["+targetDateArray[i][j]+"]");
                
            }
            System.out.println();
            
            
        }
        
        return targetDateArray;
        
    }
    
    
}
