package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class OrderScreen extends ScreenAdapter{
    //Fields
    private Main game;
    private Globals globals;
    
    //Assets
    private Texture counter = new Texture("counter.png");
    private Texture moneyBanner = new Texture("moneyBanner.png");
    private Texture background = new Texture("background.png");
    
    //Constructor
    public OrderScreen(Main game){
        this.game = game;
        this.globals = Globals.getInstance();
        globals.setCustomer(new Customer());
        globals.setOrderScreen(true);
    }
    
    @Override
    public void show(){
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //This code sets up an input processor that when the space key it either
        //shows the text or goes to the next screen if the previous option has 
        //already happened. If the escape key is pressed it exits the application
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    if (!globals.getShowText()){
                        globals.setShowText(true);
                    } else {
                        globals.setOrderScreen(false);
                        globals.setShowText(false);
                        game.setScreen(new MakingScreen(game));
                    }
                } else if (keyCode == Input.Keys.ESCAPE){
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
        
        //draws scene
        game.batch.draw(background, 0, game.screenH * 0.3f, game.screenW, game.screenH * 0.7f);
        globals.getCustomer().draw(game);
        game.batch.draw(counter, 0,0,game.screenW, game.screenH * 0.3f);
        
        //draws money banner
        game.batch.draw(moneyBanner, game.screenW * .8f, 0,game.screenW * 0.2f,game.screenH * 0.1f);
        game.font.getData().setScale(3);
        String s = String.format("%.2f", globals.getMoney());
        game.font.draw(game.batch, s, game.screenW * 0.86f, 80);
        game.font.getData().setScale(2);
        
        //stop drawing screen
        game.batch.end();
    }
    
    @Override
    public void hide(){
        //hides when screen is out of focus
        Gdx.input.setInputProcessor(null);
    }
}
