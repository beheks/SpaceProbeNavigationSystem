package xyz.felipearaujo.spaceprobenavigationsystem.exception;

public class ShipOutOfUniverseException extends RuntimeException {
  public ShipOutOfUniverseException() {
    this("Alien Ship Out Of Universe Bounds!");
  }

  public ShipOutOfUniverseException(final String message) {
    super(message);
  }

  public ShipOutOfUniverseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ShipOutOfUniverseException(final Throwable cause) {
    super(cause);
  }
}
