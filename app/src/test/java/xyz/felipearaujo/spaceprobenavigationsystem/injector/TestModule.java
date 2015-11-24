package xyz.felipearaujo.spaceprobenavigationsystem.injector;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContractImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPositionImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitDataImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepositoryImpl;

@Module
public class TestModule {

  @Provides
  @Singleton
  TrackingService provideTrackingService() {
    return Mockito.mock(TrackingService.class);
  }

  @Provides
  @Singleton
  DirectionsResponse provideDirectionsResponse() {
    return new DirectionsResponse(MockDataSource.getDirectionsList());
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

  @Provides
  @Singleton
  Universe provideUniverse() {
    return new Universe(10, 10);
  }

  @Provides
  @Singleton
  MoveShipToFinalPosition provideMoveShipToFinalPosition(TrackingRepository repository,
                                                         Universe universe,
                                                         Ship ship) {
    return new MoveShipToFinalPositionImpl(repository, universe, ship);
  }

  @Provides
  @Singleton
  SubmitData provideSubmitData(TrackingRepository repository, Ship ship) {
    return new SubmitDataImpl(repository, ship);
  }
}
