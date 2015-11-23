package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalActionException;

public class TrackingServiceContractImpl implements TrackingServiceContract {
  @Override
  public ShipAction parseAction(String action) throws IllegalActionException{
    try {
      return ShipAction.valueOf(action);
    } catch (IllegalArgumentException e) {
      throw new IllegalActionException(action);
    }
  }
}
