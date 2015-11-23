package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import android.app.Application;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

@Component(modules = CommonComponentTest.class)
public interface CommonComponentTest {
  Application getApplication();
  TrackingRepository getAlienTrackingRepository();
  Universe getUniverse();
}
