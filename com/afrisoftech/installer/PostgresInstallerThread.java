/*
 * PostgresInstallerThread.java
 *
 *  This class installs PostgresSQL ver x.x.x on any machine. It is called by the main Funsoft
 *
 *  installer thread.
 *
 * Created on January 14, 2006, 3:59 PM
 */

package com.afrisoftech.installer;

/**
 *
 * @author  Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class PostgresInstallerThread extends java.lang.Thread  {
    
    private java.lang.Thread installerThread = null;
    
    private java.lang.Process installerProcess = null;
    
    private java.lang.Runtime installerRuntime = null;
    
    java.lang.String path2PostgreSQLInstallBinaries = null;
    
    java.lang.String copyExtendedJavaLibraries = null;
    
    java.lang.String path2AcrobatInstallBinaries = null;
    
    java.lang.String createFunsoftShortcut = null;
    
    java.lang.String copyFunsoftFiles = null;
    
    java.lang.String restoreFunsoftDatabase = null;
    
    java.lang.String createFunsoftDatabase = null;
    
    boolean installerRunning = true;
    
    /** Creates a new instance of PostgresInstallerThread */
    public PostgresInstallerThread(java.lang.String funsoft_install_dir) {
        
        installerRuntime = java.lang.Runtime.getRuntime();
        
        installerThread = new java.lang.Thread(this, "Funsoft Install Thread");
        
        path2PostgreSQLInstallBinaries =  java.lang.System.getProperty("user.dir") +
        
        java.lang.System.getProperty("file.separator")+System.getProperty("postgresql_filename");
        
        path2AcrobatInstallBinaries =  java.lang.System.getProperty("user.dir") +
        
        java.lang.System.getProperty("file.separator")+System.getProperty("acrobat_filename");
        
        restoreFunsoftDatabase = "psql -U postgres -d funsoft -f funsoft_db.sql";
        
        createFunsoftDatabase = "createdb -E sql_ascii -U postgres funsoft";
        
        copyFunsoftFiles = "xcopy /E funsoft "+funsoft_install_dir;
        
        copyExtendedJavaLibraries = "xcopy /E ext "+System.getProperty("java.home");
        
        
        
        
        
        
        
    }
    
    /**
     *
     *  This method is called by the startThread() method in this class to initialise the installation of the PostgreSQL run time
     *  binaries.
     *
     */
    
    public void run() {
        
        System.out.println("Installer thread has entered running mode");
        
        while (installerRunning) {
            
            System.out.println("O.K. see how we run the installer");
            
            this.startInstallation(path2PostgreSQLInstallBinaries);
            this.startInstallation(restoreFunsoftDatabase);
            this.startInstallation(copyFunsoftFiles);
            this.startInstallation(path2AcrobatInstallBinaries);
            this.startInstallation(copyExtendedJavaLibraries);
         //   this.startInstallation(createFunsoftShortcut);
            
            try {
                
                System.out.println("Right, let's wait for the installation process to complete or fail");
                
                java.lang.Thread.currentThread().sleep(100);
                
                System.out.println("It's time for us threads to get back to work after the nap");
                
            } catch(java.lang.InterruptedException IntExec) {
                
                System.out.println(IntExec.getMessage());
                
            }
            
            
            installerRunning = false;
            
            
            System.out.println("We shall be lucky to get back to start in one piece");
            
        }
        
        if (!installerRunning) {
            
            this.stopThread();
            
        }
        
    }
    
    /**
     *
     *  This method calls the run() thread to actually start the PostgreSQL installation.
     *
     */
    public void startThread(){
        
        if(installerThread != null){
            
            installerThread.start();
            
        }
        
    }
    
    /**
     *
     *  This method stops the PostgreSQL installation thread. It also clears any garbage
     *  left behind by the postgres install process.
     *
     */
    public void stopThread(){
        
        installerThread.stop();
        
        installerThread.destroy();
        
    }
    
    private int startInstallation(java.lang.String installCommand){
        
        int installer_return = 0;
        
        java.lang.String path2PostgreSQLInstallBinaries = null;
        
        path2PostgreSQLInstallBinaries =  java.lang.System.getProperty("user.dir") +
        
        java.lang.System.getProperty("file.separator")+System.getProperty("postgresql_filename");
        
        try {
            
            System.out.println("Organising to start installation of the PostgreSQL database management system ...");
            
            installerProcess = installerRuntime.exec("cmd /C "+installCommand);
            
            try {
                
                installerProcess.waitFor();
                
            } catch(java.lang.InterruptedException IntruptExec){
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IntruptExec.getLocalizedMessage());
                
            }
            
            installer_return = installerProcess.exitValue();
            
           /*
            if (installer_return == 0) {
            
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Installation process for PostgreSQL successfully completed");
            
            } else {
            
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Installation process for PostgreSQL has failed! Please consult the nearest Funsoft system support team.");
            
            }
            */
            
        } catch(java.io.IOException IOExec){
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOExec.getMessage(), "Installation Error Message", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
        
        return installer_return;
        
    }
    
}
