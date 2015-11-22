package xyz.felipearaujo.spaceprobenavigationsystem.entity;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



import java.util.Arrays;
import java.util.List;

import xyz.felipearaujo.spaceprobenavigationsystem.repository.datasource
    .AlienTrackingServiceContract;

@RunWith(AndroidJUnit4.class)
public class AlienShipTest {

  private final static List<AlienTrackingServiceContract.AlienShipAction> ACTION_LIST =
      Arrays.asList(AlienTrackingServiceContract.AlienShipAction.FORWARD,
          AlienTrackingServiceContract.AlienShipAction.RIGHT,
          AlienTrackingServiceContract.AlienShipAction.FORWARD);

  private AlienShip service;

  @Before
  public void setUp(){
    //service = new AlienShip();
  }

  @Test
  public void testTakeActions_success(){
    //service.executeAction(ACTION_LIST);

    //Assert.assertEquals(service.getX(), 1);
    //Assert.assertEquals(service.getY(), 1);
  }

  @Test
  public void testTakeActions_outOfBounds(){
    List<AlienTrackingServiceContract.AlienShipAction> actionList =
        Arrays.asList(
            AlienTrackingServiceContract.AlienShipAction.FORWARD,
            AlienTrackingServiceContract.AlienShipAction.FORWARD,
            AlienTrackingServiceContract.AlienShipAction.FORWARD,
            AlienTrackingServiceContract.AlienShipAction.FORWARD,
            AlienTrackingServiceContract.AlienShipAction.FORWARD,
            AlienTrackingServiceContract.AlienShipAction.FORWARD);
    try {
      //service.executeActions(actionList);
      Assert.fail();
    } catch (IllegalArgumentException expected) {

    }
  }
}
