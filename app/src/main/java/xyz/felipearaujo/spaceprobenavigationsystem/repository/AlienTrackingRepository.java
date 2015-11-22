package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.List;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

public interface AlienTrackingRepository {
  Observable<List<AlienTrackingServiceContract.AlienShipAction>> getMovements(String email);
  String submitFinalPosition(String email, int x, int y);
}
