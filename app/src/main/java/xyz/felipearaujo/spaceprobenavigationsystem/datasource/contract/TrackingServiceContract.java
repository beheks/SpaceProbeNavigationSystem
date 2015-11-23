package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

public interface TrackingServiceContract {
  enum ShipAction {
    FORWARD,
    RIGHT,
    LEFT
  }

  ShipAction parseAction(Object action);
}
