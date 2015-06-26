//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package biz.systempartners.claims;

public class SampleVacuumDB implements java.lang.Runnable {
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void SampleVacuumDB() {
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        new SampleVacuumDB().SampleVacuumDB();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.vacuumDB();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                //			prThread.waitFor();
                
                //                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "The backup task has completed successfully.");
                
                
                java.lang.Thread.currentThread().sleep(2000);
                
                System.out.println("It's time for us threads to get back to work after the nap");
                
            } catch(java.lang.InterruptedException IntExec) {
                
                System.out.println(IntExec.getMessage());
                
            }
            
            //			java.lang.Thread.currentThread().sleep(2000);
            
            threadCheck = false;
            
            
            System.out.println("We shall be lucky to get back to start in one piece");
            
        }
        
        if (!threadCheck) {
            
            
            
            Thread.currentThread().stop();
            
            
            //			this.dispose();
            
            
        }
        
        //		this.equals(null);
    }
    
    public java.lang.String getDateLable() {
        
        java.lang.String date_label = null;
        
        java.lang.String month_now_strs = null;
        
        java.lang.String date_now_strs = null;
        
        java.lang.String year_now_strs = null;
        
        java.lang.String minute_now_strs = null;
        
        java.lang.String hour_now_strs = null;
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        java.util.Calendar calinst = java.util.Calendar.getInstance();
        
        java.util.Date date_now = calinst.getTime();
        
        int date_now_str = date_now.getDate();
        
        int month_now_str = date_now.getMonth();
        
        int year_now_str = date_now.getYear();
        
        int hour_now_str = date_now.getHours();
        
        int minute_now_str = date_now.getMinutes();
        
        int year_now_abs = year_now_str - 100;
        
        if (year_now_abs < 10) {
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    public void vacuumDB(){
        
        java.lang.String path2BackupFiles = null;
        
        path2BackupFiles =  java.lang.System.getProperty("user.dir") +
        
        java.lang.System.getProperty("file.separator");
        
        java.lang.Runtime rt_backup = java.lang.Runtime.getRuntime();
        
        java.lang.String backup_date_stamp = this.getDateLable();
        
        try {
            
            System.out.println("Connecting to the database to run VaccumDB");
            
            java.lang.Process vacuumingDB;
            
            vacuumingDB = rt_backup.exec("vacuumdb -U postgres -d medic");
            
            try {
                
                vacuumingDB.waitFor();
                
            } catch(java.lang.InterruptedException IntruptExec){
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IntruptExec.getLocalizedMessage());
                
            }
            
            int vacuumingDB_return = vacuumingDB.exitValue();
            
            
            if (vacuumingDB_return == 0) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "VaccumDB of the MEDIC database successfully completed on "+backup_date_stamp);
                
            } else {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "VacuumDB of the MEDIC database failed at "+backup_date_stamp);
                
            }
            
        } catch(java.io.IOException IOExec){
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Sorry, can't get path to Help utility", "Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        
    }
    
    public void stop() {
        
        Thread.currentThread().stop();
        
    }
    
    
}





