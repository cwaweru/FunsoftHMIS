/*
 * JTable.java
 *
 * Created on April 1, 2006, 4:37 PM
 */

package com.afrisoftech.dbadmin;
import java.util.Vector;
//import com.sun.org.apache.xml.internal.serialize.*;
import java.io.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.swing.JOptionPane;
/**
 *
 * @author  root
 */
public class Smart{//javax.swing.JTable {
   public void Smart()
   {
   
   } 
   
   public static String PassedPar;
   public String Card_Serial;
   public static Vector XmlValues;
    
    public static boolean SmartConfirm(String FileNamec) {
        boolean start = false;
        
        File FileCheck = new File(FileNamec);
        if (FileCheck.exists()) {
            start = true;
            //FileCheck;
        } else {
            start = false;
        }
        return start;

    }








public void Removerof() {

        File FileCheck = new File(PassedPar);
        System.out.println("The file is being removed"+PassedPar);
        try {
            FileCheck.delete();
          
        } catch (Exception e) {
            System.out.println("The exeption: " + e.getMessage());
        }

    }























public void SmartLinkXmlStart(Vector XmlVector,Vector Copay) {
        String  FileNameDoc="";
        

        try {
            
           
            /////////////////////////////
            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            ////////////////////////
            //Creating the XML tree
            //create the root element and add it to the document
            Node root = doc.createElement("ClaimData");
            doc.appendChild(root);
            System.out.println("Child data embeded");
            //create child element, add an attribute, and add to root this is the card serial number
            Node child = doc.createElement("Card_Serial");
             
            root.appendChild(child);
            //add a text element to the child
            Text text = doc.createTextNode((String) ((Vector) XmlVector.get(10)).get(0));
            System.out.println("this is the card serial number: "+text);
            child.appendChild(text);
            // this is for te receipt number
            child = doc.createElement("Receipt_Nr");
            root.appendChild(child);
            //add a text element to the child
            
            text = doc.createTextNode((String) ((Vector) XmlVector.get(10)).get(2));
            System.out.println("this is the receipt : "+text);
            child.appendChild(text);
            child = doc.createElement("Claim_Nr");
            root.appendChild(child);
            //add a text element to the child
            text = doc.createTextNode((String) ((Vector) XmlVector.get(10)).get(3));
            System.out.println("this is the claim nr: "+text);
            child.appendChild(text);
            child = doc.createElement("Patient_Name");
            root.appendChild(child);
            //add a text element to the child
            text = doc.createTextNode((String) ((Vector) XmlVector.get(10)).get(4));
            System.out.println("this is the patient name : "+text);
            child.appendChild(text);
            child = doc.createElement("Pool_Nr");
            root.appendChild(child);
            
            //add a text element to the child
            text = doc.createTextNode((String) ((Vector) XmlVector.get(10)).get(5));
            System.out.println("this is the pool number : "+text);
            child.appendChild(text);
            // the last tag in this famil
            Node childclaims = doc.createElement("ClaimLines");
            root.appendChild(childclaims);
            //add a text element to the child
           // iterate through the xml file document this is controlled by the vectore passed 
            int n = XmlVector.size();
            Vector Elements = (Vector) XmlVector.elementAt(0);
            int k=0;
            k = Elements.size();
            System.out.println("the size of elements is "+k); 
            for (int l = 0; l < k; l++) {
                child = doc.createElement("Transactions");
                childclaims.appendChild(child);
                Node Children = doc.createElement("Transaction_Time");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(0)).get(l)));
                Children = doc.createElement("Transaction_Date");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(1)).get(l)));
                Children = doc.createElement("Service_Provider_Nr");
                child.appendChild(Children);           
                Children.appendChild(doc.createTextNode("172.25.24.146"));
                Children = doc.createElement("Diagnosis_Code");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(9)).get(l)));
                Children = doc.createElement("Diagnosis_Description");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(8)).get(l)));
                Children = doc.createElement("Encounter_Type");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(7)).get(l)));
                Children = doc.createElement("Code_Type");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(6)).get(l)));
                Children = doc.createElement("Code");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(4)).get(l)));
                Children = doc.createElement("Code_Description");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(5)).get(l)));
                Children = doc.createElement("Quantity");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(3)).get(l)));
                Children = doc.createElement("Amount");
                child.appendChild(Children);
                Children.appendChild(doc.createTextNode((String) ((Vector) XmlVector.get(2)).get(l)));
                
            }
            
              Node CopayClaim = doc.createElement("PaymentModifier");
              root.appendChild(CopayClaim);
            
               Node SmallChild = doc.createElement("Type");
                CopayClaim.appendChild(SmallChild);
                SmallChild.appendChild(doc.createTextNode((String) (Copay.get(0))));
            
                Node SecondChild = doc.createElement("Requirement");
                CopayClaim.appendChild(SecondChild);
                SecondChild.appendChild(doc.createTextNode((String) (Copay.get(1))));
            
                Node ThirdChild = doc.createElement("Receipt");
                CopayClaim.appendChild(ThirdChild);
                ThirdChild.appendChild(doc.createTextNode((String) (Copay.get(2))));
                
                Node FouthChild = doc.createElement("Payment_Type");
                CopayClaim.appendChild(FouthChild);
                FouthChild.appendChild(doc.createTextNode((String) (Copay.get(3))));
            
            
            
           /* System.out.println("File Creation started: \n\n\n");
             FileNameDoc = (String) ((Vector) XmlVector.get(10)).get(6);
             //FileNameDoc="hospitalclaimsfile.xml";
             System.out.println("The file name is:"+FileNameDoc);
            XMLSerializer serializer = new XMLSerializer();
            FileWriter Writer = new FileWriter(FileNameDoc);
            serializer.setOutputCharStream(Writer);
            serializer.serialize(doc);   */
            //  Writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }   
       // System.out.println("is this name there :  "+ (String) ((Vector) XmlVector.get(10)).get(0));
        //System.out.println("Is this file present: "+ Card_Serial);
        if (SmartConfirm(FileNameDoc)){
        Card_Serial= (XmlVector.get(11)).toString().trim();
       // System.out.println("Is this file present: "+ Card_Serial);
       Card_Serial=Card_Serial+((String) ((Vector) XmlVector.get(10)).get(0))+".txt"; 
       System.out.println("Is this file present: "+ Card_Serial);
        File CardS=new File(Card_Serial);
     CardS=new File(Card_Serial);
     
      
        while (!(CardS.exists())) {
                  JOptionPane.showMessageDialog(null, "Please retrieve data from smartlink");
                  // CardS.delete();
              }
           CardS.delete();
           
       }else{
            
       }
           
       
    }





 public Vector ReadFowardCard(String XmlDoc) throws InterruptedException {

            File FileForwarded = new File(XmlDoc);    
           
        if ((SmartConfirm(XmlDoc))) {
            XmlValues = new Vector();            
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File(XmlDoc));
                // normalise the file
                doc.getDocumentElement().normalize();
                System.out.println(XmlDoc);
                NodeList CardSer = doc.getElementsByTagName("A1");
                Node CardSernode = CardSer.item(0);
                if (CardSernode.getNodeType() == Node.ELEMENT_NODE) {
                    Element FirstCardSer = (Element) CardSernode;

                    // Read the serial number of the card                
                    NodeList FirstCardE = FirstCardSer.getElementsByTagName("card_serialnumber");
                    Element firstseriale = (Element) FirstCardE.item(0);
                    NodeList CardText = firstseriale.getChildNodes();
                    // add serialnumberto vector
                    XmlValues.add(CardText.item(0).getNodeValue().trim());

                    NodeList PoolDesc = FirstCardSer.getElementsByTagName("card_validitystatus");
                    Element CardValidity = (Element) PoolDesc.item(0);
                    NodeList CardValid = CardValidity.getChildNodes();
//add validity status to vector
                    XmlValues.add(CardValid.item(0).getNodeValue().trim());

                }
                NodeList ListFileNode = doc.getElementsByTagName("Benefit");
                Node FirstFileNode = ListFileNode.item(0);
                if (FirstFileNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstPersonElement = (Element) FirstFileNode;

                    // Read pool number

                    NodeList firstNameList = firstPersonElement.getElementsByTagName("Nr");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    XmlValues.add(textFNList.item(0).getNodeValue().trim());
                    //XmlValues.add(
                    //Read the Benefit description

                    NodeList PoolDesc = firstPersonElement.getElementsByTagName("Description");
                    Element PoolDescName = (Element) PoolDesc.item(0);
                    NodeList Pooltext = PoolDescName.getChildNodes();

                    XmlValues.add(Pooltext.item(0).getNodeValue().trim());

                    NodeList PoolAmount = firstPersonElement.getElementsByTagName("Amount");
                    Element PoolDescAmount = (Element) PoolAmount.item(0);
                    NodeList AmountText = PoolDescAmount.getChildNodes();
                    XmlValues.add(AmountText.item(0).getNodeValue().trim());

                }                    
                NodeList NamesDec = doc.getElementsByTagName("A2");
                Node NamesDescd = NamesDec.item(0);
                if (NamesDescd.getNodeType() == Node.ELEMENT_NODE) {
                    Element FirstCardSer = (Element) NamesDescd;

                    // Read the serial number of the card                
                    NodeList FirstCardE = FirstCardSer.getElementsByTagName("patient_forenames");
                    Element firstseriale = (Element) FirstCardE.item(0);
                    NodeList CardText = firstseriale.getChildNodes();
                    // add serialnumberto vector
                    XmlValues.add(CardText.item(0).getNodeValue().trim());
                  
                }



 NodeList Names= doc.getElementsByTagName("B1");
                Node NamesD = Names.item(0);
                if (NamesD.getNodeType() == Node.ELEMENT_NODE) {
                    Element FirstCardSer = (Element) NamesD;

                    // Read the serial number of the card                
                    NodeList FirstCardE = FirstCardSer.getElementsByTagName("medicalaid_number");
                    Element firstseriale = (Element) FirstCardE.item(0);
                    NodeList CardText = firstseriale.getChildNodes();
                    // add serialnumberto vector
                    XmlValues.add(CardText.item(0).getNodeValue().trim());
                  
                }
                
                
                
                
                
                
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

        } else {
                   while (!(FileForwarded.exists())) {
                   JOptionPane.showMessageDialog(null, "Please forward data from smartlink");
                   
                }
             //
                   FileForwarded.delete();
                
        }
    // FileForwarded.delete();   
return XmlValues;
    }

  
public void Starterfollow(){       
        
        if (PassedPar != null) {
        while (!(SmartConfirm(PassedPar))) {
            JOptionPane.showMessageDialog(null, "Finilise claims on smartlink");
        }
        Removerof();    
        }
       
    }
}
