package Safty;

import CarControl.CarControlPanel;
import World.WorldGenerator;

public class OutOfLaneRight extends OutOfLane {

    public OutOfLaneRight(CarControlPanel car, WorldGenerator World) {
        super(car, World);
    }

    @Override
    void raiseFlag() {
        if (didSensorFind()) {
            this.carPanel.raiseOutOfLaneRightFlag(true);
        } else {
            this.carPanel.raiseOutOfLaneRightFlag(false);
        }
    }

}
