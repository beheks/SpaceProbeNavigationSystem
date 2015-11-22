package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;

public interface MoveShipToFinalPosition {
  Observable<Point> execute(String email, Ship ship);
}
