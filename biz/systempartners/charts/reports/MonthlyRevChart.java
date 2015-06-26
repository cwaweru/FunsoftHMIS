//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package biz.systempartners.charts.reports;
import com.lowagie.text.*;
//import org.jfree.data.category.CategoryToPieDataset;

//import org.jfree.data.category.CategoryToPieDataset;
//import org.jfree.data.category.CategoryToPieDataset;
//import org.jfree.data.category.DefaultCategoryDataset;

public class MonthlyRevChart {// implements java.lang.Runnable {
    double Credit = 0.00;
    
    int over = 0;
    int name = 0;
    
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String Debtor = null;
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries= null;
    java.util.Date ageingDates[][] = null;
    String ks;
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA,8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;


   
      public MonthlyRevChart(java.sql.Connection connDb, java.util.Date beginDate, java.util.Date endate) {  

        connectDB = connDb;
        
        beginDate = beginDate;
        
        endDate = endate;
        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date"+endDate);

        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(12, endDate);
  
    }
   /* 
    public org.jfree.data.general.DefaultPieDataset createPieDataSet(){
        
       // public org.jfree.data.general.
        
        org.jfree.data.general.DefaultPieDataset dataSet = new org.jfree.data.general.DefaultPieDataset();
        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals[] = new double[rangeDates.length];
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        int interval = 0;

        
        java.lang.String debitTotal = null;
        
        java.lang.String creditTotal = null;
        
        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
        
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        long dateNow = calendar.getTimeInMillis();
        
        java.sql.Date datenowSql= new java.sql.Date(dateNow);
        
        System.out.println(datenowSql.toString());
        
       String compName = null;
        String date = null;
        try {
           
            java.sql.Statement st3 = connectDB.createStatement();
            java.sql.Statement st4 = connectDB.createStatement();
            
            java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
            java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
            while(rset2.next()){
                compName = rset2.getObject(1).toString();
                ks = rset2.getString(2);
            }
            while(rset4.next())
                date = rset4.getObject(1).toString();
            
            com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName, pFontHeader2),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
            headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
            docPdf.setHeader(headerFoter);
            
        } catch(java.sql.SQLException SqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        

        double Totals = 0.00;
        double OS = 0.00;

        try {
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
            
            java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());

        } catch(java.text.ParseException psExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
            
        }

        for (int x = 0; x < rangeDates.length; x++) {
            System.out.println(rangeDates[x][0].toString());
            //com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter();
            
            try {
                
                // Date parser
                
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMM/yy");
                
                java.lang.String monthString = dateFormatter.getDateString();
                
                int days = 1;

                interval = x;

            } catch(java.text.ParseException prs) {
                prs.printStackTrace();
            }
        }
        

        try {
            
            double GrandTotal = 0.00;
            double Over120Total = 0.00;
            java.lang.Object[] listofAct = this.getListofActivities();

            System.out.println(listofAct.length);
            
            for (int i = 0; i < listofAct.length; i++) {
                double TurnOver = 0.00;
                double Over120 = 0.00;
                double TotalCount = 0.00;
                

                java.sql.Statement stmta1 = connectDB.createStatement();
                java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
                java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select activity from pb_activity WHERE code = ? GROUP BY activity");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                
                pset22.setString(1,listofAct[i].toString().toUpperCase());
                java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                while (rSeta1.next()) {
                    name = rSeta1.getInt(1);
                    
                }

                java.sql.ResultSet rset22 = pset22.executeQuery();
                
                
                
                
                
                java.sql.Statement st2 = connectDB.createStatement();
                java.sql.Statement st21 = connectDB.createStatement();
                java.sql.Statement st22 = connectDB.createStatement();
                java.sql.Statement st23 = connectDB.createStatement();
                java.sql.Statement st211 = connectDB.createStatement();
                java.sql.Statement st221 = connectDB.createStatement();
                java.sql.Statement st231 = connectDB.createStatement();
                java.sql.Statement st2A = connectDB.createStatement();
                java.sql.Statement st2B = connectDB.createStatement();
                java.sql.Statement st2C = connectDB.createStatement();
                java.sql.Statement stc = connectDB.createStatement();
                System.out.println("Dealer Is : ["+listofAct[i]+"].");
                
                java.lang.String measuredEntityActivity = null;
                
                if (name > 0){
                    
                    while (rset22.next()){

                        measuredEntityActivity = rset22.getObject(1).toString().toUpperCase();
                                               
                    }
                    java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+rangeDates[rangeDates.length - 1][0]+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                    pset1.setString(1,listofAct[i].toString());
                    java.sql.ResultSet rset1 = pset1.executeQuery();
                    
                    java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+endDate+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                    pset111.setString(1,listofAct[i].toString());
                    java.sql.ResultSet rset111 = pset111.executeQuery();
                    java.sql.Statement st02 = connectDB.createStatement();
                    
                    
                    java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ? AND date < '"+endDate+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                    pset112.setString(1,listofAct[i].toString());
                    java.sql.ResultSet rset112 = pset112.executeQuery();
                    
                    
                    for (int t = 0; t < rangeDates.length; t++) {
                        java.sql.Statement st01 = connectDB.createStatement();
                        
                        java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date between '"+rangeDates[t][0]+"' AND '"+rangeDates[t][1]+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                        pset.setString(1,listofAct[i].toString().toUpperCase());
                        java.sql.ResultSet rset = pset.executeQuery();
                        
                        
                        while (rset.next()){
                            columnTotals[t] = columnTotals[t] + rset.getDouble(1);
                            TotalCount = TotalCount + rset.getDouble(1);
                            
                        }
                        
                    }
                    
                    if(TotalCount > 0){
                    
                    System.out.println("Inserted values ["+measuredEntityActivity +" , "+TotalCount+"]");
                    dataSet.setValue(new String(measuredEntityActivity.toString()),TotalCount);
                    }

                }
            }
            

            for (int x = 0; x < rangeDates.length; x++) {
                
                turnOver = turnOver + columnTotals[x];
            }
            
            
        } catch(java.sql.SQLException SqlExec) {
            
            SqlExec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
            
        }
        
  
        return dataSet;
    }
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code,activity FROM pb_activity where (activity_category ilike 'I%' OR activity_category ilike 'EDS') order by activity");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }

        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }*/


}
