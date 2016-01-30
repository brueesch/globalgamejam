package com.ggj.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ObjectController {
  public static synchronized int register(Actor actor) {
    objects.add(actor);
    
    return ++objectId;
  }
  
  private static int objectId = 0;
  private static Array<Actor> objects = new Array<Actor>();
}
