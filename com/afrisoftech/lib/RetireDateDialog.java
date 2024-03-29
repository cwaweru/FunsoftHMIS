/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class RetireDateDialog extends javax.swing.JDialog {

    int reportName;
    String typee = "", pFNo = "";

    java.sql.Connection connectDB = null;

    java.lang.String secrets = null;

    /**
     * Creates new form DatePanel
     */
    public RetireDateDialog(java.awt.Frame parent, boolean modal, java.sql.Connection connectDb, java.lang.String type, java.lang.String pfNo) {

        super(parent, modal);

        connectDB = connectDb;
        typee = type;
        pFNo = pfNo;

        initComponents();

        if (typee.equalsIgnoreCase("Suspend")) {
            jButton1.setText("Suspend Staff Member");
            jLabel5.setText("Date Suspended");
        }
        jLabel1.setText(pfNo);

        //       return dateStartEnd; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        dotDatePicker = new com.afrisoftech.lib.DatePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentsTA = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        designitionLbl = new javax.swing.JLabel();
        reasonTxt = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Retire/Suspend");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(dotDatePicker, gridBagConstraints);

        jLabel5.setText("Retire Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comments"));

        commentsTA.setColumns(20);
        commentsTA.setRows(5);
        jScrollPane1.setViewportView(commentsTA);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("pfNo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        designitionLbl.setText("Reason");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(designitionLbl, gridBagConstraints);

        if(typee.equalsIgnoreCase("Suspend")){
            reasonTxt.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' union select reason from payroll_termination_reasons where suspend = true order by 1"));

        }else{
            reasonTxt.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' union select reason from payroll_termination_reasons where retire =true order by 1"));
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(reasonTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Select date here");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setMnemonic('n');
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setMnemonic('C');
        jButton1.setText("Retire Staff Member");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        setSize(new java.awt.Dimension(381, 272));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        com.afrisoftech.hospital.HospitalMain.passwordChange = false;
        this.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!reasonTxt.getSelectedItem().toString().equalsIgnoreCase("-")) {
            if (!pFNo.trim().isEmpty()) {

                try {
                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO public.payroll_terminations( staff_no, staff_name, id_no, date_of_employment, gender, desgination, \n"
                            + "               department, job_group, reason, comments, termination_date)   VALUES (?, ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?);");
                    pstmt2.setString(1, pFNo);

                    java.sql.Statement stmt = connectDB.createStatement();
                    java.sql.ResultSet rset = stmt.executeQuery("SELECT  first_name || ' ' || middle_name || ' ' || last_name, id_no, trim(gender), date_employed, official_desgnation,"
                            + "department,  employee_group, employee_grade  FROM master_file where employee_no = '" + pFNo + "'");

                    while (rset.next()) {

                        pstmt2.setString(2, rset.getString(1));
                        pstmt2.setObject(3, rset.getString(2));
                        pstmt2.setDate(4, rset.getDate(4));
                        pstmt2.setString(5, rset.getString(3));
                        pstmt2.setObject(6, rset.getString(5));
                        pstmt2.setString(7, rset.getString(6));
                        pstmt2.setObject(8, rset.getString(7));
                    }

                    pstmt2.setObject(9, reasonTxt.getSelectedItem().toString());
                    pstmt2.setObject(10, commentsTA.getText());
                    pstmt2.setDate(11, com.afrisoftech.lib.SQLDateFormat.getSQLDate(dotDatePicker.getDate()));

                    pstmt2.executeUpdate();
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }

                if (typee.equalsIgnoreCase("Suspend")) {
                    try {

                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("update master_file set suspend = ?, suspension_date = ? WHERE employee_no = ?");
                        pstmt.setBoolean(1, true);
                        pstmt.setDate(2, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));
                        pstmt.setString(3, pFNo);
                        pstmt.executeUpdate();
                        javax.swing.JOptionPane.showMessageDialog(this, pFNo + " Suspended successfully from the masterfile database.");
                    } catch (java.sql.SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {

                    try {
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("update master_file set retired = ?, retire_date = ? WHERE employee_no = ?");
                        pstmt.setBoolean(1, true);
                        pstmt.setDate(2, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));
                        pstmt.setString(3, pFNo);
                        pstmt.executeUpdate();
                        javax.swing.JOptionPane.showMessageDialog(this, pFNo + " retired successfully from the masterfile database.");
                    } catch (java.sql.SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Please Select Employee No. to Continue.");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Select Reason for Suspension/Retirement");
        }

        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //  new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea commentsTA;
    private javax.swing.JLabel designitionLbl;
    private com.afrisoftech.lib.DatePicker dotDatePicker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> reasonTxt;
    // End of variables declaration//GEN-END:variables

}
