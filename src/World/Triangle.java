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

    public double getArea() {
        if (this.sides == null) {
            this.sidesInit();
        }
        //Heron's formula
        return Math.sqrt((sides[0] + sides[1] + sides[2]) * (sides[0] + sides[1] - sides[2]) *
                (sides[0] - sides[1] + sides[2]) * (-sides[0] + sides[1] + sides[2])) / 4;
    }

    private void sidesInit() {
        this.sides = new double[3];
        sides[0] = p1.distance(p3);
        sides[1] = p1.distance(p2);
        sides[2] = p2.distance(p3);
    }

    //there is special case when the point continuation of one the lines 
    public boolean isPointInTriangle(Point point){
        Triangle a = new Triangle(point.gePoint(), this.p1, this.p2);
        Triangle b = new Triangle(point.gePoint(), this.p1, this.p3);
        
        return a.getArea() + b.getArea() > this.getArea() ? false : true;
    }
}