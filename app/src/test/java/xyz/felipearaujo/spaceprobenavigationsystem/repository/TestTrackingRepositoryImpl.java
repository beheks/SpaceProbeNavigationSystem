package xyz.felipearaujo.spaceprobenavigationsystem.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;

import xyz.felipearaujo.spaceprobenavigationsystem.datasource.TrackingService;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.DirectionsResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.SubmissionResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.WrongAnswerException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.DaggerTestComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.MockDataSource;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.TestModule;

@RunWith(JUnit4.class)
public class TestTrackingRepositoryImpl {

  @Inject private TrackingRepository repository;
  @Inject private TrackingServiceContract contract;
  @Inject private TrackingService service;
  private List<TrackingServiceContract.ShipAction> expected;



  @Before
  public void setup() {
    DaggerTestComponent
        .builder()
        .testModule(new TestModule())
        .build()
        .inject(this);

    List<String> parseActions = MockDataSource.getDirectionsList();
    expected = new ArrayList<>();
    for(String action : parseActions) {
      expected.add(contract.parseAction(action));
    }
  }

  @Test
  public void testGetData() {
    Mockito.when(service.getData("")).thenReturn(
        Observable.just(new DirectionsResponse(MockDataSource.getDirectionsList())));

    TestSubscriber<List<TrackingServiceContract.ShipAction>> test = new TestSubscriber<>();

    repository.getMovements("").subscribe(test);
    test.assertNoErrors();
    test.assertValue(expected);
  }

  @Test
  public void testSubmitCorrectData() {
    Mockito.when(service.submitData("", 0, 2)).thenReturn(
        Observable.just(new SubmissionResponse(TrackingServiceContract.RIGHT_ANSWER_CODE, "OK")));

    TestSubscriber<String> test = new TestSubscriber<>();

    repository.submitFinalPosition("", 0, 2).subscribe(test);
    test.assertNoErrors();
    test.assertValue("OK");
  }

  @Test
  public void testSubmitWrongData() {

    Mockito.when(service.submitData("", 0, 2)).thenReturn(
        Observable.just(new SubmissionResponse(TrackingServiceContract.WRONG_ANSWER_CODE, "ERR")));

    TestSubscriber<String> test = new TestSubscriber<>();

    repository.submitFinalPosition("", 0, 2).subscribe(test);
    test.assertError(WrongAnswerException.class);
  }
}
