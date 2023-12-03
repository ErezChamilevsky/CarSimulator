package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class CarGUI extends RepresentGUI {
    public static final int CARWIDTH = 57;
    public static final int CARHEIGHT = 125;
    
    public CarGUI(Point startingPoint){
        super(startingPoint);
    }

    @Override
    public void draw(DrawSurface d) {
        try {
                Image img = ImageIO.read(new File("src/Photos/car.png"));
                d.drawImage((int) this.upLeft.getX(), (int) this.upLeft.getY(), img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
    }

}
