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
public class WeeklyAgeing {
    
    int noOfIterations = 0;
    
    java.util.Date endDate = null;
    
    /** Creates a new instance of AgeingSeries */
    public WeeklyAgeing(int iterations, java.util.Date enddate) {
        
        noOfIterations = iterations;
        
        endDate = enddate;
        
    }
    
    public java.util.Date[][]  getAgeingDateSeries(){
        
        org.jfree.data.time.Day currentDay = new org.jfree.data.time.Day(endDate);
     
        org.jfree.data.time.Week regularWeeks = new org.jfree.data.time.Week(endDate);
       
        regularWeeks.WORKING_CALENDAR.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        
        org.jfree.data.time.RegularTimePeriod weeks = regularWeeks;
        
        weeks.WORKING_CALENDAR.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        
        java.util.Date targetDateArray[][] = new java.util.Date[noOfIterations][2];
        
        targetDateArray[0][1] = new java.util.Date(currentDay.toString());
        
        System.out.println("Printing current date ["+currentDay.toString()+"]");
        
        
       // targetDateArray[0][0] = regularWeeks.getStart();
        
        targetDateArray[targetDateArray.length - 1][0] = regularWeeks.getStart();        

        System.out.println("Printing start date ["+regularWeeks.getStart().toString()+"]");        
        
        
        for (int j = noOfIterations - 1; j >= 0; j--) {
            
            if (j != noOfIterations -1){
                
                weeks = weeks.previous();
            }
       // for (int j = 1; j < noOfIterations; j++) {
            
          //  weeks = weeks.previous();
            
            for (int k = 0; k < 2; k++) {
                
                if (k == 0) {
                    
                    targetDateArray[j][k] = weeks.getStart();
                    
                } else {
                    
                    targetDateArray[j][k] = weeks.getEnd();
                    
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
