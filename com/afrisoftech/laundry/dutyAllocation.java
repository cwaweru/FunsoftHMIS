/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.laundry;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author charlie
 */
public class dutyAllocation extends javax.swing.JInternalFrame {
    private Object[][] batch;
    Connection conn;
    List<String> staffID = new ArrayList<String>();
    List<String> dates = new ArrayList<String>();
    String hasSections="no";
    int r;
    String doneLoading="no";
    /**
     * Creates new form MachineSetup
     * @param passedConn
     */
    public dutyAllocation(Connection passedConn) {
        try {
            setVisible(true);
            initComponents();
            conn=passedConn;
            
            dtPdateFrom.setDate(new java.util.Date());
            cbxSection.removeAllItems();
            String sqlsections="SELECT DISTINCT section FROM master_file WHERE department = 'Laundry Unit' ORDER BY section ASC";
            Statement stmtsections = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rssections = stmtsections.executeQuery( sqlsections );
            rssections.last();
            System.out.println(rssections.getRow());
            if(rssections.getRow()>0){
                hasSections="yes";
                rssections.beforeFirst();
                while(rssections.next()){
                    cbxSection.addItem(rssections.getString("section"));                    
                    
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(dutyAllocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateTable();
        doneLoading="yes";
            
    }
    
    public void populateTable(){
        staffID.clear();
        dates.clear();
        Calendar cal = Calendar.getInstance();
        java.util.Date date=dtPdateFrom.getDate();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        String headers[]=new String[15];
        
        headers[0]="name";
        headers[1]=sdf.format(cal.getTime());
        dates.add(sdf2.format(cal.getTime()));
         for(int i=2; i<15; i++){
        // get start of this week in milliseconds
            cal.add(Calendar.DATE, 1);
            headers[i]=sdf.format(cal.getTime());
            dates.add(sdf2.format(cal.getTime()));
         }
       try {
            
            String sql="select CONCAT(first_name,' ',middle_name) AS Name, employee_no from master_file "
                    + "where department = 'Laundry Unit' order by first_name asc";
            if(hasSections.equals("yes")){
                sql="select CONCAT(first_name,' ',middle_name) AS Name, employee_no from master_file "
                        + "where department = 'Laundry Unit' AND section = '"+cbxSection.getSelectedItem()+"' order by first_name asc";
            }
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery( sql );
            rs.last();
            r=rs.getRow();
            batch = new Object[r][15];
      //      brandTagsTable.clear();
            rs.beforeFirst();
            int counter=0;
            while ( rs.next() ) {
                batch[counter][0]=rs.getString("Name");
                staffID.add(rs.getString("employee_no"));
                Calendar cal2 = Calendar.getInstance();
                java.util.Date date2=dtPdateFrom.getDate();
                cal2.setTime(date2);
                cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
                int i=1;
                 do{
                // get start of this week in milliseconds
                    String sql2="select duty from hr.duty_assignment "
                            + "WHERE staffid like '"+rs.getString("employee_no")+"' AND date like '"+sdf2.format(cal2.getTime())+"' ";
                    Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs2 = stmt2.executeQuery( sql2 );
                    rs2.last();
                    if(rs2.getRow()>0){
                        rs2.beforeFirst();
                        rs2.next();
                        batch[counter][i]=rs2.getString("duty");
                        System.out.println("Duty has been Selected!!!");
                    }
                    else{
                        Statement stmt3 = conn.createStatement();
                        stmt3.execute( "insert into hr.duty_assignment (staffid, date, duty) "
                                + "values('"+rs.getString("employee_no")+"','"+sdf2.format(cal2.getTime())+"',' ')" );
                        
                        System.out.println("Insert has been executed!!!");
                    }
                    cal2.add(Calendar.DATE, 1);
                    i++;
                 }while(i<15);
                
                
                counter++;
            }
            RefreshClientsTable(headers);
            JComboBox cbx=new JComboBox();
            String sql2="select dutycode from hr.duty";
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery( sql2 );
            rs2.last();
            if(rs2.getRow()>0){
                rs2.beforeFirst();
                while(rs2.next()){
                    cbx.addItem(rs2.getString("dutycode"));
                    
                }
                
                
            }
            for(int p=1; p<15; p++){
                jTable1.getColumnModel().getColumn(p).setCellEditor(new DefaultCellEditor(cbx));
                
            }

            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: "+ex.toString());

           }
        
        
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dtPdateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cbxSection = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Duty Allocation");
        setPreferredSize(new java.awt.Dimension(968, 502));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(652, 402));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee", "Sun /n 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02", "Sun 02"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Date"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Date From");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jLabel1, gridBagConstraints);

        dtPdateFrom.setDateFormatString("dd-MM-yyyy");
        dtPdateFrom.setPreferredSize(new java.awt.Dimension(191, 20));
        dtPdateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtPdateFromPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(dtPdateFrom, gridBagConstraints);

        jLabel2.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jLabel2, gridBagConstraints);

        cbxSection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSection.setPreferredSize(new java.awt.Dimension(180, 20));
        cbxSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(cbxSection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("Save Roster");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(jButton2, gridBagConstraints);

        cancelBtn.setText("Cancel");
        cancelBtn.setPreferredSize(new java.awt.Dimension(91, 23));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(cancelBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int r2=0;
        while(r2<r){
            try {
                Statement stmt3 = conn.createStatement();
                for(int tempCount=0; tempCount<14; tempCount++){
                    String value=(String) jTable1.getValueAt(r2, tempCount+1);
                    if(value==null){value=" ";}
                    stmt3.execute( "update hr.duty_assignment set duty='"+value+"' "
                            + "where staffid='"+staffID.get(r2)+"' and date='"+dates.get(tempCount)+"'" );
                    
                    System.out.println("Save Button - Update Executed!!!");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(dutyAllocation.class.getName()).log(Level.SEVERE, null, ex);
            }
            r2++;
        }
        JOptionPane.showMessageDialog(null,"The Duty Roster has been Created and Saved!","Duty Roster!",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSectionActionPerformed
        populateTable();
            
    }//GEN-LAST:event_cbxSectionActionPerformed

    private void dtPdateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtPdateFromPropertyChange
        if(doneLoading.equals("yes")){
            populateTable();
        }
    }//GEN-LAST:event_dtPdateFromPropertyChange


    private void RefreshClientsTable(String[] headers){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(batch,
        
                new String [] {
                    headers[0], headers[1], headers[2], headers[3], headers[4], headers[5], 
                    headers[6], headers[7], headers[8], headers[9], headers[10], headers[11], 
                    headers[12], headers[13], headers[14]   }){});
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        col0.setPreferredWidth(200);  
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox cbxSection;
    private com.toedter.calendar.JDateChooser dtPdateFrom;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
