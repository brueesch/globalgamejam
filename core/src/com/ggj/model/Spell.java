package com.ggj.model;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
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
    int angle_correcton = 45;
    
    this.element = element;
    this.damage = damage;
    this.destination = destination.sub(getWidth()/2, getHeight()/2);
    initialize(getModelName(), GameConfig.SCALE, position);
    
    destination.sub(ObjectController.getObject(Player.class).getVectorPosition());
    destination.nor();
    setRotation(destination.angle()+angle_correcton);
  }
  
  private String getModelName()
  {
    String elementName = element.toString().toLowerCase();
    String result = "model/shots/" + elementName + ".png";
    
    return result;
  }
  
  public Element getElement()
  {
    return this.element;
  }

  @Override
  public void act(float delta) {
    checkCollisions();
    
    float speed = 200.1f;
    setPosition(getX() + speed * delta * destination.x, getY() + speed * delta * destination.y);
  }

  private void checkCollisions() {
    List<Enemy> enemies = ObjectController.getList(Enemy.class);
    for(Enemy enemy : enemies)
    {
      Rectangle myBounds = this.getBounds();
      Rectangle hisBounds = enemy.getBounds();
      if(myBounds.overlaps(hisBounds))
      {
        // Don't kill the mobs yet until we have a handler for respawn / normal spawn
        //enemy.remove();
        enemy.hit(this);
        enemy.moveBy(300, 0);
        this.remove();
      }
    }
  }
}
