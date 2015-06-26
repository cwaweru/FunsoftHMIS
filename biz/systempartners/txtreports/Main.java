/*
 * Main.java
 *
 * Created on August 20, 2005, 4:37 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package biz.systempartners.txtreports;

/**
 *
 * @author waweru
 */
public class Main {
      static   String reportPath = null;   
    /** Creates a new instance of Main */
    public Main() {
   
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0){
        String reportPath = args[0]; 
        } else {
          reportPath = System.getProperty("user.dir") + System.getProperty("file.separator")+ "myreport.txt";
        }
        System.out.println("Writing to file [ "+reportPath+" ]");
     
        biz.systempartners.txtreports.SampleTxtReport sampleTxtReport = new biz.systempartners.txtreports.SampleTxtReport(reportPath);
        
// TODO code application logic here
    }
    
}
