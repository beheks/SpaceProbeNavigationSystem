package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition.Direction;

/**
 * Entity of Ship, a Ship has a direction and a coordinate for it's position in the Universe.
 */
public class Ship {

  private DirectedPosition mPosition;
  private DirectedPosition mInitialPosition;

  public Ship(int x, int y, Direction direction) {
    this.mPosition = new DirectedPosition(x, y, direction);
    this.mInitialPosition = new DirectedPosition(x, y, direction);

  }

  public Direction getCurrentDirection() {
    return mPosition.getDirection();
  }

  public void setCurrentDirection(Direction currentDirection) {
    mPosition.setDirection(currentDirection);
  }

  public DirectedPosition getPosition() {
    return mPosition;
  }

  public void setPosition(DirectedPosition position) {
    mPosition = position;
  }

  /*
   * Resets ship to initial position and direction to start a news search for the alien ship.
   */
  public void resetShip() {
    mPosition = mInitialPosition;
  }

  @Override
  public String toString() {
    return "At x:" + mPosition.getX() +
        " y:" + mPosition.getY() +
        " facing " + mPosition.getDirection().toString().toLowerCase();
  }
}
