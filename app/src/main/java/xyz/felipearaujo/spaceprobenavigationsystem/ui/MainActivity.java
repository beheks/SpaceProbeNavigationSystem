package xyz.felipearaujo.spaceprobenavigationsystem.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action1;
import xyz.felipearaujo.spaceprobenavigationsystem.R;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.AlienShip;
import xyz.felipearaujo.spaceprobenavigationsystem.exception.AlienShipOutOfUniverseException;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerActivityComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveAlienShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;


public class MainActivity extends AppCompatActivity {

  protected TextView textView;
  protected ProgressBar progressBar;
  protected Button button;

  @Inject MoveAlienShipToFinalPosition mMoveAlienShipToFinalPosition;
  @Inject SubmitData mSubmitData;
  @Inject AlienShip mAlienShip;

  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      if (v == button) {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        button.setEnabled(false);

        mMoveAlienShipToFinalPosition.execute("test@test.com", mAlienShip).subscribe(
            new ActivitySubscriber<Point>(MainActivity.this) {
              @Override
              public void onCompleted() {
                super.onCompleted();
                textView.setText(mAlienShip.getPosition().toString());
              }

              @Override
              public void onNext(Point point) {
                textView.setText(point.toString());
              }
            });

        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        button.setEnabled(true);
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAlienShip = new AlienShip(new Point(0, 0));

    DaggerActivityComponent
        .builder()
        .applicationComponent(SpaceProbeNavigationSystem.getApplicationComponent())
        .activityModule(new ActivityModule())
        .build()
        .inject(this);

    setContentView(R.layout.activity_main);

    textView = (TextView) findViewById(R.id.result);
    button = (Button) findViewById(R.id.button);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);

    progressBar.setVisibility(View.GONE);
    textView.setText(mAlienShip.getPosition().toString());

    button.setOnClickListener(mOnClickListener);
  }


}
