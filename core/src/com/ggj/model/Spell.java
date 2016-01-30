package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Spell extends ActorBase {
  private float angle;
  private int id;
  private Vector2 destination;
  private float damage;
  private Element element;
  
  public Spell(Element element, float damage, Vector2 position, Vector2 destination)
  {
    initialize("model/enemies/water/water_stage_1.png", GameConfig.SCALE, position);
    
    this.element = element;
    this.damage = damage;
    this.destination = destination;
    this.destination.y = Math.abs(ObjectController.getStage().getHeight() -this.destination.y); 
    this.angle = Math.abs((float)Math.atan(Math.abs(destination.y - position.y) / Math.abs(destination.x - position.x)));
  }

  @Override
  public void act(float delta) {
    checkCollisions();

    float speed = 100.1f;
    setPosition(getX() + speed * delta * (float)Math.cos(angle), getY() - speed * delta * (float)Math.sin(angle));
  }
  
  private void checkCollisions() {
    // NOP
  }
}
