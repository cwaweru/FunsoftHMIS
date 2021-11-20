/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.pacs;

import java.awt.Toolkit;
import java.net.URL;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class PACSViewer {
//
//    public static void main(String args[]) {
//        
//    }
    
    public static void viewPACSReport(javax.swing.JFrame hostComponent, String pacsReportURI) {
        
        SwingUtilities.invokeLater(() -> {
          //  PacsViewerMain jFrameTest = new PacsViewerMain();
          //  hostComponent.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JFXPanel jfxPanel = new JFXPanel();
            hostComponent.removeAll();
            hostComponent.add(jfxPanel);
            System.out.println("Fx passed 1");
            Platform.runLater(() -> {
                hostComponent.setVisible(true);
                WebView webView = new WebView();
                jfxPanel.setScene(new Scene(webView));
                webView.getEngine().load(pacsReportURI);
                 System.out.println("Fx passed 2");
            });
        });
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
}

