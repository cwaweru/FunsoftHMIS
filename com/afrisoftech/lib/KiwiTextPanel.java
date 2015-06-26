/*
 * KiwiTextPanel.java
 *
 * Created on March 29, 2004, 9:23 AM
 */

package com.afrisoftech.lib;

import java.beans.*;

/**
 *
 * @author  root
 */
public class KiwiTextPanel extends kiwi.ui.SimpleEditor implements java.io.Serializable {
    
    private static final String PROP_SAMPLE_PROPERTY = "SampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    /** Creates new KiwiTextPanel */
    public KiwiTextPanel() {
        propertySupport = new PropertyChangeSupport( this );
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
       // propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
