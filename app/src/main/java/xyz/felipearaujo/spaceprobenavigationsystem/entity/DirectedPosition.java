package xyz.felipearaujo.spaceprobenavigationsystem.entity;

public class DirectedPosition {

  public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  private int x;
  private int y;
  Direction mDirection;

  public DirectedPosition(int x, int y, Direction direction) {
    this.x = x;
    this.y = y;
    this.mDirection = direction;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Direction getDirection() {
    return mDirection;
  }

  public void setDirection(Direction direction) {
    mDirection = direction;
  }
}
