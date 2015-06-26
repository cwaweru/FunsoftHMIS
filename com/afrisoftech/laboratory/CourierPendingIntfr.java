/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */

package com.afrisoftech.laboratory;

/**
 *
 * @author  root
 */
public class CourierPendingIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable {
    
    /** Creates new form loanpymntintfr */
    javax.swing.table.TableModel tableModel = null;
    
    java.sql.Connection connectDB = null;
    com.afrisoftech.lib.DBObject dbObject;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.lang.Thread getListThread;
    
    boolean getList;
    
    public CourierPendingIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        dbObject = new com.afrisoftech.lib.DBObject();
        getListThread = new java.lang.Thread(this, "Update_Lab_Tests");
        
        initComponents();
        
        getList = true;
        
        getListThread.start();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable(){
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false,true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Courier Pending Requests");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ColorPreview.gif")));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minusarm.gif")));
        jButton3.setMnemonic('l');
        jButton3.setText("Clear");
        jButton3.setToolTipText("Click here to clear textfields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BD14755_.GIF")));
        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.setToolTipText("Click here to close window");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel7, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField6.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jTextField6, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        jPanel21.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setAutoscrolls(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Time", "Way Bill No", "From", "To", "Priority", "Send"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel21.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel2.add(jPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel1, gridBagConstraints);

        setBounds(0, 0, 748, 428);
    }//GEN-END:initComponents
        
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        long dateNow = calendar.getTimeInMillis();
        
        java.sql.Date datenowSql1= new java.sql.Date(dateNow);
        
        System.out.println(datenowSql1.toString());
        
        java.sql.Timestamp datenowSql= new java.sql.Timestamp(dateNow);
        
        System.out.println(datenowSql.toString());
        //   try{

        
        try {
            connectDB.setAutoCommit(false);
            java.sql.Statement stm12t = connectDB.createStatement();
            
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.Statement stm121 = connectDB.createStatement();
            java.sql.Statement stm1211 = connectDB.createStatement();
            java.sql.Statement stm122 = connectDB.createStatement();
            java.sql.Statement stm122x = connectDB.createStatement();
            java.sql.Statement stm1 = connectDB.createStatement();
            
            
            java.sql.Statement stm121q = connectDB.createStatement();
            
            
            
            
            
            int i = jTable1.getSelectedRow();
            // for (int i = 0; i < jTable1.getRowCount(); i++){
            if (jTable1.getValueAt(i,0) != null){
                if (Boolean.valueOf(jTable1.getModel().getValueAt(i, 6).toString()) == java.lang.Boolean.TRUE) {
                    
                    System.out.println("Count"+i);
                    
                    
                    java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE pb_courier SET sent = true,sent_time =current_timestamp where wbill_no = '"+jTable1.getValueAt(jTable1.getSelectedRow(),2).toString()+"'");
                    pstmt46.executeUpdate();
                    
                    
                    
                    
                    
                    //}
                    
                    // }
                }
            }
            
            
            // }
            
            
            //}
            javax.swing.JOptionPane.showMessageDialog(this,"Successfuly Sent","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            com.afrisoftech.reports.WayBillPdf pol = new com.afrisoftech.reports.WayBillPdf();
            pol.WayBillPdf(connectDB, jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            for (int k = 0; k < jTable1.getRowCount(); k++ ) {
                for (int r = 0; r < jTable1.getColumnCount(); r++ ) {
                    jTable1.getModel().setValueAt(null,k,r);
                }
            }
            
        }catch(java.sql.SQLException sq){
            
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void searchButtonClicked() {
        
  /*      System.out.println("Showing dialog");
   
        java.awt.Point point = this.jTextField7.getLocationOnScreen();
   
        jSearchDialog11.setSize(400,200);
   
        jSearchDialog11.setLocation(point);
   
        jSearchDialog11.setVisible(true);
   */
        
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);   // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for (int k = 0; k < jTable1.getRowCount(); k++ ) {
            for (int r = 0; r < jTable1.getColumnCount(); r++ ) {
                jTable1.getModel().setValueAt(null,k,r);
            }
        }
        
        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    public void tableModelTableChanged(javax.swing.event.TableModelEvent evt) {
 /*
       // double totals = 0.00;
        double totals = Double.parseDouble(jTextField1.getText());
        int i = 0;
  
        for (int j = 0; j < jTable1.getRowCount(); j++) {
  
            System.out.println(jTable1.getModel().getValueAt(j, 4).toString());
  
            if (Boolean.valueOf(jTable1.getModel().getValueAt(j, 4).toString()) == java.lang.Boolean.FALSE) {
  
                totals = totals - Double.parseDouble(jTable1.getModel().getValueAt(j, 3).toString());
  
                i++;
            }
        }
  
        jTextField1.setText(Double.toString(totals));
  
     //   jTextField2.setText(Integer.toString(i));
  
        System.out.println(totals);
  
        System.out.println(i);
  
        System.out.println("This table has changed");
  */
    }
    
    private void populateTable1(java.lang.String patient_no) {
        
        
    }
    
    public void run() {
        
        while(getList) {
            
            runGetListThread();
            
            try {
                
                Thread.currentThread().sleep(10000);
                
            } catch(java.lang.InterruptedException IntExec){
                
                javax.swing.JOptionPane.showMessageDialog(this, IntExec.getMessage(), "Interruption Error!", javax.swing.JOptionPane.ERROR_MESSAGE);
                
            }
            
            // getList = false;
            
        }
        
    }
    
    public void runGetListThread() {
        
        int j = 0;
        int i = 0;
        int n = 0;
        int patNo = 0;
        try {
            
            java.sql.Statement stmtTable11 = connectDB.createStatement();
            
            // java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select date,patient_no,name,payment,'false' as bill from hp_patient_visit where transaction_type ilike 'reg%' and date = current_date  ORDER BY date");
            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select distinct count(wbill_no) from pb_courier where sent = false and input_date::date>=current_date -2 ");
            
            while (rsetTable11.next()) {
                patNo = rsetTable11.getInt(1);
                
            }
            
            java.sql.Statement stmtTable1 = connectDB.createStatement();
            
            java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select distinct date,input_time,wbill_no,consigner_comp,consignee_comp,priority,sent,input_date from pb_courier where sent = false and input_date::date>=current_date -2 ORDER BY input_date asc");
            
            
            while (rsetTable1.next()) {
                
                jTable1.setValueAt(rsetTable1.getObject(1), i, 0);
                jTable1.setValueAt(rsetTable1.getObject(2), i, 1);
                jTable1.setValueAt(rsetTable1.getObject(3), i, 2);
                jTable1.setValueAt(rsetTable1.getObject(4), i, 3);
                jTable1.setValueAt(rsetTable1.getObject(5), i, 4);
                jTable1.setValueAt(rsetTable1.getObject(6), i, 5);
                jTable1.setValueAt(java.lang.Boolean.valueOf(false), i, 6);
                
                
                
                i++;
                
            }
            
            //}
            
            
            
            
        } catch(java.sql.SQLException sqlExec) {
            
            sqlExec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
            
        }
        if(patNo >5){
            
            this.jLabel1.setText("Queue Long ,'"+patNo+"' Deliveries Are Waiting");
            this.jLabel1.setForeground(new java.awt.Color(255,0,51));
        }else{
            this.jLabel1.setText("Queue Going Smoothly Only '"+patNo+"' Deliveries Waiting");
            this.jLabel1.addNotify();
            
            
            this.jLabel1.setForeground(new java.awt.Color(51,51,255));
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JButton jButton4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}
