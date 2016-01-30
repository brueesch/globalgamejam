package com.ggj.game;

public class ObjectController {
  public static synchronized int getNextId()
  {
    return ++objectId;
  }
  
  private static int objectId = 0;
}
