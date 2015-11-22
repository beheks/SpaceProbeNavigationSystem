package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    return mRepository.getMovements(email).flatMap(
        new Func1<List<AlienTrackingServiceContract.AlienShipAction>, Observable<Point>>() {

      @Override
      public Observable<Point> call(List<AlienTrackingServiceContract.AlienShipAction>
                                    alienShipActions) {
        Log.d("MoveToFinal", alienShipActions.toString());
        for(AlienTrackingServiceContract.AlienShipAction action : alienShipActions) {
          alienShip.moveShip(action);
        }

        return Observable.just(alienShip.getShipPosition()).observeOn(AndroidSchedulers.mainThread());
      }
    });
  }
}
