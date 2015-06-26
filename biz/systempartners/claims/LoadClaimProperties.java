/*
 * LoadClaimProperties.java
 *
 * Created on October 11, 2004, 6:18 PM
 */

package biz.systempartners.claims;

/**
 *
 * @author  root
 */
public class LoadClaimProperties {
    
    java.util.Properties claimProperties;
    
    java.lang.String firstArg;
    
    java.lang.String protocolUrl;
    
    java.lang.String secondArg;
    
    java.lang.String mailBox;
    
    java.lang.String mailHostServer;
    
    java.lang.String mailLogonName;
    
    java.lang.String mailLogonPassword;
    
    java.lang.String pollFrequency;
    
    java.lang.String mailProtocol;
    
    java.lang.String defaultlnf;
    
    java.lang.String defaulttheme;
    
    /** Creates a new instance of LoadClaimProperties */
    public LoadClaimProperties() {

    }
    
    public void loadProperties() {
        
        java.lang.String myAppFileUrl = null;
        
        myAppFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "claimsbackend.properties";
        
        try {
            
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);
            
            
            
            //       java.io.FileOutputStream propOutFile = new java.io.FileOutputStream("myapp.properties");
            
            java.util.Properties appProp = new java.util.Properties();
            
            try {
                System.out.println("Properties file : "+myAppFileUrl);
                
                appProp.load(propInFile);
                
                firstArg = appProp.getProperty("first_arg", "-L");
                
                System.setProperty("first_arg", firstArg);
                
                secondArg = appProp.getProperty("second_arg", "-f");
                
                System.setProperty("second_arg", secondArg);
                
                mailBox = appProp.getProperty("mail_box", "inbox");
                
                System.setProperty("mail_box", mailBox);
                
                pollFrequency = appProp.getProperty("poll_frequency", "5000");
                
                System.setProperty("poll_frequency", pollFrequency);
                
                mailHostServer = appProp.getProperty("mail_host_server", "192.168.0.70");
                
                System.setProperty("mail_host_server", mailHostServer);
                
                mailLogonName = appProp.getProperty("mail_logon_name", "claimsaddress");
                
                System.setProperty("mail_logon_name", mailLogonName);
                
                defaultlnf = appProp.getProperty("default_looknfeel", "com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
                
                System.setProperty("defaultlnf", defaultlnf);
                
                defaulttheme = appProp.getProperty("defaulttheme", "xplunathemepack.zip");
                
                System.setProperty("defaulttheme", defaulttheme);
                
                mailProtocol = appProp.getProperty("mail_protocol", "pop3");
                
                System.setProperty("mail_protocol", mailProtocol);
                
/*              cashpoint = appProp.getProperty("cashpoint");
 
                docsdir = appProp.getProperty("docsdir");
 
 
                papersize_width = appProp.getProperty("papersize_width", java.lang.String.valueOf(270));
 
 
                papersize_legnth = appProp.getProperty("papersize_legnth", java.lang.String.valueOf(270));
 
                System.setProperty("papersize_legnth", papersize_legnth);
 
                font_type_name = appProp.getProperty("font_type_name", "monospaced.plain");
 
                System.setProperty("backgrdimg",backgrdimg);
 
 
 
                System.setProperty("acrobatpath",path2Acrobat);
 
                System.setProperty("activedatabase", "MEDIC");
 
                System.out.println(dbServerIp);
 
                defaultSplitPane = appProp.getProperty("defaultsplitpane", "Hospital Operations");
 
                System.setProperty("defaultsplitpane", defaultSplitPane);
 
                defaultInvoiceDataDir = appProp.getProperty("defaultInvoiceDatadir");
 
                System.setProperty("defaultInvoiceDatadir", defaultInvoiceDataDir);
 
                String email2Claims = appProp.getProperty("claims.email.address");
 
                System.setProperty("claims.email.address", email2Claims);
 
                String fromEmailAddress = appProp.getProperty("claims.from.address");
 
                System.setProperty("claims.from.address", fromEmailAddress);
 
                String claimsHostAddress = appProp.getProperty("claims.host.address");
 
                System.setProperty("claims.host.address", claimsHostAddress );
 
               String debugBoolean = appProp.getProperty("claims.debug.boolean");
 
                System.setProperty("claims.debug.boolean", debugBoolean);
 
 */
               System.out.println("Loaded all properties ...");
                
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
    
    
    
}
