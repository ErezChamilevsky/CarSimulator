package Safty;

import CarControl.CarControlPanel;
import World.WorldGenerator;

public class LeftBlindSpot extends AbstractSafty {

    public LeftBlindSpot(CarControlPanel car, WorldGenerator World, Sensor sensor) {
        super(car, World, sensor);
    }

    @Override
    void raiseFlag() {
        if (didSensorFind()) {
            this.carPanel.raiseLeftBlindSensorFlag(true);
        } else {
            this.carPanel.raiseLeftBlindSensorFlag(false);
        }
    }

    
}
