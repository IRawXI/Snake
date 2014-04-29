/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oberflaeche;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 22.11.2013
  * @author 
  */
import bild.ErzeugeBild;
import helfer.Richtung;
import helfer.SafeIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
 
public class Oberflaeche extends JFrame {
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton6 = new JButton();
    private JButton jButton7 = new JButton();
    private JButton jButton8 = new JButton();
    private JButton jButton9 = new JButton();
    private JLabel jLabel0 = new JLabel();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
  
  
    int frameW_ohneSpielfeld;
    int frameH_ohneSpielfeld;
    int frameWidth;
    int frameHeight;
    int groesseX;
    int groesseY;
    int frameInnerWidth;
    int frameInnerHeight;
  
    private Richtung direction;
    private boolean run = false;
    private boolean start = false;
    private boolean restart = false;
  
    public Oberflaeche(String title, int groesseX, int groesseY, Richtung startrichtung) {
        super(title);
        
        direction = startrichtung;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.groesseX = groesseX;
        this.groesseY = groesseY;

        //andere Variablen zum Positionsausdruck anlegen
        final int r = 15; //universaler Abstand
        final int h = 25;
        final int w = 75;

        final int jLabel1W = 40;
        final int jLabel2W = 70;
        final int jLabel3W = 34;
        final int jLabel4W = 50;

        final int editorPaneX = groesseX;
        final int editorPaneY = groesseY;
        final int frameW1 = editorPaneX + 2 * r;
        final int frameW2 = 2 * r + 2 * w + h + jLabel1W + jLabel2W + jLabel3W + jLabel4W;
        int frameW;


        //Breitenvergleich Zielfeld && Buttonbreite

        if(frameW1>frameW2){
            frameW = frameW1;
            frameW_ohneSpielfeld = frameW - editorPaneX;
        }
        else{
            frameW = frameW2;
            frameW_ohneSpielfeld = frameW - (2 * r);
        }
        int frameH = 4 * r + 4 * h + editorPaneY;

        frameH_ohneSpielfeld = 4 * r + 4 * h;

        int minusWidth = r/2;
        int minusHeigth = r+12;
        frameWidth = frameW + minusWidth; //Ausgleich des Randes 
        frameHeight = frameH + minusHeigth;

        //int frameWidth = 800; 
        //int frameHeight = 600;
        setSize(frameWidth, frameHeight);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 10;
        int y = (d.height - getSize().height) / 10;
        setLocation(x, y);

        setResizable(false);

        /* 
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu datei = new JMenu("Datei");
        JMenu bearbeiten = new JMenu("Bearbeiten");
        menuBar.add(datei);
        menuBar.add(bearbeiten);  

        datei.add(new JMenuItem("Neu"));
        datei.add(new JMenuItem("Öffnen"));
        datei.addSeparator();
        datei.add(new AbstractAction("Titel ändern") {
        public void actionPerformed(ActionEvent e) {
        setTitle("NEUNEUNEU");
        }
        });
        */

        ErzeugeBild bggenerator = new ErzeugeBild();
        setContentPane(new BackGroundPane(bggenerator.generateBackground(groesseX, groesseY, frameWidth, frameHeight, minusWidth, minusHeigth)));//C:/Users/Rudi/Desktop

        Container cp = getContentPane();
        cp.setLayout(null);


        // Anfang Komponenten

        jButton1.setBounds(r, r, w, h);
        jButton1.setText("Speichern");
        jButton1.setForeground(Color.black);
        jButton1.setBackground(Color.white);
        jButton1.setMargin(new Insets(2, 2, 2, 2));
        jButton1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton1_ActionPerformed(evt);
            }
        });
        cp.add(jButton1);
        jButton2.setBounds(2*r+w, r, w, h);
        jButton2.setText("Laden");
        jButton2.setForeground(Color.black);
        jButton2.setBackground(Color.white);
        jButton2.setMargin(new Insets(2, 2, 2, 2));
        jButton2.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton2_ActionPerformed(evt);
            }
        });
        cp.add(jButton2);
        jButton3.setBounds(r, 3*r+editorPaneY+h, w, h);
        jButton3.setText("Start");
        jButton3.setForeground(Color.black);
        jButton3.setBackground(Color.white);
        jButton3.setMargin(new Insets(2, 2, 2, 2));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                jButton3_ActionPerformed(evt);
            }
        });
        cp.add(jButton3);
        jButton4.setBounds(frameW-r-w, frameH-r-h, w, h);
        jButton4.setText("Neustart");
        jButton4.setForeground(Color.black);
        jButton4.setBackground(Color.white);
        jButton4.setMargin(new Insets(2, 2, 2, 2));
        jButton4.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton4_ActionPerformed(evt);
            }
        });
        cp.add(jButton4);
        jButton5.setBounds((frameW/2-h/2), 3*r+editorPaneY+2*h, h, h);
        Icon ic = new ImageIcon(picturePath("play.jpg"));
        jButton5.setIcon(ic);
        //jButton5.setText("||");
        jButton5.setMargin(new Insets(2, 2, 2, 2));
        jButton5.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton5_ActionPerformed(evt);
            }
        });
        cp.add(jButton5);
        jButton6.setBounds((frameW/2-h/2)-w+1, 3*r+editorPaneY+2*h, w, h);
        jButton6.setText("<");
        jButton6.setForeground(Color.black);
        jButton6.setBackground(Color.red);
        jButton6.setMargin(new Insets(2, 2, 2, 2));
        jButton6.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton6_ActionPerformed(evt);
            }
        });
        cp.add(jButton6);
        jButton7.setBounds((frameW/2-w/2), 3*r+editorPaneY+3*h-1, w, h);
        jButton7.setText("v");
        jButton7.setForeground(Color.black);
        jButton7.setBackground(Color.red);
        jButton7.setMargin(new Insets(2, 2, 2, 2));
        jButton7.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton7_ActionPerformed(evt);
            }
        });
        cp.add(jButton7);
        jButton8.setBounds((frameW/2+h/2), 3*r+editorPaneY+2*h, w, h);
        jButton8.setText(">");
        jButton8.setForeground(Color.black);
        jButton8.setBackground(Color.red);    
        jButton8.setMargin(new Insets(2, 2, 2, 2));
        jButton8.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton8_ActionPerformed(evt);
            }
        });
        cp.add(jButton8);
        jButton9.setBounds((frameW/2-w/2), 3*r+editorPaneY+h+1, w, h);
        jButton9.setText("^");
        jButton9.setForeground(Color.black);
        jButton9.setBackground(Color.red);
        jButton9.setMargin(new Insets(2, 2, 2, 2));
        jButton9.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                jButton9_ActionPerformed(evt);
            }
        });
        cp.add(jButton9);

        jLabel0.setBounds(r +1, 2 * r + h +1, editorPaneX, editorPaneY);
        cp.add(jLabel0);
        jLabel1.setBounds(r, 3 * r + editorPaneY + 3 * h + 4, jLabel1W, 20);
        jLabel1.setText("Score: ");
        jLabel1.setForeground(Color.green);
        cp.add(jLabel1);
        jLabel2.setBounds(r + jLabel1W, 3 * r + editorPaneY + 3 * h + 4, jLabel2W, 20);
        jLabel2.setText("0123456789");
        jLabel2.setForeground(Color.green);
        cp.add(jLabel2);
        jLabel3.setBounds(frameW - r - jLabel4W - jLabel3W, 3 * r + editorPaneY + h, jLabel3W, 20);
        jLabel3.setText("Time: ");
        jLabel3.setForeground(Color.green);
        cp.add(jLabel3);
        jLabel4.setBounds(frameW - r - jLabel4W, 3 * r + editorPaneY + h, jLabel4W, 20);
        jLabel4.setText("01:45");
        jLabel4.setForeground(Color.green);
        cp.add(jLabel4);   


        
        ErzeugeBild generator = new ErzeugeBild();
        jLabel0.setIcon(generator.startPicture(groesseX, groesseY));
        

        setVisible(true);
    }

    public Richtung getDirection() {
        return direction;
    }

    public void setDirection(Richtung direction) {
        this.direction = direction;
    }
    
    public boolean isRun() {
        return run;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isRestart() {
        return restart;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    public int getFrameW_ohneSpielfeld() {
        return frameW_ohneSpielfeld;
    }

    public int getFrameH_ohneSpielfeld() {
        return frameH_ohneSpielfeld;
    }

    public void setPlayingGround(Icon image){
        ((ImageIcon)jLabel0.getIcon()).getImage().flush();
        jLabel0.setIcon(image);
        //System.out.println(image.toString());
        //System.out.println("===================> setPlayingGround-Aufruf");
    }
    
    private String picturePath(String picname) {
        ErzeugeBild generator = new ErzeugeBild();
        return (generator.getPicPath(picname));
    }

    public void jButton1_ActionPerformed(ActionEvent evt){ // "Speichern"

    } // end of jButton1_ActionPerformed

    public void jButton2_ActionPerformed(ActionEvent evt) { // "Laden"
        //File picture = new File("C:/Users/Rudi/Desktop/snake2.jpg");
        //ErzeugeBild generator = new ErzeugeBild();
        //generator.oneColorPicture(picture, groesseX, groesseY, 0, 0, 0);
        //Icon newIcon = new ImageIcon("C:/Users/Rudi/Desktop/snake2.jpg");
        //jLabel0.setIcon(newIcon);
    } // end of jButton2_ActionPerformed

    public void jButton3_ActionPerformed(ActionEvent evt) { // "Start"
        start = true;
        jLabel2.setText("start=true");
    } // end of jButton3_ActionPerformed

    public void jButton4_ActionPerformed(ActionEvent evt) { // "Neustart"
        restart = true;
    } // end of jButton4_ActionPerformed

    public void jButton5_ActionPerformed(ActionEvent evt) { // "||"
        if (run == false){
            run = true;
            Icon ic = new ImageIcon(picturePath("pause.jpg"));
            jButton5.setIcon(ic);
        }
        else {
            if (run == true){
                run = false;
                Icon ic = new ImageIcon(picturePath("play.jpg"));
                jButton5.setIcon(ic);
            }
            else {
            } 
        }       
    } // end of jButton5_ActionPerformed

    public void jButton6_ActionPerformed(ActionEvent evt) { // "<"
        if(logik.Main.direction_ATM != Richtung.RECHTS){
            direction = Richtung.LINKS;
        }
    } // end of jButton6_ActionPerformed

    public void jButton7_ActionPerformed(ActionEvent evt) { // "v"
        if(logik.Main.direction_ATM != Richtung.OBEN){
            direction = Richtung.UNTEN;
        }
    } // end of jButton7_ActionPerformed

    public void jButton8_ActionPerformed(ActionEvent evt) { // ">"
        if(logik.Main.direction_ATM != Richtung.LINKS){
            direction = Richtung.RECHTS;
        }
    } // end of jButton7_ActionPerformed

    public void jButton9_ActionPerformed(ActionEvent evt) { // "^"
        if(logik.Main.direction_ATM != Richtung.UNTEN){
            direction = Richtung.OBEN;
        }
    } // end of jButton7_ActionPerformed

    class BackGroundPane extends JPanel {
        Image img = null;
        BackGroundPane(String imagefile) {
            if (imagefile != null) {
                MediaTracker mt = new MediaTracker(this);
                img = Toolkit.getDefaultToolkit().getImage(imagefile);
                mt.addImage(img, 0);
                try {
                    mt.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        BackGroundPane(Icon icon) {
            icon = new SafeIcon(icon);
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            icon.paintIcon(new JPanel(), image.getGraphics(), 0, 0);
            if (image != null) {
                MediaTracker mt = new MediaTracker(this);
                img = image;
                mt.addImage(img, 0);
                try {
                    mt.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
        }
    }
}
