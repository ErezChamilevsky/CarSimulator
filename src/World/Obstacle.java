package World;

public class Obstacle implements World {

    private Rectangle representRectangle;

    public Obstacle(Point point, double width, double height){
        this.representRectangle = new Rectangle(point, width, height);
    }

    @Override
    public Rectangle getRectangle() {
        return this.representRectangle;
    }

    @Override
    public Point getUpLeft() {
        return this.getRectangle().getTopLeftPoint();
    }
    
    @Override
    public void move(){
        //do not do anything
    }

    @Override
    public void setPoint(Point point){
        this.getRectangle().setTopLeftPoint(point);
    }
}
