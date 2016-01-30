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

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  @Override
  public Actor hit(float x, float y, boolean touchable) {
    setY(getY()-getHeight()/GameConfig.ROCK_LEVELS);
    //get player
    //set player position

    // load the drop sound effect and the rain background "music"
//    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
//
//    // start the playback of the background music immediately
//    rainMusic.setLooping(true);
//    rainMusic.play();


    return this;
  }
}
