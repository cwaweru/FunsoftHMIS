/*
 * ReportDesignPanel.java
 *
 * Created on 30 March 2008, 10:28
 */

package biz.systempartners.reports;

import java.awt.Rectangle;

/**
 *
 * @author  Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class ReportDesignPanel extends javax.swing.JPanel {

    private int origPointY;

    private int origPointX;

    private int finalPointX;

    private int finalPointY;

    private Rectangle rectangle2Draw;
    
    /** Creates new form ReportDesignPanel */
    public ReportDesignPanel() {
        setOpaque(true);
//        this.setBackground(java.awt.Color.green);
        initComponents();
        //  this.setBackground(java.awt.Color.WHITE);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        origPointX = evt.getX();
        
        origPointY = evt.getY();
        
// TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

        finalPointX = evt.getX();
        
        finalPointY = evt.getY();

        rectangle2Draw = new java.awt.Rectangle(origPointX, origPointY, finalPointX-origPointX, finalPointY-origPointY);
        
        repaint();
        
        biz.systempartners.reports.BandProperties bandPropertiesDialog = new biz.systempartners.reports.BandProperties(new java.awt.Frame(),false);
        
        bandPropertiesDialog.setVisible(true);
        
        saveNewBand();
        
// TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
//evt.
// TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //       this.setOpaque(true);
//        this.setBackground(java.awt.Color.white);
// TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked
    
    public void paintComponent(java.awt.Graphics g){
        
        java.awt.Graphics2D graphics2d = (java.awt.Graphics2D)g;

        System.out.println(graphics2d.getBackground().toString());

        if (isOpaque()) {
            graphics2d.setColor(java.awt.Color.WHITE);
            graphics2d.fillRect(0, 0, getWidth(), getHeight());
        }
        System.out.println("These are the sizes : Width : "+this.getSize().width+" , Height : "+this.getSize().height);

        graphics2d.setColor(java.awt.Color.gray);
        for (int i = 1; i < 2000; i++){

            graphics2d.drawLine(i,0, i,this.getSize().height);
            graphics2d.drawLine(0,i, this.getSize().width,i);
            i = i + 5;
        }

     
        graphics2d.setColor(java.awt.Color.black);
        
        for (int i = 50; i < 2000; i++){
            graphics2d.drawLine(i,0, i,this.getSize().height);
            graphics2d.drawLine(0,i, this.getSize().width,i);
            i = i + 50;
        }
        
        if(rectangle2Draw != null){
            
            graphics2d.setColor(java.awt.Color.white);
            graphics2d.fillRect(rectangle2Draw.x, rectangle2Draw.y, rectangle2Draw.width , rectangle2Draw.height);
            graphics2d.drawRect(rectangle2Draw.x, rectangle2Draw.y, rectangle2Draw.width-1 , rectangle2Draw.height-1);
            
        }
        
    }
    
    private void saveNewBand(){
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
