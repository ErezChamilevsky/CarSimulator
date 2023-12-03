package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class RightSignalGUI extends RepresentGUI {
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int BLIND = 2;
    // public static final int GUIWIDTH = 600;
    // public static final int GUIHEIGHT = 800;
    public static final int WIDTH = 729;
    public static final int HEIGHT = 435;

    private boolean isRightSignalOn;
    // private Point point; //688,435
    private int counter;
    private boolean isBlind;

    public RightSignalGUI() {
        super(new Point(WIDTH,HEIGHT));
        this.isRightSignalOn = false;
        this.counter = 0;
    }

    public void setIsRightSignalOn(boolean signal) {
        this.isRightSignalOn = signal;
    }

    public void setRightBlind(boolean blind) {
        this.isBlind = blind;
    }

// *****************DRAWS*****************************
    
    @Override
    public void draw(DrawSurface d){
        drawRightSignal(d);
    }

    public void drawRightOn(DrawSurface d) {
        if (counter % 20 > 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/rightArrow.png"));
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        } else if (counter % 20 < 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/rightArrowDef.png"));
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        }
        this.counter++;
    }

    public void drawRightOff(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/rightArrowDef.png"));
            d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawRightBlind(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/blind.png"));
            d.drawImage((int) super.upLeft.getX() - 2, (int) super.upLeft.getY() + 9, img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawRightSignal(DrawSurface d) {
        if (!this.isRightSignalOn) {
            drawRightOff(d);
        } else if (this.isRightSignalOn) {
            drawRightOn(d);
        }
        if (this.isBlind) {
            drawRightBlind(d);
        }
    }
}
