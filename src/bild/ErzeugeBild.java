/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bild;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineHelper;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngWriter;
import helfer.Typ;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Rudi
 */




/**
 *
 * @author Julian
 */
public class ErzeugeBild {

    //static int PICTURE_SIZE = 400;
    final int r = 15; //universaler Abstand
    final int h = 25;
    final int w = 75;
    int rand_oben = 2*r+h;
    int rand_unten = 4*r+3*h+20;
    int rand_links = r;
    int rand_rechts = r;

    public synchronized void _paintPicture(File outputStream, int groesseX, int groesseY) {
        ImageInfo imi = new ImageInfo(groesseX, groesseY, 8, false); // 8 bits per channel, no alpha
        // open image for writing to a output stream
        PngWriter png = new PngWriter(outputStream, imi);

        // hier könnt ihr das Bild verändern
        // TIPP: Befüllt einen Zweidimensionalen Array
        // und arbeitet diesen dann ab.
        // TIPP2: Ihr wollt am Ende mit Koordinaten Pixel setzen können!
        ///////////////////////////////////////////
        ImageLineInt iline = new ImageLineInt(imi);  // eine Zeile 

        Random r = new Random();
        Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

        // schreibt eine Spalte
        for (int row = 0; row < png.imgInfo.rows; row++) {
            for (int col = 0; col < imi.cols; col++) {
                if (col == imi.cols/2) {
                    ImageLineHelper.setPixelRGB8(iline, col, 100, 100, 100);
                
                } else {
                    ImageLineHelper.setPixelRGB8(iline, col, c.getRed(), c.getGreen(), c.getBlue());
                }
            
            }
            png.writeRow(iline);
        }
        // die Spalte wird in alle Zeilen geschrieben.
        //for (int row = 0; row < png.imgInfo.rows; row++) {
        //    png.writeRow(iline);
        //}
        ///////////////////////////////////////////
        png.end();
        

    }
    
    public synchronized Icon paintPicture(Typ[][] playingGround) {
        //System.out.println("ErzeugeBild.paintPicture-Aufruf");
        ImageInfo imi = new ImageInfo(playingGround.length, playingGround[0].length, 8, false); // 8 bits per channel, no alpha
        // open image for writing to a output stream
        File pic = new File(getPicPath("snake.jpg"));//
        PngWriter png = new PngWriter(pic, imi);
        ImageLineInt iline = new ImageLineInt(imi);  // eine Zeile 

        Random r = new Random();
        Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

        // schreibt eine Spalte
        for (int row = 0; row < png.imgInfo.rows; row++) {
            for (int col = 0; col < imi.cols; col++) {
                ImageLineHelper.setPixelRGB8(iline, col, playingGround[row][col].color.getRed(), playingGround[row][col].color.getGreen(), playingGround[row][col].color.getBlue());
            }
            png.writeRow(iline);
        }
        // die Spalte wird in alle Zeilen geschrieben.
        //for (int row = 0; row < png.imgInfo.rows; row++) {
        //    png.writeRow(iline);
        //}
        ///////////////////////////////////////////
        png.end();
        
        //File picture = new File(outputStream);
        Icon icon = new ImageIcon(pic.getPath());
        return icon;

    }
    
    public synchronized Icon startPicture(int groesseX, int groesseY) {
        //System.out.println("ErzeugeBild.startPicture-Aufruf");
        return oneColorPicture(groesseX, groesseY, 255, 255, 255);
    }
    
    public synchronized Icon generateBackground(int groesseX, int groesseY, int frameWidth, int frameHeight, int minusWidth, int minusHeight) {
        //System.out.println("ErzeugeBild.generateBackground-Aufruf");
        int frameW = frameWidth - minusWidth;
        int frameH = frameHeight - minusHeight;
        
        ImageInfo imi = new ImageInfo(frameW, frameH, 8, false); // 8 bits per channel, no alpha
        File pic = new File(getPicPath("snakeBG.jpg"));//
        PngWriter png = new PngWriter(pic, imi);
        ImageLineInt iline = new ImageLineInt(imi);  // eine Zeile 

        for (int row = 0; row < png.imgInfo.rows; row++) {
            if((row < rand_oben-5) || (row > frameH-rand_unten+7)){
                for (int col = 0; col < imi.cols; col++) {
                    ImageLineHelper.setPixelRGB8(iline, col, 0, 0, 0);
                }
            }
            if((row >= (rand_oben-5) && row <= (frameH-rand_unten+7) && row <= rand_oben) || ((row >= frameH-rand_unten)&&row<frameH-rand_unten+7) ){ //
             for (int col = 0; col < imi.cols; col++) {
                    if((col < rand_links-5) || (col > frameW-rand_rechts+5)){
                        ImageLineHelper.setPixelRGB8(iline, col, 0, 0, 0);
                    }
                    else {
                        ImageLineHelper.setPixelRGB8(iline, col, 0, 0, 255);
                    }
                }   
            }
            
            if((row > rand_oben) && (row < frameH-rand_unten)){
                for (int col = 0; col < imi.cols; col++) {
                    if((col < rand_links-5) || ((col > rand_links) && (col < frameW-rand_rechts)) || (col > frameW-rand_rechts+5)){ 
                        ImageLineHelper.setPixelRGB8(iline, col, 0, 0, 0);
                    }
                    else{
                        ImageLineHelper.setPixelRGB8(iline, col, 0, 0, 255);
                    }
                }
            } 
            
            png.writeRow(iline);
        }
        png.end();
        Icon icon = new ImageIcon(pic.getPath());
        return icon;
    }
    
    public synchronized Icon oneColorPicture(int groesseX, int groesseY, int red, int green, int blue) {
        //System.out.println("ErzeugeBild.oneColorPicture-Aufruf");
        ImageInfo imi = new ImageInfo(groesseX, groesseY, 8, false); // 8 bits per channel, no alpha
        File pic = new File(getPicPath("snake.jpg"));//
        PngWriter png = new PngWriter(pic, imi);
        ImageLineInt iline = new ImageLineInt(imi);  // eine Zeile 
        for (int row = 0; row < png.imgInfo.rows; row++) {
            for (int col = 0; col < imi.cols; col++) {
                ImageLineHelper.setPixelRGB8(iline, col, red, green, blue);
            }
            png.writeRow(iline);
        }
        png.end();
        Icon icon = new ImageIcon(pic.getPath());
        return icon;
    }
    
    public synchronized void copyPicture(File inputStream, File outputStream) throws IOException {
        FileUtils.copyFile(inputStream, outputStream);
    }
    
    public String getPicPath(){//String picname){
        //System.out.println("ErzeugeBild.getPicPath-Aufruf");
        return (System.getProperty("user.dir")+"\\src\\bild\\"+getFile()); //picname);
    }
    
    public String getPicPath(String picname){
        //System.out.println("ErzeugeBild.getPicPath-Aufruf");
        return (System.getProperty("user.dir")+"\\src\\bild\\"+picname);
    }
    
    public String getFile(){
        String forReturn;
        if(logik.Main.useOtherFile){
            forReturn = "snake1.jpg";
        }
        else {
            forReturn = "snake2.jpg";
        }
        if(logik.Main.useOtherFile){
            logik.Main.useOtherFile = false;
        }else {
            logik.Main.useOtherFile = true;
        }
                
        return forReturn;
    }
}

