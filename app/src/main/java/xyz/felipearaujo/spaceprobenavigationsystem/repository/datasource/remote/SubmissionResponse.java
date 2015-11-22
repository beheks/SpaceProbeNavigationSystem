package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote;

/**
 * Created by Felipe on 22/11/2015.
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
