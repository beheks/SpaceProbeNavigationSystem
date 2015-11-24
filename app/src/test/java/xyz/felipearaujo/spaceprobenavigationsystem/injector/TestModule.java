package xyz.felipearaujo.spaceprobenavigationsystem.injector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContractImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepositoryImpl;

@Module
public class TestModule {

  @Provides
  @Singleton
  TrackingService provideTrackingService() {
    return new MockDataSource();
  }

  @Provides
  @Singleton
  DirectionsResponse provideDirectionsResponse() {
    return new DirectionsResponse(new MockDataSource().getDirectionsList());
  }

  @Provides
  @Singleton
  Ship provideShip() {
    return new Ship(0, 0, DirectedPosition.Direction.NORTH);
  }

  @Provides
  @Singleton
  TrackingServiceContract provideTrackingServiceContract() {
    return new TrackingServiceContractImpl();
  }

  @Provides
  @Singleton
  TrackingRepository provideTrackingRepository(TrackingServiceContract contract,
                                               TrackingService service) {
    return new TrackingRepositoryImpl(contract, service);
  }
}
