package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.HashMap;

public class FinishHelper {
    //Fields
    //Assets
    private Texture pizzaBox;
    private Texture textBubble;
    
    //Box Location
    private int boxX;
    private int boxY;
    private int boxW;
    private int boxH;
    
    //Other important variables
    private Globals globals;
    private Boolean customerHasBox;
    private Boolean customerPaid;
    
    //Constructor
    public FinishHelper(){
        this.globals = Globals.getInstance();
        this.pizzaBox = new Texture("pizzaBox.png");
        this.textBubble = new Texture("textBubble.png");
        this.boxX = 1200;
        this.boxY = 100;
        this.boxW = 500;
        this.boxH = 500;
        this.customerHasBox = false;
        this.customerPaid = false;
    }
    
    public void update(Main game){
        //draws the customer on the finish screen
        globals.getCustomer().draw(game);
        
        //input processing for the box, if its dragged to the customer they 
        //respond with if it matches what they wanted
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            if (mouseX >= boxX && mouseX <= boxX + boxW && game.screenH - mouseY
                    >= boxY && game.screenH - mouseY <= boxY + boxH){
                boxX = mouseX - boxW/2;
                boxY = game.screenH - mouseY - boxH/2;
                if (boxTouchingCustomer()){                                     //method added for readability
                    customerHasBox = true;
                }
            }
        }
        if(!customerHasBox){
            //draws the box if its not handed to customer yet
            game.batch.draw(pizzaBox, boxX, boxY, boxW, boxH);
        } else {
            //iterate through each customer topping
            //  if the toppings is in the pizza toppings
            //      if the number of toppings < 10
            //          the pizza does not match -- which means the bad textbox will appear
            //  else
            //      if you reach here that means the topping is not in the list, also bad textbox
            
            //PIZZA TOPPING COMPARISONS
            //first gets the toppings from the customer and the pizza
            ArrayList<String> customerToppings = globals.getCustomer().getToppings();
            HashMap<String, ArrayList<Topping>> pizzaToppings = globals.getPizza().getToppings();
            
            //start with the matching bool is true
            Boolean pizzaMatches = true;  
            //error handling, if only one list is empty its wrong
            if ((customerToppings.isEmpty() && !pizzaToppings.keySet().isEmpty()) ||
                    !globals.getPizza().hasSauce() || !globals.getPizza().hasCheese()){
                pizzaMatches = false;
            }
            
            for (String cTopping : customerToppings){                           //iterate through the customer toppings
                if (pizzaToppings.containsKey(cTopping)){                    //if the topping is in the pizza list continue
                    if (pizzaToppings.get(cTopping).size() < 10){            //if theres less than 10 it doesnt match
                        pizzaMatches = false;                                   //reassign variable and exit
                        break;
                    }
                } else {                                                        //if you reach here that means the list didnt have the topping
                    pizzaMatches = false;                                       //reassign variable and exit
                    break;
                }
            }

            //draw text bubble
            game.batch.draw(textBubble, 580, 600, 600, 600);
            //changing color for text
            game.font.setColor(0,0,0,1);
            game.font.getData().setScale(2);
            
            //show correct text and pay the right amount if the pizza matches
            if (pizzaMatches){
                game.font.draw(game.batch, "This is perfect!", 735, 1050);
                if (!customerPaid){
                    globals.setMoney(12 + (customerToppings.size()*3));
                    customerPaid = true;
                }
            } else {
                game.font.draw(game.batch, "This is not right.", 735, 1050);
                if (!customerPaid){
                    globals.setMoney(4 - customerToppings.size());
                    customerPaid = true;
                }
            }
            //reset the font color
            game.font.getData().setScale(1);
            game.font.setColor(1,1,1,1);
            //set the customer animation to talking
            globals.getCustomer().setTalking(true);
            //hint the user to continue
            game.font.draw(game.batch, "press space to continue", game.screenW * 0.8f, 150);
        }
    }
    
    //method added for readability
    private Boolean boxTouchingCustomer(){
        Customer customer = globals.getCustomer();
        return boxX >= customer.getX() &&
               boxX <= customer.getX() + customer.getW()/2 &&
               boxY >= customer.getY() &&
               boxY <= customer.getY() + customer.getH()/2;
    }
}
