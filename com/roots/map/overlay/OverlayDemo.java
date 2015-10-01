/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roots.map.overlay;

import com.roots.map.MapPanel;

import javax.swing.*;
import java.awt.*;

public class OverlayDemo {
    public void run()
    {
        final JFrame mainFrame = new JFrame("MapPanel with place marks");
        final MapPanel mapPanel = new MapPanel(new Point(3636598, 1535138), 14);
        mainFrame.add(mapPanel);

        final DemoGlassPane glassPane = new DemoGlassPane(mapPanel);
        glassPane.addPointToMark(new Point.Double(37.30957, -1.29628));
        glassPane.addPointToMark(new Point.Double(37.70957, -1.49628));
        glassPane.addPointToMark(new Point.Double(36.30957, -2.29628));
        glassPane.addPointToMark(new Point.Double(38.30957, -1.59628));
        mainFrame.setGlassPane(glassPane);

        mainFrame.getGlassPane().setVisible(true);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public static void main(String args[])
    {
        final OverlayDemo app = new OverlayDemo();

        app.run();
        
    }
}