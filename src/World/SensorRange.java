/**
 * The sensor will be a isosceles triangle.
 */

package World;

public class SensorRange {
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int MOSTOFCAR = 65;
    public static final int CARWIDTH = 37;

    public Point center;
    public int faceTo; // to where the sensor is face to, right,left,up,down
    public double range; // length of sides of the isosceles triangle
    public Point directioPointOne;
    public Point directioPointTwo;
    public double angle = 70.0;

    public SensorRange(int side, double range) {
        this.center = null;
        this.faceTo = side;
        this.range = range;
    }

    // There is an img of calculation of the two point of each line
    private void initDirection() {
        switch (faceTo) {
            case RIGHT:
                this.directioPointOne = new Point(center.getX() - range * Math.cos(angle / 2),
                        center.getY() - range * Math.sin(angle / 2));
                this.directioPointTwo = new Point(center.getX() - range * Math.cos(angle / 2),
                        center.getY() + range * Math.sin(angle / 2));
                break;
            case LEFT:
                this.directioPointOne = new Point(center.getX() + range * Math.cos(angle / 2),
                        center.getY() - range * Math.sin(angle / 2));
                this.directioPointTwo = new Point(center.getX() + range * Math.cos(angle / 2),
                        center.getY() + range * Math.sin(angle / 2));
                break;
        }
    }

    // for every point of the representing rectangle of the entity, checks if in
    // range
    public boolean isEntityInRange(World sprite) {
        return areLinesInRange(sprite) && areLinesInRange(sprite);
    }

    // img in documentation - brief - checks if center's y in middle of line's ys,
    // and if line's x is in middle of center's x and one of the sides' vertex's x
    private boolean areLinesInRange(World sprite) {
        for (Line l : sprite.getRectangle().borders) {
            if (l.getStrat().getY() < this.center.getY() &&
                    l.getEnd().getY() > this.center.getY()) {
                if (Math.abs(this.directioPointOne.getX() - this.center.getX()) >= Math
                        .abs(l.getStrat().getX() - this.directioPointOne.getX())
                        + Math.abs(l.getStrat().getX() - this.center.getX())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPoint(Point point) {
        if (this.faceTo == RIGHT) {
            this.center = new Point(point.getX() + CARWIDTH, point.getY() + MOSTOFCAR);
        } else {
            this.center = new Point(point.getX(), point.getY() + MOSTOFCAR);
        }
        // causes
        initDirection();
    }

// **************************
    // Checks if the area of the two partial triangles (with vertex point) bigger
    // than the range, full proof and
    // doc in an img
    public boolean isPointInRange(Point point) {
        Triangle original = new Triangle(this.center.gePoint(), this.directioPointOne.gePoint(),
                this.directioPointTwo.gePoint());
        return original.isPointInTriangle(point);
    }

    public boolean arePointsInRange(World sprite) {
        for (Point p : sprite.getRectangle().getPoints()) {
            if (isPointInRange(p)) {
                return true;
            }
        }
        return false;
    }

}
