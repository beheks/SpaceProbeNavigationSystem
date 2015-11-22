package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;

public class SubmitDataImpl implements SubmitData {

  AlienTrackingRepository mRepository;

  @Inject
  public SubmitDataImpl(AlienTrackingRepository repository) {
    mRepository = repository;
  }

  @Override
  public Observable<String> execute(String email, AlienShip alienShip) {
    return mRepository.submitFinalPosition(email,
        alienShip.getPosition().x,
        alienShip.getPosition().y)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }
}
