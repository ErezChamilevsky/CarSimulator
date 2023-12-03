package Safty;

import CarControl.CarControlPanel;
import World.WorldGenerator;

public class OutOfLaneLeft extends OutOfLane {

    public OutOfLaneLeft(CarControlPanel car, WorldGenerator World) {
        super(car, World);
    }

    @Override
    void raiseFlag() {
        if (didSensorFind()) {
            this.carPanel.raiseOutOfLaneLeftFlag(true);
        } else {
            this.carPanel.raiseOutOfLaneLeftFlag(false);
        }
    }

    
}
