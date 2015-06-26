/*
 * ReminderDialog.java
 *
 * Created on November 15, 2007, 3:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.afrisoftech.lib.reminders;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class ReminderDialog extends org.jdesktop.swingx.JXTipOfTheDay {
    
    /** Creates a new instance of ReminderDialog */
    public ReminderDialog() {
        
    }
    
    public void showDialog(String reminderMessage) {
        
        org.jdesktop.swingx.tips.DefaultTipOfTheDayModel tipModel = new org.jdesktop.swingx.tips.DefaultTipOfTheDayModel();
        
        org.jdesktop.swingx.tips.DefaultTip defaultTip = new org.jdesktop.swingx.tips.DefaultTip();
        
        defaultTip.setTip("My example tip");
        
        org.jdesktop.swingx.tips.DefaultTip defaultTip1 = new org.jdesktop.swingx.tips.DefaultTip();
        
        defaultTip1.setTip("Trial tips");
        
        tipModel.add(defaultTip);
        
        tipModel.add(defaultTip1);
        
        this.showDialog(new java.awt.Frame());
        
    }
    
}
