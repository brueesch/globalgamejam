package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ActorBase extends Actor {
  private int id;
  protected TextureRegion region;
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public int getId()
  {
    return id;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
  }

  public void initialize(String texturePath, float scale, Vector2 position) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    region = new TextureRegion(image);
    
    setWidth(region.getRegionWidth());
    setHeight(region.getRegionHeight());
    setOrigin(getWidth()/2, getHeight()/2);
    setPosition(position.x, position.y);

    setScale(scale);
  }
}
