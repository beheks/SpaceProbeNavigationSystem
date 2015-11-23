package xyz.felipearaujo.spaceprobenavigationsystem.interactor.util;

import android.graphics.Point;

import com.google.common.collect.ImmutableMap;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition.Direction;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.ShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;

public class ShipMovementUtil {

  private static final ImmutableMap<Direction, DirectedPosition> MOVE_FORWARD = ImmutableMap.of(
      Direction.NORTH, new DirectedPosition(0, 1, Direction.NORTH),
      Direction.SOUTH, new DirectedPosition(0, -1, Direction.SOUTH),
      Direction.EAST, new DirectedPosition(1, 0, Direction.EAST),
      Direction.WEST, new DirectedPosition(-1, 0, Direction.WEST));

  private static final ImmutableMap<Direction, Direction> ROTATE_LEFT = ImmutableMap.of(
      Direction.NORTH, Direction.WEST,
      Direction.WEST, Direction.SOUTH,
      Direction.SOUTH, Direction.EAST,
      Direction.EAST, Direction.NORTH);

  private static final ImmutableMap<Direction, Direction> ROTATE_RIGHT = ImmutableMap.of(
      Direction.NORTH, Direction.EAST,
      Direction.EAST, Direction.SOUTH,
      Direction.SOUTH, Direction.WEST,
      Direction.WEST, Direction.NORTH);

  public static DirectedPosition moveShip(TrackingServiceContract.ShipAction shipAction,
                               Ship ship,
                               Universe universe)
      throws ShipOutOfUniverseException {
    switch (shipAction) {
      case FORWARD:
        ship.setPosition(incrementPosition(ship.getPosition(),
            MOVE_FORWARD.get(ship.getCurrentDirection()),
            universe));
        break;

      case LEFT:
        ship.setCurrentDirection(ROTATE_LEFT.get(ship.getCurrentDirection()));
        break;

      case RIGHT:
        ship.setCurrentDirection(ROTATE_RIGHT.get(ship.getCurrentDirection()));
        break;

      default:
        break;
    }
    return ship.getPosition();
  }


  private static DirectedPosition incrementPosition(DirectedPosition start,
                                         DirectedPosition increase,
                                         Universe universe)
      throws ShipOutOfUniverseException {
    DirectedPosition newPosition = new DirectedPosition(start.getX() + increase.getX(),
        start.getY() + increase.getY(),
        start.getDirection());

    if(!universe.assertIfPositionIsValid(newPosition)) throw new ShipOutOfUniverseException();

    return newPosition;
  }
}
