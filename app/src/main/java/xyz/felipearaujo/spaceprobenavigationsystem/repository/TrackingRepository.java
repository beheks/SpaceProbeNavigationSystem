package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import java.util.List;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;

public interface TrackingRepository {
  Observable<List<TrackingServiceContract.AlienShipAction>> getMovements(String email);
  Observable<String> submitFinalPosition(String email, int x, int y);
}
