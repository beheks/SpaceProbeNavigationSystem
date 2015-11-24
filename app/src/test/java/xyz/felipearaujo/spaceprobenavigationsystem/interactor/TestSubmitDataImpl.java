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
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;
import xyz.felipearaujo.spaceprobenavigationsystem.datasource.entity.SubmissionResponse;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.WrongAnswerException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.DaggerTestComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.TestModule;

@RunWith(JUnit4.class)
public class TestSubmitDataImpl {
  @Inject private TrackingService service;
  @Inject private SubmitData submitData;

  @Before
  public void setup() {
    DaggerTestComponent
        .builder()
        .testModule(new TestModule())
        .build()
        .inject(this);
  }

  @Test
  public void testSubmitAnswer() {
    Mockito.when(service.submitData("", 0, 0)).thenReturn(
        Observable.just(new SubmissionResponse(TrackingServiceContract.RIGHT_ANSWER_CODE, "OK")));

    TestSubscriber<String> test = new TestSubscriber<>();
    submitData.execute("").subscribe(test);
    test.assertNoErrors();
    test.assertValue("OK");
  }

  @Test
  public void testSubmitWrongAnswer() {
    Mockito.when(service.submitData("",0,0)).thenReturn(
        Observable.just(new SubmissionResponse(TrackingServiceContract.WRONG_ANSWER_CODE, "ERR")));

    TestSubscriber<String> test = new TestSubscriber<>();
    submitData.execute("").subscribe(test);
    test.assertError(WrongAnswerException.class);
  }
}
