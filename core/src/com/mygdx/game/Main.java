package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	protected SpriteBatch batch;
	protected BitmapFont font;
        protected int screenW;
        protected int screenH;
        
	@Override
	public void create () {
            batch = new SpriteBatch();
            font = new BitmapFont();
            
            setScreen(new TitleScreen(this));
            screenW = Gdx.graphics.getWidth();
            screenH = Gdx.graphics.getHeight();
	}
	
	@Override
	public void dispose () {
            batch.dispose();
            font.dispose();
	}
}
