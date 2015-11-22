package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource;

public interface AlienTrackingServiceContract {
  enum AlienShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  AlienShipAction parseAction(Object action);
}
