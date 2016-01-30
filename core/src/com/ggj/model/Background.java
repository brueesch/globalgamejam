package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ggj.game.GameConfig;

public class Background {

  private Texture backgroundImage = new Texture(Gdx.files.internal("model/environment/background/background.png"));
  private Texture cloudsFrontImage = new Texture(Gdx.files.internal("model/environment/background/cloudsFront.png"));
  private TextureRegion regionBackgroundImage;
  private TextureRegion regionCloudsFrontImage;
  private TextureRegion regionCloudsMiddleImage;
  private TextureRegion regionCloudsBehindImage;
  private float deltaMoveBehindCloud;
  private float deltaMoveMiddleCloud;
  private float deltaMoveFrontCloud;

  public Background() {
    deltaMoveMiddleCloud = 0;
    deltaMoveFrontCloud = 0;
    deltaMoveBehindCloud = 0;
    initializeBackground("model/environment/background/background.png");
    initializeCloudFront("model/environment/background/cloudsFront.png");
    initializeCloudMiddle("model/environment/background/cloudsMiddle.png");
    initializeCloudBehind("model/environment/background/cloudsBehind.png");
  }


  public void initializeBackground(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionBackgroundImage = new TextureRegion(image);
  }

  public void initializeCloudFront(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionCloudsFrontImage = new TextureRegion(image);
  }

  public void initializeCloudMiddle(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionCloudsMiddleImage = new TextureRegion(image);
  }

  public void initializeCloudBehind(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionCloudsBehindImage = new TextureRegion(image);
  }


  public void draw(Batch batch, float parentAlpha) {
    batch.begin();
    drawBackground(batch);
    drawBehindCloud(batch, parentAlpha);
    drawMiddleCloud(batch, parentAlpha);
    drawFrontCloud(batch, parentAlpha);
    batch.end();

  }

  private void drawBackground(Batch batch) {
//    batch.draw(regionBackgroundImage, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() , GameConfig.SCALE, GameConfig.SCALE, 0);
    batch.draw(regionBackgroundImage, 0, 0, regionBackgroundImage.getRegionWidth() * GameConfig.SCALE, regionBackgroundImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawFrontCloud(Batch batch, float parentAlpha) {
    if(deltaMoveFrontCloud >= regionCloudsFrontImage.getRegionWidth()*GameConfig.SCALE) {
      deltaMoveFrontCloud=0;
    }
    deltaMoveFrontCloud = deltaMoveFrontCloud + parentAlpha * 60;
    batch.draw(regionCloudsFrontImage, deltaMoveFrontCloud, 0, regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE, regionCloudsFrontImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsFrontImage, deltaMoveFrontCloud-(regionCloudsFrontImage.getRegionWidth()*GameConfig.SCALE), 0, regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE, regionCloudsFrontImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawMiddleCloud(Batch batch, float parentAlpha) {
    if(deltaMoveMiddleCloud >= regionCloudsMiddleImage.getRegionWidth()*GameConfig.SCALE) {
      deltaMoveMiddleCloud=0;
    }
    deltaMoveMiddleCloud = deltaMoveMiddleCloud + parentAlpha * 30;
    batch.draw(regionCloudsMiddleImage, deltaMoveMiddleCloud, 0, regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE, regionCloudsMiddleImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsMiddleImage, deltaMoveMiddleCloud-(regionCloudsMiddleImage.getRegionWidth()*GameConfig.SCALE), 0, regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE, regionCloudsMiddleImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawBehindCloud(Batch batch, float parentAlpha) {
    if(deltaMoveBehindCloud >= regionCloudsBehindImage.getRegionWidth()*GameConfig.SCALE) {
      deltaMoveBehindCloud=0;
    }
    deltaMoveBehindCloud = deltaMoveBehindCloud + parentAlpha * 10;
    deltaMoveBehindCloud = deltaMoveBehindCloud + parentAlpha * 10;
    batch.draw(regionCloudsBehindImage, deltaMoveBehindCloud, 0, regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE, regionCloudsBehindImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsBehindImage, deltaMoveBehindCloud-(regionCloudsBehindImage.getRegionWidth()*GameConfig.SCALE), 0, regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE, regionCloudsBehindImage.getRegionHeight() * GameConfig.SCALE);
  }
}
