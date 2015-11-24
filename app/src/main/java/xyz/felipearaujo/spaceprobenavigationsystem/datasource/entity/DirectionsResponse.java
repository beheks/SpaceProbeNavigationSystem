package xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity;

import java.util.List;

/**
 * Response from getData from API.
 */
public class DirectionsResponse {
  private final List<String> Directions;

  public DirectionsResponse(List<String> directions) {
    Directions = directions;
  }

  public List<String> getDirections() {
    return Directions;
  }
}
