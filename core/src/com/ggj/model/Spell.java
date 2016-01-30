package com.ggj.model;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    
    // Calculate correct y position of the cursor
    this.destination.y = Math.abs(ObjectController.getStage().getHeight() -this.destination.y); 
    this.angle = Math.abs((float)Math.atan(Math.abs(destination.y - position.y) / Math.abs(destination.x - position.x)));
  }

  @Override
  public void act(float delta) {
    List<Enemy> enemies = ObjectController.getList(Enemy.class);
    for(Enemy enemy : enemies)
    {
      Rectangle myBounds = this.getBounds();
      Rectangle hisBounds = enemy.getBounds();
      if(myBounds.overlaps(hisBounds))
      {
        // Don't kill the mobs yet until we have a handler for respawn / normal spawn
        //enemy.remove();
        enemy.moveBy(300, 0);
        this.remove();
      }
    }
    
    float speed = 100.1f;
    setPosition(getX() + speed * delta * (float)Math.cos(angle), getY() - speed * delta * (float)Math.sin(angle));
  }
}
