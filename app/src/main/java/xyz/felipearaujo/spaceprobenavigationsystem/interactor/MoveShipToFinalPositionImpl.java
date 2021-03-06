package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.util.ShipMovementUtil;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;

public class MoveShipToFinalPositionImpl implements MoveShipToFinalPosition {
  private final TrackingRepository mRepository;
  private final Universe mUniverse;
  private final Ship mShip;

  @Inject
  public MoveShipToFinalPositionImpl(TrackingRepository repository, Universe universe, Ship ship) {
    this.mRepository = repository;
    this.mUniverse = universe;
    this.mShip = ship;
  }

  @Override
  public Observable<DirectedPosition> execute(String email) {
    return mRepository.getMovements(email)
        .map(new Func1<List<TrackingServiceContract.ShipAction>, DirectedPosition>() {
          @Override
          public DirectedPosition call(List<TrackingServiceContract.ShipAction> shipActions) {
            for (TrackingServiceContract.ShipAction action : shipActions) {
              ShipMovementUtil.moveShip(action, mShip, mUniverse);
            }
            return mShip.getPosition();
          }
        })/*
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())*/;
  }
}
