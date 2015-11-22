package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import java.util.List;

import rx.Observable;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.AlienTrackingServiceContract;

public interface GetData {
  Observable<List<AlienTrackingServiceContract.AlienShipAction>> execute(String email);
}
