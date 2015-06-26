/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

/**Serializing Objects To XML
 * Deserializing Objects From XML
 * @author saleem
 * Big thanks to cwaweru@systempartners.biz and www.sanjaal.com/java
 */ 

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;
public class ObjectSerialization {
    /**
     * This method saves (serializes) any java bean object into xml file
     */
    public static void serializeObjectToXML(String xmlFileLocation, Object objectToSerialize) throws Exception 
    {
        FileOutputStream os = new FileOutputStream(xmlFileLocation);
        XMLEncoder encoder = new XMLEncoder(os);
        encoder.writeObject(objectToSerialize);
        encoder.close(); 
    }
    
    /**
     * Reads Java Bean Object From XML File
     */
    public static Object deserializeXMLToObject(String xmlFileLocation) throws Exception 
    {
        FileInputStream os = new FileInputStream(xmlFileLocation);
        XMLDecoder decoder = new XMLDecoder(os);
        Object deSerializedObject = decoder.readObject();
        decoder.close();
 
        return deSerializedObject;
    
    }
    
    /**
     * Testing.
     * 1. Creates and Object.
     * 2. Serializes Object To XML
     * 3. Deserializes Object From XML
     * 4. Prints The values hold in Object
     */
    public static void main(String args[]) throws Exception {
 
        /* Location of XML File */
        String XMLLocation = "user.dir";
 
        ObjectSerialization serializer = new ObjectSerialization();
           
        /* Creating and filling a bean object */
        MyBeanToSerialize obj = new MyBeanToSerialize();
        obj.setFirstName("Johnny");
        obj.setLastName("Depp");
        obj.setAge(45);
        
        
        
 
        /* Serialzing Object to XML */
        //System.out.println("<span class="IL_AD" id="IL_AD5">Starting</span> Serialization...");
        serializer.serializeObjectToXML(XMLLocation, obj);
        System.out.println("Serialized Object: " + obj.getClass().getName());
        System.out.println("Destination XML: " + XMLLocation);
 
        /* Reading the object from serialized XML */
        System.out.println("\n\nStarting De-Serialization...");
        System.out.println("Source XML: " + XMLLocation);
        MyBeanToSerialize deserializedObj = (MyBeanToSerialize) serializer
                .deserializeXMLToObject(XMLLocation);
        System.out.println("De-serialized Object: "
                + deserializedObj.getClass().getName());
        System.out.println("\nChecking For Values In De-Serialized Object");
        System.out.println("...First Name: " + deserializedObj.getFirstName());
        System.out.println("...Last Name: " + deserializedObj.getLastName());
        System.out.println("...Age: " + deserializedObj.getAge());
        
        
        
        System.out.println(".This is Marvellous");
        
 
    }
}