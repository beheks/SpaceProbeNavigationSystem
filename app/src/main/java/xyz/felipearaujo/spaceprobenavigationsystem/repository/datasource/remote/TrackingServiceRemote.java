package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface TrackingServiceRemote {
  String BASE_URL = "https://mands-alien-test.herokuapp.com";

  @GET("/api/spaceprobe/getdata/{email}.json")
  Observable<DirectionsResponse> getData(@Path("email") String email);

  @GET("/api/spaceprobe/submitdata/{email}/{x}/{y}")
  Observable<SubmissionResponse>
  submitData(@Path("email") String email, @Path("x") int x, @Path("y") int y);
}
