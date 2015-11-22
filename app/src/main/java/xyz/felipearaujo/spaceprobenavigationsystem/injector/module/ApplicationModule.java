package xyz.felipearaujo.spaceprobenavigationsystem.injector.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import retrofit.RxJavaCallAdapterFactory;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepositoryRemote;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContractParser;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local.TrackingServiceLocal;
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
  TrackingServiceContract provideAlienTrackingServiceContract() {
    return new TrackingServiceContractParser();
  }

  @Provides
  @Singleton
  TrackingServiceLocal provideAlienTrackingServiceLocal() {
    return new TrackingServiceLocal();
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
  TrackingRepository provideAlienTrackingRepository(TrackingServiceContract contract,
                                                         AlienTrackingServiceRemote remote) {
    return new TrackingRepositoryRemote(contract, remote);
  }

  @Provides
  @Singleton
  Universe provideUniverse() {
    return new Universe(10, 10);
  }
}
