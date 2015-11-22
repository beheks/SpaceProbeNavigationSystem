package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.AlienTrackingServiceRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.AlienDirectionsResponse;

public class AlienTrackingRepositoryRemote implements AlienTrackingRepository {
  protected static AlienTrackingServiceRemote sAlienTrackingServiceRemote;
  protected static AlienTrackingServiceContract sAlienTrackingServiceContract;

  protected List<AlienTrackingServiceContract.AlienShipAction> alienShipActions;

  @Inject
  public AlienTrackingRepositoryRemote(AlienTrackingServiceContract contract,
                                       AlienTrackingServiceRemote remote) {
    sAlienTrackingServiceRemote = remote;
    sAlienTrackingServiceContract = contract;
    alienShipActions = new ArrayList<>();

  }

  //TODO Error handling
  //TODO fix main thread not waiting for callback to finish EventBus?
  @Override
  public Observable<List<AlienTrackingServiceContract.AlienShipAction>> getMovements(String email) {
    return sAlienTrackingServiceRemote.getData(email)
        .subscribeOn(Schedulers.io())
        .flatMap(
        new Func1<AlienDirectionsResponse, Observable<List<AlienTrackingServiceContract
            .AlienShipAction>>>() {
          @Override
          public Observable<List<AlienTrackingServiceContract.AlienShipAction>> call
              (AlienDirectionsResponse alienDirectionsResponse) {
            List<AlienTrackingServiceContract.AlienShipAction> actions = new ArrayList<>();
            for (String dir : alienDirectionsResponse.getDirections()) {
              actions.add(sAlienTrackingServiceContract.parseAction(dir));
            }
            return Observable.just(actions);
          }
        }
    );
  }

  @Override
  public String submitFinalPosition(String email, int x, int y) {
    /*Call<String> call = sAlienTrackingServiceRemote.submitData(email, x, y);*/

    String message = "";

    /*try {
      message = call.execute().body();
    }
    catch (IOException io) {
      Log.e(AlienTrackingRepositoryRemote.class.getSimpleName(), "error querying api");
    }*/

    return message;
  }
}