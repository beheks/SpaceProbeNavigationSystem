package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import rx.Observable;

/**
 * Service interface to Submit results to system.
 */
public interface SubmitData {

  /**
   * Submits the final coordinates of the ship to the system and returns the response message.
   *
   * @param email the string with the email that will be provided for the submission.
   * @return      the string with either a success or failure message.
   */
  Observable<String> execute(String email);
}
