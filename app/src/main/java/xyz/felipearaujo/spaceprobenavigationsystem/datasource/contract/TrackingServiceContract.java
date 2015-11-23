package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalActionException;

public interface TrackingServiceContract {
  enum ShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  ShipAction parseAction(String action) throws IllegalActionException;
}
