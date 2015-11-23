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
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepositoryImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContractImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;


@Module
public class CommonModule {
  private static Application sApplication;

  public CommonModule(Application app) {
    sApplication = app;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return sApplication;
  }

  @Provides
  @Singleton
  TrackingServiceContract provideTrackingServiceContract() {
    return new TrackingServiceContractImpl();
  }

  @Provides
  @Singleton
  TrackingService provideTrackingService() {
    return new Retrofit.Builder()
        .baseUrl(TrackingService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(TrackingService.class);
  }


  @Provides
  @Singleton
  TrackingRepository provideTrackingRepository(TrackingServiceContract contract,
                                               TrackingService service) {
    return new TrackingRepositoryImpl(contract, service);
  }

  @Provides
  @Singleton
  Universe provideUniverse() {
    return new Universe(10, 10);
  }
}
