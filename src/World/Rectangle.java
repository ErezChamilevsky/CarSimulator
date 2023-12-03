package World;

public class Rectangle {
    public Point[] points;
    public Line[] borders;
    public Point topLeftPoint;
    public double width;
    public double height;
    public double angle = 0.0; // default, straight rectangle

    public Rectangle(Point topLeftPoint, double width, double height) {
        this.topLeftPoint = topLeftPoint;
        this.width = width;
        this.height = height;
        this.points = new Point[4];
        this.borders = new Line[4];
        this.setRectangle();
    }

    // Constractor overloading for angle
    public Rectangle(Point topLeftPoint, double width, double height, double angle) {
        this.topLeftPoint = topLeftPoint;
        this.width = width;
        this.height = height;
        this.points = new Point[4];
        this.borders = new Line[4];
        this.setRectangle();
        this.angle = angle;
    }

    public void setTopLeftPoint(Point point) {
        this.topLeftPoint = point;
        this.setPoints();// every change of the topleft point changes the whole rectangle
        this.setBorders();
    }

    public Point getTopLeftPoint() {
        return this.topLeftPoint;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public Point[] getPoints() {
        return this.points;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Line[] getLines() {
        return this.borders;
    }

    private void setRectangle() {
        this.setPoints();
        this.setBorders();
    }

    // img of calculation in docu
    private void setPoints() {
        this.points[0] = this.topLeftPoint;
        this.points[1] = new Point(this.topLeftPoint.getX() + Math.cos(angle) * width,
                this.topLeftPoint.getY() - Math.sin(angle) * height);
        this.points[2] = new Point(this.topLeftPoint.getX() + Math.sin(angle) * height + Math.cos(angle) * width,
                this.topLeftPoint.getY() + Math.cos(angle) * height - Math.sin(angle) * width);
        this.points[3] = new Point(this.topLeftPoint.getX() + Math.sin(angle) * height,
                this.topLeftPoint.getY() + Math.cos(angle) * height);

    }

    private void setBorders() {
        this.borders[0] = new Line(this.points[0], this.points[1]);// leftup
        this.borders[1] = new Line(this.points[1], this.points[2]);// right up
        this.borders[2] = new Line(this.points[2], this.points[3]);// right down
        this.borders[3] = new Line(this.points[3], this.points[0]);// left down
    }

    // make the rectangle to two triangle, check if points in it
    // after, take the middle and check if it in one of triangle
    // needed to be changed casue the borders don't give true for collision
    public boolean isLineCrossRectangle(Line line) {

        Triangle one = new Triangle(this.points[0], this.points[1], this.points[2]);
        Triangle two = new Triangle(this.points[0], this.points[3], this.points[2]);

        if (one.isPointInTriangle(line.getStrat()) || one.isPointInTriangle(line.getEnd())
                || two.isPointInTriangle(line.getStrat()) || two.isPointInTriangle(line.getEnd())) {
            // one of the line's vertex inside the rectangle

            return true;
        }

        for (Line a : this.borders) {
            if (a.doesIntersect(line)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Rectangle bob = new Rectangle(new Point(554, 300), 37, 80);
        Line l2 = new Line(new Point(591, 14.760448830503073), new Point(591, 14.760448830503073 + 80));
        System.out.println(bob.isLineCrossRectangle(l2));

        bob = new Rectangle(new Point(557, 300), 37, 80);
        l2 = new Line(new Point(557 + 37, 557), new Point(557 + 37, 80 + 557));
        System.out.println(bob.isLineCrossRectangle(l2));

        bob = new Rectangle(new Point(557, 300), 37, 80);
        l2 = new Line(new Point(557 + 37, 557 + 80), new Point(557, 80 + 557));
        System.out.println(bob.isLineCrossRectangle(l2));

        bob = new Rectangle(new Point(557, 300), 37, 80);
        l2 = new Line(new Point(557, 557), new Point(557, 80 + 557));
        System.out.println(bob.isLineCrossRectangle(l2));
    }
}
