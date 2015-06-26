/*
 * SwingXTest.java
 *
 * Created on October 8, 2007, 4:41 PM
 */

package biz.systempartners.charts;

/**
 *
 * @author  Admin
 */
public class SwingXTest extends javax.swing.JFrame {
    
    /** Creates new form SwingXTest */
    public SwingXTest() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jXMultiSplitPane1 = new org.jdesktop.swingx.JXMultiSplitPane();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTreeTable1 = new org.jdesktop.swingx.JXTreeTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });
        jXDatePicker1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jXDatePicker1FocusLost(evt);
            }
        });

        jXTreeTable1.setCellSelectionEnabled(true);
        jXTreeTable1.setColumnControl(null);
        jXTreeTable1.setColumnControlVisible(true);
        jScrollPane1.setViewportView(jXTreeTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXMultiSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jXMultiSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(444, 444, 444))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        org.jdesktop.swingx.treetable.DefaultTreeTableModel treeTableModel = new org.jdesktop.swingx.treetable.DefaultTreeTableModel();
        org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode treeTableNode = new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Main");
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("New"));
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Old"));
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Cousin"));
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Aunt"));
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Baba"));
        treeTableNode.add(new org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode("Mama"));
        org.jdesktop.swingx.table.DefaultTableColumnModelExt columnModel = new org.jdesktop.swingx.table.DefaultTableColumnModelExt();
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        columnModel.addColumn(new org.jdesktop.swingx.table.TableColumnExt());
        treeTableModel.setRoot(treeTableNode);
//        jXTreeTable1.setColumnModel(columnModel);
        jXTreeTable1.setTreeTableModel(treeTableModel);

// TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown
    
    
    private void jXDatePicker1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jXDatePicker1FocusLost
// TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker1FocusLost
    
    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SwingXTest().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXMultiSplitPane jXMultiSplitPane1;
    private org.jdesktop.swingx.JXTreeTable jXTreeTable1;
    // End of variables declaration//GEN-END:variables
    
}
