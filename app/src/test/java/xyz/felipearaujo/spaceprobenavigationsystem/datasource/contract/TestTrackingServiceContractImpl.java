package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalShipActionException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.DaggerTestComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.TestModule;

@RunWith(JUnit4.class)
public class TestTrackingServiceContractImpl {

  @Inject protected TrackingServiceContract contract;

  @Before
  public void setup() {
    DaggerTestComponent
        .builder()
        .testModule(new TestModule())
        .build()
        .inject(this);
  }

  @Test
  public void testWorkingActionsParse() {
    Assert.assertEquals(contract.parseAction("FORWARD"),
        TrackingServiceContract.ShipAction.FORWARD);
    Assert.assertEquals(contract.parseAction("LEFT"), TrackingServiceContract.ShipAction.LEFT);
    Assert.assertEquals(contract.parseAction("RIGHT"), TrackingServiceContract.ShipAction.RIGHT);
  }

  @Test(expected = IllegalShipActionException.class)
  public void testFailingActionParse() {

    contract.parseAction("BROKEN");
    contract.parseAction(null);
    contract.parseAction("");

    Assert.fail();
  }
}
