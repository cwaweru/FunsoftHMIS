/*
 * PassWordRegister.java
 *
 * Created on October 11, 2004, 10:58 PM
 */

package biz.systempartners.claims;

/**
 *
 * @author  root
 */
public class PassWordRegister {
    
    
    private static java.lang.String passwordFileUrl = null;
    
    /** Creates a new instance of PassWordRegister */
    public PassWordRegister() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length < 1){
            
            registerPassword(new String[]{"pilsiner"});
        
        } else {
        
        registerPassword(args);
        
        }
    }
    
    private static void registerPassword(String[] args) {
        
        String passWord = args[0];
        
        passwordFileUrl =  System.getProperty("user.dir")
        
        + System.getProperty("file.separator")
        
        + "password.pwd";
        
        try {
            
            java.io.FileOutputStream passwdFileIOStream = new java.io.FileOutputStream(passwordFileUrl);
            
            java.io.ObjectOutputStream requisObjOutputStream = new java.io.ObjectOutputStream(passwdFileIOStream);
            
            requisObjOutputStream.writeObject(passWord);
            
            
        } catch(java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        
    }
}
