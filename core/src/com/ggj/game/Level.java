package com.ggj.game;

import java.util.Hashtable;

public class Level {
  private int totalSpawns;
  private int maxActive;
  private int killedSpawns;
  private int activeSpawns;
  private Hashtable<MonsterLevel, Integer> chanceSpawns;
  
  // For later use
  //private Hashtable<MonsterLevel, Integer> guaranteedSpawns;
  
  public Level()
  {
    chanceSpawns = new Hashtable<MonsterLevel, Integer>(); 
  }
  
  public Level(int totalMaxSpawns, int maxActive, int chance1, int chance2, int chance3)
  {
    this();
    this.totalSpawns = totalMaxSpawns;
    this.maxActive = maxActive;
    
    addDifficulty(MonsterLevel.Level1, chance1);
    addDifficulty(MonsterLevel.Level2, chance2);
    addDifficulty(MonsterLevel.Level3, chance3);
  }
  
  public Hashtable<MonsterLevel, Integer> getDifficulties()
  {
    return chanceSpawns;
  }
  
  public void addDifficulty(MonsterLevel level, int spawnChance)
  {
    chanceSpawns.put(level, spawnChance);
  }

  public int getTotalSpawns() {
    return totalSpawns;
  }

  public int getMaxActive() {
    return maxActive;
  }
  
  public synchronized void registerKill() {
    killedSpawns++;
    activeSpawns--;
  }
  
  public int getActiveSpawns() {
    return activeSpawns;
  }
  
  public synchronized void spawn() {
    activeSpawns++;
  }
  
  public int getKilledSpawns()
  {
    return killedSpawns;
  }
}
