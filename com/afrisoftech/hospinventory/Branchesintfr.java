/*
 * Members.java
 *
 * Created on August 13, 2002, 3:36 PM
 */
package com.afrisoftech.hospinventory;

/**
 *
 * @author root
 */
public class Branchesintfr extends javax.swing.JInternalFrame {

    javax.swing.table.TableColumn column = null;
    java.sql.Connection connectDB = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    /**
     * Creates new form Members
     */
    public Branchesintfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        pConnDB = pconnDB;

        connectDB = connDb;

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField111 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new com.afrisoftech.dbadmin.JTable();
        jButton91 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        saveStoresBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        subStoresScrollPane = new javax.swing.JScrollPane();
        subStoresTable = new com.afrisoftech.dbadmin.JTable(){
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            /*   boolean[] canEdit = new boolean [] {
                false, true, priceEdit, false, false
            };
            */
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        jLabel2 = new javax.swing.JLabel();
        removeRowBtn = new javax.swing.JButton();
        deleteStoreBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jSearchDialog1.setModal(true);
        jSearchDialog1.setUndecorated(true);
        jSearchDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        jTextField111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel1.add(jTextField111, gridBagConstraints);

        jSearchTable1.setShowHorizontalLines(false);
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
        jSearchTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable1MouseClicked(evt);
            }
        });
        jSearchScrollPane1.setViewportView(jSearchTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel1.add(jSearchScrollPane1, gridBagConstraints);

        jButton91.setText("Close");
        jButton91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton91ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton91, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog1.getContentPane().add(jSearchPanel1, gridBagConstraints);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Department/Store");
        setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        setFrameIcon(null);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Set Department/Stores here"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        saveStoresBtn.setMnemonic('s');
        saveStoresBtn.setText("Save stores data");
        saveStoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveStoresBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(saveStoresBtn, gridBagConstraints);

        editBtn.setMnemonic('e');
        editBtn.setText("Edit sub_stores data");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(editBtn, gridBagConstraints);

        clearBtn.setMnemonic('l');
        clearBtn.setText("Clear form");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(clearBtn, gridBagConstraints);

        closeBtn.setMnemonic('c');
        closeBtn.setText("Close form");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(closeBtn, gridBagConstraints);

        subStoresTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Patient Store", "Markup %", "Stock GL Code", "Consumable Code", "Income GL/Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        subStoresTable.setShowHorizontalLines(false);
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = subStoresTable.getColumnModel().getColumn(i);
            if (i == 0) {

                column.setPreferredWidth(25); //sport column is bigger
            } else {
                if (i == 0) {
                    column.setPreferredWidth(300);

                } else{
                    column.setPreferredWidth(20);
                }
            }
        }

        subStoresTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subStoresTableMouseClicked(evt);
            }
        });
        subStoresScrollPane.setViewportView(subStoresTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(subStoresScrollPane, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel2, gridBagConstraints);

        removeRowBtn.setMnemonic('R');
        removeRowBtn.setText("Remove row");
        removeRowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(removeRowBtn, gridBagConstraints);

        deleteStoreBtn.setMnemonic('d');
        deleteStoreBtn.setText("Delete store");
        deleteStoreBtn.setEnabled(false);
        deleteStoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStoreBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(deleteStoreBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jPanel1, gridBagConstraints);

        setBounds(0, 0, 750, 450);
    }// </editor-fold>//GEN-END:initComponents

    private void subStoresTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subStoresTableMouseClicked
        if (subStoresTable.getSelectedColumn() == 4 || subStoresTable.getSelectedColumn() == 5 || subStoresTable.getSelectedColumn() == 6) {

            this.cmbox1MouseClicked();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_subStoresTableMouseClicked
    private void cmbox1MouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = subStoresScrollPane.getLocationOnScreen();
        jSearchDialog1.setSize(700, 200);
        jSearchDialog1.setLocation(point);
        jSearchDialog1.setVisible(true);
    }
    private void jButton91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton91ActionPerformed
        this.jSearchDialog1.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jButton91ActionPerformed

    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked
        int i = jSearchTable1.getSelectedRow();
        int j = subStoresTable.getSelectedColumn();

        if (j == 4) {
            subStoresTable.setValueAt(jSearchTable1.getValueAt(i, 0), subStoresTable.getSelectedRow(), j);
        } else {
            if (j == 5) {
                subStoresTable.setValueAt(jSearchTable1.getValueAt(i, 0), subStoresTable.getSelectedRow(), j);
            } else if (j == 6) {
                subStoresTable.setValueAt(jSearchTable1.getValueAt(i, 0), subStoresTable.getSelectedRow(), j);
            }
        }

        jSearchDialog1.dispose();         // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked

    private void jTextField111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField111CaretUpdate
        int i = jSearchTable1.getSelectedRow();
        int j = subStoresTable.getSelectedColumn();

        if (j == 4) {
            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select code,activity FROM pb_activity WHERE activity ILIKE '" + jTextField111.getText() + "%' AND (activity ILIKE '%stock%' OR activity ILIKE '%invento%') order by activity"));
            jSearchScrollPane1.setViewportView(jSearchTable1);
            System.out.println("Cannot sort out");
        } else {
            if (j == 5) {
                jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select code,activity FROM pb_activity WHERE activity ILIKE '" + jTextField111.getText() + "%' AND category_class ILIKE 'pled%' order by activity"));
                jSearchScrollPane1.setViewportView(jSearchTable1);
                System.out.println("Cannot sort out");
            } else if (j == 6) {
                jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select code,activity FROM pb_activity WHERE activity ILIKE '" + jTextField111.getText() + "%' AND category_class ILIKE 'plid%' order by activity"));
                jSearchScrollPane1.setViewportView(jSearchTable1);
                System.out.println("Cannot sort out");
            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_jTextField111CaretUpdate

    private void deleteStoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStoreBtnActionPerformed
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("DELETE from st_stores WHERE store_code = '" + subStoresTable.getModel().getValueAt(subStoresTable.getSelectedRow(), 0).toString() + "'");
            pstmt31.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            saveStoresBtn.setLabel("Save");
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
//        jButton2ActionPerformed(evt);

// TODO add your handling code here:
    }//GEN-LAST:event_deleteStoreBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        for (int k = 0; k < subStoresTable.getRowCount(); k++) {
            for (int r = 0; r < subStoresTable.getColumnCount(); r++) {
                subStoresTable.getModel().setValueAt(null, k, r);
            }
        }
        int i = 0;
        subStoresTable.setAutoCreateRowSorter(true);
        subStoresTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select store_code,store_name,patient_store,mark_up,glstock_code as stock_gl_code,cs_code as cost_of_sales_gl_code,sales_code as income_gl_code from st_stores ORDER BY store_name"));
        saveStoresBtn.setText("Update");
        // Add your handling code here:
    }//GEN-LAST:event_editBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        for (int k = 0; k < subStoresTable.getRowCount(); k++) {
            for (int r = 0; r < subStoresTable.getColumnCount(); r++) {
                subStoresTable.getModel().setValueAt(null, k, r);
            }
        }

        // Add your handling code here:
    }//GEN-LAST:event_clearBtnActionPerformed

    private void removeRowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowBtnActionPerformed
        int rows2Delete = subStoresTable.getSelectedRowCount();

        int[] selectedRows = subStoresTable.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

        } else {

            if (rows2Delete > 1) {

                for (int i = 0; i < selectedRows.length; i++) {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) subStoresTable.getModel();

                    defTableModel.removeRow(selectedRows[i]);

                }

            } else {

                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) subStoresTable.getModel();

                defTableModel.removeRow(subStoresTable.getSelectedRow());
            }
        }        // Add your handling code here:
    }//GEN-LAST:event_removeRowBtnActionPerformed

    private void saveStoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStoresBtnActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());

        try {
            for (int i = 0; i < subStoresTable.getRowCount(); i++) {
                if (subStoresTable.getModel().getValueAt(i, 0) != null) {
                    if (saveStoresBtn.getLabel().equalsIgnoreCase("Update")) {
                        java.sql.PreparedStatement pstmt311 = connectDB.prepareStatement("UPDATE st_stores SET store_name = '" + subStoresTable.getValueAt(i, 1).toString() + "',patient_store = '" + subStoresTable.getValueAt(i, 2).toString() + "',mark_up = '" + subStoresTable.getValueAt(i, 3).toString() + "',glstock_code= '" + subStoresTable.getValueAt(i, 4).toString() + "',cs_code = '" + subStoresTable.getValueAt(i, 5).toString() + "', sales_code = '" + subStoresTable.getValueAt(i, 6).toString() + "' WHERE store_code = '" + subStoresTable.getModel().getValueAt(i, 0).toString() + "'");
                        pstmt311.executeUpdate();
                    } else {
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into st_stores values( ?, initcap(?),?,?,?,?,?,?,?,?,?,?)");
                        pstmt.setObject(2, subStoresTable.getValueAt(i, 0).toString());
                        pstmt.setObject(1, subStoresTable.getValueAt(i, 1).toString());
                        pstmt.setDate(3, datenowSql1);
                        pstmt.setBoolean(4, Boolean.parseBoolean(subStoresTable.getValueAt(i, 2).toString()));
                        pstmt.setDouble(5, java.lang.Double.valueOf(subStoresTable.getValueAt(i, 3).toString()));
                        pstmt.setObject(6, subStoresTable.getValueAt(i, 4).toString());
                        pstmt.setObject(7, subStoresTable.getValueAt(i, 5).toString());
                        pstmt.setObject(8, null);
                        pstmt.setBoolean(9, false);
                        pstmt.setObject(10, null);
                        if(subStoresTable.getValueAt(i, 1).toString().contains("pharm") || subStoresTable.getValueAt(i, 1).toString().contains("Pharm")){
                        pstmt.setObject(11, "Pharm");
                        } else {
                         pstmt.setObject(11, null);   
                        }
                        pstmt.setObject(12, subStoresTable.getValueAt(i, 6).toString());
                        pstmt.executeUpdate();

                    }
                }
            }
            saveStoresBtn.setLabel("Save stores data");
            jLabel2.setForeground(java.awt.Color.blue);
            jLabel2.setText("Insert successful");
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully.");

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR: " + sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(sq.getMessage());
            jLabel2.setForeground(java.awt.Color.red);
            jLabel2.setText("Sorry. Data not saved");
        }
        // Add your handling code here:
    }//GEN-LAST:event_saveStoresBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteStoreBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton jButton91;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDialog jSearchDialog1;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JButton removeRowBtn;
    private javax.swing.JButton saveStoresBtn;
    private javax.swing.JScrollPane subStoresScrollPane;
    private javax.swing.JTable subStoresTable;
    // End of variables declaration//GEN-END:variables

}