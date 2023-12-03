package GUI;

import java.util.ArrayList;

import World.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

//will be the view of the mvc
public class Display {
    public static final int WIDTH = 800;
    private static final int HEIGHT = 485;
    private GUI window;
    private static Display instance = null;
    private ArrayList<RepresentGUI> sprites;
    private RightSignalGUI rightSignal;
    private LeftSignalGUI leftSignal;
    private CarGUI car;//player
    public BackgroundGUI background;
    public BottomPanelGUI bottomPanel;

    private Display(){
        this.window = new GUI("Car Simulator", WIDTH, HEIGHT);
        this.sprites = new ArrayList<RepresentGUI>();
        instance = this;
        this.car = new CarGUI(null);
        init();
    }

    public static Display getInstance(){
        if(instance == null){
            new Display();
        }
        return instance;
    }

    public GUI getWindow() {
        return window;
    }
    
    public KeyboardSensor getKeyboard(){
        return this.window.getKeyboardSensor();
    }

    public void addSprite(RepresentGUI sprite){
        this.sprites.add(sprite);
    }

    public void removeSprite(RepresentGUI sprite){
        this.sprites.remove(sprite);
    }

    public RightSignalGUI getRightSignalGUI(){
        return this.rightSignal;
    }

    public LeftSignalGUI getLeftSignalGUI(){
        return this.leftSignal;
    }

    public void init(){
    
        //background init
        BackgroundGUI background = new BackgroundGUI(new Point(0, -435));
        this.background = background;
        addSprite(background);

        //signals init
        BottomPanelGUI panel = new BottomPanelGUI();
        RightSignalGUI rightSignal = new RightSignalGUI();
        LeftSignalGUI leftSignal = new LeftSignalGUI();
        this.rightSignal = rightSignal;
        this.leftSignal = leftSignal;
        this.bottomPanel = panel;
        ///car init
        addSprite(this.car);
    }
    
    public void carMovement(Point point){
        this.car.setPoint(point);
    }

    
    public void draw(DrawSurface d){
        for(RepresentGUI sprite : sprites){
            sprite.draw(d);
        }
        //things that were added but should not to be covered
        this.car.draw(d);
        this.bottomPanel.draw(d);
        this.rightSignal.draw(d);
        this.leftSignal.draw(d);
    }
    
    public void run(){
        DrawSurface d = this.getWindow().getDrawSurface();
        this.draw(d);
        this.getWindow().show(d);
    }

        public NPCGUI newNpc(Point point) {
            NPCGUI npc = new NPCGUI(point);
            this.addSprite(npc);
            return npc;
        }

        public void removeNpc(RepresentGUI npc){
            this.sprites.remove(npc);
        }


}