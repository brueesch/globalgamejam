package com.ggj.model;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.ggj.game.GameConfig;

public class Enemy extends ActorBase {
  private int powerlevel;
  private Element element;
  private float health;
  private float maxHealth;
  private float speed;
  
  public Enemy(Element element, Vector2 position)
  {
    this.element = element;
    health = 10;
    
    speed = getSpeed();
    super.initialize(getModelName(), GameConfig.SCALE, position);
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
    }
  }
  
  public void hit(Spell spell)
  {
    if(powerlevel > 1)
    {
      powerlevel--;
    }
    else
    {
      health = 0;
    }
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
}
