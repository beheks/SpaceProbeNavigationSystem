package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

public class GetDataImpl implements GetData {

  AlienTrackingRepository mRepository;

  @Inject
  public GetDataImpl(AlienTrackingRepository repository) {
    mRepository = repository;
  }

  //TODO FIX
  @Override
  public Observable<List<AlienTrackingServiceContract.AlienShipAction>> execute(String email) {
    return null;
  }
}
