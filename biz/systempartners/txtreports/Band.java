/*
 * Band.java
 *
 * Created on August 20, 2005, 9:09 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru
 */
public class Band extends java.lang.Object {
   
    private int lineCharLegnth = 0;
    
    private int bandHeight = 0;
    
    private boolean isBandWritten = false;
    
    /** Creates a new instance of Band */
    public Band() {
    }
    
    public int getLineCharLegnth(){
       
        return lineCharLegnth;
        
    }
    
    public void setLineCharLegnth(int newLineCharLegnth){
     
        lineCharLegnth = newLineCharLegnth;
        
    }
    
    public boolean isBandCompleted(Phrase currentPrintingPhrase){
        
        if (currentPrintingPhrase.getRemainingCharacters() > 0){
           
            return false;
        
        } else {
            
            return true;
        }
        
    }
   
   
}
