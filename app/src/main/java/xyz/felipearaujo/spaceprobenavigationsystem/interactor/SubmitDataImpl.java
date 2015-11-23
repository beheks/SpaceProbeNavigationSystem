package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

public class  SubmitDataImpl implements SubmitData {
  private final TrackingRepository mRepository;
  private final Ship mShip;

  @Inject
  public SubmitDataImpl(TrackingRepository repository, Ship ship) {
    mRepository = repository;
    mShip = ship;
  }

  @Override
  public Observable<String> execute(String email) {
    return mRepository.submitFinalPosition(email,
        mShip.getPosition().getX(),
        mShip.getPosition().getY())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
