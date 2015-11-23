package xyz.felipearaujo.spaceprobenavigationsystem.datasource;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.SubmissionResponse;

public interface TrackingService {
  String BASE_URL = "https://mands-alien-test.herokuapp.com";


  @GET("/api/spaceprobe/getdata/{email}.json")
  Observable<DirectionsResponse> getData(@Path("email") String email);


  @GET("/api/spaceprobe/submitdata/{email}/{x}/{y}")
  Observable<SubmissionResponse>
  submitData(@Path("email") String email, @Path("x") int x, @Path("y") int y);
}
