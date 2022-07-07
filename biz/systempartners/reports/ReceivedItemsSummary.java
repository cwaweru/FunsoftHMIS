/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package biz.systempartners.reports;

/**
 *
 * @author  funsoft
 */
public class ReceivedItemsSummary extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    
    /** Creates new form ReportIntfr */
    public ReceivedItemsSummary(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
      //  loadReport();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        stratDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        perstoreComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        perCategoryComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        perItemComboBox = new javax.swing.JComboBox();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Received Items Summary");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(stratDatePicker, gridBagConstraints);

        beginDateLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        beginDateLbl.setText("PER CATEGORY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        endDateLbl.setText("Date(Period)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        perstoreComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '-SELECT STORE-' UNION select distinct on(store_name) upper(store_name) as store_name  from st_main_stores ORDER BY 1")
        );
        perstoreComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perstoreComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        headerPanel.add(perstoreComboBox, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("PER STORE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(jLabel1, gridBagConstraints);

        perCategoryComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, " SELECT '-SELECT CATEGORY-' UNION SELECT distinct sub_cat_code FROM st_stock_item order by 1"));
        perCategoryComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perCategoryComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        headerPanel.add(perCategoryComboBox, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("PER ITEM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        headerPanel.add(jLabel2, gridBagConstraints);

        perItemComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"SELECT '--SELECT ITEM' UNION SELECT distinct description FROM st_stock_item order by 1--'-SELECT CATEGORY' UNION select  description as name from st_main_category order by 1"));
        perItemComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perItemComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        headerPanel.add(perItemComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyJscrollPane.setViewportView(reportBodyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(reportBodyJscrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(reportBodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        setBounds(0, 0, 643, 306);
    }// </editor-fold>//GEN-END:initComponents

    private void perstoreComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perstoreComboBoxItemStateChanged
      if(perstoreComboBox.getSelectedIndex()>0){
          System.out.println( ( "SELECT order_no as LPO_NO,delivery_note_no,  item,item_code ,units,    price_per_item,  quantity_received,   vat_amount,debit as Amount,  supplier, store,delivered_by,received_by,    date "
  +"FROM st_stock_cardex where transaction_type='Receiving' and store ilike '"+perstoreComboBox.getSelectedItem().toString()+"' "
                 + " and  date  between ('"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"') "
   +" group by order_no,delivery_note_no,  item,item_code ,units,price_per_item,quantity_received,vat_amount,debit,  supplier, store,delivered_by,received_by,date "
   +" having sum(debit-quantity_ordered) > 0"
                + " order by 1,2,3 "));
        try{
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT order_no as LPO_NO,delivery_note_no,  item,item_code ,units,    price_per_item,  quantity_received,   vat_amount,debit as Amount,  supplier, store,delivered_by,received_by,    date "
  +"FROM st_stock_cardex where transaction_type='Receiving' and store ilike '"+perstoreComboBox.getSelectedItem().toString()+"' "
                 + " and  date  between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' "
   +" group by order_no,delivery_note_no,  item,item_code ,units,price_per_item,quantity_received,vat_amount,debit,  supplier, store,delivered_by,received_by,date "
   +" having sum(debit-quantity_ordered) > 0"
                + " order by 1,2,3 "));
       }
       catch(Exception ex){
           System.out.println("the store error is "+ex);
       }
      }
    }//GEN-LAST:event_perstoreComboBoxItemStateChanged

    private void perCategoryComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perCategoryComboBoxItemStateChanged
           if(perCategoryComboBox.getSelectedIndex()>0){
               
               System.out.println( ( ""
                + " SELECT st_stock_cardex.order_no as LPO_NO,st_stock_cardex.delivery_note_no,  st_stock_cardex.item,st_stock_cardex.item_code ,st_stock_cardex.units,  " +
"   st_stock_cardex.price_per_item,  st_stock_cardex.quantity_received,   st_stock_cardex.vat_amount, " +
"   st_stock_cardex.debit as Amount,  st_stock_cardex.supplier, st_stock_cardex.store, " +
"   st_stock_cardex.delivered_by,st_stock_cardex.received_by, st_stock_cardex.date " +
"  From " +
"  st_stock_cardex Inner Join" +
"  st_stock_item On st_stock_cardex.item_code = st_stock_item.item_code " +
"Where " +
"  st_stock_cardex.transaction_type = 'Receiving' And " +
"  st_stock_item.sub_cat_code ILIKE '"+perCategoryComboBox.getSelectedItem().toString()+"' "
                + "and st_stock_item.date   between ('"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"') "  
   
            +    "   group by  " +
"  st_stock_cardex.date, st_stock_cardex.order_no ,st_stock_cardex.delivery_note_no,  st_stock_cardex.item,st_stock_cardex.item_code ,st_stock_cardex.units, " +
"   st_stock_cardex.price_per_item,  st_stock_cardex.quantity_received,   st_stock_cardex.vat_amount, " +
"   st_stock_cardex.debit ,  st_stock_cardex.supplier, st_stock_cardex.store, " +
"   st_stock_cardex.delivered_by,st_stock_cardex.received_by, st_stock_cardex.date " +
"    having sum(st_stock_cardex.debit-st_stock_cardex.quantity_ordered) > 0"
                + " order by 1,2,3 ;"
                + ""));
        try{  reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,""
                + " SELECT st_stock_cardex.order_no as LPO_NO,st_stock_cardex.delivery_note_no,  st_stock_cardex.item,st_stock_cardex.item_code ,st_stock_cardex.units,  " +
"   st_stock_cardex.price_per_item,  st_stock_cardex.quantity_received,   st_stock_cardex.vat_amount, " +
"   st_stock_cardex.debit as Amount,  st_stock_cardex.supplier, st_stock_cardex.store, " +
"   st_stock_cardex.delivered_by,st_stock_cardex.received_by, st_stock_cardex.date " +
"  From " +
"  st_stock_cardex Inner Join" +
"  st_stock_item On st_stock_cardex.item_code = st_stock_item.item_code " +
"Where " +
"  st_stock_cardex.transaction_type = 'Receiving' And " +
"  st_stock_item.sub_cat_code ILIKE '"+perCategoryComboBox.getSelectedItem().toString()+"' "
                + "and st_stock_cardex.date  between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' "  
   
            +    "   group by  " +
"  st_stock_cardex.date, st_stock_cardex.order_no ,st_stock_cardex.delivery_note_no,  st_stock_cardex.item,st_stock_cardex.item_code ,st_stock_cardex.units, " +
"   st_stock_cardex.price_per_item,  st_stock_cardex.quantity_received,   st_stock_cardex.vat_amount, " +
"   st_stock_cardex.debit ,  st_stock_cardex.supplier, st_stock_cardex.store, " +
"   st_stock_cardex.delivered_by,st_stock_cardex.received_by, st_stock_cardex.date " +
"    having sum(st_stock_cardex.debit-st_stock_cardex.quantity_ordered) > 0"
                + " order by 1,2,3 ;"
                + ""));
               }
       catch(Exception ex){
           System.err.println("the category error is "+ex);
       }
    }

    }//GEN-LAST:event_perCategoryComboBoxItemStateChanged

    private void perItemComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perItemComboBoxItemStateChanged
         if(perItemComboBox.getSelectedIndex()>0){  
             
             System.out.println( ( "SELECT order_no as LPO_NO,delivery_note_no,  item,item_code ,units,    price_per_item,  quantity_received,   vat_amount,debit as Amount,  supplier, store,delivered_by,received_by,    date "
  +"FROM st_stock_cardex where transaction_type='Receiving' and item ilike '"+perItemComboBox.getSelectedItem().toString()+"' "
                 + "  and date  between ('"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"') "
   +"group by order_no,delivery_note_no,  item,item_code ,units,price_per_item,quantity_received,vat_amount,debit,  supplier, store,delivered_by,received_by,date "
   +" having sum(debit-quantity_ordered) > 0"
                + " order by 1,2,3 "));
             
        try{ reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT order_no as LPO_NO,delivery_note_no,  item,item_code ,units,    price_per_item,  quantity_received,   vat_amount,debit as Amount,  supplier, store,delivered_by,received_by,    date "
  +"FROM st_stock_cardex where transaction_type='Receiving' and item ilike '"+perItemComboBox.getSelectedItem().toString()+"' "
                 + " and  date  between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(stratDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' "
   +"group by order_no,delivery_note_no,  item,item_code ,units,price_per_item,quantity_received,vat_amount,debit,  supplier, store,delivered_by,received_by,date "
   +" having sum(debit-quantity_ordered) > 0"
                + " order by 1,2,3 "));
   }
       catch(Exception ex){
           System.err.println("the item error is "+ex);
       }
    }

    }//GEN-LAST:event_perItemComboBoxItemStateChanged
    
 
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox perCategoryComboBox;
    private javax.swing.JComboBox perItemComboBox;
    private javax.swing.JComboBox perstoreComboBox;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    private com.afrisoftech.lib.DatePicker stratDatePicker;
    // End of variables declaration//GEN-END:variables
    
}