/*
 * @(#)FolderModel.java	1.13 01/05/23
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES OR LIABILITIES
 * SUFFERED BY LICENSEE AS A RESULT OF  OR RELATING TO USE, MODIFICATION
 * OR DISTRIBUTION OF THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */
package biz.systempartners.claims;

import javax.mail.*;
import java.util.Date;
import javax.swing.table.AbstractTableModel; 

/**
 * Maps the messages in a Folder to the Swing's Table Model
 *
 * @version	1.13, 01/05/23
 * @author	Christopher Cotton
 * @author	Bill Shannon
 */

public class FolderModel extends AbstractTableModel {
    
    Folder	folder;
    Message[]	messages;

    String[]	columnNames = { "Date", "From", "Subject"}; 
    Class[]	columnTypes = { String.class, String.class, String.class }; 

    public void setFolder(Folder what) throws MessagingException {
	if (what != null) {

	    // opened if needed
	    if (!what.isOpen()) {
		what.open(Folder.READ_WRITE);
	    }
    
	    // get the messages
	    messages = what.getMessages();
	    cached = new String[messages.length][];
	} else {
	    messages = null;
	    cached = null;
	}
	// close previous folder and switch to new folder
	if (folder != null)
	    folder.close(true);
	folder = what;
	fireTableDataChanged();
    }
    
    public Message getMessage(int which) {
	return messages[which];
    }

    //---------------------
    // Implementation of the TableModel methods
    //---------------------

    public String getColumnName(int column) {
	return columnNames[column];
    }
    
    public Class getColumnClass(int column) {
	return columnTypes[column];
    }
    

    public int getColumnCount() {
        return columnNames.length; 
    }

    public int getRowCount() {
	if (messages == null)
	    return 0;
	
	return messages.length;
    }
 
    public Object getValueAt(int aRow, int aColumn) {
	switch(aColumn) {
	case 0:	// date
	case 1: // From		String[] what = getCachedData(aRow);
	case 2: // Subject
	    String[] what = getCachedData(aRow);
	    if (what != null) {
		return what[aColumn];
	    } else {
		return "";
	    }
	    
	default:
	    return "";
	}
    }

    protected static String[][]	cached;
    
    protected String[] getCachedData(int row) {
	if (cached[row] == null) {
	    try{
		Message m = messages[row];
	    
		String[] theData = new String[4];
	    
		// Date
		Date date = m.getSentDate();
		if (date == null) {
		    theData[0] = "Unknown";
		} else {
		    theData[0] = date.toString();
		}
	    
		// From
		Address[] adds = m.getFrom();
		if (adds != null && adds.length != 0) {
		    theData[1] = adds[0].toString();	    
		} else {
		    theData[1] = "";
		}
		
		// Subject
		String subject = m.getSubject();
		if (subject != null) {
		    theData[2] = subject;
		} else {
		    theData[2] = "(No Subject)";
		}

		cached[row] = theData;
	    }
	    catch (MessagingException e) {
		e.printStackTrace();
	    }
	}
	
	return cached[row];
    }
}
