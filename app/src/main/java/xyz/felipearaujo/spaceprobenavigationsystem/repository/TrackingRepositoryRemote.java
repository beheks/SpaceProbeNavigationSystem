package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.AlienTrackingServiceRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.DirectionsResponse;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.SubmissionResponse;

public class TrackingRepositoryRemote implements TrackingRepository {
  protected static AlienTrackingServiceRemote sAlienTrackingServiceRemote;
  protected static TrackingServiceContract sTrackingServiceContract;

  protected List<TrackingServiceContract.AlienShipAction> alienShipActions;

  @Inject
  public TrackingRepositoryRemote(TrackingServiceContract contract,
                                  AlienTrackingServiceRemote remote) {
    sAlienTrackingServiceRemote = remote;
    sTrackingServiceContract = contract;
    alienShipActions = new ArrayList<>();

  }

  @Override
  public Observable<List<TrackingServiceContract.AlienShipAction>> getMovements(String email) {
    return sAlienTrackingServiceRemote.getData(email)
        .map(new Func1<DirectionsResponse, List<TrackingServiceContract.AlienShipAction>>() {

            @Override
            public List<TrackingServiceContract.AlienShipAction>
            call(DirectionsResponse directionsResponse) {
              List<TrackingServiceContract.AlienShipAction> actions = new ArrayList<>();
              for (String dir : directionsResponse.getDirections()) {
                actions.add(sTrackingServiceContract.parseAction(dir));
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
          public String call(SubmissionResponse submissionResponse) {
            Log.d("TAG", submissionResponse.getMessage());
            return submissionResponse.getMessage();
          }
        });
  }
}