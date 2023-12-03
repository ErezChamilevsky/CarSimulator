package World;

import java.util.Random;

public class Npc implements World {
    public static final double VELBOUND = 2;
    public static final double VELMIN = 0.5;

    private Rectangle representRectangle;
    private Point point;
    private double width;
    private double height;
    private double velocity;

    public Npc(Point point, double width, double height) {
        this.representRectangle = new Rectangle(point, width, height);
        this.point = point;
        this.height = height;
        this.width = width;
        velocityInit(VELBOUND);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(point, width, height);
    }

    @Override
    public Point getUpLeft() {
        return this.point.gePoint();
    }

    public void setVelocity(double bound) {
        velocityInit(bound);
    }

    private void velocityInit(double bound) {
        Random r = new Random();
        this.velocity = r.nextDouble(VELMIN, bound);
    }

    public void move(){
        this.point = new Point(this.getUpLeft().getX(), this.getUpLeft().getY() + velocity);
    } 
}
