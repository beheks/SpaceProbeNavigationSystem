package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;

public interface SubmitData {
  String execute(String email, AlienShip alienShip);
}
