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

  private Direction mCurrentDirection;
  private Point mPosition;

  private Direction mInitialDirection;
  private Point mInitialPosition;

  public Ship(Point initial, Direction direction) {
    this.mPosition = initial;
    this.mCurrentDirection = direction;

    this.mInitialPosition = initial;
    this.mInitialDirection = direction;

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
    mPosition = mInitialPosition;
    mCurrentDirection = mInitialDirection;
  }
}
