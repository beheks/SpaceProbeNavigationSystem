package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote;

/**
 * Response from submitData from API.
 */
public class SubmissionResponse {
  private int StatusCode;

  private String Message;

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
