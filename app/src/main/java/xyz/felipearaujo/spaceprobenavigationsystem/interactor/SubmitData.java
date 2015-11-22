package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;

public interface SubmitData {
  Observable<String> execute(String email, AlienShip alienShip);
}
