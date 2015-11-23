package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalShipActionException;

public interface TrackingServiceContract {
  enum ShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  ShipAction parseAction(String action) throws IllegalShipActionException;
}
