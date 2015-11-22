package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote;

import java.util.List;

/**
 * Response from getData from API.
 */
public class DirectionsResponse {
  private List<String> Directions;

  public List<String> getDirections() {
    return Directions;
  }
}
