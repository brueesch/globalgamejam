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
    id = ObjectController.register(this);
    this.position = position;
  }
  
  @Override
  public void draw(Batch batch, float parentAlpha) {
    if(health > 0)
    {
      batch.draw(new Texture(Gdx.files.internal("")), position.x, position.y, 10f * powerlevel, 10f * powerlevel);
    }
  }
  
  @Override
  public void act(float delta)
  {
    position.set(position.x - 5, position.y);
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
  public Vector2 getPosition() {
    return position;
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
  private Vector2 position;
  private int powerlevel;
  private Element element;
  private float health;
  private float maxHealth;
}
