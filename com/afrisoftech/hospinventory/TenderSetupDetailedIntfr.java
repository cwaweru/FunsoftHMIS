/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

/**
 *
 * @author funsoft
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class TenderSetupDetailedIntfr extends javax.swing.JInternalFrame {
     java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.lang.String  formatedDate;

    /**
     * Creates new form TenderSetupDetailedIntfr
     */
    public TenderSetupDetailedIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        super("setting up a detailed tender");
        connectDB = connDb;
        pConnDB = pconnDB;
         
        initComponents();
        setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog11 = new javax.swing.JDialog();
        jSearchPanel11 = new javax.swing.JPanel();
        jTextField11111 = new javax.swing.JTextField();
        jSearchScrollPane11 = new javax.swing.JScrollPane();
        jSearchTable11 = new com.afrisoftech.dbadmin.JTable();
        jButton511 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TenderNameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TenderDescTxtar = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TenderPriceTxt = new javax.swing.JTextField();
        ClosingdatePicker = new com.afrisoftech.lib.DatePicker();
        advertdatePicker = new com.afrisoftech.lib.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jTextField361 = new javax.swing.JTextField();
        searchButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProductsTable = new com.afrisoftech.dbadmin.JTable();
        SaveBtn = new javax.swing.JButton();
        EditBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        RemoveRowBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jSearchDialog11.setModal(true);
        jSearchDialog11.setUndecorated(true);
        jSearchDialog11.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel11.setLayout(new java.awt.GridBagLayout());

        jTextField11111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel11.add(jTextField11111, gridBagConstraints);

        jSearchTable11.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable11.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable11MouseClicked(evt);
            }
        });
        jSearchScrollPane11.setViewportView(jSearchTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel11.add(jSearchScrollPane11, gridBagConstraints);

        jButton511.setText("Close");
        jButton511.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton511ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(jButton511, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog11.getContentPane().add(jSearchPanel11, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tender Details"));

        jLabel1.setText("Tender N0:");

        jLabel2.setText("Tender Description");

        TenderDescTxtar.setColumns(20);
        TenderDescTxtar.setRows(5);
        TenderDescTxtar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportView(TenderDescTxtar);

        jLabel3.setText("Advertising Date");

        jLabel4.setText("Closing Date");

        jLabel5.setText("Tender Price");

        jLabel6.setText("Select Requisition");

        jPanel61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel61.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel61.setLayout(new java.awt.GridBagLayout());

        jTextField361.setEditable(false);
        jTextField361.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField361ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(jTextField361, gridBagConstraints);

        searchButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton1.setToolTipText("Search");
        searchButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel61.add(searchButton1, gridBagConstraints);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ClosingdatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(advertdatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenderPriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TenderNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TenderNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(advertdatePicker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ClosingdatePicker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TenderPriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
        );

        ProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item", "Specification", "Quantity", "Remarks"
            }
        ));
        jScrollPane3.setViewportView(ProductsTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263))
        );

        SaveBtn.setText("Save /Print Tender");
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });

        EditBtn.setText("Edit Tender");

        ClearBtn.setText("Clear");

        RemoveRowBtn.setText("Remove  Row");

        jButton1.setText("Close   ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(SaveBtn)
                .addGap(46, 46, 46)
                .addComponent(EditBtn)
                .addGap(34, 34, 34)
                .addComponent(RemoveRowBtn)
                .addGap(33, 33, 33)
                .addComponent(ClearBtn)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveBtn)
                    .addComponent(EditBtn)
                    .addComponent(ClearBtn)
                    .addComponent(RemoveRowBtn)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBtnActionPerformed
        // To setup a tender
        
        String Tender_no=TenderNameTxt.getText();
        String Tender_desc=TenderDescTxtar.getText();
        String Tender_price=TenderPriceTxt.getText();
        Integer T_id = null;
        int j=0;
        
        
        try {
           // connectDB.setAutoCommit(false);
          
            
                if (!Tender_no.equalsIgnoreCase("")){
                  
                    java.sql.Statement stmtTable11 = connectDB.createStatement();
                        java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("SELECT count(tender_id) as num  FROM tendersetuptbl WHERE tender_no ilike  '" +Tender_no + "%'");

                        while (rsetTable11.next()) {
                            j = rsetTable11.getInt("num");
                        }
                        
                        System.out.println("j is "+j);
                        if (j <= 0) {
                        java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into tendersetuptbl(tender_no,tender_desc,tender_price,advert_date,closing_date) values( UPPER(?), initcap(?),?,?,?)");
                        pstmt1.setObject(1, Tender_no);
                        pstmt1.setObject(2, Tender_desc);
                        
                        double tender_price=Double.parseDouble(Tender_price);    
                        pstmt1.setObject(3,tender_price);
                        pstmt1.setDate(4,com.afrisoftech.lib.SQLDateFormat.getSQLDate(advertdatePicker.getDate()));
                        pstmt1.setDate(5,com.afrisoftech.lib.SQLDateFormat.getSQLDate(ClosingdatePicker.getDate()));
                        pstmt1.executeUpdate();
                        
                        //get the tender id for the opentendertbl
                        java.sql.Statement stmtTable12 = connectDB.createStatement();
                        java.sql.ResultSet rsetTable12 = stmtTable12.executeQuery("SELECT tender_id  FROM tendersetuptbl WHERE tender_no ilike  '" +Tender_no + "%'");

                        while (rsetTable12.next()) {
                           T_id = rsetTable12.getInt("tender_id");
                        }
                        
                        
                   for (int i = 0; i < ProductsTable.getRowCount(); i++){
                        //inserting to the openingtendertbl
                       try{
                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into opentendertbl (item_spec,item_qty,item_name,item_remarks,tender_id,item_user_code) values(?,?,?,?,?,?)");
                        Object obj_item_spec= ProductsTable.getValueAt(i,2).toString();
                        pstmt2.setObject(1,obj_item_spec);
                        
                        int item_qty=Integer.parseInt(ProductsTable.getValueAt(i, 3).toString()); 
                        pstmt2.setObject(2,item_qty);
                        
                        pstmt2.setObject(3, ProductsTable.getValueAt(i, 1).toString());
                        
                        Object obj_item_remarks= ProductsTable.getValueAt(i,4).toString();
                        pstmt2.setObject(4, obj_item_remarks);
                        pstmt2.setObject(5,T_id);
                         pstmt2.setObject(6, ProductsTable.getValueAt(i, 0).toString());
                        pstmt2.executeUpdate(); 
                         javax.swing.JOptionPane.showMessageDialog(null, "Insert done successfully","Comfirmation",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                       }
                       catch(Exception es){System.out.println(es);}
                       
                                           }
                   
                    javax.swing.JOptionPane.showMessageDialog(null, "Insert done successfully","Comfirmation",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                   //open a new table
                            for (int k = 0; k < ProductsTable.getRowCount(); k++) {
                               for (int r = 0; r < ProductsTable.getColumnCount(); r++) {
                                        ProductsTable.getModel().setValueAt(null, k, r);
                                               }
                        
                }    
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(null, "The Tender already exists","Comfirmation",javax.swing.JOptionPane.ERROR_MESSAGE);
                
                }
            
            //connectDB.commit();
            //connectDB.setAutoCommit(true);
            
                }
                else{
                javax.swing.JOptionPane.showMessageDialog(null, "You need to enter the Tender N0:");
                
                }
                
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sq.getMessage());
            
        } // Add your handling code here:
    }//GEN-LAST:event_SaveBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //closing the internal frame
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
   //To search Suppliers
        
       // tenderSearchButtonClicked(DispatchTenderTxt,DispatchMainSearchDialog);
         System.out.println("Showing dialog");

        java.awt.Point point = jTextField361.getLocationOnScreen();

        jSearchDialog11.setSize(400, 200);

        jSearchDialog11.setLocation(point);

        jSearchDialog11.setVisible(true);

        // Add your handling code here:
        
        
    }                                             

    private void searchButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        //To search Suppliers
        
       // tenderSearchButtonClicked(DispatchTenderTxt,DispatchMainSearchDialog);
         System.out.println("Showing dialog");

        java.awt.Point point = jTextField361.getLocationOnScreen();

        jSearchDialog11.setSize(400, 200);

        jSearchDialog11.setLocation(point);

        jSearchDialog11.setVisible(true);

        // Add your handling code here:
    }//GEN-LAST:event_searchButton1ActionPerformed

    private void jTextField11111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11111CaretUpdate
        // int j = 0;
        // try {
            //  java.sql.Statement stmt = connectDB.createStatement();
            // java.sql.ResultSet rset = stmt.executeQuery("SELECT count(requisition_no) FROM st_receive_requisation WHERE requisition_no ilike 'PRQ%' and processed = false");

            //   while (rset.next()) {
                //      j = rset.getInt(1);
                //  }
            //  if (j > 0){

                jSearchTable11.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct requisition_no as Req_No,cost_center from st_receive_requisation where requisition_no ILIKE '"+jTextField11111.getText().toString()+"%' and requisition_no ilike '%RQ%' ORDER BY requisition_no"));
                //   }
            /*   try {
                searchRowSet1.execute("select distinct order_no as scheme,(supplier) as name from st_orders where order_no ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY order_no");

                jSearchTable1.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet1, new org.netbeans.lib.sql.models.TableModel.Column[] {
                    new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Order No.", false),
                    new org.netbeans.lib.sql.models.TableModel.Column("name", "Supplier", false),
                    // new org.netbeans.lib.sql.models.TableModel.Column("account_no", "Acc No.", false)
                }));
                */

                //   } catch(java.sql.SQLException sqlExec) {

                //      javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

                //   }
            jSearchTable11.setShowHorizontalLines(false);
            jSearchScrollPane11.setViewportView(jSearchTable11);

            // Add your handling code here:
    }//GEN-LAST:event_jTextField11111CaretUpdate

    private void jSearchTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable11MouseClicked
        jTextField361.setText(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 0).toString());
        //jTextField361.setText(jSearchTable11.getValueAt(DispatchMainSearchTable.getSelectedRow(), 0).toString());
       jSearchDialog11.dispose();
    }//GEN-LAST:event_jSearchTable11MouseClicked

    private void jButton511ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton511ActionPerformed
        jSearchDialog11.dispose();    // Add your handling code here:
    }//GEN-LAST:event_jButton511ActionPerformed

    private void jTextField361ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField361ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField361ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearBtn;
    private com.afrisoftech.lib.DatePicker ClosingdatePicker;
    private javax.swing.JButton EditBtn;
    private javax.swing.JTable ProductsTable;
    private javax.swing.JButton RemoveRowBtn;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JTextArea TenderDescTxtar;
    private javax.swing.JTextField TenderNameTxt;
    private javax.swing.JTextField TenderPriceTxt;
    private com.afrisoftech.lib.DatePicker advertdatePicker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton511;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JDialog jSearchDialog11;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JTextField jTextField11111;
    private javax.swing.JTextField jTextField361;
    private javax.swing.JButton searchButton1;
    // End of variables declaration//GEN-END:variables
}
