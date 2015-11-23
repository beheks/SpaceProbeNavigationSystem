package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



import java.util.Arrays;
import java.util.List;

import xyz.felipearaujo.spaceprobenavigationsystem.datasource.contract.TrackingServiceContract;

@RunWith(AndroidJUnit4.class)
public class ShipTest {

  private final static List<TrackingServiceContract.ShipAction> ACTION_LIST =
      Arrays.asList(TrackingServiceContract.ShipAction.FORWARD,
          TrackingServiceContract.ShipAction.RIGHT,
          TrackingServiceContract.ShipAction.FORWARD);

  private Ship service;

  @Before
  public void setUp(){
    //service = new Ship();
  }

  @Test
  public void testTakeActions_success(){
    //service.executeAction(ACTION_LIST);

    //Assert.assertEquals(service.getX(), 1);
    //Assert.assertEquals(service.getY(), 1);
  }

  @Test
  public void testTakeActions_outOfBounds(){
    List<TrackingServiceContract.ShipAction> actionList =
        Arrays.asList(
            TrackingServiceContract.ShipAction.FORWARD,
            TrackingServiceContract.ShipAction.FORWARD,
            TrackingServiceContract.ShipAction.FORWARD,
            TrackingServiceContract.ShipAction.FORWARD,
            TrackingServiceContract.ShipAction.FORWARD,
            TrackingServiceContract.ShipAction.FORWARD);
    try {
      //service.executeActions(actionList);
      Assert.fail();
    } catch (IllegalArgumentException expected) {
      System.out.println(expected.getMessage());
    }
  }
}
