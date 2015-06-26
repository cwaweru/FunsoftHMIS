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
public class YearyAgeing {
    
    int noOfIterations = 0;
    
    java.util.Date endDate = null;
    
    /** Creates a new instance of AgeingSeries */
    public YearyAgeing(int iterations, java.util.Date enddate) {
        
        noOfIterations = iterations;
        
        endDate = enddate;
        
    }
    
    public java.util.Date[][]  getAgeingDateSeries(){
        
        org.jfree.data.time.Day currentDay = new org.jfree.data.time.Day(endDate);
        
        org.jfree.data.time.Month regularDates = new org.jfree.data.time.Month(endDate);
        
        org.jfree.data.time.Year regularYears = new org.jfree.data.time.Year(endDate);
        
        org.jfree.data.time.RegularTimePeriod years = regularYears;
        
        java.util.Date targetDateArray[][] = new java.util.Date[noOfIterations][2];
        
        targetDateArray[0][1] = new java.util.Date(currentDay.toString());
        
        System.out.println("Printing current date ["+currentDay.toString()+"]");
        
         targetDateArray[targetDateArray.length - 1][0] = regularYears.getStart();
       
      //  targetDateArray[0][0] = regularYears.getStart();

        System.out.println("Printing start date ["+regularYears.getStart().toString()+"]");        
       
        
          for (int j = noOfIterations - 1; j >= 0; j--) {  
            
            if (j != noOfIterations -1){
                
            years = years.previous();
            }
        //for (int j = 1; j < noOfIterations; j++) {
            
        //    years = years.previous();
            
            for (int k = 0; k < 2; k++) {
                
                if (k == 0) {
                    
                    targetDateArray[j][k] = years.getStart();
                    
                } else {
                    
                    targetDateArray[j][k] = years.getEnd();
                    
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
