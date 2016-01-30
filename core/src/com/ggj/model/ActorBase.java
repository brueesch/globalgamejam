package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class ActorBase extends Actor {
  private int id;
  private Rectangle bounds;
  private Array<TextureRegion> regions;
  private int region_number = 0;
  
  public ActorBase(){
    regions = new Array<TextureRegion>();
  }
  
  public Rectangle getBounds()
  {
    float width = regions.get(0).getRegionWidth();
    float height = regions.get(0).getRegionHeight();
    
    return new Rectangle(getX(), getY(), width * getScaleX(), height * getScaleY());
  }
  
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
    batch.draw(regions.get(region_number), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
  }

  public void initialize(String texturePath, float scale, Vector2 position) {
    Vector2 origin = new Vector2(getWidth()/2, getHeight()/2);
    initialize(texturePath, scale, position, origin);
  }
  
  public void initialize(Array<String> texturePaths, float scale, Vector2 position) {
    TextureRegion region = null;
    
    for(String path : texturePaths){
      Texture image = new Texture(Gdx.files.internal(path));
      region = new TextureRegion(image);
      regions.add(region);
    }
    
    setWidth(region.getRegionWidth());
    setHeight(region.getRegionHeight());
    setOrigin(getWidth()/2, getHeight()/2);
    setPosition(position.x, position.y);

    setScale(scale);
  }
  
  public void initialize(String texturePath, float scale, Vector2 position, Vector2 origin) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    TextureRegion region = new TextureRegion(image);
    regions.add(region);
    
    setWidth(region.getRegionWidth());
    setHeight(region.getRegionHeight());
    setOrigin(origin.x, origin.y);
    setPosition(position.x, position.y);

    setScale(scale);
  }
  
  public Array<TextureRegion> getRegions(){
    return regions;
  }
  
  public void setRegion(int region_number){
    this.region_number = region_number;
  }
  
  public int getRegionNumber(){
    return region_number;
  }
}
