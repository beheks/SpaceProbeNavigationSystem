package xyz.felipearaujo.spaceprobenavigationsystem.exception;

public class IllegalActionException extends IllegalArgumentException {
  public IllegalActionException() {
    super("Data Source provided an illegal action");
  }

  public IllegalActionException(final String message) {
    super("Data Source provided an illegal action: " + message);
  }

  public IllegalActionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public IllegalActionException(final Throwable cause) {
    super(cause);
  }
}
