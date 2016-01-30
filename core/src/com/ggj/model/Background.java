package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ggj.game.GameConfig;

public class Background {

  private Texture backgroundImage = new Texture(Gdx.files.internal("model/environment/background/background.png"));
  private Texture cloudsFrontImage = new Texture(Gdx.files.internal("model/environment/background/cloudsFront.png"));
  private TextureRegion regionBackgroundImage;
  private TextureRegion regionCloudsFrontImage;

  public Background() {
    initializeBackground("model/environment/background/background.png");
    initializeCloudFront("model/environment/background/cloudsFront.png");
//    initializeBackground("model/environment/background/cloudsBehind.png", GameConfig.SCALE, GameConfig.BACKGROUND_OFFSET);
//    initializeBackground("model/environment/background/cloudsFront.png", GameConfig.SCALE, GameConfig.BACKGROUND_OFFSET);
//    initializeBackground("model/environment/background/cloudsMiddle.png", GameConfig.SCALE, GameConfig.BACKGROUND_OFFSET);
  }


  public void initializeBackground(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionBackgroundImage = new TextureRegion(image);
  }

  public void initializeCloudFront(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionCloudsFrontImage = new TextureRegion(image);
  }


  public void draw(Batch batch, float parentAlpha) {
    batch.begin();
    drawBackground(batch);
    drawCloud(batch, parentAlpha);
    batch.end();

  }

  private void drawBackground(Batch batch) {
//    batch.draw(regionBackgroundImage, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() , GameConfig.SCALE, GameConfig.SCALE, 0);
    batch.draw(regionBackgroundImage, 0, 0, regionBackgroundImage.getRegionWidth() * GameConfig.SCALE, regionBackgroundImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawCloud(Batch batch, float parentAlpha) {
    float size = parentAlpha * 10;
    batch.draw(regionCloudsFrontImage, 0, 0, regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE, regionCloudsFrontImage.getRegionHeight() * GameConfig.SCALE);
  }
}
