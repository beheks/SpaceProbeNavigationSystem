package xyz.felipearaujo.spaceprobenavigationsystem.injector.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import retrofit.RxJavaCallAdapterFactory;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepositoryLocal;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepositoryRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContractParser;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local
    .AlienTrackingServiceLocal;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote
    .AlienTrackingServiceRemote;

@Module
public class ApplicationModule {
  private static Application sApplication;

  public ApplicationModule(Application app) {
    sApplication = app;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return sApplication;
  }

  @Provides
  @Singleton
  AlienTrackingServiceContract provideAlienTrackingServiceContract() {
    return new AlienTrackingServiceContractParser();
  }

  @Provides
  @Singleton
  AlienTrackingServiceLocal provideAlienTrackingServiceLocal() {
    return new AlienTrackingServiceLocal();
  }

  @Provides
  @Singleton
  AlienTrackingServiceRemote provideAlienTrackingServiceRemote() {
    return new Retrofit.Builder()
        .baseUrl(AlienTrackingServiceRemote.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(AlienTrackingServiceRemote.class);
  }

  @Provides
  @Singleton
  AlienTrackingRepository provideAlienTrackingRepository(AlienTrackingServiceContract contract,
                                                         AlienTrackingServiceRemote remote) {
    return new AlienTrackingRepositoryRemote(contract, remote);
  }

  @Provides
  @Singleton
  Universe provideUniverse() {
    return new Universe(10, 10);
  }
}
