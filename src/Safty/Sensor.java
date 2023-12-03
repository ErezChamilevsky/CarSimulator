package Safty;

import World.Lane;
import World.Point;
import World.SensorRange;
import World.World;

public class Sensor {
    public SensorRange range;

    public Sensor(SensorRange range) {
        this.range = range;
    }

    public void setPoint(Point point) {
        this.range.setPoint(point);//setting to the range too

    }

    public boolean isInRange(World entity) {

        if (!(entity instanceof Lane) && this.range.isEntityInRange(entity)) { // lanes are different story
            return true;
        }
        return false;
    }

}
