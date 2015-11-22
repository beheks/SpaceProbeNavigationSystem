package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.graphics.Point;

public class Universe {
  private int mWidth;
  private int mHeight;

  public Universe(int width, int height) {
    mWidth = width;
    mHeight = height;
  }

  public boolean assertIfPositionIsValid(Point position) {
    //return true if the position is valid, else return false.
    return (position.x >= 0 && position.y >= 0 && position.x <= mWidth && position.y <= mHeight);
  }
}
