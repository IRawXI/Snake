/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basis;

import helfer.Itemtyp;
import helfer.Position;
import helfer.Typ;

/**
 *
 * @author Rudi
 */
public class Item {
    Position position;
    Itemtyp typ;

    public Item(int pos_x, int pos_y, Itemtyp t) {
        this.position = new Position(pos_x, pos_y);
        this.typ = t;
    }
    
    public Item(Position pos, Itemtyp t) {
        this.position = pos;
        this.typ = t;
    }

    public Position getPosition() {
        return position;
    }

    public Typ getTyp() {
        switch(typ){
            case LAENGER:
                return Typ.ITEM_L;
            case KUERZER:
                return Typ.ITEM_K;
            case SCHNELLER:
                return Typ.ITEM_S;
            default:
                return null;
        }
    }
    
    public Itemtyp getItemtyp() {
        return typ;
    }
    
}
