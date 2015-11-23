package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalShipActionException;

public class TrackingServiceContractImpl implements TrackingServiceContract {
  @Override
  public ShipAction parseAction(String action) throws IllegalShipActionException {
    try {
      return ShipAction.valueOf(action.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalShipActionException(action);
    }
  }
}
