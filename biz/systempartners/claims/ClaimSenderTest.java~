/*
 * ClaimSenderTest.java
 *
 * Created on July 3, 2006, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.claims;

/**
 *
 * @author root
 */
public class ClaimSenderTest {
    
    /** Creates a new instance of ClaimSenderTest */
    public ClaimSenderTest() {
        
        sendTestClaim();
        
    }
    
    private void sendTestClaim(){
        
        System.out.println("Sending a test claim via XML ...");
        
        biz.systempartners.claims.Claim testClaim = new biz.systempartners.claims.Claim();
        
        java.lang.Object tableDataModel[][] = {
        
            {"July 21, 2006","Description 1","1","1000","10-001-2002"},
            {"July 22, 2005","Description 2","2","2000","10-001-2002"},
            {"June 25, 2006","Description 3","3","3000","10-203-1103"},
            {"June 12, 2005","Description 4","4","4000","80-001-2002"},
            {"June 24, 2006","Description 5","5","5000","20-001-2002"},
            {"June 13, 2006","Description 6","6","6000","90-101-2002"}
      
        };
        
        java.lang.Object[] tableColumns = {"Date","Item Description","Qty","Total (KSH)","Our Ref."};
        
        javax.swing.JTable testTable = new javax.swing.JTable(tableDataModel,tableColumns);
        
        testClaim.setInvoiceTable(testTable);
        
        testClaim.setPatientNumber("PT0234343");
        
        testClaim.setPatientName("H. Z. Moijp");
        
        testClaim.setInvoiceNumber("INV0023456");
        
        testClaim.setAccountNumber("HJT45656002");
        
        testClaim.setSchemeMemberNumber("LEWERSH220");
        
        testClaim.setHealthCareProvider("Funsoft Demo Clinix Ltd");
        
        testClaim.setSchemePayer("SPL Demo Debtor Ltd");
        
        testClaim.setSchemeName("Madison Demo Scheme Ltd");
        
        testClaim.serializeClaim2XML();
        
               System.out.println("Sent a test claim via XML ...");
        
       // System.exit(0);
        
    }
    
    public static void main(String args[]){
        
        new ClaimSenderTest();
        
    }
    
}
