/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logik;

import basis.Item;
import basis.Schlange;
import basis.Spielfeld;
import bild.ErzeugeBild;
import com.sun.jmx.snmp.tasks.Task;
import helfer.FieldMapper;
import helfer.Itemtyp;
import helfer.MergeObjects;
import helfer.Position;
import helfer.Richtung;
import helfer.Typ;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import oberflaeche.Oberflaeche;

/**
 *
 * @author Rudi
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    private static final int x = 40;
    private static final int y = 40;
    
    public static final int laenge = 4;
    private static final int first_x = x/2;
    private static final int first_y = y/2;
    private static final Richtung richtung = Richtung.LINKS;
    
    private static boolean laenger = false;
    private static boolean kuerzer = false;
    private static boolean schneller = false;
    private static final int schneller_runden = 20;
    private static int schneller_runden_übrig = 0;
        
    private static boolean item_laenger_pausedRound = false;
    private static final int item_kuerzer_spawnAgain_after = 250;
    private static final int item_schneller_spawnAgain_after = 500;
    private static int item_kuerzer_spawnAgain_in = 250;
    private static int item_schneller_spawnAgain_in = 500;
    
    private static final int item_laenger_despawn_after = 750;
    private static final int item_kuerzer_despawn_after = 500;
    private static final int item_schneller_despawn_after = 500;
    private static int item_laenger_despawn_in = 750;
    private static int item_kuerzer_despawn_in = 500;
    private static int item_schneller_despawn_in = 500;
    
    private static final int sleeptime = 375;
    
    private static final int factor = 10;
    
    public static Richtung direction_ATM;
    
    public static boolean useOtherFile = false;
    
    private static Schlange snake;
    private static Spielfeld ground;
    private static ArrayList<Item> items;
    private static Oberflaeche oberflaeche;    
        
    public static void main(String[] args) {
        //Neues Spielfeldobjekt
        ground = new Spielfeld(x, y);
        
        //Neues Schlangenobjekt
        snake = new Schlange(laenge, richtung, first_x, first_y);
        
        //Neue Items-ArrayList
        items = new ArrayList<>();
        //items.add(new Item(1, 3, Itemtyp.SCHNELLER));
        //items.add(new Item(4, 4, Itemtyp.LAENGER));
        //items.add(new Item(2, 1, Itemtyp.KUERZER));
        //ENDE
        
        MergeObjects mo = new MergeObjects();
        Typ[][] mappedGround = new FieldMapper().map(mo.merge(ground, snake, items), factor, snake);
        
        oberflaeche = new Oberflaeche("Snake", mappedGround.length, mappedGround[0].length, richtung);
        direction_ATM = richtung;
        
        ErzeugeBild generator = new ErzeugeBild();
        oberflaeche.setPlayingGround(generator.paintPicture(mappedGround));
                
        checkStart();
    }   
    
    public static void initialiseStartparameters(Oberflaeche oberflaeche){
        //Neues Spielfeldobjekt
        ground = new Spielfeld(x, y);
        
        //Neues Schlangenobjekt
        snake = new Schlange(laenge, richtung, first_x, first_y);
        
        //Neue Items-ArrayList
        items = new ArrayList<>();
        items.add(new Item(1, 3, Itemtyp.SCHNELLER));
        items.add(new Item(4, 4, Itemtyp.LAENGER));
        items.add(new Item(2, 1, Itemtyp.KUERZER));
        //ENDE
        
        MergeObjects mo = new MergeObjects();
        Typ[][] mappedGround = new FieldMapper().map(mo.merge(ground, snake, items), factor, snake);
        
        //Oberflaeche oberflaeche = new Oberflaeche("Snake", mappedGround.length, mappedGround[0].length, Richtung.OBEN);
        
        ErzeugeBild generator = new ErzeugeBild();
        oberflaeche.setPlayingGround(generator.paintPicture(mappedGround));
                
        //oberflaeche.setPlayingGround(generator.startPicture(mappedGround.length, mappedGround[0].length));
                
        checkRun();
    }
    
    public static void checkStart(){
        do {
            if(oberflaeche.isStart()){
                oberflaeche.setStart(false);
                checkRun();
            }
        } while (!oberflaeche.isStart());
    }
    
    public static void checkRun(){
        do {
            if(oberflaeche.isRun()){
                schleife();
            }
        } while (!oberflaeche.isRun());
        
    }
      
    public static void schleife(){
        while(oberflaeche.isRun()){
            int new_x = 0;
            int new_y = 0;
            Richtung r = oberflaeche.getDirection();
            switch(r){
                case OBEN:
                    new_x = snake.getSnakePos(0).getPosX()-1;
                    new_y = snake.getSnakePos(0).getPosY();
                    break;
                case UNTEN:
                    new_x = snake.getSnakePos(0).getPosX()+1;
                    new_y = snake.getSnakePos(0).getPosY();
                    break;
                case LINKS:
                    new_x = snake.getSnakePos(0).getPosX();
                    new_y = snake.getSnakePos(0).getPosY()-1;
                    break;
                case RECHTS:
                    new_x = snake.getSnakePos(0).getPosX();
                    new_y = snake.getSnakePos(0).getPosY()+1;
                    break;
            }
            direction_ATM = r;
            snake.setRichtung(r);
            collision(new_x, new_y);
        }
        checkRun();
        
    }
    
    public static void collision(int new_x, int new_y){
        if(new_x < 0 || new_x >= ground.getLength() || new_y < 0 || new_y >= ground.get0Length()){
            oberflaeche.setjLabel5Text("You failed!");
            checkRestart();
        }
        else{
            boolean failed = false;
            for(int a = 0; a < snake.getLaenge()-1; a++){
                if(new_x == snake.getSnakePos(a).getPosX() && new_y == snake.getSnakePos(a).getPosY()){
                    failed = true;
                    oberflaeche.setjLabel5Text("You failed!");
                    checkRestart();
                }
            }
            for(int b = 0; b < items.size(); b++){
                if(items.get(b).getPosition().getPosX()==new_x && items.get(b).getPosition().getPosY()==new_y) {
                    switch(items.get(b).getItemtyp()){
                        case LAENGER:
                            laenger = true;
                            items.remove(b);
                            item_laenger_pausedRound = false;
                            break;
                        case KUERZER: 
                            kuerzer = true; 
                            items.remove(b);
                            item_kuerzer_spawnAgain_in = item_kuerzer_spawnAgain_after;
                            break;
                        case SCHNELLER: 
                            schneller = true;
                            items.remove(b);
                            item_schneller_spawnAgain_in = item_schneller_spawnAgain_after;
                            schneller_runden_übrig = schneller_runden;
                            break;
                    }
                }
                    
            }
            if(!failed){       
                makeMove(new_x, new_y);
            }
        }
        
        /*
        try{
            makeMove(snake, ground, items, oberflaeche, new_x, new_y, factor);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("==> YOU FAILED!!");
        }
        */
    }
    
    public static void makeMove(int new_x, int new_y){
        
        snake.bewegen(laenger, kuerzer, new_x, new_y);
        laenger = false;
        kuerzer = false;
        
        itemspawner();
    }
    
    public static void itemspawner(){
        boolean laenger_exists = false;
        boolean kuerzer_exists = false;
        boolean schneller_exists = false;
        
        for(int b = 0; b < items.size(); b++){
            switch(items.get(b).getItemtyp()){
                case LAENGER:
                    laenger_exists = true;
                    break;
                case KUERZER: 
                    kuerzer_exists = true; 
                    break;
                case SCHNELLER: 
                    schneller_exists = true;
                    break;
            }
        }
        
        //ITEM LAENGER
        if(!laenger_exists){
            if(item_laenger_pausedRound){
                Position pos;
                do {
                    pos = randomPosition();
                } while (!testPosition(pos));
                items.add(new Item(pos, Itemtyp.LAENGER));
                item_laenger_despawn_in = item_laenger_despawn_after;
            }else{
                item_laenger_pausedRound = true;
            }
        }else{}
        
        //ITEM KUERZER
        if(!kuerzer_exists){
            if(item_kuerzer_spawnAgain_in <= 0) {
                Position pos;
                do {
                    pos = randomPosition();
                } while (!testPosition(pos));
                items.add(new Item(pos, Itemtyp.KUERZER));
                item_kuerzer_despawn_in = item_kuerzer_despawn_after;
            }else { 
                item_kuerzer_spawnAgain_in-= new Random().nextInt(10);
            }
        }else{}
        
        //ITEM SCHNELLER
        if(!schneller_exists){
            if(item_schneller_spawnAgain_in <= 0) {
                Position pos;
                do {
                    pos = randomPosition();
                } while (!testPosition(pos));
                items.add(new Item(pos, Itemtyp.SCHNELLER));
                item_schneller_despawn_in = item_schneller_despawn_after;
            }else {            
                item_schneller_spawnAgain_in-= new Random().nextInt(10);
            }
        }else{}
        
        itemDespawner(laenger_exists, kuerzer_exists, schneller_exists);
        //showMove(new_x, new_y);
    }
    
    public static void itemDespawner(boolean laenger_exists, boolean kuerzer_exists, boolean schneller_exists){
        
        oberflaeche.setjLabel5Text("Länger: "+item_laenger_despawn_in+" Kürzer: "+item_kuerzer_despawn_in+" Schneller: "+item_schneller_despawn_in);
        
        //ITEM LAENGER
        if(laenger_exists){
            if(item_laenger_despawn_in <= 0){
                for(int c = 0; c < items.size(); c++){
                    if(items.get(c).getItemtyp() == Itemtyp.SCHNELLER){
                        items.remove(c);
                    }
                }
            }else{
                item_laenger_despawn_in-= new Random().nextInt(10);
            }
        }else{}
        
        //ITEM KUERZER
        if(kuerzer_exists){
            if(item_kuerzer_despawn_in <= 0) {
                for(int c = 0; c < items.size(); c++){
                    if(items.get(c).getItemtyp() == Itemtyp.SCHNELLER){
                        items.remove(c);
                    }
                }
            }else { 
                item_kuerzer_despawn_in-= new Random().nextInt(10);
            }
        }else{}
        
        //ITEM SCHNELLER
        if(schneller_exists){
            if(item_schneller_despawn_in <= 0) {
                for(int c = 0; c < items.size(); c++){
                    if(items.get(c).getItemtyp() == Itemtyp.SCHNELLER){
                        items.remove(c);
                    }
                }
            }else {            
                item_schneller_despawn_in-= new Random().nextInt(10);
            }
        }else{}
        
        
        showMove();
    }
    
    public static void showMove(){
        oberflaeche.setPlayingGround(new ErzeugeBild().paintPicture(new FieldMapper().map(new MergeObjects().merge(ground, snake, items), factor, snake)));//mappedGround
         
        sleep();
    }
    
    public static void sleep(){
        if(schneller){
            schneller_runden_übrig--;
            if(schneller_runden_übrig == 0){
                schneller = false;
            } else{}
            try {
                Thread.sleep(sleeptime/2);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        else{
            try {
                Thread.sleep(sleeptime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static void checkRestart(){
        do {
            if(oberflaeche.isRestart()){
                oberflaeche.setRestart(false);
                //oberflaeche.setRun(false);
                initialiseStartparameters(oberflaeche);
            }
        } while (!oberflaeche.isRestart());
    }
    
    
    
    
    public static Position randomPosition(){
        Random r = new Random();
        return new Position(r.nextInt(x-1), r.nextInt(y-1));
    }
    
    public static boolean testPosition(Position pos) {
        boolean goodPosition = true;
        for(int a = 0; a < snake.getLaenge()-1; a++){
            if(pos.getPosX() == snake.getSnakePos(a).getPosX() && pos.getPosX() == snake.getSnakePos(a).getPosY()){
                goodPosition = false;
            }
        }
        for(int b = 0; b < items.size(); b++){
            if(items.get(b).getPosition().getPosX() == pos.getPosX() && items.get(b).getPosition().getPosY() == pos.getPosY()) {
                goodPosition = false;
            } 
        }
        return goodPosition;
    }
    
}
