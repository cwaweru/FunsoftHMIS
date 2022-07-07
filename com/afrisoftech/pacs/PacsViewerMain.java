/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.pacs;

//import OpenUrlInJFrameAction;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners,biz>
 */
public class PacsViewerMain extends javax.swing.JFrame {

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("pACS Viewer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 833, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 406, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        SwingUtilities.invokeLater(() -> {
            PacsViewerMain jFrameTest = new PacsViewerMain();
            jFrameTest.setTitle("Orthanc Dicom/PACS Viewer");
            jFrameTest.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jFrameTest.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            JFXPanel jfxPanel = new JFXPanel();
            jFrameTest.add(jfxPanel);

            Platform.runLater(() -> {
//                jFrameTest.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
                jFrameTest.setVisible(true);
                WebView webView = new WebView();
                jfxPanel.setScene(new Scene(webView));
                // Create a trust manager that does not validate certificate chains
                TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
                };

// Install the all-trusting trust manager
                try {
                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, trustAllCerts, new java.security.SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                } catch (GeneralSecurityException e) {
                }
                webView.getEngine().setJavaScriptEnabled(true);
                webView.getEngine().getCreatePopupHandler(); //setOnAlert(null);
                webView.getEngine().load("http://funsoft.systempartners.biz:8042/");
            });
//        });
    }

    private Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);
        Text text = new Text();

        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return (scene);
    }
    // Variables declaration - do not modify                     
    // End of variables declaration                   
}