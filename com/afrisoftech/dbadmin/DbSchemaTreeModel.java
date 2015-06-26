/*
 * DbSchemaTreeModel.java
 *
 * Created on March 23, 2004, 11:51 AM
 */

package com.afrisoftech.dbadmin;

import com.afrisoftech.hospinventory.PatientsBillingIntfr_;

/**
 *
 * @author  root
 */
public class DbSchemaTreeModel {
    
    java.lang.Object[] listOfTables = null;
    
    javax.swing.JTree jTreeDBSchema = null;
    
    java.util.Vector tablesVector = null;
    
    javax.swing.tree.DefaultMutableTreeNode hpNode = new javax.swing.tree.DefaultMutableTreeNode("Hospital Operations");
    javax.swing.tree.DefaultMutableTreeNode stNode = new javax.swing.tree.DefaultMutableTreeNode("Stock & Inventory");
    javax.swing.tree.DefaultMutableTreeNode pbNode = new javax.swing.tree.DefaultMutableTreeNode("Parameter Tables");
    javax.swing.tree.DefaultMutableTreeNode acNode = new javax.swing.tree.DefaultMutableTreeNode("Accounts Tables");
    javax.swing.tree.DefaultMutableTreeNode genNode = new javax.swing.tree.DefaultMutableTreeNode("General Tables");
    
    /** Creates a new instance of DbSchemaTreeModel */
    public DbSchemaTreeModel() {
        
        //   createDBSchemaModel();
        
    }
    
    public javax.swing.JTree createDBSchemaModel(java.sql.Connection dbConnDB, javax.swing.tree.DefaultMutableTreeNode topDbAdmin) {
        
        java.lang.Object[] tableArray = getListOfTables(dbConnDB);
        
        topDbAdmin = new javax.swing.tree.DefaultMutableTreeNode("Database View");
        
        createTreeNodes(topDbAdmin, tableArray);
        
        jTreeDBSchema = new javax.swing.JTree(topDbAdmin);
        
        javax.swing.tree.DefaultTreeCellRenderer rendererDbAdmin = new javax.swing.tree.DefaultTreeCellRenderer();
        
        //        javax.swing.tree.DefaultTreeCellEditor treeEditorDbAdmin = new javax.swing.CustomTreeCellEditor(new javax.swing.JButton());
        
        rendererDbAdmin.setLeafIcon(new javax.swing.ImageIcon(getClass().getResource("/document.gif")));
        
        rendererDbAdmin.setOpaque(true);
        
        //        jTreeDBSchema.setCellEditor(treeEditorDbAdmin);
        
        jTreeDBSchema.setCellRenderer(rendererDbAdmin);
        
        
        rendererDbAdmin.setTextSelectionColor(java.awt.Color.blue);
         
        
/*        jTreeDBSchema.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        //Listen for when the selection changes.
        jTreeDBSchema.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
 
            public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
 
                javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode)
 
                jTreeDBSchema.getLastSelectedPathComponent();
 
                if (node == null) return;
 
                Object nodeInfo = node.getUserObject();
 
                if (node.isLeaf()) {
 
                    invokeTreeActionPlan(nodeInfo.toString());
 
                } else {
                    // displayURL(helpURL);
                }
            }
        });
 
 *
 */
        ///PatientsBillingIntfr_
       
        
        return jTreeDBSchema;
    }
    
    private java.lang.Object[] getListOfTables(java.sql.Connection dbConnDB) {
        
        tablesVector = new java.util.Vector(1,1);
        
        try {
             java.sql.PreparedStatement pstmtTablesList = dbConnDB.prepareStatement("SELECT table_schema||'.'||table_name FROM information_schema.tables ORDER BY 1");

            //java.sql.PreparedStatement pstmtTablesList = dbConnDB.prepareStatement("SELECT relname FROM pg_catalog.pg_statio_user_tables ORDER BY relname");
            
            java.sql.ResultSet rsetTablesList = pstmtTablesList.executeQuery();
            
            while (rsetTablesList.next()) {
                
                tablesVector.addElement(rsetTablesList.getString(1));
                
            }
            
        } catch(java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sqlExec.getMessage());
            
        }
        
        listOfTables = tablesVector.toArray();
        
        return listOfTables;
        
    }
    
    public void createTreeNodes(javax.swing.tree.DefaultMutableTreeNode treeNode, java.lang.Object[] tablesList) {
        
        for (int i = 0; i < tablesList.length; i++) {
            
            if (tablesList[i].toString().startsWith("public.ac_")) {
                
                acNode.add(new javax.swing.tree.DefaultMutableTreeNode(tablesList[i].toString()));
                
            } else if (tablesList[i].toString().startsWith("public.hp_")) {
                
                hpNode.add(new javax.swing.tree.DefaultMutableTreeNode(tablesList[i].toString()));
                
            } else if (tablesList[i].toString().startsWith("public.st_")) {
                
                stNode.add(new javax.swing.tree.DefaultMutableTreeNode(tablesList[i].toString()));
                
            } else if (tablesList[i].toString().startsWith("public.pb_")) {
                
                pbNode.add(new javax.swing.tree.DefaultMutableTreeNode(tablesList[i].toString()));
                
            } else if (tablesList[i].toString().startsWith("pg_catalog.pg_") || tablesList[i].toString().startsWith("information_schema") || tablesList[i].toString().startsWith("public.pg_") || tablesList[i].toString().startsWith("public.pga_")) {

            }else{
                
                genNode.add(new javax.swing.tree.DefaultMutableTreeNode(tablesList[i].toString()));
                
            }
            
            treeNode.add(pbNode);
            
            treeNode.add(acNode);
            
            treeNode.add(stNode);
            
            treeNode.add(hpNode);
            
            treeNode.add(genNode);
            
        }
        
    }
    
}
