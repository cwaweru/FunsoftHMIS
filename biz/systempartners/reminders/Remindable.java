/*
 * Remindable.java
 *
 * Created on April 22, 2008, 7:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.reminders;

import org.jdesktop.swingx.auth.UserNameStore;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 *
 *
 */
public class Remindable implements java.lang.Runnable {
    
    java.lang.String UserName;
    
    java.lang.String reminderTitle;
    
    java.lang.Class reminderClass;
    
    java.lang.String[] reminderArray;
    
    int snoozePeriod;
    
    java.lang.String reminderIdentity;
    
    org.jdesktop.swingx.tips.DefaultTipOfTheDayModel tipOfTheDayModel;
    
    org.jdesktop.swingx.tips.DefaultTip[] defaultTip;
    
    javax.swing.table.DefaultTableModel tipsTableModel;
    
    java.sql.Connection connectDB = null;
    
    biz.systempartners.reminders.ReminderContainer reminderContainer;
    
    /** Creates a new instance of Remindable */
    public Remindable(java.sql.Connection connDB) {
        
        java.lang.Thread reminderThread = new java.lang.Thread(this, "Remindables.Thread");
        
        connectDB = connDB;
        
        
        
        reminderContainer = new biz.systempartners.reminders.ReminderContainer();
        
        defaultTip = this.getTipsArray(getListofReminders("SELECT * FROM xray"));
        
        reminderThread.start();
        
        reminderContainer.showTipOfday();
        
    }
    
    public void run() {
        
        while(true){
            try {
                
                Thread.sleep(1000000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            reminderContainer.setTipOfThedayModel(defaultTip);
            //         System.out.println("Reminders have started .....................");
            reminderContainer.showTipOfday();
            
        }
        
    }
    
    public String getReminderTitle() {
        return reminderTitle;
    }
    
    
    public void setReminderArray(String[] reminderArray) {
        this.reminderArray = reminderArray;
    }
    
    public String[] getReminderArray() {
        return reminderArray;
    }
    
    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }
    
    public void setReminderClass(Class reminderClass) {
        this.reminderClass = reminderClass;
    }
    
    public Class getReminderClass() {
        return reminderClass;
    }
    
    public String getUserName() {
        return UserName;
    }
    
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
    public int getSnoozePeriod() {
        return snoozePeriod;
    }
    
    public void setSnoozePeriod(int snoozePeriod) {
        this.snoozePeriod = snoozePeriod;
    }
    
    public String getReminderIdentity() {
        return reminderIdentity;
    }
    
    public void setReminderIdentity(String reminderIdentity) {
        this.reminderIdentity = reminderIdentity;
    }
    
    public javax.swing.table.DefaultTableModel getListofReminders(String remindersQuery) {
        
        return com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,remindersQuery);
        
    }
    
    /**
     *
     * @param tableModel
     * @return
     */
    public org.jdesktop.swingx.tips.DefaultTip[] getTipsArray(javax.swing.table.DefaultTableModel tableModel){
        
        org.jdesktop.swingx.tips.DefaultTip tips[] = new org.jdesktop.swingx.tips.DefaultTip[tableModel.getRowCount()];
        
        for(int i = 0; i < tableModel.getRowCount(); i++) {
            
            tips[i] = new org.jdesktop.swingx.tips.DefaultTip(tableModel.getValueAt(i,0).toString(), tableModel.getValueAt(i,1));
            
        }
        
        return tips;
        
    }
}
