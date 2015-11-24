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
  private Direction mDirection;

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

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof DirectedPosition)) return false;

    DirectedPosition d = (DirectedPosition) o;
    return (this.x == d.x && this.y == d.y && this.mDirection == d.mDirection);
  }
}
