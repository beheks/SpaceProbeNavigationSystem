package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;

import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local.TrackingServiceLocal;

/**
 * Implementation for {@link TrackingRepository} with local movements retrieval and submission for
 * tests.
 */
public class TrackingRepositoryLocal implements TrackingRepository {
  protected static TrackingServiceContract sTrackingServiceContract;
  protected static TrackingServiceLocal sAlienTrackingServiceLocal;

  @Inject
  public TrackingRepositoryLocal(TrackingServiceContract contract, TrackingServiceLocal local) {
    sTrackingServiceContract = contract;
    sAlienTrackingServiceLocal = local;
  }

  @Override
  public synchronized Observable<List<TrackingServiceContract.AlienShipAction>>
  getMovements(String email) {

    return Observable.create(
        new Observable.OnSubscribe<List<TrackingServiceContract.AlienShipAction>>() {

          @Override
          public void call(
              Subscriber<? super List<TrackingServiceContract.AlienShipAction>> subscriber) {
            List<String> movements = sAlienTrackingServiceLocal.getMovements();

            if(movements != null) {
              List<TrackingServiceContract.AlienShipAction> actions = new ArrayList<>();
              for (String movement : movements) {
                actions.add(sTrackingServiceContract.parseAction(movement));
              }
              subscriber.onNext(actions);
            }
            /*else {
              subscriber.onError(new IllegalStateException());
            }*/
          }
        }
    );
  }

  @Override
  public Observable<String> submitFinalPosition(String email, int x, int y) {
    return null;
  }
}
