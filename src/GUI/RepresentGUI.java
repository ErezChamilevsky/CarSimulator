package GUI;

import World.Point;
import biuoop.DrawSurface;

public abstract class RepresentGUI {
    protected Point upLeft;

    protected RepresentGUI(Point point){
        this.upLeft = point;
    }

    abstract public void draw(DrawSurface d);

    public void setPoint(Point point){
        this.upLeft = point;
    }

    public Point getPoint(){
        return this.upLeft;
    }
    
}
