package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import javax.inject.Inject;

import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.repository.AlienTrackingRepository;

public class SubmitDataImpl implements SubmitData {

  AlienTrackingRepository mRepository;

  @Inject
  public SubmitDataImpl(AlienTrackingRepository repository) {
    mRepository = repository;
  }

  @Override
  public String execute(String email, AlienShip alienShip) {
    return mRepository.submitFinalPosition(email,
        alienShip.getShipPosition().x,
        alienShip.getShipPosition().y
    );
  }
}
