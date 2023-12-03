package CarControl;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import GUI.Display;
import GUI.NPCGUI;
import GUI.RepresentGUI;
import World.Npc;
import World.World;
import World.WorldGenerator;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

//will be the control of the mvc
public class Control{
    public static final int STAY = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int BLIND = 2;
    

    private Display view;
    private WorldGenerator model;
    private KeyboardSensor keyboard;
    private ConcurrentHashMap<World, RepresentGUI> npcMap;

    public Control() {
        this.view = Display.getInstance();
        this.model = WorldGenerator.getInstance();
        this.keyboard = this.view.getKeyboard();
        this.npcMap = new ConcurrentHashMap<World, RepresentGUI>();
    }

    public void viewByModel(){
        //blind spot
        this.view.getLeftSignalGUI().setLeftBlind(this.model.getCar().leftBlind);
        this.view.getRightSignalGUI().setRightBlind(this.model.getCar().rightBlind);

        //background move
        this.view.background.setPoint(this.model.moveTheBackground(this.view.background.getPoint()));

        //npc
        this.npcMovement();

        //the bottom pannel
        this.view.bottomPanel.setVelocity(this.model.getVelocity());

    }

    public void playerOrder() {
        //changing the point the model, returning the current point of the car and set it in the view's car 
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.view.carMovement(this.model.carMovement(LEFT));
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.view.carMovement(this.model.carMovement(RIGHT));
        } else {
            this.view.carMovement(this.model.carMovement(STAY));
        }

        //changing the signal in the model, returning the current state and set it in the view
        if (keyboard.isPressed("z")) {
            this.model.getCar().setSignal(LEFT);
            this.view.getLeftSignalGUI().setLefttSignal(this.model.getCar().isLeftSignalOn());
        } else if (keyboard.isPressed("x")) {
            this.model.getCar().setSignal(RIGHT);
            this.view.getRightSignalGUI().setIsRightSignalOn(this.model.getCar().isRightSignalOn());
        }
    }
    /*******************NPC managemant *************/
    public void npcManagement(){
        deleteFromMap();
        addToMap();
    }

    //cheking if needed to delete
    public void deleteFromMap(){
        for(World npc : this.npcMap.keySet()){
            if(this.model.isOutOfScreen(npc)){
                this.view.removeNpc(this.npcMap.get(npc));
                this.npcMap.remove(npc);
            }
        }
    }

    //checking if needed to add
    public void addToMap(){
        for(World npc : this.model.getMovingEntities()){
            if(!this.npcMap.containsKey(npc)){
                this.npcMap.put(npc, this.view.newNpc(npc.getUpLeft()));
            }
        }
    }

    //helping function
    public void npcMovement(){
        for(RepresentGUI npc : this.npcMap.values()){
            World temp = keyByValue(npc);
            if(temp == null){
                continue;
            }
            npc.setPoint(temp.getUpLeft());
        }
    }

    public World keyByValue(RepresentGUI value){
        for(World a : this.npcMap.keySet()){
            if(this.npcMap.get(a) == value){
                return a;
            }
        }
        return null;
    }


    /**************************************************/

    public void run(){
        // run

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        this.model.npcCreator();
        int i = 0;
        while(true){
            i++;
            i %= 250;
            this.model.run();
            this.npcManagement();
            viewByModel();
            
            if(this.model.isThereCollision()){
                break;
            }

            long startTime = System.currentTimeMillis(); // timing
            
            playerOrder();
            this.view.run();
            if(i % 250 == 0){
                this.model.npcCreator();
            }
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        // System.out.println(this.model.getCar().getPoint().getX() + " " + this.model.getCar().getPoint().getY());
        // System.out.println(this.model.getMovingEntities().get(0).getUpLeft().getX() + " " + this.model.getMovingEntities().get(0).getUpLeft().getX());
    }

    public static void main(String[] args) {
        Control c = new Control();
        c.run();
    }
}
