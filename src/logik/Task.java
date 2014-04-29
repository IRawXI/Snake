/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logik;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rudi
 */
public class Task extends Thread {
   
    //Vielleicht ein bisschen gehackt --> ja leicht, aber das ist ok
    
    //private final File deployStream;

    public Task(File file) {
        //this.deployStream = file;
    }

    Task() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        

    public void run() {
//        Snake snake = new Snake(new Position(25,25),5, Facing.LEFT);
//        PlayingGround playingGround = new PlayingGround(100,100,snake);
        while (true) {
//            CreatePicture instance = new CreatePicture();
            //instance.paintPicture(deployStream,playingGround.getPlayingGround()); //Size von dem Feld ist unabhängig von der größe des Pictures
//            instance.paintPicture(deployStream);
                       
            // das hier muss in die paintPictureMethode und dort als Datenbankzugriff implementiert werden
            //snake.setFacing(Task.facing);                        
//            snake.move(false);
//            boolean gameRunning = playingGround.update();
//            if (!gameRunning) {
//                //return;
//            }            

//            ItemController itemController = new ItemController();
//            itemController.spawnItems();
            
            try {
                this.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isInterrupted()) {
                return;
            }
        }
    }

//    public void valueBound(HttpSessionBindingEvent event) {
//        start(); // Will instantly be started when doing session.setAttribute("task", new Task());
//    }

//    public void valueUnbound(HttpSessionBindingEvent event) {
//        interrupt(); // Will signal interrupt when session expires.
//    }
}
