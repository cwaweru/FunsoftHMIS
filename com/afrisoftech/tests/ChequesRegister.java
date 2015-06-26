/*
 * ChequesRegister.java
 *
 * Created on January 4, 2005, 4:12 PM
 */

package com.afrisoftech.tests;

/**
 *
 * @author  root
 */
public class ChequesRegister {
    
    /** Creates a new instance of ChequesRegister */
    public ChequesRegister() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        generateChequeLeafs(5000001, 5000100);
        
    }
    
    public static void generateChequeLeafs(int startLeaf, int endLeaf){
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException cnf){
            cnf.printStackTrace(); 
        }
        
        try {
        java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic_metro", "postgres", "pilsiner");
        java.sql.Statement stmt = connection.createStatement();
        String used = "Used";
        for(int i = startLeaf; i <= endLeaf; i++){
        stmt.execute("insert into cheque_register(status,cheque_id) values('Used Again',lpad("+i+",8,'0'))");
        }
        } catch(java.sql.SQLException sql) {
           sql.printStackTrace();
        }
    }
    
    
}
