package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource;

import android.util.Log;

public class AlienTrackingServiceContractParser implements AlienTrackingServiceContract{
  @Override
  public AlienShipAction parseAction(Object action) {
    try {
      return AlienShipAction.valueOf(action.toString());
    } catch (IllegalArgumentException e) {
      Log.e("AlienTrackingContract", "Illegal Alien Ship Action: " + action.toString());
      //TODO throw again?
      throw e;
    }
  }
}
