package World;

/**
 * Point
 */
public class Point{
    private double x;
    private double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //immutable
    public Point gePoint(){
        return new Point(this.x, this.y);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double distance(Point point){
        return Math.sqrt(Math.pow(y-point.getY(), 2) + Math.pow(x-point.getX(), 2));
    }
}