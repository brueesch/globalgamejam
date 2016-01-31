package com.ggj.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.ggj.model.ActorBase;
import com.ggj.model.Element;
import com.ggj.model.Enemy;
import com.ggj.model.Player;
import com.ggj.model.Rock;

public class LevelController { 
  private ArrayList<Level> levels;
  private int levelNumber;
  
  public void Reset()
  {
    levels = new ArrayList<Level>();
    
    levels.add(new Level(2, 1, 100, 0, 0));
    levels.add(new Level(3, 2, 95, 5, 0));
    levels.add(new Level(4, 2, 90, 10, 0));
    levels.add(new Level(5, 3, 80, 20, 1));
    levels.add(new Level(6, 4, 65, 30, 5));
    levels.add(new Level(50, 50, 0, 0, 100));
  }
  
  public void Start(int levelNumber) {
    this.levelNumber = levelNumber;
    CheckSpawnThings();
    
//    for(int i=levelNumber;i<levels.size();i++)
//    {
//      Level level = levels.get(i);
//      boolean notLoss = false;
//      
//    }
    
    //ObjectController.getStage().addAction(action);
    
  }
  
  public void CheckSpawnThings()
  {
    Level level = levels.get(levelNumber);
    if(level.getTotalSpawns() == 0 || level.getTotalSpawns() - level.getKilledSpawns() > 0)
    {
      while(level.getActiveSpawns() < level.getMaxActive() && level.getTotalSpawns() - level.getKilledSpawns() > 0)
      {
        //notLoss = thingses
        if(level.getActiveSpawns() < level.getMaxActive() && level.getTotalSpawns() - level.getKilledSpawns() > level.getMaxActive())
        {
          level.spawn();
          int rnd = (int)(Math.random() * 3);
          Element ele = Element.Fire;
          switch(rnd)
          {
            case 1:
              ele = Element.LightningEarth;
              break;
            case 2:
              ele = Element.Water;
              break;
          }
          
          Enemy enemy = new Enemy(ele, new Vector2(Gdx.graphics.getWidth(), GameConfig.GROUND_Y_POSITION));
  
          ExtendedStageController stage = ObjectController.getStage();
          stage.addActor(enemy);
        }
      }
    }
    else
    {
      if(level.getTotalSpawns() > 0)
      {
        levelNumber++;
      }
    }
  }

  public void notifyKill(ActorBase actor) {
    this.levels.get(levelNumber).registerKill();
    CheckSpawnThings();
    ObjectController.unregister(actor);
    actor.dispose();
  }
}
