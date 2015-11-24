package xyz.felipearaujo.spaceprobenavigationsystem.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Arrays;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;

import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.ShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.DaggerTestComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.MockDataSource;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.TestModule;

@RunWith(JUnit4.class)
public class TestMoveShipToFinalPositionImpl {
  @Inject protected TrackingService service;
  @Inject protected MoveShipToFinalPosition moveShipToFinalPosition;

  @Before
  public void setup() {
    DaggerTestComponent
        .builder()
        .testModule(new TestModule())
        .build()
        .inject(this);
  }

  @Test
  public void testMoveShip() {
    Mockito.when(service.getData("")).thenReturn(
        Observable.just(new DirectionsResponse(MockDataSource.getDirectionsList())));

    TestSubscriber<DirectedPosition> test = new TestSubscriber<>();
    moveShipToFinalPosition.execute("").subscribe(test);
    test.assertNoErrors();
    test.assertValue(new DirectedPosition(4, 0, DirectedPosition.Direction.WEST));
  }

  @Test
  public void testMoveShipOutOfUniverse() {
    Mockito.when(service.getData("")).thenReturn(
        Observable.just(new DirectionsResponse(Arrays.asList("LEFT", "FORWARD"))));

    TestSubscriber<DirectedPosition> test = new TestSubscriber<>();
    moveShipToFinalPosition.execute("").subscribe(test);
    test.assertError(ShipOutOfUniverseException.class);
  }

  @Test
  public void testNull() {
    Mockito.when(service.getData("")).thenReturn(
        Observable.just(new DirectionsResponse(Arrays.asList("LEFT", "FORWARD"))));

    TestSubscriber<DirectedPosition> test = new TestSubscriber<>();
    moveShipToFinalPosition.execute("").subscribe(test);
    test.assertError(ShipOutOfUniverseException.class);
  }
}
