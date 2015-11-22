package xyz.felipearaujo.spaceprobenavigationsystem.injector.module;

import android.graphics.Point;

import dagger.Module;
import dagger.Provides;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.scope.PerActivity;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.ExecuteNextAlienShipAction;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.ExecuteNextAlienShipActionImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.GetData;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.GetDataImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveAlienShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveAlienShipToFinalPositionImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitDataImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;

@Module
public class ActivityModule {

  @Provides
  @PerActivity
  ExecuteNextAlienShipAction provideExecuteNextAlienShipAction() {
    return new ExecuteNextAlienShipActionImpl();
  }

  @Provides
  @PerActivity
  GetData provideGetData(AlienTrackingRepository repository) {
    return new GetDataImpl(repository);
  }

  @Provides
  @PerActivity
  MoveAlienShipToFinalPosition provideMoveAlienShipToFinalPosition(
      AlienTrackingRepository repository,
      Universe universe) {
    return new MoveAlienShipToFinalPositionImpl(repository, universe);
  }

  @Provides
  @PerActivity
  SubmitData provideSubmitData(AlienTrackingRepository repository) {
    return new SubmitDataImpl(repository);
  }

  @Provides
  @PerActivity
  AlienShip provideAlienShip() {
    Point start = new Point(0, 0);
    return new AlienShip(start);
  }
}
