package xyz.felipearaujo.spaceprobenavigationsystem.exception;

public class AlienShipOutOfUniverseException extends RuntimeException {
  public AlienShipOutOfUniverseException() {
    super();
  }

  public AlienShipOutOfUniverseException(final String message) {
    super(message);
  }

  public AlienShipOutOfUniverseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public AlienShipOutOfUniverseException(final Throwable cause) {
    super(cause);
  }
}
