package CarControl;

import World.Line;
import World.Npc;
import World.Point;
import World.Rectangle;
import World.World;

//singleton
public class Car {
    private static final Point STARTPOINT = new Point(455, 300);
    private static final double HEIGHT = 80;
    private static final double WIDTH = 37;
    private static final double VEL = 3;
    public static final int STAY = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;

    public static Car instance;
    public Rectangle representsRectangle;
    private double velocity;
    public boolean rightSignalOn = false;
    public boolean leftSignalOn = false;
    public boolean rightBlind = false;
    public boolean leftBlind = false;


    private Car() {
        this.representsRectangle = new Rectangle(STARTPOINT, WIDTH, HEIGHT);
        this.velocity = VEL;
        instance = this;
    }

    public static Car getInstance() {
        if (Car.instance == null) {
            new Car();
        }
        return instance;
    }

    public void setSignal(int side) {
        if (side == RIGHT) {
            this.rightSignalOn = !this.rightSignalOn;
        } else if (side == LEFT) {
            this.leftSignalOn = !this.leftSignalOn;
        }
    }

    public boolean isRightSignalOn(){
        return this.rightSignalOn;
    }

    public boolean isLeftSignalOn(){
        return this.leftSignalOn;
    }

    public Point drive(int side) {
        if (side == RIGHT) {
            this.representsRectangle
                    .setTopLeftPoint(new Point(this.representsRectangle.getTopLeftPoint().getX() + this.velocity,
                            this.representsRectangle.getTopLeftPoint().getY()));
        } else if (side == LEFT) {
            this.representsRectangle
                    .setTopLeftPoint(new Point(this.representsRectangle.getTopLeftPoint().getX() - this.velocity,
                            this.representsRectangle.getTopLeftPoint().getY()));
        }
        return this.representsRectangle.getTopLeftPoint();
    }

    public Point getPoint() {
        return this.representsRectangle.getTopLeftPoint();
    }

    public boolean doesCollide(World sprite){
        for(Line a : sprite.getRectangle().getLines()){
            if(this.representsRectangle.isLineCrossRectangle(a)){
                System.out.println(representsRectangle.getTopLeftPoint().getX()+ "," +representsRectangle.getTopLeftPoint().getY());
                System.out.println(a.getStrat().getX() + "," + a.getStrat().getY());
                System.out.println(a.getEnd().getX() + "," + a.getEnd().getY());
                return true;
            }
        }
        return false;
    } 

    public void setPoint(Point point){
        this.representsRectangle.topLeftPoint = point;
    }

    public static void main(String[] args) {
        World sprite = new Npc(new Point(557,557), WIDTH, HEIGHT);
        Car car = Car.getInstance();
        car.setPoint(new Point(557,300));
        System.out.println(car.doesCollide(sprite));
        
    }
}
