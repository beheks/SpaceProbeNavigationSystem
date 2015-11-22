package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.ApplicationComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContract;

public class MoveAlienShipToFinalPositionImpl implements MoveAlienShipToFinalPosition {
  private final AlienTrackingRepository mRepository;
  private final Universe mUniverse;

  @Inject
  public MoveAlienShipToFinalPositionImpl(AlienTrackingRepository repository, Universe universe) {
    this.mRepository = repository;
    this.mUniverse = universe;
  }

  @Override
  public Observable<Point> execute(String email, final AlienShip alienShip) {
    alienShip.resetShip();

    return mRepository.getMovements(email)
        .map(new Func1<List<AlienTrackingServiceContract.AlienShipAction>, Point>() {
          @Override
          public Point call(List<AlienTrackingServiceContract.AlienShipAction> alienShipActions) {
            for (AlienTrackingServiceContract.AlienShipAction action : alienShipActions) {
              ShipMovementUtil.moveShip(action, alienShip, mUniverse);
            }
            Log.d("TAG", alienShip.getPosition().toString());
            return alienShip.getPosition();
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
