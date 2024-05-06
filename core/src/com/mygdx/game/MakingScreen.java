package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MakingScreen extends ScreenAdapter{
    //Fields
    
    private Main game;
    private Pizza pizza;
    private Globals globals;
    private ToppingHelper toppingHelper;
    
    //Setting up assets
    private Texture cuttingBoard = new Texture("cuttingBoard.png");

    private Texture panSauce = new Texture("panSauce.png");
    private Texture panCheese = new Texture("panCheese.png");
    private Texture panPepperoni = new Texture("panPepperoni.png");
    private Texture panMushroom = new Texture("panMushroom.png");
    private Texture panPepper = new Texture("panPepper.png");
    private Texture pan = new Texture("pan.png");
    
    private Texture moneyBanner = new Texture("moneyBanner.png");
    
    private float panY;
    private float panW;
    private float panH;
    
    //constructor
    public MakingScreen(Main game){
        this.game = game;
        this.pizza = new Pizza();
        this.globals = Globals.getInstance();
        this.toppingHelper = new ToppingHelper();
        
        //precalculate pan dimensions
        panY = game.screenH * .75f;
        panW = game.screenW * .2f;
        panH = game.screenH * .25f;
    }
    
    @Override
    public void show(){
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //This code sets up an input processor that when the escape key is pressed
        //the application closes
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ESCAPE){
                    System.exit(0);
                }
                return true;
            }
        });
    }
    
    @Override
    public void render(float delta){
        //clear screen
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //start drawing screen
        game.batch.begin();
        
            //updates
            toppingHelper.update(game);
            pizza.update(game);

            //cutting board
            game.batch.draw(cuttingBoard, game.screenW * .2f, 0, game.screenW * .8f, game.screenH * 0.75f);
            game.batch.draw(pan, 0, 0, game.screenW * .2f, game.screenH * 0.6f);
            game.batch.draw(pan, 0,game.screenH * .6f,game.screenW * .2f, game.screenH * 0.15f);
            //texts over the reset button and oven
            game.font.draw(game.batch, "Click here to\nclear selection\nHolding: " + globals.getHolding().getType(), 65, game.screenH * .715f);
            game.font.draw(game.batch, "When you're ready\ndrag pizza here\nto cook and box it.\nMake sure you aren't\nholding anything", 65, game.screenH * .52f);
            
            //draws pans
            game.batch.draw(panSauce, 0, panY, panW, panH);
            game.batch.draw(panCheese, game.screenW * .2f, panY, panW, panH);
            game.batch.draw(panPepperoni, game.screenW * .4f, panY, panW, panH);
            game.batch.draw(panMushroom, game.screenW * .6f, panY, panW, panH);
            game.batch.draw(panPepper, game.screenW * .8f, panY, panW, panH);
            
            //draw pizza
            pizza.draw(game);
            
            //money banner drawing
            game.batch.draw(moneyBanner, game.screenW * .8f, 0,game.screenW * 0.2f,game.screenH * 0.1f);
            game.font.getData().setScale(3);
            String s = String.format("%.2f", globals.getMoney());
            game.font.draw(game.batch, s, game.screenW * 0.86f, 80);
            game.font.getData().setScale(2);
            
        //end drawing screen
        game.batch.end();
    }
    
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
