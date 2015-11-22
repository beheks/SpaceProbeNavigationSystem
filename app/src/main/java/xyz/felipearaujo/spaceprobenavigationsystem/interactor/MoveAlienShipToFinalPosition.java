package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;

public interface MoveAlienShipToFinalPosition {
  Point execute(String email, AlienShip alienShip);
}
