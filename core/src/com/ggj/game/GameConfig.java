package com.ggj.game;

import com.badlogic.gdx.math.Vector2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chris on 30.01.16.
 */
public class GameConfig {

  public static int ROCK_LEVELS;
  public static Vector2 ROCK_POSITION;
  public static float SCALE;
  public static int FLOOR_HEIGHT;
  public static Vector2 BACKGROUND_OFFSET;
  public static float GROUND_Y_POSITION;

  public GameConfig() {
    loadProperties();
  }

  private void loadProperties() {
    try {
      loadPropertiesFile();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private void loadPropertiesFile() throws IOException {
    InputStream input = new FileInputStream("../../config.properties");
    Properties prop = new Properties();
    prop.load(input);
    fillVariables(prop);
    input.close();
  }

  private void fillVariables(Properties prop) {
    ROCK_LEVELS = Integer.parseInt(prop.getProperty("levels"));
    ROCK_POSITION = new Vector2(Integer.parseInt(prop.getProperty("positionX")), Integer.parseInt(prop.getProperty("positionY")));
    SCALE = Float.parseFloat(prop.getProperty("scale"));
    FLOOR_HEIGHT = Integer.parseInt(prop.getProperty("floorheight"));
    BACKGROUND_OFFSET = new Vector2(Integer.parseInt(prop.getProperty("background_offset_x")),Integer.parseInt(prop.getProperty("background_offset_y")));
    GROUND_Y_POSITION = Float.parseFloat(prop.getProperty("ground_y_position"));
  }
}

