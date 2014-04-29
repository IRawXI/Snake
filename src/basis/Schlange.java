/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basis;

import java.util.ArrayList;
import helfer.Position;
import helfer.Richtung;
import logik.Main;

/**
 *
 * @author Rudi
 */

/**
 TODO:
  - ??
  
 DONE:
  - Konstruktor (N°1)
  * - bewegen()
 
 */

public class Schlange {
    Richtung richtung;
    ArrayList<Position> schlange = new ArrayList<Position>();
    
    public Schlange(int l, Richtung r, int first_x, int first_y){
        this.richtung = r;
        switch(r){
            case LINKS:
                for(int i = 0 ; i < l ; i++){
                    schlange.add(new Position(first_x+i, first_y));}
                break;
            case RECHTS:
                for(int i = 0 ; i < l ; i++){
                    schlange.add(new Position(first_x-i, first_y));}
                break;
            case OBEN:
                for(int i = 0 ; i < l ; i++){
                    schlange.add(new Position(first_x, first_y+i));}
                break;
            case UNTEN:
                for(int i = 0 ; i < l ; i++){
                    schlange.add(new Position(first_x, first_y-i));}
                break; 
            }//switch
        
    }//Konstruktor
    
    public void bewegen(boolean laenger, boolean kuerzer, int new_x, int new_y){
        bewegen(laenger, kuerzer, new Position(new_x, new_y));
    }//Methode

    public void bewegen(boolean laenger, boolean kuerzer, Position new_pos){
        if(!laenger){//immer kürzen, außer wenn es länger werden soll
            schlange.remove(schlange.size()-1);
        }//if
        else{}//else 
        System.out.println("(bew)");
        
        if(kuerzer){//lösche ncoh eine Position (kuerzen)
            System.out.println("(kuerzen)");
            for(int i = 0; i < 3; i++) {
                if(!(schlange.size()-1 < Main.laenge)){
                    schlange.remove(schlange.size()-1);
                }
            }
        }//if
        
        schlange.add(0, new_pos);
    }//Methode
    
    public int getLaenge() {
        return schlange.size();
    }//Methode

    public Richtung getRichtung() {
        return richtung;
    }//Methode

    public void setRichtung(Richtung richtung) {
        this.richtung = richtung;
    }//Methode
    
    public Position getSnakePos(int index){
        return schlange.get(index);
    }
    
    public void setSnakePos(int index, Position pos){
        schlange.add(index, pos);
    }
    
    public boolean schlangeContains(Position pos){
        return schlange.contains(pos);
    }
    
    public int getlastIndexOf(Position pos){
        return schlange.lastIndexOf(pos);
    }
}
