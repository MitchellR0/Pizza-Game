package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.HashMap;

public class Pizza implements Drawable{
    //Fields
    
    private int x = 700;
    private int y = 200;
    private int w = 600;
    private int h = 600;
    
    private Boolean hasSauce = false;
    private Boolean hasCheese = false;
    
    private Texture img = new Texture("pizzaDough.png");
    private HashMap<String, ArrayList<Topping>> toppings;
    
    private Globals globals;
    
    //Constructor
    public Pizza(){
        toppings = new HashMap<>();
        globals = Globals.getInstance();
        globals.setMoney(-2);
    }
    
    //Update method
    public void update(Main game){
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            String holding = globals.getHolding().getType();
            
            //if the pizza is pressed
            if (isClicking(mouseX, mouseY,game.screenH)){
                //nothing is being held
                if (holding == "empty"){
                    //moves the pizza to where the cursor is
                    move(mouseX, mouseY, game.screenW, game.screenH);
                    //if its at the oven then go to the finish screen
                    if (mouseX >= 0 && mouseX <= game.screenW * .2f && game.screenH - mouseY >= 0 && game.screenH - mouseY <= game.screenH * 0.6f){
                        globals.setPizza(this);
                        game.setScreen(new FinishScreen(game));
                    }
                //add sauce to pizza
                } else if(holding == "sauce" && !hasSauce) {
                    img = new Texture("pizzaSauce.png");
                    hasSauce = true;
                    globals.setMoney(-2);
                //add cheese to pizza
                } else if ((holding == "cheese") && (hasSauce) && !hasCheese){
                    img = new Texture("pizzaCheese.png");
                    hasCheese = true;
                    globals.setMoney(-2);
                } 
            }
        }
        
        //just pressed method, this is different than the one above because it is
        //only called once, the other method is called always while that button is down
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            String holding = globals.getHolding().getType();
            if (isClicking(mouseX, mouseY,game.screenH)){
                if (hasSauce && hasCheese && (
                        holding == "pepperoni" ||
                        holding == "mushroom" ||
                        holding == "pepper")){
                    Topping t = new Topping(globals.getHolding().getType(), 
                            mouseX - 30, game.screenH - mouseY - 30);
                    t.setLocationOnPizza(this.x, this.y);
                    addTopping(t);
                    globals.setMoney(-.20);
                } 
            }
        }
    }
    
    //draw method
    @Override
    public void draw(Main game){
        game.batch.draw(img, x, y, w, h);
        for (ArrayList<Topping> ar : toppings.values()){
            for(int i = 0; i < ar.size(); i++){
                ar.get(i).draw(game);
            }
        }
    }
    
    //Topping Methods
    public void addTopping(Topping topping){
        if (toppings.containsKey(topping.getType())){
            toppings.get(topping.getType()).add(topping);
        } else {
            ArrayList<Topping> array = new ArrayList<>();
            array.add(topping);
            toppings.put(topping.getType(), array);
        }
    }
    
    //Moving Methods
    public void move(int mouseX, int mouseY, int screenW, int screenH){
        x = mouseX - w/2;
        y = screenH - mouseY - h/2;
        for (ArrayList<Topping> ar : toppings.values()){
            for(int i = 0; i < ar.size(); i++){
                ar.get(i).setX(ar.get(i).getLocationX() + x);
                ar.get(i).setY(ar.get(i).getLocationY() + y);
            }
        }        
    }
    //is clicking the pizza method
    public Boolean isClicking(int mouseX, int mouseY, int screenH){
        return mouseX >= x && mouseX <= x + w && screenH - mouseY >= y && screenH - mouseY <= y + h;
    }
    
    //Get and Set methods
    public HashMap getToppings(){
        return toppings;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean hasSauce(){
        return this.hasSauce;
    }
    public boolean hasCheese(){
        return this.hasCheese;
    }
}
