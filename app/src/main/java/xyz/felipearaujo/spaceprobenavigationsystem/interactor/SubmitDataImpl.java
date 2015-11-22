package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

public class SubmitDataImpl implements SubmitData {

  TrackingRepository mRepository;

  @Inject
  public SubmitDataImpl(TrackingRepository repository) {
    mRepository = repository;
  }

  @Override
  public Observable<String> execute(String email, Ship ship) {
    return mRepository.submitFinalPosition(email,
        ship.getPosition().x,
        ship.getPosition().y)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }
}
