package xyz.felipearaujo.spaceprobenavigationsystem.entity;

/**
 * Entity of Universe, Universe has the boundaries of where a Ship can travel to,
 * with a width and height of the current universe.
 */
public class Universe {
  private final int mWidth;
  private final int mHeight;

  public Universe(int width, int height) {
    mWidth = width;
    mHeight = height;
  }

  /*
   * Returns true if the point is a is valid position in this Universe, else return false.
   */
  public boolean assertIfPositionIsValid(DirectedPosition position) {
    return (position.getX() >= 0 &&
        position.getY() >= 0 &&
        position.getX() <= mWidth &&
        position.getY() <= mHeight);
  }
}
