package xyz.felipearaujo.spaceprobenavigationsystem.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.felipearaujo.spaceprobenavigationsystem.R;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerActivityComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;


public class MainActivity extends AppCompatActivity {

  @Bind(R.id.result) protected TextView textView;
  @Bind(R.id.progressBar) protected ProgressBar progressBar;
  @Bind(R.id.button) protected Button button;
  @Bind(R.id.submitButton) protected Button submitButton;

  @Inject MoveShipToFinalPosition mMoveAlienShipToFinalPosition;
  @Inject SubmitData mSubmitData;
  @Inject Ship mShip;

  @OnClick(R.id.button)
  protected void buttonClick() {
    showSpinner();
    button.setEnabled(false);

    mMoveAlienShipToFinalPosition.execute("test@test.com")
        .subscribe(new ActivitySubscriber<Point>(MainActivity.this) {
          @Override
          public void onCompleted() {
            super.onCompleted();

            textView.setText(mShip.getPosition().toString());

            showText();
            showSubmitButton();
          }

          @Override
          public void onError(Throwable e) {
            super.onError(e);
            showText();
            showCalculateButton();
          }
        });
  }

  @OnClick(R.id.submitButton)
  protected void submitButtonClick() {
    showSpinner();
    submitButton.setEnabled(false);

    mSubmitData.execute("test@test.com")
        .subscribe(new ActivitySubscriber<String>(MainActivity.this) {
          @Override
          public void onCompleted() {
            super.onCompleted();
            showText();
            showCalculateButton();
          }

          @Override
          public void onNext(String message) {
            //textView.setText(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
          }

          @Override
          public void onError(Throwable e) {
            super.onError(e);
            showText();
            showSubmitButton();
          }
        });
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DaggerActivityComponent
        .builder()
        .commonComponent(SpaceProbeNavigationSystem.getCommonComponent())
        .activityModule(new ActivityModule())
        .build()
        .inject(this);

    ButterKnife.bind(this);

    showCalculateButton();
    showText();

    textView.setText(mShip.getPosition().toString());
  }

  private void showCalculateButton() {
    submitButton.setVisibility(View.GONE);
    button.setVisibility(View.VISIBLE);
    button.setEnabled(true);
  }

  private void showSubmitButton() {
    submitButton.setVisibility(View.VISIBLE);
    button.setVisibility(View.GONE);
    submitButton.setEnabled(true);
  }

  private void showText() {
    textView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
  }

  private void showSpinner() {
    textView.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
  }


}
