package xyz.felipearaujo.spaceprobenavigationsystem;

import android.app.Application;

import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.CommonComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerCommonComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.CommonModule;

public class SpaceProbeNavigationSystem extends Application {
  private static CommonComponent sCommonComponent;
  @Override
  public void onCreate() {
    super.onCreate();

    sCommonComponent = DaggerCommonComponent.builder()
        .commonModule(new CommonModule(this))
        .build();
  }

  public static CommonComponent getCommonComponent() {
    return sCommonComponent;
  }
}
