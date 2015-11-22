package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.AlienTrackingServiceRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.AlienDirectionsResponse;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.SubmissionResponse;

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

  @Override
  public Observable<List<AlienTrackingServiceContract.AlienShipAction>> getMovements(String email) {
    return sAlienTrackingServiceRemote.getData(email)
        .map(new Func1<AlienDirectionsResponse, List<AlienTrackingServiceContract.AlienShipAction>>() {

            @Override
            public List<AlienTrackingServiceContract.AlienShipAction>
            call(AlienDirectionsResponse alienDirectionsResponse) {
              List<AlienTrackingServiceContract.AlienShipAction> actions = new ArrayList<>();
              for (String dir : alienDirectionsResponse.getDirections()) {
                actions.add(sAlienTrackingServiceContract.parseAction(dir));
              }
              return actions;
            }
          }
        );
  }
  @Override
  public Observable<String> submitFinalPosition(String email, int x, int y) {
    return sAlienTrackingServiceRemote.submitData(email, x, y)
        .map(new Func1<SubmissionResponse, String>() {
          @Override
          public String call(SubmissionResponse submissionResponse) {return submissionResponse.getMessage();
          }
        });
  }
}