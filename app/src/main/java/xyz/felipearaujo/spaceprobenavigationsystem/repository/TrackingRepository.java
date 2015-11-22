package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.List;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;

/**
 * Service interface for getting the data and submitting the result.
 */
public interface TrackingRepository {

  /**
   * Retrieves the list of actions tracked for the ship.
   *
   * @param email the string with the email for access to tracking information.
   * @return      the list of actions tracked for the ship.
   */
  Observable<List<TrackingServiceContract.AlienShipAction>> getMovements(String email);

  /**
   * Submits the calculated final position of the ship.
   *
   * @param email the string with the email for submission.
   * @param x     the int with the final x value coordinate.
   * @param y     the int with the final y value coordinate.
   * @return      the message of success of failure of the submission.
   */
  Observable<String> submitFinalPosition(String email, int x, int y);
}
