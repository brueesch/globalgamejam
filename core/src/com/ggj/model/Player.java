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
    initialize("model/player/player.png", GameConfig.SCALE, position);
  }

  @Override
  public void act(float delta) {
    playerInput();
    super.act(delta);
  }

  private void playerInput() {
    if (spell_combos.size > spell_combo_size) {
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
}
