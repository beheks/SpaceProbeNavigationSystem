package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import android.app.Application;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

@Component(modules = TestApplicationComponent.class)
public interface TestApplicationComponent {
  Application getApplication();
  TrackingRepository getAlienTrackingRepository();
  Universe getUniverse();
}
