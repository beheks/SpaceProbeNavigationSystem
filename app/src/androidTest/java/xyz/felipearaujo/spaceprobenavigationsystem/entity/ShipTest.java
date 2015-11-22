package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



import java.util.Arrays;
import java.util.List;

import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource.TrackingServiceContract;

@RunWith(AndroidJUnit4.class)
public class ShipTest {

  private final static List<TrackingServiceContract.AlienShipAction> ACTION_LIST =
      Arrays.asList(TrackingServiceContract.AlienShipAction.FORWARD,
          TrackingServiceContract.AlienShipAction.RIGHT,
          TrackingServiceContract.AlienShipAction.FORWARD);

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
    List<TrackingServiceContract.AlienShipAction> actionList =
        Arrays.asList(
            TrackingServiceContract.AlienShipAction.FORWARD,
            TrackingServiceContract.AlienShipAction.FORWARD,
            TrackingServiceContract.AlienShipAction.FORWARD,
            TrackingServiceContract.AlienShipAction.FORWARD,
            TrackingServiceContract.AlienShipAction.FORWARD,
            TrackingServiceContract.AlienShipAction.FORWARD);
    try {
      //service.executeActions(actionList);
      Assert.fail();
    } catch (IllegalArgumentException expected) {

    }
  }
}
