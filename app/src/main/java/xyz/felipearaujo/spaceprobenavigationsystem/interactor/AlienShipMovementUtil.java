package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import com.google.common.collect.ImmutableMap;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.ShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship.Direction;

public class AlienShipMovementUtil {

  private static final ImmutableMap<Direction, Point> FORWARD = ImmutableMap.of(
      Direction.NORTH, new Point(0, 1),
      Direction.SOUTH, new Point(0, -1),
      Direction.EAST, new Point(1, 0),
      Direction.WEST, new Point(-1, 0));

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

  public static Point moveShip(TrackingServiceContract.AlienShipAction alienShipAction,
                               Ship ship,
                               Universe universe)
      throws ShipOutOfUniverseException {
    switch (alienShipAction) {
      case FORWARD:
        ship.setPosition(incrementPosition(ship.getPosition(),
            FORWARD.get(ship.getCurrentDirection()),
            universe));
        break;
      case LEFT:
        ship.setCurrentDirection(LEFT.get(ship.getCurrentDirection()));
        break;
      case RIGHT:
        ship.setCurrentDirection(RIGHT.get(ship.getCurrentDirection()));
        break;
      default:
        //TODO throw exception
        break;
    }
    return ship.getPosition();
  }


  private static Point incrementPosition(Point start, Point increase, Universe universe)
      throws ShipOutOfUniverseException {
    Point newPosition = new Point(start.x + increase.x, start.y + increase.y);

    if(!universe.assertIfPositionIsValid(newPosition)) throw new ShipOutOfUniverseException();

    return newPosition;
  }
}
