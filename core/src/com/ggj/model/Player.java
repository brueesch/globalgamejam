package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.ggj.game.GameConfig;
import com.ggj.game.GameSound;
import com.ggj.game.ObjectController;

public class Player extends ActorBase {
  private int spell_combo_size = 5;
  private float spell_time = 0.5f;
  
  private Array<String> spell_combos;
  private float mana = 0.0f;
  private float magic_time_accumulator = 0;
  private boolean magic_time = false;

  public Player(Vector2 position) {
    spell_combos = new Array<String>();
    Array<String> paths = new Array<String>();
    paths.add("model/player/player.png");
    paths.add("model/player/player_left.png");
    paths.add("model/player/player_up.png");
    paths.add("model/player/player_down.png");
    paths.add("model/player/player_right.png");
    initialize(paths, GameConfig.SCALE, position, new Vector2(0,0));
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
      setRegion(1);
      magic_time = true;
    }
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      spell_combos.add("up");
      ObjectController.getInterface().updateArrow();
      setRegion(2);
      magic_time = true;
    }
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      spell_combos.add("down");
      ObjectController.getInterface().updateArrow();
      setRegion(3);
      magic_time = true;
    }
    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
      spell_combos.add("right");
      ObjectController.getInterface().updateArrow();
      setRegion(4);
      magic_time = true;
    }
    if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      if(mana >= 4.0f)
      {
        switch(temp_switch)
        {
          case 1:
            shoot(Element.Water);
            break;
          case 2:
            shoot(Element.Fire);
            break;
          case 3:
            shoot(Element.LightningEarth);
            temp_switch = 0;
            break;
        }
        temp_switch++;
        spell_combos.clear();
      }
    }
    if(magic_time){
      magic_time_accumulator += Gdx.graphics.getDeltaTime();
      if(magic_time_accumulator > spell_time){
        setRegion(0);
        magic_time = false;
        magic_time_accumulator = 0;
      }
    }
  }
  
  private int temp_switch = 1;
  
  private void shoot(Element element) {
    Player player = ObjectController.getObject(Player.class);
    Vector3 mouse_position = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    ObjectController.getStage().getCamera().unproject(mouse_position);
    Spell spell = new Spell(element, 10, getVectorPosition(), new Vector2(mouse_position.x, mouse_position.y));
    GameSound.MAGIC_SPELL1.play();
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
      shoot(Element.LightningEarth);
    }
    
    ObjectController.getInterface().clearArrow();
  }
  
  public Vector2 getVectorPosition(){
    return new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
  }
}
