package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.ggj.game.ObjectController;

public class Skyscraper {
	
	private int levels;
	private int levelHeight;
	private Vector2 position;
	private int id;
	
		
	public Skyscraper(int level, int levelHeight, Vector2 position) {
		super();
		this.id = ObjectController.getNextId();
		this.levels = level;
		this.levelHeight = levelHeight;
		this.position = position;
	}
	
	public int getLevel() {
		return levels;
	}
	public void setLevel(int level) {
		this.levels = level;
	}
	public int getLevelHeight() {
		return levelHeight;
	}
	public void setLevelHeight(int levelHeight) {
		this.levelHeight = levelHeight;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void hit() {
		levels--;
	}
	
	
	public void update() {
		draw();
		
	}
	
	
	private void draw() {
		for (int i = 0; i < levels; i++) {
			drawLevel();
		}
	}

	private void drawLevel() {
		//TODO: I'm drawing, lalalala, i'm drawing, lalalala
		
	}

}
