package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Skyscraper extends Actor {

  private int levels;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private int id;
  private TextureRegion region;


  public Skyscraper(int level, Vector2 position) {
    super();
    this.id = ObjectController.register(this);
    this.levels = level;
    setX(position.x);
    setY(position.y);
    initializeTexture();

  }

  public int getLevel() {
    return levels;
  }

  public void setLevel(int level) {
    this.levels = level;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
  }

  @Override
  public Actor hit(float x, float y, boolean touchable) {
    return super.hit(x, y, touchable);

  }

  private void initializeTexture() {
    Texture image = new Texture(Gdx.files.internal("model/environment/skyscraper/skyscraper.png"));
    region = new TextureRegion(image);

    setWidth(region.getRegionWidth());
    setHeight(region.getRegionHeight());
    setOrigin(getWidth()/2, getHeight()/2);

    setScale(GameConfig.SCALE);

  }

}
