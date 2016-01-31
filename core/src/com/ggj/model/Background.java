package com.ggj.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ggj.game.GameConfig;

public class Background {

  private TextureRegion regionBackgroundImage;
  private TextureRegion regionCloudsFrontImage;
  private TextureRegion regionCloudsMiddleImage;
  private TextureRegion regionCloudsBehindImage;
  private TextureRegion regionLight1Image;
  private TextureRegion regionLight2Image;
  private TextureRegion regionLightningDarkImage;
  private TextureRegion regionLightningLightImage;
  private float deltaMoveBehindCloud;
  private float deltaMoveMiddleCloud;
  private float deltaMoveFrontCloud;
  private float time;
  private float time2;
  private float time3 = 2;
  private int awesomeNumber;
  private int count;
  private int count2;
  private boolean light1 = true;
  private boolean lightning = false;
  private float scale = 1f;
  private boolean growing = false;

  public Background() {
    deltaMoveMiddleCloud = 0;
    deltaMoveFrontCloud = 0;
    deltaMoveBehindCloud = 0;
    initializeBackground("model/environment/background/background.png");
    initializeCloudFront("model/environment/background/cloudsFront.png");
    initializeCloudMiddle("model/environment/background/cloudsMiddle.png");
    initializeCloudBehind("model/environment/background/cloudsBehind.png");
    initializeLight1("model/environment/background/light1.png");
    initializeLight2("model/environment/background/light2.png");
    initializeLightningDark("model/environment/background/lightningDark.png");
    initializeLightningLight("model/environment/background/lightningLight.png");
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


  public void initializeLight1(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionLight1Image = new TextureRegion(image);
  }

  public void initializeLight2(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionLight2Image = new TextureRegion(image);
  }

  public void initializeLightningDark(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionLightningDarkImage = new TextureRegion(image);
  }

  public void initializeLightningLight(String texturePath) {
    Texture image = new Texture(Gdx.files.internal(texturePath));
    regionLightningLightImage = new TextureRegion(image);
  }


  public void draw(Batch batch, float parentAlpha) {
    batch.begin();
    drawBackground(batch, parentAlpha);
    drawBehindCloud(batch, parentAlpha);
    drawMiddleCloud(batch, parentAlpha);
    drawFrontCloud(batch, parentAlpha);
    batch.end();

  }

  private void drawBackground(Batch batch, float parentAlpha) {
    time += parentAlpha;
    time3 -= parentAlpha;

    batch.draw(regionBackgroundImage, 0, 0, regionBackgroundImage.getRegionWidth() * GameConfig.SCALE, regionBackgroundImage.getRegionHeight() * GameConfig.SCALE);

    if (time3 <= 0.1f) {
      count++;
      count2++;

      if (count <= Math.random() * 50) {
        batch.draw(regionLightningDarkImage, 0, 0, regionLightningDarkImage.getRegionWidth() * GameConfig.SCALE, regionLightningDarkImage.getRegionHeight() * GameConfig.SCALE);
      } else {
        batch.draw(regionLightningLightImage, 0, 0, regionLightningLightImage.getRegionWidth() * GameConfig.SCALE, regionLightningLightImage.getRegionHeight() * GameConfig.SCALE);
      }
      if (count2 >= awesomeNumber) {
        count2 = 0;
        count = 0;
        time3 = 10;
        int number = (int) Math.random() * 400;
        if (number <= 30) {
          awesomeNumber = 30;
        } else {
          awesomeNumber = number;
        }
      }
    }


    if (time >= 0.1f && time2 <= 0.1f) {
      if (scale <= 0.1f) {
        growing = true;
        light1 = !light1;
      } else if (scale >= 1.0f) {
        growing = false;

      }

      if (growing) {
        scale += 0.1f;
      } else {
        scale -= 0.1f;
      }
      time = 0;
    }

    if (scale >= 0.9f) {
      time2 = (float) Math.random() * 1;
    }

    time2 -= parentAlpha;


    Color color = batch.getColor();
    float oldAlpha = color.a;
    color.a = oldAlpha * scale;
    batch.setColor(color);

    if (light1) {
      batch.draw(regionLight1Image, 0, 0, regionLight1Image.getRegionWidth() * GameConfig.SCALE, regionLight1Image.getRegionHeight() * GameConfig.SCALE);
    } else {
      batch.draw(regionLight2Image, 0, 0, regionLight2Image.getRegionWidth() * GameConfig.SCALE, regionLight2Image.getRegionHeight() * GameConfig.SCALE);
    }

    color.a = oldAlpha;
    batch.setColor(color);

  }

  private void drawFrontCloud(Batch batch, float parentAlpha) {
    if (deltaMoveFrontCloud >= regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE) {
      deltaMoveFrontCloud = 0;
    }
    deltaMoveFrontCloud = deltaMoveFrontCloud + parentAlpha * 60;
    batch.draw(regionCloudsFrontImage, deltaMoveFrontCloud, 0, regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE, regionCloudsFrontImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsFrontImage, deltaMoveFrontCloud - (regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE), 0, regionCloudsFrontImage.getRegionWidth() * GameConfig.SCALE, regionCloudsFrontImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawMiddleCloud(Batch batch, float parentAlpha) {
    if (deltaMoveMiddleCloud >= regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE) {
      deltaMoveMiddleCloud = 0;
    }
    deltaMoveMiddleCloud = deltaMoveMiddleCloud + parentAlpha * 30;
    batch.draw(regionCloudsMiddleImage, deltaMoveMiddleCloud, 0, regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE, regionCloudsMiddleImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsMiddleImage, deltaMoveMiddleCloud - (regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE), 0, regionCloudsMiddleImage.getRegionWidth() * GameConfig.SCALE, regionCloudsMiddleImage.getRegionHeight() * GameConfig.SCALE);
  }

  private void drawBehindCloud(Batch batch, float parentAlpha) {
    if (deltaMoveBehindCloud >= regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE) {
      deltaMoveBehindCloud = 0;
    }
    deltaMoveBehindCloud = deltaMoveBehindCloud + parentAlpha * 10;
    deltaMoveBehindCloud = deltaMoveBehindCloud + parentAlpha * 10;
    batch.draw(regionCloudsBehindImage, deltaMoveBehindCloud, 0, regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE, regionCloudsBehindImage.getRegionHeight() * GameConfig.SCALE);
    batch.draw(regionCloudsBehindImage, deltaMoveBehindCloud - (regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE), 0, regionCloudsBehindImage.getRegionWidth() * GameConfig.SCALE, regionCloudsBehindImage.getRegionHeight() * GameConfig.SCALE);
  }
}
