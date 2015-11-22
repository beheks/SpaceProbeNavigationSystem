package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;

/**
 * Service interface for calculating the ship's final position according to the actions provided.
 */
public interface MoveShipToFinalPosition {

  /**
   * Retrieve the ship actions
   * {@link xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract.AlienShipAction}
   * and calculates the final position of the ship {@link Ship}.
   *
   * @param email  the string with the email that will be provided to retrieve the actions.
   * @param ship   the Ship object with the initial direction and coordinates.
   * @return       the final coordinates for the Ship.
   */
  Observable<Point> execute(String email, Ship ship);
}
