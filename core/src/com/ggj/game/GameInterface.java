package com.ggj.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ggj.model.Arrow;
import com.ggj.model.Player;

public class GameInterface {
  private static BitmapFont font;
  private int screen_width;
  private int screen_height;
  private Player player;
  private Array<Arrow> arrows;
  private int height_start = 140;
  private int width_start = 300;
  private int height_gap = 40;
  
  public GameInterface(){
    font = new BitmapFont(Gdx.files.internal("font/pixel.fnt"));
    player = ObjectController.getObject(Player.class);
    
    screen_width = Gdx.graphics.getWidth();
    screen_height = Gdx.graphics.getHeight();
    
    createArrows();
  }
  
  public void draw(SpriteBatch batch){
    batch.begin();
    font.setColor(1, 1, 1, 1);
    int with_offset = 170;
    int height_offset = -18;
    int mana_with = 24;
    font.draw(batch, "Mana:   " + player.getMana(), screen_width - width_start + mana_with, screen_height - height_start - height_offset);
    height_offset += height_gap;
    font.draw(batch, "Water", screen_width - width_start + with_offset, screen_height - height_start - height_offset);
    height_offset += height_gap;
    font.draw(batch, "Fire", screen_width - width_start + with_offset, screen_height - height_start - height_offset);
    height_offset += height_gap;
    font.draw(batch, "Lighting", screen_width - width_start + with_offset, screen_height - height_start - height_offset);
    batch.end();
  }
  
  private void createArrows(){
    int height_offset = height_start;
    int width_gap = 27;
    int width_offset = width_start - width_gap;
    
    arrows = new Array<Arrow>();
    
    height_offset += height_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "up", 0));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "up", 1));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "left", 2));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "right", 3));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "down", 4));
    
    
    width_offset = width_start - width_gap;
    height_offset += height_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "left", 0));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "right", 1));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "up", 2));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "down", 3));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "down", 4));
    
    
    width_offset = width_start - width_gap;
    height_offset += height_gap;    
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "down", 0));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "right", 1));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "up", 2));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "left", 3));
    width_offset -= width_gap;
    arrows.add(new Arrow(new Vector2(screen_width - width_offset, screen_height - height_offset), "down", 4));
    
    for(Arrow arrow : arrows){
      ObjectController.getStage().addActor(arrow);
    }
  }
  
  public void updateArrow(){
    Array<String> combos = ObjectController.getObject(Player.class).getCombos();
    int i = 0;
    for(String combo : combos){
      int j = 0;
      boolean found_arrow = false;
      for(Arrow arrow : getArrowsBySequenceNumber(i)){
        if(arrow.getDirection().equals(combo)){
          if(i > 0 && getArrowsBySequenceNumber(i-1).get(j).getActivation()){
            arrow.setActivation(true);
            found_arrow = true;
          }else if(i == 0){
            arrow.setActivation(true);
            found_arrow = true;
          }
        }
        j++;
      }
      if(!found_arrow){
        clearArrow();
      }
      i++;
    }
  }
  
  public Array<Arrow> getArrowsBySequenceNumber(int value){
    Array<Arrow> sequence_arrows = new Array<Arrow>();
    for(Arrow arrow : arrows){
      if(arrow.getSequenceNumber() == value){
        sequence_arrows.add(arrow);
      }
    }
    return sequence_arrows;
  }
  
  public void clearArrow(){
    for(Arrow arrow : arrows){
      arrow.setActivation(false);
      ObjectController.getObject(Player.class).getCombos().clear();
    }
  }
}
