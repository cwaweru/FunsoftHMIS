/*
 * AgeingSeries.java
 *
 * Created on September 30, 2004, 1:31 PM
 */

package com.afrisoftech.timeseries1;

/**
 *
 * @author  root
 */
public class DailyAgeing {
    
    int noOfIterations = 0;
    
    java.util.Date endDate = null;
    
    /** Creates a new instance of AgeingSeries */
    public DailyAgeing(int iterations, java.util.Date enddate) {
        
        noOfIterations = iterations;
        
        endDate = enddate;
        
    }
    
    public java.util.Date[][]  getAgeingDateSeries(){
        
        org.jfree.data.time.Day currentDay = new org.jfree.data.time.Day(endDate);
        
        org.jfree.data.time.Month regularDates = new org.jfree.data.time.Month(endDate);
        
        org.jfree.data.time.Year regularYears = new org.jfree.data.time.Year(endDate);
        
        org.jfree.data.time.RegularTimePeriod days = currentDay;
        
        java.util.Date targetDateArray[][] = new java.util.Date[noOfIterations][2];
        
        targetDateArray[0][1] = new java.util.Date(currentDay.toString());
        
        System.out.println("Printing current date ["+currentDay.toString()+"]");
        
        
        targetDateArray[0][0] = currentDay.getStart();
        
        System.out.println("Printing start date ["+currentDay.getStart().toString()+"]");
        
        for (int j = 1; j < noOfIterations; j++) {
            
            days = days.previous();
            
            for (int k = 0; k < 2; k++) {
                
                if (k == 0) {
                    
                    targetDateArray[j][k] = days.getStart();
                    
                } else {
                    
                    targetDateArray[j][k] = days.getEnd();
                    
                }
                
            }
            
        }
        
        printDatesArray(targetDateArray);
        
        return targetDateArray;
        
    }
    
    public void printDatesArray(java.util.Date[][] datesArray){
        
        for (int i = 0; i < datesArray.length; i++) {
            
            System.out.println("First Date : ["+datesArray[i][0].toString()+"] and Second date : ["+datesArray[i][1].toString()+"]");
            
        }
        
    }
    
    
    
}
