package com.ggj.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ggj.game.ObjectController;

public class Spell extends Actor {
  public Spell(Element element, float damage, Vector2 position)
  {
    id = ObjectController.register(this);
    this.element = element;
    this.damage = damage;
    this.position = position;
  }
  
  private int id;
  private Vector2 position;
  private float damage;
  private Element element;
}
