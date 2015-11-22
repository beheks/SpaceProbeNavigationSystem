package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ApplicationModule;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local
    .AlienTrackingServiceLocal;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote
    .AlienTrackingServiceRemote;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  Application getApplication();

  AlienTrackingServiceContract getAlienTrackingServiceContract();
  AlienTrackingServiceLocal getAlienTrackingServiceLocal();
  AlienTrackingServiceRemote getAlienTrackingServiceRemote();
  AlienTrackingRepository getAlienTrackingRepository();

  Universe getUniverse();
}
