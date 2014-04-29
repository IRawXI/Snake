/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basis;

import helfer.Typ;

/**
 *
 * @author Rudi
 */
public class Spielfeld {
    Typ[][] spielfeld;

    public Spielfeld(int x, int y) {
        this.spielfeld = new Typ[x][y];
        setEmpty();
    }
    
    public void setEmpty(){
        for(int i = 0; i < spielfeld.length; i++){
            for(int j = 0; j < spielfeld[0].length; j++){
                spielfeld[i][j] = Typ.LEER;
            }
        }
    }

    public Typ getSpielfeldKoord(int koordinate_x, int koordinate_y){
        return spielfeld[koordinate_x][koordinate_y];
    }
    
    //public void setSpielfeldKoord(int koordinate_x, int koordinate_y, Typ type){
    //    spielfeld[koordinate_x][koordinate_y] = type;
    //}
    
    public int getLength(){
        return spielfeld.length;
    }
    
    public int get0Length(){
        return spielfeld[0].length;
    }
}
