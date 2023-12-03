package World;

public class Triangle {
    public Point p1;
    public Point p2;
    public Point p3;
    public double[] sides;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.sides = null;
    }

    public Point[] getPointsArray() {
        Point[] points = new Point[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        return points;
    }

    public double getArea() {
        if (this.sides == null) {
            this.sidesInit();
        }
        // Heron's formula
        return Math.sqrt((sides[0] + sides[1] + sides[2]) * (sides[0] + sides[1] - sides[2]) *
                (sides[0] - sides[1] + sides[2]) * (-sides[0] + sides[1] + sides[2])) / 4;
    }

    private void sidesInit() {
        this.sides = new double[3];
        sides[0] = p1.distance(p3);
        sides[1] = p1.distance(p2);
        sides[2] = p2.distance(p3);
    }

    public Point[] sortByDistanceFromPoint(Point point) {
        Point[] points = this.getPointsArray();
        for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < points.length - 1; j++) {
                if (points[j].distance(point) < points[j + 1].distance(point)) {
                    Point temp = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = temp;
                }
            }
        }
        return points;
    }

    // there is special case when the point continuation of one the lines
    public boolean isPointInTriangle(Point point) {
        //sorted by distance from point
        Point[] points = this.sortByDistanceFromPoint(point);

        // Triangle a = new Triangle(point.gePoint(), points[0], points[1]);
        // Triangle b = new Triangle(point.gePoint(), points[0], points[2]);

        // return a.getArea() + b.getArea() > this.getArea() ? false : true;
        double x = point.getX();
        double y = point.getY();

        double x1 = points[0].getX();
        double y1 = points[0].getY();
        double x2 = points[1].getX();
        double y2 = points[1].getY();
        double x3 = points[2].getX();
        double y3 = points[2].getY();

        double detT = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);
        double alpha = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / detT;
        double beta = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / detT;
        double gamma = 1 - alpha - beta;

        return alpha >= 0 && beta >= 0 && gamma >= 0 && alpha <= 1 && beta <= 1 && gamma <= 1;
    }
}