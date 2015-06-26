/*
 * ReminderContainer.java
 *
 * Created on April 22, 2008, 7:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.reminders;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class ReminderContainer extends org.jdesktop.swingx.JXTipOfTheDay {
    
    /** Creates a new instance of ReminderContainer */
    
    biz.systempartners.reminders.Remindable remindableAction;
    
    static org.jdesktop.swingx.tips.DefaultTipOfTheDayModel tipOfTheDayModel;
    
    static org.jdesktop.swingx.tips.DefaultTip[] defaultTip;
    
    public ReminderContainer() {
        
    //    this.setSize(500,400);
        
        tipOfTheDayModel = new org.jdesktop.swingx.tips.DefaultTipOfTheDayModel();
        
        this.setToolTipText("Important reminders for the day!");
        
    }
    
    public static void setTipOfThedayModel(org.jdesktop.swingx.tips.DefaultTip[] tipsArray){
        
        defaultTip = tipsArray;
        
        tipOfTheDayModel.setTips(tipsArray);
        
    }
    
    public void showTipOfday(){
        
        setModel(tipOfTheDayModel);
        
        setVisible(true);
        
        this.showDialog(new java.awt.Frame());
        
        // System.out.println("Reminders have started .....................");
        
    }
    
}
