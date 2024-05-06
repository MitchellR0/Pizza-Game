/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author mitch
 */
public class ToppingHelper {
    private Globals globals;
    
    public ToppingHelper(){
        globals = Globals.getInstance();
    }
    
    public void update(Main game){
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            //mouse coordinates
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            
            //clear topping selection
            if (mouseX >= 0 && mouseX <= game.screenW * .2f && game.screenH - mouseY >= game.screenH * 0 && 
                    game.screenH - mouseY > game.screenH * 0.6f){
               globals.setHolding(new Topping("empty", 0, 0)); 
            }
            
            //sauce pan detection
            if (mouseX >= game.screenW * 0 && mouseX <= game.screenW * .2f &&
                        game.screenH - mouseY >= 0 && game.screenH - mouseY >= game.screenH * .75f) {
                Topping sauce = new Topping("sauce", mouseX, game.screenH - mouseY);
                globals.setHolding(sauce);
            }
            //cheese pan detection
            if (mouseX >= game.screenW * 0.2 && mouseX <= game.screenW * .4f &&
                        game.screenH - mouseY >= 0 && game.screenH - mouseY >= game.screenH * .75f){
                Topping cheese = new Topping("cheese", mouseX, game.screenH - mouseY);
                globals.setHolding(cheese);
            }
            //pepperoni
            if (mouseX >= game.screenW * 0.4 && mouseX <= game.screenW * .6f &&
                        game.screenH - mouseY >= 0 && game.screenH - mouseY >= game.screenH * .75f){
                Topping pepperoni = new Topping("pepperoni", mouseX, game.screenH - mouseY);
                globals.setHolding(pepperoni);
            }
            //mushroom
            if (mouseX >= game.screenW * 0.6 && mouseX <= game.screenW * .8f &&
                        game.screenH - mouseY >= 0 && game.screenH - mouseY >= game.screenH * .75f){
                Topping pepperoni = new Topping("mushroom", mouseX, game.screenH - mouseY);
                globals.setHolding(pepperoni);
            }
            //pepper
            if (mouseX >= game.screenW * 0.8 && mouseX <= game.screenW &&
                        game.screenH - mouseY >= 0 && game.screenH - mouseY >= game.screenH * .75f){
                Topping pepperoni = new Topping("pepper", mouseX, game.screenH - mouseY);
                globals.setHolding(pepperoni);
            }
        }
    }
}
