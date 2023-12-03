package GUI;

import java.awt.Color;

import World.Point;
import biuoop.DrawSurface;

public class BottomPanelGUI extends RepresentGUI {

    private double worldVelocity;

    protected BottomPanelGUI() {
        super(new Point(0,435));
    }

    @Override
    public void draw(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawRectangle(0, 435, 800, 50);
        d.fillRectangle(0, 435, 800, 50);
        d.setColor(Color.black);
        d.drawText(350, 465, (int)worldVelocity + " KMH", 20);
    }

    public void setVelocity(double velocity){
        this.worldVelocity = velocity;
    }
    
    
}
