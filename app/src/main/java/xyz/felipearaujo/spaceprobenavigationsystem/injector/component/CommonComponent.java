package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.CommonModule;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

@Singleton
@Component(modules = CommonModule.class)
public interface CommonComponent {
  Application getApplication();
  TrackingRepository getTrackingRepository();
  Universe getUniverse();
}
