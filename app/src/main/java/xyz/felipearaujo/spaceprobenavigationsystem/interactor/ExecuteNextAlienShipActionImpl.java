package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import java.util.List;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

//TODO not good, currentAction should be alienship responsibility
//TODO BROKEN AF DONT USE
public class ExecuteNextAlienShipActionImpl implements ExecuteNextAlienShipAction {
  private List<AlienTrackingServiceContract.AlienShipAction> actions;
  private int currentAction = 0;

  @Override
  public void execute(AlienShip alienShip) throws AlienShipOutOfUniverseException {
    //alienShip.executeAction(actions.get(currentAction));
    currentAction++;
  }
}
