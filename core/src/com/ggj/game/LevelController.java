package com.ggj.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ggj.model.ActorBase;
import com.ggj.model.Element;
import com.ggj.model.Enemy;

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
  }
  
  public int getLevelNumber()
  {
    return levelNumber;
  }
  
  public void CheckSpawnThings()
  {
    Level level = levels.get(levelNumber - 1);
    boolean noMonstersSpawnedYet = level.getKilledSpawns() == 0 && level.getActiveSpawns() == 0;
    boolean levelHasMoreMonsterThanSpawnedAndKilled = level.getTotalSpawns() - level.getKilledSpawns() - level.getActiveSpawns() > 0;
    if(noMonstersSpawnedYet || levelHasMoreMonsterThanSpawnedAndKilled)
    {
      while(level.getActiveSpawns() < level.getMaxActive() && levelHasMoreMonsterThanSpawnedAndKilled)
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
        
        float spawnStart = (float)Gdx.graphics.getWidth();

        // Add some more time to prepare after level changes
        if(level.getKilledSpawns() == 0 && levelNumber > 1)
        {
          spawnStart += 10f;
        }
        
        List<Enemy> enemies = ObjectController.getList(Enemy.class);
        boolean recalculate = false;
        do
        {
          for(Enemy spawnedEnemy : enemies)
          {
            if(spawnedEnemy.getX() - spawnStart > 10f)
            {
              spawnStart -= 5f;
              recalculate = true;
            }
          }
        } while(recalculate);
        Enemy enemy = new Enemy(ele, new Vector2(spawnStart, GameConfig.GROUND_Y_POSITION));

        ExtendedStageController stage = ObjectController.getStage();
        stage.addActor(enemy);
      }
    }
    else
    {
      if(levelNumber < 6)
      {
        
        levelNumber++;
        Start(levelNumber);
      }
      else
      {
        // WIN
      }
    }
  }

  public void notifyKill(ActorBase actor) {
    this.levels.get(levelNumber - 1).registerKill();
    CheckSpawnThings();
    ObjectController.unregister(actor);
    actor.dispose();
  }
}
