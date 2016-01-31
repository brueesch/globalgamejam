package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ParticleGenerator {
  private int particle_number = 200;
  private float particle_create_speed = 20;
  private float particle_create_time = 0.2f;
  
  private Array<Particle> particles;
  private float particle_create_accumulator = 0;
  
  private ActorBase actor;
  
  public ParticleGenerator(String image_path, ActorBase actor){
    this.actor = actor;
    particles = new Array<Particle>();
    for(int i = 0; i < particle_number; i++){
      particles.add(new Particle(image_path));
    }
  }
  
  public void update(){
    float delta_time = Gdx.graphics.getDeltaTime();
    particle_create_accumulator += delta_time * particle_create_speed;
    if(particle_create_accumulator >= particle_create_time){
      particle_create_accumulator = 0;
      Particle particle = findFreeParticle();
      particle.createParticle(new Vector2(actor.getX(), actor.getY()));
    }
  }
  
  private Particle findFreeParticle(){
    for(Particle particle : particles){
      if(particle.isFinished()){
        return particle;
      }
    }
    return null;
  }
}
