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
  protected Button submitButton;

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
                submitButton.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
              }
            });

        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        button.setEnabled(true);
      }

      if (v == submitButton) {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);

        mSubmitData.execute("test@test.com", mAlienShip)
            .subscribe(new ActivitySubscriber<String>(MainActivity.this) {
              @Override
              public void onCompleted() {
                super.onCompleted();
                submitButton.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
              }

              @Override
              public void onNext(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
              }
            });
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
    submitButton = (Button) findViewById(R.id.submitButton);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);

    progressBar.setVisibility(View.GONE);
    submitButton.setVisibility(View.GONE);
    textView.setText(mAlienShip.getPosition().toString());

    button.setOnClickListener(mOnClickListener);
  }


}
