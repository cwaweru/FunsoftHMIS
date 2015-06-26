/*
 * SearchField.java
 *
 * Created on July 20, 2008, 9:51 AM
 */
package com.afrisoftech.lib;

import java.beans.*;
import java.io.Serializable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * @author Chrles Waweru <<cwaweru@systempartners.biz>>
 */
public class SearchField extends javax.swing.JTextField implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    private String sampleProperty = "";
    private PropertyChangeSupport propertySupport = new PropertyChangeSupport(this);
    private java.sql.Connection connectDB = null;
    private String queryString = "";

    /**
     *
     */
    public SearchField() {
        this.setText("");
        propertySupport = new PropertyChangeSupport(this);        
    }
    CaretListener caretListenerLabel = new CaretListener() {
        @Override
        public void caretUpdate(CaretEvent ce) {
            searchFieldCaretUpdate(ce);
        }
    };
    
    public SearchField(java.sql.Connection connectDB) {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public void addCaretListener(javax.swing.event.CaretListener listener) {
        this.addCaretListener(listener);
    }
    
    private void searchFieldCaretUpdate(javax.swing.event.CaretEvent evt) {
        SearchWindow.searchControlsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(getConnectDB(), getQueryString()));
    }

    /**
     * @return the connectDB
     */
    public java.sql.Connection getConnectDB() {
        return connectDB;
    }

    /**
     * @return the queryString
     */
    public String getQueryString() {
        return queryString;
    }
    
    private static class SearchWindow extends javax.swing.JWindow {
        
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.JPanel searchPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane searchControlsScrollPane = new javax.swing.JScrollPane();
        static com.afrisoftech.dbadmin.JTable searchControlsTbl = new com.afrisoftech.dbadmin.JTable();
        
        public SearchWindow() {
            
        }
    }
}
