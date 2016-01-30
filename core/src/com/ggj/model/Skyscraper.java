package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.GameConfig;

public class Skyscraper extends ActorBase {
  private int levels;

  public Skyscraper(int level, Vector2 position) {
    this.levels = level;
    initialize("model/environment/skyscraper/skyscraper.png", GameConfig.SCALE, position);
  }
  
  public int getLevel() {
    return levels;
  }

  public void setLevel(int level) {
    this.levels = level;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  @Override
  public Actor hit(float x, float y, boolean touchable) {
    return super.hit(x, y, touchable);
  }
}
