package xyz.felipearaujo.spaceprobenavigationsystem;

import android.graphics.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import xyz.felipearaujo.spaceprobenavigationsystem.interactor.util.ShipMovementUtil;

@RunWith(JUnit4.class)
public class TestShipMovementUtil {
  @Before
  public void setup() {
  }
  @Test
  public void testMove() {
    Point point = new Point(0, 0);
    Point move = new Point(0, 1);/*
    Point result = ShipMovementUtil.moveShip(point, move);
    Assert.assertThat().isEqualsTo();*/
  }
}
