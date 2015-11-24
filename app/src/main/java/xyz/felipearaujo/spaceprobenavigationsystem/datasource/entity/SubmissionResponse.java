package xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity;

/**
 * Response from submitData from API.
 */
public class SubmissionResponse {
  private final int StatusCode;

  private final String Message;

  public SubmissionResponse(int statusCode, String message) {
    StatusCode = statusCode;
    Message = message;
  }

  public String getMessage() {
    return Message;
  }

  public int getStatusCode() {
    return StatusCode;
  }
}
