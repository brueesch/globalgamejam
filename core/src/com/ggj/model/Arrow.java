package com.ggj.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ggj.game.GameConfig;

public class Arrow extends ActorBase{
  
  private boolean activated = false;
  private int sequence_number;
  private String direction;

  public Arrow(Vector2 position, String direction, int sequence_number){
    this.sequence_number = sequence_number;
    this.direction = direction;
    
    Array<String> paths = new Array<String>();
    paths.add("interface/arrow/arrow1.png");
    paths.add("interface/arrow/arrow2.png");
    initialize(paths, GameConfig.SCALE, position);
    
    if(direction.equals("down")){
      setOrigin(getOriginX()+1, getOriginY());
      setRotation(270);
    }else if(direction.equals("left")){
      setOrigin(getOriginX()+1, getOriginY()+1);
      setRotation(180);
    }else if(direction.equals("up")){
      setOrigin(getOriginX(), getOriginY()+1);
      setRotation(90);
    }else{
      setOrigin(getOriginX()-1, getOriginY()-1);
    }
  }
  
  @Override
  public void act(float delta) {
    // TODO Auto-generated method stub
    super.act(delta);
  }
  
  public void setActivation(boolean value){
    activated = value;
    if(activated){
      setRegion(1);
    }else{
      setRegion(0);
    }
  }
  
  public boolean getActivation(){
    return activated;
  }
  
  public int getSequenceNumber(){
    return sequence_number;
  }
  
  public String getDirection(){
    return direction;
  }
}
