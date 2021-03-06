package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by chris on 30.01.16.
 */
public class GameSound {

  public static Music BACKGROUND_SOUND = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));

  public static Sound EARTH_ENEMY = Gdx.audio.newSound(Gdx.files.internal("sounds/earthEnemy.wav"));
  public static Sound FIRE_ENEMY = Gdx.audio.newSound(Gdx.files.internal("sounds/fireEnemy.wav"));
  public static Sound LIGHTNING = Gdx.audio.newSound(Gdx.files.internal("sounds/lightning.wav"));
  public static Sound LIGHTNING_ENEMY = Gdx.audio.newSound(Gdx.files.internal("sounds/lightningEnemy.wav"));
  public static Sound MAGIC_SPELL1 = Gdx.audio.newSound(Gdx.files.internal("sounds/magicSpell1.wav"));
  public static Sound MAGIC_SPELL2 = Gdx.audio.newSound(Gdx.files.internal("sounds/magicSpell2.wav"));
  public static Sound WATER_ENEMIY = Gdx.audio.newSound(Gdx.files.internal("sounds/waterEnemy.wav"));
  public static Sound WIND_ENEMY = Gdx.audio.newSound(Gdx.files.internal("sounds/windEnemy.wav"));
  public static Sound EXPLOSION = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));



}
