package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class PanelGui {
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int BLIND = 2;
    public static final int GUIWIDTH = 600;
    public static final int GUIHEIGHT = 800;

    private int rightSignal;
    private int leftSignal;
    private Point point;
    private int counter;

    public PanelGui() {
        this.rightSignal = OFF;
        this.leftSignal = OFF;
        this.counter = 0;
    }

    public void setRightSignal(int signal) {
        this.rightSignal = signal;
    }

    public void setLefttSignal(int signal) {
        this.leftSignal = signal;
    }
    
    public void setPoint(Point point){
        this.point = point;
    }

// *****************DRAWS*****************************

    public void draw(DrawSurface d){
        drawRightSignal(d);
        drawleftSignal(d);
    }

    public void drawRightOn(DrawSurface d) {
        if (counter % 20 > 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/rightArrow.png"));
                d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        } else if (counter % 20 < 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/rightArrowDef.png"));
                d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
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
            d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawRightBlind(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/rightArrowBlind.png"));
            d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawRightSignal(DrawSurface d) {
        if (this.rightSignal == OFF) {
            drawRightOff(d);
        } else if (this.rightSignal == ON) {
            drawRightOn(d);
        } else if (this.rightSignal == BLIND) {
            drawRightSignal(d);
        }
    }
    // **************************************

    public void drawleftOn(DrawSurface d) {
        if (counter % 20 > 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/leftArrow.png"));
                d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
        } else if (counter % 20 < 10) {
            try {
                Image img = ImageIO.read(new File("src/Photos/leftArrowDef.png"));
                d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
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
            d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawleftBlind(DrawSurface d) {
        try {
            Image img = ImageIO.read(new File("src/Photos/leftArrowBlind.png"));
            d.drawImage((int) this.point.getX(), (int) this.point.getY(), img);
        } catch (IOException e) {
            // handle image loading errors here
            e.printStackTrace();
        }
    }

    public void drawleftSignal(DrawSurface d) {
        if (this.leftSignal == OFF) {
            drawleftOff(d);
        } else if (this.leftSignal == ON) {
            drawleftOn(d);
        } else if (this.leftSignal == BLIND) {
            drawleftSignal(d);
        }
    }


}
