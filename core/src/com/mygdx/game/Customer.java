package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.Random;

public class Customer implements Drawable{
    
    //Assets
    private Texture img;
    private Texture imgTalking;
    private Texture textBubble;
    
    //Important Variables
    private String message;
    private ArrayList<String> toppings;
    private Globals globals;
    private Random randomHelper;
    private boolean talking;
    
    //Location
    private float y = -150;
    private float x = 100;
    private float w = 600;
    private float h = 600;
    
    public Customer(){
        //Block below gets a random int and sets the customer images based on the result
        randomHelper = new Random();
        int tmp = randomHelper.nextInt(4);
        if (tmp == 0){
            img = new Texture("alien.png");
            imgTalking = new Texture("alienTalking.png");
        } else if (tmp == 1) {
            img = new Texture("man1.png");
            imgTalking = new Texture("man1Talking.png");
        } else if (tmp == 2){
            img = new Texture("captain.png");
            imgTalking = new Texture("captainTalking.png");
        } else if (tmp == 3){
            img = new Texture("man2.png");
            imgTalking = new Texture("man2Talking.png");
        }
        //Bubble asset
        textBubble = new Texture("textBubble.png");
        
        //Goes through each topping, there is a 50% chance that a customer will want each topping
        toppings = new ArrayList<>();
        if (randomHelper.nextInt(2) == 1)
            toppings.add("pepperoni");
        if (randomHelper.nextInt(2) == 1)
            toppings.add("mushroom");
        if (randomHelper.nextInt(2) == 1)
            toppings.add("pepper");
        
        //Sets the correct message for what the customer wants
        if (toppings.isEmpty()){
            message = "Just a sauce and cheese\n"
                    + "pizza please!";
        } else {
            String s = "";
            //Goes through the toppings list and adds them to a string
            for (int i = 0; i < toppings.size(); i++){
                s += toppings.get(i);
                if (i < toppings.size() - 2)
                    s += ",\n";
                else if (i < toppings.size() - 1)
                    s += ", and\n";
            }
            //After the string is done its added to the end of a message
            message = "I would like a pizza with \n" + s;
        }
        
        this.globals = Globals.getInstance();
        this.talking = false;
    }
    
    @Override
    public void draw(Main game){
        //Basic logic to draw the actual customer
        //If the customer is talking, every second it switches from the normal image
        //to the talking image, and if they aren't talking just draw the original
        if (talking) {
            if ((System.currentTimeMillis()/1000) % 2 == 0){
                game.batch.draw(img,x,y,w,h);
            } else {
                game.batch.draw(imgTalking, x, y, w, h);
            }
        } else{
            game.batch.draw(img,x,y,w,h);
        }
        
        //If the customers message is being shown the text box must be drawn with it
        if (globals.getShowText()){
            talking = true;
            game.batch.draw(textBubble, 580, 600, 600, 600);
            game.font.setColor(0,0,0,1);
            game.font.draw(game.batch, message, 735, 1050);
            game.font.setColor(1,1,1,1);
        }
        
        //Really simple intro "animation", I wanted it to see a little 
        //less clunky when the customer appears
        if (globals.getOrderScreen() && y < game.screenH * 0.3f){
            y += 10;
        } else {
            y = game.screenH * 0.3f;
        }
    }
    
    //Get and Set Methods
    
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getW(){
        return w;
    }
    public float getH(){
        return h;
    }
    public ArrayList<String> getToppings(){
        return toppings;
    }
    public void setTalking(boolean b){
        this.talking = b;
    }
    public boolean getTalking(){
        return talking;
    }
}
