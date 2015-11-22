package xyz.felipearaujo.spaceprobenavigationsystem;

import android.app.Application;

import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.ApplicationComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerApplicationComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ApplicationModule;

public class SpaceProbeNavigationSystem extends Application {
  private static ApplicationComponent sApplicationComponent;
  @Override
  public void onCreate() {
    super.onCreate();

    sApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public static ApplicationComponent getApplicationComponent() {
    return sApplicationComponent;
  }
}
