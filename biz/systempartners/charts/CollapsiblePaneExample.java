/*
 * CollapsiblePnaeExample.java
 *
 * Created on October 9, 2007, 8:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.charts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXFrame;

/**
 *
 * @author Admin
 */
public class CollapsiblePaneExample {
    
    /** Creates a new instance of CollapsiblePnaeExample */
    public CollapsiblePaneExample() {
    }
    public static void main(String[] args){
        showCollapsiblePane();
    }
    static void showCollapsiblePane() {
        JXCollapsiblePane cp = new JXCollapsiblePane();
        
        
        // JXCollapsiblePane can be used like any other container
        cp.setLayout(new BorderLayout());
        
        // the Controls panel with a textfield to filter the tree
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        controls.add(new JLabel("Search:"));
        controls.add(new JTextField(10));
        controls.add(new JButton("Refresh"));
        controls.setBorder(new TitledBorder("Filters"));
        cp.add("Center", controls);
        
        JXFrame frame = new JXFrame();
        frame.setLayout(new BorderLayout());
        
        // Put the "Controls" first
        frame.add("North", cp);
        
        // Then the tree - we assume the Controls would somehow filter the tree
        JScrollPane scroll = new JScrollPane(new JTree());
        frame.add("Center", scroll);
        
        // Show/hide the "Controls"
        JButton toggle = new JButton(cp.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        toggle.setText("Show/Hide Search Panel");
        frame.add("South", toggle);
        // get the built-in toggle action
        Action toggleAction = cp.getActionMap().
                get(JXCollapsiblePane.TOGGLE_ACTION);
        
        // use the collapse/expand icons from the JTree UI
        toggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON,
                UIManager.getIcon("Tree.expandedIcon"));
        toggleAction.putValue(JXCollapsiblePane.EXPAND_ICON,
                UIManager.getIcon("Tree.collapsedIcon"));
        
        frame.pack();
        frame.setVisible(true);
    }
}
