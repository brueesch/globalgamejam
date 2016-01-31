package com.ggj.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.ggj.game.GameConfig;
import com.ggj.game.GameSound;
import com.ggj.game.ObjectController;

public class Enemy extends ActorBase {
  private int powerlevel;
  private Element element;
  private float health;
  private float maxHealth;
  private float speed;
  private ParticleGenerator particle_generator;
  private int animation = 1;
  private float animation_time = 0.2f;
  private float animation_accumulator = 0;
  private Sound sound;

  public Enemy(Element element, Vector2 position, int power)
  {
    this(element, position);
    powerlevel = power;
  }
  public Enemy(Element element, Vector2 position)
  {
    this.element = element;
    health = 10;
    powerlevel = 1;
    speed = getSpeed();
    super.initialize(getModelName(), GameConfig.SCALE, position, new Vector2(0, 0));

    String particle_image_path = "";
    if(element.equals(Element.Fire)){
      sound = GameSound.FIRE_ENEMY;
      particle_image_path = "model/enemies/fire/particle.png";
    }else if(element.equals(Element.Water)){
      sound = GameSound.WATER_ENEMIY;
      particle_image_path = "model/enemies/water/particle.png";
    } else if (element.equals(Element.LightningEarth)) {
      sound = GameSound.LIGHTNING_ENEMY;
    }

    sound.loop(0.3f);
    if(!particle_image_path.equals("")){
      particle_generator = new ParticleGenerator(particle_image_path, this);
    }
  }

  private String getModelName()
  {
    if(animation > 3){
      animation = 1;
    }
    String elementName = element.toString().toLowerCase();
    String result = "model/enemies/" + elementName + "/" + elementName + "_stage_" + powerlevel + "_" + animation + ".png";
    
    return result;
  }
  
  private float getSpeed()
  {
    switch(element)
    {
      case Fire :
        return 45f;
      default:
        return 25f;
    }
  }
  
  @Override
  public void act(float delta)
  {
    setPosition(getX() - speed * delta, getY());

    if(this.getBounds().overlaps(ObjectController.getObject(Rock.class).getBounds())) {
      ObjectController.getObject(Rock.class).isHit();
      GameSound.EXPLOSION.play();
      killMe();
    }
    
    if(particle_generator != null){
      particle_generator.update();
    }
    
    animation_accumulator += delta;
    if(animation_accumulator > animation_time){
      animation_accumulator = 0;
      animation++;
      updateTexture();
    }
  }
  
  public void powerUp()
  {
    if(powerlevel < 3)
    {
      powerlevel++;
    }
  }
  
  public void hit(Spell spell)
  {
    boolean badCase = spell.getElement().equals(this.element);
    boolean goodCase = !badCase && (
        spell.getElement() == Element.Fire && this.element == Element.LightningEarth 
        || spell.getElement() == Element.LightningEarth && this.element == Element.Water
        || spell.getElement() == Element.Water && this.element == Element.Fire);
    
    if(!badCase && !goodCase)
    {
      // NOP?
    }
    else if(badCase)
    {
      if(powerlevel < 3)
      {
        powerlevel++;
        updateTexture();
      }
    }
    else
    {
      if(powerlevel > 1)
      {
        powerlevel--;
        updateTexture();
      }
      else
      {
        killMe();
      }
    }
  }

  private void killMe() {
    sound.stop();
    this.remove();
    if(particle_generator != null)
    {
      particle_generator.dispose();
    }
    ObjectController.getLevelController().notifyKill(this);
  }
  
  private void updateTexture() {
    super.initialize(getModelName(), GameConfig.SCALE, new Vector2(getX(), getY()), new Vector2(0,0));
  }

  public int getPowerlevel() {
    return powerlevel;
  }
  public Element getElement() {
    return element;
  }
  public float getHealth() {
    return health;
  }
  public float getMaxHealth() {
    return maxHealth;
  }
}
