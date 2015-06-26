/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package com.afrisoftech.nursing;

//import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author  saqlever
 */
public class NursingReporterIntfr extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    private String ward=null;
    private String visitId=null;
    private String gender=null;
    int years=0, months=0,days=0;
    private String patient_name=null;
    
    /** Creates new form ReportIntfr */
    public NursingReporterIntfr(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
      //  loadReport();
        ward = wardCmb.getSelectedItem().toString();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        export2SpreadSheetBtn = new javax.swing.JButton();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        saveReportBtn = new javax.swing.JButton();
        wardCmb = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        patientDetailsTextArea = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        soreTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        allbedsoreTable = new com.afrisoftech.dbadmin.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientTable = new com.afrisoftech.dbadmin.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        soreinterventionTable = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("BED SORE REPORT.....");
        setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        export2SpreadSheetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Appointments.png"))); // NOI18N
        export2SpreadSheetBtn.setMnemonic('x');
        export2SpreadSheetBtn.setText("Export report ...");
        export2SpreadSheetBtn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(export2SpreadSheetBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        beginDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Printer 4.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Print ...");
        printBtn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(printBtn, gridBagConstraints);

        saveReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Globe 4.png"))); // NOI18N
        saveReportBtn.setMnemonic('s');
        saveReportBtn.setText("Save Report ...");
        saveReportBtn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(saveReportBtn, gridBagConstraints);

        wardCmb.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '0All ') union (select distinct ward_name  from hp_wards order by 1)   UNION (SELECT distinct Clinics FROM pb_clinics order by 1 ) order by 1 asc"));
        wardCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wardCmbItemStateChanged(evt);
            }
        });
        wardCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wardCmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(wardCmb, gridBagConstraints);

        patientDetailsTextArea.setColumns(20);
        patientDetailsTextArea.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
        patientDetailsTextArea.setRows(5);
        jScrollPane3.setViewportView(patientDetailsTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        headerPanel.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(headerPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setForeground(new java.awt.Color(102, 204, 0));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        soreTabbedPane.setForeground(new java.awt.Color(0, 51, 153));
        soreTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        allbedsoreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        allbedsoreTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allbedsoreTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(allbedsoreTable);

        soreTabbedPane.addTab("ALL BED SORE PATIENTS", jScrollPane1);

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(patientTable);

        soreTabbedPane.addTab("PATIENT ANALYSIS", jScrollPane2);

        soreinterventionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(soreinterventionTable);

        soreTabbedPane.addTab("SORE INTERVENTION", jScrollPane4);

        jTabbedPane1.addTab("BED SORE", soreTabbedPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        setBounds(0, 0, 643, 441);
    }// </editor-fold>//GEN-END:initComponents

    private void wardCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wardCmbItemStateChanged
        // TODO add your handling code here:
       
      
    }//GEN-LAST:event_wardCmbItemStateChanged

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
      if(this.jTabbedPane1.getSelectedIndex()==1){
          System.out.println("SELECT distinct patient_no, transaction_date, server_date, visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where skin_integrity ilike '%PRESSURE SORE:%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and  transaction_date::date between"
                  + " '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ;");
          
          this.allbedsoreTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                  + "SELECT distinct patient_no, transaction_date, server_date, visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where skin_integrity ilike '%PRESSURE SORE:%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and  transaction_date::date between"
                  + " '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ;"));

          int rows=this.allbedsoreTable.getRowCount();
          this.patientDetailsTextArea.setText("THE NUMBER OF PATIENTS WITH BED SORE \nBETWEEN "
                  + " DATE "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"and "
                  + "  "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+""
                  + "\nIS :"+rows);
      }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void allbedsoreTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allbedsoreTableMouseClicked
        
        System.out.println("SELECT patient_no, transaction_date,   visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where patient_no='"+this.allbedsoreTable.getValueAt(this.allbedsoreTable.getSelectedRow(), 0)+"' and  skin_integrity ilike '%PRESSURE SORE%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and "
                  + " transaction_date::date between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ORDER BY 2 ASC;");
        
        this.patientTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                  + "SELECT patient_no, transaction_date,   visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where patient_no='"+this.allbedsoreTable.getValueAt(this.allbedsoreTable.getSelectedRow(), 0)+"' and  skin_integrity ilike '%PRESSURE SORE%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and "
                  + " transaction_date::date between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ORDER BY 2 ASC;"));
          
          
          this.soreinterventionTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                  + "SELECT patient_id, problem, intervention, appearance, current_users, " +
"       transaction_date, server_date, visit_id, site, state, depth, " +
"       bedsore_source " +
"  FROM nursing.pressure_intervention where patient_id='"+this.allbedsoreTable.getValueAt(this.allbedsoreTable.getSelectedRow(), 0)+"' and "
                  + "transaction_date::date between '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date order by 6 asc;"
                  + ""));
    }//GEN-LAST:event_allbedsoreTableMouseClicked

    private void wardCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wardCmbActionPerformed
     
          System.out.println("SELECT distinct patient_no, transaction_date, server_date, visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where skin_integrity ilike '%PRESSURE SORE%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and  transaction_date::date between"
                  + " '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ;");
          if(wardCmb.getSelectedItem().toString().equalsIgnoreCase("0All ")){
                this.allbedsoreTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                  + "SELECT distinct patient_no, transaction_date, server_date, visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where skin_integrity ilike '%PRESSURE SORE%' " +
"     and    transaction_date::date between"
                  + " '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ;"));

          int rows=this.allbedsoreTable.getRowCount();
          this.patientDetailsTextArea.setText("THE NUMBER OF PATIENTS WITH BED SORE \nBETWEEN "
                  + " DATE "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"and "
                  + "  "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+""
                  + "\nIS :"+rows);  
          }
          else{
          this.allbedsoreTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                  + "SELECT distinct patient_no, transaction_date, server_date, visit_id, action_user, \n" +
"       skin_color, skin_tugor, skin_integrity, comment,ward " +
"         FROM nursing.integumentary where skin_integrity ilike '%PRESSURE SORE%' " +
"         and ward ilike '%"+wardCmb.getSelectedItem()+"%' and  transaction_date::date between"
                  + " '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'::date"
                  + " and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'::date ;"));

          int rows=this.allbedsoreTable.getRowCount();
          this.patientDetailsTextArea.setText("THE NUMBER OF PATIENTS WITH BED SORE \nBETWEEN "
                  + " DATE "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"and "
                  + "  "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+""
                  + "\nIS :"+rows);
          }
    }//GEN-LAST:event_wardCmbActionPerformed

       private String getWard(){
       String wardName=null;
       int tokencount;
       String myAppOutFileUrl = System.getProperty("user.dir") + System.getProperty("file.separator") + "hosprop.properties";
                 try{
                        FileReader fr=new FileReader(myAppOutFileUrl);
                        fr.read();
                        BufferedReader br=new BufferedReader(fr);
                        String s=br.readLine();
                        System.out.println("running this class"+s);
                        int linecount=0;
                        String line;
                        String words[]=new String[1000000];
                         while ((s=br.readLine())!=null)
                                 {

                                 linecount++;
                                 int indexfound=s.indexOf("ward");
                                 if (indexfound>-1)
                                                                                          {
                      System.out.println("\n");
                      System.out.println("Word was found at position::" +indexfound+ "::on line"+linecount);
                     System.out.println("\n");
                      line=s;
                     System.out.println(line);
                     int idx=0;
                     StringTokenizer st= new StringTokenizer(line);
                     tokencount= st.countTokens();
                     System.out.println(line);
                  
                     int w1=line.indexOf("=");
                     wardName=line.substring(w1+1,line.length());
                     System.out.println("check at this word eh "+ wardName);


                           }
                   }
                      fr.close();

             }catch(Exception ed){

                   System.out.println("running this class EXCEPTIN"+ed);

                   }
             return wardName;    
   }
    
        private String getAge(int yr,int month,int day){  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");  
        int dateParts[] = {Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH};  
        Calendar today = Calendar.getInstance();  
        Calendar birthday = Calendar.getInstance();  
        birthday.set(yr,month,day); //months are 0-based  
        if(today.before(birthday))  
        {  
          System.out.println("birthday invalid - date after today, exiting");  
          System.exit(0);  
        }  
        int diff[] = new int[3];  
        for(int i = 2; i >= 0; i--)  
        {  
          while(!sdf.format(birthday.getTime()).split(" ")[i].equals(sdf.format(today.getTime()).split(" ")[i]))  
          {  
            birthday.add(dateParts[i],1);  
            diff[i]++;  
          }  
        }  
        String difference = ""+(diff[0]+" yrs : "+diff[1])+" mths : "+diff[2]+" days"; 
        years=diff[0]; months=diff[1];days=diff[2];
        System.out.println(years+" "+months+" "+days);
        return difference;
        }
      private String populateBioData(String patient_num){
      String age = null,text = null;
         try {
            connectDB.setAutoCommit(false);
             //getting age
             java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123=stm123.executeQuery("select year_of_birth from hp_inpatient_register where patient_no='"+patient_num+"'");
            while (rse123.next()){
                ///getting the user
                age = rse123.getObject(1).toString();
         }
            //getting details
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12=stm12.executeQuery(
                    "SELECT patient_no,patient_name,gender,marital_status,residence,nok,tel,bed_no,doctor,date_admitted,visit_id FROM hp_admission WHERE ward='"+wardCmb.getSelectedItem().toString()+"' AND patient_no='"+patient_num+"' AND discharge = false ");
            while (rse12.next()){
                ///getting the user

               int year=Integer.parseInt(age.substring(0, 4)); 
               int month=Integer.parseInt(age.substring(5, 7));
               int day=Integer.parseInt(age.substring(8, 10));
               

                text=rse12.getObject(2).toString()+"    Age: ".
                concat(getAge(year,month,day)+"\n Gender: ".
                concat(rse12.getObject(3).toString()+"     Marital Status: ".
                concat(rse12.getObject(4).toString()+"\n Residence: ".
                concat(rse12.getObject(5).toString()+"     Next of Kin: ".
                concat(rse12.getObject(6).toString()+"\n Tel no: ".  
                concat(rse12.getObject(7).toString()+"     Bed No: ".
                concat(rse12.getObject(8).toString()+"\n Doctor: ".
                concat(rse12.getObject(9).toString()+"    Date Admitted: ".
                concat(rse12.getObject(10).toString())))))))));
                
                visitId=rse12.getObject(11).toString();
                gender=rse12.getObject(3).toString();
                
                patient_name=rse12.getObject(2).toString();
                
                String title=wardCmb.getSelectedItem().toString()+"                           ("+rse12.getObject(2).toString().concat("    ").
              concat(rse12.getObject(2).toString()).concat(")");
      this.setTitle(title);
         }
    
            
            connectDB.commit();
            connectDB.setAutoCommit(true);          
         }
         
         catch(final Exception es){
         System.out.println(es);
         
         }

     return text;    
    }    
    private void loadReport() {
        
        biz.systempartners.reports.TrialBalanceRpt trialBalanceReport = new biz.systempartners.reports.TrialBalanceRpt(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate());
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allbedsoreTable;
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea patientDetailsTextArea;
    private javax.swing.JTable patientTable;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JTabbedPane soreTabbedPane;
    private javax.swing.JTable soreinterventionTable;
    private javax.swing.JComboBox wardCmb;
    // End of variables declaration//GEN-END:variables
    
}
