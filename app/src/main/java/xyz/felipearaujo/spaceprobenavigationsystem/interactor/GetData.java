package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import java.util.List;

import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

public interface GetData {
  List<AlienTrackingServiceContract.AlienShipAction> execute(String email);
}
