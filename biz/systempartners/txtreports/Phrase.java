/*
 * Phrase.java
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
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class Phrase {
    
    private int caretPosition = 0;
    
    private int printedCharacters = 0;
    
    private int remainingCharacters = 0;
    
    private  int MAXIMUM_CHARACTERS;
    
    private  int PHRASE_LENGTH;
    
    private  byte[] PHRASE_BYTES;
    
    private int horizontal_alignment = 0;
    
    public static final int HORIZONTAL_ALIGNMENT_LEFT = 1;
    
    public static final int HORIZONTAL_ALIGNMENT_CENTER = 2;
    
    public static final int HORIZONTAL_ALIGNMENT_RIGHT = 3;
    
    public static final String PHRASE_SEPARATOR_CHAR = System.getProperty("phrase.separator");
    
    private java.lang.String phraseString = null;
    
    private int phraseCaretPosition = 0;
    
    /** Creates a new instance of Phrase */
    public Phrase(){}
    
    public Phrase(int maxCharacters, java.lang.String phraseModel) {
        
//        System.out.println("Phrase model "+phraseModel);
        
//        MAXIMUM_CHARACTERS= maxCharacters;
        
        PHRASE_BYTES = phraseModel.getBytes();
        
//        System.out.println("Main phrase bytes "+ PHRASE_BYTES);
        
        PHRASE_LENGTH = PHRASE_BYTES.length;
        
        remainingCharacters = PHRASE_LENGTH;
        
        phraseString = phraseModel;
        
    }
    
  
    
    public int getCaretPosition(){
        
        return caretPosition;
        
    }
    
    public void setCaretPosition(int newCaretPosition){
        
        caretPosition = newCaretPosition;
        
    }
    
    public int getPrintedCharacters(){
        
        return printedCharacters;
        
    }
    
    public void setPrintedCharacters(int newPrintedCharacters){
        
        printedCharacters = printedCharacters + newPrintedCharacters;
        
    }
    
    public int getRemainingCharacters(){
//       System.out.println("Remaining phrase Characters "+ remainingCharacters);
        return remainingCharacters;
        
    }
    
    public void setRemainingCharacters(int currentPrintedCharacters){
        
        remainingCharacters = PHRASE_LENGTH - printedCharacters;
        
    }
    
    public byte[] getPhraseBytes(){
        
        return PHRASE_BYTES;
        
    }
    
    public java.lang.String toString(){
        
        return phraseString;
        
    }
    
    public int getPhraseCaretPosition(){
        
        if (printedCharacters == 0 ){
            
            phraseCaretPosition = 0;
            
        } else {
            
            phraseCaretPosition = getPrintedCharacters();
        }
        
        return phraseCaretPosition;
        
   
    }
    
}
