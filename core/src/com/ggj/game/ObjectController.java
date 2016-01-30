package com.ggj.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.ggj.model.Player;

public class ObjectController {
  protected static synchronized int register(Actor actor) {
    objects.add(actor);
    
    return ++objectId;
  }
  
  protected static Player getPlayer()
  {
    for(Actor actor : objects)
    {
      if(actor instanceof Player)
      {
        return (Player)actor;
      }
    }
    
    return null;
  }
  
  private static int objectId = 0;
  private static Array<Actor> objects = new Array<Actor>();
}
