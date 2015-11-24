package xyz.felipearaujo.spaceprobenavigationsystem.injector;

import javax.inject.Singleton;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract
    .TestTrackingServiceContractImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.TestMoveShipToFinalPositionImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.TestSubmitDataImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TestTrackingRepositoryImpl;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent {
  void inject(TestTrackingRepositoryImpl repository);
  void inject(TestMoveShipToFinalPositionImpl moveShipToFinalPosition);
  void inject(TestSubmitDataImpl submitData);
  void inject(TestTrackingServiceContractImpl contract);
}