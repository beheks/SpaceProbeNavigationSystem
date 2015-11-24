package xyz.felipearaujo.spaceprobenavigationsystem.injector;

import javax.inject.Singleton;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TestTrackingRepository;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent {
  void inject(TestTrackingRepository repository);
}