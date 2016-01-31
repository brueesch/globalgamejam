package com.ggj.model;

public enum Element {
  Fire(0),
  Water(1),
  LightningEarth(2);
  private final int value;

  private Element(int value) {
      this.value = value;
  }
  
  public int getValue() {
      return value;
  }
  
  public int getIndex()
  {
    if(this == Fire) { return 0; }
    if(this == Water) { return 1; }
    if(this == LightningEarth) { return 2; }
    return 0;
  }
}
