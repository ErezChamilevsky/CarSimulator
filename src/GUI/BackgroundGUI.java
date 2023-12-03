package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class BackgroundGUI extends RepresentGUI {
    public static final int GUIHEIGHT = 800;
    public static final int GUIWIDTH = 600;
    //800X435

    public BackgroundGUI(Point point) {
        super(point);
    }


    @Override
    public void draw(DrawSurface d) {
        try {
                Image img = ImageIO.read(new File("src/Photos/background.png"));
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), img);
                
                //second
                d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY() + 435, img);
            } catch (IOException e) {
                // handle image loading errors here
                e.printStackTrace();
            }
    }
    
}
