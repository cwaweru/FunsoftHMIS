/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.lib;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author sytem partners
 */
public class MailAuthenticator  extends Authenticator {
    
      private String PASSWORD = null;
        private String FROM_ADDRESS =null;
   public MailAuthenticator(String from,String pass){
  
       PASSWORD=pass;
       FROM_ADDRESS=from;
      
       getPasswordAuthentication();
       
  
        }
    protected PasswordAuthentication getPasswordAuthentication() {  
  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD); 
            
        }
    } 
    

