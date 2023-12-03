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
}
