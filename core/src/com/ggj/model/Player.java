package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Player extends Actor{
	private int spell_combo_size = 5;
	private float scale = 2;
	
	private Texture image;	
	private TextureRegion region;
	private Array<String> spell_combos;
	
	public Player(){
		spell_combos = new Array<String>();
		image = new Texture(Gdx.files.internal("model/player/player.png"));
		region = new TextureRegion(image);
		
		
		setWidth(region.getRegionWidth());
		setHeight(region.getRegionHeight());
		setOrigin(getWidth()/2, getHeight()/2); 
		
		setX(100);
		setY(100);
		setScale(scale);
	}
	
	@Override
	public void act(float delta) {
		playerInput();
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	private void playerInput(){
		if(spell_combos.size > spell_combo_size){
			spell_combos.clear();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
			spell_combos.add("left");
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			spell_combos.add("up");
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			spell_combos.add("down");
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			spell_combos.add("right");
		}
	}
}
