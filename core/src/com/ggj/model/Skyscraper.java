package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.ObjectController;

public class Skyscraper extends Actor {

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

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (int i = 0; i < levels; i++) {
            drawLevel(batch, position);
        }

    }

    private void drawLevel(Batch batch, Vector2 position) {
        batch.draw(new Texture(Gdx.files.internal("model/enviorment/skyscraper.png")), position.x, position.y);

    }

}
