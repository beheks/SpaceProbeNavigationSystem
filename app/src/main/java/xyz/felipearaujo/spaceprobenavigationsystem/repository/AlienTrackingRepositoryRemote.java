package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
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
  public List<AlienTrackingServiceContract.AlienShipAction> getMovements(String email) {
    alienShipActions.clear();
    Call<AlienDirectionsResponse> call = sAlienTrackingServiceRemote.getData(email);
    call.enqueue(new Callback<AlienDirectionsResponse>() {
                   @Override
                   public void onResponse(Response<AlienDirectionsResponse> response, Retrofit
                       retrofit) {
                     for (String dir : response.body().getDirections()) {
                       alienShipActions.add(sAlienTrackingServiceContract.parseAction(dir));
                       Log.d("Callback", dir);
                     }
                   }

                   @Override
                   public void onFailure(Throwable t) {
                     Log.d("Callback", t.getMessage());
                   }
                 }

    );
    return alienShipActions;
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