package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import android.graphics.Point;

import javax.inject.Inject;

import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Universe;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.ApplicationComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContract;

public class MoveAlienShipToFinalPositionImpl implements MoveAlienShipToFinalPosition {
  private final AlienTrackingRepository mRepository;
  private final Universe mUniverse;

  @Inject
  public MoveAlienShipToFinalPositionImpl(AlienTrackingRepository repository, Universe universe) {
    this.mRepository = repository;
    this.mUniverse = universe;
  }

  @Override
  public Point execute(String email, AlienShip alienShip) throws AlienShipOutOfUniverseException {
    Point shipPosition = alienShip.getShipPosition();

    // TODO: consider using GetData
    for(AlienTrackingServiceContract.AlienShipAction action : mRepository.getMovements(email)) {
      shipPosition = alienShip.moveShip(action);
      if(!mUniverse.assertIfPositionIsValid(shipPosition)) {
        throw new AlienShipOutOfUniverseException(
            "Ship is out of universe bounds! x: " + shipPosition.x + " y: " + shipPosition.y
        );
      }
    }
    return shipPosition;
  }
}
