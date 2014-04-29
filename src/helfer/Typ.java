/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helfer;

import java.awt.Color;

/**
 *
 * @author Rudi
 */
public enum Typ {
    //RAND(new Color(0, 0, 255)),
    ITEM_L(new Color(138, 43, 226)),
    ITEM_K(new Color(255, 0, 0)),
    ITEM_S(new Color(255, 255, 0)),
    SCHLANGENKOPF(new Color(0, 255, 0)),
    SCHLANGENKOERPER(new Color(50, 150, 50)),
    LEER(new Color(0, 0, 0));
    public final Color color;
    
    private Typ(Color color) {
        this.color = color;
    }
}
