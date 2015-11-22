package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource;

public interface TrackingServiceContract {
  enum AlienShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  AlienShipAction parseAction(Object action);
}
