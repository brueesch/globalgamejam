package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ggj.game.GameConfig;
import com.ggj.game.ObjectController;

public class Player extends ActorBase {
  private int spell_combo_size = 5;
  private Array<String> spell_combos;
  private float mana = 0.0f;

  public Player(Vector2 position) {
    spell_combos = new Array<String>();
    initialize("model/player/player.png", GameConfig.SCALE, position, new Vector2(0,0));
  }

  @Override
  public void act(float delta) {
    playerInput();
    mana = Math.min(mana + 1.035f, 10f);
    super.act(delta);
  }
  
  public float getMana()
  {
    return mana;
  }

  private void playerInput() {
    if (spell_combos.size >= spell_combo_size) {
      castSpell();
      spell_combos.clear();
    }

    if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
      spell_combos.add("left");
      ObjectController.getInterface().updateArrow();
    }
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      spell_combos.add("up");
      ObjectController.getInterface().updateArrow();
    }
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      spell_combos.add("down");
      ObjectController.getInterface().updateArrow();
    }
    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
      spell_combos.add("right");
      ObjectController.getInterface().updateArrow();
    }
    if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      if(mana >= 4.0f)
      {
        mana -= 4.0f;
        shoot(Element.Fire);
      }
    }
  }
  
  private void shoot(Element element) {
    Player player = ObjectController.getObject(Player.class);
    Spell spell = new Spell(Element.Fire, 10, new Vector2(player.getX(), player.getY()), new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    ObjectController.getStage().addActor(spell);
  }

  public String getSpellString(){
    String spells = "";
    for(String spell : spell_combos){
      spells += spell + "  ";
    }
    return spells;
  }
  
  public Array<String> getCombos(){
    return spell_combos;
  }
  
  private void castSpell(){
    if(spell_combos.get(0).equals("up") && spell_combos.get(1).equals("up") 
        && spell_combos.get(2).equals("left") && spell_combos.get(3).equals("right")
        && spell_combos.get(4).equals("down")){
      shoot(Element.Water);
    }else if(spell_combos.get(0).equals("left") && spell_combos.get(1).equals("right") 
        && spell_combos.get(2).equals("up") && spell_combos.get(3).equals("down")
        && spell_combos.get(4).equals("down")){
      shoot(Element.Fire);
    }else if(spell_combos.get(0).equals("down") && spell_combos.get(1).equals("right") 
        && spell_combos.get(2).equals("up") && spell_combos.get(3).equals("left")
        && spell_combos.get(4).equals("down")){
      shoot(Element.Lightning);
    }
    
    ObjectController.getInterface().clearArrow();
  }
}
