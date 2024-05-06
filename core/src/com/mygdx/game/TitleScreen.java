package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen extends ScreenAdapter{
    
    //Set up game instance
    private Main game;
    
    //Assets
    private Texture title;
    private Texture howToPlay;
    
    private boolean showHowToPlay;
    
    public TitleScreen(Main game){
        this.game = game;
        this.title = new Texture("titleScreen.png");
        this.howToPlay = new Texture("howToPlay.png");
        this.showHowToPlay = false;
    }
    
    @Override
    public void show(){
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //If the user presses space the screen goes to the Order screen
        //If the user presses escape the application closes.
        //If the user presses H it shows a how to play
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new OrderScreen(game));
                } else if (keyCode == Input.Keys.ESCAPE){
                    System.exit(0);
                } else if (keyCode == Input.Keys.H){
                    showHowToPlay = !showHowToPlay;
                }
                return true;
            }
        });
    }
    
    @Override
    public void render(float delta){
        //clears screen
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //drawing screen begins
        game.batch.begin();
        //draw background
        game.batch.draw(title, 0, 0, game.screenW, game.screenH);
        if (showHowToPlay)
            game.batch.draw(howToPlay,game.screenW/3,game.screenH/8, game.screenW * 0.3f, game.screenH * 0.8f);
        //drawing screen ends
        game.batch.end();
    }
    
    @Override
    public void hide(){
        //clears the input processor when this screen isn't in focus
        Gdx.input.setInputProcessor(null);
    }
}
