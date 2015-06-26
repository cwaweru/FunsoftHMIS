/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.maintenance;

/**
 *
 * @author maina
 */
public class HandingOverVehicle extends javax.swing.JInternalFrame {
    private java.sql.Connection connectDB=null;
    private org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB=null;

    /**
     * Creates new form ReceivingVehicle
     */
    public HandingOverVehicle(java.sql.Connection conn,org.netbeans.lib.sql.pool.PooledConnectionSource pConn) {
        
        connectDB=conn;
        pConnDB=pConn;
        
        initComponents();
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

        vehicleRegSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        searchjTextField = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton9 = new javax.swing.JButton();
        sparesbuttonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        motherjPanel = new javax.swing.JPanel();
        receiveRequestjPanel = new javax.swing.JPanel();
        handedOverByjTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        repCarriedOutjTextArea1 = new javax.swing.JTextArea();
        repCarriedOutjLabel3 = new javax.swing.JLabel();
        vehicleReadyjLabel4 = new javax.swing.JLabel();
        mechHandlingjLabel9 = new javax.swing.JLabel();
        mechHandlingjTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        handedOverByjLabel1 = new javax.swing.JLabel();
        receivedByjTextField2 = new javax.swing.JTextField();
        driverNamejTextField = new javax.swing.JTextField();
        receivedByjLabel2 = new javax.swing.JLabel();
        driverNamejLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        driverNamejLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        timeReadyjTextField = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        searchjPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        requestIDjTextField = new javax.swing.JTextField();
        motherjPanelIndex1 = new javax.swing.JPanel();
        buttonjPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        vehicleRegSearchDialog.setModal(true);
        vehicleRegSearchDialog.setUndecorated(true);
        vehicleRegSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        searchjTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchjTextFieldCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel.add(searchjTextField, gridBagConstraints);

        jSearchTable.setShowHorizontalLines(false);
        /*    try {
            searchRowSet.setCommand("select product,selling_price,gl_code FROM st_stock_prices WHERE department = 'Pharmacy' order by product");
            searchRowSet.setConnectionSource(pConnDB);

            searchRowSet.execute();

            // crset2.setExecuteOnLoad(true);
            jSearchTable.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("product", "Description", false),
                new org.netbeans.lib.sql.models.TableModel.Column("selling_price", "Amount", false),
                new org.netbeans.lib.sql.models.TableModel.Column("gl_code", "Gl_code", false)

            }));
            // jSearchScrollPane.setViewportView(jSearchTable);

        } catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());
        }
        */
        jSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTableMouseClicked(evt);
            }
        });
        jSearchScrollPane.setViewportView(jSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

        jButton9.setText("Dispose");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        vehicleRegSearchDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Handing Over Motor Vehicle");
        setPreferredSize(new java.awt.Dimension(700, 500));
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        motherjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        motherjPanel.setLayout(new java.awt.GridBagLayout());

        receiveRequestjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Motor Vehicle Section", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.red));
        receiveRequestjPanel.setLayout(new java.awt.GridBagLayout());

        handedOverByjTextField.setColumns(10);
        handedOverByjTextField.setText("To Be Controlled Elsewhere");
        handedOverByjTextField.setToolTipText("");
        handedOverByjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handedOverByjTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        receiveRequestjPanel.add(handedOverByjTextField, gridBagConstraints);

        repCarriedOutjTextArea1.setColumns(20);
        repCarriedOutjTextArea1.setRows(5);
        jScrollPane1.setViewportView(repCarriedOutjTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        receiveRequestjPanel.add(jScrollPane1, gridBagConstraints);

        repCarriedOutjLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        repCarriedOutjLabel3.setText("Repairs Carried Out");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(15, 12, 15, 0);
        receiveRequestjPanel.add(repCarriedOutjLabel3, gridBagConstraints);

        vehicleReadyjLabel4.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        vehicleReadyjLabel4.setText("Time Vehicle Ready");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        receiveRequestjPanel.add(vehicleReadyjLabel4, gridBagConstraints);

        mechHandlingjLabel9.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        mechHandlingjLabel9.setText("Mechanic's Name (Handing)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 15, 0);
        receiveRequestjPanel.add(mechHandlingjLabel9, gridBagConstraints);

        mechHandlingjTextField.setColumns(10);
        mechHandlingjTextField.setText("mechanic's name To Be controlled Elsewhere");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        receiveRequestjPanel.add(mechHandlingjTextField, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        handedOverByjLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        handedOverByjLabel1.setText("Vehicle Handed Over By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel2.add(handedOverByjLabel1, gridBagConstraints);

        receivedByjTextField2.setColumns(10);
        receivedByjTextField2.setText("To Be Controlled Elsewhere");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(receivedByjTextField2, gridBagConstraints);

        driverNamejTextField.setColumns(10);
        driverNamejTextField.setText("Driver's Name to be controlled elsewhere");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(driverNamejTextField, gridBagConstraints);

        receivedByjLabel2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        receivedByjLabel2.setText("Vehicle Received By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel2.add(receivedByjLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 15, 0);
        receiveRequestjPanel.add(jPanel2, gridBagConstraints);

        driverNamejLabel11.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        driverNamejLabel11.setText("Driver's Name (Receiving)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 15, 0);
        receiveRequestjPanel.add(driverNamejLabel11, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        sparesbuttonGroup.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jRadioButton2.setText("No");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel3.add(jRadioButton2, gridBagConstraints);

        sparesbuttonGroup.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jRadioButton1.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel3.add(jRadioButton1, gridBagConstraints);

        driverNamejLabel10.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        driverNamejLabel10.setText("Spares Replaced?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 15);
        jPanel3.add(driverNamejLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        receiveRequestjPanel.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        timeReadyjTextField.setColumns(10);
        timeReadyjTextField.setText("Time Vechicle Ready Yet To Be Impl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(timeReadyjTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        receiveRequestjPanel.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        motherjPanel.add(receiveRequestjPanel, gridBagConstraints);

        searchjPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setText("Search By Job Card No.");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        searchjPanel1.add(jLabel11, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel12.setText("Repair/ Request Job No.");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel1.add(jButton2, gridBagConstraints);

        requestIDjTextField.setEditable(false);
        requestIDjTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(requestIDjTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        searchjPanel1.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        motherjPanel.add(searchjPanel1, gridBagConstraints);

        jTabbedPane1.addTab("Handing Over", motherjPanel);
        jTabbedPane1.addTab("Spares Used", motherjPanelIndex1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        buttonjPanel.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 50);
        buttonjPanel.add(jButton1, gridBagConstraints);

        jButton3.setText("Clear Fields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 50);
        buttonjPanel.add(jButton3, gridBagConstraints);

        jButton4.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 12);
        buttonjPanel.add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        getContentPane().add(buttonjPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchjTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchjTextFieldCaretUpdate
        // Add your handling code here:
        if(searchjTextField.getCaretPosition()>2){
            jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT request_id,vehicle_reg_no FROM maintenance.motor_vehicle_requests "
                +"WHERE request_id ILIKE '"+searchjTextField.getText()+"%' " 
                +"AND section_received='true' "
                +"ORDER BY request_id"));
        }
        
    }//GEN-LAST:event_searchjTextFieldCaretUpdate

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
        // Add your handling code here:
        int selectedRow=jSearchTable.getSelectedRow();
        
        
        timeReadyjTextField.setText(jSearchTable.getValueAt(selectedRow, 0).toString());
        
        java.lang.String kmIn=null;
        
        try{
            java.sql.Statement stt=connectDB.createStatement();
            java.sql.ResultSet rSet=stt.executeQuery("SELECT kms_in, kms_out,defects,tools_inside,spare_wheel,user_name,request_id FROM maintenance.motor_vehicle_requests "
                                                    + "WHERE vehicle_reg_no='"+timeReadyjTextField.getText()+"' AND create_date='"+jSearchTable.getValueAt(selectedRow, 1)+"'");
            
            while(rSet.next()){
//                kmInjTextField.setText(rSet.getString(1));
//                kmOutjTextField.setText(rSet.getString(2));
//                defectjTextArea.setText(rSet.getString(3));
//                toolsjTextArea.setText(rSet.getString(4));
//                mechHandlingjTextField.setText(rSet.getString(5));
//                driverNamejTextField.setText(rSet.getString(6));
//                requestIDjTextField.setText(rSet.getString(7));
            }
            
        }catch(java.sql.SQLException sqle){
            sqle.printStackTrace();
        }
        
        
        vehicleRegSearchDialog.dispose();
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // Add your handling code here:
        vehicleRegSearchDialog.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        
        java.awt.Point point = requestIDjTextField.getLocationOnScreen();
        vehicleRegSearchDialog.setSize(600, 200);
        vehicleRegSearchDialog.setLocation(point);
        vehicleRegSearchDialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String job_id="MVJOB_";
        
        try{
            
            connectDB.setAutoCommit(false);
            
            java.sql.Statement stt=connectDB.createStatement();
            java.sql.ResultSet rset=stt.executeQuery("SELECT nextval('maintenance.job_card_no_seq')");
            
            while(rset.next()){
                job_id+=rset.getString(1);
            }
            
            java.sql.PreparedStatement savePrepStt=connectDB.prepareStatement("INSERT INTO maintenance.mvehicle_job_card VALUES (?,?,?,?,?)");
            savePrepStt.setString(1, job_id);
            savePrepStt.setString(2, requestIDjTextField.getText());
            savePrepStt.setString(3, handedOverByjTextField.getText());
            savePrepStt.setString(4, receivedByjTextField2.getText());
            savePrepStt.setString(5, repCarriedOutjTextArea1.getText());
            
            savePrepStt.executeUpdate();
            
            java.sql.PreparedStatement updatePrepStt=connectDB.prepareStatement("UPDATE maintenance.motor_vehicle_requests SET section_received='true' WHERE request_id='"+requestIDjTextField.getText()+"'");
            updatePrepStt.executeUpdate();
            
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            String jobType=null;
            
            if(requestIDjTextField.getText().startsWith("MVS")){
                jobType="for Service";
            }else if(requestIDjTextField.getText().startsWith("MVR")){
                jobType="for Repair";
            }else{
                jobType="";
            }
            
            javax.swing.JOptionPane.showMessageDialog(this, timeReadyjTextField.getText()+" has been received "+jobType,
                    "Insert Successful",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            jButton3.doClick();
            
        }catch(java.sql.SQLException sqle){
            sqle.printStackTrace();
            
            try{
                connectDB.rollback();
                connectDB.setAutoCommit(true);
            }catch(java.sql.SQLException sqler){
                sqler.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void handedOverByjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handedOverByjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_handedOverByjTextFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        requestIDjTextField.setText("");
//        timeReadyjTextField.setText("");
//        kmInjTextField.setText("");
//        kmOutjTextField.setText("");
//        defectjTextArea.setText("");
//        toolsjTextArea.setText("");
//        mechHandlingjTextField.setText("");
//        driverNamejTextField.setText("");
//        handedOverByjTextField.setText("");
//        receivedByjTextField2.setText("");
//        repCarriedOutjTextArea1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonjPanel;
    private javax.swing.JLabel driverNamejLabel10;
    private javax.swing.JLabel driverNamejLabel11;
    private javax.swing.JTextField driverNamejTextField;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel handedOverByjLabel1;
    private javax.swing.JTextField handedOverByjTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel mechHandlingjLabel9;
    private javax.swing.JTextField mechHandlingjTextField;
    private javax.swing.JPanel motherjPanel;
    private javax.swing.JPanel motherjPanelIndex1;
    private javax.swing.JPanel receiveRequestjPanel;
    private javax.swing.JLabel receivedByjLabel2;
    private javax.swing.JTextField receivedByjTextField2;
    private javax.swing.JLabel repCarriedOutjLabel3;
    private javax.swing.JTextArea repCarriedOutjTextArea1;
    private javax.swing.JTextField requestIDjTextField;
    private javax.swing.JPanel searchjPanel1;
    private javax.swing.JTextField searchjTextField;
    private javax.swing.ButtonGroup sparesbuttonGroup;
    private javax.swing.JTextField timeReadyjTextField;
    private javax.swing.JLabel vehicleReadyjLabel4;
    private javax.swing.JDialog vehicleRegSearchDialog;
    // End of variables declaration//GEN-END:variables
}
