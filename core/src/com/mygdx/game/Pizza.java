package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class Pizza {
    private int x = 300;
    private int y = 300;
    private int w = 600;
    private int h = 600;
    private Texture img = new Texture("tmp_pizza.png");
    public HashMap<String, Integer> toppings = new HashMap<>();
    
    //Constructor
    public Pizza(){
    }
    
    //Topping Methods
    public void addTopping(String topping){
        if (toppings.containsKey(topping)){
            
        }
            
    }
       
    
    
    //Get and Set Methods below
    public Texture getImg(){
        return img;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getW(){
        return w;
    }
    public int getH(){
        return h;
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
