package CarControl;

import World.Point;

public class CarControlPanel {
    public static final int RIGHT = 1;
    public static final int LEFT = 2;

    public boolean OutOfLaneRight = false;
    public boolean OutOfLaneLeft = false;

    public Car car = null;
    public static CarControlPanel instance;

    private CarControlPanel() {
        instance = this;
    }

    public static CarControlPanel getInstance() {
        if (instance == null) {
            new CarControlPanel();
        }
        return instance;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Point getCarPoint() {
        return this.car.getPoint();
    }


    public void raiseRightBlindSensorFlag(Boolean flag) {
        this.car.rightBlind = flag;
    }

    public void raiseLeftBlindSensorFlag(Boolean flag) {
        this.car.leftBlind = flag;
    }

    
    public void raiseOutOfLaneLeftFlag(Boolean flag) {
        if (this.car.leftSignalOn) {
            this.OutOfLaneLeft = false;
        } else {
            this.OutOfLaneLeft = flag;
        }
    }

    public void raiseOutOfLaneRightFlag(Boolean flag) {
        if (this.car.rightSignalOn) {
            this.OutOfLaneRight = false;
        } else {
            this.OutOfLaneRight = flag;
        }
    }

}
