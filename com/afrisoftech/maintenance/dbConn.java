/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.maintenance;

import java.sql.*;

/**
 *
 * @author charlie
 */
public class dbConn {

    public Connection connector() {

        Connection c = null;
       try {
          Class.forName("org.postgresql.Driver");
          c = DriverManager
                  .getConnection("jdbc:postgresql://localhost:5432/funsoft",
                "postgres", "robert");
     } catch (Exception e) {
         System.err.println("Database error: " + e.getMessage());         
         System.err.println(e.getClass().getName() + ": " + e.getMessage());      
         System.exit(0);
      }
        return c;

    }
}