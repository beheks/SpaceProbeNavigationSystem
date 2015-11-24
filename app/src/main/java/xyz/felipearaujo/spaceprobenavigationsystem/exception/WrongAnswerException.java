package xyz.felipearaujo.spaceprobenavigationsystem.exception;

public class WrongAnswerException extends RuntimeException {
  public WrongAnswerException() {
    this("That's not the right answer!");
  }

  public WrongAnswerException(final String message) {
    super(message);
  }

  public WrongAnswerException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public WrongAnswerException(final Throwable cause) {
    super(cause);
  }
}
