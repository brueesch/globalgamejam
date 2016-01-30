package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Rock extends ActorBase {
  private int levels;
  private boolean deltaSet = false;
  private float deltaHeight;

  public Rock(int level, Vector2 position) {
    this.levels = level;
    initialize("model/environment/rock/rock.png", GameConfig.SCALE, position);
    setOrigin(0,0);
  }

  public void isHit() {
    if(!deltaSet) {
      deltaHeight = getHeight() / GameConfig.ROCK_LEVELS;
    }
    levels--;
    setY(getY()-deltaHeight);
    float playerY = ObjectController.getObject(Player.class).getY();
    ObjectController.getObject(Player.class).setY(playerY-deltaHeight);
  }
}
