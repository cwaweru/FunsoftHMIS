/*
 * SampleException.java
 *
 * Created on July 5, 2003, 9:42 AM
 */

package com.afrisoftech.hospital;

/**
 *
 * @author  root
 */
public class SampleException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>SampleException</code> without detail message.
     */
    public SampleException() {
    }
    
    
    /**
     * Constructs an instance of <code>SampleException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SampleException(String msg) {
        super(msg);
    }
}
