package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContractParser;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.local.AlienTrackingServiceLocal;


import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.remote
    .AlienTrackingServiceRemote;


public class AlienTrackingRepositoryLocal implements AlienTrackingRepository {
  protected static AlienTrackingServiceContract sAlienTrackingServiceContract;
  protected static AlienTrackingServiceLocal sAlienTrackingServiceLocal;

  @Inject
  public AlienTrackingRepositoryLocal(AlienTrackingServiceContract contract, AlienTrackingServiceLocal local) {
    sAlienTrackingServiceContract = contract;
    sAlienTrackingServiceLocal = local;
  }

  @Override
  public List<AlienTrackingServiceContract.AlienShipAction> getMovements(String email) {
    List<AlienTrackingServiceContract.AlienShipAction> actions = new ArrayList<>();

    List<String> movements = sAlienTrackingServiceLocal.getMovements();

    for(String movement : movements) {
      actions.add(sAlienTrackingServiceContract.parseAction(movement));
    }

    return actions;
  }

  @Override
  public String submitFinalPosition(String email, int x, int y) {
    return sAlienTrackingServiceLocal.submitFinalPosition(x, y);
  }
}
