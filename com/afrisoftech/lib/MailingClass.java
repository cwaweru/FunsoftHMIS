/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.lib;


import com.afrisoftech.lib.MailAuthenticator;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author sytem partners
 */
public class MailingClass {
    
      java.sql.Connection connectDB = null;
 org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
 String to_mail=null;
 String from_mail=null,password=null;
 String cats=null;
    //"smtp.gmail.com"
     public  void MailingClass(java.sql.Connection connDb,java.io.File tempFiles,String to, String from,String pass,String Category){
    to_mail=to;
    from_mail =from;
   password = pass;
   cats=Category;
   connectDB=connDb;
  String filename = tempFiles.getAbsolutePath();
  String msgText1 = null;
  String subject = null;
  java.lang.String[] listSet = null;  
   java.lang.String[] ccAddresses = null; 
   java.lang.String[] toAddresses = null; 
  java.sql.ResultSet rsetArray = null;
  
  
  // create some properties and get the default Session
  Properties props = System.getProperties();
//  props.put("mail.smtp.host", host);
//  props.put("mail.smtp.starttls.enable","true");
  props.put("mail.smtp.starttls.required","true");
  
  props.put("mail.smtp.user",from_mail);
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "25");
props.put("mail.debug", "true");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.EnableSSL.enable","true");

props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.setProperty("mail.smtp.socketFactory.fallbac k", "false");
props.setProperty("mail.smtp.port", "465");
props.setProperty("mail.smtp.socketFactory.port", "465"); 
  
  //Session session = Session.getInstance(props, null);
  Session session = Session.getInstance(props, new MailAuthenticator(from_mail,password));  
  try 
  {
      // create a message
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      
      ///creating CCs
      try {
            
            //          java.sql.Connection cn = java.sql.DriverManager.getConnection("jdbc:postgresql://192.168.0.100:5432/purchase","postgres", "pilsiner");
            
            java.sql.Statement stmt = connectDB.createStatement();
            
            java.sql.ResultSet rs = stmt.executeQuery("select distinct c.email_mail_address,subject,message_body  from mailers_list_setup c,mailers_group_setup p where p.sender_mailer_code=c.sender_mailer_code and  p.mailer_category='"+cats+"'");
            
            while (rs.next()) {
                
                java.sql.Array arraySet = rs.getArray(1);
                
                java.lang.Object[] listSetTest = (java.lang.Object[])arraySet.getArray();
                
                if (!(listSetTest == null)) {
                    
                    listSet = (java.lang.String[])arraySet.getArray();
                    msgText1=rs.getObject(3).toString();
                    subject=rs.getString(2);
                    
                    System.out.println("THE TO CCs ARE " +listSet);
                    
                    //rsetArray = arraySet.getResultSet();
                    
                } else {
                    
                    listSet = null;
                    
                }
            }
            
        } catch(SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(null, sqlExec.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null,"CHECK THE EMAIL ADDRESSES:: CHECK YOUR ENTRIES");
            
        }
      
//      InternetAddress[] address = {new InternetAddress(to)};
//      msg.setRecipients(Message.RecipientType.TO, address);
//      
      InternetAddress[] ccAddress = new InternetAddress[listSet.length];
            for(int j = 0; j < listSet.length; j++){
                ccAddress[j] = new InternetAddress(listSet[j]);
            }
            msg.setRecipients(Message.RecipientType.CC, ccAddress);
      
            ////end
      
      msg.setSubject(subject);

      // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1);

      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
      
      File attachmentFiles = tempFiles.getAbsoluteFile();

            //attachmentsVector = new java.util.Vector(1,1);

            

                //attachmentsVector.addElement(attachmentFiles.getPath());
                
      FileDataSource fds = new FileDataSource(attachmentFiles.getPath());
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(String.valueOf(fds.getName()));
      
//      MimeBodyPart mbpAttachments[] =new  MimeBodyPart[1];
//            
//            // attach the file to the message
//            //for(int m =0; m < mbpAttachments.length; m++){
//                FileDataSource fds = new FileDataSource(filename);
//                mbpAttachments[1] = new MimeBodyPart();
//                mbpAttachments[1].setDataHandler(new DataHandler(fds));
//                mbpAttachments[1].setFileName(fds.getName());
//            //}
      
      //System.out.println("xxxxxxxxxxx "+fds.getName());

      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);
      
     

      // add the Multipart to the message
      msg.setContent(mp);

      // set the Date: header
      msg.setSentDate(new Date());
      
      // send the message
      Transport.send(msg);
      
  } 
  catch (MessagingException mex) 
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
     javax.swing.JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
     javax.swing.JOptionPane.showMessageDialog(null,"CONTACT THE SYSTEMS ADMINISTRATOR HINT:: check the email addresses","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
      }
    
    
    
    }
    
   
}
    
}
