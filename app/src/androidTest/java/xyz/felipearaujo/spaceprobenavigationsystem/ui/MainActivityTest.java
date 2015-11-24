package xyz.felipearaujo.spaceprobenavigationsystem.ui;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import xyz.felipearaujo.spaceprobenavigationsystem.ui.activity.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
  private MainActivity mainActivity;

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Before
  public void setup() throws Exception {
    super.setUp();
    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    mainActivity = getActivity();
  }
}
