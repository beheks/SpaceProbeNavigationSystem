package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract
    .TrackingServiceContract;

/**
 * Service interface for calculating the ship's final position according to the actions provided.
 */
public interface MoveShipToFinalPosition {

  /**
   * Retrieve the ship actions
   * {@link TrackingServiceContract.ShipAction}
   * and calculates the final position of the ship {@link Ship}.
   *
   * @param email  the string with the email that will be provided to retrieve the actions.
   * @return       the final coordinates for the Ship.
   */
  Observable<Point> execute(String email);
}
