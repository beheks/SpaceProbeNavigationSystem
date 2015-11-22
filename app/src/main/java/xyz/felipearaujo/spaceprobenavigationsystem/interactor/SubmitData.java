package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;

/**
 * Service interface to Submit results to system.
 */
public interface SubmitData {

  /**
   * Submits the final coordinates of the ship to the system and returns the response message.
   *
   * @param email the string with the email that will be provided for the submission.
   * @param ship  the Ship object with the final direction and coordinates.
   * @return      the string with either a success or failure message.
   */
  Observable<String> execute(String email, Ship ship);
}
