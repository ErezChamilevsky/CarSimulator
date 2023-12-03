package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class LeftSignalGUI extends RepresentGUI {
    
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int BLIND = 2;
    public static final int WIDTH = 17;
    public static final int HEIGHT = 439;

    private boolean isLeftSignalOn;
    private boolean isBlind;
    // private Point point;//17,435
    private int counter;

    public LeftSignalGUI() {
        super(new Point(WIDTH,HEIGHT));
        this.isLeftSignalOn = false;
        this.counter = 0;
    }
    
    public void setLefttSignal(boolean signal) {
        this.isLeftSignalOn = signal;
    }
    
    public void setLeftBlind(boolean blind){
        this.isBlind = blind;
    }

    @Override
    public void draw(DrawSurface d){
        drawleftSignal(d);
    }

    public void drawleftOn(DrawSurface d) {
        if (counter % 20 > 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/leftArrow.png"));
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        } else if (counter % 20 < 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/leftArrowDef.png"));
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        }
        this.counter++;
    }

    public void drawleftOff(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/leftArrowDef.png"));
            d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawleftBlind(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/blind.png"));
            d.drawImage((int) super.upLeft.getX() + 40, (int) super.upLeft.getY() + 7, img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawleftSignal(DrawSurface d) {
        if (!this.isLeftSignalOn) {
            drawleftOff(d);
        } else if (this.isLeftSignalOn) {
            drawleftOn(d);
        } 
        if(this.isBlind){
            drawleftBlind(d);
        }
    }
}
