/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */
package biz.systempartners.reports;

//import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author saqlever
 */
public class inPatientReporter extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    private String ward = null;
    private String visitId = null;
    private String gender = null;
    int years = 0, months = 0, days = 0;
    private String patient_name = null;
    String specificWard = null;
    String allWard = null;
    private String occupancyquery = null;

    /**
     * Creates new form ReportIntfr
     *
     * @param connDb
     */
    public inPatientReporter(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
        //  loadReport();
        ward = wardCmb.getSelectedItem().toString();
        com.afrisoftech.hospital.HospitalMain.mainSplitPane.setDividerLocation(0);

        this.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getSize());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        wardCmb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        reportBodyPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientDetailsTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        occupancytable = new com.afrisoftech.dbadmin.JTable();
        occupyno = new javax.swing.JLabel();
        labelDisplayCatgory = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dischargeTypeCmb = new javax.swing.JComboBox();
        moreReports = new javax.swing.JComboBox();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientStatusTable = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funsoft Inpatient Reporter");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        beginDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        wardCmb.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '0select-') union (select '1All Wards-') union (select distinct ward_name from hp_wards ORDER BY 1 asc) union (SELECT distinct clinics FROM pb_clinics ORDER BY 1 asc) ORDER BY 1 asc"));
        wardCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wardCmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(wardCmb, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Select Ward \\ Clinic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        patientDetailsTextArea.setEditable(false);
        patientDetailsTextArea.setBackground(new java.awt.Color(255, 255, 51));
        patientDetailsTextArea.setColumns(20);
        patientDetailsTextArea.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        patientDetailsTextArea.setForeground(new java.awt.Color(153, 0, 0));
        patientDetailsTextArea.setRows(5);
        patientDetailsTextArea.setBorder(null);
        jScrollPane1.setViewportView(patientDetailsTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        reportBodyPanel.add(jPanel1, gridBagConstraints);

        jPanel39.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel39.add(jSeparator3, gridBagConstraints);

        occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no FROM hp_admission where discharge=false and ward='"+ward+"' "));
        occupancytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                occupancytableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(occupancytable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel39.add(jScrollPane11, gridBagConstraints);

        occupyno.setForeground(new java.awt.Color(0, 51, 153));
        occupyno.setText("SHOW NO OF TRAN,REC/OCC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel39.add(occupyno, gridBagConstraints);

        labelDisplayCatgory.setForeground(new java.awt.Color(0, 0, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel39.add(labelDisplayCatgory, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        dischargeTypeCmb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dischargeTypeCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Discharge", "Clinical Discharge", "FInancial Discharge", "Nurse Discharge" }));
        dischargeTypeCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dischargeTypeCmbItemStateChanged(evt);
            }
        });
        dischargeTypeCmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dischargeTypeCmbMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(dischargeTypeCmb, gridBagConstraints);

        moreReports.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        moreReports.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Foreigners", "Diagnosis Report", "Patients for Theatre", "Patients Seen" }));
        moreReports.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                moreReportsItemStateChanged(evt);
            }
        });
        moreReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreReportsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(moreReports, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        jPanel39.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(jPanel39, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
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

        reloadReportBtn.setMnemonic('r');
        reloadReportBtn.setText("Reload report");
        reloadReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(reloadReportBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Report Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        patientStatusTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct patient_status from hp_patient_reporter "));
        patientStatusTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientStatusTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(patientStatusTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        setBounds(0, 0, 771, 441);
    }// </editor-fold>//GEN-END:initComponents

    private String getWard() {
        String wardName = null;
        int tokencount;
        String myAppOutFileUrl = System.getProperty("user.dir") + System.getProperty("file.separator") + "hosprop.properties";
        try {
            FileReader fr = new FileReader(myAppOutFileUrl);
            fr.read();
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            System.out.println("running this class" + s);
            int linecount = 0;
            String line;
            String words[] = new String[1000000];
            while ((s = br.readLine()) != null) {

                linecount++;
                int indexfound = s.indexOf("ward");
                if (indexfound > -1) {
                    System.out.println("\n");
                    System.out.println("Word was found at position::" + indexfound + "::on line" + linecount);
                    System.out.println("\n");
                    line = s;
                    System.out.println(line);
                    int idx = 0;
                    StringTokenizer st = new StringTokenizer(line);
                    tokencount = st.countTokens();
                    System.out.println(line);

                    int w1 = line.indexOf("=");
                    wardName = line.substring(w1 + 1, line.length());
                    System.out.println("check at this word eh " + wardName);

                }
            }
            fr.close();

        } catch (Exception ed) {

            System.out.println("running this class EXCEPTIN" + ed);

        }
        return wardName;
    }

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed

        loadReport();

        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private String getAge(int yr, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
        int dateParts[] = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH};
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.set(yr, month, day); //months are 0-based  
        if (today.before(birthday)) {
            System.out.println("birthday invalid - date after today, exiting");
            System.exit(0);
        }
        int diff[] = new int[3];
        for (int i = 2; i >= 0; i--) {
            while (!sdf.format(birthday.getTime()).split(" ")[i].equals(sdf.format(today.getTime()).split(" ")[i])) {
                birthday.add(dateParts[i], 1);
                diff[i]++;
            }
        }
        String difference = "" + (diff[0] + " yrs : " + diff[1]) + " mths : " + diff[2] + " days";
        years = diff[0];
        months = diff[1];
        days = diff[2];
        System.out.println(years + " " + months + " " + days);
        return difference;
    }

    private String populateBioData(String patient_num) {
        String age = null, text = null;
        try {
            connectDB.setAutoCommit(false);
            //getting age
            java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123 = stm123.executeQuery("select year_of_birth from hp_inpatient_register where patient_no='" + patient_num + "'");
            while (rse123.next()) {
                ///getting the user
                age = rse123.getObject(1).toString();
            }
            //getting details
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery(
                    "SELECT patient_no,patient_name,gender,marital_status,residence,nok,tel,bed_no,doctor,date_admitted,visit_id FROM hp_admission WHERE AND patient_no='" + patient_num + "' AND discharge = false ");
            while (rse12.next()) {
                ///getting the user

                int year = Integer.parseInt(age.substring(0, 4));
                int month = Integer.parseInt(age.substring(5, 7));
                int day = Integer.parseInt(age.substring(8, 10));

                text = rse12.getObject(2).toString() + "    Age: ".
                        concat(getAge(year, month, day) + "\n Gender: ".
                                concat(rse12.getObject(3).toString() + "     Marital Status: ".
                                        concat(rse12.getObject(4).toString() + "\n Residence: ".
                                                concat(rse12.getObject(5).toString() + "     Next of Kin: ".
                                                        concat(rse12.getObject(6).toString() + "\n Tel no: ".
                                                                concat(rse12.getObject(7).toString() + "     Bed No: ".
                                                                        concat(rse12.getObject(8).toString() + "\n Doctor: ".
                                                                                concat(rse12.getObject(9).toString() + "    Date Admitted: ".
                                                                                        concat(rse12.getObject(10).toString())))))))));

                visitId = rse12.getObject(11).toString();
                gender = rse12.getObject(3).toString();

                patient_name = rse12.getObject(2).toString();

                String title = wardCmb.getSelectedItem().toString() + "                           (" + rse12.getObject(2).toString().concat("    ").
                        concat(rse12.getObject(2).toString()).concat(")");
                this.setTitle(title);
            }

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            System.out.println(es);

        }

        return text;
    }
    private void occupancytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_occupancytableMouseClicked

        String diagnosis = "SELECT patient_no, patient_name, main_service, disease,  diagnosis,  comments,doctor_surgeon,user_name   "
                + "  FROM hp_patient_diagnosis where "
                + "patient_no='" + occupancytable.getValueAt(occupancytable.getSelectedRow(), 0).toString().trim() + "' and pat_category='IP'";

        try {
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.ResultSet rs = st3.executeQuery(diagnosis);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            String label = "";
            System.out.println("numberOfColumns is " + numberOfColumns);
            if (numberOfColumns > 0) {
                label = "";

                while (rs.next()) {
                    label = label.concat("Main Service :-" + rs.getString(3) + "\n");
                    label = label.concat("Disease :-" + rs.getString(4) + "\n");
                    label = label.concat("Diagnosis :-" + rs.getString(5) + "\n");
                    label = label.concat("Comments :-" + rs.getString(6));
                    label = label.concat("Doctor :- " + rs.getString(7) + "\n");
                    label = label.concat("Entered by :-" + rs.getString(8) + "\n");

                }
            } else if (numberOfColumns <= 0) {
                label = "NOTHING TO DISPLAY";
            }

            System.out.println("label is " + label);

            System.out.println("label occupancy instruction sis " + diagnosis);

            patientDetailsTextArea.setText(populateBioData(occupancytable.getModel().getValueAt(occupancytable.getSelectedRow(), 0).toString().trim()));

            // nameNoTxt.setText(occupancytable.getModel().getValueAt(occupancytable.getSelectedRow(), 0).toString().trim());
        } catch (SQLException thea) {
            System.out.println("eror ya thatre label  area ni " + thea);
        }
    }//GEN-LAST:event_occupancytableMouseClicked

    private void dischargeTypeCmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dischargeTypeCmbMouseClicked
        if (wardCmb.getSelectedIndex() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "You  Must Select The Ward Type First");
        } else {
            dischargeTypeCmb.setSelectedIndex(0);
            moreReports.setSelectedIndex(0);

        }
    }//GEN-LAST:event_dischargeTypeCmbMouseClicked

    private void dischargeTypeCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dischargeTypeCmbItemStateChanged
        if (wardCmb.getSelectedIndex() == 1) {
            if (dischargeTypeCmb.getSelectedIndex() == 1) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT patient_no, patient_name, admission_reason, bed_no, wing, invoice_no,discharged_by,discharge_date,date_admitted  "
                        + " FROM hp_admission  where discharge=true and check_out=false and   (discharge_date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            } else if (dischargeTypeCmb.getSelectedIndex() == 2) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  patient_no,visit_id, patient_name, admission_reason, bed_no, wing, invoice_no,discharged_by,discharge_date,date_admitted  "
                        + " FROM hp_admission  where discharge=true and check_out=true and   (discharge_date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            } else if (dischargeTypeCmb.getSelectedIndex() == 3) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  patient_no, patient_name, admission_reason, bed_no, wing, invoice_no,nurse_discharging,date_admitted "
                        + " FROM hp_admission  where nurse_discharge=true and  discharge=true and check_out=true and  (nursedischarge_date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            }
        } else if (wardCmb.getSelectedIndex() > 1) {
            if (dischargeTypeCmb.getSelectedIndex() == 1) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT patient_no, patient_name, admission_reason, bed_no, wing, invoice_no,discharged_by,discharge_date "
                        + " FROM hp_admission  where discharge=true and check_out=false and ward='" + wardCmb.getSelectedItem().toString() + "' and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            } else if (dischargeTypeCmb.getSelectedIndex() == 2) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  patient_no,visit_id, patient_name, admission_reason, bed_no, wing, invoice_no,discharged_by,discharge_date "
                        + " FROM hp_admission  where discharge=true and check_out=true and ward='" + wardCmb.getSelectedItem().toString() + "' and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            } else if (dischargeTypeCmb.getSelectedIndex() == 3) {

                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  patient_no, patient_name, admission_reason, bed_no, wing, invoice_no,discharged_by,discharge_date "
                        + " FROM hp_admission  where nurse_discharge=true and  discharge=true and check_out=true and ward='" + wardCmb.getSelectedItem().toString() + "' and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "');"));

            }
        }
        countPatientInWard();
    }//GEN-LAST:event_dischargeTypeCmbItemStateChanged
    private void countPatientInWard() {

        try {

            String label = "No of Patients in " + wardCmb.getSelectedItem() + " is :";

            label = label.concat("" + occupancytable.getRowCount() + "\t");

            occupyno.setText(label);
        } catch (Exception label) {
            label.printStackTrace();
        }

    }
    private void patientStatusTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientStatusTableMouseClicked
        if (wardCmb.getSelectedIndex() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "You Must Select The Ward Type at the top First");
        } else {
            String patientNo = null;
            Object patientStatus = null;
            try {

                java.sql.Statement stmtStatus = connectDB.createStatement();
                java.sql.ResultSet rsetStatus = stmtStatus.executeQuery("select patient_no, patient_status from hp_patient_reporter ");
                while (rsetStatus.next()) {
                    patientNo = rsetStatus.getString(1);
                    patientStatus = rsetStatus.getObject(2);
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            }
            try {
                if (wardCmb.getSelectedIndex() == 1) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT patient_no, name, visit_id, ward, date_admitted, patient_status, \n"
                            + "       report_comment, status_date, user_name,report_time\n"
                            + "  FROM hp_patient_reporter "
                            + " where  hp_patient_reporter.patient_status ='" + patientStatusTable.getModel().getValueAt(patientStatusTable.getSelectedRow(), 0).toString().trim() + "'"
                            + " and (hp_patient_reporter.status_date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 1 asc"
                    ));

                    occupancyquery = "SELECT count(hp_patient_reporter.patient_no)"
                            + " FROM hp_patient_reporter where hp_patient_reporter.patient_status = '" + patientStatusTable.getModel().getValueAt(patientStatusTable.getSelectedRow(), 0) + "' and (hp_admission.date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')";
                } else {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + " SELECT patient_no, name, visit_id, ward, date_admitted, patient_status, \n"
                            + "       report_comment, status_date, user_name,report_time\n"
                            + "  FROM hp_patient_reporter where "
                            + " hp_patient_reporter.ward='" + wardCmb.getSelectedItem().toString() + "'  and hp_patient_reporter.patient_status ='" + patientStatusTable.getModel().getValueAt(patientStatusTable.getSelectedRow(), 0).toString().trim() + "'"
                            + " and (hp_patient_reporter.status_date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 1 asc"));

                    occupancyquery = "SELECT count(hp_patient_reporter.patient_no)"
                            + " FROM hp_patient_reporter where hp_patient_reporter.ward='" + wardCmb.getSelectedItem().toString() + "' and hp_patient_reporter.patient_status = '" + patientStatusTable.getModel().getValueAt(patientStatusTable.getSelectedRow(), 0) + "' and (hp_admission.date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')";

                }
                labelDisplayCatgory.setText("Category Type :" + patientStatusTable.getModel().getValueAt(patientStatusTable.getSelectedRow(), 0).toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        countPatientInWard();
    }//GEN-LAST:event_patientStatusTableMouseClicked

    private void wardCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wardCmbActionPerformed

        if (wardCmb.getSelectedIndex() == 1) {
            occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no, patient_name, ward, wing, date_admitted "
                    + "FROM hp_admission  where discharge=false and check_out=false "
                    + " and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') "
                    + "                 union "
                    + "SELECT hp_patient_visit.patient_no, hp_patient_visit.name,hp_patient_visit.clinic,hp_patient_visit.department, hp_patient_visit.date "
                    + "  FROM hp_patient_visit  where   "
                    + "                (hp_patient_visit.date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 5,1"));

            occupancyquery = "";
        } else {
            occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no, patient_name, ward, wing, date_admitted "
                    + "FROM hp_admission  where  discharge=false and check_out=false   and ward='" + wardCmb.getSelectedItem().toString() + "'"
                    + " and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')  "
                    + "                 union "
                    + " SELECT hp_patient_visit.patient_no, hp_patient_visit.name,hp_patient_visit.clinic,hp_patient_visit.department, hp_patient_visit.date "
                    + "  FROM hp_patient_visit  where  hp_patient_visit.clinic='" + wardCmb.getSelectedItem().toString() + "' and  "
                    + "               ( hp_patient_visit.date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 5,1"));

            occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no "
                    + "FROM hp_admission  where discharge=false and check_out=false and ward='" + wardCmb.getSelectedItem().toString() + "' and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')"));

            occupancyquery = "SELECT count(patient_no) FROM hp_admission  where discharge=false and ward='" + wardCmb.getSelectedItem() + "' and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')";
        }
        countPatientInWard();
    }//GEN-LAST:event_wardCmbActionPerformed

    private void moreReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreReportsActionPerformed
        // TODO add your handling code here:
        if (wardCmb.getSelectedIndex() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "You Must Select The Ward Type at the top First");
        } else {
            if (wardCmb.getSelectedIndex() == 1) {
                if (moreReports.getSelectedIndex() == 1) {

                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + " SELECT patient_no, patient_name, ward, wing, date_admitted\n"
                            + " FROM hp_admission  where nationality !='Kenyan'  and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') "
                            + " union\n"
                            + " SELECT hpr.patient_no, hpr.first_name||''||hpr.second_name||''||hpr.last_name, hpv.clinic,hpv.department, hpv.date\n"
                            + " FROM hp_patient_register hpr,hp_patient_visit hpv where hpr.pat_nationality!='Kenyan' and hpr.patient_no=hpv.patient_no"
                            + " and (hpv.date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 5,2,1 "));

                } else if (moreReports.getSelectedIndex() == 2) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select  main_service ,disease,date_recorded::date||''||date_of_entry::time(0),doctor_surgeon from hp_patient_diagnosis "
                            + " where  ( date_recorded::date  between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') ORDER BY date_of_entry::time(0)"));
                } else if (moreReports.getSelectedIndex() == 3) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT patient_no,booking_no, p_name, booking_type,  service as Procedure_Name,patient_source,clinic as Theatre_Name, appointment_date  FROM pb_bookings  "
                            + "               where cancel=false and type_of_booking='Theatre Booking' and theatre_Discharge=false and status='Booked' and confirmation=true  ORDER BY booking_no DESC"));
                }
            } else {

                if (moreReports.getSelectedIndex() == 3) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT patient_no,booking_no, p_name, booking_type,  service as Procedure_Name,patient_source,clinic as Theatre_Name, appointment_date  FROM pb_bookings  "
                            + " where patient_source ilike '" + wardCmb.getSelectedItem().toString() + "' and cancel=false and type_of_booking='Theatre Booking' and theatre_Discharge=false and status='Booked' and confirmation=true  ORDER BY booking_no DESC"));
                } else if (moreReports.getSelectedIndex() == 2) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select  main_service ,disease,date_recorded::date||''||date_of_entry::time(0),doctor_surgeon from hp_patient_diagnosis "
                            + " where ward_name = '" + wardCmb.getSelectedItem().toString() + "' and  ( date_recorded::date  between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') ORDER BY date_of_entry::time(0)"));
                } else if (moreReports.getSelectedIndex() == 1) {
                    occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + " SELECT patient_no, patient_name, ward, wing, date_admitted\n"
                            + " FROM hp_admission  where ward='" + wardCmb.getSelectedItem() + "' and nationality !='Kenyan'  and (date_admitted between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') "
                            + " union\n"
                            + " SELECT hpr.patient_no, hpr.first_name||''||hpr.second_name||''||hpr.last_name, hpv.clinic,hpv.department, hpv.date\n"
                            + " FROM hp_patient_register hpr,hp_patient_visit hpv where hpv.clinic='" + wardCmb.getSelectedItem() + "' and hpr.pat_nationality!='Kenyan' and hpr.patient_no=hpv.patient_no"
                            + " and (hpv.date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "') order by 5,2,1 "));
                }

            }
        }
        countPatientInWard();
    }//GEN-LAST:event_moreReportsActionPerformed

    private void moreReportsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_moreReportsItemStateChanged
        if (moreReports.getSelectedIndex() == 4) {
            occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                    " select distinct \n"
                    + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,\n"
                    + "UPPER(hpv.name) as patient_name,hpv.doctor_name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,\n"
                    + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date\n"
                    + "from  hp_patient_visit hpv  where hpv.nature!='1' and (hpv.input_date::date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "')     \n"
                    + "    ORDER BY hpv.input_date::time(0)   "));

        }
    }//GEN-LAST:event_moreReportsItemStateChanged

    private void loadReport() {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JComboBox dischargeTypeCmb;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelDisplayCatgory;
    private javax.swing.JComboBox moreReports;
    private javax.swing.JTable occupancytable;
    private javax.swing.JLabel occupyno;
    private javax.swing.JTextArea patientDetailsTextArea;
    private javax.swing.JTable patientStatusTable;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JPanel reportBodyPanel;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JComboBox wardCmb;
    // End of variables declaration//GEN-END:variables

}
