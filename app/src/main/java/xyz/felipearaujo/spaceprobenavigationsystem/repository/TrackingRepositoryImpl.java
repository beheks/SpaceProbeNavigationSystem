package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.SubmissionResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.WrongAnswerException;

/**
 * Implementation for {@link TrackingRepository} for communicating with the API for retrieving
 * the actions data and submitting the calculated final position.
 */
public class TrackingRepositoryImpl implements TrackingRepository {
  protected static TrackingService sTrackingService;
  protected static TrackingServiceContract sTrackingServiceContract;

  private int retryCount = 0;

  @Inject
  public TrackingRepositoryImpl(TrackingServiceContract contract, TrackingService service) {
    sTrackingService = service;
    sTrackingServiceContract = contract;

  }

  @Override
  public Observable<List<TrackingServiceContract.ShipAction>> getMovements(String email) {
    return sTrackingService.getData(email)
        .map(new Func1<DirectionsResponse, List<TrackingServiceContract.ShipAction>>() {

            @Override
            public List<TrackingServiceContract.ShipAction>
            call(DirectionsResponse directionsResponse) {
              List<TrackingServiceContract.ShipAction> actions = new ArrayList<>();
              for (String dir : directionsResponse.getDirections()) {
                actions.add(sTrackingServiceContract.parseAction(dir));
              }
              return actions;
            }
          })
        .retry(3);
  }
  @Override
  public Observable<String> submitFinalPosition(String email, int x, int y) {
    return sTrackingService.submitData(email, x, y)
        .map(new Func1<SubmissionResponse, String>() {
          @Override
          public String call(SubmissionResponse submissionResponse) {
            if(submissionResponse.getStatusCode() != TrackingServiceContract.RIGHT_ANSWER_CODE) {
              throw new WrongAnswerException(submissionResponse.getMessage());
            }
            return submissionResponse.getMessage();
          }
        });
  }
}