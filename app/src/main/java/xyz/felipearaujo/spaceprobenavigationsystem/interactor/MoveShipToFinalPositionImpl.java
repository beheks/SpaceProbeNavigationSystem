package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.util.ShipMovementUtil;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;

public class MoveShipToFinalPositionImpl implements MoveShipToFinalPosition {
  private final TrackingRepository mRepository;
  private final Universe mUniverse;

  @Inject
  public MoveShipToFinalPositionImpl(TrackingRepository repository, Universe universe) {
    this.mRepository = repository;
    this.mUniverse = universe;
  }

  @Override
  public Observable<Point> execute(String email, final Ship ship) {
    ship.resetShip();

    return mRepository.getMovements(email)
        .map(new Func1<List<TrackingServiceContract.AlienShipAction>, Point>() {
          @Override
          public Point call(List<TrackingServiceContract.AlienShipAction> alienShipActions) {
            for (TrackingServiceContract.AlienShipAction action : alienShipActions) {
              ShipMovementUtil.moveShip(action, ship, mUniverse);
            }
            Log.d("TAG", ship.getPosition().toString());
            return ship.getPosition();
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
