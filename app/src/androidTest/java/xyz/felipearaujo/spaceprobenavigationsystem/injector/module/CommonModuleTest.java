package xyz.felipearaujo.spaceprobenavigationsystem.injector.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepositoryImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContractImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.local.TrackingServiceLocal;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;

//TODO Create Mocks
@Module
public class CommonModuleTest {

  @Provides
  @Singleton
  TrackingServiceContract provideAlienTrackingServiceContract() {
    return new TrackingServiceContractImpl();
  }

  @Provides
  @Singleton
  TrackingServiceLocal provideAlienTrackingServiceLocal() {
    return new TrackingServiceLocal();
  }

  @Provides
  @Singleton
  TrackingService provideAlienTrackingServiceRemote() {
    return new Retrofit.Builder()
        .baseUrl(TrackingService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(TrackingService.class);
  }

  @Provides
  @Singleton
  TrackingRepositoryImpl provideAlienTrackingRepositoryRemote(TrackingServiceContract contract,
                                                    TrackingService remote) {
    return new TrackingRepositoryImpl(contract, remote);
  }

  /*@Provides
  @Singleton
  TrackingRepository provide AlienTracking*/

  @Provides
  @Singleton
  Universe provideUniverse() {
    return new Universe(10, 10);
  }
}
