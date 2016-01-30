package com.ggj.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.GameConfig;

public class Background extends ActorBase{
  
  public Background() {
    initialize("model/environment/background/background.png", GameConfig.SCALE, GameConfig.BACKGROUND_OFFSET);
  }
}
