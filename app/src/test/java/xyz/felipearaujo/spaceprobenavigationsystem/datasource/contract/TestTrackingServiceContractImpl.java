package xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import xyz.felipearaujo.spaceprobenavigationsystem.exception.IllegalShipActionException;

@RunWith(JUnit4.class)
public class TestTrackingServiceContractImpl {
  String forward = "FORWARD";
  String left = "LEFT";
  String right = "RIGHT";
  String broken = "BROKEN";

  TrackingServiceContract contract;

  @Before
  public void setup() {
    contract = new TrackingServiceContractImpl();
  }

  @Test
  public void testWorkingActionsParse() {
    Assert.assertEquals(contract.parseAction(forward), TrackingServiceContract.ShipAction.FORWARD);
    Assert.assertEquals(contract.parseAction(left), TrackingServiceContract.ShipAction.LEFT);
    Assert.assertEquals(contract.parseAction(right), TrackingServiceContract.ShipAction.RIGHT);
  }

  @Test(expected = IllegalShipActionException.class)
  public void testFailingActionParse() {
    contract.parseAction(broken);
    contract.parseAction(null);
    contract.parseAction("");

    Assert.fail();
  }
}
