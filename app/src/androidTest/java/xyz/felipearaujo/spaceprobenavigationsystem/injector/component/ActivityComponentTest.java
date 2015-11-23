package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import dagger.Component;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModuleTest;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.scope.PerActivity;
import xyz.felipearaujo.spaceprobenavigationsystem.ui.MainActivity;

@PerActivity
@Component(dependencies = CommonComponentTest.class, modules = ActivityModuleTest.class)
public interface ActivityComponentTest {
  void inject(MainActivity activity);

  Ship getAlienShip();
}
