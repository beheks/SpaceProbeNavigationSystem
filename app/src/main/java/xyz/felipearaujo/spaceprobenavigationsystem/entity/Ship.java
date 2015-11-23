package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.graphics.Point;

/**
 * Entity of Ship, a Ship has a direction and a coordinate for it's position in the Universe.
 */
public class Ship {
  public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  private Direction mCurrentDirection = Direction.NORTH;
  private Point mPosition;

  public Ship(Point initial) {
    this.mPosition = initial;
  }

  public Direction getCurrentDirection() {
    return mCurrentDirection;
  }

  public void setCurrentDirection(Direction currentDirection) {
    mCurrentDirection = currentDirection;
  }

  public Point getPosition() {
    return mPosition;
  }

  public void setPosition(Point position) {
    mPosition = position;
  }

  /*
   * Resets ship to initial position and direction to start a news search for the alien ship.
   */
  public void resetShip() {
    mPosition.x = 0;
    mPosition.y = 0;
    mCurrentDirection = Direction.NORTH;
  }
}
