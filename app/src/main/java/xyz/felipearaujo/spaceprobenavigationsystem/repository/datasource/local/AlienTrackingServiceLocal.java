package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local;

import java.util.Arrays;
import java.util.List;

public class AlienTrackingServiceLocal {

  public List<String> getMovements() {
    return Arrays.asList("FORWARD", "FORWARD", "FORWARD", "RIGHT", "FORWARD",
        "FORWARD", "RIGHT", "FORWARD", "LEFT", "FORWARD", "LEFT", "FORWARD", "FORWARD",
        "FORWARD", "LEFT", "FORWARD", "FORWARD", "LEFT", "FORWARD", "FORWARD", "LEFT", "FORWARD",
        "FORWARD", "FORWARD", "FORWARD", "RIGHT", "FORWARD", "FORWARD", "LEFT", "FORWARD",
        "FORWARD", "FORWARD", "RIGHT", "RIGHT", "FORWARD", "FORWARD", "LEFT", "FORWARD", "RIGHT",
        "FORWARD", "FORWARD");
  }

  public String submitFinalPosition(int x, int y) {
    if(x == 0 && y == 4) {
      return "OK";
    }
    return "ERROR";
  }
}
