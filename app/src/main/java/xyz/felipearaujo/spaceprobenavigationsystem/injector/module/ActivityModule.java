package xyz.felipearaujo.spaceprobenavigationsystem.injector.module;

import android.graphics.Point;

import dagger.Module;
import dagger.Provides;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.scope.PerActivity;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPositionImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitDataImpl;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.TrackingRepository;

@Module
public class ActivityModule {

  @Provides
  @PerActivity
  MoveShipToFinalPosition provideMoveShipToFinalPosition(
      TrackingRepository repository,
      Universe universe) {
    return new MoveShipToFinalPositionImpl(repository, universe);
  }

  @Provides
  @PerActivity
  SubmitData provideSubmitData(TrackingRepository repository) {
    return new SubmitDataImpl(repository);
  }

  @Provides
  @PerActivity
  Ship provideAlienShip() {
    Point start = new Point(0, 0);
    return new Ship(start);
  }
}
