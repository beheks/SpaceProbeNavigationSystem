package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.graphics.Point;

import com.google.common.collect.ImmutableMap;

import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

public class AlienShip {
  public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  private Direction mCurrentDirection = Direction.NORTH;
  private Point mPosition;

  public AlienShip(Point initial) {
    this.mPosition = initial;
  }

  private static final ImmutableMap<Direction, Point> FORWARD = ImmutableMap.of(
      Direction.NORTH, new Point(1, 0),
      Direction.SOUTH, new Point(-1, 0),
      Direction.EAST, new Point(0, 1),
      Direction.WEST, new Point(0, -1));

  private static final ImmutableMap<Direction, Direction> LEFT = ImmutableMap.of(
      Direction.NORTH, Direction.WEST,
      Direction.WEST, Direction.SOUTH,
      Direction.SOUTH, Direction.EAST,
      Direction.EAST, Direction.NORTH);

  private static final ImmutableMap<Direction, Direction> RIGHT = ImmutableMap.of(
      Direction.NORTH, Direction.EAST,
      Direction.EAST, Direction.SOUTH,
      Direction.SOUTH, Direction.WEST,
      Direction.WEST, Direction.NORTH);

  public Point moveShip(AlienTrackingServiceContract.AlienShipAction alienShipAction) {
    switch (alienShipAction) {
      case FORWARD:
        updateCoordinates(FORWARD.get(mCurrentDirection));
        break;
      case LEFT:
        updateDirection(LEFT.get(mCurrentDirection));
        break;
      case RIGHT:
        updateDirection(RIGHT.get(mCurrentDirection));
        break;
      default:
        //TODO throw exception
        break;
    }
    return mPosition;
  }

  public Point getShipPosition(){
    return mPosition;
  }

  private void updateCoordinates(Point increase) {
    mPosition.x = mPosition.x + increase.x;
    mPosition.y = mPosition.y + increase.y;
  }

  private void updateDirection(Direction newDirection) {
    mCurrentDirection = newDirection;
  }
}
