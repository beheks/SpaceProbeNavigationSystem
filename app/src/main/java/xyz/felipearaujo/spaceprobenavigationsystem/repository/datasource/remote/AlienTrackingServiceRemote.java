package xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface AlienTrackingServiceRemote {
  String BASE_URL = "https://mands-alien-test.herokuapp.com";

  @GET("/api/spaceprobe/getdata/{email}.json")
  Observable<AlienDirectionsResponse> getData(@Path("email") String email);

  /*@GET("/api/spaceprobe/submitdata/{email}/{x}/{y}")
  Call<String> submitData(@Path("email") String email, @Path("x") int x, @Path("y") int y);*/
}