package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;
import android.util.Log;

import com.google.common.collect.ImmutableMap;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip.Direction;

public class ShipMovementUtil {

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

  public static Point moveShip(AlienTrackingServiceContract.AlienShipAction alienShipAction,
                               AlienShip alienShip,
                               Universe universe)
      throws AlienShipOutOfUniverseException {
    switch (alienShipAction) {
      case FORWARD:
        alienShip.setPosition(incrementPosition(alienShip.getPosition(),
            FORWARD.get(alienShip.getCurrentDirection()),
            universe));
        break;
      case LEFT:
        alienShip.setCurrentDirection(LEFT.get(alienShip.getCurrentDirection()));
        break;
      case RIGHT:
        alienShip.setCurrentDirection(RIGHT.get(alienShip.getCurrentDirection()));
        break;
      default:
        //TODO throw exception
        break;
    }
    return alienShip.getPosition();
  }


  private static Point incrementPosition(Point start, Point increase, Universe universe)
      throws AlienShipOutOfUniverseException {
    Point newPosition = new Point(start.x + increase.x, start.y + increase.y);

    if(!universe.assertIfPositionIsValid(newPosition)) throw new AlienShipOutOfUniverseException();

    return newPosition;
  }
}
