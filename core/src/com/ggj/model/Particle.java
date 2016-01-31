package com.ggj.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Particle extends ActorBase {
  private float speed = 50;
  private float alpha_speed = 1f;
  private float random_box = 30;
  
  private float alpha = 0;
  private boolean finished = true;
  
  public Particle(String image_path){
    initialize(image_path, GameConfig.SCALE, new Vector2(0,0));
    ObjectController.getStage().addActor(this);
  }
  
  @Override
  public void act(float delta) {
    alpha -= alpha_speed * delta;
    setY(getY() + speed * delta);
    super.act(delta);
    if(alpha <= 0){
      finished = true;
    }
  }
  
  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.setColor(1f, 1f, 1f, alpha);
    if(!finished){
      super.draw(batch, parentAlpha);
    }
    batch.setColor(1f, 1f, 1f, 1f);
  }
  
  public void createParticle(Vector2 position){
    finished = false;
    int random_x = (int)(Math.random() * random_box);
    float random_size = (float)Math.random() * 2;
    setX(position.x + random_x);
    setY(position.y);
    setScale(random_size);
    alpha_speed = (float)Math.random() + 0.5f;
    alpha = 1;
  }
  
  public boolean isFinished(){
    return finished;
  }
}
