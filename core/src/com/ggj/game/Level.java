package com.ggj.game;

import java.util.Hashtable;

public class Level {
    private int totalSpawns;
    private int maxActive;
    private int killedSpawns;
    private int activeSpawns;


    private int chanceMonsterLevel1;
    private int chanceMonsterLevel2;
    private int chanceMonsterLevel3;


    public Level(int totalMaxSpawns, int maxActive, int chance1, int chance2, int chance3) {
        this.totalSpawns = totalMaxSpawns;
        this.maxActive = maxActive;
        chanceMonsterLevel1 = chance1;
        chanceMonsterLevel2 = chance2;
        chanceMonsterLevel3 = chance3;
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

    public int getKilledSpawns() {
        return killedSpawns;
    }

    public int getChanceMonsterLevel1() {
        return chanceMonsterLevel1;
    }

    public void setChanceMonsterLevel1(int chanceMonsterLevel1) {
        this.chanceMonsterLevel1 = chanceMonsterLevel1;
    }

    public int getChanceMonsterLevel2() {
        return chanceMonsterLevel2;
    }

    public void setChanceMonsterLevel2(int chanceMonsterLevel2) {
        this.chanceMonsterLevel2 = chanceMonsterLevel2;
    }

    public int getChanceMonsterLevel3() {
        return chanceMonsterLevel3;
    }

    public void setChanceMonsterLevel3(int chanceMonsterLevel3) {
        this.chanceMonsterLevel3 = chanceMonsterLevel3;
    }
}
