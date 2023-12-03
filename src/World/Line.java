package World;

public class Line {
    private Point start;
    private Point end;

    public Line(Point a, Point b) {
        this.start = a;
        this.end = b;
        lineCorrectorByY();
    }

    // start will always up
    private void lineCorrectorByY() {
        if (start.getY() > end.getY()) {
            Point temp = start.gePoint();
            this.start = this.end;
            this.end = temp;
        }
    }

    public Point getStrat() {
        return this.start.gePoint();
    }

    public Point getEnd() {
        return this.end.gePoint();
    }

    public Line getLine() {
        return new Line(this.start, this.end);
    }

    public double getLong() {
        return this.start.distance(this.end);
    }

    // ((x1+x2)/2, (y1+y2)/2)
    public Point getMiddle() {
        return new Point((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
    }

    public boolean doesIntersect(Line line) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = line.getEnd().getX();
        double y3 = line.getEnd().getY();
        double x4 = line.getStrat().getX();
        double y4 = line.getStrat().getY();

        double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (det == 0) {
            // Lines are parallel, no intersection
            return false;
        } else {
            double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / det;
            double s = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / det;

            return t >= 0 && t <= 1 && s >= 0 && s <= 1;
        }
    }
}
