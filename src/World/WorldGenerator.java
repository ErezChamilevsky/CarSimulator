package World;

import java.util.ArrayList;
import java.util.Random;

import CarControl.Car;
import CarControl.CarControlPanel;
import Safty.AbstractSafty;
import Safty.LeftBlindSpot;
import Safty.RightBlindSpot;
import Safty.Sensor;

//will be the model of the mvc
public class WorldGenerator {
    public static final double CARYSTART = -100;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int CARHEIGHT = 80;
    public static final int CARWIDTH = 37;

    private double velocity = 3;
    private ArrayList<World> entities;
    private Car car;
    private CarControlPanel carPanel;
    public static WorldGenerator instance = null;
    private ArrayList<World> movingEntities;
    private ArrayList<AbstractSafty> safty;

    private WorldGenerator() {
        this.entities = new ArrayList<World>();
        this.safty = new ArrayList<AbstractSafty>();
        this.movingEntities = new ArrayList<World>();
        this.car = Car.getInstance();
        this.carPanel = CarControlPanel.getInstance();
        instance = this;
        init();
    }

    public static WorldGenerator getInstance() {
        if (instance == null) {
            new WorldGenerator();
        }
        return instance;
    }

    public Car getCar() {
        return this.car;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public ArrayList<World> getEntities() {
        return this.entities;
    }

    public void addSprit(World sprite) {
        this.entities.add(sprite);
    }

    public void removeSprite(World sprite) {
        this.entities.remove(sprite);
    }

    public Point carMovement(int side) {
        return this.car.drive(side);
    }

    public ArrayList<World> getMovingEntities() {
        return this.movingEntities;
    }
    /***********clone of arraylist **********/
    public ArrayList<World> getCopyOfMovingEntitiesList(){
        ArrayList<World> copy = new ArrayList<World>();
        for(World a : this.getMovingEntities()){
            copy.add(a);
        }
        return copy;
    }
    /******** INIT *************************/
    public void init() {
        this.carPanel.setCar(car);
        initBorders();
        initCarSafty();
    }

    public void npcCreator() {
        // need to be under the screen,
        // left lane 442 -> 535 --> x range : 442-495
        // second lane 545 -> 635 --> x range : 545-595
        Random r = new Random();
        int i;
        if (r.nextInt(RIGHT, LEFT + 1) == LEFT) {
            i = r.nextInt(442, 495);
        } else {
            i = r.nextInt(545, 595);
        }
        World npc = new Npc(new Point(i, CARYSTART), CARWIDTH, CARHEIGHT);
        this.movingEntities.add(npc);
        this.entities.add(npc);

    }

    public void initBorders() {
        // borders - by obstacles
        Obstacle rightGrass = new Obstacle(new Point(0, 0), 130, 435);
        Obstacle leftGrass = new Obstacle(new Point(670, 0), 130, 435);
        Obstacle laneDivdier = new Obstacle(new Point(355, 0), 70, 435);
        Obstacle roadSide = new Obstacle(new Point(650, 0), 10, 435);

        this.entities.add(roadSide);
        this.entities.add(rightGrass);
        this.entities.add(leftGrass);
        this.entities.add(laneDivdier);
    }

    public void initCarSafty() {
        // blind spots
        RightBlindSpot rightBlindSpot = new RightBlindSpot(this.carPanel, instance,
                new Sensor(new SensorRange(RIGHT, 12)));
        LeftBlindSpot leftBlindSpot = new LeftBlindSpot(this.carPanel, instance,
                new Sensor(new SensorRange(LEFT, 12)));
        this.safty.add(leftBlindSpot);
        this.safty.add(rightBlindSpot);

    }

    // ******************Checks for every iteration ***********************/
    public boolean isThereCollision() {
        for (World a : this.getEntities()) {
            if (this.car.doesCollide(a)) {
                return true;
            }
        }
        return false;
    }

    /****************** Control access ****************/

    public boolean isOutOfScreen(World world) {
        if (world.getUpLeft().getY() >= 435) {
            this.movingEntities.remove(world);
            return true;
        }
        return false;
    }

    /****************** View By Model *****************/

    public Point moveTheBackground(Point currentPoint) {
        double endOfScreen = Math.abs(currentPoint.getY());
        if (endOfScreen < velocity) {

            return new Point(0, -435 + velocity - endOfScreen);// 435 is the height of background img
        }
        return new Point(currentPoint.getX(), currentPoint.getY() + velocity);
    }

    /************************ RuN *********************/
    public void run() {
        for (AbstractSafty safe : this.safty) {
            safe.run();
        }
        for (World entity : this.movingEntities) {
            entity.move();
            entity.setPoint(new Point(entity.getUpLeft().getX(), entity.getUpLeft().getY() + velocity));
        }
    }

}
