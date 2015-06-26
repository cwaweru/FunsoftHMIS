/*
 * DefaultDataSet.java
 *
 * Created on August 21, 2007, 1:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.charts;

import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.CategoryToPieDataset;

//import org.jfree.data.category.CategoryToPieDataset;
//import org.jfree.data.category.CategoryToPieDataset;
//import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author funsoft
 */
public class DefaultDataSet {

    /** Creates a new instance of DefaultDataSet */
    public DefaultDataSet() {
    }

   /* public static DefaultCategoryDataset createDataSet() {

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        // org.jfree.data.
        //  Comparable charles = new Comparable();
        dataSet.addValue(200, new String("Charles"), new String("Wanjema"));
        dataSet.addValue(300, new String("Mary"), new String("Wanjema"));
        dataSet.addValue(700, new String("Charles"), new String("Njuguna"));
        dataSet.addValue(4000, new String("Mary"), new String("Karanja"));
        dataSet.addValue(20000, new String("Peter"), new String("Kimani"));
        dataSet.addValue(9000, new String("Moses"), new String("Mugwanja"));
        dataSet.addValue(2000, new String("Tabitha"), new String("Seei"));
        dataSet.addValue(3000, new String("Marion"), new String("Kariuki"));
        return dataSet;
    }
*/
   /* public static org.jfree.data.general.DefaultPieDataset createPieDataSet(){
        
        org.jfree.data.general.DefaultPieDataset dataSet = new org.jfree.data.general.DefaultPieDataset();
        
        dataSet.insertValue(0, new String("Charles"), 200);
        dataSet.insertValue(1, new String("Mary"), 2000);
        dataSet.insertValue(2, new String("Kamau"), 1000);
        dataSet.insertValue(3, new String("Moses"), 500);
        dataSet.insertValue(4, new String("Peter"), 600);
        dataSet.insertValue(5, new String("David"), 700);
        dataSet.insertValue(6, new String("Tabitha"), 800);
        dataSet.insertValue(7, new String("Marion"), 400);
        
        return dataSet;
        
    }
    
    public static void main(String[] args) {

//        chart = org.jfree.chart.ChartFactory.createPieChart3D("My Chart", new org.jfree.data.CategoryToPieDataset(createDataSet(), 1,0), true,true,true);
      //  chart = org.jfree.chart.ChartFactory.createBarChart3D("My Chart", "X-Axis", "Y-Axis", createDataSet(), org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, true); //createPieChart3D("My Chart", new org.jfree.data.CategoryToPieDataset(createDataSet(), 1,0), true,true,true);
        
        pieChart = org.jfree.chart.ChartFactory.createPieChart3D("Trial Pie Chart", createPieDataSet(), true,true, new java.util.Locale("en_US"));
        
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(pieChart, true, true, true, true, true);

        javax.swing.JFrame chartFrame = new javax.swing.JFrame("Chart Frame");
        
                chartFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                this.dispose();
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
          this.dispose();
            }
        });
       // chartFrame.add

        chartFrame.setContentPane(chartPanel);

        chartFrame.setExtendedState(chartFrame.MAXIMIZED_BOTH);

        chartFrame.setVisible(true);
        
       
        
    }
        private static void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
        
     //   int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "Do you really want to exit application?", "Caution before closing application!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
      //  if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
            
          //  this.storePreferences();
            
            this.dispose();
            
       // } else {}
        // Add your handling code here:
    } 
        
    private static JFreeChart chart;
    private static JFreeChart pieChart;*/
}
