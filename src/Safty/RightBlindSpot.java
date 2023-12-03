package Safty;

import CarControl.CarControlPanel;
import World.WorldGenerator;

public class RightBlindSpot extends AbstractSafty {

    public RightBlindSpot(CarControlPanel car, WorldGenerator world, Sensor sensor) {
        super(car, world, sensor);
    }

    @Override
    void raiseFlag() {
        if(didSensorFind()){
            this.carPanel.raiseRightBlindSensorFlag(true);
        } else {
            this.carPanel.raiseRightBlindSensorFlag(false);
        }
    }
    
}
