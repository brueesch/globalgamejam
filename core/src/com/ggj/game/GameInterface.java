package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ggj.model.Player;

public class GameInterface {
  private static BitmapFont font;
  private int screen_width;
  private int screen_height;
  private Player player;
  
  public GameInterface(){
    font = new BitmapFont(Gdx.files.internal("font/pixel.fnt"));
    player = ObjectController.getPlayer();
  }
  
  public void draw(SpriteBatch batch){
    screen_width = Gdx.graphics.getWidth();
    screen_height = Gdx.graphics.getHeight();
    
    batch.begin();
    font.setColor(0, 0, 0, 1);
    font.draw(batch, "Spells:   " + player.getSpellString(), 15, screen_height - 15);
    batch.end();
  }
}
