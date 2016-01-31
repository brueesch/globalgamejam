package com.ggj.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ggj.game.GlobalGameJam;

public class DesktopLauncher {
  
  public static float scale = 2;
  
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = (int)(512f * scale);
    config.height = (int)(192f * scale);
    config.title = "DANGER ZONE!";
    new LwjglApplication(new GlobalGameJam(), config);
  }
}
