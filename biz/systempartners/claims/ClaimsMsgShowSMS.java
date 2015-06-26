/*
 * @(#)msgshow.java	1.30 03/04/22
 *
 * Copyright 1997-2003 Sun Microsystems, Inc. All Rights Reserved.
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
import com.l2fprod.common.util.JVM;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;
import javax.activation.*;

/*
 * Demo app that exercises the Message interfaces.
 * Show information about and contents of messages.
 *
 * @author John Mani
 * @author Bill Shannon
 * @author Charles Wanjema Waweru <cwaweru@systempartners.biz>
 */

public class ClaimsMsgShowSMS {
    static String host = System.getProperty("mail.smtp.host", "localhost");//"192.168.0.105";
    ////      filename = fileName;
    //boolean debug = Boolean.valueOf(System.getProperty("claims.debug.boolean")).booleanValue();
    //     boolean debug = true;
    ////      String msgText1 = "Claim file : ["+fileName+"].\n";
    //     String subject = "Claim INFO : ["+invoiceNo+"]";//"Sending a file";
    
    ////      System.out.println("Sending File : "+fileName);
    
    // create some properties and get the default Session
    //     Properties props = System.getProperties();
    
    
    // Session session = Session.getInstance(props, auth);
    static String protocol;
//    static String host = null;
    static String user = null;
    static String password = "test";
    static String mbox = "INBOX";
    static String url = "pop3://admin:test@"+host;
    static int port = -1;
    static boolean verbose = true;
    static boolean debug = true;
    static boolean showStructure = false;
    static boolean showMessage = false;
    static boolean showAlert = false;
    static boolean saveAttachments = true;
    static int attnum = 1;
    static javax.swing.JTable claimsTable;
    static javax.swing.JTable invoiceTable;
    static   java.awt.GridBagConstraints gridBagConstraints;
    static javax.swing.JTable requisTable;
    static Folder folder = null;
    //    static    com.afrisoftech.claims.ClaimsViewer claimsViewer;
    public static void main(java.lang.String argvss[]) {
        
        biz.systempartners.claims.ClaimsMsgShowSMS msgShow = new biz.systempartners.claims.ClaimsMsgShowSMS();
        
        biz.systempartners.claims.SMSAlertDialog smsAlertDialog = new biz.systempartners.claims.SMSAlertDialog(new java.awt.Frame(), true);
        
        msgShow.ClaimsMsgShowSMS(argvss, null, null, smsAlertDialog);
        
    }
    
    public static void ClaimsMsgShowSMS(java.lang.String argvs[], Folder folder1, Store store, biz.systempartners.claims.SMSAlertDialog smsAlertDialog) {
        if (smsAlertDialog == null) {
            
        }
        int msgnum = -1;
        int optind = 0;
        InputStream msgStream = System.in;
        
        if (argvs != null) {
            
            for (optind = 0; optind < argvs.length; optind++) {
                if (argvs[optind].equals("-T")) {
                    protocol = argvs[++optind];
                } else if (argvs[optind].equals("-H")) {
                    host = argvs[++optind];
                } else if (argvs[optind].equals("-U")) {
                    user = argvs[++optind];
                } else if (argvs[optind].equals("-P")) {
                    password = argvs[++optind];
                } else if (argvs[optind].equals("-v")) {
                    verbose = true;
                } else if (argvs[optind].equals("-D")) {
                    debug = true;
                } else if (argvs[optind].equals("-f")) {
                    mbox = argvs[++optind];
                } else if (argvs[optind].equals("-L")) {
                    url = argvs[++optind];
                } else if (argvs[optind].equals("-p")) {
                    port = Integer.parseInt(argvs[++optind]);
                } else if (argvs[optind].equals("-s")) {
                    showStructure = true;
                } else if (argvs[optind].equals("-S")) {
                    saveAttachments = true;
                } else if (argvs[optind].equals("-m")) {
                    showMessage = true;
                } else if (argvs[optind].equals("-a")) {
                    showAlert = true;
                } else if (argvs[optind].equals("--")) {
                    optind++;
                    break;
                } else if (argvs[optind].startsWith("-")) {
                    System.out.println(
                            "Usage: msgshow [-L url] [-T protocol] [-H host] [-p port] [-U user]");
                    System.out.println(
                            "\t[-P password] [-f mailbox] [msgnum] [-v] [-D] [-s] [-S] [-a]");
                    System.out.println(
                            "or     msgshow -m [-v] [-D] [-s] [-S] < msg");
                    System.exit(1);
                } else {
                    break;
                }
            }
        }
        
        try {
//////            if (optind < argvs.length)
/////                msgnum = Integer.parseInt(argvs[optind]);
//            msgnum = 1;
            
            // Get a Properties object
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "localhost");
            //   props.put("mail.smtp.auth","true");
            // Get a Session object
            Session session = Session.getInstance(props, null);
            session.setDebug(debug);
            
            if (showMessage) {
                MimeMessage msg = new MimeMessage(session, msgStream);
                dumpPart(msg, smsAlertDialog);
                //	this.dispose();
            }
            // Get a Store object
            /* Store */store = null;
            if (url != null) {
                URLName urln = new URLName(url);
                store = session.getStore(urln);
                if (showAlert) {
                    store.addStoreListener(new StoreListener() {
                        public void notification(StoreEvent e) {
                            String s;
                            if (e.getMessageType() == StoreEvent.ALERT)
                                s = "ALERT: ";
                            else
                                s = "NOTICE: ";
                            System.out.println(s + e.getMessage());
                        }
                    });
                }
                store.connect();
            } else {
                if (protocol != null)
                    store = session.getStore(protocol);
                else
                    store = session.getStore();
                
                // Connect
                if (host != null || user != null || password != null)
                    store.connect(host, port, user, password);
                else
                    store.connect();
            }
            //    store.connect("192.1.1.70",110,"cwaweru","tusker");
            
            // Open the Folder
            
            /*Folder */folder = store.getDefaultFolder();
            if (folder == null) {
                System.out.println("No default folder");
             //   System.exit(1);
            }
            
            folder = folder.getFolder(mbox);
            if (folder == null) {
                System.out.println("Invalid folder");
             //   System.exit(1);
            }
            
            // try to open read/write and if that fails try read-only
            try {
                folder.open(Folder.READ_WRITE);
            } catch (MessagingException ex) {
                folder.open(Folder.READ_ONLY);
            }
            int totalMessages = folder.getMessageCount();
            
            if (totalMessages == 0) {
                System.out.println("Empty folder");
                folder.close(false);
                store.close();
               //   System.exit(1);
            }
            
            if (verbose) {
                int newMessages = folder.getNewMessageCount();
                System.out.println("Total messages = " + totalMessages);
                System.out.println("New messages = " + newMessages);
                System.out.println("-------------------------------");
            }
            
            if (msgnum == -1) {
                // Attributes & Flags for all messages ..
                Message[] msgs = folder.getMessages();
                
                // Use a suitable FetchProfile
                FetchProfile fp = new FetchProfile();
                fp.add(FetchProfile.Item.ENVELOPE);
                fp.add(FetchProfile.Item.FLAGS);
                fp.add("X-Mailer");
                folder.fetch(msgs, fp);
                
                for (int i = 0; i < msgs.length; i++) {
                    System.out.println("--------------------------");
                    System.out.println("MESSAGE #" + (i + 1) + ":");
                    dumpEnvelope(msgs[i], smsAlertDialog);
                    smsAlertDialog.smsDetailsTxt.setText(msgs[i].getContent().toString());
                    smsAlertDialog.setVisible(true);
                    msgs[i].setFlag(Flags.Flag.DELETED, true);
                    System.out.println("Message content : "+msgs[i].getContent().toString());
                    // dumpPart(msgs[i]);
                }
            } else {
                Message[] msgs = folder.getMessages();
                for (int n = 0; n < msgs.length; n++) {
                    System.out.println("Getting message number: " + n + 1 );
                    Message m = null;
                    
                    try {
                        m = folder.getMessage(msgnum + 1);
                        // m.setDisposition(
                        dumpPart(m, smsAlertDialog);
                        System.out.println("Message content : "+m.getContent().toString());
                        m.setFlag(Flags.Flag.DELETED, true);
                        //                       m.setExpunged(true);
                    } catch (IndexOutOfBoundsException iex) {
                        System.out.println("Message number out of range");
                    }
                }
                folder.setFlags(msgs, new Flags(Flags.Flag.DELETED), true);
                
            //    folder.
            }
            // folder.expunge();
            
            folder.close(true);
            
            store.close();
            
        } catch (Exception ex) {
            System.out.println("Oops, got exception! " + ex.getMessage());
            ex.printStackTrace();
                     //    System.exit(1);
        }
        //	this.dispose();
    }
    
    
    public static void dumpPart(Part p, biz.systempartners.claims.SMSAlertDialog smsDialog) throws Exception {
        if (p instanceof Message)
            dumpEnvelope((Message)p, smsDialog);
        //  p.
        /** Dump input stream ..
         *
         * InputStream is = p.getInputStream();
         * // If "is" is not already buffered, wrap a BufferedInputStream
         * // around it.
         * if (!(is instanceof BufferedInputStream))
         * is = new BufferedInputStream(is);
         * int c;
         * while ((c = is.read()) != -1)
         * System.out.write(c);
         *
         **/
        
        String ct = p.getContentType();
        try {
            pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
        } catch (ParseException pex) {
            pr("BAD CONTENT-TYPE: " + ct);
        }
        String filename = p.getFileName();
        if (filename != null)
            pr("FILENAME: " + filename);
        
        /*
         * Using isMimeType to determine the content type avoids
         * fetching the actual content data until we need it.
         */
        if (p.isMimeType("text/plain")) {
            pr("This is plain text");
            pr("---------------------------");
            if (!showStructure && !saveAttachments)
                System.out.println((String)p.getContent());
        } else if (p.isMimeType("multipart/*")) {
            pr("This is a Multipart");
            pr("---------------------------");
            Multipart mp = (Multipart)p.getContent();
            level++;
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                dumpPart(mp.getBodyPart(i), smsDialog);
            level--;
        } else if (p.isMimeType("message/rfc822")) {
            pr("This is a Nested Message");
            pr("---------------------------");
            level++;
            dumpPart((Part)p.getContent(), smsDialog);
            level--;
        } else {
            if (!showStructure && !saveAttachments) {
                /*
                 * If we actually want to see the data, and it's not a
                 * MIME type we know, fetch it and check its Java type.
                 */
                Object o = p.getContent();
                if (o instanceof String) {
                    pr("This is a string");
                    pr("---------------------------");
                    System.out.println((String)o);
                } else if (o instanceof InputStream) {
                    pr("This is just an input stream");
                    pr("---------------------------");
                    InputStream is = (InputStream)o;
                    int c;
                    while ((c = is.read()) != -1)
                        System.out.write(c);
                } else {
                    pr("This is an unknown type");
                    pr("---------------------------");
                    pr(o.toString());
                }
            } else {
                // just a separator
                pr("---------------------------");
            }
        }
        
    }
    
    public static void dumpEnvelope(Message m, biz.systempartners.claims.SMSAlertDialog smsDialog) {//throws Exception {
        try {
            pr("This is the message envelope");
            pr("---------------------------");
            Address[] a;
            // FROM
            a = m.getFrom();
            //  if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++){
                smsDialog.phoneNumberTxt.setText(a[j].toString().replaceAll("@sms.com",""));
                pr("FROM: " + a[j].toString());
            }
            //  }
            
            // TO
            if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
                for (int j = 0; j < a.length; j++)
                    pr("TO: " + a[j].toString());
            }
            
            // SUBJECT
            pr("SUBJECT: " + m.getSubject());
            
            // DATE
            Date d = m.getSentDate();
            pr("SendDate: " +
                    (d != null ? d.toString() : "UNKNOWN"));
            
            // FLAGS
            Flags flags = m.getFlags();
            StringBuffer sb = new StringBuffer();
            Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags
            
            boolean first = true;
            for (int i = 0; i < sf.length; i++) {
                String s;
                Flags.Flag f = sf[i];
                if (f == Flags.Flag.ANSWERED)
                    s = "\\Answered";
                else if (f == Flags.Flag.DELETED)
                    s = "\\Deleted";
                else if (f == Flags.Flag.DRAFT)
                    s = "\\Draft";
                else if (f == Flags.Flag.FLAGGED)
                    s = "\\Flagged";
                else if (f == Flags.Flag.RECENT)
                    s = "\\Recent";
                else if (f == Flags.Flag.SEEN)
                    s = "\\Seen";
                else
                    continue;	// skip it
                if (first)
                    first = false;
                else
                    sb.append(' ');
                sb.append(s);
            }
            
            String[] uf = flags.getUserFlags(); // get the user flag strings
            for (int i = 0; i < uf.length; i++) {
                if (first)
                    first = false;
                else
                    sb.append(' ');
                sb.append(uf[i]);
            }
            pr("FLAGS: " + sb.toString());
            
            // X-MAILER
            String[] hdrs = m.getHeader("X-Mailer");
            if (hdrs != null)
                pr("X-Mailer: " + hdrs[0]);
            else
                pr("X-Mailer NOT available");
        } catch(javax.mail.MessagingException me){
            me.printStackTrace();
        }
    }
    
    static String indentStr = "                                               ";
    static int level = 0;
    
    /**
     * Print a, possibly indented, string.
     */
    public static void pr(String s) {
        if (showStructure)
            System.out.print(indentStr.substring(0, level * 2));
        System.out.println(s);
    }
    
}
