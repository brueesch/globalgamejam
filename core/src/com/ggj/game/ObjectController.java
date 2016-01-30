package com.ggj.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.ggj.model.Player;
import com.ggj.model.Rock;

public class ObjectController {
  public static synchronized int register(Actor actor) {
    objects.add(actor);
    
    return ++objectId;
  }
  
  public static <T> T getObject(Class<T> callee)
  {
    for(Actor actor : objects)
    {
      if(callee.equals(actor.getClass()))
      {
        return (T)actor;
      }
    }
    
    return null;
  }
  
  private static int objectId = 0;
  private static Array<Actor> objects = new Array<Actor>();
}
