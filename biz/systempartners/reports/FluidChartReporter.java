/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package biz.systempartners.reports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author  funsoft
 */
public class FluidChartReporter extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    private String ward=null;
    private String visitId=null;
    private String gender=null;
    int years=0, months=0,days=0;
    private String patient_name=null;
    private Vector details= new Vector();
    
    /** Creates new form ReportIntfr */
    public FluidChartReporter(java.sql.Connection connDb,Vector detail) {
        connectDB = connDb;
       details=detail; 
       initComponents();
        fluidChartReload();
            getOutputsFluid();
      
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
        openReportBtn = new javax.swing.JButton();
        saveReportBtn = new javax.swing.JButton();
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
        Accumulatives = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        totalIntake = new javax.swing.JTextField();
        totalOutput = new javax.swing.JTextField();
        totalBalance = new javax.swing.JTextField();
        jLabel240 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        stoolTxt = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        wetDiaperTxt = new javax.swing.JTextField();
        wetBedTxt = new javax.swing.JTextField();
        visitedTxt = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funsoft Inpatient Reporte");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(export2SpreadSheetBtn, gridBagConstraints);
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

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Printer 4.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Print ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(printBtn, gridBagConstraints);

        openReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Desktop.png"))); // NOI18N
        openReportBtn.setMnemonic('o');
        openReportBtn.setText("Open Report ...");
        openReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(openReportBtn, gridBagConstraints);

        saveReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Globe 4.png"))); // NOI18N
        saveReportBtn.setMnemonic('s');
        saveReportBtn.setText("Save Report ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(saveReportBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
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
        patientDetailsTextArea.setText("FLUID CHART FOR PATIENT NO :- "+details.elementAt(0)+"  DATE :-"+details.elementAt(3)+" \n"
            + ""+details.elementAt(4)+"");
        patientDetailsTextArea.setBorder(null);
        jScrollPane1.setViewportView(patientDetailsTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
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
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel39.add(jSeparator3, gridBagConstraints);

        occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
            "SELECT \n" +
            "  fluid_input.iv, \n" +
            "  fluid_input.oral, \n" +
            "  fluid_input.naso, \n" +
            "  fluid_input.peg_feed, \n" +
            "  fluid_output.drains, \n" +
            "  fluid_output.urine, \n" +
            "  fluid_output.stool, \n" +
            "  fluid_output.ng, \n" +
            "  fluid_output.vomitus\n" +
            "FROM \n" +
            "  nursing.fluid_input, \n" +
            "  nursing.fluid_output\n" +
            "WHERE \n" +
            "  fluid_input.patient_id ='"+details.elementAt(0)+"' AND fluid_output.patient_no='"+details.elementAt(0)+"' AND\n" +
            "  fluid_input.visit_id ='"+details.elementAt(1)+"' AND fluid_output.visit_id='"+details.elementAt(1)+"' AND\n" +
            "  fluid_input.transaction_date >='"+details.elementAt(3)+"' and fluid_output.transaction_date>='"+details.elementAt(3)+"';"));
    jScrollPane11.setViewportView(occupancytable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel39.add(jScrollPane11, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    reportBodyPanel.add(jPanel39, gridBagConstraints);

    Accumulatives.setBackground(new java.awt.Color(240, 242, 241));
    Accumulatives.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 255))); // NOI18N
    Accumulatives.setToolTipText("");
    Accumulatives.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
    Accumulatives.setLayout(new java.awt.GridBagLayout());

    jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monitor Accumulatives", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 255))); // NOI18N
    jPanel11.setForeground(new java.awt.Color(0, 0, 255));
    jPanel11.setLayout(new java.awt.GridBagLayout());

    jLabel222.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel222.setForeground(new java.awt.Color(153, 0, 0));
    jLabel222.setText("ACCUM INTAKE");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel222, gridBagConstraints);

    jLabel223.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel223.setForeground(new java.awt.Color(153, 0, 0));
    jLabel223.setText("ACCUM OUTPUT");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel223, gridBagConstraints);

    jLabel224.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel224.setForeground(new java.awt.Color(153, 0, 0));
    jLabel224.setText("ACCUM BALANCE");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel224, gridBagConstraints);

    jLabel225.setText("ml");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel225, gridBagConstraints);

    jLabel226.setText("ml");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel226, gridBagConstraints);

    totalIntake.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(totalIntake, gridBagConstraints);

    totalOutput.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(totalOutput, gridBagConstraints);

    totalBalance.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(totalBalance, gridBagConstraints);

    jLabel240.setText("ml");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(jLabel240, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    Accumulatives.add(jPanel11, gridBagConstraints);

    jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Others", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    jPanel21.setLayout(new java.awt.GridBagLayout());

    jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 153, 153)));
    jPanel23.setLayout(new java.awt.GridBagLayout());

    jLabel5.setText("Stool");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel23.add(jLabel5, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel23.add(stoolTxt, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel21.add(jPanel23, gridBagConstraints);

    jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Urine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 153, 153)));
    jPanel24.setLayout(new java.awt.GridBagLayout());

    jLabel21.setText("Wet Bed");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel24.add(jLabel21, gridBagConstraints);

    jLabel53.setText("Wet Diapers");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel24.add(jLabel53, gridBagConstraints);

    jLabel56.setText("Visited Toilet");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel24.add(jLabel56, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel24.add(wetDiaperTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel24.add(wetBedTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel24.add(visitedTxt, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel21.add(jPanel24, gridBagConstraints);

    jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Intravenous Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 153, 153)));
    jPanel26.setLayout(new java.awt.GridBagLayout());
    jPanel26.add(jProgressBar1, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel21.add(jPanel26, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 1.0;
    Accumulatives.add(jPanel21, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.3;
    gridBagConstraints.weighty = 1.0;
    reportBodyPanel.add(Accumulatives, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
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
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(buttonPanel, gridBagConstraints);

    setBounds(0, 0, 643, 441);
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed

occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
    "SELECT \n" +
    "  fluid_input.iv, \n" +
    "  fluid_input.oral, \n" +
    "  fluid_input.naso, \n" +
    "  fluid_input.peg_feed, \n" +
    "  fluid_output.drains, \n" +
    "  fluid_output.urine, \n" +
    "  fluid_output.stool, \n" +
    "  fluid_output.ng, \n" +
    "  fluid_output.vomitus\n" +
    "FROM \n" +
    "  nursing.fluid_input, \n" +
    "  nursing.fluid_output\n" +
    "WHERE \n" +
    "  fluid_input.patient_id ='"+details.elementAt(0)+"' AND fluid_output.patient_no='"+details.elementAt(0)+"' AND\n" +
    "  fluid_input.visit_id ='"+details.elementAt(1)+"' AND fluid_output.visit_id='"+details.elementAt(1)+"' AND\n" +
    "  fluid_input.transaction_date between ("+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"::date and"
            + " "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"::date ) "
            + "and fluid_output.transaction_date between ("+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"::date and"
            + " "+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"::date )"));
    jScrollPane11.setViewportView(occupancytable);

    }//GEN-LAST:event_openReportBtnActionPerformed

       private void fluidChartReload(){
        //get input
    
                try{
                //getting details
                    connectDB.setAutoCommit(false);

                    //inputs
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12=stm12.executeQuery("select * from nursing.fluid_chart_input where patient_id= '"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"'"); 
            //java.sql.ResultSet rse125=stm12.executeQuery("select iv_type as Iv_Type,iv_amount as Iv_Amount,oral_type as Oral_Type,oral_amount as Oral_Amount,naso_gastric as Naso_Gastric,peg_feed as Peg_feed from nursing.fluid_chart_input where patient_id='"+nameNoTxt.getText()+"' AND visit_id='"+visitId+"'");      
            Double Total=0.0;        
            while (rse12.next()){
                ///getting the user
                Double subTotal;
                subTotal=Double.parseDouble(rse12.getString("iv_amount"))+
               Double.parseDouble(rse12.getString("oral_amount"))+
                               Double.parseDouble(rse12.getString("naso_gastric"))+
                                       Double.parseDouble(rse12.getString("peg_feed"));
                Total=Total+subTotal;
         }
                //outputs
             java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123=stm123.executeQuery("select * from nursing.fluid_chart_output where patient_no='"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"'"); 
                  Double Totals=0.0;        
            while (rse123.next()){
                ///getting the user
                Double subTotal;
                subTotal=Double.parseDouble(rse123.getString("urine_amount"))+
                            Double.parseDouble(rse123.getString("ng_sanction"))+
                             Double.parseDouble(rse123.getString("vomitus"))+
                               Double.parseDouble(rse123.getString("drain_one_amount"))+
                               Double.parseDouble(rse123.getString("drain_two_amount"))+
                               Double.parseDouble(rse123.getString("drain_three_amount"))+
                              Double.parseDouble(rse123.getString("drain_four_amount"))+
                        Double.parseDouble(rse123.getString("drain_five_amount"));
                Totals=Totals+subTotal;
         }
            totalIntake.setText(Double.toString(Total));
            totalOutput.setText(Double.toString(Totals));
            totalBalance.setText(Double.toString(Total-Totals));
            
            
          
                
            connectDB.commit();
            connectDB.setAutoCommit(true);          
         }
         
         catch(final Exception es){
         System.out.println(es);
         
         }
                
                
    }
       
        private void getOutputsFluid(){
        try {
            connectDB.setAutoCommit(false);
            //stool frequency
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12=stm12.executeQuery("select * from nursing.fluid_chart_output where patient_no='"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"' AND stool='yes'"); 
            int i=0;
            while (rse12.next()){
                ///getting the user
                i=i+1;
            }
            stoolTxt.setText(Integer.toString(i));
            
             //wet diaper frequency
           java.sql.Statement st = connectDB.createStatement();
           java.sql.ResultSet rs=st.executeQuery("select * from nursing.fluid_chart_output where patient_no='"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"' AND urine_state='Wet-Diaper'"); 
           int j=0;
            while (rs.next()){
                ///getting the user
                j=j+1;
            }
           wetDiaperTxt.setText(Integer.toString(j));
            
              
             //visited frequency
           java.sql.Statement sa = connectDB.createStatement();
           java.sql.ResultSet res=sa.executeQuery("select * from nursing.fluid_chart_output where patient_no='"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"' AND urine_state='Visited-Toilet'"); 
           int k=0;
           while (res.next()){
                ///getting the user
                k=k+1;
            }
           visitedTxt.setText(Integer.toString(k));

                       //wet bed frequency
           java.sql.Statement stm = connectDB.createStatement();
           java.sql.ResultSet rse=stm.executeQuery("select * from nursing.fluid_chart_output where patient_no='"+details.elementAt(0)+"' AND visit_id='"+details.elementAt(1)+"' AND urine_state='Wet-Bed'"); 
           int l=0; 
           while (rse.next()){
                ///getting the user
                l=l+1;
            } 
           wetBedTxt.setText(Integer.toString(l)); 
            
         connectDB.commit();
            connectDB.setAutoCommit(true);           
        }
         catch(final Exception es){
         System.out.println(es);
         
         }
    }
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Accumulatives;
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable occupancytable;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JTextArea patientDetailsTextArea;
    private javax.swing.JButton printBtn;
    public static javax.swing.JPanel reportBodyPanel;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTextField stoolTxt;
    private javax.swing.JTextField totalBalance;
    private javax.swing.JTextField totalIntake;
    private javax.swing.JTextField totalOutput;
    private javax.swing.JTextField visitedTxt;
    private javax.swing.JTextField wetBedTxt;
    private javax.swing.JTextField wetDiaperTxt;
    // End of variables declaration//GEN-END:variables
    
}
