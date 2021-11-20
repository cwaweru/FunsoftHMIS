/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.nursing;

import static com.afrisoftech.hospital.HospitalMain.saccopn;
import static com.afrisoftech.nursing.EditNursing.getconnection;
import java.awt.Color;
import com.afrisoftech.lib.DBObject;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import pupilgauge.PupilGauge;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jdalbey.timechooser.TimeChooser;
import test.testing;
import com.afrisoftech.lib.DBObject;
import java.util.Objects;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import org.jdesktop.swingx.decorator.ColorHighlighter;

/**
 *
 * @author saqlever
 */
public class NursingTriage extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    Date date = new Date();
    private static String ward = null;
    private String user = null;
    private String visitId = null;
    private String gender = null;
    private String patient_name = null;
    com.afrisoftech.lib.DBObject dbObject;
    com.afrisoftech.nursing.NursingObject nursingObject;
    int years = 0, months = 0, days = 0;
    java.lang.String integrities = "";
    JComboBox dentures = null;
    JComboBox Jewellery = null;
    JComboBox Wigs = null;
    JComboBox Preparation = null;
    JComboBox Others = null;
    private String medicine = null;
    private String transfusionStatus;
    Vector bloodvector = new Vector();
    private boolean bloodPackExist;
    private boolean confirmation;
    private boolean transfusion;
    private String packDetails;
    String namess = null;

    private static Vector implemented = new Vector();
    private static Vector formDetails = new Vector();
    private String bookingNo;
    private String dateAdmitted;
    private String theatrePatients;
    private boolean underFive;
    String src = "";

    /**
     * Creates new form NursingTriage
     */
    public NursingTriage(java.sql.Connection connDb, String Ward, String source) {
        ward = Ward;
        connectDB = connDb;
        src = source;

        this.setTitle(ward);
        user = getUser();
        System.out.println("the ward is this one " + ward);
        initComponents();
        this.setTitle(ward);
        
        if(source.equalsIgnoreCase("Reg")){
            
            nursingTabbedPane.remove(7);
            nursingTabbedPane.remove(6);
            nursingTabbedPane.remove(5);
            nursingTabbedPane.remove(4);
            nursingTabbedPane.remove(3);
            nursingTabbedPane.remove(2);
            //nursingTabbedPane.remove(9);
            
        }
        
         
//         com.afrisoftech.dbadmin.JTable predicateTable1 = (com.afrisoftech.dbadmin.JTable) occupancyTable;
//            predicateTable1.setHighlighterPipeline(predicateTable1, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.ORANGE, java.awt.Color.BLACK, "Normal", 5, 5),
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.RED, java.awt.Color.BLACK, "Emergency", 5, 5)
//            });

java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable = (com.afrisoftech.dbadmin.JXTable) occupancyTable;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate = new org.jdesktop.swingx.decorator.PatternPredicate("Normal", 5, 5);
        ColorHighlighter yellow = new ColorHighlighter(patternPredicate, Color.ORANGE, null, Color.ORANGE, null);
        tableHighlighters.addElement(yellow);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate1 = new org.jdesktop.swingx.decorator.PatternPredicate("Emergency", 5, 5);
        ColorHighlighter pink = new ColorHighlighter(patternPredicate1, Color.RED, null, Color.RED, null);
        tableHighlighters.add(pink);
        // Highlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(6));
        ColorHighlighter tableHighlightersArray[] = new ColorHighlighter[]{yellow, pink};
        predicateTable.setHighlighterPipeline(predicateTable, tableHighlightersArray);

        //
        occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment,input_date::TIME(0) AS arrival_time\n"
                + " From  hp_patient_visit   \n"
                + " where date::date >= now()::date - 2 AND discharge is null ORDER BY 6,8,10 "
        ));
//                occupancytable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
//                + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment\n"
//                + " From  hp_patient_visit   \n"
//                + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1"
//        ));
        occupyno.setText("No of Patients in " + ward + " is :" + occupancyTable.getRowCount());
       

        coluorTable();
        litrestxt.setEnabled(false);
        modeofadmincmb.setEnabled(false);
        saturationtxt.setEnabled(false);
        com.afrisoftech.hospital.HospitalMain.mainSplitPane.setDividerLocation(0);

        this.setSize(com.afrisoftech.hospital.HospitalMain.saccopn.getSize());
        //hide drains 
        hideDrains();
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

        patientSearch = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton522 = new javax.swing.JButton();
        fluidChartDialog = new javax.swing.JDialog();
        fluidscrllpn = new javax.swing.JScrollPane();
        fluidtbltxt = new javax.swing.JTable();
        vitalSigns = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        commentVital = new javax.swing.JTextArea();
        labelPanel3 = new javax.swing.JPanel();
        imgPanel3 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        actionsPanel3 = new javax.swing.JPanel();
        newAction3 = new javax.swing.JButton();
        clearAction3 = new javax.swing.JButton();
        spacerPanel3 = new javax.swing.JPanel();
        helpPanel3 = new javax.swing.JPanel();
        helpAction3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jComboBoxPainScale = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jComboBoxTypePain = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        lblRespiration = new javax.swing.JLabel();
        txtRespiratory = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jComboBoxLungSound = new javax.swing.JComboBox();
        jLabel97 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        txtPulseRate = new javax.swing.JTextField();
        jCheckBoxRegular = new javax.swing.JCheckBox();
        jCheckBoxIrregular = new javax.swing.JCheckBox();
        txtPulseOximity = new javax.swing.JTextField();
        pulseOximityTxt = new javax.swing.JLabel();
        pulseRateProgessBar = new javax.swing.JProgressBar();
        tempProgessBar2 = new javax.swing.JProgressBar();
        jLabel96 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        weightTxt = new javax.swing.JTextField();
        heightTxt = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        bmiTxt = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        txtSystolic = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();
        txtDiastolicBp = new javax.swing.JTextField();
        jProgressBarSystolicBp = new javax.swing.JProgressBar();
        jProgressBarDiastolic = new javax.swing.JProgressBar();
        jPanel27 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        tempProgessBar = new javax.swing.JProgressBar();
        glucoseProgressBar = new javax.swing.JProgressBar();
        txtSpinner = new javax.swing.JTextField();
        bloodglucosetxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtUrinary = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        urgencyComboBox = new javax.swing.JComboBox();
        jButton14 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        occpancygroup = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        stool = new javax.swing.ButtonGroup();
        packRdbgroupBtn = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        occupancyDetailsDialog = new javax.swing.JDialog();
        jSearchPanel3 = new javax.swing.JPanel();
        jButton524 = new javax.swing.JButton();
        occupancywardCMB = new javax.swing.JComboBox();
        okupybtnngrp = new javax.swing.ButtonGroup();
        usersSearchdialog = new javax.swing.JDialog();
        jPanel58 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        userTable = new com.afrisoftech.dbadmin.JTable();
        reportbtngroup = new javax.swing.ButtonGroup();
        systemicAssesment = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jComboBoxBestVerbal = new javax.swing.JComboBox();
        jComboBoxEyeOpen = new javax.swing.JComboBox();
        jComboBoxBest = new javax.swing.JComboBox();
        jPanel12111 = new javax.swing.JPanel();
        facilityidLbl11411 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        txtRightEyeSize = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        txtLeftEyeSize = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jComboBoxLReaction = new javax.swing.JComboBox();
        jComboBoxReaction = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel122 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jComboBoxRightArm = new javax.swing.JComboBox();
        jLabel124 = new javax.swing.JLabel();
        jComboBoxLeftArm = new javax.swing.JComboBox();
        jPanel45 = new javax.swing.JPanel();
        jComboBoxRightLeg = new javax.swing.JComboBox();
        jComboBoxLeftLeg = new javax.swing.JComboBox();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel46 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jComboBoxCorneal = new javax.swing.JComboBox();
        jComboBoxGag = new javax.swing.JComboBox();
        jComboBoxBabinski = new javax.swing.JComboBox();
        jComboBoxBreathingPattern = new javax.swing.JComboBox();
        jComboBoxSeizureActivity = new javax.swing.JComboBox();
        jPanel47 = new javax.swing.JPanel();
        seizureactivity = new javax.swing.JRadioButton();
        breathingpattern = new javax.swing.JRadioButton();
        consciousnesterm = new javax.swing.JRadioButton();
        selecttermcmb = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        descriptiontxarea = new javax.swing.JTextArea();
        labelPanel = new javax.swing.JPanel();
        imgPanel = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        newAction = new javax.swing.JButton();
        updateAction = new javax.swing.JButton();
        deleteAction = new javax.swing.JButton();
        clearAction = new javax.swing.JButton();
        spacerPanel = new javax.swing.JPanel();
        helpPanel = new javax.swing.JPanel();
        helpAction = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        roomairradio = new javax.swing.JCheckBox();
        oxygenradio = new javax.swing.JCheckBox();
        roomairtxt = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        panelhide = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        modeofadmincmb = new javax.swing.JComboBox();
        saturationtxt = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        litrestxt = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        airwaycmb = new javax.swing.JComboBox();
        respirationcmb = new javax.swing.JComboBox();
        breathingsoundscmb = new javax.swing.JComboBox();
        labelPanel6 = new javax.swing.JPanel();
        imgPanel6 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        actionsPanel7 = new javax.swing.JPanel();
        newAction7 = new javax.swing.JButton();
        updateAction6 = new javax.swing.JButton();
        deleteAction6 = new javax.swing.JButton();
        clearAction7 = new javax.swing.JButton();
        spacerPanel7 = new javax.swing.JPanel();
        helpPanel6 = new javax.swing.JPanel();
        helpAction6 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        labelPanel7 = new javax.swing.JPanel();
        imgPanel7 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        actionsPanel8 = new javax.swing.JPanel();
        saveCadiovascular = new javax.swing.JButton();
        updateAction7 = new javax.swing.JButton();
        clearAction8 = new javax.swing.JButton();
        spacerPanel8 = new javax.swing.JPanel();
        helpPanel7 = new javax.swing.JPanel();
        helpAction7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pollorcmb = new javax.swing.JComboBox();
        extremitiescmb = new javax.swing.JComboBox();
        jPanel55 = new javax.swing.JPanel();
        jLabel165 = new javax.swing.JLabel();
        txtPulseRate1 = new javax.swing.JTextField();
        regularchk = new javax.swing.JCheckBox();
        irregularchk = new javax.swing.JCheckBox();
        txtPulseOximity1 = new javax.swing.JTextField();
        pulseOximityTxt1 = new javax.swing.JLabel();
        pulseRateProgessBar2 = new javax.swing.JProgressBar();
        jLabel98 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        others = new javax.swing.JLabel();
        otherstxt = new javax.swing.JTextField();
        labelPanel8 = new javax.swing.JPanel();
        imgPanel8 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        actionsPanel9 = new javax.swing.JPanel();
        saveGastrointestinal = new javax.swing.JButton();
        updateAction8 = new javax.swing.JButton();
        clearAction9 = new javax.swing.JButton();
        spacerPanel9 = new javax.swing.JPanel();
        helpPanel8 = new javax.swing.JPanel();
        helpAction8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        feedingcmb = new javax.swing.JComboBox();
        bowlsoundcmb = new javax.swing.JComboBox();
        abdomencmb = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        yeslabel = new javax.swing.JLabel();
        vomitingcmb = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        abdomenstatuscmb = new javax.swing.JComboBox();
        yescmb = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAbdomenStatus = new javax.swing.JTextArea();
        genitolurinarypanel = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        labelPanel9 = new javax.swing.JPanel();
        imgPanel9 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        actionsPanel10 = new javax.swing.JPanel();
        saveGenitourinary = new javax.swing.JButton();
        updateAction9 = new javax.swing.JButton();
        clearAction10 = new javax.swing.JButton();
        spacerPanel10 = new javax.swing.JPanel();
        helpPanel9 = new javax.swing.JPanel();
        helpAction9 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        urineoutputcmb = new javax.swing.JComboBox();
        externalcmb = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        lmpnaturecmb = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        menarch = new com.afrisoftech.lib.DatePicker();
        lmpdate = new com.afrisoftech.lib.DatePicker();
        jScrollPane2 = new javax.swing.JScrollPane();
        abnormalitytxtarea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        urineoutputtxtarea = new javax.swing.JTextArea();
        genitolurinarypanel1 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        labelPanel10 = new javax.swing.JPanel();
        imgPanel10 = new javax.swing.JPanel();
        jLabel170 = new javax.swing.JLabel();
        actionsPanel11 = new javax.swing.JPanel();
        extremitiesSave = new javax.swing.JButton();
        updateAction10 = new javax.swing.JButton();
        clearAction11 = new javax.swing.JButton();
        spacerPanel11 = new javax.swing.JPanel();
        helpPanel10 = new javax.swing.JPanel();
        helpAction10 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        moveallextremitiescmb = new javax.swing.JComboBox();
        deformitycmb = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        deformitytxtarea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        extremitycommenttxtarea = new javax.swing.JTextArea();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        lessionschk = new javax.swing.JCheckBox();
        infestationschk = new javax.swing.JCheckBox();
        bruiseschk = new javax.swing.JCheckBox();
        rasheschk = new javax.swing.JCheckBox();
        scarschk = new javax.swing.JCheckBox();
        burnschk = new javax.swing.JCheckBox();
        pressuresorechk = new javax.swing.JCheckBox();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        notestxtarea = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        colorcmb = new javax.swing.JComboBox();
        skintugor = new javax.swing.JComboBox();
        jPanel33 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        turningChart = new javax.swing.JPanel();
        turningchart = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel531111 = new javax.swing.JLabel();
        jButton57 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jComboBox2 = new javax.swing.JComboBox();
        labelPanel11 = new javax.swing.JPanel();
        imgPanel11 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        actionsPanel14 = new javax.swing.JPanel();
        newAction10 = new javax.swing.JButton();
        updateAction13 = new javax.swing.JButton();
        clearAction14 = new javax.swing.JButton();
        spacerPanel14 = new javax.swing.JPanel();
        helpPanel11 = new javax.swing.JPanel();
        helpAction11 = new javax.swing.JButton();
        pressureSorejPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        labelPanel1 = new javax.swing.JPanel();
        imgPanel1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        actionsPanel1 = new javax.swing.JPanel();
        newAction1 = new javax.swing.JButton();
        updateAction1 = new javax.swing.JButton();
        clearAction1 = new javax.swing.JButton();
        spacerPanel1 = new javax.swing.JPanel();
        helpPanel1 = new javax.swing.JPanel();
        helpAction1 = new javax.swing.JButton();
        pressureSoreInnerPane = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBoxGeneralCond = new javax.swing.JComboBox();
        jComboBoxSkinIntegrity = new javax.swing.JComboBox();
        jComboBoxNutrition = new javax.swing.JComboBox();
        jComboBoxMobility = new javax.swing.JComboBox();
        jComboBoxBodyBuild = new javax.swing.JComboBox();
        jComboBoxAge = new javax.swing.JComboBox();
        jComboBoxContinence = new javax.swing.JComboBox();
        jComboBoxConsciousness = new javax.swing.JComboBox();
        jComboBoxMedication = new javax.swing.JComboBox();
        riskScoreTxt = new javax.swing.JTextField();
        underground = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        header = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        nameNoTxt = new javax.swing.JTextField();
        searchButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        headerDatePicker = new com.afrisoftech.lib.DatePicker();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        patientDetailsTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        signstable = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        fluidbalance = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        nursingTabbedPane = new javax.swing.JTabbedPane();
        occupancy = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        occupancyTable = new com.afrisoftech.dbadmin.JXTable();
        occupyno = new javax.swing.JLabel();
        currentOccupRbtn = new javax.swing.JCheckBox();
        urgencycmbx = new javax.swing.JComboBox();
        theatreChbx = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        systemicReview = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        home = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nurseReport = new javax.swing.JPanel();
        nursescardex = new javax.swing.JPanel();
        cardexTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane28 = new javax.swing.JScrollPane();
        requestsTable = new com.afrisoftech.dbadmin.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        cardexTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        nursesRptCardex = new javax.swing.JTextArea();
        actionsPanel6 = new javax.swing.JPanel();
        newAction6 = new javax.swing.JButton();
        clearAction6 = new javax.swing.JButton();
        newAction12 = new javax.swing.JButton();
        newAction13 = new javax.swing.JButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        fluidChartTabbed = new javax.swing.JTabbedPane();
        fluidchart = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jComboBoxIvType = new javax.swing.JComboBox();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        iv_amount = new javax.swing.JSpinner();
        ivRate = new javax.swing.JSpinner();
        jScrollPane12 = new javax.swing.JScrollPane();
        iv_instructions = new javax.swing.JTextArea();
        txtTime = new javax.swing.JTextField();
        jPanel60 = new javax.swing.JPanel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jComboBoxOral_type = new javax.swing.JComboBox();
        jLabel212 = new javax.swing.JLabel();
        oral_amount = new javax.swing.JSpinner();
        jScrollPane17 = new javax.swing.JScrollPane();
        oral_fluids_instructions = new javax.swing.JTextArea();
        feedpane1 = new javax.swing.JPanel();
        jLabel214 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        nasofeed1 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        naso_gastric = new javax.swing.JSpinner();
        peg_feed = new javax.swing.JSpinner();
        jScrollPane18 = new javax.swing.JScrollPane();
        naso_instructions = new javax.swing.JTextArea();
        labelPanel12 = new javax.swing.JPanel();
        imgPanel12 = new javax.swing.JPanel();
        jLabel238 = new javax.swing.JLabel();
        actionsPanel12 = new javax.swing.JPanel();
        newAction8 = new javax.swing.JButton();
        clearAction12 = new javax.swing.JButton();
        spacerPanel12 = new javax.swing.JPanel();
        helpPanel12 = new javax.swing.JPanel();
        helpAction12 = new javax.swing.JButton();
        totalTxt = new javax.swing.JTextField();
        jLabel215 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        wetbedchk1 = new javax.swing.JCheckBox();
        wetdiaperchk1 = new javax.swing.JCheckBox();
        visittoiletchk1 = new javax.swing.JCheckBox();
        urine_amount = new javax.swing.JSpinner();
        urine_amount1 = new javax.swing.JSpinner();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel221 = new javax.swing.JLabel();
        lblDrain1 = new javax.swing.JLabel();
        lblDrain2 = new javax.swing.JLabel();
        lblDrain3 = new javax.swing.JLabel();
        lblDrain4 = new javax.swing.JLabel();
        lblDrain5 = new javax.swing.JLabel();
        drainscmb1 = new javax.swing.JComboBox();
        mls1 = new javax.swing.JLabel();
        mls2 = new javax.swing.JLabel();
        mls3 = new javax.swing.JLabel();
        mls4 = new javax.swing.JLabel();
        mls5 = new javax.swing.JLabel();
        drain1txt1 = new javax.swing.JSpinner();
        drain2txt1 = new javax.swing.JSpinner();
        drain5txt1 = new javax.swing.JSpinner();
        drain3txt1 = new javax.swing.JSpinner();
        drain4txt1 = new javax.swing.JSpinner();
        caption1 = new javax.swing.JTextField();
        caption2 = new javax.swing.JTextField();
        caption3 = new javax.swing.JTextField();
        caption4 = new javax.swing.JTextField();
        caption5 = new javax.swing.JTextField();
        drainCaption = new javax.swing.JLabel();
        resty1 = new javax.swing.JPanel();
        ngSuction = new javax.swing.JSpinner();
        vomitus = new javax.swing.JSpinner();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jCheckBoxStoolYes = new javax.swing.JCheckBox();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jCheckBoxStoolNo = new javax.swing.JCheckBox();
        labelPanel13 = new javax.swing.JPanel();
        imgPanel13 = new javax.swing.JPanel();
        jLabel239 = new javax.swing.JLabel();
        actionsPanel13 = new javax.swing.JPanel();
        newAction9 = new javax.swing.JButton();
        updateAction12 = new javax.swing.JButton();
        clearAction13 = new javax.swing.JButton();
        spacerPanel13 = new javax.swing.JPanel();
        helpPanel13 = new javax.swing.JPanel();
        helpAction13 = new javax.swing.JButton();
        totalTxt1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
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
        headerDatePicker3 = new com.afrisoftech.lib.DatePicker();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable6 = new com.afrisoftech.dbadmin.JTable();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable7 = new com.afrisoftech.dbadmin.JTable();
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
        jPanel64 = new javax.swing.JPanel();
        packkCombo = new javax.swing.JComboBox();
        jScrollPane30 = new javax.swing.JScrollPane();
        packTable = new com.afrisoftech.dbadmin.JTable();
        confimedRbtn = new javax.swing.JCheckBox();
        nonConfirmedRbt = new javax.swing.JCheckBox();
        bloodpackbtn = new javax.swing.JButton();
        jCheckBox6 = new javax.swing.JCheckBox();
        bloodTransfusionMainPane = new javax.swing.JPanel();
        labelPanel15 = new javax.swing.JPanel();
        imgPanel2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        actionsPanel2 = new javax.swing.JPanel();
        bloodtransfusionbtn = new javax.swing.JButton();
        clearbloodtransfusion = new javax.swing.JButton();
        jTextArea4 = new javax.swing.JTextArea();
        jButton11 = new javax.swing.JButton();
        helpPanel2 = new javax.swing.JPanel();
        helpAction2 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        bloodtransfusionpane = new javax.swing.JPanel();
        bloodTransfusionCenter = new javax.swing.JPanel();
        endtime = new javax.swing.JTextField();
        remarks = new javax.swing.JTextField();
        endtransfusion = new javax.swing.JCheckBox();
        jPanel36 = new javax.swing.JPanel();
        jLabel209 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        exacttime = new javax.swing.JTextField();
        bp = new javax.swing.JTextField();
        pr = new javax.swing.JTextField();
        jLabel161 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        rr = new javax.swing.JTextField();
        temp = new javax.swing.JTextField();
        hourlyrate = new javax.swing.JTextField();
        confirmationdetails = new javax.swing.JTextArea();
        amounttxtfld = new javax.swing.JTextField();
        dataPanel2 = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        signedby = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        bloodTransfusionUpper = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        packno = new javax.swing.JTextField();
        bloodtype = new javax.swing.JTextField();
        transfusiondate = new javax.swing.JTextField();
        jPanel38 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        starttime = new javax.swing.JTextField();
        transfusionrate = new javax.swing.JTextField();
        startedby = new javax.swing.JTextField();
        countercheck = new javax.swing.JTextField();
        starttransfusion = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        observationchart =   new com.afrisoftech.dbadmin.JTable();
        bloodTransfusionLower = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        general = new javax.swing.JComboBox();
        dermatological = new javax.swing.JComboBox();
        cardaic = new javax.swing.JComboBox();
        renal = new javax.swing.JComboBox();
        haemotological = new javax.swing.JComboBox();
        othersymptoms = new javax.swing.JTextField();
        symptomstime = new javax.swing.JTextField();
        jLabel207 = new javax.swing.JLabel();
        symptomscheck = new javax.swing.JCheckBox();
        nursingCare = new javax.swing.JPanel();
        nursingcareTab = new javax.swing.JTabbedPane();
        nursingCare1 = new javax.swing.JPanel();
        nursingcareplan = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jButton9 = new javax.swing.JButton();
        jButton523 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        nursingcaretable = new javax.swing.JTable();
        labelPanel14 = new javax.swing.JPanel();
        imgPanel14 = new javax.swing.JPanel();
        jLabel241 = new javax.swing.JLabel();
        actionsPanel15 = new javax.swing.JPanel();
        newAction11 = new javax.swing.JButton();
        clearAction15 = new javax.swing.JButton();
        spacerPanel15 = new javax.swing.JPanel();
        helpPanel14 = new javax.swing.JPanel();
        helpAction14 = new javax.swing.JButton();
        jPanel65 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        implementedCareTable = new com.afrisoftech.dbadmin.JTable();
        preOperativeList = new javax.swing.JPanel();
        preOperativeList1 = new javax.swing.JPanel();
        jPanel12112 = new javax.swing.JPanel();
        facilityidLbl11412 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        bladdercheckTxt = new javax.swing.JTextField();
        jLabel140 = new javax.swing.JLabel();
        urinalysisTxt = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ivdripsTxt = new javax.swing.JTextField();
        bloodsugarTxt = new javax.swing.JTextField();
        gastricTxt = new javax.swing.JTextField();
        albuminTxt = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        operationstxt = new javax.swing.JTextField();
        jScrollPane27 = new javax.swing.JScrollPane();
        preopgearsTable = new javax.swing.JTable();
        jPanel51 = new javax.swing.JPanel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jCheckBox49 = new javax.swing.JCheckBox();
        jCheckBox50 = new javax.swing.JCheckBox();
        certifiedTxt = new javax.swing.JTextField();
        bloodavailTxt = new javax.swing.JTextField();
        userTxt = new javax.swing.JTextField();
        xraysTxt = new javax.swing.JTextField();
        conscentcomb = new javax.swing.JComboBox();
        premedication = new javax.swing.JScrollPane();
        medicationArea = new javax.swing.JTextArea();
        certifiedtime = new com.afrisoftech.lib.DatePicker();
        wardnursetime = new com.afrisoftech.lib.DatePicker();
        labelPanel4 = new javax.swing.JPanel();
        imgPanel4 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        actionsPanel4 = new javax.swing.JPanel();
        newAction4 = new javax.swing.JButton();
        clearAction4 = new javax.swing.JButton();
        spacerPanel4 = new javax.swing.JPanel();
        helpPanel4 = new javax.swing.JPanel();
        helpAction4 = new javax.swing.JButton();
        drugAdministration = new javax.swing.JPanel();
        drugAdministration1 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel56 = new javax.swing.JPanel();
        drugAdministration2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        doctorMed = new com.afrisoftech.dbadmin.JTable();
        newAction5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        monitorMed = new com.afrisoftech.dbadmin.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        adminBtn = new javax.swing.JButton();
        doneAdministrationBtn = new javax.swing.JButton();
        jPanel57 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        medReport = new com.afrisoftech.dbadmin.JTable();
        completlyAdm = new javax.swing.JRadioButton();
        currentlyAdmt = new javax.swing.JRadioButton();
        timeAdministered = new javax.swing.JRadioButton();
        reportPanel = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        quadriplegicPatientRdi = new javax.swing.JRadioButton();
        paraplegicPatientRdi = new javax.swing.JRadioButton();
        veryIllPatient = new javax.swing.JRadioButton();
        referredInPatientRdi = new javax.swing.JRadioButton();
        gunshotsPatientRdi = new javax.swing.JRadioButton();
        totalNursingPatientRdi = new javax.swing.JRadioButton();
        assistedFeedingRdi = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jPanel70 = new javax.swing.JPanel();
        patientGuardedRdi = new javax.swing.JRadioButton();
        patientMissingRdi = new javax.swing.JRadioButton();
        patientReactiveRdi = new javax.swing.JRadioButton();
        patientDeadRdi = new javax.swing.JRadioButton();
        patientBreasFeedingRdi = new javax.swing.JRadioButton();
        sexuallyAssultedRdi = new javax.swing.JRadioButton();
        incidenceRdi = new javax.swing.JRadioButton();
        patientIsStaffRdi = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        commentArea = new javax.swing.JTextArea();
        jLabel72 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        savePatientReportBtn = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        billingPanel = new javax.swing.JPanel();
        medicinePanel = new javax.swing.JPanel();

        patientSearch.setModal(true);
        patientSearch.setUndecorated(true);
        patientSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel2.setLayout(new java.awt.GridBagLayout());

        jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField113CaretUpdate(evt);
            }
        });
        jTextField113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField113ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel2.add(jTextField113, gridBagConstraints);

        jSearchTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jSearchTable2.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable2.setShowHorizontalLines(false);
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
        jSearchTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable2MouseClicked(evt);
            }
        });
        jSearchScrollPane2.setViewportView(jSearchTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel2.add(jSearchScrollPane2, gridBagConstraints);

        jButton522.setText("Dispose");
        jButton522.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton522ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton522, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        patientSearch.getContentPane().add(jSearchPanel2, gridBagConstraints);

        fluidChartDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fluidChartDialog.setMinimumSize(new java.awt.Dimension(1200, 400));
        fluidChartDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        fluidtbltxt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Time", "IV Type", "Amt Started", "Rate", "IV Balance", "Total Input", "Urine", "NG Suction", "Stools", "Total Output"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fluidtbltxt.setMinimumSize(new java.awt.Dimension(700, 500));
        fluidtbltxt.setPreferredSize(new java.awt.Dimension(700, 500));
        fluidtbltxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fluidtbltxtFocusGained(evt);
            }
        });
        fluidscrllpn.setViewportView(fluidtbltxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fluidChartDialog.getContentPane().add(fluidscrllpn, gridBagConstraints);

        vitalSigns.setLayout(new java.awt.GridBagLayout());

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cardex/Comments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        commentVital.setColumns(20);
        commentVital.setRows(5);
        jScrollPane8.setViewportView(commentVital);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        vitalSigns.add(jScrollPane8, gridBagConstraints);

        labelPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel3.setLayout(new java.awt.GridBagLayout());

        imgPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel3.add(jLabel110, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel3.add(imgPanel3, gridBagConstraints);

        actionsPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel3.setLayout(new java.awt.GridBagLayout());

        newAction3.setMnemonic('w');
        newAction3.setText("Save");
        newAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(newAction3, gridBagConstraints);

        clearAction3.setMnemonic('C');
        clearAction3.setText("Clear");
        clearAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel3.add(clearAction3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel3.add(spacerPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel3.add(actionsPanel3, gridBagConstraints);

        helpPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel3.setLayout(new java.awt.GridBagLayout());

        helpAction3.setMnemonic('H');
        helpAction3.setText("Help");
        helpAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction3ActionPerformed(evt);
            }
        });
        helpPanel3.add(helpAction3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel3.add(helpPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        vitalSigns.add(labelPanel3, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pain", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel64.setText("Pain Scale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel12.add(jLabel64, gridBagConstraints);

        jComboBoxPainScale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBoxPainScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxPainScaleItemStateChanged(evt);
            }
        });
        jComboBoxPainScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPainScaleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 100);
        jPanel12.add(jComboBoxPainScale, gridBagConstraints);

        jLabel65.setText("Types of Pain");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel12.add(jLabel65, gridBagConstraints);

        jComboBoxTypePain.setEditable(true);
        jComboBoxTypePain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sharp", "Dull", "Calm" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 100);
        jPanel12.add(jComboBoxTypePain, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        vitalSigns.add(jPanel12, gridBagConstraints);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Respiratory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel15.setLayout(new java.awt.GridBagLayout());

        lblRespiration.setForeground(new java.awt.Color(204, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        jPanel15.add(lblRespiration, gridBagConstraints);

        txtRespiratory.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRespiratoryCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(txtRespiratory, gridBagConstraints);

        jLabel66.setText("Lung Sounds");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(jLabel66, gridBagConstraints);

        jComboBoxLungSound.setEditable(true);
        jComboBoxLungSound.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Crackles or rales crackling or rattling sounds", "Wheezing high-pitched whistling expirations", "Stridor harsh", "high-pitched inspirations", "Rhonchi coarse", "gravelly sounds", "Clear" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(jComboBoxLungSound, gridBagConstraints);

        jLabel97.setText("Respiratory Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(jLabel97, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        vitalSigns.add(jPanel15, gridBagConstraints);

        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pulse", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel52.setLayout(new java.awt.GridBagLayout());

        jLabel158.setText("Pulse Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel52.add(jLabel158, gridBagConstraints);

        txtPulseRate.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPulseRateCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(txtPulseRate, gridBagConstraints);

        buttonGroup2.add(jCheckBoxRegular);
        jCheckBoxRegular.setText("Regular");
        jCheckBoxRegular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRegularActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jCheckBoxRegular, gridBagConstraints);

        buttonGroup2.add(jCheckBoxIrregular);
        jCheckBoxIrregular.setText("Irregular");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jCheckBoxIrregular, gridBagConstraints);

        txtPulseOximity.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPulseOximityCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(txtPulseOximity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        jPanel52.add(pulseOximityTxt, gridBagConstraints);

        pulseRateProgessBar.setBackground(java.awt.SystemColor.info);
        pulseRateProgessBar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        pulseRateProgessBar.setForeground(java.awt.Color.green);
        pulseRateProgessBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pulseRateProgessBar.setBorderPainted(false);
        pulseRateProgessBar.setFocusable(false);
        pulseRateProgessBar.setString("");
        pulseRateProgessBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel52.add(pulseRateProgessBar, gridBagConstraints);

        tempProgessBar2.setBackground(java.awt.SystemColor.info);
        tempProgessBar2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        tempProgessBar2.setForeground(java.awt.Color.green);
        tempProgessBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tempProgessBar2.setBorderPainted(false);
        tempProgessBar2.setFocusable(false);
        tempProgessBar2.setString("");
        tempProgessBar2.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel52.add(tempProgessBar2, gridBagConstraints);

        jLabel96.setText("Pulse Oximetry");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel52.add(jLabel96, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        vitalSigns.add(jPanel52, gridBagConstraints);

        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B.M.I", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel53.setLayout(new java.awt.GridBagLayout());

        jLabel61.setText("BMI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel53.add(jLabel61, gridBagConstraints);

        weightTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                weightTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 100);
        jPanel53.add(weightTxt, gridBagConstraints);

        heightTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                heightTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 100);
        jPanel53.add(heightTxt, gridBagConstraints);

        jLabel62.setText("Weight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel53.add(jLabel62, gridBagConstraints);

        bmiTxt.setEditable(false);
        bmiTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bmiTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 100);
        jPanel53.add(bmiTxt, gridBagConstraints);

        jLabel67.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel53.add(jLabel67, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        vitalSigns.add(jPanel53, gridBagConstraints);

        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pressure", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel54.setLayout(new java.awt.GridBagLayout());

        jLabel157.setText("Systolic BP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 100);
        jPanel54.add(jLabel157, gridBagConstraints);

        txtSystolic.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSystolicCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel54.add(txtSystolic, gridBagConstraints);

        jLabel160.setText("Diastolic BP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 100);
        jPanel54.add(jLabel160, gridBagConstraints);

        txtDiastolicBp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDiastolicBpCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel54.add(txtDiastolicBp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel54.add(jProgressBarSystolicBp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel54.add(jProgressBarDiastolic, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        vitalSigns.add(jPanel54, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        jLabel55.setText("Temperature");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel27.add(jLabel55, gridBagConstraints);

        jLabel60.setText("Blood Glucose");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel27.add(jLabel60, gridBagConstraints);

        tempProgessBar.setBackground(java.awt.SystemColor.info);
        tempProgessBar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        tempProgessBar.setForeground(java.awt.Color.green);
        tempProgessBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tempProgessBar.setBorderPainted(false);
        tempProgessBar.setFocusable(false);
        tempProgessBar.setString("");
        tempProgessBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(tempProgessBar, gridBagConstraints);

        glucoseProgressBar.setBackground(new java.awt.Color(255, 255, 102));
        glucoseProgressBar.setForeground(new java.awt.Color(255, 51, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(glucoseProgressBar, gridBagConstraints);

        txtSpinner.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSpinnerCaretUpdate(evt);
            }
        });
        txtSpinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpinnerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(txtSpinner, gridBagConstraints);

        bloodglucosetxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bloodglucosetxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(bloodglucosetxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        vitalSigns.add(jPanel27, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel17.setText("Urinalysis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(txtUrinary, gridBagConstraints);

        jLabel46.setText("Patient Urgency");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(jLabel46, gridBagConstraints);

        urgencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Emergency" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(urgencyComboBox, gridBagConstraints);

        jButton14.setForeground(new java.awt.Color(255, 51, 153));
        jButton14.setText("Register Allergies/Drug Reactions");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel3.add(jButton14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        vitalSigns.add(jPanel3, gridBagConstraints);

        occupancyDetailsDialog.setAlwaysOnTop(true);
        occupancyDetailsDialog.setModal(true);
        occupancyDetailsDialog.setUndecorated(true);
        occupancyDetailsDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel3.setLayout(new java.awt.GridBagLayout());

        jButton524.setText("Dispose");
        jButton524.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton524ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(jButton524, gridBagConstraints);

        occupancywardCMB.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-select' union select ward_name from hp_wards order by 1"));
        occupancywardCMB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                occupancywardCMBItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(occupancywardCMB, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        occupancyDetailsDialog.getContentPane().add(jSearchPanel3, gridBagConstraints);

        usersSearchdialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        usersSearchdialog.setTitle("Users Search");
        usersSearchdialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel58.setLayout(new java.awt.GridBagLayout());

        jSplitPane4.setDividerLocation(50);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 153, 204))); // NOI18N
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });
        jSplitPane4.setLeftComponent(jTextField1);

        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(userTable);

        jSplitPane4.setRightComponent(jScrollPane13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel58.add(jSplitPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        usersSearchdialog.getContentPane().add(jPanel58, gridBagConstraints);

        systemicAssesment.setBackground(new java.awt.Color(255, 255, 204));
        systemicAssesment.setForeground(new java.awt.Color(0, 0, 153));
        systemicAssesment.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        systemicAssesment.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        systemicAssesment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                systemicAssesmentMouseClicked(evt);
            }
        });

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GLASCOW COMA SCALE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel40.setLayout(new java.awt.GridBagLayout());

        jLabel112.setText("Best Mortal Responce");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jLabel112, gridBagConstraints);

        jLabel114.setText("Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jLabel114, gridBagConstraints);

        txtTotal.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(txtTotal, gridBagConstraints);

        jLabel115.setText("Best Verbal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jLabel115, gridBagConstraints);

        jLabel116.setText("Eye Open");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jLabel116, gridBagConstraints);

        jComboBoxBestVerbal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Oriented -5-", "Confused -4-", "Inappropriate Word -3-", "Incomplihend Sonds -2-", "None -1-", "ET /Trach -T-" }));
        jComboBoxBestVerbal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBestVerbalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jComboBoxBestVerbal, gridBagConstraints);

        jComboBoxEyeOpen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontaneous -4-", "To Speech -3-", "To Pain -2-", "None -1-", "Eyes CLOSED by Edema -C-" }));
        jComboBoxEyeOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEyeOpenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jComboBoxEyeOpen, gridBagConstraints);

        jComboBoxBest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Obeys Commands -6-", "Localizes(Pain) -5-", "Withdraws (Pain) -4-", "Flexion (Pain) -3-", "Extension (Pain) -2-", "None -1-" }));
        jComboBoxBest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBestActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel40.add(jComboBoxBest, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel40, gridBagConstraints);

        jPanel12111.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pupil Gauge", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel12111.setLayout(new java.awt.GridBagLayout());

        facilityidLbl11411.setText("Reaction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(facilityidLbl11411, gridBagConstraints);

        jLabel117.setText("Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jLabel117, gridBagConstraints);

        txtRightEyeSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRightEyeSizeMouseClicked(evt);
            }
        });
        txtRightEyeSize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRightEyeSizeFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(txtRightEyeSize, gridBagConstraints);

        jLabel118.setText("Reaction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jLabel118, gridBagConstraints);

        txtLeftEyeSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLeftEyeSizeMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(txtLeftEyeSize, gridBagConstraints);

        jLabel119.setText("Right Eye:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jLabel119, gridBagConstraints);

        jLabel120.setText("Left Eye:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jLabel120, gridBagConstraints);

        jLabel121.setText("Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jLabel121, gridBagConstraints);

        jComboBoxLReaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Brisk -B-", "Sluggish -S-", "No Reaction -N-", "Eyes Closed -C-", "Normal Reaction" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jComboBoxLReaction, gridBagConstraints);

        jComboBoxReaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Brisk -B-", "Sluggish -S-", "No Reaction -N-", "Eyes Closed -C-", "Normal Reaction" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        jPanel12111.add(jComboBoxReaction, gridBagConstraints);
        jPanel12111.add(jSeparator1, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        jPanel12111.add(jLabel122, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel12111, gridBagConstraints);

        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Extremeties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jPanel42.setLayout(new java.awt.GridBagLayout());

        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Arm Check", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel43.setLayout(new java.awt.GridBagLayout());

        jLabel123.setText("Right Arm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel43.add(jLabel123, gridBagConstraints);

        jComboBoxRightArm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "NP=Normal Power", "W=Weakness", "NR=No Response" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel43.add(jComboBoxRightArm, gridBagConstraints);

        jLabel124.setText("Left Arm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel43.add(jLabel124, gridBagConstraints);

        jComboBoxLeftArm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "NP=Normal Power", "W=Weakness", "NR=No Response" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel43.add(jComboBoxLeftArm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel42.add(jPanel43, gridBagConstraints);

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leg Check", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel45.setLayout(new java.awt.GridBagLayout());

        jComboBoxRightLeg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "NP=Normal Power", "W=Weakness", "NR=No Response" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel45.add(jComboBoxRightLeg, gridBagConstraints);

        jComboBoxLeftLeg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "NP=Normal Power", "W=Weakness", "NR=No Response" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel45.add(jComboBoxLeftLeg, gridBagConstraints);

        jLabel125.setText("Left Leg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel45.add(jLabel125, gridBagConstraints);

        jLabel126.setText("Right Leg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel45.add(jLabel126, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel42.add(jPanel45, gridBagConstraints);

        jTabbedPane3.setForeground(new java.awt.Color(0, 0, 204));

        jPanel46.setLayout(new java.awt.GridBagLayout());

        jLabel127.setText("Corneal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jLabel127, gridBagConstraints);

        jLabel128.setText("GAG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jLabel128, gridBagConstraints);

        jLabel129.setText("Babinski");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jLabel129, gridBagConstraints);

        jLabel130.setText("Breathing Pattern");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jLabel130, gridBagConstraints);

        jLabel132.setText("Seizure Activity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jLabel132, gridBagConstraints);

        jComboBoxCorneal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P=Present", "A=Absent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jComboBoxCorneal, gridBagConstraints);

        jComboBoxGag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P=Present", "A=Absent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jComboBoxGag, gridBagConstraints);

        jComboBoxBabinski.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P=Present", "A=Absent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jComboBoxBabinski, gridBagConstraints);

        jComboBoxBreathingPattern.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tachypnea", "Apnea", "Gasping", "Cheyne-Strokes", "Normal" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jComboBoxBreathingPattern, gridBagConstraints);

        jComboBoxSeizureActivity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Focal", "Psychomotor Temporal Lobe", "Atonic -or- Akinetic", "Petit Mal", "Grand Mal", "Status Epilepticus", "Normal" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel46.add(jComboBoxSeizureActivity, gridBagConstraints);

        jTabbedPane3.addTab("Reflexes", jPanel46);

        jPanel47.setLayout(new java.awt.GridBagLayout());

        seizureactivity.setText("Seizure Activity");
        seizureactivity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seizureactivityItemStateChanged(evt);
            }
        });
        seizureactivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seizureactivityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel47.add(seizureactivity, gridBagConstraints);

        breathingpattern.setText("Breathing Pattern ");
        breathingpattern.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breathingpatternItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel47.add(breathingpattern, gridBagConstraints);

        consciousnesterm.setText("Consciousnes Terms");
        consciousnesterm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                consciousnestermItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel47.add(consciousnesterm, gridBagConstraints);

        selecttermcmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selecttermcmbItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel47.add(selecttermcmb, gridBagConstraints);

        descriptiontxarea.setColumns(20);
        descriptiontxarea.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        descriptiontxarea.setRows(5);
        jScrollPane9.setViewportView(descriptiontxarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel47.add(jScrollPane9, gridBagConstraints);

        jTabbedPane3.addTab("Descriptive Terms", jPanel47);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel42.add(jTabbedPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel42, gridBagConstraints);

        labelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel.setLayout(new java.awt.GridBagLayout());

        imgPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel.add(jLabel134, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(imgPanel, gridBagConstraints);

        actionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel.setLayout(new java.awt.GridBagLayout());

        newAction.setMnemonic('w');
        newAction.setText("Save");
        newAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(newAction, gridBagConstraints);

        updateAction.setMnemonic('U');
        updateAction.setText("Edit");
        updateAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(updateAction, gridBagConstraints);

        deleteAction.setMnemonic('D');
        deleteAction.setText("Delete");
        deleteAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(deleteAction, gridBagConstraints);

        clearAction.setMnemonic('C');
        clearAction.setText("Clear");
        clearAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(clearAction, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel.add(spacerPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel.add(actionsPanel, gridBagConstraints);

        helpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel.setLayout(new java.awt.GridBagLayout());

        helpAction.setMnemonic('H');
        helpAction.setText("Help");
        helpAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionActionPerformed(evt);
            }
        });
        helpPanel.add(helpAction, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(helpPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(labelPanel, gridBagConstraints);

        systemicAssesment.addTab("Neurological Observations", jPanel14);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oxygen Saturation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel9.setLayout(new java.awt.GridBagLayout());

        roomairradio.setText("Room Air");
        roomairradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomairradioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(roomairradio, gridBagConstraints);

        oxygenradio.setText("Oxygen Administration");
        oxygenradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oxygenradioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(oxygenradio, gridBagConstraints);

        roomairtxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(roomairtxt, gridBagConstraints);

        jLabel70.setText("(%)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jLabel70, gridBagConstraints);

        panelhide.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelhide.setLayout(new java.awt.GridBagLayout());

        jLabel68.setText("Mode of Administration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(jLabel68, gridBagConstraints);

        modeofadmincmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mask", "Prongs", "Hood", "Y-Piece" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(modeofadmincmb, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(saturationtxt, gridBagConstraints);

        jLabel71.setText("(L/Min)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(jLabel71, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(litrestxt, gridBagConstraints);

        jLabel69.setText("Saturation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelhide.add(jLabel69, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 8.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(panelhide, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 7.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabel75.setText("Airway");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(jLabel75, gridBagConstraints);

        jLabel76.setText("Respiration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(jLabel76, gridBagConstraints);

        jLabel77.setText("Breathing Sounds");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(jLabel77, gridBagConstraints);

        airwaycmb.setEditable(true);
        airwaycmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "patent", "strido", "obstructed" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(airwaycmb, gridBagConstraints);

        respirationcmb.setEditable(true);
        respirationcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Easy", "Dyspoea", "Tachypnoea", "Shallow" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(respirationcmb, gridBagConstraints);

        breathingsoundscmb.setEditable(true);
        breathingsoundscmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Diminished", "Wheezes", "Crackles", "Clear", "Not ausculated" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(breathingsoundscmb, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 7.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jPanel20, gridBagConstraints);

        labelPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel6.setLayout(new java.awt.GridBagLayout());

        imgPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel6.add(jLabel163, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel6.add(imgPanel6, gridBagConstraints);

        actionsPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel7.setLayout(new java.awt.GridBagLayout());

        newAction7.setMnemonic('w');
        newAction7.setText("Save");
        newAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel7.add(newAction7, gridBagConstraints);

        updateAction6.setMnemonic('U');
        updateAction6.setText("Update");
        updateAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel7.add(updateAction6, gridBagConstraints);

        deleteAction6.setMnemonic('D');
        deleteAction6.setText("Delete");
        deleteAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAction6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel7.add(deleteAction6, gridBagConstraints);

        clearAction7.setMnemonic('C');
        clearAction7.setText("Clear");
        clearAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel7.add(clearAction7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel7.add(spacerPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel6.add(actionsPanel7, gridBagConstraints);

        helpPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel6.setLayout(new java.awt.GridBagLayout());

        helpAction6.setMnemonic('H');
        helpAction6.setText("Help");
        helpAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction6ActionPerformed(evt);
            }
        });
        helpPanel6.add(helpAction6, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel6.add(helpPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel8.add(labelPanel6, gridBagConstraints);

        systemicAssesment.addTab("Respiratory", jPanel8);

        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadiovascular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel48.setLayout(new java.awt.GridBagLayout());

        labelPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel7.setLayout(new java.awt.GridBagLayout());

        imgPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel7.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel7.add(jLabel162, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel7.add(imgPanel7, gridBagConstraints);

        actionsPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel8.setLayout(new java.awt.GridBagLayout());

        saveCadiovascular.setMnemonic('w');
        saveCadiovascular.setText("Save");
        saveCadiovascular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCadiovascularActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel8.add(saveCadiovascular, gridBagConstraints);

        updateAction7.setMnemonic('U');
        updateAction7.setText("Edit");
        updateAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel8.add(updateAction7, gridBagConstraints);

        clearAction8.setMnemonic('C');
        clearAction8.setText("Clear");
        clearAction8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel8.add(clearAction8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel8.add(spacerPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel7.add(actionsPanel8, gridBagConstraints);

        helpPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel7.setLayout(new java.awt.GridBagLayout());

        helpAction7.setMnemonic('H');
        helpAction7.setText("Help");
        helpAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction7ActionPerformed(evt);
            }
        });
        helpPanel7.add(helpAction7, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel7.add(helpPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel48.add(labelPanel7, gridBagConstraints);

        jLabel2.setText("Extremities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel48.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Pallor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel48.add(jLabel4, gridBagConstraints);

        pollorcmb.setEditable(true);
        pollorcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel48.add(pollorcmb, gridBagConstraints);

        extremitiescmb.setEditable(true);
        extremitiescmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Warm", "Cold" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel48.add(extremitiescmb, gridBagConstraints);

        jPanel55.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pulse", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel55.setLayout(new java.awt.GridBagLayout());

        jLabel165.setText("Pulse Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel55.add(jLabel165, gridBagConstraints);

        txtPulseRate1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPulseRate1CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel55.add(txtPulseRate1, gridBagConstraints);

        regularchk.setText("Regular");
        regularchk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regularchkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel55.add(regularchk, gridBagConstraints);

        irregularchk.setText("Irregular");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.5;
        jPanel55.add(irregularchk, gridBagConstraints);

        txtPulseOximity1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPulseOximity1CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel55.add(txtPulseOximity1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        jPanel55.add(pulseOximityTxt1, gridBagConstraints);

        pulseRateProgessBar2.setBackground(java.awt.SystemColor.info);
        pulseRateProgessBar2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        pulseRateProgessBar2.setForeground(java.awt.Color.green);
        pulseRateProgessBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pulseRateProgessBar2.setBorderPainted(false);
        pulseRateProgessBar2.setFocusable(false);
        pulseRateProgessBar2.setString("");
        pulseRateProgessBar2.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel55.add(pulseRateProgessBar2, gridBagConstraints);

        jLabel98.setText("Pulse Oximetry");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel55.add(jLabel98, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel48.add(jPanel55, gridBagConstraints);

        systemicAssesment.addTab("Cadiovascular", jPanel48);

        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gastrointestinal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel49.setLayout(new java.awt.GridBagLayout());

        jLabel164.setText("Abdomen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(jLabel164, gridBagConstraints);

        others.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        others.setForeground(new java.awt.Color(204, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(others, gridBagConstraints);

        otherstxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(otherstxt, gridBagConstraints);

        labelPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel8.setLayout(new java.awt.GridBagLayout());

        imgPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel8.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel8.add(jLabel166, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel8.add(imgPanel8, gridBagConstraints);

        actionsPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel9.setLayout(new java.awt.GridBagLayout());

        saveGastrointestinal.setMnemonic('w');
        saveGastrointestinal.setText("Save");
        saveGastrointestinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGastrointestinalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel9.add(saveGastrointestinal, gridBagConstraints);

        updateAction8.setMnemonic('U');
        updateAction8.setText("Edit");
        updateAction8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel9.add(updateAction8, gridBagConstraints);

        clearAction9.setMnemonic('C');
        clearAction9.setText("Clear");
        clearAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel9.add(clearAction9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel9.add(spacerPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel8.add(actionsPanel9, gridBagConstraints);

        helpPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel8.setLayout(new java.awt.GridBagLayout());

        helpAction8.setMnemonic('H');
        helpAction8.setText("Help");
        helpAction8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction8ActionPerformed(evt);
            }
        });
        helpPanel8.add(helpAction8, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel8.add(helpPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel49.add(labelPanel8, gridBagConstraints);

        jLabel6.setText("Bowel Sounds");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Feeding");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(jLabel7, gridBagConstraints);

        feedingcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Oral", "NG Tube", "Gastronomy", "NPO", "Parenteral" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(feedingcmb, gridBagConstraints);

        bowlsoundcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Present", "Absent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(bowlsoundcmb, gridBagConstraints);

        abdomencmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Soft", "Distended", "Tender" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(abdomencmb, gridBagConstraints);

        jLabel8.setText("Abdominal Stoma");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel49.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel49.add(yeslabel, gridBagConstraints);

        vomitingcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Not Vomiting", "Projectile Vomiting", "Non-Projectile Vomiting" }));
        vomitingcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vomitingcmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(vomitingcmb, gridBagConstraints);

        jLabel10.setText("Vomiting");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel49.add(jLabel10, gridBagConstraints);

        abdomenstatuscmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Present", "Abscent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(abdomenstatuscmb, gridBagConstraints);

        yescmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Coffee Ground", "Frank Blood", "Bile Content", "Faecal Content", "Others" }));
        yescmb.setEnabled(false);
        yescmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yescmbItemStateChanged(evt);
            }
        });
        yescmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yescmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel49.add(yescmb, gridBagConstraints);

        txtAbdomenStatus.setColumns(20);
        txtAbdomenStatus.setRows(5);
        txtAbdomenStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comment on Abdomen Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jScrollPane7.setViewportView(txtAbdomenStatus);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel49.add(jScrollPane7, gridBagConstraints);

        systemicAssesment.addTab("Gastro-intestinal", jPanel49);

        genitolurinarypanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gento-Urinary System", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        genitolurinarypanel.setLayout(new java.awt.GridBagLayout());

        jLabel167.setText("External Genitalia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel.add(jLabel167, gridBagConstraints);

        labelPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel9.setLayout(new java.awt.GridBagLayout());

        imgPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel9.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel9.add(jLabel168, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel9.add(imgPanel9, gridBagConstraints);

        actionsPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel10.setLayout(new java.awt.GridBagLayout());

        saveGenitourinary.setMnemonic('w');
        saveGenitourinary.setText("Save");
        saveGenitourinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGenitourinaryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel10.add(saveGenitourinary, gridBagConstraints);

        updateAction9.setMnemonic('U');
        updateAction9.setText("Edit");
        updateAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel10.add(updateAction9, gridBagConstraints);

        clearAction10.setMnemonic('C');
        clearAction10.setText("Clear");
        clearAction10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel10.add(clearAction10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel10.add(spacerPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel9.add(actionsPanel10, gridBagConstraints);

        helpPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel9.setLayout(new java.awt.GridBagLayout());

        helpAction9.setMnemonic('H');
        helpAction9.setText("Help");
        helpAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction9ActionPerformed(evt);
            }
        });
        helpPanel9.add(helpAction9, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel9.add(helpPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel.add(labelPanel9, gridBagConstraints);

        jLabel11.setText("Urine Output");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel.add(jLabel11, gridBagConstraints);

        urineoutputcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Present", "Absent" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel.add(urineoutputcmb, gridBagConstraints);

        externalcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal ", "Abnormal" }));
        externalcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                externalcmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel.add(externalcmb, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Female Only", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jPanel6.setLayout(new java.awt.GridBagLayout());

        lmpnaturecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Regular", "Irregular", " " }));
        lmpnaturecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lmpnaturecmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel6.add(lmpnaturecmb, gridBagConstraints);

        jLabel12.setText("LMP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel6.add(jLabel12, gridBagConstraints);

        jLabel14.setText("Menarch");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel6.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Nature");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        jPanel6.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel6.add(menarch, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel6.add(lmpdate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel.add(jPanel6, gridBagConstraints);

        abnormalitytxtarea.setEditable(false);
        abnormalitytxtarea.setColumns(20);
        abnormalitytxtarea.setRows(5);
        abnormalitytxtarea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Specify The Abnormality", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jScrollPane2.setViewportView(abnormalitytxtarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel.add(jScrollPane2, gridBagConstraints);

        urineoutputtxtarea.setColumns(20);
        urineoutputtxtarea.setRows(5);
        urineoutputtxtarea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add your comment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jScrollPane3.setViewportView(urineoutputtxtarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel.add(jScrollPane3, gridBagConstraints);

        systemicAssesment.addTab("Genito-Urinary System", genitolurinarypanel);

        genitolurinarypanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Extremities", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        genitolurinarypanel1.setLayout(new java.awt.GridBagLayout());

        jLabel169.setText("Deformities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel1.add(jLabel169, gridBagConstraints);

        labelPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel10.setLayout(new java.awt.GridBagLayout());

        imgPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel10.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel10.add(jLabel170, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel10.add(imgPanel10, gridBagConstraints);

        actionsPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel11.setLayout(new java.awt.GridBagLayout());

        extremitiesSave.setMnemonic('w');
        extremitiesSave.setText("Save");
        extremitiesSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extremitiesSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel11.add(extremitiesSave, gridBagConstraints);

        updateAction10.setMnemonic('U');
        updateAction10.setText("Edit");
        updateAction10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel11.add(updateAction10, gridBagConstraints);

        clearAction11.setMnemonic('C');
        clearAction11.setText("Clear");
        clearAction11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel11.add(clearAction11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel11.add(spacerPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel10.add(actionsPanel11, gridBagConstraints);

        helpPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel10.setLayout(new java.awt.GridBagLayout());

        helpAction10.setMnemonic('H');
        helpAction10.setText("Help");
        helpAction10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction10ActionPerformed(evt);
            }
        });
        helpPanel10.add(helpAction10, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel10.add(helpPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel1.add(labelPanel10, gridBagConstraints);

        jLabel16.setText("Move all Extremities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel1.add(jLabel16, gridBagConstraints);

        moveallextremitiescmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Abnormal" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel1.add(moveallextremitiescmb, gridBagConstraints);

        deformitycmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "NO", "YES" }));
        deformitycmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deformitycmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        genitolurinarypanel1.add(deformitycmb, gridBagConstraints);

        deformitytxtarea.setEditable(false);
        deformitytxtarea.setColumns(20);
        deformitytxtarea.setRows(5);
        deformitytxtarea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comment On  The Deformity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jScrollPane4.setViewportView(deformitytxtarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel1.add(jScrollPane4, gridBagConstraints);

        extremitycommenttxtarea.setColumns(20);
        extremitycommenttxtarea.setRows(5);
        extremitycommenttxtarea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add your comment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(153, 0, 0))); // NOI18N
        jScrollPane5.setViewportView(extremitycommenttxtarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        genitolurinarypanel1.add(jScrollPane5, gridBagConstraints);

        systemicAssesment.addTab("MusculoSkeletal", genitolurinarypanel1);

        jPanel28.setMinimumSize(new java.awt.Dimension(576, 386));
        jPanel28.setName(""); // NOI18N
        jPanel28.setPreferredSize(new java.awt.Dimension(576, 386));
        jPanel28.setLayout(new java.awt.GridBagLayout());

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Integrity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel29.setLayout(new java.awt.GridBagLayout());

        lessionschk.setText("Lessions");
        lessionschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessionschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(lessionschk, gridBagConstraints);

        infestationschk.setText("Infestations");
        infestationschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infestationschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(infestationschk, gridBagConstraints);

        bruiseschk.setText("Bruises");
        bruiseschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bruiseschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(bruiseschk, gridBagConstraints);

        rasheschk.setText("Rashes");
        rasheschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rasheschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(rasheschk, gridBagConstraints);

        scarschk.setText("Scars");
        scarschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scarschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(scarschk, gridBagConstraints);

        burnschk.setText("Burns");
        burnschk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burnschkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(burnschk, gridBagConstraints);

        pressuresorechk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pressuresorechk.setText("Pressure Sore (also updates the Bed/Pressure sore report)");
        pressuresorechk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressuresorechkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(pressuresorechk, gridBagConstraints);

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel30.setLayout(new java.awt.GridBagLayout());

        notestxtarea.setColumns(20);
        notestxtarea.setLineWrap(true);
        notestxtarea.setRows(7);
        jScrollPane14.setViewportView(notestxtarea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel30.add(jScrollPane14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(jPanel30, gridBagConstraints);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel29.add(jSeparator4, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel29.add(jPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel29, gridBagConstraints);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Appearance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel32.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("Color");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jLabel13, gridBagConstraints);

        jLabel59.setText("Skin Tugor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jLabel59, gridBagConstraints);

        colorcmb.setEditable(true);
        colorcmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pallor", "Jaundice", "Cyanosis", "Pink/Normal" }));
        colorcmb.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                colorcmbComponentAdded(evt);
            }
        });
        colorcmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                colorcmbItemStateChanged(evt);
            }
        });
        colorcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorcmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(colorcmb, gridBagConstraints);

        skintugor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Abnormal" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(skintugor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel32, gridBagConstraints);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel33.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Save Patient Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton1, gridBagConstraints);

        jButton6.setText("Edit data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton6, gridBagConstraints);

        jButton8.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel33, gridBagConstraints);

        systemicAssesment.addTab("Integumentary", jPanel28);

        turningChart.setLayout(new java.awt.GridBagLayout());

        turningchart.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 255), 2, true), "Turning Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 0, 255))); // NOI18N
        turningchart.setLayout(new java.awt.GridBagLayout());

        jLabel102.setText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        turningchart.add(jLabel102, gridBagConstraints);

        jLabel531111.setForeground(new java.awt.Color(255, 0, 51));
        jLabel531111.setText("Remarks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        turningchart.add(jLabel531111, gridBagConstraints);

        jButton57.setText("Report");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        turningchart.add(jButton57, gridBagConstraints);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane16.setViewportView(jTextArea2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        turningchart.add(jScrollPane16, gridBagConstraints);

        jComboBox2.setEditable(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Left Lateral", "Right Lateral", "Sopine", "Prone" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        turningchart.add(jComboBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        turningChart.add(turningchart, gridBagConstraints);

        labelPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel11.setLayout(new java.awt.GridBagLayout());

        imgPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel11.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel11.add(jLabel159, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel11.add(imgPanel11, gridBagConstraints);

        actionsPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel14.setLayout(new java.awt.GridBagLayout());

        newAction10.setMnemonic('w');
        newAction10.setText("Save");
        newAction10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel14.add(newAction10, gridBagConstraints);

        updateAction13.setMnemonic('U');
        updateAction13.setText("Edit");
        updateAction13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel14.add(updateAction13, gridBagConstraints);

        clearAction14.setMnemonic('C');
        clearAction14.setText("Clear");
        clearAction14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel14.add(clearAction14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel14.add(spacerPanel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel11.add(actionsPanel14, gridBagConstraints);

        helpPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel11.setLayout(new java.awt.GridBagLayout());

        helpAction11.setMnemonic('H');
        helpAction11.setText("Help");
        helpAction11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction11ActionPerformed(evt);
            }
        });
        helpPanel11.add(helpAction11, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel11.add(helpPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        turningChart.add(labelPanel11, gridBagConstraints);

        pressureSorejPanel.setEnabled(false);
        pressureSorejPanel.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Risk Score", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("10-14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel1, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("At Risk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel20, gridBagConstraints);

        jLabel22.setText("15-19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel22, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("High Risk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel23, gridBagConstraints);

        jLabel24.setText("20 & Above");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel24, gridBagConstraints);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Very High Risk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel25, gridBagConstraints);

        jRadioButton1.setEnabled(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setEnabled(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jRadioButton2, gridBagConstraints);

        jRadioButton3.setEnabled(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jRadioButton3, gridBagConstraints);

        jLabel18.setText("Below 10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel18, gridBagConstraints);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jLabel44, gridBagConstraints);

        jRadioButton4.setEnabled(false);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 0);
        jPanel10.add(jRadioButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 5.0;
        pressureSorejPanel.add(jPanel10, gridBagConstraints);

        labelPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel1.setLayout(new java.awt.GridBagLayout());

        imgPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel1.add(jLabel38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel1.add(imgPanel1, gridBagConstraints);

        actionsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel1.setLayout(new java.awt.GridBagLayout());

        newAction1.setMnemonic('w');
        newAction1.setText("Save");
        newAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(newAction1, gridBagConstraints);

        updateAction1.setMnemonic('U');
        updateAction1.setText("Sore Assessment");
        updateAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(updateAction1, gridBagConstraints);

        clearAction1.setMnemonic('C');
        clearAction1.setText("Clear");
        clearAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel1.add(clearAction1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel1.add(spacerPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel1.add(actionsPanel1, gridBagConstraints);

        helpPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel1.setLayout(new java.awt.GridBagLayout());

        helpAction1.setMnemonic('H');
        helpAction1.setText("Help");
        helpAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction1ActionPerformed(evt);
            }
        });
        helpPanel1.add(helpAction1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel1.add(helpPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        pressureSorejPanel.add(labelPanel1, gridBagConstraints);

        pressureSoreInnerPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assessment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        pressureSoreInnerPane.setLayout(new java.awt.GridBagLayout());

        jLabel26.setText("General Physical Conditions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel26, gridBagConstraints);

        jLabel27.setText("Body Build");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pressureSoreInnerPane.add(jLabel27, gridBagConstraints);

        jLabel28.setText("Skin Integrity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel28, gridBagConstraints);

        jLabel29.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pressureSoreInnerPane.add(jLabel29, gridBagConstraints);

        jLabel30.setText("Nutrition");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel30, gridBagConstraints);

        jLabel31.setText("Mobility");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel31, gridBagConstraints);

        jLabel32.setText("Continence");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pressureSoreInnerPane.add(jLabel32, gridBagConstraints);

        jLabel33.setText("Level Of Consciousness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pressureSoreInnerPane.add(jLabel33, gridBagConstraints);

        jLabel34.setText("Medication");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel34, gridBagConstraints);

        jLabel35.setText("Total Scored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jLabel35, gridBagConstraints);

        jComboBoxGeneralCond.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0- Good", "1- Fair", "2- Poor" }));
        jComboBoxGeneralCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGeneralCondActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxGeneralCond, gridBagConstraints);

        jComboBoxSkinIntegrity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0- Healthy (well nourished)", "4- Unhealthy (Dry,Oedematious,Cold and Clammy,Discoloured)", "6- Single sore (Broken skin,blistered)", "8-Multiple sore, flaps" }));
        jComboBoxSkinIntegrity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSkinIntegrityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxSkinIntegrity, gridBagConstraints);

        jComboBoxNutrition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0-Good Appetite(Able To Feed)", "2-Poor Appetite(Unable To Feed)", "2-NGT/TPN", "4-NBM/Anorexic/Fluids Only" }));
        jComboBoxNutrition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNutritionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxNutrition, gridBagConstraints);

        jComboBoxMobility.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0-Fully Mobile", "3-Restricted Movement(Transactions/Restraints)", "4-Moves Only With Assistance e.g (Weakness on one side)", "6-Immobile(Para/Quadriplegia,Coma)" }));
        jComboBoxMobility.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMobilityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxMobility, gridBagConstraints);

        jComboBoxBodyBuild.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0- Normal/ Average", "4- Emaciated", "5 - Obese" }));
        jComboBoxBodyBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBodyBuildActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxBodyBuild, gridBagConstraints);

        jComboBoxAge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0-(0-13years)", "1 - (14 - 44 years)", "2 - (45 - 64 years)", "4 - (65+ years)" }));
        jComboBoxAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAgeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxAge, gridBagConstraints);

        jComboBoxContinence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0- (Complete Continence/Catheter Intact)", "1- (Occasional Incontinence {0-2 times in 24 hrs} )", "3- (Incontinence of Faeces)", "4- (Double Incontinence {Urine and Faeces})" }));
        jComboBoxContinence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContinenceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxContinence, gridBagConstraints);

        jComboBoxConsciousness.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0 - (Alert {Responds Readily})", "1 - (Lethargic {Slow to Respond})", "2 - (Semi-Conscious)", "3 - (Coma {No Response to Stimuli}) " }));
        jComboBoxConsciousness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsciousnessActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxConsciousness, gridBagConstraints);

        jComboBoxMedication.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "2 - (High close anti-inflamatory drugs)", "4 - (Steroids/Cycotoxic/Anti-coagulants/Radiotherapy)", "NONE" }));
        jComboBoxMedication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMedicationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(jComboBoxMedication, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        pressureSoreInnerPane.add(riskScoreTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        pressureSorejPanel.add(pressureSoreInnerPane, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("General Nursing");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        underground.setBackground(new java.awt.Color(204, 255, 204));
        underground.setLayout(new java.awt.GridBagLayout());

        jSplitPane3.setDividerSize(20);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setOneTouchExpandable(true);

        header.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        header.setForeground(new java.awt.Color(0, 0, 204));
        header.setLayout(new java.awt.GridBagLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Here......", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabel93.setText("Patient No/Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel17.add(jLabel93, gridBagConstraints);

        jPanel44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel44.setLayout(new java.awt.GridBagLayout());

        nameNoTxt.setEditable(false);
        nameNoTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                nameNoTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel44.add(nameNoTxt, gridBagConstraints);

        searchButton4.setText("....");
        searchButton4.setToolTipText("Search");
        searchButton4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton4.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton4.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel44.add(searchButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel17.add(jPanel44, gridBagConstraints);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel17.add(jPanel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel17.add(headerDatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        header.add(jPanel17, gridBagConstraints);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patients Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel18.setForeground(new java.awt.Color(0, 0, 204));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        patientDetailsTextArea.setEditable(false);
        patientDetailsTextArea.setColumns(4);
        patientDetailsTextArea.setForeground(new java.awt.Color(255, 0, 0));
        patientDetailsTextArea.setLineWrap(true);
        patientDetailsTextArea.setRows(3);
        patientDetailsTextArea.setTabSize(5);
        patientDetailsTextArea.setText("\n");
        patientDetailsTextArea.setWrapStyleWord(true);
        patientDetailsTextArea.setAutoscrolls(false);
        patientDetailsTextArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane19.setViewportView(patientDetailsTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        jPanel18.add(jScrollPane19, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("SYSTEMIC ASSESMENT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton2, gridBagConstraints);

        jButton4.setText("PATIENT CARD");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton4, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT HISTORY", "VIEW HISTORY", "ADD HISTORY" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jComboBox1, gridBagConstraints);

        jButton13.setText("Patient Charts");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        header.add(jPanel18, gridBagConstraints);

        jSplitPane3.setLeftComponent(header);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setDividerLocation(160);

        jPanel25.setBackground(new java.awt.Color(153, 255, 153));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Patient's latest Check-up", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel25.setLayout(new java.awt.GridBagLayout());

        jScrollPane24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane24.setPreferredSize(new java.awt.Dimension(167, 138));

        signstable.setBackground(new java.awt.Color(238, 238, 238));
        signstable.setFont(new java.awt.Font("Droid Serif", 1, 11)); // NOI18N
        signstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Temp (c)", null},
                {"Diastolic", null},
                {"Systolic", null},
                {"RR ", null},
                {"PR", null},
                {"Blood Sugar", null}
            },
            new String [] {
                "Detail", "Results"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        signstable.setPreferredSize(new java.awt.Dimension(45, 95));
        signstable.setShowHorizontalLines(false);
        jScrollPane24.setViewportView(signstable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.34;
        jPanel25.add(jScrollPane24, gridBagConstraints);

        fluidbalance.setBackground(new java.awt.Color(238, 238, 238));
        fluidbalance.setFont(new java.awt.Font("Droid Serif", 1, 11)); // NOI18N
        fluidbalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Accum Input ", null},
                {"Accum Output  ", null},
                {"Accum Balance ", null}
            },
            new String [] {
                "Details", "Results"
            }
        ));
        fluidbalance.setShowHorizontalLines(false);
        jScrollPane25.setViewportView(fluidbalance);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.19;
        jPanel25.add(jScrollPane25, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("Fluid Balance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel25.add(jLabel9, gridBagConstraints);

        jPanel34.setBackground(new java.awt.Color(153, 255, 153));
        jPanel34.setLayout(new java.awt.GridBagLayout());

        jLabel106.setText("last Patient Turn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        jPanel34.add(jLabel106, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel25.add(jPanel34, gridBagConstraints);

        jPanel35.setLayout(new java.awt.GridBagLayout());
        jPanel25.add(jPanel35, new java.awt.GridBagConstraints());

        jSplitPane1.setLeftComponent(jPanel25);

        nursingTabbedPane.setForeground(new java.awt.Color(0, 102, 51));
        nursingTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        nursingTabbedPane.setMinimumSize(new java.awt.Dimension(400, 300));
        nursingTabbedPane.setPreferredSize(new java.awt.Dimension(400, 300));
        nursingTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nursingTabbedPaneStateChanged(evt);
            }
        });
        nursingTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nursingTabbedPaneMouseClicked(evt);
            }
        });

        occupancy.setLayout(new java.awt.GridBagLayout());

        jPanel39.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel39.add(jSeparator3, gridBagConstraints);

        occupancyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                occupancyTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(occupancyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel39.add(jScrollPane11, gridBagConstraints);

        occupyno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        occupyno.setText("SHOW NO OF TRAN,REC/OCC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel39.add(occupyno, gridBagConstraints);

        occpancygroup.add(currentOccupRbtn);
        currentOccupRbtn.setText("Occupancy");
        currentOccupRbtn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                currentOccupRbtnItemStateChanged(evt);
            }
        });
        currentOccupRbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentOccupRbtnMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel39.add(currentOccupRbtn, gridBagConstraints);

        urgencycmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Patient Urgency", "All", "Emergency", "Normal" }));
        urgencycmbx.setOpaque(false);
        urgencycmbx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                urgencycmbxItemStateChanged(evt);
            }
        });
        urgencycmbx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                urgencycmbxMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel39.add(urgencycmbx, gridBagConstraints);

        occpancygroup.add(theatreChbx);
        theatreChbx.setText("Theatre Patients");
        theatreChbx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                theatreChbxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel39.add(theatreChbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        occupancy.add(jPanel39, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setBackground(java.awt.Color.pink);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setText("TRIAGED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        occupancy.add(jTextField2, gridBagConstraints);

        jTextField9.setEditable(false);
        jTextField9.setBackground(java.awt.Color.green);
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField9.setText("LAB RESULTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        occupancy.add(jTextField9, gridBagConstraints);

        jTextField11.setEditable(false);
        jTextField11.setBackground(java.awt.Color.magenta);
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField11.setText("X-RAY RESULTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        occupancy.add(jTextField11, gridBagConstraints);

        jTextField3.setEditable(false);
        jTextField3.setBackground(java.awt.Color.cyan);
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.setText("CLERKED by Dr.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        occupancy.add(jTextField3, gridBagConstraints);

        nursingTabbedPane.addTab("Occupancy", occupancy);

        systemicReview.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setFont(new java.awt.Font("Vani", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox1.setText("Systemic Assement");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel7.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setFont(new java.awt.Font("Vani", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox2.setText("Pressure Sore");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel7.add(jCheckBox2, gridBagConstraints);

        jCheckBox3.setFont(new java.awt.Font("Vani", 1, 12)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox3.setText("Turning Chart");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel7.add(jCheckBox3, gridBagConstraints);

        jCheckBox4.setFont(new java.awt.Font("Vani", 1, 12)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox4.setText("Vital Signs");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel7.add(jCheckBox4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        systemicReview.add(jPanel7, gridBagConstraints);

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/l2fprod/gui/plaf/skin/icons/nursing.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        home.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        systemicReview.add(home, gridBagConstraints);

        nursingTabbedPane.addTab("Systemic  Review", systemicReview);

        nurseReport.setLayout(new java.awt.GridBagLayout());

        nursescardex.setBackground(new java.awt.Color(204, 255, 204));
        nursescardex.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nursescardex.setLayout(new java.awt.GridBagLayout());

        cardexTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        cardexTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        requestsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane28.setViewportView(requestsTable);

        cardexTabbedPane.addTab("Doctor's Requests", jScrollPane28);

        cardexTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select urgency,transaction_time,cardex,nurse_name from nursing.nurse_cardex where patient_no='"+nameNoTxt.getText()+"' AND visit_id='"+visitId+"' "));
        cardexTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardexTblMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(cardexTbl);

        cardexTabbedPane.addTab("Nursing Cardex", jScrollPane15);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        nursescardex.add(cardexTabbedPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 8.0;
        nurseReport.add(nursescardex, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        nursesRptCardex.setColumns(20);
        nursesRptCardex.setRows(5);
        nursesRptCardex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nursesRptCardexKeyPressed(evt);
            }
        });
        jScrollPane21.setViewportView(nursesRptCardex);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel22.add(jScrollPane21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        nurseReport.add(jPanel22, gridBagConstraints);

        actionsPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel6.setLayout(new java.awt.GridBagLayout());

        newAction6.setMnemonic('w');
        newAction6.setText("Save");
        newAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel6.add(newAction6, gridBagConstraints);

        clearAction6.setMnemonic('C');
        clearAction6.setText("Clear");
        clearAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel6.add(clearAction6, gridBagConstraints);

        newAction12.setMnemonic('w');
        newAction12.setText("General Billing");
        newAction12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel6.add(newAction12, gridBagConstraints);

        newAction13.setMnemonic('w');
        newAction13.setText("Print Cardex");
        newAction13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel6.add(newAction13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        nurseReport.add(actionsPanel6, gridBagConstraints);

        jCheckBox5.setText("Alarming Notes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        nurseReport.add(jCheckBox5, gridBagConstraints);

        nursingTabbedPane.addTab("Nurses Cardex", nurseReport);

        fluidChartTabbed.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        fluidChartTabbed.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fluidChartTabbed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fluidChartTabbedMouseClicked(evt);
            }
        });

        fluidchart.setBackground(new java.awt.Color(240, 242, 241));
        fluidchart.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intake", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 255))); // NOI18N
        fluidchart.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        fluidchart.setLayout(new java.awt.GridBagLayout());

        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intravenous", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(153, 0, 102))); // NOI18N
        jPanel59.setLayout(new java.awt.GridBagLayout());

        jComboBoxIvType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Hartman's solution", "Sodium Chloride 0.9%(Normal saline)", "Dextrose 5%", "Dextrose 10%", "Glucose with sodium Chloride(DNS)", "Haemacel 3.5%", "Mannitol 20%", "Half strength Darrow's solution", "Others" }));
        jComboBoxIvType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxIvTypeItemStateChanged(evt);
            }
        });
        jComboBoxIvType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIvTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jComboBoxIvType, gridBagConstraints);

        jLabel202.setText("AMOUNT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel202, gridBagConstraints);

        jLabel203.setText("RATE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel203, gridBagConstraints);

        jLabel204.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel204, gridBagConstraints);

        jLabel205.setText("IV TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel205, gridBagConstraints);

        jLabel206.setText("ml/hr");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel206, gridBagConstraints);

        jLabel208.setText("Time IV Started");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jLabel208, gridBagConstraints);

        iv_amount.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        iv_amount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                iv_amountStateChanged(evt);
            }
        });
        iv_amount.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                iv_amountHierarchyChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(iv_amount, gridBagConstraints);

        ivRate.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        ivRate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ivRateStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(ivRate, gridBagConstraints);

        iv_instructions.setColumns(20);
        iv_instructions.setForeground(new java.awt.Color(51, 102, 255));
        iv_instructions.setRows(3);
        iv_instructions.setText("Instructions for intravenous infusion");
        iv_instructions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iv_instructionsMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(iv_instructions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel59.add(jScrollPane12, gridBagConstraints);

        txtTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimeMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel59.add(txtTime, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        fluidchart.add(jPanel59, gridBagConstraints);

        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(153, 0, 102))); // NOI18N
        jPanel60.setLayout(new java.awt.GridBagLayout());

        jLabel210.setText("ORAL TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jLabel210, gridBagConstraints);

        jLabel211.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jLabel211, gridBagConstraints);

        jComboBoxOral_type.setEditable(true);
        jComboBoxOral_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Soup", "Uji", "Tea", "Milk" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jComboBoxOral_type, gridBagConstraints);

        jLabel212.setText("AMOUNT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jLabel212, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(oral_amount, gridBagConstraints);

        oral_fluids_instructions.setColumns(20);
        oral_fluids_instructions.setForeground(new java.awt.Color(51, 102, 255));
        oral_fluids_instructions.setRows(3);
        oral_fluids_instructions.setText("Instructions for Oral Fluid");
        oral_fluids_instructions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oral_fluids_instructionsMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(oral_fluids_instructions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel60.add(jScrollPane17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 6.0;
        fluidchart.add(jPanel60, gridBagConstraints);

        feedpane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direct Feed", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(153, 0, 102))); // NOI18N
        feedpane1.setLayout(new java.awt.GridBagLayout());

        jLabel214.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(jLabel214, gridBagConstraints);

        jLabel216.setText("PEG FEED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(jLabel216, gridBagConstraints);

        nasofeed1.setText("NASO GASTRIC FEED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(nasofeed1, gridBagConstraints);

        jLabel217.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(jLabel217, gridBagConstraints);

        naso_gastric.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(naso_gastric, gridBagConstraints);

        peg_feed.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        feedpane1.add(peg_feed, gridBagConstraints);

        naso_instructions.setColumns(20);
        naso_instructions.setForeground(new java.awt.Color(51, 102, 255));
        naso_instructions.setRows(3);
        naso_instructions.setText("Instructions for NASOGASTRIC SUCTION");
        naso_instructions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                naso_instructionsMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(naso_instructions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        feedpane1.add(jScrollPane18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        fluidchart.add(feedpane1, gridBagConstraints);

        labelPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel12.setLayout(new java.awt.GridBagLayout());

        imgPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel12.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel12.add(jLabel238, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel12.add(imgPanel12, gridBagConstraints);

        actionsPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel12.setLayout(new java.awt.GridBagLayout());

        newAction8.setMnemonic('w');
        newAction8.setText("Save");
        newAction8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel12.add(newAction8, gridBagConstraints);

        clearAction12.setMnemonic('C');
        clearAction12.setText("Clear");
        clearAction12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel12.add(clearAction12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel12.add(spacerPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel12.add(actionsPanel12, gridBagConstraints);

        helpPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel12.setLayout(new java.awt.GridBagLayout());

        helpAction12.setMnemonic('H');
        helpAction12.setText("Help");
        helpAction12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction12ActionPerformed(evt);
            }
        });
        helpPanel12.add(helpAction12, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel12.add(helpPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        fluidchart.add(labelPanel12, gridBagConstraints);

        totalTxt.setEditable(false);
        totalTxt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        totalTxt.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fluidchart.add(totalTxt, gridBagConstraints);

        jLabel215.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        fluidchart.add(jLabel215, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setText("VIEW TOTAL INPUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        fluidchart.add(jButton3, gridBagConstraints);

        fluidChartTabbed.addTab("Register Intake (c.c 's)", fluidchart);

        jPanel61.setBackground(new java.awt.Color(240, 242, 241));
        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel61.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jPanel61.setLayout(new java.awt.GridBagLayout());

        jPanel62.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Urine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(153, 0, 102))); // NOI18N
        jPanel62.setLayout(new java.awt.GridBagLayout());

        jLabel219.setText("Catheter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(jLabel219, gridBagConstraints);

        jLabel220.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(jLabel220, gridBagConstraints);

        wetbedchk1.setText("Wet Bed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(wetbedchk1, gridBagConstraints);

        wetdiaperchk1.setText("Wet Diaper");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(wetdiaperchk1, gridBagConstraints);

        visittoiletchk1.setText("Visited Toilet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(visittoiletchk1, gridBagConstraints);

        urine_amount.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(urine_amount, gridBagConstraints);

        urine_amount1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(urine_amount1, gridBagConstraints);

        jLabel229.setText("Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(jLabel229, gridBagConstraints);

        jLabel230.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel62.add(jLabel230, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(jPanel62, gridBagConstraints);

        jPanel63.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drain", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(153, 0, 102))); // NOI18N
        jPanel63.setLayout(new java.awt.GridBagLayout());

        jLabel221.setText("No. of Drains");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(jLabel221, gridBagConstraints);

        lblDrain1.setText("Drain 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(lblDrain1, gridBagConstraints);

        lblDrain2.setText("Drain 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(lblDrain2, gridBagConstraints);

        lblDrain3.setText("Drain 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(lblDrain3, gridBagConstraints);

        lblDrain4.setText("Drain 4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(lblDrain4, gridBagConstraints);

        lblDrain5.setText("Drain 5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(lblDrain5, gridBagConstraints);

        drainscmb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5" }));
        drainscmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drainscmb1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(drainscmb1, gridBagConstraints);

        mls1.setText("mls");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel63.add(mls1, gridBagConstraints);

        mls2.setText("mls");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel63.add(mls2, gridBagConstraints);

        mls3.setText("mls");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel63.add(mls3, gridBagConstraints);

        mls4.setText("mls");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel63.add(mls4, gridBagConstraints);

        mls5.setText("mls");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel63.add(mls5, gridBagConstraints);

        drain1txt1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(drain1txt1, gridBagConstraints);

        drain2txt1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel63.add(drain2txt1, gridBagConstraints);

        drain5txt1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(drain5txt1, gridBagConstraints);

        drain3txt1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(drain3txt1, gridBagConstraints);

        drain4txt1.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel63.add(drain4txt1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel63.add(caption1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel63.add(caption2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel63.add(caption3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel63.add(caption4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel63.add(caption5, gridBagConstraints);

        drainCaption.setForeground(new java.awt.Color(0, 0, 255));
        drainCaption.setText("Drain Caption");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel63.add(drainCaption, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(jPanel63, gridBagConstraints);

        resty1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drain", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(153, 0, 102))); // NOI18N
        resty1.setLayout(new java.awt.GridBagLayout());

        ngSuction.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(ngSuction, gridBagConstraints);

        vomitus.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(vomitus, gridBagConstraints);

        jLabel232.setText("NG Suction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jLabel232, gridBagConstraints);

        jLabel233.setText("Vomitus");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jLabel233, gridBagConstraints);

        jLabel234.setText("Stool");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jLabel234, gridBagConstraints);

        jCheckBoxStoolYes.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jCheckBoxStoolYes, gridBagConstraints);

        jLabel236.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jLabel236, gridBagConstraints);

        jLabel237.setText("ml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jLabel237, gridBagConstraints);

        jCheckBoxStoolNo.setText("No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        resty1.add(jCheckBoxStoolNo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(resty1, gridBagConstraints);

        labelPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        labelPanel13.setLayout(new java.awt.GridBagLayout());

        imgPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        imgPanel13.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel13.add(jLabel239, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel13.add(imgPanel13, gridBagConstraints);

        actionsPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        actionsPanel13.setLayout(new java.awt.GridBagLayout());

        newAction9.setMnemonic('w');
        newAction9.setText("Save");
        newAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel13.add(newAction9, gridBagConstraints);

        updateAction12.setMnemonic('U');
        updateAction12.setText("Edit");
        updateAction12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAction12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel13.add(updateAction12, gridBagConstraints);

        clearAction13.setMnemonic('C');
        clearAction13.setText("Clear");
        clearAction13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAction13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel13.add(clearAction13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel13.add(spacerPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel13.add(actionsPanel13, gridBagConstraints);

        helpPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        helpPanel13.setLayout(new java.awt.GridBagLayout());

        helpAction13.setMnemonic('H');
        helpAction13.setText("Help");
        helpAction13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAction13ActionPerformed(evt);
            }
        });
        helpPanel13.add(helpAction13, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel13.add(helpPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel61.add(labelPanel13, gridBagConstraints);

        totalTxt1.setEditable(false);
        totalTxt1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        totalTxt1.setForeground(new java.awt.Color(255, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel61.add(totalTxt1, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 0));
        jButton5.setText("VIEW TOTAL OUTPUT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel61.add(jButton5, gridBagConstraints);

        fluidChartTabbed.addTab("Register Output (c.c.'s)", jPanel61);

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

        headerDatePicker3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                headerDatePicker3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                headerDatePicker3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                headerDatePicker3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerDatePicker3MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel11.add(headerDatePicker3, gridBagConstraints);

        jButton7.setText("ok");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel11.add(jButton7, gridBagConstraints);

        jButton10.setText("FLUID REPORT");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel11.add(jButton10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        Accumulatives.add(jPanel11, gridBagConstraints);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monitor Intakes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jScrollPane22.setViewportView(jTable6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jScrollPane22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        Accumulatives.add(jPanel13, gridBagConstraints);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monitor Outputs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jScrollPane23.setViewportView(jTable7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel19.add(jScrollPane23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        Accumulatives.add(jPanel19, gridBagConstraints);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Others", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 153, 153))); // NOI18N
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

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Urine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 153, 153))); // NOI18N
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

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Intravenous Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 153, 153))); // NOI18N
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

        fluidChartTabbed.addTab("Cumulative Fluid Intake/Output", Accumulatives);

        jPanel64.setLayout(new java.awt.GridBagLayout());

        packkCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select ", "My Current Patient", "All Patient" }));
        packkCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                packkComboItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel64.add(packkCombo, gridBagConstraints);

        packTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, " +
            "       date_transfused, done_by, ward,'Confirmed' as confirmation, date_recorded, " +
            "       blood_type" +
            "  FROM nursing.blood_pack_verification where confirmation=true;"));
    packTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            packTableMouseClicked(evt);
        }
    });
    jScrollPane30.setViewportView(packTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel64.add(jScrollPane30, gridBagConstraints);

    confimedRbtn.setText("Confirmed Pack");
    confimedRbtn.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            confimedRbtnItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel64.add(confimedRbtn, gridBagConstraints);

    nonConfirmedRbt.setText("Non Confirmed");
    nonConfirmedRbt.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            nonConfirmedRbtItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel64.add(nonConfirmedRbt, gridBagConstraints);

    bloodpackbtn.setText("Confirm Pack");
    bloodpackbtn.setEnabled(false);
    bloodpackbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bloodpackbtnActionPerformed(evt);
        }
    });
    jPanel64.add(bloodpackbtn, new java.awt.GridBagConstraints());

    jCheckBox6.setSelected(true);
    jCheckBox6.setText(".");
    jCheckBox6.setEnabled(false);
    jCheckBox6.setOpaque(false);
    jCheckBox6.setVisible(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
    jPanel64.add(jCheckBox6, gridBagConstraints);

    fluidChartTabbed.addTab("Pack Information", jPanel64);

    bloodTransfusionMainPane.setPreferredSize(new java.awt.Dimension(977, 344));
    bloodTransfusionMainPane.setLayout(new java.awt.GridBagLayout());

    labelPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    labelPanel15.setLayout(new java.awt.GridBagLayout());

    imgPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    imgPanel2.setLayout(new java.awt.GridBagLayout());

    jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif"))); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    imgPanel2.add(jLabel39, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel15.add(imgPanel2, gridBagConstraints);

    actionsPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
    actionsPanel2.setLayout(new java.awt.GridBagLayout());

    bloodtransfusionbtn.setMnemonic('w');
    bloodtransfusionbtn.setText("SAVE");
    bloodtransfusionbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bloodtransfusionbtnActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
    actionsPanel2.add(bloodtransfusionbtn, gridBagConstraints);

    clearbloodtransfusion.setMnemonic('C');
    clearbloodtransfusion.setText("Clear");
    clearbloodtransfusion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearbloodtransfusionActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel2.add(clearbloodtransfusion, gridBagConstraints);

    jTextArea4.setBackground(new java.awt.Color(232, 232, 233));
    jTextArea4.setColumns(20);
    jTextArea4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
    jTextArea4.setForeground(new java.awt.Color(255, 10, 12));
    jTextArea4.setRows(5);
    jTextArea4.setText("\n\nHOURS OF OBSERVATION\nBefore Transfusion\n       00 Mins\n       15 Mins\n       45 Mins\n   1Hr 15 Mins\n   1Hr 45 Mins\n   2Hr 15 Mins\n   2Hr 45 Mins\n   3Hr 15 Mins\n   3Hr 45 Mins\n   4Hr 15 Mins\n   4Hr After Blood Transfusion");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel2.add(jTextArea4, gridBagConstraints);

    jButton11.setText("TRANSFUSION REPORT");
    jButton11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton11ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
    actionsPanel2.add(jButton11, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    labelPanel15.add(actionsPanel2, gridBagConstraints);

    helpPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
    helpPanel2.setLayout(new java.awt.GridBagLayout());

    helpAction2.setMnemonic('H');
    helpAction2.setText("Help");
    helpAction2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            helpAction2ActionPerformed(evt);
        }
    });
    helpPanel2.add(helpAction2, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel15.add(helpPanel2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 7;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.2;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionMainPane.add(labelPanel15, gridBagConstraints);

    jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTabbedPane2MouseClicked(evt);
        }
    });

    bloodtransfusionpane.setMinimumSize(new java.awt.Dimension(400, 300));
    bloodtransfusionpane.setPreferredSize(new java.awt.Dimension(400, 300));
    bloodtransfusionpane.setLayout(new java.awt.GridBagLayout());

    bloodTransfusionCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(51, 102, 255))); // NOI18N
    bloodTransfusionCenter.setLayout(new java.awt.GridBagLayout());

    endtime.setEditable(false);
    endtime.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            endtimeMouseClicked(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionCenter.add(endtime, gridBagConstraints);

    remarks.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REMARKS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 0, 12), new java.awt.Color(0, 0, 254))); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionCenter.add(remarks, gridBagConstraints);

    endtransfusion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    endtransfusion.setText("End Transfusion");
    endtransfusion.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            endtransfusionItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    bloodTransfusionCenter.add(endtransfusion, gridBagConstraints);

    jPanel36.setLayout(new java.awt.GridBagLayout());

    jLabel209.setText("EXACT TIME");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel209, gridBagConstraints);

    jLabel213.setText("BP");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel213, gridBagConstraints);

    jLabel218.setText("PR");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel218, gridBagConstraints);

    exacttime.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            exacttimeMouseClicked(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(exacttime, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(bp, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(pr, gridBagConstraints);

    jLabel161.setText("Rate of Transfusion(ml)");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel161, gridBagConstraints);

    jLabel227.setText("TEMP(Celsius)");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel227, gridBagConstraints);

    jLabel228.setText("RR");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 0.5;
    jPanel36.add(jLabel228, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(rr, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(temp, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel36.add(hourlyrate, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionCenter.add(jPanel36, gridBagConstraints);

    confirmationdetails.setEditable(false);
    confirmationdetails.setColumns(20);
    confirmationdetails.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
    confirmationdetails.setRows(5);
    confirmationdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Blood Pack Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 0, 10), new java.awt.Color(255, 10, 12))); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 2.0;
    gridBagConstraints.weighty = 2.0;
    bloodTransfusionCenter.add(confirmationdetails, gridBagConstraints);

    amounttxtfld.setEditable(false);
    amounttxtfld.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
    amounttxtfld.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amnt Transfused", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10), new java.awt.Color(255, 0, 51))); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionCenter.add(amounttxtfld, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    bloodtransfusionpane.add(bloodTransfusionCenter, gridBagConstraints);

    dataPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sign Off By", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    dataPanel2.setMinimumSize(new java.awt.Dimension(52, 50));
    dataPanel2.setPreferredSize(new java.awt.Dimension(52, 50));
    dataPanel2.setLayout(new java.awt.GridBagLayout());

    jLabel156.setText("Name :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    dataPanel2.add(jLabel156, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.5;
    dataPanel2.add(signedby, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    bloodtransfusionpane.add(dataPanel2, gridBagConstraints);

    jPanel37.setLayout(new java.awt.GridBagLayout());

    bloodTransfusionUpper.setBackground(new java.awt.Color(243, 240, 240));
    bloodTransfusionUpper.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfusion Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
    bloodTransfusionUpper.setForeground(new java.awt.Color(0, 0, 204));
    bloodTransfusionUpper.setLayout(new java.awt.GridBagLayout());

    jLabel37.setText("Date Of Transfusion");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    bloodTransfusionUpper.add(jLabel37, gridBagConstraints);

    jLabel40.setText("Type of Blood Transfused");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 0.5;
    bloodTransfusionUpper.add(jLabel40, gridBagConstraints);

    jLabel41.setText("Blood Unit Donor No:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    bloodTransfusionUpper.add(jLabel41, gridBagConstraints);

    packno.setEnabled(false);
    packno.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            packnoActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionUpper.add(packno, gridBagConstraints);

    bloodtype.setEditable(false);
    bloodtype.setBackground(new java.awt.Color(255, 255, 255));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionUpper.add(bloodtype, gridBagConstraints);

    transfusiondate.setEditable(false);
    transfusiondate.setBackground(new java.awt.Color(255, 255, 255));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionUpper.add(transfusiondate, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel37.add(bloodTransfusionUpper, gridBagConstraints);

    jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "**********", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
    jPanel38.setLayout(new java.awt.GridBagLayout());

    jLabel42.setText("Transfusion Started By");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 0.5;
    jPanel38.add(jLabel42, gridBagConstraints);

    jLabel43.setText("Counter Checked By");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 0.5;
    jPanel38.add(jLabel43, gridBagConstraints);

    jLabel45.setText("Rate Of Transfusion(mL/min)");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    jPanel38.add(jLabel45, gridBagConstraints);

    starttime.setEditable(false);
    starttime.setBackground(new java.awt.Color(255, 255, 255));
    starttime.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            starttimeMouseClicked(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel38.add(starttime, gridBagConstraints);

    transfusionrate.setEditable(false);
    transfusionrate.setBackground(new java.awt.Color(255, 255, 255));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel38.add(transfusionrate, gridBagConstraints);

    startedby.setEditable(false);
    startedby.setBackground(new java.awt.Color(255, 255, 255));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel38.add(startedby, gridBagConstraints);

    countercheck.setEditable(false);
    countercheck.setBackground(new java.awt.Color(255, 255, 255));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel38.add(countercheck, gridBagConstraints);

    starttransfusion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    starttransfusion.setText("Start Transfusion");
    starttransfusion.addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentShown(java.awt.event.ComponentEvent evt) {
            starttransfusionComponentShown(evt);
        }
    });
    starttransfusion.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            starttransfusionItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    jPanel38.add(starttransfusion, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel37.add(jPanel38, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    bloodtransfusionpane.add(jPanel37, gridBagConstraints);

    jTabbedPane2.addTab("Blood Obseravations", bloodtransfusionpane);

    jPanel16.setLayout(new java.awt.GridBagLayout());

    jTabbedPane4.setForeground(new java.awt.Color(0, 0, 204));
    jTabbedPane4.setMinimumSize(new java.awt.Dimension(200, 212));
    jTabbedPane4.setPreferredSize(new java.awt.Dimension(200, 290));
    jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTabbedPane4MouseClicked(evt);
        }
    });

    jScrollPane1.setPreferredSize(new java.awt.Dimension(375, 100));

    observationchart.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ));
    observationchart.setEnabled(false);
    jScrollPane1.setViewportView(observationchart);

    jTabbedPane4.addTab("OBSERVATION CHART", jScrollPane1);

    bloodTransfusionLower.setMinimumSize(new java.awt.Dimension(100, 120));
    bloodTransfusionLower.setPreferredSize(new java.awt.Dimension(100, 120));
    bloodTransfusionLower.setLayout(new java.awt.GridBagLayout());

    jLabel48.setText("General");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel48, gridBagConstraints);

    jLabel49.setText("Dermatological");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel49, gridBagConstraints);

    jLabel50.setText("Cardiac/ Respiratory");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel50, gridBagConstraints);

    jLabel51.setText("Renal");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel51, gridBagConstraints);

    jLabel52.setText("Haematological");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel52, gridBagConstraints);

    jLabel54.setText("Others");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(jLabel54, gridBagConstraints);

    general.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Fever", "Chill/Rigors", "Flushing", "Nausea/Vomiting" }));
    general.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(general, gridBagConstraints);

    dermatological.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Urticaria", "Skin Rash" }));
    dermatological.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(dermatological, gridBagConstraints);

    cardaic.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Chest Pain", "Dyspnoea", "Hypotension", "Tachycardia" }));
    cardaic.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(cardaic, gridBagConstraints);

    renal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Haemoglobinuria", "Oliguria", "anuria" }));
    renal.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(renal, gridBagConstraints);

    haemotological.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Unexplained Bleeding" }));
    haemotological.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(haemotological, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionLower.add(othersymptoms, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    bloodTransfusionLower.add(symptomstime, gridBagConstraints);

    jLabel207.setText("TIME OBSERVED");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    bloodTransfusionLower.add(jLabel207, gridBagConstraints);

    symptomscheck.setBackground(new java.awt.Color(242, 239, 239));
    symptomscheck.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
    symptomscheck.setForeground(new java.awt.Color(255, 10, 12));
    symptomscheck.setText("SELECT SYMPTOMS TO ENTER THEN SAVE  OR DESELECT TO CANCEL");
    symptomscheck.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            symptomscheckItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 4;
    bloodTransfusionLower.add(symptomscheck, gridBagConstraints);

    jTabbedPane4.addTab("Symptoms/Signs Of Transfusion Reactions Observed", bloodTransfusionLower);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    jPanel16.add(jTabbedPane4, gridBagConstraints);

    jTabbedPane2.addTab("Blood Symptoms", jPanel16);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 5;
    gridBagConstraints.gridheight = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    bloodTransfusionMainPane.add(jTabbedPane2, gridBagConstraints);

    fluidChartTabbed.addTab("Blood Transfusion", bloodTransfusionMainPane);

    nursingTabbedPane.addTab("Fluid Chart", fluidChartTabbed);

    nursingCare.setMinimumSize(new java.awt.Dimension(385, 250));
    nursingCare.setPreferredSize(new java.awt.Dimension(385, 250));
    nursingCare.setLayout(new java.awt.GridBagLayout());

    nursingcareTab.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IMPLEMENTED NURSING CARE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(204, 0, 0))); // NOI18N
    nursingcareTab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
    nursingcareTab.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            nursingcareTabMouseClicked(evt);
        }
    });

    nursingCare1.setMinimumSize(new java.awt.Dimension(385, 250));
    nursingCare1.setPreferredSize(new java.awt.Dimension(385, 250));
    nursingCare1.setLayout(new java.awt.GridBagLayout());

    nursingcareplan.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 255), 2, true), "Enter Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 0, 255))); // NOI18N
    nursingcareplan.setLayout(new java.awt.GridBagLayout());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    nursingcareplan.add(jSeparator11, gridBagConstraints);

    jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    nursingcareplan.add(jLabel36, gridBagConstraints);

    jSplitPane2.setDividerLocation(180);

    jButton9.setText("Implement");
    jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
        }
    });
    jSplitPane2.setLeftComponent(jButton9);

    jButton523.setText("Report");
    jButton523.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jButton523.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton523ActionPerformed(evt);
        }
    });
    jSplitPane2.setRightComponent(jButton523);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
    nursingcareplan.add(jSplitPane2, gridBagConstraints);

    nursingcaretable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null}
        },
        new String [] {
            "Diagnosis", "Expected Outcome", "Plan of Care", "Intervetion", "Rationale", "Evaluation"
        }
    ));
    jScrollPane6.setViewportView(nursingcaretable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    nursingcareplan.add(jScrollPane6, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    nursingCare1.add(nursingcareplan, gridBagConstraints);

    labelPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    labelPanel14.setLayout(new java.awt.GridBagLayout());

    imgPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    imgPanel14.setLayout(new java.awt.GridBagLayout());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    imgPanel14.add(jLabel241, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel14.add(imgPanel14, gridBagConstraints);

    actionsPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
    actionsPanel15.setLayout(new java.awt.GridBagLayout());

    newAction11.setMnemonic('w');
    newAction11.setText("Save");
    newAction11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            newAction11ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel15.add(newAction11, gridBagConstraints);

    clearAction15.setMnemonic('C');
    clearAction15.setText("Clear");
    clearAction15.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAction15ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel15.add(clearAction15, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 5.0;
    actionsPanel15.add(spacerPanel15, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    labelPanel14.add(actionsPanel15, gridBagConstraints);

    helpPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
    helpPanel14.setLayout(new java.awt.GridBagLayout());

    helpAction14.setMnemonic('H');
    helpAction14.setText("Help");
    helpAction14.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            helpAction14ActionPerformed(evt);
        }
    });
    helpPanel14.add(helpAction14, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel14.add(helpPanel14, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 7;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 2.0;
    nursingCare1.add(labelPanel14, gridBagConstraints);

    nursingcareTab.addTab("IMPLEMENTING NURSING CARE", nursingCare1);

    jPanel65.setLayout(new java.awt.GridBagLayout());

    implementedCareTable.setModel(new javax.swing.table.DefaultTableModel(
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
    jScrollPane31.setViewportView(implementedCareTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel65.add(jScrollPane31, gridBagConstraints);

    nursingcareTab.addTab("IMPLEMENTED NURSING CARE", jPanel65);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    nursingCare.add(nursingcareTab, gridBagConstraints);

    nursingTabbedPane.addTab("Nursing Care", nursingCare);

    preOperativeList.setLayout(new java.awt.GridBagLayout());

    preOperativeList1.setLayout(new java.awt.GridBagLayout());

    jPanel12112.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    jPanel12112.setLayout(new java.awt.GridBagLayout());

    facilityidLbl11412.setText("Albumin");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(facilityidLbl11412, gridBagConstraints);

    jLabel139.setText("Bladder Check & Urinary Catheter");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(jLabel139, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(bladdercheckTxt, gridBagConstraints);

    jLabel140.setText("Blood Sugar");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(jLabel140, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(urinalysisTxt, gridBagConstraints);

    jLabel141.setText("I.V (Drips)");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(jLabel141, gridBagConstraints);

    jLabel142.setText("Urinalysis");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(jLabel142, gridBagConstraints);

    jLabel143.setText("Gastric tube");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(jLabel143, gridBagConstraints);
    jPanel12112.add(jSeparator2, new java.awt.GridBagConstraints());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(ivdripsTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(bloodsugarTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(gastricTxt, gridBagConstraints);

    albuminTxt.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            albuminTxtActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel12112.add(albuminTxt, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    preOperativeList1.add(jPanel12112, gridBagConstraints);

    jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    jPanel50.setLayout(new java.awt.GridBagLayout());

    jLabel144.setText("Operation");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.3;
    jPanel50.add(jLabel144, gridBagConstraints);

    operationstxt.setEditable(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.3;
    jPanel50.add(operationstxt, gridBagConstraints);

    preopgearsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            { null, null, null, null, null},

        },
        new String [] {
            "Dentures", "Jewellery", "Wigs", "Preparation", "Others"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            true, true, true, true, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    dentures = new javax.swing.JComboBox();

    dentures.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT 'YES' UNION SELECT 'NO') ORDER BY 1"));

    javax.swing.table.TableColumn seditor11 = this.preopgearsTable.getColumn("Dentures");

    seditor11.setCellEditor(new javax.swing.DefaultCellEditor(dentures));

    Jewellery= new javax.swing.JComboBox();

    Jewellery.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT 'YES' UNION SELECT 'NO') ORDER BY 1"));

    javax.swing.table.TableColumn seditor12 = this.preopgearsTable.getColumn("Jewellery");

    seditor12.setCellEditor(new javax.swing.DefaultCellEditor(Jewellery));

    Wigs= new javax.swing.JComboBox();

    Wigs.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT 'YES' UNION SELECT 'NO') ORDER BY 1"));

    javax.swing.table.TableColumn seditor13 = this.preopgearsTable.getColumn("Wigs");

    seditor13.setCellEditor(new javax.swing.DefaultCellEditor(Wigs));

    Preparation= new javax.swing.JComboBox();

    Preparation.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT 'YES' UNION SELECT 'NO') ORDER BY 1"));

    javax.swing.table.TableColumn seditor14 = this.preopgearsTable.getColumn("Preparation");

    seditor14.setCellEditor(new javax.swing.DefaultCellEditor(Preparation));

    Preparation= new javax.swing.JComboBox();

    Preparation.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT 'YES' UNION SELECT 'NO') ORDER BY 1"));

    javax.swing.table.TableColumn seditor15 = this.preopgearsTable.getColumn("Preparation");

    seditor15.setCellEditor(new javax.swing.DefaultCellEditor(Preparation));
    jScrollPane27.setViewportView(preopgearsTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.2;
    jPanel50.add(jScrollPane27, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    preOperativeList1.add(jPanel50, gridBagConstraints);

    jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(153, 0, 0))); // NOI18N
    jPanel51.setLayout(new java.awt.GridBagLayout());

    jLabel146.setText("X-Rays");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel146, gridBagConstraints);

    jLabel147.setText("Blood Available(ltrs)");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel147, gridBagConstraints);

    jLabel148.setText("Certified By");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel148, gridBagConstraints);

    jLabel149.setText("Date & Time");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel149, gridBagConstraints);

    jLabel150.setText("Date & Time");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel150, gridBagConstraints);

    jLabel151.setText("Has Conscent Been Given?");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel151, gridBagConstraints);

    jLabel152.setText("Ward Nurse");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel152, gridBagConstraints);

    jLabel153.setText("Has Pre-Medication Been Given?");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jLabel153, gridBagConstraints);

    jCheckBox49.setText("No");
    jCheckBox49.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox49ItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jCheckBox49, gridBagConstraints);

    jCheckBox50.setText("Yes");
    jCheckBox50.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox50ItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(jCheckBox50, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(certifiedTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(bloodavailTxt, gridBagConstraints);

    userTxt.setEditable(false);
    userTxt.setText(getUser());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(userTxt, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    jPanel51.add(xraysTxt, gridBagConstraints);

    conscentcomb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "No", "Yes" }));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel51.add(conscentcomb, gridBagConstraints);

    medicationArea.setEditable(false);
    medicationArea.setColumns(20);
    medicationArea.setRows(5);
    premedication.setViewportView(medicationArea);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    jPanel51.add(premedication, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    jPanel51.add(certifiedtime, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    jPanel51.add(wardnursetime, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 6.0;
    gridBagConstraints.weighty = 1.0;
    preOperativeList1.add(jPanel51, gridBagConstraints);

    labelPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    labelPanel4.setLayout(new java.awt.GridBagLayout());

    imgPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    imgPanel4.setLayout(new java.awt.GridBagLayout());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    imgPanel4.add(jLabel154, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel4.add(imgPanel4, gridBagConstraints);

    actionsPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
    actionsPanel4.setLayout(new java.awt.GridBagLayout());

    newAction4.setMnemonic('w');
    newAction4.setText("Save");
    newAction4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            newAction4ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel4.add(newAction4, gridBagConstraints);

    clearAction4.setMnemonic('C');
    clearAction4.setText("Clear");
    clearAction4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAction4ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    actionsPanel4.add(clearAction4, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 5.0;
    actionsPanel4.add(spacerPanel4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 20.0;
    labelPanel4.add(actionsPanel4, gridBagConstraints);

    helpPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
    helpPanel4.setLayout(new java.awt.GridBagLayout());

    helpAction4.setMnemonic('H');
    helpAction4.setText("Help");
    helpAction4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            helpAction4ActionPerformed(evt);
        }
    });
    helpPanel4.add(helpAction4, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    labelPanel4.add(helpPanel4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 8;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    preOperativeList1.add(labelPanel4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    preOperativeList.add(preOperativeList1, gridBagConstraints);

    nursingTabbedPane.addTab("Pre-Operative Checklist", preOperativeList);

    drugAdministration.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Administration of Medication From the Doctor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    drugAdministration.setLayout(new java.awt.GridBagLayout());

    drugAdministration1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N
    drugAdministration1.setLayout(new java.awt.GridBagLayout());

    jPanel41.setLayout(new java.awt.GridBagLayout());

    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
    jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTabbedPane1MouseClicked(evt);
        }
    });

    jPanel56.setLayout(new java.awt.GridBagLayout());

    drugAdministration2.setLayout(new java.awt.GridBagLayout());

    doctorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT false as Receive, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText()+"' and receive=false and administer= false"));
    doctorMed.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            doctorMedMouseClicked(evt);
        }
    });
    jScrollPane10.setViewportView(doctorMed);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 7;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 8.0;
    gridBagConstraints.weighty = 1.0;
    drugAdministration2.add(jScrollPane10, gridBagConstraints);

    newAction5.setMnemonic('w');
    newAction5.setText("RECEIVE PRESCRIBTIONS");
    newAction5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            newAction5ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 1;
    drugAdministration2.add(newAction5, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel56.add(drugAdministration2, gridBagConstraints);

    jTabbedPane1.addTab("Recieve Dr's Requests", jPanel56);

    jPanel4.setLayout(new java.awt.GridBagLayout());

    monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT false as Administer, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText()+"' and receive=true and administer= false"));
    monitorMed.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            monitorMedMouseClicked(evt);
        }
    });
    jScrollPane26.setViewportView(monitorMed);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 7;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 8.0;
    gridBagConstraints.weighty = 1.0;
    jPanel4.add(jScrollPane26, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.weightx = 0.1;
    jPanel4.add(jLabel19, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weighty = 0.1;
    jPanel4.add(jLabel58, gridBagConstraints);

    adminBtn.setText("Administer");
    adminBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            adminBtnActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.weightx = 0.1;
    jPanel4.add(adminBtn, gridBagConstraints);

    doneAdministrationBtn.setText("Drug Done Adminstration ");
    doneAdministrationBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            doneAdministrationBtnActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.weightx = 0.2;
    jPanel4.add(doneAdministrationBtn, gridBagConstraints);

    jTabbedPane1.addTab("Monitor Medication", jPanel4);

    jPanel57.setLayout(new java.awt.GridBagLayout());

    medReport.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT drug_code, date_prescribed, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText()+"'"));
    medReport.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            medReportMouseClicked(evt);
        }
    });
    jScrollPane29.setViewportView(medReport);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 7;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 8.0;
    gridBagConstraints.weighty = 1.0;
    jPanel57.add(jScrollPane29, gridBagConstraints);

    completlyAdm.setText("Drugs completely Administerd ");
    completlyAdm.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            completlyAdmItemStateChanged(evt);
        }
    });
    completlyAdm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            completlyAdmActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    jPanel57.add(completlyAdm, gridBagConstraints);

    currentlyAdmt.setText("Drugs currently under medication");
    currentlyAdmt.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            currentlyAdmtItemStateChanged(evt);
        }
    });
    currentlyAdmt.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            currentlyAdmtActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    jPanel57.add(currentlyAdmt, gridBagConstraints);

    timeAdministered.setText("Times Administered");
    timeAdministered.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            timeAdministeredItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    jPanel57.add(timeAdministered, gridBagConstraints);

    jTabbedPane1.addTab("Medication Report", jPanel57);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel41.add(jTabbedPane1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    drugAdministration1.add(jPanel41, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    drugAdministration.add(drugAdministration1, gridBagConstraints);

    nursingTabbedPane.addTab("Administration of Medication", drugAdministration);

    reportPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    reportPanel.setLayout(new java.awt.GridBagLayout());

    jPanel67.setLayout(new java.awt.GridBagLayout());

    jPanel69.setLayout(new java.awt.GridBagLayout());

    reportbtngroup.add(quadriplegicPatientRdi);
    quadriplegicPatientRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    quadriplegicPatientRdi.setText("Quadriplegic Patient");
    quadriplegicPatientRdi.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            quadriplegicPatientRdiItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(quadriplegicPatientRdi, gridBagConstraints);

    reportbtngroup.add(paraplegicPatientRdi);
    paraplegicPatientRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    paraplegicPatientRdi.setText("paraplegic Patient");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(paraplegicPatientRdi, gridBagConstraints);

    reportbtngroup.add(veryIllPatient);
    veryIllPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    veryIllPatient.setText("Patient very ill");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(veryIllPatient, gridBagConstraints);

    reportbtngroup.add(referredInPatientRdi);
    referredInPatientRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    referredInPatientRdi.setText("Patient referred in");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(referredInPatientRdi, gridBagConstraints);

    reportbtngroup.add(gunshotsPatientRdi);
    gunshotsPatientRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    gunshotsPatientRdi.setText("patient with gunshots");
    gunshotsPatientRdi.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            gunshotsPatientRdiActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(gunshotsPatientRdi, gridBagConstraints);

    reportbtngroup.add(totalNursingPatientRdi);
    totalNursingPatientRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    totalNursingPatientRdi.setText("Patient under total nursing care");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(totalNursingPatientRdi, gridBagConstraints);

    reportbtngroup.add(assistedFeedingRdi);
    assistedFeedingRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    assistedFeedingRdi.setText("patient for assisted feeding");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(assistedFeedingRdi, gridBagConstraints);

    reportbtngroup.add(jRadioButton5);
    jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jRadioButton5.setForeground(new java.awt.Color(204, 0, 0));
    jRadioButton5.setText("Call Received");
    jRadioButton5.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton5ItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(jRadioButton5, gridBagConstraints);

    reportbtngroup.add(jRadioButton6);
    jRadioButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jRadioButton6.setForeground(new java.awt.Color(204, 0, 0));
    jRadioButton6.setText("Porter Arrived");
    jRadioButton6.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton6ItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 8;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(jRadioButton6, gridBagConstraints);

    reportbtngroup.add(jRadioButton7);
    jRadioButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jRadioButton7.setForeground(new java.awt.Color(204, 0, 0));
    jRadioButton7.setText("Patient Sent To Theatre");
    jRadioButton7.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton7ItemStateChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 9;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel69.add(jRadioButton7, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel67.add(jPanel69, gridBagConstraints);

    jPanel70.setLayout(new java.awt.GridBagLayout());

    reportbtngroup.add(patientGuardedRdi);
    patientGuardedRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    patientGuardedRdi.setText("Patient Guarded");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientGuardedRdi, gridBagConstraints);

    reportbtngroup.add(patientMissingRdi);
    patientMissingRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    patientMissingRdi.setText("Patient Missing");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientMissingRdi, gridBagConstraints);

    reportbtngroup.add(patientReactiveRdi);
    patientReactiveRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    patientReactiveRdi.setText("Patient Reactive");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientReactiveRdi, gridBagConstraints);

    reportbtngroup.add(patientDeadRdi);
    patientDeadRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    patientDeadRdi.setText("Patient Dead");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientDeadRdi, gridBagConstraints);

    reportbtngroup.add(patientBreasFeedingRdi);
    patientBreasFeedingRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    patientBreasFeedingRdi.setText("Patient Breastfeeding");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientBreasFeedingRdi, gridBagConstraints);

    reportbtngroup.add(sexuallyAssultedRdi);
    sexuallyAssultedRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    sexuallyAssultedRdi.setText("Patient sexually assaulted");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(sexuallyAssultedRdi, gridBagConstraints);

    reportbtngroup.add(incidenceRdi);
    incidenceRdi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    incidenceRdi.setText("Report An incidence");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(incidenceRdi, gridBagConstraints);

    reportbtngroup.add(patientIsStaffRdi);
    patientIsStaffRdi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    patientIsStaffRdi.setText("Patient is a Staff");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel70.add(patientIsStaffRdi, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel67.add(jPanel70, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.gridheight = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.3;
    reportPanel.add(jPanel67, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    reportPanel.add(jLabel47, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.7;
    reportPanel.add(jLabel57, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.1;
    gridBagConstraints.weighty = 0.5;
    reportPanel.add(jLabel63, gridBagConstraints);

    jPanel68.setLayout(new java.awt.GridBagLayout());

    commentArea.setColumns(20);
    commentArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
    commentArea.setRows(5);
    commentArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Your Comment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10), new java.awt.Color(153, 0, 0))); // NOI18N
    jScrollPane20.setViewportView(commentArea);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.6;
    jPanel68.add(jScrollPane20, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    reportPanel.add(jPanel68, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.2;
    reportPanel.add(jLabel72, gridBagConstraints);

    jPanel71.setLayout(new java.awt.GridBagLayout());

    savePatientReportBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    savePatientReportBtn.setText("SAVE");
    savePatientReportBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            savePatientReportBtnActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel71.add(savePatientReportBtn, gridBagConstraints);

    jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButton12.setText("CLEAR");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel71.add(jButton12, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 0.2;
    reportPanel.add(jPanel71, gridBagConstraints);

    nursingTabbedPane.addTab("Report This Patient", reportPanel);

    billingPanel.setLayout(new java.awt.GridBagLayout());
    if(!src.equalsIgnoreCase("Reg")){

        com.afrisoftech.hospital.GeneralBillingIntfr genBilling = new com.afrisoftech.hospital.GeneralBillingIntfr(connectDB,pConnDB);
        java.awt.GridBagConstraints gridBagConstraintbilling = new java.awt.GridBagConstraints();
        gridBagConstraintbilling.gridx = 0;
        gridBagConstraintbilling.gridy = 0;
        gridBagConstraintbilling.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraintbilling.weightx = 1.0;
        gridBagConstraintbilling.weighty = 1.0;
        billingPanel.add(genBilling.getContentPane(), gridBagConstraintbilling);

        nursingTabbedPane.addTab("Services Posting", billingPanel);
    }

    medicinePanel.setLayout(new java.awt.GridBagLayout());
    if(!src.equalsIgnoreCase("Reg")){

        com.afrisoftech.hospinventory.PatientsBillingIntfr_  medicine = new com.afrisoftech.hospinventory.PatientsBillingIntfr_(connectDB,pConnDB);
        java.awt.GridBagConstraints gridBagConstraintmedicine = new java.awt.GridBagConstraints();
        gridBagConstraintmedicine.gridx = 0;
        gridBagConstraintmedicine.gridy = 0;
        gridBagConstraintmedicine.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraintmedicine.weightx = 1.0;
        gridBagConstraintmedicine.weighty = 1.0;
        medicinePanel.add(medicine.getContentPane(), gridBagConstraintmedicine);

        nursingTabbedPane.addTab("Medicine Posting", medicinePanel);
    }

    jSplitPane1.setRightComponent(nursingTabbedPane);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel2.add(jSplitPane1, gridBagConstraints);

    jSplitPane3.setRightComponent(jPanel2);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    underground.add(jSplitPane3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(underground, gridBagConstraints);

    pack();
    }// </editor-fold>//GEN-END:initComponents
   private void bloodUpdate(String packno) {
        try {
            String updateQuery = "UPDATE nursing.blood_transfusion SET  transfusion_status=? WHERE"
                    + " blood_unit_pack='" + packno + "' and patient_no='" + nameNoTxt.getText().trim().toString().trim() + "' and  visit_id='" + visitId + "' and transfusion_status!='Complete';";

            System.out.println("QUERIES ARE " + updateQuery);
            java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement(updateQuery);
            pstmt31.setObject(1, "Complete");
            pstmt31.executeUpdate();

            String updatePack = "UPDATE nursing.blood_pack_verification   SET  transfusion_state=true,amount='" + amounttxtfld.getText() + "' where "
                    + " pack_no='" + packno + "' and patient_no='" + nameNoTxt.getText().trim().toString().trim() + "' and  visit_id='" + visitId + "' ;";

            System.out.println("QUERIES ARE " + updatePack);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement(updatePack);
            pstmt.executeUpdate();

        } catch (Exception bloodUpdate) {

            System.out.println("blood update error is " + bloodUpdate);
        }

    }

    private boolean bloodConfirmation(String packno) {
        try {

            PreparedStatement prest1 = connectDB.prepareStatement("SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, \n"
                    + "            date_transfused, done_by, ward, confirmation, date_recorded,blood_type,transfusion_state "
                    + "  FROM nursing.blood_pack_verification where ward='" + ward + "'  and  pack_no='" + packno + "' "
                    + "and patient_no='" + nameNoTxt.getText().trim() + "' "
                    + "and visit_id='" + visitId + "';");
            ResultSet rs = prest1.executeQuery();
            rs.next();
            transfusion = rs.getBoolean(13);
            confirmation = rs.getBoolean(10);
            bloodPackExist = true;

            packDetails = "PACK NO :-" + rs.getString(1) + " "
                    + " CONFIRMED " + rs.getString(10) + "\n"
                    + "Pack Details " + rs.getString(2) + "\n"
                    + "Type of Blood " + rs.getString(12) + "\n"
                    + "Date Transfused " + rs.getString(7) + " \n"
                    + "Patient Blood Group Detail " + rs.getString(5) + " " + rs.getString(6) + "";
            bloodtype.setText(rs.getString(12));
            transfusiondate.setText(rs.getString(7));

            System.out.println("your transfusion state is  " + confirmation);
            System.out.println("your blood pack state is  " + bloodPackExist);
        } catch (SQLException caret) {

            bloodPackExist = false;
            System.out.println("your transfusion state is  " + confirmation);
            System.out.println("your blood pack state is  " + bloodPackExist);
            System.out.println("your caret update error in blood verification is " + caret);
        }
        return bloodPackExist;

    }

    private void hideDrains() {
        drain1txt1.setValue(0);
        drain2txt1.setValue(0);
        drain3txt1.setValue(0);
        drain4txt1.setValue(0);
        drain5txt1.setValue(0);

        caption1.hide();
        caption2.hide();
        caption3.hide();
        caption4.hide();
        caption5.hide();
        drainCaption.hide();

        mls1.hide();
        mls2.hide();
        mls3.hide();
        mls4.hide();
        mls5.hide();
        drain1txt1.hide();
        lblDrain1.hide();
        drain2txt1.hide();
        lblDrain2.hide();
        drain3txt1.hide();
        lblDrain3.hide();
        drain4txt1.hide();
        lblDrain4.hide();
        drain5txt1.hide();
        lblDrain5.hide();
    }

    private String getAge(int yr, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
        int dateParts[] = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH};
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.set(yr, month, day); //months are 0-based  
        int diff[] = new int[3];
        if (today.before(birthday)) {
            System.out.println("birthday invalid - date after today, exiting");

        } else {

            for (int i = 2; i >= 0; i--) {
                while (!sdf.format(birthday.getTime()).split(" ")[i].equals(sdf.format(today.getTime()).split(" ")[i])) {
                    birthday.add(dateParts[i], 1);
                    diff[i]++;
                }
            }
        }
        String difference = "" + (diff[0] + " yrs : " + diff[1]) + " mths : " + diff[2] + " days";
        years = diff[0];
        months = diff[1];
        days = diff[2];
        System.out.println(years + " " + months + " " + days);

        return difference;
    }
////check blood glucose

    public void checkBloodGlucoseStatus(javax.swing.JProgressBar progressBar) {
        // tracking the  changes of a progress bar
        if (bloodglucosetxt.getText().equals("")) {
            progressBar.setValue(0);
            progressBar.setString("");
            progressBar.setForeground(Color.white);
            progressBar.setBackground(Color.white);
        } else {
            if (Double.parseDouble(bloodglucosetxt.getText()) < 3.5) {
                progressBar.setIndeterminate(false);
                progressBar.setBackground(new Color(0, 127, 0));
                progressBar.setString("Sub normal");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.black);
                progressBar.setValue(Integer.parseInt(bloodglucosetxt.getText()));
                progressBar.setStringPainted(true);
            } else if (Double.parseDouble(bloodglucosetxt.getText()) >= 3.5 && Double.parseDouble(bloodglucosetxt.getText()) <= 8) {
                // progressBar.setIndeterminate(false);
                progressBar.setBackground(Color.green);
                progressBar.setString("Normal");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.black);
                progressBar.setValue(Integer.parseInt(bloodglucosetxt.getText()));
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            } else if (Double.parseDouble(bloodglucosetxt.getText()) > 8) {
                //progressBar.setIndeterminate(false);
                progressBar.setBackground(Color.yellow);
                progressBar.setString("Above Normal");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.green);
                progressBar.setValue(Integer.parseInt(bloodglucosetxt.getText()));
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            }

        }

    }

    ///check vital signs behaviour
    public void CheckVitalSignsStatus(javax.swing.JProgressBar progressBar) {
        // tracking the  changes of a progress bar
        if (txtSpinner.getText().toString().equals("")) {
            progressBar.setValue(0);
            progressBar.setString("");
            progressBar.setForeground(Color.white);
            progressBar.setBackground(Color.white);
        } else {
            if (Double.parseDouble(txtSpinner.getText()) < 35.6) {
                progressBar.setIndeterminate(false);
                progressBar.setBackground(new Color(0, 127, 0));
                progressBar.setString("Sub normal");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.black);
                progressBar.setValue(Integer.parseInt(txtSpinner.getText()));
                progressBar.setStringPainted(true);
            } else if (Double.parseDouble(txtSpinner.getText()) >= 35.6 && Double.parseDouble(txtSpinner.getText()) <= 37.2) {
                // progressBar.setIndeterminate(false);
                progressBar.setBackground(Color.green);
                progressBar.setString("Normal");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.black);
                progressBar.setValue(Integer.parseInt(txtSpinner.getText()));
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            } else if (Double.parseDouble(txtSpinner.getText()) >= 37.3 && Double.parseDouble(txtSpinner.getText()) <= 38.8) {
                //progressBar.setIndeterminate(false);
                progressBar.setBackground(Color.yellow);
                progressBar.setString("moderate fever");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.green);
                progressBar.setValue(Integer.parseInt(txtSpinner.getText()));
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            } else if (Double.parseDouble(txtSpinner.getText()) >= 38.9 && Double.parseDouble(txtSpinner.getText()) <= 39.9) {
                //progressBar.setIndeterminate(false);
                progressBar.setBackground(Color.yellow);
                progressBar.setString("High fever");
                progressBar.setOpaque(true);
                progressBar.setForeground(Color.red);
                progressBar.setValue(Integer.parseInt(txtSpinner.getText()));
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            } else if (Double.parseDouble(txtSpinner.getText()) >= 40) {
                progressBar.setForeground(Color.red);
                progressBar.setBackground(Color.red);
                progressBar.setString("severe fever");
                progressBar.setValue(Integer.parseInt(txtSpinner.getText()));
                progressBar.setOpaque(true);
                progressBar.setStringPainted(true);
                progressBar.setIndeterminate(true);
            }

        }

    }

    private void searchButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton4ActionPerformed
        patientSearchButtonClicked();
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButton4ActionPerformed

    private int pressure_sore() {
        int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0, total;
        if (jComboBoxGeneralCond.getSelectedIndex() == 0) {
            one = 0;
        } else if (jComboBoxGeneralCond.getSelectedIndex() == 1) {
            one = 0;
        } else if (jComboBoxGeneralCond.getSelectedIndex() == 2) {
            one = 1;
        } else if (jComboBoxGeneralCond.getSelectedIndex() == 3) {
            one = 2;
        }
        //SkinIntegrity
        if (jComboBoxSkinIntegrity.getSelectedIndex() == 0) {
            two = 0;
        } else if (jComboBoxSkinIntegrity.getSelectedIndex() == 1) {
            two = 0;
        } else if (jComboBoxSkinIntegrity.getSelectedIndex() == 2) {
            two = 4;
        } else if (jComboBoxSkinIntegrity.getSelectedIndex() == 3) {
            two = 6;
        } else if (jComboBoxSkinIntegrity.getSelectedIndex() == 4) {
            two = 8;
        }
        //Nutrition
        if (jComboBoxNutrition.getSelectedIndex() == 0) {
            three = 0;
        } else if (jComboBoxNutrition.getSelectedIndex() == 1) {
            three = 0;
        } else if (jComboBoxNutrition.getSelectedIndex() == 2) {
            three = 2;
        } else if (jComboBoxNutrition.getSelectedIndex() == 3) {
            three = 2;
        } else if (jComboBoxNutrition.getSelectedIndex() == 4) {
            three = 4;
        }
        //Mobility
        if (jComboBoxMobility.getSelectedIndex() == 0) {
            four = 0;
        } else if (jComboBoxMobility.getSelectedIndex() == 1) {
            four = 0;
        } else if (jComboBoxMobility.getSelectedIndex() == 2) {
            four = 3;
        } else if (jComboBoxMobility.getSelectedIndex() == 3) {
            four = 4;
        } else if (jComboBoxMobility.getSelectedIndex() == 4) {
            four = 6;
        }
        //Medication
        if (jComboBoxMedication.getSelectedIndex() == 0) {
            five = 0;
        } else if (jComboBoxMedication.getSelectedIndex() == 1) {
            five = 2;
        } else if (jComboBoxMedication.getSelectedIndex() == 2) {
            five = 4;
        }
        //BodyBuild
        if (jComboBoxBodyBuild.getSelectedIndex() == 0) {
            six = 0;
        } else if (jComboBoxBodyBuild.getSelectedIndex() == 1) {
            six = 0;
        } else if (jComboBoxBodyBuild.getSelectedIndex() == 2) {
            six = 4;
        } else if (jComboBoxBodyBuild.getSelectedIndex() == 3) {
            six = 5;
        }

        //Age
        if (jComboBoxAge.getSelectedIndex() == 0) {
            seven = 0;
        } else if (jComboBoxAge.getSelectedIndex() == 1) {
            seven = 1;
        } else if (jComboBoxAge.getSelectedIndex() == 2) {
            seven = 2;
        } else if (jComboBoxAge.getSelectedIndex() == 3) {
            seven = 4;
        }

        //Continence
        if (jComboBoxContinence.getSelectedIndex() == 0) {
            eight = 0;
        } else if (jComboBoxContinence.getSelectedIndex() == 1) {
            eight = 0;
        } else if (jComboBoxContinence.getSelectedIndex() == 2) {
            eight = 1;
        } else if (jComboBoxContinence.getSelectedIndex() == 3) {
            eight = 3;
        } else if (jComboBoxContinence.getSelectedIndex() == 4) {
            eight = 4;
        }

        //Consciousness
        if (jComboBoxConsciousness.getSelectedIndex() == 0) {
            nine = 0;
        } else if (jComboBoxConsciousness.getSelectedIndex() == 1) {
            nine = 0;
        } else if (jComboBoxConsciousness.getSelectedIndex() == 2) {
            nine = 1;
        } else if (jComboBoxConsciousness.getSelectedIndex() == 3) {
            nine = 2;
        } else if (jComboBoxConsciousness.getSelectedIndex() == 4) {
            nine = 3;
        }

        total = one + two + three + four + five + six + seven + eight + nine;

        if (total < 10) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(true);

            jLabel18.setForeground(Color.GREEN);
            jLabel44.setForeground(Color.GREEN);

            jLabel1.setForeground(Color.BLACK);
            jLabel20.setForeground(Color.BLACK);
            jLabel23.setForeground(Color.BLACK);
            jLabel22.setForeground(Color.BLACK);
            jLabel25.setForeground(Color.BLACK);
            jLabel24.setForeground(Color.BLACK);
        } else if (total >= 10 && total <= 14) {
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);

            jLabel18.setForeground(Color.BLACK);
            jLabel44.setForeground(Color.BLACK);

            jLabel1.setForeground(Color.YELLOW);
            jLabel20.setForeground(Color.YELLOW);

            jLabel23.setForeground(Color.BLACK);
            jLabel22.setForeground(Color.BLACK);
            jLabel25.setForeground(Color.BLACK);
            jLabel24.setForeground(Color.BLACK);
        } else if (total >= 15 && total <= 19) {
            jRadioButton2.setSelected(true);
            jRadioButton3.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton4.setSelected(false);

            jLabel18.setForeground(Color.BLACK);
            jLabel44.setForeground(Color.BLACK);
            jLabel23.setForeground(Color.ORANGE);
            jLabel22.setForeground(Color.ORANGE);

            jLabel1.setForeground(Color.BLACK);
            jLabel20.setForeground(Color.BLACK);
            jLabel25.setForeground(Color.BLACK);
            jLabel24.setForeground(Color.BLACK);
        } else if (total >= 20) {
            jRadioButton3.setSelected(true);
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton4.setSelected(false);

            jLabel18.setForeground(Color.BLACK);
            jLabel44.setForeground(Color.BLACK);
            jLabel25.setForeground(Color.red);
            jLabel24.setForeground(Color.red);

            jLabel1.setForeground(Color.BLACK);
            jLabel20.setForeground(Color.BLACK);
            jLabel23.setForeground(Color.BLACK);
            jLabel22.setForeground(Color.BLACK);
        }
//               else if(total<10){
//               jLabel1.setForeground(Color.BLACK);
//                jLabel20.setForeground(Color.BLACK);
//                jLabel23.setForeground(Color.BLACK);
//               jLabel22.setForeground(Color.BLACK);
//               jLabel25.setForeground(Color.BLACK);
//                jLabel24.setForeground(Color.BLACK);
//                
//                jRadioButton1.setSelected(false);
//               jRadioButton2.setSelected(false);
//               jRadioButton3.setSelected(false);
//           }
        return total;
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        com.afrisoftech.reports.emr.MOHPatientCardPdf policy = new com.afrisoftech.reports.emr.MOHPatientCardPdf();
        policy.MOHPatientCardPdf(connectDB, headerDatePicker.getDate(), headerDatePicker.getDate(), nameNoTxt.getText(), "OP");

        com.afrisoftech.reports.PatientCardPdf policy1 = new com.afrisoftech.reports.PatientCardPdf();
        policy1.PatientCardPdf(connectDB, headerDatePicker.getDate(), headerDatePicker.getDate(), nameNoTxt.getText().trim(),false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    private String getUser() {
        String users = null;
        try {
            connectDB.setAutoCommit(false);

            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery("select current_user ");
            while (rse12.next()) {
                ///getting the user
                users = rse12.getObject(1).toString();
            }
        } catch (final Exception es) {
            System.out.println(es);

        }
        return users;
    }

    private void patientSearchButtonClicked() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.nameNoTxt.getLocationOnScreen();

        patientSearch.setSize(400, 200);

        patientSearch.setLocation(point);

        patientSearch.setVisible(true);

    }
    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        if (jTextField113.getText().length() > 5) {
            patientDetailsTextArea.setText("");

            jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                    "SELECT v.patient_no AS Patient_No, v.name AS Name, v.age as patient_age, v.urgency, v.payment From  hp_patient_visit v "
                    + "  where v.date::date>= '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                    + "  AND "
                    + " (v.patient_no ILIKE '%" + jTextField113.getText() + "%' OR v.name ILIKE '%" + jTextField113.getText() + "%') AND v.discharge is null AND input_date::date > now()::date - 3 ORDER BY 1"));
            jSearchTable2.setShowHorizontalLines(false);

            jSearchTable2.getColumnModel().getColumn(0).setPreferredWidth(350);
            jSearchTable2.getColumnModel().getColumn(1).setPreferredWidth(350);

            jSearchScrollPane2.setViewportView(jSearchTable2);
        }
        // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate
    private String populateBioData(String patient_num) {
        String age = null, text = null;
        String title = null;

        try {
            connectDB.setAutoCommit(false);
            //getting age
            java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123 = stm123.executeQuery("select age(year_of_birth::date) from hp_patient_register where patient_no ilike '%" + patient_num + "%'");
            while (rse123.next()) {
                ///getting the user
                age = rse123.getString(1);
                System.out.println("\n\nam executing the age which is " + age + "\n\n");
            }
            //getting details
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery(
                    "Select  hp_patient_visit.patient_no,  hp_patient_visit.name,  "
                    + "   hp_patient_visit.gender, hp_patient_visit.marital_status,  hp_patient_visit.date::varchar,  hp_patient_visit.clinic "
                    + "From  hp_patient_visit "
                    + " where date::date>=( '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'::date-5)"
                    + " and hp_patient_visit.patient_no ilike '%" + patient_num + "%'  ");
            while (rse12.next()) {
                ///getting the user
                System.out.println("\n\nam executing the user\n\n");
//                int year = Integer.parseInt(age.substring(0, 4));
//                int month = Integer.parseInt(age.substring(5, 7));
//                int day = Integer.parseInt(age.substring(8, 10));

                dateAdmitted = rse12.getString(5);

                text = "Names:-" + rse12.getString(2) + "    Age: ".
                        concat(age + "\n Gender: ".
                                concat(rse12.getString(3) + "     Marital Status: ".
                                        concat(rse12.getString(4) + "\n Clinic : ".
                                                concat(rse12.getString(6) + "    Date of Visit: ".
                                                        concat(rse12.getString(5))))));
                System.out.println("\n\nam executing the user and the text is " + text + "\n\n");
                String visit = rse12.getString(5).trim();
                System.out.println("the visit id is now " + visit);
                visitId = visit.replace("-", "");
                System.out.println("the visitID id is now " + visitId);
                gender = rse12.getString(3);

                patient_name = rse12.getString(2);

                title = visitId + " Clinic:-" + ward + "                           (" + rse12.getString(1).concat("    ").
                        concat(rse12.getString(2)).concat(")");
                this.setTitle(title);

            }

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final SQLException | NumberFormatException es) {
            System.out.println(es);
            es.printStackTrace();

        }

        if (gender.equalsIgnoreCase("Female")) {
            lmpdate.setEnabled(true);
            menarch.setEnabled(true);
            lmpnaturecmb.setEnabled(true);
            jPanel6.enable(true);
        } else if (gender.equalsIgnoreCase("Male")) {
            jPanel6.enable(false);
            lmpdate.setEnabled(false);
            menarch.setEnabled(false);
            lmpnaturecmb.setEnabled(false);
        }
        return text;
    }
    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
        underFive = false;
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = null;

            rset1 = stmt1.executeQuery("select gender,age::int,department from hp_patient_visit where patient_no ='" + jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString() + "' ORDER BY age desc LIMIT 1 ");

            while (rset1.next()) {

                if (rset1.getInt(2) <= 5) {
                    underFive = true;
                }

            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqe.getMessage());
        }
        System.err.println(">>>>>>>>>>>>>Populating");
        String patTypee = "op";
         if(src.equalsIgnoreCase("Reg"))  patTypee = "Registration";
        if (Objects.equals(checkPayment(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString(), jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 4).toString(), patTypee, jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString()), Boolean.TRUE)) {

            years = Math.round((float) Double.parseDouble(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString()));
            nameNoTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString().trim());
            String title = ward + "                           (" + jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString().concat("    ").
                    concat(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString()).concat(")");
            this.setTitle(title);

            patientDetailsTextArea.setForeground(Color.red);
            patientDetailsTextArea.setText(populateBioData(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString()));
            fluidChartReload();
            fluidChartReload3();
            patientSearch.dispose();
        }
// Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked

    private void jButton522ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton522ActionPerformed
        this.patientSearch.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton522ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        com.afrisoftech.nursing.NursingSystemicAssessmentIntfr dismt = new com.afrisoftech.nursing.NursingSystemicAssessmentIntfr(connectDB, "out", ward, nameNoTxt.getText().trim().trim(), "OP");
        dismt.setVisible(true);
        saccopn.add(dismt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            dismt.setSelected(true);
            dismt.setVisible(true);
        } catch (java.beans.PropertyVetoException pvt) {
            System.out.println("the nursing theater problem is " + pvt);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fluidtbltxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fluidtbltxtFocusGained
        // TODO add your handling code here:
        for (int k = 0; k < fluidtbltxt.getRowCount(); k++) {
            for (int r = 0; r < fluidtbltxt.getColumnCount(); r++) {
                fluidtbltxt.getModel().setValueAt(null, k, r);
            }
        }
        int i = 0;
        fluidtbltxt.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select test_code,test_desc from rh.ante_natal_tests ORDER BY test_desc"));
    }//GEN-LAST:event_fluidtbltxtFocusGained
    private void saveVital() {
        dbObject = new com.afrisoftech.lib.DBObject();
        nursingObject = new com.afrisoftech.nursing.NursingObject();
        String regular = null;
        String patientname = null;
        if (jCheckBoxIrregular.isSelected()) {
            regular = "Irregular";
        } else if (jCheckBoxRegular.isSelected()) {
            regular = "Regular";
        }
        try {
            System.out.println("the text returned is " + new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxPainScale.getSelectedItem()));
            connectDB.setAutoCommit(false);
            patientname = patient_name;

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement(
                    "INSERT INTO hp_signs_record("
                    + "patient_no,visit_id,date,"
                    + "temp,blood_glucose ,systolic ,diastolic ,pulse ,"
                    + "pulse_oximetry,pulse_regularity,resp ,lung_sounds,pain_scale,"
                    + "type_of_pain,weight ,height ,bmi ,comments,urinary,patient_name,urgency,rbs)"
                    + "VALUES (?,?, ?, ?::numeric, ?::numeric,  "
                    + "?::numeric, ?::numeric, ?::numeric, ?, ?, "
                    + "?::numeric, ?, ?, ?, ?::numeric,"
                    + "?::numeric, ?::numeric, ?, ?, ?,?,?);");
            pstmt.setObject(1, nameNoTxt.getText().trim().trim());
            pstmt.setObject(2, visitId);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(txtSpinner.getText()));
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(bloodglucosetxt.getText()));
            pstmt.setObject(6, new com.afrisoftech.nursing.NursingObject().NursingObject(txtSystolic.getText()));
            pstmt.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(txtDiastolicBp.getText()));
            pstmt.setObject(8, new com.afrisoftech.nursing.NursingObject().NursingObject(txtPulseRate.getText()));
            pstmt.setObject(9, new com.afrisoftech.nursing.NursingObject().NursingObject(txtPulseOximity.getText()));
            pstmt.setObject(10, regular);
            pstmt.setObject(11, new com.afrisoftech.nursing.NursingObject().NursingObject(txtRespiratory.getText()));
            pstmt.setObject(12, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxLungSound.getSelectedItem().toString()));
            pstmt.setObject(13, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxPainScale.getSelectedItem().toString()));
            pstmt.setObject(14, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxTypePain.getSelectedItem().toString()));
            pstmt.setObject(15, new com.afrisoftech.nursing.NursingObject().NursingObject(weightTxt.getText()));
            pstmt.setObject(16, new com.afrisoftech.nursing.NursingObject().NursingObject(heightTxt.getText()));
            pstmt.setObject(17, new com.afrisoftech.nursing.NursingObject().NursingObject(bmiTxt.getText()));
            pstmt.setObject(18, new com.afrisoftech.nursing.NursingObject().NursingObject(commentVital.getText()));
            pstmt.setObject(19, new com.afrisoftech.nursing.NursingObject().NursingObject(txtUrinary.getText()));
            pstmt.setObject(20, patient_name);
            pstmt.setObject(21, new com.afrisoftech.nursing.NursingObject().NursingObject(urgencyComboBox.getSelectedItem().toString()));
            if(bloodglucosetxt.getText().trim().isEmpty()){
                pstmt.setObject(22, null);
            }else{
                pstmt.setDouble(22, Double.parseDouble(bloodglucosetxt.getText()));
            }

            pstmt.executeUpdate();

            ////update hp patient visit
            if (this.urgencyComboBox.getSelectedIndex() > 0) {

                System.out.println("UPDATE hp_patient_visit"
                        + "   SET  urgency='" + urgencyComboBox.getSelectedItem() + "'"
                        + " WHERE patient_no='" + nameNoTxt.getText().trim() + "' and ip_no='OP' and input_date::date>='(" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + ")-1' ;");

                java.sql.PreparedStatement pstmtupdate = connectDB.prepareStatement(
                        "UPDATE hp_patient_visit"
                        + "   SET  urgency='" + urgencyComboBox.getSelectedItem() + "'"
                        + " WHERE patient_no='" + nameNoTxt.getText().trim() + "' and ip_no='OP' and input_date::date>='(" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + ")-1' ;");
                pstmtupdate.executeUpdate();
            }
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            System.out.println("saving error is " + sq);
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }

        fluidChartReload3();
        fluidChartReload();

    }

    private void clearVital() {
        this.txtSpinner.setText("");
        this.bloodsugarTxt.setText("");
        this.bloodglucosetxt.setText("");
        this.txtSystolic.setText("");
        this.txtDiastolicBp.setText("");
        this.txtPulseRate.setText("");
        this.txtPulseOximity.setText("");
        this.txtRespiratory.setText("");
        this.jComboBoxLungSound.setSelectedIndex(0);
        this.jComboBoxPainScale.setSelectedIndex(0);
        this.jComboBoxTypePain.setSelectedIndex(0);
        this.weightTxt.setText("");
        this.heightTxt.setText("");
        this.bmiTxt.setText("");
        this.commentVital.setText("");
        this.txtUrinary.setText("");
        this.urgencyComboBox.setSelectedIndex(0);

    }
    private void newAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction3ActionPerformed
        // TODO add your handling code here:
        if (nameNoTxt.getText().trim().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {

            saveVital();
            clearVital();

            home.removeAll();
            home.setLayout(new java.awt.BorderLayout());
            home.add(vitalSigns, java.awt.BorderLayout.CENTER);
            vitalSigns.updateUI();

        }


    }//GEN-LAST:event_newAction3ActionPerformed

    private void clearAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction3ActionPerformed

        this.txtSpinner.setText("");
        this.bloodsugarTxt.setText("");
        this.txtSystolic.setText("");
        this.txtDiastolicBp.setText("");
        this.txtPulseRate.setText("");
        this.txtPulseOximity.setText("");
        this.txtRespiratory.setText("");
        this.jComboBoxLungSound.setSelectedIndex(0);
        this.jComboBoxPainScale.setSelectedIndex(0);
        this.jComboBoxTypePain.setSelectedIndex(0);
        this.weightTxt.setText("");
        this.heightTxt.setText("");
        this.bmiTxt.setText("");
        this.commentVital.setText("");
        this.txtUrinary.setText("");
        this.urgencyComboBox.setSelectedIndex(0);
        clearVital();
        this.home.removeAll();
        this.home.setLayout(new java.awt.BorderLayout());
        this.vitalSigns.updateUI();
        this.home.add(vitalSigns, java.awt.BorderLayout.CENTER);


    }//GEN-LAST:event_clearAction3ActionPerformed

    private void helpAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction3ActionPerformed

    private void jComboBoxPainScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPainScaleActionPerformed
        System.out.println("\n\n\n\n\n\nthe text returned is " + new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxPainScale.getSelectedItem()));
    }//GEN-LAST:event_jComboBoxPainScaleActionPerformed

    private void jCheckBoxRegularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRegularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxRegularActionPerformed
    private void pulseOximity(javax.swing.JTextField txtPulseOximity, javax.swing.JLabel pulseOximityTxt) {
        if (txtPulseOximity.getText().toString().equals("")) {
            pulseOximityTxt.setText("");
            pulseOximityTxt.setForeground(Color.red);
        } else {
            if (Integer.parseInt(txtPulseOximity.getText()) < 85) {
                pulseOximityTxt.setText("Severe hypoxia 'Give 100% Oxygen'");
                pulseOximityTxt.setForeground(Color.red);
            } else if (Integer.parseInt(txtPulseOximity.getText()) >= 86 && Integer.parseInt(txtPulseOximity.getText()) <= 90) {
                pulseOximityTxt.setText("Moderate hypoxia 'Give 100% Oxygen'");
                pulseOximityTxt.setForeground(Color.red);
            } else if (Integer.parseInt(txtPulseOximity.getText()) >= 91 && Integer.parseInt(txtPulseOximity.getText()) <= 94) {
                pulseOximityTxt.setText("Mild hypoxia 'Give Oxygen'");
                pulseOximityTxt.setForeground(Color.red);
            } else if (Integer.parseInt(txtPulseOximity.getText()) >= 95 && Integer.parseInt(txtPulseOximity.getText()) <= 100) {
                pulseOximityTxt.setText("Normal 'None or placebic'");
                pulseOximityTxt.setForeground(Color.red);
            }
        }

    }
    private void txtSpinnerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSpinnerCaretUpdate
        // try{
        CheckVitalSignsStatus(tempProgessBar);
//        }catch(NumberFormatException e){
//            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage()+"\n Temperature should be numerical", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
//            tempSpinner.setText("");
//        }finally{
//           // tempSpinner.setText("");
//        }

    }//GEN-LAST:event_txtSpinnerCaretUpdate
    private void getOutputsFluid() {
        try {
            connectDB.setAutoCommit(false);
            //stool frequency
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND stool='yes'");
            int i = 0;
            while (rse12.next()) {
                ///getting the user
                i = i + 1;
            }
            stoolTxt.setText(Integer.toString(i));

            //wet diaper frequency
            java.sql.Statement st = connectDB.createStatement();
            java.sql.ResultSet rs = st.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Wet-Diaper'");
            int j = 0;
            while (rs.next()) {
                ///getting the user
                j = j + 1;
            }
            wetDiaperTxt.setText(Integer.toString(j));

            //visited frequency
            java.sql.Statement sa = connectDB.createStatement();
            java.sql.ResultSet res = sa.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Visited-Toilet'");
            int k = 0;
            while (res.next()) {
                ///getting the user
                k = k + 1;
            }
            visitedTxt.setText(Integer.toString(k));

            //wet bed frequency
            java.sql.Statement stm = connectDB.createStatement();
            java.sql.ResultSet rse = stm.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Wet-Bed'");
            int l = 0;
            while (rse.next()) {
                ///getting the user
                l = l + 1;
            }
            wetBedTxt.setText(Integer.toString(l));

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            System.out.println(es);

        }
    }

    private void getOutputsFluid2() {
        try {
            connectDB.setAutoCommit(false);
            //stool frequency
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND stool='yes' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            int i = 0;
            while (rse12.next()) {
                ///getting the user
                i = i + 1;
            }
            stoolTxt.setText(Integer.toString(i));

            //wet diaper frequency
            java.sql.Statement st = connectDB.createStatement();
            java.sql.ResultSet rs = st.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Wet-Diaper' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            int j = 0;
            while (rs.next()) {
                ///getting the user
                j = j + 1;
            }
            wetDiaperTxt.setText(Integer.toString(j));

            //visited frequency
            java.sql.Statement sa = connectDB.createStatement();
            java.sql.ResultSet res = sa.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Visited-Toilet' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            int k = 0;
            while (res.next()) {
                ///getting the user
                k = k + 1;
            }
            visitedTxt.setText(Integer.toString(k));

            //wet bed frequency
            java.sql.Statement stm = connectDB.createStatement();
            java.sql.ResultSet rse = stm.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND urine_state='Wet-Bed' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            int l = 0;
            while (rse.next()) {
                ///getting the user
                l = l + 1;
            }
            wetBedTxt.setText(Integer.toString(l));

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            System.out.println(es);

        }
    }

    private void fluidChartReload() {
        //get input
        dbObject = new com.afrisoftech.lib.DBObject();
        Double Total = null;
        Double Totals = null;
        try {
            //getting details
            connectDB.setAutoCommit(false);

            //inputs
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery(""
                    + "select sum(iv_amount)as iv   "
                    + "                          , sum(oral_amount) as oral   "
                    + "                          , sum(naso_gastric)  as naso "
                    + "                          , sum(peg_feed) as peg,(sum(iv_amount)   "
                    + "                          + sum(oral_amount)   "
                    + "                          + sum(naso_gastric)   "
                    + "                          + sum(peg_feed)) as total_input  "
                    + "   FROM nursing.fluid_chart_input where"
                    + " transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                    + " and patient_id='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'");

            while (rse12.next()) {
                ///getting the user

                Total = rse12.getDouble(5);

            }
            //outputs
            java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123 = stm123.executeQuery(""
                    + "select(sum(urine_amount)"
                    + "                        +  sum(ng_sanction)"
                    + "                        +  sum(vomitus)"
                    + "                        +  sum(drain_one_amount)"
                    + "                        +  sum(drain_two_amount)"
                    + "                        +  sum(drain_three_amount)"
                    + "                        +  sum(drain_four_amount)"
                    + "                        +  sum(drain_five_amount))as total from nursing.fluid_chart_output"
                    + " where transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                    + " and patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'");

            while (rse123.next()) {
                ///getting the user

                Totals = rse123.getDouble(1);
            }//dbObject.getDBObject(
            totalIntake.setText(dbObject.getDBObject(Total, ""));
            totalOutput.setText(dbObject.getDBObject(Totals, ""));
            totalBalance.setText(dbObject.getDBObject((Total - Totals), ""));

            fluidbalance.setValueAt(dbObject.getDBObject(Total, "") + "ml", 0, 1);
            fluidbalance.setValueAt(dbObject.getDBObject(Totals, "") + "ml", 1, 1);
            fluidbalance.setValueAt(dbObject.getDBObject((Total - Totals), "") + "ml", 2, 1);

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            es.printStackTrace();
            totalIntake.setText(dbObject.getDBObject(Total, ""));
            totalOutput.setText(dbObject.getDBObject(Totals, ""));
            totalBalance.setText(dbObject.getDBObject(Total, ""));

            fluidbalance.setValueAt(dbObject.getDBObject(Total, "") + "ml", 0, 1);
            fluidbalance.setValueAt(dbObject.getDBObject(Totals, "") + "ml", 1, 1);
            fluidbalance.setValueAt(dbObject.getDBObject(Total, "") + "ml", 2, 1);

        }

    }

    private void fluidChartReload3() {
        //get input

        try {
            //getting details
            connectDB.setAutoCommit(false);
            //inputshgfbuijk
            System.out.println("select * from hp_signs_record where patient_no='" + nameNoTxt.getText() + "' AND visit_id='" + visitId + "' order by input_date desc");
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery("select * from hp_signs_record where patient_no='" + nameNoTxt.getText() + "' AND visit_id='" + visitId + "' order by input_date desc");
            //java.sql.ResultSet rse125=stm12.executeQuery("select iv_type as Iv_Type,iv_amount as Iv_Amount,oral_type as Oral_Type,oral_amount as Oral_Amount,naso_gastric as Naso_Gastric,peg_feed as Peg_feed from nursing.fluid_chart_input where patient_id='"+nameNoTxt.getText()+"' AND visit_id='"+visitId+"'");      
            Object temp = "";
            Object diastolic = "";
            Object systolic = "";
            Object resp = "";
            Object pulse = "";
            Object bloodsugar = "";
            Object pulseOximity = "";
            String pulseRegular = "";
            //  rse12.next();
            ///getting the user
            if (rse12.next()) {

                dbObject = new com.afrisoftech.lib.DBObject();
//                temp =  rse12.getObject("temp");
//                diastolic =  rse12.getObject("diastolic");
//                systolic =  rse12.getObject("systolic");
//                resp =  rse12.getObject("resp");
//                pulse = rse12.getObject("pulse");
//                bloodsugar =  rse12.getObject("blood_glucose");
                temp = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("temp")));
                diastolic = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("diastolic")));
                systolic = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("systolic")));
                resp = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("resp")));
                pulse = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("pulse")));
                bloodsugar = (new com.afrisoftech.nursing.NursingObject().NursingNullObject(rse12.getObject("blood_glucose")));
                pulseOximity = dbObject.getDBObject(rse12.getObject("pulse_oximetry"), "0");
                pulseRegular = dbObject.getDBObject(rse12.getString("pulse_regularity"), "0");

                System.out.println(pulseOximity);
                System.out.println(pulse);
                System.out.println(pulseRegular);
            }
            signstable.setValueAt(temp + " degrees", 0, 1);
            signstable.setValueAt(diastolic + " mmHg", 1, 1);
            signstable.setValueAt(systolic + " mmHg", 2, 1);
            signstable.setValueAt(resp + " breaths/min", 3, 1);
            signstable.setValueAt(pulse + " b/min", 4, 1);
            signstable.setValueAt(bloodsugar, 5, 1);

            if (pulseRegular.equalsIgnoreCase("")) {

            } else if (pulseRegular.equalsIgnoreCase("Regular")) {
                regularchk.setSelected(true);
                irregularchk.setSelected(false);
            } else if (pulseRegular.equalsIgnoreCase("Irregular")) {
                irregularchk.setSelected(true);
                regularchk.setSelected(false);
            }
//                txtPulseRate1.setText(Double.toString(pulse));
//               txtPulseOximity1.setText(Double.toString(pulseOximity));

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            es.printStackTrace();
            System.out.println(es);
            double temp = 0.0;
            double diastolic = 0.0;
            double systolic = 0.0;
            double resp = 0.0;
            double pulse = 0.0;
            double bloodsugar = 0.0;

            signstable.setValueAt(temp + " degrees", 0, 1);
            signstable.setValueAt(diastolic + " mmHg", 1, 1);
            signstable.setValueAt(systolic + " mmHg", 2, 1);
            signstable.setValueAt(resp + " breaths/min", 3, 1);
            signstable.setValueAt(pulse + " b/min", 4, 1);
            signstable.setValueAt(bloodsugar, 5, 1);

        }

    }

    private void fluidChartReload2() {
        //get input

        try {
            //getting details
            connectDB.setAutoCommit(false);

            //inputs
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.ResultSet rse12 = stm12.executeQuery("select * from nursing.fluid_chart_input where patient_id='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            //java.sql.ResultSet rse125=stm12.executeQuery("select iv_type as Iv_Type,iv_amount as Iv_Amount,oral_type as Oral_Type,oral_amount as Oral_Amount,naso_gastric as Naso_Gastric,peg_feed as Peg_feed from nursing.fluid_chart_input where patient_id='"+nameNoTxt.getText().trim()+"' AND visit_id='"+visitId+"'");      
            Double Total = 0.0;
            while (rse12.next()) {
                ///getting the user
                Double subTotal;
                subTotal = Double.parseDouble(rse12.getString("iv_amount"))
                        + Double.parseDouble(rse12.getString("oral_amount"))
                        + Double.parseDouble(rse12.getString("naso_gastric"))
                        + Double.parseDouble(rse12.getString("peg_feed"));
                Total = Total + subTotal;
            }
            //outputs
            java.sql.Statement stm123 = connectDB.createStatement();
            java.sql.ResultSet rse123 = stm123.executeQuery("select * from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'");
            Double Totals = 0.0;
            while (rse123.next()) {
                ///getting the user
                Double subTotal;
                subTotal = Double.parseDouble(rse123.getString("urine_amount"))
                        + Double.parseDouble(rse123.getString("ng_sanction"))
                        + Double.parseDouble(rse123.getString("vomitus"))
                        + Double.parseDouble(rse123.getString("drain_one_amount"))
                        + Double.parseDouble(rse123.getString("drain_two_amount"))
                        + Double.parseDouble(rse123.getString("drain_three_amount"))
                        + Double.parseDouble(rse123.getString("drain_four_amount"))
                        + Double.parseDouble(rse123.getString("drain_five_amount"));
                Totals = Totals + subTotal;
            }
            totalIntake.setText(Double.toString(Total));
            totalOutput.setText(Double.toString(Totals));
            totalBalance.setText(Double.toString(Total - Totals));

            connectDB.commit();
            connectDB.setAutoCommit(true);
        } catch (final Exception es) {
            System.out.println(es);

        }

    }
    private void txtSpinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpinnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpinnerActionPerformed

    private void txtPulseOximityCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPulseOximityCaretUpdate
        try {
            pulseOximity(txtPulseOximity, pulseOximityTxt);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n Pulse oximity allow numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtPulseOximity.setText("");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtPulseOximityCaretUpdate

    private void txtPulseRateCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPulseRateCaretUpdate
        // TODO add your handling code here:
        try {
            pulseRate(txtPulseRate, pulseRateProgessBar, jCheckBoxRegular, jCheckBoxIrregular);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n Pulse Rate allow numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtPulseRate.setText("");
        }
    }//GEN-LAST:event_txtPulseRateCaretUpdate

    private void txtSystolicCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSystolicCaretUpdate
        // TODO add your handling code here:
        try {
            systolicBp(jProgressBarSystolicBp);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n Systolic BP allow numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtSystolic.setText("");
        }
    }//GEN-LAST:event_txtSystolicCaretUpdate

    private void txtDiastolicBpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDiastolicBpCaretUpdate

        // TODO add your handling code here:
        try {
            diastolicBp(jProgressBarDiastolic);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n Diastolic BP allow numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtDiastolicBp.setText("");
        }
    }//GEN-LAST:event_txtDiastolicBpCaretUpdate

    private void txtRespiratoryCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRespiratoryCaretUpdate
        // TODO add your handling code here:
        try {
            respiratory();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n Systolic BP allow numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtRespiratory.setText("");
        }

    }//GEN-LAST:event_txtRespiratoryCaretUpdate

    private void heightTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_heightTxtCaretUpdate
        if (weightTxt.getText().length() > 1) {
            if (weightTxt.getText().toString().equals("")) {
                weightTxt.setText("0");
            }
            calculateBMI();
        }
    }//GEN-LAST:event_heightTxtCaretUpdate
    private void calculateBMI() {
        Double BMI = 0.0;
        dbObject = new com.afrisoftech.lib.DBObject();
        double height = 0.0;
        double weight = 0.0;
        if (heightTxt.getText().length() > 0 && weightTxt.getText().length() > 0) {
            height = Double.valueOf(dbObject.getDBObject(this.heightTxt.getText(), "0.00"));
            weight = Double.valueOf(dbObject.getDBObject(this.weightTxt.getText(), "0.00"));
        }

        if (weightTxt.getText().toString().equals("")) {
            weight = 0.0;
        } else if (heightTxt.getText().toString().equals("")) {
            height = 0.0;
        }

        BMI = weight / ((height * 0.01) * (height * 0.01));

        try {
            if (BMI.isInfinite() || BMI.isNaN()) {
                bmiTxt.setText("0.0");
            }
            bmiTxt.setText(String.valueOf(BMI.shortValue()));

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage() + "\n weight allows numerical only", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            weightTxt.setText("");
        }

    }
    private void weightTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_weightTxtCaretUpdate
        if (heightTxt.getText().length() > 1) {
            if (heightTxt.getText().toString().equals("")) {
                heightTxt.setText("0");
            }

            calculateBMI();
        }
    }//GEN-LAST:event_weightTxtCaretUpdate

    private void bmiTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bmiTxtCaretUpdate
        // TODO add your handling code here:

//            if(java.lang.Double.parseDouble(this.bmiTxt.getText())<= 18.5){
//                lblBmi.setText("Underweight");
//                lblBmi.setForeground(Color.red);
//            }else if(java.lang.Double.parseDouble(this.bmiTxt.getText())<= 18.5){
//                lblBmi.setText("Normal weight");
//                lblBmi.setForeground(Color.red);
//            }else if(java.lang.Double.parseDouble(this.bmiTxt.getText())<= 18.5){
//                lblBmi.setText("Overweight");
//                lblBmi.setForeground(Color.red);
//            }else if(java.lang.Double.parseDouble(this.bmiTxt.getText())>=30){
//                lblBmi.setText("Obesity");
//                lblBmi.setForeground(Color.red);
//            }
    }//GEN-LAST:event_bmiTxtCaretUpdate

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if (jComboBox1.getSelectedItem() == "VIEW HISTORY") {
            try {

                java.sql.Statement stmt11 = connectDB.createStatement();

                namess = patient_name;
            } catch (Exception est) {
                System.out.println(est.getMessage());
            }
            com.afrisoftech.nursing.patientHistoryPdf policy = new com.afrisoftech.nursing.patientHistoryPdf();
            policy.patientHistoryPdf(connectDB, nameNoTxt.getText().trim().toString(), namess);
        } else {
            if (jComboBox1.getSelectedItem() == "ADD HISTORY") {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {

                        System.out.println("hgfhgdugjdu" + headerDatePicker3.getDate().toLocaleString());
                        patientsHistory dialog = new patientsHistory(new javax.swing.JFrame(), true, connectDB, pConnDB, nameNoTxt.getText().trim().toString(), com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()), visitId, ward, gender);
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        });
                        dialog.setVisible(true);
                    }
                });
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    private void compute() {
        Double j
                = Double.parseDouble(iv_amount.getValue().toString())
                + Double.parseDouble(oral_amount.getValue().toString())
                + Double.parseDouble(naso_gastric.getValue().toString())
                + Double.parseDouble(peg_feed.getValue().toString());
        totalTxt.setText(Double.toString(j));
    }

    private void compute2() {

        Double j
                = Double.parseDouble(urine_amount.getValue().toString())
                + Double.parseDouble(drain1txt1.getValue().toString())
                + Double.parseDouble(drain2txt1.getValue().toString())
                + Double.parseDouble(drain3txt1.getValue().toString())
                + Double.parseDouble(drain4txt1.getValue().toString())
                + Double.parseDouble(drain5txt1.getValue().toString())
                + Double.parseDouble(ngSuction.getValue().toString())
                + Double.parseDouble(vomitus.getValue().toString());
        totalTxt1.setText(Double.toString(j));
    }
    private void jButton524ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton524ActionPerformed
        this.occupancyDetailsDialog.dispose();
    }//GEN-LAST:event_jButton524ActionPerformed

    private void occupancywardCMBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_occupancywardCMBItemStateChanged


    }//GEN-LAST:event_occupancywardCMBItemStateChanged

    private void bloodglucosetxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bloodglucosetxtCaretUpdate
        checkBloodGlucoseStatus(glucoseProgressBar);
    }//GEN-LAST:event_bloodglucosetxtCaretUpdate

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (jTextField1.getText().length() > 5) {
            this.userTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "select  distinct login_name from secure_menu_access WHERE login_name ILIKE '%" + jTextField1.getText().toString().trim() + "%'"));
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        this.packTable.setValueAt(userTable.getValueAt(userTable.getSelectedRow(), 0), this.packTable.getSelectedRow(), 2);
        this.usersSearchdialog.dispose();
    }//GEN-LAST:event_userTableMouseClicked
    private java.lang.Boolean checkPayment(String patientNo, String paymentMode, String patType, String urgency) {
        Boolean patientPaid = false;
        System.err.println("????"+patType);
        switch (patType.toLowerCase().trim()) {
            case "ip": {
                patientPaid = true;
            }
            break;
            case "registration": {
                    patientPaid = true;
                }
                break;
            case "op": {
                switch (paymentMode.toLowerCase().trim()) {

                    case "scheme": {
                        patientPaid = true;
                    }
                    break;
                    // case "cash": {
                    default: {
                        switch (urgency.toLowerCase().trim()) {
                            case "urgent": {
                                patientPaid = true;
                            }
                            break;
                            
                            case "emergency": {
                                patientPaid = true;
                            }
                            break;
                            case "normal": {

                                try {
                                    java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                                            + "SELECT COUNT(patient_no) FROM ac_cash_collection WHERE patient_no ='" + patientNo + "' "
                                            + " and receipt_time::date >= (select current_timestamp(0)::date -1) ");

                                    java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

                                    if (rsetVector.next()) {
                                        if (rsetVector.getInt(1) > 0 || underFive) {
                                            patientPaid = true;
                                        } else if (rsetVector.getInt(1) <= 0) {
                                            patientPaid = false;
                                            javax.swing.JOptionPane.showMessageDialog(this, "The patient has not paid for this service", "Information Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                        }
                                    }
                                } catch (Exception ex) {
                                    patientPaid = false;
                                    javax.swing.JOptionPane.showMessageDialog(this, "The patient has not paid for this service", "Information Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                    Logger.getLogger(NursingTriage.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            break;
                        }

                    }
                }
            }
            break;
        }
        System.err.println(">>>>>>>>>>>>>Populating 3"+ patientPaid);
        return patientPaid;
    }
    private void occupancyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_occupancyTableMouseClicked
        underFive = false;
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = null;

            rset1 = stmt1.executeQuery("select gender,age::int,department from hp_patient_visit where patient_no ='" + occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 0).toString() + "' ORDER BY age desc LIMIT 1 ");

            while (rset1.next()) {

                if (rset1.getInt(2) <= 5) {
                    underFive = true;
                }

            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqe.getMessage());
        }
        System.err.println(">>>>>>>>>>>>>Populating");

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (evt.getClickCount() == 1) {
            String patTypee = "op";
            if(src.equalsIgnoreCase("Reg"))  patTypee = "Registration";
            if (Objects.equals(checkPayment(occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 0).toString(), occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 8).toString(), patTypee, occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 5).toString()), Boolean.TRUE)) {
                if (theatreChbx.isSelected() == true) {
                    bookingNo = null;
                    bookingNo = occupancyTable.getModel().getValueAt(occupancyTable.getSelectedRow(), 1).toString().trim();
                    operationstxt.setText(occupancyTable.getModel().getValueAt(occupancyTable.getSelectedRow(), 4).toString().trim());
                }
                System.err.println(">>>>>>>>>>>>>Populating 2");

                try {
                    nameNoTxt.setText(occupancyTable.getModel().getValueAt(occupancyTable.getSelectedRow(), 0).toString().trim());
                    patientDetailsTextArea.setText(populateBioData(nameNoTxt.getText().trim()));
                    String title = ward + "                           (" + occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 0).toString().concat("    ").
                            concat(occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 1).toString()).concat(")");
                    java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement(""
                            + "UPDATE hp_patient_visit SET nature = 'triage' "
                            + "where nature != 'Cons' and patient_no = '" + occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 0).toString() + "' "
                            + " AND "
                            + " date::date ='" + occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 7) + "'::date");
                    pstmt46.executeUpdate();
                    this.setTitle(title);
                    fluidChartReload3();
                    fluidChartReload();

                } catch (Exception thea) {

                    System.out.println("eror ya thatre label  area ni ");
                    thea.printStackTrace();
                }
                System.out.println("SELECT trans_date::date as date,  service, quantity, \n"
                        + "       amount,  \n"
                        + "       visit_id,doctor,false as Administer"
                        + "  FROM pb_doctors_request  where  paid=true and collected=false and  requisition_no='GENERAL'"
                        + " and patient_no='" + this.nameNoTxt.getText() + "' "
                        + "and trans_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' ");
                this.requestsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                        "SELECT trans_date::date as date,  service, quantity, \n"
                        + "       amount,  \n"
                        + "       visit_id,doctor,false as Administer"
                        + "  FROM pb_doctors_request  where  paid=true and collected=false and  requisition_no='GENERAL'"
                        + " and patient_no='" + this.nameNoTxt.getText() + "' "
                        + "and trans_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "));
            }
        }

        years = Math.round((float) Double.parseDouble(occupancyTable.getValueAt(occupancyTable.getSelectedRow(), 3).toString()));

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_occupancyTableMouseClicked

    private void currentOccupRbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentOccupRbtnMouseClicked
        urgencycmbx.setSelectedIndex(0);

    }//GEN-LAST:event_currentOccupRbtnMouseClicked

    private void currentOccupRbtnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currentOccupRbtnItemStateChanged
        if (currentOccupRbtn.isSelected() == Boolean.TRUE) {

            occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment\n"
                    + " From  hp_patient_visit   \n"
                    + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1"
            ));
            occupyno.setText("No of Patients in " + ward + " is :" + occupancyTable.getRowCount());

            //coluorTable();
        }
    }//GEN-LAST:event_currentOccupRbtnItemStateChanged

    private void urgencycmbxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_urgencycmbxMouseClicked
        currentOccupRbtn.setSelected(false);

    }//GEN-LAST:event_urgencycmbxMouseClicked

    private void urgencycmbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_urgencycmbxItemStateChanged
        if (urgencycmbx.getSelectedIndex() == 1) {

            occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment\n"
                    + " From  hp_patient_visit   \n"
                    + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1"
            ));

            try {
                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.ResultSet rs = st3.executeQuery("SELECT   count(patient_no) From  hp_patient_visit   \n"
                        + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1");

                ResultSetMetaData rsMetaData = rs.getMetaData();
                String label = "No of Patients in " + ward + " is :";
                int row = 0;
                rs.next();
                {

                    label = label.concat("" + rs.getObject(1) + "\t");
                }
                occupyno.setText(label);
            } catch (Exception label) {

            }

        }

        if (urgencycmbx.getSelectedIndex() == 3) {
            occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment"
                    + " From  hp_patient_visit   \n"
                    + " where urgency='Normal' and  date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1"
            ));

            try {
                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.ResultSet rs = st3.executeQuery("SELECT count(patient_no) From  hp_patient_visit   \n"
                        + " where urgency='Normal' and date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1");

                ResultSetMetaData rsMetaData = rs.getMetaData();
                String label = "No of Patients in " + ward + " is :";
                int row = 0;
                rs.next();
                {

                    label = label.concat("" + rs.getObject(1) + "\t");
                }
                occupyno.setText(label);
            } catch (Exception label) {

            }
        }
        if (urgencycmbx.getSelectedIndex() == 2) {
            occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no AS Patient_No, name AS Name,comments as Patient_Visist,age,gender,urgency,nature,date::date,payment"
                    + " From  hp_patient_visit   \n"
                    + " where urgency='Emergency' and  date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1"
            ));

            try {
                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.ResultSet rs = st3.executeQuery("SELECT count(patient_no) From  hp_patient_visit   \n"
                        + " where urgency='Emergency' and date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "'  and hp_patient_visit.clinic='" + ward + "'   AND discharge is null ORDER BY 1");

                ResultSetMetaData rsMetaData = rs.getMetaData();
                String label = "No of Patients in " + ward + " is :";
                int row = 0;
                while (rs.next()) {

                    label = label.concat("" + rs.getObject(1) + "\t");
                }
                occupyno.setText(label);
            } catch (Exception label) {

            }
        }

    }//GEN-LAST:event_urgencycmbxItemStateChanged

    private void theatreChbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_theatreChbxItemStateChanged
        if (theatreChbx.isSelected() == true) {
            occupancyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT patient_no,booking_no, p_name, booking_type,  service as Procedure_Name,patient_source,clinic as Theatre_Name, appointment_date  FROM pb_bookings  "
                    + "               where patient_source ilike '" + ward + "' or patient_source ilike 'OP'  and cancel=false and type_of_booking='Theatre Booking' and theatre_Discharge=false and status='Booked' and confirmation=true  ORDER BY booking_no DESC"));

        }
    }//GEN-LAST:event_theatreChbxItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        txtRightEyeSize.setText("0");
        txtLeftEyeSize.setText("0");
        txtTotal.setText("0");

        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        if (jCheckBox1.isSelected()) {
            home.removeAll();
            home.setLayout(new java.awt.BorderLayout());
            home.add(systemicAssesment, java.awt.BorderLayout.CENTER);
            systemicAssesment.updateUI();

        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        jCheckBox1.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        if (jCheckBox2.isSelected()) {

            home.removeAll();
            home.setLayout(new java.awt.BorderLayout());
            home.add(pressureSorejPanel, java.awt.BorderLayout.CENTER);
            pressureSorejPanel.updateUI();
            if (years >= 14 && years <= 44) {
                jComboBoxAge.setSelectedIndex(1);
            } else if (years >= 45 && years <= 64) {
                jComboBoxAge.setSelectedIndex(2);
            } else if (years >= 65) {
                jComboBoxAge.setSelectedIndex(3);
            }
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        jCheckBox2.setSelected(false);
        jCheckBox1.setSelected(false);
        jCheckBox4.setSelected(false);
        if (jCheckBox3.isSelected()) {
            home.removeAll();
            home.setLayout(new java.awt.BorderLayout());
            home.add(turningChart, java.awt.BorderLayout.CENTER);
            turningChart.updateUI();

        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:

        jCheckBox1.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox2.setSelected(false);
        if (jCheckBox4.isSelected()) {
            home.removeAll();
            home.setLayout(new java.awt.BorderLayout());
            home.add(vitalSigns, java.awt.BorderLayout.CENTER);
            vitalSigns.updateUI();

        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void cardexTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardexTblMouseClicked
        // TODO add your handling code here:

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vector vee = new Vector();
                vee.addElement(cardexTbl.getValueAt(cardexTbl.getSelectedRow(), 1).toString());
                vee.addElement(cardexTbl.getValueAt(cardexTbl.getSelectedRow(), 2).toString());
                vee.addElement(cardexTbl.getValueAt(cardexTbl.getSelectedRow(), 3).toString());
                NurseCardex dialog = new NurseCardex(new javax.swing.JFrame(), true, vee);
                dialog.setBounds(350, 150, 600, 400);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_cardexTblMouseClicked

    private void nursesRptCardexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nursesRptCardexKeyPressed
        int KEY = evt.getKeyCode();
        if (KEY == KeyEvent.VK_ENTER) {
            savedCardex();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_nursesRptCardexKeyPressed

    private void newAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction6ActionPerformed
        {
            savedCardex();
        }
    }//GEN-LAST:event_newAction6ActionPerformed

    private void clearAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction6ActionPerformed
        nursesRptCardex.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAction6ActionPerformed

    private void newAction12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction12ActionPerformed
        com.afrisoftech.hospital.GeneralBillingIntfr comp = new com.afrisoftech.hospital.GeneralBillingIntfr(connectDB, pConnDB);
        saccopn.add(comp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            comp.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
    }//GEN-LAST:event_newAction12ActionPerformed

    private void newAction13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction13ActionPerformed
        // TODO add your handling code here:
        com.afrisoftech.nursingreports.NursinCardexPdf policy = new com.afrisoftech.nursingreports.NursinCardexPdf();

        policy.NursinCardexPdf(connectDB, nameNoTxt.getText().trim().trim(), visitId, patient_name);
    }//GEN-LAST:event_newAction13ActionPerformed

    private void doctorMedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorMedMouseClicked
        // TODO add your handling code here:

        //        int rowindex =doctorMed.getSelectedRow();
        //
        //        if(doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0).equals(true)){
        //
        //            System.err.println("the row value  is "+doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0));
        //            if(!implemented.contains(rowindex)){
        //                implemented.addElement(rowindex);
        //
        //            }
        //            System.err.println("The number of selected rows = "+rowindex);
        //
        //        }else
        //        if(doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0).equals(false)){
        //            System.err.println("the row value  is "+doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0));
        //            if(implemented.contains(rowindex)){
        //                implemented.remove(rowindex);
        //            }
        //
        //        }
    }//GEN-LAST:event_doctorMedMouseClicked

    private void newAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction5ActionPerformed
        // TODO add your handling code here:
        try {
            for (int i = 0; i < doctorMed.getRowCount(); i++) {
                if (doctorMed.getModel().getValueAt(i, 0).toString().trim().equalsIgnoreCase("true") && doctorMed.getModel().getValueAt(i, 0).toString() != null) {
                    //                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel)doctorMed.getModel();
                    //                     defTableModel.removeRow(i);
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE nursing.medication_administration SET receive=true WHERE patient_no='" + nameNoTxt.getText().trim() + "' and receive=false and administer = false and drug_code='" + doctorMed.getModel().getValueAt(i, 2) + "'");
                    System.out.println(pstmt);
                    pstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            doctorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT false as Receive, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=false and administer = false"));
        } catch (Exception sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_newAction5ActionPerformed

    private void monitorMedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monitorMedMouseClicked
        // TODO add your handling code here:

        //        int rowindex =doctorMed.getSelectedRow();
        //
        //        if(doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0).equals(true)){
        //
        //            System.err.println("the row value  is "+doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0));
        //            if(!implemented.contains(rowindex)){
        //                implemented.addElement(rowindex);
        //
        //            }
        //            System.err.println("The number of selected rows = "+rowindex);
        //
        //        }else
        //        if(doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0).equals(false)){
        //            System.err.println("the row value  is "+doctorMed.getModel().getValueAt(doctorMed.getSelectedRow(),0));
        //            if(implemented.contains(rowindex)){
        //                implemented.remove(rowindex);
        //            }
        //
        //        }
    }//GEN-LAST:event_monitorMedMouseClicked

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        // TODO add your handling code here:

        try {
            connectDB.setAutoCommit(false);
            for (int i = 0; i < monitorMed.getRowCount(); i++) {
                if (monitorMed.getModel().getValueAt(i, 0).equals(true)) {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO nursing.administered_drugs("
                            + "patient_no, visit_id, server_date, trans_date, drug_code, drug, dosage, route, frequency, no_of_days, users)"
                            + "    VALUES (?, ?, localtimestamp, ?::date, ?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setObject(1, nameNoTxt.getText().trim());
                    pstmt.setObject(2, visitId);
                    pstmt.setObject(3, headerDatePicker.getDate().toLocaleString());
                    pstmt.setObject(4, monitorMed.getValueAt(i, 2));//drug_code
                    pstmt.setObject(5, monitorMed.getValueAt(i, 3));//drug
                    pstmt.setObject(6, monitorMed.getValueAt(i, 4));//dosage
                    pstmt.setObject(7, monitorMed.getValueAt(i, 5));//route
                    if (monitorMed.getModel().getValueAt(i, 6).toString().equalsIgnoreCase("stat")) {
                        java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("UPDATE nursing.medication_administration SET administer=true WHERE patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer =false and drug_code= '" + monitorMed.getModel().getValueAt(i, 2) + "'");
                        pstmt1.executeUpdate();
                        pstmt.setObject(8, monitorMed.getValueAt(i, 6));//freq

                    } else if (!"Stat".equals(monitorMed.getModel().getValueAt(i, 6).toString())) {
                        pstmt.setObject(8, monitorMed.getValueAt(i, 6));//freq
                    }
                    pstmt.setObject(9, monitorMed.getValueAt(i, 7));//no_of_days
                    pstmt.setObject(10, monitorMed.getValueAt(i, 8));//user

                    pstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT false as Administer, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer= false"));
        } catch (Exception sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            //System.out.println( );
            sq.printStackTrace();
        }
    }//GEN-LAST:event_adminBtnActionPerformed

    private void doneAdministrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneAdministrationBtnActionPerformed
        // TODO add your handling code here:
        try {
            for (int i = 0; i < monitorMed.getRowCount(); i++) {
                if (monitorMed.getModel().getValueAt(i, 0).equals(true)) {
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE nursing.medication_administration SET administer=true WHERE patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer =false and drug_code= '" + monitorMed.getModel().getValueAt(i, 2) + "'");
                    //doctorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT receive, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText().trim()+"' and receive=false and adminster = false"));
                    pstmt.executeUpdate();
                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT false as administer, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=false and administer = false"));
        } catch (Exception sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_doneAdministrationBtnActionPerformed

    private void medReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_medReportMouseClicked

    private void completlyAdmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_completlyAdmItemStateChanged
        // TODO add your handling code here:
        medReport.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT drug_code, date_prescribed,  drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer=true"));
    }//GEN-LAST:event_completlyAdmItemStateChanged

    private void completlyAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completlyAdmActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_completlyAdmActionPerformed

    private void currentlyAdmtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currentlyAdmtItemStateChanged
        // TODO add your handling code here:
        medReport.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT drug_code, date_prescribed,  drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer=false"));
    }//GEN-LAST:event_currentlyAdmtItemStateChanged

    private void currentlyAdmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentlyAdmtActionPerformed

    }//GEN-LAST:event_currentlyAdmtActionPerformed

    private void timeAdministeredItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_timeAdministeredItemStateChanged
        // TODO add your handling code here:
        medReport.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT drug_code, trans_date as date_done, drug, dosage, route, frequency, no_of_days, users as nurse FROM nursing.administered_drugs order by drug_code"));
    }//GEN-LAST:event_timeAdministeredItemStateChanged

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

        if (jTabbedPane1.getSelectedIndex() == 0) {
            doctorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT false as Receive, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=false and administer= false"));
            //monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT administer, date_prescribed, drug_code, drug, dosage, route, frequency, time_due, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText().trim()+"' and receive=true"));
        }
        if (jTabbedPane1.getSelectedIndex() == 1) {
            monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT false as Administer, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "' and receive=true and administer= false"));
            //monitorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT administer, date_prescribed, drug_code, drug, dosage, route, frequency, time_due, doctor FROM nursing.medication_administration where patient_no='"+nameNoTxt.getText().trim()+"' and receive=true"));
        }
        if (jTabbedPane1.getSelectedIndex() == 2) {
            timeAdministered.setSelected(false);
            completlyAdm.setSelected(false);
            currentlyAdmt.setSelected(false);
            medReport.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT drug_code, date_prescribed, drug, dosage, route, frequency, no_of_days, doctor FROM nursing.medication_administration where patient_no='" + nameNoTxt.getText().trim() + "'"));
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void nursingTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nursingTabbedPaneMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (nursingTabbedPane.getSelectedIndex() == 6) {
            doctorMed.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT false as Receive, date_prescribed, drug_code, drug, dosage, route, frequency, no_of_days, doctor"
                    + " FROM nursing.medication_administration where pharmacy_dispense=true and patient_no='" + nameNoTxt.getText().trim() + "' and receive=false and administer = false"));
        }

        if (nameNoTxt.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Select Patient NO First", "Error Message!second", javax.swing.JOptionPane.ERROR_MESSAGE);
            nursingTabbedPane.setEnabled(false);
        } else if (!nameNoTxt.getText().trim().isEmpty()) {
            nursingTabbedPane.setEnabled(true);
            if (nursingTabbedPane.getSelectedIndex() == 2) {
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                home.removeAll();
                home.setLayout(new java.awt.BorderLayout());
                home.add(jLabel3, java.awt.BorderLayout.CENTER);
                jLabel3.updateUI();

                cardexTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select urgency,transaction_time,cardex,nurse_name from nursing.nurse_cardex where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' "));
                cardexTbl.getColumnModel().getColumn(0).setPreferredWidth(100);
                cardexTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
                cardexTbl.getColumnModel().getColumn(2).setPreferredWidth(500);
                cardexTbl.getColumnModel().getColumn(3).setPreferredWidth(150);
            }
            this.requestsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                    "SELECT trans_date::date as date,  service, quantity, \n"
                    + "       amount,  \n"
                    + "       visit_id,doctor,false as Administer"
                    + "  FROM pb_doctors_request  where  paid=true and collected=false and  requisition_no='GENERAL'"
                    + " and patient_no='" + this.nameNoTxt.getText() + "' "
                    + "and trans_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_nursingTabbedPaneMouseClicked

    private void nursingTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nursingTabbedPaneStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nursingTabbedPaneStateChanged

    private void gunshotsPatientRdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gunshotsPatientRdiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gunshotsPatientRdiActionPerformed

    private void savePatientReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePatientReportBtnActionPerformed
        // TODO add your handling code here:
        try {

            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement(""
                    + "INSERT INTO hp_patient_reporter(patient_no, visit_id, ward, date_admitted, patient_status, report_comment, "
                    + "            status_date, user_name,name,,report_time) VALUES (?, ?, ?, ?::date, ?, ?, ?, ?,?,localtimestamp)");

            pstmt1.setObject(1, nameNoTxt.getText());
            pstmt1.setObject(2, visitId);
            pstmt1.setObject(3, ward);
            pstmt1.setDate(4, java.sql.Date.valueOf(dateAdmitted));//
            if (patientDeadRdi.isSelected()) {
                pstmt1.setObject(5, "DEAD PATIENTS");
            } else if (patientBreasFeedingRdi.isSelected()) {
                pstmt1.setObject(5, "BREAST FEEDING PATIENTS");
            } else if (patientGuardedRdi.isSelected()) {
                pstmt1.setObject(5, "GUARDED PATIENTS");
            } else if (patientReactiveRdi.isSelected()) {
                pstmt1.setObject(5, "REACTIVE PATIENTS");
            } else if (patientMissingRdi.isSelected()) {
                pstmt1.setObject(5, "MISSING PATIENTS");
            } else if (sexuallyAssultedRdi.isSelected()) {
                pstmt1.setObject(5, "SEXUALLY ASSULTED");
            } else if (incidenceRdi.isSelected()) {
                pstmt1.setObject(5, "REPORTED INCIDENCE");
            } else if (quadriplegicPatientRdi.isSelected()) {
                pstmt1.setObject(5, "QUARDLIPLEGIC PATIENTS");
            } else if (paraplegicPatientRdi.isSelected()) {
                pstmt1.setObject(5, "PARAPLEGIC PATIENTS");
            } else if (veryIllPatient.isSelected()) {
                pstmt1.setObject(5, "VERY ILL PATIENTS");
            } else if (referredInPatientRdi.isSelected()) {
                pstmt1.setObject(5, "REFERRED IN PATIENTS");
            } else if (gunshotsPatientRdi.isSelected()) {
                pstmt1.setObject(5, "PATIENT WITH GUNSHOTS");
            } else if (totalNursingPatientRdi.isSelected()) {
                pstmt1.setObject(5, "UNDER TOTAL NURSING CARE");
            } else if (assistedFeedingRdi.isSelected()) {
                pstmt1.setObject(5, "FOR ASSISTED FEEDING");
            } else if (patientIsStaffRdi.isSelected()) {
                pstmt1.setObject(5, "STAFF PATIENTS");
            } else {
                pstmt1.setObject(5, theatrePatients);
            }
            //patientIsStaffRdi
            pstmt1.setObject(6, commentArea.getText());
            pstmt1.setObject(7, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt1.setObject(8, user);
            pstmt1.setObject(9, patient_name);
            pstmt1.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Report Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            commentArea.setText("");

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_savePatientReportBtnActionPerformed

    private void quadriplegicPatientRdiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_quadriplegicPatientRdiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_quadriplegicPatientRdiItemStateChanged

    private void jRadioButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton5ItemStateChanged
        if (jRadioButton5.isSelected() == true) {
            theatrePatients = "Call Received";
        }
    }//GEN-LAST:event_jRadioButton5ItemStateChanged

    private void jRadioButton6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton6ItemStateChanged
        if (jRadioButton6.isSelected() == true) {
            theatrePatients = "Porter Arrival";
        }
    }//GEN-LAST:event_jRadioButton6ItemStateChanged

    private void jRadioButton7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton7ItemStateChanged
        if (jRadioButton7.isSelected() == true) {
            theatrePatients = "Patient Sent For Theatre";
        }
    }//GEN-LAST:event_jRadioButton7ItemStateChanged

    private void jComboBoxBestVerbalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBestVerbalActionPerformed
        txtTotal.setText(Integer.toString(eyeOpen()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBestVerbalActionPerformed

    private void jComboBoxEyeOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEyeOpenActionPerformed
        txtTotal.setText(Integer.toString(eyeOpen()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEyeOpenActionPerformed

    private void jComboBoxBestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBestActionPerformed
        txtTotal.setText(Integer.toString(eyeOpen()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBestActionPerformed

    private void txtRightEyeSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRightEyeSizeMouseClicked

        PupilGauge dialog = new PupilGauge(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        txtRightEyeSize.setText(Integer.toString(dialog.pupilGauge()));
        dialog.dispose();
    }//GEN-LAST:event_txtRightEyeSizeMouseClicked

    private void txtRightEyeSizeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRightEyeSizeFocusGained

    }//GEN-LAST:event_txtRightEyeSizeFocusGained

    private void txtLeftEyeSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLeftEyeSizeMouseClicked

        PupilGauge dialog = new PupilGauge(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        txtLeftEyeSize.setText(Integer.toString(dialog.pupilGauge()));
        dialog.dispose();
    }//GEN-LAST:event_txtLeftEyeSizeMouseClicked

    private void seizureactivityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_seizureactivityItemStateChanged
        // TODO add your handling code here:
        selecttermcmb.addItem("Focal");
        selecttermcmb.addItem("Psychomotor Temporal Lobe");
        selecttermcmb.addItem("Atonic -or- Akinetic");
        selecttermcmb.addItem("Petit Mal");
        selecttermcmb.addItem("Grand Mal");
        selecttermcmb.addItem("Status Epilepticus");

        if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Focal")) {
            descriptiontxarea.setText("No loss of consciosness; may involve motor, sensory and/ or autonomic symptoms.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Psychomotor Temporal Lobe")) {
            descriptiontxarea.setText("May be preceded by an aura.At onset of seizure, ther will be a consciousness change."
                    + "Ends with a postal-ictal period");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Atonic -or- Akinetic")) {
            descriptiontxarea.setText("sudden loss of body tone -or- body"
                    + "movement");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Petit Mal")) {
            descriptiontxarea.setText("Sudden onset and cessation -or- loss of reponsivness; no post ictal "
                    + "symptoms");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Grand Mal")) {
            descriptiontxarea.setText("Or tonic-clonic seizures.Pre-ictal symptoms may involve focal seizure loss of consciousness at onset"
                    + "of seizure with increased muscle tone(rigid flexed and rigid extended postures).Bilateral rythmic jerks follow and become further"
                    + "apart "
                    + "Post-ictal period follows");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Status Epilepticus")) {
            descriptiontxarea.setText("Generalised tonic-clonic seizure lasting longer than 30min -or- failure of patient to "
                    + "regain consciousness between a series of seizures");
        }
    }//GEN-LAST:event_seizureactivityItemStateChanged

    private void seizureactivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seizureactivityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seizureactivityActionPerformed

    private void breathingpatternItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breathingpatternItemStateChanged
        // TODO add your handling code here:
        selecttermcmb.addItem("Tachypnea");
        selecttermcmb.addItem("Apnea");
        selecttermcmb.addItem("Gasping");
        selecttermcmb.addItem("Cheyne - Strokes");

        if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Tachypnea")) {
            descriptiontxarea.setText("No loss of consciosness; may involve motor, sensory and/ or autonomic symptoms.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Apnea")) {
            descriptiontxarea.setText("Increased frequency Breathing");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Gasping")) {
            descriptiontxarea.setText("Cessation of respirations.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Cheyne - Strokes")) {
            descriptiontxarea.setText("Sequence of Gradually increasing tidal volume, following by gradual decreasing tida volume");
        }
    }//GEN-LAST:event_breathingpatternItemStateChanged

    private void consciousnestermItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_consciousnestermItemStateChanged
        // TODO add your handling code here:
        selecttermcmb.addItem("Alert");
        selecttermcmb.addItem("Lethargic");
        selecttermcmb.addItem("Obtunded");
        selecttermcmb.addItem("Stuporous");
        selecttermcmb.addItem("Semicomatose");
        selecttermcmb.addItem("Coma");

        if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Alert")) {
            descriptiontxarea.setText("Responds immediately and fully to visual, auditory or tactile stimulation");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Lethargic")) {
            descriptiontxarea.setText("Drowsy, sleeps a lot, but is easily aroused and then responds to visual, auditory or tactile"
                    + "stimulation.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Obtunded")) {
            descriptiontxarea.setText("Can be aroused by stimuli(not painful); will then respond to questions or command."
                    + "Remains aroused as long is applied. During the arousal, patient responds but may be confused");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Stuporous")) {
            descriptiontxarea.setText("Very hard to arouse.Looks around when stimulated. does not obey commands at times"
                    + "May curse or say 'don't' when stimulated.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Semicomatose")) {
            descriptiontxarea.setText("Purposeful movement when stimulated.Does not obey commands or answer questions."
                    + "Does not not at all.");
        } else if (selecttermcmb.getSelectedItem().toString().equalsIgnoreCase("Coma")) {
            descriptiontxarea.setText("Decorticate: draws hands up onto chest when stimulated, but not purposely."
                    + "Decerebrate: Extends arms and legs, arches neck and internally rotates hands and arms when stimulated"
                    + "Unresponsive: no response to any stimuli.");
        }
    }//GEN-LAST:event_consciousnestermItemStateChanged

    private void selecttermcmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selecttermcmbItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_selecttermcmbItemStateChanged

    private void newActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionActionPerformed
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            neuralogicalSave();
            clearNueralogical();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_newActionActionPerformed

    private void updateActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionActionPerformed

    private void deleteActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionActionPerformed

    private void clearActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionActionPerformed
        // TODO add your handling code here:
        clearNueralogical();
    }//GEN-LAST:event_clearActionActionPerformed

    private void helpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpActionActionPerformed

    private void roomairradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomairradioActionPerformed
        // TODO add your handling code here:

        if (roomairradio.isSelected()) {
            roomairtxt.setEditable(true);
            litrestxt.setEnabled(false);
            modeofadmincmb.setEnabled(false);
            saturationtxt.setEnabled(false);

            roomairtxt.setText("");
            litrestxt.setText("");
            modeofadmincmb.setSelectedIndex(0);
            saturationtxt.setText("");
        } else if (!roomairradio.isSelected()) {
            roomairtxt.setEditable(false);
            roomairtxt.setText("");
        }
    }//GEN-LAST:event_roomairradioActionPerformed

    private void oxygenradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oxygenradioActionPerformed
        // TODO add your handling code here:
        roomairtxt.setText("");
        litrestxt.setEnabled(true);
        modeofadmincmb.setEnabled(true);
        saturationtxt.setEnabled(true);
        roomairtxt.setEditable(false);
    }//GEN-LAST:event_oxygenradioActionPerformed

    private void newAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction7ActionPerformed
        // TODO add your handling code here:
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            respiratorySave();
            respiratoryClear();
        }
    }//GEN-LAST:event_newAction7ActionPerformed

    private void updateAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction6ActionPerformed

    private void deleteAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAction6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteAction6ActionPerformed

    private void clearAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAction7ActionPerformed

    private void helpAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction6ActionPerformed

    private void saveCadiovascularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCadiovascularActionPerformed
        // TODO add your handling code here:
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            cardioVascularSave();
            cardioVascularClear();
        }
    }//GEN-LAST:event_saveCadiovascularActionPerformed

    private void updateAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction7ActionPerformed
        //                txtPulseRate1.setText("0");
        //               txtPulseOximity1.setText("0");
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction7ActionPerformed

    private void clearAction8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAction8ActionPerformed

    private void helpAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction7ActionPerformed

    private void txtPulseRate1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPulseRate1CaretUpdate
        // TODO add your handling code here:

        pulseRate(txtPulseRate1, pulseRateProgessBar2, regularchk, irregularchk);
    }//GEN-LAST:event_txtPulseRate1CaretUpdate

    private void regularchkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regularchkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regularchkActionPerformed

    private void txtPulseOximity1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPulseOximity1CaretUpdate
        // TODO add your handling code here:

        pulseOximity(txtPulseOximity1, pulseOximityTxt1);
    }//GEN-LAST:event_txtPulseOximity1CaretUpdate

    private void saveGastrointestinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGastrointestinalActionPerformed
        // TODO add your handling code here:
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            gastroIntestinalSave();
            gastroIntestinalClear();
        }
    }//GEN-LAST:event_saveGastrointestinalActionPerformed

    private void updateAction8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction8ActionPerformed

    private void clearAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction9ActionPerformed
        // TODO add your handling code here:
        gastroIntestinalClear();
    }//GEN-LAST:event_clearAction9ActionPerformed

    private void helpAction8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction8ActionPerformed

    private void vomitingcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vomitingcmbActionPerformed
        // TODO add your handling code here:

        String athas = vomitingcmb.getSelectedItem().toString();
        if (athas == "Non-Projectile Vomiting" || athas == "Projectile Vomiting") {
            yeslabel.setText(athas);
            yescmb.setEnabled(true);
        } else {
            yeslabel.setText("");
            yescmb.setEnabled(false);
        }
    }//GEN-LAST:event_vomitingcmbActionPerformed

    private void yescmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yescmbItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yescmbItemStateChanged

    private void yescmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yescmbActionPerformed
        // TODO add your handling code here:
        //xxxxxxx
        String athas = yescmb.getSelectedItem().toString();
        if (athas == "Others") {
            others.setText(athas);
            otherstxt.setEditable(true);
        } else {
            others.setText("");
            otherstxt.setEditable(false);
        }
    }//GEN-LAST:event_yescmbActionPerformed

    private void saveGenitourinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGenitourinaryActionPerformed
        // TODO add your handling code here:
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            genitourinarySave();
            genitourinaryClear();
        }
    }//GEN-LAST:event_saveGenitourinaryActionPerformed

    private void updateAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction9ActionPerformed

    }//GEN-LAST:event_updateAction9ActionPerformed

    private void clearAction10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction10ActionPerformed
        // TODO add your handling code here:
        genitourinaryClear();
    }//GEN-LAST:event_clearAction10ActionPerformed

    private void helpAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction9ActionPerformed

    private void externalcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_externalcmbActionPerformed
        // TODO add your handling code here:
        if (externalcmb.getSelectedIndex() == 2) {
            abnormalitytxtarea.setEditable(true);
        }
    }//GEN-LAST:event_externalcmbActionPerformed

    private void lmpnaturecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lmpnaturecmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lmpnaturecmbActionPerformed

    private void extremitiesSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extremitiesSaveActionPerformed
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            extremetieSave();
            extremetiesClear();
        }
    }//GEN-LAST:event_extremitiesSaveActionPerformed

    private void updateAction10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction10ActionPerformed

    private void clearAction11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction11ActionPerformed
        // TODO add your handling code here:
        extremetiesClear();
    }//GEN-LAST:event_clearAction11ActionPerformed

    private void helpAction10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction10ActionPerformed

    private void deformitycmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deformitycmbActionPerformed

        if (deformitycmb.getSelectedItem().toString() == "YES") {
            deformitytxtarea.setEditable(true);
        } else {
            deformitytxtarea.setEditable(false);
            deformitytxtarea.setText("");
        }
    }//GEN-LAST:event_deformitycmbActionPerformed

    private void lessionschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessionschkActionPerformed
        // TODO add your handling code here:
        if (lessionschk.isSelected()) {
            integrities = integrities + "" + lessionschk.getActionCommand() + ",";
        } else if (!lessionschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_lessionschkActionPerformed

    private void infestationschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infestationschkActionPerformed
        // TODO add your handling code here:

        if (infestationschk.isSelected()) {
            integrities = integrities + "" + infestationschk.getActionCommand() + ",";
        } else if (!infestationschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_infestationschkActionPerformed

    private void bruiseschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bruiseschkActionPerformed
        // TODO add your handling code here:

        if (bruiseschk.isSelected()) {
            integrities = integrities + "" + bruiseschk.getActionCommand() + ",";
        } else if (!bruiseschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_bruiseschkActionPerformed

    private void rasheschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rasheschkActionPerformed
        // TODO add your handling code here:
        if (rasheschk.isSelected()) {
            integrities = integrities + "" + rasheschk.getActionCommand() + ",";
        } else if (!rasheschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_rasheschkActionPerformed

    private void scarschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scarschkActionPerformed
        // TODO add your handling code here:
        if (scarschk.isSelected()) {
            integrities = integrities + "" + scarschk.getActionCommand() + ",";
        } else if (!scarschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_scarschkActionPerformed

    private void burnschkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burnschkActionPerformed
        // TODO add your handling code here
        if (burnschk.isSelected()) {
            integrities = integrities + "" + burnschk.getActionCommand() + ",";
        } else if (!burnschk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_burnschkActionPerformed

    private void pressuresorechkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressuresorechkActionPerformed
        // TODO add your handling code here:
        if (pressuresorechk.isSelected()) {
            integrities = integrities + " " + "Pressure Sore" + ",";
            /* Create and display the dialog */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    BedSoreAssesment dialog = new BedSoreAssesment(new javax.swing.JFrame(), true, connectDB, pConnDB, nameNoTxt.getText(), com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()), visitId);
                    dialog.setTitle("Pressure Ulcer Preventive Protocol");
                    dialog.setBounds(350, 150, 600, 400);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                }
            });
        } else if (!pressuresorechk.isSelected()) {
            integrities = " ";
        }
    }//GEN-LAST:event_pressuresorechkActionPerformed

    private void colorcmbComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_colorcmbComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_colorcmbComponentAdded

    private void colorcmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_colorcmbItemStateChanged
        //        // TODO add your handling code here:
        ////        if (jComboBox1.getSelectedIndex()==-1){
        ////            jTextArea2.setEditable(false);
        //
        //        }
    }//GEN-LAST:event_colorcmbItemStateChanged

    private void colorcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorcmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorcmbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //integrity
        if (nameNoTxt.getText().equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "First select patient.\n No patient selected", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else {
            saveIntegumentary();
            clearIntegumentry();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void systemicAssesmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_systemicAssesmentMouseClicked
        // TODO add your handling code here:
        if (systemicAssesment.getSelectedIndex() == 4) {
            System.out.println(gender);
            if (gender == "Female") {
                //            lmpdate
                //                    menarch
                //                    lmpnaturecmb
                jPanel6.enable(true);
            } else if (gender == "Male") {
                jPanel6.enable(false);
            }
        }
    }//GEN-LAST:event_systemicAssesmentMouseClicked

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        com.afrisoftech.nursingreports.TurningChartPdf policy = new com.afrisoftech.nursingreports.TurningChartPdf();

        policy.TurningChartPdf(connectDB, nameNoTxt.getText(), visitId, patient_name); // Add your handling code here:
    }//GEN-LAST:event_jButton57ActionPerformed

    private void newAction10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction10ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {

            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into nursing.turning_chart"
                    + "(patient_no,visit_id,positions,remarks,transaction_date,action_user,server_date,ward)"
                    + " values(?,?,initcap(?),initcap(?),?,?,localtimestamp,?)");

            pstmt1.setObject(1, nameNoTxt.getText());
            pstmt1.setObject(2, visitId);
            pstmt1.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBox2.getSelectedItem()));
            pstmt1.setObject(4, jTextArea2.getText());
            pstmt1.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt1.setObject(6, user);
            pstmt1.setObject(7, ward);

            pstmt1.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            nursesRptCardex.setText("");
        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

        }
    }//GEN-LAST:event_newAction10ActionPerformed

    private void updateAction13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction13ActionPerformed

    private void clearAction14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction14ActionPerformed
        // TODO add your handling code here:
        jTextArea2.setText("");
        jComboBox2.setSelectedIndex(0);
    }//GEN-LAST:event_clearAction14ActionPerformed

    private void helpAction11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction11ActionPerformed

    private void jComboBoxIvTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxIvTypeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIvTypeItemStateChanged

    private void jComboBoxIvTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIvTypeActionPerformed

        if (jComboBoxIvType.getSelectedItem().toString().equalsIgnoreCase("others")) {
            jComboBoxIvType.setEditable(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIvTypeActionPerformed

    private void iv_amountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_iv_amountStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_iv_amountStateChanged

    private void iv_amountHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_iv_amountHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_iv_amountHierarchyChanged

    private void ivRateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ivRateStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ivRateStateChanged

    private void iv_instructionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iv_instructionsMouseClicked
        // TODO add your handling code here:
        iv_instructions.setText("");
    }//GEN-LAST:event_iv_instructionsMouseClicked

    private void txtTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimeMouseClicked
        // TODO add your handling code here:
        TimeChooser dlg = new TimeChooser(new testing(), true);
        dlg.setLocation(400, 350);
        dlg.setVisible(true);
        txtTime.setText(dlg.getHHMM());
    }//GEN-LAST:event_txtTimeMouseClicked

    private void oral_fluids_instructionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oral_fluids_instructionsMouseClicked
        // TODO add your handling code here:
        oral_fluids_instructions.setText("");
    }//GEN-LAST:event_oral_fluids_instructionsMouseClicked

    private void naso_instructionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naso_instructionsMouseClicked
        // TODO add your handling code here:
        naso_instructions.setText("");
    }//GEN-LAST:event_naso_instructionsMouseClicked

    private void newAction8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction8ActionPerformed
        // TODO add your handling code here:
        compute();

        try {

            dbObject = new com.afrisoftech.lib.DBObject();
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into nursing.fluid_chart_input"
                    + "(patient_id ,visit_id,iv_type,iv_rate,iv_amount,iv_time_start"
                    + ",oral_type,oral_amount,naso_gastric,peg_feed,iv_instructions,oral_fluids_instructions,"
                    + "naso_instructions,action_user,action_time,transaction_date)"
                    + " values(?,?,initcap(?),?,?,"
                    + "?::time,initcap(?),?,?,?,"
                    + "initcap(?),initcap(?),initcap(?),initcap(?),localtimestamp,?)");

            pstmt1.setObject(1, nameNoTxt.getText());
            pstmt1.setObject(2, visitId);
            pstmt1.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxIvType.getSelectedItem()));
            pstmt1.setObject(4, Double.parseDouble(ivRate.getValue().toString()));
            pstmt1.setObject(5, Double.parseDouble(iv_amount.getValue().toString()));
            pstmt1.setObject(6, dbObject.getDBObject(txtTime.getText(), "0:00"));
            pstmt1.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxOral_type.getSelectedItem()));
            pstmt1.setObject(8, Double.parseDouble(oral_amount.getValue().toString()));
            pstmt1.setObject(9, Double.parseDouble(naso_gastric.getValue().toString()));
            pstmt1.setObject(10, Double.parseDouble(peg_feed.getValue().toString()));
            pstmt1.setObject(11, iv_instructions.getText());
            pstmt1.setObject(12, oral_fluids_instructions.getText());
            pstmt1.setObject(13, naso_instructions.getText());
            pstmt1.setObject(14, user);
            pstmt1.setDate(15, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt1.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

        }
        jComboBoxIvType.setSelectedIndex(0);
        ivRate.setValue(0);
        iv_amount.setValue(0);
        txtTime.setText("");
        jComboBoxOral_type.setSelectedIndex(0);
        oral_amount.setValue(0);
        naso_gastric.setValue(0);
        peg_feed.setValue(0);
        iv_instructions.setText("Instructions for intravenous infusion");
        oral_fluids_instructions.setText("Instructions for Oral Fluid");
        naso_instructions.setText("Instructions for NASOGASTRIC SUCTION");
        totalTxt.setText("0");

        fluidChartReload();
    }//GEN-LAST:event_newAction8ActionPerformed

    private void clearAction12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction12ActionPerformed
        // TODO add your handling code here:
        jComboBoxIvType.setSelectedIndex(0);
        ivRate.setValue(0);
        iv_amount.setValue(0);
        txtTime.setText("");
        jComboBoxOral_type.setSelectedIndex(0);
        oral_amount.setValue(0);
        naso_gastric.setValue(0);
        peg_feed.setValue(0);
        iv_instructions.setText("Instructions for intravenous infusion");
        oral_fluids_instructions.setText("Instructions for Oral Fluid");
        naso_instructions.setText("Instructions for NASOGASTRIC SUCTION");
        totalTxt.setText("0");
    }//GEN-LAST:event_clearAction12ActionPerformed

    private void helpAction12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction12ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        compute();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void drainscmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drainscmb1ActionPerformed
        // TODO add your handling code here:
        drainCaption.show();
        if (drainscmb1.getSelectedIndex() == 1) {
            drain1txt1.show();
            lblDrain1.show();
            drain2txt1.hide();
            drain2txt1.setValue(0);
            lblDrain2.hide();
            drain3txt1.hide();
            drain3txt1.setValue(0);
            lblDrain3.hide();
            drain4txt1.hide();
            drain4txt1.setValue(0);
            lblDrain4.hide();
            drain5txt1.hide();
            drain5txt1.setValue(0);
            lblDrain5.hide();

            caption1.show();
            caption2.hide();
            caption3.hide();
            caption4.hide();
            caption5.hide();
            mls1.show();
            mls2.hide();
            mls3.hide();
            mls4.hide();
            mls5.hide();
        } else if (drainscmb1.getSelectedIndex() == 2) {
            drain3txt1.setValue(0);
            drain4txt1.setValue(0);
            drain5txt1.setValue(0);

            caption1.show();
            caption2.show();
            caption3.hide();
            caption4.hide();
            caption5.hide();

            mls1.show();
            mls2.show();
            mls3.hide();
            mls4.hide();
            mls5.hide();
            drain1txt1.show();
            lblDrain1.show();
            drain2txt1.show();
            lblDrain2.show();
            drain3txt1.hide();
            lblDrain3.hide();
            drain4txt1.hide();
            lblDrain4.hide();
            drain5txt1.hide();
            lblDrain5.hide();
        } else if (drainscmb1.getSelectedIndex() == 3) {
            mls1.show();
            mls2.show();
            mls3.show();
            mls4.hide();
            mls5.hide();

            drain4txt1.setValue(0);
            drain5txt1.setValue(0);

            caption1.show();
            caption2.show();
            caption3.show();
            caption4.hide();
            caption5.hide();

            drain1txt1.show();
            lblDrain1.show();
            drain2txt1.show();
            lblDrain2.show();
            drain3txt1.show();
            lblDrain3.show();
            drain4txt1.hide();
            lblDrain4.hide();
            drain5txt1.hide();
            lblDrain5.hide();
        } else if (drainscmb1.getSelectedIndex() == 4) {
            mls1.show();
            mls2.show();
            mls3.show();
            mls4.show();
            mls5.hide();

            drain5txt1.setValue(0);

            caption1.show();
            caption2.show();
            caption3.show();
            caption4.show();
            caption5.hide();

            drain1txt1.show();
            lblDrain1.show();
            drain2txt1.show();
            lblDrain2.show();
            drain3txt1.show();
            lblDrain3.show();
            drain4txt1.show();
            lblDrain4.show();
            drain5txt1.hide();
            lblDrain5.hide();
        } else if (drainscmb1.getSelectedIndex() == 5) {
            mls1.show();
            mls2.show();
            mls3.show();
            mls4.show();
            mls5.show();

            caption1.show();
            caption2.show();
            caption3.show();
            caption4.show();
            caption5.show();

            drain1txt1.show();
            lblDrain1.show();
            drain2txt1.show();
            lblDrain2.show();
            drain3txt1.show();
            lblDrain3.show();
            drain4txt1.show();
            lblDrain4.show();
            drain5txt1.show();
            lblDrain5.show();
        } else if (drainscmb1.getSelectedIndex() == 0) {
            hideDrains();
        }
    }//GEN-LAST:event_drainscmb1ActionPerformed

    private void newAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction9ActionPerformed

        // TODO add your handling code here:
        // Double accum=fluidChart();
        compute2();
        // Double tAccum=accum-Double.parseDouble(totalTxt1.getText());

        String urineStatus = null;
        String stoolStatus = null;
        if (jCheckBoxStoolYes.isSelected()) {
            stoolStatus = "yes";
        } else if (jCheckBoxStoolNo.isSelected()) {
            stoolStatus = "no";
        }

        if (wetbedchk1.isSelected()) {
            urineStatus = "wet-bed";
        } else if (wetdiaperchk1.isSelected()) {
            urineStatus = "wet-diaper";
        } else if (visittoiletchk1.isSelected()) {
            urineStatus = "visited-toilet";
        }
        try {

            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into nursing.fluid_chart_output"
                    + "(patient_no ,visit_id,urine_state,urine_amount,ng_sanction,vomitus"
                    + ",stool,drain_one,drain_one_amount,drain_two,drain_two_amount,drain_three,drain_three_amount,"
                    + "drain_four,drain_four_amount,drain_five,drain_five_amount,"
                    + "action_time,action_user,transaction_date)"
                    + " values(?,?,initcap(?),?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,localtimestamp,?,?)");
            pstmt1.setObject(1, nameNoTxt.getText());
            pstmt1.setObject(2, visitId);
            pstmt1.setObject(3, urineStatus);
            pstmt1.setObject(4, Double.parseDouble(urine_amount.getValue().toString()));
            pstmt1.setObject(5, Double.parseDouble(ngSuction.getValue().toString()));
            pstmt1.setObject(6, Double.parseDouble(vomitus.getValue().toString()));
            pstmt1.setObject(7, stoolStatus);
            pstmt1.setObject(9, Double.parseDouble(drain1txt1.getValue().toString()));
            pstmt1.setObject(8, caption1.getText());
            pstmt1.setObject(11, Double.parseDouble(drain2txt1.getValue().toString()));
            pstmt1.setObject(10, caption2.getText());
            pstmt1.setObject(13, Double.parseDouble(drain3txt1.getValue().toString()));
            pstmt1.setObject(12, caption3.getText());
            pstmt1.setObject(15, Double.parseDouble(drain4txt1.getValue().toString()));
            pstmt1.setObject(14, caption4.getText());
            pstmt1.setObject(17, Double.parseDouble(drain5txt1.getValue().toString()));
            pstmt1.setObject(16, caption5.getText());
            pstmt1.setObject(18, user);
            pstmt1.setObject(19, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));

            pstmt1.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

        }
        urine_amount.setValue(0);
        ngSuction.setValue(0);
        vomitus.setValue(0);
        drain1txt1.setValue(0);
        caption1.setText("");
        drain2txt1.setValue(0);
        caption2.setText("");
        drain3txt1.setValue(0);
        caption3.setText("");
        drain4txt1.setValue(0);
        caption4.setText("");
        drain5txt1.setValue(0);
        caption5.setText("");
        totalTxt1.setText("0");
        wetbedchk1.setSelected(false);
        wetdiaperchk1.setSelected(false);
        visittoiletchk1.setSelected(false);
        jCheckBoxStoolNo.setSelected(false);

        fluidChartReload();
    }//GEN-LAST:event_newAction9ActionPerformed

    private void updateAction12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction12ActionPerformed
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  Index0=table query Index1=delete query Index2=update index 3=visit id index 4=user index 5=reference_table index 6=reference
                Vector vee = new Vector();

                vee.addElement("SELECT transaction_date,urine_state,urine_amount,ng_sanction,vomitus,stool,drain_one,drain_one_amount,"
                        + "drain_two,drain_two_amount,drain_three,drain_three_amount,drain_four,drain_four_amount,drain_five,"
                        + "drain_five_amount,action_time,action_user FROM nursing.fluid_chart_output where patient_id='" + nameNoTxt.getText() + "' AND visit_id='" + visitId + "'");
                vee.addElement("delete from nursing.fluid_chart_output where transaction_date=");
                vee.addElement("update nursing.fluid_chart_output SET "
                        + "transaction_date=?,iv_type=?,iv_rate=?,iv_time_start=?,iv_instructions=?,oral_type=?,"
                        + "oral_amount=?,oral_fluids_instructions=?,naso_gastric=?,naso_instructions=?,peg_feed=? "
                        + " where transaction_date=");
                vee.addElement(visitId);
                vee.addElement(user);
                vee.addElement("nursing.fluid_chart_input");
                vee.addElement("fluid_chart_intake");

                EditNursing dialog = new EditNursing(new javax.swing.JFrame(), true, vee, getconnection());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                });
                dialog.setVisible(true);
            }
        });
        // TODO add your handling code here:
    }//GEN-LAST:event_updateAction12ActionPerformed

    private void clearAction13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction13ActionPerformed
        // TODO add your handling code here:
        urine_amount.setValue(0);
        ngSuction.setValue(0);
        vomitus.setValue(0);
        drain1txt1.setValue(0);
        caption1.setText("");
        drain2txt1.setValue(0);
        caption2.setText("");
        drain3txt1.setValue(0);
        caption3.setText("");
        drain4txt1.setValue(0);
        caption4.setText("");
        drain5txt1.setValue(0);
        caption5.setText("");
        totalTxt1.setText("0");
        wetbedchk1.setSelected(false);
        wetdiaperchk1.setSelected(false);
        visittoiletchk1.setSelected(false);
        jCheckBoxStoolNo.setSelected(false);
    }//GEN-LAST:event_clearAction13ActionPerformed

    private void helpAction13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction13ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        compute2();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void headerDatePicker3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerDatePicker3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_headerDatePicker3MouseClicked

    private void headerDatePicker3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerDatePicker3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_headerDatePicker3MouseEntered

    private void headerDatePicker3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerDatePicker3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_headerDatePicker3MouseExited

    private void headerDatePicker3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerDatePicker3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_headerDatePicker3MousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        fluidChartReload2();
        getOutputsFluid2();
        jTable6.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select transaction_date as date,concat(iv_type,' ',iv_amount,'ml') as Intravenous,concat(oral_type,' ',oral_amount,'ml') as Oral,concat(naso_gastric,'ml') as Naso_Gastric,concat(peg_feed,'ml') as Peg_feed,concat((iv_amount+oral_amount+naso_gastric+peg_feed),'ml') as Total from nursing.fluid_chart_input where patient_id='" + nameNoTxt.getText() + "' AND visit_id='" + visitId + "' AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'"));
        jScrollPane22.setViewportView(jTable6);

        jTable7.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select transaction_date as date,concat(urine_amount,'ml') as Urine,concat(ng_sanction,'ml') as NG_Sanction,concat((drain_one_amount+drain_two_amount+drain_three_amount+drain_four_amount+drain_five_amount),'ml') as Drains ,concat(vomitus,'ml') as Vomitus,concat((urine_amount+ng_sanction+vomitus+drain_one_amount+drain_two_amount+drain_three_amount+drain_four_amount+drain_five_amount),'ml') as Total from nursing.fluid_chart_output where patient_no='" + nameNoTxt.getText() + "' AND visit_id='" + visitId + "'"
                + " AND (urine_amount!=0 OR ng_sanction!=0 OR vomitus!=0 OR drain_one_amount!=0 OR drain_two_amount!=0 OR drain_three_amount!=0 OR drain_four_amount!=0 OR drain_five_amount!=0) AND transaction_date ILIKE '%" + headerDatePicker3.getDate().toLocaleString().substring(0, 12) + "%'"));
        jScrollPane23.setViewportView(jTable7);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Vector careDetails = new Vector();
            careDetails.removeAllElements();
            careDetails.addElement(nameNoTxt.getText());
            careDetails.addElement(visitId);
            careDetails.addElement(ward);
            careDetails.addElement(com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            careDetails.addElement(patientDetailsTextArea.getText().trim());
            biz.systempartners.reports.FluidChartReporter reporter = new biz.systempartners.reports.FluidChartReporter(connectDB, careDetails);//connectDB, pConnDB);

            saccopn.add(reporter, javax.swing.JLayeredPane.DEFAULT_LAYER);
            try {
                reporter.setSize(saccopn.getSize());
                reporter.setSelected(true);
            } catch (java.beans.PropertyVetoException pvt) {
            }
        } catch (Exception vector) {
            vector.printStackTrace();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void packkComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_packkComboItemStateChanged
        if (packkCombo.getSelectedIndex() == 0) {
            confimedRbtn.setEnabled(false);
            nonConfirmedRbt.setEnabled(false);
            confimedRbtn.setSelected(false);
            nonConfirmedRbt.setSelected(false);
        } else {
            confimedRbtn.setEnabled(true);
            nonConfirmedRbt.setEnabled(true);
        }
    }//GEN-LAST:event_packkComboItemStateChanged

    private void packTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packTableMouseClicked
        if (packkCombo.getSelectedIndex() == 1) {
            if (confimedRbtn.isSelected() == true) {
                String date = null;
                try {
                    java.lang.Boolean transfusionState = false;
                    java.sql.Statement st112 = connectDB.createStatement();
                    java.sql.ResultSet rs112 = st112.executeQuery(""
                            + "SELECT date_transfused,transfusion_state from nursing.blood_pack_verification  WHERE pack_no='" + packTable.getValueAt(packTable.getSelectedRow(), 0).toString().trim() + "'");
                    while (rs112.next()) {
                        date = rs112.getString(1);
                        transfusionState = rs112.getBoolean(2);
                    }
                    if (transfusionState == true) {
                        bloodtransfusionbtn.setVisible(false);
                    } else if (transfusionState == false) {
                        bloodtransfusionbtn.setVisible(true);
                        System.out.println("\n\n\n\nstatus ya hapa ni false and the button should appear\n\n\n");
                        if (date == null) {
                            date = com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()).toString();
                        } else if (date != null) {
                            String packN = packTable.getValueAt(packTable.getSelectedRow(), 0).toString().trim();
                            PreparedStatement prest1 = connectDB.prepareStatement("SELECT "
                                    + "  blood_transfusion.transfusion_status, blood_transfusion.started_by, transfusion_rate,  blood_transfusion.start_time, blood_transfusion.counter_checkedby "
                                    + "FROM "
                                    + "  nursing.blood_transfusion "
                                    + " where   ward='" + ward + "' and  blood_unit_pack='" + packN + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' order by time_recorded desc limit 1");
                            ResultSet rs = prest1.executeQuery();

                            if (rs.next()) {
                                System.out.println("transfusion_status " + rs.getString("transfusion_status") + " \n\n\n");
                                if (!rs.getString("transfusion_status").equalsIgnoreCase("Complete")) {
                                    starttransfusion.setEnabled(false);
                                    starttime.setText(rs.getString(4));
                                    transfusionrate.setText(rs.getString(3));
                                    startedby.setText(rs.getString(2));
                                    countercheck.setText(rs.getString(5));
                                } else if (rs.getString("transfusion_status").equalsIgnoreCase("Complete")) {
                                    bloodtransfusionbtn.setEnabled(false);
                                    starttransfusion.setEnabled(false);
                                    starttime.setText(rs.getString(4));
                                    transfusionrate.setText(rs.getString(3));
                                    startedby.setText(rs.getString(2));
                                    countercheck.setText(rs.getString(5));
                                }
                            }
                            observationchart.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                                    + "SELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                                    + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                                    + "  FROM   nursing.blood_transfusion "
                                    + " where ward='" + ward + "' and  blood_unit_pack='" + packno.getText() + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' "));
                        }
                    }
                    this.transfusiondate.setText(date);
                    this.packno.setText(packTable.getValueAt(packTable.getSelectedRow(), 0).toString().trim());
                    this.confirmationdetails.setText(packTable.getValueAt(packTable.getSelectedRow(), 1).toString().trim());
                    this.bloodtype.setText(packTable.getValueAt(packTable.getSelectedRow(), 11).toString().trim());
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nSELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                            + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                            + "  FROM   nursing.blood_transfusion "
                            + " where ward='" + ward + "' and  blood_unit_pack='" + packno.getText() + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' ");
                    observationchart.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                            + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                            + "  FROM   nursing.blood_transfusion "
                            + " where ward='" + ward + "' and  blood_unit_pack='" + packno.getText() + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' "));

                    System.out.println("the pack information is " + (date) + ""
                            + "" + (packTable.getValueAt(packTable.getSelectedRow(), 0).toString().trim())
                            + "" + (packTable.getValueAt(packTable.getSelectedRow(), 1).toString().trim())
                            + "" + (packTable.getValueAt(packTable.getSelectedRow(), 11).toString().trim()));
                } catch (Exception sq) {
                    System.out.println("the pack click error is " + sq);
                    sq.printStackTrace();

                }
            }
            if (true == nonConfirmedRbt.isSelected()) {
                if (this.packTable.getSelectedColumn() == 2) {
                    if (this.packTable.getValueAt(packTable.getSelectedRow(), 0).toString().trim().equalsIgnoreCase("true")) {
                        java.awt.Point point = this.packTable.getLocationOnScreen();

                        usersSearchdialog.setSize(400, 200);

                        usersSearchdialog.setLocation(point);

                        usersSearchdialog.setVisible(true);

                    }
                }
            }

        }
    }//GEN-LAST:event_packTableMouseClicked

    private void confimedRbtnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_confimedRbtnItemStateChanged

        if (confimedRbtn.isSelected() == Boolean.TRUE) {

            nonConfirmedRbt.setSelected(false);
            bloodpackbtn.setEnabled(false);
            if (packkCombo.getSelectedIndex() == 2) {
                String packQUery = "SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, \n"
                        + "       date_transfused, done_by, ward,'Confirmed' as confirmation, date_recorded, \n"
                        + "       blood_type\n"
                        + "  FROM nursing.blood_pack_verification where confirmation=true;";
                System.out.println(packQUery);

                packTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, \n"
                        + "       date_transfused, done_by, ward,'Confirmed' as confirmation, date_recorded, \n"
                        + "       blood_type\n"
                        + "  FROM nursing.blood_pack_verification where confirmation=true;"));
            } else if (packkCombo.getSelectedIndex() == 1) {
                String packQuery = "SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, \n"
                        + "       date_transfused, done_by, ward,'Confirmed' as confirmation, date_recorded, \n"
                        + "       blood_type\n"
                        + "  FROM nursing.blood_pack_verification where confirmation=true and patient_no='" + nameNoTxt.getText() + "';";
                System.out.println(packQuery);

                packTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT pack_no, pack_details, patient_no, visit_id, blood_group, blood_rhesus, \n"
                        + "       date_transfused, done_by, ward,'Confirmed' as confirmation, date_recorded, \n"
                        + "       blood_type\n"
                        + "  FROM nursing.blood_pack_verification where confirmation=true and patient_no='" + nameNoTxt.getText() + "';"));
            }
        }
    }//GEN-LAST:event_confimedRbtnItemStateChanged

    private void nonConfirmedRbtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nonConfirmedRbtItemStateChanged

        if (nonConfirmedRbt.isSelected() == Boolean.TRUE) {
            confimedRbtn.setSelected(false);
            bloodpackbtn.setEnabled(true);

            packTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT false as Confirm_Pack,pack_no,'' as Checked_by,(select current_user) as To_Be_Confirmed_BY, pack_details,"
                    + " blood_group, blood_rhesus,  blood_type"
                    + " FROM nursing.blood_pack_verification where confirmation=false "));

        }
    }//GEN-LAST:event_nonConfirmedRbtItemStateChanged

    private void bloodpackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodpackbtnActionPerformed

        if ((nameNoTxt.getText().length() > 0) && (nonConfirmedRbt.isSelected() == true)) {
            try {
                String user = null;
                java.sql.Statement st112 = connectDB.createStatement();
                java.sql.ResultSet rs112 = st112.executeQuery("SELECT CURRENT_USER");
                while (rs112.next()) {

                    user = rs112.getObject(1).toString();

                }

                connectDB.setAutoCommit(false);

                for (int i = 0; i < packTable.getModel().getRowCount(); i++) {
                    if (packTable.getModel().getValueAt(i, 0).toString().trim().equalsIgnoreCase("true")) {

                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement(""
                                + "UPDATE nursing.blood_pack_verification "
                                + "   SET  patient_no=?, visit_id=?,   ward=?, confirmation=?, "
                                + "        confirmed_by=?,  checked_by=?, confirmation_date=?, confirmation_entry=localtimestamp"
                                + " WHERE pack_no='" + packTable.getModel().getValueAt(i, 1).toString().trim() + "'");
                        pstmt.setObject(1, nameNoTxt.getText());
                        pstmt.setObject(2, visitId);
                        pstmt.setObject(3, ward);
                        pstmt.setBoolean(4, true);
                        pstmt.setObject(5, user);
                        pstmt.setObject(6, packTable.getModel().getValueAt(i, 2).toString().trim());
                        pstmt.setObject(7, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));

                        pstmt.executeUpdate();
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Confirmed Successfully", "", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                connectDB.commit();
                connectDB.setAutoCommit(true);

            } catch (Exception packinsert) {
                javax.swing.JOptionPane.showMessageDialog(this, "Confirmation Not Successfull.", "", javax.swing.JOptionPane.ERROR_MESSAGE);
                System.out.println("Pack insert error is " + packinsert);
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Ensure You select\n ONLY ONE PATIENT AT A TIME\n !!.", "", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_bloodpackbtnActionPerformed

    private void bloodtransfusionbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodtransfusionbtnActionPerformed
        if (nameNoTxt.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Enter Your NO first", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        } else if (!nameNoTxt.getText().isEmpty()) {
            try {
                connectDB.setAutoCommit(false);

                try {
                    java.lang.String state = null;
                    java.sql.Statement st112 = connectDB.createStatement();
                    java.sql.ResultSet rs112 = st112.executeQuery(""
                            + "SELECT date_transfused from nursing.blood_pack_verification  WHERE pack_no='" + packno.getText().trim() + "'");
                    while (rs112.next()) {
                        state = rs112.getString(1);

                    }

                    if (state == null) {
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement(""
                                + "UPDATE nursing.blood_pack_verification"
                                + "   SET date_transfused=? "
                                + " WHERE pack_no=?;");
                        pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                        pstmt.setObject(2, packno.getText());

                        pstmt.executeUpdate();
                    }
                } catch (Exception bloodpack) {
                    bloodpack.printStackTrace();
                    System.err.println("the bloodpack error is " + bloodpack);
                }

                if (symptomscheck.isSelected() == Boolean.FALSE) {
                    try {
                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO nursing.blood_transfusion "
                                + "(patient_no, visit_id, date_recoded, time_recorded, date_of_transfusion, \n"
                                + "            blood_unit_pack, blood_type, start_time, transfusion_rate, started_by, \n"
                                + "            counter_checkedby, bp, temp, respiratry_rate, pulse_rate, exact_time, \n"
                                + "            time_ended, remarks, signed_ofby, transfusion_status, done_by,ward,hourly_rate)"
                                + "   VALUES (?, ?, ?, localtimestamp, ?::date, ?, ?, ?::time, ?, ?, ?, ?, ?, ?, ?, ?::time, ?::time, ?, ?,?,?, ?, ?);");

                        pstmt.setObject(1, nameNoTxt.getText());
                        pstmt.setObject(2, visitId);
                        pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                        pstmt.setObject(4, transfusiondate.getText());
                        pstmt.setObject(5, packno.getText());
                        pstmt.setObject(6, bloodtype.getText());
                        pstmt.setObject(7, starttime.getText());
                        pstmt.setObject(8, transfusionrate.getText());
                        pstmt.setObject(9, startedby.getText());
                        pstmt.setObject(10, countercheck.getText());
                        pstmt.setObject(11, bp.getText());
                        pstmt.setObject(12, temp.getText());
                        pstmt.setObject(13, rr.getText());
                        pstmt.setObject(14, pr.getText());
                        pstmt.setObject(15, exacttime.getText());
                        if (endtransfusion.isSelected() == Boolean.TRUE) {
                            pstmt.setObject(16, endtime.getText());
                        } else if (endtransfusion.isSelected() == Boolean.FALSE) {
                            pstmt.setObject(16, null);
                        }
                        pstmt.setObject(17, remarks.getText());
                        pstmt.setObject(18, signedby.getText());
                        pstmt.setObject(19, transfusionStatus);
                        pstmt.setObject(20, user);
                        pstmt.setObject(21, ward);
                        pstmt.setObject(22, hourlyrate.getText());

                        pstmt.executeUpdate();
                        starttransfusion.setEnabled(false);
                        starttransfusion.setSelected(false);
                        if (endtransfusion.isSelected() == Boolean.TRUE) {
                            bloodUpdate(packno.getText());
                        }
                        javax.swing.JOptionPane.showMessageDialog(this, "Blood Transfusion Details saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    } catch (SQLException bloodinsert) {
                        bloodinsert.printStackTrace();
                        System.out.println("the blood transfusion insert is " + bloodinsert);
                    }

                } else if (symptomscheck.isSelected() == true) {
                    try {

                        java.sql.PreparedStatement symptomspstmt = connectDB.prepareStatement(""
                                + "INSERT INTO nursing.blood_symptoms(\n"
                                + "            patient_no, visit_id, date_recoded, time_recorded, date_of_transfusion, \n"
                                + "            blood_unit_pack, blood_type, started_by, counter_checkedby, general, \n"
                                + "            cardaic_respiratory, haematological, dermatological, renal, others, \n"
                                + "            done_by,ward)\n"
                                + "    VALUES (?, ?, ?, localtimestamp, ?::date,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?);"
                                + "");

                        symptomspstmt.setObject(1, nameNoTxt.getText());
                        symptomspstmt.setObject(2, visitId);
                        symptomspstmt.setObject(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                        symptomspstmt.setObject(4, transfusiondate.getText());
                        symptomspstmt.setObject(5, packno.getText());
                        symptomspstmt.setObject(6, bloodtype.getText());
                        symptomspstmt.setObject(7, startedby.getText());
                        symptomspstmt.setObject(8, countercheck.getText());
                        symptomspstmt.setObject(9, new com.afrisoftech.nursing.NursingObject().NursingObject(general.getSelectedItem()));
                        symptomspstmt.setObject(10, new com.afrisoftech.nursing.NursingObject().NursingObject(cardaic.getSelectedItem()));
                        symptomspstmt.setObject(11, new com.afrisoftech.nursing.NursingObject().NursingObject(haemotological.getSelectedItem()));
                        symptomspstmt.setObject(12, new com.afrisoftech.nursing.NursingObject().NursingObject(dermatological.getSelectedItem()));
                        symptomspstmt.setObject(13, new com.afrisoftech.nursing.NursingObject().NursingObject(renal.getSelectedItem()));
                        symptomspstmt.setObject(14, othersymptoms.getText());
                        symptomspstmt.setObject(15, user);
                        symptomspstmt.setObject(16, ward);

                        symptomspstmt.executeUpdate();

                        javax.swing.JOptionPane.showMessageDialog(this, "Symptoms Details saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        if (endtransfusion.isSelected() == true) {
                            bloodtransfusionbtn.setEnabled(false);
                        }
                    } catch (Exception symp) {
                        symp.printStackTrace();
                        System.out.println("error ya symptoms ni " + symp);
                    }
                }

                connectDB.commit();
                connectDB.setAutoCommit(true);

            } catch (java.sql.SQLException sq) {
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                sq.printStackTrace();
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        System.out.println("SELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                + "  FROM   nursing.blood_transfusion "
                + " where ward='" + ward + "' and  blood_unit_pack='" + packno.getText() + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' ");

        observationchart.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                + "  FROM   nursing.blood_transfusion "
                + " where ward='" + ward + "' and  blood_unit_pack='" + packno.getText() + "' and patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' "));
    }//GEN-LAST:event_bloodtransfusionbtnActionPerformed

    private void clearbloodtransfusionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbloodtransfusionActionPerformed
        nameNoTxt.setText(null);
        packno.setText(null);
        starttime.setText(null);
        transfusionrate.setText(null);

        hourlyrate.setText(null);
        startedby.setText(null);
        countercheck.setText(null);
        bp.setText(null);
        temp.setText(null);
        rr.setText(null);
        pr.setText(null);
        remarks.setText(null);
        signedby.setText(null);

        if (symptomscheck.isSelected() == true) {

            general.setSelectedIndex(0);
            cardaic.setSelectedIndex(0);
            haemotological.setSelectedIndex(0);
            dermatological.setSelectedIndex(0);
            renal.setSelectedIndex(0);
            othersymptoms.setText(null);
        }
    }//GEN-LAST:event_clearbloodtransfusionActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        Vector careDetails = new Vector();
        careDetails.addElement(nameNoTxt.getText());
        careDetails.addElement(visitId);
        careDetails.addElement(packno.getText());
        careDetails.addElement(com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
        careDetails.addElement(populateBioData(nameNoTxt.getText()));

        com.afrisoftech.nursingreports.BloodTransfusionpdf nursingcare = new com.afrisoftech.nursingreports.BloodTransfusionpdf();
        nursingcare.BloodTransfusionpdf(connectDB, pConnDB, careDetails);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void helpAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction2ActionPerformed

    private void endtimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endtimeMouseClicked
        TimeChooser dlg = new TimeChooser(new testing(), true);
        dlg.setLocation(400, 350);
        dlg.setVisible(true);
        endtime.setText(dlg.getHHMM());
    }//GEN-LAST:event_endtimeMouseClicked

    private void endtransfusionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_endtransfusionItemStateChanged

        if (endtransfusion.isSelected() == true) {
            endtime.setEditable(true);
            amounttxtfld.setEditable(true);
            endtime.setText(null);
            amounttxtfld.setText(null);

        } else if (endtransfusion.isSelected() == false) {
            endtime.setEditable(true);
            endtime.setText(null);
            amounttxtfld.setText(null);

        }
    }//GEN-LAST:event_endtransfusionItemStateChanged

    private void exacttimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exacttimeMouseClicked

        TimeChooser dlg = new TimeChooser(new testing(), true);
        dlg.setLocation(400, 350);
        dlg.setVisible(true);
        exacttime.setText(dlg.getHHMM());
    }//GEN-LAST:event_exacttimeMouseClicked

    private void packnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packnoActionPerformed

    private void starttimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_starttimeMouseClicked

        TimeChooser dlg = new TimeChooser(new testing(), true);
        dlg.setLocation(400, 350);
        dlg.setVisible(true);
        starttime.setText(dlg.getHHMM());
    }//GEN-LAST:event_starttimeMouseClicked

    private void starttransfusionComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_starttransfusionComponentShown

    }//GEN-LAST:event_starttransfusionComponentShown

    private void starttransfusionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_starttransfusionItemStateChanged
        if (starttransfusion.isSelected() == true) {
            starttime.setEditable(true);
            startedby.setEditable(true);
            countercheck.setEditable(true);
            transfusionrate.setEditable(true);
            transfusionStatus = "Started";
        } else if (starttransfusion.isSelected() == false) {
            transfusionStatus = "Commencing";
            starttime.setEditable(false);
            startedby.setEditable(false);
            countercheck.setEditable(false);
            transfusionrate.setEditable(false);
            starttime.setText(null);
            startedby.setText(null);
            countercheck.setText(null);
            transfusionrate.setText(null);
        }
    }//GEN-LAST:event_starttransfusionItemStateChanged

    private void symptomscheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_symptomscheckItemStateChanged
        if (symptomscheck.isSelected() == true) {
            general.setEnabled(true);
            cardaic.setEnabled(true);
            haemotological.setEnabled(true);
            dermatological.setEnabled(true);
            renal.setEnabled(true);
            othersymptoms.setEnabled(true);

        }
        if (symptomscheck.isSelected() == false) {
            general.setEnabled(false);
            cardaic.setEnabled(false);
            haemotological.setEnabled(false);
            dermatological.setEnabled(false);
            renal.setEnabled(false);
            othersymptoms.setEnabled(false);
        }
    }//GEN-LAST:event_symptomscheckItemStateChanged

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
        if (jTabbedPane4.getName() == "OBSERVATION CHART") {
            observationchart.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT   exact_time, bp, temp, respiratry_rate, pulse_rate, remarks,  done_by FROM blood_transfusion WHERE patient_no='" + nameNoTxt.getText().toString().trim() + "' and  visit_id='" + visitId + "' and blood_unit_pack='" + packno.getText().toString().trim() + "' ;"));
        } else if (jTabbedPane4.getName() == "Symptoms/Signs Of Transfusion Reactions Observed") {
            if (symptomscheck.isSelected() == false) {
                javax.swing.JOptionPane.showMessageDialog(this, "SELECT SYMPTOMS BUTTON TO CONTINUE ", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked

        observationchart.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT  blood_transfusion.exact_time, blood_transfusion.bp, blood_transfusion.temp,blood_transfusion.pulse_rate,  blood_transfusion.respiratry_rate,blood_transfusion.remarks, \n"
                + "  blood_transfusion.hourly_rate, blood_transfusion.date_of_transfusion,blood_transfusion.done_by\n"
                + "  FROM   nursing.blood_transfusion "
                + " where    patient_no='" + nameNoTxt.getText().toString().trim() + "' and "
                + "blood_transfusion.date_of_transfusion::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "+1' "));
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void fluidChartTabbedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fluidChartTabbedMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (nameNoTxt.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Select Patient NO First", "Error Message!second", javax.swing.JOptionPane.ERROR_MESSAGE);
            fluidChartTabbed.setEnabled(false);
        } else if (!nameNoTxt.getText().isEmpty()) {
            fluidChartTabbed.setEnabled(true);

            if (fluidChartTabbed.getSelectedIndex() == 2) {
                fluidChartReload();
                getOutputsFluid();
                try {
                    java.sql.Statement stm12 = connectDB.createStatement();
                    java.sql.ResultSet rse12 = stm12.executeQuery(""
                            + "select count(patient_id)   "
                            + "   FROM nursing.fluid_chart_input where"
                            + " transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                            + " and patient_id='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'");

                    while (rse12.next()) {
                        if (rse12.getDouble(1) == 0) {
                            jTable6.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                                    + "select current_date as date,\n"
                                    + "'' as Intravenous,\n"
                                    + "'' as Oral,\n"
                                    + "'' as Naso_Gastric,\n"
                                    + "'' as Peg_feed,\n"
                                    + "'' as Total\n"
                                    + " from nursing.fluid_chart_input limit 1"));
                            jScrollPane22.setViewportView(jTable6);
                        } else if (rse12.getDouble(1) > 0) {
                            jTable6.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select transaction_date as date,concat(iv_type,' ',iv_amount,'ml') as Intravenous,concat(oral_type,' ',oral_amount,'ml') as Oral,concat(naso_gastric,'ml') as Naso_Gastric,concat(peg_feed,'ml') as Peg_feed,concat((iv_amount+oral_amount+naso_gastric+peg_feed),'ml') as Total from nursing.fluid_chart_input "
                                    + "where"
                                    + " transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                                    + " and patient_id='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'"));
                            jScrollPane22.setViewportView(jTable6);
                        }

                    }
                } catch (Exception table) {
                    table.printStackTrace();
                }

                try {
                    java.sql.Statement stm12 = connectDB.createStatement();
                    java.sql.ResultSet rse12 = stm12.executeQuery(""
                            + "select count(patient_no)   "
                            + "   FROM nursing.fluid_chart_output where"
                            + " transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                            + " and patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'");

                    while (rse12.next()) {
                        if (rse12.getDouble(1) == 0) {
                            jTable7.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                                    + "select current_date as date,\n"
                                    + "'' as Urine,\n"
                                    + "''  as NG_Sanction,\n"
                                    + "''  as Drains ,\n"
                                    + "''  as Vomitus,\n"
                                    + "''  as Total from nursing.fluid_chart_output  limit 1 "));
                            jScrollPane23.setViewportView(jTable7);
                        } else if (rse12.getDouble(1) > 0) {
                            jTable7.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select transaction_date as date,concat(urine_amount,'ml') as Urine,concat(ng_sanction,'ml') as NG_Sanction,concat((drain_one_amount+drain_two_amount+drain_three_amount+drain_four_amount+drain_five_amount),'ml') as Drains ,concat(vomitus,'ml') as Vomitus,concat((urine_amount+ng_sanction+vomitus+drain_one_amount+drain_two_amount+drain_three_amount+drain_four_amount+drain_five_amount),'ml') as Total from nursing.fluid_chart_output "
                                    + "where"
                                    + " transaction_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                                    + " and patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "'"
                                    + " AND (urine_amount!=0 OR ng_sanction!=0 OR vomitus!=0 OR drain_one_amount!=0 OR drain_two_amount!=0 OR drain_three_amount!=0 OR drain_four_amount!=0 OR drain_five_amount!=0) "));
                            jScrollPane23.setViewportView(jTable7);
                        }

                    }
                } catch (Exception table) {
                    table.printStackTrace();
                }

            }
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_fluidChartTabbedMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        final Vector FormDetails = new Vector();
        FormDetails.addElement(nameNoTxt.getText());
        FormDetails.addElement(visitId);
        FormDetails.addElement(ward);
        FormDetails.addElement(user);
        FormDetails.addElement(populateBioData(nameNoTxt.getText()));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NursingCareImplementation dialog = new NursingCareImplementation(new javax.swing.JFrame(), true, connectDB, pConnDB, FormDetails);
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton523ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton523ActionPerformed
        com.afrisoftech.nursingreports.NursingCarePdf policy = new com.afrisoftech.nursingreports.NursingCarePdf();

        policy.NursingCarePdf(connectDB, nameNoTxt.getText(), visitId, patient_name, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()).toString());
    }//GEN-LAST:event_jButton523ActionPerformed

    private void newAction11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction11ActionPerformed
        // TODO add your handling code here:
        try {

            connectDB.setAutoCommit(false);
            for (int i = 0; i < nursingcaretable.getRowCount(); i++) {
                System.out.println("niko hapa sasa ndani ya loop mara ya " + i);
                if ((nursingcaretable.getValueAt(i, 0) != null)
                        && (nursingcaretable.getValueAt(i, 1) != null)
                        && (nursingcaretable.getValueAt(i, 2) != null)
                        && (nursingcaretable.getValueAt(i, 3) != null)
                        && (nursingcaretable.getValueAt(i, 4) != null)
                        && (nursingcaretable.getValueAt(i, 5) != null)) {
                    System.out.println("niko hapa sasa ndani ya if loop mara ya " + i + " and it is " + nursingcaretable.getValueAt(i, 0).toString().trim().isEmpty());
                    java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("INSERT INTO nursing.nursing_care_plan("
                            + "            patient_id, visit_id, transaction_time, server_time, diagnosis, "
                            + "            expected_outcome, plan_of_care, intervention,rationale, evaluation, nurse_name, "
                            + "            ward, implementation, implemented_by)"
                            + "    VALUES (?, ?, ?, localtimestamp, ?,?, ?, ?,?, ?, ?, ?, ?, ?);");

                    pstmt1.setObject(1, nameNoTxt.getText());
                    pstmt1.setObject(2, visitId);
                    pstmt1.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                    pstmt1.setObject(4, nursingcaretable.getValueAt(i, 0).toString().trim());
                    pstmt1.setObject(5, nursingcaretable.getValueAt(i, 1).toString().trim());
                    pstmt1.setObject(6, nursingcaretable.getValueAt(i, 2).toString().trim());
                    pstmt1.setObject(7, nursingcaretable.getValueAt(i, 3).toString().trim());
                    pstmt1.setObject(8, nursingcaretable.getValueAt(i, 4).toString().trim());
                    pstmt1.setObject(9, nursingcaretable.getValueAt(i, 5).toString().trim());
                    pstmt1.setObject(10, user);
                    pstmt1.setObject(11, ward);
                    pstmt1.setObject(12, false);
                    pstmt1.setObject(13, "NOT");

                    pstmt1.executeUpdate();
                }
            }

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            clearAction15.doClick();
        } catch (Exception sq) {
            System.out.println("the nursing care table insert error is " + sq);
            sq.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sq, "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_newAction11ActionPerformed

    private void clearAction15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction15ActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < nursingcaretable.getModel().getRowCount(); i++) {
            for (int j = 0; j < nursingcaretable.getModel().getColumnCount(); j++) {
                nursingcaretable.getModel().setValueAt(null, i, j);
            }
        }
    }//GEN-LAST:event_clearAction15ActionPerformed

    private void helpAction14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction14ActionPerformed

    private void nursingcareTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nursingcareTabMouseClicked

        if (nursingcareTab.getSelectedIndex() == 1) {

            System.out.println("SELECT transaction_time,  diagnosis,   expected_outcome, plan_of_care, intervention,rationale, evaluation, nurse_name"
                    + " FROM nursing.nursing_care_plan  "
                    + "where ward='" + ward + "' and transaction_time >='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                    + "and patient_id='" + nameNoTxt.getText() + "' and visit_id='" + visitId + "' ");

            implementedCareTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                    "SELECT transaction_time,  diagnosis,   expected_outcome, plan_of_care, intervention,rationale, evaluation, nurse_name"
                    + " FROM nursing.nursing_care_plan  "
                    + "where ward='" + ward + "' and transaction_time >='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "
                    + "and patient_id='" + nameNoTxt.getText() + "' and visit_id='" + visitId + "' "));

        }
    }//GEN-LAST:event_nursingcareTabMouseClicked

    private void albuminTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albuminTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albuminTxtActionPerformed

    private void jCheckBox49ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox49ItemStateChanged

        if (jCheckBox49.isSelected() == true) {
            medicine = "No";
            medicationArea.setEditable(false);
            medicationArea.setText(null);
        } else if (jCheckBox49.isSelected() == true) {
            medicine = " ";
            medicationArea.setEditable(false);
            medicationArea.setText(null);
        }
    }//GEN-LAST:event_jCheckBox49ItemStateChanged

    private void jCheckBox50ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox50ItemStateChanged
        if (jCheckBox50.isSelected() == true) {
            medicine = "Yes";
            medicationArea.setEditable(true);
        } else if (jCheckBox50.isSelected() == false) {
            medicine = "";
            medicationArea.setEditable(false);
            medicationArea.setText(null);
        }
    }//GEN-LAST:event_jCheckBox50ItemStateChanged

    private void newAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction4ActionPerformed

        if (!nameNoTxt.getText().isEmpty() && !bookingNo.isEmpty()) {
            try {
                connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO nursing.nurse_preoperative(\n"
                        + "            patient_no, visit_id, date_recoded, time_recorded, operation, \n"
                        + "            dentures, jewellery, wigs, preparation, others, iv_drips, albumin, \n"
                        + "            urinalysis, sugar, bladder_check, gastric_tube, x_rays, blood_available, \n"
                        + "            consent, pre_medication,medication, ward_nurse, nursedate_time, certifiedby, \n"
                        + "            cerifieddate_time, done_by,ward,bookingNO)\n"
                        + "    VALUES (?, ?, current_date, localtimestamp, ?,?,?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");

                pstmt.setObject(1, nameNoTxt.getText());
                pstmt.setObject(2, visitId);
                pstmt.setObject(3, operationstxt.getText());
                pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(dentures.getSelectedItem()));
                pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(Jewellery.getSelectedItem()));
                pstmt.setObject(6, new com.afrisoftech.nursing.NursingObject().NursingObject(Wigs.getSelectedItem()));
                pstmt.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(Preparation.getSelectedItem()));
                pstmt.setObject(8, preopgearsTable.getValueAt(0, 4));
                pstmt.setObject(9, ivdripsTxt.getText());
                pstmt.setObject(10, albuminTxt.getText());
                pstmt.setObject(11, urinalysisTxt.getText());
                pstmt.setObject(12, bloodsugarTxt.getText());
                pstmt.setObject(13, bladdercheckTxt.getText());
                pstmt.setObject(14, gastricTxt.getText());
                pstmt.setObject(15, xraysTxt.getText());
                pstmt.setObject(16, bloodavailTxt.getText());
                pstmt.setObject(17, new com.afrisoftech.nursing.NursingObject().NursingObject(conscentcomb.getSelectedItem()));
                pstmt.setObject(18, medicine);
                pstmt.setObject(19, medicationArea.getText());
                pstmt.setObject(20, user);
                pstmt.setDate(21, com.afrisoftech.lib.SQLDateFormat.getSQLDate(wardnursetime.getDate()));
                pstmt.setObject(22, certifiedTxt.getText());
                pstmt.setObject(23, com.afrisoftech.lib.SQLDateFormat.getSQLDate(certifiedtime.getDate()));
                pstmt.setObject(24, user);
                pstmt.setObject(25, ward);
                pstmt.setObject(26, bookingNo);
                pstmt.executeUpdate();
                connectDB.commit();
                connectDB.setAutoCommit(true);
                clearAction4.doClick();

                javax.swing.JOptionPane.showMessageDialog(this, "Saved Successfully", "", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException preop) {
                javax.swing.JOptionPane.showMessageDialog(this, preop, "Error Message!second", javax.swing.JOptionPane.ERROR_MESSAGE);
                preop.printStackTrace();
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Select Patient NO First \nFROM THEATRE LIST IN THE OCCUPANCY", "Error Message!second", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_newAction4ActionPerformed

    private void clearAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction4ActionPerformed

        operationstxt.setText(null);
        dentures.setSelectedIndex(0);
        Jewellery.setSelectedIndex(0);
        Wigs.setSelectedIndex(0);
        Preparation.setSelectedIndex(0);
        preopgearsTable.setValueAt(null, 0, 4);
        ivdripsTxt.setText(null);
        albuminTxt.setText(null);
        urinalysisTxt.setText(null);
        bloodsugarTxt.setText(null);
        bladdercheckTxt.setText(null);
        gastricTxt.setText(null);
        xraysTxt.setText(null);
        bloodavailTxt.setText(null);
        conscentcomb.setSelectedIndex(0);
        medicationArea.setText(null);
        certifiedTxt.setText(null);
        for (int y = 0; y < 5; y++) {
            preopgearsTable.setValueAt("", 0, y);
        }
    }//GEN-LAST:event_clearAction4ActionPerformed

    private void helpAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction4ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton3.setSelected(false);
            jRadioButton2.setSelected(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton3.setSelected(false);
            jRadioButton1.setSelected(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void newAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction1ActionPerformed
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.pressure_sore ("
                    + "patient_no,physical,nutrition,skin_integrity,mobility,"
                    + "medication,total_scored,body_build,age,level_of_consciousness,"
                    + "continence,risk_score,server_date,transaction_date,current_users,"
                    + "ward,visit_id"
                    + ") values("
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,localtimestamp,?,"
                    + "?,?)");

            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setObject(2, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxGeneralCond.getSelectedItem()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxNutrition.getSelectedItem()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxSkinIntegrity.getSelectedItem()));
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxMobility.getSelectedItem()));
            pstmt.setObject(6, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxMedication.getSelectedItem()));
            pstmt.setObject(7, riskScoreTxt.getText());
            pstmt.setObject(8, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxBodyBuild.getSelectedItem()));
            pstmt.setObject(9, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxAge.getSelectedItem()));
            pstmt.setObject(10, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxConsciousness.getSelectedItem()));
            pstmt.setObject(11, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxContinence.getSelectedItem()));
            pstmt.setObject(12, riskScoreTxt.getText());
            pstmt.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(14, user);
            pstmt.setObject(15, ward);
            pstmt.setObject(16, visitId);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
        if (Integer.parseInt(riskScoreTxt.getText()) > 10) {
            /* Create and display the dialog */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    BedSoreAssesment dialog = new BedSoreAssesment(new javax.swing.JFrame(), true, connectDB, pConnDB, nameNoTxt.getText(), com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()), visitId);
                    dialog.setTitle("Pressure Ulcer Preventive Protocol");
                    dialog.setBounds(350, 150, 600, 400);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                }
            });
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_newAction1ActionPerformed

    private void updateAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAction1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BedSoreAssesment dialog = new BedSoreAssesment(new javax.swing.JFrame(), true, connectDB, pConnDB, nameNoTxt.getText(), com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()), visitId);
                dialog.setTitle("Pressure Ulcer Preventive Protocol");
                dialog.setBounds(350, 150, 600, 400);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_updateAction1ActionPerformed

    private void clearAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAction1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAction1ActionPerformed

    private void helpAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAction1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpAction1ActionPerformed

    private void jComboBoxGeneralCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGeneralCondActionPerformed
        // TODO add your handling code here:
        riskScoreTxt.setText(Integer.toString(pressure_sore()));
    }//GEN-LAST:event_jComboBoxGeneralCondActionPerformed

    private void jComboBoxSkinIntegrityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSkinIntegrityActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSkinIntegrityActionPerformed

    private void jComboBoxNutritionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNutritionActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNutritionActionPerformed

    private void jComboBoxMobilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMobilityActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMobilityActionPerformed

    private void jComboBoxBodyBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBodyBuildActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBodyBuildActionPerformed

    private void jComboBoxAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAgeActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAgeActionPerformed

    private void jComboBoxContinenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContinenceActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxContinenceActionPerformed

    private void jComboBoxConsciousnessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsciousnessActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxConsciousnessActionPerformed

    private void jComboBoxMedicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMedicationActionPerformed
        riskScoreTxt.setText(Integer.toString(pressure_sore()));         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMedicationActionPerformed

    private void jComboBoxPainScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPainScaleItemStateChanged
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nthe text returned is " + new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxPainScale.getSelectedItem()));
    }//GEN-LAST:event_jComboBoxPainScaleItemStateChanged

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        com.afrisoftech.nursingreports.NursingGraphicalView dismt = new com.afrisoftech.nursingreports.NursingGraphicalView(connectDB, pConnDB, "out", ward, nameNoTxt.getText().trim(), "OP");
        dismt.setVisible(true);
        saccopn.add(dismt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            dismt.setSelected(true);
            dismt.setVisible(true);
        } catch (java.beans.PropertyVetoException pvt) {
            System.out.println("the nursing theater problem is " + pvt);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void nameNoTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_nameNoTxtCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_nameNoTxtCaretUpdate

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if(nameNoTxt.getText().isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Select patient first!");
        }else{
            com.afrisoftech.lib.AllergiesIntfr chgPasswd = new com.afrisoftech.lib.AllergiesIntfr(new java.awt.Frame() , true, connectDB, nameNoTxt.getText(), patient_name);

            chgPasswd.setVisible(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed
    private void diastolicBp(javax.swing.JProgressBar progressBar) {
        System.out.println("Print patient age : [" + years + "]");
        if (txtDiastolicBp.getText().equals("")) {
            progressBar.setValue(0);
            progressBar.setString("");
            progressBar.setForeground(Color.white);
            progressBar.setBackground(Color.white);
        } else {

            if (years > 1) {
                //over one year 
                if (years <= 8) {//Children
                    if (Integer.parseInt(txtDiastolicBp.getText()) < 80) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) >= 80 && Integer.parseInt(txtDiastolicBp.getText()) <= 110) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) > 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    }
                } else { //adult
                    if (Integer.parseInt(txtDiastolicBp.getText()) < 60) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) >= 60 && Integer.parseInt(txtDiastolicBp.getText()) <= 90) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) > 90) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    }
                }
            } else {
                // below one year
                if (months > 0) {//Infants
                    if (Integer.parseInt(txtDiastolicBp.getText()) < 70) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) >= 70 && Integer.parseInt(txtDiastolicBp.getText()) <= 95) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) > 95) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    }
                } else {//Neonates
                    if (Integer.parseInt(txtDiastolicBp.getText()) < 120) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) >= 120 && Integer.parseInt(txtDiastolicBp.getText()) <= 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtDiastolicBp.getText()) > 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtDiastolicBp.getText()));
                        progressBar.setStringPainted(true);

                    }
                }
            }
        }
    }

    private void systolicBp(javax.swing.JProgressBar progressBar) {
        if (txtSystolic.getText().toString().equals("")) {
            progressBar.setValue(0);
            progressBar.setString("");
            progressBar.setForeground(Color.white);
            progressBar.setBackground(Color.white);
        } else {

            if (years > 0) {
                //over one year 
                if (years <= 8) {//Children
                    if (Integer.parseInt(txtSystolic.getText()) < 80) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) >= 80 && Integer.parseInt(txtSystolic.getText()) <= 110) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) > 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    }
                } else { //adult
                    if (Integer.parseInt(txtSystolic.getText()) < 90) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) >= 90 && Integer.parseInt(txtSystolic.getText()) <= 140) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) > 140) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    }
                }
            } else {
                //below one year
                if (months > 0) {//Infants
                    if (Integer.parseInt(txtSystolic.getText()) < 70) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Weak");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) >= 70 && Integer.parseInt(txtSystolic.getText()) <= 95) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) > 95) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("High");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    }
                } else {//Neonates
                    if (Integer.parseInt(txtSystolic.getText()) < 120) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Low");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.pink);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) >= 120 && Integer.parseInt(txtSystolic.getText()) <= 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.GREEN);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);
                    } else if (Integer.parseInt(txtSystolic.getText()) > 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("Strong");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.RED);
                        progressBar.setValue(Integer.parseInt(txtSystolic.getText()));
                        progressBar.setStringPainted(true);

                    }
                }
            }
        }
    }

    private void pulseRate(javax.swing.JTextField txtPulseRate, javax.swing.JProgressBar progressBar, javax.swing.JCheckBox jCheckBoxRegular, javax.swing.JCheckBox jCheckBoxIrregular) {
        if (txtPulseRate.getText().toString().equals("")) {
            progressBar.setValue(0);
            progressBar.setString("");
            progressBar.setForeground(Color.white);
            progressBar.setBackground(Color.white);
            jCheckBoxRegular.setSelected(false);
            jCheckBoxIrregular.setSelected(false);
        } else {

            if (years > 0) {
//over one year 
                if (years <= 8) {//Children
                    if (Integer.parseInt(txtPulseRate.getText()) < 80) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Bradycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    } else if (Integer.parseInt(txtPulseRate.getText()) >= 80 && Integer.parseInt(txtPulseRate.getText()) <= 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(false);
                        //jCheckBoxRegular.setSelected(true);
                    } else if (Integer.parseInt(txtPulseRate.getText()) > 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("Tachycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    }
                } else { //adult
                    if (Integer.parseInt(txtPulseRate.getText()) < 60) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Bradycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    } else if (Integer.parseInt(txtPulseRate.getText()) >= 60 && Integer.parseInt(txtPulseRate.getText()) <= 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(false);
                        //jCheckBoxRegular.setSelected(true);
                    } else if (Integer.parseInt(txtPulseRate.getText()) > 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("Tachycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    }
                }
            } else {
//below one year
                if (months > 0) {//Infants
                    if (Integer.parseInt(txtPulseRate.getText()) < 100) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Bradycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    } else if (Integer.parseInt(txtPulseRate.getText()) >= 100 && Integer.parseInt(txtPulseRate.getText()) <= 120) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(false);
                        //jCheckBoxRegular.setSelected(true);
                    } else if (Integer.parseInt(txtPulseRate.getText()) > 120) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("Tachycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    }
                } else {//Neonates
                    if (Integer.parseInt(txtPulseRate.getText()) < 120) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.pink);
                        progressBar.setString("Bradycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    } else if (Integer.parseInt(txtPulseRate.getText()) >= 120 && Integer.parseInt(txtPulseRate.getText()) <= 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.GREEN);
                        progressBar.setString("Normal");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(false);
                        //jCheckBoxRegular.setSelected(true);
                    } else if (Integer.parseInt(txtPulseRate.getText()) > 160) {
                        progressBar.setIndeterminate(false);
                        progressBar.setBackground(Color.RED);
                        progressBar.setString("Tachycardia");
                        progressBar.setOpaque(true);
                        progressBar.setForeground(Color.black);
                        progressBar.setValue(Integer.parseInt(txtPulseRate.getText()));
                        progressBar.setStringPainted(true);
                        //jCheckBoxIrregular.setSelected(true);
                        //jCheckBoxRegular.setSelected(false);
                    }
                }
            }
        }

    }

    private int eyeOpen() {

        int open = 0, verbal = 0;
        if (jComboBoxEyeOpen.getSelectedIndex() == 0) {
            open = 0;
        } else if (jComboBoxEyeOpen.getSelectedIndex() == 1) {
            open = 4;
        } else if (jComboBoxEyeOpen.getSelectedIndex() == 2) {
            open = 3;
        } else if (jComboBoxEyeOpen.getSelectedIndex() == 3) {
            open = 2;
        } else if (jComboBoxEyeOpen.getSelectedIndex() == 4) {
            open = 1;
        } else if (jComboBoxEyeOpen.getSelectedIndex() == 5) {
            open = 0;
        }
        //verbal response
        if (jComboBoxBestVerbal.getSelectedIndex() == 0) {
            verbal = 0;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 1) {
            verbal = 5;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 2) {
            verbal = 4;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 3) {
            verbal = 3;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 4) {
            verbal = 2;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 5) {
            verbal = 1;
        } else if (jComboBoxBestVerbal.getSelectedIndex() == 6) {
            verbal = 0;
        }
        //best motor
        int verb = 0;
        if (jComboBoxBest.getSelectedIndex() == 0) {
            verb = 0;
        } else if (jComboBoxBest.getSelectedIndex() == 1) {
            verb = 6;
        } else if (jComboBoxBest.getSelectedIndex() == 2) {
            verb = 5;
        } else if (jComboBoxBest.getSelectedIndex() == 3) {
            verb = 4;
        } else if (jComboBoxBest.getSelectedIndex() == 4) {
            verb = 3;
        } else if (jComboBoxBest.getSelectedIndex() == 5) {
            verb = 2;
        } else if (jComboBoxBest.getSelectedIndex() == 6) {
            verb = 1;
        }
        int t = open + verbal + verb;

        return t;

    }

    private void respiratory() {
        if (txtRespiratory.getText().toString().equals("")) {
            lblRespiration.setText("");
        } else {

            if (years > 0) {
                //over one year 
                if (years <= 8) {//Children
                    if (Integer.parseInt(txtRespiratory.getText()) < 15) {
                        lblRespiration.setText("below normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) >= 15 && Integer.parseInt(txtRespiratory.getText()) <= 30) {
                        lblRespiration.setText("Normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) > 30) {
                        lblRespiration.setText("above normal rate");
                        lblRespiration.setForeground(Color.red);
                    }
                } else { //adult
                    if (Integer.parseInt(txtRespiratory.getText()) < 12) {
                        lblRespiration.setText("below normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) >= 12 && Integer.parseInt(txtRespiratory.getText()) <= 20) {
                        lblRespiration.setText("Normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) > 20) {
                        lblRespiration.setText("above normal rate");
                        lblRespiration.setForeground(Color.red);
                    }
                }
            } else {
                //below one year
                if (months > 0) {//Infants
                    if (Integer.parseInt(txtRespiratory.getText()) < 25) {
                        lblRespiration.setText("below normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) >= 25 && Integer.parseInt(txtRespiratory.getText()) <= 50) {
                        lblRespiration.setText("Normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) > 50) {
                        lblRespiration.setText("above normal rate");
                        lblRespiration.setForeground(Color.red);
                    }
                } else {//Neonates
                    if (Integer.parseInt(txtRespiratory.getText()) < 40) {
                        lblRespiration.setText("below normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) >= 40 && Integer.parseInt(txtRespiratory.getText()) <= 60) {
                        lblRespiration.setText("Normal rate");
                        lblRespiration.setForeground(Color.red);
                    } else if (Integer.parseInt(txtRespiratory.getText()) > 60) {
                        lblRespiration.setText("above normal rate");
                        lblRespiration.setForeground(Color.red);

                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Accumulatives;
    private javax.swing.JComboBox abdomencmb;
    private javax.swing.JComboBox abdomenstatuscmb;
    private javax.swing.JTextArea abnormalitytxtarea;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JPanel actionsPanel1;
    private javax.swing.JPanel actionsPanel10;
    private javax.swing.JPanel actionsPanel11;
    private javax.swing.JPanel actionsPanel12;
    private javax.swing.JPanel actionsPanel13;
    private javax.swing.JPanel actionsPanel14;
    private javax.swing.JPanel actionsPanel15;
    private javax.swing.JPanel actionsPanel2;
    private javax.swing.JPanel actionsPanel3;
    private javax.swing.JPanel actionsPanel4;
    private javax.swing.JPanel actionsPanel6;
    private javax.swing.JPanel actionsPanel7;
    private javax.swing.JPanel actionsPanel8;
    private javax.swing.JPanel actionsPanel9;
    private javax.swing.JButton adminBtn;
    private javax.swing.JComboBox airwaycmb;
    private javax.swing.JTextField albuminTxt;
    private javax.swing.JTextField amounttxtfld;
    private javax.swing.JRadioButton assistedFeedingRdi;
    private javax.swing.JPanel billingPanel;
    private javax.swing.JTextField bladdercheckTxt;
    private javax.swing.JPanel bloodTransfusionCenter;
    private javax.swing.JPanel bloodTransfusionLower;
    private javax.swing.JPanel bloodTransfusionMainPane;
    private javax.swing.JPanel bloodTransfusionUpper;
    private javax.swing.JTextField bloodavailTxt;
    private javax.swing.JTextField bloodglucosetxt;
    private javax.swing.JButton bloodpackbtn;
    private javax.swing.JTextField bloodsugarTxt;
    public static javax.swing.JButton bloodtransfusionbtn;
    private javax.swing.JPanel bloodtransfusionpane;
    public static javax.swing.JTextField bloodtype;
    private javax.swing.JTextField bmiTxt;
    private javax.swing.JComboBox bowlsoundcmb;
    private javax.swing.JTextField bp;
    private javax.swing.JRadioButton breathingpattern;
    private javax.swing.JComboBox breathingsoundscmb;
    private javax.swing.JCheckBox bruiseschk;
    private javax.swing.JCheckBox burnschk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JTextField caption1;
    private javax.swing.JTextField caption2;
    private javax.swing.JTextField caption3;
    private javax.swing.JTextField caption4;
    private javax.swing.JTextField caption5;
    private javax.swing.JComboBox cardaic;
    private javax.swing.JTabbedPane cardexTabbedPane;
    private javax.swing.JTable cardexTbl;
    private javax.swing.JTextField certifiedTxt;
    private com.afrisoftech.lib.DatePicker certifiedtime;
    private javax.swing.JButton clearAction;
    private javax.swing.JButton clearAction1;
    private javax.swing.JButton clearAction10;
    private javax.swing.JButton clearAction11;
    private javax.swing.JButton clearAction12;
    private javax.swing.JButton clearAction13;
    private javax.swing.JButton clearAction14;
    private javax.swing.JButton clearAction15;
    private javax.swing.JButton clearAction3;
    private javax.swing.JButton clearAction4;
    private javax.swing.JButton clearAction6;
    private javax.swing.JButton clearAction7;
    private javax.swing.JButton clearAction8;
    private javax.swing.JButton clearAction9;
    private javax.swing.JButton clearbloodtransfusion;
    private javax.swing.JComboBox colorcmb;
    private javax.swing.JTextArea commentArea;
    private javax.swing.JTextArea commentVital;
    private javax.swing.JRadioButton completlyAdm;
    private javax.swing.JCheckBox confimedRbtn;
    public static javax.swing.JTextArea confirmationdetails;
    private javax.swing.JComboBox conscentcomb;
    private javax.swing.JRadioButton consciousnesterm;
    public static javax.swing.JTextField countercheck;
    private javax.swing.JCheckBox currentOccupRbtn;
    private javax.swing.JRadioButton currentlyAdmt;
    private javax.swing.JPanel dataPanel2;
    private javax.swing.JComboBox deformitycmb;
    private javax.swing.JTextArea deformitytxtarea;
    private javax.swing.JButton deleteAction;
    private javax.swing.JButton deleteAction6;
    private javax.swing.JComboBox dermatological;
    private javax.swing.JTextArea descriptiontxarea;
    private javax.swing.JTable doctorMed;
    private javax.swing.JButton doneAdministrationBtn;
    private javax.swing.JSpinner drain1txt1;
    private javax.swing.JSpinner drain2txt1;
    private javax.swing.JSpinner drain3txt1;
    private javax.swing.JSpinner drain4txt1;
    private javax.swing.JSpinner drain5txt1;
    private javax.swing.JLabel drainCaption;
    private javax.swing.JComboBox drainscmb1;
    private javax.swing.JPanel drugAdministration;
    private javax.swing.JPanel drugAdministration1;
    private javax.swing.JPanel drugAdministration2;
    private javax.swing.JTextField endtime;
    private javax.swing.JCheckBox endtransfusion;
    private javax.swing.JTextField exacttime;
    private javax.swing.JComboBox externalcmb;
    private javax.swing.JButton extremitiesSave;
    private javax.swing.JComboBox extremitiescmb;
    private javax.swing.JTextArea extremitycommenttxtarea;
    private javax.swing.JLabel facilityidLbl11411;
    private javax.swing.JLabel facilityidLbl11412;
    private javax.swing.JComboBox feedingcmb;
    private javax.swing.JPanel feedpane1;
    private javax.swing.JDialog fluidChartDialog;
    private javax.swing.JTabbedPane fluidChartTabbed;
    private javax.swing.JTable fluidbalance;
    private javax.swing.JPanel fluidchart;
    private javax.swing.JScrollPane fluidscrllpn;
    private javax.swing.JTable fluidtbltxt;
    private javax.swing.JTextField gastricTxt;
    private javax.swing.JComboBox general;
    private javax.swing.JPanel genitolurinarypanel;
    private javax.swing.JPanel genitolurinarypanel1;
    private javax.swing.JProgressBar glucoseProgressBar;
    private javax.swing.JRadioButton gunshotsPatientRdi;
    private javax.swing.JComboBox haemotological;
    private javax.swing.JPanel header;
    private com.afrisoftech.lib.DatePicker headerDatePicker;
    private com.afrisoftech.lib.DatePicker headerDatePicker3;
    private javax.swing.JTextField heightTxt;
    private javax.swing.JButton helpAction;
    private javax.swing.JButton helpAction1;
    private javax.swing.JButton helpAction10;
    private javax.swing.JButton helpAction11;
    private javax.swing.JButton helpAction12;
    private javax.swing.JButton helpAction13;
    private javax.swing.JButton helpAction14;
    private javax.swing.JButton helpAction2;
    private javax.swing.JButton helpAction3;
    private javax.swing.JButton helpAction4;
    private javax.swing.JButton helpAction6;
    private javax.swing.JButton helpAction7;
    private javax.swing.JButton helpAction8;
    private javax.swing.JButton helpAction9;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JPanel helpPanel1;
    private javax.swing.JPanel helpPanel10;
    private javax.swing.JPanel helpPanel11;
    private javax.swing.JPanel helpPanel12;
    private javax.swing.JPanel helpPanel13;
    private javax.swing.JPanel helpPanel14;
    private javax.swing.JPanel helpPanel2;
    private javax.swing.JPanel helpPanel3;
    private javax.swing.JPanel helpPanel4;
    private javax.swing.JPanel helpPanel6;
    private javax.swing.JPanel helpPanel7;
    private javax.swing.JPanel helpPanel8;
    private javax.swing.JPanel helpPanel9;
    private javax.swing.JPanel home;
    private javax.swing.JTextField hourlyrate;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JPanel imgPanel1;
    private javax.swing.JPanel imgPanel10;
    private javax.swing.JPanel imgPanel11;
    private javax.swing.JPanel imgPanel12;
    private javax.swing.JPanel imgPanel13;
    private javax.swing.JPanel imgPanel14;
    private javax.swing.JPanel imgPanel2;
    private javax.swing.JPanel imgPanel3;
    private javax.swing.JPanel imgPanel4;
    private javax.swing.JPanel imgPanel6;
    private javax.swing.JPanel imgPanel7;
    private javax.swing.JPanel imgPanel8;
    private javax.swing.JPanel imgPanel9;
    private javax.swing.JTable implementedCareTable;
    private javax.swing.JRadioButton incidenceRdi;
    private javax.swing.JCheckBox infestationschk;
    private javax.swing.JCheckBox irregularchk;
    private javax.swing.JSpinner ivRate;
    private javax.swing.JSpinner iv_amount;
    private javax.swing.JTextArea iv_instructions;
    private javax.swing.JTextField ivdripsTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton522;
    private javax.swing.JButton jButton523;
    private javax.swing.JButton jButton524;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox49;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox50;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBoxIrregular;
    private javax.swing.JCheckBox jCheckBoxRegular;
    private javax.swing.JCheckBox jCheckBoxStoolNo;
    private javax.swing.JCheckBox jCheckBoxStoolYes;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBoxAge;
    private javax.swing.JComboBox jComboBoxBabinski;
    private javax.swing.JComboBox jComboBoxBest;
    private javax.swing.JComboBox jComboBoxBestVerbal;
    private javax.swing.JComboBox jComboBoxBodyBuild;
    private javax.swing.JComboBox jComboBoxBreathingPattern;
    private javax.swing.JComboBox jComboBoxConsciousness;
    private javax.swing.JComboBox jComboBoxContinence;
    private javax.swing.JComboBox jComboBoxCorneal;
    private javax.swing.JComboBox jComboBoxEyeOpen;
    private javax.swing.JComboBox jComboBoxGag;
    private javax.swing.JComboBox jComboBoxGeneralCond;
    private javax.swing.JComboBox jComboBoxIvType;
    private javax.swing.JComboBox jComboBoxLReaction;
    private javax.swing.JComboBox jComboBoxLeftArm;
    private javax.swing.JComboBox jComboBoxLeftLeg;
    private javax.swing.JComboBox jComboBoxLungSound;
    private javax.swing.JComboBox jComboBoxMedication;
    private javax.swing.JComboBox jComboBoxMobility;
    private javax.swing.JComboBox jComboBoxNutrition;
    private javax.swing.JComboBox jComboBoxOral_type;
    private javax.swing.JComboBox jComboBoxPainScale;
    private javax.swing.JComboBox jComboBoxReaction;
    private javax.swing.JComboBox jComboBoxRightArm;
    private javax.swing.JComboBox jComboBoxRightLeg;
    private javax.swing.JComboBox jComboBoxSeizureActivity;
    private javax.swing.JComboBox jComboBoxSkinIntegrity;
    private javax.swing.JComboBox jComboBoxTypePain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel531111;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel12111;
    private javax.swing.JPanel jPanel12112;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBarDiastolic;
    private javax.swing.JProgressBar jProgressBarSystolicBp;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JPanel jSearchPanel3;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JPanel labelPanel1;
    private javax.swing.JPanel labelPanel10;
    private javax.swing.JPanel labelPanel11;
    private javax.swing.JPanel labelPanel12;
    private javax.swing.JPanel labelPanel13;
    private javax.swing.JPanel labelPanel14;
    private javax.swing.JPanel labelPanel15;
    private javax.swing.JPanel labelPanel3;
    private javax.swing.JPanel labelPanel4;
    private javax.swing.JPanel labelPanel6;
    private javax.swing.JPanel labelPanel7;
    private javax.swing.JPanel labelPanel8;
    private javax.swing.JPanel labelPanel9;
    private javax.swing.JLabel lblDrain1;
    private javax.swing.JLabel lblDrain2;
    private javax.swing.JLabel lblDrain3;
    private javax.swing.JLabel lblDrain4;
    private javax.swing.JLabel lblDrain5;
    private javax.swing.JLabel lblRespiration;
    private javax.swing.JCheckBox lessionschk;
    private javax.swing.JTextField litrestxt;
    private com.afrisoftech.lib.DatePicker lmpdate;
    private javax.swing.JComboBox lmpnaturecmb;
    private javax.swing.JTable medReport;
    private javax.swing.JTextArea medicationArea;
    private javax.swing.JPanel medicinePanel;
    private com.afrisoftech.lib.DatePicker menarch;
    private javax.swing.JLabel mls1;
    private javax.swing.JLabel mls2;
    private javax.swing.JLabel mls3;
    private javax.swing.JLabel mls4;
    private javax.swing.JLabel mls5;
    private javax.swing.JComboBox modeofadmincmb;
    private javax.swing.JTable monitorMed;
    private javax.swing.JComboBox moveallextremitiescmb;
    private javax.swing.JTextField nameNoTxt;
    private javax.swing.JSpinner naso_gastric;
    private javax.swing.JTextArea naso_instructions;
    private javax.swing.JLabel nasofeed1;
    private javax.swing.JButton newAction;
    private javax.swing.JButton newAction1;
    private javax.swing.JButton newAction10;
    private javax.swing.JButton newAction11;
    private javax.swing.JButton newAction12;
    private javax.swing.JButton newAction13;
    private javax.swing.JButton newAction3;
    private javax.swing.JButton newAction4;
    private javax.swing.JButton newAction5;
    private javax.swing.JButton newAction6;
    private javax.swing.JButton newAction7;
    private javax.swing.JButton newAction8;
    private javax.swing.JButton newAction9;
    private javax.swing.JSpinner ngSuction;
    private javax.swing.JCheckBox nonConfirmedRbt;
    private javax.swing.JTextArea notestxtarea;
    private javax.swing.JPanel nurseReport;
    private javax.swing.JTextArea nursesRptCardex;
    private javax.swing.JPanel nursescardex;
    private javax.swing.JPanel nursingCare;
    private javax.swing.JPanel nursingCare1;
    private javax.swing.JTabbedPane nursingTabbedPane;
    private javax.swing.JTabbedPane nursingcareTab;
    private javax.swing.JPanel nursingcareplan;
    private javax.swing.JTable nursingcaretable;
    public static javax.swing.JTable observationchart;
    private javax.swing.ButtonGroup occpancygroup;
    private javax.swing.JPanel occupancy;
    private javax.swing.JDialog occupancyDetailsDialog;
    private javax.swing.JTable occupancyTable;
    private javax.swing.JComboBox occupancywardCMB;
    private javax.swing.JLabel occupyno;
    private javax.swing.ButtonGroup okupybtnngrp;
    private javax.swing.JTextField operationstxt;
    private javax.swing.JSpinner oral_amount;
    private javax.swing.JTextArea oral_fluids_instructions;
    private javax.swing.JLabel others;
    private javax.swing.JTextField otherstxt;
    private javax.swing.JTextField othersymptoms;
    private javax.swing.JCheckBox oxygenradio;
    private javax.swing.ButtonGroup packRdbgroupBtn;
    private javax.swing.JTable packTable;
    private javax.swing.JComboBox packkCombo;
    public static javax.swing.JTextField packno;
    private javax.swing.JPanel panelhide;
    private javax.swing.JRadioButton paraplegicPatientRdi;
    private javax.swing.JRadioButton patientBreasFeedingRdi;
    private javax.swing.JRadioButton patientDeadRdi;
    private javax.swing.JTextArea patientDetailsTextArea;
    private javax.swing.JRadioButton patientGuardedRdi;
    private javax.swing.JRadioButton patientIsStaffRdi;
    private javax.swing.JRadioButton patientMissingRdi;
    private javax.swing.JRadioButton patientReactiveRdi;
    private javax.swing.JDialog patientSearch;
    private javax.swing.JSpinner peg_feed;
    private javax.swing.JComboBox pollorcmb;
    private javax.swing.JTextField pr;
    private javax.swing.JPanel preOperativeList;
    private javax.swing.JPanel preOperativeList1;
    private javax.swing.JScrollPane premedication;
    private javax.swing.JTable preopgearsTable;
    private javax.swing.JPanel pressureSoreInnerPane;
    private javax.swing.JPanel pressureSorejPanel;
    private javax.swing.JCheckBox pressuresorechk;
    private javax.swing.JLabel pulseOximityTxt;
    private javax.swing.JLabel pulseOximityTxt1;
    private javax.swing.JProgressBar pulseRateProgessBar;
    private javax.swing.JProgressBar pulseRateProgessBar2;
    private javax.swing.JRadioButton quadriplegicPatientRdi;
    private javax.swing.JCheckBox rasheschk;
    private javax.swing.JRadioButton referredInPatientRdi;
    private javax.swing.JCheckBox regularchk;
    private javax.swing.JTextField remarks;
    private javax.swing.JComboBox renal;
    private javax.swing.JPanel reportPanel;
    private javax.swing.ButtonGroup reportbtngroup;
    private javax.swing.JTable requestsTable;
    private javax.swing.JComboBox respirationcmb;
    private javax.swing.JPanel resty1;
    private javax.swing.JTextField riskScoreTxt;
    private javax.swing.JCheckBox roomairradio;
    private javax.swing.JTextField roomairtxt;
    private javax.swing.JTextField rr;
    private javax.swing.JTextField saturationtxt;
    private javax.swing.JButton saveCadiovascular;
    private javax.swing.JButton saveGastrointestinal;
    private javax.swing.JButton saveGenitourinary;
    private javax.swing.JButton savePatientReportBtn;
    private javax.swing.JCheckBox scarschk;
    private javax.swing.JButton searchButton4;
    private javax.swing.JRadioButton seizureactivity;
    private javax.swing.JComboBox selecttermcmb;
    private javax.swing.JRadioButton sexuallyAssultedRdi;
    private javax.swing.JTextField signedby;
    private javax.swing.JTable signstable;
    private javax.swing.JComboBox skintugor;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JPanel spacerPanel1;
    private javax.swing.JPanel spacerPanel10;
    private javax.swing.JPanel spacerPanel11;
    private javax.swing.JPanel spacerPanel12;
    private javax.swing.JPanel spacerPanel13;
    private javax.swing.JPanel spacerPanel14;
    private javax.swing.JPanel spacerPanel15;
    private javax.swing.JPanel spacerPanel3;
    private javax.swing.JPanel spacerPanel4;
    private javax.swing.JPanel spacerPanel7;
    private javax.swing.JPanel spacerPanel8;
    private javax.swing.JPanel spacerPanel9;
    public static javax.swing.JTextField startedby;
    public static javax.swing.JTextField starttime;
    public static javax.swing.JCheckBox starttransfusion;
    private javax.swing.ButtonGroup stool;
    private javax.swing.JTextField stoolTxt;
    private javax.swing.JCheckBox symptomscheck;
    private javax.swing.JTextField symptomstime;
    private javax.swing.JTabbedPane systemicAssesment;
    private javax.swing.JPanel systemicReview;
    private javax.swing.JTextField temp;
    private javax.swing.JProgressBar tempProgessBar;
    private javax.swing.JProgressBar tempProgessBar2;
    private javax.swing.JCheckBox theatreChbx;
    private javax.swing.JRadioButton timeAdministered;
    private javax.swing.JTextField totalBalance;
    private javax.swing.JTextField totalIntake;
    private javax.swing.JRadioButton totalNursingPatientRdi;
    private javax.swing.JTextField totalOutput;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JTextField totalTxt1;
    public static javax.swing.JTextField transfusiondate;
    public static javax.swing.JTextField transfusionrate;
    private javax.swing.JPanel turningChart;
    private javax.swing.JPanel turningchart;
    private javax.swing.JTextArea txtAbdomenStatus;
    private javax.swing.JTextField txtDiastolicBp;
    private javax.swing.JTextField txtLeftEyeSize;
    private javax.swing.JTextField txtPulseOximity;
    private javax.swing.JTextField txtPulseOximity1;
    private javax.swing.JTextField txtPulseRate;
    private javax.swing.JTextField txtPulseRate1;
    private javax.swing.JTextField txtRespiratory;
    private javax.swing.JTextField txtRightEyeSize;
    private javax.swing.JTextField txtSpinner;
    private javax.swing.JTextField txtSystolic;
    private javax.swing.JTextField txtTime;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUrinary;
    private javax.swing.JPanel underground;
    private javax.swing.JButton updateAction;
    private javax.swing.JButton updateAction1;
    private javax.swing.JButton updateAction10;
    private javax.swing.JButton updateAction12;
    private javax.swing.JButton updateAction13;
    private javax.swing.JButton updateAction6;
    private javax.swing.JButton updateAction7;
    private javax.swing.JButton updateAction8;
    private javax.swing.JButton updateAction9;
    private javax.swing.JComboBox urgencyComboBox;
    private javax.swing.JComboBox urgencycmbx;
    private javax.swing.JTextField urinalysisTxt;
    private javax.swing.JSpinner urine_amount;
    private javax.swing.JSpinner urine_amount1;
    private javax.swing.JComboBox urineoutputcmb;
    private javax.swing.JTextArea urineoutputtxtarea;
    private javax.swing.JTable userTable;
    private javax.swing.JTextField userTxt;
    private javax.swing.JDialog usersSearchdialog;
    private javax.swing.JRadioButton veryIllPatient;
    private javax.swing.JTextField visitedTxt;
    private javax.swing.JCheckBox visittoiletchk1;
    public javax.swing.JPanel vitalSigns;
    private javax.swing.JComboBox vomitingcmb;
    private javax.swing.JSpinner vomitus;
    private com.afrisoftech.lib.DatePicker wardnursetime;
    private javax.swing.JTextField weightTxt;
    private javax.swing.JTextField wetBedTxt;
    private javax.swing.JTextField wetDiaperTxt;
    private javax.swing.JCheckBox wetbedchk1;
    private javax.swing.JCheckBox wetdiaperchk1;
    private javax.swing.JTextField xraysTxt;
    private javax.swing.JComboBox yescmb;
    private javax.swing.JLabel yeslabel;
    // End of variables declaration//GEN-END:variables

    private void savedCardex() {

        saveCardex();
        cardexTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select urgency,transaction_time,cardex,nurse_name from nursing.nurse_cardex where patient_no='" + nameNoTxt.getText().trim() + "' AND visit_id='" + visitId + "' "));
        cardexTbl.getColumnModel().getColumn(0).setPreferredWidth(100);
        cardexTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
        cardexTbl.getColumnModel().getColumn(2).setPreferredWidth(500);
        cardexTbl.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.requestsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                "SELECT trans_date::date as date,  service, quantity, \n"
                + "       amount,  \n"
                + "       visit_id,doctor,false as Administer"
                + "  FROM pb_doctors_request  where  paid=true and collected=false and  requisition_no='GENERAL'"
                + " and patient_no='" + this.nameNoTxt.getText() + "' "
                + "and trans_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()) + "' "));

    }

    private void neuralogicalSave() {
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.neurological_observations ("
                    + "patient_no,eye_open,best_mortal_response,best_verbal,right_eye_size,left_eye_size,total_glascow_coma,"
                    + "left_eye_size_reaction,right_eye_size_reaction,right_arm,left_arm,right_leg,left_leg,corneal,seizure_activity,"
                    + "gag,babinski,breathing_pattern,transaction_date,visit_id,server_date"
                    + ") values("
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,localtimestamp)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setObject(2, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxEyeOpen.getSelectedItem()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxBest.getSelectedItem()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxBestVerbal.getSelectedItem()));
            pstmt.setObject(5, Integer.parseInt(txtRightEyeSize.getText()));
            pstmt.setObject(6, Integer.parseInt(txtLeftEyeSize.getText()));
            pstmt.setObject(7, Integer.parseInt(txtTotal.getText()));
            pstmt.setObject(8, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxLReaction.getSelectedItem()));
            pstmt.setObject(9, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxReaction.getSelectedItem()));
            pstmt.setObject(10, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxRightArm.getSelectedItem()));
            pstmt.setObject(11, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxLeftArm.getSelectedItem()));
            pstmt.setObject(12, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxRightLeg.getSelectedItem()));
            pstmt.setObject(13, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxLeftLeg.getSelectedItem()));
            pstmt.setObject(14, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxCorneal.getSelectedItem()));
            pstmt.setObject(15, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxSeizureActivity.getSelectedItem()));
            pstmt.setObject(16, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxGag.getSelectedItem()));
            pstmt.setObject(17, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxBreathingPattern.getSelectedItem()));
            pstmt.setObject(18, new com.afrisoftech.nursing.NursingObject().NursingObject(jComboBoxBabinski.getSelectedItem()));
            pstmt.setDate(19, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(20, visitId);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void clearNueralogical() {
        jComboBoxEyeOpen.setSelectedIndex(0);
        jComboBoxBestVerbal.setSelectedIndex(0);
        jComboBoxBest.setSelectedIndex(0);
        txtTotal.setText("0");
        txtRightEyeSize.setText("0");
        txtLeftEyeSize.setText("0");
        jComboBoxReaction.setSelectedIndex(0);
        jComboBoxLReaction.setSelectedIndex(0);
        jComboBoxRightArm.setSelectedIndex(0);
        jComboBoxLeftArm.setSelectedIndex(0);
        jComboBoxRightLeg.setSelectedIndex(0);
        jComboBoxLeftLeg.setSelectedIndex(0);
        jComboBoxCorneal.setSelectedIndex(0);
        jComboBoxSeizureActivity.setSelectedIndex(0);
        jComboBoxGag.setSelectedIndex(0);
        jComboBoxBreathingPattern.setSelectedIndex(0);
        jComboBoxBabinski.setSelectedIndex(0);
    }

    private void respiratoryClear() {
        airwaycmb.setSelectedIndex(0);
        respirationcmb.setSelectedIndex(0);
        breathingsoundscmb.setSelectedIndex(0);
        roomairradio.getActionCommand();
        oxygenradio.getActionCommand();
        modeofadmincmb.setSelectedIndex(0);
        litrestxt.setText("");
        saturationtxt.setText("");
    }

    private void respiratorySave() {
        String first = null;
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.respiratory("
                    + "patient_no,transaction_date,server_date,airway,respiration,"
                    + "breathing_sounds,oxygen_saturation,administration_mode,litres_per_min,saturation,"
                    + "visit_id"
                    + ") values("
                    + "?,?,localtimestamp,?,?,"
                    + "?,?,?,?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(airwaycmb.getSelectedItem()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(respirationcmb.getSelectedItem()));
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(breathingsoundscmb.getSelectedItem()));

            if (roomairradio.isSelected()) {
                first = roomairradio.getActionCommand();
            } else if (oxygenradio.isSelected()) {
                first = oxygenradio.getActionCommand();
            }

            pstmt.setObject(6, first);
            pstmt.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(modeofadmincmb.getSelectedItem()));
            pstmt.setObject(8, litrestxt.getText());
            pstmt.setObject(9, saturationtxt.getText());
            pstmt.setObject(10, visitId);
            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void genitourinarySave() {
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.genitourinarysystem("
                    + "patient_no,transaction_date,server_date,externalnorm,abnormality,"
                    + "urine_output,urine_desc,lmp_nature,lmp_date,menarch,"
                    + "visit_id,action_user,ward"
                    + ") values("
                    + "?,?,localtimestamp,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(externalcmb.getSelectedItem()));
            pstmt.setObject(4, abnormalitytxtarea.getText());
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(urineoutputcmb.getSelectedItem()));
            pstmt.setObject(6, urineoutputtxtarea.getText());
            if (gender.equalsIgnoreCase("Female")) {
                pstmt.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(lmpnaturecmb.getSelectedItem()));
                pstmt.setObject(8, com.afrisoftech.lib.SQLDateFormat.getSQLDate(lmpdate.getDate()));
                pstmt.setObject(9, menarch.getDate().toLocaleString());

            } else if (gender.equalsIgnoreCase("Male")) {
                pstmt.setObject(7, null);
                pstmt.setObject(8, null);
                pstmt.setObject(9, null);
            }
            pstmt.setObject(10, visitId);
            pstmt.setObject(11, user);
            pstmt.setObject(12, ward);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void genitourinaryClear() {
        externalcmb.setSelectedIndex(0);
        abnormalitytxtarea.setText("");
        urineoutputcmb.setSelectedIndex(0);
        urineoutputtxtarea.setText("");
        lmpnaturecmb.setSelectedIndex(0);
    }

    private void cardioVascularClear() {
        regularchk.setSelected(false);
        irregularchk.setSelected(false);
        txtPulseRate1.setText("");
        pulseRateProgessBar2.setString("");
        txtPulseOximity1.setText("");
        pulseOximityTxt1.setText("");
        extremitiescmb.setSelectedIndex(0);
        pollorcmb.setSelectedIndex(0);
    }

    private void cardioVascularSave() {
        java.lang.String irregularity = null;
        try {
            dbObject = new com.afrisoftech.lib.DBObject();
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.cadiovascular("
                    + "patient_no,transaction_date,server_date,pulse_rate,pulse_status,pulse_regularity,pulse_oxiemetry,pulse_comment,exremities,pollor,visit_id,action_user,ward)"
                    + "values(?,?,localtimestamp,?::numeric,?,?,?,?,?,?,?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            if (regularchk.isSelected()) {
                irregularity = regularchk.getActionCommand();
            } else if (irregularchk.isSelected()) {
                irregularity = irregularchk.getActionCommand();
            }
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(txtPulseRate1.getText()));
            pstmt.setObject(4, pulseRateProgessBar2.getString());
            pstmt.setObject(5, irregularity);
            pstmt.setObject(6, txtPulseOximity1.getText());
            pstmt.setObject(7, pulseOximityTxt1.getText());
            pstmt.setObject(8, new com.afrisoftech.nursing.NursingObject().NursingObject(extremitiescmb.getSelectedItem()));
            pstmt.setObject(9, new com.afrisoftech.nursing.NursingObject().NursingObject(pollorcmb.getSelectedItem()));
            pstmt.setObject(10, visitId);
            pstmt.setObject(11, user);
            pstmt.setObject(12, ward);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void gastroIntestinalSave() {
        if (yescmb.getSelectedItem().toString() == "Others") {
            otherstxt.setEditable(true);
        } else {
            otherstxt.setEditable(false);
        }

        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.gastrointestinal("
                    + "patient_no,transaction_date,server_date,abdomen,abdominal_status,"
                    + "bowl_sounds,feeding,vomitting,vomit_desc,others,"
                    + "visit_id,action_user,ward,comment_abdomen_status) "
                    + "values(?,?,localtimestamp,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(abdomencmb.getSelectedItem()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(abdomenstatuscmb.getSelectedItem()));
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(bowlsoundcmb.getSelectedItem()));
            pstmt.setObject(6, new com.afrisoftech.nursing.NursingObject().NursingObject(feedingcmb.getSelectedItem()));
            pstmt.setObject(7, new com.afrisoftech.nursing.NursingObject().NursingObject(vomitingcmb.getSelectedItem()));

            java.lang.String vomit = "No Vomit";
            if (otherstxt.getText().isEmpty()) {
                pstmt.setObject(8, vomit);
            } else {
                pstmt.setObject(8, new com.afrisoftech.nursing.NursingObject().NursingObject(yescmb.getSelectedItem()));
            }
            java.lang.String mm = otherstxt.getText().toString();
            if (otherstxt.getText().isEmpty()) {
                mm = "No Others";
                pstmt.setObject(9, mm);
            } else {
                pstmt.setObject(9, otherstxt.getText());

            }
            pstmt.setObject(10, visitId);
            pstmt.setObject(11, user);
            pstmt.setObject(12, ward);
            pstmt.setObject(13, txtAbdomenStatus.getText());
            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void gastroIntestinalClear() {
        abdomencmb.setSelectedIndex(0);
        abdomenstatuscmb.setSelectedIndex(0);
        bowlsoundcmb.setSelectedIndex(0);
        feedingcmb.setSelectedIndex(0);
        vomitingcmb.setSelectedIndex(0);
        yescmb.setSelectedIndex(0);
        otherstxt.setText("");
        txtAbdomenStatus.setText("");
    }

    private void extremetieSave() {
        java.lang.String deformity, comment = null;
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.MusculoSkeletal("
                    + "patient_no,transaction_date,server_date,deformity,deformity_comment,move_all_extremeties,extremities_comment,visit_id,action_user) "
                    + "values(?,?,localtimestamp,?,?,?,?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(deformitycmb.getSelectedItem()));

            if (deformitytxtarea.getText().isEmpty()) {
                deformity = "No Deformity";
            } else {
                deformity = deformitytxtarea.getText();
            }
            pstmt.setObject(4, deformity);
            pstmt.setObject(5, new com.afrisoftech.nursing.NursingObject().NursingObject(moveallextremitiescmb.getSelectedItem()));

            if (extremitycommenttxtarea.getText().isEmpty()) {
                comment = "No Comment";
            } else {
                comment = extremitycommenttxtarea.getText();
            }
            pstmt.setObject(6, comment);
            pstmt.setObject(7, visitId);
            pstmt.setObject(8, user);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void extremetiesClear() {
        deformitycmb.setSelectedIndex(0);
        deformitytxtarea.setText("");
        moveallextremitiescmb.setSelectedIndex(0);
        extremitycommenttxtarea.setText("");
    }

    private void clearIntegumentry() {
        colorcmb.setSelectedIndex(0);
        skintugor.setSelectedIndex(0);
        notestxtarea.setText("");
        lessionschk.setSelected(false);
        infestationschk.setSelected(false);
        bruiseschk.setSelected(false);
        rasheschk.setSelected(false);
        scarschk.setSelected(false);
        burnschk.setSelected(false);
        pressuresorechk.setSelected(false);
    }

    private void saveIntegumentary() {
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into nursing.integumentary("
                    + "patient_no,transaction_date,server_date,skin_color,skin_tugor,skin_integrity,comment,visit_id,action_user,ward) "
                    + "values(?,?,localtimestamp,?,?,?,?,?,?,?)");
            pstmt.setObject(1, nameNoTxt.getText());
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
            pstmt.setObject(3, new com.afrisoftech.nursing.NursingObject().NursingObject(colorcmb.getSelectedItem()));
            pstmt.setObject(4, new com.afrisoftech.nursing.NursingObject().NursingObject(skintugor.getSelectedItem()));
            pstmt.setObject(5, integrities);
            pstmt.setObject(6, notestxtarea.getText());
            pstmt.setObject(7, visitId);
            pstmt.setObject(8, user);
            pstmt.setObject(9, ward);

            pstmt.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }

        pressuresorechk.setSelected(false);
        lessionschk.setSelected(false);
        infestationschk.setSelected(false);
        bruiseschk.setSelected(false);
        rasheschk.setSelected(false);
        scarschk.setSelected(false);
        burnschk.setSelected(false);

    }

    private void saveCardex() {

        ////saving the cardex and outputing the comments on the above table as per date and time
        String cardexString = nursesRptCardex.getText().toString();

        try {
            connectDB.setAutoCommit(false);
            if (cardexTabbedPane.getSelectedIndex() == 0) {

                for (int i = 0; i < this.requestsTable.getRowCount(); i++) {
                    if (this.requestsTable.getValueAt(i, 6) == Boolean.TRUE) {
                        java.sql.PreparedStatement pstmtrequest = connectDB.prepareStatement(""
                                + "update pb_doctors_request set collected=true,results=true "
                                + " where service=? and visit_id=? and paid=true and collected=false and  requisition_no='GENERAL' and patient_no='" + this.nameNoTxt.getText() + "' "
                                + "and trans_date::date=?::date");
                        pstmtrequest.setObject(1, this.requestsTable.getValueAt(i, 1).toString().trim());
                        pstmtrequest.setObject(2, this.requestsTable.getValueAt(i, 4).toString().trim());
                        pstmtrequest.setObject(3, this.requestsTable.getValueAt(i, 0).toString().trim());
                        // pstmtrequest.setObject(4, user);
                        pstmtrequest.executeUpdate();

                        java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into nursing.nurse_cardex(transaction_time,server_time,patient_no,visit_id,nurse_name,cardex,ward,urgency) values(?,localtimestamp,?,?,initcap(?),initcap(?),?,?)");
                        pstmt1.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                        pstmt1.setObject(2, nameNoTxt.getText());
                        pstmt1.setObject(3, visitId);
                        pstmt1.setObject(4, user);
                        pstmt1.setObject(5, this.requestsTable.getValueAt(i, 1));
                        pstmt1.setObject(6, ward);
                        pstmt1.setBoolean(7, false);
                        pstmt1.executeUpdate();
                    }
                }

            } else {

                java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into nursing.nurse_cardex(transaction_time,server_time,patient_no,visit_id,nurse_name,cardex,ward,urgency) values(?,localtimestamp,?,?,initcap(?),initcap(?),?,?)");
                pstmt1.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(headerDatePicker.getDate()));
                pstmt1.setObject(2, nameNoTxt.getText());
                pstmt1.setObject(3, visitId);
                pstmt1.setObject(4, user);
                pstmt1.setObject(5, nursesRptCardex.getText());
                pstmt1.setObject(6, ward);
                pstmt1.setBoolean(7, jCheckBox5.isSelected());
                pstmt1.executeUpdate();

                nursesRptCardex.setText("");
            }
            connectDB.commit();
            javax.swing.JOptionPane.showMessageDialog(this, "Implementation Done Successfully", "Comfirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            connectDB.setAutoCommit(true);
        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();

        }

    }

    private void coluorTable() {
//        com.afrisoftech.dbadmin.JXTable predicateTable = (com.afrisoftech.dbadmin.JXTable) occupancyTable;
//        com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
//        try {
//            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement("SELECT distinct patient_no   FROM pb_doctors_request where requisition_no='X-RAY' and collected = true and paid=true and results=true  ");
//
////            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
////            predicateTable.setHighlighterPipeline(predicateTable, new org.jdesktop.swingx.decorator.Highlighter[]{
////                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.PINK, java.awt.Color.BLACK, "triage", 6, 6, 0),
////                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.PINK, java.awt.Color.BLACK, "Cons", 6, 6, 0),
////                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.CYAN, java.awt.Color.BLACK, "Cons", 6, 6, 1)
////            });
//            
//            
//           
//
////            while (rsetVector.next()) {
////
////                predicateTable.setHighlighterPipeline(predicateTable, new org.jdesktop.swing.decorator.PatternHighlighter[]{
////                    new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.MAGENTA, java.awt.Color.BLACK, dbObject.getDBObject(rsetVector.getString(1), ""), 0, 0, 3)});
////            }
//
//        } catch (Exception req) {
//            req.printStackTrace();
//
//        }
//        
//        
//        
//        try {
//            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(" "
//                    + "SELECT distinct patient_no   FROM pb_doctors_request where requisition_no='LAB' and collected = true and paid=true and results=true ");
//
//            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
//            while (rsetVector.next()) {
//
//                predicateTable.setHighlighterPipeline(predicateTable, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                    new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.GREEN, java.awt.Color.BLACK, dbObject.getDBObject(rsetVector.getString(1), ""), 0, 0, 2)
//
//                });
//            }
//        } catch (Exception req) {
//            req.printStackTrace();
//
//        }
    }

}
