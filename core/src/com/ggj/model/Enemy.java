package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.ObjectController;

public class Enemy extends Actor {
  public Enemy(Element element, Vector2 position)
  {
    this.element = element;
    id = ObjectController.register(this);
    this.setPosition(position.x, position.y);
    health = 10;
    
    Texture image = new Texture(Gdx.files.internal(getModelName()));
    region = new TextureRegion(image);
    speed = getSpeed();
    
    setWidth(region.getRegionWidth());
    setHeight(region.getRegionHeight());
    setOrigin(getWidth()/2, getHeight()/2);

    setScale(scale);
  }
  
  private String getModelName()
  {
    String elementName = element.toString().toLowerCase();
    String result = "model/enemies/" + elementName + "/" + elementName + "_stage_1.png";
    
    return result;
  }
  
  private float getSpeed()
  {
    switch(element)
    {
      case Wind:
        return 0.9f;
      default:
        return 0.5f;
    }
  }
  
  @Override
  public void draw(Batch batch, float parentAlpha) {
    if(health > 0)
    {
      batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
  }
  
  @Override
  public void act(float delta)
  {
    setPosition(getX() - speed, getY());
  }
  
  public void powerUp()
  {
    if(powerlevel < 3)
    {
      powerlevel++;
      notify();
    }
  }
  
  public void hit(Spell spell)
  {
    if(powerlevel > 1)
    {
      powerlevel--;
      notify();
    }
    else
    {
      health = 0;
    }
  }
  
  public int getId() {
    return id;
  }
  public int getPowerlevel() {
    return powerlevel;
  }
  public Element getElement() {
    return element;
  }
  public float getHealth() {
    return health;
  }
  public float getMaxHealth() {
    return maxHealth;
  }

  private int id;
  private int powerlevel;
  private Element element;
  private float health;
  private float maxHealth;
  private float speed;
  private float scale = 2;
  TextureRegion region;
}
