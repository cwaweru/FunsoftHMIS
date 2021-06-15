/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */
package com.afrisoftech.laboratory;

import biz.systempartners.claims.XMLClaimFile;
import com.afrisoftech.hospital.VitalSignRecIntfr;
import com.inet.jortho.SpellChecker;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.FileUserDictionary;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
//

/**
 *
 * @author SPL
 */
//public class ConsultationIntfr extends javax.swing.JInternalFrame{
public class ConsultationIntfr extends javax.swing.JInternalFrame implements java.lang.Runnable, CaretListener {

    com.afrisoftech.lib.DBObject dbObject;
    Boolean clear = true;
    Boolean prescribeNeg = true;
    javax.swing.table.TableModel tableModel = null;
    public Vector symps = new Vector();
    java.sql.Connection connectDB = null;
    int agee = 0;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.lang.Thread getListThread;
    int PatLabResults = 90;
    boolean getList;
    boolean getList1;
    java.lang.Thread getListThread1;
    private static java.util.ArrayList dataVector;
    private static java.util.ArrayList detailsVector;
    private javax.swing.JComboBox cmbox5;
    private javax.swing.JComboBox cmbox6;
    private boolean provisinal;
    private boolean icd10table;
    private String icdCode;
    private String query;
    private Object jTextField9221;
    private JTextField jTextField7 = new javax.swing.JTextField();
    private String visitID;
    private Connection limsConnectDB;
    private String limsip;
    private String limsport;
    private String limsdatabase;
    private String limsusers;
    private String limspass;
    WaitingPatientsThread waitingPatientsThread = new WaitingPatientsThread();
    private String Sex;
    private String Age;
    private javax.swing.JComboBox cmBoxspecimen = new javax.swing.JComboBox();
    private String labNo;
    private boolean emergencyStatus = false;
    private boolean underFive;
    double schemeDrugsMarkup = 1.00;
    boolean show_drug_balance = false;

    public ConsultationIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, com.afrisoftech.hospital.HospitalMain parentHospitalFrame) {

        connectDB = connDb;

        pConnDB = pconnDB;

        java.util.Date beginDate = null;

        java.util.Date endDate = null;

        dbObject = new com.afrisoftech.lib.DBObject();
        getListThread = new java.lang.Thread(this, "Update_Lab_Tests");
        getListThread1 = new java.lang.Thread(this, "Update_Lab_Tests1");
        initComponents();
        
        prescribeNeg = com.afrisoftech.lib.GetItemInfo.allowOutOfSockPrescription(connectDB);
        show_drug_balance= com.afrisoftech.lib.GetItemInfo.showDrugsBalance(connectDB);

        initialiseSpellChecker();
        SpellChecker.register(complainsTextPaneTxt);
        SpellChecker.register(clinicalExamineditorTxt);
////lims integration details

        ////
        drawingPanel.removeAll();

        java.awt.GridBagConstraints gridBagConstraints;
        drawingPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        drawingPanel.add(new com.afrisoftech.lib.BasicPaint().getGui(), gridBagConstraints);

        parentHospitalFrame.mainSplitPane.setDividerLocation(0);

        this.setSize(parentHospitalFrame.saccopn.getSize());
        getList = true;
        //comment for now  waitingPatientsThread.start();
        // getListThread.start();
        getList1 = true;

        // getListThread1.start();
//          com.afrisoftech.dbadmin.JTable predicateTable0 = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
//            predicateTable0.setHighlighterPipeline(predicateTable0, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//              
//            });
        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters5 = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable5 = (com.afrisoftech.dbadmin.JXTable) clerkingwaitingTable;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate5x = new org.jdesktop.swingx.decorator.PatternPredicate("1", 9, 9);
        ColorHighlighter pink5 = new ColorHighlighter(patternPredicate5x, Color.PINK, null, Color.PINK, null);
        tableHighlighters5.addElement(pink5);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate5 = new org.jdesktop.swingx.decorator.PatternPredicate("Cons", 9, 9);
        ColorHighlighter cyan5 = new ColorHighlighter(patternPredicate5, Color.CYAN, null, Color.CYAN, null);
        tableHighlighters5.add(cyan5);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate4x = new org.jdesktop.swingx.decorator.PatternPredicate("Normal", 4, 4);
        ColorHighlighter green4 = new ColorHighlighter(patternPredicate4x, Color.ORANGE, null, Color.ORANGE, null);
        tableHighlighters5.addElement(green4);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate4 = new org.jdesktop.swingx.decorator.PatternPredicate("Emergency", 4, 4);
        ColorHighlighter redd = new ColorHighlighter(patternPredicate4, Color.RED, null, Color.RED, null);
        tableHighlighters5.add(redd);
        // Highlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(6));
        ColorHighlighter tableHighlightersArray5[] = new ColorHighlighter[]{pink5, cyan5, green4, redd};
        predicateTable5.setHighlighterPipeline(predicateTable5, tableHighlightersArray5);

//        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters4 = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
//        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
//        com.afrisoftech.dbadmin.JXTable predicateTable4 = (com.afrisoftech.dbadmin.JXTable) clerkingwaitingTable;
//        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate4x = new org.jdesktop.swingx.decorator.PatternPredicate("Normal", 4, 4);
//        ColorHighlighter green4 = new ColorHighlighter(patternPredicate4x, Color.ORANGE, null, Color.ORANGE, null);
//        tableHighlighters4.addElement(green4);
//        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate4 = new org.jdesktop.swingx.decorator.PatternPredicate("Emergency", 4, 4);
//        ColorHighlighter redd = new ColorHighlighter(patternPredicate4, Color.RED, null, Color.RED, null);
//        tableHighlighters4.add(redd);
//        // Highlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(6));
//        ColorHighlighter tableHighlightersArray4[] = new ColorHighlighter[]{green4, redd};
//        predicateTable4.setHighlighterPipeline(predicateTable4, tableHighlightersArray4);
        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable = (com.afrisoftech.dbadmin.JXTable) labresultsTable;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate = new org.jdesktop.swingx.decorator.PatternPredicate("true", 5, 5);
        ColorHighlighter yellow = new ColorHighlighter(patternPredicate, Color.YELLOW, null, Color.YELLOW, null);
        tableHighlighters.addElement(yellow);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate1 = new org.jdesktop.swingx.decorator.PatternPredicate("false", 5, 5);
        ColorHighlighter pink = new ColorHighlighter(patternPredicate1, Color.PINK, null, Color.PINK, null);
        tableHighlighters.add(pink);
        // Highlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(6));
        ColorHighlighter tableHighlightersArray[] = new ColorHighlighter[]{yellow, pink};
        predicateTable.setHighlighterPipeline(predicateTable, tableHighlightersArray);

        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters1 = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable1 = (com.afrisoftech.dbadmin.JXTable) laboratoryResultsDisplayTbl;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate2 = new org.jdesktop.swingx.decorator.PatternPredicate("true", 5, 5);
        ColorHighlighter red = new ColorHighlighter(patternPredicate2, Color.RED, null, Color.RED, null);
        tableHighlighters1.addElement(red);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate3 = new org.jdesktop.swingx.decorator.PatternPredicate("false", 5, 5);
        ColorHighlighter green = new ColorHighlighter(patternPredicate3, Color.GREEN, null, Color.GREEN, null);
        tableHighlighters1.add(green);
        // Highlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(6));
        ColorHighlighter tableHighlightersArray1[] = new ColorHighlighter[]{red, green};
        predicateTable1.setHighlighterPipeline(predicateTable1, tableHighlightersArray1);

        schemeDrugsMarkup = com.afrisoftech.lib.StoreFactory.getSchemeDrugsMarkup(connectDB);

    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pharmacySearchDialog = new javax.swing.JDialog();
        pharmacySearchPanel = new javax.swing.JPanel();
        pharmacySearchTxt = new javax.swing.JTextField();
        pharmacySearchScrollPane = new javax.swing.JScrollPane();
        pharmacySearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton911 = new javax.swing.JButton();
        jSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField112 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jButton92 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jSearchDialog211 = new javax.swing.JDialog();
        jSearchPanel211 = new javax.swing.JPanel();
        jTextField11311 = new javax.swing.JTextField();
        jSearchScrollPane211 = new javax.swing.JScrollPane();
        jSearchTable211 = new com.afrisoftech.dbadmin.JTable();
        jButton5211 = new javax.swing.JButton();
        jSearchDialog2 = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton522 = new javax.swing.JButton();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        jSearchDialog212 = new javax.swing.JDialog();
        jSearchPanel212 = new javax.swing.JPanel();
        jTextField11312 = new javax.swing.JTextField();
        jSearchScrollPane213 = new javax.swing.JScrollPane();
        jSearchTable213 = new javax.swing.JTable();
        jButton5213 = new javax.swing.JButton();
        buttonGroup10 = new javax.swing.ButtonGroup();
        jSearchDialog11 = new javax.swing.JDialog();
        jSearchPanel11 = new javax.swing.JPanel();
        jTextField1111 = new javax.swing.JTextField();
        jSearchScrollPane11 = new javax.swing.JScrollPane();
        jSearchTable11 = new com.afrisoftech.dbadmin.JTable();
        jButton91 = new javax.swing.JButton();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        jSearchDialog213 = new javax.swing.JDialog();
        jSearchPanel213 = new javax.swing.JPanel();
        jTextField11313 = new javax.swing.JTextField();
        jSearchScrollPane212 = new javax.swing.JScrollPane();
        jSearchTable212 = new com.afrisoftech.dbadmin.JTable();
        jButton5212 = new javax.swing.JButton();
        jSearchDialog214 = new javax.swing.JDialog();
        jSearchPanel214 = new javax.swing.JPanel();
        jTextField11314 = new javax.swing.JTextField();
        jSearchScrollPane214 = new javax.swing.JScrollPane();
        jSearchTable214 = new com.afrisoftech.dbadmin.JTable();
        jButton5214 = new javax.swing.JButton();
        buttonGroup13 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();
        icd10SearchDialog = new javax.swing.JDialog();
        jSearchPanel3 = new javax.swing.JPanel();
        icd10SearchTxt = new javax.swing.JTextField();
        jSearchScrollPane3 = new javax.swing.JScrollPane();
        icd10SearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton93 = new javax.swing.JButton();
        buttonGroup14 = new javax.swing.ButtonGroup();
        complainsDialog = new javax.swing.JDialog();
        diseasesSearchPanel = new javax.swing.JPanel();
        jSearchScrollPane4 = new javax.swing.JScrollPane();
        complainsdialogtable = new com.afrisoftech.dbadmin.JTable();
        jButton912 = new javax.swing.JButton();
        searchDiseasesTxt = new javax.swing.JTextField();
        buttonGroup15 = new javax.swing.ButtonGroup();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jPanel422 = new javax.swing.JPanel();
        searchButton22 = new javax.swing.JButton();
        jTextField82 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jTextField922 = new javax.swing.JTextField();
        jLabel812 = new javax.swing.JLabel();
        jTextField911 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox12 = new javax.swing.JComboBox();
        jPanel43 = new javax.swing.JPanel();
        jTextField93 = new javax.swing.JTextField();
        searchButton3 = new javax.swing.JButton();
        jPanel421 = new javax.swing.JPanel();
        searchButton21 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField921 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel413 = new javax.swing.JPanel();
        jTextField912 = new javax.swing.JTextField();
        searchButton12 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jPanel93 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        datePicker12 = new com.afrisoftech.lib.DatePicker();
        jPanel52 = new javax.swing.JPanel();
        try {
            java.lang.Class.forName("org.postgresql.Driver");
        }catch (java.lang.ClassNotFoundException sl){
            System.out.println(sl.getMessage());
        }

        requestbtn = new javax.swing.JButton();
        try {
            java.lang.Class.forName("org.postgresql.Driver");
        }catch (java.lang.ClassNotFoundException sl){
            System.out.println(sl.getMessage());
        }

        jButton121 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        spacerPanel = new javax.swing.JPanel();
        buttonGroup16 = new javax.swing.ButtonGroup();
        reaadlabbuttonGroup = new javax.swing.ButtonGroup();
        tbScreeningDialog = new javax.swing.JDialog();
        screeningHeaderPanel = new javax.swing.JPanel();
        visitDateLbl = new javax.swing.JLabel();
        screeningDatePicker = new com.afrisoftech.lib.DatePicker();
        jLabel8 = new javax.swing.JLabel();
        tbScreeningPatientNoTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tbScreeningPatientNameTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tbScreeningAgeTxt = new javax.swing.JTextField();
        screeningBodyPanel = new javax.swing.JPanel();
        tbScreeningScrollPane = new javax.swing.JScrollPane();
        tbScreeningTable = new com.afrisoftech.dbadmin.JTable();
        screeningActionDialog = new javax.swing.JPanel();
        tbScreeningSaveDataBtn = new javax.swing.JButton();
        tbScreeningClearFormBtn = new javax.swing.JButton();
        tbScreeningCloseBtn = new javax.swing.JButton();
        tbScreeningSpacerLbl = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        tbScreeningSaveInvestigationsBtn = new javax.swing.JButton();
        tbScreeeningGeneExpertChkbx = new javax.swing.JCheckBox();
        tbScreeningSputumChkbx = new javax.swing.JCheckBox();
        tbScreeningChestXrayChkbx = new javax.swing.JCheckBox();
        spacerlabels = new javax.swing.JLabel();
        spacerlabelExtra = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        screeningInvestigationsTxt = new javax.swing.JEditorPane();
        tbScreeningResultsPanel = new javax.swing.JPanel();
        tbScreeningSaveInvestigationsResultsBtn = new javax.swing.JButton();
        tbScreeningGeneExpertResultCmbx = new javax.swing.JComboBox();
        tbScreeningSputumSmearResultCmbx = new javax.swing.JComboBox();
        tbScreeningChestXrayResultCmbx = new javax.swing.JComboBox();
        jScrollPane24 = new javax.swing.JScrollPane();
        screeningResultsTxt = new javax.swing.JEditorPane();
        symptomsDialog = new javax.swing.JDialog();
        symptomsSearchPanel = new javax.swing.JPanel();
        symptomsSearchScrollPane = new javax.swing.JScrollPane();
        symptomsDialogTable = new com.afrisoftech.dbadmin.JTable();
        symptomsCloseButton = new javax.swing.JButton();
        consultationTabbedPane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        waitingclinicscmbx = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        urgencyComboBox = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        loadALLpatientsbtn = new javax.swing.JButton();
        searchpatienttxtfld = new javax.swing.JTextField();
        loadTRIAGEDpatientsbtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        normalTextField = new javax.swing.JTextField();
        seenTextField = new javax.swing.JTextField();
        triageTextField = new javax.swing.JTextField();
        x_rayresultsTextField = new javax.swing.JTextField();
        labTextField = new javax.swing.JTextField();
        emergencyTextField = new javax.swing.JTextField();
        internalReferralLbl = new javax.swing.JLabel();
        internalReferralCmbx = new javax.swing.JComboBox<>();
        internalReferralBtn = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel33 = new javax.swing.JPanel();
        patientNameTxt = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        nameNoTxt = new javax.swing.JTextField();
        searchButton2 = new javax.swing.JButton();
        jPanel4111 = new javax.swing.JPanel();
        jTextField9111 = new javax.swing.JTextField();
        searchButton111 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton82 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        maleChkbx = new javax.swing.JCheckBox();
        femaleChkbx = new javax.swing.JCheckBox();
        jPanel30 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        ageTxt = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        paymentModeTxt = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        clinicComboBox = new javax.swing.JComboBox();
        schemeNameTxt = new javax.swing.JTextField();
        schemeTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        clerkingwaitingJscrl = new javax.swing.JScrollPane();
        clerkingwaitingTable = new com.afrisoftech.dbadmin.JXTable();
        jPanel41 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton71 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        symptomsTable = new com.afrisoftech.dbadmin.JTable();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel74 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        rxplanTable = new com.afrisoftech.dbadmin.JTable();
        tabbedpaneAll = new javax.swing.JTabbedPane();
        globalpane = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        firstpageHistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        neurohistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel60 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        gynaehistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel79 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        endocrinehistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel90 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        surgeryhistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel100 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        medicationhistoryTable = new com.afrisoftech.dbadmin.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton61 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        try {
            java.lang.Class.forName("org.postgresql.Driver");
        }catch (java.lang.ClassNotFoundException sl){
            System.out.println(sl.getMessage());
        }

        jButton12 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        doctorsCPOEPanel = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        try {
            java.lang.Class.forName("org.postgresql.Driver");
        }catch (java.lang.ClassNotFoundException sl){
            System.out.println(sl.getMessage());
        }

        clerkingSavingbtn = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        clerkingTabbedPane = new javax.swing.JTabbedPane();
        clerkingPanel = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel71 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        vitalsignstable = new com.afrisoftech.dbadmin.JTable();
        diagnosispane = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jSplitPane5 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane131 = new javax.swing.JScrollPane();
        clinicalExamineditorTxt = new javax.swing.JEditorPane();
        provisionalDiagnosistxt = new javax.swing.JTextField();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        complainsTextPaneTxt = new javax.swing.JTextPane();
        complainstxt = new javax.swing.JTextField();
        requestspanel = new javax.swing.JPanel();
        cpoeSplitPane = new javax.swing.JSplitPane();
        examinationpane = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        searchCheckBox42 = new javax.swing.JCheckBox();
        searchCheckBox51 = new javax.swing.JCheckBox();
        generalCheckBox = new javax.swing.JCheckBox();
        lablatoryCheck = new javax.swing.JCheckBox();
        pharmacyCheckBox = new javax.swing.JCheckBox();
        radiologyCheck = new javax.swing.JCheckBox();
        notesTextField = new javax.swing.JTextField();
        searchServicesMainPanel = new javax.swing.JPanel();
        generalServicesOrderingPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable14 = new com.afrisoftech.dbadmin.JTable();
        pharmacyRequestsPanel = new javax.swing.JPanel();
        pharmacyScrollPane = new javax.swing.JScrollPane();
        pharmacyTable = new com.afrisoftech.dbadmin.JTable();
        servicesOrderHeaderPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable13 = new com.afrisoftech.dbadmin.JTable();
        viewBillBtn = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        diagnosticsScrollPane = new javax.swing.JScrollPane();
        diagnosticsServicesTable = new com.afrisoftech.dbadmin.JTable();
        searchservicesTextField = new javax.swing.JTextField();
        icd10CodingPanel = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        diagnosisTable = new com.afrisoftech.dbadmin.JTable();
        ICD10CheckBox = new javax.swing.JCheckBox();
        noICD10CheckBox = new javax.swing.JCheckBox();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        referalCheckbox = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        printgeneralCheckBox = new javax.swing.JCheckBox();
        printlablatoryCheck = new javax.swing.JCheckBox();
        printpharmacyCheckBox = new javax.swing.JCheckBox();
        printradiologyCheck = new javax.swing.JCheckBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        requestsViewTable = new com.afrisoftech.dbadmin.JTable();
        printgeneralButton = new javax.swing.JButton();
        printlabButton = new javax.swing.JButton();
        printprescriptionsButton = new javax.swing.JButton();
        printxrayButton = new javax.swing.JButton();
        requestsCheckBox = new javax.swing.JCheckBox();
        illustrationPanel = new javax.swing.JPanel();
        drawingPanel = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        illustrationButtonPanel = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        labResultsTabbenPane = new javax.swing.JTabbedPane();
        labResultsListingPanel = new javax.swing.JPanel();
        jPanel212 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        labresultsTable = new com.afrisoftech.dbadmin.JXTable(){
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jLabel24 = new javax.swing.JLabel();
        labdatePicker = new com.afrisoftech.lib.DatePicker();
        labresultsButton = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jTextField63 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        currentCheckBox = new javax.swing.JCheckBox();
        allpatCheckBox = new javax.swing.JCheckBox();
        readCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        labResultsDisplayPanel = new javax.swing.JPanel();
        laboratoryResultsHeaderPanel = new javax.swing.JPanel();
        laboratoryResultsDatePicker = new com.afrisoftech.lib.DatePicker();
        laboratoryResultsTimeLbl = new javax.swing.JLabel();
        laboratoryResultsDateLbl = new javax.swing.JLabel();
        laboratoryResultsTimeTxt = new javax.swing.JTextField();
        laboratoryResultsPatientNumberTxt = new javax.swing.JTextField();
        laboratoryResultsPatientNameLbl = new javax.swing.JLabel();
        laboratoryResultsPatientNumberLbl = new javax.swing.JLabel();
        laboratoryResultsPatientNameTxt = new javax.swing.JTextField();
        labResultsDisplayDetailsJscrl = new javax.swing.JScrollPane();
        laboratoryProcedureResultDetailsTxt = new javax.swing.JEditorPane();
        laboratoryProcedureNameLbl = new javax.swing.JLabel();
        laboratoryProcedureDescriptionTxt = new javax.swing.JTextField();
        ageGenderTxt = new javax.swing.JTextField();
        ageGenderLbl = new javax.swing.JLabel();
        laboratoryResultsBodyPanel = new javax.swing.JPanel();
        laboratoryDisplayJscrl = new javax.swing.JScrollPane();
        laboratoryResultsDisplayTbl = new com.afrisoftech.dbadmin.JXTable();
        laboratoryResultsButtonPanel = new javax.swing.JPanel();
        back2LabListingBrn = new javax.swing.JButton();
        spacerLabel = new javax.swing.JLabel();
        displayLaboratoryResultsPDFBtn = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel213 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        radiologyResultsTbl = new com.afrisoftech.dbadmin.JTable(){
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jLabel27 = new javax.swing.JLabel();
        radiologyResultsDatePicker = new com.afrisoftech.lib.DatePicker();
        jButton38 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JSeparator();
        jTextField64 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel83 = new javax.swing.JPanel();
        attendjCheckBox53 = new javax.swing.JCheckBox();
        schooljCheckBox62 = new javax.swing.JCheckBox();
        heavymanCheckBox72 = new javax.swing.JCheckBox();
        driveCheckBox82 = new javax.swing.JCheckBox();
        othersCheckBox91 = new javax.swing.JCheckBox();
        jPanel73 = new javax.swing.JPanel();
        bedreRadioButton1 = new javax.swing.JRadioButton();
        convalesceRadioButton2 = new javax.swing.JRadioButton();
        lightdutiesRadioButton3 = new javax.swing.JRadioButton();
        othersRadioButton4 = new javax.swing.JRadioButton();
        jLabel83 = new javax.swing.JLabel();
        explanationTextField94 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        namejTextField18 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        companyTextField23 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        memberjTextField41 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        this.jTextField7.setVisible(false);
        jPanel61 = new javax.swing.JPanel();
        patnoTextField = new javax.swing.JTextField();
        searchButton4 = new javax.swing.JButton();
        jTextField51 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        accTextField114 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        categoryTextField19 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        paymentjTextField20 = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        datePicker14 = new com.afrisoftech.lib.DatePicker();
        jLabel101 = new javax.swing.JLabel();
        sheetjTextField101 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        startdatePicker111 = new com.afrisoftech.lib.DatePicker();
        jLabel612 = new javax.swing.JLabel();
        jLabel621 = new javax.swing.JLabel();
        resumptiondatePicker121 = new com.afrisoftech.lib.DatePicker();
        jLabel54 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        noofsickjTextField31 = new javax.swing.JTextField();
        doctorsTextField81 = new javax.swing.JTextField();

        jButton15 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel231 = new javax.swing.JPanel();
        jPanel811 = new javax.swing.JPanel();
        jLabel1133 = new javax.swing.JLabel();
        esnursejTextField1213 = new javax.swing.JTextField();
        jLabel342 = new javax.swing.JLabel();
        consultanjTextField241 = new javax.swing.JTextField();
        jLabel241 = new javax.swing.JLabel();
        transtechnicianjTextField181 = new javax.swing.JTextField();
        jScrollPane161 = new javax.swing.JScrollPane();
        clinicalsummjTextPane14 = new javax.swing.JTextPane();
        jLabel122 = new javax.swing.JLabel();

        jButton122 = new javax.swing.JButton();
        insttransjTextField1711 = new javax.swing.JTextField();
        jLabel9211 = new javax.swing.JLabel();
        jLabel2311 = new javax.swing.JLabel();
        transreasonjTextField811 = new javax.swing.JTextField();
        jLabel11311 = new javax.swing.JLabel();
        provisiodiagjTextField12111 = new javax.swing.JTextField();
        jButton541 = new javax.swing.JButton();
        jPanel423 = new javax.swing.JPanel();
        otherspatnojTextField923 = new javax.swing.JTextField();
        searchButton23 = new javax.swing.JButton();
        othernamejTextField115 = new javax.swing.JTextField();
        otherpaymentjTextField211 = new javax.swing.JTextField();
        othercategoryjTextField24 = new javax.swing.JTextField();
        jLabel911 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        outpatientCheckBox = new javax.swing.JCheckBox();
        inpatientCheckBox = new javax.swing.JCheckBox();
        jPanel32 = new javax.swing.JPanel();
        searchnocheckbx = new javax.swing.JCheckBox();
        searchnamecheckbx = new javax.swing.JCheckBox();
        transdatePicker = new com.afrisoftech.lib.DatePicker();

        pharmacySearchDialog.setModal(true);
        pharmacySearchDialog.setUndecorated(true);
        pharmacySearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        pharmacySearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pharmacySearchPanel.setLayout(new java.awt.GridBagLayout());

        pharmacySearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pharmacySearchTxtCaretUpdate(evt);
            }
        });
        pharmacySearchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacySearchTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        pharmacySearchPanel.add(pharmacySearchTxt, gridBagConstraints);

        pharmacySearchScrollPane.setDoubleBuffered(true);

        pharmacySearchTable.setDoubleBuffered(true);
        pharmacySearchTable.setShowHorizontalLines(false);
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
        pharmacySearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pharmacySearchTableMouseClicked(evt);
            }
        });
        pharmacySearchScrollPane.setViewportView(pharmacySearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        pharmacySearchPanel.add(pharmacySearchScrollPane, gridBagConstraints);

        jButton911.setText("Dispose");
        jButton911.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton911ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pharmacySearchPanel.add(jButton911, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pharmacySearchDialog.getContentPane().add(pharmacySearchPanel, gridBagConstraints);

        jSearchDialog.setMinimumSize(new java.awt.Dimension(600, 200));
        jSearchDialog.setModal(true);
        jSearchDialog.setUndecorated(true);
        jSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jTextField112.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField112CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel.add(jTextField112, gridBagConstraints);

        jSearchScrollPane.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

        jButton92.setText("Dispose");
        jButton92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton92ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton92, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

        jSearchDialog211.setModal(true);
        jSearchDialog211.setUndecorated(true);
        jSearchDialog211.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel211.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel211.setLayout(new java.awt.GridBagLayout());

        jTextField11311.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11311CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel211.add(jTextField11311, gridBagConstraints);

        jSearchTable211.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable211.setShowHorizontalLines(false);
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
        jSearchTable211.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable211MouseClicked(evt);
            }
        });
        jSearchScrollPane211.setViewportView(jSearchTable211);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel211.add(jSearchScrollPane211, gridBagConstraints);

        jButton5211.setText("Dispose");
        jButton5211.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5211ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel211.add(jButton5211, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog211.getContentPane().add(jSearchPanel211, gridBagConstraints);

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel2.setLayout(new java.awt.GridBagLayout());

        jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField113CaretUpdate(evt);
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

        jSearchTable2.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable2.setShowHorizontalLines(false);
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
        jSearchDialog2.getContentPane().add(jSearchPanel2, gridBagConstraints);

        jSearchDialog212.setModal(true);
        jSearchDialog212.setUndecorated(true);
        jSearchDialog212.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel212.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel212.setLayout(new java.awt.GridBagLayout());

        jTextField11312.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11312CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel212.add(jTextField11312, gridBagConstraints);

        jSearchTable213.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable213.setShowHorizontalLines(false);
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
        jSearchTable213.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable213MouseClicked(evt);
            }
        });
        jSearchScrollPane213.setViewportView(jSearchTable213);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel212.add(jSearchScrollPane213, gridBagConstraints);

        jButton5213.setText("Dispose");
        jButton5213.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5213ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel212.add(jButton5213, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog212.getContentPane().add(jSearchPanel212, gridBagConstraints);

        jSearchDialog11.setModal(true);
        jSearchDialog11.setUndecorated(true);
        jSearchDialog11.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel11.setLayout(new java.awt.GridBagLayout());

        jTextField1111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jSearchPanel11.add(jTextField1111, gridBagConstraints);

        jSearchTable11.setShowHorizontalLines(false);
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
        jSearchTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable11MouseClicked(evt);
            }
        });
        jSearchScrollPane11.setViewportView(jSearchTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel11.add(jSearchScrollPane11, gridBagConstraints);

        jButton91.setText("Dispose");
        jButton91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton91ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(jButton91, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog11.getContentPane().add(jSearchPanel11, gridBagConstraints);

        jSearchDialog213.setModal(true);
        jSearchDialog213.setUndecorated(true);
        jSearchDialog213.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel213.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel213.setLayout(new java.awt.GridBagLayout());

        jTextField11313.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11313CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel213.add(jTextField11313, gridBagConstraints);

        jSearchScrollPane212.setDoubleBuffered(true);

        jSearchTable212.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jSearchTable212.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable212.setDoubleBuffered(true);
        jSearchTable212.setShowHorizontalLines(false);
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
        jSearchTable212.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable212MouseClicked(evt);
            }
        });
        jSearchScrollPane212.setViewportView(jSearchTable212);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel213.add(jSearchScrollPane212, gridBagConstraints);

        jButton5212.setText("Dispose");
        jButton5212.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5212ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel213.add(jButton5212, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog213.getContentPane().add(jSearchPanel213, gridBagConstraints);

        jSearchDialog214.setModal(true);
        jSearchDialog214.setUndecorated(true);
        jSearchDialog214.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel214.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel214.setLayout(new java.awt.GridBagLayout());

        jTextField11314.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11314CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel214.add(jTextField11314, gridBagConstraints);

        jSearchScrollPane214.setDoubleBuffered(true);

        jSearchTable214.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable214.setDoubleBuffered(true);
        jSearchTable214.setShowHorizontalLines(false);
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
        jSearchTable214.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable214MouseClicked(evt);
            }
        });
        jSearchScrollPane214.setViewportView(jSearchTable214);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel214.add(jSearchScrollPane214, gridBagConstraints);

        jButton5214.setText("Dispose");
        jButton5214.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5214ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel214.add(jButton5214, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog214.getContentPane().add(jSearchPanel214, gridBagConstraints);

        jEditorPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vital Signs", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jEditorPane2.setAutoscrolls(false);
        jScrollPane4.setViewportView(jEditorPane2);

        icd10SearchDialog.setMinimumSize(new java.awt.Dimension(300, 50));
        icd10SearchDialog.setModal(true);
        icd10SearchDialog.setUndecorated(true);
        icd10SearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel3.setLayout(new java.awt.GridBagLayout());

        icd10SearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                icd10SearchTxtCaretUpdate(evt);
            }
        });
        icd10SearchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                icd10SearchTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel3.add(icd10SearchTxt, gridBagConstraints);

        icd10SearchTable.setShowHorizontalLines(false);
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
        icd10SearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icd10SearchTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                icd10SearchTableMouseEntered(evt);
            }
        });
        jSearchScrollPane3.setViewportView(icd10SearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel3.add(jSearchScrollPane3, gridBagConstraints);

        jButton93.setText("Dispose");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel3.add(jButton93, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        icd10SearchDialog.getContentPane().add(jSearchPanel3, gridBagConstraints);

        complainsDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        diseasesSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        diseasesSearchPanel.setLayout(new java.awt.GridBagLayout());

        jSearchScrollPane4.setDoubleBuffered(true);

        complainsdialogtable.setDoubleBuffered(true);
        complainsdialogtable.setShowHorizontalLines(false);
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
        complainsdialogtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                complainsdialogtableMouseClicked(evt);
            }
        });
        jSearchScrollPane4.setViewportView(complainsdialogtable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        diseasesSearchPanel.add(jSearchScrollPane4, gridBagConstraints);

        jButton912.setText("Dispose");
        jButton912.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton912ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        diseasesSearchPanel.add(jButton912, gridBagConstraints);

        searchDiseasesTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchDiseasesTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        diseasesSearchPanel.add(searchDiseasesTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        complainsDialog.getContentPane().add(diseasesSearchPanel, gridBagConstraints);

        jSearchTable.setDoubleBuffered(true);
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

        jPanel422.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel422.setLayout(new java.awt.GridBagLayout());

        searchButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton22.setToolTipText("Search");
        searchButton22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton22.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton22.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton22ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel422.add(searchButton22, gridBagConstraints);

        jTextField82.setEditable(false);
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select DISTINCT f_name||' '||l_name as name FROM secure_menu_access WHERE login_name = current_user");
            while (rset1.next()) {
                jTextField82.setText(rset1.getObject(1).toString());
                jTextField8.setText(rset1.getObject(1).toString());
                doctorsTextField81.setText(rset1.getObject(1).toString());
                jTextField911.setText(rset1.getObject(1).toString());
                jTextField9111.setText(rset1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel422.add(jTextField82, gridBagConstraints);

        jLabel102.setText("Doctor No.");

        jTextField922.setEditable(false);
        jTextField922.setText(getUser());

        jLabel812.setForeground(new java.awt.Color(255, 0, 51));
        jLabel812.setText("Doctor's Name");

        jTextField911.setText("jTextField1");

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Patient Here"));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        jLabel92.setText("Service No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jLabel92, gridBagConstraints);

        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jLabel13, gridBagConstraints);

        jTextField12.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel24.add(jTextField12, gridBagConstraints);

        jLabel23.setForeground(new java.awt.Color(255, 0, 51));
        jLabel23.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel24.add(jLabel23, gridBagConstraints);

        jLabel32.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel24.add(jLabel32, gridBagConstraints);

        jTextField22.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel24.add(jTextField22, gridBagConstraints);

        jTextField4.setEditable(false);
        jTextField4.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField4.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jTextField4, gridBagConstraints);

        jComboBox12.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct UPPER(main_service) from pb_operating_parameters  EXCEPT select main_service from pb_operating_parameters WHERE category = 'CA'"));
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jComboBox12, gridBagConstraints);

        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel43.setLayout(new java.awt.GridBagLayout());

        jTextField93.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel43.add(jTextField93, gridBagConstraints);

        searchButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton3.setToolTipText("Search");
        searchButton3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton3.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton3.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel43.add(searchButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jPanel43, gridBagConstraints);

        jPanel421.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel421.setLayout(new java.awt.GridBagLayout());

        searchButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton21.setToolTipText("Search");
        searchButton21.setEnabled(false);
        searchButton21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton21.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton21.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton21ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel421.add(searchButton21, gridBagConstraints);

        jTextField8.setEditable(false);
        jTextField8.setText(getUser());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel421.add(jTextField8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel24.add(jPanel421, gridBagConstraints);

        jLabel82.setForeground(new java.awt.Color(255, 0, 51));
        jLabel82.setText("Doctor's Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(jLabel82, gridBagConstraints);

        jLabel10.setText("Ref No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(jLabel10, gridBagConstraints);

        jTextField921.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jTextField921, gridBagConstraints);

        jLabel62.setText("Req No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel24.add(jLabel62, gridBagConstraints);

        jTextField10.setEditable(false);
        jTextField10.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField10.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel24.add(jTextField10, gridBagConstraints);

        jLabel14.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel24.add(jLabel14, gridBagConstraints);

        jPanel413.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel413.setLayout(new java.awt.GridBagLayout());

        jTextField912.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel413.add(jTextField912, gridBagConstraints);

        searchButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton12.setToolTipText("Search");
        searchButton12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton12.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton12.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel413.add(searchButton12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel24.add(jPanel413, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel24.add(jPanel18, gridBagConstraints);

        jTextField5.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel24.add(jTextField5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        jPanel14.add(jPanel24, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jSeparator13, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel14.add(jPanel10, gridBagConstraints);

        jPanel93.setLayout(new java.awt.GridBagLayout());

        jLabel52.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel93.add(jLabel52, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel93.add(datePicker12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel93, gridBagConstraints);

        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel52.setLayout(new java.awt.GridBagLayout());

        requestbtn.setMnemonic('s');
        requestbtn.setText("Request");
        requestbtn.setToolTipText("Click here enter data");
        requestbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(requestbtn, gridBagConstraints);

        jButton121.setMnemonic('R');
        jButton121.setText("Req & Print");
        jButton121.setToolTipText("Click here enter data");
        jButton121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton121ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton121, gridBagConstraints);

        jButton22.setText("Reprint");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton22, gridBagConstraints);

        jButton63.setMnemonic('r');
        jButton63.setText("Remove Row");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton63, gridBagConstraints);

        jButton33.setMnemonic('l');
        jButton33.setText("Clear");
        jButton33.setToolTipText("Click here to clear textfields");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton33, gridBagConstraints);

        jButton43.setMnemonic('C');
        jButton43.setText("Close");
        jButton43.setToolTipText("Click here to close window");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton43, gridBagConstraints);

        jButton5.setMnemonic('R');
        jButton5.setText("Replace");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel52.add(jButton5, gridBagConstraints);

        spacerPanel.setBackground(new java.awt.Color(204, 204, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        jPanel52.add(spacerPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jPanel52, gridBagConstraints);

        tbScreeningDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        tbScreeningDialog.setTitle("TB Screening Dialog");
        tbScreeningDialog.setModal(true);
        tbScreeningDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        screeningHeaderPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        screeningHeaderPanel.setLayout(new java.awt.GridBagLayout());

        visitDateLbl.setText("Visit Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(visitDateLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(screeningDatePicker, gridBagConstraints);

        jLabel8.setText("Patient Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(jLabel8, gridBagConstraints);

        tbScreeningPatientNoTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(tbScreeningPatientNoTxt, gridBagConstraints);

        jLabel9.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(jLabel9, gridBagConstraints);

        tbScreeningPatientNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(tbScreeningPatientNameTxt, gridBagConstraints);

        jLabel6.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(jLabel6, gridBagConstraints);

        tbScreeningAgeTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningHeaderPanel.add(tbScreeningAgeTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningDialog.getContentPane().add(screeningHeaderPanel, gridBagConstraints);

        screeningBodyPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        screeningBodyPanel.setLayout(new java.awt.GridBagLayout());

        tbScreeningScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "If yes to any, order Gene Expert, Sputum Smear and Chest Xray", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("18thCentury", 1, 10))); // NOI18N

        tbScreeningTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Screening Indicator", "Yes", "No", "Outcome/Comments", "Indicator"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbScreeningScrollPane.setViewportView(tbScreeningTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningBodyPanel.add(tbScreeningScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        tbScreeningDialog.getContentPane().add(screeningBodyPanel, gridBagConstraints);

        screeningActionDialog.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        screeningActionDialog.setLayout(new java.awt.GridBagLayout());

        tbScreeningSaveDataBtn.setText("Save screening data");
        tbScreeningSaveDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningSaveDataBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningActionDialog.add(tbScreeningSaveDataBtn, gridBagConstraints);

        tbScreeningClearFormBtn.setText("Clear form");
        tbScreeningClearFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningClearFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningActionDialog.add(tbScreeningClearFormBtn, gridBagConstraints);

        tbScreeningCloseBtn.setText("Close dialog");
        tbScreeningCloseBtn.setEnabled(false);
        tbScreeningCloseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningCloseBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        screeningActionDialog.add(tbScreeningCloseBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        screeningActionDialog.add(tbScreeningSpacerLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningDialog.getContentPane().add(screeningActionDialog, gridBagConstraints);

        actionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordering Investigations"));
        actionsPanel.setLayout(new java.awt.GridBagLayout());

        tbScreeningSaveInvestigationsBtn.setText("Save Screening Investigations");
        tbScreeningSaveInvestigationsBtn.setEnabled(false);
        tbScreeningSaveInvestigationsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningSaveInvestigationsBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        actionsPanel.add(tbScreeningSaveInvestigationsBtn, gridBagConstraints);

        tbScreeeningGeneExpertChkbx.setText("Gene Expert");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(tbScreeeningGeneExpertChkbx, gridBagConstraints);

        tbScreeningSputumChkbx.setText("Sputum Smear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(tbScreeningSputumChkbx, gridBagConstraints);

        tbScreeningChestXrayChkbx.setText("X-Ray");
        tbScreeningChestXrayChkbx.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tbScreeningChestXrayChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningChestXrayChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(tbScreeningChestXrayChkbx, gridBagConstraints);

        spacerlabels.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(spacerlabels, gridBagConstraints);

        spacerlabelExtra.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(spacerlabelExtra, gridBagConstraints);

        jScrollPane23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Investigation request details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 153, 255))); // NOI18N
        jScrollPane23.setViewportView(screeningInvestigationsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        actionsPanel.add(jScrollPane23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningDialog.getContentPane().add(actionsPanel, gridBagConstraints);

        tbScreeningResultsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Results for screening investigations"));
        tbScreeningResultsPanel.setLayout(new java.awt.GridBagLayout());

        tbScreeningSaveInvestigationsResultsBtn.setText("Save Screening Investigation Results");
        tbScreeningSaveInvestigationsResultsBtn.setEnabled(false);
        tbScreeningSaveInvestigationsResultsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbScreeningSaveInvestigationsResultsBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningResultsPanel.add(tbScreeningSaveInvestigationsResultsBtn, gridBagConstraints);

        tbScreeningGeneExpertResultCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Negative", "Positive" }));
        tbScreeningGeneExpertResultCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Gene Expert"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningResultsPanel.add(tbScreeningGeneExpertResultCmbx, gridBagConstraints);

        tbScreeningSputumSmearResultCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Negative", "Positive", " " }));
        tbScreeningSputumSmearResultCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Sputum Smear"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningResultsPanel.add(tbScreeningSputumSmearResultCmbx, gridBagConstraints);

        tbScreeningChestXrayResultCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Non Suggestive", "Suggestive" }));
        tbScreeningChestXrayResultCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("X-Ray"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningResultsPanel.add(tbScreeningChestXrayResultCmbx, gridBagConstraints);

        jScrollPane24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clinical examination details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 153, 255))); // NOI18N
        jScrollPane24.setViewportView(screeningResultsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        tbScreeningResultsPanel.add(jScrollPane24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tbScreeningDialog.getContentPane().add(tbScreeningResultsPanel, gridBagConstraints);

        symptomsDialog.setAutoRequestFocus(false);
        symptomsDialog.setFocusable(false);
        symptomsDialog.setFocusableWindowState(false);
        symptomsDialog.setUndecorated(true);
        symptomsDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        symptomsSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        symptomsSearchPanel.setLayout(new java.awt.GridBagLayout());

        symptomsSearchScrollPane.setDoubleBuffered(true);

        symptomsDialogTable.setDoubleBuffered(true);
        symptomsDialogTable.setShowHorizontalLines(false);
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
        symptomsDialogTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                symptomsDialogTableMouseClicked(evt);
            }
        });
        symptomsSearchScrollPane.setViewportView(symptomsDialogTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        symptomsSearchPanel.add(symptomsSearchScrollPane, gridBagConstraints);

        symptomsCloseButton.setText("Dispose");
        symptomsCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                symptomsCloseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        symptomsSearchPanel.add(symptomsCloseButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        symptomsDialog.getContentPane().add(symptomsSearchPanel, gridBagConstraints);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clinician/Physician Order Entry (CPOE) and Prescriptions Form - OPD Card");
        setFrameIcon(null);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        consultationTabbedPane.setForeground(new java.awt.Color(0, 51, 0));
        consultationTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultationTabbedPaneMouseClicked(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 255), 2, true), "Click The Relevant Button", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());

        waitingclinicscmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT distinct clinics FROM pb_clinics ORDER BY 1 asc"));
        //SELECT '--ALL--' as clinics UNION
        waitingclinicscmbx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                waitingclinicscmbxItemStateChanged(evt);
            }
        });
        waitingclinicscmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waitingclinicscmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(waitingclinicscmbx, gridBagConstraints);

        jLabel2.setText("Choose Clinic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        urgencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Normal", "Urgent" }));
        urgencyComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                urgencyComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(urgencyComboBox, gridBagConstraints);

        jLabel28.setText("PATIENT URGENCY LISTING");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel28, gridBagConstraints);

        loadALLpatientsbtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loadALLpatientsbtn.setForeground(new java.awt.Color(0, 51, 0));
        loadALLpatientsbtn.setText("LOAD ALL PATIENTS");
        loadALLpatientsbtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loadALLpatientsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadALLpatientsbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(loadALLpatientsbtn, gridBagConstraints);

        searchpatienttxtfld.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search patient ......", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 255))); // NOI18N
        searchpatienttxtfld.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchpatienttxtfldCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(searchpatienttxtfld, gridBagConstraints);

        loadTRIAGEDpatientsbtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loadTRIAGEDpatientsbtn.setForeground(new java.awt.Color(0, 51, 0));
        loadTRIAGEDpatientsbtn.setText("LOAD TRIAGED PATIENTS");
        loadTRIAGEDpatientsbtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loadTRIAGEDpatientsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTRIAGEDpatientsbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(loadTRIAGEDpatientsbtn, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        normalTextField.setEditable(false);
        normalTextField.setBackground(java.awt.Color.orange);
        normalTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        normalTextField.setText("NORMAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(normalTextField, gridBagConstraints);

        seenTextField.setEditable(false);
        seenTextField.setBackground(java.awt.Color.cyan);
        seenTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seenTextField.setText("SEEN BY DR.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(seenTextField, gridBagConstraints);

        triageTextField.setEditable(false);
        triageTextField.setBackground(java.awt.Color.pink);
        triageTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        triageTextField.setText("TRIAGED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(triageTextField, gridBagConstraints);

        x_rayresultsTextField.setEditable(false);
        x_rayresultsTextField.setBackground(java.awt.Color.magenta);
        x_rayresultsTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        x_rayresultsTextField.setText("X-RAY RESULTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(x_rayresultsTextField, gridBagConstraints);

        labTextField.setEditable(false);
        labTextField.setBackground(java.awt.Color.green);
        labTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labTextField.setText("LAB RESULTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(labTextField, gridBagConstraints);

        emergencyTextField.setEditable(false);
        emergencyTextField.setBackground(java.awt.Color.red);
        emergencyTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emergencyTextField.setText("EMERGENCY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel9.add(emergencyTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 6.0;
        jPanel1.add(jPanel9, gridBagConstraints);

        internalReferralLbl.setText("Referral Clinic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(internalReferralLbl, gridBagConstraints);

        internalReferralCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' as clinics UNION SELECT distinct clinics FROM pb_clinics ORDER BY 1 asc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(internalReferralCmbx, gridBagConstraints);

        internalReferralBtn.setText("Activate Referral");
        internalReferralBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                internalReferralBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(internalReferralBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(jPanel1, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField6.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(jTextField6, gridBagConstraints);

        jSplitPane4.setDividerLocation(200);

        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel33.setLayout(new java.awt.GridBagLayout());

        patientNameTxt.setEditable(false);
        patientNameTxt.setBackground(new java.awt.Color(255, 255, 255));
        patientNameTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(patientNameTxt, gridBagConstraints);

        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel42.setLayout(new java.awt.GridBagLayout());

        nameNoTxt.setEditable(false);
        nameNoTxt.setBackground(new java.awt.Color(255, 255, 255));
        nameNoTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel42.add(nameNoTxt, gridBagConstraints);

        searchButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton2.setToolTipText("Search");
        searchButton2.setEnabled(false);
        searchButton2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton2.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton2.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel42.add(searchButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel33.add(jPanel42, gridBagConstraints);

        jPanel4111.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 8))); // NOI18N
        jPanel4111.setLayout(new java.awt.GridBagLayout());

        jTextField9111.setEditable(false);
        jTextField9111.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9111.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField9111.setText(getUser());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4111.add(jTextField9111, gridBagConstraints);

        searchButton111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton111.setToolTipText("Search");
        searchButton111.setEnabled(false);
        searchButton111.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton111.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton111.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton111.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton111ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel4111.add(searchButton111, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel33.add(jPanel4111, gridBagConstraints);

        jButton72.setText("Click For History");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton72, gridBagConstraints);

        jButton82.setText("Click For Clinical Examination");
        jButton82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton82ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton82, gridBagConstraints);

        jButton1.setText("Patient Card/Notes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jButton1, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        maleChkbx.setText("MALE");
        maleChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(maleChkbx, gridBagConstraints);

        femaleChkbx.setText("FEMALE");
        femaleChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(femaleChkbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel33.add(jPanel12, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabel15.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel30.add(jLabel15, gridBagConstraints);

        ageTxt.setEditable(false);
        ageTxt.setBackground(new java.awt.Color(255, 255, 255));
        ageTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel30.add(ageTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jPanel30, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        paymentModeTxt.setEditable(false);
        paymentModeTxt.setBackground(new java.awt.Color(255, 255, 255));
        paymentModeTxt.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(paymentModeTxt, gridBagConstraints);

        jLabel42.setText("Payment Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel42, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel33.add(jPanel13, gridBagConstraints);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Choose Clinic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel25.add(jLabel3, gridBagConstraints);

        clinicComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '-select-') union (select distinct ward_name  from hp_wards)   UNION (SELECT distinct Clinics FROM pb_clinics) ORDER BY 1 asc"));
        clinicComboBox.setEnabled(false);

        clinicComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clinicComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel25.add(clinicComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        jPanel33.add(jPanel25, gridBagConstraints);

        schemeNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(schemeNameTxt, gridBagConstraints);

        schemeTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(schemeTxt, gridBagConstraints);

        jSplitPane4.setLeftComponent(jPanel33);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel21.setLayout(new java.awt.GridBagLayout());

        clerkingwaitingJscrl.setAutoscrolls(true);

        clerkingwaitingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        clerkingwaitingTable.setRowHeight(22);
        clerkingwaitingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clerkingwaitingTableMouseClicked(evt);
            }
        });
        clerkingwaitingJscrl.setViewportView(clerkingwaitingTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel21.add(clerkingwaitingJscrl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel2.add(jPanel21, gridBagConstraints);

        jSplitPane4.setRightComponent(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jSplitPane4, gridBagConstraints);

        jPanel41.setLayout(new java.awt.GridBagLayout());

        jButton4.setMnemonic('C');
        jButton4.setText("Close form");
        jButton4.setToolTipText("Click here to close window");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel41.add(jButton4, gridBagConstraints);

        jButton3.setMnemonic('l');
        jButton3.setText("Clear and refresh form");
        jButton3.setToolTipText("Click here to clear textfields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(jButton3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel41, gridBagConstraints);

        consultationTabbedPane.addTab("Waiting List", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jSeparator11, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel7, gridBagConstraints);

        jTextField61.setEditable(false);
        jTextField61.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField61.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        jPanel4.add(jTextField61, gridBagConstraints);

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel22.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel22.setLayout(new java.awt.GridBagLayout());

        jButton71.setText("Click For History");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(jButton71, gridBagConstraints);

        jButton81.setText("Click For Clinical Examination");
        jButton81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton81ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(jButton81, gridBagConstraints);

        jButton11.setText("Patient Card/Notes");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(jButton11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jPanel22, gridBagConstraints);

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel111.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Symptoms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel111.setLayout(new java.awt.GridBagLayout());

        symptomsTable.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)));
        symptomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Symptom", "Severity", "Duration of illness", "Description"
            }
        ));
        symptomsTable.setRowHeight(22);
        javax.swing.table.TableColumn column3 = null;
        for (int i = 0; i < 3; i++) {
            column3 = symptomsTable.getColumnModel().getColumn(i);
            if (i == 0) {

                column3.setPreferredWidth(100); //sport column is bigger
            } else {
                if(i == 1){
                    column3.setPreferredWidth(50);
                }else{
                    if(i == 3){
                        column3.setPreferredWidth(250);
                    }else{
                        //if (i == 2) {
                            column3.setPreferredWidth(250);
                            // }  else
                        //column.setPreferredWidth(50);
                    }
                }
            }
        }

        java.lang.Object[] strCmb11 = {"Absent","Low","Moderate","Marked"};

        javax.swing.JComboBox cmBox11 = new javax.swing.JComboBox(strCmb11);

        javax.swing.table.TableColumn teditor11 = this.symptomsTable.getColumn("Severity");

        teditor11.setCellEditor(new javax.swing.DefaultCellEditor(cmBox11));
        symptomsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                symptomsTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(symptomsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        jPanel111.add(jScrollPane11, gridBagConstraints);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Degree of Severity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 70);
        jPanel111.add(jLabel111, gridBagConstraints);

        jEditorPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remarks", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(0, 102, 153))); // NOI18N
        jScrollPane12.setViewportView(jEditorPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel111.add(jScrollPane12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        jPanel5.add(jPanel111, gridBagConstraints);

        jPanel74.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "............", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        jPanel74.setLayout(new java.awt.GridBagLayout());

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder("RX Plan"));

        rxplanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Marital Status", "Sexual History", "Contraceptive Use", "Contraceptive Method", "Chronic Illness", "Allergy", "Allergy Type", "Alcohol Use", "Smoking", "X-Ray", "X-Ray Taken"
            }
        ));
        rxplanTable.setGridColor(new java.awt.Color(204, 204, 255));
        rxplanTable.setRowHeight(22);
        javax.swing.table.TableColumn column112 = null;
        for (int ii = 0; ii < 8; ii++) {
            column112 = rxplanTable.getColumnModel().getColumn(ii);
            column112.setPreferredWidth(100);
        }

        java.lang.Object[] strCmb22 = {"Under Age", "Never Married", "Engaged", "Married", "Widowed", "Co-habiting", "Separated", "Divorced" };

        javax.swing.JComboBox maritalcmBox = new javax.swing.JComboBox(strCmb22);

        javax.swing.table.TableColumn teditor22 = this.rxplanTable.getColumn("Marital Status");

        teditor22.setCellEditor(new javax.swing.DefaultCellEditor(maritalcmBox));

        java.lang.Object[] strCmb12 = {" N/A", "Active", "Not Active" };

        javax.swing.JComboBox sexualcmBox = new javax.swing.JComboBox(strCmb12);

        javax.swing.table.TableColumn teditor12 = this.rxplanTable.getColumn("Sexual History");

        teditor12.setCellEditor(new javax.swing.DefaultCellEditor(sexualcmBox));

        java.lang.Object[] strCmb32 = {"No", "Yes" };

        javax.swing.JComboBox contraceptivecmBox = new javax.swing.JComboBox(strCmb32);
        contraceptivecmBox.setEditable(true);
        javax.swing.table.TableColumn teditor32 = this.rxplanTable.getColumn("Contraceptive Use");

        teditor32.setCellEditor(new javax.swing.DefaultCellEditor(contraceptivecmBox));

        java.lang.Object[] strCmb42 = {"No", "Yes" };

        javax.swing.JComboBox chronicCmBox = new javax.swing.JComboBox(strCmb42);
        chronicCmBox.setEditable(true);
        javax.swing.table.TableColumn teditor42 = this.rxplanTable.getColumn("Chronic Illness");

        teditor42.setCellEditor(new javax.swing.DefaultCellEditor(chronicCmBox));

        java.lang.Object[] strCmball = {"None", "Occational","Moderate","Heavy" };

        javax.swing.JComboBox allergyCmBox = new javax.swing.JComboBox(strCmball);
        allergyCmBox.setEditable(true);
        javax.swing.table.TableColumn teditorall = this.rxplanTable.getColumn("Allergy");

        teditorall.setCellEditor(new javax.swing.DefaultCellEditor(allergyCmBox));

        java.lang.Object[] strCmb5 = {"None", "Occational","Moderate","Heavy" };

        javax.swing.JComboBox alcoholCmBox = new javax.swing.JComboBox(strCmb5);
        alcoholCmBox.setEditable(true);
        javax.swing.table.TableColumn teditoralco = this.rxplanTable.getColumn("Alcohol Use");

        teditoralco.setCellEditor(new javax.swing.DefaultCellEditor(alcoholCmBox));

        java.lang.Object[] strCmb6 = {"None", "Occational","Moderate","Heavy" };

        javax.swing.JComboBox cigaCmBox = new javax.swing.JComboBox(strCmb6);
        cigaCmBox.setEditable(true);
        javax.swing.table.TableColumn teditorciga = this.rxplanTable.getColumn("Smoking");

        teditorciga.setCellEditor(new javax.swing.DefaultCellEditor(cigaCmBox));

        java.lang.Object[] strCmb7 =  {"No", "Yes" };

        javax.swing.JComboBox xCmBox = new javax.swing.JComboBox(strCmb7);
        xCmBox.setEditable(true);
        javax.swing.table.TableColumn teditorx = this.rxplanTable.getColumn("X-Ray");

        teditorx.setCellEditor(new javax.swing.DefaultCellEditor(xCmBox));

        rxplanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rxplanTableMouseClicked(evt);
            }
        });
        rxplanTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rxplanTableKeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(rxplanTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel74.add(jScrollPane15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 6.0;
        jPanel5.add(jPanel74, gridBagConstraints);

        jTabbedPane2.addTab("Symptoms", jPanel5);

        tabbedpaneAll.setBackground(new java.awt.Color(255, 255, 255));
        tabbedpaneAll.setForeground(new java.awt.Color(204, 0, 0));
        tabbedpaneAll.setFont(new java.awt.Font("Droid Serif", 1, 13)); // NOI18N
        tabbedpaneAll.setMinimumSize(new java.awt.Dimension(831, 568));
        tabbedpaneAll.setPreferredSize(new java.awt.Dimension(831, 568));

        globalpane.setLayout(new java.awt.GridBagLayout());

        firstpageHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane13.setViewportView(firstpageHistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        globalpane.add(jScrollPane13, gridBagConstraints);

        tabbedpaneAll.addTab("General History", globalpane);

        jPanel39.setLayout(new java.awt.GridBagLayout());

        neurohistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane18.setViewportView(neurohistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel39.add(jScrollPane18, gridBagConstraints);

        tabbedpaneAll.addTab("Respiratory/Cardiac/Neuro", jPanel39);

        jPanel60.setLayout(new java.awt.GridBagLayout());

        gynaehistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane19.setViewportView(gynaehistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jScrollPane19, gridBagConstraints);

        tabbedpaneAll.addTab("Gastric/Renal/Gynae", jPanel60);

        jPanel79.setLayout(new java.awt.GridBagLayout());

        endocrinehistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(endocrinehistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel79.add(jScrollPane20, gridBagConstraints);

        tabbedpaneAll.addTab("Muscular S/Endocrine", jPanel79);

        jPanel90.setLayout(new java.awt.GridBagLayout());

        surgeryhistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane21.setViewportView(surgeryhistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel90.add(jScrollPane21, gridBagConstraints);

        tabbedpaneAll.addTab("Blood/past surgery", jPanel90);

        jPanel100.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel100.setLayout(new java.awt.GridBagLayout());

        medicationhistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Drug Name", "Strength", "Tablets per Day", "Number at Each Time"
            }
        ));
        medicationhistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicationhistoryTableMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(medicationhistoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 8.0;
        gridBagConstraints.weighty = 1.0;
        jPanel100.add(jScrollPane22, gridBagConstraints);

        tabbedpaneAll.addTab("Current Medication", jPanel100);

        jTabbedPane2.addTab("Patient History", tabbedpaneAll);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jTabbedPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jPanel4.add(jPanel11, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jButton61.setMnemonic('r');
        jButton61.setText("Remove Row");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jButton61, gridBagConstraints);

        jButton51.setMnemonic('h');
        jButton51.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jButton51, gridBagConstraints);

        jButton41.setMnemonic('C');
        jButton41.setText("Close");
        jButton41.setToolTipText("Click here to close window");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jButton41, gridBagConstraints);

        jButton31.setMnemonic('l');
        jButton31.setText("Clear Fields");
        jButton31.setToolTipText("Click here to clear textfields");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jButton31, gridBagConstraints);

        jButton12.setMnemonic('O');
        jButton12.setText("Save");
        jButton12.setToolTipText("Click here enter data");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jButton12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel8, gridBagConstraints);

        consultationTabbedPane.addTab("Examination & History", jPanel4);

        doctorsCPOEPanel.setLayout(new java.awt.GridBagLayout());

        jPanel81.setLayout(new java.awt.GridBagLayout());

        jButton32.setMnemonic('l');
        jButton32.setText("Remove Row");
        jButton32.setToolTipText("Click here to clear textfields");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel81.add(jButton32, gridBagConstraints);

        clerkingSavingbtn.setMnemonic('O');
        clerkingSavingbtn.setText(".......");
        clerkingSavingbtn.setToolTipText("");
        clerkingSavingbtn.setEnabled(false);
        clerkingSavingbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clerkingSavingbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel81.add(clerkingSavingbtn, gridBagConstraints);

        jButton37.setMnemonic('l');
        jButton37.setText("Clear");
        jButton37.setToolTipText("Click here to clear textfields");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel81.add(jButton37, gridBagConstraints);

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveButton.setText("Save     ");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel81.add(saveButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        doctorsCPOEPanel.add(jPanel81, gridBagConstraints);

        clerkingTabbedPane.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        clerkingTabbedPane.setForeground(new java.awt.Color(0, 153, 0));
        clerkingTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        clerkingPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        clerkingPanel.add(jSeparator12, gridBagConstraints);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        clerkingPanel.add(jLabel71, gridBagConstraints);

        jTextField62.setEditable(false);
        jTextField62.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField62.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        clerkingPanel.add(jTextField62, gridBagConstraints);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Details"));
        jPanel23.setLayout(new java.awt.GridBagLayout());

        vitalsignstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        vitalsignstable.setRowHeight(20);
        jScrollPane7.setViewportView(vitalsignstable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel23.add(jScrollPane7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        clerkingPanel.add(jPanel23, gridBagConstraints);

        diagnosispane.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 1, 2, 1, new java.awt.Color(0, 51, 255)));
        diagnosispane.setLayout(new java.awt.GridBagLayout());

        jPanel82.setLayout(new java.awt.GridBagLayout());

        jLabel131.setText("LMP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel82.add(jLabel131, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel82.add(datePicker2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        diagnosispane.add(jPanel82, gridBagConstraints);

        jSplitPane5.setDividerLocation(400);
        jSplitPane5.setDividerSize(15);

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        clinicalExamineditorTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Physical Examination/Findings", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jScrollPane131.setViewportView(clinicalExamineditorTxt);

        jSplitPane1.setLeftComponent(jScrollPane131);

        provisionalDiagnosistxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Working/Provisional Diagnosis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N
        provisionalDiagnosistxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                provisionalDiagnosistxtFocusGained(evt);
            }
        });
        provisionalDiagnosistxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                provisionalDiagnosistxtMouseClicked(evt);
            }
        });
        jSplitPane1.setRightComponent(provisionalDiagnosistxt);

        jSplitPane5.setRightComponent(jSplitPane1);

        jSplitPane2.setDividerLocation(0);
        jSplitPane2.setDividerSize(0);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setToolTipText("");

        jScrollPane6.setBorder(null);

        complainsTextPaneTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("History of presenting illness"));
        complainsTextPaneTxt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        complainsTextPaneTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                complainsTextPaneTxtCaretUpdate(evt);
            }
        });
        jScrollPane6.setViewportView(complainsTextPaneTxt);

        jSplitPane2.setRightComponent(jScrollPane6);

        complainstxt.setEditable(false);
        complainstxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter Complain here.....", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10), new java.awt.Color(255, 51, 51))); // NOI18N
        complainstxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                complainstxtMouseClicked(evt);
            }
        });
        complainstxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                complainstxtKeyPressed(evt);
            }
        });
        jSplitPane2.setLeftComponent(complainstxt);

        jSplitPane5.setLeftComponent(jSplitPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        diagnosispane.add(jSplitPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        clerkingPanel.add(diagnosispane, gridBagConstraints);

        clerkingTabbedPane.addTab("Clerking Patient", clerkingPanel);

        requestspanel.setLayout(new java.awt.GridBagLayout());

        cpoeSplitPane.setDividerLocation(150);

        examinationpane.setLayout(new java.awt.GridBagLayout());

        jLabel53.setText("Billed Total Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        examinationpane.add(jLabel53, gridBagConstraints);

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        examinationpane.add(jTextField3, gridBagConstraints);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        searchCheckBox42.setText("Search By Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel28.add(searchCheckBox42, gridBagConstraints);

        searchCheckBox51.setSelected(true);
        searchCheckBox51.setText("Search By Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel28.add(searchCheckBox51, gridBagConstraints);

        buttonGroup15.add(generalCheckBox);
        generalCheckBox.setForeground(new java.awt.Color(0, 0, 255));
        generalCheckBox.setText("General Services");
        generalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(generalCheckBox, gridBagConstraints);

        buttonGroup15.add(lablatoryCheck);
        lablatoryCheck.setForeground(new java.awt.Color(0, 0, 255));
        lablatoryCheck.setText("Laboratory procedures");
        lablatoryCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lablatoryCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(lablatoryCheck, gridBagConstraints);

        buttonGroup15.add(pharmacyCheckBox);
        pharmacyCheckBox.setForeground(new java.awt.Color(0, 0, 255));
        pharmacyCheckBox.setText("Pharmacy Prescriptions");
        pharmacyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacyCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(pharmacyCheckBox, gridBagConstraints);

        buttonGroup15.add(radiologyCheck);
        radiologyCheck.setForeground(new java.awt.Color(51, 0, 255));
        radiologyCheck.setText("Radiology/Imaging procedures");
        radiologyCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiologyCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(radiologyCheck, gridBagConstraints);

        notesTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        notesTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter test notes here", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 0, 153))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(notesTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        examinationpane.add(jPanel28, gridBagConstraints);

        searchServicesMainPanel.setLayout(new java.awt.GridBagLayout());

        generalServicesOrderingPanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("RX Plan"));

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Service", "Quantity", "Price", "Amount", "GL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable14.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable14.setDragEnabled(true);
        jTable14.setGridColor(new java.awt.Color(204, 204, 255));
        javax.swing.table.TableColumn column14 = null;
        for (int i = 0; i < 5; i++) {
            column14 = jTable14.getColumnModel().getColumn(i);
            if (i == 0) {

                column14.setPreferredWidth(500); //sport column is bigger
            } else {
                if(i == 1){
                    column14.setPreferredWidth(50);
                }else{
                    if(i == 5){
                        column14.setPreferredWidth(250);
                    }else{
                        //if (i == 2) {
                            column14.setPreferredWidth(100);
                            // }  else
                        //column.setPreferredWidth(50);
                    }
                }
            }
        }
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jTable14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable14KeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(jTable14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        generalServicesOrderingPanel.add(jScrollPane9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        searchServicesMainPanel.add(generalServicesOrderingPanel, gridBagConstraints);

        pharmacyRequestsPanel.setLayout(new java.awt.GridBagLayout());

        pharmacyScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("RX Plan"));

        pharmacyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Drug", "Qty ", "Cost", "Route", "Freqcy", "No.of Days", "Dosage", "Gl Code", "Code", "Pharmacy Balances", "Units"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true, true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pharmacyTable.setGridColor(new java.awt.Color(204, 204, 255));
        pharmacyTable.setRowHeight(24);
        javax.swing.table.TableColumn column1 = null;
        for (int i = 0; i < 9; i++) {
            column1 = pharmacyTable.getColumnModel().getColumn(i);
            if (i == 0) {

                column1.setPreferredWidth(200); //sport column is bigger
            } else {
                if (i == 1) {
                    column1.setPreferredWidth(50);
                } else {
                    if (i == 6) {
                        column1.setPreferredWidth(100);
                    } else {
                        //if (i == 2) {
                            column1.setPreferredWidth(50);
                            // }  else
                        //column.setPreferredWidth(50);
                    }
                }
            }
        }

        //   java.lang.Object[] strCmb2 = {"Oral", "I.M", "I.V", "Intramuscular", "Topical", "Vaginal", "Rectal", "Sublingual", "Subcutaneuos", "Intrathical", "Intradermal","Nebulization"};

        javax.swing.JComboBox cmBox2 = new javax.swing.JComboBox(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT admin_mode FROM st_admin_mode ORDER BY rank"));

        javax.swing.table.TableColumn teditor2 = this.pharmacyTable.getColumn("Route");

        teditor2.setCellEditor(new javax.swing.DefaultCellEditor(cmBox2));

        java.lang.Object[] strCmb1 = {"OD", "BD", "TID", "QID", "Stat", "Mane", "Nocte", "Prn"};

        javax.swing.JComboBox cmBox1 = new javax.swing.JComboBox(strCmb1);

        javax.swing.table.TableColumn teditor1 = this.pharmacyTable.getColumn("Freqcy");

        cmBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmBox1ActionPerformed(evt);
            }
        });

        teditor1.setCellEditor(new javax.swing.DefaultCellEditor(cmBox1));

        java.lang.Object[] strCmb3 = {"Once", "1", "3", "5", "7", "10", "14", "30","60", "90"};

        javax.swing.JComboBox cmBox3 = new javax.swing.JComboBox(strCmb3);

        cmBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmBox3ActionPerformed(evt);
            }
        });
        cmBox3.setEditable(true);
        javax.swing.table.TableColumn teditor3 = this.pharmacyTable.getColumn("No.of Days");

        teditor3.setCellEditor(new javax.swing.DefaultCellEditor(cmBox3));

        java.lang.Object[] strCmb4 = {"I", "II", "III", "IV", "2ml", "5ml", "10ml", "15ml", "20"};

        javax.swing.JComboBox cmBox4 = new javax.swing.JComboBox(strCmb4);
        cmBox4.setEditable(true);
        javax.swing.table.TableColumn teditor4 = this.pharmacyTable.getColumn("Dosage");

        teditor4.setCellEditor(new javax.swing.DefaultCellEditor(cmBox4));
        pharmacyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pharmacyTableMouseClicked(evt);
            }
        });
        pharmacyTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pharmacyTableKeyReleased(evt);
            }
        });
        pharmacyScrollPane.setViewportView(pharmacyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pharmacyRequestsPanel.add(pharmacyScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchServicesMainPanel.add(pharmacyRequestsPanel, gridBagConstraints);

        servicesOrderHeaderPanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("RX Plan"));

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Service", "Quantity", "Price", "Amount", "GL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable13.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable13.setDragEnabled(true);
        jTable13.setGridColor(new java.awt.Color(204, 204, 255));
        jTable13.setRowHeight(24);
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = jTable13.getColumnModel().getColumn(i);
            if (i == 0) {

                column.setPreferredWidth(500); //sport column is bigger
            } else {
                if(i == 1){
                    column.setPreferredWidth(50);
                }else{
                    if(i == 5){
                        column.setPreferredWidth(250);
                    }else{
                        //if (i == 2) {
                            column.setPreferredWidth(100);
                            // }  else
                        //column.setPreferredWidth(50);
                    }
                }
            }
        }
        jTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable13MouseClicked(evt);
            }
        });
        jTable13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable13KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        servicesOrderHeaderPanel.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        searchServicesMainPanel.add(servicesOrderHeaderPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        examinationpane.add(searchServicesMainPanel, gridBagConstraints);

        viewBillBtn.setText("View Scheme Patient Bill");
        viewBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBillBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        examinationpane.add(viewBillBtn, gridBagConstraints);

        cpoeSplitPane.setRightComponent(examinationpane);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        diagnosticsServicesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diagnosticsServicesTableMouseClicked(evt);
            }
        });
        diagnosticsScrollPane.setViewportView(diagnosticsServicesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(diagnosticsScrollPane, gridBagConstraints);

        searchservicesTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "search LAB OR X- RAY here...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        searchservicesTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchservicesTextFieldCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel20.add(searchservicesTextField, gridBagConstraints);

        cpoeSplitPane.setLeftComponent(jPanel20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        requestspanel.add(cpoeSplitPane, gridBagConstraints);

        clerkingTabbedPane.addTab("Service Requests", requestspanel);

        icd10CodingPanel.setLayout(new java.awt.GridBagLayout());

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setText("FINAL Diagnosis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1.0;
        icd10CodingPanel.add(jLabel113, gridBagConstraints);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DIAGNOSIS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(153, 153, 0))); // NOI18N

        diagnosisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Description", "Category"
            }
        ));
        diagnosisTable.setRowHeight(25);
        javax.swing.table.TableColumn column3x = null;
        for (int i = 0; i < 3; i++) {
            column3x = diagnosisTable.getColumnModel().getColumn(i);
            if (i == 1) {

                column3x.setPreferredWidth(300); //sport column is bigger
            } else if (i == 2) {

                column3x.setPreferredWidth(100); //sport column is bigger
            } else{

                column3x.setPreferredWidth(50);
            }
        }

        javax.swing.JComboBox cmBox2x = new javax.swing.JComboBox(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT classification FROM hp_opdisease_classification UNION SELECT DISTINCT classification FROM hp_opdisease_underfive ORDER BY 1"));
        javax.swing.table.TableColumn teditor2x = this.diagnosisTable.getColumn("Category");
        teditor2x.setCellEditor(new javax.swing.DefaultCellEditor(cmBox2x));
        diagnosisTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diagnosisTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(diagnosisTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 2);
        icd10CodingPanel.add(jScrollPane5, gridBagConstraints);

        buttonGroup16.add(ICD10CheckBox);
        ICD10CheckBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ICD10CheckBox.setForeground(new java.awt.Color(204, 0, 0));
        ICD10CheckBox.setSelected(true);
        ICD10CheckBox.setText("ICD10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        icd10CodingPanel.add(ICD10CheckBox, gridBagConstraints);

        buttonGroup16.add(noICD10CheckBox);
        noICD10CheckBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        noICD10CheckBox.setForeground(new java.awt.Color(204, 0, 0));
        noICD10CheckBox.setText("NO ICD10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        icd10CodingPanel.add(noICD10CheckBox, gridBagConstraints);

        clerkingTabbedPane.addTab("Diagnosis (ICD10 Coding)", icd10CodingPanel);

        jPanel38.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Admission Notes"));
        jScrollPane3.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel38.add(jScrollPane3, gridBagConstraints);

        jComboBox2.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select ward_name from hp_wards order by ward_name"));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel38.add(jComboBox2, gridBagConstraints);

        jLabel16.setForeground(new java.awt.Color(255, 51, 153));
        jLabel16.setText("Ward");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel38.add(jLabel16, gridBagConstraints);

        buttonGroup15.add(referalCheckbox);
        referalCheckbox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        referalCheckbox.setForeground(new java.awt.Color(255, 0, 51));
        referalCheckbox.setText("Refer for Admission");
        referalCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                referalCheckboxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel38.add(referalCheckbox, gridBagConstraints);

        jButton2.setText("Booking");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel38.add(jButton2, gridBagConstraints);

        clerkingTabbedPane.addTab("Refer for Admission", jPanel38);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        buttonGroup15.add(printgeneralCheckBox);
        printgeneralCheckBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printgeneralCheckBox.setForeground(new java.awt.Color(0, 0, 255));
        printgeneralCheckBox.setText("General");
        printgeneralCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                printgeneralCheckBoxItemStateChanged(evt);
            }
        });
        printgeneralCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printgeneralCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(printgeneralCheckBox, gridBagConstraints);

        buttonGroup15.add(printlablatoryCheck);
        printlablatoryCheck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printlablatoryCheck.setForeground(new java.awt.Color(0, 0, 255));
        printlablatoryCheck.setText("Laboratory");
        printlablatoryCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                printlablatoryCheckItemStateChanged(evt);
            }
        });
        printlablatoryCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printlablatoryCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(printlablatoryCheck, gridBagConstraints);

        buttonGroup15.add(printpharmacyCheckBox);
        printpharmacyCheckBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printpharmacyCheckBox.setForeground(new java.awt.Color(0, 0, 255));
        printpharmacyCheckBox.setText("Pharmacy");
        printpharmacyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printpharmacyCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(printpharmacyCheckBox, gridBagConstraints);

        buttonGroup15.add(printradiologyCheck);
        printradiologyCheck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printradiologyCheck.setForeground(new java.awt.Color(51, 0, 255));
        printradiologyCheck.setText("Radiology");
        printradiologyCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printradiologyCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(printradiologyCheck, gridBagConstraints);

        requestsViewTable.setModel(new javax.swing.table.DefaultTableModel(
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
        requestsViewTable.setRowHeight(22);
        jScrollPane10.setViewportView(requestsViewTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane10, gridBagConstraints);

        printgeneralButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printgeneralButton.setText("Print General Services");
        printgeneralButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204)));
        printgeneralButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printgeneralButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel6.add(printgeneralButton, gridBagConstraints);

        printlabButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printlabButton.setText("Print Lab Requests");
        printlabButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204)));
        printlabButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printlabButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel6.add(printlabButton, gridBagConstraints);

        printprescriptionsButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printprescriptionsButton.setText("Print Prescriptions");
        printprescriptionsButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204)));
        printprescriptionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printprescriptionsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel6.add(printprescriptionsButton, gridBagConstraints);

        printxrayButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printxrayButton.setText("Print X-Ray Request");
        printxrayButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204), new java.awt.Color(0, 0, 204)));
        printxrayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printxrayButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel6.add(printxrayButton, gridBagConstraints);

        buttonGroup15.add(requestsCheckBox);
        requestsCheckBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        requestsCheckBox.setForeground(new java.awt.Color(51, 0, 255));
        requestsCheckBox.setText("Requests Payments");
        requestsCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                requestsCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel6.add(requestsCheckBox, gridBagConstraints);

        clerkingTabbedPane.addTab("Print Requests", jPanel6);

        illustrationPanel.setLayout(new java.awt.GridBagLayout());

        drawingPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        drawingPanel.add(jPanel44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        illustrationPanel.add(drawingPanel, gridBagConstraints);

        illustrationButtonPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        illustrationPanel.add(illustrationButtonPanel, gridBagConstraints);

        clerkingTabbedPane.addTab("Graphical Illustration ", illustrationPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        doctorsCPOEPanel.add(clerkingTabbedPane, gridBagConstraints);

        consultationTabbedPane.addTab("Doctors' Requests (CPOE)", doctorsCPOEPanel);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel26.setLayout(new java.awt.GridBagLayout());

        labResultsTabbenPane.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        labResultsListingPanel.setLayout(new java.awt.GridBagLayout());

        jPanel212.setLayout(new java.awt.GridBagLayout());

        jScrollPane16.setAutoscrolls(true);

        labresultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date of Procedure", "Patient No.", "Patient Name", "Laboratory Request No.", "Results Read"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        labresultsTable.setRowHeight(20);

        labresultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labresultsTableMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(labresultsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel212.add(jScrollPane16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        labResultsListingPanel.add(jPanel212, gridBagConstraints);

        jLabel24.setText("Start date for listed laboratory results");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(jLabel24, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(labdatePicker, gridBagConstraints);

        labresultsButton.setMnemonic('l');
        labresultsButton.setText("Refresh listing of laboratory results");
        labresultsButton.setToolTipText("Click here to clear textfields");
        labresultsButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labresultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labresultsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        labResultsListingPanel.add(labresultsButton, gridBagConstraints);

        jButton45.setMnemonic('C');
        jButton45.setText("Close form");
        jButton45.setToolTipText("Click here to close window");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(jButton45, gridBagConstraints);

        jTextField63.setEditable(false);
        jTextField63.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField63.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        labResultsListingPanel.add(jTextField63, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(jLabel19, gridBagConstraints);

        reaadlabbuttonGroup.add(currentCheckBox);
        currentCheckBox.setSelected(true);
        currentCheckBox.setText("Display list of results for the current Patient");
        currentCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(currentCheckBox, gridBagConstraints);

        reaadlabbuttonGroup.add(allpatCheckBox);
        allpatCheckBox.setText("Display listing of results for All Patients");
        allpatCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                allpatCheckBoxItemStateChanged(evt);
            }
        });
        allpatCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allpatCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(allpatCheckBox, gridBagConstraints);

        readCheckBox.setText("Read All results for selected patient");
        readCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                readCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(readCheckBox, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 500.0;
        gridBagConstraints.weighty = 1.0;
        labResultsListingPanel.add(jLabel5, gridBagConstraints);

        labResultsTabbenPane.addTab("Listing of Laboratory Procedures that have been reported", labResultsListingPanel);

        labResultsDisplayPanel.setLayout(new java.awt.GridBagLayout());

        laboratoryResultsHeaderPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsDatePicker, gridBagConstraints);

        laboratoryResultsTimeLbl.setText("Time when prodecure was done");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsTimeLbl, gridBagConstraints);

        laboratoryResultsDateLbl.setText("Laboratory Procedure Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsDateLbl, gridBagConstraints);

        laboratoryResultsTimeTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsTimeTxt, gridBagConstraints);

        laboratoryResultsPatientNumberTxt.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsPatientNumberTxt, gridBagConstraints);

        laboratoryResultsPatientNameLbl.setText("Patient Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsPatientNameLbl, gridBagConstraints);

        laboratoryResultsPatientNumberLbl.setText("Patient Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsPatientNumberLbl, gridBagConstraints);

        laboratoryResultsPatientNameTxt.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(laboratoryResultsPatientNameTxt, gridBagConstraints);

        laboratoryProcedureResultDetailsTxt.setEditable(false);
        laboratoryProcedureResultDetailsTxt.setBackground(new java.awt.Color(204, 204, 204));
        laboratoryProcedureResultDetailsTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Details/Remarks of laboratory procedure"));
        labResultsDisplayDetailsJscrl.setViewportView(laboratoryProcedureResultDetailsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        laboratoryResultsHeaderPanel.add(labResultsDisplayDetailsJscrl, gridBagConstraints);

        laboratoryProcedureNameLbl.setText("Name of Laboratory Procedure");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        laboratoryResultsHeaderPanel.add(laboratoryProcedureNameLbl, gridBagConstraints);

        laboratoryProcedureDescriptionTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        laboratoryResultsHeaderPanel.add(laboratoryProcedureDescriptionTxt, gridBagConstraints);

        ageGenderTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(ageGenderTxt, gridBagConstraints);

        ageGenderLbl.setText("Age and Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsHeaderPanel.add(ageGenderLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labResultsDisplayPanel.add(laboratoryResultsHeaderPanel, gridBagConstraints);

        laboratoryResultsBodyPanel.setLayout(new java.awt.GridBagLayout());

        laboratoryDisplayJscrl.setBorder(javax.swing.BorderFactory.createTitledBorder("Deatails of laboratory procedure results"));

        laboratoryResultsDisplayTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        laboratoryDisplayJscrl.setViewportView(laboratoryResultsDisplayTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsBodyPanel.add(laboratoryDisplayJscrl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        labResultsDisplayPanel.add(laboratoryResultsBodyPanel, gridBagConstraints);

        laboratoryResultsButtonPanel.setLayout(new java.awt.GridBagLayout());

        back2LabListingBrn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        back2LabListingBrn.setText("<<Back to result listings panel");
        back2LabListingBrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back2LabListingBrnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsButtonPanel.add(back2LabListingBrn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsButtonPanel.add(spacerLabel, gridBagConstraints);

        displayLaboratoryResultsPDFBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        displayLaboratoryResultsPDFBtn.setText("Display results in PDF");
        displayLaboratoryResultsPDFBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayLaboratoryResultsPDFBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        laboratoryResultsButtonPanel.add(displayLaboratoryResultsPDFBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labResultsDisplayPanel.add(laboratoryResultsButtonPanel, gridBagConstraints);

        labResultsTabbenPane.addTab("Display of reports for a laboratory procedure", labResultsDisplayPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel26.add(labResultsTabbenPane, gridBagConstraints);

        consultationTabbedPane.addTab("Laboratory Results", jPanel26);

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel29.setLayout(new java.awt.GridBagLayout());

        jPanel213.setLayout(new java.awt.GridBagLayout());

        jScrollPane17.setAutoscrolls(true);

        radiologyResultsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Pat No", "Name", "No", "Read"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        radiologyResultsTbl.setRowHeight(20);

        radiologyResultsTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiologyResultsTblMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(radiologyResultsTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel213.add(jScrollPane17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        jPanel29.add(jPanel213, gridBagConstraints);

        jLabel27.setText("Start date for displayed results");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel29.add(jLabel27, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel29.add(radiologyResultsDatePicker, gridBagConstraints);

        jButton38.setMnemonic('l');
        jButton38.setText("Refresh results listing");
        jButton38.setToolTipText("Click here to clear textfields");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(jButton38, gridBagConstraints);

        jButton47.setMnemonic('C');
        jButton47.setText("Close form");
        jButton47.setToolTipText("Click here to close window");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(jButton47, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel29.add(jSeparator16, gridBagConstraints);

        jTextField64.setEditable(false);
        jTextField64.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField64.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel29.add(jTextField64, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(jLabel22, gridBagConstraints);

        consultationTabbedPane.addTab("Radiology and Imaging Results", jPanel29);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jPanel83.setBorder(javax.swing.BorderFactory.createTitledBorder("Exemptions"));
        jPanel83.setLayout(new java.awt.GridBagLayout());

        buttonGroup6.add(attendjCheckBox53);
        attendjCheckBox53.setText("Attend Work");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel83.add(attendjCheckBox53, gridBagConstraints);

        buttonGroup6.add(schooljCheckBox62);
        schooljCheckBox62.setText("School");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel83.add(schooljCheckBox62, gridBagConstraints);

        buttonGroup6.add(heavymanCheckBox72);
        heavymanCheckBox72.setText("Do Heavy Manual Work");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel83.add(heavymanCheckBox72, gridBagConstraints);

        buttonGroup6.add(driveCheckBox82);
        driveCheckBox82.setText("Drive/Handle Heavy machinery");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel83.add(driveCheckBox82, gridBagConstraints);

        buttonGroup6.add(othersCheckBox91);
        othersCheckBox91.setText("Others");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel83.add(othersCheckBox91, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jPanel83, gridBagConstraints);

        jPanel73.setBorder(javax.swing.BorderFactory.createTitledBorder("Recommedations"));
        jPanel73.setLayout(new java.awt.GridBagLayout());

        buttonGroup7.add(bedreRadioButton1);
        bedreRadioButton1.setText("Strict Bed Rest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel73.add(bedreRadioButton1, gridBagConstraints);

        buttonGroup7.add(convalesceRadioButton2);
        convalesceRadioButton2.setText("Convalesce at home");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel73.add(convalesceRadioButton2, gridBagConstraints);

        buttonGroup7.add(lightdutiesRadioButton3);
        lightdutiesRadioButton3.setText("Light duties at work");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel73.add(lightdutiesRadioButton3, gridBagConstraints);

        buttonGroup7.add(othersRadioButton4);
        othersRadioButton4.setText("Others");
        jPanel73.add(othersRadioButton4, new java.awt.GridBagConstraints());

        jLabel83.setText("Explanation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel73.add(jLabel83, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel73.add(explanationTextField94, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jPanel73, gridBagConstraints);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Select patient here"));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jPanel27.setLayout(new java.awt.GridBagLayout());

        jLabel93.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel27.add(jLabel93, gridBagConstraints);

        jLabel110.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel27.add(jLabel110, gridBagConstraints);

        namejTextField18.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel27.add(namejTextField18, gridBagConstraints);

        jLabel33.setText("Company");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel27.add(jLabel33, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel27.add(companyTextField23, gridBagConstraints);

        jLabel43.setText("Member No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel27.add(jLabel43, gridBagConstraints);

        memberjTextField41.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel27.add(memberjTextField41, gridBagConstraints);

        jTextField71.setEditable(false);
        jTextField71.setEnabled(false);
        jTextField71.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField71.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel27.add(jTextField71, gridBagConstraints);

        jPanel61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel61.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel61.setLayout(new java.awt.GridBagLayout());

        patnoTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(patnoTextField, gridBagConstraints);

        searchButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
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
        gridBagConstraints.gridy = 1;
        jPanel61.add(searchButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(jPanel61, gridBagConstraints);

        jTextField51.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField51.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel27.add(jTextField51, gridBagConstraints);

        jLabel114.setText("Account No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        jPanel27.add(jLabel114, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel27.add(accTextField114, gridBagConstraints);

        jLabel20.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel27.add(jLabel20, gridBagConstraints);

        categoryTextField19.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel27.add(categoryTextField19, gridBagConstraints);

        jLabel26.setText("Payment Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        jPanel27.add(jLabel26, gridBagConstraints);

        paymentjTextField20.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel27.add(paymentjTextField20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel16.add(jPanel27, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel15.add(jPanel16, gridBagConstraints);

        jPanel35.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.setLayout(new java.awt.GridBagLayout());

        jLabel63.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel35.add(jLabel63, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel35.add(datePicker14, gridBagConstraints);

        jLabel101.setText("Sheet No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel35.add(jLabel101, gridBagConstraints);

        sheetjTextField101.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel35.add(sheetjTextField101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jPanel35, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        this.startdatePicker111.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                changeDate();
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(startdatePicker111, gridBagConstraints);

        jLabel612.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel17.add(jLabel612, gridBagConstraints);

        jLabel621.setText("Resumption Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1.0;
        jPanel17.add(jLabel621, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel17.add(resumptiondatePicker121, gridBagConstraints);

        jLabel54.setText("No. of sick Days");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel17.add(jLabel54, gridBagConstraints);

        jLabel25.setText("Doctor's Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        jPanel17.add(jLabel25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(noofsickjTextField31, gridBagConstraints);

        doctorsTextField81.setEditable(false);
        doctorsTextField81.setBackground(new java.awt.Color(255, 255, 255));
        doctorsTextField81.setText(getUser());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel17.add(doctorsTextField81, gridBagConstraints);

        jButton15.setMnemonic('O');
        jButton15.setText("Save Data");
        jButton15.setToolTipText("Click here enter data");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jButton15, gridBagConstraints);

        jButton36.setMnemonic('l');
        jButton36.setText("Clear");
        jButton36.setToolTipText("Click here to clear textfields");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jButton36, gridBagConstraints);

        jButton46.setMnemonic('C');
        jButton46.setText("Close");
        jButton46.setToolTipText("Click here to close window");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jButton46, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        jPanel17.add(jSeparator15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jPanel17, gridBagConstraints);

        consultationTabbedPane.addTab("Sick Off/Leave", jPanel15);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jButton8.setText("BID");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel19.add(jButton8, gridBagConstraints);

        jPanel231.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "External Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 0, 255))); // NOI18N
        jPanel231.setLayout(new java.awt.GridBagLayout());

        jPanel811.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 0, 153))); // NOI18N
        jPanel811.setLayout(new java.awt.GridBagLayout());

        jLabel1133.setText("Consultants I/C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel811.add(jLabel1133, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel811.add(esnursejTextField1213, gridBagConstraints);

        jLabel342.setText("Es. Nurse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel811.add(jLabel342, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel811.add(consultanjTextField241, gridBagConstraints);

        jLabel241.setText("Transferring  Clinician");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel811.add(jLabel241, gridBagConstraints);

        transtechnicianjTextField181.setEditable(false);
        transtechnicianjTextField181.setBackground(new java.awt.Color(255, 255, 255));
        transtechnicianjTextField181.setText(getUser());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel811.add(transtechnicianjTextField181, gridBagConstraints);

        jScrollPane161.setViewportView(clinicalsummjTextPane14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel811.add(jScrollPane161, gridBagConstraints);

        jLabel122.setText("Clinical Summ.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel811.add(jLabel122, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        jPanel231.add(jPanel811, gridBagConstraints);

        jButton122.setMnemonic('O');
        jButton122.setText("Ok");
        jButton122.setToolTipText("Click here enter data");
        jButton122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton122ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jButton122, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(insttransjTextField1711, gridBagConstraints);

        jLabel9211.setText("Institution Transferred to");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel231.add(jLabel9211, gridBagConstraints);

        jLabel2311.setText("Reason for Transfer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jLabel2311, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(transreasonjTextField811, gridBagConstraints);

        jLabel11311.setText(" Diagnosis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jLabel11311, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel231.add(provisiodiagjTextField12111, gridBagConstraints);

        jButton541.setText("Report");
        jButton541.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton541ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel231.add(jButton541, gridBagConstraints);

        jPanel423.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel423.setLayout(new java.awt.GridBagLayout());

        otherspatnojTextField923.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel423.add(otherspatnojTextField923, gridBagConstraints);

        searchButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton23.setToolTipText("Search");
        searchButton23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton23.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton23.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton23ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel423.add(searchButton23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jPanel423, gridBagConstraints);

        othernamejTextField115.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(othernamejTextField115, gridBagConstraints);

        otherpaymentjTextField211.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel231.add(otherpaymentjTextField211, gridBagConstraints);

        othercategoryjTextField24.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel231.add(othercategoryjTextField24, gridBagConstraints);

        jLabel911.setText("Patient No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jLabel911, gridBagConstraints);

        jLabel123.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jLabel123, gridBagConstraints);

        jLabel311.setText("Payment Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel231.add(jLabel311, gridBagConstraints);

        jLabel34.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel231.add(jLabel34, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel19.add(jPanel231, gridBagConstraints);

        consultationTabbedPane.addTab("Patient Referrals", jPanel19);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        getContentPane().add(consultationTabbedPane, gridBagConstraints);

        jPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel31.setLayout(new java.awt.GridBagLayout());

        buttonGroup8.add(outpatientCheckBox);
        outpatientCheckBox.setForeground(new java.awt.Color(51, 51, 255));
        outpatientCheckBox.setMnemonic('o');
        outpatientCheckBox.setSelected(true);
        outpatientCheckBox.setText("Display List of OUT-Patients");
        outpatientCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outpatientCheckBoxItemStateChanged(evt);
            }
        });
        outpatientCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outpatientCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(outpatientCheckBox, gridBagConstraints);

        buttonGroup8.add(inpatientCheckBox);
        inpatientCheckBox.setForeground(new java.awt.Color(255, 0, 51));
        inpatientCheckBox.setMnemonic('i');
        inpatientCheckBox.setText("Display List of IN-Patients");
        inpatientCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inpatientCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(inpatientCheckBox, gridBagConstraints);

        jPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel32.setLayout(new java.awt.GridBagLayout());

        buttonGroup9.add(searchnocheckbx);
        searchnocheckbx.setSelected(true);
        searchnocheckbx.setText("Search By  No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel32.add(searchnocheckbx, gridBagConstraints);

        buttonGroup9.add(searchnamecheckbx);
        searchnamecheckbx.setText("Search By Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel32.add(searchnamecheckbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel31.add(jPanel32, gridBagConstraints);

        transdatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel31.add(transdatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jPanel31, gridBagConstraints);

        setBounds(0, 0, 1242, 570);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton541ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton541ActionPerformed
        com.afrisoftech.laboratory.RefferalExtPdf pol = new com.afrisoftech.laboratory.RefferalExtPdf();
        pol.RefferalExtPdf(connectDB, otherspatnojTextField923.getText(), otherspatnojTextField923.getText());   // Add your handling code here:
    }//GEN-LAST:event_jButton541ActionPerformed

    private void searchButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton23ActionPerformed
        searchButtonClicked2e();   // Add your handling code here:

        // Add your handling code here:
    }//GEN-LAST:event_searchButton23ActionPerformed
    private void searchButtonClicked2e() {

        System.out.println("Showing dialog");
        java.awt.Point point = null;

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        if (consultationTabbedPane.getSelectedIndex() == 5) {
            point = this.nameNoTxt.getLocationOnScreen();
        } else if (consultationTabbedPane.getSelectedIndex() == 6) {
            point = this.otherspatnojTextField923.getLocationOnScreen();
        }

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }
    private void jButton122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton122ActionPerformed
        if (this.othernamejTextField115.getText().equals("") || othernamejTextField115.getText().equals(null)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Name Missing", "Error Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {

            String user = null;
            try {
                connectDB.setAutoCommit(false);

                java.sql.Statement ps11 = connectDB.createStatement();
                java.sql.ResultSet rst11 = ps11.executeQuery("select current_user");
                while (rst11.next()) {
                    user = rst11.getObject(1).toString();
                }

                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_refferal_ext values(?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?,?)");

                pstmt2.setString(1, othernamejTextField115.getText());
                pstmt2.setString(2, otherspatnojTextField923.getText());
                pstmt2.setString(3, othercategoryjTextField24.getText());
                pstmt2.setString(4, otherpaymentjTextField211.getText());
                pstmt2.setObject(5, jTextField9111.getText());
                pstmt2.setObject(6, transtechnicianjTextField181.getText());
                pstmt2.setString(8, provisiodiagjTextField12111.getText());
                pstmt2.setString(7, transreasonjTextField811.getText());
                pstmt2.setString(9, insttransjTextField1711.getText());
                pstmt2.setString(10, clinicalsummjTextPane14.getText());
                pstmt2.setObject(11, consultanjTextField241.getText());
                pstmt2.setObject(12, esnursejTextField1213.getText());
                pstmt2.setString(13, user);
                pstmt2.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                pstmt2.setObject(15, Sex);
                pstmt2.setObject(16, transtechnicianjTextField181.getText());
                pstmt2.setObject(17, Age);

                pstmt2.executeUpdate();

                connectDB.commit();
                connectDB.setAutoCommit(true);
                javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                com.afrisoftech.laboratory.RefferalExtPdf pol = new com.afrisoftech.laboratory.RefferalExtPdf();
                pol.RefferalExtPdf(connectDB, jTextField922.getText(), jTextField922.getText());

            } catch (java.sql.SQLException sq) {

                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(sq.getMessage());
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
        }  // Add your handling code here:
    }//GEN-LAST:event_jButton122ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        javax.swing.JInternalFrame other = new com.afrisoftech.accounting.DeathNotificationIntfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);    // Add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        javax.swing.JInternalFrame other = new com.afrisoftech.hospital.TheatreBookingIntfr(connectDB, pConnDB);
        this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            other.setSelected(true);
        } catch (java.beans.PropertyVetoException pvt) {
        }
        other.setVisible(true);       // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5214ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5214ActionPerformed
        jSearchDialog214.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton5214ActionPerformed

    private void jTextField11314CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11314CaretUpdate
        /*if (this.jCheckBox22.isSelected()) {
        
         if (jTextField11314.getCaretPosition() < 1) {
        
         System.out.println("Nothing");
         } else {
         jSearchTable214.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct inv_no,patient_no,patient_name FROM pb_doctors_request WHERE inv_no ILIKE '%" + jTextField11314.getText() + "%' and revenue_code ilike '%pharm%' and patient_no='" + nameNoTxt.getText() + "'  order by inv_no"));
         //  select product as service_type,selling_price as price,gl_code FROM st_stock_prices WHERE product ILIKE '%"+jTextField11.getText()+"%' AND department ilike '"+jComboBox1.getSelectedItem()+"%' order by service_type
        
        
         jSearchScrollPane214.setViewportView(jSearchTable214);
        
        
         }
         } else {
         }
         */
        // Add your handling code here:
    }//GEN-LAST:event_jTextField11314CaretUpdate

    private void jSearchTable214MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable214MouseClicked
        nameNoTxt.setText(jSearchTable214.getValueAt(jSearchTable214.getSelectedRow(), 0).toString());
        jSearchDialog214.dispose();
        int i = 0;
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select service,amount,requisition_no,bed_no,time_due,dosage from pb_doctors_request where inv_no = '" + nameNoTxt.getText() + "'");
            while (rset1.next()) {
                pharmacyTable.setValueAt(rset1.getObject(1), i, 0);
                pharmacyTable.setValueAt(rset1.getObject(2), i, 2);
                pharmacyTable.setValueAt(rset1.getObject(3), i, 3);
                pharmacyTable.setValueAt(rset1.getObject(4), i, 4);
                pharmacyTable.setValueAt(rset1.getObject(5), i, 5);
                pharmacyTable.setValueAt(rset1.getObject(6), i, 6);
                i++;
            }

        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }  // Add your handling code here:
    }//GEN-LAST:event_jSearchTable214MouseClicked
    private void cmbox5ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cmbox6ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());
        //   try{
        String billNo = null;
        String transNo = null;
        String payMode = null;
        String patientAcc = null;
        String cardNo = null;
        String AccDesc = null;
        String Xray = null;
        String cardName = null;
        String isurer = null;
        String expDate = null;
        String staffNo = null;
        String glAcc = null;
        String user = null;
        String accDesc1 = null;
        String glAcc1 = null;
        String chbox = null;
        String units = null;
        String gacc = null;
        String item = null;
        int itemInt = 0;
        double price = 0.00;
        double qty = 0.00;
        String glCode = null;
        String actCode = null;
        double amount = 0.00;
        double sellPrice = 0.00;
        java.util.Date admDate = null;
        String visitid = "-";
        String Sex = null;
        String Gender = null;
        String rank = null;
        if (maleChkbx.isSelected()) {
            Gender = "Male";
        } else {
            Gender = "Female";
        }

        if (femaleChkbx.isSelected()) {
            Sex = this.femaleChkbx.getText();
        } else {
            Sex = this.maleChkbx.getText();
        }

        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt1x = connectDB.prepareStatement("delete from pb_doctors_request where inv_no = '" + nameNoTxt.getText() + "' and patient_no = '" + nameNoTxt.getText() + "'");

            pstmt1x.executeUpdate();
            connectDB.commit();
            connectDB.setAutoCommit(true);

        } catch (java.sql.SQLException sq) {

            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
        //        this.jTable11.getModel().setValueAt("",10,5);

        try {
            connectDB.setAutoCommit(false);
            //this.jTextField922.setSelectionColor(java.awt.Color.blue);
            //   this.jTextField922.setOpaque(true);
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.Statement stm121 = connectDB.createStatement();
            java.sql.Statement stm1211 = connectDB.createStatement();
            java.sql.Statement stm122 = connectDB.createStatement();
            java.sql.Statement stm122x = connectDB.createStatement();
            java.sql.Statement stm1 = connectDB.createStatement();

            java.sql.ResultSet rse12 = stm12.executeQuery("select comments,date from hp_patient_visit where patient_no ='" + jTextField922.getText() + "' ORDER BY date DESC LIMIT 1");

            while (rse12.next()) {
                rank = rse12.getString(1);
            }
            billNo = nameNoTxt.getText().toString();

            java.sql.Statement ps11 = connectDB.createStatement();
            java.sql.ResultSet rst11 = ps11.executeQuery("select current_user");
            while (rst11.next()) {

                user = rst11.getObject(1).toString();

            }

            java.sql.Statement stm121q = connectDB.createStatement();
            for (int i = 0; i < jTable13.getRowCount(); i++) {
                if (jTable13.getValueAt(i, 0) != null) {
                    java.sql.ResultSet rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + jTable13.getValueAt(i, 4).toString() + "'");

                    while (rse121.next()) {

                        glAcc = rse121.getObject(1).toString();

                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");

                        pstmt2.setString(1, nameNoTxt.getText());

                        if (patientNameTxt.getText().equals("")) {
                            javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        } else {

                            pstmt2.setString(2, patientNameTxt.getText());
                        }
                        // pstmt2.setString(2,jTextField1.getText());
                        pstmt2.setString(3, paymentModeTxt.getText());
                        if (jTextField8.getText().equals("")) {
                            javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        } else {

                            pstmt2.setString(4, jTextField8.getText());
                        }
                        //  pstmt2.setString(4,jTextField8.getText());
                        pstmt2.setString(5, jTable13.getValueAt(i, 0).toString());
                        pstmt2.setString(6, jTable13.getValueAt(i, 1).toString());
                        pstmt2.setString(7, jTable13.getValueAt(i, 3).toString());
                        pstmt2.setObject(8, jTable13.getValueAt(i, 4).toString());
                        pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.sql.Date.valueOf(datePicker12.getDate().toString())));
                        pstmt2.setString(10, billNo);
                        if (paymentModeTxt.getText().startsWith("Scheme")) {
                            pstmt2.setBoolean(12, true);
                        } else {
                            pstmt2.setBoolean(12, false);
                        }
                        pstmt2.setString(11, user);
                        pstmt2.setString(13, glAcc);
                        pstmt2.setString(14, billNo);
                        pstmt2.setString(15, Sex);
                        pstmt2.setBoolean(16, false);
                        pstmt2.setBoolean(17, false);
                        pstmt2.setString(18, Xray);
                        pstmt2.setString(19, datePicker12.getDate().toString());
                        pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                        pstmt2.setString(21, waitingclinicscmbx.getSelectedItem().toString());
                        pstmt2.setString(22, rank);
                        pstmt2.setString(23, ageTxt.getText().toString());
                        pstmt2.setString(24, clinicalExamineditorTxt.getText().toString());

                        pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                        pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                        pstmt2.setString(27, Gender);

                        pstmt2.executeUpdate();
                    }

                }
            }

            for (int i = 0; i < pharmacyTable.getRowCount(); i++) {
                if (pharmacyTable.getValueAt(i, 0) != null) {
                    this.pharmacyTable.setCellSelectionEnabled(false);

                    //this.jTextField922.setHighlighter().;
                    //this.jTextField922.setOpaque(true);
                    java.sql.ResultSet rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + jTextField10.getText().toString() + "'");

                    while (rse121.next()) {

                        glAcc = rse121.getObject(1).toString();

                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?, ?, ?, ?,?,?,?, ?, ?, ?,?,?)");
                        if (patientNameTxt.getText().equals("")) {
                            javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        } else {

                            pstmt2.setString(2, patientNameTxt.getText());
                        }
                        // pstmt2.setString(2,jTextField1.getText());
                        pstmt2.setString(3, paymentModeTxt.getText());
                        if (jTextField8.getText().equals("")) {
                            javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        } else {

                            pstmt2.setString(4, jTextField8.getText());
                        }

                        pstmt2.setString(1, nameNoTxt.getText());
                        //  pstmt2.setString(2,jTextField1.getText());
                        //  pstmt2.setString(3,paymentModetxt.getText());
                        // pstmt2.setString(4,jTextField8.getText());
                        pstmt2.setString(5, pharmacyTable.getValueAt(i, 0).toString());
                        pstmt2.setDouble(6, 1);
                        pstmt2.setString(7, pharmacyTable.getValueAt(i, 2).toString());
                        pstmt2.setString(8, jTextField10.getText());
                        pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.sql.Date.valueOf(datePicker12.getDate().toString())));
                        pstmt2.setString(10, billNo);
                        if (paymentModeTxt.getText().startsWith("Scheme")) {
                            pstmt2.setBoolean(12, true);
                        } else {

                            pstmt2.setBoolean(12, false);
                        }
                        pstmt2.setString(11, user);
                        pstmt2.setString(13, glAcc);
                        pstmt2.setString(14, billNo);
                        pstmt2.setString(15, pharmacyTable.getValueAt(i, 3).toString());

                        pstmt2.setBoolean(17, false);
                        pstmt2.setObject(18, pharmacyTable.getValueAt(i, 4).toString());
                        pstmt2.setString(19, pharmacyTable.getValueAt(i, 5).toString());
                        pstmt2.setBoolean(16, false);
                        pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));

                        pstmt2.setObject(21, pharmacyTable.getValueAt(i, 6).toString());
                        pstmt2.setString(22, rank);
                        pstmt2.setString(23, ageTxt.getText().toString());
                        pstmt2.setString(24, clinicalExamineditorTxt.getText().toString());

                        pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                        pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                        pstmt2.setString(27, Gender);

                        pstmt2.executeUpdate();

                    }
                }

            }
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful.Bill No. " + billNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            for (int k = 0; k < jTable13.getRowCount(); k++) {
                for (int r = 0; r < jTable13.getColumnCount(); r++) {
                    jTable13.setValueAt(null, k, r);
                }
            }
            for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
                for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                    pharmacyTable.setValueAt(null, k, r);
                }
            }
        } catch (java.sql.SQLException sq) {

            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }  // Add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jSearchTable212MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable212MouseClicked
        nameNoTxt.setText(jSearchTable213.getValueAt(jSearchTable213.getSelectedRow(), 0).toString());
        jSearchDialog213.dispose();
        int i = 0;
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select service,amount,requisition_no,bed_no,time_due,dosage from pb_doctors_request where inv_no = '" + nameNoTxt.getText() + "'");
            while (rset1.next()) {
                jTable13.setValueAt(rset1.getObject(1), i, 0);
                jTable13.setValueAt(rset1.getObject(2), i, 1);
                jTable13.setValueAt(rset1.getObject(3), i, 2);
                jTable13.setValueAt(rset1.getObject(4), i, 3);
                jTable13.setValueAt(rset1.getObject(5), i, 4);
                jTable13.setValueAt(rset1.getObject(6), i, 5);
                i++;
            }

        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        } // Add your handling code here:
    }//GEN-LAST:event_jSearchTable212MouseClicked

    private void jButton5212ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5212ActionPerformed
        jSearchDialog213.dispose();   // Add your handling code here:
    }//GEN-LAST:event_jButton5212ActionPerformed

    private void jTextField11313CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11313CaretUpdate
        if (this.pharmacyCheckBox.isSelected()) {

            if (jTextField11313.getCaretPosition() < 1) {

                System.out.println("Nothing");
            } else {
                jSearchTable213.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct inv_no,patient_no,patient_name FROM pb_doctors_request WHERE inv_no ILIKE '%" + jTextField11313.getText() + "%' and revenue_code ilike '%pharm%' and patient_no='" + nameNoTxt.getText() + "'  order by inv_no"));
                //  select product as service_type,selling_price as price,gl_code FROM st_stock_prices WHERE product ILIKE '%"+jTextField11.getText()+"%' AND department ilike '"+jComboBox1.getSelectedItem()+"%' order by service_type

                jSearchScrollPane212.setViewportView(jSearchTable213);

            }
        } else {
        } // Add your handling code here:
    }//GEN-LAST:event_jTextField11313CaretUpdate

    private void searchButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton12ActionPerformed
        searchButtonClicked213();
        jButton121.setEnabled(false);
        requestbtn.setEnabled(false);
        jButton22.setEnabled(true);
        jButton5.setEnabled(true);  // Add your handling code here:
    }//GEN-LAST:event_searchButton12ActionPerformed
    private void searchButtonClicked213() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.nameNoTxt.getLocationOnScreen();

        jSearchDialog214.setSize(400, 200);

        jSearchDialog214.setLocation(point);

        jSearchDialog214.setVisible(true);

    }
    private void searchButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton22ActionPerformed
        searchButtonClicked211s(); // Add your handling code here:
    }//GEN-LAST:event_searchButton22ActionPerformed
    private void searchButtonClicked211s() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField82.getLocationOnScreen();

        jSearchDialog211.setSize(400, 200);

        jSearchDialog211.setLocation(point);

        jSearchDialog211.setVisible(true);

    }
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        com.afrisoftech.laboratory.PatientcardPdf policy = new com.afrisoftech.laboratory.PatientcardPdf();

        policy.PatientcardPdf(connectDB, transdatePicker.getDate(), transdatePicker.getDate(), jTextField922.getText());// Add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
    /* private void cmbox1MouseClickedhh() {
    
     System.out.println("Showing dialog");
     java.awt.Point point = this.jTextField91111.getLocationOnScreen();
     jSearchDialog11.setSize(400, 200);
     jSearchDialog11.setLocation(point);
     jSearchDialog11.setVisible(true);
     }*/
    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        this.setVisible(false);    // Add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void labresultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labresultsButtonActionPerformed
        for (int k = 0; k < labresultsTable.getRowCount(); k++) {
            for (int r = 0; r < labresultsTable.getColumnCount(); r++) {
                labresultsTable.getModel().setValueAt(null, k, r);
            }
        }
        readCheckBox.setEnabled(true);
        readCheckBox.setSelected(false);
        String labNo = null;
        String patientNo = null;
        int p = 0;
        int q = 0;
        //  int r = 0;
        int patNo1 = 0;
        int Total1 = 0;
        int Total = 0;
        int labNo1 = 0;
        try {
            java.sql.Statement stmtTable113 = connectDB.createStatement();

            java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery(""
                    + "(select count(distinct lab_no) from hp_lab_results where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "'   and lab_no !='null' and lab_no is not null) ");
            while (rsetTable113.next()) {
                labNo1 = rsetTable113.getInt(1);
                System.out.println("Lab no is " + labNo1);
            }

            if (labNo1 > 0) {
                java.sql.Statement stmtTable112 = connectDB.createStatement();
                java.sql.ResultSet rsetTable112 = stmtTable112.executeQuery(""
                        + "(select distinct lab_no,patient_name from hp_lab_results where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "' and   lab_no is not null and lab_no !='null' ) ");
                while (rsetTable112.next()) {
                    labNo = rsetTable112.getString(1);
                    patientNo = rsetTable112.getString(2);
                    System.out.println("Lab no2 is " + patientNo);
                }

                stmtTable112.close();
                rsetTable112.close();

            } else {
            }

            labresultsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select distinct date, patient_no, patient_name, lab_no as procedure_request_no, typeof_test procedure_name, false as results_read from hp_lab_results "
                    + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "'    "
                    + "  order by 1 asc) "));
            stmtTable113.close();
            rsetTable113.close();

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }
        if (patNo1 > 5) {

            this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");
            this.jLabel19.setForeground(new java.awt.Color(255, 0, 51));
        } else {
            this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");

            this.jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        }

    }//GEN-LAST:event_labresultsButtonActionPerformed

    private void symptomsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_symptomsTableMouseClicked
        if (symptomsTable.getSelectedColumn() == 1) {

            symptomsTable.setValueAt(0, symptomsTable.getSelectedRow(), 2);
            symptomsTable.setValueAt("N/A", symptomsTable.getSelectedRow(), 3);

        }
        // Add your handling code here:
    }//GEN-LAST:event_symptomsTableMouseClicked

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        this.jEditorPane1.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        int rows2Delete = diagnosisTable.getSelectedRowCount();

        int[] selectedRows = diagnosisTable.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            //javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");
        } else {

            if (rows2Delete > 1) {

                for (int i = 0; i < selectedRows.length; i++) {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) diagnosisTable.getModel();

                    defTableModel.removeRow(selectedRows[i]);

                }

            } else {

                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) diagnosisTable.getModel();

                defTableModel.removeRow(diagnosisTable.getSelectedRow());
            }
        }


        /*   int rows2Delete3 = jTable3.getSelectedRowCount();
        
         int[] selectedRows3 = jTable3.getSelectedRows();
        
         if (rows2Delete3 < 1) {
         //java.awt.Toolkit.getDefaultToolkit().beep();
         //javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");
         } else {
        
         if (rows2Delete3 > 1) {
        
         for (int i = 0; i < selectedRows3.length; i++) {
        
        
        
         javax.swing.table.DefaultTableModel defTableModel3 = (javax.swing.table.DefaultTableModel) jTable3.getModel();
        
         defTableModel3.removeRow(selectedRows3[i]);
        
         }
        
        
        
         } else {
        
         javax.swing.table.DefaultTableModel defTableModel3 = (javax.swing.table.DefaultTableModel) jTable3.getModel();
        
         defTableModel3.removeRow(jTable3.getSelectedRow());
         }
         }*/
        jButton63ActionPerformed(evt);
        // Add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jSearchTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable11MouseClicked
        /* jTextField91111.setText(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 0).toString());
         jSearchDialog11.dispose();
         //jTable16.setValueAt(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(),0),jTable16.getSelectedRow(),0);
         jSearchDialog11.dispose();
        
         int CountNo = 1;
         for (int k = 0; k < jSearchTable11.getRowCount(); k++) {
         for (int r = 0; r < jSearchTable11.getColumnCount(); r++) {
         jSearchTable11.setValueAt(null, k, r);
         }
         }
         jTextField1111.setText("");*/
        //  jTable16.setValueAt(""+CountNo,jTable16.getSelectedRow(), 1);
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable11MouseClicked

    private void jButton91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton91ActionPerformed
        jSearchDialog11.dispose(); // Add your handling code here:
    }//GEN-LAST:event_jButton91ActionPerformed

    private void jTextField1111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1111CaretUpdate
        jSearchTable11.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT (upper(disease_name)) as name,code from hp_diseases where disease_name ILIKE '" + jTextField1111.getText() + "%' order by disease_name"));
        /*  searchRowSet2.execute("SELECT (upper(disease_name)) as name,code from hp_diseases where disease_name ILIKE '"+jTextField1111.getText()+"%' order by disease_name");
        
         //searchRowSet1.execute("SELECT patient_no, (upper(second_name||' '||first_name||' '||last_name)) as name, year_of_birth, residence from hp_patient_register where patient_no ILIKE '"+jTextField1111.getText()+"%' and last_visit > current_date - 5 order by second_name");
        
         jSearchTable2.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet2, new org.netbeans.lib.sql.models.TableModel.Column[] {
         // new org.netbeans.lib.sql.models.TableModel.Column("patient_no", "Patient no", false),
         new org.netbeans.lib.sql.models.TableModel.Column("name", "Name", false),
         new org.netbeans.lib.sql.models.TableModel.Column("code", "Code", false)
         //  new org.netbeans.lib.sql.models.TableModel.Column("residence", "Residence", false)
         }));
         */
        jSearchTable11.setShowHorizontalLines(false);
        jSearchScrollPane11.setViewportView(jSearchTable11);  // Add your handling code here:
    }//GEN-LAST:event_jTextField1111CaretUpdate

    private void consultationTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultationTabbedPaneMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (this.consultationTabbedPane.getSelectedIndex() == 0) {
            this.loadALLpatientsbtn.doClick();
        }

        if (femaleChkbx.isSelected()) {
            // jLabel131.setText("LMP");
            // datePicker2.setVisible(true);
            this.jPanel82.setVisible(true);
            this.jPanel82.setEnabled(true);
        } else if (femaleChkbx.isSelected()) {
            this.jPanel82.setVisible(false);
            //      jLabel131.setText("");
            //  datePicker2.setVisible(false);
            this.jPanel82.setEnabled(false);
        }
        if (this.consultationTabbedPane.getSelectedIndex() == 2) {
            System.out.println("Select"
                    + "  hp_signs_record.date,"
                    + "   concat(hp_signs_record.\"temp\",' Celsius') as Temp,"
                    + "  hp_signs_record.weight,"
                    + "  hp_signs_record.height,"
                    + "  hp_signs_record.bmi,"
                    + "  concat(hp_signs_record.diastolic,' mmhg') as Diastolic,"
                    + "   concat(hp_signs_record.systolic,' mmhg') as Systolic,"
                    + " concat( hp_signs_record.pulse,' b/min') as Pulse_Rate,"
                    + " concat( hp_signs_record.pulse_oximetry,' b/min') as Pulse_Oximetry,"
                    + "  concat(hp_signs_record.pulse_regularity,' b/min') as Pulse_Reqularity,"
                    + "   concat( hp_signs_record.resp,' b/min') as Resp,"
                    + "  hp_signs_record.rbs,"
                    + "  hp_signs_record.blood_group,"
                    + "  hp_signs_record.rhesus,"
                    + "  hp_signs_record.urinary,"
                    + "  hp_signs_record.type_of_pain,"
                    + "  hp_signs_record.pain_scale,"
                    + "  hp_signs_record.lung_sounds,"
                    + "  hp_signs_record.comments "
                    + "From"
                    + "  hp_signs_record   "
                    + "  where     hp_signs_record.patient_no='" + nameNoTxt.getText().trim() + "' "
                    + "   and hp_signs_record.date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1'  ");

            this.vitalsignstable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "Select"
                    + "  hp_signs_record.date,"
                    + "   concat(hp_signs_record.\"temp\",' Celsius') as Temp,"
                    + "  hp_signs_record.weight,"
                    + "  hp_signs_record.height,"
                    + "  hp_signs_record.bmi,"
                    + "  concat(hp_signs_record.diastolic,' mmhg') as Diastolic,"
                    + "   concat(hp_signs_record.systolic,' mmhg') as Systolic,"
                    + " concat( hp_signs_record.pulse,' b/min') as Pulse_Rate,"
                    + " concat( hp_signs_record.pulse_oximetry,' b/min') as Pulse_Oximetry,"
                    + "  concat(hp_signs_record.pulse_regularity,' b/min') as Pulse_Reqularity,"
                    + "   concat( hp_signs_record.resp,' b/min') as Resp,"
                    + "  hp_signs_record.rbs,"
                    + "  hp_signs_record.blood_group,"
                    + "  hp_signs_record.rhesus,"
                    + "  hp_signs_record.urinary,"
                    + "  hp_signs_record.type_of_pain,"
                    + "  hp_signs_record.pain_scale,"
                    + "  hp_signs_record.lung_sounds,"
                    + "  hp_signs_record.comments "
                    + "From"
                    + "  hp_signs_record   "
                    + "  where     hp_signs_record.patient_no='" + nameNoTxt.getText().trim() + "' "
                    + "   and hp_signs_record.date::date >= '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' order by 1 asc  "));

        } else if (this.consultationTabbedPane.getSelectedIndex() == 3) {
        } else {
            //    waitingPatientsThread.stop();
        }
        this.pharmacyRequestsPanel.setVisible(false);
        this.servicesOrderHeaderPanel.setVisible(false);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_consultationTabbedPaneMouseClicked

    private void labresultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labresultsTableMouseClicked

        labNo = labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3).toString();

        labResultsTabbenPane.setSelectedIndex(1);

        try {
            java.sql.PreparedStatement pstmtResults = connectDB.prepareStatement("SELECT patient_no, initcap(patient_name), upper(typeof_test), age::int, upper(gender), input_date, comments||' '||pathologist_comment as comments FROM hp_lab_results WHERE lab_no = ?");

            pstmtResults.setObject(1, labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3));

            java.sql.ResultSet rsetResults = pstmtResults.executeQuery();

            while (rsetResults.next()) {

                laboratoryResultsPatientNumberTxt.setText(rsetResults.getString(1));
                laboratoryResultsPatientNameTxt.setText(rsetResults.getString(2));
                laboratoryProcedureDescriptionTxt.setText(rsetResults.getString(3));
                laboratoryResultsTimeTxt.setText(rsetResults.getTimestamp(6).toString());
                ageGenderTxt.setText("Age : " + rsetResults.getString(4) + " Yrs and Gender : " + rsetResults.getString(5));
                laboratoryProcedureResultDetailsTxt.setText(rsetResults.getString(7));
            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();
            //            ex.printStackTrace();             //ex.printStackTrace();
        }

        laboratoryResultsDisplayTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT parameter, units, out_come, lower_limit, upper_limit, (CASE WHEN result::varchar ~ '^[0-9]*.?[0-9]*$' AND (result < lower_limit or result > upper_limit) THEN true  ELSE false END) AS exceptional_results from hp_lab_results WHERE lab_no = '" + labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3) + "'"));

        String receiptNo = null;
        //        if (jTable1.getSelectedColumn() == 2) {
        System.out.print("See receipt No3. : " + receiptNo);
        for (int i = 0; i < labresultsTable.getRowCount(); i++) {
            if (labresultsTable.getModel().getValueAt(i, 0) != null) {

                try {

                    java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE hp_lab_results SET doc_read = true where lab_no = '" + labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3) + "'");

                    pstmt46.executeUpdate();

                } catch (java.sql.SQLException sqlExec) {

                    sqlExec.printStackTrace();

                    javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

                }
            }
        }
        //    if (Boolean.valueOf(labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 4).toString())) {
        // receiptNo = labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3).toString();
        com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();

        policy.PatientLabResultsPdf(connectDB, labNo, labNo);

        //    }
        // Add your handling code here:
    }//GEN-LAST:event_labresultsTableMouseClicked
    private void searchButtonClicked211h() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField9111.getLocationOnScreen();

        jSearchDialog211.setSize(600, 200);

        jSearchDialog211.setLocation(point);

        jSearchDialog211.setVisible(true);

    }

    private void jSearchTable213MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable213MouseClicked
        //   jTextField92.setText(jSearchTable213.getValueAt(jSearchTable213.getSelectedRow(), 0).toString());
        //        jTextField3.setText(jSearchTable21.getValueAt(jSearchTable21.getSelectedRow(), 1).toString());
        /* for (int k = 0; k < jTable12.getRowCount(); k++ ) {
         for (int r = 0; r < jTable12.getColumnCount(); r++ ) {
         jTable12.getModel().setValueAt(null,k,r);
         }
         }
        
         this.jSearchDialog212.dispose();
         int i = 0;
        
        
         try {
         java.sql.Statement stmt = connectDB.createStatement();
        
        
         java.sql.Statement stmtTable1 = connectDB.createStatement();
        
         java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT parameter FROM pb_clinical where test = '"+this.jTextField92.getText()+"'");
        
         while (rsetTable1.next()) {
        
         System.out.println("Working at table row "+i);
         jTable12.setValueAt(rsetTable1.getObject(1), i, 0);
         // jTable2.setValueAt(rsetTable1.getObject(2), i, 1);
         //  jTable2.setValueAt(rsetTable1.getObject(3), i, 2);
         //jTable1.setValueAt(rsetTable1.getObject(4), i, 3);
        
        
         i++;
        
         //                i = i + i;
         //            jTable1.setValueAt(rsetTable1.getObject(1), i, 0);
         }
        
        
         } catch(java.sql.SQLException sqlExec) {
        
         javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
        
         }
         */// Add your handling code here:
    }//GEN-LAST:event_jSearchTable213MouseClicked

    private void jButton5213ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5213ActionPerformed
        this.jSearchDialog212.dispose();   // Add your handling code here:
    }//GEN-LAST:event_jButton5213ActionPerformed

    private void jTextField11312CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11312CaretUpdate
        jSearchTable213.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct initcap(test) as test from pb_clinical where test ILIKE '" + jTextField11312.getText() + "%'  order by test"));

        jSearchTable213.setShowHorizontalLines(false);
        jSearchScrollPane213.setViewportView(jSearchTable213);  // Add your handling code here:
    }//GEN-LAST:event_jTextField11312CaretUpdate

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        int rows2Delete = symptomsTable.getSelectedRowCount();

        int[] selectedRows = symptomsTable.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

        } else {

            if (rows2Delete > 1) {

                for (int i = 0; i < selectedRows.length; i++) {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) symptomsTable.getModel();

                    defTableModel.removeRow(selectedRows[i]);

                }

            } else {

                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) symptomsTable.getModel();

                defTableModel.removeRow(symptomsTable.getSelectedRow());
            }
        }  // Add your handling code here:
    }//GEN-LAST:event_jButton61ActionPerformed

    private void clerkingSavingbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clerkingSavingbtnActionPerformed
//
//        try {
//            connectDB.setAutoCommit(false);
//            // for (int i = 0; i < jTable1.getRowCount(); i++){
//            if (clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0) != null) {
//                if (this.outpatientCheckBox.isSelected() == true) {
//                    for (int i = 0; i < this.clerkingwaitingTable.getRowCount(); i++) {
//                        if (Boolean.valueOf(clerkingwaitingTable.getModel().getValueAt(i, 7).toString()) == java.lang.Boolean.TRUE) {
//                            java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE hp_patient_visit SET nature = 'Cons',doctor_name  = current_user ,cons_time =current_timestamp where patient_no = '" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 2).toString() + "' AND transaction_type ilike  'Regis%' and date = '" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString() + "'");
//                            pstmt46.executeUpdate();
//                            break;
//                        }
//                    }
//
//                }
//            }
//            connectDB.setAutoCommit(true);
//        } catch (java.sql.SQLException sq) {
//
//            try {
//                connectDB.rollback();
//            } catch (java.sql.SQLException sql) {
//                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
//            }
//            System.out.println(sq.getMessage());
//            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
//
//        }
//
//        java.util.Calendar calendar = java.util.Calendar.getInstance();
//
//        long dateNow = calendar.getTimeInMillis();
//
//        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);
//
//        System.out.println(datenowSql1.toString());
//
//        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);
//
//        java.sql.Savepoint registerSavePoint = null;
//
//        System.out.println(datenowSql.toString());
//        String user = null;
//        String cat = null;
//        float diagnosed = 0;
//        int incDays = 0;
//        String Sex = null;
//        String marital = null;
//        String plans = "-";
//        String diagnosis = "-";
//        String symptoms = "-";
//        if (clerkingwaitingTable.isEditing()) {
//            clerkingwaitingTable.getCellEditor().stopCellEditing();
//        }
//
//        if (diagnosisTable.isEditing()) {
//            diagnosisTable.getCellEditor().stopCellEditing();
//        }
//
//        if (jTable13.isEditing()) {
//            jTable13.getCellEditor().stopCellEditing();
//        }
//
//        /*        if (jTable3.isEditing()) {
//         jTable3.getCellEditor().stopCellEditing();
//         }*/
//        if (pharmacyTable.isEditing()) {
//            pharmacyTable.getCellEditor().stopCellEditing();
//        }
//        symptoms = this.complainsTextPane.getText() + " " + jEditorPane2.getText();//jTable3.getValueAt(0, 0) + " " + jTable3.getValueAt(1, 0) + " " + jTable3.getValueAt(2, 0) + " " + jTable3.getValueAt(3, 0);
//        diagnosis = diagnosisTable.getValueAt(0, 1) + " " + diagnosisTable.getValueAt(1, 1) + " ";
//        if (pharmacyCheckBox.isSelected()) {
//            plans = pharmacyTable.getValueAt(0, 0) + " " + pharmacyTable.getValueAt(1, 0) + " " + pharmacyTable.getValueAt(2, 0);
//        } else {
//            plans = jTable13.getValueAt(0, 0) + " " + jTable13.getValueAt(1, 0) + " " + jTable13.getValueAt(2, 0);
//
//        }
//
//        if (outpatientCheckBox.isSelected()) {
//            cat = "OP";
//        } else if (inpatientCheckBox.isSelected()) {
//            cat = "IP";
//        }
//        String Rst = null;
//        boolean seen = true;
//        if (clerkingwaitingTable.isEditing()) {
//            clerkingwaitingTable.getCellEditor().stopCellEditing();
//        }
//
//        if (diagnosisTable.isEditing()) {
//            diagnosisTable.getCellEditor().stopCellEditing();
//        }
//
//        if (jTable13.isEditing()) {
//            jTable13.getCellEditor().stopCellEditing();
//        }
//        if (pharmacyTable.isEditing()) {
//            pharmacyTable.getCellEditor().stopCellEditing();
//        }
//        requestbtn.doClick();
//        // requestbtnActionPerformed(evt);
//        if (clinicalExamineditor.getText().toCharArray().length > 0 && diagnosisTable.getValueAt(0, 1) == null) {
//            javax.swing.JOptionPane.showMessageDialog(this, "You must input diagnosis after you put clinical examination", "Warning Message!", javax.swing.JOptionPane.WARNING_MESSAGE);
//
//        } else {
//            if (provisionalDiagnosistxt.getText().toCharArray().length > 0 && diagnosisTable.getValueAt(0, 1) == null) {
//                javax.swing.JOptionPane.showMessageDialog(this, "You must input diagnosis from the list not just provisional diagnosis", "Warning Message!", javax.swing.JOptionPane.WARNING_MESSAGE);
//
//            } else {
//
//                try {
//                    connectDB.setAutoCommit(false);
//                    /* java.sql.Statement stm121bx = connectDB.createStatement();
//                     java.sql.ResultSet rset24b = stm121bx.executeQuery("SELECT waiting_patient FROM hp_patient_register WHERE patient_no = '" + jTextField922.getText() + "'");
//                     while (rset24b.next()) {
//                     seen = rset24b.getBoolean(1);
//                     }*/
//
//                    registerSavePoint = connectDB.setSavepoint("registration");
//                    java.sql.Statement stm121a = connectDB.createStatement();
//                    java.sql.Statement stm121b = connectDB.createStatement();
//                    java.sql.Statement stm121 = connectDB.createStatement();
//                    java.sql.ResultSet rse121 = stm121.executeQuery("select current_user");
//                    java.sql.PreparedStatement pstmt21 = null;
//                    java.sql.ResultSet rset24a = null;
//                    java.sql.ResultSet rset24bx = null;
//                    java.sql.Statement st = connectDB.createStatement();
//                    java.sql.PreparedStatement pstmt46 = null;
//                    java.sql.PreparedStatement pstmt46x = null;
//                    java.sql.PreparedStatement pstmtd = null;
//                    java.sql.ResultSet rs = null;
//                    java.sql.Statement sts = connectDB.createStatement();
//                    java.sql.ResultSet rss = null;
//                    java.sql.PreparedStatement pstmt = null;
//                    while (rse121.next()) // glAcc = rse121.getObject(1).toString();
//                    {
//                        user = rse121.getObject(1).toString();
//                    }
//
//                    pstmt21 = connectDB.prepareStatement("INSERT INTO hp_clinical_results VALUES(?,?,?,?,?,?,?, ?, ?, ?, ?, ?,?,?)");
//
//                    pstmt21.setString(1, jTextField922.getText());
//                    pstmt21.setString(2, jTextField11.getText());
//                    pstmt21.setString(3, clinicalExamineditor.getText());
//                    pstmt21.setString(4, "");
//                    pstmt21.setString(5, cat);
//                    // pstmt21.setString(6,jTable1.getValueAt(i,0).toString());
//                    pstmt21.setString(6, jTextField9111.getText());
//                    pstmt21.setDate(7, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate()));
//                    pstmt21.setString(8, Rst);
//
//                    pstmt21.setObject(9, symptoms);
//                    pstmt21.setTimestamp(10, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
//                    pstmt21.setString(11, user);
//                    pstmt21.setObject(12, diagnosis);
//                    pstmt21.setObject(13, plans);
//                    pstmt21.setString(14, "");
//
//                    pstmt21.executeUpdate();
//
//                    if (jCheckBox63.isSelected()) {
//                        Sex = this.jCheckBox63.getText();
//                    } else {
//                        Sex = this.jCheckBox52.getText();
//                    }
//
//                    rset24a = stm121a.executeQuery("SELECT gender FROM hp_patient_visit WHERE patient_no = '" + jTextField922.getText() + "' ORDER BY 1 DESC LIMIT 1");
//                    while (rset24a.next()) {
//                        marital = rset24a.getString(1);
//                    }
//                    for (int i = 0; i < diagnosisTable.getRowCount(); i++) {
//                        if (diagnosisTable.getModel().getValueAt(i, 1) != null) {
//                            String categ = "";
//                            rset24bx = stm121b.executeQuery("SELECT COUNT(main_service) FROM hp_patient_diagnosis WHERE patient_no = '" + jTextField922.getText() + "' AND main_service = '" + diagnosisTable.getValueAt(i, 0).toString() + "' AND date_recorded = '" + datePicker11.getDate() + "'");
//                            while (rset24bx.next()) {
//                                diagnosed = rset24bx.getFloat(1);
//                            }
//                            if (diagnosed > 0) {
//                            } else {
//
//                                rs = st.executeQuery("SELECT ('" + datePicker11.getDate() + "')::date-('" + datePicker11.getDate() + "')::date");
//                                while (rs.next()) {
//                                    incDays = rs.getInt(1) + 1;
//                                }
//
//                                rss = sts.executeQuery("SELECT drug_time_limit FROM hp_diseases WHERE code = '" + diagnosisTable.getValueAt(i, 0) + "'");
//                                while (rss.next()) {
//                                    categ = rss.getString(1);
//                                }
//
//                                pstmt = connectDB.prepareStatement("INSERT INTO hp_patient_diagnosis VALUES(?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?)");
//                                pstmt.setString(1, nameNoTxt.getText());
//                                pstmt.setString(2, jTextField12.getText());
//                                pstmt.setObject(3, diagnosisTable.getValueAt(i, 0).toString());
//                                pstmt.setObject(4, diagnosisTable.getValueAt(i, 1).toString());
//                                pstmt.setInt(5, 1);
//                                pstmt.setString(6, jTextField8.getText());
//                                pstmt.setDouble(7, 0.00);
//                                pstmt.setString(8, this.clinicalExamineditor.getText());
//                                pstmt.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate()));
//                                pstmt.setDate(10, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate()));
//                                pstmt.setString(11, "Discharge");
//                                pstmt.setString(12, "");
//                                pstmt.setString(13, categ);
//                                pstmt.setString(14, "");
//                                pstmt.setString(15, clinicComboBox.getSelectedItem().toString());
//                                pstmt.setString(16, this.clinicalExamineditor.getText());
//                                pstmt.setString(17, cat);
//                                pstmt.setDouble(18, java.lang.Double.valueOf(jTextField15.getText()));
//                                pstmt.setString(19, Sex);
//                                pstmt.setString(20, marital);
//                                pstmt.setDate(21, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate()));
//                                pstmt.setDate(22, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate()));
//                                pstmt.setTimestamp(23, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
//                                pstmt.setString(24, user);
//                                pstmt.setInt(25, incDays);
//                                pstmt.executeUpdate();
//                            }
//                        }
//                    }
//
//                    /* for (int i = 0; i < jTable3.getRowCount(); i++) {
//                     if (jTable3.getValueAt(i, 0) != null) {
//                     java.sql.Statement stx1 = connectDB.createStatement();
//                     java.sql.ResultSet rsx1 = stx1.executeQuery("SELECT COUNT(symptom) FROM hp_symptoms WHERE symptom ILIKE '" + jTable3.getValueAt(i, 0).toString() + "'");
//                     while (rsx1.next()) {
//                    
//                     if (rsx1.getInt(1) < 1) {
//                     java.sql.PreparedStatement pstmt2f = connectDB.prepareStatement("INSERT INTO hp_symptoms(symptom) VALUES (?)");
//                     pstmt2f.setString(1, jTable3.getValueAt(i, 0).toString());
//                    
//                     pstmt2f.executeUpdate();
//                     }
//                     }
//                     }
//                     }*/
//                    pstmt46 = connectDB.prepareStatement("UPDATE hp_patient_visit SET nature = 'Cons',doctor_name  = current_user ,cons_time =current_timestamp where patient_no = '" + jTextField922.getText() + "' AND transaction_type ilike  'Regis%' and nature = '1' ");
//                    pstmt46.executeUpdate();
//
//                    pstmt46x = connectDB.prepareStatement("UPDATE hp_patient_register SET waiting_patient = false where patient_no = '" + jTextField922.getText() + "'");
//                    pstmt46x.executeUpdate();
//
//                    if (referalCheckbox.isSelected()) {
//                        pstmtd = connectDB.prepareStatement("INSERT INTO hp_admission_request("
//                                + "patient_no, patient_name, doctor, ward_refered, date_requested,"
//                                + "comments,admitted)"
//                                + "VALUES(?, ?, ?, ?, ?, ?, ?)");
//
//                        pstmtd.setString(1, jTextField922.getText());
//                        pstmtd.setString(2, jTextField11.getText());
//                        pstmtd.setObject(3, jTextField9111.getText());
//                        pstmtd.setObject(4, jComboBox2.getSelectedItem());
//                        pstmtd.setTimestamp(5, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
//
//                        pstmtd.setString(6, this.jTextPane1.getText());
//                        pstmtd.setBoolean(7, false);
//                        pstmtd.executeUpdate();
//                    }
//                    /*for (int k = 0; k < jTable3.getRowCount(); k++) {
//                     for (int r = 0; r < jTable3.getColumnCount(); r++) {
//                     jTable3.setValueAt(null, k, r);
//                     }
//                     }*/
//                    provisionalDiagnosistxt.setText("");
//                    this.complainsTextPane.setText("");
//                    for (int k = 0; k < diagnosisTable.getRowCount(); k++) {
//                        for (int r = 0; r < diagnosisTable.getColumnCount(); r++) {
//                            diagnosisTable.setValueAt(null, k, r);
//                        }
//                    }
//                    connectDB.commit();
//                    connectDB.setAutoCommit(true);
//                    javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
//
////            this.jEditorPane113.setText("");
//                    this.clinicalExamineditor.setText("");
//                    //this.jEditorPane1111.setText("");
//                    //  jComboBox2.setSelectedItem(null);
//                    if (stm121a == null) {
//                    } else {
//                        stm121a.close();
//                        stm121a = null;
//                    }
//
//                    if (stm121b == null) {
//                        stm121b.close();
//                        stm121b = null;
//                    }
//
//                    if (stm121 == null) {
//                    } else {
//                        stm121.close();
//                        stm121 = null;
//                    }
//                    if (rse121 == null) {
//                    } else {
//                        rse121.close();
//                        rse121 = null;
//                    }
//                    if (pstmt21 == null) {
//                    } else {
//                        pstmt21.close();
//                        pstmt21 = null;
//                    }
//                    if (rset24a == null) {
//                    } else {
//                        rset24a.close();
//                        rset24a = null;
//                    }
//                    if (rset24bx == null) {
//                    } else {
//                        rset24bx.close();
//                    }
//                    if (st == null) {
//                    } else {
//                        st.close();
//                        st = null;
//                    }
//                    if (pstmt46 == null) {
//                    } else {
//                        pstmt46.close();
//                        pstmt46 = null;
//                    }
//                    if (pstmt46x == null) {
//                    } else {
//                        pstmt46x.close();
//                        pstmt46x = null;
//                    }
//                    if (pstmtd == null) {
//                    } else {
//                        pstmtd.close();
//                        pstmtd = null;
//                    }
//                    if (rs == null) {
//                    } else {
//                        rs.close();
//                        rs = null;
//                    }
//                    if (sts == null) {
//                    } else {
//                        sts.close();
//                        sts = null;
//                    }
//                    if (rss == null) {
//                    } else {
//                        rss.close();
//                        rss = null;
//                    }
//                    if (pstmt == null) {
//                    } else {
//                        pstmt.close();
//                        pstmt = null;
//                    }
//
//                } catch (java.sql.SQLException sq) {
//                    try {
//                        connectDB.rollback(registerSavePoint);
//                    } catch (java.sql.SQLException sql) {
//                        sql.printStackTrace();
//                        javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
//                    }
//                    System.out.println(sq.getMessage());
//                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
//
//                }
//            }
//        }// Add your handling code here:
    }//GEN-LAST:event_clerkingSavingbtnActionPerformed

    private void jButton81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton81ActionPerformed
        String receiptNo = null;
        receiptNo = this.jTextField922.getText().toString();

        com.afrisoftech.laboratory.PatientClinicalComprehPdf policy = new com.afrisoftech.laboratory.PatientClinicalComprehPdf();

        policy.PatientClinicalComprehPdf(connectDB, receiptNo);  // Add your handling code here:
    }//GEN-LAST:event_jButton81ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        String receiptNo = null;
        receiptNo = this.jTextField922.getText().toString();

        com.afrisoftech.laboratory.PatientClinicalPdf policy = new com.afrisoftech.laboratory.PatientClinicalPdf();

        policy.PatientClinicalPdf(connectDB, receiptNo);   // Add your handling code here:
    }//GEN-LAST:event_jButton71ActionPerformed
    private void searchButtonClicked2d() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        //        java.awt.Point point = this.jTextField361.getLocationOnScreen();
        jSearchDialog2.setSize(400, 200);

        // jSearchDialog2.setLocation(point);
        jSearchDialog2.setVisible(true);

    }
    private void outpatientCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outpatientCheckBoxActionPerformed

        clerkingTabbedPane.setSelectedIndex(0);
        // Add your handling code here:
    }//GEN-LAST:event_outpatientCheckBoxActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());

        String billNo = null;

        String transNo = null;
        String payMode = null;
        String patientAcc = null;
        String cardNo = null;
        String AccDesc = null;
        String scheme = null;
        String cardName = null;
        String isurer = null;
        String expDate = null;
        String staffNo = null;
        String glAcc = null;
        String user = null;
        String accDesc1 = null;
        String glAcc1 = null;

        String BillNo = null;
        // String BillNo = null;
        String code = null;
        //        String user = null;
        String prescNo = null;
        String glAcc2 = null;

        String Stock = null;
        String actCode = null;
        String accountCode = null;
        String userName = null;
        String transNo1 = null;
        String glType = null;
        String glCode = null;
        String units = null;
        String chbox = null;
        try {
            connectDB.setAutoCommit(false);

            java.sql.Statement ps11 = connectDB.createStatement();
            java.sql.ResultSet rst11 = ps11.executeQuery("select nextval('attendance_no'),current_user");
            while (rst11.next()) {
                rst11.getObject(1).toString();

                prescNo = rst11.getObject(1).toString();
                user = rst11.getObject(2).toString();
            }
            this.sheetjTextField101.setText(prescNo);

            //   double presc = java.lang.Double.parseDouble(jTextField8.getText());
            String Excemt = null;
            String IP = null;
            String Recom = null;
            if (outpatientCheckBox.isSelected()) {
                IP = "OP";
            } else {
                if (inpatientCheckBox.isSelected()) {
                    IP = "IP";
                }
            }
            if (attendjCheckBox53.isSelected()) {
                Excemt = this.attendjCheckBox53.getText();
            } else {
                if (schooljCheckBox62.isSelected()) {
                    Excemt = schooljCheckBox62.getText();
                } else {
                    if (heavymanCheckBox72.isSelected()) {
                        Excemt = heavymanCheckBox72.getText();
                    }
                }
                if (driveCheckBox82.isSelected()) {
                    Excemt = driveCheckBox82.getText();
                } else {
                    if (othersCheckBox91.isSelected()) {
                        Excemt = othersCheckBox91.getText();
                    }
                }
            }
            if (this.bedreRadioButton1.isSelected()) {
                Recom = bedreRadioButton1.getText();
            } else {
                if (convalesceRadioButton2.isSelected()) {
                    Recom = convalesceRadioButton2.getText();
                } else {
                    if (othersRadioButton4.isSelected()) {
                        Recom = othersRadioButton4.getText();
                    }
                }
                if (lightdutiesRadioButton3.isSelected()) {
                    Recom = lightdutiesRadioButton3.getText();
                }

            }

            java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO hp_sick_sheet VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)");

            pstmt2.setString(1, patnoTextField.getText());
            pstmt2.setString(2, namejTextField18.getText());

            pstmt2.setString(3, IP);
            pstmt2.setString(4, companyTextField23.getText());
            pstmt2.setString(5, categoryTextField19.getText());
            pstmt2.setString(7, Recom);
            pstmt2.setString(6, Excemt);
            pstmt2.setDouble(8, java.lang.Double.valueOf(noofsickjTextField31.getText()));
            pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(startdatePicker111.getDate()));
            pstmt2.setDate(10, com.afrisoftech.lib.SQLDateFormat.getSQLDate(resumptiondatePicker121.getDate()));

            pstmt2.setString(11, sheetjTextField101.getText());
            pstmt2.setString(14, user);
            pstmt2.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker14.getDate()));

            pstmt2.setString(12, prescNo);
            pstmt2.setObject(15, explanationTextField94.getText());
            pstmt2.setObject(16, doctorsTextField81.getText());

            pstmt2.setObject(17, accTextField114.getText());

            pstmt2.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Leave Grant Successful", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            com.afrisoftech.reports.SickSheetPdf policy = new com.afrisoftech.reports.SickSheetPdf();
            policy.SickSheetPdf(connectDB, sheetjTextField101.getText());

            // }
            namejTextField18.setText("");
            companyTextField23.setText("");
            // jTextField4.setText("");
            patnoTextField.setText("");
            // }else{
            //   javax.swing.JOptionPane.showMessageDialog(this,"Insert NOT Successful","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            // }
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }  // Add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void searchButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton4ActionPerformed
        searchButtonClicked2s();   // Add your handling code here:
    }//GEN-LAST:event_searchButton4ActionPerformed
    private void searchButtonClicked2s() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.patnoTextField.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }
    private void searchButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton3ActionPerformed
        searchButtonClicked2b();
        jButton121.setEnabled(true);
        requestbtn.setEnabled(true);
        jButton22.setEnabled(true);
        jButton5.setEnabled(false);// Add your handling code here:
    }//GEN-LAST:event_searchButton3ActionPerformed
    private void searchButtonClicked2b() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.otherspatnojTextField923.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }

    private void searchButtonClicked2a() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.nameNoTxt.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }
    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
//////        clinicalExamineditor.setText("");
        patnoTextField.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());

        patientNameTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        jTextField922.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());
        paymentjTextField20.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString());
        categoryTextField19.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());
        namejTextField18.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());

//        jTextField21.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString());
        //  jTextField2.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());
        otherspatnojTextField923.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());
        jTextField12.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        paymentModeTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString());
        jTextField22.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());

        // 4.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());
        othernamejTextField115.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        otherpaymentjTextField211.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString());
        othercategoryjTextField24.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());

        String Xrayed = null;
        try {
            int p = 0;

            java.sql.Statement st = connectDB.createStatement();
            java.sql.ResultSet rset = null;
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = null;
            java.sql.Statement stmt11 = connectDB.createStatement();
            java.sql.ResultSet rset11 = null;

            java.sql.PreparedStatement pstmt112f = null;
            java.sql.Statement stmt1sd = connectDB.createStatement();
            java.sql.ResultSet rset1sd = null;
            java.sql.Statement stf = connectDB.createStatement();
            java.sql.ResultSet rsf = null;
            java.sql.Statement stf2 = connectDB.createStatement();
            java.sql.ResultSet rsf2 = null;
            java.sql.Statement stf1 = connectDB.createStatement();
            java.sql.ResultSet rsf1 = null;

            java.sql.Statement stmt1s = connectDB.createStatement();
            java.sql.ResultSet rset1s = null;

            java.sql.Statement stmt1ss = connectDB.createStatement();
            java.sql.ResultSet rset1ss = null;

            boolean exempt = false;
            if (this.outpatientCheckBox.isSelected() == true) {

                rset1s = stmt1s.executeQuery("SELECT COUNT(patient_no) FROM ac_cash_collection WHERE patient_no ='" + jTextField922.getText() + "' "
                        + " AND date::date >= (select current_date -1) ");
                while (rset1s.next()) {
                    p = rset1s.getInt(1);
                }
                p = 2;
            } else if (this.inpatientCheckBox.isSelected() == true) {
                p = 2;
            }
            rset1ss = stmt1ss.executeQuery("SELECT exemption FROM pb_patient_categories WHERE patient_type ILIKE '" + jTextField22.getText() + "'");
            while (rset1ss.next()) {
                //rname = rse1211.getObject(1).toString();
                exempt = rset1ss.getBoolean(1);
            }

            {
                //Checking if the patient or relative has exceeded limit
                if (paymentModeTxt.getText().startsWith("Schem")) {
                    float balance = 0;
                    float bill = 0;
                    float limit = 0;
                    float pharm = 0;

                    rset1sd = stmt1sd.executeQuery("SELECT card_no FROM hp_patient_register WHERE patient_no = '" + jTextField922.getText() + "' and pay_mode ILIKE 'scheme'");
                    while (rset1sd.next()) {
                        jTextField9221 = rset1sd.getString(1);
                    }
                    rset1sd.close();
                    rsf1 = stf1.executeQuery("SELECT sum(limit_amount) FROM hp_schemestaff WHERE staff_no = '" + jTextField9221 + "'");
                    while (rsf1.next()) {
                        limit = rsf1.getFloat(1);
                    }
                    rsf1.close();
                    rsf = stf.executeQuery("SELECT sum(amount) FROM hp_patient_billing WHERE visit_id = '" + jTextField9221 + "' AND payment_mode ILIKE 'Scheme'");
                    while (rsf.next()) {
                        bill = rsf.getFloat(1);
                    }

                    rsf.close();

                    rsf2 = stf2.executeQuery("SELECT sum(amount) FROM hp_pharmacy WHERE visit_id = '" + jTextField9221 + "' AND pay_mode ILIKE 'Scheme'");
                    while (rsf2.next()) {
                        pharm = rsf2.getFloat(1);
                    }
                    rsf2.close();
                    System.out.println("select sex,ROUND(((select current_timestamp(0)::date) - year_of_birth::date)/365) AS age ,account_no,description,card_no from hp_patient_register where patient_no ='" + jTextField922.getText() + "' ");
                    rset1 = stmt1.executeQuery("select sex,ROUND(((select current_timestamp(0)::date) - year_of_birth::date)/365) AS age ,account_no,description,card_no from hp_patient_register where patient_no ='" + jTextField922.getText() + "' ");
                    while (rset1.next()) {
                        ageTxt.setText(dbObject.getDBObject(rset1.getObject(2), "-"));
                        memberjTextField41.setText(dbObject.getDBObject(rset1.getObject(3), "-"));
                        companyTextField23.setText(dbObject.getDBObject(rset1.getObject(4), "-"));
                        accTextField114.setText(dbObject.getDBObject(rset1.getObject(5), "-"));

                        Sex = dbObject.getDBObject(rset1.getObject(1), "-");
                        Age = dbObject.getDBObject(rset1.getObject(2), "-");

                    }

                    if (this.inpatientCheckBox.isSelected()) {

                        rset11 = stmt11.executeQuery("select account_no,description from hp_inpatient_register where patient_no ='" + jTextField922.getText() + "' ");
                        while (rset11.next()) {

                            accTextField114.setText(rset11.getObject(1).toString());
                            companyTextField23.setText(rset11.getObject(2).toString());
                        }
                        rset11.close();
                    }
                }
            }

            java.sql.Statement stmt11diag = connectDB.createStatement();
            java.sql.ResultSet rset11diag = stmt11diag.executeQuery(""
                    + "SELECT  main_service, disease,  doctor_surgeon "
                    + "  FROM hp_patient_diagnosis  "
                    + "  where patient_no='" + otherspatnojTextField923.getText() + "'   "
                    + "  order by date_recorded desc ,date_of_entry desc limit 1; ");
            while (rset11diag.next()) {
                provisiodiagjTextField12111.setText(rset11diag.getString(2));

            }

        } catch (Exception sqe) {

            System.out.println("\n\n\n\n\n\n\n\t\t\terror ya select is " + sqe);
            sqe.printStackTrace();
        }

        this.jSearchDialog2.dispose();
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked
    private void populateTable1g(java.lang.String patient_no) {

        int i = 0;
        int j = 0;
        try {
            // if (this.jCheckBox1.isSelected())
            {
                java.sql.Statement stmt = connectDB.createStatement();

                java.sql.ResultSet rset = stmt.executeQuery("SELECT count(main_service) FROM hp_patient_diagnosis WHERE patient_no = '" + patient_no + "' and date_recorded = (select current_timestamp(0)::date)");

                while (rset.next()) {
                    j = rset.getInt(1);
                }
                if (j > 0) {
                    java.sql.Statement stmtTable1 = connectDB.createStatement();

                    java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT main_service,disease FROM hp_patient_diagnosis WHERE patient_no = '" + patient_no + "' and date_recorded = (select current_timestamp(0)::date)");

                    while (rsetTable1.next()) {

                        System.out.println("Working at table row " + i);
                        diagnosisTable.setValueAt(rsetTable1.getObject(1), i, 0);
                        diagnosisTable.setValueAt(rsetTable1.getObject(2), i, 1);
                        i++;

                    }
                }
            }
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

    }
    private void jButton522ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton522ActionPerformed
        this.jSearchDialog2.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton522ActionPerformed

    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        if (this.outpatientCheckBox.isSelected() && this.searchnocheckbx.isSelected()) {

            if (jTextField113.getCaretPosition() < 3) {
                System.out.println("Nothing");
            } else {
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, (upper(second_name||' '||first_name)) as name, pay_mode as Mode, category as categ from hp_patient_register where patient_no ILIKE '%" + jTextField113.getText() + "%' "
                        + "  order by second_name"));

                //    searchRowSet2.execute("SELECT patient_no, (upper(second_name||' '||first_name||' '||last_name)) as name, year_of_birth, residence from hp_patient_register where patient_no ILIKE '%"+jTextField113.getText()+"%' and last_visit > current_date - 5 order by second_name");
                jSearchTable2.setShowHorizontalLines(false);
                jSearchScrollPane2.setViewportView(jSearchTable2);

            }

        } else {
            if (this.outpatientCheckBox.isSelected() && this.searchnamecheckbx.isSelected()) {
                if (jTextField113.getCaretPosition() < 3) {
                    System.out.println("Nothing");
                } else {
                    jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, (upper(second_name||' '||first_name)) as name, pay_mode as Mode, category as categ from hp_patient_register where second_name||' '||first_name ILIKE '%" + jTextField113.getText() + "%' order by second_name"));

                    jSearchTable2.setShowHorizontalLines(false);
                    jSearchScrollPane2.setViewportView(jSearchTable2);

                }
            }
        }
        // }else{
        if (this.inpatientCheckBox.isSelected() && this.searchnocheckbx.isSelected()) {

            // if(this.jCheckBox21.isSelected()){
            //   if( jTextField113.getCaretPosition() > 3){
            if (jTextField113.getCaretPosition() < 3) {
                System.out.println("Nothing");
            } else {

                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT  patient_no, patient_name AS name,pat_age, ward FROM hp_admission WHERE patient_no ILIKE '%" + jTextField113.getText() + "%' AND discharge = false ORDER BY 1"));

                jSearchTable2.setShowHorizontalLines(false);
                jSearchScrollPane2.setViewportView(jSearchTable2);

            }
        } else {
            if (this.inpatientCheckBox.isSelected() && this.searchnamecheckbx.isSelected()) {
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, patient_name AS name,pat_age, ward FROM hp_admission WHERE patient_name ILIKE '%" + jTextField113.getText() + "%' AND discharge = false ORDER BY 2"));

                jSearchTable2.setShowHorizontalLines(false);
                jSearchScrollPane2.setViewportView(jSearchTable2);

            }
        }    // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate
    private void searchButtonClicked2() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField922.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }
    private void searchButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton21ActionPerformed
        searchButtonClicked2111(); // Add your handling code here:
    }//GEN-LAST:event_searchButton21ActionPerformed
    private void searchButtonClicked2111() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField8.getLocationOnScreen();

        jSearchDialog211.setSize(400, 200);

        jSearchDialog211.setLocation(point);

        jSearchDialog211.setVisible(true);

    }
    private void jSearchTable211MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable211MouseClicked
        //jTextField9111setText(jSearchTable211.getValueAt(jSearchTable211.getSelectedRow(), 0).toString());
//
        jTextField9111.setText(jSearchTable211.getValueAt(jSearchTable211.getSelectedRow(), 0).toString());
        jTextField8.setText(jSearchTable211.getValueAt(jSearchTable211.getSelectedRow(), 0).toString());
        //  jTextField9221.setText(jSearchTable211.getValueAt(jSearchTable211.getSelectedRow(), 1).toString());
        jTextField82.setText(jSearchTable211.getValueAt(jSearchTable211.getSelectedRow(), 0).toString());

        this.jSearchDialog211.dispose();

        for (int k = 0; k < jSearchTable211.getRowCount(); k++) {
            for (int r = 0; r < jSearchTable211.getColumnCount(); r++) {
                jSearchTable211.setValueAt(null, k, r);
            }
        }
        jTextField11311.setText("");

        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable211MouseClicked

    private void jButton5211ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5211ActionPerformed
        this.jSearchDialog211.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton5211ActionPerformed

    private void jTextField11311CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11311CaretUpdate
        jSearchTable211.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select first_name||' '||middle_name as name,staff_no from pb_doctors_list where status ilike  'active' and  first_name||' '||middle_name ILIKE '%" + jTextField11311.getText() + "%'  order by name"));

        jSearchTable211.setShowHorizontalLines(false);
        jSearchScrollPane211.setViewportView(jSearchTable211);

        // Add your handling code here:
    }//GEN-LAST:event_jTextField11311CaretUpdate
    private void searchButtonClicked211() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.jTextField9111.getLocationOnScreen();

        jSearchDialog211.setSize(400, 200);

        jSearchDialog211.setLocation(point);

        jSearchDialog211.setVisible(true);

    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());
        String user = null;
        String cat = null;
        String sexHist = null;
        String illness = null;
        String alcohol = null;
        String contr = null;
        String smoking = null;
        String drug = null;
        String marital = null;

        if (outpatientCheckBox.isSelected()) {
            cat = "OP";
        } else {
            cat = "IP";
        }

        String Rst = null;

        try {
            connectDB.setAutoCommit(false);
            for (int i = 0; i < 6; i++) {
                if (symptomsTable.getModel().getValueAt(i, 1) != null || rxplanTable.getModel().getValueAt(i, 1) != null) {

                    java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement(""
                            + "INSERT INTO hp_patients_hist("
                            + "            patient_no, name, date, doctor,"
                            + " marital_status, sex_hist, contraceptive, "
                            + "            cont_method, illness,drug_allergy, illness_method,  medicine, "
                            + "            alcohol, smoking, hist_heading,narration, symptom, result, duration, description, "
                            + "            user_name, trans_date, clinical_duration)"
                            + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,?,  ?::numeric,  ?,"
                            + "current_user, current_timestamp, ?);");

                    pstmt21.setString(1, jTextField922.getText());
                    pstmt21.setString(2, patientNameTxt.getText());
                    pstmt21.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                    pstmt21.setString(4, jTextField9111.getText());
                    pstmt21.setObject(5, dbObject.getDBObject(rxplanTable.getValueAt(i, 0), ""));
                    pstmt21.setObject(6, dbObject.getDBObject(rxplanTable.getValueAt(i, 1), ""));
                    pstmt21.setObject(7, dbObject.getDBObject(rxplanTable.getValueAt(i, 2), ""));

                    pstmt21.setObject(8, dbObject.getDBObject(rxplanTable.getValueAt(i, 3), ""));
                    pstmt21.setObject(9, dbObject.getDBObject(rxplanTable.getValueAt(i, 4), ""));
                    pstmt21.setObject(10, dbObject.getDBObject(rxplanTable.getValueAt(i, 5), ""));
                    pstmt21.setObject(11, dbObject.getDBObject(rxplanTable.getValueAt(i, 6), ""));
                    pstmt21.setObject(12, drug);

                    // pstmt21.setObject(6,jTable1.getValueAt(i,0),""));
                    pstmt21.setObject(13, dbObject.getDBObject(rxplanTable.getValueAt(i, 7), ""));
                    pstmt21.setObject(14, dbObject.getDBObject(rxplanTable.getValueAt(i, 8), ""));

                    //                      pstmt21.setObject(9,jEditorPane1.getText());
                    pstmt21.setObject(15, dbObject.getDBObject(rxplanTable.getValueAt(i, 9), ""));
                    pstmt21.setObject(16, dbObject.getDBObject(rxplanTable.getValueAt(i, 10), ""));
                    pstmt21.setObject(17, symptomsTable.getValueAt(i, 0));
                    pstmt21.setObject(18, symptomsTable.getValueAt(i, 1));
                    pstmt21.setObject(19, "1");
                    pstmt21.setObject(20, symptomsTable.getValueAt(i, 3));
                    pstmt21.setObject(21, symptomsTable.getValueAt(i, 2));

                    pstmt21.executeUpdate();
                }
            }

            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            for (int k = 0; k < symptomsTable.getRowCount(); k++) {
                for (int r = 0; r < symptomsTable.getColumnCount(); r++) {
                    symptomsTable.getModel().setValueAt(null, k, r);
                }
            }
            this.jEditorPane1.setText("");

            //  jComboBox2.setSelectedItem(null);
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }   // Add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void searchButtonClicked212() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        //        java.awt.Point point = this.jTextField92.getLocationOnScreen();
        // jSearchDialog212.setSize(400,200);
        // jSearchDialog212.setLocation(point);
        // jSearchDialog212.setVisible(true);
    }
    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        this.setVisible(false);   // Add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        for (int k = 0; k < jTable13.getRowCount(); k++) {
            for (int r = 0; r < jTable13.getColumnCount(); r++) {
                jTable13.setValueAt(null, k, r);
            }
        }
        for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
            for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                pharmacyTable.setValueAt(null, k, r);
            }
        } // Add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        if (this.servicesOrderHeaderPanel.isVisible()) {
            int rows2Delete = jTable13.getSelectedRowCount();

            int[] selectedRows = jTable13.getSelectedRows();

            if (rows2Delete < 1) {

                java.awt.Toolkit.getDefaultToolkit().beep();

                javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

            } else {

                if (rows2Delete > 1) {

                    for (int i = 0; i < selectedRows.length; i++) {

                        javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) jTable13.getModel();

                        defTableModel.removeRow(selectedRows[i]);

                    }

                } else {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) jTable13.getModel();

                    defTableModel.removeRow(jTable13.getSelectedRow());
                }
            }

            tableModelTableChanged();
        } else {

            int rows2Delete = pharmacyTable.getSelectedRowCount();

            int[] selectedRows = pharmacyTable.getSelectedRows();

            if (rows2Delete < 1) {

                java.awt.Toolkit.getDefaultToolkit().beep();

                javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

            } else {

                if (rows2Delete > 1) {

                    for (int i = 0; i < selectedRows.length; i++) {

                        javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) pharmacyTable.getModel();

                        defTableModel.removeRow(selectedRows[i]);

                    }

                } else {

                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) pharmacyTable.getModel();

                    defTableModel.removeRow(pharmacyTable.getSelectedRow());
                }
            }

            tableModelTableChanged();

        }  // Add your handling code here:
    }//GEN-LAST:event_jButton63ActionPerformed
    public void tableModelTableChanged() {
        System.out.println("Calculating totals for table 11 and 2.");
        //        double resFloat = 0.00;
        double resFloat = 0.00;

        for (int i = 0; i < jTable13.getRowCount(); i++) {

            if (jTable13.getValueAt(i, 3) != null) {

                resFloat = resFloat + Double.parseDouble(jTable13.getValueAt(i, 3).toString());

                jTextField3.setText(java.lang.String.valueOf(resFloat));

            } else {
                resFloat = resFloat + Double.parseDouble(jTable13.getValueAt(i, 3).toString());

                jTextField3.setText(java.lang.String.valueOf(resFloat));

            }
        }
        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }

    public void tableModelTableChanged(javax.swing.event.TableModelEvent evt) {

        //        double resFloat = 0.00;
        double resFloat = 0.00;

        for (int i = 0; i < jTable13.getRowCount(); i++) {

            if (jTable13.getValueAt(i, 3) != null) {

                //   if (jTable11.getSelectedColumn() == 1) {
                resFloat = resFloat + Double.parseDouble(jTable13.getValueAt(i, 3).toString());

                //   }
                jTextField3.setText(java.lang.String.valueOf(resFloat));

            } else {
                resFloat = resFloat + Double.parseDouble(jTable13.getValueAt(i, 3).toString());

                //   }
                jTextField3.setText(java.lang.String.valueOf(resFloat));
            }

        }

        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        if (this.pharmacyCheckBox.isSelected()) {
            com.afrisoftech.reports.OutPatPrescPdf pol = new com.afrisoftech.reports.OutPatPrescPdf();
            pol.OutPatPrescPdf(connectDB, nameNoTxt.getText());
        } else {
            com.afrisoftech.reports.ProcedureSlipPdf pol = new com.afrisoftech.reports.ProcedureSlipPdf();
            pol.ProcedureSlipPdf(connectDB, nameNoTxt.getText());
        }   // Add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton121ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());

        String billNo = null;

        try {
            connectDB.setAutoCommit(false);
            requestbtnActionPerformed(evt);

            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            int i = 0;
            //RequestPdf policy = new RequestPdf();
            // policy.RequestPdf(connectDB, jTextField922.getText(),jTextField1.getText(),jTextField3.getText(),billNo,this.paymentModetxt.getText());
            if (this.pharmacyCheckBox.isSelected()) {
                com.afrisoftech.reports.OutPatPrescPdf pol = new com.afrisoftech.reports.OutPatPrescPdf();
                pol.OutPatPrescPdf(connectDB, nameNoTxt.getText());
            } else if (this.radiologyCheck.isSelected()) {
                com.afrisoftech.reports.XrayReqSlipPdf pol = new com.afrisoftech.reports.XrayReqSlipPdf();
                pol.XrayReqSlipPdf(connectDB, nameNoTxt.getText());

            } else {
                com.afrisoftech.reports.ProcedureSlipPdf pol = new com.afrisoftech.reports.ProcedureSlipPdf();
                pol.ProcedureSlipPdf(connectDB, nameNoTxt.getText());
            }

            //        ps11.close();
            // jLabel7.setForeground(java.awt.Color.blue);
            // jLabel7.setText("Insert successful");
            //  jComboBox2.setSelectedItem(null);
        } catch (java.sql.SQLException sq) {

            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }    // Add your handling code here:
    }//GEN-LAST:event_jButton121ActionPerformed

    private void pharmacySearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pharmacySearchTableMouseClicked
        float Qty = 0;
        int j = 0;
        
        try {
            
            java.sql.Statement pstmt = connectDB.createStatement();
            java.sql.Statement pstmt1 = connectDB.createStatement();
            java.sql.Statement pstmt1x = connectDB.createStatement();
            java.sql.ResultSet rs1 = pstmt1.executeQuery("select count(product) from stockprices sc where product_id = '" + pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 3)  + "' and department ilike '%Pharma%'");
            while (rs1.next()) {
                j = rs1.getInt(1);
            }
            if (j > 0) {
                java.sql.ResultSet rs = pstmt.executeQuery("select sum(qty) from stock_balance_qty where item_code = '" + pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 3)  + "'  AND dates::date <= '" + transdatePicker.getDate() + "'");

                while (rs.next()) {

                    Qty = rs.getFloat(1);
                    

                }
            } else {
                Qty = 0;
                //pharmacyTable.setValueAt("0.00", pharmacyTable.getSelectedRow(), 9);
            }
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }
        
        if(prescribeNeg || Qty > 0 ){
        
        pharmacyTable.setValueAt(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 0), pharmacyTable.getSelectedRow(), 0);
        pharmacyTable.setValueAt("1", pharmacyTable.getSelectedRow(), 1);
        pharmacyTable.setValueAt(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 1), pharmacyTable.getSelectedRow(), 2);
        pharmacyTable.setValueAt(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 2), pharmacyTable.getSelectedRow(), 7);
        pharmacyTable.setValueAt(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 3), pharmacyTable.getSelectedRow(), 8);
        pharmacyTable.setValueAt(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 4), pharmacyTable.getSelectedRow(), 10);
        pharmacyTable.setValueAt(Qty, pharmacyTable.getSelectedRow(), 9);

        jTextField10.setText(pharmacySearchTable.getValueAt(pharmacySearchTable.getSelectedRow(), 3).toString());

        pharmacySearchDialog.dispose();
        int i = pharmacyTable.getSelectedRow();
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select distinct admin_mode,genre,days,strength from st_stock_item where item_code = '" + pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 8) + "'");
            while (rset1.next()) {
                pharmacyTable.setValueAt(rset1.getObject(1), i, 3);
                pharmacyTable.setValueAt(rset1.getObject(2), i, 4);
                pharmacyTable.setValueAt(rset1.getObject(3), i, 5);
                pharmacyTable.setValueAt(rset1.getObject(4), i, 6);
            }

            

            
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }

        for (int k = 0; k < pharmacySearchTable.getRowCount(); k++) {
            for (int r = 0; r < pharmacySearchTable.getColumnCount(); r++) {
                pharmacySearchTable.setValueAt(null, k, r);
            }
        }

        pharmacySearchTxt.setText("");
    }else{
             javax.swing.JOptionPane.showMessageDialog(this, "The selected drug is out of stock. Current balance is "+Qty, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                          
        }
// Add your handling code here:
    }//GEN-LAST:event_pharmacySearchTableMouseClicked

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
        if (lablatoryCheck.isSelected() == true || radiologyCheck.isSelected() == true) {
            jTable13.setValueAt(true, jTable13.getSelectedRow(), 0);
            jTable13.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0), jTable13.getSelectedRow(), 1);
            jTable13.setValueAt(1, jTable13.getSelectedRow(), 2);
            jTable13.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1), jTable13.getSelectedRow(), 3);
            jTable13.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1), jTable13.getSelectedRow(), 4);
            jTable13.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 2), jTable13.getSelectedRow(), 5);

            double floatCol2 = java.lang.Double.parseDouble(jTable13.getValueAt(jTable13.getSelectedRow(), 2).toString());

            double floatCol3 = java.lang.Double.parseDouble(jTable13.getValueAt(jTable13.getSelectedRow(), 3).toString());

            double resVal = floatCol2 * floatCol3;

            jTable13.setValueAt(new java.lang.Float(resVal), jTable13.getSelectedRow(), 4);
            double resFloat = 0.00;

            for (int i = 0; i < jTable13.getRowCount(); i++) {

                if (jTable13.getValueAt(i, 0) == Boolean.TRUE) {//toString().compareToIgnoreCase(null) {

                    resFloat = resFloat + Double.parseDouble(jTable13.getValueAt(i, 4).toString());

                    jTextField3.setText(java.lang.String.valueOf(resFloat));

                }
            }
        } else {
            jTable14.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0), jTable14.getSelectedRow(), 0);
            jTable14.setValueAt(1, jTable14.getSelectedRow(), 1);
            jTable14.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1), jTable14.getSelectedRow(), 2);
            jTable14.setValueAt(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 2), jTable14.getSelectedRow(), 4);

            double floatCol2 = java.lang.Double.parseDouble(jTable14.getValueAt(jTable14.getSelectedRow(), 1).toString());

            double floatCol3 = java.lang.Double.parseDouble(jTable14.getValueAt(jTable14.getSelectedRow(), 2).toString());

            double resVal = floatCol2 * floatCol3;

            jTable14.setValueAt(new java.lang.Float(resVal), jTable14.getSelectedRow(), 3);
            double resFloat = 0.00;

            for (int i = 0; i < jTable14.getRowCount(); i++) {

                if (jTable14.getValueAt(i, 0) != null) {//toString().compareToIgnoreCase(null) {

                    resFloat = resFloat + Double.parseDouble(jTable14.getValueAt(i, 3).toString());

                    jTextField3.setText(java.lang.String.valueOf(resFloat));

                }
            }
        }
        // resFloat = resVal+resFloat + Double.parseDouble(jTable1.getValueAt( jTable1.getSelectedRow(), 3).toString());
        for (int k = 0; k < jSearchTable.getRowCount(); k++) {
            for (int r = 0; r < jSearchTable.getColumnCount(); r++) {
                jSearchTable.setValueAt(null, k, r);
            }
        }

        jSearchDialog.dispose();   // Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton92ActionPerformed
        jSearchDialog.dispose(); // Add your handling code here:
    }//GEN-LAST:event_jButton92ActionPerformed

    private void jTextField112CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField112CaretUpdate
        if (!paymentModeTxt.getText().equalsIgnoreCase("Scheme")) {

            if (this.outpatientCheckBox.isSelected()) {

                if (this.radiologyCheck.isSelected() == true || lablatoryCheck.isSelected() == true) {
//                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
//                            + "SELECT  clerking_requests.request,pb_operating_parameters.rate ,pb_operating_parameters.gl_account "
//                            + "FROM clerking_requests,pb_operating_parameters "
//                            + "where clerking_requests.request ilike '%" + jTextField112.getText() + "%' and pb_operating_parameters.service_type=clerking_requests.request order by 1 asc; "
//                            + "  "));
//
//                    jSearchScrollPane.setViewportView(jSearchTable);
                } else {
                    {

                        if (jTextField112.getCaretPosition() < 5) {

                            System.out.println("Nothing");
                        } else {
                            jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct service_type,rate as price,gl_account as gl_code FROM pb_operating_parameters WHERE  service_type ILIKE '%" + jTextField112.getText() + "%' AND main_service  NOT ILIKE 'ward%' order by service_type LIMIT 30"));

                            jSearchScrollPane.setViewportView(jSearchTable);

                        }
                    }

                }

            } else if (this.inpatientCheckBox.isSelected()) {

                if (this.radiologyCheck.isSelected()) {
//
//
//                if (jTextField112.getCaretPosition() < 5) {
//
//                    System.out.println("Nothing");
//                } else {
//                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity WHERE code ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND pb_activity.department ILIKE 'XRY' ORDER BY service_type LIMIT 30"));
//
//
//
//                    jSearchScrollPane.setViewportView(jSearchTable);
//
//
//                }
                } else if (this.lablatoryCheck.isSelected()) {
//                    if (jTextField112.getCaretPosition() < 5) {
//
//                        System.out.println("Nothing");
//                    } else {
//                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity" +
//" WHERE   service_type ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND  pb_operating_parameters.main_service ILIKE 'LAB%'  ORDER BY service_type "));
//
//
//                        jSearchScrollPane.setViewportView(jSearchTable);
//                        System.out.println("Cannot sort out");
//
//                    }
                } else if (this.generalCheckBox.isSelected()) {

                    if (jTextField112.getCaretPosition() < 5) {

                        System.out.println("Nothing");
                    } else {
                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,rate as price,gl_account as gl_code FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%' AND main_service  NOT ILIKE 'ward%'  order by service_type LIMIT 30"));

                        jSearchScrollPane.setViewportView(jSearchTable);

                    }
                }

            }
        } else {
            if (this.outpatientCheckBox.isSelected()) {

                if (this.radiologyCheck.isSelected()) {
//
//                if (jTextField112.getCaretPosition() < 5) {
//
//                    System.out.println("Nothing");
//                } else {
//                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity WHERE service_type ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND pb_activity.department ILIKE 'XRY' order by service_type LIMIT 30"));
//
//
//
//                    jSearchScrollPane.setViewportView(jSearchTable);
//
//
//                }
                } else if (this.lablatoryCheck.isSelected()) {
//                    if (jTextField112.getCaretPosition() < 5) {
//
//                        System.out.println("Nothing");
//                    } else {
//                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity WHERE  service_type ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND pb_activity.department ILIKE 'LAB'  order by service_type LIMIT 30"));
//
//
//                        jSearchScrollPane.setViewportView(jSearchTable);
//                        System.out.println("Cannot sort out");
//
//                    }
                } else if (this.generalCheckBox.isSelected()) {

                    if (jTextField112.getCaretPosition() < 5) {

                        System.out.println("Nothing");
                    } else {
                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters WHERE  service_type ILIKE '%" + jTextField112.getText() + "%' AND main_service  NOT ILIKE 'ward%' order by service_type LIMIT 30"));

                        jSearchScrollPane.setViewportView(jSearchTable);

                    }
                }

            } else if (this.inpatientCheckBox.isSelected()) {

                if (this.radiologyCheck.isSelected()) {
//
//                if (jTextField112.getCaretPosition() < 5) {
//
//                    System.out.println("Nothing");
//                } else {
//                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity WHERE code ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND pb_activity.department ILIKE 'XRY'  order by service_type LIMIT 30"));
//
//
//
//                    jSearchScrollPane.setViewportView(jSearchTable);
//
//
//                }
                } else if (this.lablatoryCheck.isSelected()) {
//                    if (jTextField112.getCaretPosition() < 5) {
//
//                        System.out.println("Nothing");
//                    } else {
//                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters,pb_activity WHERE code ILIKE '%" + jTextField112.getText() + "%' AND gl_account = pb_activity.code AND pb_activity.department ILIKE 'LAB'  order by service_type  LIMIT 30"));
//
//
//                        jSearchScrollPane.setViewportView(jSearchTable);
//                        System.out.println("Cannot sort out");
//
//                    }
                } else if (this.generalCheckBox.isSelected()) {

                    if (jTextField112.getCaretPosition() < 5) {

                        System.out.println("Nothing");
                    } else {
                        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select service_type,anaesthetist_rate as price,gl_account as gl_code FROM pb_operating_parameters WHERE service_type ILIKE '%" + jTextField112.getText() + "%' AND main_service  NOT ILIKE 'ward%'  order by service_type LIMIT 30"));

                        jSearchScrollPane.setViewportView(jSearchTable);

                    }
                }

            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_jTextField112CaretUpdate
    private void cmboxMouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = searchServicesMainPanel.getLocationOnScreen();
        jSearchDialog.setSize(600, 200);
        jSearchDialog.setLocation(point);
        jSearchDialog.setVisible(true);
    }
    private void requestbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestbtnActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());
        //   try{
        String billNo = null;
        String Xray = null;
        String glAcc = null;
        String user = null;
        int transNo = 0;
        String Sex = null;
        double price = 0.00;
        String Store = null;
        String units = null;
        String Gender = null;
        String rank = null;
        String productId = null;

        if (maleChkbx.isSelected()) {
            Gender = "Male";
        } else {
            Gender = "Female";
        }

        if (femaleChkbx.isSelected()) {
            Sex = this.femaleChkbx.getText();
        } else {
            Sex = this.maleChkbx.getText();
        }

        if (jTable13.isEditing()) {
            jTable13.getCellEditor().stopCellEditing();
        }

        if (jTable14.isEditing()) {
            jTable14.getCellEditor().stopCellEditing();
        }

        if (pharmacyTable.isEditing()) {
            pharmacyTable.getCellEditor().stopCellEditing();
        }
        //        this.jTable11.getModel().setValueAt("",10,5);

        try {
            connectDB.setAutoCommit(false);
            //this.jTextField922.setSelectionColor(java.awt.Color.blue);
            //   this.jTextField922.setOpaque(true);
            java.sql.Statement stm12 = connectDB.createStatement();
            java.sql.Statement stm121 = connectDB.createStatement();
            java.sql.Statement stm1211 = connectDB.createStatement();
            java.sql.Statement stm122 = connectDB.createStatement();
            java.sql.Statement stm122x = connectDB.createStatement();
            java.sql.Statement stm1 = connectDB.createStatement();
            java.sql.Statement ps = connectDB.createStatement();
            java.sql.ResultSet rse12 = null;
            java.sql.ResultSet rst = null;
            java.sql.Statement ps11 = connectDB.createStatement();
            java.sql.ResultSet rst11 = null;
            java.sql.Statement stm121q = connectDB.createStatement();
            java.sql.ResultSet rse121 = null;
            java.sql.PreparedStatement pstmt2 = null;
            java.sql.PreparedStatement pstmt2f = null;
            java.sql.Statement pstB = connectDB.createStatement();
            java.sql.ResultSet rsB = null;
            java.sql.PreparedStatement pstmt1 = null;
            java.sql.PreparedStatement pstmtdrug = null;

            rse12 = stm12.executeQuery("select comments,date from hp_patient_visit where patient_no ='" + jTextField922.getText() + "' ORDER BY date DESC LIMIT 1");

            while (rse12.next()) {
                rank = rse12.getString(1);
            }
            rst = ps.executeQuery("select nextval('transaction_no_seq')");

            while (rst.next()) {
                rst.getObject(1).toString();

                transNo = rst.getInt(1);
            }
            //java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category ='PR'");

            if (this.outpatientCheckBox.isSelected()) {

                rst11 = ps11.executeQuery("select 'O'||nextval('billing_no_seq'),current_user");
                while (rst11.next()) {
                    rst11.getObject(1).toString();

                    billNo = rst11.getObject(1).toString();
                    user = rst11.getObject(2).toString();
                    this.nameNoTxt.setText(billNo);
                }
            } else {

                rst11 = ps11.executeQuery("select 'I'||nextval('billing_no_seq'),current_user");
                while (rst11.next()) {
                    rst11.getObject(1).toString();

                    billNo = rst11.getObject(1).toString();
                    user = rst11.getObject(2).toString();
                    this.nameNoTxt.setText(billNo);
                }

            }

            ///nursing medication entries
            if (pharmacyCheckBox.isSelected() == Boolean.TRUE) {
                System.out.println("Inserting pharmacy items ..11.11");
                ///jtable111 operations
                for (int n = 0; n < pharmacyTable.getRowCount(); n++) {
                    System.out.println("Inserting pharmacy items ...11");
                    if (pharmacyTable.getValueAt(n, 0) != null) {
                        System.out.println("Inserting pharmacy items ...");
                        // this.pharmacyTable.setCellSelectionEnabled(false);

                        //this.jTextField922.setHighlighter().;
                        //this.jTextField922.setOpaque(true);
                        rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + pharmacyTable.getValueAt(n, 7).toString().trim() + "'");

                        while (rse121.next()) {

                            glAcc = rse121.getObject(1).toString();

                            pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?, ?, ?, ?,?,?,?,?,?,?,?,?)");
                            pstmt2.setString(1, nameNoTxt.getText());
                            if (patientNameTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {
                                pstmt2.setString(2, patientNameTxt.getText());
                            }
                            // pstmt2.setString(2,jTextField1.getText());
                            pstmt2.setString(3, paymentModeTxt.getText());
                            if (jTextField8.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(4, jTextField8.getText());
                            }
                            pstmt2.setString(5, pharmacyTable.getValueAt(n, 0).toString());
                            pstmt2.setDouble(6, 1);
                            pstmt2.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
                            pstmt2.setString(8, jTextField10.getText());
                            pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt2.setString(10, billNo);
                            pstmt2.setString(11, user);
                            if (paymentModeTxt.getText().startsWith("Scheme")) {
                                pstmt2.setBoolean(12, true);
                            } else {
                                pstmt2.setBoolean(12, false);
                            }
                            pstmt2.setString(13, glAcc);
                            pstmt2.setString(14, billNo);
                            pstmt2.setString(15, pharmacyTable.getValueAt(n, 3).toString());
                            pstmt2.setBoolean(16, false);
                            pstmt2.setBoolean(17, false);
                            pstmt2.setObject(18, pharmacyTable.getValueAt(n, 4).toString());
                            pstmt2.setString(19, pharmacyTable.getValueAt(n, 5).toString());
                            pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                            pstmt2.setObject(21, pharmacyTable.getValueAt(n, 6).toString());
                            pstmt2.setString(22, rank);
                            pstmt2.setString(23, ageTxt.getText().toString());
                            pstmt2.setString(24, clinicalExamineditorTxt.getText().toString());
                            pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                            pstmt2.setString(27, Gender);

                            pstmt2.execute();

                            rsB = pstB.executeQuery("SELECT transfer_price,units,department,product_id,  (SELECT strength FROM st_stock_item WHERE st_stock_item.item_code = st_stock_prices.product_id ORDER BY 1 DESC LIMIT 1) AS strength FROM st_stock_prices WHERE product_id = '" + pharmacyTable.getValueAt(n, 8) + "' and gl_code =  '" + pharmacyTable.getValueAt(n, 7) + "'");
                            String strength = "-";
                            while (rsB.next()) {
                                price = rsB.getDouble(1);
                                units = rsB.getString(2);
                                Store = rsB.getString(3);
                                productId = rsB.getString(4);
                                strength = rsB.getString(5);

                            }
                            pstmt1 = connectDB.prepareStatement("INSERT INTO hp_pharmacy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt1.setString(1, nameNoTxt.getText());
                            pstmt1.setString(2, patientNameTxt.getText());
                            pstmt1.setDouble(4, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
                            pstmt1.setDouble(3, 1);
                            pstmt1.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
                            if (paymentModeTxt.getText().startsWith("Scheme")) {
                                pstmt1.setBoolean(6, true);
                            } else {
                                pstmt1.setBoolean(6, false);
                            }

                            pstmt1.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
                            pstmt1.setObject(8, pharmacyTable.getValueAt(n, 7).toString());
                            pstmt1.setString(9, "" + transNo);
                            pstmt1.setObject(10, jTextField8.getText());
                            pstmt1.setBoolean(11, false);
                            pstmt1.setString(12, units);
                            pstmt1.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt1.setObject(14, Store);
                            pstmt1.setString(15, "");
                            pstmt1.setString(16, paymentModeTxt.getText());
                            pstmt1.setObject(17, pharmacyTable.getValueAt(n, 8).toString());
                            pstmt1.setDouble(18, 0.00);
                            pstmt1.setString(19, user);
                            pstmt1.setObject(20, jTextField9111.getText());
                            pstmt1.setObject(21, "");
                            pstmt1.setString(22, productId);
                            pstmt1.setObject(23, strength);
                            pstmt1.execute();
                        }
                        pstmtdrug = connectDB.prepareStatement("INSERT INTO nursing.medication_administration"
                                + "(patient_no, visit_id, server_date, date_prescribed,"
                                + " drug_code,drug, dosage, route,"
                                + " frequency, no_of_days, doctor, receive,administer) "
                                + "VALUES "
                                + "(?, ?, localtimestamp, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)");
                        pstmtdrug.setObject(1, nameNoTxt);
                        if (inpatientCheckBox.isSelected() == Boolean.TRUE) {
                            pstmtdrug.setObject(2, visitID);
                        } else if (outpatientCheckBox.isSelected() == Boolean.TRUE) {
                            pstmtdrug.setObject(2, "");
                        }
                        pstmtdrug.setObject(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                        pstmtdrug.setObject(4, pharmacyTable.getValueAt(n, 8).toString());
                        pstmtdrug.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
                        pstmtdrug.setObject(6, pharmacyTable.getValueAt(n, 6).toString());
                        pstmtdrug.setObject(7, pharmacyTable.getValueAt(n, 3).toString());
                        pstmtdrug.setObject(8, pharmacyTable.getValueAt(n, 4).toString());
                        pstmtdrug.setObject(9, pharmacyTable.getValueAt(n, 5).toString());
                        pstmtdrug.setObject(10, jTextField9111.getText());
                        pstmtdrug.setBoolean(11, false);
                        pstmtdrug.setBoolean(12, false);
                        pstmtdrug.executeUpdate();

                    }
                    ///nursing medication entries
                }

            }
            for (int i = 0; i < jTable13.getRowCount(); i++) {
                if (jTable13.getValueAt(i, 0) != null) {
                    rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + jTable13.getValueAt(i, 4).toString() + "'");

                    while (rse121.next()) {
                        if (lablatoryCheck.isSelected() == false && radiologyCheck.isSelected() == false) {
                            System.out.println("am executing this now lab check and radio check false");
                            glAcc = rse121.getObject(1).toString();

                            pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");

                            pstmt2.setString(1, nameNoTxt.getText());

                            if (patientNameTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(2, patientNameTxt.getText());
                            }
                            // pstmt2.setString(2,jTextField1.getText());
                            pstmt2.setString(3, paymentModeTxt.getText());
                            if (jTextField8.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(4, jTextField8.getText());
                            }
                            //  pstmt2.setString(4,jTextField8.getText());
                            pstmt2.setString(5, jTable13.getValueAt(i, 0).toString());
                            pstmt2.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 1).toString()));
                            pstmt2.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 3).toString()));
                            pstmt2.setObject(8, jTable13.getValueAt(i, 4).toString());
                            pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt2.setString(10, billNo);
                            if (paymentModeTxt.getText().startsWith("Scheme")) {
                                pstmt2.setBoolean(12, true);
                            } else {

                                pstmt2.setBoolean(12, false);
                            }
                            pstmt2.setString(11, user);
                            pstmt2.setString(13, glAcc);
                            pstmt2.setString(14, billNo);
                            pstmt2.setString(15, Sex);
                            pstmt2.setBoolean(16, false);
                            pstmt2.setBoolean(17, false);
                            pstmt2.setString(18, Xray);
                            pstmt2.setString(19, datePicker12.getDate().toString());
                            pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                            pstmt2.setString(21, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(22, rank);
                            pstmt2.setString(23, ageTxt.getText().toString());
                            pstmt2.setString(24, clinicalExamineditorTxt.getText().toString());
                            pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                            pstmt2.setString(27, Gender);
                            pstmt2.executeUpdate();

                            if (jTable13.getValueAt(i, 0).toString().startsWith("plaster") || jTable13.getValueAt(i, 0).toString().startsWith("POP") || jTable13.getValueAt(i, 0).toString().startsWith("p.o.p")) {
                            } else {

                                pstmt2f = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?)");

                                pstmt2f.setString(1, nameNoTxt.getText());
                                pstmt2f.setString(2, patientNameTxt.getText());
                                pstmt2f.setString(3, paymentModeTxt.getText());
                                pstmt2f.setString(4, jTextField22.getText());
                                pstmt2f.setString(5, jTable13.getValueAt(i, 0).toString());
                                pstmt2f.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 1).toString()));
                                pstmt2f.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 3).toString()));
                                pstmt2f.setObject(8, jTable13.getValueAt(i, 4).toString());
                                pstmt2f.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",datePicker1.getDate())));
                                pstmt2f.setString(10, billNo);
                                pstmt2f.setString(11, user);
                                if (paymentModeTxt.getText().startsWith("Scheme")) {
                                    pstmt2f.setBoolean(12, true);
                                } else {
                                    pstmt2f.setBoolean(12, false);
                                }
                                pstmt2f.setString(13, glAcc);
                                pstmt2f.setString(14, jTextField9111.getText());
                                pstmt2f.setString(15, billNo);
                                pstmt2f.setBoolean(16, false);
                                pstmt2f.executeUpdate();
                            }
                        } else if (lablatoryCheck.isSelected() == true || radiologyCheck.isSelected() == true) {
                            if (jTable13.getValueAt(i, 0) == Boolean.TRUE) {
                                System.out.println("am executing this now lab check and radio check true");
                                glAcc = rse121.getObject(1).toString();

                                pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");

                                pstmt2.setString(1, nameNoTxt.getText());

                                if (patientNameTxt.getText().equals("")) {
                                    javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                                } else {

                                    pstmt2.setString(2, patientNameTxt.getText());
                                }
                                // pstmt2.setString(2,jTextField1.getText());
                                pstmt2.setString(3, paymentModeTxt.getText());
                                if (jTextField8.getText().equals("")) {
                                    javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                                } else {

                                    pstmt2.setString(4, jTextField8.getText());
                                }
                                //  pstmt2.setString(4,jTextField8.getText());
                                pstmt2.setString(5, jTable13.getValueAt(i, 1).toString());
                                pstmt2.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 2).toString()));
                                pstmt2.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                                pstmt2.setObject(8, jTable13.getValueAt(i, 5).toString());
                                pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                                pstmt2.setString(10, billNo);
                                if (paymentModeTxt.getText().startsWith("Scheme")) {
                                    pstmt2.setBoolean(12, true);
                                } else {

                                    pstmt2.setBoolean(12, false);
                                }
                                pstmt2.setString(11, user);
                                pstmt2.setString(13, glAcc);
                                pstmt2.setString(14, billNo);
                                pstmt2.setString(15, Sex);
                                pstmt2.setBoolean(16, false);
                                pstmt2.setBoolean(17, false);
                                pstmt2.setString(18, Xray);
                                pstmt2.setString(19, datePicker12.getDate().toString());
                                pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                pstmt2.setString(21, waitingclinicscmbx.getSelectedItem().toString());
                                pstmt2.setString(22, rank);
                                pstmt2.setString(23, ageTxt.getText().toString());
                                pstmt2.setString(24, clinicalExamineditorTxt.getText().toString());
                                pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                                pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                                pstmt2.setString(27, Gender);
                                pstmt2.executeUpdate();

                                if (jTable13.getValueAt(i, 1).toString().startsWith("plaster") || jTable13.getValueAt(i, 1).toString().startsWith("POP") || jTable13.getValueAt(i, 1).toString().startsWith("p.o.p")) {
                                } else {

                                    pstmt2f = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?)");

                                    pstmt2f.setString(1, nameNoTxt.getText());
                                    pstmt2f.setString(2, patientNameTxt.getText());
                                    pstmt2f.setString(3, paymentModeTxt.getText());
                                    pstmt2f.setString(4, jTextField22.getText());
                                    pstmt2f.setString(5, jTable13.getValueAt(i, 1).toString());
                                    pstmt2f.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 2).toString()));
                                    pstmt2f.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                                    pstmt2f.setObject(8, jTable13.getValueAt(i, 5).toString());
                                    pstmt2f.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",datePicker1.getDate())));
                                    pstmt2f.setString(10, billNo);
                                    pstmt2f.setString(11, user);
                                    if (paymentModeTxt.getText().startsWith("Scheme")) {
                                        pstmt2f.setBoolean(12, true);
                                    } else {
                                        pstmt2f.setBoolean(12, false);
                                    }
                                    pstmt2f.setString(13, glAcc);
                                    pstmt2f.setString(14, jTextField9111.getText());
                                    pstmt2f.setString(15, billNo);
                                    pstmt2f.setBoolean(16, false);
                                    pstmt2f.executeUpdate();
                                }
                            }
                        }
                        //end

                    }
                }
            }
//            System.out.println("Inserting pharmacy items ..11.11");
//            ///jtable111 operations
//            for (int n = 0; n < pharmacyTable.getRowCount(); n++) {
//                System.out.println("Inserting pharmacy items ...11");
//                if (pharmacyTable.getValueAt(n, 0) != null) {
//                    System.out.println("Inserting pharmacy items ...");
//                    // this.pharmacyTable.setCellSelectionEnabled(false);
//
//                    //this.jTextField922.setHighlighter().;
//                    //this.jTextField922.setOpaque(true);
//                    rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + pharmacyTable.getValueAt(n, 7).toString().trim() + "'");
//
//                    while (rse121.next()) {
//
//                        glAcc = rse121.getObject(1).toString();
//
//                        pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?, ?, ?, ?,?,?,?,?,?,?,?,?)");
//                        pstmt2.setString(1, nameNoTxt.getText());
//                        if (patientNametxt.getText().equals("")) {
//                            javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
//                        } else {
//                            pstmt2.setString(2, patientNametxt.getText());
//                        }
//                        // pstmt2.setString(2,jTextField1.getText());
//                        pstmt2.setString(3, paymentModetxt.getText());
//                        if (jTextField8.getText().equals("")) {
//                            javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
//                        } else {
//
//                            pstmt2.setString(4, jTextField8.getText());
//                        }
//                        pstmt2.setString(5, pharmacyTable.getValueAt(n, 0).toString());
//                        pstmt2.setDouble(6, 1);
//                        pstmt2.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
//                        pstmt2.setString(8, jTextField10.getText());
//                        pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
//                        pstmt2.setString(10, billNo);
//                        pstmt2.setString(11, user);
//                        if (paymentModetxt.getText().startsWith("Scheme")) {
//                            pstmt2.setBoolean(12, true);
//                        } else {
//                            pstmt2.setBoolean(12, false);
//                        }
//                        pstmt2.setString(13, glAcc);
//                        pstmt2.setString(14, billNo);
//                        pstmt2.setString(15, pharmacyTable.getValueAt(n, 3).toString());
//                        pstmt2.setBoolean(16, false);
//                        pstmt2.setBoolean(17, false);
//                        pstmt2.setObject(18, pharmacyTable.getValueAt(n, 4).toString());
//                        pstmt2.setString(19, pharmacyTable.getValueAt(n, 5).toString());
//                        pstmt2.setTimestamp(20, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
//                        pstmt2.setObject(21, pharmacyTable.getValueAt(n, 6).toString());
//                        pstmt2.setString(22, rank);
//                        pstmt2.setString(23, jTextField15.getText().toString());
//                        pstmt2.setString(24, clinicalExamineditor.getText().toString());
//                        pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
//                        pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
//                        pstmt2.setString(27, Gender);
//
//                        pstmt2.execute();
//
//                        rsB = pstB.executeQuery("SELECT transfer_price,units,department,product_id FROM st_stock_prices WHERE product_id = '" + pharmacyTable.getValueAt(n, 8) + "' and gl_code =  '" + pharmacyTable.getValueAt(n, 7) + "'");
//
//                        while (rsB.next()) {
//                            price = rsB.getDouble(1);
//                            units = rsB.getString(2);
//                            Store = rsB.getString(3);
//                            productId = rsB.getString(4);
//
//                        }
//                        pstmt1 = connectDB.prepareStatement("INSERT INTO hp_pharmacy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//                        pstmt1.setString(1, nameNoTxt.getText());
//                        pstmt1.setString(2, patientNametxt.getText());
//                        pstmt1.setDouble(4, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
//                        pstmt1.setDouble(3, 1);
//                        pstmt1.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
//                        if (paymentModetxt.getText().startsWith("Scheme")) {
//                            pstmt1.setBoolean(6, true);
//                        } else {
//                            pstmt1.setBoolean(6, false);
//                        }
//
//                        pstmt1.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
//                        pstmt1.setObject(8, pharmacyTable.getValueAt(n, 7).toString());
//                        pstmt1.setString(9, "" + transNo);
//                        pstmt1.setObject(10, jTextField8.getText());
//                        pstmt1.setBoolean(11, false);
//                        pstmt1.setString(12, units);
//                        pstmt1.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
//                        pstmt1.setObject(14, Store);
//                        pstmt1.setString(15, "");
//                        pstmt1.setString(16, paymentModetxt.getText());
//                        pstmt1.setObject(17, pharmacyTable.getValueAt(n, 8).toString());
//                        pstmt1.setDouble(18, 0.00);
//                        pstmt1.setString(19, user);
//                        pstmt1.setObject(20, jTextField9111.getText());
//                        pstmt1.setObject(21, "");
//                        pstmt1.setString(22, productId);
//                        pstmt1.setObject(23, "");
//                        pstmt1.execute();
//                    }
//
//                    ///nursing medication entries
//                    if (pharmacyCheckBox.isSelected() == Boolean.TRUE) {
//                        pstmtdrug = connectDB.prepareStatement("INSERT INTO nursing.medication_administration"
//                                + "(patient_no, visit_id, server_date, date_prescribed,"
//                                + " drug_code,drug, dosage, route,"
//                                + " frequency, no_of_days, doctor, receive,administer) "
//                                + "VALUES "
//                                + "(?, ?, localtimestamp, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)");
//                        pstmtdrug.setObject(1, nameNoTxt);
//                        if (inpatientCheckBox.isSelected() == Boolean.TRUE) {
//                            pstmtdrug.setObject(2, visitID);
//                        } else if (outpatientCheckBox.isSelected() == Boolean.TRUE) {
//                            pstmtdrug.setObject(2, "");
//                        }
//                        pstmtdrug.setObject(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
//                        pstmtdrug.setObject(4, pharmacyTable.getValueAt(n, 8).toString());
//                        pstmtdrug.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
//                        pstmtdrug.setObject(6, pharmacyTable.getValueAt(n, 6).toString());
//                        pstmtdrug.setObject(7, pharmacyTable.getValueAt(n, 3).toString());
//                        pstmtdrug.setObject(8, pharmacyTable.getValueAt(n, 4).toString());
//                        pstmtdrug.setObject(9, pharmacyTable.getValueAt(n, 5).toString());
//                        pstmtdrug.setObject(10, jTextField9111.getText());
//                        pstmtdrug.setBoolean(11, false);
//                        pstmtdrug.setBoolean(12, false);
//                        pstmtdrug.executeUpdate();
//
//                    }
//                    ///nursing medication entries
//                }
//
//            }
            connectDB.commit();
            connectDB.setAutoCommit(true);

            javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful.Bill No. " + billNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            if (stm12 == null) {
            } else {
                stm12.close();
                stm12 = null;
            }
            if (stm121 == null) {
            } else {
                stm121.close();
                stm121 = null;
            }
            if (stm1211 == null) {
            } else {
                stm1211.close();
                stm1211 = null;
            }

            if (stm122 == null) {
            } else {
                stm122.close();
                stm122 = null;
            }
            if (stm122x == null) {
            } else {
                stm122x.close();
                stm122x = null;
            }
            if (stm1 == null) {
            } else {
                stm1.close();
                stm1 = null;
            }
            if (ps == null) {
            } else {
                ps.close();
                ps = null;
            }
            if (rse12 == null) {
            } else {
                rse12.close();
                rse12 = null;
            }
            if (rst == null) {
            } else {
                rst.close();
                rst = null;
            }
            if (ps11 == null) {
            } else {
                ps11.close();
                ps11 = null;
            }
            if (rst11 == null) {
            } else {
                rst11.close();
                rst11 = null;
            }
            if (stm121q == null) {
            } else {
                stm121q.close();
                stm121q = null;
            }
            if (rse121 == null) {
            } else {
                rse121.close();
                rse121 = null;
            }
            if (pstmt2 == null) {
            } else {
                pstmt2.close();
                pstmt2 = null;
            }
            if (pstB == null) {
            } else {
                pstB.close();
                pstB = null;
            }
            if (pstmt1 == null) {
            } else {
                pstmt1.close();
                pstmt1 = null;
            }
            if (pstmt2f == null) {
            } else {
                pstmt2f.close();
                pstmt2f = null;

            }
            if (rsB == null) {
            } else {
                rsB.close();
                rsB = null;

            }
            for (int k = 0; k < jTable13.getRowCount(); k++) {
                for (int r = 0; r < jTable13.getColumnCount(); r++) {
                    jTable13.setValueAt(null, k, r);
                }
            }
            for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
                for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                    pharmacyTable.setValueAt(null, k, r);
                }
            }

        } catch (java.sql.SQLException sq) {
            System.out.println("the insert request error is " + sq);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());

        }    // Add your handling code here:
    }//GEN-LAST:event_requestbtnActionPerformed

    private void jButton911ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton911ActionPerformed
        pharmacySearchDialog.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jButton911ActionPerformed

    private void pharmacySearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pharmacySearchTxtCaretUpdate

        if (pharmacySearchTxt.getCaretPosition() < 3) {

            System.out.println("Nothing");

        } else {
            if (show_drug_balance) {
                if (paymentModeTxt.getText().equalsIgnoreCase("Scheme")) {
                    pharmacySearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select distinct product||' '||st_stock_prices.strength as Drug_Prescribing,CEILING(selling_price * " + schemeDrugsMarkup + ") as price,gl_code,product_id,st_stock_prices.units,(select sum(qty) from stock_balance_qty where item_code = product_id  AND dates::date <= '" + transdatePicker.getDate() + "' ) AS balance FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + pharmacySearchTxt.getText() + "%' and opd = true AND product_id = item_code AND (st_stock_prices.department ilike '%pharmacy%' or st_stock_prices.department ilike '%nutrition%') ORDER BY 1"));

                } else {
                    pharmacySearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select distinct product||' '||st_stock_prices.strength as Drug_Prescribing,selling_price as price,gl_code,product_id,st_stock_prices.units, (select sum(qty) from stock_balance_qty where item_code = product_id  AND dates::date <= '" + transdatePicker.getDate() + "' ) AS balance FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + pharmacySearchTxt.getText() + "%' and opd = true AND product_id = item_code AND (st_stock_prices.department ilike '%pharmacy%' or st_stock_prices.department ilike '%nutrition%') ORDER BY 1"));
                }
            } else {
                if (paymentModeTxt.getText().equalsIgnoreCase("Scheme")) {
                    pharmacySearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select distinct product||' '||st_stock_prices.strength as Drug_Prescribing,CEILING(selling_price * " + schemeDrugsMarkup + ") as price,gl_code,product_id,st_stock_prices.units FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + pharmacySearchTxt.getText() + "%' and opd = true AND product_id = item_code AND (st_stock_prices.department ilike '%pharmacy%' or st_stock_prices.department ilike '%nutrition%') ORDER BY 1"));

                } else {
                    pharmacySearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "select distinct product||' '||st_stock_prices.strength as Drug_Prescribing,selling_price as price,gl_code,product_id,st_stock_prices.units FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + pharmacySearchTxt.getText() + "%' and opd = true AND product_id = item_code AND (st_stock_prices.department ilike '%pharmacy%' or st_stock_prices.department ilike '%nutrition%') ORDER BY 1"));
                }
            }

            
            pharmacySearchScrollPane.setViewportView(pharmacySearchTable);
            System.out.println("Cannot sort out");

        }

//        if (!paymentModetxt.getText().equalsIgnoreCase("Scheme")) {
//
////            if (this.inpatientCheckBox.isSelected()) {
////
////                if (jTextField111.getCaretPosition() < 3) {
////
////                    System.out.println("Nothing");
////                } else {
////                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
////                            + "select distinct product||' '||st_stock_prices.strength as Drug,selling_price as price,gl_code as gl_code,product_id  FROM st_stock_prices,st_stock_item WHERE product_id ILIKE '%" + jTextField111.getText() + "%' and opd = true AND product_id = item_code order by 1 "));
////                    //  select product as service_type,selling_price as price,gl_code FROM st_stock_prices WHERE product ILIKE '%"+jTextField11.getText()+"%' AND department ilike '"+jComboBox1.getSelectedItem()+"%' order by service_type
////
////                    jSearchScrollPane1.setViewportView(jSearchTable1);
////
////                }
////            } else 
//            {
//                if (jTextField111.getCaretPosition() < 3) {
//
//                    System.out.println("Nothing");
//                } else {
//
//                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
//                            + "select distinct product||' '||st_stock_prices.strength as Drug,selling_price as price,gl_code,product_id FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + jTextField111.getText() + "%' and opd = true AND product_id = item_code ORDER BY 1"));
//
//                    jSearchScrollPane1.setViewportView(jSearchTable1);
//                    System.out.println("Cannot sort out");
//
//                }
//            }
//        } else {
//
//            if (this.inpatientCheckBox.isSelected()) {
//
//                if (jTextField111.getCaretPosition() < 3) {
//
//                    System.out.println("Nothing");
//                } else {
//                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " select product||' '||st_stock_prices.strength as Drug,selling_price as price,gl_code as gl_code,product_id FROM st_stock_prices,st_stock_item WHERE product_id ILIKE '%" + jTextField111.getText() + "%' and opd = true AND product_id = item_code order by 1"));
//                    //  select product as service_type,selling_price as price,gl_code FROM st_stock_prices WHERE product ILIKE '%"+jTextField11.getText()+"%' AND department ilike '"+jComboBox1.getSelectedItem()+"%' order by service_type
//
//                    jSearchScrollPane1.setViewportView(jSearchTable1);
//
//                }
//            } else {
//                if (jTextField111.getCaretPosition() < 3) {
//
//                    System.out.println("Nothing");
//                } else {
//                    jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " select distinct product||' '||st_stock_prices.strength as Drug,selling_price as price,gl_code,product_id FROM st_stock_prices,st_stock_item WHERE product||' '||st_stock_prices.strength ILIKE '%" + jTextField111.getText() + "%' and opd = true AND product_id = item_code ORDER BY 1"));
//
//                    jSearchScrollPane1.setViewportView(jSearchTable1);
//                    System.out.println("Cannot sort out");
//
//                }
//            }
//        }
        /// Add your handling code here:
    }//GEN-LAST:event_pharmacySearchTxtCaretUpdate
    private void cmboxMouseClickedd() {

        System.out.println("Showing dialog");
        java.awt.Point point = searchServicesMainPanel.getLocationOnScreen();
        pharmacySearchDialog.setSize(600, 200);
        pharmacySearchDialog.setLocation(point);
        pharmacySearchDialog.setVisible(true);
    }
    private void waitingclinicscmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waitingclinicscmbxActionPerformed
        clinicComboBox.setSelectedItem(waitingclinicscmbx.getSelectedItem());
        for (int k = 0; k < clerkingwaitingTable.getRowCount(); k++) {
            for (int r = 0; r < clerkingwaitingTable.getColumnCount(); r++) {
                clerkingwaitingTable.getModel().setValueAt(null, k, r);
            }
        }
        loadALLpatientsbtn.doClick();
// Add your handling code here:
    }//GEN-LAST:event_waitingclinicscmbxActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        /*        javax.swing.JInternalFrame other = new com.afrisoftech.laboratory.ClinicalExamIntfr(connectDB, pConnDB);
         this.getParent().add(other, javax.swing.JLayeredPane.DEFAULT_LAYER);
         try {
         other.setSelected(true);
         } catch(java.beans.PropertyVetoException pvt){}
         other.setVisible(true); 
        
         */// Add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void loadALLpatientsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadALLpatientsbtnActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());
        int i = 0;

        try {
            if (this.outpatientCheckBox.isSelected()) {
                System.out.println("select distinct "
                        + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                        + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                        + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                        + "from  hp_patient_visit hpv  where   hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)   "
                        + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.urgency, hpv.input_date::time(0)");
                if (waitingclinicscmbx.getSelectedItem().toString().equalsIgnoreCase("--ALL--")) {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "select distinct "
                            + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                            + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                            + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                            + ",hpv.nature as Seen, '' as receipt_no from  hp_patient_visit hpv  where hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)    "
                            + "  ORDER BY hpv.urgency,hpv.input_date::time(0)"));
                    //coluorTable();
                } else {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "select distinct "
                            + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                            + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                            + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                            + ",hpv.nature as Seen, '' as receipt_no from  hp_patient_visit hpv  where hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)    "
                            //+ ",hpv.nature as Seen, (SELECT receipt_no FROM ac_cash_collection WHERE (ac_cash_collection.description ILIKE '%consultation%' OR ac_cash_collection.description ILIKE '%card%' OR ac_cash_collection.description ILIKE '%attend%') AND ac_cash_collection.patient_no = hpv.patient_no AND ac_cash_collection.date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1) ORDER BY ac_cash_collection.date DESC LIMIT 1) as receipt_no from  hp_patient_visit hpv  where hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)    "
                            + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.urgency,hpv.input_date::time(0)"));
                    //coluorTable();
                }
            }
            if (inpatientCheckBox.isSelected()) {
                if (waitingclinicscmbx.getSelectedItem().toString().equalsIgnoreCase("--ALL--")) {
                    System.out.println("SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,(SELECT count(patient_no) from hp_admission "
                            + "where patient_no=patient_no)as Visits,visit_id FROM hp_admission  where discharge=false and"
                            + " ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false;");
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,(SELECT count(patient_no) from hp_admission "
                            + "where patient_no=patient_no)as Visits,visit_id FROM hp_admission  where discharge=false and"
                            + " check_out=false;"));
                } else {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,(SELECT count(patient_no) from hp_admission "
                            + "where patient_no=patient_no)as Visits,visit_id FROM hp_admission  where discharge=false and"
                            + " ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false;"));

                }
            }

            coluorTable();
        } catch (Exception sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }
        // Add your handling code here:
    }//GEN-LAST:event_loadALLpatientsbtnActionPerformed
    private java.lang.Boolean checkPayment(String patientNo, String paymentMode, String patType, String urgency, String clinic) {

        int gracePariodDays = 2;
        boolean clinicExempted = false;

        try {
            java.sql.PreparedStatement pstmtPayment = connectDB.prepareStatement("SELECT review_grace_period::int FROM pb_patient_names");

            java.sql.ResultSet rsetPayment = pstmtPayment.executeQuery();

            while (rsetPayment.next()) {

                gracePariodDays = rsetPayment.getInt(1);

            }

            pstmtPayment = connectDB.prepareStatement("SELECT exempted FROM pb_clinics WHERE clinics ILIKE '" + clinic + "'");
            rsetPayment = pstmtPayment.executeQuery();
            while (rsetPayment.next()) {
                clinicExempted = rsetPayment.getBoolean(1);
            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();             //ex.printStackTrace();
        }
        Boolean patientPaid = false;
        switch (patType.toLowerCase().trim()) {
            case "ip": {
                patientPaid = true;
            }
            break;
            case "op": {
                switch (paymentMode.toLowerCase().trim()) {

                    case "scheme": {
                        patientPaid = true;
                    }
                    break;
                    //case "cash": {
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
                                            + " and receipt_time::date >= (select current_timestamp(0)::date - " + gracePariodDays + ") ");

                                    java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

                                    if (rsetVector.next()) {
                                        if (rsetVector.getInt(1) > 0 || underFive || clinicExempted) {
                                            patientPaid = true;
                                        } else {
                                            patientPaid = false;
                                            javax.swing.JOptionPane.showMessageDialog(this, "The patient has not paid for this service", "Information Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                        }
                                    }
                                } catch (Exception ex) {
                                    patientPaid = false;
                                    javax.swing.JOptionPane.showMessageDialog(this, "The patient has not paid for this service", "Information Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                    Logger.getLogger(ConsultationIntfr.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            break;
                        }

                    }
                }
            }
            break;
        }

        return patientPaid;
    }
    private void clerkingwaitingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clerkingwaitingTableMouseClicked

        schemeNameTxt.setText(null);
        paymentModeTxt.setText(null);
        underFive = false;
        String clinn = clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 3).toString();
        if (clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4) != null) {
            if (clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString().contains("Emergency")) {
                emergencyStatus = true;
            } else {
                emergencyStatus = false;
            }
        }
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = null;
            if (outpatientCheckBox.isSelected()) {
                rset1 = stmt1.executeQuery("select gender,age::int,department from hp_patient_visit where patient_no ='" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString() + "' ORDER BY age desc LIMIT 1 ");
            } else {
                rset1 = stmt1.executeQuery("select gender,pat_age::int,ward from hp_admission where patient_no ='" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString() + "' ORDER BY date_admitted desc LIMIT 1 ");
            }
            while (rset1.next()) {

                if (rset1.getInt(2) <= 5) {
                    underFive = true;
                }

            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sqe.getMessage());
        }
        

        //   java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select distinct hp_patient_visit.input_date::timestamp(0),hp_patient_visit.patient_no,hp_patient_visit.name,hp_patient_register.category,hp_patient_register.department,hp_patient_visit.comments,'false' as bill from hp_patient_visit,hp_patient_register where transaction_type ilike 'reg%' and hp_patient_visit.date = current_date and nature ='1' and hp_patient_visit.patient_no=hp_patient_register.patient_no ORDER BY input_date");
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        String patType = null;
        if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
            patType = "op";
        } else if (this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
            patType = "ip";
        }

        System.out.println("Patient Type : " + patType);
        System.out.println("Testing clerking eligibility : [" + checkPayment(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString(), clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 6).toString(), patType, clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString(), waitingclinicscmbx.getSelectedItem().toString()) + "]");
        System.out.println("Patient Type : " + patType + " and " + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 6).toString() + " and " + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString() + " for patient no " + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString());

        if (evt.getClickCount() == 1) {
            if (Objects.equals(checkPayment(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString(), clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 6).toString(), patType, clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString(), waitingclinicscmbx.getSelectedItem().toString()), Boolean.TRUE)) {

                jButton121.setEnabled(true);
                requestbtn.setEnabled(true);
                jButton22.setEnabled(true);
                jButton5.setEnabled(false);

                nameNoTxt.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString());
                jTextField922.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString());
                otherspatnojTextField923.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString());
                patnoTextField.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString());
                paymentModeTxt.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 6).toString());
                if (this.outpatientCheckBox.isSelected() == true) {
                    //   jTextField1.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 3).toString());
                    patientNameTxt.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 2).toString());
                    jTextField12.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 2).toString());
                    namejTextField18.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 2).toString());
                    visitID = "OP" + String.valueOf(com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate())).replace("-", "");
                }

                if (this.inpatientCheckBox.isSelected() == true) {
                    visitID = clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 7).toString();
                    //  jTextField1.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 1).toString());
                    patientNameTxt.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 1).toString());
                    jTextField12.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 1).toString());
                    namejTextField18.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 1).toString());
                }

                if (this.outpatientCheckBox.isSelected() == true) {
                    //      jTextField16.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString());
//            jTextField21.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString());
                    paymentModeTxt.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 6).toString());
                    paymentjTextField20.setText(clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 4).toString());

                }
                this.setTitle("Patient Card      : Patient NO:-" + nameNoTxt.getText() + " Name :-" + patientNameTxt.getText());
                //    jTextField19.setText(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString());
                String Sex = null;
                
                try {

                    java.sql.Statement stmt1 = connectDB.createStatement();
                    java.sql.ResultSet rset1 = null;
                    if (outpatientCheckBox.isSelected()) {
                        rset1 = stmt1.executeQuery("select hp.gender,funsoft_patient_age(hr.year_of_birth::date), hp.department , age from hp_patient_visit hp, hp_patient_register hr where hp.patient_no ='" + nameNoTxt.getText() + "' AND hp.patient_no = hr.patient_no LIMIT 1 ");
                    } else {
                        rset1 = stmt1.executeQuery("select hp.gender,funsoft_patient_age(hr.year_of_birth::date), hp.ward, pat_age from hp_admission hp, hp_inpatient_register hr where hp.patient_no ='" + nameNoTxt.getText() + "' AND hp.patient_no = hr.patient_no LIMIT 1 ");
                    }
                    while (rset1.next()) {

                        ageTxt.setText(rset1.getString(2));
                        agee = rset1.getInt(4);

                        // jTextField2.setText(rset1.getObject(3).toString());
                        //        jTextField17.setText(rset1.getObject(3).toString());
                        jTextField22.setText(rset1.getObject(3).toString());
                        categoryTextField19.setText(rset1.getObject(3).toString());

                        Sex = rset1.getObject(1).toString();

                        if (Sex.equalsIgnoreCase("Female")) {

                            this.femaleChkbx.setSelected(true);

                        } else {
                            this.maleChkbx.setSelected(true);
                        }
                    }
                } catch (java.sql.SQLException sqe) {
                    sqe.printStackTrace();
                    //  System.out.println("Insert not successful");
                }
                jEditorPane2.setText("");

                clinicalExamineditorTxt.setText("");
                
//                try {
//                    if (outpatientCheckBox.isSelected()) {
//                        java.sql.Statement stmt1 = connectDB.createStatement();
//                        java.sql.ResultSet rset1 = stmt1.executeQuery("select category,pay_mode,description,card_no from hp_patient_register where patient_no  = '" + nameNoTxt.getText() + "'");
//                        while (rset1.next()) {
//
//                            schemeNameTxt.setText(dbObject.getDBObject(rset1.getObject(3), "-").toString());
//
//                        }
//                    } else {
//                        java.sql.Statement stmt1 = connectDB.createStatement();
//                        java.sql.ResultSet rset1 = stmt1.executeQuery("select category,pay_mode,description,member_no from hp_inpatient_register where patient_no  = '" + this.nameNoTxt.getText().toString() + "'");
//                        while (rset1.next()) {
//
//                            schemeNameTxt.setText(dbObject.getDBObject(rset1.getObject(3), "-").toString());
//                        }
//                    }
//
//                } catch (java.sql.SQLException sqe) {
//                    sqe.printStackTrace();
//                    //  System.out.println("Insert not successful");
//                }
                
                
                

                int i = 0;
                try {
                    connectDB.setAutoCommit(false);
                    // for (int i = 0; i < jTable1.getRowCount(); i++){
                    if (clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0) != null) {
                        if (this.outpatientCheckBox.isSelected() == true) {
                            //  if (Boolean.valueOf(clerkingwaitingTable.getModel().getValueAt(i, 9).toString()) == java.lang.Boolean.TRUE) 
                            {
                                System.out.println("UPDATE hp_patient_visit SET nature = 'Cons',doctor_name  = current_user ,cons_time =current_timestamp "
                                        + "where patient_no = '" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString() + "' "
                                        + "AND  "
                                        + " date::date ='" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 8) + "'::date");
                                java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement(""
                                        + "UPDATE hp_patient_visit SET nature = 'Cons',doctor_name  = current_user ,cons_time =current_timestamp "
                                        + "where nature != 'Cons' and patient_no = '" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 0).toString() + "' "
                                        + "AND "
                                        + " date::date ='" + clerkingwaitingTable.getValueAt(clerkingwaitingTable.getSelectedRow(), 8) + "'::date");
                                pstmt46.executeUpdate();
                            }

                        }
                    }

                    java.sql.Statement stmtTable1 = connectDB.createStatement();

                    if (this.outpatientCheckBox.isSelected() == true) {

                        this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                                "select distinct "
                                + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                                + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                                + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                                + ",hpv.nature as Seen from  hp_patient_visit hpv  where  hpv.nature= 'triage' and   "
                                + " hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'    "
                                + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)"));
                    }

                    this.vitalsignstable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "Select"
                            + "  'WGHT' || hp_signs_record.weight as WGHT,"
                            + "  'HGHT' || hp_signs_record.height as HGHT,"
                            + "  'D-BP' || hp_signs_record.diastolic as D_BP,"
                            + "  'S-BP' || hp_signs_record.systolic as S_BP ,"
                            + "  'PULSE' || hp_signs_record.pulse as PULSE,"
                            + "  'TEMP' || hp_signs_record.\"temp\" as TEMP,"
                            + "  'RESP' || hp_signs_record.resp as RESP,"
                            + "  'BLOOD SUGAR' || hp_signs_record.rbs as BLOOD_SUGAR,"
                            + "  'COMMENTS' || hp_signs_record.comments as COMMENTS,"
                            + "  'TIME '||input_date as TIME "
                            + "From"
                            + "  hp_signs_record WHERE input_date::date ='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'  AND patient_no ='" + nameNoTxt.getText() + "'  order by input_date desc limit 10"));
                    System.out.println("Select"
                            + "  'WGHT' || hp_signs_record.weight as WGHT,"
                            + "  'HGHT' || hp_signs_record.height as HGHT,"
                            + "  'D-BP' || hp_signs_record.diastolic as D_BP,"
                            + "  'S-BP' || hp_signs_record.systolic as S_BP ,"
                            + "  'PULSE' || hp_signs_record.pulse as PULSE,"
                            + "  'TEMP' || hp_signs_record.\"temp\" as TEMP,"
                            + "  'RESP' || hp_signs_record.resp as RESP,"
                            + "  'BLOOD SUGAR' || hp_signs_record.rbs as BLOOD_SUGAR,"
                            + "  'COMMENTS' || hp_signs_record.comments as COMMENTS,"
                            + "  'TIME '||input_date as TIME "
                            + " From"
                            + "  hp_signs_record WHERE input_date::date ='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'  AND patient_no ='" + nameNoTxt.getText() + "'  order by input_date desc limit 10");

                    System.out.println("Select"
                            + "  'WGHT' || hp_signs_record.weight,"
                            + "  'HGHT' || hp_signs_record.height,"
                            + "  'D-BP' || hp_signs_record.diastolic,"
                            + "  'S-BP' || hp_signs_record.systolic  ,"
                            + "  'PULSE' || hp_signs_record.pulse,"
                            + "  'TEMP' || hp_signs_record.\"temp\","
                            + "  'RESP' || hp_signs_record.resp,"
                            + "  'BLOOD SUGAR' || hp_signs_record.rbs,"
                            + "  'COMMENTS' || hp_signs_record.comments,"
                            + "  'TIME '||input_date"
                            + "  From "
                            + "  hp_signs_record WHERE input_date::date =current_date  AND patient_no ='" + jTextField922.getText() + "'  order by input_date desc limit 1");

                    java.sql.Statement st = connectDB.createStatement();

                    ResultSet rset = st.executeQuery("Select"
                            + "  'WGHT' || hp_signs_record.weight,"
                            + "  'HGHT' || hp_signs_record.height,"
                            + "  'D-BP' || hp_signs_record.diastolic,"
                            + "  'S-BP' || hp_signs_record.systolic  ,"
                            + "  'PULSE' || hp_signs_record.pulse,"
                            + "  'TEMP' || hp_signs_record.\"temp\","
                            + "  'RESP' || hp_signs_record.resp,"
                            + "  'BLOOD SUGAR' || hp_signs_record.rbs,"
                            + "  'COMMENTS' || hp_signs_record.comments,"
                            + "  'TIME '||input_date"
                            + "  From "
                            + "  hp_signs_record WHERE input_date::date =current_date  AND patient_no ='" + jTextField922.getText() + "'  order by input_date desc limit 1");

                    com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
                    while (rset.next()) {

                        this.jEditorPane2.setText(dbObject.getDBObject(rset.getObject(1), "-") + " " + dbObject.getDBObject(rset.getObject(2), "-") + " "
                                + dbObject.getDBObject(rset.getObject(3), "-") + " " + dbObject.getDBObject(rset.getObject(4), "-") + " "
                                + dbObject.getDBObject(rset.getObject(5), "-") + " " + dbObject.getDBObject(rset.getObject(6), "-") + " "
                                + dbObject.getDBObject(rset.getObject(7), "-") + " " + dbObject.getDBObject(rset.getObject(8), "-") + " "
                                + dbObject.getDBObject(rset.getObject(9), "-") + " " + dbObject.getDBObject(rset.getObject(10), "-") + " ");

                    }
                    java.sql.PreparedStatement pstmtScheme = connectDB.prepareStatement("SELECT scheme FROM credit_acc_slip WHERE patient_no = ? AND (date = current_date OR date = current_date - 1)");
                    pstmtScheme.setString(1, nameNoTxt.getText());
                    java.sql.ResultSet rsetScheme = pstmtScheme.executeQuery();
                    while (rsetScheme.next()) {
                        schemeNameTxt.setText(rsetScheme.getString(1));
                    }
                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                    //javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (java.sql.SQLException sq) {
                    sq.printStackTrace();
                    try {
                        connectDB.rollback();
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(sq.getMessage());
                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        boolean hasScreening = false;
        int recentVisits = 0;
        try {
            // Establish whether the clinic requires tb-screening
            java.sql.PreparedStatement pstmtScreening = connectDB.prepareStatement("SELECT has_screening FROM pb_clinics WHERE upper(clinics) = ?");
            System.err.println("Clinic " + waitingclinicscmbx.getSelectedItem().toString());
            if (waitingclinicscmbx.getSelectedItem().toString().equalsIgnoreCase("--ALL--")) {
                pstmtScreening.setString(1, clinn);
                System.err.println("Clinnnn " + clinn);
            } else {
                pstmtScreening.setString(1, waitingclinicscmbx.getSelectedItem().toString().toUpperCase());

            }
            java.sql.ResultSet rsetScreening = pstmtScreening.executeQuery();
            while (rsetScreening.next()) {
                hasScreening = rsetScreening.getBoolean(1);
            }

            // Get number of screening encounters for the patient in the last 12 days
            java.sql.PreparedStatement pstmtVisit = connectDB.prepareStatement("SELECT count(*) FROM hp_screening_data WHERE patient_no = ? AND visit_date::date > now()::date - 13");
            pstmtVisit.setString(1, nameNoTxt.getText());
            java.sql.ResultSet rsetVisit = pstmtVisit.executeQuery();
            while (rsetVisit.next()) {
                recentVisits = rsetVisit.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        if (hasScreening) {
            if (recentVisits < 1) {
                this.tbScreeningPatientNoTxt.setText(nameNoTxt.getText());
                this.tbScreeningPatientNameTxt.setText(patientNameTxt.getText());
                this.tbScreeningAgeTxt.setText(ageTxt.getText());
                tbScreeningTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT indicator_description as screening_indicator, false as yes, false as no, null as remarks FROM hp_screening_indicators WHERE lower_age_limit < " + agee + " AND upper_age_limit > " + agee+ " ORDER BY indicator_index"));
                tbScreeningTable.setRowHeight(20);
                this.tbScreeningDialog.setSize(900, 700);
                this.tbScreeningDialog.setVisible(true);
            }
        }

        loadALLpatientsbtnActionPerformed(null);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // Add your handling code here:
    }//GEN-LAST:event_clerkingwaitingTableMouseClicked
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
        for (int k = 0; k < clerkingwaitingTable.getRowCount(); k++) {
            for (int r = 0; r < clerkingwaitingTable.getColumnCount(); r++) {
                clerkingwaitingTable.getModel().setValueAt(null, k, r);
            }
        }

        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    private void cmbox1MouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = diagnosisTable.getLocationOnScreen();
        icd10SearchDialog.setSize(600, 200);
        icd10SearchDialog.setLocation(point);
        icd10SearchDialog.setVisible(true);
    }

    private void icd10SearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_icd10SearchTxtCaretUpdate
        if (icd10SearchTxt.getText().length() > 2) {
            if (icd10table = true) {
                icd10SearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT (upper(disease_name)) as name,code from hp_diseases where  (disease_name ILIKE '%" + icd10SearchTxt.getText() + "%' OR code ILIKE '%" + icd10SearchTxt.getText() + "%')  ORDER BY code,1   "));

                System.out.println("SELECT (upper(disease_name)) as name,code from hp_diseases where code='" + icdCode + "' and  disease_name ILIKE '%" + icd10SearchTxt.getText() + "%' ORDER BY code,1  LIMIT 50");
            }
            if (provisinal = true) {
                icd10SearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT (upper(disease_name)) as name,code from hp_diseases where (disease_name ILIKE '%" + icd10SearchTxt.getText() + "%' OR code ILIKE '%" + icd10SearchTxt.getText() + "%') ORDER BY code,1   "));

                System.out.println("SELECT (upper(disease_name)) as name,code from hp_diseases where (disease_name ILIKE '%" + icd10SearchTxt.getText() + "%' OR code ILIKE '%" + icd10SearchTxt.getText() + "%') ORDER BY code,1  LIMIT 50");
            }

        }
}//GEN-LAST:event_icd10SearchTxtCaretUpdate

    private void icd10SearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icd10SearchTableMouseClicked
        if (provisinal = true) {
            provisionalDiagnosistxt.setText(icd10SearchTable.getValueAt(icd10SearchTable.getSelectedRow(), 1).toString().trim() + ":-" + icd10SearchTable.getValueAt(icd10SearchTable.getSelectedRow(), 0).toString().trim());

            icdCode = icd10SearchTable.getValueAt(icd10SearchTable.getSelectedRow(), 1).toString().trim();
            System.out.println("\n\n\n\n\nthe icd10 code is " + icdCode);
        }

        if (icd10table = true) {
            diagnosisTable.setValueAt(icd10SearchTable.getValueAt(icd10SearchTable.getSelectedRow(), 0), diagnosisTable.getSelectedRow(), 1);
            diagnosisTable.setValueAt(icd10SearchTable.getValueAt(icd10SearchTable.getSelectedRow(), 1), diagnosisTable.getSelectedRow(), 0);
            icd10SearchDialog.dispose();
            icd10SearchTxt.setText("");

        }

}//GEN-LAST:event_icd10SearchTableMouseClicked

    private void icd10SearchTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icd10SearchTableMouseEntered
        // TODO add your handling code here:
}//GEN-LAST:event_icd10SearchTableMouseEntered

    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        icd10SearchDialog.dispose();  // Add your handling code here:
}//GEN-LAST:event_jButton93ActionPerformed

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        try {
            java.sql.Statement ps11 = connectDB.createStatement();
            java.sql.ResultSet rst11 = ps11.executeQuery("select code,sub_code from pb_activity WHERE activity ilike '" + jComboBox12.getSelectedItem() + "'");
            while (rst11.next()) {

                jTextField5.setText(rst11.getString(1));
                //jTextField22.setText(rst11.getString(2));

            }
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("Select not successful");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        if (clear) {
            jEditorPane2.setText("");
            notesTextField.setText(null);
            clinicalExamineditorTxt.setText("");
            provisionalDiagnosistxt.setText("");
            complainstxt.setText("");
            complainsTextPaneTxt.setText("");
            jTextPane1.setText("");
            referalCheckbox.setSelected(false);

            for (int k = 0; k < diagnosisTable.getRowCount(); k++) {
                for (int r = 0; r < diagnosisTable.getColumnCount(); r++) {
                    diagnosisTable.getModel().setValueAt(null, k, r);
                }
            }
            com.afrisoftech.lib.ClearTable.clearthisTable(jTable13);
            com.afrisoftech.lib.ClearTable.clearthisTable(jTable14);
            com.afrisoftech.lib.ClearTable.clearthisTable(pharmacyTable);
            com.afrisoftech.lib.ClearTable.clearthisTable(symptomsTable);
            com.afrisoftech.lib.ClearTable.clearthisTable(rxplanTable);
            com.afrisoftech.lib.ClearTable.clearthisTable(diagnosisTable);
        }
        /*        for (int k = 0; k < jTable3.getRowCount(); k++) {
         for (int r = 0; r < jTable3.getColumnCount(); r++) {
         jTable3.getModel().setValueAt(null, k, r);
         }
         }
         */
// TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void radiologyResultsTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiologyResultsTblMouseClicked

        if (Boolean.valueOf(radiologyResultsTbl.getModel().getValueAt(radiologyResultsTbl.getSelectedRow(), 4).toString()) == java.lang.Boolean.TRUE) {

            java.util.Date dates = null;

            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT ?::date");
                pstmt.setObject(1, radiologyResultsTbl.getValueAt(radiologyResultsTbl.getSelectedRow(), 0));
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    dates = rset.getDate(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();             //ex.printStackTrace();
            }
            com.afrisoftech.reports.XrayResultPdf policy = new com.afrisoftech.reports.XrayResultPdf();

            policy.XrayResultPdf(connectDB, dates, dates, radiologyResultsTbl.getValueAt(radiologyResultsTbl.getSelectedRow(), 1).toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_radiologyResultsTblMouseClicked

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        for (int k = 0; k < radiologyResultsTbl.getRowCount(); k++) {
            for (int r = 0; r < radiologyResultsTbl.getColumnCount(); r++) {
                radiologyResultsTbl.getModel().setValueAt(null, k, r);
            }
        }
        radiologyResultsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct date,patient_no,patient_name,xray_no,false as read_results from hp_xray_results where date >= '" + radiologyResultsDatePicker.getDate() + "' ORDER BY date,xray_no"));

//        String labNo = null;
//        String patientNo = null;
//        int p = 0;
//        int q = 0;
//        //  int r = 0;
//        int patNo1 = 0;
//        int Total1 = 0;
//        int Total = 0;
//        int labNo1 = 0;
//        try {
//            java.sql.Statement stmtTable113 = connectDB.createStatement();
//            // java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery("select count(distinct lab_no) from hp_lab_results where doc_read = false  and result_shown = false AND date >='" + datePicker11.getDate().toString() + "' and lab_no !='null' and lab_no is not null ");
//            java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery("select count(distinct xray_no) from hp_xray_results where patient_no = '" + nameNoTxt.getText() + "' and xray_no !='null' and xray_no is not null ");
//
//            // java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery("select count(distinct lab_no) from hp_lab_results where doc_read = false  and result_shown = false AND date >='"+datePicker11.getDate().toString()+"' and lab_no !='null' and lab_no is not null and clinic ilike '"+jComboBox11.getSelectedItem().toString()+"'");
//            while (rsetTable113.next()) {
//                labNo1 = rsetTable113.getInt(1);
//                System.out.println("Lab no is " + labNo1);
//            }
//
//            if (labNo1 > 0) {
//                java.sql.Statement stmtTable112 = connectDB.createStatement();
//
//                //java.sql.ResultSet rsetTable112 = stmtTable112.executeQuery("select distinct lab_no,patient_name from hp_lab_results where doc_read = false and result_shown = false AND date >='"+datePicker13.getDate().toString()+"' and lab_no is not null and lab_no !='null' and clinic ilike '"+jComboBox11.getSelectedItem().toString()+"' order by lab_no  ");
//                java.sql.ResultSet rsetTable112 = stmtTable112.executeQuery("select distinct xray_no,patient_name from hp_xray_results where patient_no = '" + nameNoTxt.getText() + "' and xray_no is not null and xray_no !='null' order by xray_no  ");
//
//                while (rsetTable112.next()) {
//                    labNo = rsetTable112.getString(1);
//                    patientNo = rsetTable112.getString(2);
//                    System.out.println("Lab no2 is " + patientNo);
//                }
//
//                stmtTable112.close();
//                rsetTable112.close();
//
//            } else {
//            }
//
//            // for (int l = 0; l < listofDays.length; l++) {
//            java.sql.Statement stmtTable11 = connectDB.createStatement();
//
//            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select count(distinct xray_no) from hp_xray_results where patient_no = '" + nameNoTxt.getText() + "'");
//
//            while (rsetTable11.next()) {
//                patNo1 = rsetTable11.getInt(1);
//
//            }
//
//            //   jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct date,patient_no,patient_name,lab_no,doc_read from hp_lab_results where doc_read = false AND date >='"+datePicker1.getDate().toString()+"'  ORDER BY date,lab_no"));
//            java.sql.Statement stmtTable1r = connectDB.createStatement();
//
//            java.sql.ResultSet rsetTable1r = stmtTable1r.executeQuery("select distinct date,patient_no,patient_name,xray_no,false from hp_xray_results where patient_no = '" + nameNoTxt.getText() + "' ORDER BY date,xray_no");
//
//            while (rsetTable1r.next()) {
//
//                radilogyResultsTbl.setValueAt(rsetTable1r.getObject(1), p, 0);
//                radilogyResultsTbl.setValueAt(rsetTable1r.getObject(2), p, 1);
//                radilogyResultsTbl.setValueAt(rsetTable1r.getObject(3), p, 2);
//                radilogyResultsTbl.setValueAt(rsetTable1r.getObject(4), p, 3);
//                radilogyResultsTbl.setValueAt(rsetTable1r.getObject(5), p, 4);
//
//                p++;
//
//            }
//            radilogyResultsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct date,patient_no,patient_name,xray_no,false from hp_xray_results where date >= '" + radiologyResultsDatePicker.getDate() + "' ORDER BY date,xray_no"));
//            stmtTable1r.close();
//            rsetTable1r.close();
//            //}
//            stmtTable113.close();
//            rsetTable113.close();
//
//            stmtTable11.close();
//            rsetTable11.close();
//
//        } catch (java.sql.SQLException sqlExec) {
//
//            sqlExec.printStackTrace();
//
//            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
//
//        }
        /* if (patNo1 > 5) {
        
         this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");
         this.jLabel19.setForeground(new java.awt.Color(255, 0, 51));
         } else {
         this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");
        
        
         this.jLabel19.setForeground(new java.awt.Color(51, 51, 255));
         }*/
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void pharmacySearchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacySearchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pharmacySearchTxtActionPerformed

    private void outpatientCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outpatientCheckBoxItemStateChanged
        clerkingTabbedPane.setSelectedIndex(0);//(select '--ALL--') UNION 
        this.waitingclinicscmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "  (SELECT distinct Clinics FROM pb_clinics) ORDER BY 1 asc"));
        this.clinicComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '--ALL--') UNION   (SELECT distinct Clinics FROM pb_clinics) ORDER BY 1 asc"));

    }//GEN-LAST:event_outpatientCheckBoxItemStateChanged

    private void inpatientCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inpatientCheckBoxItemStateChanged
        clerkingTabbedPane.setSelectedIndex(0);//(select '--ALL--') union 
        this.waitingclinicscmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select distinct ward_name  from hp_wards)   ORDER BY 1 asc"));
        this.clinicComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '--ALL--') union (select distinct ward_name  from hp_wards)   ORDER BY 1 asc"));

    }//GEN-LAST:event_inpatientCheckBoxItemStateChanged

    private void waitingclinicscmbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_waitingclinicscmbxItemStateChanged
        this.clinicComboBox.setSelectedItem(this.waitingclinicscmbx.getSelectedItem());


    }//GEN-LAST:event_waitingclinicscmbxItemStateChanged

    private void complainsdialogtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complainsdialogtableMouseClicked
        String textpane = provisionalDiagnosistxt.getText();
        provisionalDiagnosistxt.setText(this.complainsdialogtable.getValueAt(complainsdialogtable.getSelectedRow(), 0).toString().trim());
        this.complainsDialog.dispose();
        textpane = textpane + " " + this.provisionalDiagnosistxt.getText().trim() + ".";
        provisionalDiagnosistxt.setText(textpane);
    }//GEN-LAST:event_complainsdialogtableMouseClicked

    private void jButton912ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton912ActionPerformed
        this.complainsDialog.dispose();
    }//GEN-LAST:event_jButton912ActionPerformed

    private void searchDiseasesTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchDiseasesTxtCaretUpdate
        if (searchDiseasesTxt.getText().length() > 2) {

            complainsdialogtable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT (upper(disease_name)) as name,code from hp_diseases where  (disease_name ILIKE '%" + searchDiseasesTxt.getText() + "%' OR code ILIKE '%" + searchDiseasesTxt.getText() + "%')  ORDER BY 1, code LIMIT 50"));

            //  System.out.println("SELECT (upper(disease_name)) as name,code from hp_diseases where code='" + icdCode + "' and  disease_name ILIKE '%" + icd10SearchTxt.getText() + "%' ORDER BY code,1  LIMIT 50");
        }
//        if (searchDiseasesTxt.getText().length() >= 3) {
//            this.complainsdialogtable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
//                    "SELECT (upper(disease_name)) as name,code from hp_diseases where disease_name ilike '%" + searchDiseasesTxt.getText().toString() + "%' order by 1"));
//        }
    }//GEN-LAST:event_searchDiseasesTxtCaretUpdate

    private void urgencyComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_urgencyComboBoxItemStateChanged
        if (this.outpatientCheckBox.isSelected() == true) {
            if (this.urgencyComboBox.getSelectedIndex() == 1) {

                this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                        "select distinct "
                        + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                        + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                        + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                        + ",hpv.nature as Seen from  hp_patient_visit hpv  where  hpv.nature= 'triage' and hpv.urgency='Normal' and  hpv.input_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'    "
                        + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)"));
            } else if (this.urgencyComboBox.getSelectedIndex() == 2) {

                this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                        "select distinct "
                        + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                        + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                        + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                        + ",hpv.nature as Seen from  hp_patient_visit hpv  where  hpv.nature= 'triage' and  hpv.urgency='Urgent' and  hpv.input_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'    "
                        + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)"));
            }
        }
    }//GEN-LAST:event_urgencyComboBoxItemStateChanged

    private void searchButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton2ActionPerformed
        if (clinicComboBox.getSelectedItem().toString().equalsIgnoreCase("-")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please choose type of clinic you are Running", "Information Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {
            searchButtonClicked2a();
        }
        // Add your handling code here:
    }//GEN-LAST:event_searchButton2ActionPerformed

    private void searchButton111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton111ActionPerformed
        searchButtonClicked211h();  // Add your handling code here:
    }//GEN-LAST:event_searchButton111ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        if (nameNoTxt.getText().length() > 0) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                        + "from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' ");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient History "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + ""
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + ""
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            java.util.ArrayList queryVector = new ArrayList();
            queryVector.add("SELECT   date, marital_status, sex_hist, contraceptive, "
                    + "       cont_method, illness as Chronic_illness, drug_allergy as Allergic, illness_method as  Allergy_Type,   "
                    + "       alcohol, smoking, hist_heading as X_RAy , narration as XRay_type, "
                    + "       user_name,  doctor"
                    + "  FROM hp_patients_hist "
                    + "   where patient_no ='" + this.nameNoTxt.getText().toString() + "' "
                    + "  AND date::date <="
                    + " '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + " order by  date asc");
            queryVector.add(" select  date,symptom,result as Severity,duration,description,doctor from hp_patients_hist where"
                    + " patient_no='" + this.nameNoTxt.getText().toString() + "' "
                    + "  AND date::date <="
                    + " '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + " order by  date asc");
            com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
            pdf.FunsoftCustomPdf(connectDB, header, com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB, ""
                    + "(select  date,concat('symptom:-',symptom,' Severity:-',result,'\n duration:-',duration,' description:-',description) as details,doctor from hp_patients_hist "
                    + "where patient_no='" + this.nameNoTxt.getText().toString() + "'   AND date::date <= '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + " order by  date asc) "
                    + "union "
                    + "  (SELECT   date,concat( 'marital:-',marital_status, ' Sex:-',sex_hist, '\n Contraceptive:-',contraceptive,  "
                    + "       '  Method:-',cont_method, ' Illness:-',illness  ,'\n Allergic:-', drug_allergy ,' Type:-', illness_method , "
                    + "       '\n Alcoho:-',alcohol,' Smoking:-', smoking,'\n X_Ray:-', hist_heading   , ' Type:-', narration  ) as details,  "
                    + "          doctor"
                    + "  FROM hp_patients_hist  "
                    + "   where patient_no ='" + this.nameNoTxt.getText().toString() + "'   AND date::date <= '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + " order by  date asc)"));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST select a patient file to view the history");
        }
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton82ActionPerformed
        if (nameNoTxt.getText().length() > 0) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                        + "from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' ");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient Examination "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + ""
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + ""
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("select distinct typeof_test as Examination,comments as Symptoms, description as Prov_Diag,doctor,input_date as Date "
                    + "from hp_clinical_results where patient_no = '" + this.nameNoTxt.getText().toString() + "' "
                    + "  AND input_date::date <='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + " order by input_date asc");
            com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
            pdf.FunsoftCustomPdf(connectDB, header,
                    com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB, ""
                            + "select distinct typeof_test as Examination,comments as Symptoms, description as Prov_Diag,doctor,input_date as Date "
                            + "from hp_clinical_results where patient_no = '" + this.nameNoTxt.getText().toString() + "' "
                            + "  AND input_date::date <='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                            + " order by input_date asc"));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST select a patient file to view the history");
        }
    }//GEN-LAST:event_jButton82ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (nameNoTxt.getText().length() > 0) {
//            com.afrisoftech.reports.emr.MOHPatientCardPdf policy = new com.afrisoftech.reports.emr.MOHPatientCardPdf();
//            if (outpatientCheckBox.isSelected()) {
//                policy.MOHPatientCardPdf(connectDB, transdatePicker.getDate(), transdatePicker.getDate(), nameNoTxt.getText(), "OP");
//            } else {
//                policy.MOHPatientCardPdf(connectDB, transdatePicker.getDate(), transdatePicker.getDate(), nameNoTxt.getText(), "OP");
//            }
            com.afrisoftech.reports.PatientCardPdf policyReport = new com.afrisoftech.reports.PatientCardPdf();//connectDB, transdatePicker.getDate(), transdatePicker.getDate(),nameNoTxt.getText());
//
            policyReport.PatientCardPdf(connectDB, transdatePicker.getDate(), transdatePicker.getDate(), nameNoTxt.getText());

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST select a patient file in order to view the card");
        }// Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clinicComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clinicComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clinicComboBoxActionPerformed

    private void complainsTextPaneTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_complainsTextPaneTxtCaretUpdate
        if (complainsTextPaneTxt.getText().replaceAll("^.*?(\\w+)\\W*$", "$1").length() > 4) {
            java.awt.Point point = this.complainsTextPaneTxt.getLocationOnScreen();
            point.setLocation(complainsTextPaneTxt.getLocationOnScreen().x, complainsTextPaneTxt .getLocationOnScreen().y + 120);

            System.out.println("Showing dialog");
            // java.awt.Point point = complainsTextPaneTxt.getLocationOnScreen();
            symptomsDialog.setSize(600, 200);
            symptomsDialog.setLocation(point);
            symptomsDialog.setVisible(true);

            symptomsDialogTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT symptom FROM hp_symptoms WHERE symptom ILIKE '%" + complainsTextPaneTxt.getText().replaceAll("^.*?(\\w+)\\W*$", "$1") + "%' ORDER BY 1"));
            complainsTextPaneTxt.requestFocusInWindow();;
        } else {
            symptomsDialog.dispose();
        }
//complainsTextPaneTxt.getText().replaceAll("^.*?(\\w+)\\W*$", "$1");
    }//GEN-LAST:event_complainsTextPaneTxtCaretUpdate

    private void complainstxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complainstxtMouseClicked
        System.out.println("Showing dialog");
        java.awt.Point point = complainsTextPaneTxt.getLocationOnScreen();
        complainsDialog.setSize(600, 200);
        complainsDialog.setLocation(point);
        complainsDialog.setVisible(true);
    }//GEN-LAST:event_complainstxtMouseClicked

    private void complainstxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_complainstxtKeyPressed
        int KEY = evt.getKeyCode();
        if (KEY == KeyEvent.VK_ENTER) {
            String textpane = complainsTextPaneTxt.getText();
            textpane = textpane + "" + this.complainstxt.getText().trim() + ".";
            complainsTextPaneTxt.setText(textpane);
        }
    }//GEN-LAST:event_complainstxtKeyPressed

    private void jTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable13MouseClicked
        if (radiologyCheck.isSelected() == false && lablatoryCheck.isSelected() == false) {
            if (jTable13.getSelectedColumn() == 0) {

                this.cmboxMouseClicked();
            }
        } else if (radiologyCheck.isSelected() == true || lablatoryCheck.isSelected() == true) {
            if (jTable13.getSelectedColumn() == 1) {

                this.cmboxMouseClicked();
            }
        }

    }//GEN-LAST:event_jTable13MouseClicked

    private void jTable13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable13KeyReleased
        if (radiologyCheck.isSelected() == false && lablatoryCheck.isSelected() == false) {
            if (jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1) != null) {
                if (jTable13.getSelectedColumn() == jTable13.getSelectedColumn()) {
                    float qty = java.lang.Float.parseFloat(jTable13.getValueAt(jTable13.getSelectedRow(), 1).toString());
                    float price = java.lang.Float.parseFloat(jTable13.getValueAt(jTable13.getSelectedRow(), 2).toString());
                    float total = qty * price;
                    jTable13.setValueAt(total, jTable13.getSelectedRow(), 3);
                    double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(jTable13, 3);
                    jTextField3.setText(java.lang.String.valueOf(totalSum));
                }

            }
        } else if (radiologyCheck.isSelected() == true || lablatoryCheck.isSelected() == true) {
            for (int i = 0; i < jTable13.getRowCount(); i++) {
                if (jTable13.getModel().getValueAt(0, 1) == Boolean.TRUE) {
                    float qty = java.lang.Float.parseFloat(jTable13.getValueAt(i, 2).toString());
                    float price = java.lang.Float.parseFloat(jTable13.getValueAt(i, 3).toString());
                    float total = qty * price;
                    jTable13.setValueAt(total, i, 4);
                    double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(jTable13, 4);
                    jTextField3.setText(java.lang.String.valueOf(totalSum));
                }

            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable13KeyReleased

    private void pharmacyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pharmacyTableMouseClicked
        if (radiologyCheck.isSelected() == false || lablatoryCheck.isSelected() == false) {
            if (pharmacyTable.getSelectedColumn() == 0) {

                this.cmboxMouseClickedd();
            }
        }// Add your handling code here:
    }//GEN-LAST:event_pharmacyTableMouseClicked

    private void pharmacyTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pharmacyTableKeyReleased
        if (pharmacyTable.getSelectedColumn() != 6) {
            computeTotals();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_pharmacyTableKeyReleased

    private void generalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalCheckBoxActionPerformed
        this.searchservicesTextField.setEditable(false);
        this.servicesOrderHeaderPanel.setVisible(false);
        this.generalServicesOrderingPanel.setVisible(true);
        this.pharmacyRequestsPanel.setVisible(false);
        this.jPanel82.setVisible(true);
        this.jScrollPane2.setVisible(true);
        this.jScrollPane3.setVisible(false);
        this.jLabel16.setVisible(false);
        jComboBox2.setVisible(false);
        for (int k = 0; k < jTable13.getRowCount(); k++) {
            for (int r = 0; r < jTable13.getColumnCount(); r++) {
                jTable13.setValueAt(null, k, r);
            }
        }
        for (int k = 0; k < jTable14.getRowCount(); k++) {
            for (int r = 0; r < jTable14.getColumnCount(); r++) {
                jTable14.setValueAt(null, k, r);
            }
        }

        for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
            for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                pharmacyTable.setValueAt(null, k, r);
            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_generalCheckBoxActionPerformed

    private void lablatoryCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lablatoryCheckActionPerformed
        float hasHistory = 0;
        java.sql.Statement stm121b;
        try {
            stm121b = connectDB.createStatement();

            java.sql.ResultSet rset24bx = stm121b.executeQuery("SELECT COUNT(patient_no) FROM hp_clinical_results WHERE patient_no = '" + nameNoTxt.getText() + "' AND typeof_test IS NOT NULL AND comments IS NOT NULL AND date = '" + com.afrisoftech.lib.ServerTime.getSQLDate(connectDB)+ "'");
            while (rset24bx.next()) {
                hasHistory = rset24bx.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hasHistory > 0 || inpatientCheckBox.isSelected()) {
            this.searchservicesTextField.setEditable(true);
            query = "Laboratory";
            this.generalServicesOrderingPanel.setVisible(false);
            this.servicesOrderHeaderPanel.setVisible(true);
            this.pharmacyRequestsPanel.setVisible(false);
            this.jPanel82.setVisible(true);
            this.jScrollPane2.setVisible(true);
            this.jScrollPane3.setVisible(false);
            this.jLabel16.setVisible(false);
            jComboBox2.setVisible(false);
            for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
                for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                    pharmacyTable.setValueAt(null, k, r);
                }
            }

            diagnosticsServicesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select request_code,request_name FROM clerking_requests_category where department ilike 'Laboratory' order by 1 asc"));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST record patient history before proceeding to diagnostics/investigations.");
        }
        // Add your handling code here:
    }//GEN-LAST:event_lablatoryCheckActionPerformed

    private void pharmacyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacyCheckBoxActionPerformed
        int number = 0;
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_patient_diagnosis WHERE patient_no = ? AND date_recorded::date > now()::date - 2");

            pstmt.setString(1, nameNoTxt.getText());

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                number = rset.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        if (number > 0 || inpatientCheckBox.isSelected()) {
            this.generalServicesOrderingPanel.setVisible(false);
            this.pharmacyRequestsPanel.setVisible(true);
            this.servicesOrderHeaderPanel.setVisible(false);
            this.jPanel82.setVisible(true);
            for (int k = 0; k < jTable13.getRowCount(); k++) {
                for (int r = 0; r < jTable13.getColumnCount(); r++) {
                    jTable13.setValueAt(null, k, r);
                }
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST record the diagnosis for this patient before proceeding to do prescription.");

            clerkingTabbedPane.setSelectedIndex(2);
        }

        // Add your handling code here:
    }//GEN-LAST:event_pharmacyCheckBoxActionPerformed

    private void radiologyCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiologyCheckActionPerformed
        float hasHistory = 0;
        java.sql.Statement stm121b;
        try {
            stm121b = connectDB.createStatement();

            java.sql.ResultSet rset24bx = stm121b.executeQuery("SELECT COUNT(patient_no) FROM hp_clinical_results WHERE patient_no = '" + nameNoTxt.getText() + "' AND typeof_test IS NOT NULL AND comments IS NOT NULL AND date = '" + com.afrisoftech.lib.ServerTime.getSQLDate(connectDB) + "'");
            while (rset24bx.next()) {
                hasHistory = rset24bx.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hasHistory > 0 || inpatientCheckBox.isSelected()) {
            this.searchservicesTextField.setEditable(true);
            this.generalServicesOrderingPanel.setVisible(false);
            query = "X-Ray";
            this.jPanel82.setVisible(true);
            this.servicesOrderHeaderPanel.setVisible(true);
            this.jScrollPane2.setVisible(true);
            this.jScrollPane3.setVisible(false);
            this.jLabel16.setVisible(false);
            jComboBox2.setVisible(false);
            for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
                for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                    pharmacyTable.setValueAt(null, k, r);
                }
            }

            diagnosticsServicesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select request_code,request_name FROM clerking_requests_category where trim(department) ilike 'X-Ray' or trim(department) ilike 'XRay' or trim(department) ilike 'x-ray' or trim(department) ilike 'radiology' or trim(department) ilike 'imaging' order by 1 asc"));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "You MUST record patient history before proceeding to diagnostics/investigations.");
        }
        // Add your handling code here:
    }//GEN-LAST:event_radiologyCheckActionPerformed

    private void diagnosticsServicesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagnosticsServicesTableMouseClicked
        System.out.println(""
                + "SELECT  false as Approve,clerking_requests.request,'0.0' as Quantity,pb_operating_parameters.rate,pb_operating_parameters.rate as Amount,pb_operating_parameters.gl_account "
                + "FROM clerking_requests,pb_operating_parameters "
                + "where"
                + " clerking_requests.request_category ilike '" + diagnosticsServicesTable.getValueAt(diagnosticsServicesTable.getSelectedRow(), 1).toString().trim() + "'"
                + " and pb_operating_parameters.service_type=clerking_requests.request order by 2 asc; ");

        if(paymentModeTxt.getText().equalsIgnoreCase("Scheme")){
            this.jTable13.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT  false as Approve,clerking_requests.request,'1' as Quantity,pb_operating_parameters.anaesthetist_rate as rate,"
                + "pb_operating_parameters.anaesthetist_rate as Amount,pb_operating_parameters.gl_account,'' as Specimen "
                + " FROM clerking_requests,pb_operating_parameters "
                + " where"
                + " clerking_requests.request_category ilike '" + diagnosticsServicesTable.getValueAt(diagnosticsServicesTable.getSelectedRow(), 1).toString().trim() + "'"
                + " and pb_operating_parameters.service_type=clerking_requests.request  order by 2 asc; "));
        }else{
        this.jTable13.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT  false as Approve,clerking_requests.request,'1' as Quantity,pb_operating_parameters.rate,"
                + "pb_operating_parameters.rate as Amount,pb_operating_parameters.gl_account,'' as Specimen "
                + " FROM clerking_requests,pb_operating_parameters "
                + " where"
                + " clerking_requests.request_category ilike '" + diagnosticsServicesTable.getValueAt(diagnosticsServicesTable.getSelectedRow(), 1).toString().trim() + "'"
                + " and pb_operating_parameters.service_type=clerking_requests.request  order by 2 asc; ")); //AND pb_operating_parameters.status = 'Active'
        }
        if (lablatoryCheck.isSelected() == Boolean.TRUE) {

            cmBoxspecimen.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT distinct specimen  FROM pb_lab_specimen;"));

            javax.swing.table.TableColumn teditor2 = this.jTable13.getColumn("SPECIMEN");

            teditor2.setCellEditor(new javax.swing.DefaultCellEditor(cmBoxspecimen));

        }

        //        try {
        //
        //                java.sql.PreparedStatement stm12 = connectDB.prepareStatement(""
        //                        + "SELECT  clerking_requests.request,'0.0' as Quantity,pb_operating_parameters.rate,'0.0' as Amount,pb_operating_parameters.gl_account " +
        //"FROM clerking_requests,pb_operating_parameters " +
        //"where"
        //            + " clerking_requests.request_category ilike '" + jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim() + "-" + jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim() + "-" + query + "'" +
        //" and pb_operating_parameters.service_type=clerking_requests.request order by 1 asc; ");
        //                java.sql.ResultSet rse12 = stm12.executeQuery();
        //
        //                    for (int i = 0; i < pharmacyTable.getRowCount(); i++) {
        //                        while (rse12.next()) {
        //                            System.out.println("niko hapa ajez");
        //                for (int j = 0; j < pharmacyTable.getColumnCount(); j++) {
        //
        //                    this.pharmacyTable.setValueAt(rse12.getObject(j), i, j);
        //
        //                        }
        //                }
        //            }
        //        } catch (Exception ex) {
        //
        //        }
    }//GEN-LAST:event_diagnosticsServicesTableMouseClicked

    private void provisionalDiagnosistxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_provisionalDiagnosistxtFocusGained
    }//GEN-LAST:event_provisionalDiagnosistxtFocusGained

    private void provisionalDiagnosistxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_provisionalDiagnosistxtMouseClicked

        icd10table = false;
        provisinal = false;
        provisinal = true;
        icd10table = false;
        System.out.println("Showing dialog");
        java.awt.Point point = provisionalDiagnosistxt.getLocationOnScreen();
        complainsDialog.setSize(600, 200);
        complainsDialog.setLocation(point);
        complainsDialog.setVisible(true);

        // this.cmbox1MouseClicked();
    }//GEN-LAST:event_provisionalDiagnosistxtMouseClicked

    private void diagnosisTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagnosisTableMouseClicked
        if (this.ICD10CheckBox.isSelected() == Boolean.TRUE) {
            if (diagnosisTable.getSelectedColumn() == 0) {
                icd10table = true;
                provisinal = false;
                this.cmbox1MouseClicked();

            }
        }
    }//GEN-LAST:event_diagnosisTableMouseClicked

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        /* this.jButton1.setVisible(true);
         try {

         java.sql.Statement stmt = connectDB.createStatement();
         java.sql.ResultSet rset = stmt.executeQuery("select ward_code from hp_wards where ward_name ='" + jComboBox2.getSelectedItem() + "'");
         while (rset.next()) {
         jTextField5.setText(rset.getObject(1).toString());
         // jTextField11.setText(rset.getObject(2).toString());
         }
         } catch (java.sql.SQLException sqe) {
         sqe.printStackTrace();
         System.out.println("Select not successful");
         }

         // crset4.setCommand("select bed_no from hp_bed_setup where ward ='"+jComboBox2.getSelectedItem()+"' and occupied = 'f' and available = 'Available' order by bed_no asc");
         // try {
         // crset4.execute();
         if (jButton1.getText().equalsIgnoreCase("Update")) {
         jComboBox4.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '" + jTextField42.getText() + "' as bed_no UNION ALL select bed_no from hp_bed_setup where ward ='" + jComboBox2.getSelectedItem() + "' and occupied = false and available = 'Available' order by bed_no asc"));

         } else {
         jComboBox4.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '-' as bed_no UNION ALL select bed_no from hp_bed_setup where ward ='" + jComboBox2.getSelectedItem() + "' and occupied = false and available = 'Available' order by bed_no asc"));
         // jComboBox4.setModel(new org.netbeans.lib.sql.models.ComboBoxModel(crset4, "bed_no", null, null, null));
         //}catch (java.sql.SQLException sql){}
         }*/
        // Add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void referalCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_referalCheckboxActionPerformed
        this.servicesOrderHeaderPanel.setVisible(true);
        this.pharmacyRequestsPanel.setVisible(false);
        this.jPanel82.setVisible(true);
        this.jScrollPane2.setVisible(false);
        this.jScrollPane3.setVisible(true);
        this.jLabel16.setVisible(true);
        jComboBox2.setVisible(true);

        for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
            for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                pharmacyTable.setValueAt(null, k, r);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_referalCheckboxActionPerformed

    private void rxplanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rxplanTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rxplanTableMouseClicked

    private void rxplanTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rxplanTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rxplanTableKeyReleased

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        if (this.jTabbedPane2.getSelectedIndex() == 1) {

            firstpageHistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT   type_of_accident, "
                    + "       mode_of_arrival, status, patient_at_risk, medication_taken, patient_valuables, "
                    + "       serious_illness, specify_illness, illness_time, organ_transplant, "
                    + "       transplant_desc, transplant_time, drinks_alcohol, alcohol_amount, "
                    + "       smokes, smoking_duration, ever_smoked, stopped_smoking, alergic, "
                    + "       specify_alergy, social_history, specify_history, difficulty_in "
                    + "  FROM nursing.history_firstpage where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and transaction_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by transaction_date asc "));

            //////second tab
            neurohistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT  suffers_stroke, "
                    + "       stroke_effect, epileptic, epilepsy_desc, epileptic_when, suffers_depression, "
                    + "       depression_time, high_bp, hbp_time, chest_pain, chestpain_desc, "
                    + "       chestpain_time, heart_disease, disease_desc, disease_time, rheumatic_fever, "
                    + "       rheumatic_when, swelling_ankle, difficulty_breathing, specify_breathing, "
                    + "       home_oxygen, oxygen_explain, chronic, chronic_duration, chronic_desc, "
                    + "       chronic_amout, chronic_color, asmatic, asmatic_when "
                    + "  FROM nursing.respiratory_cardiac_neurohistory  where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and transaction_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by transaction_date asc "));

            /////third tab
            gynaehistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + " SELECT    kidney_disorder, "
                    + "       disorder_type, disorder_time, gastric_reflux, specify_gastric, "
                    + "       gastric_when, menstrual_period, menstrual_weeks, breast_feeding "
                    + "  FROM nursing.gastric_renal_gynaehistory   where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and action_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by action_date asc "));

////fourth
            endocrinehistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "SELECT   artificial_joints, "
                    + "       joint_specify, injuries, explain_injury, diabetic, controlled_by, "
                    + "       hepatitis, specify_hepatitis,  hepatitiswhen"
                    + "  FROM nursing.muscularsceletal_endocrinehistory    where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and action_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by action_date asc "));

            //////five
            surgeryhistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + " SELECT    blood_disorder, "
                    + "       specify_disorder, disorder_time, blood_transfusion, specify_transfussion, "
                    + "       blood_clot, specify_clot, clot_time, bleeding_tendency, specify_tendency "
                    + "  FROM nursing.blood_past_surgeryhistory "
                    + "   where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and transaction_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by transaction_date asc "));
/////six

            medicationhistoryTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "   SELECT  drug_name strength, tablets_per_day, number_at_each_time "
                    + "  FROM nursing.medication_table_history  where "
                    + "   patient_no='" + jTextField922.getText().toString().trim() + "' and transaction_date::date between '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                    + "and  '" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "+1' order by transaction_date asc "));

        }

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void searchpatienttxtfldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchpatienttxtfldCaretUpdate
        if (searchpatienttxtfld.getText().length() > 6) {
            if (searchnocheckbx.isSelected()) {
                if (outpatientCheckBox.isSelected()) {
                    System.out.println(" select distinct "
                            + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                            + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                            + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date,"
                            // + "(SELECT receipt_no FROM ac_cash_collection WHERE (ac_cash_collection.description ILIKE '%consultation%' OR ac_cash_collection.description ILIKE '%card%' OR ac_cash_collection.description ILIKE '%attend%') AND ac_cash_collection.patient_no = hpv.patient_no AND ac_cash_collection.date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1) ORDER BY ac_cash_collection.date DESC LIMIT 1) as receipt_no "
                            + "from  hp_patient_visit hpv  where hpv.patient_no ilike '" + searchpatienttxtfld.getText() + "%'  "
                            + "and hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)    "
                            + "    ORDER BY hpv.input_date::time(0) ");
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            " select distinct "
                            + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                            + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                            + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                            + ",hpv.nature as Seen, '' as receipt_no"
                            //                            + ",hpv.nature as Seen,(SELECT receipt_no FROM ac_cash_collection WHERE (ac_cash_collection.description ILIKE '%consultation%' OR ac_cash_collection.description ILIKE '%card%' OR ac_cash_collection.description ILIKE '%attend%') AND ac_cash_collection.patient_no = hpv.patient_no AND ac_cash_collection.date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1) ORDER BY ac_cash_collection.date DESC LIMIT 1) as receipt_no"

                            + " from  hp_patient_visit hpv  where (hpv.patient_no ilike '%" + searchpatienttxtfld.getText() + "%' OR hpv.name ilike '%" + searchpatienttxtfld.getText() + "%') "
                            + "and hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)    "
                            + "   ORDER BY hpv.input_date::time(0) "));
                } else if (inpatientCheckBox.isSelected()) {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,"
                            + "(SELECT count(patient_no) from hp_admission where patient_no=patient_no)as Visits "
                            + "FROM"
                            + " hp_admission  where "
                            + " (patient_no  ilike '%" + searchpatienttxtfld.getText() + "%' OR patient_name ilike '%" + searchpatienttxtfld.getText() + "%') and"
                            + " discharge=false and ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false;"));
                }
            } else if (searchnamecheckbx.isSelected()) {
                if (outpatientCheckBox.isSelected()) {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "select distinct "
                            + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                            + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                            + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                            + ",hpv.nature as Seen, '' as receipt_no "
                            + " from  hp_patient_visit hpv  where  "
                            + "   (hpv.name ilike '%" + searchpatienttxtfld.getText() + "%' OR hpv.patient_no ilike '%" + searchpatienttxtfld.getText() + "%') "
                            + "and hpv.input_date::date>'" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date  - (SELECT review_grace_period FROM pb_patient_names LIMIT 1)   "
                            + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)  "));
                } else if (inpatientCheckBox.isSelected() == true) {
                    this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                            "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,"
                            + "(SELECT count(patient_no) from hp_admission where patient_no=patient_no)as Visits "
                            + "FROM"
                            + " hp_admission  where "
                            + "  (patient_name ilike '%" + searchpatienttxtfld.getText() + "%' OR patient_no ilike '%" + searchpatienttxtfld.getText() + "%') and "
                            + " discharge=false and ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false; "));
                }
            }
            coluorTable();
        }

    }//GEN-LAST:event_searchpatienttxtfldCaretUpdate

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        try {
            connectDB.setAutoCommit(false);
            String cat = null, Sex, marital = null;
            if (outpatientCheckBox.isSelected()) {
                cat = "OP";
            } else if (inpatientCheckBox.isSelected()) {
                cat = "IP";
            }
            if (femaleChkbx.isSelected()) {
                Sex = this.femaleChkbx.getText();
            } else {
                Sex = this.maleChkbx.getText();
            }
            java.sql.Statement stm121a = connectDB.createStatement();
            java.sql.ResultSet rset24a = null;
            rset24a = stm121a.executeQuery("SELECT gender FROM hp_patient_visit WHERE patient_no = '" + jTextField922.getText() + "' ORDER BY 1 DESC LIMIT 1");
            while (rset24a.next()) {
                marital = rset24a.getString(1);
            }
            if (clerkingTabbedPane.getSelectedIndex() == 0) {
                float history = 0;

                java.sql.Statement stm121bx = connectDB.createStatement();

                java.sql.ResultSet rset24bx1 = stm121bx.executeQuery("SELECT COUNT(patient_no) FROM hp_clinical_results WHERE patient_no = '" + nameNoTxt.getText() + "' AND typeof_test IS NOT NULL AND comments IS NOT NULL AND date = '" + transdatePicker.getDate() + "'");

                while (rset24bx1.next()) {
                    history = rset24bx1.getFloat(1);
                }
                if ((clinicalExamineditorTxt.getText().length() > 5 && complainsTextPaneTxt.getText().length() > 5 && provisionalDiagnosistxt.getText().length() > 3) || history > 0) {
                    PreparedStatement pstmt21 = connectDB.prepareStatement("INSERT INTO hp_clinical_results VALUES(?,?,?,?,?,?,?, ?, ?, ?, current_user, ?,?,?)");

                    pstmt21.setString(1, jTextField922.getText()); 
                    pstmt21.setString(2, patientNameTxt.getText());
                    pstmt21.setString(3, clinicalExamineditorTxt.getText());
                    pstmt21.setString(4, "");
                    pstmt21.setString(5, cat);
                    // pstmt21.setString(6,jTable1.getValueAt(i,0).toString());
                    pstmt21.setString(6, jTextField9111.getText());
                    pstmt21.setDate(7, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                    pstmt21.setString(8, "");

                    pstmt21.setObject(9, complainsTextPaneTxt.getText());
                    pstmt21.setTimestamp(10, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));

                    pstmt21.setObject(11, provisionalDiagnosistxt.getText());
                    pstmt21.setObject(12, "");
                    pstmt21.setString(13, "");

                    pstmt21.executeUpdate();
                    PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE hp_patient_visit SET nature = 'Cons',doctor_name  = current_user ,cons_time =current_timestamp where patient_no = '" + jTextField922.getText() + "' AND  nature = '1' ");
                    pstmt46.executeUpdate();
                    PreparedStatement pstmt46x = connectDB.prepareStatement("UPDATE hp_patient_register SET waiting_patient = false where patient_no = '" + jTextField922.getText() + "'");
                    pstmt46x.executeUpdate();
                    javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    clear = false;
                    javax.swing.JOptionPane.showMessageDialog(this, "The client history/complaints, physical examination and provisional diagnosis MUST be indicated before proceeding to save the info.");
                }
            } else if (clerkingTabbedPane.getSelectedIndex() == 2) {
                if (diagnosisTable.isEditing()) {
                    diagnosisTable.getCellEditor().stopCellEditing();
                }

                java.sql.Statement sts = connectDB.createStatement();
                java.sql.Statement st = connectDB.createStatement();
                java.sql.Statement stm121b = connectDB.createStatement();
                java.sql.ResultSet rss = null;
                java.sql.ResultSet rs = null;
                java.sql.ResultSet rset24bx = null;
                boolean categorySelected = true;
                for (int i = 0; i < diagnosisTable.getRowCount(); i++) {
                    if (diagnosisTable.getModel().getValueAt(i, 1) != null) {
                        if (diagnosisTable.getModel().getValueAt(i, 2) == null) {
                            categorySelected = false;
                        }
                    }

                }
                if (!categorySelected) {
                    clear = false;
                    javax.swing.JOptionPane.showMessageDialog(this, "You MUST Select the disease category in order to save");

                } else {

                    for (int i = 0; i < diagnosisTable.getRowCount(); i++) {
                        if (diagnosisTable.getModel().getValueAt(i, 1) != null) {
                            String categ = "";
                            float diagnosed = 0;
                            rset24bx = stm121b.executeQuery("SELECT COUNT(main_service) FROM hp_patient_diagnosis WHERE patient_no = '" + jTextField922.getText() + "' AND main_service = '" + diagnosisTable.getValueAt(i, 0).toString() + "' AND date_recorded = '" + transdatePicker.getDate() + "'");
                            while (rset24bx.next()) {
                                diagnosed = rset24bx.getFloat(1);
                            }

                            float history = 0;

                            java.sql.Statement stm121bx = connectDB.createStatement();

                            java.sql.ResultSet rset24bx1 = stm121bx.executeQuery("SELECT COUNT(patient_no) FROM hp_clinical_results WHERE patient_no = '" + nameNoTxt.getText() + "' AND typeof_test IS NOT NULL AND comments IS NOT NULL AND date = '" + com.afrisoftech.lib.ServerTime.getSQLDate(connectDB) + "'");

                            while (rset24bx1.next()) {
                                history = rset24bx1.getFloat(1);
                            }

                            if (diagnosed > 0) {
                            } else {
                                if (history > 0) {
                                    int incDays = 0;
                                    rs = st.executeQuery("SELECT ('" + transdatePicker.getDate() + "')::date-('" + transdatePicker.getDate() + "')::date");
                                    while (rs.next()) {
                                        incDays = rs.getInt(1) + 1;
                                    }

                                    rss = sts.executeQuery("SELECT drug_time_limit FROM hp_diseases WHERE code = '" + diagnosisTable.getValueAt(i, 0) + "'");
                                    while (rss.next()) {
                                        categ = rss.getString(1);
                                    }
                                    PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO hp_patient_diagnosis VALUES"
                                            + "(?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,current_user,?)");
                                    pstmt.setString(1, nameNoTxt.getText());
                                    pstmt.setString(2, patientNameTxt.getText());
                                    pstmt.setObject(3, diagnosisTable.getValueAt(i, 0).toString());
                                    pstmt.setObject(4, diagnosisTable.getValueAt(i, 1).toString());
                                    pstmt.setInt(5, 1);
                                    pstmt.setString(6, jTextField8.getText());
                                    pstmt.setDouble(7, 0.00);
                                    pstmt.setString(8, this.clinicalExamineditorTxt.getText());
                                    pstmt.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt.setDate(10, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt.setString(11, "Discharge");
                                    pstmt.setString(12, "");
                                    pstmt.setObject(13, diagnosisTable.getValueAt(i, 2));//categ
                                    pstmt.setString(14, "");
                                    pstmt.setString(15, waitingclinicscmbx.getSelectedItem().toString());
                                    pstmt.setString(16, this.clinicalExamineditorTxt.getText());
                                    pstmt.setString(17, cat);
                                    if(ageTxt.getText().contains("yr")){
                                    String accountNumber = null;
                                    String[] result = ageTxt.getText().split("\\s");
                                    for (int x = 0; x < result.length; x++) {
                                        System.out.println("Token " + x + " is [" + result[x] + "]");
                                    }
                                    String age = result[0];
                                    
                                    pstmt.setDouble(18, java.lang.Double.valueOf(age));
                                    } else {
                                        pstmt.setDouble(18, java.lang.Double.valueOf("0"));
                                    }
                                    pstmt.setString(19, Sex);
                                    pstmt.setString(20, marital);
                                    pstmt.setDate(21, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt.setDate(22, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt.setTimestamp(23, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));// new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));

                                    pstmt.setInt(24, incDays);
                                    pstmt.executeUpdate();
                                } else {
                                    clear = false;
                                    javax.swing.JOptionPane.showMessageDialog(this, "You MUST capture patient history first before proceeding to diagnosis.");

                                }
                            }
                        }
                    }
                    javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (clerkingTabbedPane.getSelectedIndex() == 1) {
                Boolean cultureTestPresent = false;
                if (lablatoryCheck.isSelected()) {
                    for (int i = 0; i < jTable13.getRowCount(); i++) {
                        if (jTable13.getValueAt(i, 0) == Boolean.TRUE) {
                            if (jTable13.getValueAt(i, 1).toString().toLowerCase().contains("culture")) {
                                cultureTestPresent = true;
                            }

                        }
                    }
                }
                boolean prescInfo = true;
                if (pharmacyCheckBox.isSelected()) {
                    for (int i = 0; i < pharmacyTable.getRowCount(); i++) {
                        if (pharmacyTable.getValueAt(i, 0) != null) {
                            if (pharmacyTable.getValueAt(i, 3) == null || pharmacyTable.getValueAt(i, 4) == null || pharmacyTable.getValueAt(i, 5) == null || pharmacyTable.getValueAt(i, 6) == null) {
                                prescInfo = false;
                            }

                            if (prescInfo) {
                                try {
                                    if (pharmacyTable.getValueAt(i, 3).toString().isEmpty() || pharmacyTable.getValueAt(i, 4).toString().isEmpty() || pharmacyTable.getValueAt(i, 5).toString().isEmpty() || pharmacyTable.getValueAt(i, 6).toString().isEmpty()) {
                                        prescInfo = false;
                                    }
                                } catch (Exception e) {
                                    clear = false;
                                    javax.swing.JOptionPane.showMessageDialog(this, "Please ensure you have provided all the Prescription Details (Route, Frequency, No. of Days & Dosage)");

                                }
                            }
                        }
                    }

                }

                if (!prescInfo) {
                    clear = false;
                    javax.swing.JOptionPane.showMessageDialog(this, "Please ensure you have provided all the Prescription Details (Route, Frequency, No. of Days & Dosage)");

                } else if (lablatoryCheck.isSelected() && notesTextField.getText().length() < 3 && cultureTestPresent) {
                    javax.swing.JOptionPane.showMessageDialog(this, "You MUST capture Notes on antibiotics Used before sending the lAB CULTURE Requests.");
                    notesTextField.requestFocusInWindow();
                    clear = false;
                } else {
                    if (jTable13.isEditing()) {
                        jTable13.getCellEditor().stopCellEditing();
                    }
                    if (pharmacyTable.isEditing()) {
                        pharmacyTable.getCellEditor().stopCellEditing();
                    }
                    saverequest(Sex);
                }

            } else if (clerkingTabbedPane.getSelectedIndex() == 3) {

                if (referalCheckbox.isSelected()) {
                    PreparedStatement pstmtd = connectDB.prepareStatement("INSERT INTO hp_admission_request("
                            + "patient_no, patient_name, doctor, ward_refered, date_requested,"
                            + "comments,admitted)"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)");

                    pstmtd.setString(1, jTextField922.getText());
                    pstmtd.setString(2, patientNameTxt.getText());
                    pstmtd.setObject(3, jTextField9111.getText());
                    pstmtd.setObject(4, jComboBox2.getSelectedItem());
                    pstmtd.setTimestamp(5, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));

                    pstmtd.setString(6, this.jTextPane1.getText());
                    pstmtd.setBoolean(7, false);
                    pstmtd.executeUpdate();
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
            connectDB.commit();
            connectDB.setAutoCommit(true);

            this.jButton37.doClick();
            clear = true;
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

        this.setCursor(
                new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_saveButtonActionPerformed

    private void searchservicesTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchservicesTextFieldCaretUpdate
        if (searchservicesTextField.getText().length() > 3) {
            if (lablatoryCheck.isSelected()) {
//                jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select request_code::int,request_name "
//                        + "FROM clerking_requests_category "
//                        + "where department ilike 'Laboratory' and request_name ilike '%" + jTextField1.getText() + "%' order by 1 asc"));
                this.jTable13.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  false as Approve,clerking_requests.request,'1' as Quantity,pb_operating_parameters.rate,pb_operating_parameters.rate as Amount,pb_operating_parameters.gl_account,'' as specimen "
                        + "FROM clerking_requests,pb_operating_parameters "
                        + "where"
                        + " clerking_requests.request ilike '%" + searchservicesTextField.getText() + "%'"
                        + " and pb_operating_parameters.service_type=clerking_requests.request order by 2 asc; "));
            }
            if (radiologyCheck.isSelected()) {
//                jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select request_code::int,request_name "
//                        + "FROM clerking_requests_category "
//                        + "where department ilike 'X-Ray' and request_name ilike '%" + jTextField1.getText() + "%' order by 1 asc"));
                this.jTable13.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT  false as Approve,clerking_requests.request,'1' as Quantity,pb_operating_parameters.rate,pb_operating_parameters.rate as Amount,pb_operating_parameters.gl_account ,'' as part_"
                        + "FROM clerking_requests,pb_operating_parameters "
                        + "where"
                        + " clerking_requests.request ilike '%" + searchservicesTextField.getText() + "%'"
                        + " and pb_operating_parameters.service_type=clerking_requests.request order by 2 asc; "));
            }
        }
    }//GEN-LAST:event_searchservicesTextFieldCaretUpdate

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        if (jTable14.getSelectedColumn() == 0) {

            this.cmboxMouseClicked();
        }
    }//GEN-LAST:event_jTable14MouseClicked

    private void jTable14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable14KeyReleased
        if (jTable14.getModel().getValueAt(jTable14.getSelectedRow(), 1) != null) {
            if (jTable14.getSelectedColumn() == jTable14.getSelectedColumn()) {
                float qty = java.lang.Float.parseFloat(jTable14.getValueAt(jTable14.getSelectedRow(), 1).toString());
                float price = java.lang.Float.parseFloat(jTable14.getValueAt(jTable14.getSelectedRow(), 2).toString());
                float total = qty * price;
                jTable14.setValueAt(total, jTable14.getSelectedRow(), 3);
                double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(jTable14, 3);
                jTextField3.setText(java.lang.String.valueOf(totalSum));
            }

        }
    }//GEN-LAST:event_jTable14KeyReleased

    private void printgeneralCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printgeneralCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printgeneralCheckBoxActionPerformed

    private void printlablatoryCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printlablatoryCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printlablatoryCheckActionPerformed

    private void printpharmacyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printpharmacyCheckBoxActionPerformed
        System.out.println(""
                + "SELECT   distinct medication_administration.prescription_no,"
                + "medication_administration.date_prescribed, medication_administration.drug_code, "
                + "       medication_administration.drug, medication_administration.dosage, medication_administration.route, "
                + "       medication_administration.frequency, medication_administration.no_of_days, medication_administration.doctor "
                + "  FROM nursing.medication_administration"
                + "  where   medication_administration.patient_no='" + this.nameNoTxt.getText() + "'"
                + "   and medication_administration.date_prescribed::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'   "
                + "   order by 1,2,4 desc  ");

        this.requestsViewTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "SELECT   distinct medication_administration.prescription_no,"
                + "medication_administration.date_prescribed, medication_administration.drug_code, "
                + "       medication_administration.drug, medication_administration.dosage, medication_administration.route, "
                + "       medication_administration.frequency, medication_administration.no_of_days, medication_administration.doctor "
                + "  FROM nursing.medication_administration"
                + "  where   medication_administration.patient_no='" + this.nameNoTxt.getText() + "'"
                + "   and medication_administration.date_prescribed::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'   "
                + "   order by 1,2,4 desc  "));

    }//GEN-LAST:event_printpharmacyCheckBoxActionPerformed

    private void printradiologyCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printradiologyCheckActionPerformed

        this.requestsViewTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                "SELECT trans_date,  service, quantity, "
                + "       amount,  "
                + "       visit_id,  bed_no, "
                + "          collected, results, paid,   "
                + "       posted_to_lab,doctor"
                + "  FROM pb_doctors_request  where  requisition_no='X-RAY' and patient_no='" + this.nameNoTxt.getText() + "' "
                + "and trans_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "));

    }//GEN-LAST:event_printradiologyCheckActionPerformed

    private void printgeneralCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_printgeneralCheckBoxItemStateChanged
        this.requestsViewTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                "SELECT trans_date,  service, quantity, "
                + "       amount,  "
                + "       visit_id,  bed_no, "
                + "          collected, results, paid,   "
                + "       posted_to_lab,doctor"
                + "  FROM pb_doctors_request  where  requisition_no='GENERAL' and patient_no='" + this.nameNoTxt.getText() + "' "
                + "and trans_date::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "));

    }//GEN-LAST:event_printgeneralCheckBoxItemStateChanged

    private void printlablatoryCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_printlablatoryCheckItemStateChanged

        this.requestsViewTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                "SELECT trans_date,  service, quantity, "
                + "       amount,  "
                + "       visit_id,  bed_no, "
                + "          collected, results, paid,   "
                + "       posted_to_lab,doctor"
                + "  FROM pb_doctors_request  where  requisition_no='LAB' and patient_no='" + this.nameNoTxt.getText() + "'"
                + " and trans_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "));


    }//GEN-LAST:event_printlablatoryCheckItemStateChanged

    private void printgeneralButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printgeneralButtonActionPerformed
        if (printgeneralCheckBox.isSelected() == true) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                        + "from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' ");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient Examination "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + ""
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + ""
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
            pdf.FunsoftCustomPdf(connectDB, header,
                    com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB, ""
                            + "SELECT trans_date,  service, quantity, "
                            + "       amount,  "
                            + "       visit_id,  bed_no, "
                            + "          collected, results, paid,   "
                            + "       posted_to_lab,doctor"
                            + "  FROM pb_doctors_request  where  requisition_no='GENERAL' and patient_no='" + this.nameNoTxt.getText() + "' "
                            + "and trans_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "));
        }
    }//GEN-LAST:event_printgeneralButtonActionPerformed

    private void printlabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printlabButtonActionPerformed
        if (printlablatoryCheck.isSelected() == true) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + " (select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit::text,last_visit::text "
                        + " from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' order by last_visit limit 1)"
                        + " union "
                        + " (SELECT distinct patient_no, patient_name, speciality,residence,tel,date_admitted||' visit ID:-'||visit_id,visit_id::text \n"
                        + " FROM hp_admission where patient_no = '" + this.nameNoTxt.getText().toString() + "' order by visit_id limit 1)");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient LAB Request\n "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + "\n"
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + "\n"
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            com.afrisoftech.laboratory.LabRequestPdf pdf = new com.afrisoftech.laboratory.LabRequestPdf();
            pdf.FunsoftCustomPdf(connectDB, header, nameNoTxt.getText(), com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
        }
    }//GEN-LAST:event_printlabButtonActionPerformed

    private void printprescriptionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printprescriptionsButtonActionPerformed
        if (printpharmacyCheckBox.isSelected() == true) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                        + "from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' ");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient Examination "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + ""
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + ""
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
            pdf.FunsoftCustomPdf(connectDB, header,
                    com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB, ""
                            + "SELECT   distinct medication_administration.prescription_no,"
                            + "medication_administration.date_prescribed, medication_administration.drug_code, "
                            + "       medication_administration.drug, medication_administration.dosage, medication_administration.route, "
                            + "       medication_administration.frequency, medication_administration.no_of_days, medication_administration.doctor "
                            + "  FROM nursing.medication_administration"
                            + "  where   medication_administration.patient_no='" + this.nameNoTxt.getText() + "'"
                            + "   and medication_administration.date_prescribed::date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'   "
                            + "   order by 1,2,4 desc  "));
        }
    }//GEN-LAST:event_printprescriptionsButtonActionPerformed

    private void printxrayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printxrayButtonActionPerformed
        if (printradiologyCheck.isSelected() == true) {
            String header = null;
            try {
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                        + "from hp_patient_register where patient_no = '" + this.nameNoTxt.getText().toString() + "' ");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                if (rsetVector.next()) {
                    header = "                Patient Examination "
                            + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + ""
                            + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + ""
                            + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
            pdf.FunsoftCustomPdf(connectDB, header,
                    com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB,
                            "SELECT trans_date,  service, quantity, "
                            + "       amount,  "
                            + "       visit_id,  bed_no, "
                            + "          collected, results, paid,   "
                            + "       posted_to_lab,doctor"
                            + "  FROM pb_doctors_request  where  requisition_no='X-RAY' and patient_no='" + this.nameNoTxt.getText() + "' "
                            + "and trans_date='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "));
        }
    }//GEN-LAST:event_printxrayButtonActionPerformed

    private void requestsCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_requestsCheckBoxItemStateChanged
        System.out.println("(SELECT  hp_pharmacy.paid ,hp_pharmacy.manual_pr as Receipt_NO,"
                + "                    hp_pharmacy.description,"
                + "                    hp_pharmacy.quantity,"
                + "                    hp_pharmacy.price,"
                + "                    round(hp_pharmacy.amount) AS amount,"
                + "                    hp_pharmacy.date_prescribed"
                + "                   FROM hp_pharmacy"
                + "                  WHERE "
                + "                   hp_pharmacy.date_prescribed>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'"
                + " and patient_no='" + this.nameNoTxt.getText() + "')"
                + "UNION ALL"
                + "(SELECT hp_patient_billing.paid,hp_patient_billing.doctor as Receipt_NO,"
                + "                    hp_patient_billing.service AS description,"
                + "                    hp_patient_billing.quantity,"
                + "                    trunc(hp_patient_billing.amount / hp_patient_billing.quantity, 2) AS price,"
                + "                    hp_patient_billing.amount,"
                + "                    hp_patient_billing.trans_date AS date_prescribed "
                + "                   FROM hp_patient_billing"
                + "                  WHERE    hp_patient_billing.trans_date >='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                + "and patient_no='" + this.nameNoTxt.getText() + "')");

        this.requestsViewTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(SELECT  hp_pharmacy.paid ,hp_pharmacy.manual_pr as Receipt_NO,"
                + "                    hp_pharmacy.description,"
                + "                    hp_pharmacy.quantity,"
                + "                    hp_pharmacy.price,"
                + "                    round(hp_pharmacy.amount) AS amount,"
                + "                    hp_pharmacy.date_prescribed"
                + "                   FROM hp_pharmacy"
                + "                  WHERE "
                + "                   hp_pharmacy.date_prescribed>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'"
                + " and patient_no='" + this.nameNoTxt.getText() + "')"
                + "UNION ALL"
                + "(SELECT hp_patient_billing.paid,hp_patient_billing.doctor as Receipt_NO,"
                + "                    hp_patient_billing.service AS description,"
                + "                    hp_patient_billing.quantity,"
                + "                    trunc(hp_patient_billing.amount / hp_patient_billing.quantity, 2) AS price,"
                + "                    hp_patient_billing.amount,"
                + "                    hp_patient_billing.trans_date AS date_prescribed "
                + "                   FROM hp_patient_billing"
                + "                  WHERE    hp_patient_billing.trans_date >='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "' "
                + "and patient_no='" + this.nameNoTxt.getText() + "')"));
    }//GEN-LAST:event_requestsCheckBoxItemStateChanged

    private void loadTRIAGEDpatientsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTRIAGEDpatientsbtnActionPerformed

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

        System.out.println(datenowSql1.toString());

        java.sql.Timestamp datenowSql = new java.sql.Timestamp(dateNow);

        System.out.println(datenowSql.toString());
        int i = 0;

        try {
            if (this.outpatientCheckBox.isSelected() == true) {
                System.out.println("select distinct "
                        + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                        + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                        + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                        + "from  hp_patient_visit hpv  where   hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'    "
                        + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)");

                this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,
                        "select distinct "
                        + "hpv.patient_no,hpv.input_date::time(0) as Visit_time,"
                        + "UPPER(hpv.name) as name,hpv.clinic,hpv.urgency,hpv.comments as New_Old,hpv.payment,"
                        + "(select refer_source from hp_patient_register where patient_no=hpv.patient_no )as ReferredFrom, hpv.input_date::date as Visit_Date"
                        + ",hpv.nature as Seen from  hp_patient_visit hpv  where hpv.nature= 'triage' and hpv.input_date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'    "
                        + "and hpv.clinic ='" + this.waitingclinicscmbx.getSelectedItem().toString() + "'  ORDER BY hpv.input_date::time(0)"));
            }
            if (this.inpatientCheckBox.isSelected() == true) {

                System.out.println("SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,(SELECT count(patient_no) from hp_admission "
                        + "where patient_no=patient_no)as Visits,visit_id FROM hp_admission  where discharge=false and"
                        + " ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false;");
                this.clerkingwaitingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + "SELECT patient_no, patient_name, bed_no, wing, date_admitted, admission_no,mode_of_payment,(SELECT count(patient_no) from hp_admission "
                        + "where patient_no=patient_no)as Visits,visit_id FROM hp_admission  where discharge=false and"
                        + " ward='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and check_out=false;"));
            }
            coluorTable();
        } catch (Exception sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }
    }//GEN-LAST:event_loadTRIAGEDpatientsbtnActionPerformed

    private void allpatCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_allpatCheckBoxItemStateChanged
        if (allpatCheckBox.isSelected() == Boolean.TRUE) {
            labresultsButton.doClick();

            readCheckBox.setSelected(false);
            readCheckBox.setEnabled(false);
        }
    }//GEN-LAST:event_allpatCheckBoxItemStateChanged

    private void readCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_readCheckBoxItemStateChanged
        if (readCheckBox.isSelected() == Boolean.TRUE) {
            for (int i = 0; i < labresultsTable.getRowCount(); i++) {
                if (labresultsTable.getModel().getValueAt(i, 0) != null) {
                    labresultsTable.getModel().setValueAt(true, i, 4);
                }
            }
            String receiptNo = null;
            //        if (jTable1.getSelectedColumn() == 2) {
            System.out.print("See receipt No3. : " + receiptNo);
            for (int i = 0; i < labresultsTable.getRowCount(); i++) {
                if (labresultsTable.getModel().getValueAt(i, 4) == Boolean.TRUE) {

                    try {

                        java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE pb_doctors_request SET collected = true where request_id = '" + labresultsTable.getValueAt(i, 3).toString() + "'");
                        pstmt46.executeUpdate();

                    } catch (java.sql.SQLException sqlExec) {

                        sqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

                    }
                }
            }
            if (Objects.equals(Boolean.valueOf(readCheckBox.isSelected()), java.lang.Boolean.TRUE)) {
//            receiptNo = labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 3).toString();
//            com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();
//
//            policy.PatientLabResultsPdf(connectDB, receiptNo, receiptNo);
                try {
                    java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                            + "select distinct patient_no,initcap(first_name||' '||second_name) as Names,Category,residence,tel_no,last_visit "
                            + "from hp_patient_register where patient_no = '" + labresultsTable.getValueAt(labresultsTable.getSelectedRow(), 1).toString() + "' ");
                    String header = null;
                    java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                    if (rsetVector.next()) {
                        header = "                Patient LAB Results \n"
                                + "Patient NO :-" + rsetVector.getString(1) + "        Names:-" + rsetVector.getString(2) + "\n"
                                + "Patient Category:-" + rsetVector.getString(3) + "        Last Visit:-" + rsetVector.getString(6) + "\n"
                                + "Residence:-" + rsetVector.getString(4) + "       Tel No:-" + rsetVector.getString(5) + "";
                    }

                    com.afrisoftech.lib.FunsoftCustomPdf pdf = new com.afrisoftech.lib.FunsoftCustomPdf();
                    pdf.FunsoftCustomPdf(connectDB, header,
                            com.afrisoftech.lib.EditorTextSetUp.editorGetText(connectDB, ""
                                    + "(SELECT date  ,"
                                    + "  concat('Date:-',date,'   Time taken:-', time_taken,'Time Posted:-',input_date  ,"
                                    + "   '\nRequest ID:-',request_id,'  Test Code:-',code  ,"
                                    + "  '\nLab Test:-',typeof_test,'    Parameter:-', parameter,  "
                                    + "          '\nResult:-',result,   "
                                    + "         '\nLab Technologist:-', lab_manager,   "
                                    + "         'Comments:-', comments, '\nPosted by:-',user_name,  "
                                    + "         '   Doctor:-',doctor ) as Results "
                                    + "  FROM hp_lab_results where date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "' "
                                    + "   and patient_no='" + labresultsTable.getValueAt(0, 1).toString() + "' "
                                    + "    order by input_date desc) "));
//                                    + "union"
//                                    + "    "
//                                    + "(SELECT date  ,"
//                                    + "  concat('Date:-',date,'   Time taken:-', time_taken,'Time Posted:-',input_date  ,"
//                                    + "   '\nRequest ID:-',request_id,'  Test Code:-',code  ,"
//                                    + "  '\nLab Test:-',typeof_test,'    Parameter:-', parameter,  "
//                                    + "       '\nResult:-',result,   "
//                                    + "         '\nLab Technologist:-', labmanager,   "
//                                    + "         '\nComments:-', '' , '\nPosted by:-',user_name,  "
//                                    + "         '   Doctor:-',doctor ) as Results "
//                                    + "    FROM lims_lab_results "
//                                    + "    where date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "' "
//                                    + "   and patient_no='" + labresultsTable.getValueAt(0, 1).toString() + "' "
//                                    + "    order by input_date desc) "));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_readCheckBoxItemStateChanged

    private void back2LabListingBrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back2LabListingBrnActionPerformed
        labResultsTabbenPane.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_back2LabListingBrnActionPerformed

    private void displayLaboratoryResultsPDFBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayLaboratoryResultsPDFBtnActionPerformed

        com.afrisoftech.reports.PatientLabResultsPdf policy = new com.afrisoftech.reports.PatientLabResultsPdf();

        policy.PatientLabResultsPdf(connectDB, labNo, labNo);

        // TODO add your handling code here:
    }//GEN-LAST:event_displayLaboratoryResultsPDFBtnActionPerformed

    private void icd10SearchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_icd10SearchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_icd10SearchTxtActionPerformed

    private void medicationhistoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationhistoryTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_medicationhistoryTableMouseClicked

    private void currentCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentCheckBoxActionPerformed

        labresultsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select distinct date, patient_no, patient_name, lab_no as procedure_request_no, typeof_test procedure_name, false as results_read from hp_lab_results "
                + " where date::date>='" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(labdatePicker.getDate()) + "'  and patient_no = '" + nameNoTxt.getText() + "' "
                + "  order by 1 asc) "));
// TODO add your handling code here:
    }//GEN-LAST:event_currentCheckBoxActionPerformed

    private void tbScreeningCloseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningCloseBtnActionPerformed

        tbScreeningDialog.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningCloseBtnActionPerformed

    private void tbScreeningSaveDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningSaveDataBtnActionPerformed
        int checkStatus = 0;

        int checkPositive = 0;

        for (int j = 0; j < tbScreeningTable.getRowCount(); j++) {
            if ((!Boolean.parseBoolean(tbScreeningTable.getValueAt(j, 1).toString()) && !Boolean.parseBoolean(tbScreeningTable.getValueAt(j, 2).toString())) || (Boolean.parseBoolean(tbScreeningTable.getValueAt(j, 1).toString()) && Boolean.parseBoolean(tbScreeningTable.getValueAt(j, 2).toString()))) {
                checkStatus++;
            }

            if (Boolean.parseBoolean(tbScreeningTable.getValueAt(j, 1).toString())) {
                checkPositive++;
            }
        }
        if (checkStatus < 1) {
            if (tbScreeningTable.isEditing()) {
                tbScreeningTable.getCellEditor().stopCellEditing();
            }
            try {
                for (int i = 0; i < tbScreeningTable.getRowCount(); i++) {
                    if (Boolean.parseBoolean(tbScreeningTable.getValueAt(i, 1).toString())) {
                        java.sql.PreparedStatement pstmtScreeningData = connectDB.prepareStatement("INSERT INTO public.hp_screening_data("
                                + "            patient_no, patient_name, indicator_checked, clinic_attended, "
                                + "            remarks, emergency, gender, patient_age)"
                                + "    VALUES (?, ?, ?, ?, ?, "
                                + "            ?, ?, ?)");

                        pstmtScreeningData.setString(1, tbScreeningPatientNoTxt.getText());
                        pstmtScreeningData.setString(2, tbScreeningPatientNameTxt.getText());
                        pstmtScreeningData.setString(3, tbScreeningTable.getValueAt(i, 0).toString());
                        pstmtScreeningData.setString(4, waitingclinicscmbx.getSelectedItem().toString());
                        pstmtScreeningData.setObject(5, tbScreeningTable.getValueAt(i, 3).toString());
                        pstmtScreeningData.setBoolean(6, emergencyStatus);
                        if (maleChkbx.isSelected()) {
                            pstmtScreeningData.setString(7, "Male");
                        } else {
                            pstmtScreeningData.setString(7, "Female");
                        }
                        pstmtScreeningData.setDouble(8, agee);
                        pstmtScreeningData.execute();
                    }else{
                        java.sql.PreparedStatement pstmtScreeningData = connectDB.prepareStatement("INSERT INTO public.hp_screening_data_neg("
                                + "            patient_no, patient_name, indicator_checked, clinic_attended, "
                                + "            remarks, emergency, gender, patient_age)"
                                + "    VALUES (?, ?, ?, ?, ?, "
                                + "            ?, ?, ?)");

                        pstmtScreeningData.setString(1, tbScreeningPatientNoTxt.getText());
                        pstmtScreeningData.setString(2, tbScreeningPatientNameTxt.getText());
                        pstmtScreeningData.setString(3, tbScreeningTable.getValueAt(i, 0).toString());
                        pstmtScreeningData.setString(4, waitingclinicscmbx.getSelectedItem().toString());
                        pstmtScreeningData.setObject(5, tbScreeningTable.getValueAt(i, 3).toString());
                        pstmtScreeningData.setBoolean(6, emergencyStatus);
                        if (maleChkbx.isSelected()) {
                            pstmtScreeningData.setString(7, "Male");
                        } else {
                            pstmtScreeningData.setString(7, "Female");
                        }
                        pstmtScreeningData.setDouble(8, agee);
                        pstmtScreeningData.execute();
                    }
                }
                if (checkPositive > 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Screening data saved successfully and you MUST order at least one investigation.");
                    tbScreeningSaveInvestigationsBtn.setEnabled(true);
                    tbScreeningSaveInvestigationsResultsBtn.setEnabled(true);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Screening data saved successfully.");
                    tbScreeningClearFormBtn.doClick();
                    tbScreeningDialog.dispose();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {

            javax.swing.JOptionPane.showMessageDialog(this, "You MUST select atleast once condition for each indicator i.e. Yes or No column and only either column MUST be checked.");

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningSaveDataBtnActionPerformed

    private void tbScreeningClearFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningClearFormBtnActionPerformed

        tbScreeningAgeTxt.setText(null);
        tbScreeningPatientNameTxt.setText(null);
        tbScreeningPatientNoTxt.setText(null);
        for (int i = 0; i < tbScreeningTable.getRowCount(); i++) {
            for (int j = 0; j < tbScreeningTable.getColumnCount(); j++) {
                tbScreeningTable.setValueAt(null, i, j);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningClearFormBtnActionPerformed

    private void tbScreeningSaveInvestigationsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningSaveInvestigationsBtnActionPerformed
        if (tbScreeeningGeneExpertChkbx.isSelected() || tbScreeningSputumChkbx.isSelected() || tbScreeningChestXrayChkbx.isSelected() || screeningInvestigationsTxt.getText().length() > 5) {
            if (tbScreeeningGeneExpertChkbx.isSelected()) {
                try {
                    java.sql.PreparedStatement pstmtScreeningInvestigations = connectDB.prepareStatement("INSERT INTO public.hp_screening_procedures("
                            + "            requested_procedure, "
                            + "            patient_no, patient_name, comments)"
                            + "    VALUES (?, "
                            + "            ?, ?, ?)");
                    pstmtScreeningInvestigations.setString(1, "Gene Expert");
                    pstmtScreeningInvestigations.setString(2, tbScreeningPatientNoTxt.getText());
                    pstmtScreeningInvestigations.setString(3, tbScreeningPatientNameTxt.getText());
                    pstmtScreeningInvestigations.setString(4, screeningInvestigationsTxt.getText());
                    pstmtScreeningInvestigations.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }

            if (tbScreeningSputumChkbx.isSelected()) {
                try {
                    java.sql.PreparedStatement pstmtScreeningInvestigations = connectDB.prepareStatement("INSERT INTO public.hp_screening_procedures("
                            + "            requested_procedure, "
                            + "            patient_no, patient_name, comments)"
                            + "    VALUES (?, "
                            + "            ?, ?, ?)");
                    pstmtScreeningInvestigations.setString(1, "Sputum Smear");
                    pstmtScreeningInvestigations.setString(2, tbScreeningPatientNoTxt.getText());
                    pstmtScreeningInvestigations.setString(3, tbScreeningPatientNameTxt.getText());
                    pstmtScreeningInvestigations.setString(4, screeningInvestigationsTxt.getText());

                    pstmtScreeningInvestigations.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }

            if (tbScreeningChestXrayChkbx.isSelected()) {
                try {
                    java.sql.PreparedStatement pstmtScreeningInvestigations = connectDB.prepareStatement("INSERT INTO public.hp_screening_procedures("
                            + "            requested_procedure, "
                            + "            patient_no, patient_name, comments)"
                            + "    VALUES (?, "
                            + "            ?, ?, ?)");
                    pstmtScreeningInvestigations.setString(1, "Chest Xray");
                    pstmtScreeningInvestigations.setString(2, tbScreeningPatientNoTxt.getText());
                    pstmtScreeningInvestigations.setString(3, tbScreeningPatientNameTxt.getText());
                    pstmtScreeningInvestigations.setString(4, screeningInvestigationsTxt.getText());
                    pstmtScreeningInvestigations.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }

            javax.swing.JOptionPane.showMessageDialog(this, "Screening procedures submitted successfully");
            tbScreeningClearFormBtn.doClick();
            tbScreeningDialog.dispose();
        } else {
            if (screeningInvestigationsTxt.getText().length() > 5) {
                try {
                    java.sql.PreparedStatement pstmtScreeningInvestigations = connectDB.prepareStatement("INSERT INTO public.hp_screening_procedures("
                            + "             "
                            + "            patient_no,patient_name, comments)"
                            + "    VALUES (?, ?,"
                            + "            ?)");
                    pstmtScreeningInvestigations.setString(1, tbScreeningPatientNoTxt.getText());
                    pstmtScreeningInvestigations.setString(2, tbScreeningPatientNameTxt.getText());
                    pstmtScreeningInvestigations.setString(3, screeningInvestigationsTxt.getText());
                    pstmtScreeningInvestigations.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Please select one of the investigation options or type in some investigation details or other type of investigation as provided.");
            }
        }

// TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningSaveInvestigationsBtnActionPerformed

    private void tbScreeningSaveInvestigationsResultsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningSaveInvestigationsResultsBtnActionPerformed

        try {
            java.sql.PreparedStatement pstmtScreeningResults = connectDB.prepareStatement("UPDATE public.hp_screening_procedures"
                    + "   SET  result_time=?, "
                    + "       procedure_result= ?"
                    + " WHERE patient_no = ? AND request_time::date >= current_date - 1 AND requested_procedure ilike 'gene expert'");
            pstmtScreeningResults.setTimestamp(1, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));
            pstmtScreeningResults.setObject(2, tbScreeningGeneExpertResultCmbx.getSelectedItem());
            pstmtScreeningResults.setString(3, tbScreeningPatientNoTxt.getText());
            pstmtScreeningResults.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        try {
            java.sql.PreparedStatement pstmtScreeningResults = connectDB.prepareStatement("UPDATE public.hp_screening_procedures"
                    + "   SET  result_time=?, "
                    + "       procedure_result= ?"
                    + " WHERE patient_no = ? AND request_time::date >= current_date - 1 AND requested_procedure ilike 'Sputum Smear'");
            pstmtScreeningResults.setTimestamp(1, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));
            pstmtScreeningResults.setObject(2, tbScreeningSputumSmearResultCmbx.getSelectedItem());
            pstmtScreeningResults.setString(3, tbScreeningPatientNoTxt.getText());
            pstmtScreeningResults.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        try {
            java.sql.PreparedStatement pstmtScreeningResults = connectDB.prepareStatement("UPDATE public.hp_screening_procedures"
                    + "   SET  result_time=?, "
                    + "       procedure_result= ?"
                    + " WHERE patient_no = ? AND request_time::date >= current_date - 1 AND requested_procedure ilike 'Chest Xray'");
            pstmtScreeningResults.setTimestamp(1, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));
            pstmtScreeningResults.setObject(2, tbScreeningChestXrayResultCmbx.getSelectedItem());
            pstmtScreeningResults.setString(3, tbScreeningPatientNoTxt.getText());
            pstmtScreeningResults.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Screening results registered successfully. You can order a treatment plan.");
        tbScreeningClearFormBtn.doClick();
        tbScreeningDialog.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningSaveInvestigationsResultsBtnActionPerformed

    private void tbScreeningChestXrayChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbScreeningChestXrayChkbxActionPerformed

        if (tbScreeningChestXrayChkbx.isSelected()) {
            consultationTabbedPane.setSelectedIndex(2);
            clerkingTabbedPane.setSelectedIndex(1);
            radiologyCheck.setSelected(true);
            radiologyCheck.doClick();
            this.searchservicesTextField.setEditable(true);
            this.generalServicesOrderingPanel.setVisible(false);
            query = "X-Ray";
            this.jPanel82.setVisible(true);
            this.servicesOrderHeaderPanel.setVisible(true);
            this.jScrollPane2.setVisible(true);
            this.jScrollPane3.setVisible(false);
            this.jLabel16.setVisible(false);
            jComboBox2.setVisible(false);
            for (int k = 0; k < pharmacyTable.getRowCount(); k++) {
                for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
                    pharmacyTable.setValueAt(null, k, r);
                }
            }

            diagnosticsServicesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select request_code,request_name FROM clerking_requests_category where trim(department) ilike 'X-Ray' or trim(department) ilike 'XRay' or trim(department) ilike 'x-ray' or trim(department) ilike 'radiology' or trim(department) ilike 'imaging' order by 1 asc"));

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tbScreeningChestXrayChkbxActionPerformed

    private void internalReferralBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_internalReferralBtnActionPerformed
        if (waitingclinicscmbx.getSelectedItem() != null && internalReferralCmbx.getSelectedItem() != null) {
            if (waitingclinicscmbx.getSelectedItem().toString().toUpperCase() != internalReferralCmbx.getSelectedItem().toString().toUpperCase() && nameNoTxt.getText().length() > 0) {
                try {
                    java.sql.PreparedStatement pstmtInternalReferral = connectDB.prepareStatement("INSERT INTO public.hp_patient_visit("
                            + "            patient_no, name, ip_no, payment, visit_no, services, quantity, "
                            + "            department, amount, test, date, result, doctor_name, user_name, "
                            + "            input_date, transaction_type, nature, \"time\", comments, parameter, "
                            + "            status, age, gender, marital_status, obs_by, shift_no, cons_time, "
                            + "            obs_time, clinic, urgency, discharge, dischargedetails, patient_disability, "
                            + "            refer_source, service_start_time, samaritan_name, samaritan_tel_no, "
                            + "            nok_name, nok_tel_no, referral_type, referral_county, referral_facility)"
                            + "SELECT patient_no, name, ip_no, payment, visit_no, services, quantity, "
                            + "       department, amount, test, now()::date, result, doctor_name, user_name, "
                            + "       now(), transaction_type, nature, \"time\", comments, parameter, "
                            + "       status, age, gender, marital_status, obs_by, shift_no, cons_time, "
                            + "       obs_time, ?, urgency, discharge, dischargedetails, patient_disability, "
                            + "       refer_source, service_start_time, samaritan_name, samaritan_tel_no, "
                            + "       nok_name, nok_tel_no, referral_type, referral_county, referral_facility"
                            + "  FROM public.hp_patient_visit WHERE patient_no = ? ORDER BY input_date DESC LIMIT 1");
                    pstmtInternalReferral.setString(1, internalReferralCmbx.getSelectedItem().toString());
                    pstmtInternalReferral.setString(2, nameNoTxt.getText());
                    pstmtInternalReferral.execute();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "The patient file must be set and the referring clinic cannot be the same as referral clinic!");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "The referring clinic and referral clinic must be set!");
        }
    }//GEN-LAST:event_internalReferralBtnActionPerformed

    private void viewBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBillBtnActionPerformed
        if (paymentModeTxt.getText().equalsIgnoreCase("Scheme")) {
            com.afrisoftech.reports.OutPatientBillPdf policy = new com.afrisoftech.reports.OutPatientBillPdf();

            policy.OutPatientBillPdf(connectDB, datePicker2.getDate(), transdatePicker.getDate(), nameNoTxt.getText());

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "The patient is not using medical insurance for billing.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_viewBillBtnActionPerformed

    private void symptomsDialogTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_symptomsDialogTableMouseClicked
        if (!complainsTextPaneTxt.getText().contains(" ")) {

            System.out.println("One word ...");
//            if (symptomsDialogTable.getValueAt(symptomsDialogTable.getSelectedRow(), 0) != null) {
            System.out.println("Replace One word ..." + symptomsDialogTable.getSelectedRow());
            String selectedSymptom = symptomsDialogTable.getValueAt(symptomsDialogTable.getSelectedRow(), 0).toString();
            complainsTextPaneTxt.setText(null);
            complainsTextPaneTxt.setText(selectedSymptom);
//            } complainsTextPaneTxt.setText(null);
        } else {
            complainsTextPaneTxt.setText(complainsTextPaneTxt.getText().replaceAll(" \\S*$", "") + ", " + symptomsDialogTable.getValueAt(symptomsDialogTable.getSelectedRow(), 0));
        }
        symptomsDialog.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_symptomsDialogTableMouseClicked

    private void symptomsCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_symptomsCloseButtonActionPerformed

        symptomsDialog.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_symptomsCloseButtonActionPerformed

    private void allpatCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allpatCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allpatCheckBoxActionPerformed

    private void populateTable1(java.lang.String patient_no) {
    }

    public void run() {

        while (getList) {

            runGetListThread();

            try {

                Thread.currentThread().sleep(1000000);

            } catch (java.lang.InterruptedException IntExec) {

                javax.swing.JOptionPane.showMessageDialog(this, IntExec.getMessage(), "Interruption Error!", javax.swing.JOptionPane.ERROR_MESSAGE);

            }

            // getList = false;
        }

    }

    public void runGetListThread() {
        int l = 0;
        int j = 0;
        int i = 0;
        int r = 0;
        int n = 0;
        int patNo = 0;
        try {

            // for (int l = 0; l < listofDays.length; l++) {
            java.sql.Statement stmtTable11 = connectDB.createStatement();

            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select distinct count(hp_patient_visit.patient_no) from hp_patient_visit,hp_patient_register where   hp_patient_visit.date = (select current_timestamp(0)::date) and nature ='1' and hp_patient_visit.department = '" + this.waitingclinicscmbx.getSelectedItem().toString() + "' and hp_patient_visit.patient_no=hp_patient_register.patient_no");
            //   java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select distinct count(patient_no) from hp_patient_visit where transaction_type ilike 'reg%' and date = current_date and nature is null");

            while (rsetTable11.next()) {
                patNo = rsetTable11.getInt(1);

            }

            java.sql.Statement stmtTable1 = connectDB.createStatement();

            java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select distinct hp_patient_visit.patient_no,hp_patient_visit.date,hp_patient_visit.input_date::timestamp(0),hp_patient_visit.name,pay_mode,'false' as seen from hp_patient_visit,hp_patient_register where   hp_patient_visit.date = (select current_timestamp(0)::date) and nature ='1' and hp_patient_visit.patient_no=hp_patient_register.patient_no and hp_patient_visit.comments = '" + this.waitingclinicscmbx.getSelectedItem().toString() + "' ORDER BY input_date");
            //java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select distinct date,input_date::timestamp(0),patient_no,name,'false' as bill from hp_patient_visit where transaction_type ilike 'reg%' and date = current_date and nature is null ORDER BY input_date");

            while (rsetTable1.next()) {

                clerkingwaitingTable.setValueAt(rsetTable1.getObject(2), i, 0);
                clerkingwaitingTable.setValueAt(rsetTable1.getObject(3), i, 1);
                clerkingwaitingTable.setValueAt(rsetTable1.getObject(1), i, 2);
                clerkingwaitingTable.setValueAt(rsetTable1.getObject(4), i, 3);
                clerkingwaitingTable.setValueAt(rsetTable1.getObject(5), i, 4);

                clerkingwaitingTable.setValueAt(java.lang.Boolean.valueOf(false), i, 5);

                i++;

            }
            stmtTable1.close();
            rsetTable1.close();

            stmtTable11.close();
            rsetTable11.close();
            //}
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        String labNo = null;
        String patientNo = null;
        int p = 0;
        int q = 0;
        //  int r = 0;
        int patNo1 = 0;
        int Total1 = 0;
        int Total = 0;
        int labNo1 = 0;
        try {
            java.sql.Statement stmtTable113 = connectDB.createStatement();
            java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery("select count(distinct lab_no) from hp_lab_results where patient_no = '" + jTextField922.getText() + "' and lab_no !='null' and lab_no is not null ");

            // java.sql.ResultSet rsetTable113 = stmtTable113.executeQuery("select count(distinct lab_no) from hp_lab_results where doc_read = false  and result_shown = false AND date >='"+datePicker11.getDate().toString()+"' and lab_no !='null' and lab_no is not null and clinic ilike '"+jComboBox11.getSelectedItem().toString()+"'");
            while (rsetTable113.next()) {
                labNo1 = rsetTable113.getInt(1);
                System.out.println("Lab no is " + labNo1);
            }

            if (labNo1 > 0) {
                java.sql.Statement stmtTable112 = connectDB.createStatement();

                //java.sql.ResultSet rsetTable112 = stmtTable112.executeQuery("select distinct lab_no,patient_name from hp_lab_results where doc_read = false and result_shown = false AND date >='"+datePicker13.getDate().toString()+"' and lab_no is not null and lab_no !='null' and clinic ilike '"+jComboBox11.getSelectedItem().toString()+"' order by lab_no  ");
                java.sql.ResultSet rsetTable112 = stmtTable112.executeQuery("select distinct lab_no,patient_name from hp_lab_results where patient_no = '" + jTextField922.getText() + "' and lab_no is not null and lab_no !='null' order by lab_no  ");

                while (rsetTable112.next()) {
                    labNo = rsetTable112.getString(1);
                    patientNo = rsetTable112.getString(2);
                    System.out.println("Lab no2 is " + patientNo);
                }

                stmtTable112.close();
                rsetTable112.close();

            } else {
            }

            // for (int l = 0; l < listofDays.length; l++) {
            java.sql.Statement stmtTable11 = connectDB.createStatement();

            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select count(distinct lab_no) from hp_lab_results where patient_no = '" + jTextField922.getText() + "'");

            while (rsetTable11.next()) {
                patNo1 = rsetTable11.getInt(1);

            }

            //   jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct date,patient_no,patient_name,lab_no,doc_read from hp_lab_results where doc_read = false AND date >='"+datePicker1.getDate().toString()+"'  ORDER BY date,lab_no"));
            java.sql.Statement stmtTable1r = connectDB.createStatement();

            java.sql.ResultSet rsetTable1r = stmtTable1r.executeQuery("select distinct date,patient_no,patient_name,lab_no,false from hp_lab_results where patient_no = '" + jTextField922.getText() + "' ORDER BY date,lab_no");

            while (rsetTable1r.next()) {

                labresultsTable.setValueAt(rsetTable1r.getObject(1), p, 0);
                labresultsTable.setValueAt(rsetTable1r.getObject(2), p, 1);
                labresultsTable.setValueAt(rsetTable1r.getObject(3), p, 2);
                labresultsTable.setValueAt(rsetTable1r.getObject(4), p, 3);
                labresultsTable.setValueAt(rsetTable1r.getObject(5), p, 4);

                p++;

            }
            stmtTable1r.close();
            rsetTable1r.close();
            //}
            stmtTable113.close();
            rsetTable113.close();

            stmtTable11.close();
            rsetTable11.close();

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        try {

            // for (int l = 0; l < listofDays.length; l++) {
            java.sql.Statement stmtTable11 = connectDB.createStatement();

            java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select count(distinct xray_no) from hp_xray_results where patient_no = '" + jTextField922.getText() + "' AND xray_no ILIKE 'X%'");

            while (rsetTable11.next()) {
                patNo1 = rsetTable11.getInt(1);

            }

            //   jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct date,patient_no,patient_name,lab_no,doc_read from hp_lab_results where doc_read = false AND date >='"+datePicker1.getDate().toString()+"'  ORDER BY date,lab_no"));
            java.sql.Statement stmtTable1r = connectDB.createStatement();

            java.sql.ResultSet rsetTable1r = stmtTable1r.executeQuery("select distinct date,patient_no,patient_name,xray_no,false from hp_xray_results where patient_no = '" + jTextField922.getText() + "' AND xray_no ILIKE 'X%' ORDER BY date");

            while (rsetTable1r.next()) {

                radiologyResultsTbl.setValueAt(rsetTable1r.getObject(1), p, 0);
                radiologyResultsTbl.setValueAt(rsetTable1r.getObject(2), p, 1);
                radiologyResultsTbl.setValueAt(rsetTable1r.getObject(3), p, 2);
                radiologyResultsTbl.setValueAt(rsetTable1r.getObject(4), p, 3);
                radiologyResultsTbl.setValueAt(rsetTable1r.getObject(5), p, 4);

                p++;

            }
            stmtTable1r.close();
            rsetTable1r.close();
            //}
            stmtTable11.close();
            rsetTable11.close();

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

        }

        if (patNo1 > 5) {

            this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");
            this.jLabel19.setForeground(new java.awt.Color(255, 0, 51));
        } else {
            this.jLabel19.setText("You Have '" + patNo1 + "' Results  Unread");

            this.jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        }

        //for(patNo=0;patNo>i;patNo++){
        //  if(patNo>=patNo+1){
        // if(labNo.equalsIgnoreCase(null)){
        // }else{
        /* 
         if(labNo1>0){
         java.awt.Toolkit.getDefaultToolkit().beep();
         //  javax.swing.JOptionPane.showMessageDialog(this, "Result No. '"+labNo+"' for '"+patientNo+"' are out","Information Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        
         try {
        
         java.sql.PreparedStatement pstmt46 = connectDB.prepareStatement("UPDATE hp_lab_results SET result_shown = true where lab_no = '"+labNo+"'");
         pstmt46.executeUpdate();
        
        
         } catch(java.sql.SQLException sqlExec) {
        
         sqlExec.printStackTrace();
        
         javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
        
         }
         }else{
        
         }
         */
    }

    /* private void populateTable14(java.lang.String patient_no) {
    
    
     }
    
     public void run() {
    
     while(getList1) {
    
     runGetListThread1();
    
     try {
    
     Thread.currentThread().sleep(100000000);
    
     } catch(java.lang.InterruptedException IntExec){
    
     javax.swing.JOptionPane.showMessageDialog(this, IntExec.getMessage(), "Interruption Error!", javax.swing.JOptionPane.ERROR_MESSAGE);
    
     }
    
     // getList = false;
    
     }
    
     }
    
     /* public void runGetListThread1() {
    
     int j = 0;
     int i = 0;
     int n = 0;
    
     try {
    
     // for (int l = 0; l < listofDays.length; l++) {
    
     java.sql.Statement stmtTable1 = connectDB.createStatement();
    
     java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select trans_date,patient_no,patient_name,payment_mode,service,quantity,amount,inv_no,doctor,'false' as bill,bed_no,time_due from pb_doctors_request where revenue_code ilike 'lab%' and paid = false AND collected = false and trans_date>=current_date -2 ORDER BY trans_date asc");
    
    
     while (rsetTable1.next()) {
    
     jTable14.setValueAt(rsetTable1.getObject(1), i, 0);
     jTable14.setValueAt(rsetTable1.getObject(2), i, 1);
     jTable14.setValueAt(rsetTable1.getObject(3), i, 2);
     jTable14.setValueAt(rsetTable1.getObject(4), i, 3);
     jTable14.setValueAt(rsetTable1.getObject(5), i, 4);
     jTable14.setValueAt(rsetTable1.getObject(6), i, 5);
     jTable14.setValueAt(rsetTable1.getObject(7), i, 6);
     jTable14.setValueAt(rsetTable1.getObject(8), i, 7);
     jTable14.setValueAt(rsetTable1.getObject(9), i, 8);
     jTable14.setValueAt(java.lang.Boolean.valueOf(false), i, 9);
     jTable14.setValueAt(rsetTable1.getObject(11), i, 10);
     jTable14.setValueAt(rsetTable1.getObject(12), i, 11);
    
    
     i++;
    
     }
    
     //}
    
    
    
    
     } catch(java.sql.SQLException sqlExec) {
    
     sqlExec.printStackTrace();
    
     javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
    
     }
    
    
    
     }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ICD10CheckBox;
    private javax.swing.JTextField accTextField114;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JLabel ageGenderLbl;
    private javax.swing.JTextField ageGenderTxt;
    private javax.swing.JTextField ageTxt;
    private javax.swing.JCheckBox allpatCheckBox;
    private javax.swing.JCheckBox attendjCheckBox53;
    private javax.swing.JButton back2LabListingBrn;
    private javax.swing.JRadioButton bedreRadioButton1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup15;
    private javax.swing.ButtonGroup buttonGroup16;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JTextField categoryTextField19;
    private javax.swing.JPanel clerkingPanel;
    private javax.swing.JButton clerkingSavingbtn;
    private javax.swing.JTabbedPane clerkingTabbedPane;
    private javax.swing.JScrollPane clerkingwaitingJscrl;
    private javax.swing.JTable clerkingwaitingTable;
    private javax.swing.JComboBox clinicComboBox;
    private javax.swing.JEditorPane clinicalExamineditorTxt;
    private javax.swing.JTextPane clinicalsummjTextPane14;
    private javax.swing.JTextField companyTextField23;
    private javax.swing.JDialog complainsDialog;
    private javax.swing.JTextPane complainsTextPaneTxt;
    private javax.swing.JTable complainsdialogtable;
    private javax.swing.JTextField complainstxt;
    private javax.swing.JTextField consultanjTextField241;
    private javax.swing.JTabbedPane consultationTabbedPane;
    private javax.swing.JRadioButton convalesceRadioButton2;
    private javax.swing.JSplitPane cpoeSplitPane;
    private javax.swing.JCheckBox currentCheckBox;
    private com.afrisoftech.lib.DatePicker datePicker12;
    private com.afrisoftech.lib.DatePicker datePicker14;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JTable diagnosisTable;
    private javax.swing.JPanel diagnosispane;
    private javax.swing.JScrollPane diagnosticsScrollPane;
    private javax.swing.JTable diagnosticsServicesTable;
    private javax.swing.JPanel diseasesSearchPanel;
    private javax.swing.JButton displayLaboratoryResultsPDFBtn;
    private javax.swing.JPanel doctorsCPOEPanel;
    private javax.swing.JTextField doctorsTextField81;
    private javax.swing.JPanel drawingPanel;
    private javax.swing.JCheckBox driveCheckBox82;
    private javax.swing.JTextField emergencyTextField;
    private javax.swing.JTable endocrinehistoryTable;
    private javax.swing.JTextField esnursejTextField1213;
    private javax.swing.JPanel examinationpane;
    private javax.swing.JTextField explanationTextField94;
    private javax.swing.JCheckBox femaleChkbx;
    private javax.swing.JTable firstpageHistoryTable;
    private javax.swing.JCheckBox generalCheckBox;
    private javax.swing.JPanel generalServicesOrderingPanel;
    public static javax.swing.JPanel globalpane;
    private javax.swing.JTable gynaehistoryTable;
    private javax.swing.JCheckBox heavymanCheckBox72;
    private javax.swing.JPanel icd10CodingPanel;
    private javax.swing.JDialog icd10SearchDialog;
    private javax.swing.JTable icd10SearchTable;
    private javax.swing.JTextField icd10SearchTxt;
    private javax.swing.JPanel illustrationButtonPanel;
    private javax.swing.JPanel illustrationPanel;
    private javax.swing.JCheckBox inpatientCheckBox;
    private javax.swing.JTextField insttransjTextField1711;
    private javax.swing.JButton internalReferralBtn;
    private javax.swing.JComboBox<String> internalReferralCmbx;
    private javax.swing.JLabel internalReferralLbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton121;
    private javax.swing.JButton jButton122;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton41;
    public javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton5211;
    private javax.swing.JButton jButton5212;
    private javax.swing.JButton jButton5213;
    private javax.swing.JButton jButton5214;
    private javax.swing.JButton jButton522;
    private javax.swing.JButton jButton541;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton81;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButton91;
    private javax.swing.JButton jButton911;
    private javax.swing.JButton jButton912;
    private javax.swing.JButton jButton92;
    private javax.swing.JButton jButton93;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel11311;
    private javax.swing.JLabel jLabel1133;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel2311;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel612;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel621;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel812;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel911;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel9211;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JPanel jPanel212;
    private javax.swing.JPanel jPanel213;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel231;
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
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel4111;
    private javax.swing.JPanel jPanel413;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel421;
    private javax.swing.JPanel jPanel422;
    private javax.swing.JPanel jPanel423;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel811;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane131;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane161;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JDialog jSearchDialog;
    private javax.swing.JDialog jSearchDialog11;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JDialog jSearchDialog211;
    private javax.swing.JDialog jSearchDialog212;
    private javax.swing.JDialog jSearchDialog213;
    private javax.swing.JDialog jSearchDialog214;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JPanel jSearchPanel211;
    private javax.swing.JPanel jSearchPanel212;
    private javax.swing.JPanel jSearchPanel213;
    private javax.swing.JPanel jSearchPanel214;
    private javax.swing.JPanel jSearchPanel3;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JScrollPane jSearchScrollPane211;
    private javax.swing.JScrollPane jSearchScrollPane212;
    private javax.swing.JScrollPane jSearchScrollPane213;
    private javax.swing.JScrollPane jSearchScrollPane214;
    private javax.swing.JScrollPane jSearchScrollPane3;
    private javax.swing.JScrollPane jSearchScrollPane4;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JTable jSearchTable211;
    private javax.swing.JTable jSearchTable212;
    private javax.swing.JTable jSearchTable213;
    private javax.swing.JTable jSearchTable214;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField1111;
    private javax.swing.JTextField jTextField112;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField11311;
    private javax.swing.JTextField jTextField11312;
    private javax.swing.JTextField jTextField11313;
    private javax.swing.JTextField jTextField11314;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField911;
    private javax.swing.JTextField jTextField9111;
    private javax.swing.JTextField jTextField912;
    private javax.swing.JTextField jTextField921;
    private javax.swing.JTextField jTextField922;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JScrollPane labResultsDisplayDetailsJscrl;
    private javax.swing.JPanel labResultsDisplayPanel;
    private javax.swing.JPanel labResultsListingPanel;
    private javax.swing.JTabbedPane labResultsTabbenPane;
    private javax.swing.JTextField labTextField;
    private com.afrisoftech.lib.DatePicker labdatePicker;
    private javax.swing.JCheckBox lablatoryCheck;
    private javax.swing.JScrollPane laboratoryDisplayJscrl;
    private javax.swing.JTextField laboratoryProcedureDescriptionTxt;
    private javax.swing.JLabel laboratoryProcedureNameLbl;
    private javax.swing.JEditorPane laboratoryProcedureResultDetailsTxt;
    private javax.swing.JPanel laboratoryResultsBodyPanel;
    private javax.swing.JPanel laboratoryResultsButtonPanel;
    private javax.swing.JLabel laboratoryResultsDateLbl;
    private com.afrisoftech.lib.DatePicker laboratoryResultsDatePicker;
    private javax.swing.JTable laboratoryResultsDisplayTbl;
    private javax.swing.JPanel laboratoryResultsHeaderPanel;
    private javax.swing.JLabel laboratoryResultsPatientNameLbl;
    private javax.swing.JTextField laboratoryResultsPatientNameTxt;
    private javax.swing.JLabel laboratoryResultsPatientNumberLbl;
    private javax.swing.JTextField laboratoryResultsPatientNumberTxt;
    private javax.swing.JLabel laboratoryResultsTimeLbl;
    private javax.swing.JTextField laboratoryResultsTimeTxt;
    private javax.swing.JButton labresultsButton;
    private javax.swing.JTable labresultsTable;
    private javax.swing.JRadioButton lightdutiesRadioButton3;
    private javax.swing.JButton loadALLpatientsbtn;
    private javax.swing.JButton loadTRIAGEDpatientsbtn;
    private javax.swing.JCheckBox maleChkbx;
    private javax.swing.JTable medicationhistoryTable;
    private javax.swing.JTextField memberjTextField41;
    private javax.swing.JTextField nameNoTxt;
    private javax.swing.JTextField namejTextField18;
    private javax.swing.JTable neurohistoryTable;
    private javax.swing.JCheckBox noICD10CheckBox;
    private javax.swing.JTextField noofsickjTextField31;
    private javax.swing.JTextField normalTextField;
    private javax.swing.JTextField notesTextField;
    private javax.swing.JTextField othercategoryjTextField24;
    private javax.swing.JTextField othernamejTextField115;
    private javax.swing.JTextField otherpaymentjTextField211;
    private javax.swing.JCheckBox othersCheckBox91;
    private javax.swing.JRadioButton othersRadioButton4;
    private javax.swing.JTextField otherspatnojTextField923;
    private javax.swing.JCheckBox outpatientCheckBox;
    private javax.swing.JTextField patientNameTxt;
    private javax.swing.JTextField patnoTextField;
    private javax.swing.JTextField paymentModeTxt;
    private javax.swing.JTextField paymentjTextField20;
    private javax.swing.JCheckBox pharmacyCheckBox;
    private javax.swing.JPanel pharmacyRequestsPanel;
    private javax.swing.JScrollPane pharmacyScrollPane;
    private javax.swing.JDialog pharmacySearchDialog;
    private javax.swing.JPanel pharmacySearchPanel;
    private javax.swing.JScrollPane pharmacySearchScrollPane;
    private javax.swing.JTable pharmacySearchTable;
    private javax.swing.JTextField pharmacySearchTxt;
    private javax.swing.JTable pharmacyTable;
    private javax.swing.JButton printgeneralButton;
    private javax.swing.JCheckBox printgeneralCheckBox;
    private javax.swing.JButton printlabButton;
    private javax.swing.JCheckBox printlablatoryCheck;
    private javax.swing.JCheckBox printpharmacyCheckBox;
    private javax.swing.JButton printprescriptionsButton;
    private javax.swing.JCheckBox printradiologyCheck;
    private javax.swing.JButton printxrayButton;
    private javax.swing.JTextField provisiodiagjTextField12111;
    private javax.swing.JTextField provisionalDiagnosistxt;
    private javax.swing.JCheckBox radiologyCheck;
    private com.afrisoftech.lib.DatePicker radiologyResultsDatePicker;
    private javax.swing.JTable radiologyResultsTbl;
    private javax.swing.ButtonGroup reaadlabbuttonGroup;
    private javax.swing.JCheckBox readCheckBox;
    private javax.swing.JCheckBox referalCheckbox;
    private javax.swing.JButton requestbtn;
    private javax.swing.JCheckBox requestsCheckBox;
    private javax.swing.JTable requestsViewTable;
    private javax.swing.JPanel requestspanel;
    private com.afrisoftech.lib.DatePicker resumptiondatePicker121;
    private javax.swing.JTable rxplanTable;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField schemeNameTxt;
    private javax.swing.JTextField schemeTxt;
    private javax.swing.JCheckBox schooljCheckBox62;
    private javax.swing.JPanel screeningActionDialog;
    private javax.swing.JPanel screeningBodyPanel;
    private com.afrisoftech.lib.DatePicker screeningDatePicker;
    private javax.swing.JPanel screeningHeaderPanel;
    private javax.swing.JEditorPane screeningInvestigationsTxt;
    private javax.swing.JEditorPane screeningResultsTxt;
    private javax.swing.JButton searchButton111;
    private javax.swing.JButton searchButton12;
    private javax.swing.JButton searchButton2;
    private javax.swing.JButton searchButton21;
    private javax.swing.JButton searchButton22;
    private javax.swing.JButton searchButton23;
    private javax.swing.JButton searchButton3;
    private javax.swing.JButton searchButton4;
    private javax.swing.JCheckBox searchCheckBox42;
    private javax.swing.JCheckBox searchCheckBox51;
    private javax.swing.JTextField searchDiseasesTxt;
    private javax.swing.JPanel searchServicesMainPanel;
    private javax.swing.JCheckBox searchnamecheckbx;
    private javax.swing.JCheckBox searchnocheckbx;
    private javax.swing.JTextField searchpatienttxtfld;
    private javax.swing.JTextField searchservicesTextField;
    private javax.swing.JTextField seenTextField;
    private javax.swing.JPanel servicesOrderHeaderPanel;
    private javax.swing.JTextField sheetjTextField101;
    private javax.swing.JLabel spacerLabel;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JLabel spacerlabelExtra;
    private javax.swing.JLabel spacerlabels;
    private com.afrisoftech.lib.DatePicker startdatePicker111;
    private javax.swing.JTable surgeryhistoryTable;
    private javax.swing.JButton symptomsCloseButton;
    private javax.swing.JDialog symptomsDialog;
    private javax.swing.JTable symptomsDialogTable;
    private javax.swing.JPanel symptomsSearchPanel;
    private javax.swing.JScrollPane symptomsSearchScrollPane;
    private javax.swing.JTable symptomsTable;
    private javax.swing.JTabbedPane tabbedpaneAll;
    private javax.swing.JCheckBox tbScreeeningGeneExpertChkbx;
    private javax.swing.JTextField tbScreeningAgeTxt;
    private javax.swing.JCheckBox tbScreeningChestXrayChkbx;
    private javax.swing.JComboBox tbScreeningChestXrayResultCmbx;
    public static javax.swing.JButton tbScreeningClearFormBtn;
    private javax.swing.JButton tbScreeningCloseBtn;
    private javax.swing.JDialog tbScreeningDialog;
    private javax.swing.JComboBox tbScreeningGeneExpertResultCmbx;
    private javax.swing.JTextField tbScreeningPatientNameTxt;
    private javax.swing.JTextField tbScreeningPatientNoTxt;
    private javax.swing.JPanel tbScreeningResultsPanel;
    private javax.swing.JButton tbScreeningSaveDataBtn;
    private javax.swing.JButton tbScreeningSaveInvestigationsBtn;
    private javax.swing.JButton tbScreeningSaveInvestigationsResultsBtn;
    private javax.swing.JScrollPane tbScreeningScrollPane;
    private javax.swing.JLabel tbScreeningSpacerLbl;
    private javax.swing.JCheckBox tbScreeningSputumChkbx;
    private javax.swing.JComboBox tbScreeningSputumSmearResultCmbx;
    private javax.swing.JTable tbScreeningTable;
    private com.afrisoftech.lib.DatePicker transdatePicker;
    private javax.swing.JTextField transreasonjTextField811;
    private javax.swing.JTextField transtechnicianjTextField181;
    private javax.swing.JTextField triageTextField;
    private javax.swing.JComboBox urgencyComboBox;
    private javax.swing.JButton viewBillBtn;
    private javax.swing.JLabel visitDateLbl;
    private javax.swing.JTable vitalsignstable;
    private javax.swing.JComboBox waitingclinicscmbx;
    private javax.swing.JTextField x_rayresultsTextField;
    // End of variables declaration//GEN-END:variables

    private void saverequest(String Sex) {
        try {
            java.sql.Statement ps11 = connectDB.createStatement(), stm12 = connectDB.createStatement(), ps = connectDB.createStatement();
            java.sql.ResultSet rst11 = null, rse12 = null, rst = null;
            String billNo = null, user = null, rank = null;

            rse12 = stm12.executeQuery("select comments,date from hp_patient_visit where patient_no ='" + jTextField922.getText() + "' ORDER BY date DESC LIMIT 1");

            while (rse12.next()) {
                rank = rse12.getString(1);
            }
            int transNo = 0;
            rst = ps.executeQuery("select nextval('transaction_no_seq')");

            while (rst.next()) {
                rst.getObject(1).toString();

                transNo = rst.getInt(1);
            }
            if (this.outpatientCheckBox.isSelected()) {

                rst11 = ps11.executeQuery("select 'O'||nextval('billing_no_seq'),current_user");
                while (rst11.next()) {
                    rst11.getObject(1).toString();
                    billNo = rst11.getObject(1).toString();
                    user = rst11.getObject(2).toString();
                    this.jTextField912.setText(billNo);
                }
            } else {

                rst11 = ps11.executeQuery("select 'I'||nextval('billing_no_seq'),current_user");
                while (rst11.next()) {
                    rst11.getObject(1).toString();

                    billNo = rst11.getObject(1).toString();
                    user = rst11.getObject(2).toString();
                    this.jTextField912.setText(billNo);
                }

            }
            int k = 0;
            if (lablatoryCheck.isSelected() == true || radiologyCheck.isSelected() == true) {
                k = jTable13.getRowCount();
            } else if (generalCheckBox.isSelected() == true) {
                k = jTable14.getRowCount();
            }

            String patientPaymode = this.paymentModeTxt.getText();

            for (int i = 0; i < k; i++) {
                java.sql.Statement stm121q = connectDB.createStatement();
                java.sql.ResultSet rse121 = null;
                patientPaymode = this.paymentModeTxt.getText();
                if (generalCheckBox.isSelected() == true) {
                    if (jTable14.getValueAt(i, 0) != null) {

                        if (patientPaymode.contains("Scheme") && outpatientCheckBox.isSelected()) {
                            boolean serviceSchemeExclusion = com.afrisoftech.lib.GLCodesFactory.getServiceSchemeExclusionStatus(connectDB, schemeNameTxt.getText(), jTable14.getValueAt(i, 0).toString(), jTable14.getValueAt(i, 4).toString());
                            if (serviceSchemeExclusion) {
                                patientPaymode = com.afrisoftech.lib.GLCodesFactory.getDefaultPaymode(connectDB);
                            }
                        }

                        rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + jTable14.getValueAt(i, 4).toString() + "'");

                        while (rse121.next()) {

                            System.out.println("am executing this now lab check and radio check false");
                            String glAcc = rse121.getObject(1).toString();
                            PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)");

                            pstmt2.setString(1, nameNoTxt.getText());

                            if (patientNameTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(2, patientNameTxt.getText());
                            }
                            // pstmt2.setString(2,jTextField1.getText());
                            pstmt2.setString(3, patientPaymode);
                            if (jTextField8.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(4, jTextField8.getText());
                            }
                            //  pstmt2.setString(4,jTextField8.getText());
                            pstmt2.setString(5, jTable14.getValueAt(i, 0).toString());
                            pstmt2.setDouble(6, java.lang.Double.valueOf(jTable14.getValueAt(i, 1).toString()));
                            pstmt2.setDouble(7, java.lang.Double.valueOf(jTable14.getValueAt(i, 3).toString()));
                            pstmt2.setObject(8, jTable14.getValueAt(i, 4).toString());
                            pstmt2.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt2.setString(10, billNo);
                            if (patientPaymode.contains("Scheme") || inpatientCheckBox.isSelected()) {
                                pstmt2.setBoolean(12, true);
                            } else {

                                pstmt2.setBoolean(12, false);
                            }
                            pstmt2.setString(11, user);
                            pstmt2.setString(13, glAcc);
                            pstmt2.setString(18, billNo);
                            pstmt2.setString(15, "GENERAL");
                            pstmt2.setBoolean(16, false);
                            pstmt2.setBoolean(17, false);
                            pstmt2.setString(14, visitID);
                            pstmt2.setString(19, datePicker12.getDate().toString());
                            pstmt2.setTimestamp(20, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                            pstmt2.setString(21, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(22, rank);
                            pstmt2.setString(23, ageTxt.getText().toString());
                            pstmt2.setString(24, notesTextField.getText().toString());
                            pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                            pstmt2.setString(27, Sex);
                            pstmt2.setString(28, "DOC POSTING");
                            pstmt2.setString(29, billNo + "-" + i);
                            pstmt2.executeUpdate();

                            if (jTable14.getValueAt(i, 0).toString().startsWith("plaster") || jTable14.getValueAt(i, 0).toString().startsWith("POP") || jTable14.getValueAt(i, 0).toString().startsWith("p.o.p")) {
                            } else {

                                if (!patientPaymode.startsWith("Scheme") && outpatientCheckBox.isSelected()) {
                                    PreparedStatement pstmt2f = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?)");

                                    pstmt2f.setString(1, nameNoTxt.getText());
                                    pstmt2f.setString(2, patientNameTxt.getText());
                                    pstmt2f.setString(3, patientPaymode);
                                    pstmt2f.setString(4, jTextField22.getText());
                                    pstmt2f.setString(5, jTable14.getValueAt(i, 0).toString());
                                    pstmt2f.setDouble(6, java.lang.Double.valueOf(jTable14.getValueAt(i, 1).toString()));
                                    pstmt2f.setDouble(7, java.lang.Double.valueOf(jTable14.getValueAt(i, 3).toString()));
                                    pstmt2f.setObject(8, jTable14.getValueAt(i, 4).toString());
                                    pstmt2f.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",datePicker1.getDate())));
                                    pstmt2f.setString(10, billNo);
                                    pstmt2f.setString(11, user);
//                                    if (paymentModetxt.getText().startsWith("Scheme")) {
//                                        pstmt2f.setBoolean(12, true);
//                                    } else {
//                                        pstmt2f.setBoolean(12, false);
//                                    }
                                    pstmt2f.setBoolean(12, false);
                                    pstmt2f.setString(13, glAcc);
                                    pstmt2f.setString(14, visitID);
                                    pstmt2f.setString(15, "");
                                    pstmt2f.setBoolean(16, true);
                                    pstmt2f.executeUpdate();
                                } else if (patientPaymode.contains("Scheme") || inpatientCheckBox.isSelected()) {
                                    //in patient and outpatient scheme
                                    ////************************************
                                    ////************************************
////insert to patient card

                                    String payMode = null;
                                    String patientAcc = null;
                                    String cardNo = null;
                                    String AccDesc = null;
                                    String scheme = patientPaymode;
                                    String cardName = null;
                                    String isurer = null;
                                    String expDate = null;
                                    String staffNo = null;

                                    java.sql.Statement stm1 = connectDB.createStatement();
                                    java.sql.ResultSet rse1 = stm1.executeQuery("select pay_mode,account_no,description,payer,payer,expiry_date,account_no from hp_inpatient_register where patient_no ='" + nameNoTxt.getText() + "'");
                                    while (rse1.next()) {
                                        cardNo = dbObject.getDBObject(rse1.getObject(2), "-");
                                        scheme = dbObject.getDBObject(rse1.getObject(3), "-");
                                        cardName = dbObject.getDBObject(rse1.getObject(4), "-");
                                        isurer = dbObject.getDBObject(rse1.getObject(5), "-");
                                        expDate = rse1.getString(6);
                                        staffNo = dbObject.getDBObject(rse1.getObject(7), "-");

                                    }

                                    java.sql.Statement stm12p = connectDB.createStatement();
                                    java.sql.ResultSet rse12p = stm12p.executeQuery("select code,activity from pb_activity where activity_category ='PR'");
                                    while (rse12p.next()) {

                                        patientAcc = rse12p.getObject(1).toString();
                                        AccDesc = rse12p.getObject(2).toString();
                                    }

                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, nameNoTxt.getText());
                                    pstmt.setObject(2, jTable14.getValueAt(i, 0).toString());
                                    pstmt.setString(3, jTextField22.getText());
                                    pstmt.setString(4, patientPaymode);
                                    pstmt.setString(5, String.valueOf(transNo));
                                    pstmt.setString(7, schemeNameTxt.getText());
                                    pstmt.setString(6, cardNo);
                                    pstmt.setString(8, cardName);
                                    pstmt.setString(9, isurer);
                                    pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                    pstmt.setString(11, "");
                                    pstmt.setDouble(12, java.lang.Double.valueOf(jTable14.getValueAt(i, 3).toString()));
                                    pstmt.setDouble(13, 0.00);
                                    pstmt.setDate(14, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt.setObject(15, patientAcc);
                                    pstmt.setString(16, glAcc);
                                    pstmt.setDouble(17, java.lang.Double.valueOf(jTable14.getValueAt(i, 1).toString()));
                                    pstmt.setObject(18, staffNo);
                                    pstmt.setBoolean(19, false);
                                    pstmt.setString(20, "Billing");
                                    pstmt.setBoolean(21, false);
                                    pstmt.setString(22, AccDesc);
                                    pstmt.setObject(23, visitID);
                                    pstmt.setString(24, user);
                                    pstmt.setString(25, billNo);
                                    if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt.setString(26, "OP");
                                    } else if (this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt.setString(26, "IP");
                                    }
                                    pstmt.setTimestamp(27, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                    pstmt.setObject(28, visitID);
                                    pstmt.executeUpdate();

                                    java.sql.PreparedStatement pstmt24 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt24.setObject(1, jTable14.getValueAt(i, 4).toString());
                                    pstmt24.setString(2, glAcc);
                                    pstmt24.setString(3, nameNoTxt.getText());
                                    pstmt24.setString(4, patientNameTxt.getText());
                                    pstmt24.setString(5, "");
                                    pstmt24.setString(6, cardNo);
                                    pstmt24.setString(7, cardName);
                                    if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt24.setString(8, "OP");
                                    } else if (this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt24.setString(8, "IP");
                                    }
                                    pstmt24.setString(9, jTextField9111.getText());
                                    pstmt24.setObject(10, patientPaymode);
                                    pstmt24.setString(11, "");
                                    pstmt24.setString(12, "");
                                    pstmt24.setString(13, "");
                                    pstmt24.setString(14, jTable14.getValueAt(i, 0).toString());
                                    pstmt24.setString(15, "Billing");
                                    pstmt24.setDouble(16, 0.00);
                                    pstmt24.setDouble(17, java.lang.Double.valueOf(jTable14.getValueAt(i, 3).toString()));
                                    pstmt24.setDate(18, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                                    pstmt24.setObject(19, transNo);
                                    pstmt24.setBoolean(20, false);
                                    pstmt24.setBoolean(21, false);
                                    pstmt24.setBoolean(22, false);
                                    pstmt24.setString(23, user);
                                    pstmt24.executeUpdate();

                                    //in patient and outpatient scheme
                                    ////************************************
                                    ////************************************
                                    ////************************************
                                }
                            }
                        }

                    }
                    //end of general services
                } else if (lablatoryCheck.isSelected() || radiologyCheck.isSelected()) {
//                    float hasHistory = 0;
//                    java.sql.Statement stm121b = connectDB.createStatement();
//                    java.sql.ResultSet rset24bx = stm121b.executeQuery("SELECT COUNT(patient_no) FROM hp_clinical_results WHERE patient_no = '" + nameNoTxt.getText() + "' AND typeof_test IS NOT NULL AND comments IS NOT NULL AND date = '" + transdatePicker.getDate() + "'");
//                    while (rset24bx.next()) {
//                        hasHistory = rset24bx.getFloat(1);
//                    }
//
//                    if (hasHistory > 0) {
                    if (jTable13.getValueAt(i, 0) == Boolean.TRUE) {

                        if (patientPaymode.contains("Scheme") && outpatientCheckBox.isSelected()) {
                            boolean serviceSchemeExclusion = com.afrisoftech.lib.GLCodesFactory.getServiceSchemeExclusionStatus(connectDB, schemeNameTxt.getText(), jTable13.getValueAt(i, 1).toString(), jTable13.getValueAt(i, 5).toString());
                            if (serviceSchemeExclusion) {
                                patientPaymode = com.afrisoftech.lib.GLCodesFactory.getDefaultPaymode(connectDB);
                            }
                        }

                        rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + jTable13.getValueAt(i, 5).toString() + "'");

                        while (rse121.next()) {

                            System.out.println("am executing this now lab check and radio check false");
                            String glAcc = rse121.getObject(1).toString();
                            PreparedStatement pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)");

                            pstmt2.setString(1, nameNoTxt.getText());

                            if (patientNameTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(2, patientNameTxt.getText());
                            }
                            // pstmt2.setString(2,jTextField1.getText());
                            pstmt2.setString(3, patientPaymode);
                            if (jTextField8.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(4, jTextField8.getText());
                            }
                            //  pstmt2.setString(4,jTextField8.getText());
                            pstmt2.setString(5, jTable13.getValueAt(i, 1).toString());
                            pstmt2.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 2).toString()));
                            pstmt2.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                            pstmt2.setObject(8, jTable13.getValueAt(i, 5).toString());
                            pstmt2.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt2.setString(10, billNo);
                            if (patientPaymode.startsWith("Scheme")) {
                                pstmt2.setBoolean(12, true);
                            } else {

                                pstmt2.setBoolean(12, false);
                            }
                            pstmt2.setString(11, user);
                            pstmt2.setString(13, glAcc);
                            pstmt2.setString(18, billNo);
                            if (lablatoryCheck.isSelected() == true) {
                                pstmt2.setString(15, "LAB");
                            } else if (radiologyCheck.isSelected() == true) {
                                pstmt2.setString(15, "X-RAY");
                            }

                            pstmt2.setBoolean(16, false);
                            pstmt2.setBoolean(17, false);
                            pstmt2.setString(14, visitID);
                            pstmt2.setString(19, datePicker12.getDate().toString());
                            pstmt2.setTimestamp(20, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                            pstmt2.setString(21, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(22, rank);
                            pstmt2.setString(23, ageTxt.getText().toString());
                            pstmt2.setString(24, notesTextField.getText().toString());
                            pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                            if (lablatoryCheck.isSelected() == Boolean.TRUE) {
                                if (this.jTable13.getValueAt(i, 6) != null) {
                                    pstmt2.setString(26, jTable13.getValueAt(i, 6).toString());
                                }
                            } else {
                                pstmt2.setString(26, "");
                            }

                            pstmt2.setString(27, Sex);
                            pstmt2.setString(28, "DOC POSTING");
                            pstmt2.setString(29, billNo + "-" + i);
                            pstmt2.executeUpdate();

                            if (jTable13.getValueAt(i, 1).toString().startsWith("plaster") || jTable13.getValueAt(i, 1).toString().startsWith("POP") || jTable13.getValueAt(i, 1).toString().startsWith("p.o.p")) {
                            } else {
                                if (!patientPaymode.startsWith("Scheme") && outpatientCheckBox.isSelected()) {
                                    PreparedStatement pstmt2f = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?)");

                                    pstmt2f.setString(1, nameNoTxt.getText());
                                    pstmt2f.setString(2, patientNameTxt.getText());
                                    pstmt2f.setString(3, patientPaymode);
                                    pstmt2f.setString(4, jTextField22.getText());
                                    pstmt2f.setString(5, jTable13.getValueAt(i, 1).toString());
                                    pstmt2f.setDouble(6, java.lang.Double.valueOf(jTable13.getValueAt(i, 2).toString()));
                                    pstmt2f.setDouble(7, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                                    pstmt2f.setObject(8, jTable13.getValueAt(i, 5).toString());
                                    pstmt2f.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB)); //SQLDateFormat.getSQLDate(datePicker12.getDate()));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",datePicker1.getDate())));
                                    pstmt2f.setString(10, billNo);
                                    pstmt2f.setString(11, user);
//                                    if (paymentModetxt.getText().startsWith("Scheme") || inpatientCheckBox.isSelected() == Boolean.TRUE) {
//                                        pstmt2f.setBoolean(12, true);
//                                    } else if (paymentModetxt.getText().startsWith("Cash") && outpatientCheckBox.isSelected() == Boolean.TRUE) {
//                                        pstmt2f.setBoolean(12, true);
//                                    }
                                    pstmt2f.setBoolean(12, false);
                                    pstmt2f.setString(13, glAcc);
                                    pstmt2f.setString(14, visitID);
                                    pstmt2f.setString(15, "");
                                    pstmt2f.setBoolean(16, false);
                                    pstmt2f.executeUpdate();
                                    /*
                                     org.apache.commons.httpclient.methods.PostMethod postMethod = new org.apache.commons.httpclient.methods.PostMethod("");
                                     org.apache.commons.httpclient.NameValuePair[] data = {
                                     new org.apache.commons.httpclient.NameValuePair("patient_no", nameNoTxt.getText()),
                                     new org.apache.commons.httpclient.NameValuePair("patient_name", patientNametxt.getText()),
                                     new org.apache.commons.httpclient.NameValuePair("bill_no", billNo),
                                     new org.apache.commons.httpclient.NameValuePair("service", "bloggs"),
                                     new org.apache.commons.httpclient.NameValuePair("revenue_code", "bloggs"),
                                     new org.apache.commons.httpclient.NameValuePair("service_fee", "bloggs")
                                     };
                                     postMethod.setRequestBody(data);
                                     postMethod.execute(new org.apache.commons.httpclient.HttpState(), new org.apache.commons.httpclient.HttpConnection("54.69.234.12",8000));
                                     // execute method and handle any error responses.
                                    
                                     InputStream in = postMethod.getResponseBodyAsStream();
                                     String in2 = postMethod.getResponseBodyAsString();
                                     System.out.println(in2);
                                     */
//                                    HttpPost httpClient = new HttpPost("");
//                                    httpClient.setURI(null);
                                } else if (patientPaymode.startsWith("Scheme") || inpatientCheckBox.isSelected()) {
                                    //in patient and outpatient scheme
                                    ////************************************
                                    ////************************************

                                    String payMode = null;
                                    String patientAcc = null;
                                    String cardNo = null;
                                    String AccDesc = null;
                                    String scheme = patientPaymode;
                                    String cardName = null;
                                    String isurer = null;
                                    String expDate = null;
                                    String staffNo = null;

                                    java.sql.Statement stm1 = connectDB.createStatement();
                                    java.sql.ResultSet rse1 = stm1.executeQuery("select pay_mode,account_no,description,payer,payer,expiry_date,account_no from hp_inpatient_register where patient_no ='" + nameNoTxt.getText() + "'");
                                    while (rse1.next()) {
                                        cardNo = dbObject.getDBObject(rse1.getObject(2), "-");
                                        scheme = dbObject.getDBObject(rse1.getObject(3), "-");
                                        cardName = dbObject.getDBObject(rse1.getObject(4), "-");
                                        isurer = dbObject.getDBObject(rse1.getObject(5), "-");
                                        expDate = rse1.getString(6);
                                        staffNo = dbObject.getDBObject(rse1.getObject(7), "-");

                                    }

                                    java.sql.Statement stm12p = connectDB.createStatement();
                                    java.sql.ResultSet rse12p = stm12p.executeQuery("select code,activity from pb_activity where activity_category ='PR'");
                                    while (rse12p.next()) {

                                        patientAcc = rse12p.getObject(1).toString();
                                        AccDesc = rse12p.getObject(2).toString();
                                    }

                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, nameNoTxt.getText());
                                    pstmt.setObject(2, jTable13.getValueAt(i, 1).toString());
                                    pstmt.setString(3, jTextField22.getText());
                                    pstmt.setString(4, patientPaymode);
                                    pstmt.setString(5, String.valueOf(transNo));
                                    pstmt.setString(7, schemeNameTxt.getText());
                                    pstmt.setString(6, cardNo);
                                    pstmt.setString(8, cardName);
                                    pstmt.setString(9, isurer);
                                    pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                    pstmt.setString(11, "");
                                    pstmt.setDouble(12, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                                    pstmt.setDouble(13, 0.00);
                                    pstmt.setDate(14, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB)); //SQLDateFormat.getSQLDate(datePicker11.getDate()));
                                    pstmt.setObject(15, patientAcc);
                                    pstmt.setString(16, glAcc);
                                    pstmt.setDouble(17, java.lang.Double.valueOf(jTable13.getValueAt(i, 2).toString()));
                                    pstmt.setObject(18, staffNo);
                                    pstmt.setBoolean(19, false);
                                    pstmt.setString(20, "Billing");
                                    pstmt.setBoolean(21, false);
                                    pstmt.setString(22, AccDesc);
                                    pstmt.setObject(23, visitID);
                                    pstmt.setString(24, user);
                                    pstmt.setString(25, billNo);
                                    if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt.setString(26, "OP");
                                    } else if (this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt.setString(26, "IP");
                                    }
                                    pstmt.setTimestamp(27, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                    pstmt.setObject(28, visitID);
                                    pstmt.executeUpdate();

                                    java.sql.PreparedStatement pstmt24 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt24.setObject(1, jTable13.getValueAt(i, 5).toString());
                                    pstmt24.setString(2, glAcc);
                                    pstmt24.setString(3, nameNoTxt.getText());
                                    pstmt24.setString(4, patientNameTxt.getText());
                                    pstmt24.setString(5, "");
                                    pstmt24.setString(6, cardNo);
                                    pstmt24.setString(7, cardName);
                                    if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt24.setString(8, "OP");
                                    } else if (this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                        pstmt24.setString(8, "IP");
                                    }
                                    pstmt24.setString(9, jTextField9111.getText());
                                    pstmt24.setObject(10, patientPaymode);
                                    pstmt24.setString(11, "");
                                    pstmt24.setString(12, "");
                                    pstmt24.setString(13, "");
                                    pstmt24.setString(14, jTable13.getValueAt(i, 1).toString());
                                    pstmt24.setString(15, "Billing");
                                    pstmt24.setDouble(16, 0.00);
                                    pstmt24.setDouble(17, java.lang.Double.valueOf(jTable13.getValueAt(i, 4).toString()));
                                    pstmt24.setDate(18, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB)); //SQLDateFormat.getSQLDate(datePicker11.getDate()));
                                    pstmt24.setObject(19, transNo);
                                    pstmt24.setBoolean(20, false);
                                    pstmt24.setBoolean(21, false);
                                    pstmt24.setBoolean(22, false);
                                    pstmt24.setString(23, user);
                                    pstmt24.executeUpdate();

                                    //in patient and outpatient scheme
                                    ////************************************
                                    ////************************************
                                    ////************************************
                                }
                            }
                        }

                    }

//                    } else {
//                        javax.swing.JOptionPane.showMessageDialog(this, "You MUST record patient history before proceeding to diagnostics/investigations.");
//                    }
                }
            }
            // Forward request to LIMS
            if ((paymentModeTxt.getText().contains("Scheme") || inpatientCheckBox.isSelected()) && com.afrisoftech.lib.LabRequestJSON.isLIMSEnabled(connectDB)) {
                if (com.afrisoftech.lib.GetItemInfo.checkLabItems(String.valueOf(transNo), nameNoTxt.getText(), connectDB)) {
                    String limsSystem = com.afrisoftech.lib.LabRequestJSON.getLIMSSystemName(connectDB);
                    if (outpatientCheckBox.isSelected()) {
                        if (limsSystem.equalsIgnoreCase("BLIS")) {
                            com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequestBlis(connectDB, "eJGuuIQvhjHiqM5W1f9cFavsH39Wjcs3", String.valueOf(transNo), nameNoTxt.getText(), patientNameTxt.getText(), paymentModeTxt.getText(), schemeNameTxt.getText(), com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, String.valueOf(transNo), nameNoTxt.getText()), "OP");
                        } else {
                            com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequest(connectDB, "bGltc19hY2Nlc3M6MTJAITIzIzQk", String.valueOf(transNo), nameNoTxt.getText(), patientNameTxt.getText(), paymentModeTxt.getText(), schemeNameTxt.getText(), com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, String.valueOf(transNo), nameNoTxt.getText()), "OUT");
                        }
                    } else {
                        if (limsSystem.equalsIgnoreCase("BLIS")) {
                            com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequestBlis(connectDB, "eJGuuIQvhjHiqM5W1f9cFavsH39Wjcs3", String.valueOf(transNo), nameNoTxt.getText(), patientNameTxt.getText(), paymentModeTxt.getText(), schemeNameTxt.getText(), com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, String.valueOf(transNo), nameNoTxt.getText()), "IP");
                        } else {
                            com.afrisoftech.funsoft.mobilepay.MobilePayAPI.sendLabRequest(connectDB, "bGltc19hY2Nlc3M6MTJAITIzIzQk", String.valueOf(transNo), nameNoTxt.getText(), patientNameTxt.getText(), paymentModeTxt.getText(), schemeNameTxt.getText(), com.afrisoftech.lib.LabRequestJSON.getLabRequester(connectDB, String.valueOf(transNo), nameNoTxt.getText()), "IN");
                        }
                    }
                }
            }
            ///END OF JTABLE13 OPERATIONS

            ///jtable111 operations
            java.sql.Statement pstB = connectDB.createStatement(), stm121q = connectDB.createStatement();
            java.sql.ResultSet rse121 = null, rsB = null;
            java.sql.PreparedStatement pstmt2 = null;
            java.sql.PreparedStatement pstmt2f = null;
            String glAcc = null;
            if (pharmacyCheckBox.isSelected() == Boolean.TRUE) {
                for (int n = 0; n < pharmacyTable.getRowCount(); n++) {
                    if (pharmacyTable.getValueAt(n, 0) != null) {
                        patientPaymode = paymentModeTxt.getText();

                        if (patientPaymode.contains("Scheme") && outpatientCheckBox.isSelected()) {
                            boolean serviceSchemeExclusion = com.afrisoftech.lib.GLCodesFactory.getServiceSchemeExclusionStatus(connectDB, schemeNameTxt.getText(), pharmacyTable.getValueAt(n, 0).toString(), pharmacyTable.getValueAt(n, 7).toString());
                            if (serviceSchemeExclusion) {
                                patientPaymode = com.afrisoftech.lib.GLCodesFactory.getDefaultPaymode(connectDB);
                            }
                        }

                        rse121 = stm121q.executeQuery("select activity from pb_activity where code = '" + pharmacyTable.getValueAt(n, 7).toString().trim() + "'");
                        //rse121 = stm121q.executeQuery("select code from pb_activity where activity ilike '%Pharmacy fee%'");
                        while (rse121.next()) {
                            glAcc = rse121.getObject(1).toString();

                            pstmt2 = connectDB.prepareStatement("INSERT INTO pb_doctors_request VALUES(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?),?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt2.setString(1, nameNoTxt.getText());
                            if (patientNameTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Patient Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {
                                pstmt2.setString(2, patientNameTxt.getText());
                            }
                            // pstmt2.setString(2,jTextField1.getText());
                            pstmt2.setString(3, patientPaymode);
                            if (jTextField8.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Doctor's Name missing", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt2.setString(4, jTextField8.getText());
                            }
                            pstmt2.setString(5, pharmacyTable.getValueAt(n, 0).toString());
                            pstmt2.setDouble(6, Double.parseDouble(pharmacyTable.getValueAt(n, 1).toString()));
                            pstmt2.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
                            pstmt2.setString(8, jTextField10.getText());
                            pstmt2.setDate(9, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt2.setString(10, billNo);
                            pstmt2.setString(11, user);
                            if (patientPaymode.startsWith("Scheme") || inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                pstmt2.setBoolean(12, true);
                            } else {
                                pstmt2.setBoolean(12, false);
                            }
                            pstmt2.setString(13, glAcc);
                            pstmt2.setString(14, visitID);
                            pstmt2.setString(15, pharmacyTable.getValueAt(n, 3).toString());
                            pstmt2.setBoolean(16, false);
                            pstmt2.setBoolean(17, false);
                            pstmt2.setObject(18, pharmacyTable.getValueAt(n, 4).toString());
                            pstmt2.setString(19, pharmacyTable.getValueAt(n, 5).toString());
                            pstmt2.setTimestamp(20, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connectDB));//new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                            pstmt2.setObject(21, pharmacyTable.getValueAt(n, 6).toString());
                            pstmt2.setString(22, rank);
                            pstmt2.setString(23, ageTxt.getText().toString());
                            pstmt2.setString(24, notesTextField.getText()); //clinicalExamineditor.getText().toString());
                            pstmt2.setString(25, waitingclinicscmbx.getSelectedItem().toString());
                            pstmt2.setString(26, provisionalDiagnosistxt.getText().toString());
                            pstmt2.setString(27, Sex);
                            pstmt2.setString(28, "DOC POSTING");
                            pstmt2.setString(29, billNo + "-" + n);
                            pstmt2.setString(30, "" + transNo);
                            pstmt2.executeUpdate();

                            rsB = pstB.executeQuery("SELECT transfer_price,units,department,product_id, (SELECT strength FROM st_stock_item WHERE st_stock_item.item_code = st_stock_prices.product_id ORDER BY 1 DESC LIMIT 1) AS strength FROM st_stock_prices WHERE product_id = '" + pharmacyTable.getValueAt(n, 8) + "' and gl_code =  '" + pharmacyTable.getValueAt(n, 7) + "'");
                            double price = 0;
                            String units = null, Store = null, productId = null;
                            String strength = "-";
                            while (rsB.next()) {
                                price = rsB.getDouble(1);
                                units = rsB.getString(2);
                                Store = rsB.getString(3);
                                productId = rsB.getString(4);
                                strength = rsB.getString(5);

                            }
                            PreparedStatement pstmt1 = connectDB.prepareStatement("INSERT INTO hp_pharmacy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt1.setString(1, nameNoTxt.getText());
                            pstmt1.setString(2, patientNameTxt.getText());
                            pstmt1.setDouble(4, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString().replace(",", "")));
                            pstmt1.setDouble(3, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 1).toString()));
                            pstmt1.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
                            if (patientPaymode.startsWith("Scheme") || this.inpatientCheckBox.isSelected() == Boolean.TRUE) {
                                pstmt1.setBoolean(6, true);
                            } else {
                                pstmt1.setBoolean(6, false);
                            }

                            pstmt1.setDouble(7, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 2).toString()));
                            pstmt1.setObject(8, pharmacyTable.getValueAt(n, 7).toString());
                            pstmt1.setString(9, "" + transNo);
                            pstmt1.setObject(10, jTextField8.getText());
                            pstmt1.setBoolean(11, false);
                            pstmt1.setString(12, units);
                            pstmt1.setDate(13, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker12.getDate()));
                            pstmt1.setObject(14, Store);
                            pstmt1.setString(15, "");
                            pstmt1.setString(16, patientPaymode);
                            pstmt1.setObject(17, pharmacyTable.getValueAt(n, 8).toString());
                            pstmt1.setDouble(18, 0.00);
                            pstmt1.setString(19, user);
                            pstmt1.setObject(20, visitID);
                            pstmt1.setObject(21, "");
                            pstmt1.setString(22, productId);
                            pstmt1.setObject(23, strength);
                            pstmt1.executeUpdate();
                        }

                        ///nursing medication entries
                        {
                            PreparedStatement pstmtdrug = connectDB.prepareStatement("INSERT INTO nursing.medication_administration"
                                    + "(patient_no, visit_id, server_date, date_prescribed,"
                                    + " drug_code,drug, dosage, route,"
                                    + " frequency, no_of_days, doctor, receive,administer,prescription_no,requested) "
                                    + "VALUES "
                                    + "(?, ?, localtimestamp, ?, ?,?, ?, ?, ?, ?, ?, ?, ?,?,?)");
                            pstmtdrug.setObject(1, nameNoTxt.getText());
                            pstmtdrug.setObject(2, visitID);
                            pstmtdrug.setObject(3, com.afrisoftech.lib.ServerTime.getSQLDate(connectDB));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()));
                            pstmtdrug.setObject(4, pharmacyTable.getValueAt(n, 8).toString());
                            pstmtdrug.setObject(5, pharmacyTable.getValueAt(n, 0).toString());
                            pstmtdrug.setObject(6, pharmacyTable.getValueAt(n, 6).toString());
                            pstmtdrug.setObject(7, pharmacyTable.getValueAt(n, 3).toString());
                            pstmtdrug.setObject(8, pharmacyTable.getValueAt(n, 4).toString());
                            pstmtdrug.setObject(9, pharmacyTable.getValueAt(n, 5).toString());
                            pstmtdrug.setObject(10, jTextField9111.getText());
                            pstmtdrug.setBoolean(11, false);
                            pstmtdrug.setBoolean(12, false);
                            pstmtdrug.setString(13, "" + transNo);
                            pstmtdrug.setDouble(14, java.lang.Double.valueOf(pharmacyTable.getValueAt(n, 1).toString()));
                            pstmtdrug.executeUpdate();

                        }
                        ///nursing medication entries
                    }
                }
            }

            ////END OF JTABLE111 OPERATIONS
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful.Bill No. " + billNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception req) {
            System.out.println("the req error is \n\n\n\n\n");
            req.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, req.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void coluorTable() {

        //  com.afrisoftech.dbadmin.JTable predicateTable = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
        int triage = 0, seen = 0, normal = 0, emergency = 0;
        if (this.outpatientCheckBox.isSelected() == Boolean.TRUE) {
            //   }
            for (int i = 0; i < this.clerkingwaitingTable.getRowCount(); i++) {
                String patientType = String.valueOf(this.clerkingwaitingTable.getValueAt(i, 9)).toLowerCase().trim();
                String patientstatus = String.valueOf(this.clerkingwaitingTable.getValueAt(i, 4)).toLowerCase().trim();
                switch (patientType) {
                    case "triage": {
                        triage++;
                    }
                    break;
                    case "cons": {
                        triage++;
                        seen++;
                    }
                    break;

                }
                switch (patientstatus) {
                    case "normal": {
                        normal++;
                    }
                    break;
                    case "emergency": {
                        emergency++;
                    }
                    break;
                }
            }
            this.emergencyTextField.setText("EMERGENCY :-" + emergency);
            this.normalTextField.setText("NORMAL :-" + normal);
            this.triageTextField.setText("TRIAGED :-" + triage);
            this.seenTextField.setText("SEEN BY DR. :-" + seen);
//            com.afrisoftech.dbadmin.JTable predicateTable0 = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
//            predicateTable0.setHighlighterPipeline(predicateTable0, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.PINK, java.awt.Color.BLACK, "triage", 9, 9, 1),
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.PINK, java.awt.Color.BLACK, "Cons", 9, 9, 1),
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.CYAN, java.awt.Color.BLACK, "Cons", 9, 9, 2)
//            });
        }

//        try {
//            com.afrisoftech.dbadmin.JTable predicateTable = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
//            predicateTable.setHighlighterPipeline(predicateTable, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.ORANGE, java.awt.Color.BLACK, "Normal", 4, 4, 4),
//                new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.RED, java.awt.Color.BLACK, "Emergency", 4, 4, 4)
//            });
//            System.out.println("this is triage and seen checking");
//        } catch (Exception es) {
//            es.printStackTrace();
//
//        }
        try {
            int xray = 0;

            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement("SELECT distinct patient_no   FROM pb_doctors_request where "
                    + "requisition_no='X-RAY' and collected = true and paid=true and results=true  and dosage='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' ");

            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
            while (rsetVector.next()) {
                xray++;
//                com.afrisoftech.dbadmin.JTable predicateTablexray = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
//                predicateTablexray.setHighlighterPipeline(predicateTablexray, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                    new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.MAGENTA, java.awt.Color.BLACK, dbObject.getDBObject(rsetVector.getString(1), ""), 0, 0, 4)});
            }
            this.x_rayresultsTextField.setText("X-RAY RESULTS :-" + xray);

        } catch (Exception req) {
            req.printStackTrace();

        }
        /////
        /////////////////////////////////////

        try {
            int lab = 0;

            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(" "
                    + "SELECT distinct patient_no   FROM pb_doctors_request where "
                    + "requisition_no='LAB' and collected = true and paid=true and results=true and "
                    + " dosage='" + this.waitingclinicscmbx.getSelectedItem().toString() + "' ");

            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
            while (rsetVector.next()) {
                lab++;
//                com.afrisoftech.dbadmin.JTable predicateTablelab = (com.afrisoftech.dbadmin.JTable) clerkingwaitingTable;
//                predicateTablelab.setHighlighterPipeline(predicateTablelab, new org.jdesktop.swing.decorator.PatternHighlighter[]{
//                    new org.jdesktop.swing.decorator.PatternHighlighter(java.awt.Color.GREEN, java.awt.Color.BLACK, dbObject.getDBObject(rsetVector.getString(1), ""), 0, 0, 3)
//                });
            }
            this.labTextField.setText("LAB RESULTS :-" + lab);
        } catch (Exception req) {
            req.printStackTrace();

        }
    }

    private void computeTotals() {
        float totalSum =0;
        for(int i = 0; i < pharmacyTable.getRowCount(); i++){
        if (pharmacyTable.getModel().getValueAt(i, 2) != null) {
//            if (pharmacyTable.getSelectedColumn() == pharmacyTable.getSelectedColumn()) {
                float qty = java.lang.Float.parseFloat(pharmacyTable.getValueAt(i, 1).toString().trim().replace(",", ""));;
                float price = java.lang.Float.parseFloat(pharmacyTable.getValueAt(i, 2).toString().trim().replace(",", ""));
                float total = qty * price;
                //pharmacyTable.setValueAt(total, pharmacyTable.getSelectedRow(), 3);
                 totalSum += total ; //com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(pharmacyTable, 2);
                

//            }
        }
        }
        jTextField3.setText(java.lang.String.valueOf(totalSum));
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void caretUpdate(CaretEvent e) {

        e.getDot();
        //To change body of generated methods, choose Tools | Templates.

    }

//    class WaitingPatientsThread extends Thread {
//
//        public WaitingPatientsThread() {
//        }
//
//        public void run() {
//            Vector requestids = new Vector();
//            System.out.println("the lims test thread");
//
//            // labresultsdata.clear();
//            // requestids.clear();
//            while (true) {
//                //  patientListTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, first_name ||' '||second_name||' '||last_name AS patient_name FROM hp_patient_register WHERE waiting_patient = true and last_visit = current_date"));
//                HashMap labresultsdata = new HashMap();
//                try {
//                    if (connectDB != null) {
//                        // String funsoft="select pb_doctors_request.request_id from pb_doctors_request,hp_lab_results where pb_doctors_request.paid=true and pb_doctors_request.collected=false and curr_date::date>=current_date-85 and pb_doctors_request.request_id!=hp_lab_results.request_id  ;";
//                        String funsoft = "select pb_doctors_request.request_id from pb_doctors_request where request_id is not null and requisition_no='LAB' and pb_doctors_request.paid=true and pb_doctors_request.collected=false and curr_date::date>=current_date-1  except select  hp_lab_results.request_id from hp_lab_results";
//
//                        java.sql.PreparedStatement stmntfunsoft = connectDB.prepareStatement(" select pb_doctors_request.request_id from pb_doctors_request where   request_id is not null and requisition_no='LAB' and pb_doctors_request.paid=true and "
//                                + "pb_doctors_request.collected=false and curr_date::date>=current_date-1 except select  hp_lab_results.request_id from hp_lab_results");
//                        java.sql.ResultSet rsetfunsoft = stmntfunsoft.executeQuery();
//
//                        while (rsetfunsoft.next()) {
//
//                            requestids.addElement(rsetfunsoft.getObject(1));
//                            System.out.println("request id ni " + rsetfunsoft.getObject(1));
//                        }
//                        System.out.println("The no of patients that are yet toreceive their results is " + requestids.size());
//
//                    } else {
//                        System.out.println("Failed to make connection!");
//
//                    }
//
//                } catch (Exception ex) {
//                    System.out.println("Failed to make connection during retreival!");
//                    Logger.getLogger(ConsultationIntfr.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                if (!requestids.isEmpty()) {
//                    limsconnection(limsip, limsport, limsdatabase, limsusers, limspass);
//                    if (limsConnectDB != null) {
//                        try {
//
//                            System.out.println("You made it, take control your labtrial database now!");
//
//                            for (int i = 0; i < requestids.size(); i++) {
//
//                                String labtrialresults = "SELECT  test_name, test_parameter, units, result, lab_comment, "
//                                        + "       login, loginb, lab_comments, loginc, result_time, code"
//                                        + "  FROM list_of_completed_tests where ipno;";
//                                System.out.println((""
//                                        + "SELECT  test_name, test_parameter, units, result, lab_comment, "
//                                        + "       login, loginb, lab_comments, loginc, result_time, code"
//                                        + "  FROM list_of_completed_tests where ipno='" + requestids.elementAt(i) + "' ;"));
////                  rigghht code                   java.sql.PreparedStatement stmntpem = limsConnectDB.prepareStatement(""
//// + "SELECT  test_name, test_name,units,result, lab_comments,login, login, lab_comments, login,result_time, code FROM list_of_completed_tests where ipno=" + requestids.elementAt(i) + " ;");
//                                java.sql.PreparedStatement stmntpem = limsConnectDB.prepareStatement(""
//                                        + "SELECT  test_name, test_parameter, units, result, lab_comment, "
//                                        + "       login, loginb, lab_comments, loginc, result_time, code"
//                                        + "  FROM list_of_completed_tests where ipno='" + requestids.elementAt(i).toString().trim() + "' ;");
//                            System.out.println("\n\nthe labtrial result set is  for id " + requestids.elementAt(i));
//                            java.sql.ResultSet rsetlabtrial = stmntpem.executeQuery();
//                                // Row row =rsetlabtrial.getRow();
////                                if (!rsetlabtrial.isBeforeFirst()) {
////                                    requestids.removeElementAt(i);
////                                    i--;
////                                    System.out.println("the labtrial result set is null");
////                                } else if (rsetlabtrial.isBeforeFirst())
//                                {
//                                    System.out.println("the labtrial result set is not null for id " + requestids.elementAt(i));
//                                    Vector resultsetdata = new Vector();
//
//                                    while (rsetlabtrial.next()) {
//                                        System.out.println("niko hapa sasa");
//                                        resultsetdata.addElement(rsetlabtrial.getObject(1));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(2));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(3));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(4));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(5));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(6));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(7));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(8));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(9));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(10));
//                                        resultsetdata.addElement(rsetlabtrial.getObject(11));
//                                        System.out.println("getting the data for the map");
//
//                                        labresultsdata.put(requestids.elementAt(i), resultsetdata);
//                                        resultsetdata.clear();
//                                    }
//                                }
//                                System.out.println("the labtrial result map elements is " + labresultsdata.size());
//                                // rsetlabtrial = null;
//                            }/////end of for loop
//
//                            limsConnectDB.close();
//
//                        } catch (Exception s) {
//                            s.printStackTrace();
//                        }
//
//                        ///////end of connection and querying of the lab trial database
//                        try {
//                            connectDB.setAutoCommit(false);
//
//                            ////////getting the terms from the hashmap
//                            // for(int j=0;j<labresultsdata.size();j++) {
//                            Set set = labresultsdata.entrySet();
//                            // Get an iterator
//                            Iterator i = set.iterator();
//                            System.out.println("\t \t the iterator is " + i);
//
//                            while (i.hasNext()) {
//                                Map.Entry me = (Map.Entry) i.next();
//                                Object key = me.getKey();
//
//                                Vector values = new Vector();
//                                values = (Vector) me.getValue();;
//                                System.out.println("\n\n\n\n\n\n\nnnn\nnthe size from our vector is" + values.size());
//                                java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement(
//                                        "INSERT INTO hp_lab_results VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?)");
//                                pstmt21.setString(1, " ");
//                                pstmt21.setString(2, "");
//                                pstmt21.setString(3, " ");
//                                pstmt21.setDouble(4, 0);
//                                pstmt21.setString(5, " ");
//                                pstmt21.setString(6, values.elementAt(0).toString());//from labTrail
//                                pstmt21.setString(7, values.elementAt(1).toString());//from labTrail
//                                pstmt21.setString(8, "-");
//                                pstmt21.setDouble(9, 0);
//                                pstmt21.setDouble(10, 0);
//                                pstmt21.setDouble(11, Double.valueOf(values.elementAt(3).toString())); //from labTrail
//                                pstmt21.setString(12, values.elementAt(4).toString()); //from labTrail
//                                pstmt21.setTimestamp(14, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis())); //from labTrail
//                                pstmt21.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker11.getDate())); //from labTrail
//                                pstmt21.setString(16, "");
//                                pstmt21.setDouble(15, 1);
//                                pstmt21.setString(20, values.elementAt(8).toString()); //from labTrail
//                                pstmt21.setString(17, values.elementAt(5).toString()); //from labTrail
//                                pstmt21.setString(18, values.elementAt(6).toString()); //from labTrail
//                                pstmt21.setString(19, values.elementAt(7).toString()); //from labTrail
//                                pstmt21.setString(21, "");
//                                pstmt21.setBoolean(22, false);
//                                pstmt21.setBoolean(23, false);
//                                pstmt21.setString(24, values.elementAt(9).toString()); //from labTrail
//                                pstmt21.setString(25, "LAB");
//                                pstmt21.setString(26, values.elementAt(10).toString()); //from labTrail
//                                pstmt21.setString(27, " ");
//                                pstmt21.setString(28, String.valueOf(key));
//
//                                pstmt21.executeUpdate();
//
//                                java.sql.PreparedStatement stmntquery = connectDB.prepareStatement(""
//                                        + "SELECT patient_no, patient_name,age,gender, doctor,clinic FROM "
//                                        + "pb_doctors_request where requisition_no='LAB' and request_id='" + String.valueOf(key) + "';");
//                                java.sql.ResultSet rsetquery = stmntquery.executeQuery();
//                                while (rsetquery.next()) {
//                                    java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("update hp_lab_results  set patient_no='" + rsetquery.getString(1) + "', "
//                                            + "patient_name='" + rsetquery.getString(2) + "',age='" + rsetquery.getString(3) + "',gender='" + rsetquery.getString(4) + "', "
//                                            + " doctor='" + rsetquery.getString(5) + "',clinic='" + rsetquery.getString(6) + "'  where request_id='" + String.valueOf(key) + "' ;");
//                                    pstmt31.executeUpdate();
//                                }
//                            }
//                            labresultsdata.clear();
//
//                            ///end of for loop
//                            ////////end of getting the values
//                            connectDB.commit();
//                            connectDB.setAutoCommit(true);
//
//                        } catch (Exception s) {
//                            s.printStackTrace();
//                            try {
//                                connectDB.rollback();
//                            } catch (java.sql.SQLException sql) {
//                                javax.swing.JOptionPane.showMessageDialog(null, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
//                            }
//                        }
//                    } else {
//                        limsConnectDB = null;
//
//                    }
////                        try { ///getting data to be inserted in the lab results table from pb doctors request 
////                            for (int i = 0; i < requestids.size(); i++) {
////                                String query = "SELECT patient_no, patient_name,age,gender, doctor,clinic FROM pb_doctors_request where requisition_no='LAB' and request_id=" + requestids.elementAt(i) + ";";
////
////                             
////
////                            }
////                            ///end getting data to be inserted in the lab results table from pb doctors request 
////                        } catch (Exception s) {
////
////                        }
//
//                }
//
//                /// main try statement
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(VitalSignRecIntfr.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                labresultsdata.clear();
//                requestids.clear();
//            }
//
//        }
//    }
    class WaitingPatientsThread extends Thread {

        public WaitingPatientsThread() {
        }

        public void run() {
            Vector requestids = new Vector();
            System.out.println("the lims test thread");

            // labresultsdata.clear();
            // requestids.clear();
            while (true) {
                //  patientListTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT patient_no, first_name ||' '||second_name||' '||last_name AS patient_name FROM hp_patient_register WHERE waiting_patient = true and last_visit = current_date"));
                HashMap labresultsdata = new HashMap();
                try {
                    if (connectDB != null) {
                        connectDB.setAutoCommit(false);
                        java.sql.PreparedStatement stmntfunsoft = connectDB.prepareStatement(""
                                + "insert into hp_lab_results "
                                + "SELECT labtrail_to_insert.patient_no, labtrail_to_insert.patient_name, labtrail_to_insert.lab_no, labtrail_to_insert.age::numeric, labtrail_to_insert.gender, labtrail_to_insert.service, labtrail_to_insert.parameter, labtrail_to_insert. "
                                + "       units, labtrail_to_insert.upper_limit, labtrail_to_insert.lower_limit, labtrail_to_insert.result::numeric, labtrail_to_insert.outcome, labtrail_to_insert.date, labtrail_to_insert.\"timestamp\", labtrail_to_insert. "
                                + "       time_taken, labtrail_to_insert.ext_ref, labtrail_to_insert.lab_manager, labtrail_to_insert.pathologist, labtrail_to_insert.comments, labtrail_to_insert.user_name, labtrail_to_insert. "
                                + "       doctor, labtrail_to_insert.doc_read, labtrail_to_insert.result_shown, labtrail_to_insert.spec_time, labtrail_to_insert.code, labtrail_to_insert.clinic, labtrail_to_insert.result_no,  "
                                + "       labtrail_to_insert.request_id "
                                + "  FROM labtrail_to_insert  "
                                + " where not exists( "
                                + "  SELECT patient_no, patient_name, lab_no, age::numeric, gender, typeof_test, parameter,  "
                                + "       units, upper_limit, lower_limit, result::numeric, out_come, date, input_date,  "
                                + "       time_taken, ext_ref, lab_manager, pathologist, comments, user_name,  "
                                + "       doctor, doc_read, result_shown, spec_time, clinic, code, result_no,  "
                                + "       request_id "
                                + "  FROM hp_lab_results where labtrail_to_insert.request_id=request_id) "
                                + "");
                        stmntfunsoft.executeUpdate();

                        connectDB.commit();
                        connectDB.setAutoCommit(true);
                        System.out.println("\n\n\n\n\n\n\nupdated data successfully this is GREEEEAAAAAAATTT\nGLORY MANCHESTER CITY \n\n\n\n");

                    } else {
                        System.out.println("\n\n\n\n\nFailed to make connection!\n\n\n\n");

                    }

                } catch (Exception ex) {
                    System.out.println("\n\n\n\n\nFailed to make connection during retreival!\n\n\n\n");
                    try {
                        connectDB.rollback();
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(null, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    Logger.getLogger(ConsultationIntfr.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VitalSignRecIntfr.class.getName()).log(Level.SEVERE, null, ex);
                }
                labresultsdata.clear();
                requestids.clear();
            }

        }
    }

///////////////////////////////////////////////////////
    private void changeDate() {
        try {
            if (noofsickjTextField31.getText().trim().length() > 0 && noofsickjTextField31.getText().trim() != null) {
                System.out.println("SELECT ('" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date + (select round(" + dbObject.getDBObject(noofsickjTextField31.getText().trim(), "0") + ",0))::integer) ::date as date");
                java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                        + "SELECT ('" + com.afrisoftech.lib.SQLDateFormat.getSQLDate(transdatePicker.getDate()) + "'::date + (select round(" + dbObject.getDBObject(noofsickjTextField31.getText().trim(), "0") + ",0))::integer) ::date as date");

                java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
                while (rsetVector.next()) {
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat();//.getInstance();
                    dateFormat.applyPattern("yyyy-MM-dd");
                    XMLClaimFile.patientDOB = rsetVector.getString(1);
                    resumptiondatePicker121.setDate(dateFormat.parse(XMLClaimFile.patientDOB));

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultationIntfr.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initialiseSpellChecker() {
        //FILE LOCATION OF DICTIONARY
        String userDictionaryPath = "/dictionary/";

        //SET DICTIONARY PROVIDER FROM DICTIONARY PATH
        SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));

        //REGISTER DICTIONARY
        SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");

    }

    private void cmBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        calculateDrugQty();

    }

    private void cmBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        calculateDrugQty();

    }

    private void calculateDrugQty() {
        System.err.println("Values " + pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4));
        System.err.println("Values " + pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5));
        if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4) != null && pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5) != null && !pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5).toString().isEmpty() && !pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5).toString().isEmpty()) {
            pharmacyTable.setValueAt(calculateQuantity(), pharmacyTable.getSelectedRow(), 1);

            boolean neg = true;
            try {

                java.sql.Statement pstmt1 = connectDB.createStatement();
//                java.sql.ResultSet rs1 = pstmt1.executeQuery("select neg_allow_doc from st_stock_control"); //from orders where supplier ='"+jTable1.getValueAt(i,4).toString()+"'");
//
//                while (rs1.next()) {
//
//                    neg = rs1.getBoolean(1);
//                }

                float bal = java.lang.Float.parseFloat(pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 9).toString());
                float qty = calculateQuantity();

                if (neg == false && qty > bal) {
                    javax.swing.JOptionPane.showMessageDialog(this, "The quantity for the drug is more than the remaining quantity.", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    pharmacyTable.setValueAt(0, pharmacyTable.getSelectedRow(), 1);

//                    for (int r = 0; r < pharmacyTable.getColumnCount(); r++) {
//                        pharmacyTable.getModel().setValueAt("", pharmacyTable.getSelectedRow(), r);
//
//                    }
                }

            } catch (java.sql.SQLException sq) {
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                sq.printStackTrace();
                System.out.println(sq.getMessage());

            }

            // pharmacyTable.setValueAt(Double.valueOf(pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 1).toString()) * Double.valueOf(pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 2).toString()), pharmacyTable.getSelectedRow(), 11);
            double totalSum = com.afrisoftech.lib.TableColumnTotal.getTableColumnTotalByQtyUnitPrice(pharmacyTable, 1, 2);
            jTextField3.setText(java.lang.String.valueOf(totalSum));

            System.err.println("Total is " + totalSum);

        }

    }

    private int calculateQuantity() {
        int qty = 0;
        int freq = 0;
        int noOfDays = 0;

        if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("sus")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("syrup")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("cream")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("tube")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("bottle")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 0).toString().toLowerCase().contains("drop")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("cream")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("syrup")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("susp")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("bottle")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("Tube")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("drop")
                || pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 10).toString().toLowerCase().contains("pack")) {
            qty = 1;
        } else {
            if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4).toString().equalsIgnoreCase("OD")) {
                freq = 1;
            } else if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4).toString().equalsIgnoreCase("BD")) {
                freq = 2;
            } else if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4).toString().equalsIgnoreCase("TID")) {
                freq = 3;
            } else if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 4).toString().equalsIgnoreCase("QID")) {
                freq = 4;
            } else {
                freq = 1;
            }

            if (pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5).toString().equalsIgnoreCase("Once")) {
                noOfDays = 1;
            } else {
                noOfDays = Integer.valueOf(pharmacyTable.getValueAt(pharmacyTable.getSelectedRow(), 5).toString());
            }
            qty = noOfDays * freq;
        }

        return qty;
    }

}
