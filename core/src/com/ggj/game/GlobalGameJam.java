package com.ggj.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GlobalGameJam extends Game {
	
	public static final int LOG_LEVEL = Application.LOG_NONE;
	
	private Screen game_controller;
	
	@Override
	public void create () {
		game_controller = new GameController(this);
		this.setScreen(game_controller);
		Gdx.app.setLogLevel(LOG_LEVEL);
	}

	@Override
	public void render () {
		super.render();
	}
}
