/*
 * FunsoftInstaller.java
 *
 * Created on December 9, 2005, 3:50 PM
 *
 */

package com.afrisoftech.installer;

/**
 *
 * @author  Charles Wanjema Waweru <cwaweru@systempartners.biz> 
 *
 *
 */
public class FunsoftInstaller {

    java.lang.String noText = "";
    
    java.lang.String dbServerIp;
    
    java.sql.Connection connDB = null;
    
    java.lang.Thread loginThread;
    
    com.afrisoftech.sys.SplashScreenDialog splashScreen;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB = null;
    
    com.afrisoftech.hospital.HospitalMain hospitalInst;
    
    java.lang.String path2Acrobat;
    
    java.lang.String defaultlnf;
    
    java.lang.String cashpoint;
    
    java.lang.String papersize_width;
    
    java.lang.String papersize_legnth;
    
    java.lang.String backgrdimg;
    
    java.lang.String defaulttheme;
    
    java.lang.String dbPort;
    
    java.lang.String font_type_name;
    
    java.lang.String activeDatabase;
    
    java.lang.String receiptFontSize;
    
    java.lang.String receiptTitleFontSize;
    
    java.lang.String receiptPageMargin;
    
    java.util.Properties sysProp;
    
    java.lang.String docsdir;
    
    java.lang.String defaultSplitPane;
    
    private org.netbeans.lib.sql.PasswordInfo pwdInfo;
    
    private java.lang.String userName;
    
    
//    private ProgressThread progressThread;
    
    public static Thread login2HospitalThread;
    
    public String phraseSeparator;
    
    public String lineCharacter;
    
    public String dottedLineCharacter;
    
    public String newLineCharacter;
    
    public String linesPerPage;
                
    public String charactersPerLine;
    
    public String rcptLinesPerPage;
    
    public String rcptCharsPerPage;
    
    public String defaultPrinter;
    
    public String postgresInstallerFileName; 
    
    public String acrobatInstallerFileName;
    
    protected javax.swing.JProgressBar splashProgressBar;
    
    com.afrisoftech.sys.Login2Hospital login2Hospital;
    
    boolean splashStart = true;
    
    boolean starting = true;
    
//    java.lang.String backgrdimg;
    
    javax.swing.ImageIcon desktopPaneIcon; 
    //java.util.Properties sysProp;
        
    /** Creates a new instance of FunsoftInstaller */
    public FunsoftInstaller() {
        
        java.lang.String installerPropertyFileUrl = null;
        
        installerPropertyFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "funsoft_installer.properties";
        
        try {
            //catch java.lang.ClassNotFoundException(this.show(This is not true));     
            java.io.FileInputStream propInFile = new java.io.FileInputStream(installerPropertyFileUrl);
            
            
            
            //       java.io.FileOutputStream propOutFile = new java.io.FileOutputStream("myapp.properties");
            
            java.util.Properties appProp = new java.util.Properties();
            
            try {
                System.out.println("Properties file : "+installerPropertyFileUrl);
                
                appProp.load(propInFile);
                
                dbServerIp = appProp.getProperty("dbServerIpAdd", "127.0.0.1");
                
                System.out.println("Database Server IP : "+dbServerIp);
                
                activeDatabase = appProp.getProperty("activeDatabase");
                
                System.setProperty("activeDatabase", activeDatabase);
                
                receiptPageMargin = appProp.getProperty("receiptPageMargin", "0");
                
                System.setProperty("receiptPageMargin", receiptPageMargin);
                
                receiptFontSize = appProp.getProperty("receiptFontSize", "10");
                
                System.setProperty("receiptFontSize", this.receiptFontSize);
                
                receiptTitleFontSize = appProp.getProperty("receiptTitleFontSize", "12");
                
                System.setProperty("receiptTitleFontSize", this.receiptTitleFontSize);
                
                dbPort = appProp.getProperty("dbPort", "5432");
                
                System.setProperty("dbPort", dbPort);
                
                path2Acrobat = appProp.getProperty("path2Acrobat", "c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe");
                
                defaultlnf = appProp.getProperty("defaultlnf", "com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
                
                backgrdimg = appProp.getProperty("backgrdimg", "c:/Tests/clouds.jpg");
                
                cashpoint = appProp.getProperty("cashpoint");
                
                docsdir = appProp.getProperty("docsdir");
                
                defaultPrinter = appProp.getProperty("defaultprinter","PRN");
                
                System.setProperty("docsdir", docsdir);
                
                System.setProperty("defaultprinter",defaultPrinter);
                
                papersize_width = appProp.getProperty("papersize_width", java.lang.String.valueOf(270));
                
                System.setProperty("papersize_width", papersize_width);
                
                papersize_legnth = appProp.getProperty("papersize_legnth", java.lang.String.valueOf(270));
                
                System.setProperty("papersize_legnth", papersize_legnth);
                
                font_type_name = appProp.getProperty("font_type_name", "monospaced.plain");
                
                System.setProperty("backgrdimg",backgrdimg);
                
                
                System.setProperty("defaultlnf", defaultlnf);
                
                                
                postgresInstallerFileName = appProp.getProperty("postgresql_filename", "xplunathemepack.zip");
               
                System.setProperty("postgresql_filename", postgresInstallerFileName);
                                
                acrobatInstallerFileName = appProp.getProperty("acrobat_filename", "xplunathemepack.zip");
               
                System.setProperty("acrobat_filename", acrobatInstallerFileName);
                
                System.out.println("Setting installer filename "+System.getProperty("postgresql_filename"));
                
                defaulttheme = appProp.getProperty("defaulttheme", "xplunathemepack.zip");
                
                System.setProperty("defaulttheme", defaulttheme);
                
                System.setProperty("acrobatpath",path2Acrobat);
                
                System.setProperty("activedatabase", "MEDIC");
                
                System.out.println(dbServerIp);
                
                defaultSplitPane = appProp.getProperty("defaultsplitpane", "Hospital Operations");
                
                System.setProperty("defaultsplitpane", defaultSplitPane);
                
                phraseSeparator = appProp.getProperty("phrase.separator", "  ");
                
                lineCharacter = appProp.getProperty("line.character", "_");
                
                dottedLineCharacter = appProp.getProperty("dotted.line.character", "-");
                
                newLineCharacter = appProp.getProperty("new.line.character", " \n ");
                
                linesPerPage = appProp.getProperty("linesperpage", "62");
                
                charactersPerLine = appProp.getProperty("charactersperline", "70");
                
                rcptLinesPerPage = appProp.getProperty("rcptlinesperpage","16");
                
                rcptCharsPerPage = appProp.getProperty("rcptcharactersperpage","72");                
                 
                System.setProperty("phrase.separator", phraseSeparator);
                
                System.setProperty("line.character", lineCharacter);
                
                System.setProperty("dotted.line.character", dottedLineCharacter);
                
                System.setProperty("new.line.character", newLineCharacter);
                
                System.setProperty("linesperpage",linesPerPage);
                
                System.setProperty("charactersperline",charactersPerLine);
                
                System.setProperty("rcptlinesperpage",rcptLinesPerPage);
                
                System.setProperty("rcptcharactersperline",rcptCharsPerPage);
               
                
                propInFile.close();
                
                //  return dbServerIp;
                
                
            } catch (java.io.IOException ioExec){
                
                System.out.println(ioExec.getMessage());
                
            }
            
            // return dbServerIp;
            
        } catch (java.lang.Exception exc){
            
            System.out.println(exc.getMessage());
            
            //    javax.swing.JOptionPane.showMessageDialog(this, "Properties file not found!");
            
        }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new com.afrisoftech.installer.FunsoftInstaller();
        
        System.out.println("Installer path name "+System.getProperty("postgresql_filename"));
        
        com.afrisoftech.installer.FunsoftInstallerDialog installerDialog = new com.afrisoftech.installer.FunsoftInstallerDialog(new java.awt.Frame(), false);
        
       // installerDialog.setSize(700,600);
        
        installerDialog.setVisible(true);
        
    }
    
}
