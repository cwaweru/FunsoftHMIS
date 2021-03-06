/*
 * stockprices.java
 *
 * Created on November 5, 2002, 11:47 AM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  root
 */
public class MergingAccountsIntfr extends javax.swing.JInternalFrame {
    javax.swing.JComboBox cmbox2 =null;
    java.sql.Connection connectDB = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public MergingAccountsIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog22 = new javax.swing.JDialog();
        jSearchPanel22 = new javax.swing.JPanel();
        jTextField1132 = new javax.swing.JTextField();
        jSearchScrollPane22 = new javax.swing.JScrollPane();
        jSearchTable22 = new com.afrisoftech.dbadmin.JTable();
        jButton522 = new javax.swing.JButton();
        jSearchDialog221 = new javax.swing.JDialog();
        jSearchPanel221 = new javax.swing.JPanel();
        jTextField11321 = new javax.swing.JTextField();
        jSearchScrollPane221 = new javax.swing.JScrollPane();
        jSearchTable221 = new com.afrisoftech.dbadmin.JTable();
        jButton5221 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel61 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jTextField51 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jTextField91 = new javax.swing.JTextField();
        searchButton1 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jTextField101 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel411 = new javax.swing.JPanel();
        jTextField911 = new javax.swing.JTextField();
        searchButton11 = new javax.swing.JButton();
        jPanel6411 = new javax.swing.JPanel();
        searchButton21 = new javax.swing.JButton();
        jTextField71 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        try  {
            java.lang.Class.forName("org.postgresql.Driver");
            System.out.println("Found driver");
        } catch(java.lang.ClassNotFoundException cnf){
            System.out.println("driver not found");
        }
        jButton11 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();

        jSearchDialog22.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog22.setModal(true);
        jSearchDialog22.setUndecorated(true);
        jSearchPanel22.setLayout(new java.awt.GridBagLayout());

        jSearchPanel22.setBorder(new javax.swing.border.EtchedBorder());
        jTextField1132.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1132CaretUpdate(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel22.add(jTextField1132, gridBagConstraints);

        jSearchTable22.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable22.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable2.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable22MouseClicked(evt);
            }
        });

        jSearchScrollPane22.setViewportView(jSearchTable22);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel22.add(jSearchScrollPane22, gridBagConstraints);

        jButton522.setMnemonic('l');
        jButton522.setText("Close");
        jButton522.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton522ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel22.add(jButton522, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog22.getContentPane().add(jSearchPanel22, gridBagConstraints);

        jSearchDialog221.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog221.setModal(true);
        jSearchDialog221.setUndecorated(true);
        jSearchPanel221.setLayout(new java.awt.GridBagLayout());

        jSearchPanel221.setBorder(new javax.swing.border.EtchedBorder());
        jTextField11321.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11321CaretUpdate(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel221.add(jTextField11321, gridBagConstraints);

        jSearchTable221.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable221.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable2.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable221.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable221MouseClicked(evt);
            }
        });

        jSearchScrollPane221.setViewportView(jSearchTable221);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel221.add(jSearchScrollPane221, gridBagConstraints);

        jButton5221.setMnemonic('l');
        jButton5221.setText("Close");
        jButton5221.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5221ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel221.add(jButton5221, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog221.getContentPane().add(jSearchPanel221, gridBagConstraints);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Merging Scheme Invoices");
        setPreferredSize(new java.awt.Dimension(650, 700));
        setVisible(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 4;
        getContentPane().add(jLabel5, gridBagConstraints);

        jPanel61.setLayout(new java.awt.GridBagLayout());

        jPanel31.setLayout(new java.awt.GridBagLayout());

        jPanel31.setBorder(new javax.swing.border.TitledBorder("Account Details"));
        jPanel21.setLayout(new java.awt.GridBagLayout());

        jTextField51.setEditable(false);
        jTextField51.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField51.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel21.add(jTextField51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(jPanel21, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder("To"));
        jLabel31.setText("Account No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel31, gridBagConstraints);

        jPanel41.setLayout(new java.awt.GridBagLayout());

        jPanel41.setBorder(new javax.swing.border.EtchedBorder());
        jTextField91.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(jTextField91, gridBagConstraints);

        searchButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
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
        gridBagConstraints.gridy = 2;
        jPanel41.add(searchButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel41, gridBagConstraints);

        jTextField13.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField13, gridBagConstraints);

        jLabel42.setText("Payer Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel42, gridBagConstraints);

        jLabel91.setText("Scheme Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel91, gridBagConstraints);

        jTextField101.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jTextField101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("From"));
        jLabel13.setText("Account No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jLabel13, gridBagConstraints);

        jPanel411.setLayout(new java.awt.GridBagLayout());

        jPanel411.setBorder(new javax.swing.border.EtchedBorder());
        jTextField911.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel411.add(jTextField911, gridBagConstraints);

        searchButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton11.setToolTipText("Search");
        searchButton11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton11.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton11.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton11ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel411.add(searchButton11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel411, gridBagConstraints);

        jPanel6411.setLayout(new java.awt.GridBagLayout());

        jPanel6411.setBorder(new javax.swing.border.EtchedBorder());
        jPanel6411.setMinimumSize(new java.awt.Dimension(82, 37));
        searchButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton21.setToolTipText("Search");
        searchButton21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton21.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton21.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6411.add(searchButton21, gridBagConstraints);

        jTextField71.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6411.add(jTextField71, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        jPanel2.add(jPanel6411, gridBagConstraints);

        jLabel12.setText("Scheme Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel71.setText("Scheme Payer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel2.add(jLabel71, gridBagConstraints);

        jTextField41.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jTextField41, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel61.add(jPanel31, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        jButton11.setMnemonic('O');
        jButton11.setText("Merge Accounts");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton11, gridBagConstraints);

        jButton31.setMnemonic('R');
        jButton31.setText("Refresh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton31, gridBagConstraints);

        jButton41.setMnemonic('C');
        jButton41.setText("Close");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton41, gridBagConstraints);

        jButton51.setMnemonic('h');
        jButton51.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jButton51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel61.add(jPanel11, gridBagConstraints);

        jTabbedPane1.addTab("Merge Accounts", jPanel61);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 667, 235);
    }//GEN-END:initComponents

    private void jSearchTable221MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable221MouseClicked
       int i = 0;
        
        //this.jTextField31.setText("");
        
      //  jTextField81.setText("");
        
        String code = null;
       // jTextField13.setText(jSearchTable22.getValueAt(jSearchTable22.getSelectedRow(), 2).toString());
        jTextField71.setText(jSearchTable221.getValueAt(jSearchTable221.getSelectedRow(), 1).toString());
        jTextField911.setText(jSearchTable221.getValueAt(jSearchTable221.getSelectedRow(), 0).toString());
        jTextField41.setText(jSearchTable221.getValueAt(jSearchTable221.getSelectedRow(), 2).toString());
        //jTextField7.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());
        
        jSearchDialog221.dispose();
        /*try {
            
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.ResultSet rset = stmt.executeQuery("select payee,account_no,dealer,debit,balance from ac_debtors where invoice_no ='"+jTextField911.getText()+"'");
            while (rset.next()){
                // jComboBox6.setSelectedItem(rset.getObject(1).toString());
               // jTextField71.setText(rset.getObject(1).toString());
                //jTextField81.setText(rset.getObject(2).toString());
                //jTextField41.setText(rset.getObject(3).toString());
                //jTextField21.setText(rset.getObject(4).toString());
                jTextField15.setText(rset.getObject(5).toString());
                
                
            }
            
            
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("SELECT not successful");
        }
      //  this.populateTable11(this.jTextField91.getText());
double floatDeduct = java.lang.Double.parseDouble(jTextField15.getText());
        double floatTotal = java.lang.Double.parseDouble(jTextField31.getText());
       
       double netPay = floatTotal + floatDeduct;
            this.jTextField14.setText(java.lang.String.valueOf(netPay));
         */
        
        jSearchDialog221.dispose();    // Add your handling code here:
    }//GEN-LAST:event_jSearchTable221MouseClicked

    private void jButton5221ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5221ActionPerformed
      jSearchDialog221.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton5221ActionPerformed

    private void jTextField11321CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11321CaretUpdate
       if(this.jTextField11321.getCaretPosition() < 2){
            System.out.print("Nothing");
        }else{
                jSearchTable221.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select account_no,scheme_name as name,payer_name from ac_schemes  where scheme_name ILIKE '"+jTextField11321.getText()+"%' order by scheme_name")); 

        
                jSearchScrollPane221.setViewportView(jSearchTable221);
                System.out.println("Cannot sort out");
          
        }   // Add your handling code here:
    }//GEN-LAST:event_jTextField11321CaretUpdate

    private void searchButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton11ActionPerformed
       searchButton221Clicked();         // Add your handling code here:
      // Add your handling code here:
    }//GEN-LAST:event_searchButton11ActionPerformed
  
    private void searchButton221Clicked() {
        
        System.out.println("Showing dialog");
        
        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField911.getLocationOnScreen();
        
        jSearchDialog221.setSize(400,200);
        
        jSearchDialog221.setLocation(point);
        
        jSearchDialog221.setVisible(true);
    }
    private void jSearchTable22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable22MouseClicked
        int i = 0;
        
     //   this.jTextField31.setText("");
        
      //  jTextField81.setText("");
        
        String code = null;
        jTextField13.setText(jSearchTable22.getValueAt(jSearchTable22.getSelectedRow(), 2).toString());
        jTextField101.setText(jSearchTable22.getValueAt(jSearchTable22.getSelectedRow(), 1).toString());
        jTextField91.setText(jSearchTable22.getValueAt(jSearchTable22.getSelectedRow(), 0).toString());
        //jTextField61.setText(jSearchTable22.getValueAt(jSearchTable22.getSelectedRow(), 3).toString());
        //jTextField7.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());
        
        jSearchDialog22.dispose();
       
        jSearchDialog22.dispose();         // Add your handling code here:
    }//GEN-LAST:event_jSearchTable22MouseClicked
  
    
    private void jButton522ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton522ActionPerformed
        jSearchDialog22.dispose();// Add your handling code here:
    }//GEN-LAST:event_jButton522ActionPerformed

    private void jTextField1132CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1132CaretUpdate
        if(this.jTextField1132.getCaretPosition() < 2){
            System.out.print("Nothing");
        }else{
             jSearchTable22.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select account_no,scheme_name as pat_no,payer_name as name from ac_schemes  where scheme_name ILIKE '"+jTextField1132.getText()+"%' order by scheme_name")); 
     
                jSearchScrollPane22.setViewportView(jSearchTable22);
                System.out.println("Cannot sort out");
           
        }        // Add your handling code here:
    }//GEN-LAST:event_jTextField1132CaretUpdate

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
        searchButton22Clicked();         // Add your handling code here:
    }//GEN-LAST:event_searchButton1ActionPerformed
    private void searchButton22Clicked() {
        
        System.out.println("Showing dialog");
        
        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField91.getLocationOnScreen();
        
        jSearchDialog22.setSize(400,200);
        
        jSearchDialog22.setLocation(point);
        
        jSearchDialog22.setVisible(true);
    }
    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
 setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        long dateNow = calendar.getTimeInMillis();
        
        java.sql.Date datenowSql1= new java.sql.Date(dateNow);
        
        System.out.println(datenowSql1.toString());
        
        java.sql.Timestamp datenowSql= new java.sql.Timestamp(dateNow);
        
        System.out.println(datenowSql.toString());
        
        String receiptNo = null;
        String actCode = null;
        String glCode = null;
        String bankAcc = null;
        String glCode1 = null;
        String bankAcc1 = null;
        String transNo = null;
        String payMode = null;
        String patCat = null;
        String patientAcc = null;
        String cardNo = null;
        String scheme = null;
        String cardName = null;
        String isurer = null;
        String expDate = null;
        String staffNo = null;
        String actNames = null;
        String user = null;
        String actNames1 = null;
        String actCode1 = null;
        String invoiceNo = null;
        
        
        
        
       // invoiceNo = this.jTextField9.getText();
        
        //  try {
        //      connectDB.setAutoCommit(false);
        
        //      java.sql.Statement stm1 = connectDB.createStatement();
        
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "Are you sure You would like to merge account '"+jTextField911.getText()+"' to '"+jTextField91.getText()+"'?", "Caution before Merging Accounts!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
        
        try {
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstmt3a = connectDB.prepareStatement("insert into pb_merged_accounts(acc_no,pat_no,pat_name,inv_no,inv_date,balance,credit_bal,receipt_no,orig_acc,date) select '"+jTextField91.getText()+"',admission_no,item,invoice_no,date,balance,credit_bal,receipt_no,account_no,current_date from ac_debtors  WHERE account_no = '"+jTextField911.getText()+"'");
            pstmt3a.executeUpdate();
             
            java.sql.PreparedStatement pstmt22 = connectDB.prepareStatement("UPDATE ac_debtors set payee = ?,dealer = ?,account_no = ? WHERE account_no = ?");
            pstmt22.setString(1, jTextField101.getText());
            pstmt22.setString(2, jTextField13.getText());
            pstmt22.setString(3, jTextField91.getText());
            pstmt22.setString(4, jTextField911.getText());
            pstmt22.executeUpdate();
            java.sql.PreparedStatement pstmt221 = connectDB.prepareStatement("UPDATE ac_schemes set closed = 'true'  WHERE account_no = ?");
            pstmt221.setString(1,jTextField911.getText());
            pstmt221.executeUpdate();
            //java.sql.PreparedStatement pstmt222 = connectDB.prepareStatement("UPDATE hp_patient_card set date = '"+jTextField61.getText()+"',invoice_no = '"+jTextField91.getText()+"' WHERE invoice_no = '"+jTextField911.getText()+"'");
            //pstmt222.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);
           // invoiceNo = jTextField91.getText();
            
           // FinalInvoiceByinvPdf policy = new FinalInvoiceByinvPdf();
           // policy.FinalInvoiceByinvPdf(connectDB,invoiceNo,invoiceNo);
            
            javax.swing.JOptionPane.showMessageDialog(this,"Data Inserted Successfully ","Confirmation Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }catch(java.sql.SQLException sq){
            sq.printStackTrace();
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        } 
        }// Add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
                    private void searchButton11aClicked() {
        
        System.out.println("Showing dialog");
        
   
    }
         
             private void searchButton2Clicked() {
        
        System.out.println("Showing dialog");
        
      
    }        private void populateTable1(java.lang.String patient_no) {
   
    }
    
      
    
    
            
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTable jSearchTable221;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel6411;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JButton jButton5221;
    private javax.swing.JTable jSearchTable22;
    private javax.swing.JDialog jSearchDialog221;
    private javax.swing.JPanel jPanel411;
    private javax.swing.JScrollPane jSearchScrollPane221;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDialog jSearchDialog22;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton searchButton1;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JPanel jSearchPanel221;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton searchButton11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jSearchScrollPane22;
    private javax.swing.JButton jButton522;
    private javax.swing.JPanel jSearchPanel22;
    private javax.swing.JButton jButton11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JTextField jTextField1132;
    private javax.swing.JButton jButton41;
    private javax.swing.JTextField jTextField11321;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton searchButton21;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JTextField jTextField911;
    // End of variables declaration//GEN-END:variables
    
}
