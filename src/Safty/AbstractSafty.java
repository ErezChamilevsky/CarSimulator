package Safty;

import CarControl.CarControlPanel;
import World.World;
import World.WorldGenerator;

public abstract class AbstractSafty {
    protected CarControlPanel carPanel;
    protected WorldGenerator world;
    protected Sensor sensor;

    public AbstractSafty(CarControlPanel car, WorldGenerator world, Sensor sensor) {
        this.carPanel = car;
        this.world = world;
        this.sensor = sensor;
    }

    protected boolean didSensorFind() {
        for (World entity : this.world.getEntities()) {
            if(sensor.isInRange(entity)){
                return true;
            }
        }
        return false;
    }

    abstract void raiseFlag();

    public void run() {
        setSensorPoints();
        raiseFlag();
    }

    public void setSensorPoints() {
        this.sensor.setPoint(this.carPanel.getCarPoint());
    }

}
