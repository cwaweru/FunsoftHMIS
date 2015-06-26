/*
 * ClaimsBackEndMain.java
 *
 * Created on August 4, 2004, 4:06 PM
 */

package biz.systempartners.claims;

/**
 * 
 * @author  Charles Wanjema Waweru <cwaweru@systempartners.biz>
 */
public class ClaimsBackEndMain {
    
    static    biz.systempartners.claims.ClaimsViewer claimsViewer;
    
    java.lang.Thread threadClaims = null;
    
    boolean processedClaims = true;
    
    biz.systempartners.claims.Monitor claimsMonitor;
    
    /** Creates a new instance of ClaimsBackEndMain */
    public ClaimsBackEndMain() {
        
        //        threadClaims = new java.lang.Thread(this, "ThreadClaims");
        
        //        threadClaims.start();
     //   launchClaimsBackEnd();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        biz.systempartners.claims.ClaimsBackEndMain claimsBackendMain = new biz.systempartners.claims.ClaimsBackEndMain();
        
        claimsBackendMain.launchClaimsBackEnd();
        
    }
    
    public void launchClaimsBackEnd()  {
        
        claimsViewer = new biz.systempartners.claims.ClaimsViewer();
        
        claimsMonitor = new biz.systempartners.claims.Monitor();
        
        try {
            
            claimsMonitor.Monitor(claimsViewer);
            
        } catch(java.lang.ClassNotFoundException cnfExcep) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), cnfExcep.getMessage());
            
        }
        
        
    }
    
}
