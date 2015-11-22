package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.graphics.Point;

/**
 * Entity of Universe, Universe has the boundaries of where a Ship can travel to,
 * with a width and height of the current universe.
 */
public class Universe {
  private int mWidth;
  private int mHeight;

  public Universe(int width, int height) {
    mWidth = width;
    mHeight = height;
  }

  /*
   * Returns true if the point is a is valid position in this Universe, else return false.
   */
  public boolean assertIfPositionIsValid(Point position) {
    return (position.x >= 0 && position.y >= 0 && position.x <= mWidth && position.y <= mHeight);
  }
}
