/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roots.map.overlay;

import java.awt.*;

public interface ILatLongToScreenCoordinatesConverter {
    Point getScreenCoordinates(Point.Double coords);
}
