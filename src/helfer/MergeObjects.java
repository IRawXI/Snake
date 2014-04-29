/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helfer;

import basis.Item;
import basis.Schlange;
import basis.Spielfeld;
import java.util.ArrayList;

/**
 *
 * @author Rudi
 */
public class MergeObjects {
    Spielfeld spielfeld;
    Schlange snake;
    ArrayList<Item> items;
    Typ[][] map;
    
    public Typ[][] merge(Spielfeld sf, Schlange schlange, ArrayList<Item> itemlist){
        this.spielfeld = sf;
        this.snake = schlange;
        this.items = itemlist;
        
        map = new Typ[spielfeld.getLength()][spielfeld.get0Length()];
        for (int i = 0; i < spielfeld.getLength(); i++) {
            for (int j = 0; j < spielfeld.get0Length(); j++) {
                
                map[i][j] = spielfeld.getSpielfeldKoord(i, j);
            }
        }
        
        
        map[snake.getSnakePos(0).getPosX()][snake.getSnakePos(0).getPosY()] = Typ.SCHLANGENKOPF;
        for (int a = 1; a < snake.getLaenge(); a++) {
            map[snake.getSnakePos(a).getPosX()][snake.getSnakePos(a).getPosY()] = Typ.SCHLANGENKOERPER;
        }
        for (int b = 0; b < items.size(); b++) {
            map[items.get(b).getPosition().getPosX()][items.get(b).getPosition().getPosY()] = items.get(b).getTyp();
        }
               
        return map;
    }
    
}
