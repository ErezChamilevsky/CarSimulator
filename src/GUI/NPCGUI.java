package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import World.Point;
import biuoop.DrawSurface;

public class NPCGUI extends RepresentGUI {

    private Image image;

    protected NPCGUI(Point point) {
        super(point);
        try {
            imgRandom();
        } catch (IOException e) {
            // nothing happen cause this is npc
        }

    }

    private void imgRandom() throws IOException {
        Random r = new Random();
        int choose = r.nextInt(4);
        switch (choose) {
            case 1:
                this.image = ImageIO.read(new File("src/Photos/car1.png"));
                break;

            case 2:
                this.image = ImageIO.read(new File("src/Photos/car2.png"));
                break;

            case 3:
                this.image = ImageIO.read(new File("src/Photos/car3.png"));
                break;

            case 4:
                this.image = ImageIO.read(new File("src/Photos/car4.png"));
                break;

        }
    }

    @Override
    public void draw(DrawSurface d) {
        d.drawImage((int) super.upLeft.getX(), (int) super.upLeft.getY(), this.image);
    }

    @Override
    public boolean isImageNull(){
        if(this.image == null){
            return true;
        }
        return super.isImageNull();
    }    
}
