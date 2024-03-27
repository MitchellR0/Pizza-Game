/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 * @author mitch
 */
public class TitleScreen extends ScreenAdapter{
    
    Main game;
    public TitleScreen(Main game){
        this.game = game;
    }
    
    @Override
    public void show(){
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //This code sets up an input processor that when the space key is pressed it
        //will advance to the next screen which is the OrderScreen
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new OrderScreen(game));
                }
                return true;
            }
        });
    }
    
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "Title Screen!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }
    
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
