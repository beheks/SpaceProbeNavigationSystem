package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import org.junit.Before;

import javax.inject.Inject;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.MockModule;

public class TestTrackingRepository {
  @Inject
  TrackingService service;

  @Component(dependencies = MockModule.class)
  public interface RepositoryComponent {
    void inject(TestTrackingRepository repository);
  }

  @Before
  void setup() {

  }
}
