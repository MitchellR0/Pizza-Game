package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class FinishScreen extends ScreenAdapter{
    //Fields
    //Assets
    private Texture counter = new Texture("counter.png");
    private Texture moneyBanner = new Texture("moneyBanner.png");
    private Texture background = new Texture("background.png");
    
    //Other important variables
    private Main game;
    private Globals globals;
    private FinishHelper helper;
    
    //Constructor
    public FinishScreen(Main game){
        this.game = game;
        this.globals = Globals.getInstance();
        this.helper = new FinishHelper();
    }
    
    @Override
    public void show(){
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //This code sets up an input processor that when the space key is pressed it
        //will advance to the next screen which is the OrderScreen or if they press
        //escape it closes the application
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE && globals.getCustomer().getTalking()) {
                    globals.getCustomer().setTalking(false);
                    game.setScreen(new OrderScreen(game));
                } else if (keyCode == Input.Keys.ESCAPE){
                    System.exit(0);
                }
                return true;
            }
        });
    }
    
    @Override
    public void render(float delta){
        
        //Clear screen
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //Begin drawing screen
        game.batch.begin();
        //Draw scene
        game.batch.draw(background, 0, game.screenH * 0.3f, game.screenW, game.screenH * 0.7f);
        game.batch.draw(counter, 0,0,game.screenW, game.screenH * 0.3f);
        
        //Update finish helper
        helper.update(game);
        
        //Draw the money in the bottom right
        game.batch.draw(moneyBanner, game.screenW * .8f, 0,game.screenW * 0.2f,game.screenH * 0.1f);
        game.font.getData().setScale(3);
        String s = String.format("%.2f", globals.getMoney());
        game.font.draw(game.batch, s, game.screenW * 0.86f, 80);
        game.font.getData().setScale(2);
        
        //Finish drawing screen
        game.batch.end();
    }
    
    @Override
    public void hide(){
        //Gets rid of input processor when out of focus
        Gdx.input.setInputProcessor(null);
    }
}
