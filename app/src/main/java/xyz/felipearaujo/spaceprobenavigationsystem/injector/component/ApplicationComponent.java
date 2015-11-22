package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ApplicationModule;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  Application getApplication();
  TrackingRepository getAlienTrackingRepository();
  Universe getUniverse();
}
