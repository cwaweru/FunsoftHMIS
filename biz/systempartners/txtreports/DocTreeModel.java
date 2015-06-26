/*
 * DocTreeModel.java
 *
 * Created on August 20, 2005, 9:11 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package biz.systempartners.txtreports;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class DocTreeModel extends javax.swing.tree.DefaultMutableTreeNode {
    
    private javax.swing.tree.DefaultMutableTreeNode docTreeNode;
    
    /** Creates a new instance of DocTreeModel */
    public DocTreeModel() {
    
    }
    
    public javax.swing.tree.DefaultMutableTreeNode getDocTreeNode(){
        
        return docTreeNode;
    }
    
    public void setDocTreeNode(javax.swing.tree.DefaultMutableTreeNode docTreeNode){
        
    }
}
