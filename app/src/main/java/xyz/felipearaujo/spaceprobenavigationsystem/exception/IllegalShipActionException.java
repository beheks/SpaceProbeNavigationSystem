package xyz.felipearaujo.spaceprobenavigationsystem.exception;

public class IllegalShipActionException extends IllegalArgumentException {
  public IllegalShipActionException() {
    super("Data Source provided an illegal action");
  }

  public IllegalShipActionException(final String message) {
    super("Data Source provided an illegal action: " + message);
  }

  public IllegalShipActionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public IllegalShipActionException(final Throwable cause) {
    super(cause);
  }
}
