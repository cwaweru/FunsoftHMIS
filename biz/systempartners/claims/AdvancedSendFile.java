/*
 * @(#)sendfile.java	1.11 03/06/19
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
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * sendfile will create a multipart message with the second block of the message
 * being the given file.<p>
 *
 * This demonstrates how to use the FileDataSource to send a file via mail.<p>
 *
 * usage: <code>java sendfile <i>to from smtp file true|false</i></code> where
 * <i>to</i> and <i>from</i> are the destination and origin email addresses,
 * respectively, and <i>smtp</i>
 * is the hostname of the machine that has smtp server running. <i>file</i> is
 * the file to send. The next parameter either turns on or turns off debugging
 * during sending.
 *
 * @author	Christopher Cotton
 */
public class AdvancedSendFile {

    // private String filename;
    public void SendFile() {

    }

    public static void SendFile(java.sql.Connection connection, String fileNames[], String toAddresses[], String ccAddresses[], String bccAddresses[], String mailSubject, String msgBody) {
        if (fileNames != null) {
            System.out.println("No of file names :[" + fileNames.length + "]");
        }

        //  for(int){
        //  }
        //  System.out.println("Scheme mail adress ::: ["+schemeMailAddress+"]");
        //	String to = System.getProperty("claims.email.address");
        //	String from = System.getProperty("claims.from.address");
        //	String host = System.getProperty("claims.host.address");
        // String to = schemeMailAddress;//"waweru@systempartners.com";
        System.out.println("Sending funsoft mail");
        String from = biz.systempartners.utils.FunsoftUser.getEmailAddress(connection); //System.getProperty(biz.systempartners.utils.FunsoftUser.getEmailAddress(connection), "cwaweru@systempartners.biz");
        String host = biz.systempartners.utils.FunsoftUser.getMailServerAddress(connection); //System.getProperty("mail.smtp.host", "127.0.0.1");//"192.168.0.105";
        //filename = fileName;
        //boolean debug = Boolean.valueOf(System.getProperty("claims.debug.boolean")).booleanValue();
        boolean debug = true;
        //String msgText1 = "Claim file : ["+fileName+"].\n";
        //String subject = "Claim file : ["+fileName+"]";//"Sending a file";

        //System.out.println("Sending File : "+fileName);
        // create some properties and get the default Session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
 //       props.put("mail.smtp.port", "587");
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        final String fromEmail = "hr@komarockmodernhealthcare.org>"; //requires valid gmail id//needs to come from a properties file or db
        final String password = "kmhc@3020!"; // correct password for gmail id
       // final String toEmail = "myemail@yahoo.com";
        




       
                        //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
	//	EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
		
	
        
        ////Session session = Session.getInstance(props, null);
        session.setDebug(debug);

        try {
            // create a message
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));

            // create to addresses
            InternetAddress[] toAddress = new InternetAddress[toAddresses.length];
            for (int i = 0; i < toAddresses.length; i++) {
                toAddress[i] = new InternetAddress(toAddresses[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddress);

            // create cc addresses
            if (ccAddresses != null) {
                InternetAddress[] ccAddress = new InternetAddress[ccAddresses.length];
                for (int j = 0; j < ccAddresses.length; j++) {
                    ccAddress[j] = new InternetAddress(toAddresses[j]);
                }
                msg.setRecipients(Message.RecipientType.CC, ccAddress);

            }

            // create bcc addresses
            if (bccAddresses != null) {
                InternetAddress[] bccAddress = new InternetAddress[bccAddresses.length];
                for (int k = 0; k < bccAddresses.length; k++) {
                    bccAddress[k] = new InternetAddress(bccAddresses[k]);
                }
                msg.setRecipients(Message.RecipientType.BCC, bccAddress);

            }

            msg.setSubject(mailSubject);

            // create the Multipart and add its parts to it
            Multipart mp = new MimeMultipart();
            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(msgBody);
            mp.addBodyPart(mbp1);

            // create the second message part
            if (fileNames != null) {
                MimeBodyPart mbpAttachments[] = new MimeBodyPart[fileNames.length];

                // attach the file to the message
                for (int m = 0; m < fileNames.length; m++) {
                    FileDataSource fds = new FileDataSource(fileNames[m]);
                    mbpAttachments[m] = new MimeBodyPart();
                    mbpAttachments[m].setDataHandler(new DataHandler(fds));
                    mbpAttachments[m].setFileName(fds.getName());
                }

                for (int n = 0; n < mbpAttachments.length; n++) {
                    mp.addBodyPart(mbpAttachments[n]);
                }
            }

            // add the Multipart to the message
            msg.setContent(mp);

            // set the Date: header
            msg.setSentDate(new Date());

            // send the message
            Transport.send(msg);

            System.err.println(mailSubject + " Sent succussfully  :");

        } catch (MessagingException mex) {
            mex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), mex.getMessage(), "Error send email!", javax.swing.JOptionPane.ERROR_MESSAGE);
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage(), "Error send email!", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    //   }
    /*
     public static void main(String[] args) {
     if (args.length != 5) {
     System.out.println("usage: java sendfile <to> <from> <smtp> <file> true|false");
     System.exit(1);
     }
     }
     */
}
