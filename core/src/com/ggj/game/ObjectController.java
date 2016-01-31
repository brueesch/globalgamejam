package com.ggj.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggj.model.ActorBase;
import com.ggj.model.Player;
import com.ggj.model.Rock;

public class ObjectController {
  public static synchronized int register(ActorBase actor) {
    objects.add(actor);
    
    return ++objectId;
  }
  
  public static synchronized void unregister(ActorBase actor) {
    for(int i=0;i<objects.size;i++)
    {
      if(((ActorBase)objects.get(i)).getId() == actor.getId())
      {
        objects.removeIndex(i);
        break;
      }
    }
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
  
  public static <T> List<T> getList(Class<T> callee)
  {
    List<T> result = new ArrayList<T>();
    for(Actor actor : objects)
    {
      if(callee.equals(actor.getClass()))
      {
        result.add((T)actor);
      }
    }
    
    return result;
  }
  
  public static ExtendedStageController getStage()
  {
    return stage;
  }
  
  public static void setStage(ExtendedStageController newStage)
  {
    stage = newStage;
  }
  
  public static void setInterface(GameInterface game_interface){
    ObjectController.game_interface = game_interface;
  }
  
  public static GameInterface getInterface(){
    return game_interface;
  }
  
  public static void setLevelController(LevelController levelController)
  {
    ObjectController.levelController = levelController;
  }
  
  public static LevelController getLevelController()
  {
    return levelController;
  }
  
  public static void setViewPort(Viewport port){
    view_port = port;
  }
  
  public static Viewport getViewPort(){
    return view_port;
  }
  
  private static Viewport view_port;
  
  private static ExtendedStageController stage;
  private static GameInterface game_interface;
  private static LevelController levelController;
  private static int objectId = 0;
  private static Array<ActorBase> objects = new Array<ActorBase>();
}
