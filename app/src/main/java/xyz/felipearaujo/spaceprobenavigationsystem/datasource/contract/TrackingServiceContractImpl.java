package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import android.util.Log;

public class TrackingServiceContractImpl implements TrackingServiceContract {
  @Override
  public ShipAction parseAction(Object action) {
    try {
      return ShipAction.valueOf(action.toString());
    } catch (IllegalArgumentException e) {
      Log.e("AlienTrackingContract", "Illegal Alien Ship Action: " + action.toString());
      throw e;
    }
  }
}
