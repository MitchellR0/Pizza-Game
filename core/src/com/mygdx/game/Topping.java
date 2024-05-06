package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public class Topping implements Drawable{
    //fields
    
    private int x;
    private int y;
    private int w;
    private int h;
    private int pizzaLocationX;
    private int pizzaLocationY;
    
    private Texture img;
    private String type;
    
    //constructor
    public Topping(String type, int x, int y){
        //picture assignment
        if (type == "pepperoni"){
            img = new Texture("pepperoni.png");
        } else if (type == "mushroom"){
            img = new Texture("mushroom.png");
        } else if (type == "pepper"){
            img = new Texture("pepper.png");
        }
        
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = 70;
        this.h = 70;  
    }
    
    @Override
    public void draw(Main game){
        game.batch.draw(img, x, y, w, h);
    }
    
    //Get and Set methods
    public int getLocationX(){
        return pizzaLocationX;
    }
    public int getLocationY(){
        return pizzaLocationY;
    }
    public void setLocationOnPizza(int pizzaX, int pizzaY){
        pizzaLocationX = this.x - pizzaX;
        pizzaLocationY = this.y - pizzaY;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public String getType(){
        return type;
    }
}
