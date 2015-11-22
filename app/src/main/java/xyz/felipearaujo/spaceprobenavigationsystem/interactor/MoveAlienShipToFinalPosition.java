package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;

public interface MoveAlienShipToFinalPosition {
  Observable<Point> execute(String email, AlienShip alienShip);
}
