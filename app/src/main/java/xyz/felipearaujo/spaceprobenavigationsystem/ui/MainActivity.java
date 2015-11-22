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

import xyz.felipearaujo.spaceprobenavigationsystem.R;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerActivityComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;


public class MainActivity extends AppCompatActivity {

  protected TextView textView;
  protected ProgressBar progressBar;
  protected Button button;
  protected Button submitButton;

  @Inject MoveShipToFinalPosition mMoveAlienShipToFinalPosition;
  @Inject SubmitData mSubmitData;
  @Inject Ship mShip;

  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      if (v == button) {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        button.setEnabled(false);

        mMoveAlienShipToFinalPosition.execute("test@test.com", mShip)
            .subscribe(new ActivitySubscriber<Point>(MainActivity.this) {
              @Override
              public void onCompleted() {
                super.onCompleted();
                textView.setText(mShip.getPosition().toString());

                submitButton.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                button.setEnabled(true);
              }

              @Override
              public void onError(Throwable e) {
                super.onError(e);
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                button.setEnabled(true);
              }
            });


      }

      else if (v == submitButton) {
        Log.d("TAG", "OK0");
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);

        mSubmitData.execute("test@test.com", mShip)
            .subscribe(new ActivitySubscriber<String>(MainActivity.this) {
              @Override
              public void onCompleted() {
                super.onCompleted();
                submitButton.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
              }

              @Override
              public void onNext(String message) {
                //textView.setText(message);
              }
            });
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mShip = new Ship(new Point(0, 0));

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
    textView.setText(mShip.getPosition().toString());

    button.setOnClickListener(mOnClickListener);
    submitButton.setOnClickListener(mOnClickListener);
  }


}
