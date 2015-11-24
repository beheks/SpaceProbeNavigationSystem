package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalShipActionException;

public interface TrackingServiceContract {
  enum ShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  int RIGHT_ANSWER_CODE = 200;
  int WRONG_ANSWER_CODE = 500;

  ShipAction parseAction(String action) throws IllegalShipActionException;
}
