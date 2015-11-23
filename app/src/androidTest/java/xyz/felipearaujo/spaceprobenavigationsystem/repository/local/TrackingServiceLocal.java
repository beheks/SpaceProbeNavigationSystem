package xyz.felipearaujo.spaceprobenavigationsystem.repository.local;

import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.SubmissionResponse;

public class TrackingServiceLocal implements TrackingService {

  @Override
  public Observable<DirectionsResponse> getData(String email) {
    return Observable.create(new Observable.OnSubscribe<DirectionsResponse>() {

      @Override
      public void call(Subscriber<? super DirectionsResponse> subscriber) {
        if(!subscriber.isUnsubscribed()) {

          subscriber.onNext(new DirectionsResponse(Arrays.asList("FORWARD", "FORWARD", "FORWARD",
              "RIGHT", "FORWARD", "FORWARD", "RIGHT", "FORWARD", "LEFT", "FORWARD", "LEFT",
              "FORWARD", "FORWARD", "FORWARD", "LEFT", "FORWARD", "FORWARD", "LEFT", "FORWARD",
              "FORWARD", "LEFT", "FORWARD", "FORWARD", "FORWARD", "FORWARD", "RIGHT", "FORWARD",
              "FORWARD", "LEFT", "FORWARD", "FORWARD", "FORWARD", "RIGHT", "RIGHT", "FORWARD",
              "FORWARD", "LEFT", "FORWARD", "RIGHT", "FORWARD", "FORWARD")));
        }
        if(!subscriber.isUnsubscribed()) {
          subscriber.onCompleted();
        }
      }
    });
  }

  @Override
  public Observable<SubmissionResponse> submitData(String email, final int x, final int y) {
    return Observable.create(new Observable.OnSubscribe<SubmissionResponse>() {

      @Override
      public void call(Subscriber<? super SubmissionResponse> subscriber) {
        if(!subscriber.isUnsubscribed()) {
          if(x == 4 && y == 0) {
            subscriber.onNext(new SubmissionResponse(200, "OK"));
          }
          else {
            subscriber.onNext(new SubmissionResponse(500, "WRONG POSITION ERROR"));
          }
        }
        if(!subscriber.isUnsubscribed()) {
          subscriber.onCompleted();
        }
      }
    });
  }
}
