/*
 * @(#)monitor.java	1.8 03/04/22
 *
 * Copyright 1996-2003 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES OR LIABILITIES
 * SUFFERED BY LICENSEE AS A RESULT OF  OR RELATING TO USE, MODIFICATION
 * OR DISTRIBUTION OF THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

package biz.systempartners.claims;

import java.util.*;
import java.io.*;
import java.security.Security;
import javax.mail.*;
import javax.mail.event.*;
import javax.activation.*;

/* Monitors given mailbox for new mail */

public class Monitor {
    
    private java.lang.String myAppFileUrl = null;
    
    private java.lang.String passwordFileUrl = null;
    
    private java.lang.String passWord;
    
    private biz.systempartners.claims.LoadClaimProperties loadClaimProperties;
    
    
    //    boolean msgShow = false;
    
    //    String arg[] = {"-L", "pop3://waweru:pilsiner@192.168.0.70", "-f", "inbox", "1"};
    
    //    static java.lang.String host = "192.168.0.70", user = "waweru", password = "pilsiner", mbox = "inbox", freqs = "5000";
    
    biz.systempartners.claims.ClaimsMsgShow msgShow;
    
    public static void main(String argv[]) {
        
        Monitor claimsMonitor = new Monitor();
        
        try {
        
        claimsMonitor.Monitor(new biz.systempartners.claims.ClaimsViewer());
        
        } catch (java.lang.ClassNotFoundException cnfExcep) {
         
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), cnfExcep.getMessage(), "ERROR : Class not found!", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public void Monitor(biz.systempartners.claims.ClaimsViewer claimsViewer) throws java.lang.ClassNotFoundException {
        
        loadClaimProperties = new biz.systempartners.claims.LoadClaimProperties();
        
        loadClaimProperties.loadProperties();
        
        myAppFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "claimsbackend.properties";
        
        passwordFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "password.pwd";

      //  Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());


        try {
          
            java.io.FileInputStream propInFile = new java.io.FileInputStream(myAppFileUrl);
            
            java.io.FileInputStream passwordInFile = new java.io.FileInputStream(passwordFileUrl);
                
            java.io.ObjectInputStream passwordObjInStream = new java.io.ObjectInputStream(passwordInFile);
                
                
                //                javax.swing.table.JTableHeader claimsTableHeader = (javax.swing.table.JTableHeader)requisObjInStream.readObject();
                
                passWord = (java.lang.String)passwordObjInStream.readObject();  
               
                System.setProperty("mail_password", passWord);
                
        } catch (java.io.IOException ioExec){
            
            System.out.println(ioExec.getMessage());
            
        }
        
        String arg[] = {System.getProperty("first_arg"), System.getProperty("mail_protocol")+"://"+System.getProperty("mail_logon_name")+":"+System.getProperty("mail_password")+"@"+System.getProperty("mail_host_server")+":"+System.getProperty("pop.port.number"), System.getProperty("second_arg"),  System.getProperty("mail_box"), "1"};
        
        java.lang.String host = System.getProperty("mail_host_server", "localhost"), user = System.getProperty("mail_logon_name", "claims"), password = System.getProperty("mail_password","claims"), mbox = System.getProperty("mail_box"), freqs = System.getProperty("poll_frequency");
        
        System.out.println("Loaded all properties ...");
        
        msgShow = new biz.systempartners.claims.ClaimsMsgShow();
        
        
        /*if (argv.length != 5) {
           System.out.println("Usage: monitor <host> <user> <password> <mbox> <freq>");
           System.exit(1);
        }*/
        //	System.out.println("\nTesting monitor\n");
        
        try {
            Properties props = System.getProperties();
            
            // Get a Session object
            Session session = Session.getInstance(props, null);
            // session.setDebug(true);
            
            // Get a Store object
            Store store = session.getStore("pop3");
            
            // Connect
            // store.connect(argv[0], argv[1], argv[2]);
      ////      store.connect(host, user, password);
                store.connect(host, user = System.getProperty("mail_logon_name", "claims"), password = System.getProperty("mail_password","claims")); 
            // Open a Folder
            Folder folder = store.getFolder(mbox);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder");
                System.exit(1);
            }
            
            //  folder.open(Folder.READ_WRITE);
            
            // Add messageCountListener to listen for new messages
            folder.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
                    System.out.println("Got " + msgs.length + " new messages");
                    
                    // Just dump out the new messages
                    for (int i = 0; i < msgs.length; i++) {
                        try {
                            DataHandler dh = msgs[i].getDataHandler();
                            InputStream is = dh.getInputStream();
                            int c;
                            while ((c = is.read()) != -1)
                                System.out.write(c);
                        } catch (IOException ioex) {
                            ioex.printStackTrace();
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                        }
                    }
                }
            });
            
            // Check mail once in "freq" MILLIseconds
            
            int freq = Integer.parseInt(freqs);
            
            for (; ;) {
                Thread.sleep(freq); // sleep for freq milliseconds
                System.out.println("Demo running...");
                // This is to force the IMAP server to send us
                // EXISTS notifications.
                folder.open(Folder.READ_WRITE);
                int msgCount = folder.getMessageCount();
                folder.close(false);
                if (msgCount > 0) {
                    
                    if (msgShow == null) {
                        
                        msgShow.ClaimsMsgShow(arg, folder, store, claimsViewer);
                        
                    } else {
                        
                        msgShow.ClaimsMsgShowReady(arg, folder, store, claimsViewer);
                        
                    }
                    //}
                }
                
                System.out.println("Found [ "+folder.getMessageCount()+" ] message(s) in the INBOX.");
                //                folder.close(true);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
