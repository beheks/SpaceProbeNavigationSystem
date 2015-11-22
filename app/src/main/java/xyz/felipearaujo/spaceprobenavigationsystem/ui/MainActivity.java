package xyz.felipearaujo.spaceprobenavigationsystem.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import xyz.felipearaujo.spaceprobenavigationsystem.R;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerActivityComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveAlienShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;


public class MainActivity extends AppCompatActivity {

  TextView textView;
  Button button;

  @Inject AlienShip mAlienShip;
  @Inject MoveAlienShipToFinalPosition mMoveAlienShipToFinalPosition;
  @Inject SubmitData mSubmitData;

  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      if(v == button) {
        try {
          mMoveAlienShipToFinalPosition.execute("test@test.com", mAlienShip);
          //String msg = mSubmitData.execute("test@test.com", mAlienShip);
          textView.setText(
              mAlienShip.getShipPosition().x + " " + mAlienShip.getShipPosition().y + " " /*+ msg*/
          );
        }
        catch(AlienShipOutOfUniverseException e) {
          Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
          toast.show();
        }
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DaggerActivityComponent
        .builder()
        .applicationComponent(SpaceProbeNavigationSystem.getApplicationComponent())
        .activityModule(new ActivityModule())
        .build()
        .inject(this);

    setContentView(R.layout.activity_main);

    textView = (TextView) findViewById(R.id.result);
    button = (Button) findViewById(R.id.button);

    textView.setText(mAlienShip.getShipPosition().x + " " + mAlienShip.getShipPosition().y);

    button.setOnClickListener(mOnClickListener);
  }


}
