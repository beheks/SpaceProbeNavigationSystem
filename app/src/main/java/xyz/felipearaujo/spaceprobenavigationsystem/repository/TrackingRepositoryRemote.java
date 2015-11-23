package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.TrackingServiceRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.DirectionsResponse;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote.SubmissionResponse;

/**
 * Implementation for {@link TrackingRepository} for communicating with the API for retrieving
 * the actions data and submitting the calculated final position.
 */
public class TrackingRepositoryRemote implements TrackingRepository {
  protected static TrackingServiceRemote sTrackingServiceRemote;
  protected static TrackingServiceContract sTrackingServiceContract;

  protected List<TrackingServiceContract.AlienShipAction> shipActions;

  @Inject
  public TrackingRepositoryRemote(TrackingServiceContract contract,
                                  TrackingServiceRemote remote) {
    sTrackingServiceRemote = remote;
    sTrackingServiceContract = contract;
    shipActions = new ArrayList<>();

  }

  @Override
  public Observable<List<TrackingServiceContract.AlienShipAction>> getMovements(String email) {
    return sTrackingServiceRemote.getData(email)
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
    return sTrackingServiceRemote.submitData(email, x, y)
        .map(new Func1<SubmissionResponse, String>() {
          @Override
          public String call(SubmissionResponse submissionResponse) {
            Log.d("TAG", submissionResponse.getMessage());
            return submissionResponse.getMessage();
          }
        });
  }
}