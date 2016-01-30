package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ggj.game.GameConfig;

public class Player extends ActorBase {
  private int spell_combo_size = 5;
  private Array<String> spell_combos;

  public Player(Vector2 position) {
    spell_combos = new Array<String>();
    initialize("model/player/player.png", GameConfig.SCALE, position, new Vector2(0,0));
  }

  @Override
  public void act(float delta) {
    playerInput();
    super.act(delta);
  }

  private void playerInput() {
    if (spell_combos.size >= spell_combo_size) {
      castSpell();
      spell_combos.clear();
    }

    if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
      spell_combos.add("left");
    }
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      spell_combos.add("up");
    }
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      spell_combos.add("down");
    }
    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
      spell_combos.add("right");
    }
  }
  
  public String getSpellString(){
    String spells = "";
    for(String spell : spell_combos){
      spells += spell + "  ";
    }
    return spells;
  }
  
  private void castSpell(){
    if(spell_combos.get(0).equals("up") && spell_combos.get(1).equals("up") 
        && spell_combos.get(2).equals("left") && spell_combos.get(3).equals("right")
        && spell_combos.get(4).equals("down")){
      System.out.println("Cast Water");
    }else if(spell_combos.get(0).equals("left") && spell_combos.get(1).equals("right") 
        && spell_combos.get(2).equals("up") && spell_combos.get(3).equals("down")
        && spell_combos.get(4).equals("down")){
      System.out.println("Cast Fire");
    }else if(spell_combos.get(0).equals("down") && spell_combos.get(1).equals("right") 
        && spell_combos.get(2).equals("up") && spell_combos.get(3).equals("left")
        && spell_combos.get(4).equals("down")){
      System.out.println("Cast Bolt");
    }
  }
}
