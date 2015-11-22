package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContractParser;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local.AlienTrackingServiceLocal;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote
    .AlienTrackingServiceRemote;


public class AlienTrackingRepositoryLocal implements AlienTrackingRepository {
  protected static AlienTrackingServiceContract sAlienTrackingServiceContract;
  protected static AlienTrackingServiceLocal sAlienTrackingServiceLocal;

  @Inject
  public AlienTrackingRepositoryLocal(AlienTrackingServiceContract contract, AlienTrackingServiceLocal local) {
    sAlienTrackingServiceContract = contract;
    sAlienTrackingServiceLocal = local;
  }

  @Override
  public synchronized Observable<List<AlienTrackingServiceContract.AlienShipAction>>
  getMovements(String email) {

    return Observable.create(
        new Observable.OnSubscribe<List<AlienTrackingServiceContract.AlienShipAction>>() {

          @Override
          public void call(
              Subscriber<? super List<AlienTrackingServiceContract.AlienShipAction>> subscriber) {
            List<String> movements = sAlienTrackingServiceLocal.getMovements();

            if(movements != null) {
              List<AlienTrackingServiceContract.AlienShipAction> actions = new ArrayList<>();
              for (String movement : movements) {
                actions.add(sAlienTrackingServiceContract.parseAction(movement));
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
