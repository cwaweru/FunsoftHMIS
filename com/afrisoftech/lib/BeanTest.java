package com.afrisoftech.lib;

/*
 * BeanTest.java
 *
 * Created on August 21, 2003, 9:20 PM
 */

import java.beans.*;

/**
 *
 * @author  root
 */
public class BeanTest extends kiwi.ui.DateChooserField implements java.io.Serializable {
    
    private static final String PROP_SAMPLE_PROPERTY = "SampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    /** Creates new BeanTest */
    public BeanTest() {
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
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
