package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    
    //Set up important variables
    protected SpriteBatch batch;
    protected BitmapFont font;
    protected int screenW;
    protected int screenH;
    protected Music music;
        
    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
        screenW = Gdx.graphics.getWidth();
        screenH = Gdx.graphics.getHeight();
        
        //Start game with Title screen
        setScreen(new TitleScreen(this));
        music = Gdx.audio.newMusic(Gdx.files.internal("MITCH GAME.mp3"));
        music.play();
        music.setLooping(true);
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        music.dispose();
    }
}
