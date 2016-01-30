package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Rock extends ActorBase {
  private int levels;

  public Rock(int level, Vector2 position) {
    this.levels = level;
    initialize("model/environment/rock/rock.png", GameConfig.SCALE, position);
    setOrigin(0,0);
  }
  
  public int getLevel() {
    return levels;
  }

  public void setLevel(int level) {
    this.levels = level;
  }

  public void isHit() {
    setY(getY()-getHeight()/GameConfig.ROCK_LEVELS);
    ObjectController.getObject(Player.class).setY(getY()-getHeight()/GameConfig.ROCK_LEVELS);
  }


}
