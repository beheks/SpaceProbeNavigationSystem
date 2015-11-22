package xyz.felipearaujo.spaceprobenavigationsystem.injector.component;

import dagger.Component;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.scope.PerActivity;
import xyz.felipearaujo.spaceprobenavigationsystem.ui.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(MainActivity activity);

  AlienShip getAlienShip();
}
