package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ggj.model.Player;
import com.ggj.model.Skyscraper;

public class GameController implements Screen{
	
	private ScreenViewport screenviewport;
	private Stage stage;
	private OrthographicCamera camera;
	private Player player;
	private Skyscraper skyscraper;
	
	public GameController(final GlobalGameJam globalgamejam){
		setUpGame();
		createWorld();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		player.update();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void createWorld(){
		player = new Player();
		skyscraper = new Skyscraper(5, 50, new Vector2(10,10));
	}
	
	private void setUpGame(){
		screenviewport = new ScreenViewport();
		stage = new Stage(screenviewport);
		Gdx.input.setInputProcessor(stage);
		camera = (OrthographicCamera)stage.getCamera();
	}
}
